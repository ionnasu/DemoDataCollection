package TezaParser;

import Query.TezaDB;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.sql.SQLException;
import java.util.List;

public class meteo {
/*    static void GetAll1(){
        ChromeDriver driver = new ChromeDriver();
        driver.get("http://meteo.md/index.php/ro/weather/current-forecast/1770");
        List<WebElement> meteo_li = driver.findElements(By.cssSelector("li.selected-area > .forecast-list > ul > li"));
        for (WebElement eachitem : meteo_li) {
            String temp_min = eachitem.findElement(By.cssSelector("span.temp > span.min")).getText();
            String date = eachitem.findElement(By.cssSelector("span.date")).getText();
            String type_forecast = eachitem.findElement(By.cssSelector("span.icon > span")).getText();
            String temp_max = eachitem.findElement(By.cssSelector("span.temp > span.max")).getText();
            String wind = eachitem.findElement(By.cssSelector("span.ws")).getText();
            System.out.println(date.replace("\n", "")+" == "+type_forecast.trim()+" == "+temp_min.replace("\n", "")+" == "+temp_max.replace("\n", "")+" == "+wind.replace("\n", ""));
        }
        driver.quit();
    }*/
    public static void GetAll() throws SQLException {
        ChromeDriver driver = new ChromeDriver();
        driver.get("http://meteo.md/index.php/ro/weather/current-forecast/1770");
        List<WebElement> meteo_list = driver.findElements(By.cssSelector("ul.forecast-area-list > li"));
        for (WebElement eachitem1 : meteo_list) {
            eachitem1.findElement(By.cssSelector("a")).click();
            String location = eachitem1.findElement(By.cssSelector("a")).getText();
            System.out.println(location);
            List<WebElement> meteo_li = driver.findElements(By.cssSelector("li.selected-area > .forecast-list > ul > li"));

            for (WebElement eachitem : meteo_li) {
                String type_forecast = eachitem.findElement(By.cssSelector("span.icon > span")).getText();
                String date = eachitem.findElement(By.cssSelector("span.date")).getText();
                String temp_min = eachitem.findElement(By.cssSelector("span.temp > span.min")).getText();
                String temp_max = eachitem.findElement(By.cssSelector("span.temp > span.max")).getText();
                String wind = eachitem.findElement(By.cssSelector("span.ws")).getText();
                TezaDB.InsertIntoMeteo(type_forecast,date,temp_min,temp_max,wind,location);
                System.out.println(date.replace("\n", "") + " == " + type_forecast.trim() + " == " + temp_min.replace("\n", "") + " == " + temp_max.replace("\n", "") + " == " + wind.replace("\n", ""));
            }
        }
     driver.quit();
    }
}
