package utilities.mobileUtils;

import utilities.javaUtils.ProcessUtility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MobileOSUtility {

    private static String[] WIN_RUNTIME = {"cmd.exe", "/C", ""};
    private static String[] OS_LINUX_RUNTIME = {"/bin/bash", "-c", ""};

    /**
     * This Method reads the device name from the command {"adb devices -l"}
     * and returns the device name
     *
     * @return Device Name
     */
    public static String getDeviceName() {
        String commandOutput = null;
        String line, previous = null;
        BufferedReader bufferedReader;

        try {
            bufferedReader = runShellCommand("adb devices -l");

            //  need the second line of output so ignoring the firstLine
            bufferedReader.readLine();
            while ((line = bufferedReader.readLine()) != null && !line.isEmpty())
                if (!line.equals(previous)) {
                    previous = line;
                    commandOutput = line;
                }
        } catch (Exception e) {
            throw new RuntimeException("Error while fetching the device name " + e.getMessage());
        }
        return commandOutput.split("device:")[1].split(" ")[0];
    }

    /**
     * This Method reads the device name from the command {"adb devices -l"}
     * and returns the device ud id
     *
     * @return Device ud id
     */
    public static String getUDId() {
        String commandOutput = null;
        String line, previous = null;
        BufferedReader bufferedReader;

        try {
            bufferedReader = runShellCommand("adb devices -l");

            //  need the second line of output so ignoring the firstLine
            bufferedReader.readLine();
            while ((line = bufferedReader.readLine()) != null && !line.isEmpty())
                if (!line.equals(previous)) {
                    previous = line;
                    commandOutput = line;
                }
        } catch (Exception e) {
            throw new RuntimeException("Error while fetching the device name " + e.getMessage());
        }
        return commandOutput.split(" ")[0];
    }

    /**
     * This Method reads the OS version from the device and returns OS version
     *
     * @return OS Version
     */
    public static String getDeviceOsVersion() {
        BufferedReader bufferedReader;
        String deviceOsVersion;
        try {
            bufferedReader = runShellCommand("adb shell getprop ro.build.version.release");
            deviceOsVersion = bufferedReader.readLine();

        } catch (Exception e) {
            throw new RuntimeException("error while fetching OS name. " + e.getMessage());
        }
        return deviceOsVersion;
    }


    /**
     * This method runs the shell command. It works both on MAC and WINDOWS
     *
     * @param command - Command which is to be executed
     * @return Output in BufferReader
     */
    public static BufferedReader runShellCommand(String command) {
        //  store commands
        String[] arrCommand = new String[3];
        BufferedReader bufferedReader;

        try {
            //  check for operating system
            String operatingSystemName = System.getProperty("os.name").toLowerCase();
            if (operatingSystemName.contains("win"))
                arrCommand = WIN_RUNTIME;

            if (operatingSystemName.contains("mac"))
                arrCommand = OS_LINUX_RUNTIME;

            //  add command
            arrCommand[2] = command;
            ProcessBuilder processBuilder = new ProcessBuilder(arrCommand);
            processBuilder.command();

            Process process = processBuilder.start();
            ProcessUtility.waitForProcessToComplete(process, 10);
            bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));

        } catch (IOException e) {
            throw new RuntimeException("error while running the commands.", e);
        }
        return bufferedReader;
    }
}
