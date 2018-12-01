/*
*getMotDepuisListe
*
*/
package game;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class Dico extends DefaultHandler {

    private ArrayList<String> listeNiveau1;
    private ArrayList<String> listeNiveau2;
    private ArrayList<String> listeNiveau3;
    private ArrayList<String> listeNiveau4;
    private ArrayList<String> listeNiveau5;
    private String cheminFichierDico;
    
    //résultats de notre parsing 
    private List<Mot> dictionnaire;
    private Mot mot;
    //flags nous indiquant la position du parseur 
	private boolean inDictionnaire, inMot;
    //buffer nous permettant de récupérer les données  
    private StringBuffer buffer;
    
    
    public Dico(String cheminFichier) {
        super();
        inDictionnaire = false;
        inMot = false;
        //instancie les listes par niveau
        this.listeNiveau1 = new ArrayList<String>();
        this.listeNiveau2 = new ArrayList<String>();
        this.listeNiveau3 = new ArrayList<String>();
        this.listeNiveau4 = new ArrayList<String>();
        this.listeNiveau5 = new ArrayList<String>();
                
        this.cheminFichierDico = cheminFichier;

    }
    

    public String getMotDepuisListeNiveau(int niveau) {
        switch (vérifieNiveau(niveau)) {
            case 5: 
                return getMotDepuisListe(this.listeNiveau5);
            case 4: 
                return getMotDepuisListe(this.listeNiveau4);
            case 3: 
                return getMotDepuisListe(this.listeNiveau3);
            case 2: 
                return getMotDepuisListe(this.listeNiveau2);
            case 1: 
                return getMotDepuisListe(this.listeNiveau1);
            default: 
        }
        return null;
    }

    //ajout du mot dans la liste du niveau spécifique
    public void ajouteMotADico(int niveau, String mot) {
        int niv = vérifieNiveau(niveau);
        switch (niv) {
            case 5: 
                listeNiveau5.add(mot); 
                break;
            case 4: 
                listeNiveau4.add(mot);
                break;
            case 3: 
                listeNiveau3.add(mot);
                break;
            case 2: 
                listeNiveau2.add(mot);
                break;
            case 1: 
                listeNiveau1.add(mot);
                break;
            default: 
        }
    }
    

    public String getCheminFichierDico() {
        return cheminFichierDico;
    }

    //verifie si le niveau est compris entre 1 et 5
    private int vérifieNiveau(int niveau) {
        if ((niveau >= 1) && (niveau <= 5) ){            
            return niveau;
        }
        else{
            return 1; //par défaut
        }
    }
    
    //choix aléatoire du mot dans la liste
    private String getMotDepuisListe(ArrayList<String> list) {
        if(list.size() > 0){
            int range = (list.size()-1) - 0 + 1  ; 
            // generate random numbers entre 0 et la taille de la list
            int rand = (int) (Math.random() * range) + 0;
            //return le mot de d'indice rand
            //System.out.println( rand + "-> "+ list.get(rand) );
            return list.get(rand);
        }
        //si liste vide return valeur par défaut
        else {
            return "default";
        }
        
    }
        
    //détection d'ouverture de balise 
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("ns1:dictionnaire")) {
            dictionnaire = new LinkedList<Mot>();
            inDictionnaire = true;
        } else if (qName.equals("ns1:mot")) {
            mot = new Mot(); 
            try{ 
                int niveau = Integer.parseInt(attributes.getValue("niveau"));
                mot.setNiveau(niveau);
                //ajouteMotADico(niveau, mot.getMot());
            }catch(Exception e){ 
                //erreur, le contenu de niveau n'est pas un entier 
                throw new SAXException(e); 
            }
            buffer = new StringBuffer();
            inMot = true; 
        }else {
            throw new SAXException("Balise "+qName+" inconnue."); 
        }
    }

    @Override
    //détection fin de balise 
    public void endElement(String uri, String localName, String qName) throws SAXException{ 
        if (qName.equals("ns1:dictionnaire")) {
            inDictionnaire = false; 
        } else if (qName.equals("ns1:mot")) {
            mot.setMot(buffer.toString()); 
            buffer = null;

            dictionnaire.add(mot);
            mot = null; 
            
            inMot = false; 
        }else{ 
            //erreur, on peut lever une exception 
            throw new SAXException("Balise "+qName+" inconnue."); 
        }           
    } 

    @Override
    //détection de caractères 
    public void characters(char[] ch,int start, int length) throws SAXException{ 
        String lecture = new String(ch,start,length); 
        if(buffer != null) buffer.append(lecture);        
    } 
    
    
    //début du parsing 
    @Override
    public void startDocument() throws SAXException {
    } 
    //fin du parsing 
    
    
    @Override
    public void endDocument() throws SAXException {
        for (Mot p : dictionnaire) {
            ajouteMotADico(p.getNiveau(), p.getMot());
//            System.out.println("Ajouté au dico: " + p);
        }
    }

}
