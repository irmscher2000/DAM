(: 8 Nombre, apellidos y edad de los autores en el siguiente formato:)
for $x in doc("autorespoliciacos.xml")//autor
let $edad := 2025 - number($x/nacimiento)
return
  <autor>
    <nombre>{data($x/nombre)}</nombre>
    <apellidos>{data($x/apellido)}</apellidos>
    <edad>{$edad}</edad>
  </autor>