package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BasePage {
    // static hace que haya un solo valor para todas las instancias de esta clase.
    // Es decir, una sola instancia de WebDriver compartida para todas las clases
    protected static WebDriver driver;
    private static WebDriverWait wait;
    private static Actions action;

    // bloque estatico que se va a ejecutar al comienzo
    static {
        System.setProperty("webdriver.chrome.driver", "C:\\Marianella\\proyecto-Ger\\basic-selenium-cucumber\\src\\main\\resources\\drivers\\chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(chromeOptions);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    //Constructor
    public BasePage(WebDriver driver) {
        BasePage.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // [[[*]]] esto es el wait IMPLISCITO PURO que no es recomendable usar ya que va a esperar esos 10 segundos para cada accion.
        // driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    public static void navigateTo(String url) {
        driver.get(url);
    }

    public static void closeBrowser(){
        driver.quit();
    }

    // No quiero instanciar los webelements desde cada pagina sino que lo quiero hacer una sola vez desde aqui
    // y luego, usar la herencia
    private WebElement Find(String locator) {
        // Aqui seteamos un wait general que va a hacer que espere hasta que el elemento sea visible o que alcance el limite de 10 segundos que seteamos más arriba.
        // Esta es un tipico wait EXPLISCITO si se declara en cada instanciacion, pero la hemos hecho IMPLISCITA  ya que al estar en la BASEPAGE, se ejecutará de manera
        // global cada vez que sea heredada. Pero es solo implscita en el sentido que es una espera global. Una espera implscita pura es la que vemos en [[[*]]] más arriba.
        // <<*>> Esto obviamente nos limita a usar la localizancion mediante XPATH
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
    }

    // ******************** BASIC ACTIONS ********************
    // Como el click es un método extremadamente usado, lo definieroms en esta base Page pasandole como parámetro el
    // webElement que retorna la funcion de arriba.
    public void clickElement(String locator) {
        Find(locator).click();
    }

    public void write(String locator, String textToWrite) {
        Find(locator).clear();
        Find(locator).sendKeys(textToWrite);
    }

    public void selectFromDropDownByValue(String locator, String valueToSelect) {
        Select dropdown = new Select(Find(locator));
        dropdown.selectByValue(valueToSelect);
    }

    public void selectFromDropDownByIndex(String locator, int valueToSelect) {
        Select dropdown = new Select(Find(locator));
        dropdown.selectByIndex(valueToSelect);
    }

    public void selectFromDropDownByText(String locator, String textToSelect) {
        Select dropdown = new Select(Find(locator));
        dropdown.selectByVisibleText(textToSelect);
    }

    public void goToLinkText(String linkText) {
        driver.findElement(By.linkText(linkText)).click();
    }

    // ******************** HOVER - DOUBLE CLICK - RIGHT CLICK ********************
    // Definimos más actions comunes sobre los webelements: es decir, hover over, doble click y click derecho
    public void hoverOverElement(String locator) {
        action.moveToElement(Find(locator));
    }

    public void doubleClick(String locator) {
        action.doubleClick(Find(locator));
    }

    public void rightClick(String locator) {
        action.contextClick(Find(locator));
    }

    // ******************** TABLES ********************
    public String getValueFromTable(String locator, int row, int column) {
        String cellNeed = locator+"/table/tbody/tr["+row+"]/td["+column+"]";
        return Find(cellNeed).getText();
    }

    // Funcionará obviamente para tablas con celdas editables
    public void setValueOnTable(String locator, int row, int column, String stringToSend){
        String cellToFill = locator+"/table/tbody/tr["+row+"]/td["+column+"]";
        Find(cellToFill).sendKeys(stringToSend);
    }

    // ******************** IFRAME AND ALERTS ********************
    public void switchToiFram(int iFrameIndex){
        driver.switchTo().frame(iFrameIndex);
    }

    public void switchToParentFrame(){
        driver.switchTo().parentFrame();
    }

    public void dismissAlert(){
        driver.switchTo().alert().dismiss();
    }

    // ******************* RETURNING DATA FOR ASSERTIONS ********************

    // Esta funcion devuelve un texto y tiene como finalidad definir aqui solamente esa responsabilidad de devolver el texto
    // para que los assertions no esten aqui. Tampoco es buena práctica que los asserts esten en la pagina del POM ya que ellas
    // solo deben ser utilizadas para modelar la pagina y no para hacer validaciones. Lo idea es que los asserts vayan en los step definitions.

    // Para validar text de un elemento
    public String textFromElement(String locator){
        return Find(locator).getText();
    }

    // Para validar si un elemento es visible o no
    public boolean elementIsDisplayed(String locator){
        return Find(locator).isDisplayed();
    }

    public boolean elementIsSelected(String locator){
        return Find(locator).isSelected();
    }

    public boolean elementIsEnabled(String locator){
        return Find(locator).isEnabled();
    }

    // ******************* LISTS ********************
    public List<WebElement> bringMeAllElements(String locator){
        return driver.findElements(By.className(locator));
    }

}
