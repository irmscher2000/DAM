(: 7 Nombre y apellidos de los autores que tienen más de una afición en el siguiente formato:)
for $x in doc("autorespoliciacos.xml")//autor
let $numAficiones := count($x/aficion)
where $numAficiones > 1 
return <autor>{concat($x/nombre, " ", $x/apellido, " - ", $numAficiones, " aficiones")}</autor>