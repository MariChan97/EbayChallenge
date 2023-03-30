package steps;

import io.cucumber.java.en.*;
import org.junit.Assert;
import pages.GooglePage;

public class GoogleSteps {

    GooglePage google = new GooglePage();

    @Given("^I am on the Google search page$")
    public void navigateToGoogleSearchPage(){
        google.navigateToGoogle();
    }

    @When("^I enter a search criteria$")
    public void enterSearchCriteria(){
        google.enterSearchCriteria("Google");
    }

    @And("^click on the search button$")
    public void clickOnSearchButton(){
        google.clickGoogleSearch();
    }

    @Then("^results match the criteria$")
    public void validateResults(){
        Assert.assertEquals("Argentina.gob.ar: Inicio", google.firstResult());
    }
}
