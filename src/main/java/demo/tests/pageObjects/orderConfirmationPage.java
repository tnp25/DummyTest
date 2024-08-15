package demo.tests.pageObjects;

import demo.tests.components.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class orderConfirmationPage extends AbstractComponent {

    WebDriver driver;

    public orderConfirmationPage(WebDriver driver){
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css=".hero-primary")
    WebElement orderMessage;


    public String OrderMessage(){
        return orderMessage.getText();
    }


}
