/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.w3c.dom.Document;
import xml.XMLUtil;

/**
 *
 * @author dothit
 */
public class mainRoom {
    
    private int depth;
    private int height;
    private int width;
    private String textureBottom;
    private String textureNorth;
    private String textureEast;
    private String textureWest;
    private String textureTop;    
    private String textureSouth;
    public Document _doc;
    public final String filePlateauXML = "src/xml/data/xml/plateau.xml";

    /**
     * Constructeur sans paramètre de la room à partir du document plateau.xml
     */
    public mainRoom(){
        this.depth = 100;
        this.height = 60;
        this.width = 100;
//        this.textureBottom = "textures/floor/grass2.png";
//        this.textureNorth = "textures/skybox/sunny/north.png";
//        this.textureEast = "textures/skybox/sunny/east.png";
//        this.textureWest = "textures/skybox/sunny/west.png";
        loadPlateau();
    }

    /**
     * Charge (parse) le document contenant l'environnement
     */
    private void loadPlateau() {
        _doc = fromXML(filePlateauXML);
        this.textureBottom = _doc.getElementsByTagName("textureBottom").item(0).getTextContent();
        this.textureNorth = _doc.getElementsByTagName("textureNorth").item(0).getTextContent();
        this.textureEast = _doc.getElementsByTagName("textureEast").item(0).getTextContent();
        this.textureWest = _doc.getElementsByTagName("textureWest").item(0).getTextContent();

        this.depth = Integer.parseInt(_doc.getElementsByTagName("depth").item(0).getTextContent());
        this.height = Integer.parseInt(_doc.getElementsByTagName("height").item(0).getTextContent());
        this.width = Integer.parseInt(_doc.getElementsByTagName("width").item(0).getTextContent());
    }

    private Document fromXML(String nomFichier) {
        try {
            return XMLUtil.DocumentFactory.fromFile(nomFichier);
        } catch (Exception ex) {
            Logger.getLogger(Profil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public String getTextureBottom() {
        return textureBottom;
    }

    public void setTextureBottom(String textureBottom) {
        this.textureBottom = textureBottom;
    }

    public String getTextureNorth() {
        return textureNorth;
    }

    public void setTextureNorth(String textureNorth) {
        this.textureNorth = textureNorth;
    }

    public String getTextureEast() {
        return textureEast;
    }

    public void setTextureEast(String textureEast) {
        this.textureEast = textureEast;
    }

    public String getTextureWest() {
        return textureWest;
    }

    public void setTextureWest(String textureWest) {
        this.textureWest = textureWest;
    }

    public String getTextureTop() {
        return textureTop;
    }

    public void setTextureTop(String textureTop) {
        this.textureTop = textureTop;
    }

    public String getTextureSouth() {
        return textureSouth;
    }

    public void setTextureSouth(String textureSouth) {
        this.textureSouth = textureSouth;
    }
    
    
}
