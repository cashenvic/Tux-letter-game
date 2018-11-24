/*
*getMotDepuisListe
*
*/
package game;

import java.util.ArrayList;


public class Dico {

    private ArrayList<String> listeNiveau1;
    private ArrayList<String> listeNiveau2;
    private ArrayList<String> listeNiveau3;
    private ArrayList<String> listeNiveau4;
    private ArrayList<String> listeNiveau5;
    private String cheminFichierDico;
    
    
    public Dico(String cheminFichier) {
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
        return "";
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
            int rand = (int)(Math.random() * range) + 0;
            //return le mot de d'indice rand
            //System.out.println( rand + "-> "+ list.get(rand) );
            return list.get(rand);
        }
        //si liste vide return valeur par défaut
        else {
            return "default";
        }
        
    }
    
}
