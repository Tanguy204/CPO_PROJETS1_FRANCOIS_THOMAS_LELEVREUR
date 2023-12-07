/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package miniprojet2_labyrinthe_francois_thomas_lelevreur;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author Tanguy
 */
public class TuileGraphique extends JButton {

    String Chemin;
    Image imageADessiner;

    public TuileGraphique(String chemin) {
        Chemin = chemin;
        
        repaint();
        ImageIcon imageIcon;

        imageIcon = new ImageIcon(getClass().getResource(Chemin));

        Image imageRedimenssionne = imageIcon.getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH);

        imageADessiner = new ImageIcon(imageRedimenssionne).getImage();
    }

    public void rotationImageDroite() {
        BufferedImage imageRotated = new BufferedImage(imageADessiner.getHeight(null), imageADessiner.getWidth(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = imageRotated.createGraphics();

        // Appliquer la rotation à l'image
        AffineTransform rotation = AffineTransform.getRotateInstance(Math.toRadians(90), 32, 32);
        g2d.setTransform(rotation);
        g2d.drawImage(imageADessiner, 0, 0, null);
        g2d.dispose();

        // Mettre à jour l'image dans l'objet TuileGraphique
        imageADessiner = imageRotated;
    }
    
    

    @Override
    protected void paintComponent(Graphics g) {


        g.drawImage(imageADessiner, 0, 0, this);

    }

}
