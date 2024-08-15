package demo.tests.pageObjects;

import demo.tests.components.AbstractComponent;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class landingPage extends AbstractComponent {

    WebDriver driver;
    String url ="https://rahulshettyacademy.com/client";
    public landingPage(WebDriver driver){
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(id="userEmail")
    WebElement userEmail;

    @FindBy(id="userPassword")
    WebElement userPassword;

    @FindBy(id="login")
    WebElement loginBtn;

    @FindBy(css="[class*='flyInOut']")
    WebElement credentialsErrorMessage;

    @FindBy(css=".invalid-feedback div")
    WebElement invalidEmailErrorMessage;

    public productCatalougePage loginApplication(String email, String password){
        userEmail.sendKeys(email);
        userPassword.sendKeys(password);
        loginBtn.click();
        productCatalougePage productCatalougePageObj = new productCatalougePage(driver);
        return productCatalougePageObj;
    }

    public void gotoLandingPage(){
        driver.get(url);
    }

    public String getCredentialsErrorMessage(){
        waitForElementToAppear(credentialsErrorMessage);
        return credentialsErrorMessage.getText();
    }

    public String getEmailErrorMessage(String invalidEmailAddress){
        userEmail.sendKeys(invalidEmailAddress);
        userEmail.sendKeys(Keys.ENTER);
        return invalidEmailErrorMessage.getText();
    }

}

