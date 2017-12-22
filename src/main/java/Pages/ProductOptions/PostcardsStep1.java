package Pages.ProductOptions;

import Pages.Settings.Pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;

public class PostcardsStep1 extends Pages{

    public PostcardsStep1(WebDriver driver) {
        super(driver);
    }

    private static String titleOfProduct;
    private static String titleOfMaterial;
    private static String titleOfSizeQuantity;
    private static String titleOfCoverage;
    private static String circulationValue;
    private static String termValue;
    private static String priceValue;

    private static int quantityOfOperations = 0;
    private static int type = 0;
    private static double material = 0;
    private static int side = 0;
    private static double coverage = 0;
    private static double quantity = 0;
    private static double day = 0;
    private double coefficientOfOperation = Double.parseDouble(data.getProperty("coefficientOfOperations"));

    public String getTitleOfProduct() {
        return titleOfProduct;
    }

    public String getTitleOfMaterial() {
        return titleOfMaterial;
    }

    public String getTitleOfSizeQuantity(){
        return titleOfSizeQuantity;
    }

    public String getTitleOfCoverage() {
        return titleOfCoverage;
    }

    public String getCirculationValue() {
        return circulationValue + " шт";
    }

    public String getTermValue() {
        return termValue;
    }

    public String getPriceValue() {
        return priceValue;
    }

    public PostcardsStep1 selectTypeOfProduct(int position){
        WebElement element = driver.findElement(By.xpath("//div[@class='owl-wrapper']/div[1]/div[" + position + "]/li/figure/a"));

        element.click();
        titleOfProduct = element.getAttribute("data-format");

        switch (position){
            case 1:
                type = Integer.parseInt(data.getProperty("Type1"));
                break;
            case 2:
                type = Integer.parseInt(data.getProperty("Type2"));
                quantityOfOperations = 1;
                break;
            case 3:
                type = Integer.parseInt(data.getProperty("Type3"));
                quantityOfOperations = 2;
        }

        return this;
    }

    public PostcardsStep1 selectMaterial(int position){
        WebElement element = driver.findElement(By.xpath("//form[@class='product-2step']/div[2]/div/div/div/div[" + position + "]/input"));

        element.click();
        titleOfMaterial = element.getAttribute("value");

        switch (position){
            case 1:
                material = Double.parseDouble(data.getProperty("Material1"));
                break;
            case 2:
                material = Double.parseDouble(data.getProperty("Material2"));
                break;
        }

        return this;
    }

    public PostcardsStep1 selectQuantityOfSide(int quantity){
        WebElement element = driver.findElement(By.xpath("//form[@class='product-2step']/div[3]/div/div/div/div[" + quantity + "]/input"));

        element.click();
        titleOfSizeQuantity = element.getAttribute("value");

        if (quantity == 1 || quantity == 2)
            side = Integer.parseInt(data.getProperty("Side"));

        return this;
    }

    public PostcardsStep1 selectTypeOfCoverage(int position){
        WebElement element = driver.findElement(By.xpath("//form[@class='product-2step']/div[4]/div/div/div/div/div[1]/div[" + position + "]/input"));

        element.click();
        titleOfCoverage = element.getAttribute("value");

        switch (position){
            case 1:
                coverage = Double.parseDouble(data.getProperty("Coverage1"));
                break;
            case 2:
                coverage = Double.parseDouble(data.getProperty("Coverage2"));
                break;
        }
        return this;
    }

