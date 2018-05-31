package Service;

import Query.TezaDB;
import TezaParser.aliexpress;
import TezaParser.amazon;
import TezaParser.meteo;
import TezaParser.setter;
import Avito.Index;
import Query.Avito;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;

import java.awt.*;
import Avito.ResultModel;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import Avito.RegionsModel;


public class Test {

    private void GetFunction(String for_parsing, int id) throws IOException, SQLException, InterruptedException {
        switch (for_parsing){
            case "www.aliexpress.com":
                aliexpress.GetAllItems(for_parsing,id);
                break;
            case "www.meteo.md":
                meteo.GetAll();
                break;
            case "www.amazon.com":
                amazon.GetFromAmazon(id);
                break;
            case "www.gearbest.com":
                break;
            default:
                System.out.println("Empty");

        }
    }

    private void GetCompareFunction(String for_parsing, int compare_id) throws IOException, SQLException {
        String[] platforme = for_parsing.split(",");

        if(ArrayUtils.contains(platforme,"www.amazon.com")){
           amazon.FindItem(setter.getTag(),compare_id);
        }
        if(ArrayUtils.contains(platforme,"www.aliexpress.com")){
            aliexpress.FindItem(setter.getTag(),compare_id);
        }
    }

    @Before
    public void Setup() {
        System.setProperty("webdriver.chrome.driver", "D:\\testare\\new\\chromedriver.exe");
    }

    @org.junit.Test
    public void function() throws SQLException, IOException, InterruptedException {
        while (true){
            if(TezaDB.CheckTasks() > 0){
                TezaDB.GetOrders();
                TezaDB.StartRuning(setter.getId_send());
                GetFunction(setter.getName(),setter.getId_send());
            }else {
                Thread.sleep(5000);
                System.out.println("Task for collection wait!!!");
            }
            if(TezaDB.CheckCompare() > 0){
                TezaDB.GetTaskCompare();
                TezaDB.RuningUpdateCompare(setter.getCompare());
                String platforme = setter.getName();
                GetCompareFunction(platforme,setter.getCompare());
                TezaDB.CompletCompare(setter.getCompare());
            }
            else{
                Thread.sleep(5000);
                System.out.println("Task for compare wait!!!");
            }
        }
    }
//    @org.junit.Test
//    public void te() throws IOException {
//        Runtime runtime = Runtime.getRuntime();
////        Process p = Runtime.getRuntime().exec("C:/Users/nasui/Desktop/run.sh");
//        Process p1 = runtime.exec("cmd /c start C:\\Users\\nasui\\Desktop\\RunFace.bat 2&1");
//
//    }
    /*@org.junit.Test
    public void Proxy(){
        String sProxyServerUrl = "206.189.33.170";
        int iProxyServerPort = 8080;
        String sHttpProxy = sProxyServerUrl+":"+iProxyServerPort;
        Proxy proxy = new Proxy();
        proxy.setHttpProxy(sHttpProxy).setFtpProxy(sHttpProxy).setSslProxy(sHttpProxy).setSslProxy(sHttpProxy);
        DesiredCapabilities cap = DesiredCapabilities.chrome();
        cap.setCapability(CapabilityType.PROXY, proxy);
        WebDriver driver = new ChromeDriver(cap);
        driver.get("https://whatismyipaddress.com/");


    }*/

    @org.junit.Test
    public void Avito() throws SQLException {
        Index.GetResult();
    }

    @org.junit.Test
    public void test() throws SQLException, IOException, InterruptedException, AWTException {
        for ( String url: Index.GetAll() ) {
            String result = "";
            String shop_name = "no shop name";
            String contact_name = "no contat name";
            String address = "no address";
            String tip_notice = "";
            ChromeDriver driver = new ChromeDriver();
            String img_name_for_db = RandomStringUtils.randomAlphanumeric(20) + ".png";
//            driver.manage().window().setPosition(new Point(0, -2000));
            driver.get(url);
            int id =  Avito.GetOne("results", "where url = '"+url+"'");
            System.out.println(id);
            driver.findElement(By.cssSelector("span.item-phone-button-sub-text")).click();
            Thread.sleep(1000);
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_ESCAPE);
            robot.keyRelease(KeyEvent.VK_ESCAPE);
            Thread.sleep(1000);
            String im = driver.findElement(By.cssSelector("a.button.item-phone-button.js-item-phone-button.button-origin.button-origin_full-width.button-origin_large-extra.item-phone-button_card.js-item-phone-button_card.item-phone-button_with-img > img")).getAttribute("src");
            System.out.println(im);
            if(driver.findElements(By.cssSelector("div.item-view-contacts.js-item-view-contacts > div:nth-of-type(2) > div > div:nth-of-type(1) > div:nth-of-type(1) > div > a")).size()>0){
                 shop_name = driver.findElement(By.cssSelector("div.item-view-contacts.js-item-view-contacts > div:nth-of-type(2) > div > div:nth-of-type(1) > div:nth-of-type(1) > div > a")).getText();
            }
            if(driver.findElements(By.cssSelector("div.seller-info-prop.seller-info-prop_short_margin > div.seller-info-value")).size() > 0){
                contact_name = driver.findElement(By.cssSelector("div.seller-info-prop.seller-info-prop_short_margin > div.seller-info-value")).getText();
            }
            if(driver.findElement(By.cssSelector("div.seller-info-prop > div.seller-info-label")).getText().equals("Адрес")){
                 address = driver.findElement(By.cssSelector("div.seller-info-prop > div.seller-info-value")).getText();
            }else {
                address = driver.findElement(By.cssSelector("div.item-map-location")).getText();
            }

            tip_notice = driver.findElement(By.cssSelector("div.title-info-metadata > div:nth-of-type(1)")).getText();

            driver.close();
            ChromeDriver driver1 = new ChromeDriver();
//            driver1.manage().window().setPosition(new Point(0, -2000));
            driver1.get(im);
            File scrFile = ((TakesScreenshot)driver1).getScreenshotAs(OutputType.FILE);

            FileUtils.copyFile(scrFile, new File("C:\\Users\\nasui\\Desktop\\phoneNR\\"+img_name_for_db));
            File imageFile = new File("C:\\Users\\nasui\\Desktop\\phoneNR\\"+img_name_for_db);
            ITesseract instance = new Tesseract();
            try {
                result = instance.doOCR(imageFile);
                System.out.println(result);
            } catch (TesseractException e) {
                System.err.println(e.getMessage());
                System.out.println("Error while reading image");
            }
            ResultModel.setShop_name(shop_name);
            ResultModel.setAddress(address);
            ResultModel.setName_contact(contact_name);
            ResultModel.setPhone(result);
            ResultModel.setTip_notice(tip_notice);
            Avito.Update("results",id);
            driver1.close();
        }
    }
    @org.junit.Test
    public void ne() throws SQLException {
//        Index.GetAvitoRegions();
        ArrayList<RegionsModel> seccicl = Avito.getAllregions();
        for (RegionsModel model : seccicl) {
            Object rowData[] = {model.getId(), model.getName()};
            System.out.println(rowData[1]);
        }
    }
    @After
    public void CloseBrowser(){
    }
}
