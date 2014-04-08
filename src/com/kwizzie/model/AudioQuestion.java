package com.kwizzie.model;

public class AudioQuestion extends Question{

	private String audioURL;
	public AudioQuestion(String audioURL, QuestionLocation location,
			QuestionCategory category, String questionTitle,
			AnswerType answerType, boolean isLocked) {
		super();
		this.audioURL = audioURL;
		this.location = location;
		this.category = category;
		this.questionTitle = questionTitle;
		this.answerType = answerType;
		this.isLocked = isLocked;
	}
	public String getAudioURL() {
		return audioURL;
	}
	public void setAudioURL(String audioURL) {
		this.audioURL = audioURL;
	}
}
