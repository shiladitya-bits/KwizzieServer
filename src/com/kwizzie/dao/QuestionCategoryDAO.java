package com.kwizzie.dao;

import java.util.List;

import com.google.code.morphia.Morphia;
import com.google.code.morphia.dao.BasicDAO;
import com.kwizzie.model.QuestionCategory;
import com.mongodb.Mongo;

public class QuestionCategoryDAO extends BasicDAO<QuestionCategory, String>{

	protected QuestionCategoryDAO(Mongo mongo, Morphia morphia, String dbName) {
		super(mongo, morphia, dbName);
	}

	public List<QuestionCategory> getCategories(){
		return ds.find(QuestionCategory.class).asList();
	}
	
}
