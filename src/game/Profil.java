/*
 *
 * 
 */
package game;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.*;
import xml.XMLUtil;


public class Profil {
    private String nom;
    private String dateNaissance;
    private String avatar;
    public final String fileProfilXML = "src/xml/data/xml/profil.xml";
    //private ArrayList<Partie> parties;
    public Partie p;
    public Document _doc;
    private Boolean _doc_loaded = false;

    private Element nomCharge; //le nom en DOM (Element DOM)
    private Element profil; //le profil en Dom (Element DOM)

    private final String listeProfilsTag = "ns1:listeProfils";
    private final String profilTag = "ns1:profil";
    private final String nomTag = "ns1:nom";
    private final String dateNaisTag = "ns1:anniversaire";
    private final String avatarTag = "ns1:avatar";
    private final String partiesTag = "ns1:parties";
    private final String partieTag = "ns1:partie";
    private final String motTag = "ns1:mot";
    private final String tempsTag = "ns1:temps";
    private final String trouvéAttr = "trouvé";
    private final String niveauAttr = "niveau";

    public Profil() {
        this.nom = "";
        this.avatar = "";
        this.dateNaissance = "";
    }

    /**
     * Constructeur de profil depuis un fichier xml en se basant sur le nom de
     * joueur qui lui est passé. Profil(nomJoueur: String)
     *
     * @param nomJoueur
     */
    public Profil(String nomJoueur) {
        init_doc();

        if (charge(nomJoueur)) {
            if (nomJoueur.equals(nomCharge.getTextContent())) {
                profil = (Element) nomCharge.getParentNode();
                Element dateNais = (Element) profil.getElementsByTagName(dateNaisTag).item(0);
                Element avatar = (Element) profil.getElementsByTagName(avatarTag).item(0);
                this.nom = nomCharge.getTextContent();
                this.dateNaissance = xmlDateToProfileDate(dateNais.getTextContent());
                this.avatar = avatar.getTextContent();
            }
        }
    }

    /**
     * Constructeur de profil qui ecrit dans le xml à partir le nom et la date
     * de naissance du joueur qui lui est passé. Profil(nom: String,
     * dateNaissance: String)
     *
     * @param nom
     * @param dateNaissance
     */
    public Profil(String nom, /*String avatar,*/ String dateNaissance) { //2/1
        this.nom = nom;
        this.dateNaissance = dateNaissance;
        this.avatar = "";

        if (!_doc_loaded) {
            init_doc();
        }

        Element profilElem = (Element) _doc.createElement(profilTag);

        //balise temps 
        Element nomElem = (Element) _doc.createElement(nomTag);
        nomElem.appendChild(_doc.createTextNode(this.nom));

        Element avatarElem = (Element) _doc.createElement(avatarTag);
        avatarElem.appendChild(_doc.createTextNode(this.avatar));

        Element dateNaisElem = (Element) _doc.createElement(dateNaisTag);
        dateNaisElem.appendChild(_doc.createTextNode(profileDateToXmlDate(this.dateNaissance)));

        Element partiesElem = (Element) _doc.createElement(partiesTag);

        //liaison
        profilElem.appendChild(nomElem);
        profilElem.appendChild(avatarElem);
        profilElem.appendChild(dateNaisElem);
        profilElem.appendChild(partiesElem);
        _doc.getElementsByTagName(listeProfilsTag).item(0).appendChild(profilElem);
        toXML(fileProfilXML);
        profil = profilElem;
    }

    /**
     * Charge le document .xml pour la lecture et l'ecriture par le DOM
     *
     */
    private void init_doc() {
        _doc = fromXML(fileProfilXML);
        _doc_loaded = true;
    }

    /**
     * Charge une partie existante despuis le xml en fonction du nom et la date
     * de naissance du joueur qui lui sont passés loadPartie(nomJouer: String,
     * date: String)
     *
     * @param nomJoueur
     * @param date
     */
    public Partie loadPartie(String nomJoueur, String date) {
        if (!_doc_loaded) {
            init_doc();
        }

        if (charge(nomJoueur)) {
            if (nomJoueur.equals(nomCharge.getTextContent())) {
                Element partieElm = (Element) profil.getElementsByTagName(partieTag).item(0);
                p = new Partie(partieElm);
                p.setDate(xmlDateToProfileDate(p.getDate()));
            }
        }
        return p;
    }
    
