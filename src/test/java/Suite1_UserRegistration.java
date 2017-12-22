import Pages.Basket;
import Pages.MainPage;
import Pages.PasswordRecovery;
import Pages.ProductOptions.PostcardsStep1;
import Pages.ProductOptions.PostcardsStep2;
import Pages.Profile;
import Pages.Settings.ReadMail;
import Pages.Settings.SqlServer;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.SQLException;

public class Suite1_UserRegistration extends BasicData{

    @Test
    public void Test1_UserRegistration(){
        new MainPage(driver)
                .openLoginPage()
                .openRegisterPage()
                .userRegistration(
                        data.getProperty("firstName"),
                        data.getProperty("lastName"),
                        data.getProperty("investor1qa"),
                        "test5555",
                        "test5555");

        new ReadMail(driver)
                .openRegisterMessageAndGotoLink(
                        data.getProperty("investor1qa"),
                        data.getProperty("gmailPassword"));
    }

    @Test
    public void Test2_PasswordRecovery() throws InterruptedException {
        new MainPage(driver)
                .openLoginPage()
                .openPasswordRecoveryPage()
                .passwordRecovery(data.getProperty("investor1qa"));

        Assert.assertEquals(new PasswordRecovery(driver).getConfirmationText(), "На вашу почту отправлена ссылка для восстановления пароля!");

        Thread.sleep(3000);

        new ReadMail(driver)
                .openRecoveryMessageAndGotoLink(data.getProperty("investor1qa"), data.getProperty("gmailPassword"));

        new PasswordRecovery(driver)
                .setNewPasswordData(
                        data.getProperty("investor1qa"),
                        data.getProperty("password"),
                        data.getProperty("password"));

        new MainPage(driver)
                .openLoginPage()
                .loginAs(data.getProperty("investor1qa"), data.getProperty("password"));

        Assert.assertEquals(new Profile(driver).getURL(), "https://new.one2print.com.ua/profile");
    }

    @Test
    public void Test3_Ordering(){
        new MainPage(driver)
                .openProductsPage()
                .selectPostcards()
                .selectTypeOfProduct(2)
                .selectMaterial(2)
                .selectQuantityOfSide(2)
                .selectTypeOfCoverage(2);

        Assert.assertEquals(
                new PostcardsStep1(driver).getPrice(3, 1),
                new PostcardsStep1(driver).checkFormulaWithoutDiscount( 50, 3) + " грн");
    }

    @Test
    public void Test4_OrderingWithDiscount(){
        new MainPage(driver)
                .openLoginPage()
                .loginAs(data.getProperty("investor1qa"), data.getProperty("password"))
                .openProductsPage()
                .selectPostcards()
                .selectTypeOfProduct(2)
                .selectMaterial(2)
                .selectQuantityOfSide(2)
                .selectTypeOfCoverage(2);

        Assert.assertEquals(
                new PostcardsStep1(driver).getPrice(3, 1),
                new PostcardsStep1(driver).checkFormulaWithDiscount( 50, 3) + " грн");

        new PostcardsStep1(driver)
                .selectPriceFromTable(3, 1);

        Assert.assertEquals(new PostcardsStep1(driver).getTitleOfProduct(), new PostcardsStep2(driver).getTitleOfProduct());
        Assert.assertEquals(new PostcardsStep1(driver).getTitleOfMaterial(), new PostcardsStep2(driver).getTitleOfMaterial());
        Assert.assertEquals(new PostcardsStep1(driver).getTitleOfSizeQuantity(), new PostcardsStep2(driver).getTitleOfSizeQuantity());
        Assert.assertEquals(new PostcardsStep1(driver).getTitleOfCoverage(), new PostcardsStep2(driver).getTitleOfCoverage());
        Assert.assertEquals(new PostcardsStep1(driver).getCirculationValue(), new PostcardsStep2(driver).getCirculationValue());
        Assert.assertEquals(new PostcardsStep1(driver).getTermValue(), new PostcardsStep2(driver).getTermValue());
        Assert.assertEquals(new PostcardsStep1(driver).getPriceValue(), new PostcardsStep2(driver).getPrice());
    }

    @Test
    public void Test5_Upload(){
        int i = 0;

        new MainPage(driver)
                .openLoginPage()
                .loginAs("art@yopmail.com", "qwerty");

        while (i < 23){
            new MainPage(driver)
                    .openProductsPage()
                    .selectPostcards()
                    .selectTypeOfProduct(1)
                    .selectMaterial(1)
                    .selectQuantityOfSide(1)
                    .selectTypeOfCoverage(1)
                    .selectPriceFromTable(1, 1)
                    .uploadFileAndCheckIt(data.getProperty("postcard"));

            Assert.assertEquals(new PostcardsStep2(driver).getSizeStatus(), true);
            Assert.assertEquals(new PostcardsStep2(driver).getFormatStatus(), true);
            Assert.assertEquals(new PostcardsStep2(driver).getColorStatus(), true);
            Assert.assertEquals(new PostcardsStep2(driver).getLayoutSizeStatus(), true);
            Assert.assertEquals(new PostcardsStep2(driver).getResolutionStatus(), true);

            new PostcardsStep2(driver)
                    .approveLayout();

            Assert.assertEquals(new PostcardsStep2(driver).isAddToBasketButtonDisplayed(), true);

            new PostcardsStep2(driver)
                    .addOrderToBasket();

            i++;
        }

        driver.get("https://new.one2print.com.ua/cart/");
        new Basket(driver)
                .confirmDelivery()
                .confirmOrder();

        driver.findElement(By.xpath("//input[@name='btn_text']")).click();
        driver.findElement(By.cssSelector("#Conteiner > div:nth-child(1) > div > div.menu > div > div > div:nth-child(2) > div.svg__block > svg")).click();
        driver.findElement(By.xpath("//*[@id=\"Conteiner\"]/div[2]/div/div[2]/div/div")).click();


//        Assert.assertEquals(new Basket(driver).getPrice(), new PostcardsStep1(driver).getPriceValue());
    }


    @Test
    public void Test6_base() throws SQLException {
        SqlServer sql = new SqlServer();
        sql.connect();
    }
}
