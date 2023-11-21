/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package miniprojet2_labyrinthe_francois_thomas_lelevreur;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

/**
 *
 * @author Tanguy
 */
public class PlateauGraphique extends javax.swing.JFrame {

    Plateau plateau;
    JPanel PlateauGrph;
    JPanel TuileEnPlus;
    Image carrePointille;

    /**
     * Creates new form PlateauGraphique
     */
    public PlateauGraphique() {
        initComponents();
        this.plateau = new Plateau();
        int x = 64;
        // Vous devez également déclarer y s'il n'est pas déjà déclaré.

        JLayeredPane layeredPane = new JLayeredPane(){
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Image image = new ImageIcon("C:\\OneDrive - Fondation EPF\\Documents\\Cours\\2ème année\\CPO\\CPO_PROJET_LAB\\PlateauFondV2.jpg").getImage();
                    g.drawImage(image, 0, 0, getWidth(), getHeight(), (ImageObserver) this);
                }
            };
        
        getContentPane().add(layeredPane);
        PlateauGrph = new JPanel();
        
        PlateauGrph.setOpaque(false);
        PlateauGrph.setLayout(new GridLayout(7, 7));// Les valeurs RGB pour le bleu marine peuvent varier
        PlateauGrph.setPreferredSize(new Dimension(x * 7, x * 7));
        PlateauGrph.setBounds(12, 12, 7 * x, 7 * x);

        // Ajoutez le panneau au JLayeredPane
        layeredPane.add(PlateauGrph, JLayeredPane.DEFAULT_LAYER); // Utilisez DEFAULT_LAYER ou un autre entier pour spécifier la couche

        // Réglez la taille du JLayeredPane en fonction de la taille du panneau
        layeredPane.setBounds(2*x, 2*x, x * 7+20, x * 7+20);
        add(layeredPane);

