import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SeleniumTest {

    public static WebDriver getDriver(String browser){
        return switch (browser) {
            case "chrome" -> new ChromeDriver();
            case "firefox" -> new FirefoxDriver();
            case "ie" -> new InternetExplorerDriver();
            default -> throw new IllegalArgumentException("Invalid browser name");
        };
    }

    // Old version
    /*
    @Test
    public void openGooglePageChrome(){
        ChromeOptions options = new ChromeOptions();
        options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT); // Ustawienie strategii przy niespodziewanych alertach
        WebDriver driver = getDriver("chrome");
        driver.manage().window().maximize(); // Maksymalizacja okna przeglądarki
        driver.get("https://www.google.com");

        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("alert('Hello')"); // wywołanie kodu JavaScript - niespodziewany alert - fail w teście

        // Zamknięcie przeglądarki - zamyka wszystkie okna
        driver.quit();
    }
    */
    @Test
    public void openGooglePageFirefox(){
        WebDriver driver = getDriver("firefox");
        // Ustawienie konkretnej rozdzielczości
        Dimension window_size = new Dimension(200,200);
        driver.manage().window().setSize(window_size);
        driver.get("https://www.google.com");
        // Zamknięcie przeglądarki - sposób II - zamyka okno pierwotne
        driver.close();
    }

    @Test
    public void openGooglePageIE(){
        WebDriver driver = getDriver("ie");
        driver.get("https://www.google.com");
    }
}
