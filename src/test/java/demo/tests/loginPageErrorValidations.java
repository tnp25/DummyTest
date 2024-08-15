package demo.tests;

import demo.testComponents.baseTest;
import demo.testComponents.retry;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class loginPageErrorValidations extends baseTest {

    @Test(groups = {"ErrorOnLogin"},retryAnalyzer = retry.class)
    public void incorrectLoginCreds() throws InterruptedException, IOException {

        //product page object
//        extentReports.createTest("incorrectLoginCreds");

        landingPageObj.loginApplication("nbjb4214@flymail.tk","duped");
        Assert.assertEquals(landingPageObj.getCredentialsErrorMessage(),"Incorrect email or password.");

    }

    @Test
    public void incorrectEmailValidation(){
//        extentReports.createTest("incorrectEmailValidation");
        String errorMessage = landingPageObj.getEmailErrorMessage("gdd");
        Assert.assertEquals(errorMessage,"*Enter Valid Email");
    }



}
