package com.kwizzie.model;

import com.google.code.morphia.annotations.Id;

public class QuestionCategory {

	@Id
	private String categoryCode;
	private String categoryName;
	
	public QuestionCategory(){
		
	}
	
	public QuestionCategory(String categoryCode, String categoryName){
		this.categoryCode = categoryCode;
		this.categoryName = categoryName;
	}
	
	public String getCategoryCode() {
		return categoryCode;
	}
	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	
	
}
