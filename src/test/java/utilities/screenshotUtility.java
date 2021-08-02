package utilities;

import cleartripTestCases.CleartripBaseClass;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class screenshotUtility extends CleartripBaseClass {

    public static String takeScreenshot(WebDriver driver, String filePath, String fileName)  {
    try {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        File fileSrc = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String fileFullPath = filePath + fileName + simpleDateFormat.format(new Date());
        try {
            FileUtils.copyFile(fileSrc, new File(fileFullPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileFullPath;
    }catch (Exception e){
        throw new RuntimeException("Error while taking screenshot");
    }
    }



}
