<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : ejercicio2.xslt
    Created on : 20 de febrero de 2025, 0:06
    Author     : Eugen Moga
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html"/>

    <xsl:template match="/">
        <html>
            <head>
                <title>Lista de Autoras de Novela Negra</title>
                <style>
                    h1 {
                        font-family: Arial, sans-serif;
                        color: blue;
                        margin-left: 20px;
                    }
                    p {
                        color: black;
                        font-size: 35px;
                    }
                    body {
                        font-family: Arial, sans-serif;
                    }
                    a { 
                        color: blue; 
                    }
                    li {
                        margin-bottom: 10px;
                        padding-bottom: 5px;
                    }
                    li:not(:last-child) {
                        border-bottom: 1px solid #ccc;
                    }  
                </style>
            </head>
            
            <body>
                <b><p>Lista de Autoras de Novela Negra</p></b>
                <h1>Autoras Femeninas de novela negra nacidas antes de 1975</h1>
                <ul>
                    <xsl:for-each select="autorespoliciacos/autor[@sexo='M' and nacimiento &lt; 1975]">
                        <li>
                            <b><xsl:value-of select="nombre"/></b>
                            <xsl:text> </xsl:text>
                            <b><xsl:value-of select="apellido"/></b>
                            <xsl:text>, Año de nacimiento: </xsl:text>
                            <xsl:value-of select="nacimiento"/>
                            <xsl:text>, Edad actual: </xsl:text>
                            <xsl:value-of select="2025 - nacimiento"/>,
                            <a href="{@info}" target="_blank" style="text-decoration: none;">información adicional</a>
                        </li>
                    </xsl:for-each>
                </ul>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>