import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class WebDriverManagerTest {

    @Test
    public void openBrowser(){
        // Należy mieć przeglądarkę na komputerze
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
    }
}
