package demo.tests.pageObjects;

import demo.tests.components.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class productCatalougePage extends AbstractComponent {

    WebDriver driver;
    public productCatalougePage(WebDriver driver){
        super(driver);
        this.driver= driver;
        PageFactory.initElements(this.driver,this);
    }

    By productBy = By.cssSelector(".mb-3");
    @FindBy(css=".mb-3")
    List<WebElement> products;

    By productText = By.cssSelector("b");

    By selectProduct = By.cssSelector(".card-body button:last-of-type");

    By successMessgageAppearance = By.cssSelector("#toast-container");


    @FindBy(css=".ng-animating")
    WebElement successMessageDisappeared;

    public List<WebElement> getMeProducts(){
        waitForLocatorToAppear(productBy);
//        products = driver.findElements(By.cssSelector(".mb-3"));
        return products;
    }


    public WebElement findProduct(String productName) {
        WebElement desiredProduct = products.stream().filter(product->
                product.findElement(productText).getText().equals(productName)).findFirst().orElse(null);
        return desiredProduct;
    }

    public void selectDesiredProduct(String productName) throws InterruptedException {
        findProduct(productName).findElement(selectProduct).click();
        waitForLocatorToAppear(successMessgageAppearance);
        waitForElementToDisapper(successMessageDisappeared);
    }
}
