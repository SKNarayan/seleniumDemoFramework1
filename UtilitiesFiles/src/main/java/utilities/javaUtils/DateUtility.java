package utilities.javaUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Class for date related operations
 *
 * @author Shashank2.Singh
 */
public class DateUtility {
    /**
     * to convert format of date
     *
     * @param dateInString date in string format
     * @param oldFormat    old format of date, for example:
     *                     Date is: 30-01-2020 | Format of date is: dd-MM-yyyy (old format)
     * @param newFormat    new format, for example ddMMyyyy
     * @return return will be string date 30012020
     */
    public static String convertDateToNewFormat(String dateInString, String oldFormat, String newFormat) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(oldFormat);
            Date date = simpleDateFormat.parse(dateInString);

            return new SimpleDateFormat(newFormat).format(date);

        } catch (Exception e) {
            throw new RuntimeException("DateUtility | convert_date_to_new_format : Error while converting the date in new format.");
        }
    }

    public static String convertDateToNewFormat(Date date, String newFormat) {
        try {
            return new SimpleDateFormat(newFormat).format(date);

        } catch (Exception e) {
            throw new RuntimeException("DateUtility | convert_date_to_new_format : Error while converting the date in new format.");
        }
    }

    /**
     * get current date time in specific date format
     *
     * @param dateTimeFormat in which format you want to get the current date time
     *                       for example dd-MM-yyyy
     * @return current date time, for example 30-01-2020
     */
    public static String getCurrentDateTime(String dateTimeFormat) {
        try {
            return convertDateToNewFormat(new SimpleDateFormat("dd MMM yyyy HH:mm:ss").format(new Date()), "dd MMM yyyy HH:mm:ss", dateTimeFormat);
        } catch (Exception e) {
            throw new RuntimeException("DateUtility | convert_date_to_new_format : Error while getting the current date time.");
        }
    }

    /**
     * to check whether the date valid or not
     *
     * @param inDate date in string format
     * @param format format of date, for example dd-MMM-yy
     * @return for valid case return is true
     * for invalid case return is false
     */
    public static boolean isValidDate(String inDate, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        dateFormat.setLenient(false);

        try {
            dateFormat.parse(inDate.trim());
        } catch (ParseException pe) {
            return false;
        }
        return true;
    }


    public static String addDaysInCurrentDate(int numberOfDays, String dateFormat) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
            Calendar calendar = Calendar.getInstance();

            calendar.add(Calendar.DATE, numberOfDays);
            return simpleDateFormat.format(calendar.getTime());

        } catch (Exception e) {
            throw new RuntimeException("DateUtility | addDaysInCurrentDate : Error while adding the days in current date time.");
        }
    }

    public static String addMonthInCurrentDate(int numberOfMonths, String dateFormat)
    {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
            Calendar calendar = Calendar.getInstance();

            calendar.add(Calendar.MONTH, numberOfMonths);
            return simpleDateFormat.format(calendar.getTime());

        } catch (Exception e) {
            throw new RuntimeException("DateUtility | addMonthInCurrentDate : Error while adding the months in current date time.");
        }
    }

    public static String addYearInCurrentDate(int numberOfYears, String dateFormat)
    {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
            Calendar calendar = Calendar.getInstance();

            calendar.add(Calendar.YEAR, numberOfYears);
            return simpleDateFormat.format(calendar.getTime());

        } catch (Exception e) {
            throw new RuntimeException("DateUtility | addYearInCurrentDate : Error while adding the years in current date time.");
        }
    }

    public static String getTimeInMillisSeconds() {
        try {
            Calendar calendar = Calendar.getInstance();
            return String.valueOf(calendar.getTimeInMillis());

        } catch (Exception e) {
            throw new RuntimeException("DateUtility | getTimeInMillisSeconds : Error while converting the date in millis second. .");
        }
    }
}
