import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.testng.annotations.Test;

public class SeleniumTest {

    public WebDriver getDriver(String browser){
        return switch (browser) {
            case "chrome" -> new ChromeDriver();
            case "firefox" -> new FirefoxDriver();
            case "ie" -> new InternetExplorerDriver();
            default -> throw new IllegalArgumentException("Invalid browser name");
        };
    }

    @Test
    public void openGooglePageChrome(){
        WebDriver driver = getDriver("chrome");
        driver.get("https://www.google.com");
    }

    @Test
    public void openGooglePageFirefox(){
        WebDriver driver = getDriver("firefox");
        driver.get("https://www.google.com");
    }

    @Test
    public void openGooglePageIE(){
        WebDriver driver = getDriver("ie");
        driver.get("https://www.google.com");
    }
}