        // Le reste de votre code...
        this.pack();
        this.revalidate();

        

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                TuileGraphique boutonTuile = plateau.Labyrinthe[i][j].Tuile; // création d'un bouton
                boutonTuile.setBounds(64 * i, 64 * j, 64, 64); // x, y, largeur, hauteur // ajout au Jpanel PanneauGrille
                PlateauGrph.add(boutonTuile);
            }
        }
        JLayeredPane layeredPane2 = new JLayeredPane(){
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Image image = new ImageIcon("C:\\OneDrive - Fondation EPF\\Documents\\Cours\\2ème année\\CPO\\CPO_PROJET_LAB\\PlateauFondV2.png").getImage();
                    g.drawImage(image, -60, -80, 1200, 902, (ImageObserver) this);
                }
            };
        layeredPane2.setBounds(0, 0, 1200, 902);
        add(layeredPane2);
        defTuileEnPlus( layeredPane2);
        
        defBtnPousser(layeredPane,  layeredPane2);
        
        
        
    }
    
    public void Actualiser(JLayeredPane layeredPane, JLayeredPane layeredPane2) {
    int x = 64;
    
    // Effacez les composants existants du PlateauGrph avant d'ajouter les nouveaux
    PlateauGrph.removeAll();
    TuileEnPlus.removeAll();

    for (int i = 0; i < 7; i++) {
        for (int j = 0; j < 7; j++) {
            TuileGraphique boutonTuile = plateau.Labyrinthe[i][j].Tuile;
            boutonTuile.setBounds(64 * i, 64 * j, 64, 64);
            PlateauGrph.add(boutonTuile);
        }
    }
    
    // Vous devrez peut-être appeler revalidate() et repaint() après avoir modifié le panneau
    PlateauGrph.revalidate();
    PlateauGrph.repaint();
    TuileEnPlus.add(plateau.TuilePoussoire.Tuile);
}
    public void boutonSurvole(JButton button){
        button.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    // Charger et définir une nouvelle image lorsque la souris survole le bouton
                    ImageIcon icon = new ImageIcon(plateau.TuilePoussoire.Tuile.imageADessiner);
                    button.setIcon(icon);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    // Réinitialiser l'image lorsque la souris quitte le bouton
                    
                    button.setIcon(null);
                }
            });
    
    }
    public void defBtnPousser(JLayeredPane layeredPane, JLayeredPane layeredPane2){
        int x=64;
        
        JPanel[] BoutonsPousser;
        
        
        BoutonsPousser = new JPanel[12];
        
        for (int i = 0; i < 12; i++) {
            
                BoutonsPousser[i] = new JPanel();
                BoutonsPousser[i].setPreferredSize(new Dimension(x, x));
                BoutonsPousser[i].setLayout(new GridLayout(1, 1));
                BoutonsPousser[i].setOpaque(false);
        }
        for (int k = 0; k < 3; k++) {
            
            int imp=(2*k)+1;
            
            JButton pousse = new JButton();
            ActionListener ecouteur = new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    plateau.PousserColonneB(imp);
                    Actualiser(layeredPane,layeredPane2);

                    repaint();

                }
            };
            pousse.addActionListener(ecouteur);
            pousse.setOpaque(false);
            pousse.setContentAreaFilled(false);
            pousse.setBorderPainted(false);

            boutonSurvole(pousse);
            
            
            BoutonsPousser[k].setPreferredSize(new Dimension(x, x));
            BoutonsPousser[k].setBounds(138+imp*x,  x, x, x);
            BoutonsPousser[k].add(pousse);
            layeredPane2.add(BoutonsPousser[k], JLayeredPane.DEFAULT_LAYER);
        }
        for (int k = 0; k < 3; k++) {
            
            int imp=(2*k)+1;
            
            JButton pousse = new JButton();
            ActionListener ecouteur = (ActionEvent e) -> {
                plateau.PousserLigneD(imp);
                Actualiser(layeredPane,layeredPane2);
                
                repaint();
            };
            
            pousse.addActionListener(ecouteur);
            pousse.setOpaque(false);
            pousse.setContentAreaFilled(false);
            pousse.setBorderPainted(false);

            boutonSurvole(pousse);
            
            BoutonsPousser[k+3].setPreferredSize(new Dimension(x, x));
            BoutonsPousser[k+3].setBounds(x,  138+imp*x, x, x);
            BoutonsPousser[k+3].add(pousse);
            layeredPane2.add(BoutonsPousser[k+3], JLayeredPane.DEFAULT_LAYER);
            
            
        }
        for (int k = 0; k < 3; k++) {
            
            int imp=(2*k)+1;
            
            JButton pousse = new JButton();
            ActionListener ecouteur = (ActionEvent e) -> {
                plateau.PousserLigneG(imp);
                Actualiser(layeredPane,layeredPane2);
                
                repaint();
            };
           
            pousse.addActionListener(ecouteur);
            pousse.setOpaque(false);
            pousse.setContentAreaFilled(false);
            pousse.setBorderPainted(false);

            boutonSurvole(pousse);
            
            BoutonsPousser[k+6].setPreferredSize(new Dimension(x, x));
            BoutonsPousser[k+6].setBounds(9*x+20,  138+imp*x, x, x);
            BoutonsPousser[k+6].add(pousse);
            layeredPane2.add(BoutonsPousser[k+6], JLayeredPane.DEFAULT_LAYER);
            
            
        }
        for (int k = 0; k < 3; k++) {
            
            int imp=(2*k)+1;
            
            JButton pousse = new JButton();
                
            
            
            
            ActionListener ecouteur = (ActionEvent e) -> {
                plateau.PousserColonneH(imp);
                Actualiser(layeredPane,layeredPane2);
                
                repaint();
            };
            
            
            pousse.addActionListener(ecouteur);
            pousse.setOpaque(false);
            pousse.setContentAreaFilled(false);
            pousse.setBorderPainted(false);

            boutonSurvole(pousse);
            
            BoutonsPousser[k+9].setPreferredSize(new Dimension(x, x));
            BoutonsPousser[k+9].setBounds(138+imp*x,  9*x+20, x, x);
            BoutonsPousser[k+9].add(pousse);
            layeredPane2.add(BoutonsPousser[k+9], JLayeredPane.DEFAULT_LAYER);
            
            
        }

        this.pack();
        this.revalidate();
    }
    public void defTuileEnPlus(JLayeredPane layeredPane2){
        int x=64;
    TuileEnPlus = new JPanel();
        
        TuileEnPlus.setPreferredSize(new Dimension(x, x));
        TuileEnPlus.setBounds(11 * x+17, 5 * x+10, x, x);
        TuileEnPlus.setLayout(new GridLayout(1, 1));
        TuileEnPlus.setOpaque(false);
        TuileEnPlus.add(plateau.TuilePoussoire.Tuile);
        layeredPane2.add(TuileEnPlus, JLayeredPane.DEFAULT_LAYER); // Utilisez DEFAULT_LAYER ou un autre entier pour spécifier la couche

        BtnTourner.setPreferredSize(new Dimension(32, 32));
        BtnTourner.setBounds(11 * x + 36, 6 * x+10, 32, 32);
        BtnTourner.setLayout(new GridLayout(1, 1));
        JButton droite = new JButton();
        BtnTourner.setOpaque(false);
        droite.setOpaque(false);
        droite.setContentAreaFilled(false);
        droite.setBorderPainted(false);
        ActionListener ecouteurClick = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                plateau.TuilePoussoire.Tuile.rotationImageDroite();

                repaint();

            }
        };
        droite.addActionListener(ecouteurClick);
        BtnTourner.add(droite);
        layeredPane2.add(BtnTourner, JLayeredPane.DEFAULT_LAYER); // Utilisez DEFAULT_LAYER ou un autre entier pour spécifier la couche
        
}
    
    
    
        
        
        
       

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BtnTourner = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout BtnTournerLayout = new javax.swing.GroupLayout(BtnTourner);
        BtnTourner.setLayout(BtnTournerLayout);
        BtnTournerLayout.setHorizontalGroup(
            BtnTournerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 83, Short.MAX_VALUE)
        );
        BtnTournerLayout.setVerticalGroup(
            BtnTournerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 54, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(636, Short.MAX_VALUE)
                .addComponent(BtnTourner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(179, 179, 179))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(137, 137, 137)
                .addComponent(BtnTourner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(383, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PlateauGraphique.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PlateauGraphique.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PlateauGraphique.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PlateauGraphique.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new PlateauGraphique().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel BtnTourner;
    // End of variables declaration//GEN-END:variables
}
