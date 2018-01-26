package com.nefrit.ratingview.gui;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nefrit.ratingview.R;
import com.nefrit.ratingview.model.Scale;

public class RatingItem extends LinearLayout {

	private ImageView imgStar;

	private TextView tvCount;
	private TextView tvMark;

	private int progressColor;
	private int marksCount;

	private String markValue;

	private View progressViewLeft;
	private View progressViewRight;

	RatingItem(Context context) {
		super(context);

		this.progressColor = getResources().getColor(R.color.mark_green);
		this.markValue = "";
		this.marksCount = 0;

		init(context);
	}

	private void init(Context context) {
		View.inflate(context, R.layout.item_rating, this);

		tvMark = findViewById(R.id.tv_mark);
		tvCount = findViewById(R.id.tv_count);
		progressViewLeft = findViewById(R.id.view_progress_left);
		progressViewRight = findViewById(R.id.view_progress_right);
		imgStar = findViewById(R.id.img_star);
	}

	public void setScale(Scale scale) {

		this.progressColor = scale.getColor();
		this.markValue = scale.getMark();
		this.marksCount = scale.getCount();

		updateMarksCount();
		updateProgressColor();
		updateMarksValue();
	}

	public void setProgressColor(int color) {
		this.progressColor = color;
		updateProgressColor();
	}

	public void setMarksValue(String value) {
		this.markValue = value;
		updateMarksValue();
	}

	private void updateProgressColor() {
		progressViewLeft.setBackgroundColor(progressColor);
	}

	private void updateMarksValue() {
		tvMark.setText(markValue);
	}

	private void updateMarksCount() {
		tvCount.setText(String.valueOf(marksCount));
		RatingView view = ((RatingView) getParent());
		int maxValue = view.getMaxValue();
		if (maxValue == 0) {
			return;
		}
		int percent = (marksCount * 100) / maxValue;

		progressViewLeft.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, percent));
		progressViewRight.setLayoutParams(new LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 100 - percent));
	}

	public void showStar() {
		imgStar.setVisibility(View.VISIBLE);
	}

	public void hideStar() {
		imgStar.setVisibility(View.GONE);
	}

	public TextView getValueTextView() {
		return tvMark;
	}

	public TextView getCountTextView() {
		return tvCount;
	}

	public ImageView getImageStar() {
		return imgStar;
	}
}