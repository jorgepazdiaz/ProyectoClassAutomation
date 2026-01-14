package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SelectPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public SelectPage(WebDriver d) {
        driver = d;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "department")
    private WebElement selectDepartamento;

    @FindBy(id = "city")
    private WebElement selectCiudad;

    @FindBy(id = "commands")
    private WebElement selectComando;

    // =========================
    // MÉTODOS FIJOS
    // =========================
    public void selectDepartamento() {
        wait.until(ExpectedConditions.elementToBeClickable(selectDepartamento));
        new Select(selectDepartamento).selectByValue("LIMA");
    }

    public void selectCiudad() {
        wait.until(ExpectedConditions.elementToBeClickable(selectCiudad));
        new Select(selectCiudad).selectByValue("CAÑETE");
    }

    public void selectComando() {
        wait.until(ExpectedConditions.elementToBeClickable(selectComando));
        new Select(selectComando).selectByVisibleText("Switch Commands");
    }

    // =========================
    // MÉTODOS PARAMETRIZADOS
    // =========================
    public void selectDepartamento(String depa) {
        wait.until(ExpectedConditions.elementToBeClickable(selectDepartamento));
        new Select(selectDepartamento).selectByValue(depa);
    }

    public void selectCiudad(String city) {
        wait.until(ExpectedConditions.elementToBeClickable(selectCiudad));
        new Select(selectCiudad).selectByValue(city);
    }

    public void selectComando(String command) {
        wait.until(ExpectedConditions.elementToBeClickable(selectComando));
        new Select(selectComando).selectByVisibleText(command); // ✅ CLAVE
    }
}
