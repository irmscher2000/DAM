(: 1 Nombre, apellidos y país de todos los autores, en el siguiente formato:)
for $x in doc("autorespoliciacos.xml")//autor
return
  <autor>
    <nombre>{$x/nombre/text()}</nombre>
    <apellido>{$x/apellido/text()}</apellido>
    <pais>{$x/pais/text()}</pais>
  </autor>
