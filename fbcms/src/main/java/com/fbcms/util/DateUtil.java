package com.fbcms.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 *
 */
public class DateUtil {

	/**
	 * Base ISO 8601 Date format yyyyMMdd i.e., 20021225 for the 25th day of
	 * December in the year 2002
	 */
	public static final String ISO_DATE_FORMAT = "yyyyMMdd";

	/**
	 * Expanded ISO 8601 Date format yyyy-MM-dd i.e., 2002-12-25 for the 25th
	 * day of December in the year 2002
	 */
	public static final String ISO_EXPANDED_DATE_FORMAT = "yyyy-MM-dd";

	/**
	 * yyyy-MM-dd hh:mm:ss
	 */
	public static String DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

	public static String DATETIME_PATTERN_MS = "yyyy-MM-dd HH:mm:ss sss";

    public static String DATETIME_PATTERN_YMD_CHN = "yyyy年M月d日";

	/**
	 * Default lenient setting for getDate.
	 */
	private static boolean LENIENT_DATE = false;

	/**
	 * Returns the days between two dates. Positive values indicate that the
	 * second date is after the first, and negative values indicate, well, the
	 * opposite. Relying on specific times is problematic.
	 * 
	 * @param early
	 *            the "first date"
	 * @param late
	 *            the "second date"
	 * @return the days between the two dates
	 */
	public static final int daysBetween(Date early, Date late) {

		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTime(early);
		c2.setTime(late);

		return daysBetween(c1, c2);
	}

	/**
	 * Returns the days between two dates. Positive values indicate that the
	 * second date is after the first, and negative values indicate, well, the
	 * opposite.
	 * 
	 * @param early
	 * @param late
	 * @return the days between two dates.
	 */
	public static final int daysBetween(Calendar early, Calendar late) {

		return (int) (toJulian(late) - toJulian(early));
	}

	/**
	 * Return a Julian date based on the input parameter. This is based from
	 * calculations found at
	 * <a href="http://quasar.as.utexas.edu/BillInfo/JulianDatesG.html">Julian
	 * Day Calculations (Gregorian Calendar)</a>, provided by Bill Jeffrys.
	 * 
	 * @param c
	 *            a calendar instance
	 * @return the julian day number
	 */
	public static final float toJulian(Calendar c) {

		int Y = c.get(Calendar.YEAR);
		int M = c.get(Calendar.MONTH);
		int D = c.get(Calendar.DATE);
		int A = Y / 100;
		int B = A / 4;
		int C = 2 - A + B;
		float E = (int) (365.25f * (Y + 4716));
		float F = (int) (30.6001f * (M + 1));
		float JD = C + D + E + F - 1524.5f;

		return JD;
	}

	/**
	 * <Method Simple Description>
	 * 
	 * @param isoString
	 * @param fmt
	 * @param field
	 * @param amount
	 * @return
	 * @author wyb
	 * @see
	 */
	public static final String dateIncrease(String isoString, String fmt,
			int field, int amount) {

		try {
			Calendar cal = GregorianCalendar.getInstance(TimeZone
					.getTimeZone("GMT"));
			cal.setTime(stringToDate(isoString, fmt, true));
			cal.add(field, amount);

			return dateToString(cal.getTime(), fmt);

		} catch (Exception ex) {
			return null;
		}
	}

	/**
	 * Time Field Rolling function. Rolls (up/down) a single unit of time on the
	 * given time field.
	 * 
	 * @param isoString
	 * @param field
	 *            the time field.
	 * @param up
	 *            Indicates if rolling up or rolling down the field value.
	 * @exception ParseException
	 *                if an unknown field value is given.
	 */
	public static final String roll(String isoString, String fmt, int field,
			boolean up) throws ParseException {

		Calendar cal = GregorianCalendar.getInstance(TimeZone
				.getTimeZone("GMT"));
		cal.setTime(stringToDate(isoString, fmt));
		cal.roll(field, up);

		return dateToString(cal.getTime(), fmt);
	}

