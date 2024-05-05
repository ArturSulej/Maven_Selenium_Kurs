import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ElementsStatusTests {
    WebDriver driver = new ChromeDriver();
    @Test
    public void checkIfExists(){
        driver.get("https://testeroprogramowania.github.io/selenium/basics.html");
        System.out.println(elementExists(By.xpath("//p[@class='topSecret']")));
        System.out.println(elementExists(By.xpath("//p[@id='topSecret']")));

        System.out.println(elementExistsSecond(By.xpath("//p[@class='topSecret']")));
        System.out.println(elementExistsSecond(By.xpath("//p[@id='topSecret']")));

        //driver.findElement(By.xpath("//p[@class='topSecret']"));
        //driver.findElement(By.xpath("//p[@id='topSecret']")); // Zakończy się niepowodzeniem
    }

    public boolean elementExists(By locator){
        try{
            driver.findElement(locator);
            return true;
        }catch (NoSuchElementException e){
            return false;
        }
    }

    public boolean elementExistsSecond(By locator){
        if(!driver.findElements(locator).isEmpty()){
            return true;
        }
        return false;
    }

    @Test
    public void checkIfDisplayed(){
        driver.get("https://testeroprogramowania.github.io/selenium/basics.html");
        System.out.println(driver.findElement(By.xpath("//p[@class='topSecret']")).isDisplayed());
    }

    @Test
    public void checkIfInteractable(){
        driver.get("https://testeroprogramowania.github.io/selenium/basics.html");
        System.out.println( driver.findElement(By.xpath("//button[@id='clickOnMe']")).isEnabled());
    }

    @Test
    public void ifCheckboxChecked(){
        driver.get("https://testeroprogramowania.github.io/selenium/basics.html");
        System.out.println( driver.findElement(By.xpath("//input[@type='checkbox']")).isSelected());
    }

    @Test
    public void checkIfImageLoaded(){
        driver.get("https://testeroprogramowania.github.io/selenium/image.html");
        WebElement image = driver.findElement(By.xpath("//img[@id='smileImage']"));
        String height = image.getAttribute("naturalHeight"); // Jeżeli wysokość większa od 0 to się wyświetla

        Assert.assertEquals(height,"0");

    }
}
