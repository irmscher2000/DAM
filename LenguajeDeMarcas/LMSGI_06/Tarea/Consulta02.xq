(: 2 Nombre y apellidos (sin etiquetas) de los autores que nacieron antes de 1961:)
for $x in doc("autorespoliciacos.xml")//autor
where $x/nacimiento < 1961
return concat($x/nombre, ' ', $x/apellido)