	/**
	 * Time Field Rolling function. Rolls (up/down) a single unit of time on the
	 * given time field.
	 * 
	 * @param isoString
	 * @param field
	 *            the time field.
	 * @param up
	 *            Indicates if rolling up or rolling down the field value.
	 * @exception ParseException
	 *                if an unknown field value is given.
	 */
	public static final String roll(String isoString, int field, boolean up)
			throws ParseException {

		return roll(isoString, DATETIME_PATTERN, field, up);
	}

	/**
	 * <Method Simple Description>
	 * 
	 * @param dateText
	 * @param format
	 * @param lenient
	 * @return
	 * @author wyb
	 * @see
	 */
	public static Date stringToDate(String dateText, String format,
			boolean lenient) {

		if (dateText == null) {

			return null;
		}

		DateFormat df = null;

		try {

			if (format == null) {
				df = new SimpleDateFormat();
			} else {
				df = new SimpleDateFormat(format);
			}

			df.setLenient(false);

			return df.parse(dateText);
		} catch (ParseException e) {

			return null;
		}
	}

	/**
	 * <Method Simple Description>
	 * 
	 * @param dateString
	 * @param format
	 * @return
	 * @author wyb
	 * @see
	 */
	public static Date stringToDate(String dateString, String format) {

		return stringToDate(dateString, format, LENIENT_DATE);
	}

	/**
	 * <Method Simple Description>
	 * 
	 * @param dateString
	 * @return
	 * @author wyb
	 * @see
	 */
	public static Date stringToDate(String dateString) {

		return stringToDate(dateString, ISO_EXPANDED_DATE_FORMAT, LENIENT_DATE);
	}

	/**
	 * <Method Simple Description>
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 * @author wyb
	 * @see
	 */
	public static String dateToString(Date date, String pattern) {

		if (date == null) {

			return null;
		}

		try {

			SimpleDateFormat sfDate = new SimpleDateFormat(pattern);
			sfDate.setLenient(false);

			return sfDate.format(date);
		} catch (Exception e) {

			return null;
		}
	}

	/**
	 * <Method Simple Description>
	 * 
	 * @param date
	 * @return
	 * @author wyb
	 * @see
	 */
	public static String dateToString(Date date) {
		return dateToString(date, ISO_EXPANDED_DATE_FORMAT);
	}

	/**
	 * <Method Simple Description>
	 * 
	 * @return
	 * @author wyb
	 * @see
	 */
	public static Date getCurrentDateTime() {
		java.util.Calendar calNow = java.util.Calendar.getInstance();
		java.util.Date dtNow = calNow.getTime();

		return dtNow;
	}

	/**
	 * <Method Simple Description>
	 * 
	 * @param pattern
	 * @return
	 * @author wyb
	 * @see
	 */
	public static String getCurrentDateString(String pattern) {
		return dateToString(getCurrentDateTime(), pattern);
	}

	/**
	 * <Method Simple Description>
	 * 
	 * @return
	 * @author wyb
	 * @see
	 */
	public static String getCurrentDateString() {
		return dateToString(getCurrentDateTime(), ISO_EXPANDED_DATE_FORMAT);
	}

	/**
	 * <Method Simple Description>
	 * 
	 * @param date
	 * @return
	 * @author wyb
	 * @see
	 */
	public static String dateToStringWithTime(Date date) {

		return dateToString(date, DATETIME_PATTERN);
	}

	/**
	 * <Method Simple Description>
	 * 
	 * @param date
	 * @param days
	 * @return
	 * @author wyb
	 * @see
	 */
	public static Date dateIncreaseByDay(Date date, int days) {

		Calendar cal = GregorianCalendar.getInstance(TimeZone
				.getTimeZone("GMT"));
		cal.setTime(date);
		cal.add(Calendar.DATE, days);

		return cal.getTime();
	}

