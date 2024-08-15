package demo.testComponents;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import demo.tests.pageObjects.landingPage;

import org.apache.commons.io.FileUtils;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class baseTest {

    public WebDriver driver;
    public landingPage landingPageObj;

    public WebDriver initializeDriver() throws IOException {
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\java\\demo\\resources\\globalData.properties");
        prop.load(fis);
        String browser = prop.getProperty("browser");

        if(browser.equalsIgnoreCase("chrome")){
            if(prop.getProperty("headless").equalsIgnoreCase("yes")){
                ChromeOptions options=new ChromeOptions();
                options.addArguments("headless");
                driver=new ChromeDriver(options);
            }
            else{
                driver = new ChromeDriver();
            }

        }
        else{
            driver = new EdgeDriver();
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        return driver;
    }

    public List<Map<String, String>> getJsonData(String filePath) throws IOException, ParseException {

        // json to string

        String json =
//                File.readFileToString(new File(filePath), StandardCharsets.UTF_8);
                new String(Files.readAllBytes(Paths.get(filePath)));

        //string to map using JacksonBind (maven dependency)

        ObjectMapper mapper = new ObjectMapper();
        List<Map<String, String>> map = mapper.readValue(json, new TypeReference<List<Map<String,String>>>() {});
        return map;
    }

    public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
        TakesScreenshot ts =  (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        File file = new File(System.getProperty("user.dir") + "\\reports\\screenshots\\" + testCaseName + ".png");
        FileUtils.copyFile(source, file);

        return System.getProperty("user.dir") + "\\reports\\screenshots\\" + testCaseName + ".png";
    }

    @BeforeMethod(alwaysRun = true)
    public landingPage launchApplication() throws IOException {
        initializeDriver();
        landingPageObj = new landingPage(driver);
        landingPageObj.gotoLandingPage();
        return landingPageObj;
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(){

        driver.quit();
    }


}
