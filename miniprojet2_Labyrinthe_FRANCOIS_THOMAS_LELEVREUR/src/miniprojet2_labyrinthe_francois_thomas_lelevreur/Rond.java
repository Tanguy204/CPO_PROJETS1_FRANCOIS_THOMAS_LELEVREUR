/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package miniprojet2_labyrinthe_francois_thomas_lelevreur;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JLabel;

/**
 *
 * @author Tanguy
 */
public class Rond extends JLabel{
    String clr;
    public Rond(String CLR){
        clr=CLR;
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
    
    
}
