package com.nefrit.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import com.nefrit.ratingview.gui.RatingItemView;
import com.nefrit.ratingview.gui.RatingView;
import com.nefrit.ratingview.model.Scale;

import java.util.ArrayList;
import java.util.List;

public class SampleActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sample);

		// Five scales
		RatingView ratingView = findViewById(R.id.view_rating);

		List<Scale> scales = new ArrayList<>();
		scales.add(new Scale("5", 172, getResources().getColor(R.color.mark_green)));
		scales.add(new Scale("4", 42, getResources().getColor(R.color.mark_light_green)));
		scales.add(new Scale("3", 7, getResources().getColor(R.color.mark_yellow)));
		scales.add(new Scale("2", 2, getResources().getColor(R.color.mark_orange)));
		scales.add(new Scale("1", 3, getResources().getColor(R.color.mark_red)));

		ratingView.setScales(scales);

		// Three scales
		RatingView ratingView2 = findViewById(R.id.view_rating2);

		List<Scale> scales2 = new ArrayList<>();
		scales2.add(new Scale("One", 78, getResources().getColor(R.color.mark_green)));
		scales2.add(new Scale("Two", 52, getResources().getColor(R.color.mark_light_green)));
		scales2.add(new Scale("Three", 36, getResources().getColor(R.color.mark_yellow)));

		ratingView2.setScales(scales2);

		// Using constructor
		RatingView ratingView1 = new RatingView(this);

		List<Scale> scales1 = new ArrayList<>();
		scales1.add(new Scale("Yes", 492, getResources().getColor(R.color.mark_green)));
		scales1.add(new Scale("No", 224, getResources().getColor(R.color.mark_red)));
		scales1.add(new Scale("Neutral", 7, getResources().getColor(R.color.mark_yellow)));

		ratingView1.setScales(scales1);

		LinearLayout linearLayout = findViewById(R.id.container);
		linearLayout.addView(ratingView1);

		// You can get RatingItem from view to customize
		RatingItemView ratingItemView = ratingView.getRatingItem(4);
		ratingItemView.setProgressColor(getResources().getColor(R.color.mark_red));

		ratingView1.setOnScaleClickListener(new RatingView.OnScaleClickListener() {
			@Override
			public void onClick(RatingItemView ratingItemView) {
				// You can get your scale object from clicked view
				Scale scale = ratingItemView.getScale();
				Log.d("mylog", "clicked " +scale.getMark() +" with value: " +scale.getCount());
			}
		});

		ratingView1.setOnScaleLongClickListener(new RatingView.OnScaleLongClickListener() {
			@Override
			public void onClick(RatingItemView ratingItemView) {
				Scale scale = ratingItemView.getScale();
				Log.d("mylog", "long clicked " +scale.getMark() +" with value: " +scale.getCount());
			}
		});
	}
}