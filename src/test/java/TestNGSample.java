import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;


public class TestNGSample {
    private WebDriver driver;
    private boolean isHeadless = Boolean.parseBoolean(System.getProperty("headless"));
    @BeforeMethod
    @Parameters("browser")
    public void Setup(String browser) {
        System.out.println("Starting Test On "+browser+" Browser");
        switch (browser) {
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--headless");
                driver = new ChromeDriver(chromeOptions);
                driver.manage().window().maximize();
                driver.manage().deleteAllCookies();
                driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
                driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                break;
            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--headless");
                driver = new FirefoxDriver(firefoxOptions);
                driver.manage().window().maximize();
                driver.manage().deleteAllCookies();
                driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
                driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                break;
        }


    }

    @Test
    public void f() {

        String baseUrl = "https://www.toolsqa.com/";
        driver.get(baseUrl);
        String testTitle = "Tools QA";
        String originalTitle = driver.getTitle();
        Assert.assertEquals(originalTitle, testTitle);
    }

    @AfterMethod
    @Parameters("browser")
    public void afterMethod(String browser) {
        driver.close();
        System.out.println("Finished Test On "+browser+"Browser");
    }
}

