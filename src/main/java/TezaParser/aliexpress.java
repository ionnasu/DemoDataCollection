package TezaParser;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import Query.TezaDB;


public class aliexpress extends general
{
  private static String shiping;
  private static String firstPart;
  private static String name;
  private static String price;
  private static String url;
  public static void GetAllItems(String name_plaforme , int id) throws IOException, SQLException, InterruptedException {
    ChromeDriver driver = new ChromeDriver();
//    String url = "https://www.aliexpress.com/category/200004720/office-electronics.html?spm=2114.search0103.2.1.4f8a7637Zhci1N&site=glo";
    String url = TezaDB.GetUrlFromTable(id);

    Pattern p = Pattern.compile(".*/\\s*(.*)");
    Matcher m = p.matcher(url);
    if (m.find()){
      int i = m.group(1).indexOf('.');
      firstPart = m.group(1).substring(0, i);
    }
    String[] paginate_url = url.split(firstPart);
//    System.out.println(m.group(0));
//    System.out.println(m.group(1));
//    System.out.println(firstPart);

    driver.get(url);


////
    List<WebElement> pages = driver.findElements(By.cssSelector(".ui-pagination-navi > a"));
    System.out.println(pages.size());
    int i = 2;
    while (i <= pages.size()){
      List<WebElement> aliexpreeListItems = driver.findElements(By.cssSelector("ul#list-items > li"));
      for (WebElement eachitem : aliexpreeListItems) {
        String img_name_for_db = RandomStringUtils.randomAlphanumeric(20) + ".png";
        String name = eachitem.findElement(By.cssSelector(".right-block > .right-block-wrap > .detail > h3 > a > span")).getText();
        String price = eachitem.findElement(By.cssSelector(".right-block > .right-block-wrap  > .info > .price > .value")).getText();
        if(eachitem.findElements(By.cssSelector(".right-block > .right-block-wrap > div.info > strong.free-s")).size() > 0){
          shiping = eachitem.findElement(By.cssSelector(".right-block > .right-block-wrap > div.info > strong.free-s")).getText();
        }else{
          shiping = eachitem.findElement(By.cssSelector(".right-block > .right-block-wrap > div.info > dl.pnl-shipping")).getText();
        }
//        String shiping1 = eachitem.findElement(By.cssSelector(".right-block > .right-block-wrap > div:nth-child(2) > dl:nth-child(2)")).getText();

        String url_item = eachitem.findElement(By.cssSelector(".img-container > .img > a")).getAttribute("href");

        WebElement element = (new WebDriverWait(driver,10)).until(ExpectedConditions.elementToBeClickable(eachitem.findElement(By.cssSelector(".img-container > .img > a > img"))));

        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("window.scrollBy(0,250)", "");

        ScreenShot(element, img_name_for_db, "aliexpress");

        System.out.println(name);
//        System.out.println(price);
//        System.out.println(shiping);
//        System.out.println(url_item);
//        System.out.println(img_name_for_db);
        TezaDB.InsertIntoAliexpress(1,1,"", name,url_item,img_name_for_db," ",price,"","",shiping);
      }
//      driver.get("https://www.aliexpress.com/category/200004720/office-electronics/"+i+".html?spm=2114.search0103.2.1.4f8a7637Zhci1N&site=glo");
      driver.get(paginate_url[0]+firstPart+"/"+i+paginate_url[1]);
      i++;
    }
    driver.close();
    System.out.println("Task was executed!!!");
  }
  public static void FindItem(String tag, int compare_id) throws IOException, SQLException {
      ChromeDriver driver = new ChromeDriver();
      driver.get("https://www.aliexpress.com/premium/category/200003482.html?g=y&d=n&blanktest=0&spm=2114.11010108.101.3.4297649bqEyUrw&tc=ppc&CatId=200003482&catName=dresses&isViewCP=y");
      driver.findElement(By.cssSelector("#search-key")).sendKeys(tag);
      driver.findElement(By.cssSelector("input.search-button")).click();
      driver.findElement(By.cssSelector("#view-thum")).click();

      List<WebElement> ali_elements = driver.findElements(By.cssSelector("#hs-below-list-items > li"));
    for (WebElement eachitem : ali_elements) {
      String img_name_for_db = RandomStringUtils.randomAlphanumeric(20) + ".png";
      if(eachitem.findElements(By.cssSelector("div.item > div.info > h3 > a.history-item.product")).size() > 0){
        name = eachitem.findElement(By.cssSelector("div.item > div.info > h3 > a.history-item.product")).getAttribute("title");
        name = name.replace("'","");
      }
      else {
        name = "no_name";
      }
      if(eachitem.findElements(By.cssSelector("div.item > div.info > h3 > a.history-item.product")).size() > 0){
        url = eachitem.findElement(By.cssSelector("div.item > div.info > h3 > a.history-item.product")).getAttribute("href");
      }
      else {
        url = "no_url";
      }
      if(eachitem.findElements(By.cssSelector("div.item > div.info > span.price.price-m > span.value")).size() > 0){
        price = eachitem.findElement(By.cssSelector("div.item > div.info > span.price.price-m > span.value")).getText();
        price = price.substring(4);
      }
      else {
        price = "no_price";
      }

      if (eachitem.findElements(By.cssSelector("div.item > div.img.img-border > div.pic > a.picRind.history-item > img.picCore.pic-Core-v")).size() > 0) {
        WebElement element = (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(eachitem.findElement(By.cssSelector(" div.item > div.img.img-border > div.pic > a.picRind.history-item > img.picCore.pic-Core-v"))));
        ScreenShot(element, img_name_for_db, "aliexpress");
      }
      else if (eachitem.findElements(By.cssSelector("div.item > div.img.img-border > div.pic > a.picRind.history-item > img.picCore")).size() > 0) {
        WebElement element = (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(eachitem.findElement(By.cssSelector(" div.item > div.img.img-border > div.pic > a.picRind.history-item > img.picCore"))));
        ScreenShot(element, img_name_for_db, "aliexpress");
      }
      else {
        img_name_for_db = "no_img";
      }
      JavascriptExecutor jse = (JavascriptExecutor)driver;
      jse.executeScript("window.scrollBy(0,250)", "");

          System.out.println(name);
          System.out.println(price);
      TezaDB.InsertIntoAliexpress(compare_id,1,tag, name, url ,img_name_for_db," ",price,"","",shiping);
    }

    driver.close();
    System.out.println("Task was executed!!!");

  }
}
