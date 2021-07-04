package cucumberHomework.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static io.restassured.RestAssured.given;

public class HomeworkSteps {

    private WebDriver driver;

    @Given("We have webdriver")
    public void weHaveWebdriver() {
        System.out.println("Создан класс с вебдрайвером");
    }

    @When("Getting to Google.com")
    public void gettingToGoogleCom() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.google.com/");
        WebElement logo = driver.findElement(By.cssSelector("img.lnXdpd"));
        Assertions.assertEquals("Google", logo.getAttribute("alt"));
    }

    @Then("We get right statuscode from server")
    public void weGetRightStatuscodeFromServer() {
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get("https://www.google.com/")
                .then()
                .extract().response();

        System.out.println(response.getBody().prettyPrint());
        Assertions.assertEquals(200, response.statusCode());
    }


}
