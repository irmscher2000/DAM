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
