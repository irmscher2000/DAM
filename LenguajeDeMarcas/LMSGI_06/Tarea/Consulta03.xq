(: 3 El número de autores extranjeros,aquellos cuyo país no es España. :)
let $y := doc("autorespoliciacos.xml")//autor[pais != 'España']
return <num_autores_extranjeros>{count($y)}</num_autores_extranjeros>