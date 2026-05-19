(:4 Nombre y apellidos de las autoras policiacas mujeres:)
for $x in doc("autorespoliciacos.xml")//autor[@sexo = 'M']
return 
<autor>
  <nombre>{data($x/nombre)}</nombre>
  <apellido>{data($x/apellido)}</apellido>
  <pais>{data($x/pais)}</pais>
</autor>