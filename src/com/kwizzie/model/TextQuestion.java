package com.kwizzie.model;

public class TextQuestion extends Question{

	private String subTitle;

	public TextQuestion(String subTitle, QuestionLocation location,
			QuestionCategory category, String questionTitle,
			AnswerType answerType, boolean isLocked) {
		super();
		this.subTitle = subTitle;
		this.location = location;
		this.category = category;
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
