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
import java.util.ArrayList;
import java.util.Random;
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
    JPanel CarteObj;
    Image carrePointille;

    JLayeredPane layeredPane2;
    JLayeredPane layeredPane;
    JLayeredPane layeredPion;
    int tourDe;
    int nbJoueurs;

    /**
     * Creates new form PlateauGraphique
     *
     * @param nbJoueurs
     */
    public PlateauGraphique(int nbJoueurs) {
        plateau = new Plateau(nbJoueurs);
        for (int i = 0; i<7;i++){
            for (int j = 0; j<7;j++){
                System.out.println(plateau.Labyrinthe[i][j].CoteBas+" "+plateau.Labyrinthe[i][j].CoteGauche+" "+plateau.Labyrinthe[i][j].CoteHaut+" "+plateau.Labyrinthe[i][j].CoteDroit+"ligne"+i);
            }
        }
        this.nbJoueurs = nbJoueurs;
        layeredPion = new JLayeredPane() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image image = new ImageIcon("C:\\OneDrive - Fondation EPF\\Documents\\Cours\\2ème année\\CPO\\CPO_PROJET_LAB\\PlateauFondV2.jpg").getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), (ImageObserver) this);
            }
        };
        initComponents();
        Random random = new Random();
        tourDe = random.nextInt(Math.abs(nbJoueurs));
        

        int x = 64;
        // Vous devez également déclarer y s'il n'est pas déjà déclaré.

        layeredPane = new JLayeredPane() {
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

        PlateauGrph.setBounds(12, 12, 7 * x, 7 * x);

        // Ajoutez le panneau au JLayeredPane
        layeredPane.add(PlateauGrph, JLayeredPane.DEFAULT_LAYER); // Utilisez DEFAULT_LAYER ou un autre entier pour spécifier la couche

        // Réglez la taille du JLayeredPane en fonction de la taille du panneau
        layeredPane.setBounds(2 * x, 2 * x, x * 7 + 20, x * 7 + 20);
        add(layeredPane);

        // Le reste de votre code...
        this.pack();
        this.revalidate();

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {

                TuileGraphique boutonTuile;
                boutonTuile = plateau.Labyrinthe[i][j].Tuile;// création d'un bouton
                boutonTuile.setBounds(64 * i, 64 * j, 64, 64); // x, y, largeur, hauteur // ajout au Jpanel PanneauGrille
                PlateauGrph.add(boutonTuile);
            }
        }
        layeredPane2 = new JLayeredPane() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image image = new ImageIcon("C:\\OneDrive - Fondation EPF\\Documents\\Cours\\2ème année\\CPO\\CPO_PROJET_LAB\\PlateauFondV2.png").getImage();
                g.drawImage(image, -60, -80, 1200, 902, (ImageObserver) this);
            }
        };
        layeredPane2.setBounds(0, 0, 1200, 902);
        add(layeredPane2);
        defTuileEnPlus(layeredPane2);
        defBtnPousser(layeredPane, layeredPane2);
        defCarteObj(layeredPane2, nbJoueurs);
        defPion();

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

    public void boutonSurvole(JButton button) {
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

    public void defCarteObj(JLayeredPane layeredPane2, int nbJoueur) {
        JPanel montrer = new JPanel();
        JButton MONTRER = new JButton();
        montrer.setLayout(new GridLayout(1, 1));
        montrer.setBounds(932, 564, 50, 50);
        CarteObj = new JPanel();
         int  l = 2 ; 
        
        
        if (nbJoueur == 1 || nbJoueur == 2) {
            CarteObj.setOpaque(false);
            CarteObj.setLayout(new GridLayout(6, 2));
            l=12;
            
            CarteObj.setBounds(832, 180, 200, 384);
            for (int i = 0; i < 12; i++) {
                CarteObj.add(plateau.objectifs[i][tourDe]);
            }
        }
        if (nbJoueur == 3) {

            CarteObj.setLayout(new GridLayout(8, 1));
            CarteObj.setBounds(832, 180, 100, 512);
            l=8;
            for (int i = 0; i < 8; i++) {
                CarteObj.add(plateau.objectifs[i][tourDe]);
            }
            
        }
        if (nbJoueur == 4) {
            l=6;
            CarteObj.setLayout(new GridLayout(6, 1));
            CarteObj.setBounds(832, 180, 100, 384);
            for (int i = 0; i < 6; i++) {
                CarteObj.add(plateau.objectifs[i][tourDe]);
            }
        }
        ActionListener montre = new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    
                
                    if (plateau.objectifs[0][tourDe].vision==false){
                        for( int i = 0 ; i<plateau.objectifs.length;i++){
                            plateau.objectifs[i][tourDe].MontrerCarte();
                    }
                    }else{
                    for( int i = 0 ; i<plateau.objectifs.length;i++){
                            plateau.objectifs[i][tourDe].CacherCarte();
                            }

                    repaint();

                }
                }
            };
        MONTRER.addActionListener(montre);
        montrer.add(MONTRER);
        layeredPane2.add(montrer, JLayeredPane.DEFAULT_LAYER);
        layeredPane2.add(CarteObj, JLayeredPane.DEFAULT_LAYER);
    }

    public void defBtnPousser(JLayeredPane layeredPane, JLayeredPane layeredPane2) {
        int x = 64;

        JPanel[] BoutonsPousser;

        BoutonsPousser = new JPanel[12];

        for (int i = 0; i < 12; i++) {

            BoutonsPousser[i] = new JPanel();
            BoutonsPousser[i].setPreferredSize(new Dimension(x, x));
            BoutonsPousser[i].setLayout(new GridLayout(1, 1));
            BoutonsPousser[i].setOpaque(false);
        }
        for (int k = 0; k < 3; k++) {

            int imp = (2 * k) + 1;

            JButton pousse = new JButton();
            ActionListener ecouteur = new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    plateau.PousserColonneB(imp);
                    Actualiser(layeredPane, layeredPane2);
                    

                    repaint();

                }
            };
            pousse.addActionListener(ecouteur);
            pousse.setOpaque(false);
            pousse.setContentAreaFilled(false);
            pousse.setBorderPainted(false);

            boutonSurvole(pousse);

            BoutonsPousser[k].setPreferredSize(new Dimension(x, x));
            BoutonsPousser[k].setBounds(138 + imp * x, x, x, x);
            BoutonsPousser[k].add(pousse);
            layeredPane2.add(BoutonsPousser[k], JLayeredPane.DEFAULT_LAYER);
        }
        for (int k = 0; k < 3; k++) {

            int imp = (2 * k) + 1;

            JButton pousse = new JButton();
            ActionListener ecouteur = (ActionEvent e) -> {
                plateau.PousserLigneD(imp);
                Actualiser(layeredPane, layeredPane2);

                repaint();
            };

            pousse.addActionListener(ecouteur);
            pousse.setOpaque(false);
            pousse.setContentAreaFilled(false);
            pousse.setBorderPainted(false);

            boutonSurvole(pousse);

            BoutonsPousser[k + 3].setPreferredSize(new Dimension(x, x));
            BoutonsPousser[k + 3].setBounds(x, 138 + imp * x, x, x);
            BoutonsPousser[k + 3].add(pousse);
            layeredPane2.add(BoutonsPousser[k + 3], JLayeredPane.DEFAULT_LAYER);

        }
        for (int k = 0; k < 3; k++) {

            int imp = (2 * k) + 1;

            JButton pousse = new JButton();
            ActionListener ecouteur = (ActionEvent e) -> {
                plateau.PousserLigneG(imp);
                Actualiser(layeredPane, layeredPane2);

                repaint();
            };

            pousse.addActionListener(ecouteur);
            pousse.setOpaque(false);
            pousse.setContentAreaFilled(false);
            pousse.setBorderPainted(false);

            boutonSurvole(pousse);

            BoutonsPousser[k + 6].setPreferredSize(new Dimension(x, x));
            BoutonsPousser[k + 6].setBounds(9 * x + 20, 138 + imp * x, x, x);
            BoutonsPousser[k + 6].add(pousse);
            layeredPane2.add(BoutonsPousser[k + 6], JLayeredPane.DEFAULT_LAYER);

        }
        for (int k = 0; k < 3; k++) {

            int imp = (2 * k) + 1;

            JButton pousse = new JButton();

            ActionListener ecouteur = (ActionEvent e) -> {
                plateau.PousserColonneH(imp);
                Actualiser(layeredPane, layeredPane2);

                repaint();
            };

            pousse.addActionListener(ecouteur);
            pousse.setOpaque(false);
            pousse.setContentAreaFilled(false);
            pousse.setBorderPainted(false);

            boutonSurvole(pousse);

            BoutonsPousser[k + 9].setPreferredSize(new Dimension(x, x));
            BoutonsPousser[k + 9].setBounds(138 + imp * x, 9 * x + 20, x, x);
            BoutonsPousser[k + 9].add(pousse);
            layeredPane2.add(BoutonsPousser[k + 9], JLayeredPane.DEFAULT_LAYER);

        }

        this.pack();
        this.revalidate();
    }

    public void defTuileEnPlus(JLayeredPane layeredPane2) {
        int x = 64;
        TuileEnPlus = new JPanel();

        TuileEnPlus.setPreferredSize(new Dimension(x, x));
        TuileEnPlus.setBounds(11 * x + 17, 5 * x + 10, x, x);
        TuileEnPlus.setLayout(new GridLayout(1, 1));
        TuileEnPlus.setOpaque(false);
        TuileEnPlus.add(plateau.TuilePoussoire.Tuile);
        layeredPane2.add(TuileEnPlus, JLayeredPane.DEFAULT_LAYER); // Utilisez DEFAULT_LAYER ou un autre entier pour spécifier la couche

        BtnTourner.setPreferredSize(new Dimension(32, 32));
        BtnTourner.setBounds(11 * x + 36, 6 * x + 10, 32, 32);
        BtnTourner.setLayout(new GridLayout(1, 1));
        JButton droite = new JButton();
        BtnTourner.setOpaque(false);
        droite.setOpaque(false);
        droite.setContentAreaFilled(false);
        droite.setBorderPainted(false);
        ActionListener ecouteurClick = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                plateau.TuilePoussoire.TournerTuileD();

                repaint();

            }
        };
        droite.addActionListener(ecouteurClick);
        BtnTourner.add(droite);
        layeredPane2.add(BtnTourner, JLayeredPane.DEFAULT_LAYER); // Utilisez DEFAULT_LAYER ou un autre entier pour spécifier la couche

    }

    public void defPion() {

        for (int n = 0; n<nbJoueurs;n++){
        layeredPane.add(plateau.Pions[n], JLayeredPane.PALETTE_LAYER);
        }
        JPanel BoutonsY = new JPanel();
        BoutonsY.setLayout(new GridLayout(2, 1));
        JButton btnMonte = new JButton();
        ActionListener Monte = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                plateau.avancerHaut(plateau.Pions[tourDe]);

                layeredPane.remove(plateau.Pions[tourDe]);
                layeredPane.add(plateau.Pions[tourDe], JLayeredPane.PALETTE_LAYER);

                repaint();
                for (int i = 0; i<7;i++){
            for (int j = 0; j<7;j++){
                System.out.println(plateau.Labyrinthe[i][j].CoteBas+" "+plateau.Labyrinthe[i][j].CoteGauche+" "+plateau.Labyrinthe[i][j].CoteHaut+" "+plateau.Labyrinthe[i][j].CoteDroit+"ligne"+i);
            }
        }

            }
        };
        btnMonte.addActionListener(Monte);
        JButton btnDesc = new JButton();
        ActionListener Descendre = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                plateau.avancerBas(plateau.Pions[tourDe]);

                layeredPane.remove(plateau.Pions[tourDe]);
                layeredPane.add(plateau.Pions[tourDe], JLayeredPane.PALETTE_LAYER);

                repaint();
                for (int i = 0; i<7;i++){
            for (int j = 0; j<7;j++){
                System.out.println(plateau.Labyrinthe[i][j].CoteBas+" "+plateau.Labyrinthe[i][j].CoteGauche+" "+plateau.Labyrinthe[i][j].CoteHaut+" "+plateau.Labyrinthe[i][j].CoteDroit+"ligne"+i);
            }
        }

            }
        };
        btnDesc.addActionListener(Descendre);

        BoutonsY.setBounds(750, 500, 40, 80);
        
        BoutonsY.add(btnMonte);
        BoutonsY.add(btnDesc);
        JPanel btnDroit = new JPanel();
        btnDroit.setLayout(new GridLayout(1, 1));
        JButton JbtnDroit = new JButton();
        ActionListener droite = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                plateau.avancerDroite(plateau.Pions[tourDe]);

                layeredPane.remove(plateau.Pions[tourDe]);
                layeredPane.add(plateau.Pions[tourDe], JLayeredPane.PALETTE_LAYER);

                repaint();
                
        }
                 };
        JbtnDroit.addActionListener(droite);
        btnDroit.add(JbtnDroit);
        btnDroit.setBounds(790, 540, 40, 40);
        
        JPanel btnGauche = new JPanel();
        btnGauche.setLayout(new GridLayout(1, 1));
        JButton JbtnGauche = new JButton();
        ActionListener gauche = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                plateau.avancerGauche(plateau.Pions[tourDe]);

                layeredPane.remove(plateau.Pions[tourDe]);
                layeredPane.add(plateau.Pions[tourDe], JLayeredPane.PALETTE_LAYER);

                repaint();
                
        }
                 };
        JbtnGauche.addActionListener(gauche);
        btnGauche.add(JbtnGauche);
        btnGauche.setBounds(710, 540, 40, 40);
        

        layeredPane2.add(BoutonsY, JLayeredPane.DEFAULT_LAYER);
        layeredPane2.add(btnDroit, JLayeredPane.DEFAULT_LAYER);// Utilisez DEFAULT_LAYER ou un autre entier pour spécifier la couche
        layeredPane2.add(btnGauche, JLayeredPane.DEFAULT_LAYER);// Utilisez DEFAULT_LAYER ou un autre entier pour spécifier la couche

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
            new PlateauGraphique(3).setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel BtnTourner;
    // End of variables declaration//GEN-END:variables
}
