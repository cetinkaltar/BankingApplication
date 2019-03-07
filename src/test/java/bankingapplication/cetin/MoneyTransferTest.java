package bankingapplication.cetin;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.List;


public class MoneyTransferTest {

    @Test
    public void sendMoney() throws URISyntaxException {
        //chromedriver needed for webdriver in selenium to run chrome
        URL res = getClass().getClassLoader().getResource("chromedriver.exe");
        File file = Paths.get(res.toURI()).toFile();
        String absolutePath = file.getAbsolutePath();
        System.setProperty("webdriver.chrome.driver",
               absolutePath);
        String baseUrl = "http://localhost:8080/transfer";
        WebDriver driver = new ChromeDriver();
        driver.get(baseUrl);

        driver.findElement(By.id("fromTxt")).clear();
        driver.findElement(By.id("fromTxt")).sendKeys("1");
        driver.findElement(By.id("toTxt")).clear();
        driver.findElement(By.id("toTxt")).sendKeys("2");
        driver.findElement(By.id("amount")).clear();
        driver.findElement(By.id("amount")).sendKeys("100");

        driver.findElement(By.id("sendMoney")).click();
        baseUrl = "http://localhost:8080/";
        driver.get(baseUrl);
        WebElement table_element = driver.findElement(By.id("infoTable"));
        List<WebElement> tr_collection = table_element.findElements(By.xpath("id('infoTable')/tbody/tr/td"));
        double val1 = Double.parseDouble(tr_collection.get(2).getText());
        double val2 = Double.parseDouble(tr_collection.get(5).getText());
        Assert.assertEquals(900.0, val1, 1);
        Assert.assertEquals(1100.0 , val2, 1);

        driver.close();
    }
}
