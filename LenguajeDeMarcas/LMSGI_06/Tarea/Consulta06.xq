(: 6 Nombre y apellidos de los autores premiados únicamente con el "Premio Planeta":)
for $x in doc("autorespoliciacos.xml")//autor
where $x/premios = "Premio Planeta"
return concat($x/nombre, " ", $x/apellido)