    public String getPrice(int quantityOfDay, int position){
        String price = "";
        int quantityOfPointsOnList = driver.findElements(By.xpath("//table[@class='sf-table standard_bordered tb-price']/tbody/tr/td[2]")).size();

        ArrayList<String> list1Day = new ArrayList<String>();
        ArrayList<String> list1Circulation = new ArrayList<String>();
        ArrayList<String> list1Term = new ArrayList<String>();
        for (int i = 2; i <= quantityOfPointsOnList; i++){
            WebElement column1Day = driver.findElement(By.xpath("//table[@class='sf-table standard_bordered tb-price']/tbody/tr[" + i + "]/td[2]"));

            list1Day.add(column1Day.getText());
            list1Circulation.add(column1Day.getAttribute("data-circulationvalue"));
            list1Term.add(column1Day.getAttribute("data-termvalue"));
        }

        ArrayList<String> list3Day = new ArrayList<String>();
        ArrayList<String> list3Circulation = new ArrayList<String>();
        ArrayList<String> list3Term = new ArrayList<String>();

        for (int i = 2; i <= quantityOfPointsOnList; i++){
            WebElement column3Day = driver.findElement(By.xpath("//table[@class='sf-table standard_bordered tb-price']/tbody/tr[" + i + "]/td[3]"));

            list3Day.add(column3Day.getText());
            list3Circulation.add(column3Day.getAttribute("data-circulationvalue"));
            list3Term.add(column3Day.getAttribute("data-termvalue"));
        }

        switch (quantityOfDay){
            case 1:
                price = list1Day.get(position - 1);
                circulationValue = list1Circulation.get(position - 1);
                termValue = list1Term.get(position - 1);
                break;
            case 3:
                price = list3Day.get(position - 1);
                circulationValue = list3Circulation.get(position - 1);
                termValue = list3Term.get(position - 1);
                break;
        }

        priceValue = price;
        return price;
    }

    public int checkFormulaWithoutDiscount(int quantityOfPieces, int quantityOfDays){
        double result;

        switch (quantityOfPieces){
            case 50:
                quantity = Double.parseDouble(data.getProperty("Quantity50"));
                break;
            case 10000:
                quantity = Double.parseDouble(data.getProperty("Quantity10000"));
                break;
        }

        switch (quantityOfDays){
            case 1:
                day = Double.parseDouble(data.getProperty("Day1"));
                break;
            case 3:
                day = Double.parseDouble(data.getProperty("Day3"));
                break;
        }

        if (quantityOfOperations == 0){
            result = type * material * side * coverage * quantity * day;
        } else {
            result = type * material * side * coverage * quantity * day + quantityOfOperations * coefficientOfOperation * quantityOfPieces;
        }

        return (int) Math.round(result);
    }

    public int checkFormulaWithDiscount(int quantityOfPieces, int quantityOfDays){
        double result;

        int discount = Integer.parseInt(driver.findElement(By.xpath("//p[@class='text-grey text-margin-top percent-prod']")).getAttribute("data-percent"));

        switch (quantityOfPieces){
            case 50:
                quantity = Double.parseDouble(data.getProperty("Quantity50"));
                break;
            case 10000:
                quantity = Double.parseDouble(data.getProperty("Quantity10000"));
                break;
        }

        switch (quantityOfDays){
            case 1:
                day = Double.parseDouble(data.getProperty("Day1"));
                break;
            case 3:
                day = Double.parseDouble(data.getProperty("Day3"));
                break;
        }

        if (quantityOfOperations == 0){
            result = type * material * side * coverage * quantity * day;
        } else {
            result = type * material * side * coverage * quantity * day + quantityOfOperations * coefficientOfOperation * quantityOfPieces;
        }

        result -= result * discount / 100;

        return (int) Math.round(result);
    }

    public PostcardsStep2 selectPriceFromTable(int quantityOfDay, int position) {
        WebElement price;

        switch (quantityOfDay){
            case 1:
                price = driver.findElement(By.xpath("//table[@class='sf-table standard_bordered tb-price']/tbody/tr[" + (position + 1) + "]/td[2]"));
                price.click();
                break;
            case 3:
                price = driver.findElement(By.xpath("//table[@class='sf-table standard_bordered tb-price']/tbody/tr[" + (position + 1) + "]/td[3]"));
                price.click();
                break;
        }

        return new PostcardsStep2(driver);
    }
}
