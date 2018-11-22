/*
TODO 
- faut instancier l'exemple dans lequel le cube est sur la meme que 
un autre cube -> graine
- affichage pas à pas des lettres trouvé dans la console -> list bonOrdre
- l.374 // enregistre la partie dans le profil --> enregistre le profil
 */
package game;

import env3d.Env;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import org.lwjgl.input.Keyboard;

public abstract class Jeu {

    // Une énumération pour définir les choix de l'utilisateur
    enum MENU_VAL {
        MENU_SORTIE, MENU_CONTINUE, MENU_JOUE
    }


    // attributs de classe
    protected Env env;
    protected mainRoom mainRoom;    
    protected Tux tux;
    protected Profil profil;
    protected ArrayList<Letter> lettres; 
    protected Dico dico;
    
    //niveau
    private int niveau;
    //mot du dico
    private String mot;
    //date de la partie
    private String date;
    
    // ... attributs existants ...
    protected char caract[];
    protected Boolean finished;
    
    // ...
    private final mainRoom menuRoom;
    
    EnvText textNomJoueur;
    EnvText textMenuQuestion;
    EnvText textMenuJeu1;
    EnvText textMenuJeu2;
    EnvText textMenuJeu3;
    EnvText textMenuJeu4;
    EnvText textMenuPrincipal1;
    EnvText textMenuPrincipal2;
    EnvText textMenuPrincipal3;
    
    //question niveau
    EnvText textMenuQ1;
    //reponses des niveau 
    EnvText textMenuR1;
    EnvText textMenuR2;
    EnvText textMenuR3;
    EnvText textMenuR4;
    EnvText textMenuR5;




    public Jeu() {

        // Crée un nouvel environnement
        env = new Env();

        // Instancie une mainRoom
        mainRoom = new mainRoom();

        // Instancie une autre mainRoom pour les menus
        menuRoom = new mainRoom();
        menuRoom.setTextureEast("textures/black.png");
        menuRoom.setTextureWest("textures/black.png");
        menuRoom.setTextureNorth("textures/black.png");
        menuRoom.setTextureBottom("textures/black.png");

        // Règle la camera
        env.setCameraXYZ(50, 60, 175);
        env.setCameraPitch(-20);

        // Désactive les contrôles par défaut
        env.setDefaultControl(false);

        // Instancie un profil par défaut
        profil = new Profil("Profil.xml");
        
        // Dictionnaire
        //instancie les lettres en Liste Letter
        lettres = new ArrayList<Letter>();        
        //instancie de dico
        dico = new Dico("dico.xml");
        //ajout du dico niveau 1 
        dico.ajouteMotADico(1, "mot");
        dico.ajouteMotADico(1, "grenoble");
        dico.ajouteMotADico(1, "maman");
        dico.ajouteMotADico(1, "papa");   
        dico.ajouteMotADico(1, "j ai");  
        

        // Textes affichés à l'écran
        textMenuQuestion = new EnvText(env, "Voulez vous ?", 200, 300);
        textMenuJeu1 = new EnvText(env, "1. Commencer une nouvelle partie ?", 250, 280);
        textMenuJeu2 = new EnvText(env, "2. Charger une partie existante ?", 250, 260);
        textMenuJeu3 = new EnvText(env, "3. Sortir de ce jeu ?", 250, 240);
        textMenuJeu4 = new EnvText(env, "4. Quitter le jeu ?", 250, 220);
        textNomJoueur = new EnvText(env, "Choisissez un nom de joueur : ", 200, 300);
        textMenuPrincipal1 = new EnvText(env, "1. Charger un profil de joueur existant ?", 250, 280);
        textMenuPrincipal2 = new EnvText(env, "2. Créer un nouveau joueur ?", 250, 260);
        textMenuPrincipal3 = new EnvText(env, "3. Sortir du jeu ?", 250, 240);
        
        textMenuQ1 = new EnvText(env, "1.1 Choisissez votre niveau ?", 250, 240);        
        textMenuR1 = new EnvText(env, "Niveau 1", 250, 240);        
        textMenuR2 = new EnvText(env, "Niveau 2", 250, 240);        
        textMenuR3 = new EnvText(env, "Niveau 3", 250, 240);
        textMenuR4 = new EnvText(env, "Niveau 4", 250, 240);
        textMenuR5 = new EnvText(env, "Niveau 5", 250, 240);
        
    }

