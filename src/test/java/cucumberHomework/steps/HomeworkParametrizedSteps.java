package cucumberHomework.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class HomeworkParametrizedSteps {

    private WebDriver driver;

    @Given("We have webdriver for parametrized test")
    public void weHaveWebdriverForParametrizedTest() {
        System.out.println("Создан класс с вебдрайвером");
    }

    @When("Getting to Google.com to search something {string}")
    public void gettingToGoogleComToSearchSomething(String something) {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.google.com/");
        WebElement searchField = driver.findElement(By.name("q"));
        searchField.sendKeys(something);
        searchField.sendKeys(Keys.ENTER);
        System.out.println("Поиск совершен");
    }

    @Then("Can find some information about something and see it in titles {string}")
    public void canFindSomeInformationAboutSomethingAndSeeItInTitles(String title) {
        Assertions.assertTrue(driver.findElement(By.cssSelector("a[href] h3")).getText().contains(title));
        driver.quit();
    }
}
