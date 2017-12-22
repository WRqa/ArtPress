package Pages;

import Pages.Settings.Pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Register extends Pages{

    public Register(WebDriver driver){
        super(driver);
    }

    private void clickRegisterButton(){
        driver.findElement(By.name("register")).click();
    }

    private void setName(String name){
        driver.findElement(By.id("name")).sendKeys(name);
    }

    private void setLastName(String lastName){
        driver.findElement(By.id("lastname")).sendKeys(lastName);
    }

    private void setEmail(String email){
        driver.findElement(By.id("email")).sendKeys(email);
    }

    private void setPassword(String password){
        driver.findElement(By.id("password")).sendKeys(password);
    }

    private void setConfirmPassword(String confirmPassword){
        driver.findElement(By.id("repassword")).sendKeys(confirmPassword);
    }

    public void userRegistration(String name, String lastName, String email, String password, String confirmPassword){
        setName(name);
        setLastName(lastName);
        setEmail(email);
        setPassword(password);
        setConfirmPassword(confirmPassword);
        clickRegisterButton();
    }
}
