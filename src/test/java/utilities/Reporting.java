package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.utils.FileUtil;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
//Listener class used to generate extent reports
public class Reporting extends TestListenerAdapter {

    public ExtentHtmlReporter htmlReporter;
    public ExtentReports extent;
    public ExtentTest logger;
    WebDriver driver;

    public void onStart(ITestContext testContext) {
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()); //time stamp
        String reportName = "Test-Report-" + timeStamp + ".html";
        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/test-output/" + reportName);//specifying the location of the test report
      //  htmlReporter.loadXMLConfig(System.getProperty("user.dir") + "/extent-config.xml");

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("User", "Shashi");

        htmlReporter.config().setDocumentTitle("ClearTrip test automation");//Title of the report
        htmlReporter.config().setReportName("Search flight test");//name of the report
        htmlReporter.config().setTheme(Theme.DARK);

    }

    public void onTestSuccess(ITestResult testResult){
        logger = extent.createTest(testResult.getName()); //create new entry in the report
        logger.log(Status.PASS, MarkupHelper.createLabel(testResult.getName(), ExtentColor.GREEN)); // send the passed information

    }

    public void onTestFailure(ITestResult testResult){
        logger = extent.createTest(testResult.getName()); //create new entry in the report
        logger.log(Status.FAIL, MarkupHelper.createLabel(testResult.getName(), ExtentColor.RED)); //send passed information

        String screenshotpath = screenshotUtility.takeScreenshot(driver, System.getProperty("user.dir")+"/Screenshots", testResult.getName());
       // String screenshotpath = System.getProperty("user.dir")+"/Screenshots/" + testResult.getName()+".png";
        System.out.println("screenshot path is : "+ screenshotpath);
        File file = new File(screenshotpath);
        if(file.exists()){
            try{
                logger.fail("screenshot is below: "+logger.addScreenCaptureFromPath(screenshotpath));
            }catch (IOException e){
                e.printStackTrace();
            }
        }

    }

    public void onTestSkipped(ITestResult testResult){
        logger = extent.createTest(testResult.getName());//create new entry in the report
        logger.log(Status.SKIP, MarkupHelper.createLabel(testResult.getName(), ExtentColor.ORANGE));
    }

    public void onFinish(ITestContext testContext){
        extent.flush();
    }



}
