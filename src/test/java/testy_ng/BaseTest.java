package testy_ng;

import org.testng.annotations.*;

public class BaseTest {

    // Przed @BeforeTest
    @BeforeSuite
    public void beforeSuite(){
        System.out.println("Before suite");
    }

    // Przed @BeforeMethod, a po @BeforeTest
    @BeforeClass
    public void beforeClass(){
        System.out.println("Before class");
    }

    // Na końcu po @AfterTest
    @AfterSuite
    public void afterSuite(){
        System.out.println("After suite");
    }

    // Po testach z jednej klasy
    @AfterClass
    public void afterClass(){
        System.out.println("After class");
    }


    // Uruchamia się przed pierwszym testem
    @BeforeTest
    public void beforeTest(){
        System.out.println("I am running before test");
    }

    // Uruchamia się przed każdą metodą
    @BeforeMethod
    public void beforeMethod(){
        System.out.println("I am running before method");
    }

    // Uruchamia się po ostatnim teście, po @AfterClass
    @AfterTest
    public void afterTest(){
        System.out.println("I am running after test");
    }

    // Uruchamia się po każdej metodzie
    @AfterMethod
    public void afterMethod(){
        System.out.println("I am running after method");
    }
}
