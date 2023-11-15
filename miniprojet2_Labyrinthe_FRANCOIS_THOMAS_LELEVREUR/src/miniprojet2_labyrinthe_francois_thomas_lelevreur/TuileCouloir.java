/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package miniprojet2_labyrinthe_francois_thomas_lelevreur;

import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author Tanguy
 */
public  class TuileCouloir {
    
    int PositionX;
    int PositionY;
    boolean CoteHaut;
    boolean CoteBas;
    boolean CoteDroit;
    boolean CoteGauche;
    String Item;
    
    TuileGraphique Tuile;
    public TuileCouloir(boolean H,boolean B,boolean D,boolean G, String I, String imagePath){
        
        CoteHaut=H;
        CoteBas=B;
        CoteDroit=D;
        CoteGauche=G;
        Item=I;
        
        Tuile= new TuileGraphique(imagePath );
        
    }


public boolean getCoteH(){
    return CoteHaut;
}

public boolean getCoteB(){
    return CoteBas;
}

public boolean getCoteD(){
    return CoteDroit;
}

public boolean getCoteG(){
    return CoteGauche;
}

public void TournerTuileG(){
    boolean haut =CoteHaut;
    CoteHaut=CoteDroit;
    CoteDroit=CoteBas;
    CoteBas=CoteGauche;
    CoteGauche=haut;
}
public void TournerTuileD(){
    boolean haut =CoteHaut;
    CoteHaut=CoteGauche;
    CoteGauche=CoteBas;
    CoteBas=CoteDroit;
    CoteDroit=haut;
    AffineTransform rotation = AffineTransform.getRotateInstance(Math.toRadians(45), 32, 32);
}


}