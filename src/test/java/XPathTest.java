import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class XPathTest {
    @Test
    public void xPathSearch(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://testeroprogramowania.github.io/selenium/basics.html");

        // Wyszukanie przycisków znajdujących się w body
        WebElement element = driver.findElement(By.xpath("/html/body/button"));

        // Pomijanie niektórych elementów strony - lepsze rozwiązanie - wyszukiwanie <tr> w całej strukturze
        WebElement element2 = driver.findElement(By.xpath("//tr"));
        WebElement element3 = driver.findElement(By.xpath("/html/body/table//tr"));

        // Wyszukiwanie w strukturze przycisku o konkretnym id
        WebElement clickOnMeButton =  driver.findElement(By.xpath("/html/body/button[@id='clickOnMe']"));
        WebElement clickOnMeButton2 =  driver.findElement(By.xpath("//button[@id='clickOnMe']"));

        // Wyszukiwanie w strukturze po atrybucie name
        WebElement firstNameInput =  driver.findElement(By.xpath("//input[@name='fname']"));

        // Wyszukiwanie elementów po atrybucie class
        WebElement topSecretClass = driver.findElement(By.xpath("//p[@class='topSecret']"));

        // Lokalizowanie linków z tekstem
        WebElement textedLink = driver.findElement(By.xpath("//a[text()='IamWeirdLink']")); // Należy przekazać cały tekst
        WebElement partialLink = driver.findElement(By.xpath("//a[contains(text(), 'Visit')]"));

        // Lokalizowanie za pomocą indeksów - numerowanie od 1
        WebElement indexedElement = driver.findElement(By.xpath("//input[2]"));
        WebElement lastElement = driver.findElement(By.xpath("//input[last()]")); // ostatni element

        // Wszystkie elementy zawierające atrybut name
        WebElement elementsWithAttribute = driver.findElement(By.xpath("//*[@name]"));

        // Elementy o id różnym od ClickOnMe
        WebElement noClickOnMe = driver.findElement(By.xpath("//*[@id!='clickOnMe']"));

        // Elementy których atrybut name zawiera tekst 'ame'
        WebElement elementsContainingAttributes = driver.findElement(By.xpath("//*[contains(@name, 'ame')]"));

        // Elementy których atrybut name zaczyna się na 'ame'
        WebElement elementsStartingAttributes = driver.findElement(By.xpath("//*[starts-with(@name, 'user')]"));

        // Elementy których atrybut name kończy się na 'ame'
        //WebElement elementsEndsAttributes = driver.findElement(By.xpath("//*[ends-with(@name, 'me')]")); // nie działa - należy użyć substring
        WebElement elementsEndsAttributes = driver.findElement(By.xpath("//*[substring(@name, string-length(@name)-string-length('ame')+1)='ame']"));

        // Wyszukiwanie po relacjach - dzieci, rodzice, wstępni i zstępni

            // Znajdowanie dziecka (jednego)
            driver.findElement(By.xpath("//div/child::ul"));

            // Znajdowanie dziecka (wszystkich)
            driver.findElement(By.xpath("//div/descendant::ul"));

            // Znajdowanie zstępnych (przodków)
            driver.findElement(By.xpath("//div/ancestor::*"));

            // Znajdowanie bezpośredniego rodzica
            driver.findElement(By.xpath("//div/.."));

            // Znajdowanie bezpośredniego dziadka
            driver.findElement(By.xpath("//div/../.."));

            // Znajdowanie tagów następujących po obiekcie - cała struktura
            driver.findElement(By.xpath("//img/following::*"));

            // Znajdowanie tagów następujących po obiekcie - ten sam poziom
            driver.findElement(By.xpath("//img/following-sibling::*"));

            // Znajdowanie tagów poprzedzających obiekt - cała struktura
            driver.findElement(By.xpath("//img/preceding::*"));

            // Znajdowanie tagów poprzedzających obiekt - ten sam poziom
            driver.findElement(By.xpath("//img/preceding-sibling::*"));

        // Operatory

            // Wyszukanie inputów i odnośników - suma
            driver.findElement(By.xpath("//a | //input"));

            // Wyszukanie inputa którego id jest fname i name jest fname (logiczne and)
            driver.findElement(By.xpath("//input[@name='fname' and @id='fname']"));

            // Wyszukanie input którego id=fname lub name=fname (logiczne or)
            driver.findElement(By.xpath("//input[@name='fname' or @id='fname']"));


    }
}
