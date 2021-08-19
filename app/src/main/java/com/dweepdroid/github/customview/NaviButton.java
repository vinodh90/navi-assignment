package com.dweepdroid.github.customview;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.Button;

import com.dweepdroid.github.R;

public class NaviButton extends androidx.appcompat.widget.AppCompatButton {
	private String mTtfType;

	public NaviButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);

		assignTtfType(attrs);
		init();
	}

	public NaviButton(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public NaviButton(Context context) {
		this(context, null, 0);
	}

	private void assignTtfType(AttributeSet attrs) {
		if (attrs != null) {
			mTtfType = attrs.getAttributeValue(
					"http://schemas.android.com/apk/res-auto", "ttf_type");
		}
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
			} else {
				font = Typeface.createFromAsset(getContext().getAssets(), "font/Roboto-Regular.ttf");
			}
			setTypeface(font);
		}
	}
}
