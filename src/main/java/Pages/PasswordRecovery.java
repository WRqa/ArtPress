package Pages;

import Pages.Settings.Pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PasswordRecovery extends Pages{

    public PasswordRecovery(WebDriver driver) {
        super(driver);
    }

    private void setEmail(String email){
        driver.findElement(By.id("email")).sendKeys(email);
    }

    private void setNewPassword(String newPassword) {
        driver.findElement(By.id("password")).sendKeys(newPassword);
    }

    private void setConfirmNewPassword(String confirmNewPassword){
        driver.findElement(By.id("password-confirm")).sendKeys(confirmNewPassword);
    }

    private void clickRestoreButton(){
        driver.findElement(By.name("reset")).click();
    }

    private void clickSaveButton(){
        driver.findElement(By.xpath("//button[@type='submit']")).submit();
    }

    public String getConfirmationText(){
        return driver.findElement(By.xpath("//div[@class='modal-body']/p")).getText();
    }

    public void passwordRecovery(String email) {
        setEmail(email);
        clickRestoreButton();
    }

    public void setNewPasswordData(String email, String newPassword, String confirmNewPassword){
        setEmail(email);
        setNewPassword(newPassword);
        setConfirmNewPassword(confirmNewPassword);
        clickSaveButton();

    }


}
