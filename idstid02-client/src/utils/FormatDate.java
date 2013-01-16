package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class FormatDate {
        public FormatDate(){}
       
        public static String getToday()
        {
          Date date = Calendar.getInstance().getTime();
          SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
          return sdf.format(date);
        }
       //Con l'ora
        public static String getFormatDateH(String dateStr)
        {if(dateStr != null){
          SimpleDateFormat okFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
          SimpleDateFormat  fromUser = new SimpleDateFormat("yyyy-MM-dd HH:mm");
          try {
			    dateStr = okFormat.format(fromUser.parse(dateStr));
		       } 
          catch (ParseException e){e.printStackTrace();}}
		return dateStr;
		
        }
        //Senza l'ora
        public static String getFormatDate(String dateStr)
        { if(dateStr != ""){
          SimpleDateFormat okFormat = new SimpleDateFormat("dd/MM/yyyy");
          SimpleDateFormat  fromUser = new SimpleDateFormat("yyyy-MM-dd");
          try {
			    dateStr = okFormat.format(fromUser.parse(dateStr));
		       } 
          catch (ParseException e){e.printStackTrace();}}
		return dateStr;
		}
}

