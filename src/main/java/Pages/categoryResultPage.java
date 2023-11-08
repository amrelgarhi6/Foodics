package Pages;

import com.shaft.driver.SHAFT;
import com.shaft.validation.Validations;
import org.apache.poi.ss.formula.functions.Subtotal;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.locators.RelativeLocator;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import static Pages.productPage.*;
import static Pages.productPage.subTotalPrice;

public class categoryResultPage {


    private final SHAFT.GUI.WebDriver driver;

    //constructor
    public categoryResultPage(SHAFT.GUI.WebDriver driver) {
        this.driver = driver;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////  Locators  ///////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static By videoGamesTitle() {return By.cssSelector(".pageBanner");}
    public static By selectFilterOptionCheckBox(String filterOptionsCheckBox) {return By.xpath("(//span[text()='"+filterOptionsCheckBox+"'])[2]");}
    public static By conditionOptions() {return By.xpath("(//li[contains(@id,'condition-type')]//span[@class='a-list-item'])[1]");}
    public static By selectSortingResult() {return By.xpath("//select[contains(@id,'result-sort-select')]");}
    public static By freeShippingAttribute() {return By.xpath("//ul[contains(@aria-labelledby,'free_shipping_eligible-title')]");}
    public static By listOfItemsPrices  = By.xpath("//div[@class='puisg-row']//span[@class='a-price-whole']");

    public static By navigationBar(String pageNumber) {return By.xpath("//span[contains(@class,'pagination')]//a[@aria-label='Go to page "+pageNumber+"']");}



    int price;
    int[] subtotal;

    List<WebElement> listOfPrices ;



    public categoryResultPage enableFreeShippingFilter(String filterOptionsCheckBox){
        driver.element().getCurrentFrame();
        driver.element().click((selectFilterOptionCheckBox(filterOptionsCheckBox)));
        return this;
    }
    public categoryResultPage filterWithNewConditionOption(){
        driver.element().click((conditionOptions()));
        return this;
    }
    public categoryResultPage selectSortingResultType(String sortResultType)  {
        driver.element().select(selectSortingResult(),sortResultType);
        return this;
    }
    public String getSelectSortingResultType()  {
         return driver.element().getSelectedText(selectSortingResult());
    }

    public categoryResultPage addItemToBasket(String successMsg)   {

    int i = 0;
    while (i <= 2) {
        listOfPrices= driver.getDriver().findElements(listOfItemsPrices);

        int k;
        for (k = 0; k < listOfPrices.size(); k++) {
            price = Integer.parseInt(listOfPrices.get(k).getText().replaceAll(",", ""));
            System.out.println("The price after editing  >>> " + price);

            if (price < 15000) {
                System.out.println("Price is less than 15000 I'm going to add it cart  ");
                listOfPrices.get(k).click();
                driver.element().click(addProductToCartBtn());
                System.out.println("Product page opened");
                subtotal = new int[]{price};
                int subtotalPrice = Integer.parseInt(driver.element().getText(subTotalPrice()).replaceAll(",", ""));
                Validations.assertThat().object(subtotalPrice).isEqualTo(price).perform();
                Validations.assertThat().element(successAddToCartMsg()).text().isEqualTo(successMsg).perform();
                driver.browser().navigateBack();
                driver.element().click(backToResultPageBtn());
                System.out.println("Array f subtotal  >>>>  "+Arrays.toString(subtotal));
            } else
                continue;
            return this;
        }
        System.out.println("2 >>>>>>>");
        driver.element().click(navigationBar("3"));
        i++;
    }
    System.out.println("Array f subtotal  >>>>  "+Arrays.toString(subtotal));
    return this;
}














}









