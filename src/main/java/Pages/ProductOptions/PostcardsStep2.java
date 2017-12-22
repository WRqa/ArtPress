package Pages.ProductOptions;

import Pages.Settings.Pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import Pages.Products;


public class PostcardsStep2 extends Pages{

    public PostcardsStep2(WebDriver driver) {
        super(driver);
    }

    @FindBy (xpath = "//table[@class='shop_attributes']/tbody/tr[2]/td/p") private WebElement titleOfProduct;
    @FindBy (xpath = "//table[@class='shop_attributes']/tbody/tr[3]/td/p") private WebElement titleOfMaterial;
    @FindBy (xpath = "//table[@class='shop_attributes']/tbody/tr[4]/td/p") private WebElement titleOfSizeQuantity;
    @FindBy (xpath = "//table[@class='shop_attributes']/tbody/tr[5]/td/p") private WebElement titleOfCoverage;
    @FindBy (xpath = "//table[@class='shop_attributes']/tbody/tr[6]/td/p") private WebElement circulationValue;
    @FindBy (xpath = "//table[@class='shop_attributes']/tbody/tr[7]/td/p") private WebElement termValue;
    @FindBy (xpath = "//table[@class='shop_attributes']/tbody/tr[10]/td/p") private WebElement price;

    @FindBy (xpath = "//input[@class='fileloader']") private WebElement uploadFile;
    @FindBy (xpath = "//button[@class='btn btn-primary validate-order btn-asset-co']") private WebElement approveButton;
    @FindBy (xpath = "//input[@class='btn btn-info product-order']") private WebElement addToBasketButton;

    private void clickCheckButton(){
        driver.findElement(By.xpath("//input[@value='Проверить']")).click();
    }

    private void clickApproveCheckbox(){
        driver.findElement(By.xpath("//input[@name='aprove']")).click();
    }

    private void clickApproveButtonInPopup(){
        approveButton.click();
    }

    private void clickAddToBasketButton(){addToBasketButton.submit();}

    private void clickCloseButtonInPopup(){
        driver.findElement(By.className("btn btn-default btn-cancel")).click();
    }

    public boolean isApproveCheckboxSelected(){
        return driver.findElement(By.name("aprove")).isSelected();
    }

    public boolean isAddToBasketButtonDisplayed(){
        return addToBasketButton.isDisplayed();
    }

    public String getTitleOfProduct() {
        return titleOfProduct.getText();
    }

    public String getTitleOfMaterial() {
        return titleOfMaterial.getText();
    }

    public String getTitleOfSizeQuantity(){
        return titleOfSizeQuantity.getText();
    }

    public String getTitleOfCoverage() {
        return titleOfCoverage.getText();
    }

    public String getCirculationValue() {
        return circulationValue.getText();
    }

    public String getTermValue() {
        return termValue.getText();
    }

    public String getPrice(){
        return price.getText();
    }

    private void setUploadFile(String file){
        uploadFile.sendKeys(file);
    }

    public boolean getSizeStatus(){
        return driver.findElement(By.xpath("//table[@class='shop_attributes validation-table']/tbody/tr[2]/td[3]")).getText().equals("OK");
    }

    public boolean getFormatStatus(){
        return driver.findElement(By.xpath("//table[@class='shop_attributes validation-table']/tbody/tr[3]/td[3]")).getText().equals("OK");
    }

    public boolean getColorStatus(){
        return driver.findElement(By.xpath("//table[@class='shop_attributes validation-table']/tbody/tr[4]/td[3]")).getText().equals("OK");
    }

    public boolean getLayoutSizeStatus(){
        return driver.findElement(By.xpath("//table[@class='shop_attributes validation-table']/tbody/tr[5]/td[3]")).getText().equals("OK");
    }

    public boolean getResolutionStatus(){
        return driver.findElement(By.xpath("//table[@class='shop_attributes validation-table']/tbody/tr[6]/td[3]")).getText().equals("OK");
    }


    public PostcardsStep2 uploadFileAndCheckIt(String filePath){
        setUploadFile(filePath);
        clickCheckButton();
        return this;
    }


    public PostcardsStep2 approveLayout(){
        clickApproveCheckbox();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        clickApproveButtonInPopup();
        return this;
    }

    public Products addOrderToBasket(){
        clickAddToBasketButton();
        return new Products(driver);
    }



}
