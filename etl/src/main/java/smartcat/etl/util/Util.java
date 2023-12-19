package smartcat.etl.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Util 
{
	public static String getEstDateFromTimestamp(long timestamp)
	{
		String date = "";
		
		Date timestampDate = new Date(timestamp * 1000);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		TimeZone zoneEst = TimeZone.getTimeZone("EST");
	    df.setTimeZone(zoneEst);

	    date = df.format(timestampDate);
		
		return date;
	}
}
