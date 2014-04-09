package com.kwizzie.model;

import com.google.code.morphia.annotations.Id;

public abstract class QuizRoom {
	protected String description;
	protected String roomName;
	@Id
	protected String roomID;
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	public String getRoomID() {
		return roomID;
	}
	public void setRoomID(String roomID) {
		this.roomID = roomID;
	}
	
}
