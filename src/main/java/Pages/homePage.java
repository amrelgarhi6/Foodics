package Pages;

import com.shaft.driver.SHAFT;
import org.openqa.selenium.By;

public class homePage {

    private final SHAFT.GUI.WebDriver driver;

    //constructor
    public homePage(SHAFT.GUI.WebDriver driver) {
        this.driver = driver;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////  Locators  ///////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static By allBurgerMenuCTA() {return By.xpath("//a[@data-csa-c-slot-id= 'HamburgerMenuDesktop']");}
    public static By seeAllShopByCategoryCTA() {return By.cssSelector("a.hmenu-compressed-btn");}
    public static By chooseCategory(String shopByCategoryName) {return By.xpath("//a[@class='hmenu-item']//div[contains(text(),'"+shopByCategoryName+"')]");}
    public static By chooseGamesCategoryCTA(String gamesCategoryName) {return By.xpath("//a[contains(text(),'"+gamesCategoryName+"')]");}


    public homePage clickOnAllBtnFromBurgerMenu(){
        driver.element().click(allBurgerMenuCTA());
        return this;
    }

    public homePage clickSeeAllShopCategoryCTA(){
        driver.element().click(seeAllShopByCategoryCTA());
        return this;
    }
    public homePage chooseShopCategory(String shopByCategoryName){
        driver.element().click(chooseCategory(shopByCategoryName));
        return this;
    }
    public categoryResultPage chooseGamesCategory(String gamesCategoryName){
        driver.element().click((chooseGamesCategoryCTA(gamesCategoryName)));
        return new categoryResultPage(driver);
    }




}
