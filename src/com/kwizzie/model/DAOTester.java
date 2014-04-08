package com.kwizzie.model;

import java.net.UnknownHostException;
import java.util.List;

import org.slf4j.LoggerFactory;

import com.google.code.morphia.Morphia;
import com.mongodb.Mongo;

public class DAOTester {
	public static void main(String[] args) throws UnknownHostException{
		testData rd = new testData("144" , "neha");
		Mongo mongo = new Mongo("localhost");
		Morphia morphia = new Morphia();
		TestDataDAO rdao = new TestDataDAO(mongo , morphia, "test");
		List<testData> td1 = rdao.findAll();
		System.out.println(td1);
		rdao.save(rd);
		td1 = rdao.findAll();
		System.out.println(td1);
	}
}
