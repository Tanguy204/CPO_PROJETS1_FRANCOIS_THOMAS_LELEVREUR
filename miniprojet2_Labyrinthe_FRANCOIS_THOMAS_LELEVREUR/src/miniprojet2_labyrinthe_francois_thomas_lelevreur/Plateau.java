/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package miniprojet2_labyrinthe_francois_thomas_lelevreur;

import static java.lang.Math.random;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;
import javax.swing.ImageIcon;

/**
 *
 * @author Tanguy
 */
public final class Plateau {

    TuileCouloir[][] Labyrinthe;
    CarteObjectif[][] objectifs;
    

    TuileCouloir TuilePoussoire;
    Pion[] Pions;
    Pion[][] classement;
    int nbJoueurs;

    public Plateau(int NBJoueurs) {
        nbJoueurs = NBJoueurs;
        Labyrinthe = new TuileCouloir[7][7];
        Pions = new Pion[nbJoueurs] ;
        
        Initialisation();

    }
    public void avancerDroite(Pion pion){
        
        if (getCoteD(pion.x,pion.y)==true ) {
            pion.y+=1;
        }
        pion.actualiser();
        
    }
        public void avancerGauche(Pion pion){
        
        if (getCoteG(pion.x,pion.y)==true ) {
            pion.y-=1;
        }
        pion.actualiser();
    }
     public void avancerHaut(Pion pion){
        
        if (getCoteH(pion.x,pion.y)==true ) {
            pion.x-=1;
        }
        pion.actualiser();
    }       
    public void avancerBas(Pion pion){
        
        if (getCoteB(pion.x,pion.y)==true ) {
            pion.x+=1;
        }
        pion.actualiser();
    }
           
    
    public boolean getCoteH(int x, int y ){
        if (x==0||Labyrinthe[x][y].CoteHaut==false ||Labyrinthe[x-1][y].CoteBas==false){
            return false;
        }else{
            return true;
        }
    }
    public boolean getCoteB(int x, int y ){
        if (x==6||Labyrinthe[x][y].CoteBas==false ||Labyrinthe[x+1][y].CoteHaut==false){
            return false;
        }else{
            return true;
        }
    }
    public boolean getCoteD(int x, int y ){
        if (y==6||Labyrinthe[x][y].CoteDroit==false ||Labyrinthe[x][y+1].CoteGauche==false){
            return false;
        }else{
            return true;
        }
    }
    public boolean getCoteG(int x, int y ){
        if (y==0||Labyrinthe[x][y].CoteGauche==false ||Labyrinthe[x][y-1].CoteDroit==false){
            return false;
        }else{
            return true;
        }
    }

    public void PousserLigneD(int x) {
        TuileCouloir TuileSauv = Labyrinthe[x][6];
        Labyrinthe[x][6] = Labyrinthe[x][5];
        Labyrinthe[x][5] = Labyrinthe[x][4];
        Labyrinthe[x][4] = Labyrinthe[x][3];
        Labyrinthe[x][3] = Labyrinthe[x][2];
        Labyrinthe[x][2] = Labyrinthe[x][1];
        Labyrinthe[x][1] = Labyrinthe[x][0];
        Labyrinthe[x][0] = TuilePoussoire;
        TuilePoussoire = TuileSauv;
        for ( int i =0; i<Pions.length;i++){
            
            if (Pions[i].x==x){
                if (Pions[i].y==6){
                    Pions[i].y=0;
                    
                }else{
                Pions[i].y+=1;
            }
                
            Pions[i].actualiser();
        }
    }
    }

    public void PousserColonneB(int x) {
        TuileCouloir TuileSauv = Labyrinthe[6][x];
        Labyrinthe[6][x] = Labyrinthe[5][x];
        Labyrinthe[5][x] = Labyrinthe[4][x];
        Labyrinthe[4][x] = Labyrinthe[3][x];
        Labyrinthe[3][x] = Labyrinthe[2][x];
        Labyrinthe[2][x] = Labyrinthe[1][x];
        Labyrinthe[1][x] = Labyrinthe[0][x];
        Labyrinthe[0][x] = TuilePoussoire;
        TuilePoussoire = TuileSauv;
        for ( int i =0; i<Pions.length;i++){
            
            if (Pions[i].y==x)
                if (Pions[i].x==6){
                    
                   Pions[i].x=0; 
                }else{
                Pions[i].x+=1;
                }
            
            Pions[i].actualiser();
        
    }
    }

