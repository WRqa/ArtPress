package Pages;

import Pages.Settings.Pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Login extends Pages{

    public Login(WebDriver driver){
        super(driver);
    }

    private void clickRegisterLink(){
        driver.findElement(By.xpath("//a[@class='reg-link']")).click();
    }

    private void clickLoginButton(){
        driver.findElement(By.name("login")).click();
    }

    private void setEmail(String email){
        driver.findElement(By.id("email")).sendKeys(email);
    }

    private void setPassword(String password){
        driver.findElement(By.id("password")).sendKeys(password);
    }

    public Register openRegisterPage(){
        clickRegisterLink();
        return new Register(driver);
    }

    public PasswordRecovery openPasswordRecoveryPage() {
        driver.findElement(By.linkText("Забыли пароль?")).click();
        return new PasswordRecovery(driver);
    }

    public Profile loginAs(String email, String password){
        setEmail(email);
        setPassword(password);
        clickLoginButton();
        return new Profile(driver);
    }
}
