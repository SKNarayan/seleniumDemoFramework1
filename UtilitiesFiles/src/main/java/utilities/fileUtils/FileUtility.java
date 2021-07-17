package utilities.fileUtils;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class FileUtility {

    public static String createTextFile(String filePath, String fileContent) {
        File file = new File(filePath);
        try (FileWriter writer = new FileWriter(file, false)) {
            writer.write(fileContent);
        } catch (Exception e) {
            throw new RuntimeException("Error while creating the file.", e);
        }
        return null;
    }

    public static String createDownloadDirectory(String directoryName) {
        String directoryPath = "";
        try {
            //  get the project parent folder
            String projectLocation = System.getProperty("user.dir");

            //  get the parent directory location
            directoryPath = projectLocation.substring(0, projectLocation.lastIndexOf("\\")) + "\\" + directoryName;

            //  create directory
            createDirectory(directoryPath);

        } catch (Exception e) {
            throw new RuntimeException("error while creating the directory.", e);
        }
        return directoryPath;
    }

    public static void createDirectory(String directoryPath) {
        try {
            //  create directory
            File file = new File(directoryPath);
            if (!file.exists())
                file.mkdir();
        } catch (Exception e) {
            throw new RuntimeException("error while creating the directory.", e);
        }
    }

    public static void cleanDirectory(String directoryPath) {
        try {
            File file = new File(directoryPath);
            File[] files = file.listFiles();
            for (File fileName : files) {
                if (fileName.isFile()) {
                    fileName.delete();
                } else {
                    throw new Exception("cant delete a file due to open or error");
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("error while performing the clean directory operation.", e);
        }
    }

    public static void createDirectories(String directoryPath) {
        try {
            File file = new File(directoryPath);
            if (!file.exists()) {
                file.mkdirs();
            }
        } catch (Exception e) {
            throw new RuntimeException("error while performing the create directories operation.", e);
        }
    }

    public static void unZipFile(String zipFilePath, String destinationDirectory) {
        try (FileInputStream fileInputStream = new FileInputStream(zipFilePath);
             ZipInputStream zipInputStream = new ZipInputStream(fileInputStream)) {
            FileUtility.createDirectory(destinationDirectory);

            ZipEntry zipEntry = zipInputStream.getNextEntry();
            while (zipEntry != null) {
                String filePath = destinationDirectory + File.separator + zipEntry.getName();
                if (!zipEntry.isDirectory()) {

                    // if the entry is a file, extracts it
                    extractFile(zipInputStream, filePath);
                } else {
                    // if the entry is a directory, make the directory
                    FileUtility.createDirectory(filePath);
                }
                zipInputStream.closeEntry();
                zipEntry = zipInputStream.getNextEntry();
            }

        } catch (Exception e) {
            throw new RuntimeException("error while unzipping the file.", e);
        }
    }

    private static void extractFile(ZipInputStream zipInputStream, String filePath) {
        try (BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(filePath))) {
            byte[] bytesIn = new byte[4096];
            int read;
            while ((read = zipInputStream.read(bytesIn)) != -1) {
                bufferedOutputStream.write(bytesIn, 0, read);
            }
        } catch (Exception e) {
            throw new RuntimeException("error while extracting the file.", e);
        }
    }
}
