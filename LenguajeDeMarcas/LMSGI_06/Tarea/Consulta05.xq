(:5 Código Id e información solo de las autoras mujeres, en el siguiente formato :)
for $x in doc("autorespoliciacos.xml")//autor[@sexo = 'M']
return 
  <autor>
    <codigo>{data($x/@id)}</codigo>
    <info>{data($x/@info)}</info>
  </autor>