package demo.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {

//    public static ExtentReports extentReports;


    public static ExtentReports reportConfig(){
        String path = System.getProperty("user.dir")+"\\reports\\report.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        reporter.config().setReportName("Automation Result");
        reporter.config().setDocumentTitle("Test Results");

        ExtentReports extentReports = new ExtentReports();
        extentReports.attachReporter(reporter);
        extentReports.setSystemInfo("Tester","Naveen Prasath");

        return extentReports;
    }
}
