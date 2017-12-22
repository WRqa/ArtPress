import Pages.Settings.ConfigProperties;
import Pages.Settings.ReadMail;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.util.concurrent.TimeUnit;

public class BasicData {

    WebDriver driver;
    WebDriverWait wait;
    ConfigProperties data = new ConfigProperties();


    @BeforeTest
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "/Users/workrocksQA/BB_AutoTests/Drivers_for_Selenium/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 30, 500);

        driver.manage().window().fullscreen();

        new ReadMail(driver)
                .clearAllMessages("investor1qa@gmail.com", "test55555");
        new ReadMail(driver)
                .clearAllMessages("investor2qa@gmail.com", "test55555");

        driver.get("https://new.one2print.com.ua/");
    }

    @AfterTest
    public void tearDown(){
        if (driver != null)
            driver.quit();
    }
}