    /**
     * Gère le menu principal
     *
     */
    public void execute() {
        MENU_VAL mainLoop;
        mainLoop = MENU_VAL.MENU_SORTIE;
        do {
            mainLoop = menuPrincipal();
        } while (mainLoop != MENU_VAL.MENU_SORTIE);
        this.env.setDisplayStr("Au revoir !", 300, 30);
        env.exit();
    }

    /**
     * Teste si une code clavier correspond bien à une lettre
     *
     * @return true si le code est une lettre
     */
    private Boolean isLetter(int codeKey) {
        return codeKey == Keyboard.KEY_A || codeKey == Keyboard.KEY_B || codeKey == Keyboard.KEY_C || codeKey == Keyboard.KEY_D || codeKey == Keyboard.KEY_E
                || codeKey == Keyboard.KEY_F || codeKey == Keyboard.KEY_G || codeKey == Keyboard.KEY_H || codeKey == Keyboard.KEY_I || codeKey == Keyboard.KEY_J
                || codeKey == Keyboard.KEY_K || codeKey == Keyboard.KEY_L || codeKey == Keyboard.KEY_M || codeKey == Keyboard.KEY_N || codeKey == Keyboard.KEY_O
                || codeKey == Keyboard.KEY_P || codeKey == Keyboard.KEY_Q || codeKey == Keyboard.KEY_R || codeKey == Keyboard.KEY_S || codeKey == Keyboard.KEY_T
                || codeKey == Keyboard.KEY_U || codeKey == Keyboard.KEY_V || codeKey == Keyboard.KEY_W || codeKey == Keyboard.KEY_X || codeKey == Keyboard.KEY_Y
                || codeKey == Keyboard.KEY_Z;
    }

    /**
     * Récupère une lettre à partir d'un code clavier
     *
     * @return une lettre au format String
     */
    private String getLetter(int letterKeyCode) {
        Boolean shift = false;
        if (this.env.getKeyDown(Keyboard.KEY_LSHIFT) || this.env.getKeyDown(Keyboard.KEY_RSHIFT)) {
            shift = true;
        }
        env.advanceOneFrame();
        String letterStr = "";
        if ((letterKeyCode == Keyboard.KEY_SUBTRACT || letterKeyCode == Keyboard.KEY_MINUS)) {
            letterStr = "-";
        } else {
            letterStr = Keyboard.getKeyName(letterKeyCode);
        }
        if (shift) {
            return letterStr.toUpperCase();
        }
        return letterStr.toLowerCase();
    }

