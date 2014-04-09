package com.kwizzie.model;

import java.util.List;

import com.google.code.morphia.annotations.Embedded;
import com.google.code.morphia.annotations.Entity;

@Entity
public class PrivateQuizRoom extends QuizRoom{

	@Embedded
	private List<Question> questions;
	private String securityKey;

	public PrivateQuizRoom(){
		
	}
	public PrivateQuizRoom(List<Question> questions, String securityKey, String description, String roomName, String roomID) {
		super();
		this.questions = questions;
		this.securityKey = securityKey;
		this.description = description;
		this.roomName = roomName;
		this.roomID = roomID;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}
	public String getSecurityKey() {
		return securityKey;
	}

	public void setSecurityKey(String securityKey) {
		this.securityKey = securityKey;
	}
}
