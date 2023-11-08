package Pages;

import com.shaft.driver.SHAFT;
import org.openqa.selenium.By;

public class shoppingCartPage {





    private final SHAFT.GUI.WebDriver driver;

    //constructor
    public shoppingCartPage(SHAFT.GUI.WebDriver driver) {
        this.driver = driver;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////  Locators  /////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static By shoppingCartTitle() {return By.xpath("//div[@data-testid='loader-loader']");}
    public static By goToshoppingCartBtn() {return By.cssSelector("#nav-cart");}

    public shoppingCartPage clickOnCart (){
        driver.element().click(goToshoppingCartBtn());
        return this;
    }

}
