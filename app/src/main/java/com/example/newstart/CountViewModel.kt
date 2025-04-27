package com.example.newstart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newstart.adapter.Person

class CountViewModel(): ViewModel() {
    private var _count = MutableLiveData<List<Person>>(listOf())
    val count get() = _count

    private val filterModel = FilterModel()

    fun touchButton(text: String) {
       // _count.value = _count.value?.plus(1)
        val filterData = filterModel.getPersons().filter {
            it.name.contains(text)
        }
        _count.value = filterData
    }
}

class FilterModel() {
    private val persons = listOf(
        Person("asd", 0, 2.0),
        Person("qwe", 1, 2.0),
        Person("zxc", 2, 2.0),
    )

    fun getPersons(): List<Person> = persons
}