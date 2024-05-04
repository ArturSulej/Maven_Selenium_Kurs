import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FirstTest {

    // Pierwszy test
    @Test
    public void searchGoogleChrome(){
        WebDriver driver = SeleniumTest.getDriver("chrome");
        driver.manage().window().maximize();
        driver.get("https://www.google.com");

        // Zaakceptowanie cookies w google.com
        //driver.switchTo().frame(0); // przejście do okna z plikami cookie
        WebElement agreeButton = driver.findElement(By.xpath("//div[text()='Zaakceptuj wszystko']"));
        agreeButton.click(); // Naciśnięcie przycisku

        // Powrót do pierwotnego okna
        //driver.switchTo().defaultContent();

        //Symulacja wyszukiwania
        WebElement searchField = driver.findElement(By.name("q"));
        searchField.sendKeys("Selenium");
        searchField.sendKeys(Keys.ENTER);

        // Znaleźć rezultat
        WebElement result = driver.findElement(By.xpath("//a[contains(@href,'selenium.dev')]//h3"));

        // Sprawdzenie - asercja
        Assert.assertTrue(result.isDisplayed());
    }
}
