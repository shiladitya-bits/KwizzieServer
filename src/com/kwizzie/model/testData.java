package com.kwizzie.model;

import com.google.code.morphia.annotations.Id;

public class testData {
	
	@Id
	public String id;
	public String name;

	public testData(){
		
	}
	public testData(String id, String name) {
		this.id = id;
		this.name = name;
	}
	@Override
	public String toString() {
		return "testData [id=" + id + ", name=" + name + "]";
	}
	

	
	
}
