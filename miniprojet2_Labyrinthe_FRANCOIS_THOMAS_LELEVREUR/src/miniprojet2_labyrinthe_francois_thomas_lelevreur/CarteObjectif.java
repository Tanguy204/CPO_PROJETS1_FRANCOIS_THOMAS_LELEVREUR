/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package miniprojet2_labyrinthe_francois_thomas_lelevreur;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author Tanguy
 */
public class CarteObjectif extends JButton  {
    String item;
    Plateau plateau;
    Pion proprietaire;
    String Chemin;
    ImageIcon imageADessiner;
    boolean Valide;
    ImageIcon imagededos;
    boolean vision;
    public CarteObjectif(String Item,String chemin){
        item = Item;
        Chemin = chemin;
        
        repaint();
        ImageIcon imageIcon;

        imageIcon = new ImageIcon(Chemin);

        Image imageRedimenssionne = imageIcon.getImage().getScaledInstance(64, 100, Image.SCALE_SMOOTH);
        imageADessiner = new ImageIcon(imageRedimenssionne);
        
        
        ImageIcon imageDeBase = new ImageIcon("C:\\OneDrive - Fondation EPF\\Documents\\Cours\\2ème année\\CPO\\CPO_PROJET_LAB\\dosCarte.png");

        Image newimage = imageDeBase.getImage().getScaledInstance(64, 100, Image.SCALE_SMOOTH);
        imagededos = new ImageIcon(newimage);
        setIcon(imageADessiner);
        
        
        
    }

   
    
    
    public void MontrerCarte(){
        setIcon(imageADessiner);
        vision = true;
    }
    public void CacherCarte(){
        setIcon(imagededos);
        vision = false;
    }
}
