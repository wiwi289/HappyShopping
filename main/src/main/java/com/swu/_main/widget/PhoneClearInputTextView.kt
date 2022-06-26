package com.swu._main.widget

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.graphics.drawable.Drawable
import android.os.Build
import android.text.Editable
import android.text.SpannableStringBuilder
import android.text.TextUtils
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.annotation.RequiresApi
import com.google.android.material.textfield.TextInputEditText
import com.swu._main.R
import java.util.regex.Pattern

/**
 * Created by chenxiong
 * date 11/27/21
 */
/*
 * 自定义电话输入框实现 文本末尾画×
 */
@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
@SuppressLint("UseCompatLoadingForDrawables")
class PhoneClearInputTextView @JvmOverloads constructor(
    context: Context,
    attr: AttributeSet? = null,
    style: Int = 0
) : TextInputEditText(context, attr, style), TextWatcher {

    private var deleteIcon: Drawable

    private val normalLinePaint = Paint().apply {
        color = Color.parseColor("#D8D8D8")
        this.style = Paint.Style.STROKE
        strokeWidth = 5f
    }
    private val focusedLinePaint = Paint().apply {
        color = Color.parseColor("#71C9CE")
        this.style = Paint.Style.STROKE
        strokeWidth = 5f
    }
    private var hasFocus = false

    private val mDeleteIconSize = 50

    //扩展文本监听
    private var extraTextWatcher: TextWatcher? = null


    init {
        deleteIcon = context.getDrawable(R.drawable.delete)!!
        deleteIcon.setBounds(0, 0, mDeleteIconSize, mDeleteIconSize)
        parseAttrs(attr)
        addTextChangedListener(this)
    }

    private fun parseAttrs(attr: AttributeSet?) {
        if (attr == null) return
        val typedArray = context.obtainStyledAttributes(attr,R.styleable.PhoneClearInputTextView)
        typedArray.recycle()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (hasFocus) {
            canvas?.drawLine(0f,height-5f,width.toFloat(),height-5f,focusedLinePaint)
        } else {
            canvas?.drawLine(0f,height-5f,width.toFloat(),height-5f,normalLinePaint)
        }

    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event == null) return super.onTouchEvent(event)
        val point = Point(event.x.toInt(), event.y.toInt())
        val rect = Rect(measuredWidth - mDeleteIconSize, 0, measuredWidth, measuredHeight)
        if (rect.contains(point.x,point.y)) {
            setText("")
        }
        return super.onTouchEvent(event)
    }



    fun getPhoneNumber() = SpannableStringBuilder(editableText).apply {
                delete(3,4)
                delete(7,8)
        }.toString()

    private fun isMobilePhone(): Boolean {
        val number = getPhoneNumber()
        val regExp = "[1][3456789]\\d{9}"
        var p = Pattern.compile(regExp)
        val m = p.matcher(number)//"[1]"代表第1位为数字1，"[34578]"代表第二位可以为3、4、5、7、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        return if (TextUtils.isEmpty(number)) {
            false
        } else {
            m.matches()
        }
    }

    fun isValidPhoneNum() = if (editableText.length != 13) false else isMobilePhone()

    override fun onFocusChanged(focused: Boolean, direction: Int, previouslyFocusedRect: Rect?) {
        hasFocus = focused
        invalidate()
        super.onFocusChanged(focused, direction, previouslyFocusedRect)
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        extraTextWatcher?.beforeTextChanged(s,start,count, after)
        if (TextUtils.isEmpty(text)) {
            setCompoundDrawables(null,null,null,null)
        } else {
            setCompoundDrawables(null,null,deleteIcon,null)
        }
    }

    override fun afterTextChanged(s: Editable?) {
        extraTextWatcher?.afterTextChanged(s)
        postInvalidate()
    }

    override fun onTextChanged(
        text: CharSequence?,
        start: Int,
        lengthBefore: Int,
        lengthAfter: Int
    ) {
        extraTextWatcher?.onTextChanged(text,start,lengthBefore,lengthAfter)
        if (hasFocus) {
            if (TextUtils.isEmpty(text)) {
                setCompoundDrawables(null,null,null,null)
            } else {
                setCompoundDrawables(null,null,deleteIcon,null)
            }
        }
    }

    fun setExtraTextWatcher(textWatcher: TextWatcher) {
        extraTextWatcher = textWatcher
    }

}