package Definitions;

import PageObjects.ClicksPage;
import PageObjects.DatosPage;
import PageObjects.SelectPage;
import Support.DriverFactory;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.openqa.selenium.WebDriver;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class FormularioDefinitions {

    private DatosPage datos;
    private ClicksPage cliks;
    private SelectPage select;

    String CSV_File_Path = "src/test/resources/data/data.csv";

    @Before(order = 1)
    public void init() {
        WebDriver driver = DriverFactory.getDriver();

        datos = new DatosPage(driver);
        cliks = new ClicksPage(driver);
        select = new SelectPage(driver);
    }

    @Given("El alumno ingresa al formulario de novus")
    public void elAlumnoIngresaAlFormularioDeNovus() {
        DriverFactory.getDriver().get("https://novustechnology.pe/practice-form/");
    }

    @When("Ingreso el nombre del alumno")
    public void ingresoElNombreDelAlumno() {
        datos.IngresarNombre();
    }

    @And("Ingreso el apellido del alumno")
    public void ingresoElApellidoDelAlumno() {
        datos.IngresarApellido();
    }

    @And("selecciono pasatiempo")
    public void seleccionoPasatiempo() {
        cliks.ClickDeporte();
        cliks.ClickLectura();
    }

    @And("doy click en genero")
    public void doyClickEnGenero() {
        cliks.ClickMasculino();
    }

    @And("ingreso numero de telefono")
    public void ingresoNumeroDeTelefono() {
        datos.IngresarTelefono();
    }

    @And("ingreso correo electronico")
    public void ingresoCorreoElectronico() {
        datos.IngresarCorreo();
    }

    @And("selecciono departamento")
    public void seleccionoDepartamento() {
        select.selectDepartamento();
    }

    @And("selecciono provincia")
    public void seleccionoProvincia() {
        select.selectCiudad();
    }

    @And("seleciono comando")
    public void selecionoComando() {
        select.selectComando();
    }

    @Then("doy click en enviar")
    public void doyClickEnEnviar() {
        datos.BtnEnviar();
    }

    @And("visualizo datos de popup")
    public void visualizoDatosDePopup() {
        datos.CapturaDatos();
    }

    @And("doy click en cerrar")
    public void doyClickEnCerrar() {
        datos.BtnClose();
    }

    @When("ingreso mis datos")
    public void ingresoMisDatos() {
        datos.IngresarNombre();
        datos.IngresarApellido();
        cliks.ClickLectura();
        cliks.ClickDeporte();
        cliks.ClickMasculino();
        datos.IngresarTelefono();
        datos.IngresarCorreo();
        select.selectDepartamento();
        select.selectCiudad();
        select.selectComando();
    }


    @When("da click en el boton alerta")
    public void daClickEnElBotonAlerta() {
         cliks.clickAlerta();
    }

    @Then("aparece un mensaje de alerta para aceptar")
    public void apareceUnMensajeDeAlertaParaAceptar() {
        cliks.aceptarAlerta();
    }

    @And("me dirige a la pantalla principal")
    public void meDirigeALaPantallaPrincipal() {
        datos.CambiarVentana();
        datos.validarTextoPantallaPrincipal();
        datos.cerrarPestana();
    }

    @When("Ingreso datos del usuario")
    public void ingresoDatosDelUsuario(DataTable datosform) {
        List<Map<String,String >> lista=datosform.asMaps(String.class,String.class);
        for (int i=0; i<lista.size();i++){
            datos.IngresarNombre(lista.get(i).get("nombre"));
            datos.IngresarApellido(lista.get(i).get("apellido"));
            cliks.ClickPasatiempo(lista.get(i).get("pasatiempo"));
            cliks.clickGenero(lista.get(i).get("genero"));
            datos.IngresarTelefono(lista.get(i).get("telefono"));
            datos.IngresarCorreo(lista.get(i).get("correo"));
            select.selectDepartamento(lista.get(i).get("departamento"));
            select.selectCiudad(lista.get(i).get("ciudad"));
            select.selectComando(lista.get(i).get("comando"));

        }


    }

    @And("visualizo datos datable")
    public void visualizoDatosDatable(DataTable datosForm2) {
        List<Map<String,String >> lista=datosForm2.asMaps(String.class,String.class);
        for (int i=0; i<lista.size();i++){
            datos.ValidarNom(lista.get(i).get("nombre"));
            datos.valdarApelli(lista.get(i).get("apellido"));
        }
        datos.BtnClose();
    }

    @When("ingresa datos en el campos de texto {string} , {string} , {string} , {string}")
    public void ingresaDatosEnElCamposDeTexto(String nombre, String apellido, String telefono, String correo) {
        datos.IngresarNombre(nombre);
        datos.IngresarApellido(apellido);
        datos.IngresarTelefono(telefono);
        datos.IngresarCorreo(correo);
    }

    @And("ingresa usamos todos los campos seleccionables {string}, {string} , {string}, {string} , {string}")
    public void ingresaUsamosTodosLosCamposSeleccionables(String pasatiempo, String genero, String departamento, String ciudad, String comando) {
        cliks.ClickPasatiempo(pasatiempo);
        cliks.clickGenero(genero);
        select.selectDepartamento(departamento);
        select.selectCiudad(ciudad);
        select.selectComando(comando);
    }

    @And("validamos los datos {string} , {string}")
    public void validamosLosDatos(String nombre, String apellido) {
        datos.ValidarNom(nombre);
        datos.valdarApelli(apellido);
    }

    @When("ingresamos al CSV")
    public void ingresamosAlCSV() throws IOException {
        BufferedReader reader= Files.newBufferedReader(Paths.get(CSV_File_Path));
        CSVFormat formato= CSVFormat.DEFAULT.
                withHeader("nombre","apellido","pasatiempo","genero","telefono","correo","departamento","ciudad","comando") //nombre de toda la cabecera del CSV
                .withSkipHeaderRecord() //Ignorar la cabecera de todo el CSV
                .withTrim(); //Limitar los espacios

        Iterable<CSVRecord> records=formato.parse(reader);

        for (CSVRecord fila:records){
            datos.IngresarNombre(fila.get("nombre"));
            datos.IngresarApellido(fila.get("apellido"));
            cliks.ClickPasatiempo(fila.get("pasatiempo"));
            cliks.clickGenero(fila.get("genero"));
            datos.IngresarTelefono(fila.get("telefono"));
            datos.IngresarCorreo(fila.get("correo"));
            select.selectDepartamento(fila.get("departamento"));
            select.selectCiudad(fila.get("ciudad"));
            select.selectComando(fila.get("comando"));
        }
    }

    @And("validamos los datos del csv")
    public void validamosLosDatosDelCsv() throws IOException {
            BufferedReader reader = Files.newBufferedReader(Paths.get(CSV_File_Path));
            CSVFormat formato = CSVFormat.DEFAULT
                    .withHeader("nombre","apellido")
                    .withSkipHeaderRecord()   // ignora la primera fila del header
                    .withTrim();              // limpia espacios

            Iterable<CSVRecord> records = formato.parse(reader);

            for (CSVRecord fila : records) {
                datos.ValidarNom(fila.get("nombre"));
                datos.valdarApelli(fila.get("apellido"));
            }
    }
}
