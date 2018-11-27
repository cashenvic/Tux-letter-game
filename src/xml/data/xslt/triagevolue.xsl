<?xml version="1.0" encoding="UTF-8"?>
<!--
    trie les mots en fonction de leur niveau
-->
<xsl:stylesheet 
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
    targetNamespace="http://myGame/tux"
    xmlns:pref="http://myGame/tux"
    version="1.0">    
    <xsl:output method="html" />
    <xsl:template match="/">
        <html>
            <head>
                <title>Dictionnaire</title>
            </head>
            <body>
                <h1> Les mots triés </h1>
                <br/>
                <xsl:apply-templates select="/pref:dictionnaire/pref:mot"> 
                    <xsl:sort select="@niveau" order="ascending"/>
                    <xsl:sort select="text()" order="ascending"/>  
                </xsl:apply-templates> 
            </body>
        </html>
    </xsl:template>
    
     <xsl:template match="pref:mot">
        <h4>Niveau <xsl:value-of select="@niveau"/> : </h4>
        <ul><li><xsl:value-of select="text()"/></li> </ul>     
    </xsl:template>    
</xsl:stylesheet>


    
   