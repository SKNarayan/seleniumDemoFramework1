package utilities.javaUtils;

import org.apache.commons.lang3.StringUtils;

public class StringUtility {

    public static String getAllIntegerInString(String string) {
        try {
            return string.replaceAll("[^0-9]", "");
        } catch (Exception e) {
            throw new RuntimeException("Error while getting the number form the string. String is : " + string);
        }
    }

    public static String stringLeftPadding(String string, int length, String replacementString) {
        try {
            return StringUtils.leftPad(string, length, replacementString);
        } catch (Exception e) {
            throw new RuntimeException("error while performing the left padding.");
        }
    }

    public static String stringRightPadding(String string, int length, String replacementString) {
        try {
            return StringUtils.rightPad(string, length, replacementString);
        } catch (Exception e) {
            throw new RuntimeException("error while performing the right padding.");
        }
    }
}
