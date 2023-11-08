package Pages;

import com.shaft.driver.SHAFT;
import org.openqa.selenium.By;

public class productPage {


    private final SHAFT.GUI.WebDriver driver;

    //constructor
    public productPage(SHAFT.GUI.WebDriver driver) {
        this.driver = driver;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////  Locators  ///////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static By addProductToCartBtn() {return By.cssSelector("input#add-to-cart-button");}
    public static By subTotalPrice  () {return By.xpath("//span[contains(@class,'subtotal-amount')]//span[@class='a-price-whole']");}

    public static By productPriceDetailsPage() {return By.xpath("(//div[contains(@id,'corePrice')])[1]//span[@class='a-price-whole']");}
    public static By successAddToCartMsg() {return By.xpath("//div[contains(@id,'MSG_SUCCESS')]");}

    public static By backToResultPageBtn() {return By.id("breadcrumb-back-link");}
    public static By warrantyHeader() {return By.id("attach-warranty-header");}
    public static By skipWarrantyHeaderBtn() {return By.id("attachSiNoCoverage");}










}
