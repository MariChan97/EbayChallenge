package pages;

public class GooglePage extends BasePage {

    //private String searchButton = "(//div/center/input[@name='btnK'])[2]";
    private String searchButton = "//body/div[1]/div[3]/form[1]/div[1]/div[1]/div[2]/div[2]/div[5]/center[1]/input[1]";
    private String searchTextField = "//input[contains(@class,'gLFyf gsfi')]";
    private String firstResult = "//h3[contains(text(),'Argentina.gob.ar: Inicio')]";

    public GooglePage() {

        super(driver);
    }

    public void navigateToGoogle() {

        navigateTo("https://www.google.com/");
    }

    public void clickGoogleSearch() {
        super.clickElement(searchButton);
    }

    public void enterSearchCriteria(String criteria){
        write(searchTextField, "Argentina");
    }

    public String firstResult(){
        return textFromElement(firstResult);
    }
}
