package demo.tests.pageObjects;

import demo.tests.components.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

public class cartPage extends AbstractComponent {

    WebDriver driver;

    public cartPage(WebDriver driver){
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath="//button[@routerlink='/dashboard/cart']")
    WebElement cartBtn;

    @FindBy(css=".cartSection h3")
    List<WebElement> contentInCart;

    @FindBy(css=".totalRow button")
    WebElement checkoutBtn;


    public void validateCartContents(String productName){
        Boolean match = contentInCart.stream().allMatch(cartProduct-> cartProduct.getText().equalsIgnoreCase(productName));
        Assert.assertTrue(match);
    }

    public checkoutPage proceedCheckout() {
        checkoutBtn.click();
        return new checkoutPage(driver);
    }
}