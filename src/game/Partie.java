
package game;


import org.w3c.dom.Document;
import org.w3c.dom.*;



/**
 *
 * @author dothit
 */
public class Partie {
    private String date;
    private String mot;
    private int niveau;
    private int trouvé; 
    private double temps;
    
    //construction d'une nouvelle partie et initialise donc tous ses attributs
    public Partie(String date, String mot, int niveau){
        this.date = date;
        this.mot = mot;
        this.niveau = niveau;
    }
    
    //construction et la réinitialisation d'une Partie déjà faite et issue du XML
    //initialiser la partie à jouer
    public Partie(Element partieElt) { // élément DOM correspondant à une partie
        //recuperer les données d'une partie
        this.mot = partieElt.getElementsByTagName("ns1:mot").getTextContent();
        this.niveau = partieElt.getAttribute("ns1:niveau");
        this.date = partieElt.getAttribute("ns1:date");
        
        //reinitialisation des données
        partieElt.setAttribute("trouvé",this.trouvé);
        partieElt.getElementsByTagName("ns1:temps").setNodeValue(this.temps);
        
    }
    
    //crée le bloc XML représentant une partie à partir du paramètre doc(pour créer les éléments du XML) et le renvoie
    public Element getPartie(Document doc) {
        //balise partie avec attribut date et trouvé        
        Element partieElem =  (Element) doc.createElement("ns1:partie");
        partieElem.setAttribute("date", this.date);
        partieElem.setAttribute("trouvé", ""+this.trouvé);
        
        //balise temps 
        Element tempsElem = (Element) doc.createElement("ns1:temps");
        
        tempsElem.appendChild(doc.createTextNode(""+this.temps));
        //balise mot avec attribut niveau
        Element motElem = (Element) doc.createElement("ns1:mot");
        motElem.setAttribute("niveau", ""+this.niveau);
        motElem.appendChild(doc.createTextNode(this.mot));

        //relie 
        partieElem.appendChild(tempsElem);
        partieElem.appendChild(motElem);
        
        return partieElem;
    }
    
    
    /*
    *Gestion des partie    
    */    
    
    public void setTrouve(int nbLettresRestantes) {
        this.trouvé = (this.mot.length() - nbLettresRestantes) * 100 / this.mot.length();
    }
   //renvoyer un pourcentage des lettres trouvées.
    public int getTrouve() {
        return trouvé;
    }
    
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
    
    public String getDate() {
        return date;
    }

    public int getTemps() {
        return (int) temps;
    }

    
}
