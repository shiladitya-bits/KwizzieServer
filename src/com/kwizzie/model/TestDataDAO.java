package com.kwizzie.model;

import java.net.UnknownHostException;
import java.util.List;

import com.google.code.morphia.Morphia;
import com.google.code.morphia.dao.BasicDAO;
import com.mongodb.Mongo;

public class TestDataDAO extends BasicDAO<testData,String>{
	public TestDataDAO(Mongo mongo,Morphia morphia, String dbName){
		super(mongo,morphia, dbName);
		
	}
	public List<testData> findAll(){
		return ds.find(testData.class).asList();
		
	}
	
	
}
