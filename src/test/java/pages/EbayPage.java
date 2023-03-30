package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

public class EbayPage extends BasePage {

    String SearchBox = "//*[@id=\"gh-ac\"]";
    String SearchBtn = "//*[@id=\"gh-btn\"]";
    String BrandNike = "//*[@id=\"s0-51-15-6-3-4[0]-3-1-1-list\"]/li[13]";
    String ShoeSize = "//ul[@class='carousel__list']/li[7]";
    String ShoeExample = "//*[@id=\"srp-river-results\"]/ul/li[3]/div/div[2]/div[1]/a/div/span";
    String AllResults = "//h1[@class='srp-controls__count-heading']";
    String PriceShoes = "s-item__price"; //list
    String ProductName = "s-item__link"; //list
    String sortList = "//div//span//button[@class='fake-menu-button__button btn btn--secondary']/span['btn__cell']//span";

    public EbayPage() {
        super(driver);
    }

    public void navigateToEbay() {
        navigateTo("http://www.ebay.com");
    }

    public void searchForShoes() throws InterruptedException {
        clickElement(SearchBox);
        write(SearchBox, "Shoes");
        clickElement(SearchBtn);

        Thread.sleep(5000);
        clickElement(BrandNike);
        Thread.sleep(5000);
        clickElement(ShoeSize);
    }

    public void printTotalResults() {
    String totalResults = textFromElement(AllResults);
    System.out.println("The total number of results are");
    System.out.println(totalResults);
    }

    public List<Integer> orderAndPrintResults() {
        navigateTo("https://www.ebay.com/sch/i.html?_from=R40&_nkw=shoes&_sacat=0&Brand=Nike&_dcat=93427&US%2520Shoe%2520Size=10&_sop=15");
        List<WebElement> shoePrices = bringMeAllElements(PriceShoes); //Product price
        List<WebElement> productName = bringMeAllElements(ProductName);// nombre del producto
        System.out.println("The firs five results with price are:");
        for (int i = 0; i < 6; i++) {
            if (productName.size() > 0 && shoePrices.size() >= 12) {
                WebElement shoe = shoePrices.get(i);
                WebElement name = productName.get(i);
                System.out.println(name.getText().replaceAll("Se abre en una ventana nueva", "") + shoe.getText());
            }
        }
        Iterator<WebElement> it = shoePrices.iterator();
        List<String> resultList = new ArrayList<>();
        while (it.hasNext()) { //2
            String price = it.next().getText();
            if (price.length() > 0 && price.length() <= 12) {
                price = price.replaceAll("[^\\d.]", "");
                resultList.add(price);
            }
        }
        //para pasar de string a float:
        List<Float> shoePrice = resultList.stream().map(Float::parseFloat).collect(Collectors.toList());
         List <Float> orderedPrice = new ArrayList<>();
        for (Float total : shoePrice) {
            orderedPrice.add(total);
        }
        // para ordenar los precios de menor a mayor
        Collections.sort(orderedPrice, Collections.reverseOrder());
        Collections.reverse(orderedPrice);

        System.out.println("The prices in order from minor to major are: ");
        System.out.println(orderedPrice);

        return null;
        }
        public void printProductsInAscendent(){
        List<WebElement> products = bringMeAllElements(ProductName);
        Iterator it = products.iterator();
        while (it.hasNext()){
           System.out.println(it.next());
        }
        }
}

