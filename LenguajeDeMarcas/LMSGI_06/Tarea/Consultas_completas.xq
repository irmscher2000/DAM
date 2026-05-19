(: 1 Nombre, apellidos y país de todos los autores, en el siguiente formato:)
for $x in doc("autorespoliciacos.xml")//autor
return
  <autor>
    <nombre>{$x/nombre/text()}</nombre>
    <apellido>{$x/apellido/text()}</apellido>
    <pais>{$x/pais/text()}</pais>
  </autor>

(: 2 Nombre y apellidos (sin etiquetas) de los autores que nacieron antes de 1961:)
for $x in doc("autorespoliciacos.xml")//autor
where $x/nacimiento < 1961
return concat($x/nombre, ' ', $x/apellido)

(: 3 El número de autores extranjeros,aquellos cuyo país no es España. :)
let $y := doc("autorespoliciacos.xml")//autor[pais != 'España']
return <num_autores_extranjeros>{count($y)}</num_autores_extranjeros>

(:4 Nombre y apellidos de las autoras policiacas mujeres:)
for $x in doc("autorespoliciacos.xml")//autor[@sexo = 'M']
return 
<autor>
  <nombre>{data($x/nombre)}</nombre>
  <apellido>{data($x/apellido)}</apellido>
  <pais>{data($x/pais)}</pais>
</autor>

(:5 Código Id e información solo de las autoras mujeres, en el siguiente formato :)
for $x in doc("autorespoliciacos.xml")//autor[@sexo = 'M']
return 
  <autor>
    <codigo>{data($x/@id)}</codigo>
    <info>{data($x/@info)}</info>
  </autor>

(: 6 Nombre y apellidos de los autores premiados únicamente con el "Premio Planeta":)
for $x in doc("autorespoliciacos.xml")//autor
where $x/premios = "Premio Planeta"
return concat($x/nombre, " ", $x/apellido)

(: 7 Nombre y apellidos de los autores que tienen más de una afición en el siguiente formato:)
for $x in doc("autorespoliciacos.xml")//autor
let $numAficiones := count($x/aficion)
where $numAficiones > 1 
return <autor>{concat($x/nombre, " ", $x/apellido, " - ", $numAficiones, " aficiones")}</autor>

(: 8 Nombre, apellidos y edad de los autores en el siguiente formato:)
for $x in doc("autorespoliciacos.xml")//autor
let $edad := 2025 - number($x/nacimiento)
return
  <autor>
    <nombre>{data($x/nombre)}</nombre>
    <apellidos>{data($x/apellido)}</apellidos>
    <edad>{"Edad:" ||$edad}</edad>
  </autor>

(: 9 Nombre, apellidos y edad de los autores hombres mayores de 50 años en el siguiente formato: (XQuery FLWOR + HTML):)
(: Guardo el ejecutao la consulta y guardo el resultado como Consulta09.html:)
let $year := 2025
return 
  <html>
    <head>
      <title>Eugen Moga</title>
    </head>
    <body>
      <h1>Autores mayores de 50:</h1>
      <ol>
        {
          for $x in doc("autorespoliciacos.xml")//autor[@sexo='H']
          let $edad := $year - number($x/nacimiento)
          where $edad > 50
          return
            <li>
              {data($x/nombre) || " " || data($x/apellido)} - {string($edad)} años
            </li>
        }
      </ol>
    </body>
  </html>

(:10  Nombre, país y obra destacada de los autores extranjeros ordenados por apellido en formato: (XQuery FLWOR + HTML)
En BaseX obtendrás el resultado que se podrá visualizar en html de la siguiente forma en un navegador:)
let $year := 2025 (: Si no inicializo la variable antes del return no funciona la sentencia asi que le he dejalo la variable de la consulta 9:)
return 
  <html>
    <head>
      <title>Eugen Moga</title>
    </head>
    <body>
      <h1>Autores Extranjeros</h1>
      <table border="1">
        <tr>
          <th>Nombre</th>
          <th>País</th>
          <th>Obra Destacada</th>
        </tr>
        {
          for $x in doc("autorespoliciacos.xml")//autor
          let $pais := $x/pais
          where $pais != 'España'
          order by $x/apellido
          return 
            <tr>
              <td>{data($x/nombre) || " " || data($x/apellido)}</td>
              <td>{data($pais)}</td>
              <td>{data($x/obra_destacada)}</td>
            </tr>
        }
      </table>
    </body>
  </html>
