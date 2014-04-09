package com.kwizzie.model;

public class VideoQuestion extends Question{

	private String videoURL;
	
	public VideoQuestion(){
		
	}
	
	public VideoQuestion(String videoURL, QuestionLocation location,
			String questionTitle,
			AnswerType answerType, boolean isLocked) {
		super();
		this.videoURL = videoURL;
		this.location = location;
		this.questionTitle = questionTitle;
		this.answerType = answerType;
		this.isLocked = isLocked;
	}
	public String getVideoURL() {
		return videoURL;
	}
	public void setVideoURL(String videoURL) {
		this.videoURL = videoURL;
	}
}
