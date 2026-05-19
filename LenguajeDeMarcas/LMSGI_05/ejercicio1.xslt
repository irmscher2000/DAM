<?xml version="1.0" encoding="ISO-8859-1"?>

<!--
    Document   : ejercicio1
    Created on : 19 de febrero de 2025, 11:39
    Author     : Eugen Moga
    Description:
-->

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html" encoding="UTF-8" indent="yes"/>

    <!-- Plantilla Principal -->
    <xsl:template match="/">
        <html>
            <head>
                <title>Listado de autores de Novela Negra</title>
                <style>
                    body {
                        font-family: Calibri, sans-serif; 
                    }
                    table {
                        width: 95%; 
                        margin: 10px auto;
                        border-collapse: collapse;
                        outline: 2px solid black;
                        border:2px solid white;
                    }           
                    th, td {
                        padding: 10px;
                        text-align: left;
                        border: 2px solid white;
                    }
                    th {
                        background-color: black;
                        color: white
                    }
                    tr:nth-child(even) {
                        background-color: #f2f2f2;
                    }
                    a { 
                        text-decartion: none; 
                        color: blue; 
                    }
                    a:hover {
                        text-decoration: underline;
                    }
                    table.extranjeros {
                        font-family: Arial, sans-serif;
                    }
                </style>
            </head>
            <body>
                <h1>Listado de autores de Novela Negra</h1>

                <!-- Tabla de autores españoles -->
                <table>
                    <tr>
                        <th colspan="8" style="text-align: center;"><h2>AUTORES DE NOVELA NEGRA ESPAÑOLES</h2></th>
                    </tr>
                    <tr>
                        <th>Código</th>
                        <th>Nombre</th>
                        <th>Apellidos</th>
                        <th>Año de Nacimiento</th>
                        <th>Paí­s</th>
                        <th>Obra Destacada</th>
                        <th>Premios</th>
                        <th>Más info</th>
                    </tr>
                    <xsl:for-each select="autorespoliciacos/autor[pais='España']">
                        <xsl:sort select="apellido"/>
                        <tr>
                            <td><xsl:value-of select="@id"/></td>
                            <td>
                                <xsl:choose>
                                    <xsl:when test="nombre">
                                        <xsl:value-of select="nombre"/>
                                    </xsl:when>
                                    <xsl:otherwise>se desconoce</xsl:otherwise>
                                </xsl:choose>
                            </td>
                            <td>
                                <xsl:choose>
                                    <xsl:when test="apellido">
                                        <xsl:value-of select="apellido"/>
                                    </xsl:when>
                                    <xsl:otherwise>se desconoce</xsl:otherwise>
                                </xsl:choose>
                            </td>
                            <td>
                                <xsl:choose>
                                    <xsl:when test="nacimiento">
                                        <xsl:value-of select="nacimiento"/>
                                    </xsl:when>
                                    <xsl:otherwise>se desconoce</xsl:otherwise>
                                </xsl:choose>
                            </td>
                            <td>
                                <xsl:choose>
                                    <xsl:when test="pais">
                                        <xsl:value-of select="pais"/>
                                    </xsl:when>
                                    <xsl:otherwise>se desconoce</xsl:otherwise>
                                </xsl:choose>
                            </td>
                            <td>
                                <xsl:choose>
                                    <xsl:when test="obra_destacada">
                                        <xsl:value-of select="obra_destacada"/>
                                    </xsl:when>
                                    <xsl:otherwise>se desconoce</xsl:otherwise>
                                </xsl:choose>
                            </td>
                            <td>
                                <xsl:choose>
                                    <xsl:when test="premios">
                                        <xsl:value-of select="premios"/>
                                    </xsl:when>
                                    <xsl:otherwise>se desconoce</xsl:otherwise>
                                </xsl:choose>
                            </td>
                            <td>
                                <xsl:choose>
                                    <xsl:when test="normalize-space(@info)">
                                        <a href="{@info}" target="_blank">-+-</a>
                                    </xsl:when>
                                    <xsl:otherwise>se desconoce</xsl:otherwise>
                                </xsl:choose>
                            </td>
                        </tr>
                    </xsl:for-each>
                </table>
                
                <!-- Tabla de autores extrangeros -->
                <table class="extranjeros">
                    <tr>
                        <th colspan="8" style="text-align: center;"><h2>AUTORES DE NOVELA NEGRA EXTRANJEROS</h2></th>
                    </tr>
                    <tr>
                        <th>Código</th>
                        <th>Nombre</th>
                        <th>Apellidos</th>
                        <th>Año de Nacimiento</th>
                        <th>Paí­s</th>
                        <th>Obra Destacada</th>
                        <th>Premios</th>
                        <th>Más info</th>
                    </tr>
                    <xsl:for-each select="autorespoliciacos/autor[pais!='España']">
                        <xsl:sort select="apellido"/>
                        <tr>
                            <td><xsl:value-of select="@id"/></td>
                            <td><xsl:value-of select="nombre"/></td>
                            <td><xsl:value-of select="apellido"/></td>
                            <td><xsl:value-of select="nacimiento"/></td>
                            <td><xsl:value-of select="pais"/></td>
                            <td><xsl:value-of select="obra_destacada"/></td>
                            <td><xsl:value-of select="premios"/></td>
                            <td>
                                <a href="{@info}" target="_blank">-+-</a>
                            </td>
                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>