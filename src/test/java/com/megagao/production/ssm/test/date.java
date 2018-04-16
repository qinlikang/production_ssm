package com.megagao.production.ssm.test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class date {

	public static void main(String[] args) {
		Date date=new Date();
		SimpleDateFormat dateFormat=new SimpleDateFormat("HHmmss"); 
		int thisTime=Integer.valueOf(dateFormat.format(date).toString());
		
	}

}
