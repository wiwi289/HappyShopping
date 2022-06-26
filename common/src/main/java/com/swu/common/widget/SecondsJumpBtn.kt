package com.swu.common.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Build
import android.os.CountDownTimer
import android.util.AttributeSet
import android.view.View
import androidx.annotation.RequiresApi
import com.swu.lib_common.R

class SecondsJumpBtn @JvmOverloads constructor(context: Context, attr: AttributeSet? = null, style: Int = 0)
    : View(context, attr, style){

    private var mTextSize = 10
    private var textColor = Color.WHITE
    private var bgColor = resources.getColor(R.color.jump_btn_bg)

    private var seconds = 3 * 1000L

    private var mText = ""

    private var finishCallBack: (()->Unit)? = null

    private val bgPaint: Paint by lazy {
        Paint().apply {
            this.style = Paint.Style.FILL
            color = bgColor
        }
    }

    private val textPaint: Paint by lazy {
        Paint().apply {
            color = textColor
            textSize = mTextSize.toFloat()
            textAlign = Paint.Align.CENTER
        }
    }

    //倒计时timer
    private val countDownTimer = object : CountDownTimer(seconds,1000) {
        override fun onTick(millisUntilFinished: Long) {
            val time = millisUntilFinished + 1000
            val hintCount = (time / 1000).toInt()
            mText = "跳过$hintCount"
            invalidate()
        }

        override fun onFinish() {
            finishCallBack?.invoke()
        }
    }

    init {
        parseAttr(attr)
        setOnClickListener {
            countDownTimer.cancel()
            finishCallBack?.invoke()
        }
    }

    private fun parseAttr(attr: AttributeSet?) {
        val typedArr = context.obtainStyledAttributes(attr, R.styleable.SecondsJumpBtn)
        textColor = typedArr.getColor(R.styleable.SecondsJumpBtn_text_color, Color.WHITE)
        typedArr.getInt(R.styleable.SecondsJumpBtn_seconds, 3).let { time->
            seconds = time * 1000L
            mText = "跳过$time"
        }
        mTextSize = typedArr.getInt(R.styleable.SecondsJumpBtn_text_size, 10)
        bgColor = typedArr.getColor(R.styleable.SecondsJumpBtn_bg_color, Color.GRAY)
        typedArr.recycle()
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onDraw(canvas: Canvas?) {
        canvas?.drawRoundRect(
            0f, 0f, measuredWidth.toFloat(), measuredHeight.toFloat(),
            measuredWidth / 2f, measuredWidth / 2f, bgPaint
        )
        val metrics = textPaint.fontMetrics
        val space = (metrics.descent-metrics.ascent)/2f-metrics.descent
        canvas?.drawText(mText, measuredWidth / 2f, measuredHeight / 2 + space, textPaint)
    }

    fun startCountDown() {
        countDownTimer.start()
    }

    fun setCountFinishListener(finishCallBack: ()->Unit) {
        this.finishCallBack = finishCallBack
    }

}