import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.util.List;

public class SelectorTest {


    @Test
    public void findElements(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://testeroprogramowania.github.io/selenium/basics.html");

        // Znajdowanie elementu po Id
        WebElement clickOnMeButton =  driver.findElement(By.id("clickOnMe"));

        //Znajdowanie elementu po name
        WebElement firstNameInput =  driver.findElement(By.name("fname"));

        // Znajdowanie elementów po class
        WebElement topSecretClass = driver.findElement(By.className("topSecret"));

        // Znajdowanie elementów po tagu HTML
        WebElement inputElement = driver.findElement(By.tagName("input")); // Tylko pierwszy element
        List<WebElement> inputElements = driver.findElements(By.tagName("input"));

        // Lokalizowanie linków z tekstem
        WebElement textedLink = driver.findElement(By.linkText("IamWeirdLink")); // Należy przekazać cały tekst
        WebElement partialTextedLink = driver.findElement(By.partialLinkText("Visit"));

        // Lokalizowanie za pomocą css
            // Id
            WebElement cssId = driver.findElement(By.cssSelector("#clickOnMe"));

            // Klasa
            WebElement cssClass = driver.findElement(By.cssSelector(".topSecret"));

            // Tag
            WebElement cssTag = driver.findElement(By.cssSelector("input"));

            // Name
            WebElement cssName = driver.findElement(By.cssSelector("[name='fname']"));

            // Class
            WebElement cssClass2 = driver.findElement(By.cssSelector("[class='topSecret']"));
    }


}
