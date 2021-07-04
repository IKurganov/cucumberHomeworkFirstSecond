package cucumberHomework.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitions {
    @Given("We have user id in DB")
    public void weHaveUserIdInDB() {
        System.out.println("user in DB");
    }

    @When("User sent request to get info")
    public void userSentRequestToGetInfo() {
        System.out.println("user send request");
    }

    @Then("We saved information about user request id DB")
    public void weSavedInformationAboutUserRequestIdDB() {
        System.out.println("Information saved in DB");
    }
}