    public void PousserColonneH(int x) {
        TuileCouloir TuileSauv = Labyrinthe[0][x];
        Labyrinthe[0][x] = Labyrinthe[1][x];
        Labyrinthe[1][x] = Labyrinthe[2][x];
        Labyrinthe[2][x] = Labyrinthe[3][x];
        Labyrinthe[3][x] = Labyrinthe[4][x];
        Labyrinthe[4][x] = Labyrinthe[5][x];
        Labyrinthe[5][x] = Labyrinthe[6][x];
        Labyrinthe[6][x] = TuilePoussoire;
        TuilePoussoire = TuileSauv;
        for ( int i =0; i<Pions.length;i++){
            
            if (Pions[i].y==x){
                if (Pions[i].x==0){
                    
                   Pions[i].x=6; 
                }else{
                Pions[i].x-=1;
                }
            }
            
                
            Pions[i].actualiser();
        
        }

    }

    public void PousserLigneG(int x) {
        TuileCouloir TuileSauv = Labyrinthe[x][0];
        Labyrinthe[x][0] = Labyrinthe[x][1];
        Labyrinthe[x][1] = Labyrinthe[x][2];
        Labyrinthe[x][2] = Labyrinthe[x][3];
        Labyrinthe[x][3] = Labyrinthe[x][4];
        Labyrinthe[x][4] = Labyrinthe[x][5];
        Labyrinthe[x][5] = Labyrinthe[x][6];
        Labyrinthe[x][6] = TuilePoussoire;
        TuilePoussoire = TuileSauv;
        for ( int i =0; i<Pions.length;i++){
             
            if (Pions[i].x==x){
                
                   
                
            if (Pions[i].y==0){
                   Pions[i].y=6;
                    
                }else {
                Pions[i].y-=1;
                
                }
            }
          
            
          Pions[i].actualiser();
        }
        
    }
    

