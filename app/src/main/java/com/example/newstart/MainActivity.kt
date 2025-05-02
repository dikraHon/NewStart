package com.example.newstart

import android.animation.ValueAnimator
import android.os.Bundle
import android.view.animation.DecelerateInterpolator
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val circularProgress = findViewById<CircularProgressView>(R.id.circularProgress)

        val progressAnimator = ValueAnimator.ofFloat(0f, 75f).apply {
            duration = 2000
            repeatCount = 40
            interpolator = DecelerateInterpolator()
            addUpdateListener { animation ->
                circularProgress.progress = animation.animatedValue as Float
            }
        }

        progressAnimator.start()
    }
}