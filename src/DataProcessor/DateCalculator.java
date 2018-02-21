package DataProcessor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;

import org.apache.commons.lang3.time.DateUtils;

public class DateCalculator {
	
	
	

	
	public static String nextDate(String previousTo) {

		
		int year=get_Year(previousTo);
		int month=get_Month(previousTo);
		int day=get_Day(previousTo);
		int hour=get_Hour(previousTo);
		int minutes=get_Minutes(previousTo);
		//Get date from String
		Date date;

		Calendar cal=Calendar.getInstance();
		
		cal.set(year,month,day,hour,minutes,00);
		date=cal.getTime();
		

		
		//Add 1 hour
		date=DateUtils.addHours(date, 1);
		//Transform date to String again
		
		cal.setTime(date);
		
		year=cal.get(Calendar.YEAR);
		month=cal.get(Calendar.MONTH);
		day=cal.get(Calendar.DAY_OF_MONTH);
		hour=cal.get(Calendar.HOUR_OF_DAY);
		minutes=cal.get(Calendar.MINUTE);
		
		
		String month_String="";
		if(month<10)
			month_String="0"+month;
		else
			month_String=""+month;
		String day_String="";
		if(day<10)
			day_String="0"+day;
		else
			day_String=""+day;
		String hour_String="";
		if(hour==0)
			hour_String="00";
		else if(hour<10)
			hour_String="0"+hour;
		else
			hour_String=""+hour;
		String minute_String="";
		if(minutes==0)
			minute_String="00";
		else if(minutes<10)
			minute_String="0"+minutes;
		else
			minute_String=""+minutes;
		
		
			
		String newDate=""+year+month_String+day_String+hour_String+minute_String;
		

		return newDate;
	}



	private static int get_Minutes(String previousTo) {
		return Integer.parseInt(previousTo.substring(10,12));
	}

	private static int get_Hour(String previousTo) {
		return Integer.parseInt(previousTo.substring(8, 10));
	}

	private static int get_Day(String previousTo) {
		return Integer.parseInt(previousTo.substring(6,8));
	}

	private static int get_Month(String previousTo) {
		return Integer.parseInt(previousTo.substring(4,6));
	}

	private static int get_Year(String previousTo) {
		return Integer.parseInt(previousTo.substring(0,4));
	}
	
	
	
	

}
