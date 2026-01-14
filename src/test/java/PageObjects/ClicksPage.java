package PageObjects;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ClicksPage {
    //declarar variables driver y wait para usarla en toda la clase.
    private WebDriver driver;
    private WebDriverWait wait;

    //constructor
    public ClicksPage(WebDriver d) {
        //instanciar el driver y el wait
        driver=d;
        wait= new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver,this);
    }

    @FindBy (id = "preloader") private WebElement Loading;
    @FindBy (id = "sports") private WebElement deporte;
    @FindBy (id = "reading") private WebElement lectura;
    @FindBy (id = "music") private WebElement musica;
    @FindBy(id = "male") private WebElement masculino;
    @FindBy(id = "female") private WebElement femenino;
    @FindBy(id = "other") private WebElement otro;
    @FindBy(xpath = "//*[@id=\"mostrarAlerta\"]") private WebElement clickAlerta;


    public void ClickDeporte(){
        deporte.click();
    }
    public void ClickLectura(){
        lectura.click();
    }
    public void ClickMusica(){
        musica.click();
    }
    public void ClickMasculino(){
        masculino.click();
    }
    public void ClickFemenino(){ femenino.click();}
    public void ClickOtro(){
        otro.click();
    }

    //datos con Datable
    public void ClickPasatiempo(String pasatiempo){
        System.out.println("El deporte ingresado es: " + pasatiempo);
        if (pasatiempo.equals("deporte")) {
            deporte.click();
        } else if (pasatiempo.equals("lectura")) {

            lectura.click();
        } else {
            musica.click();
        }
    }

    public void clickGenero(String Genero){
        if (Genero.equals("Masculino")) {
            masculino.click();
        } else if (Genero.equals("Femenino")) {
            femenino.click();
        } else {
            otro.click();
        }
    }


    public void clickAlerta(){
        wait.until(ExpectedConditions.invisibilityOf(Loading));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();",clickAlerta);

    }

    public void aceptarAlerta(){
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alerta= driver.switchTo().alert();
        alerta.accept();
    }

}
