package testy_ng;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class NG2Tests extends BaseTest {
    WebDriver driver = new ChromeDriver();

    @Test
    public void firstTest(){
        driver.get("https://testeroprogramowania.github.io/selenium/wait2.html");

        driver.findElement(By.xpath("//button[@id='clickOnMe']")).click();
        waitForElementToExist(By.xpath("//p"));

        String paraText = driver.findElement(By.xpath("//p")).getText();

        Assert.assertEquals(paraText,"Dopiero się pojawiłem!");
    }

    @Test
    public void secondTest(){
        driver.get("https://testeroprogramowania.github.io/selenium/wait2.html");

        driver.findElement(By.xpath("//button[@id='clickOnMe']")).click();
        waitForElementToExist(By.xpath("//p"));

        String paraText = driver.findElement(By.xpath("//p")).getText();

        Assert.assertEquals(paraText,"Dopiero się pojawiłem!");
    }

    public void waitForElementToExist(By locator){
        FluentWait<WebDriver> wait2 = new FluentWait<>(driver);
        wait2.ignoring(NoSuchElementException.class); // Dodanie ignorowania wyjątku
        wait2.withTimeout(Duration.ofSeconds(10)); // dodanie
        wait2.pollingEvery(Duration.ofSeconds(1));
        wait2.until((driver)-> {
            List<WebElement> elements = driver.findElements(locator);
            if (!elements.isEmpty()){
                System.out.println("Element jest na stronie");
                return true;
            }
            else{
                System.out.println("Elementu nie ma na stronie");
                return false;
            }
        });
    }
}
