package game;

import env3d.Env;
import env3d.advanced.EnvNode;
import org.lwjgl.input.Keyboard;

/**
 *
 * @author dothit
 */
public class Tux  extends EnvNode{
    private Env env;
    private mainRoom room;

    /**
     * Constructeur du personnage Tux
     *
     * @param env
     * @param room
     */
    public Tux(Env env, mainRoom room){
        //Dans le constructeur
        this.env = env;
        this.room = room;
        
        setScale(5.0);
        setX( this.room.getWidth()/2 );// positionnement au milieu de la largeur de la room
        setY(getScale() * 1.1); // positionnement en hauteur basé sur la taille de Tux
        setZ( this.room.getDepth()/2 ); // positionnement au milieu de la profondeur de la room
        setTexture("models/tux/tux_happy.png");
        setModel("models/tux/tux.obj"); 
        
    }

    //déplacement du tux dans l'env elon les touches du clavier
    /**
     * Methode qui assure le deplacement de tux en fonction des touches
     * assignées à cet effet
     *
     */
    public void déplace() {    
        
        if (env.getKeyDown(Keyboard.KEY_Z) || env.getKeyDown(Keyboard.KEY_UP)) { // Fleche 'haut' ou Z
            // Haut
            this.setRotateY(180);
            if(testeRoomCollision(this.getX() , this.getZ() - 1.0)){
                this.setZ(this.getZ() - 1.0);
            }
        }
        if (env.getKeyDown(Keyboard.KEY_S) || env.getKeyDown(Keyboard.KEY_DOWN)) { // Fleche 'bas' ou S
           // Bas
            this.setRotateY(360);
            if(testeRoomCollision(this.getX() , this.getZ() + 1.0)){
                this.setZ(this.getZ() + 1.0);
            }
        }
        if (env.getKeyDown(Keyboard.KEY_Q) || env.getKeyDown(Keyboard.KEY_LEFT)) { // Fleche 'gauche' ou Q
            // Gauche
            this.setRotateY(-90);
            if(testeRoomCollision(this.getX() - 1.0 , this.getZ())){
                this.setX(this.getX() - 1.0);
            }
        }
        if (env.getKeyDown(Keyboard.KEY_D) || env.getKeyDown(Keyboard.KEY_RIGHT)) { // Fleche 'droite' ou D
            // Droite
            this.setRotateY(90);
            if(testeRoomCollision(this.getX() + 1.0 , this.getZ())){
                this.setX(this.getX() + 1.0);
            }
        }
        
    }
    
    //limite des déplacements en fonction de l'environnement (largeur et profondeur)
    /**
     * Teste la collision de tux avec les limites de l'environnement et
     * l'empeche d'en sortir. Retourne True si si est au bord de
     * l'environnement.
     *
     * @return boolean
     */
    private Boolean testeRoomCollision ( double x, double z){
        if( (( (this.room.getDepth()- getScale()) > z) && ( 0.0 + getScale()  < z) ) && ( (this.room.getWidth() - getScale())> x) && (0.0 + getScale() < x) ) {
            return true;
        }
        return false;
    }  
    
}
