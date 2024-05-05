package testy_ng;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.List;

@Listeners(value = {SampleTestListener.class})
public class NGTests extends BaseTest {
    WebDriver driver = new ChromeDriver();

    // @Ignore - ignorowanie testu
    @Test
    public void firstTest(){
        driver.get("https://testeroprogramowania.github.io/selenium/wait2.html");

        driver.findElement(By.xpath("//button[@id='clickOnMe']")).click();
        waitForElementToExist(By.xpath("//p"));

        WebElement paraText = driver.findElement(By.xpath("//p"));

        // Twarde Asercje - koniec testu w momencie fail
        Assert.assertEquals(paraText.isDisplayed(), true);
        Assert.assertTrue(paraText.isDisplayed(), "Element is not displayed");
        Assert.assertEquals(paraText.getText(), "Dopiero", "Teksty są różne");
        Assert.assertTrue(paraText.getText().startsWith("Dopiero"));
        Assert.assertFalse(paraText.getText().startsWith("Pojawiłem"));
        Assert.assertEquals(paraText.getText(),"Dopiero się pojawiłem!");
    }

    @Test @Ignore
    public void secondTest(){
        driver.get("https://testeroprogramowania.github.io/selenium/wait2.html");

        driver.findElement(By.xpath("//button[@id='clickOnMe']")).click();
        waitForElementToExist(By.xpath("//p"));

        WebElement paraText = driver.findElement(By.xpath("//p"));

        SoftAssert softAssert = new SoftAssert();

        // Miękkie Asercje - pomimo fail następne też się wywołają
        softAssert.assertEquals(paraText.isDisplayed(), true);
        softAssert.assertTrue(paraText.isDisplayed(), "Element is not displayed");
        softAssert.assertEquals(paraText.getText(), "Dopiero", "Teksty są różne");
        softAssert.assertTrue(paraText.getText().startsWith("Dopiero"));
        softAssert.assertFalse(paraText.getText().startsWith("Pojawiłem"));
        softAssert.assertEquals(paraText.getText(),"Dopiero się", "Druga Asercja");

        // Dodać na koniec!
        softAssert.assertAll();
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
