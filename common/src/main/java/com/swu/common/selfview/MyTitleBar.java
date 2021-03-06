package com.swu.common.selfview;
import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;

import com.swu.common.util.BackgroundUtils;
import com.swu.lib_common.R;


/**
 * Created by 刘金豪 on 2021/1/15
 * desc: 自定义标题栏
 */
public class MyTitleBar extends LinearLayout {
    /**
     * 自定义属性
     */
    private int imgleft;
    private int imgright;

    private String texttitle;
    private String textright;
    private boolean isHeadBack;
    private ImageView ivLeft, ivRight;
    private TextView title, tvRight;
    private FrameLayout frRight;
    private FrameLayout flLeft;

    public MyTitleBar(Context context) {
        this(context, null);
    }

    public MyTitleBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public MyTitleBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }



    private void init(Context context, AttributeSet attrs) {
        inflate(context, R.layout.title_bar, this);
        findViews();

        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MyTitleBar);
            imgleft = a.getResourceId(R.styleable.MyTitleBar_imgleft, -1);
            imgright = a.getResourceId(R.styleable.MyTitleBar_imgright, -1);
            texttitle = a.getString(R.styleable.MyTitleBar_texttitle);
            textright = a.getString(R.styleable.MyTitleBar_textright);
            isHeadBack = a.getBoolean(R.styleable.MyTitleBar_is_head_back,true);
            a.recycle();
        }

        if (imgleft != -1) {
            ivLeft.setImageResource(imgleft);
            setRippleBgLeft();
        }

        //如果能返回 则为true 默认为true
        if(isHeadBack){
            ivLeft.setVisibility(VISIBLE);
        }else {
            ivLeft.setVisibility(GONE);
        }

        if (!TextUtils.isEmpty(texttitle)) {
            title.setText(texttitle);
        }

        if (imgright != -1) {
            ivRight.setImageResource(imgright);

            setRippleBgRight();
        }

        if (!TextUtils.isEmpty(textright)) {
            tvRight.setText(textright);
            setRippleBgRight();
        }

        flLeft.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onMyTitleBarListener != null) {
                    onMyTitleBarListener.onLeftClick();
                }
            }
        });

        frRight.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onMyTitleBarListener != null) {
                    onMyTitleBarListener.onRightClick(view);
                }
            }
        });

    }

    private void setRippleBgLeft() {

        BackgroundUtils.setRippleBackground(flLeft);
    }

    private void setRippleBgRight() {

        BackgroundUtils.setRippleBackground(frRight);
    }



    public void setTextRight(int right) {
        if (tvRight != null) {
            tvRight.setText(right);
        }

        setRippleBgRight();
    }

    private void findViews() {
        ivLeft = findViewById(R.id.icon_left);
        ivRight = findViewById(R.id.icon_right);
        title = findViewById(R.id.tv_title);
        tvRight = findViewById(R.id.tv_right);
        frRight = findViewById(R.id.llt_right);
        flLeft = findViewById(R.id.fl_left);
    }

    public interface OnMyTitleBarListener {
        void onLeftClick();

        void onRightClick(View v);

    }

    OnMyTitleBarListener onMyTitleBarListener;

    public void setOnMyTitleBarListener(OnMyTitleBarListener listener) {
        this.onMyTitleBarListener = listener;
    }

    public void setTitle(String txt) {
        if (title != null && !TextUtils.isEmpty(txt)) {
            title.setText(txt);
        }
    }

    public TextView getTitle() {
        return title;
    }

    public void isShowRight(boolean isShow){
        if(isShow){
            tvRight.setVisibility(VISIBLE);
        }else {
            tvRight.setVisibility(GONE);
        }
    }
}