/**
tuxTrouveLettre -> si c'est pas la bonne lettre
 */
package game;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class JeuDevineLeMotOrdre extends Jeu{
    private int nbLettresrestantes;
    private Chronometre chrono;
    private ArrayList<Letter> lettresRestantes;
    private ArrayList<Letter> lettresTrouvees;
    private ArrayList<Letter> wrongLetters;
    
    //affichage du mot pdt 5s
    
    public JeuDevineLeMotOrdre(){
        super();        
        lettresRestantes = new ArrayList<Letter>();
        lettresTrouvees = new ArrayList<Letter>();
    }
    
    @Override
    protected void démarrePartie(Partie partie){   
        //affichage du mot pendant quelques secondes
        affichageDelai( partie.getMot(),3000);
               
        //instanciation du mot à deviner et le tux dans l'env
        ajoutEnvJeu(partie.getMot());                
        
        //initialise la limite + instancie le chrono et commence le chrono -> start
        int limite = 10 * 1000/*5*60*1000*/;
        chrono = new Chronometre(limite);
        chrono.start();
                        
    }
    
    
    @Override
    protected void appliqueRegles(Partie partie){
        int i = 0;
        System.out.println("Temps écoulé: " + chrono.getTime() + " ****** Temps restantes " + chrono.remainsTime());
        
        if(!chrono.remainsTime()){
            finished = true;
            System.out.println("\nFIN de la partie \nPas de chance!!!");
        }        
        if(nbLettresrestantes == 0 ){
            System.out.println("\nFIN de la partie \nVous avez gagné!!! ");
            finished=true;
        }
        
        if (tuxTrouveLettre()) {
            nbLettresrestantes--;
        }
    }
    
    @Override
    protected void terminePartie(Partie partie){
        chrono.stop();    
        //set le temps et le pourcentage du jeu                
        partie.setTemps(chrono.getSeconds()); 
        partie.setTrouve(nbLettresrestantes);
        
        System.out.println("Temps réparti : " + chrono.getMilliseconds() + " ms"
                + "\nPourcentage des lettres trouvé : " + partie.getTrouve() + "%");
        
    }
    
    //a chaque lettre trouver -> on enleve la lettre de la liste et de l'env
    //si mauvaise lettre on l'ajoute dans la liste de wrongLetters
    private boolean tuxTrouveLettre(){
        for (Letter l : lettresRestantes) {
            if( collision(l) ){ 
                if(l == lettresRestantes.get(0)) {
                    lettresRestantes.remove(l);
                    lettresTrouvees.add(l);
                    env.removeObject(l);
                    System.out.print(l.getLetter());
                    return true;
                }
                else{
                    wrongLetters.add(l);
                }
            }
        }            
        return false;
    }
    
    
            
    private void ajoutEnvJeu(String mot){
        //ajout dans l'environnment le mot en lettres à deviner et le tux
        caract = decouppeMot(mot);
        int i=0;
        for (char c : caract) {
            Letter var = new Letter(c, randomDouble(0 + 3.0 , mainRoom.getWidth() - 3.0 ), randomDouble(0 + 3.0, mainRoom.getDepth() -3.0 ) );
            lettres.add(var);
            //initialises de la liste lettresRestantes à deviner
            lettresRestantes.add(var);    
            env.addObject(lettres.get(i));
            i++;
        }  
        //inialise (int) nbLettresrestantes         
        nbLettresrestantes = getNbLettresRestantes();
        
         //comporte les lettres qui sont mal choisi/désodre
        wrongLetters = new ArrayList<Letter>();
        
        // Instancie un Tux
        tux = new Tux(env, mainRoom);
        env.addObject(this.tux);
        
    }
    
        
    private void affichageDelai(String mot, int temps){
        //affichage du mot à deviner pendant 3s
        long max = temps; // 60000 = 1mn
        long tmax = System.currentTimeMillis() + max;

        //caract = decouppeMot(partie.getMot());
        char caracts[] = decouppeMot(mot);
        double i = 0.0;
        for (char c : caracts) {
            Letter var = new Letter(c, 6 + i, mainRoom.getDepth());
            env.addObject(var);
            i += 7.0;
        }
        env.advanceOneFrame();

        while (System.currentTimeMillis() < tmax) {
        }
        
        for (char c : caracts) {
            env.removeObject(c);
        }
    }
            
        
    
    
    private int getNbLettresRestantes(){
        return lettresRestantes.size();
    }
    
    private int getTemps(){
        return 0;
    }
    
   
}
