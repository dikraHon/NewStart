package com.example.newstart

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import kotlin.properties.Delegates

class CircularProgressView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val rectF = RectF()
    private var startAngle = -90f

    var progressColor by Delegates.observable(Color.BLUE) { _, _, _ -> invalidate() }
    var circleBackgroundColor by Delegates.observable(Color.LTGRAY) { _, _, _ -> invalidate() }
    var strokeWidth by Delegates.observable(20f) { _, _, _ -> invalidate() }
    var progress by Delegates.observable(0f) { _, _, _ -> invalidate() }
    var maxProgress by Delegates.observable(100f) { _, _, _ -> invalidate() }

    init {
        attrs?.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.CircularProgressView)
            progressColor = typedArray.getColor(R.styleable.CircularProgressView_progressColor, Color.BLUE)
            circleBackgroundColor = typedArray.getColor(R.styleable.CircularProgressView_backgroundColor, Color.LTGRAY)
            strokeWidth = typedArray.getDimension(R.styleable.CircularProgressView_strokeWidth, 20f)
            progress = typedArray.getFloat(R.styleable.CircularProgressView_progress, 0f)
            maxProgress = typedArray.getFloat(R.styleable.CircularProgressView_maxProgress, 100f)
            typedArray.recycle()
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        paint.color = circleBackgroundColor
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = strokeWidth
        paint.strokeCap = Paint.Cap.ROUND
        canvas.drawArc(rectF, 0f, 360f, false, paint)
        paint.color = progressColor
        val sweepAngle = 360f * (progress / maxProgress)
        canvas.drawArc(rectF, startAngle, sweepAngle, false, paint)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        val size = minOf(w, h)
        val padding = strokeWidth / 2
        rectF.set(padding, padding, size - padding, size - padding)
    }
}