package com.kwizzie.model;

public class PictureQuestion extends Question {

	private String pictureURL;

	public PictureQuestion(){
		
	}
	
	public PictureQuestion(String pictureURL, QuestionLocation location,
			String questionTitle,
			AnswerType answerType, boolean isLocked) {
		super();
		this.pictureURL = pictureURL;
		this.location = location;
		this.questionTitle = questionTitle;
		this.answerType = answerType;
		this.isLocked = isLocked;
	}

	public String getPictureURL() {
		return pictureURL;
	}

	public void setPictureURL(String pictureURL) {
		this.pictureURL = pictureURL;
	}
	

}
