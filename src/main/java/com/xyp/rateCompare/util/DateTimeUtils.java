package com.xyp.rateCompare.util;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;
import org.apache.commons.lang.time.FastDateFormat;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.PeriodType;
import org.joda.time.format.DateTimeFormat;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class DateTimeUtils {
    private static final Log logger = LogFactory.getLog(DateTimeUtils.class);
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String DATE_FORMAT_US = "MM/dd/yyyy";
    public static final String DATE_FORMAT_US2 = "MM-dd-yyyy";
    public static final String DATE_FORMAT_COMP = "yyyyMMdd";
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String TIME_FORMAT = "HH:mm:ss";
    public static final String FULL_DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";

    public static final String FULL_DATE_TIME_FORMAT2 = "yyyy-MM-dd'T'HH:mm:ss+00:00";

    public static final String DATE_FORMAT_COMP_YYMMDD = "yyyyMMdd";
    public static final String YYMMdd_HHmmss = "yyyyMMdd_HHmmss";

    public static Date parse(String input) {
        if (input == null || input.equals("")) {
            return null;
        }
        return parse(input, DATE_FORMAT);
    }

    public static Date parseAllType(String input) {
        if (StringUtils.isBlank(input)) {
            return null;
        }

        if (input.matches("\\d{4}-\\d{1,2}-\\d{1,2}")) {
            return parse(input, DATE_FORMAT);
        } else if (input.matches("\\d{4}-\\d{1,2}-\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2}")) {
            return parse(input, DATE_TIME_FORMAT);
        } else if (input.matches("\\d{4}-\\d{1,2}-\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2}\\.\\d{3}")) {
            return parse(input, FULL_DATE_TIME_FORMAT);
        } else {
            try {
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS z");
                return dateFormat.parse(input);
            } catch (ParseException e) {
                logger.error(e);
                return null;
            }
        }
    }

    public static Date parse(String input, String pattern) {
        Validate.notEmpty(input);
        Validate.notEmpty(pattern);
        return DateTimeFormat.forPattern(pattern).parseDateTime(input).toDate();
    }

    public static Long getIntervalDays(Date begin, Date end) {
        Validate.notNull(begin);
        Validate.notNull(end);
        int days = new Period(
                begin.getTime(),
                end.getTime(),
                PeriodType.days()).getDays();
        return (long) days;
    }

    public static Date offsetDays(Date date, int days) {
        return date == null ? date : new DateTime(date.getTime()).plusDays(days).toDate();
    }

    public static Date offsetHours(Date date, int hours) {
        return date == null ? date : new DateTime(date.getTime()).plusHours(hours).toDate();
    }

    public static Date offsetMinutes(Date date, int minutes) {
        return date == null ? date : new DateTime(date.getTime()).plusMinutes(minutes).toDate();
    }


    public static Date offsetDays(int days) {
        return offsetDays(new Date(), days);
    }

    public static String getDateString(int offsetDaysFromNow) {
        return formatDate(offsetDays(new Date(), offsetDaysFromNow));
    }

    public static String formatDate(Date date) {
        return formatDateTime(date, DATE_FORMAT);
    }

    public static String formatUSDate(Date date) {
        return formatDateTime(date, DATE_FORMAT_US);
    }

    public static String formatDateTime(Date dateTime) {
        return formatDateTime(dateTime, DATE_TIME_FORMAT);
    }

    public static String formatTime(Date time) {
        return formatDateTime(time, TIME_FORMAT);
    }

    public static String formatDateTime(Date dateTime, String format) {
        if (dateTime == null) {
            return null;
        }
        return FastDateFormat.getInstance(format).format(dateTime);
    }

    public static Date getStartOfMonth(int year, int month) {
        DateTime dateTime = new DateTime(year, month, 1, 0, 0, 0, 0);
        return dateTime.toDate();
    }

    public static Date getEndOfMonth(int year, int month) {
        DateTime dateTime = new DateTime(year, month, 1, 0, 0, 0, 0);
        dateTime = dateTime.plusMonths(1).minusMillis(1);
        return dateTime.toDate();
    }

    public static int getYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    public static int getYear() {
        return getYear(new Date());
    }

    public static int getDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    public static int getDay() {
        return getDay(new Date());
    }

    public static int getMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH);
    }

    public static int getMonth() {
        return getMonth(new Date());
    }

    public static Date getStartOfDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, calendar.getMinimum(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE, calendar.getMinimum(Calendar.MINUTE));
        calendar.set(Calendar.SECOND, calendar.getMinimum(Calendar.SECOND));
        calendar.set(Calendar.MILLISECOND, calendar.getMinimum(Calendar.MILLISECOND));
        return calendar.getTime();
    }

    public static Date getEndOfDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, calendar.getMaximum(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE, calendar.getMaximum(Calendar.MINUTE));
        calendar.set(Calendar.SECOND, calendar.getMaximum(Calendar.SECOND));
        calendar.set(Calendar.MILLISECOND, calendar.getMaximum(Calendar.MILLISECOND));
        return calendar.getTime();
    }

    public static XMLGregorianCalendar toXML(Date date) {

        if (date == null) {
            return null;
        }

        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);

            DatatypeFactory datatypeFactory = DatatypeFactory.newInstance();
            int year = calendar.get(Calendar.ERA) == GregorianCalendar.BC ?
                    -calendar.get(Calendar.YEAR) : calendar.get(Calendar.YEAR);
            return datatypeFactory.newXMLGregorianCalendar(
                    year,
                    calendar.get(Calendar.MONTH) + 1,
                    calendar.get(Calendar.DAY_OF_MONTH),
                    calendar.get(Calendar.HOUR_OF_DAY),
                    calendar.get(Calendar.MINUTE),
                    calendar.get(Calendar.SECOND),
                    calendar.get(Calendar.MILLISECOND),
                    calendar.get((Calendar.ZONE_OFFSET) + calendar.get(Calendar.DST_OFFSET)) / 60000);
        } catch (DatatypeConfigurationException e) {
            logger.error(e);
            return null;
        }
    }


    public static Date getLastMonday() {
        int mondayPlus = getMondayPlus();
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, mondayPlus + 7 * -1);
        Date monday = currentDate.getTime();
        return monday;
    }

    public static Date getLastMonthStartDate() {
        Calendar lastDate = Calendar.getInstance();
        lastDate.set(Calendar.DATE, 1);
        lastDate.add(Calendar.MONTH, -1);
        //lastDate.add(Calendar.DATE,-1);
        return lastDate.getTime();
    }

    public static Date getLastMonthEndDate() {
        Calendar lastDate = Calendar.getInstance();
        lastDate.add(Calendar.MONTH, -1);
        lastDate.set(Calendar.DATE, 1);
        lastDate.roll(Calendar.DATE, -1);
        return lastDate.getTime();
    }

    private static int getMondayPlus() {
        Calendar cd = Calendar.getInstance();
        int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK) - 1;
        if (dayOfWeek == 1) {
            return 0;
        } else {
            return 1 - dayOfWeek;
        }
    }

    public static Date fromXML(XMLGregorianCalendar gregorianCalendar) {
        if (gregorianCalendar == null) {
            return null;
        }

        Calendar calendar = gregorianCalendar.toGregorianCalendar();
        return calendar.getTime();
    }

    public static Date addHours(Date date, int hours) {
        return new DateTime(date.getTime()).plusHours(hours).toDate();
    }


    public static Date max(Date date1, Date date2) {
        return date1.after(date2) ? date1 : date2;
    }

    public static Date min(Date date1, Date date2) {
        return date1.before(date2) ? date1 : date2;
    }


    public static XMLGregorianCalendar toXMLGregorianCalendar(Date date) {

        XMLGregorianCalendar result = null;
        try {
            GregorianCalendar gregorianCalendar = new GregorianCalendar();
            gregorianCalendar.setTime(date);
            result = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar);
        } catch (DatatypeConfigurationException e) {
            logger.error(e);
        }

        return result;
    }

    public static String getCurrentWeekday() {
        int weeks = 0;
        int mondayPlus = getMondayPlus();
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, mondayPlus + 6);
        Date monday = currentDate.getTime();

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String preMonday = df.format(monday);
        return preMonday;
    }

    public static String getFirstDayOfMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(calendar.getTime());
    }

    public static String getFirstDayOfMonth(DateFormat df) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        return df.format(calendar.getTime());
    }

    public static String getFirstDayOfWeek() {
        Calendar monday = Calendar.getInstance();
        Calendar result = getADayOfWeek(monday, Calendar.MONDAY);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(result.getTime());
    }

    private static Calendar getADayOfWeek(Calendar day, int dayOfWeek) {
        int week = day.get(Calendar.DAY_OF_WEEK);
        if (week == dayOfWeek)
            return day;
        int diffDay = dayOfWeek - week;
        if (week == Calendar.SUNDAY) {
            diffDay -= 7;
        } else if (dayOfWeek == Calendar.SUNDAY) {
            diffDay += 7;
        }
        day.add(Calendar.DATE, diffDay);
        return day;
    }

    public static Date getUtcDatetime() {
        Calendar cal = Calendar.getInstance();

        cal.setTimeInMillis(System.currentTimeMillis() - java.util.TimeZone.getDefault().getRawOffset());

        return cal.getTime();
    }

    public static String getLogTimestamp() {
        return formatDateTime(getUtcDatetime(), FULL_DATE_TIME_FORMAT);
    }

    public static String getStartOfDate(Date date) {
        String strDate = DateTimeUtils.formatDateTime(date, DATE_FORMAT);
        if (StringUtils.isNotEmpty(strDate)) {
            return strDate + " 00:00:00";
        } else {
            return null;
        }
    }

    public static String getEndOfDate(Date date) {
        String strDate = DateTimeUtils.formatDateTime(date, DATE_FORMAT);
        if (StringUtils.isNotEmpty(strDate)) {
            return strDate + " 23:59:59.999";
        } else {
            return null;
        }
    }

    public static void main(String[] args) {
//        String thisWeekDay = DateTimeUtils.getFirstDayOfMonth(new SimpleDateFormat(DATE_FORMAT_US));
//        String nextWeekDay = DateUtil.formatDate(DateUtil.addDays(DateUtil.parseDate(thisWeekDay),7));

//        System.out.println(thisWeekDay);


        Date t = DateTimeUtils.parse("2013-11-05");
        int year = DateTimeUtils.getYear(t);
        int month = DateTimeUtils.getMonth(t);

        System.out.println(year);
        System.out.println(month);

        GregorianCalendar calendar = new GregorianCalendar(year, month, 1);
        Date exitReportDay = calendar.getTime();
        System.out.println(formatDate(exitReportDay));
    }

    public static Date parse(String input, String pattern, Locale locale) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, locale == null ? Locale.US : locale);
        Date date = null;

        try {
            date = simpleDateFormat.parse(input);
        } catch (ParseException e) {
            logger.error(e);
        }

        return date;
    }

    public static Date parseDateWithCompatibility(String input, String preferredPattern) {
        if(StringUtils.isEmpty(input)){
            return null;
        }

        Date date = null;

        if(StringUtils.isNotEmpty(preferredPattern)){
            try {
                date = DateTimeUtils.parse(input, preferredPattern);
            } catch (Exception ignored) {
                logger.error(ignored);
            }

            if(date != null){
                return date;
            }
        }

        if(!DateTimeUtils.DATE_FORMAT.equals(preferredPattern)){
            try {
                date = DateTimeUtils.parse(input, DateTimeUtils.DATE_FORMAT);
            } catch (Exception ignored) {
                logger.error(ignored);
            }

            if(date != null){
                return date;
            }
        }

        if(!DateTimeUtils.DATE_FORMAT_US.equals(preferredPattern)){
            try {
                date = DateTimeUtils.parse(input, DateTimeUtils.DATE_FORMAT_US);
            } catch (Exception ignored) {
                logger.error(ignored);
            }

            if(date != null){
                return date;
            }
        }

        if(!DateTimeUtils.DATE_FORMAT_US2.equals(preferredPattern)){
            try {
                date = DateTimeUtils.parse(input, DateTimeUtils.DATE_FORMAT_US2);
            } catch (Exception ignored) {
                logger.error(ignored);
            }

            if(date != null){
                return date;
            }
        }

        if(!DateTimeUtils.DATE_FORMAT_COMP.equals(preferredPattern)){
            try {
                date = DateTimeUtils.parse(input, DateTimeUtils.DATE_FORMAT_COMP);
            } catch (Exception ignored) {
                logger.error(ignored);
            }

            if(date != null){
                return date;
            }
        }

        if(!"yyyy_MM_dd".equals(preferredPattern)){
            try {
                date = DateTimeUtils.parse(input, DateTimeUtils.DATE_FORMAT_COMP);
            } catch (Exception ignored) {
                logger.error(ignored);
            }

            if(date != null){
                return date;
            }
        }

        return date;
    }

    //判断是否是日期格式 允许null
    public static boolean isValidDate(String str) {
        if(StringUtils.isBlank(str)){
            return true;
        }
        str=str.trim();
        if (str.contains("/")) {
            str = str.replaceAll("/", "-");
        }
        boolean convertSuccess = true;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        try {
            format.setLenient(false);
            format.parse(str);
        } catch (ParseException e) {
            convertSuccess = false;
        }
        return convertSuccess;
    }
    //比较日期大小
    public static Integer compareDate(String date1,String date2){
        if(StringUtils.isNotBlank(date1) && StringUtils.isNotBlank(date2)){
            date1=date1.trim();
            date2=date2.trim();
            return DateTimeUtils.parseAllType(date1).compareTo(DateTimeUtils.parseAllType(date2));
        }
        return null;
    }

    // checkIn checkOut date filter checkIn not null or empty
    public static boolean checkDateFilter(String checkIn,String checkOut){
        if(StringUtils.isBlank(checkIn)){
            return false;
        }
        if(isValidDate(checkIn)&&isValidDate(checkOut)){
            Integer compare = compareDate(checkIn,checkOut);
            if(null==compare||compare<=0){
                return true;
            }
        }
        return false;
    }

    public static String formatYYMMdd_HHmmss(Date dateTime) {
        return formatDateTime(dateTime, YYMMdd_HHmmss);
    }
}
