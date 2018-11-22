/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import game.Dico;


public class TestDico {
   public static void main(String [] args){  
        Dico dico = new Dico("dico.xml");
        dico.ajouteMotADico(1,"papa");
        dico.ajouteMotADico(1,"mama");
        
        dico.ajouteMotADico(3,"non");
        dico.ajouteMotADico(3,"oui");
        
        dico.ajouteMotADico(5,"do");
        dico.ajouteMotADico(5,"hanh");
        
        dico.ajouteMotADico(4,"cisse");
        dico.ajouteMotADico(4,"drissa");
        
        System.out.println("mot de niveau 1 " + dico.getMotDepuisListeNiveau(1));
        
        System.out.println("mot de niveau 2 " + dico.getMotDepuisListeNiveau(2));
        
        System.out.println("mot de niveau 3 " +dico.getMotDepuisListeNiveau(3));
        
        System.out.println("mot de niveau 4 " +dico.getMotDepuisListeNiveau(4));
        
        System.out.println("mot de niveau 5 "+dico.getMotDepuisListeNiveau(5));
        
        System.out.println("mot de niveau 6 "+dico.getMotDepuisListeNiveau(6));
        
        System.out.println("mot de niveau 0 " +dico.getMotDepuisListeNiveau(0));
       
        
   }
}