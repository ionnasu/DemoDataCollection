package Avito;

import Query.Avito;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.sql.SQLException;
import java.util.List;

public class Index extends Avito{
    public static String name;
    public static String url;
    public static String price;
    public static String publish_date;
    public static String text;
    public static String phone;
    public static String type_notice;
    public static String name_notice;

    public static void GetResult() throws SQLException {
        Avito.GetRegions();
        int i = set_get.getCount();
        Index.parsing();
    }

    private static void parsing() throws SQLException {
        ChromeDriver driver = new ChromeDriver();
        String one_adr = "volgogradskaya_oblast";
        set_get.setRegion_id(10);

        Index.Setter();
        driver.get("https://www.avito.ru/"+one_adr+"/velosipedy?sgtd=8&user=2");
//        driver.get("https://www.avito.ru/"+set_get.getName()+"?user=2&q=велосипед");
        int i = 2;
        int pages = driver.findElements(By.cssSelector("div.pagination-pages > a")).size();
        while (i < pages){
            List<WebElement> avito_elem_before = driver.findElements(By.cssSelector("div.js-catalog_before-ads > div.item_table "));
            for (WebElement eachitem_before : avito_elem_before) {
               Index.Insert(eachitem_before);
            }
            List<WebElement> avito_elem_after = driver.findElements(By.cssSelector("div.js-catalog_after-ads > div.item_table"));
            for (WebElement eachitem_after : avito_elem_after) {
                Index.Insert(eachitem_after);
           }
            driver.get("https://www.avito.ru/"+one_adr+"/velosipedy?p="+i+"&sgtd=8&user=2");
            i++;
        }
        driver.close();
    }

    private static void Setter(){
        ResultModel.setTag_id(1);
//        ResultModel.setTag_id(set_get.getId());
        ResultModel.setRegion_id(set_get.getRegion_id());
    }
    private static void Insert(WebElement eachitem_before) throws SQLException {
//        name = eachitem_before.findElement(By.cssSelector("h3.title.item-description-title > a")).getText();
        name = eachitem_before.findElement(By.cssSelector("h3.title > a.item-description-title-link")).getAttribute("title");
        url = eachitem_before.findElement(By.cssSelector("h3.title.item-description-title > a")).getAttribute("href");
        price = eachitem_before.findElement(By.cssSelector("div.description.item_table-description > div.item_table-header > div.about")).getText();
        publish_date = eachitem_before.findElement(By.cssSelector("div.description.item_table-description > div.data > div.js-item-date")).getText();
        if(eachitem_before.findElements(By.cssSelector("div.description.item_table-description > a.js-item_table-extended-description")).size() > 0){
            text = eachitem_before.findElement(By.cssSelector("div.description.item_table-description > a.js-item_table-extended-description")).getText();
        }
//        System.out.println(name));
        ResultModel.setName_notice(name.replace("'",""));
        ResultModel.setPrice(price);
        ResultModel.setPublish_date(publish_date);
        ResultModel.setUrl(url);
//        ResultModel.setText(text);

        System.out.println(name);
        Avito.InsertResults();
    }

    public static List<String> GetAll() throws SQLException {
        return Avito.GetAll();
    }


    public static void GetAvitoRegions() throws SQLException {
        ChromeDriver driver_2 = new ChromeDriver();
        driver_2.get("https://www.avito.ru");
        driver_2.findElement(By.cssSelector("span.catalog-counts__switch.pseudo-link")).click();
        List<WebElement> ul = driver_2.findElements(By.cssSelector("div.catalog-counts__more > ul"));
        System.out.println(ul.size());
        for (WebElement eachul : ul){
            List<WebElement> li = eachul.findElements(By.cssSelector("li"));
            for (WebElement eachli : li ){
                String name = eachli.findElement(By.cssSelector("a.js-catalog-counts__link")).getAttribute("href");
                String[] split = name.split("o.ru/");
                System.out.println(split[1]);
                Avito.InsertRegions(split[1]);
            }
        }
        driver_2.close();
    }




}
