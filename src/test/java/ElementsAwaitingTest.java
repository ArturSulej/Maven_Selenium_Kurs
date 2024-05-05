import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

public class ElementsAwaitingTest {
    WebDriver driver = new ChromeDriver();

    @Test
    public void sleepMethod() throws InterruptedException {
        driver.get("https://testeroprogramowania.github.io/selenium/wait2.html");

        driver.findElement(By.xpath("//button[@id='clickOnMe']")).click();
        Thread.sleep(5000); // czas określony na sztywno
        driver.findElement(By.xpath("//p"));
    }

    @Test
    public void implicitMethod()  {
        driver.get("https://testeroprogramowania.github.io/selenium/wait2.html");
        // Powiązany z całym testem, nie z konkretnym miejscem
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.findElement(By.xpath("//button[@id='clickOnMe']")).click();

        driver.findElement(By.xpath("//p"));
    }

    @Test
    public void explicitMethod()  {
        driver.get("https://testeroprogramowania.github.io/selenium/wait2.html");

        driver.findElement(By.xpath("//button[@id='clickOnMe']")).click();
        // Powiązany z konkretnym miejscem (domyślnie ignoruje wyjątki) - jest dzieckiem FluentWait
        //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p")));
        // II Sposób - FluentWait - nie ignoruje domyślnie wyjątków
        FluentWait<WebDriver> wait2 = new FluentWait<>(driver);
        wait2.ignoring(NoSuchElementException.class); // Dodanie ignorowania wyjątku
        wait2.withTimeout(Duration.ofSeconds(10)); // dodanie
        wait2.pollingEvery(Duration.ofSeconds(1)); // Co ile sprawdzamy warunek
        wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p")));
        // Używamy locator zamiast WebElement, bo WebElement sprawdza już przy uruchomieniu programu

    }

    @Test
    public void ownConditions(){
        driver.get("https://testeroprogramowania.github.io/selenium/wait2.html");

        driver.findElement(By.xpath("//button[@id='clickOnMe']")).click();
        waitForElementToExist(By.xpath("//p"));
    }

    public void waitForElementToExist(By locator){
        FluentWait<WebDriver> wait2 = new FluentWait<>(driver);
        wait2.ignoring(NoSuchElementException.class); // Dodanie ignorowania wyjątku
        wait2.withTimeout(Duration.ofSeconds(10)); // dodanie
        wait2.pollingEvery(Duration.ofSeconds(1));
        // I sposób - klasa anonimowa
        /*
        wait2.until(new Function<WebDriver, Boolean>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                List<WebElement> elements = driver.findElements(locator);
                if (!elements.isEmpty()){
                    System.out.println("Element jest na stronie");
                    return true;
                }
                else{
                    System.out.println("Elementu nie ma na stronie");
                    return false;
                }
            }
        });
         */
        // II sposób - lambda
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
