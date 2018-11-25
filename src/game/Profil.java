/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.w3c.dom.Document;
import xml.XMLUtil;

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
        this.dateNaissance = dateNaissance;
    }
    
    // Cree un DOM à partir d'un fichier XML
    public Profil(String nomFichier) {
        Partie p;
        _doc = fromXML(nomFichier);
        this.nom = _doc.getElementsByTagName("nom").item(0).getTextContent();
        this.avatar = _doc.getElementsByTagName("avatar").item(0).getTextContent();
        this.dateNaissance = _doc.getElementsByTagName("anniversaire").item(0).getTextContent();

        for (int i = 0; i < _doc.getElementsByTagName("partie").getLength(); i++) {
            String date = _doc.getElementsByTagName("partie").item(i).getAttributes().item(0).getTextContent();
            String trouve = _doc.getElementsByTagName("partie").item(i).getAttributes().item(1).getTextContent();
            String temps = _doc.getElementsByTagName("partie").item(i).getChildNodes().item(0).getTextContent();
            String mot = _doc.getElementsByTagName("partie").item(i).getChildNodes().item(1).getTextContent();
            String niveau = _doc.getElementsByTagName("partie").item(i).getAttributes().item(0).getTextContent();

            p = new Partie(xmlDateToProfileDate(date), mot, Integer.parseInt(niveau));
            p.setTemps(Integer.parseInt(temps));
            p.setTrouve(Integer.parseInt(trouve));

            parties.add(p);
        }
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
        parties.add(p);
    }
    
    public int getDernierNiveau(){
        return parties.get(parties.size() - 1).getNiveau();
    }
    
    public String toString(){
        return "";
    }
    
    public void sauvegarder(String filename){
        //ecrire les informations dans un doc dom et appeler toXml()
        _doc.getElementsByTagName("nom").item(0).setTextContent(this.nom);
        _doc.getElementsByTagName("avatar").item(0).setTextContent(this.avatar);
        _doc.getElementsByTagName("anniversaire").item(0).setTextContent(this.dateNaissance);

        for (Partie p : parties) {
            _doc.getElementsByTagName("partie").item(0).getAttributes().item(0).setTextContent(profileDateToXmlDate(p.getDate()));
            _doc.getElementsByTagName("partie").item(0).getAttributes().item(1).setTextContent(p.getTrouvé() + "%");
            _doc.getElementsByTagName("partie").item(0).getChildNodes().item(0).setTextContent("" + p.getTemps());
            _doc.getElementsByTagName("partie").item(0).getChildNodes().item(1).setTextContent(p.getMot());
            _doc.getElementsByTagName("partie").item(0).getAttributes().item(0).setTextContent("" + p.getNiveau());
        }

        toXML(filename);
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