    /**
     * Permet de saisir le nom d'un joueur et de l'affiche à l'écran durant la saisie
     *
     * @return le nom du joueur au format String
     */
    private String getNomJoueur() {
        textNomJoueur.modify("Choisissez un nom de joueur : ");
        int touche = 0;
        String nomJoueur = "";
        while (!(nomJoueur.length() > 0 && touche == Keyboard.KEY_RETURN)) {
            touche = 0;
            while (!isLetter(touche) && touche != Keyboard.KEY_MINUS && touche != Keyboard.KEY_SUBTRACT && touche != Keyboard.KEY_RETURN) {
                touche = env.getKey();
                env.advanceOneFrame();
            }
            if (touche != Keyboard.KEY_RETURN) {
                if ((touche == Keyboard.KEY_SUBTRACT || touche == Keyboard.KEY_MINUS) && nomJoueur.length() > 0) {
                    nomJoueur += "-";
                } else {
                    nomJoueur += getLetter(touche);
                }
                textNomJoueur.modify("Choisissez un nom de joueur : " + nomJoueur);
            }
        }
        textNomJoueur.erase();
        return nomJoueur;
    }

    
    /**
     * Menu principal
     *
     * @return le choix du joueur
     */
    private MENU_VAL menuPrincipal() {

        MENU_VAL choix = MENU_VAL.MENU_CONTINUE;
        String nomJoueur;

        // restaure la room du menu
        env.setRoom(menuRoom);

        // affiche le menu principal
        textMenuQuestion.display();
        textMenuPrincipal1.display();
        textMenuPrincipal2.display();
        textMenuPrincipal3.display();

        // vérifie qu'une touche 1, 2 ou 3 est pressée
        int touche = 0;
        while (!(touche == Keyboard.KEY_1 || touche == Keyboard.KEY_2 || touche == Keyboard.KEY_3)) {
            touche = env.getKey();
            env.advanceOneFrame();
        }

        // efface le menu
        textMenuQuestion.erase();
        textMenuPrincipal1.erase();
        textMenuPrincipal2.erase();
        textMenuPrincipal3.erase();

        // et décide quoi faire en fonction de la touche pressée
        switch (touche) {
            // -------------------------------------
            // Touche 1 : Charger un profil existant
            // -------------------------------------
            case Keyboard.KEY_1:
                // demande le nom du joueur existant
                nomJoueur = getNomJoueur();
                // charge le profil de ce joueur si possible
                if (profil.charge(nomJoueur)) {
                    // lance le menu de jeu et récupère le choix à la sortie de ce menu de jeu
                    choix = menuJeu();
                } else {
                    // sinon continue (et boucle dans ce menu)
                    choix = MENU_VAL.MENU_CONTINUE;
                }
                break;

            // -------------------------------------
            // Touche 2 : Créer un nouveau joueur
            // -------------------------------------
            case Keyboard.KEY_2:
                // demande le nom du nouveau joueur
                nomJoueur = getNomJoueur();
                // crée un profil avec le nom d'un nouveau joueur
                profil = new Profil(nomJoueur);
                // lance le menu de jeu et récupère le choix à la sortie de ce menu de jeu
                choix = menuJeu();
                break;

            // -------------------------------------
            // Touche 3 : Sortir du jeu
            // -------------------------------------
            case Keyboard.KEY_3:
                // le choix est de sortir du jeu (quitter l'application)
                choix = MENU_VAL.MENU_SORTIE;
        }
        return choix;
    }

    
    /**
     * Menu de jeu
     *
     * @return le choix du joueur une fois la partie terminée
     */
    private MENU_VAL menuJeu() {

        MENU_VAL playTheGame;
        playTheGame = MENU_VAL.MENU_JOUE;
        Partie partie;
        do {
            // restaure la room du menu
            env.setRoom(menuRoom);

            // affiche le menu de jeu
            textMenuQuestion.display();
            textMenuJeu1.display();
            textMenuJeu2.display();
            textMenuJeu3.display();
            textMenuJeu4.display();

            // vérifie qu'une touche 1, 2, 3 ou 4 est pressée
            int touche = 0;
            while (!(touche == Keyboard.KEY_1 || touche == Keyboard.KEY_2 || touche == Keyboard.KEY_3 || touche == Keyboard.KEY_4)) {
                touche = env.getKey();
                env.advanceOneFrame();
            }

            // efface le menu
            textMenuQuestion.erase();
            textMenuJeu1.erase();
            textMenuJeu2.erase();
            textMenuJeu3.erase();
            textMenuJeu4.erase();

            // restaure la room du jeu
            env.setRoom(mainRoom);

            // et décide quoi faire en fonction de la touche pressée
            switch (touche) {
                // -----------------------------------------
                // Touche 1 : Commencer une nouvelle partie
                // -----------------------------------------                
                case Keyboard.KEY_1: // choisi un niveau et charge un mot depuis le dico
                    
                    //choix du niveau
                    textMenuQ1.display();
                    textMenuR1.display();
                    textMenuR2.display();
                    textMenuR3.display();
                    textMenuR4.display();                    
                    textMenuR5.display();   
                    
                    int touche1 = 0;
                    while (!(touche1 == Keyboard.KEY_1 || touche1 == Keyboard.KEY_2 || touche1 == Keyboard.KEY_3 || touche1 == Keyboard.KEY_4 || touche1 == Keyboard.KEY_5) ) {
                        touche1 = env.getKey();
                        env.advanceOneFrame();
                    }
                    
                   
                    switch(touche1){
                        case Keyboard.KEY_1 :
                            this.niveau = 1;
                            break;
                        case Keyboard.KEY_2 :
                            this.niveau = 2;
                            break;    
                        case Keyboard.KEY_3 :
                            this.niveau = 3;
                            break;    
                        case Keyboard.KEY_4 :
                            this.niveau = 4;
                            break;    
                        case Keyboard.KEY_5 :
                            this.niveau = 5;
                            break;    
                        default : break;    
                    }
                    
                    // efface le menu
                    textMenuQ1.erase();
                    textMenuR1.erase();
                    textMenuR2.erase();
                    textMenuR3.erase();
                    textMenuR4.erase();
                    textMenuR5.erase();  
                    
                    // restaure la room du jeu
                    env.setRoom(mainRoom);
                    
                    // .......... dico ...........
                    //choix du mot à deviner
                    this.mot = dico.getMotDepuisListeNiveau(this.niveau);                      
                    //date
                    date = new SimpleDateFormat("dd/mm/aaaa_hh:mm:ss").format(Calendar.getInstance().getTime());                    
                    // crée un nouvelle partie
                    partie = new Partie(this.date,this.mot,this.niveau); 
                    // joue
                    joue(partie);
                    
                    // enregistre la partie dans le profil --> enregistre le profil
                    // .......... profil .........
                    playTheGame = MENU_VAL.MENU_JOUE;
                    break;

                // -----------------------------------------
                // Touche 2 : Charger une partie existante
                // -----------------------------------------                
                case Keyboard.KEY_2: // charge une partie existante
                    // demander de fournir une date
                    // ..........
                    // tenter de trouver une partie à cette date
                    partie = new Partie("","",0); /******/
                    // .......
                    // Si partie trouvée, recupère le mot de la partie existante, sinon ???
                    // ..........
                    // ..........
                    // joue
                    joue(partie);
                    // enregistre la partie dans le profil --> enregistre le profil
                    // .......... profil ........
                    playTheGame = MENU_VAL.MENU_JOUE;
                    break;

                // -----------------------------------------
                // Touche 3 : Sortie de ce jeu
                // -----------------------------------------                
                case Keyboard.KEY_3:
                    playTheGame = MENU_VAL.MENU_CONTINUE;
                    break;

                // -----------------------------------------
                // Touche 4 : Quitter le jeu
                // -----------------------------------------                
                case Keyboard.KEY_4:
                    playTheGame = MENU_VAL.MENU_SORTIE;
            }
        } while (playTheGame == MENU_VAL.MENU_JOUE);
        return playTheGame;
    }

    
    /**
     * Méthode joue(partie : Partie) : void
     * 
     */
        public void joue(Partie partie){
         // TEMPORAIRE : on règle la room de l'environnement. Ceci sera à enlever lorsque vous ajouterez les menus.
        env.setRoom(mainRoom);

        // Instancie un Tux
        tux = new Tux(env,mainRoom);
        env.addObject(this.tux);        
        
        // Ici, on peut initialiser des valeurs pour une nouvelle partie
        démarrePartie(partie);        
                
        // Boucle de jeu
        finished = false;  
        while (!finished ) {             
            
            // Contrôles globaux du jeu (sortie, ...)
            //1 is for escape key
            if (env.getKey() == 1) {
                finished = true;               
            }

            // Contrôles des déplacements de Tux (gauche, droite, ...)
            tux.déplace();     
                          
            // Ici, on applique les regles
            appliqueRegles(partie);
 
            // Fait avancer le moteur de jeu (mise à jour de l'affichage, de l'écoute des événements clavier...)
            env.advanceOneFrame();
        }
        
        // Ici on peut calculer des valeurs lorsque la partie est terminée
        terminePartie(partie);
        
    }    
    
    
    //generer les coordonnees alétoirement
    public double randomDouble(double min, double max) {
        double range = max - min + 1.0 ;
        // generate random numbers 
        double rand = (double)(Math.random() * range) + min;
        
        if ( (rand != tux.getX() || rand != tux.getY()) && rand != mainRoom.getDepth() && rand != mainRoom.getWidth()) {
            return rand;
        } else
            rand = randomDouble(min, max);
        return rand;
    }
    
