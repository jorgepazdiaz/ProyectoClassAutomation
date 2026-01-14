Feature: Registrar Formulario de Novus
  Como alumno del curso de Automatizacion
  Quiero registrar el formulacion
  Para poder practicar lo que aprendi.


  @Prueba1
  Scenario: 001 - Un alumno ingresa los datos al formulario
    Given El alumno ingresa al formulario de novus
    When Ingreso el nombre del alumno
    And Ingreso el apellido del alumno
    And selecciono pasatiempo
    And doy click en genero
    And ingreso numero de telefono
    And ingreso correo electronico
    And selecciono departamento
    And selecciono provincia
    And seleciono comando
    Then doy click en enviar
    And visualizo datos de popup
    And doy click en cerrar

  @Prueba1
  Scenario: 002 Validar registro de datos en el formulario
    Given  El alumno ingresa al formulario de novus
    When ingreso mis datos
    And doy click en enviar
    Then visualizo datos de popup
    And doy click en cerrar

  @Prueba1
  Scenario: 003. Validar prueba con mensaje de Alerta
    Given  El alumno ingresa al formulario de novus
    When da click en el boton alerta
    Then aparece un mensaje de alerta para aceptar
    And me dirige a la pantalla principal

  @Prueba1
  Scenario: 004 - Un alumno ingresa los datos al formulario
    Given El alumno ingresa al formulario de novus
    When Ingreso datos del usuario
      | nombre | apellido | pasatiempo | genero    | telefono | correo        | departamento | ciudad | comando         |
      | Jorge  | Paz      | deporte    | Masculino | 55555555 | jpaz@test.com | LIMA         | LIMA   | Switch Commands |
    Then doy click en enviar
    And visualizo datos datable
      | nombre | apellido |
      | Jorge  | Paz      |
    And doy click en cerrar


  @Prueba1
  Scenario Outline: 005 validar prueba con varios registros¡
    Given El alumno ingresa al formulario de novus
    When ingresa datos en el campos de texto "<nombre>" , "<apellido>" , "<telefono>" , "<correo>"
    And ingresa usamos todos los campos seleccionables "<pasatiempo>", "<genero>" , "<departamento>", "<ciudad>" , "<comandos>"
    Then doy click en enviar
    And validamos los datos "<nombre>" , "<apellido>"
    And doy click en cerrar
    Examples:
      | nombre      | apellido   | pasatiempo | genero    | telefono   | correo                | departamento | ciudad | comandos            |
      | Jorge       | Paz        | Musica     | Masculino | 935404484  | jorgepazdiaz@test.com | LIMA         | LIMA   | Switch Commands     |
      | Juan Carlos | Llerena    | Lectura    | Masculino | 9447878888 | juacarlos@test.com    | LIMA         | CAÑETE | Navigation Commands |
      | Joan        | Serrato    | Deporte    | Masculino | 955533333  | joan@test.com         | PIURA        | PIURA  | Navigation Commands |
      | Carolina    | Meza       | Lectura    | Femenino  | 913233456  | carolina@test.com     | LIMA         | LIMA   | Browser Commands    |
      | Yesica      | Estrada    | Deporte    | Femenino  | 935404484  | yesica@test.com       | LIMA         | LIMA   | Wait Commands       |
      | Madeleine   | Valenzuela | Musica     | Femenino  | 935404484  | madeleine@test.com    | LIMA         | LIMA   | WebElement Commands |


  @testsolo
  Scenario: 006 probando usuario con ingreso de data desde CSV
    Given El alumno ingresa al formulario de novus
    When ingresamos al CSV
    Then doy click en enviar
    And validamos los datos del csv
    And doy click en cerrar

