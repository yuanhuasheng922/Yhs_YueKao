package com.example.yuan.yhs_yuekao.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.yuan.yhs_yuekao.R;

public class CustomView extends LinearLayout {
    public CustomView(Context context) {
        super(context);
        init();
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomView(Context context,  AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
                View view= View.inflate(getContext(),R.layout.custom_item,null);
                EditText editText =findViewById(R.id.custom_editText);
                addView(view);
    }
}
