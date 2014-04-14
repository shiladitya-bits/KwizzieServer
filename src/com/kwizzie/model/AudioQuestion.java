package com.kwizzie.model;

public class AudioQuestion extends Question{

	private String audioURL;
	
	public AudioQuestion(){
		
	}
	
	public AudioQuestion(String audioURL, QuestionLocation location,
			String questionTitle,
			AnswerType answerType, boolean isLocked) {
		super();
		this.audioURL = audioURL;
		this.location = location;
		this.questionTitle = questionTitle;
		this.answerType = answerType;
		this.isLocked = isLocked;
		this.typeOfQuestion="AUDIO_QUESTION";
	}
	public String getAudioURL() {
		return audioURL;
	}
	public void setAudioURL(String audioURL) {
		this.audioURL = audioURL;
	}
}
