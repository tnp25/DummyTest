package demo.testComponents;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import demo.resources.ExtentReporterNG;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listeners extends baseTest implements ITestListener {

    ExtentTest test;
    ExtentReports extent = ExtentReporterNG.reportConfig();

//    public ExtentReports extent;

    @Override
    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.log(Status.PASS,"Test Passed Successfully");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.fail(result.getThrowable());
        String path = null;
        try {
            driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
            path = getScreenshot(result.getMethod().getMethodName(),driver);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        test.addScreenCaptureFromPath(path,result.getMethod().getMethodName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        // TODO Auto-generated method stub
        test.log(Status.SKIP,"Retrying Again, count : 1");
    }


    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}
