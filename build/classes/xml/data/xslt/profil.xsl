<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : profil.xsl
    Created on : October 21, 2018, 4:55 PM
    Author     : hanh1
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet 
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
    xmlns:pref="http://myGame/tux"
    version="1.0">
    <xsl:output method="html"/>
    <xsl:template match="/">
        <html>
            <head>
                <title> </title>
            </head>
            <body>
                <table style="width:100%">
                    <tr>
                        <td><h1> <xsl:value-of select="//pref:nom"/> </h1></td>                        
                        <xsl:variable name="photo" select="//pref:avatar"/>
                        <td> <img  src = "$photo" width="100" height="100" /></td>    
                    </tr>
                    <tr>
                        <td><h3> Née le <xsl:value-of select="//pref:anniversaire"/> </h3></td>
                    </tr>
                </table>
                <br/><br/>
                <table style="width:100%" border="1">
                    <tr>
                        <th>Partie</th>
                        <th>Date</th> 
                        <th>Temps</th>
                        <th>Mot</th>
                    </tr>
                    <xsl:apply-templates select="//pref:partie"> </xsl:apply-templates>
                </table>
            </body>
        </html>
    </xsl:template>
    
    <xsl:template match="pref:partie">        
        <tr>
            <td> <xsl:value-of select="position()"/> </td>     
            <td> <xsl:value-of select="@date"/></td>    
            <td> <xsl:value-of select="pref:temps"/></td>    
            <td> <xsl:value-of select="pref:mot"/></td>   
        </tr> 
    </xsl:template>
    
    <!--
    <ns1:parties>
        <ns1:partie date="2013-12-10">
            <ns1:temps>20.0</ns1:temps>
            <ns1:mot niveau="1">cle</ns1:mot>
        </ns1:partie>
    </ns1:parties>
    -->

</xsl:stylesheet>
