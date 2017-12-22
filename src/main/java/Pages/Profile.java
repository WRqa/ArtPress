package Pages;

import Pages.Settings.Pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Profile extends Pages{

    public Profile(WebDriver driver){
        super(driver);
    }

    public String getURL(){
        return driver.getCurrentUrl();
    }
}
