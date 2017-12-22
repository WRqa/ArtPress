package Pages;

import Pages.ProductOptions.*;
import Pages.Settings.Pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Products extends Pages{


    public Products(WebDriver driver) {
        super(driver);
    }

    private void clickBasketIcon(){
        driver.findElement(By.linkText("Просмотр вашей корзины")).click();
    }

    private void clickSubmitOrder(){
        driver.findElement(By.xpath("/ul[@class='menu']/li/ul/li/div/a/span")).click();
    }

    public PostcardsStep1 selectPostcards(){
        driver.findElement(By.linkText("Открытки — АВТОТЕСТ")).click();
        return new PostcardsStep1(driver);
    }

    public Basket openBasket(){
        clickBasketIcon();
        clickSubmitOrder();
        return new Basket(driver);
    }
}
