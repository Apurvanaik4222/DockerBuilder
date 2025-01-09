package Utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportL {


    public static ExtentReports getExtentObject(){

        ExtentSparkReporter reporter = new ExtentSparkReporter(System.getProperty("user.dir")+"\\src\\test\\java\\Utils\\ExtentReport.html");
        reporter.config().setTheme(Theme.DARK);
        reporter.config().setDocumentTitle("Automation Report");

        ExtentReports extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Tester","Vishal");
        return extent;
    }
}
