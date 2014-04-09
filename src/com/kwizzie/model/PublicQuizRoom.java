package com.kwizzie.model;

import java.util.List;
import java.util.Map;

import com.google.code.morphia.annotations.Embedded;
import com.google.code.morphia.annotations.Entity;

@Entity
public class PublicQuizRoom extends QuizRoom{

	@Embedded
	private Map<String,List<Question>> categoryQuestionMap;
	
	public PublicQuizRoom(){
		
	}
	public PublicQuizRoom(
			Map<String, List<Question>> categoryQuestionMap, String description, String roomName,
			String roomID) {
		super();
		this.categoryQuestionMap = categoryQuestionMap;
		this.description = description;
		this.roomName = roomName;
		this.roomID = roomID;
	}

	public Map<String, List<Question>> getCategoryQuestionMap() {
		return categoryQuestionMap;
	}

	public void setCategoryQuestionMap(
			Map<String, List<Question>> categoryQuestionMap) {
		this.categoryQuestionMap = categoryQuestionMap;
	}
	
}
