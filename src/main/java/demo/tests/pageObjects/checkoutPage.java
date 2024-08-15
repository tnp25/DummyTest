package demo.tests.pageObjects;

import demo.tests.components.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class checkoutPage extends AbstractComponent {

    WebDriver driver;

    public checkoutPage(WebDriver driver){
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath="(//button[contains(@class,'ta-item')])[2]")
    WebElement indiaBtn;

    By results = By.cssSelector(".ta-results");

    @FindBy(xpath="//input[@placeholder='Select Country']")
    WebElement countryDropdown;

    @FindBy(css=".action__submit")
    WebElement placeOrderBtn;


    public void selectCountryInCheckout(String countryName){
        Actions a = new Actions(driver);
        a.sendKeys(countryDropdown, countryName).build().perform();
        waitForLocatorToAppear(results);
        indiaBtn.click();
    }

    public orderConfirmationPage placeOrder(){
        placeOrderBtn.click();
        return new orderConfirmationPage(driver);
    }

}
