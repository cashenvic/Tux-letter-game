
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
    
    /**
     * Constructeur de partie sans paramètre qui se charge d'initialiser les
     * attributs de classe. La date à la date du jour, mot à "" et niveau à 1
     */
    public Partie() {
        this.date = "";
        this.mot = "";
        this.niveau = 1;
    }

    //construction d'une nouvelle partie et initialise donc tous ses attributs
    /**
     * Constructeur de partie qui initialise les attributs de classes aux
     * valeurs passées en paramètres. Partie(date: String, mot: String, niveau:
     * int)
     *
     * @param date
     * @param mot
     * @param niveau
     */
    public Partie(String date, String mot, int niveau){
        this.date = date;
        this.mot = mot;
        this.niveau = niveau;
    }
    
    //construction et la réinitialisation d'une Partie déjà faite et issue du XML
    //initialiser la partie à jouer
    public Partie(Element partieElt) { // élément DOM correspondant à une partie
        //recuperer les données d'une partie
        Element mot = (Element) partieElt.getElementsByTagName("ns1:mot");
        this.mot = mot.getTextContent();
        this.niveau = Integer.parseInt(partieElt.getAttribute("ns1:niveau"));
        this.date = partieElt.getAttribute("ns1:date");
        
        //reinitialisation des données
        partieElt.setAttribute("trouvé", "" + this.trouvé);
        Element temps = (Element) partieElt.getElementsByTagName("ns1:temps");
        temps.setNodeValue("" + this.temps);
        
    }
    
    //crée le bloc XML représentant une partie à partir du paramètre doc(pour créer les éléments du XML) et le renvoie
    public Element createPartieOnDOM(Document doc) {
        //balise partie avec attribut date et trouvé        
        Element partieElem =  (Element) doc.createElement("ns1:partie");
        partieElem.setAttribute("date", this.date);
        partieElem.setAttribute("trouvé", this.trouvé + "%");
        
        //balise temps 
        Element tempsElem = (Element) doc.createElement("ns1:temps");
        tempsElem.appendChild(doc.createTextNode("" + this.temps));

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

    public void setDate(String date) {
        this.date = date;
    }

}
