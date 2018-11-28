/*
 *
 * 
 */
package game;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import xml.XMLUtil;


public class Profil {
    private String nom;
    private String dateNaissance;
    private String avatar;    
    private ArrayList<Partie> parties;
    
    public Document _doc;
    private String file;
    
    public Profil(String nom, String dateNaissance){
        this.nom = nom;
        this.dateNaissance = dateNaissance;
    }
    
    // Cree un DOM à partir d'un fichier XML
    //-> utilise le document DOM pour extraire les données nécessaires 
    //à la récupération des valeurs du profil et des parties existantes
    public Profil(String nomFichier) {
        
        _doc = fromXML(nomFichier);
        this.nom = _doc.getElementsByTagName("ns1:nom").item(0).getTextContent();
        this.avatar = _doc.getElementsByTagName("ns1:avatar").item(0).getTextContent();
        this.dateNaissance = _doc.getElementsByTagName("ns1:anniversaire").item(0).getTextContent();
        
        //balise profil
        Element racine =  (Element) _doc.createElement("ns1:profil");
        racine.setAttributeNS("http://www.w3.org/2001/XMLSchema-instance", "xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
        racine.setAttributeNS("http://myGame/tux", "xmlns:ns1", "");
        racine.setAttributeNS("http://myGame/tux ../xsd/profil.xsd", "xsi:schemaLocation", "");
        _doc.appendChild((Node) racine);
        
        //balise nom
        Element nomElem = (Element) _doc.createElement("ns1:nom");
        nomElem.setNodeValue(this.nom);
        //balise avatar
        Element avatarElem = (Element) _doc.createElement("ns1:avatar");
        avatarElem.setNodeValue(this.avatar);
        //balise anniversaire
        Element annivElem = (Element) _doc.createElement("ns1:anniversaire");
        annivElem.setNodeValue(this.dateNaissance);
        //balise parties
        Element partiesElem = (Element) _doc.createElement("ns1:parties");
        
        racine.appendChild(nomElem);
        racine.appendChild(avatarElem);
        racine.appendChild(annivElem);
        racine.appendChild(partiesElem);
                
        String date;
        double temps;
        String mot;
        int niveau;
        int trouvé;
        Partie p;        
        NodeList listpartie = _doc.getElementsByTagName("ns1:partie");
//        for (int i = 0; i < _doc.getElementsByTagName("ns1:partie").getLength(); i++) {
//            date = listpartie[i].getAttribute("date");
//            temps = listpartie[i].getElementsByTagName("ns1:temps").item(0).getTextContent();
//            mot = listpartie[i].getElementsByTagName("ns1:mot").item(0).getTextContent();           
//            niveau = listpartie[i].getAttribute("niveau");       
//            trouvé = listpartie[i].getAttribute("trouvé");                   
//                       
//            p = new Partie(profileDateToXmlDate(date), mot, Integer.parseInt(niveau));
//            p.setTemps(Integer.parseInt(temps));
//            p.setTrouve(Integer.parseInt(trouvé));
//            //ajout dans la liste des parties
//            ajouterPartie(p);
//        }
//        
//        for(Partie unepartie : parties){            
//            Element partieExistant = unepartie.getPartie( (javax.swing.text.Document) _doc);
//            partiesElem.appendChild(partieExistant);
//        }
        
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
    public void ajouterPartie(Partie p){
        parties.add(p);
    }
    
    public int getDernierNiveau(){
        return parties.get(parties.size() - 1).getNiveau();
    }
    
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

    protected boolean charge(String nomJoueur) {
        String file = "src/xml/data/profil.xml";
        _doc = fromXML(file);
        String nom = _doc.getElementsByTagName("ns1:nom").item(0).getTextContent();
        System.out.println("nom : "+nom);
        if(nom.equals(nomJoueur)){
            this.nom = nomJoueur;
            this.file = file;
            return true;
        }
        
        return false;
    }
    
}
