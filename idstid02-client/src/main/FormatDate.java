package main;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class FormatDate {
	public FormatDate(){} 
	
	public static String getToday()
	{
	  Date date = Calendar.getInstance().getTime();
	  SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	  return sdf.format(date);
	}

}
