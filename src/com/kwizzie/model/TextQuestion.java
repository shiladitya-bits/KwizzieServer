package com.kwizzie.model;

public class TextQuestion extends Question{

	private String subTitle;

	public TextQuestion(){
		
	}
	public TextQuestion(String subTitle, QuestionLocation location,
		String questionTitle,
			AnswerType answerType, boolean isLocked) {
		super();
		this.subTitle = subTitle;
		this.location = location;
		this.questionTitle = questionTitle;
		this.answerType = answerType;
		this.isLocked = isLocked;
	}
	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}
}