    public void Initialisation() {
        ArrayList<TuileCouloir> PileDeTuiles = new ArrayList<>();
        ArrayList<CarteObjectif> PileDeCartes = new ArrayList<>();
        Labyrinthe = new TuileCouloir[7][7];
        Pions[0]= new Pion("J",Labyrinthe)   ;
        
        if (nbJoueurs>1){
            
        Pions[1]= new Pion("B",Labyrinthe)   ;
        }
        if (nbJoueurs>2){
    Pions[2]= new Pion("V",Labyrinthe )   ;
        }
    if (nbJoueurs>3){
    Pions[3]= new Pion("R",Labyrinthe )   ;
    }
    
        Labyrinthe[6][0] = new TuileCouloir(false, true, true, false, null, "/images/tuile2.png");
        Labyrinthe[6][0].TournerTuileD();
        Labyrinthe[6][0].TournerTuileD();
        Labyrinthe[6][0].TournerTuileD();
        
        Labyrinthe[0][0] = new TuileCouloir(false, true, true, false, null, "/images/tuile2.png");
        Labyrinthe[6][6] = new TuileCouloir(false, true, true, false, null, "/images/tuile2.png");
        Labyrinthe[6][6].TournerTuileD();
        Labyrinthe[6][6].TournerTuileD();
        Labyrinthe[0][6] = new TuileCouloir(false, true, true, false, null, "/images/tuile2.png");
        Labyrinthe[0][6].TournerTuileD();
        Labyrinthe[0][2] = new TuileCouloir(false, true, true, true, "heaume", "/images/heaume.png");
        Labyrinthe[0][4] = new TuileCouloir(false, true, true, true, "chandelier", "/images/chandelier.png");
        Labyrinthe[2][0] = new TuileCouloir(true, true, true, false, "epee", "/images/epee.png");
        Labyrinthe[2][2] = new TuileCouloir(true, true, true, false, "saphir", "/images/saphir.png");
        Labyrinthe[2][4] = new TuileCouloir(false, true, true, true, "tresor", "/images/tresor.png");
        Labyrinthe[2][6] = new TuileCouloir(true, true, false, true, "bague", "/images/bague.png");
        Labyrinthe[4][0] = new TuileCouloir(true, true, true, false, "crane", "/images/crane.png");
        Labyrinthe[4][2] = new TuileCouloir(true, false, true, true, "cle", "/images/cle.png");
        Labyrinthe[4][4] = new TuileCouloir(true, true, false, true, "couronne", "/images/couronne.png");
        Labyrinthe[4][6] = new TuileCouloir(true, true, false, true, "carteTresor", "/images/carteTresor.png");
        Labyrinthe[6][2] = new TuileCouloir(true, false, true, true, "bourse", "/images/bourse.png");
        Labyrinthe[6][4] = new TuileCouloir(true, false, true, true, "livre", "/images/livre.png");
        PileDeTuiles.add(new TuileCouloir(true, false, false, true, "araignee", "/images/araignee.png"));
        PileDeTuiles.add(new TuileCouloir(true, false, true, true, "chauvesouris", "/images/chauvesouris.png"));
        PileDeTuiles.add(new TuileCouloir(false, true, true, false, "chouette", "/images/chouette.png"));
        PileDeTuiles.add(new TuileCouloir(true, false, true, true, "dragon", "/images/dragon.png"));
        PileDeTuiles.add(new TuileCouloir(true, false, true, true, "fantome", "/images/fantome.png"));
        PileDeTuiles.add(new TuileCouloir(true, false, true, true, "fee", "/images/fee.png"));
        PileDeTuiles.add(new TuileCouloir(true, false, true, true, "genie", "/images/genie.png"));
        PileDeTuiles.add(new TuileCouloir(true, false, true, true, "gobelin", "/images/gobelin.png"));
        PileDeTuiles.add(new TuileCouloir(true, false, false, true, "lezard", "/images/lezard.png"));
        PileDeTuiles.add(new TuileCouloir(true, false, false, true, "papillon", "/images/papillon.png"));
        PileDeTuiles.add(new TuileCouloir(false, true, false, true, "rat", "/images/rat.png"));
        PileDeTuiles.add(new TuileCouloir(true, false, true, false, "scarabee", "/images/scarabee.png"));
        for (int i = 0; i < 11; i++) {
            PileDeTuiles.add(new TuileCouloir(true, true, false, false, null, "/images/tuile1.png"));
        }
        for (int i = 0; i < 11; i++) {
            PileDeTuiles.add(new TuileCouloir(false, true, true, false, null, "/images/tuile2.png"));
        }
        PileDeCartes.add(new CarteObjectif("heaume", "/images/heaumeO.png"));
        PileDeCartes.add(new CarteObjectif("chandelier", "/images/chandelierO.png"));
        PileDeCartes.add(new CarteObjectif("epee", "/images/epeeO.jpg"));
        PileDeCartes.add(new CarteObjectif("saphir", "/images/saphirO.png"));
        PileDeCartes.add(new CarteObjectif("tresor", "/images/tresorO.png"));
        PileDeCartes.add(new CarteObjectif("bague", "/images/bagueO.jpg"));
        PileDeCartes.add(new CarteObjectif("crane", "/images/craneO.jpg"));
        PileDeCartes.add(new CarteObjectif("cle", "/images/cleO.png"));
        PileDeCartes.add(new CarteObjectif("couronne", "/images/couronneO.png"));
        PileDeCartes.add(new CarteObjectif("carteTresor", "/images/carteTresorO.png"));
        PileDeCartes.add(new CarteObjectif("bourse", "/images/bourseO.png"));
        PileDeCartes.add(new CarteObjectif("livre", "/images/livreO.jpg"));
        PileDeCartes.add(new CarteObjectif("araignee", "/images/araigneeO.png"));
        PileDeCartes.add(new CarteObjectif("chauvesouris", "/images/chauvesourisO.jpg"));
        PileDeCartes.add(new CarteObjectif("chouette", "/images/chouetteO.jpg"));
        PileDeCartes.add(new CarteObjectif("dragon", "/images/dragonO.png"));
        PileDeCartes.add(new CarteObjectif("fantome", "/images/fantomeO.png"));
        PileDeCartes.add(new CarteObjectif("fee", "/images/feeO.png"));
        PileDeCartes.add(new CarteObjectif("genie", "/images/genieO.png"));
        PileDeCartes.add(new CarteObjectif("gobelin", "/images/gobelinO.png"));
        PileDeCartes.add(new CarteObjectif("lezard", "/images/lezardO.png"));
        PileDeCartes.add(new CarteObjectif("papillon", "/images/papillonO.jpg"));
        PileDeCartes.add(new CarteObjectif("rat", "/images/ratO.png"));
        PileDeCartes.add(new CarteObjectif("scarabee", "/images/scarabeeO.jpg"));

        for(int b =0; b<5;b++){
            Collections.shuffle(PileDeCartes);
            Collections.shuffle(PileDeTuiles);
        }
        if (nbJoueurs==1){
            
            Pions[0].cartesObjs = new CarteObjectif[12];
            for (int p = 0;p<12;p++){
                
                
                Pions[0].cartesObjs[p]= PileDeCartes.get(1);
                PileDeCartes.remove(Pions[0].cartesObjs[p]);
                
                
            }
            
        }
        
        if (nbJoueurs==2){
            
            
            for (int i = 0;i<2;i++){
                Pions[i].cartesObjs = new CarteObjectif[12];
                for (int j = 0;j<12;j++){
                    
                
                Pions[i].cartesObjs[j] = PileDeCartes.get(0);
                PileDeCartes.remove(Pions[i].cartesObjs[j]);
                
                
                
            }
        }
        }
        if (nbJoueurs==3){
            
            for (int i = 0;i<3;i++){
                Pions[i].cartesObjs = new CarteObjectif[8];
                for (int j = 0;j<8;j++){
                Pions[i].cartesObjs[j] = PileDeCartes.get(0);
                
                PileDeCartes.remove(Pions[i].cartesObjs[j]);
               
            }
        }
        }
        if (nbJoueurs==4){
            
            for (int i = 0;i<4;i++){
                Pions[i].cartesObjs = new CarteObjectif[6];
                for (int j = 0;j<6;j++){
                Pions[i].cartesObjs[j] = PileDeCartes.get(0);    
                
                PileDeCartes.remove(Pions[i].cartesObjs[j]);
                
            }
        }
        }
        
        
        
        
        Random random = new Random();
        int x =33;
        for (int y = 0; y < 3; y++) {

            for (int i = 0; i < 7; i++) {

                int Col = (2 * y) + 1;

                
                int Spin = random.nextInt(4);

                Labyrinthe[i][Col] = PileDeTuiles.get(x);

                for (int j = 0; j < Spin; j++) {
                    Labyrinthe[i][Col].TournerTuileD();
                }
                PileDeTuiles.remove(Labyrinthe[i][Col]);

                x -= 1;
            }
        }

        for (int y = 0; y < 3; y++) {

            for (int i = 0; i < 4; i++) {

                int Lig = (2 * y) + 1;
                int Col = (2 * i);

                int Spin = random.nextInt(3);
                Labyrinthe[Lig][Col] = PileDeTuiles.get(x);

                for (int j = 0; j < Spin; j++) {
                    Labyrinthe[Lig][Col].TournerTuileD();
                }
                PileDeTuiles.remove(Labyrinthe[Lig][Col]);
                x -= 1;
            }
        }

        TuilePoussoire = PileDeTuiles.get(0);
        for (int c = 0 ;c <nbJoueurs;c++){
            for (int o=0;o<x/nbJoueurs;o++){
             objectifs[o][c]=PileDeCartes.get(o);
             PileDeCartes.remove(objectifs[o][c]);
        }
            
    }
    
    

    }
public void classement() {
    classement = new Pion[nbJoueurs][nbJoueurs] ;
     Pion[] pionBis = new Pion[Pions.length];
     
     for (int l =0; l<Pions.length;l++){
         pionBis[l]=Pions[l];
     }

    Arrays.sort(pionBis, 0, nbJoueurs, Comparator.comparingInt(Pion::getCompt).reversed());
    boolean exaequo =false;
    for (int i = 0; i < nbJoueurs; i++) {
        exaequo =false;
        for (int j = 1; j<(nbJoueurs+1); j++) {
            int k=nbJoueurs-j;
            
            if (pionBis[i].compt==pionBis[k].compt ){
                System.out.println("j"+j);
                int Qcol=0;
                while(classement[k][Qcol]!=null){
                    Qcol+=1;
                    
                }
                classement[k][Qcol]=pionBis[i];
                exaequo =true;
                break;
                
            }
        }
        if (exaequo=false){
            classement[i][0]=pionBis[i];
        }
    }
    

    // Placer les pions triés dans le tableau de classement
   
    for (int i = 0; i < nbJoueurs; i++) {
        for (int j = 0; j < nbJoueurs; j++) {
            if (classement[i][j] != null) {
                System.out.println("Classement [" + i + "][" + j + "]: couleur=" + classement[i][j].clr + ", compt=" + classement[i][j].compt);
            }
        }
    }
}
}

