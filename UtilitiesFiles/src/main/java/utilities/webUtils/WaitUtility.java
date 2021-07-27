package utilities.webUtils;

import java.util.Properties;

public class WaitUtility {

    private static int minWaitInSeconds;
    private static int avgWaitInSeconds;
    private static int maxWaitInSeconds;

    public static void setWaitValueInSeconds(Properties properties) {
        try {
            minWaitInSeconds = Integer.parseInt(properties.getProperty("MIN_WAIT")) * 1000;
            avgWaitInSeconds = Integer.parseInt(properties.getProperty("AVG_WAIT")) * 1000;
            maxWaitInSeconds = Integer.parseInt(properties.getProperty("MAX_WAIT")) * 1000;

        } catch (Exception e) {
            throw new RuntimeException("error while setting the hard wait values.", e);
        }
    }

    public static void minimumWait() {
        try {
            Thread.sleep(minWaitInSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void averageWait() {
        try {
            Thread.sleep(avgWaitInSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void maximumWait() {
        try {
            Thread.sleep(maxWaitInSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
