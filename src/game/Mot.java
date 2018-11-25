/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

/**
 *
 * @author hanh1
 */
public class Mot {
	private int niveau; 
	private String mot; 
  
	public Mot(){
        } 
  
	public int getNiveau(){
            return niveau;
        } 
	public String getMot(){
            return mot;
        } 
  
	public void setNiveau(int niveau){
            this.niveau = niveau;
        } 
	public void setMot(String mot){
            this.mot = mot;
        } 
        
	public String toString(){ 
		return new StringBuffer("Niveau : ").append(niveau).append(", ") 
			.append("Mot : ").append(mot)
			.toString(); 
	} 
}

