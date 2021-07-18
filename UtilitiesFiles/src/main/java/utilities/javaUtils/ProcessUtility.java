package utilities.javaUtils;

public class ProcessUtility {

    public static void waitForProcessToComplete(Process process, int waitTime) {
        try {
            int i = 0;
            do {
                Thread.sleep(1000);
            } while (process.isAlive() && (i <= waitTime));

        } catch (Exception e) {
            throw new RuntimeException("Error while performing the operation on process.", e);
        }
    }
}
