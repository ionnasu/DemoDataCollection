package TezaParser;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class general {

     static void ScreenShot(WebElement driver, String img_name, String plaforme) throws IOException {
         String img = driver.getAttribute("src");
         URL imageURL = new URL(img);
         BufferedImage saveImage = ImageIO.read(imageURL);
         File screenshotLocation = new File("C:\\Users\\nasui\\Desktop\\License\\public\\upfiles\\"+plaforme+"\\"+img_name);
//         File screenshotLocation = new File("C:\\Users\\nasui\\Desktop\\License\\public\\upfiles\\"+plaforme+"\\"+img_name);
         ImageIO.write(saveImage, "png", screenshotLocation);
    }

     public static void ScreenShot1(ChromeDriver driver, WebElement webElement, String img_name) throws IOException {

//         Screenshot screenshot = new AShot().takeScreenshot(driver,webElement);
//         ImageIO.write(screenshot.getImage(),"PNG",new File(System.getProperty("user.dir") +"\\ErrorScreenshots\\ElementScreenshot.png"));

     }
}