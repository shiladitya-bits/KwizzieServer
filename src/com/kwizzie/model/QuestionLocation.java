package com.kwizzie.model;

import com.google.code.morphia.annotations.Embedded;

@Embedded
public class QuestionLocation {

	double latitude;
	double longitute;
	double radius;
	String locationName;
	String imageUrl;
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitute() {
		return longitute;
	}
	public void setLongitute(double longitute) {
		this.longitute = longitute;
	}
	public double getRadius() {
		return radius;
	}
	public void setRadius(double radius) {
		this.radius = radius;
	}
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public QuestionLocation(double latitude, double longitute, double radius,
			String locationName, String imageUrl) {
		super();
		this.latitude = latitude;
		this.longitute = longitute;
		this.radius = radius;
		this.locationName = locationName;
		this.imageUrl = imageUrl;
	}
	public QuestionLocation() {}
}
