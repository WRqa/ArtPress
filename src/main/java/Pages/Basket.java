package Pages;

import Pages.Settings.Pages;
import com.sun.tools.doclets.formats.html.resources.standard;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Basket extends Pages{

    public Basket(WebDriver driver) {
        super(driver);
    }

    @FindBy(className = "allprices") private WebElement price;

    private void clickSubmitButton(){
        driver.findElement(By.xpath("//*[@id=\"13225\"]/div[1]/form/div[1]/div[2]/div/div/a/span")).click();
    }

    private void clickDeliveryButton(){
        driver.findElement(By.xpath("//td[@class='product-quantity']/div/button")).click();
    }

    private void clickUseForAllItemCheckbox(){
        driver.findElement(By.xpath("//td[@class='product-quantity']/form/input[2]")).click();
    }

    private void clickSaveDeliveryButton(){
        driver.findElement(By.xpath("//div[@id='shipping']/div/div/div[3]/a[1]/span")).click();
    }

    private void clickOkButton(){
        driver.switchTo().alert().accept();
    }

    public String getPrice(){
        return price.getText();
    }

    public Basket confirmDelivery(){
        clickDeliveryButton();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        clickSaveDeliveryButton();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        clickUseForAllItemCheckbox();
        clickOkButton();
        return this;
    }

    public Basket confirmOrder(){
        clickSubmitButton();
        return this;
    }
}
