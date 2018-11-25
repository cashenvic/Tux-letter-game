/*
 * Ajoutez la possibilité de sélectionner un niveau au début d'une partie
(avant de choisir le mot dans le dictionnaire, bien entendu).
 */
package game;

import javax.swing.text.Document;
import javax.swing.text.Element;

/**
 *
 * @author dothit
 */
public class Partie {
    private String date;
    private String mot;
    private int niveau;
    private int trouvé; //pourcentage (arrondi) de lettres trouvées
    private int temps;
    
    //construction d'une nouvelle partie et initialise donc tous ses attributs
    public Partie(String date, String mot, int niveau){
        this.date = date;
        this.mot = mot;
        this.niveau = niveau;
    }
    
    //construction et la réinitialisation d'une Partie déjà faite et issue du XML
    //parsing du document DOM ; le constucteur utilisera cet élément pour récupérer les bonnes valeurs et initialiser la partie.
    public Partie(Element partieElt) {
//        • getElementsByTagName(name: String): NodeList renvoie la liste des éléments descendants
//        de l’élément courant qui ont pour nom celui passé en attribut.
//        getAttribute(name: String): String retourne la valeur de l’attribut dont le nom est passé
//        en paramètre.
//        • setAttribute(name: String, valeur: String): void modifie la valeur de l’attribut.
//        • removeAttribute(name: String): void supprime l’attribut dont le nom est passé en paramètre
//        • getTagName(): String renvoie le nom de l’élément.
    }
    
    //crée le bloc XML représentant une partie à partir du paramètre doc(pour créer les éléments du XML) et renvoie ce bloc en tant que Element.
    public /*Element*/ void getPartie(Document doc) {
        
    }
    
    
    //renvoyer un pourcentage en fonction du nombre de lettres trouvées.
    public int setTrouve(int nbLettresRestantes) {
        return (this.mot.length() - nbLettresRestantes) * 100 / this.mot.length();
    }
    
    
    /*
    *Gestion des partie    
    */
    
    public void setTemps(int temps) {
        this.temps = temps;
    }
    
    public int getNiveau(){
        return this.niveau;
    }
    
    public String toString(){
     return "";   
    } 
    
    
    //supplementaire
    public String getMot(){
        return this.mot;   
    } 
    
    public void setMot(String mot){
        this.mot = mot;   
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getTrouvé() {
        return trouvé;
    }

    public int getTemps() {
        return temps;
    }


}