    //menu 1.2
    // Cree un DOM à partir d'un fichier XML -> joueur existant
    //-> utilise le document DOM pour extraire les données nécessaires 
    //à la récupération des valeurs du profil et des parties existantes 
    {
//    public Profil(String nomFichier, String nomJoueur, String dateJeu) {
//        //recuperation des données du joueur
//        //_doc = fromXML(fileProfilXML);
//        int j=0;
//        Boolean trouverprofil = false;
//        NodeList listprofil = _doc.getElementsByTagName("ns1:profil"); 
//        
//        while(j< listprofil.getLength() && !trouverprofil){
//            Element listeprof = (Element) listprofil.item(j);
//            if (listeprof.getElementsByTagName("ns1:nom").equals(nomJoueur)){
//                this.nom = listeprof.getElementsByTagName("ns1:nom").item(0).getTextContent();
//                this.avatar = _doc.getElementsByTagName("ns1:avatar").item(0).getTextContent();
//                this.dateNaissance = _doc.getElementsByTagName("ns1:anniversaire").item(0).getTextContent();
//                this.dateNaissance = xmlDateToProfileDate(this.dateNaissance);
//                trouverprofil =true;
//            }
//            j++;
//        }        
//        Element profilJoueur = (Element) listprofil.item(j);
//        
//        //charge partie
//        String date;
//        double temps=0.0;
//        String mot="";
//        int niveau=1;
//        int trouvé=0;
//                
//        int i=0;
//        Boolean trouverpartie = false;
//        NodeList listpartie = profilJoueur.getElementsByTagName("ns1:partie"); 
//        
//        while( (i < listpartie.getLength()) && !trouverpartie) {
//            Element liste = (Element) listpartie.item(i);
//            
//            if (liste.getAttribute("date").equals(dateJeu)){
//                //temps = Double.parseDouble(liste.getElementsByTagName("ns1:temps").item(0).getTextContent());
//                mot = liste.getElementsByTagName("ns1:mot").item(0).getTextContent();           
//                niveau = Integer.parseInt(liste.getAttribute("niveau"));       
//                //trouvé = Integer.parseInt(liste.getAttribute("trouvé"));
//                trouverpartie =true;
//            }
//            i++;
//        }            
//                       
//            p = new Partie(profileDateToXmlDate(dateJeu), mot, niveau);
//           // p.setTemps((int) 0.0);
//            //p.setTrouve(0);
//            //ajout dans la liste des parties        
//    }
    }
    
    {
//    public Partie chargerPartie(String dateNais) {
//        double temps = 0.0;
//        String mot = "";
//        String date = "";
//        int niveau = 0;
//        Element partie;
//        //this._doc = fromXML(file);
//        NodeList parties = this._doc.getElementsByTagName("partie");
//
//        int i = 0;
//        Boolean found = false;
//        while (i < parties.getLength() && !found) {
//            partie = (Element) parties.item(i);
//            date = partie.getAttribute("date");
//            if (date.equals(dateNais)) {
//                temps = Double.parseDouble(partie.getElementsByTagName("ns1:temps").item(0).getTextContent());
//                mot = partie.getElementsByTagName("mot").item(0).getTextContent();
//                niveau = Integer.parseInt(partie.getAttribute("niveau"));
//            }
//        }
//        return new Partie(xmlDateToProfileDate(date), mot, niveau);
//    }
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
    
    public String toString(){
        return "profil de " + nom;
    }
    
    //sauvegarder le document DOM dans un fichier XML
    /**
     * Méthode pour sauvegarder dans un le fichier des profils (xml) une partie
     * sauvegarder(p : Partie) : void
     *
     * @param p
     */
    public void sauvegarder(Partie p) {
        //init_doc();
        p.setDate(profileDateToXmlDate(p.getDate()));
        Element partie = p.createPartieOnDOM(_doc);
        Element parties = (Element) profil.getElementsByTagName(partiesTag).item(0);
        parties.appendChild(partie);
        toXML(fileProfilXML);
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
        if (!_doc_loaded) {
            init_doc();
        }
        NodeList noms = _doc.getElementsByTagName(nomTag);
        int i;
        for (i = 0; i < noms.getLength(); i++) {
            String nom = noms.item(i).getTextContent();
            
            if (nom.equals(nomJoueur)) {
                nomCharge = (Element) noms.item(i);
                this.nom = nomJoueur;
                System.out.println("Profil de nom : " + nom + " trouvé");
                return true;
            }            
        }        
        return false;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Document getDoc() {
        return _doc;
    }

    public void setDoc(Document _doc) {
        this._doc = _doc;
    }
    
    
}
