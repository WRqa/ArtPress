package Pages.Settings;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import Pages.Products;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class Pages {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected ConfigProperties data = new ConfigProperties();

    public Pages(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public Products openProductsPage(){
        driver.findElement(By.linkText("Продукция")).click();
        return new Products(driver);
    }

    public void switchBetweenWindows(int windowNumber){
        ArrayList<Object> myHandles = new ArrayList<Object>(driver.getWindowHandles());
        driver.switchTo().window(String.valueOf(myHandles.get(windowNumber)));
    }

    public WebElement fluentWait(){
        FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(30, TimeUnit.SECONDS)
                .pollingEvery(5, TimeUnit.SECONDS)
                .ignoring(NullPointerException.class);

        return wait.until(new Function<WebDriver, WebElement>() {

            public WebElement apply(WebDriver driver) {
                return driver.findElement(By.xpath("//div[@class='modal-content']/div[1]/h4"));
            }
        });
    }

//    (new WebDriverWait(driver, 10))
//            .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@class='btn btn-primary validate-order btn-asset-co']"))).isDisplayed();
}
