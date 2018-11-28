/*
 *
 * 
 */
package game;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.*;
import xml.XMLUtil;


public class Profil {
    private String nom;
    private String dateNaissance;
    private String avatar;    
    //private ArrayList<Partie> parties;
    public Partie p;
    public Document _doc;
    
    
    //charger profil -> nouvelle partie 1.1
    public Profil(Partie p) {
        //recuperation des données du joueur        
        Element partieActuelle = (Element) p.getPartie(_doc); 
        
    }
    
    public Profil(Partie p, String nom, String dateNaissance){ //2/1
        this.nom = nom;
        this.dateNaissance = dateNaissance;
        //DOM .............
        
        
        //Element partieActuelle = (Element) p.getPartie(_doc); 
        
    }
    
    //menu 1.2
    // Cree un DOM à partir d'un fichier XML -> joueur existant
    //-> utilise le document DOM pour extraire les données nécessaires 
    //à la récupération des valeurs du profil et des parties existantes 
    public Profil(String nomFichier, String nomJoueur, String dateJeu) {
        //recuperation des données du joueur
        _doc = fromXML(nomFichier);        
        int j=0;
        Boolean trouverprofil = false;
        NodeList listprofil = _doc.getElementsByTagName("ns1:profil"); 
        
        while(j< listprofil.getLength() && !trouverprofil){
            Element listeprof = (Element) listprofil.item(j);
            if (listeprof.getElementsByTagName("ns1:nom").equals(nomJoueur)){
                this.nom = listeprof.getElementsByTagName("ns1:nom").item(0).getTextContent();
                this.avatar = _doc.getElementsByTagName("ns1:avatar").item(0).getTextContent();
                this.dateNaissance = _doc.getElementsByTagName("ns1:anniversaire").item(0).getTextContent();
                trouverprofil =true;
            }
            j++;
        }        
        Element profilJoueur = (Element) listprofil.item(j);
        
        //charge partie
        String date;
        double temps=0.0;
        String mot="";
        int niveau=1;
        int trouvé=0;
                
        int i=0;
        Boolean trouverpartie = false;
        NodeList listpartie = profilJoueur.getElementsByTagName("ns1:partie"); 
        
        while( (i < listpartie.getLength()) && !trouverpartie) {
            Element liste = (Element) listpartie.item(i);
            
            if (liste.getAttribute("date").equals(dateJeu)){
                //temps = Double.parseDouble(liste.getElementsByTagName("ns1:temps").item(0).getTextContent());
                mot = liste.getElementsByTagName("ns1:mot").item(0).getTextContent();           
                niveau = Integer.parseInt(liste.getAttribute("niveau"));       
                //trouvé = Integer.parseInt(liste.getAttribute("trouvé"));
                trouverpartie =true;
            }
            i++;
        }            
                       
            p = new Partie(profileDateToXmlDate(dateJeu), mot, niveau);
           // p.setTemps((int) 0.0);
            //p.setTrouve(0);
            //ajout dans la liste des parties
           
        
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
    
    
    //rajouter à la liste des parties une Partie instanciée.
   /* public void ajouterPartie(Partie p){
        parties.add(p);
    }*/
    /*
    public int getDernierNiveau(){
        return parties.get(parties.size() - 1).getNiveau();
    }*/
    
    public String toString(){
        return "";
    }
    
    //sauvegarder le document DOM dans un fichier XML
    public void sauvegarder(String filename) {       
        
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

    protected boolean charge(String nomJoueur, String file) {
        _doc = fromXML(file);
        NodeList noms = _doc.getElementsByTagName("ns1:nom");
        int i;
        for(i= 0 ; i<noms.getLength(); i++){
            Element nom = (Element) noms.item(i);            
            System.out.println("nom : "+nom);
            
            if(nom.equals(nomJoueur)){
                this.nom = nomJoueur;
                return true;
            }            
        }        
        return false;
    }
    
    
}