    //découpe le mot string en tableau de char
    public char[] decouppeMot(String mot){
        char motdecoupé[];
        motdecoupé = new char[mot.length()];
        
        for (int i=0; i<mot.length(); i++){
            motdecoupé[i] = mot.charAt(i);
        }
        
        return motdecoupé;
    }
    
    
    //distance entre la lettre et le tux pour la collision
    protected double distance(Letter letter){
        double dx = letter.getX()-tux.getX(); //largeur
        //double dy = tux.getY()-letter.getY(); //hauteur
        double dz = letter.getZ()-tux.getZ(); //profondeur
        double distance =  Math.sqrt(Math.pow(dx,2) /*+ Math.pow(dy,2)*/ + Math.pow(dz,2)) ;
        return distance; //tux - lettres
    }
    
    
     protected boolean collision(Letter letter){
        if ( (distance(letter) > 4) || (distance(letter) < -4)  ){  //scale du tux = 3
            return false;
        }
        return true;
    }
    
    
    
    
    /**
     * Méthode abstraite démarrePartie(partie : Partie) : void
     * 
     */
     protected abstract void démarrePartie(Partie partie);
    
    
    /**
     * Méthode abstraite appliqueRègles(partie : Partie) : void
     * 
     */
    protected abstract void appliqueRegles(Partie partie);
    
    /**
     * Méthode abstraite terminePartie(partie : Partie) : void
     * 
     */
     protected abstract void terminePartie(Partie partie);
    
}
