package com.kwizzie.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.code.morphia.annotations.Embedded;
import com.google.code.morphia.annotations.Entity;

@Entity
public class PublicQuizRoom extends QuizRoom{

	@Embedded
	private Map<String,List<Question>> categoryQuestionMap;
	
	public PublicQuizRoom(){
		this(new HashMap<String, List<Question>>());
	}
	
	public PublicQuizRoom(
			Map<String, List<Question>> categoryQuestionMap) {
		this.categoryQuestionMap = categoryQuestionMap;
		this.description = "Public Quiz Room";
		this.roomName = "public";
		this.roomID = "public";
	}

	public Map<String, List<Question>> getCategoryQuestionMap() {
		return categoryQuestionMap;
	}

	public void setCategoryQuestionMap(
			Map<String, List<Question>> categoryQuestionMap) {
		this.categoryQuestionMap = categoryQuestionMap;
	}
	
}
