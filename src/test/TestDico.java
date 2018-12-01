/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import game.Dico;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class TestDico {
   public static void main(String [] args){  
       Dico dico = new Dico("src/xml/data/xml/dico.xml");
       List dict;

       try {
           lireDictionnaire(dico);
       } catch (SAXException ex) {
           Logger.getLogger(Dico.class.getName()).log(Level.SEVERE, null, ex);
       }

        
       System.out.println("mot de niveau 1 " + dico.getMotDepuisListeNiveau(1));
//        
        System.out.println("mot de niveau 2 " + dico.getMotDepuisListeNiveau(2));

       System.out.println("mot de niveau 3 " + dico.getMotDepuisListeNiveau(3));

       System.out.println("mot de niveau 4 " + dico.getMotDepuisListeNiveau(4));

       System.out.println("mot de niveau 5 " + dico.getMotDepuisListeNiveau(5));

       System.out.println("mot de niveau 6 " + dico.getMotDepuisListeNiveau(6));

       System.out.println("mot de niveau 0 " + dico.getMotDepuisListeNiveau(0));
       
        
    }

    private static void lireDictionnaire(Dico dico) throws org.xml.sax.SAXException {
        String pathToDicoFile = "src/xml/data/xml/dico.xml";

        try {
            // création d'une fabrique de parseurs SAX 
            SAXParserFactory fabrique = SAXParserFactory.newInstance();

            // création d'un parseur SAX 
            SAXParser parseur = fabrique.newSAXParser();

            // lecture d'un fichier XML avec un DefaultHandler 
            File fichier = new File(pathToDicoFile);
            //DefaultHandler gestionnaire = dico;
            parseur.parse(fichier, dico);
            System.out.println("Fin lireDictionnaire()");

        } catch (ParserConfigurationException pce) {
            System.out.println("Erreur de configuration du parseur");
            System.out.println("Lors de l'appel à newSAXParser()");
        } catch (IOException ex) {
            Logger.getLogger(Dico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}