package utilities.connectionUtils;

import com.jcraft.jsch.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Properties;

/**
 * class to perform the unix operations
 *
 * @author Shashank2.Singh
 */
public class UnixUtility {

    public static Session session = null;

    /**
     * to create unix connection
     *
     * @param hostName unix hostname in string
     * @param userName username in string
     * @param password password in string
     * @param port     port in integer
     */
    public static void createUnixConnection(String hostName, String userName, String password, int port) {
        try {

            Properties config = new Properties();
            config.put("StrictHostKeyChecking", "no");

            JSch jSch = new JSch();

            session = jSch.getSession(userName, hostName, port);
            session.setPassword(password);

            session.setConfig(config);
            session.connect();
        } catch (Exception e) {
            throw new RuntimeException("Error while connecting the unix server", e);
        }
    }

    /**
     * move file from remote to local directory
     *
     * @param remoteDirectory remote directory path in string
     * @param localDirectory  local directory path in string
     * @param fileName        file name in string
     */
    public static void getFileUsingSftp(String remoteDirectory, String localDirectory, String fileName) {
        Channel channel = null;
        try {
            channel = session.openChannel("sftp");
            channel.connect();

            ChannelSftp channelSftp = (ChannelSftp) channel;
            channelSftp.cd(remoteDirectory);

            channelSftp.get(fileName, localDirectory);

        } catch (Exception e) {
            throw new RuntimeException("Error while performing the sftp file download operation", e);
        } finally {
            channel.disconnect();
        }
    }
    /**
     * execute shell commands
     *
     * @param shellCommands list of shell commands
     */
    public static void performShellOperation(List<String> shellCommands) {
        try {
            Channel channel = session.openChannel("shell");
            ChannelShell channelShell = (ChannelShell) channel;
            channelShell.setPtyType("vt102");
            channelShell.connect();

            try (PrintStream printStream = new PrintStream(channelShell.getOutputStream())) {
                printStream.println("#!/bin/bash");
                for (String command : shellCommands) {
                    printStream.println(command);
                }

                printStream.println("exit");
                printStream.flush();
            } catch (Exception e) {
                throw new RuntimeException("Error while executing the shell commands", e);
            }

            //  read the stream
            byte[] buffer = new byte[1024];

            try (InputStream inputStream = channelShell.getInputStream()) {
                String line = "";
                while (true) {
                    while (inputStream.available() > 0) {
                        int i = inputStream.read(buffer, 0, 1024);
                        if (i < 0)
                            break;
                        line = new String(buffer, 0, i);
                        System.out.println(line);
                    }
                    if (line.contains("Graceful Shutdown Complete"))
                        break;

                    if (channelShell.isClosed())
                        break;
                    Thread.sleep(1000);
                }
            } catch (Exception e) {
                System.out.println("Error while reading channel output: " + e);
            } finally {
                channel.disconnect();
            }
        } catch (Exception e) {
            throw new RuntimeException("Error while performing the shell operation", e);
        }
    }

    /**
     * execute shell commands
     *
     * @param channelShell  shell channel
     * @param shell_command shell command in string
     */
    public static String executeShellCommands(ChannelShell channelShell, String shell_command) {
        OutputStream outputStream;
        try {
            outputStream = new OutputStream() {
                private StringBuilder stringBuilder = new StringBuilder();

                @Override
                public void write(int b) throws IOException {
                    this.stringBuilder.append((char) b);
                }

                public String toString() {
                    return this.stringBuilder.toString();
                }
            };

            channelShell.setOutputStream(outputStream);
            PrintStream printStream = new PrintStream(channelShell.getOutputStream());
            printStream.println(shell_command);

            printStream.flush();
            Thread.sleep(1000);
        } catch (Exception e) {
            throw new RuntimeException("Error while executing the shell commands.", e);
        }
        return outputStream.toString();
    }

    public static void uploadFileOverSftp(String remoteDirectory, String localDirectory, String fileName) {
        Channel channel;
        try {
            channel = session.openChannel("sftp");
            channel.connect();

            ChannelSftp channelSftp = (ChannelSftp) channel;
            channelSftp.cd(remoteDirectory);
            channelSftp.put(localDirectory + "//" + fileName, fileName);

            if(channelSftp.isConnected()) {
                channelSftp.disconnect();
                channel.disconnect();
            }

        } catch (Exception e) {
            throw new RuntimeException("Error while uploading the file.", e);
        }
    }

    public static void deleteFileOverSftp(String remoteDirectory, String fileName) {
        Channel channel;
        try {
            channel = session.openChannel("sftp");
            channel.connect();

            ChannelSftp channelSftp = (ChannelSftp) channel;
            channelSftp.cd(remoteDirectory);
            channelSftp.rm(fileName);

            if(channelSftp.isConnected()) {
                channelSftp.disconnect();
                channel.disconnect();
            }

        } catch (Exception e) {
            throw new RuntimeException("Error while uploading the file.", e);
        }
    }

    public static boolean checkFileExist(String filePath) {
        SftpATTRS attrs;
        Channel channel;
        try {
            channel = session.openChannel("sftp");
            channel.connect();

            ChannelSftp channelSftp = (ChannelSftp) channel;
            attrs = channelSftp.stat(filePath);
            channelSftp.exit();

            channel.disconnect();

            if(attrs != null)
                return true;
            else
                return false;

        } catch (Exception e) {
            throw new RuntimeException("path not found", e);
        }
    }
}
