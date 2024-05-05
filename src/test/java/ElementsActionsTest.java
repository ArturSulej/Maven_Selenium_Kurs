import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

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

    @Test
    public void getTextFromInput(){
        driver.get("https://testeroprogramowania.github.io/selenium/basics.html");
        WebElement input = driver.findElement(By.id("fname"));
        input.sendKeys("Artur");
        System.out.println(input.getText()); // To nie wyświetla tekstu
        System.out.println(input.getAttribute("value"));
    }

    @Test
    public void getTextFromHiddenElement(){
        driver.get("https://testeroprogramowania.github.io/selenium/basics.html");
        WebElement hiddenElement = driver.findElement(By.xpath("//p[@class='topSecret']"));
        System.out.println("get text: "+hiddenElement.getText());
        System.out.println("get attribute value: "+hiddenElement.getAttribute("value"));
        System.out.println("get attribute textContent: "+hiddenElement.getAttribute("textContent"));
    }

    @Test
    public void handleAlerts(){
        driver.get("https://testeroprogramowania.github.io/selenium/basics.html");
        WebElement usernameInput = driver.findElement(By.name("username"));
        usernameInput.clear();
        usernameInput.sendKeys("Admin");
        usernameInput.sendKeys(Keys.ENTER);
        // Przepięcie do alertu
        Alert alert = driver.switchTo().alert();
        alert.accept();
        driver.switchTo().alert().accept();
    }

    @Test
    public void handleNewWindow(){
        driver.get("https://testeroprogramowania.github.io/selenium/basics.html");
        String currentWindow = driver.getWindowHandle(); // Nazwa okna przed kliknięciem
        WebElement button = driver.findElement(By.xpath("//button[@id='newPage']"));
        button.click();
        Set<String> windowNames = driver.getWindowHandles(); // Nazwy wwszystkich dostępnych okien
        for(String window : windowNames){
            if(!window.equals(currentWindow)){
                driver.switchTo().window(window); // Trzeba znać nazwę okna
            }
        }
        driver.findElement(By.xpath("//button/div[text()='Zaakceptuj wszystko']")).click();
        driver.findElement(By.name("q")).sendKeys("Selenium");
        driver.switchTo().window(currentWindow);
        driver.findElement(By.name("fname")).sendKeys("Artur");
    }

    @Test
    public void handleIFrame(){
        driver.get("https://testeroprogramowania.github.io/selenium/iframe.html");
        // I sposób - przy zmianie kolejności iframe na stronie będą problemy
        //driver.switchTo().frame(0); // numerujemy od 0
        // II sposób
        WebElement iframe = driver.findElement(By.xpath("//iframe[@src='basics.html']"));
        driver.switchTo().frame(iframe);

        WebElement inputText = driver.findElement(By.xpath("//input[@id='fname']"));
        inputText.sendKeys("Artur");
        driver.switchTo().defaultContent(); // Powrót do pierwotnej strony
    }

    @Test
    public void executeJavaScriptExecutor(){
        driver.get("https://testeroprogramowania.github.io/selenium/");
        WebElement basicPageLink = driver.findElement(By.partialLinkText("Podstawowa"));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();",basicPageLink);
        // Wprowadzenie wartości za pomocą JavaScript Executor
        WebElement firstname = driver.findElement(By.xpath("//input[@name='fname']"));
        executor.executeScript("arguments[0].setAttribute('value','Artur')",firstname);
    }

    @Test
    public void uploadFile(){
        driver.get("https://testeroprogramowania.github.io/selenium/fileupload.html");
        WebElement chooseFile = driver.findElement(By.xpath("//input[@type='file']"));
        chooseFile.sendKeys("C:\\Users\\sulej\\Downloads\\plik.txt");
        // II sposób - sikuli
    }

    @Test
    public void takeScreenShot() throws IOException {
        driver.get("https://testeroprogramowania.github.io/selenium/fileupload.html");
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File src = screenshot.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(src, new File("src/test/resources/screenshot.png"));
    }

    @Test
    public void mouseRightClick() {
        driver.get("https://testeroprogramowania.github.io/selenium/fileupload.html");
        WebElement chooseFile = driver.findElement(By.xpath("//input[@type='file']"));
        Actions actions = new Actions(driver);
        //actions.contextClick().perform(); // na stronie
        actions.contextClick(chooseFile).perform(); // na elemencie
    }

    @Test
    public void mouseDoubleClick(){
        driver.get("https://testeroprogramowania.github.io/selenium/doubleclick.html");
        WebElement button = driver.findElement(By.xpath("//button[@id='bottom']"));

        Actions actions = new Actions(driver);
        actions.doubleClick(button).perform();
    }

    @Test
    public void hoveringElement(){
        driver.get("https://testeroprogramowania.github.io/selenium/basics.html");
        WebElement h1 = driver.findElement(By.xpath("//h1[text()='Witaj na stronie testowej']"));

        Actions actions = new Actions(driver);
        actions.moveToElement(h1).perform();
    }

    @Test
    public void getTitleAndUrl(){
        driver.get("https://testeroprogramowania.github.io/selenium/basics.html");
        System.out.println(driver.getTitle());
        System.out.println(driver.getCurrentUrl());
    }
}
