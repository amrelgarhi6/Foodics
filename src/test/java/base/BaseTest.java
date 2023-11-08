package base;

import Pages.EntryPointPage;
import com.shaft.driver.SHAFT;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    public SHAFT.GUI.WebDriver driver;

    @BeforeMethod
    public void init() {
        driver = new SHAFT.GUI.WebDriver();
        new EntryPointPage(driver).openPortal();
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod(){
        driver.quit();
    }
}