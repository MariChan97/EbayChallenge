package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import pages.EbayPage;

import java.util.Arrays;
import java.util.List;

public class EbaySteps {
    EbayPage ebay = new EbayPage();
    @Given("I navigate to {string}")
    public void i_navigate_to(String url) {
        ebay.navigateToEbay();
    }
    @When("I search for shoes brand puma number ten")
    public void iSearchForShoes() throws InterruptedException {
        ebay.searchForShoes();
        String ShoeExample = "//*[@id=\"srp-river-results\"]/ul/li[3]/div/div[2]/div[1]/a/div/span";
        Assert.assertTrue(ebay.elementIsDisplayed(ShoeExample));
    }
    @And("I print the number of results")
    public void iPrintTheNumberOfResults(){
    ebay.printTotalResults();
    }

    @Then("I print the name and price from the first five results from minor to major")
    public List<Integer> orderAndPrintFirstFive(){
       List <Integer> firstFive = ebay.orderAndPrintResults();
        return firstFive;
    }
    @And("Print the products name in ascendent form")
    public void printInAscendentForm(){
        ebay.printProductsInAscendent();
    }

    @And("Print the products price in descendent form")
    public void PrintPriceInDescendent(){
    ebay.printPricesInDescendentForm();
    }
}

