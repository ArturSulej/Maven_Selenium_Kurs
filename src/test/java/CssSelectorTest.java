import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.util.List;

public class CssSelectorTest {

    @Test
    public void cssSelectors(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://testeroprogramowania.github.io/selenium/basics.html");

        // Wszystkie tagi
        List<WebElement> allElements = driver.findElements(By.cssSelector("*"));

        // Lista z listą zagnieżdżoną
        List<WebElement> allListInDiv = driver.findElements(By.cssSelector("div ul"));

        // Bezpośrednie dziecko elementu - bez list zagnieżdżonych
        WebElement child = driver.findElement(By.cssSelector("div > ul"));
        //WebElement tableRow = driver.findElement(By.cssSelector("table > tr")); // nie zadziała bo tr jest w tbody

        // Pierwszy formularz po label
        WebElement form = driver.findElement(By.cssSelector("label + form"));

        // Wszystkie formularze po tagu label
        List<WebElement> forms = driver.findElements(By.cssSelector("label ~ form"));


    }

    @Test
    public void findByAttributeValue(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://testeroprogramowania.github.io/selenium/basics.html");

        // Elementy których wartość atrybutu name zawiera 'ame'
        WebElement firstNameInput = driver.findElement(By.cssSelector("[name*='ame']"));

        // Elementy których wartość atrybutu name zaczyna się od f
        WebElement firstNameInput2 = driver.findElement(By.cssSelector("[name^='f']"));

        // Elementy których wartość atrybutu name kończy się na 'me'
        WebElement firstNameInput3 = driver.findElement(By.cssSelector("[name$='ame']"));
    }

    @Test
    public void findTagChild(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://testeroprogramowania.github.io/selenium/basics.html");

        // Wyszukanie pierwszego dziecka - pierwsze dziecko w KAŻDYM znaczniku rodzica - dostajemy 2 w tym przypadki
        WebElement firstChild = driver.findElement(By.cssSelector("li:first-child"));

        // Wyszukanie ostatniego dziecka
        WebElement lastChild = driver.findElement(By.cssSelector("li:last-child"));

        // Wyszukanie n-tego dziecka (numerujemy od 1)
        WebElement nChild = driver.findElement(By.cssSelector("li:nth-child(2)"));


    }
}
