/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package miniprojet2_labyrinthe_francois_thomas_lelevreur;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Tanguy
 */
public class Pion extends JLabel {
    int y ;
    int x;
    int a ; 
    int b;
    int carteValidées;
    CarteObjectif[] cartesObjs;
    boolean gagné;
    
    
    String clr;
    
    public Pion(String Clr ){
       carteValidées=0;
        
        clr=Clr;
        
        gagné = false;
        if (Clr=="J"){
            x=6;
            y=0;
            a=-2;
            b=-2;
        }
        if (Clr=="R"){
            x=6;
            y=6;
            a=2;
            b=2;
        }
        if (Clr=="B"){
            x=0;
            y=0;
            a=-2;
            b=2;
                    
        }
        if (Clr=="V"){
            x=0;
            y=6;
            a = 2;
            b = -2;
        }
        
        setBounds(64*y+32+a,64*x+32+b, 25, 25);
        
        
        
         
    }
    
     public void actualiser(){
        this.setBounds(64*(y)+32+a,64*(x)+32+b, 25, 25);
    }
    
    @Override
   
    protected void paintComponent(Graphics g) {
        int w = this.getWidth();
        int h = this.getHeight();
        
        if (clr == "R") {
            g.setColor(Color.red);
            
        } 
        if (clr == "J") {
            g.setColor(Color.yellow);
            
        }
        if (clr == "V") {
            g.setColor(Color.green);
            
        }
        if (clr == "B") {
            g.setColor(Color.blue);
           
        }
        g.fillOval(3, 1, w - 4, h - 4);
    }
    
public boolean compteCarteV(){
    int t = 0;
    while(cartesObjs[t].Valide == true  ){
        
            
        
        t+=1;
        if (t==cartesObjs.length){
            return true;
        }
    }
    return false;
}  

    
}
