package com.nefrit.ratingview.model;

public class Scale {

	private String mark;

	private int count;

	private int color;

	public Scale(String mark, int count, int color) {
		this.mark = mark;
		this.count = count;
		this.color = color;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}
}