package com.example.newstart

import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity() {

    private lateinit var search: EditText
    private lateinit var results: TextView
    private val disposables = CompositeDisposable()

    private val list = listOf(
        "dima", "action", "pandora", "plmnuiwjdij"
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        search = findViewById(R.id.search_edit_text)
        results = findViewById(R.id.results_view)

        setupSearchObservable()
    }

    private fun setupSearchObservable() {
        Observable.create<String> { emitter ->
            val watcher = search.addTextChangedListener(
                afterTextChanged = { editable ->
                    editable?.toString()?.let { emitter.onNext(it) }
                }
            )

            emitter.setCancellable {
                search.removeTextChangedListener(watcher)
            }
        }
            .debounce(300, TimeUnit.MILLISECONDS)
            .switchMap { query ->
                simulateServerRequest(query)
                    .subscribeOn(Schedulers.io())
                    .onErrorResumeNext { error: Throwable ->
                        println("Error occurred: ${error.message}")
                        Observable.just(listOf("No results due to error"))
                    }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { result ->
                results.text = result.joinToString("\n")
            }
            .let { disposables.add(it) }
    }

    private fun simulateServerRequest(query: String): Observable<List<String>> {
        return Observable.fromCallable {
            Thread.sleep((200..800).random().toLong())
            if (query == "error") {
                throw RuntimeException("Server error")
            }
            list.filter { it.contains(query, ignoreCase = true) }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.clear()
    }

}