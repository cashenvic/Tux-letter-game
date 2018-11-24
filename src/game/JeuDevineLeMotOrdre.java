/**
tuxTrouveLettre -> si c'est pas la bonne lettre
* terminePartie -> pourcentage
* apparition de la lettre pdt 5s
 */
package game;

import java.util.ArrayList;

public class JeuDevineLeMotOrdre extends Jeu{
    private int nbLettresrestantes;
    private Chronometre chrono;
    private ArrayList<Letter> lettresRestantes;
    private ArrayList<Letter> wrongLetters;
    
    //affichage du mot pdt 5s
    
    public JeuDevineLeMotOrdre(){
        super();        
        lettresRestantes = new ArrayList<Letter>();
        
    }
    
    @Override
    protected void démarrePartie(Partie partie){     
        
        //initialiser+ajout dans l'environnment le mot en lettres à deviner
        caract = decouppeMot(partie.getMot());
        int i=0;
        for (char c : caract) {
            Letter var = new Letter(c, randomDouble(0 + 3.0 , mainRoom.getWidth() - 3.0 ), randomDouble(0 + 3.0, mainRoom.getDepth() -3.0 ) );
            lettres.add(var);
            //init des listes
            lettresRestantes.add(var);    
            env.addObject(lettres.get(i));
            i++;
        }  
        
         // restaure la room du jeu
        //env.setRoom(mainRoom);   
        
        nbLettresrestantes = getNbLettresRestantes();
        //comporte les lettres qui sont mal choisi/désodre
        wrongLetters = new ArrayList<Letter>();
        
        //affichage du mot à trouvé pendant 5s
        
        
        
        //initialise la limite + instancie le chrono et commence le chrono -> start
        int limite = 5*60*1000; 
        chrono = new Chronometre(limite);
        chrono.start();
                        
    }
    
    @Override
    protected void appliqueRegles(Partie partie){
        int i=0;      
        
        if(!chrono.remainsTime()){
            finished=true;
        }        
        if(nbLettresrestantes == 0 ){
            System.out.println("FIN de la partie ");
            finished=true;
        }
        
        if(tuxTrouveLettre()){
            System.out.println("touché ");            
            nbLettresrestantes--;

        }
    }
    
    @Override
    protected void terminePartie(Partie partie){
        chrono.stop();       
        //partie.setTrouve(lettres.size() - lettresRestantes.size());       
        
        //a supp car faut le faire dans partie settrouve
        int pourcent =  ( lettres.size() - getNbLettresRestantes() ) * 100 / lettres.size() ;
        
         System.out.println("Temps réparti : " + chrono.getMilliseconds() + " ms"
                            + "\nPourcentage des lettres trouvé : " + pourcent);
        
    }
    
    //a chaque lettre trouver -> on enleve la lettre de la liste et de l'env
    //si mauvaise lettre on l'ajoute dans la liste de wrongLetters
    private boolean tuxTrouveLettre(){
        for (Letter l : lettresRestantes) {
            if( collision(l) ){ 
                if(l == lettresRestantes.get(0)) {
                    lettresRestantes.remove(l);
                    env.removeObject(l);
                    return true;
                }
                else{
                    wrongLetters.add(l);
                }
            }
        }            
        return false;
    }
    
    private int getNbLettresRestantes(){
        return lettresRestantes.size();
    }
    
    private int getTemps(){
        return 0;
    }
    
   
}
