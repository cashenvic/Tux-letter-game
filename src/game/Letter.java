/*

 */
package game;

import env3d.Env;
import env3d.advanced.EnvNode;

public class Letter extends EnvNode {
    private char letter;
    
    public Letter() {
        
    }
        
     //ajouter le code nécéssaire à l'affichage d'un cube sans lettre si un espace est donné en paramètre du constructeur
    public Letter(char l, double x, double y) {
        this.letter = l;
        setScale(3.0);
        setX(x);// positionnement au milieu de la largeur de la room
        setY(getScale() * 1.1); // positionnement en hauteur basé sur la taille de Tux
        setZ(y); // positionnement au milieu de la profondeur de la room

        if (Character.isWhitespace(l)) {
            setTexture("models/cube/cube.png");

        } else {
            setTexture("models/letter/" + l + ".png");
        }
        setModel("models/letter/cube.obj");
    }

    public char getLetter() {
        return letter;
    }
    
    
}
