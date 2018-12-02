/*

 */
package game;

import env3d.Env;
import env3d.advanced.EnvNode;

public class Letter extends EnvNode {
    private char letter;
        
    //ajouter le code nécéssaire à l'affichage d'un cube sans lettre si un espace est donné en paramètre du constructeur
    /**
     * Construit une lettre du jeu avec un caractère, une position x(largeur) et
     * z(profondeur)
     *
     * @param l
     * @param x
     * @param z
     */
    public Letter(char l, double x, double z) {
        this.letter = l;
        setScale(2.0);
        setX(x);// positionnement de la largeur de la room
        setY(getScale() * 1.1); // positionnement en hauteur 
        setZ(z); // positionnement e la profondeur de la room

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
