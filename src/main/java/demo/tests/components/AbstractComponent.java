package demo.tests.components;

import demo.tests.pageObjects.cartPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AbstractComponent {

    WebDriver driver;
    WebDriverWait wait;
    @FindBy(xpath="//button[@routerlink='/dashboard/cart']")
    WebElement cartBtn;


    public AbstractComponent(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriverWait waitHelper(){
        if(wait == null){
            wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        }
        return wait;
    }

    public cartPage navigateToCart(){
        cartBtn.click();
        cartPage cartPageObj = new cartPage(driver);
        return cartPageObj;
    }

    public void waitForLocatorToAppear(By findby){
        waitHelper().until(ExpectedConditions.visibilityOfElementLocated(findby));
    }

    public void waitForElementToAppear(WebElement webElement){
        waitHelper().until(ExpectedConditions.visibilityOf(webElement));
    }

    public void waitForElementToDisapper(WebElement Element) throws InterruptedException {
//        waitHelper().until(ExpectedConditions.invisibilityOf(Element));
        Thread.sleep(1000);
    }

}

