package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DatosPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Constructor
    public DatosPage(WebDriver d) {
        this.driver = d;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    /* =========================
       ELEMENTOS ESTABLES
       ========================= */

    @FindBy(id = "preloader")
    private WebElement loading;

    @FindBy(id = "nombre")
    private WebElement nombre;

    @FindBy(id = "apellido")
    private WebElement apellido;

    @FindBy(name = "mobile")
    private WebElement telefono;

    @FindBy(id = "email")
    private WebElement correo;

    @FindBy(xpath = "//*[@id='registroForm']/div[6]/div/button")
    private WebElement btnEnviar;

    @FindBy(id = "nombreModal")
    private WebElement nombreCaptura;

    @FindBy(id = "apellidoModal")
    private WebElement apellidoCaptura;

    @FindBy(xpath = "//*[contains(text(),'Close')]")
    private WebElement btnClose;

    /* =========================
       INGRESO DE DATOS (FIJOS)
       ========================= */

    public void IngresarNombre() {
        wait.until(ExpectedConditions.invisibilityOf(loading));
        nombre.sendKeys("Jorge Erickson");
    }

    public void IngresarApellido() {
        apellido.sendKeys("Paz Diaz");
    }

    public void IngresarTelefono() {
        telefono.sendKeys("935404484");
    }

    public void IngresarCorreo() {
        correo.sendKeys("jorge@test.com");
    }

    /* =========================
       INGRESO DE DATOS (DINÁMICOS)
       ========================= */

    public void IngresarNombre(String campoNom) {
        wait.until(ExpectedConditions.invisibilityOf(loading));
        wait.until(ExpectedConditions.visibilityOf(nombre));
        nombre.clear();
        nombre.sendKeys(campoNom);
    }

    public void IngresarApellido(String campoApe) {
        wait.until(ExpectedConditions.visibilityOf(apellido));
        apellido.clear();
        apellido.sendKeys(campoApe);
    }

    public void IngresarTelefono(String campoTelefono) {
        wait.until(ExpectedConditions.visibilityOf(telefono));
        telefono.clear();
        telefono.sendKeys(campoTelefono);
    }

    public void IngresarCorreo(String mail) {
        wait.until(ExpectedConditions.visibilityOf(correo));
        correo.clear();
        correo.sendKeys(mail);
    }

    /* =========================
       ACCIONES
       ========================= */

    public void BtnEnviar() {
        wait.until(ExpectedConditions.elementToBeClickable(btnEnviar));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btnEnviar);
    }

    public void BtnClose() {
        wait.until(ExpectedConditions.elementToBeClickable(btnClose));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btnClose);
    }

    /* =========================
       VALIDACIONES
       ========================= */

    public void CapturaDatos() {
        wait.until(ExpectedConditions.visibilityOf(nombreCaptura));
        wait.until(ExpectedConditions.visibilityOf(apellidoCaptura));
    }

    public void ValidarNom(String nombreEsperado) {
        wait.until(ExpectedConditions.visibilityOf(nombreCaptura));
        String capturaNombre = nombreCaptura.getText();
        assertEquals(nombreEsperado, capturaNombre,
                "El nombre mostrado no coincide");
    }

    public void valdarApelli(String apellidoEsperado) {
        wait.until(ExpectedConditions.visibilityOf(apellidoCaptura));
        String capturaApellido = apellidoCaptura.getText();
        assertEquals(apellidoEsperado, capturaApellido,
                "El apellido mostrado no coincide");
    }

    /**
     * VALIDACIÓN CORREGIDA PARA EVITAR STALE ELEMENT
     */
    public void validarTextoPantallaPrincipal() {

        By textoPrincipalLocator = By.xpath(
                "//*[@id='page-content']/section[1]/div[4]/div/div/div/div/div[2]/div/h4"
        );

        WebElement texto = wait.until(
                ExpectedConditions.visibilityOfElementLocated(textoPrincipalLocator)
        );

        texto.isDisplayed();
    }

    /* =========================
       MANEJO DE VENTANAS
       ========================= */

    public void CambiarVentana() {
        for (String ventana : driver.getWindowHandles()) {
            driver.switchTo().window(ventana);
        }
    }

    public void cerrarPestana() {
        driver.close();
        for (String ventana : driver.getWindowHandles()) {
            driver.switchTo().window(ventana);
            break;
        }
    }
}
