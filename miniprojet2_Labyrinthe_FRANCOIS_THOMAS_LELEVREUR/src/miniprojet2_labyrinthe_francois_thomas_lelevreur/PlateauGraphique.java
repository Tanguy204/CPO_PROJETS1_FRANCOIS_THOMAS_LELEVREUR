/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package miniprojet2_labyrinthe_francois_thomas_lelevreur;

import java.awt.Dimension;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

/**
 *
 * @author Tanguy
 */
public class PlateauGraphique extends javax.swing.JFrame {

    Plateau plateau;

    /**
     * Creates new form PlateauGraphique
     */
    public PlateauGraphique() {
        initComponents();
        int x = 64;
        // Vous devez également déclarer y s'il n'est pas déjà déclaré.

        JLayeredPane layeredPane = new JLayeredPane();
        getContentPane().add(layeredPane);

        PlateauGrph.setPreferredSize(new Dimension(x * 7, x * 7));
        PlateauGrph.setBounds(2 * x, 2 * x, 7 * x, 7 * x);

        // Ajoutez le panneau au JLayeredPane
        layeredPane.add(PlateauGrph, JLayeredPane.DEFAULT_LAYER); // Utilisez DEFAULT_LAYER ou un autre entier pour spécifier la couche

        // Réglez la taille du JLayeredPane en fonction de la taille du panneau
        layeredPane.setBounds(0, 0, x * 12, x * 12);
        add(layeredPane);

        // Le reste de votre code...
        this.pack();
        this.revalidate();

        this.plateau = new Plateau();

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                TuileGraphique boutonTuile = plateau.Labyrinthe[i][j].Tuile; // création d'un bouton
                boutonTuile.setBounds(64 * i, 64 * j, 64, 64); // x, y, largeur, hauteur // ajout au Jpanel PanneauGrille
                PlateauGrph.add(boutonTuile);
            }
        }
        TuileEnPlus.setPreferredSize(new Dimension(x, x));
        TuileEnPlus.setBounds(11 * x, 5 * x, x, x);
        TuileEnPlus.setLayout(new GridLayout(1, 1));
        TuileEnPlus.add(plateau.TuilePoussoire.Tuile);
        layeredPane.add(TuileEnPlus, JLayeredPane.DEFAULT_LAYER); // Utilisez DEFAULT_LAYER ou un autre entier pour spécifier la couche

        BtnTourner.setPreferredSize(new Dimension(32, 32));
        BtnTourner.setBounds(11 * x + 16, 6 * x, 32, 32);
        BtnTourner.setLayout(new GridLayout(1, 1));
        JButton droite = new JButton();
        ActionListener ecouteurClick = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                plateau.TuilePoussoire.Tuile.rotationImageDroite();

                repaint();

            }
        };
        droite.addActionListener(ecouteurClick);
        BtnTourner.add(droite);
        layeredPane.add(BtnTourner, JLayeredPane.DEFAULT_LAYER); // Utilisez DEFAULT_LAYER ou un autre entier pour spécifier la couche
        
        
        
        
        JPanel[] BoutonsPousser;
        
        
        BoutonsPousser = new JPanel[12];
        
        for (int i = 0; i < 12; i++) {
            
                BoutonsPousser[i] = new JPanel();
                BoutonsPousser[i].setPreferredSize(new Dimension(x, x));
                BoutonsPousser[i].setLayout(new GridLayout(1, 1));
        }
        for (int k = 0; k < 3; k++) {
            
            int imp=(2*k)+1;
            
            JButton pousse = new JButton();
            ActionListener ecouteur = new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    plateau.PousserColonneB(imp);
                    Actualiser(layeredPane);

                    repaint();

                }
            };
            pousse.addActionListener(ecouteur);
            BoutonsPousser[k].setPreferredSize(new Dimension(x, x));
            BoutonsPousser[k].setBounds(128+imp*x,  x, x, x);
            BoutonsPousser[k].add(pousse);
            layeredPane.add(BoutonsPousser[k], JLayeredPane.DEFAULT_LAYER);
            
            
        }
        for (int k = 0; k < 3; k++) {
            
            int imp=(2*k)+1;
            
            JButton pousse = new JButton();
            ActionListener ecouteur = new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    plateau.PousserLigneD(imp);
                    Actualiser(layeredPane);

                    repaint();

                }
            };
            pousse.addActionListener(ecouteur);
            BoutonsPousser[k+3].setPreferredSize(new Dimension(x, x));
            BoutonsPousser[k+3].setBounds(x,  128+imp*x, x, x);
            BoutonsPousser[k+3].add(pousse);
            layeredPane.add(BoutonsPousser[k+3], JLayeredPane.DEFAULT_LAYER);
            
            
        }
        for (int k = 0; k < 3; k++) {
            
            int imp=(2*k)+1;
            
            JButton pousse = new JButton();
            ActionListener ecouteur = new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    plateau.PousserLigneG(imp);
                    Actualiser(layeredPane);

                    repaint();

                }
            };
            pousse.addActionListener(ecouteur);
            BoutonsPousser[k+6].setPreferredSize(new Dimension(x, x));
            BoutonsPousser[k+6].setBounds(9*x,  128+imp*x, x, x);
            BoutonsPousser[k+6].add(pousse);
            layeredPane.add(BoutonsPousser[k+6], JLayeredPane.DEFAULT_LAYER);
            
            
        }
        for (int k = 0; k < 3; k++) {
            
            int imp=(2*k)+1;
            
            JButton pousse = new JButton();
            ActionListener ecouteur = new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    plateau.PousserColonneH(imp);
                    Actualiser(layeredPane);

                    repaint();

                }
            };
            pousse.addActionListener(ecouteur);
            BoutonsPousser[k+9].setPreferredSize(new Dimension(x, x));
            BoutonsPousser[k+9].setBounds(128+imp*x,  9*x, x, x);
            BoutonsPousser[k+9].add(pousse);
            layeredPane.add(BoutonsPousser[k+9], JLayeredPane.DEFAULT_LAYER);
            
            
        }

        this.pack();
        this.revalidate();
    }
    public void Actualiser(JLayeredPane layeredPane){
        int x = 64;
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                TuileGraphique boutonTuile = plateau.Labyrinthe[i][j].Tuile; // création d'un bouton
                boutonTuile.setBounds(64 * i, 64 * j, 64, 64); // x, y, largeur, hauteur // ajout au Jpanel PanneauGrille
                PlateauGrph.add(boutonTuile);
            }
        }
        TuileEnPlus.setPreferredSize(new Dimension(x, x));
        TuileEnPlus.setBounds(11 * x, 5 * x, x, x);
        TuileEnPlus.setLayout(new GridLayout(1, 1));
        TuileEnPlus.add(plateau.TuilePoussoire.Tuile);
        layeredPane.add(TuileEnPlus, JLayeredPane.DEFAULT_LAYER); // Utilisez DEFAULT_LAYER ou un autre entier pour spécifier la couche

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PlateauGrph = new javax.swing.JPanel();
        TuileEnPlus = new javax.swing.JPanel();
        BtnTourner = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout PlateauGrphLayout = new javax.swing.GroupLayout(PlateauGrph);
        PlateauGrph.setLayout(PlateauGrphLayout);
        PlateauGrphLayout.setHorizontalGroup(
            PlateauGrphLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 272, Short.MAX_VALUE)
        );
        PlateauGrphLayout.setVerticalGroup(
            PlateauGrphLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 172, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout TuileEnPlusLayout = new javax.swing.GroupLayout(TuileEnPlus);
        TuileEnPlus.setLayout(TuileEnPlusLayout);
        TuileEnPlusLayout.setHorizontalGroup(
            TuileEnPlusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 63, Short.MAX_VALUE)
        );
        TuileEnPlusLayout.setVerticalGroup(
            TuileEnPlusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 66, Short.MAX_VALUE)
        );

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
            .addGroup(layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addComponent(PlateauGrph, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TuileEnPlus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 405, Short.MAX_VALUE)
                .addComponent(BtnTourner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TuileEnPlus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(PlateauGrph, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(86, 86, 86)
                        .addComponent(BtnTourner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(346, Short.MAX_VALUE))
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
    private javax.swing.JPanel PlateauGrph;
    private javax.swing.JPanel TuileEnPlus;
    // End of variables declaration//GEN-END:variables
}
