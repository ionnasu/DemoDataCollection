/*
package TezaParser;

import Query.TezaDB;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class parse extends aliexpress{

    private void GetFunction(String for_parsing) throws IOException {
        switch (for_parsing){
            case "aliexpress":
               GetAllItems(for_parsing);
                break;
            case "meteo":
               meteo.GetAll();
                break;
            default:
                System.out.println("Empty");

        }
    }

    @Before
    public void config(){
        System.setProperty("webdriver.chrome.driver", "D:\\testare\\new\\chromedriver.exe");
    }

    @Test
    public void RunMiniService() throws SQLException, InterruptedException, IOException {

        while (true){

            if(TezaDB.CheckTasks() > 0){
                TezaDB.GetOrders();
                System.out.println(setter.getId_send());
                GetFunction(setter.getName());
                System.out.println("true");
                break;

            }else {
                Thread.sleep(5000);
                System.out.println("Task in asteptare");

            }
        }
    }

    @Test
    public void GoogleSimple(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.google.com");
        driver.findElement(By.cssSelector("#lst-ib")).sendKeys("Selenium web scraping");
        driver.findElement(By.cssSelector(".jsb > center:nth-child(1) > input:nth-child(1)")).click();
        List<WebElement> myList = driver.findElements(By.cssSelector(".srg > .g"));
        for (WebElement aMyList : myList) {
            String name = aMyList.findElement(By.cssSelector("h3.r > a")).getText();
            String href = aMyList.findElement(By.cssSelector("h3.r > a")).getAttribute("href");
            System.out.println(name);
            System.out.println(href);
        }
    }
//    @After
//    public void QuitFromBrowser(){
//        driver.quit();
//    }
}
*/
