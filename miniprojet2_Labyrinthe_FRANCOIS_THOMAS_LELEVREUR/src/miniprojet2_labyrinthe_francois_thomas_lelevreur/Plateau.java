/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package miniprojet2_labyrinthe_francois_thomas_lelevreur;
import java.util.ArrayList;
import java.util.Random;
/**
 *
 * @author Tanguy
 */
public class Plateau {
    TuileCouloir[][]  Labyrinthe;
    public ArrayList<TuileCouloir> PileDeTuiles ;
    TuileCouloir TuilePoussoire;
    
    public Plateau(){
        PileDeTuiles = new ArrayList<>();
        Labyrinthe = new TuileCouloir[7][7];
        Labyrinthe[6][0] = new TuileCouloir(true,false,true,false,null);
        Labyrinthe[0][0] = new TuileCouloir(false,true,true,false,null);
        Labyrinthe[6][6] = new TuileCouloir(true,false,false,true,null);
        Labyrinthe[0][6] = new TuileCouloir(false,true,false,true,null);
        Labyrinthe[0][2] = new TuileCouloir(false,true,true,true,"heaume");
        Labyrinthe[0][4] = new TuileCouloir(false,true,true,true,"chandelier");
        Labyrinthe[2][0] = new TuileCouloir(true,true,true,false,"epee");
        Labyrinthe[2][2] = new TuileCouloir(true,true,true,false,"saphir");
        Labyrinthe[2][4] = new TuileCouloir(false,true,true,true,"tresor");
        Labyrinthe[2][6] = new TuileCouloir(true,true,false,true,"bague");
        Labyrinthe[4][0] = new TuileCouloir(true,true,true,false,"crane");
        Labyrinthe[4][2] = new TuileCouloir(true,false,true,true,"cle");
        Labyrinthe[4][4] = new TuileCouloir(true,true,false,true,"couronne");
        Labyrinthe[4][6] = new TuileCouloir(true,true,false,true,"carteTresor");
        Labyrinthe[6][2] = new TuileCouloir(true,false,true,true,"bourse");
        Labyrinthe[6][4] = new TuileCouloir(true,false,true,true,"livre");
        PileDeTuiles.add(new TuileCouloir(true,false,false,true,"araigné"));
        PileDeTuiles.add(new TuileCouloir(true,false,false,true,"chauvesouris"));
        PileDeTuiles.add(new TuileCouloir(false,true,true,false,"chouette"));
        PileDeTuiles.add(new TuileCouloir(true,false,false,true,"araigné"));
        PileDeTuiles.add(new TuileCouloir(true,false,true,true,"dragon"));
        PileDeTuiles.add(new TuileCouloir(true,false,true,true,"fantome"));
        PileDeTuiles.add(new TuileCouloir(true,false,true,true,"fee"));
        PileDeTuiles.add(new TuileCouloir(true,false,true,true,"genie"));
        PileDeTuiles.add(new TuileCouloir(true,false,true,true,"gobelin"));
        PileDeTuiles.add(new TuileCouloir(true,false,false,true,"lezard"));
        PileDeTuiles.add(new TuileCouloir(true,false,false,true,"papillon"));
        PileDeTuiles.add(new TuileCouloir(false,true,false,true,"rat"));
        PileDeTuiles.add(new TuileCouloir(true,false,true,false,"scarabee"));
        for (int i = 0 ; i<11;i++){
            PileDeTuiles.add(new TuileCouloir(true,false,true,false,null));
        }
        for (int i =0 ; i<10;i++){
           PileDeTuiles.add(new TuileCouloir(true,false,false,true,null));
        }
        Random random = new Random();
        for ( int y = 0; y<3;y++){
            int compt=0;
        for (int i = 0; i<7;i++){
            compt+=1;
            int Col=(2*y)+1;
            int x = random.nextInt(34-compt);
            int Spin = random.nextInt(3);
            Labyrinthe[i][Col]= PileDeTuiles.get(x);
            for(int j =0; j<Spin;j++){
                Labyrinthe[i][Col].TournerTuileD();
            }
            PileDeTuiles.remove(Labyrinthe[i][Col]);
        }
        }
        for ( int y = 0; y<3;y++){
            int compt2=0;
        for (int i = 0; i<4;i++){
            compt2+=1;
            int Col=(2*y)+1;
            int Lig =(2*i)+1;
            int x = random.nextInt(13-compt2);
            int Spin = random.nextInt(3);
            Labyrinthe[Lig][Col]= PileDeTuiles.get(x);
            for(int j =0; j<Spin;j++){
                Labyrinthe[Lig][Col].TournerTuileD();
            }
            PileDeTuiles.remove(Labyrinthe[Lig][Col]);
        }
        }
        TuilePoussoire = PileDeTuiles.get(0);
        
        
        }
        
    
}
