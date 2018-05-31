package TezaParser;

import Query.TezaDB;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

import static TezaParser.general.ScreenShot;

public class amazon extends TezaDB{
    private static String price;
    private static String name;
    private static String url_item;
    private static String shipping;
    private static String detail = "";


    public static void GetFromAmazon(int id) throws IOException, SQLException {
        ChromeDriver driver = new ChromeDriver();
        String url = GetUrlFromTable(id);
        setter.setP_id(id);
        driver.get(url);
//        String[] paginate_url = url.split("&ie=UTF8");
//        String pages = driver.findElement(By.cssSelector(".pagnDisabled")).getText();
//        System.out.println(Integer.parseInt(pages));
        int i = 2;
//        while (i <= 5) {
            List<WebElement> amazon_elements = driver.findElements(By.cssSelector(".s-result-list > li"));
            for (WebElement eachitem : amazon_elements) {
                String img_name_for_db = RandomStringUtils.randomAlphanumeric(20) + ".png";

                if (eachitem.findElements(By.cssSelector("div.s-item-container > div:nth-of-type(3) > div:first-child > a.a-link-normal.s-access-detail-page.s-color-twister-title-link.a-text-normal")).size() > 0) {
                    name = eachitem.findElement(By.cssSelector("div.s-item-container > div:nth-of-type(3) > div:first-child > a.a-link-normal.s-access-detail-page.s-color-twister-title-link.a-text-normal")).getAttribute("title");
                    url_item = eachitem.findElement(By.cssSelector("div.s-item-container > div:nth-of-type(3) > div:first-child > a.a-link-normal.s-access-detail-page.s-color-twister-title-link.a-text-normal")).getAttribute("href");
                } else {
                    name = "no_name";
                    url_item = "no_item";
                }
                if (eachitem.findElements(By.cssSelector("span.sx-price.sx-price-large")).size() > 0) {
                    price = eachitem.findElement(By.cssSelector("span.sx-price.sx-price-large")).getText();
                    price = price.substring(2);
                    price = price.replace(" ",".");
                }
                else if(eachitem.findElements(By.cssSelector("span.a-size-base.a-color-base")).size() > 0){
                    price = eachitem.findElement(By.cssSelector("span.a-size-base.a-color-base")).getText();
                    price = price.substring(1);
                }
                else {
                    price = "no_price";
                }
                if (eachitem.findElements(By.cssSelector("div.s-item-container > div:nth-of-type(4) > div.a-row > span.a-size-base-plus.a-color-secondary")).size() > 0) {
                    shipping = eachitem.findElement(By.cssSelector("div.s-item-container > div:nth-of-type(4) > div.a-row > span.a-size-base-plus.a-color-secondary")).getText();
                } else if (eachitem.findElements(By.cssSelector("div.s-item-container > div:nth-of-type(5) > div.a-row > span.a-size-small.a-color-secondary")).size() > 0) {
                    shipping = eachitem.findElement(By.cssSelector("div.s-item-container > div:nth-of-type(5) > div.a-row > span.a-size-small.a-color-secondary")).getText();
                } else {
                    shipping = "no_shipping";
                }
                if (eachitem.findElements(By.cssSelector("a.a-link-normal.a-text-normal > div.s-card > img.s-access-image.cfMarker")).size() > 0) {
                    WebElement element = (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(eachitem.findElement(By.cssSelector("a.a-link-normal.a-text-normal > div.s-card > img.s-access-image.cfMarker"))));
                    ScreenShot(element, img_name_for_db, "amazon");
                }
                else if (eachitem.findElements(By.cssSelector("a.a-link-normal.a-text-normal > img.s-access-image.cfMarker")).size() > 0) {
                    WebElement element = (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(eachitem.findElement(By.cssSelector("a.a-link-normal.a-text-normal > img.s-access-image.cfMarker"))));
                    ScreenShot(element, img_name_for_db, "amazon");
                }
                else {
                    img_name_for_db = "no_images";
                }
                System.out.println(name);
                InsertIntoAmazon(setter.getP_id(),1,id,"", name, url_item, img_name_for_db, "", price, "", "", shipping);
            }
            driver.close();
            System.out.println("Task was executed!!!");


//            driver.get(paginate_url[0] + "&page=" + i + "&ie=UTF8" + paginate_url[1]);
//            i++;
//        }
    }

    public static void FindItem(String tag, int compare_id) throws IOException, SQLException {
        ChromeDriver driver = new ChromeDriver();
        driver.get("https://www.amazon.com");
        driver.findElement(By.cssSelector("#twotabsearchtextbox")).sendKeys(tag);
        driver.findElement(By.cssSelector("div.nav-search-submit.nav-sprite > input.nav-input")).click();
        List<WebElement> amazon_elements = driver.findElements(By.cssSelector(".s-result-list > li"));
        for (WebElement eachitem : amazon_elements) {
            String img_name_for_db = RandomStringUtils.randomAlphanumeric(20) + ".png";
            String name = eachitem.findElement(By.cssSelector("a.a-link-normal > h2")).getText();
            url_item = eachitem.findElement(By.cssSelector("a.a-link-normal")).getAttribute("href");
            if (eachitem.findElements(By.cssSelector("span.sx-price.sx-price-large")).size() > 0) {
                price = eachitem.findElement(By.cssSelector("span.sx-price.sx-price-large")).getText();
                price = price.substring(2);
                price = price.replace(" ",".");
            }
            else if(eachitem.findElements(By.cssSelector("span.a-size-base.a-color-base")).size() > 0){
                price = eachitem.findElement(By.cssSelector("span.a-size-base.a-color-base")).getText();
                price = price.substring(1);
            }
            System.out.println(name);
            System.out.println(price);

            int list = eachitem.findElements(By.cssSelector("dl.a-definition-list.a-vertical > li")).size();
            if(list > 0){
                List<WebElement> features = eachitem.findElements(By.cssSelector("dl.a-definition-list.a-vertical > li"));
                for (WebElement each_feature : features) {
                    String item = each_feature.findElement(By.cssSelector("span.a-list-item > span:nth-child(1)")).getText();
                    String val_bold = each_feature.findElement(By.cssSelector("span.a-list-item > span.a-text-bold")).getText();
                    detail = detail.concat("<p>"+item +"<strong>"+val_bold+"</strong></p>");
                }
                System.out.println(detail);
            }
            if (eachitem.findElements(By.cssSelector("a.a-link-normal.a-text-normal > div.s-card > img.s-access-image.cfMarker")).size() > 0) {
                WebElement element = (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(eachitem.findElement(By.cssSelector("a.a-link-normal.a-text-normal > div.s-card > img.s-access-image.cfMarker"))));
                ScreenShot(element, img_name_for_db, "amazon");
            }
            else if (eachitem.findElements(By.cssSelector("a.a-link-normal.a-text-normal > img.s-access-image.cfMarker")).size() > 0) {
                WebElement element = (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(eachitem.findElement(By.cssSelector("a.a-link-normal.a-text-normal > img.s-access-image.cfMarker"))));
                ScreenShot(element, img_name_for_db, "amazon");
            }
            else {
                img_name_for_db = "no_img";
            }
            InsertIntoAmazon(setter.getP_id(),compare_id,1,tag, name, url_item, img_name_for_db, "", price, "", detail, "no_ship");
            detail = " ";
        }
        driver.close();
        System.out.println("Task was executed!!!");
    }
}
