package com.dweepdroid.github.customview;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import com.dweepdroid.github.R;

public class NaviTextView extends androidx.appcompat.widget.AppCompatTextView {
    private String mTtfType;

    public NaviTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        assignTtfType(attrs);
        init();
    }

    public NaviTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NaviTextView(Context context) {
        this(context, null, 0);
    }

    private void assignTtfType(AttributeSet attrs) {
        if (attrs != null) {
            mTtfType = attrs.getAttributeValue(
                    "http://schemas.android.com/apk/res-auto", "ttf_type");
        }
    }

    public void setTTFType(String ttfType) {
        mTtfType = ttfType;
        init();
        invalidate();
    }

    /**
     * Initialises the font type
     */
    private void init() {
        if (!isInEditMode()) {
            Typeface font = null;
            Resources res = getContext().getResources();

            if (mTtfType != null && mTtfType.equalsIgnoreCase(res.getString(R.string.light))) {
                font = Typeface.createFromAsset(getContext().getAssets(), "font/Roboto-Light.ttf.ttf");
            } else if (mTtfType != null && mTtfType.equalsIgnoreCase(res.getString(R.string.medium))) {
                font = Typeface.createFromAsset(getContext().getAssets(), "font/Roboto-Medium.ttf.ttf");
            } else if (mTtfType != null && mTtfType.equalsIgnoreCase(res.getString(R.string.bold))){
                font = Typeface.createFromAsset(getContext().getAssets(), "font/Roboto-Bold.ttf");
            } else if (mTtfType != null && mTtfType.equalsIgnoreCase(res.getString(R.string.italic))){
                font = Typeface.createFromAsset(getContext().getAssets(), "font/Roboto-BlackItalic.ttf");
            }  else {
                font = Typeface.createFromAsset(getContext().getAssets(), "font/Roboto-Regular.ttf");
            }
            setTypeface(font);
        }
    }
}

