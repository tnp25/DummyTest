package demo.tests;

import demo.testComponents.baseTest;
import demo.tests.pageObjects.cartPage;
import demo.tests.pageObjects.checkoutPage;
import demo.tests.pageObjects.orderConfirmationPage;
import demo.tests.pageObjects.productCatalougePage;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubmitOrderTest extends baseTest {

    @Test(dataProvider="dataFromJson")
    public void placeOrder(Map<String,String> map) throws InterruptedException, IOException {

//        extentReports.createTest("placeOrder");

        //product page object
        productCatalougePage productCatalougeObj =
                landingPageObj.loginApplication(map.get("email"), map.get("password"));
        productCatalougeObj.getMeProducts();
        productCatalougeObj.selectDesiredProduct(map.get("productName"));
        //cart page object
        cartPage cartPageObj =
                productCatalougeObj.navigateToCart();
        cartPageObj.validateCartContents(map.get("productName"));
        //checkout page object
        checkoutPage checkoutPageObj =
                cartPageObj.proceedCheckout();
        checkoutPageObj.selectCountryInCheckout("India");
        //orderConfirmation page object
        orderConfirmationPage orderConfirmationPageObj =
                checkoutPageObj.placeOrder();
        String actualOrderConfirmationText = orderConfirmationPageObj.OrderMessage();
        Assert.assertEquals(actualOrderConfirmationText,"THANKYOU FOR THE ORDER.");

    }

    @DataProvider(name="dataFromMap")
    public Object[][] getDataFromMap() throws IOException, ParseException {
        Object[][] newObj = new Object[2][1];
        HashMap<String,String> map = new HashMap<String,String>();
        map.put("email","nbjb4214@flymail.tk");
        map.put("password","Nbjb4214@flymail.tk");
        map.put("productName","ZARA COAT 3");
        newObj[0][0]=map;
        map.put("productName","ADIDAS ORIGINAL");
        newObj[1][0]=map;

        return newObj;
    }

    @DataProvider(name="dataFromJson")
    public Object[][] getDataFromJson() throws IOException, ParseException {
        List<Map<String,String>> data = getJsonData(System.getProperty("user.dir")+"/src/test/java/demo/data/purchaseData.json");
        return new Object[][] {{data.get(0)},{data.get(1)}};
    }


}
