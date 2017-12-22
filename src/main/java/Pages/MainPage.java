package Pages;

import Pages.Settings.Pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends Pages {

    public MainPage(WebDriver driver){
        super(driver);
    }

    private void clickLoginIcon(){
        driver.findElement(By.xpath("//i[@class='sf-icon-account']")).click();
    }

    public Login openLoginPage(){
        clickLoginIcon();
        return new Login(driver);
    }


}
