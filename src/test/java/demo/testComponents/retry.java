package demo.testComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class retry implements IRetryAnalyzer {

    int count=0;
    int maxCounter=1;
    @Override
    public boolean retry(ITestResult iTestResult) {
        if(count<maxCounter){
            count++;
            return true;
        }
        return false;
    }
}
