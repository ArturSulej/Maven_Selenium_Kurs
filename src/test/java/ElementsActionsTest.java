import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import java.util.List;

public class ElementsActionsTest {
    WebDriver driver = new ChromeDriver();
    @Test
    public void clickOnElement(){
        driver.get("https://testeroprogramowania.github.io/selenium/");
        WebElement basicPageLink = driver.findElement(By.partialLinkText("Podstawowa"));
        basicPageLink.click();

        WebElement button = driver.findElement(By.id("clickOnMe"));
        button.click();
    }

    @Test
    public void passInput(){
        driver.get("https://testeroprogramowania.github.io/selenium/basics.html");
        driver.findElement(By.id("fname")).sendKeys("Artur");
        driver.findElement(By.name("username")).clear();
    }

    @Test
    public void pressKeyboardKeys(){
        driver.get("https://testeroprogramowania.github.io/selenium/basics.html");
        WebElement usernameInput = driver.findElement(By.name("username"));
        usernameInput.clear();
        usernameInput.sendKeys("Admin");
        usernameInput.sendKeys(Keys.TAB); // Nie da się dwa razy przejść - należy zlokalizować następne pole i na nim wywołać TAB
    }

    @Test
    public void selectCheckBoxAndRadioButton(){
        driver.get("https://testeroprogramowania.github.io/selenium/basics.html");
        WebElement checkbox =  driver.findElement(By.cssSelector("[type='checkbox']"));
        checkbox.click();

        WebElement radioButton = driver.findElement(By.xpath("//input[@type='radio' and @name='gender' and @value='female']"));
        radioButton.click();
    }

    @Test
    public void chooseFromSelect(){
        driver.get("https://testeroprogramowania.github.io/selenium/basics.html");
        WebElement selectCar = driver.findElement(By.xpath("//select"));
        Select cars = new Select(selectCar);
        cars.selectByIndex(2); // Numerowanie od 0
        cars.selectByVisibleText("Saab");
        cars.selectByValue("volvo");
    }

    @Test
    public void getOptionsFromSelect(){
        driver.get("https://testeroprogramowania.github.io/selenium/basics.html");
        WebElement selectCar = driver.findElement(By.xpath("//select"));
        Select cars = new Select(selectCar);

        List<WebElement> selectOptions = cars.getOptions();
        for(WebElement element: selectOptions){
            System.out.println(element.getText());
        }
    }

    // Praca domowa - sprawdzenie czy string znajduje się w Select
    public boolean pd(String value){
        driver.get("https://testeroprogramowania.github.io/selenium/basics.html");
        WebElement selectCar = driver.findElement(By.xpath("//select"));
        Select cars = new Select(selectCar);

        List<WebElement> selectOptions = cars.getOptions();
        for(WebElement element: selectOptions){
            if(element.getText().equals(value)){
                return true;
            }
        }
        return false;
    }


}