	/**
	 * <Method Simple Description>
	 * 
	 * @param date
	 * @param mnt
	 * @return
	 * @author wyb
	 * @see
	 */
	public static Date dateIncreaseByMonth(Date date, int mnt) {

		Calendar cal = GregorianCalendar.getInstance(TimeZone
				.getTimeZone("GMT"));
		cal.setTime(date);
		cal.add(Calendar.MONTH, mnt);

		return cal.getTime();
	}

	/**
	 * <Method Simple Description>
	 * 
	 * @param date
	 * @param mnt
	 * @return
	 * @author wyb
	 * @see
	 */
	public static Date dateIncreaseByYear(Date date, int mnt) {

		Calendar cal = GregorianCalendar.getInstance(TimeZone
				.getTimeZone("GMT"));
		cal.setTime(date);
		cal.add(Calendar.YEAR, mnt);

		return cal.getTime();
	}

	/**
	 * <Method Simple Description>
	 * 
	 * @param date
	 * @param days
	 * @return
	 * @author wyb
	 * @see
	 */
	public static String dateIncreaseByDay(String date, int days) {
		return dateIncreaseByDay(date, ISO_DATE_FORMAT, days);
	}

	/**
	 * <Method Simple Description>
	 * 
	 * @param date
	 * @param seconds
	 * @return
	 * @author wangyibo
	 * @see
	 */
	public static Date dateIncreaseBySeconds(Date date, int seconds) {
		Calendar cal = GregorianCalendar.getInstance(TimeZone
				.getTimeZone("GMT"));
		cal.setTime(date);
		cal.add(Calendar.SECOND, seconds);
		return cal.getTime();
	}

	/**
	 * <Method Simple Description>
	 * 
	 * @param date
	 * @param fmt
	 * @param days
	 * @return
	 * @author wyb
	 * @see
	 */
	public static String dateIncreaseByDay(String date, String fmt, int days) {
		return dateIncrease(date, fmt, Calendar.DATE, days);
	}

	/**
	 * <Method Simple Description>
	 * 
	 * @param src
	 * @param srcfmt
	 * @param desfmt
	 * @return
	 * @author wyb
	 * @see
	 */
	public static String stringToString(String src, String srcfmt, String desfmt) {
		return dateToString(stringToDate(src, srcfmt), desfmt);
	}

	/**
	 * 
	 * Description:加一天
	 * 
	 * @param date
	 * @return
	 * @author dzh
	 * @since：2007-12-13 下午02:57:38
	 */
	public static Date addDay(Date date,Integer num) {
		Calendar cd = Calendar.getInstance();
		cd.setTime(date);
		cd.add(Calendar.DAY_OF_YEAR, num);
		return cd.getTime();
	}

	/**
	 * 选择日期到现在的天数
	 * Created by ZHZEPHI at 2015年3月12日 下午2:20:27
	 *
	 * @param dateStr
	 * @return
	 * @throws ParseException
	 */
	public static int getLongTime(String dateStr){
        try{
            SimpleDateFormat dateDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = dateDateFormat.parse(dateStr);
            Calendar calendar = Calendar.getInstance();
            long days = (calendar.getTime().getTime() - date.getTime()) / (24 * 60 * 60 * 1000);
            return (int) days;
        }catch (Exception e){
            return 0;
        }
	}

	/**
	 * 选择未来日期到现在的天数
	 * Created by ZHZEPHI at 2015年3月12日 下午2:20:27
	 *
	 * @param dateStr 未来某个时间
	 * @return
	 * @throws ParseException
	 */
	public static int getDaysFromFuture(String dateStr) {
        try{
            SimpleDateFormat dateDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = dateDateFormat.parse(dateStr);
            Calendar calendar = Calendar.getInstance();
            long days = (date.getTime() - calendar.getTime().getTime()) / (24 * 60 * 60 * 1000);
            return (int) days;
        }catch (Exception e){
            return 0;
        }
	}
	public static Date addHour(Date date ,Integer hour ) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) + hour);
		return calendar.getTime();
	} 
}
