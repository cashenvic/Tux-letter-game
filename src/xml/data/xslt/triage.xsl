<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet 
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
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
                <ul>
                    <xsl:apply-templates select="/pref:dictionnaire/pref:mot">
                        <xsl:sort select="text()" order="ascending"/>   
                    </xsl:apply-templates>
                </ul>            
            </body>
        </html>
    </xsl:template>
    <xsl:template match="pref:mot">
        <li> 
            <xsl:value-of select="text()"/>  
        </li>
    </xsl:template>
    
</xsl:stylesheet>