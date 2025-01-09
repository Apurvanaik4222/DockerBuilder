package Utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import test.BaseTest;

import java.io.IOException;

public class Listeners extends BaseTest implements ITestListener  {
    private static final Logger log = LoggerFactory.getLogger(Listeners.class);
    ExtentReports extent =  ExtentReportL.getExtentObject();
    ExtentTest test;

    @Override
    public void onTestStart(ITestResult result) {
        log.info("Test Started: {}",result.getName());
        test =extent.createTest(result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        log.info("Test Passed: {}",result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        log.error("Test Failed: {}",result.getName());

        try {
            driver = (WebDriver)result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try {
            test.addScreenCaptureFromBase64String(takeScreenshot(driver));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }





    @Override
    public void onTestSkipped(ITestResult result) {
        log.warn("Test Skipped: {}",result.getName());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        log.error("Test Failed but within success percentage: {}",result.getName());
    }

    @Override
    public void onStart(ITestContext context) {
        log.info("Test Suite Started: {}",context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        log.info("Test Suite Finished: {}",context.getName());
        extent.flush();

    }
}
