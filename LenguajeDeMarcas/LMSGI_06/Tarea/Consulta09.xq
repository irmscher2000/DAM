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
