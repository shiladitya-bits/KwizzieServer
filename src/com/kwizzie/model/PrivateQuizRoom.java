package com.kwizzie.model;

import java.util.List;

public class PrivateQuizRoom extends QuizRoom{

	private List<Question> questions;

	public PrivateQuizRoom(List<Question> questions, LeaderBoard leaderBoard,
			String description, String roomName, String roomID) {
		super();
		this.questions = questions;
		this.leaderBoard = leaderBoard;
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
	
}
