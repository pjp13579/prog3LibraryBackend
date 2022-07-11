package com.example.demo.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class testDate {

	public static void main(String[] args) throws ParseException {
		
		final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
		
		String d = "2019-03-11";
		Date dat = DATE_FORMAT.parse(d);
		System.out.println(dat);
		
		

	}

}
