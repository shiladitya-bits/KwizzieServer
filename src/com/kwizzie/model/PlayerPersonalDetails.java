package com.kwizzie.model;

import javax.persistence.Embeddable;

import com.google.code.morphia.annotations.Id;

@Embeddable
public class PlayerPersonalDetails {
	private String photoUrl;
	private String name;
	@Id
	private String emailId;
	public String getPhotoUrl() {
		return photoUrl;
	}
	public PlayerPersonalDetails(){}
	
	public PlayerPersonalDetails(String photoUrl, String name,
			String emailId) {
		this.photoUrl = photoUrl;
		this.setName(name);
		this.emailId = emailId;
	}
	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "PlayerPersonalDetails [photoUrl=" + photoUrl + ", name="
				+ name + ", emailId=" + emailId + "]";
	}
}
