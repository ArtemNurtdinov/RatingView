package com.nefrit.ratingview.gui;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.nefrit.ratingview.R;
import com.nefrit.ratingview.model.Scale;

import java.util.Arrays;
import java.util.List;

public class RatingView extends LinearLayout {

	private RatingItem[] ratingItems;

	private int scalesCount;
	private boolean needStars;

	private int maxValue;

	public RatingView(Context context) {
		super(context);

		scalesCount = 0;

		init(context);
	}

	public RatingView(Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);

		if (attrs != null) {
			TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.RatingView);
			scalesCount = attributes.getInteger(R.styleable.RatingView_scales, 0);
			needStars = attributes.getBoolean(R.styleable.RatingView_stars, false);

			attributes.recycle();
		}

		init(context);
	}

	private void init(Context context) {

		setOrientation(LinearLayout.VERTICAL);

	}

	public void setScales(List<Scale> scales) {
		removeAllViews();
		scalesCount = scales.size();

		if (scales.isEmpty()) {
			return;
		}

		ratingItems = new RatingItem[scalesCount];

		maxValue = scales.get(0).getCount();

		for (Scale scale : scales) {
			if (scale.getCount() > maxValue) {
				maxValue = scale.getCount();
			}
		}

		for (int i = 0; i < scalesCount ; i++) {
			RatingItem ratingItem = new RatingItem(getContext());
			ratingItems[i] = ratingItem;
			addView(ratingItem);
			ratingItem.setScale(scales.get(i));
			if (needStars) {
				ratingItem.showStar();
			} else {
				ratingItem.hideStar();
			}
		}

		post(new Runnable() {
			@Override
			public void run() {
				updateWidth();
			}
		});
	}

	int getMaxValue() {
		return maxValue;
	}

	public RatingItem getRatingItem(int position) {
		return ratingItems[position];
	}

	public int getScalesCount() {
		return scalesCount;
	}

	private void updateWidth() {
		int maxWidth = ratingItems[0].getValueTextView().getWidth();
		int maxCountWidth = ratingItems[0].getValueTextView().getWidth();

		for (RatingItem ratingItem : ratingItems) {
			if (ratingItem.getValueTextView().getWidth() > maxWidth) {
				maxWidth = ratingItem.getValueTextView().getWidth();
			}
			if (ratingItem.getCountTextView().getWidth() > maxCountWidth) {
				maxCountWidth = ratingItem.getCountTextView().getWidth();
			}
		}

		for (RatingItem ratingItem : ratingItems) {
			ratingItem.getValueTextView().setWidth(maxWidth);
			ratingItem.getCountTextView().setWidth(maxCountWidth);
		}
	}

	public List<RatingItem> getItems() {
		return Arrays.asList(ratingItems);
	}
}