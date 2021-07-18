package utilities.javaUtils;

import java.util.Random;

public class DataGeneratorUtility {

    public static String generateMobileNumber() {
        try {
            Random random = new Random();
            String num2 = "";

            int num = random.nextInt(900000000) + 100000000;
            String str = String.valueOf(num);

            boolean loop = true;
            while (loop) {
                int num1 = random.nextInt(9);
                if (num1 == 9 || num1 == 8 || num1 == 7) {
                    loop = false;
                    num2 = String.valueOf(num1);
                }
            }
            return num2 + str;
        } catch (Exception e) {
            throw new RuntimeException("Error while generating Mobile Number " + e.getMessage());
        }
    }

    public static String generatePan() {
        try {
            Random random = new Random();
            return ("AJWPQ" + (random.nextInt(9999) + 1) + "P");
        } catch (Exception e) {
            throw new RuntimeException("Error while generating pan number " + e.getMessage());
        }
    }

    public static String getAlphaNumericString(int stringLength) {
        try {
            // chose a Character random from this String
            String alphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                    + "0123456789"
                    + "abcdefghijklmnopqrstuvxyz";

            StringBuilder stringBuilder = new StringBuilder(stringLength);
            for (int i = 0; i < stringLength; i++) {
                int index
                        = (int) (alphaNumericString.length()
                        * Math.random());

                stringBuilder.append(alphaNumericString
                        .charAt(index));
            }
            return stringBuilder.toString();
        } catch (Exception e) {
            throw new RuntimeException("error while generating the Alpha-Numeric string.", e);
        }
    }

    public static String getNumericString(int stringLength) {
        try {
            String alphaNumericString = "0123456789";
            StringBuilder stringBuilder = new StringBuilder(stringLength);
            for (int i = 0; i < stringLength; i++) {
                int index
                        = (int) (alphaNumericString.length()
                        * Math.random());

                stringBuilder.append(alphaNumericString
                        .charAt(index));
            }
            return stringBuilder.toString();
        } catch (Exception e) {
            throw new RuntimeException("error while generating the Alpha-Numeric string.", e);
        }
    }
}
