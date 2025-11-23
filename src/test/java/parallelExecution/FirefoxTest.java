package parallelExecution;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import java.time.Duration;

public class FirefoxTest {
    @Test
    public void FirefoxTestMethod01() {
        System.out.println("Initializing the Microsoft Edge driver");
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        //Initialize the Edge driver
        System.out.println("The thread ID for Edge is " + Thread.currentThread().getId());
        driver.get("https://sweetshop.netlify.app/");
        driver.quit();
    }

    @Test
    public void FirefoxTestMethod02() {
        System.out.println("Initializing the Microsoft Edge driver");
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        //Initialize the Edge driver
        System.out.println("The thread ID for Edge is " + Thread.currentThread().getId());
        driver.get("https://sweetshop.netlify.app/");
        driver.quit();
    }

    @Test
    public void FirefoxTestMethod03() {
        System.out.println("Initializing the Microsoft Edge driver");
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        //Initialize the Edge driver
        System.out.println("The thread ID for Edge is " + Thread.currentThread().getId());
        driver.get("https://sweetshop.netlify.app/");
        driver.quit();
    }

    @Test
    public void FirefoxTestMethod04() {
        System.out.println("Initializing the Microsoft Edge driver");
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        //Initialize the Edge driver
        System.out.println("The thread ID for Edge is " + Thread.currentThread().getId());
        driver.get("https://sweetshop.netlify.app/");
        driver.quit();
    }
}
