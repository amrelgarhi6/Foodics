package base;

import Pages.categoryResultPage;
import Pages.homePage;
import Pages.shoppingCartPage;
import com.shaft.tools.io.JSONFileManager;
import com.shaft.validation.Validations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static Pages.categoryResultPage.freeShippingAttribute;
import static Pages.categoryResultPage.videoGamesTitle;
import static Pages.shoppingCartPage.shoppingCartTitle;

public class End2EndTC extends BaseTest{


    private JSONFileManager homePageData = new JSONFileManager("src/test/resources/testDataFiles/homePage.json");
//    private JSONFileManager gameCategoryData = new JSONFileManager("src/test/resources/TestDataFiles/SummaryScreenData.json");

    private String shopCategory;
    private String gameCategory;
    private String shoppingCartTitle;
    private String sortResultByHighToLowType;
    private String filterOptionsCheckBox;

    private String successMsg;
    private String videoGamesTitle;

    @BeforeMethod
    public void setup() throws InterruptedException {
        successMsg = homePageData.getTestData("successMsg");
        shopCategory = homePageData.getTestData("shopCategory");
        gameCategory = homePageData.getTestData("gameCategory");
        filterOptionsCheckBox = homePageData.getTestData("filterOptionsCheckBox");
        sortResultByHighToLowType = homePageData.getTestData("sortResultByHighToLowType");
        videoGamesTitle = homePageData.getTestData("videoGamesTitle");
        shoppingCartTitle = homePageData.getTestData("shoppingCartTitle");


        new homePage(driver).clickOnAllBtnFromBurgerMenu()
                .clickSeeAllShopCategoryCTA()
                .chooseShopCategory(shopCategory)
                .chooseGamesCategory(gameCategory);
        Validations.assertThat().element(videoGamesTitle()).text().isEqualTo(videoGamesTitle).perform();

        new categoryResultPage(driver).enableFreeShippingFilter(filterOptionsCheckBox);
        Validations.assertThat().element(freeShippingAttribute()).exists().perform();


        new categoryResultPage(driver).filterWithNewConditionOption()
                .selectSortingResultType(sortResultByHighToLowType);
        String actualSortingType = new categoryResultPage(driver).getSelectSortingResultType();
        Validations.assertThat().object(actualSortingType).isEqualTo(sortResultByHighToLowType).perform();

    }


    @Test
    public void checkAddingProductToCart() {
        new categoryResultPage(driver).addItemToBasket(successMsg);
        new shoppingCartPage(driver).clickOnCart();
        Validations.assertThat().element(shoppingCartTitle()).text().contains(shoppingCartTitle).perform();

    }




}
