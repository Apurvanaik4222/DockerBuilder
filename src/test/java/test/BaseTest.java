package test;

import Utils.Config;
import Utils.Constants;
import Utils.JsonToMapReader;
import com.google.common.util.concurrent.Uninterruptibles;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class BaseTest {

    protected WebDriver driver;
    protected List<HashMap<String,Object>>  list;
    protected HashMap<String,Object> map;
    protected static final Logger logg =LoggerFactory.getLogger(BaseTest.class);


    @BeforeSuite(alwaysRun = true)
    public  void configSetUp() {
        try {

            Config.initialize();
            logg.info("Configurations are loaded successfully");

        } catch (IOException e) {
            logg.error(e.getMessage());
        }
    }


        @BeforeTest
    @Parameters({"filePath"})
    public void setup(String filePath) throws IOException {
       this.driver = Boolean.getBoolean(Constants.GRID_ENABLE)?getRemoteDriver():getLocalDriver();
        this.driver.manage().window().maximize();
        this.list = JsonToMapReader.reader(filePath);
        this.map = list.get(0);

    }

    public WebDriver getLocalDriver(){
        return new ChromeDriver();
    }

    public RemoteWebDriver getRemoteDriver() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        if(Config.getProperty(Constants.BROWSER).equalsIgnoreCase(Constants.CHROME))
            capabilities.setBrowserName(Constants.CHROME);
            else if(Config.getProperty(Constants.BROWSER).equalsIgnoreCase(Constants.FIREFOX))
                capabilities.setBrowserName(Constants.FIREFOX);
            else
                capabilities.setBrowserName(Constants.EDGE);

           String hubHost ="hub";
           String urlFormat =Config.getProperty(Constants.GRID_URL_FORMAT);
           String url =String.format(urlFormat,hubHost);
        logg.info("--------HUB_HOST----------");
        logg.info(Config.getProperty("hubHost"));
        logg.info("--------URL----------");
        logg.info(url);
        return new RemoteWebDriver(new URL(url),capabilities);
    }

    @AfterTest
    public void tearDown(){
        driver.quit();
    }

    @AfterMethod
    public void sleep(){
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(5));
    }


    public String takeScreenshot(WebDriver driver) throws IOException {
        // code to take screenshot
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String dateL = formatter.format(date)+".png";

        File file = new File(((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64));
        FileUtils.copyFile(file,new File(dateL));
        return dateL;
    }


}
