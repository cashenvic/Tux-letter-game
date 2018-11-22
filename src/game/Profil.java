/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

/**
 *
 * @author dothit
 */
public class Profil {
    private String nom;
    private String dateNaissance;
    private String avatar;    
    private ArrayList<Partie> parties;
    
    public Document _doc;
    
    public Profil(String nom, String dateNaissance){
        this.nom = nom;
        this.dateNaissance =dateNaissance;        
    }
    
    // Cree un DOM à partir d'un fichier XML
    public Profil(String nomFichier){
        _doc = fromXML(nomFichier);
        DOMParser parseur = new DOMParser();
        parseur.parse(nomFichier);
        
        // parsing à compléter
        // ?!#?!    
    }
    
    
    // Sauvegarde un DOM en XML
    private void toXML(String nomFichier) {
        try {
            XMLUtil.DocumentTransform.writeDoc(_doc, nomFichier);
        } catch (Exception ex) {
            Logger.getLogger(Profil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // Cree un DOM à partir d'un fichier XML
    private Document fromXML(String nomFichier) {
        try {
            return XMLUtil.DocumentFactory.fromFile(nomFichier);
        } catch (Exception ex) {
            Logger.getLogger(Profil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
    public void ajouterPartie(Partie p){
        
    }
    
    public int getDernierNiveau(){
        
    }
    
    public String toString(){
        
    }
    
    public void sauvegarder(String filename){
        
    }
    
    /// Takes a date in XML format (i.e. ????-??-??) and returns a date
    /// in profile format: dd/mm/yyyy
    private static String xmlDateToProfileDate(String xmlDate) {
        String date;
        // récupérer le jour
        date = xmlDate.substring(xmlDate.lastIndexOf("-") + 1, xmlDate.length());
        date += "/";
        // récupérer le mois
        date += xmlDate.substring(xmlDate.indexOf("-") + 1, xmlDate.lastIndexOf("-"));
        date += "/";
        // récupérer l'année
        date += xmlDate.substring(0, xmlDate.indexOf("-"));

        return date;
    }
    
    /// Takes a date in profile format: dd/mm/yyyy and returns a date
    /// in XML format (i.e. ????-??-??)
    private static String profileDateToXmlDate(String profileDate) {
        String date;
        // Récupérer l'année
        date = profileDate.substring(profileDate.lastIndexOf("/") + 1, profileDate.length());
        date += "-";
        // Récupérer  le mois
        date += profileDate.substring(profileDate.indexOf("/") + 1, profileDate.lastIndexOf("/"));
        date += "-";
        // Récupérer le jour
        date += profileDate.substring(0, profileDate.indexOf("/"));

        return date;
    }

    boolean charge(String nomJoueur) {
        return false;
    }
            
}
