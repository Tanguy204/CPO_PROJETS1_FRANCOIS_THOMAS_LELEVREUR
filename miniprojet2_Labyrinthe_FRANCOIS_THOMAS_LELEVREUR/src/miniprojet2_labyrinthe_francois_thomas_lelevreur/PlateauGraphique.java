/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package miniprojet2_labyrinthe_francois_thomas_lelevreur;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
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
    JButton[] btnPousser;
    JButton[] btnDeplacer;
    JLayeredPane commandes;
    JLayeredPane layeredPane2;
    JLayeredPane layeredPane;
    JLayeredPane layeredPion;
    JPanel clrJoueur;
    JLabel instruction;


    int tourDe;
    int nbJoueurs;

    /**
     * Creates new form PlateauGraphique
     *
     * @param nbJoueurs
     */
    public PlateauGraphique(int nbJoueurs) {
        initComponents();
        setPreferredSize(new Dimension(1000, 700));
        
        Toolkit toolkit = Toolkit.getDefaultToolkit();

        Dimension screenSize = toolkit.getScreenSize();
        int L = (int) screenSize.getWidth();
        int H = (int) screenSize.getHeight();
        setLocation((L-1000)/2, (H-700)/2);
        
        
        btnPousser = new JButton[12];
        btnDeplacer = new JButton[4];
        plateau = new Plateau(nbJoueurs);
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.println(plateau.Labyrinthe[i][j].CoteBas + " " + plateau.Labyrinthe[i][j].CoteGauche + " " + plateau.Labyrinthe[i][j].CoteHaut + " " + plateau.Labyrinthe[i][j].CoteDroit + "ligne" + i);
            }
        }
        this.nbJoueurs = nbJoueurs;

        
        Random random = new Random();
        tourDe = random.nextInt(Math.abs(nbJoueurs));

        int x = 64;
        // Vous devez également déclarer y s'il n'est pas déjà déclaré.
        commandes = new JLayeredPane() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image image = new ImageIcon("C:\\OneDrive - Fondation EPF\\Documents\\Cours\\2ème année\\CPO\\CPO_PROJET_LAB\\fondCommandes.png").getImage();
                g.drawImage(image, 0, 32, 9 * x, 9 * x + 32, (ImageObserver) this);
            }
        };
        clrJoueur = new JPanel();
        defCouleurJ();
        commandes.setBounds(680, 0, x * 9 + 80, x * 9 + 80);

        add(commandes);
        layeredPane = new JLayeredPane() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.red);
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
        layeredPane.setBounds(100, 100, x * 7 + 20, x * 7 + 20);
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
                g.drawImage(image, -88, -108, 1200, 902, (ImageObserver) this);
            }
        };
        layeredPane2.setBounds(0, 0, 1200, 902);
        add(layeredPane2);
        defTuileEnPlus(layeredPane2);
        defBtnPousser(layeredPane, layeredPane2);
        defCarteObj();
        defPion();
        defBtnValider();
        afficherClassement();
        defInstruction("C:\\OneDrive - Fondation EPF\\Documents\\Cours\\2ème année\\CPO\\CPO_PROJET_LAB\\déplacerPion.png");

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

    public void defCarteObj() {

        // Rendez le JLabel transparent
        CarteObj = new JPanel();
        int l = 2;
        CarteObj.setLayout(new GridLayout(1, 1));
        CarteObj.setBounds(152, 290, 64, 100);

        CarteObj.add(plateau.Pions[tourDe].cartesObjs[plateau.Pions[tourDe].compt]);

        commandes.add(CarteObj, JLayeredPane.DEFAULT_LAYER);
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
            btnPousser[k] = pousse;
            ActionListener ecouteur = new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    plateau.PousserColonneB(imp);
                    Actualiser(layeredPane, layeredPane2);
                    for (int i = 0; i < 12; i++) {
                        btnPousser[i].setEnabled(false);
                    }
                    for (int i = 0; i < 4; i++) {
                        btnDeplacer[i].setEnabled(true);
                    }

                    repaint();

                }
            };
            pousse.addActionListener(ecouteur);
            pousse.setOpaque(false);
            pousse.setContentAreaFilled(false);
            pousse.setBorderPainted(false);

            boutonSurvole(pousse);

            BoutonsPousser[k].setPreferredSize(new Dimension(x, x));
            BoutonsPousser[k].setBounds(110 + imp * x, 36, x, x);
            BoutonsPousser[k].add(pousse);
            layeredPane2.add(BoutonsPousser[k], JLayeredPane.DEFAULT_LAYER);
            
        }
        for (int k = 0; k < 3; k++) {

            int imp = (2 * k) + 1;

            JButton pousse = new JButton();
            btnPousser[k + 3] = pousse;
            ActionListener ecouteur = (ActionEvent e) -> {
                plateau.PousserLigneD(imp);
                Actualiser(layeredPane, layeredPane2);
                for (int i = 0; i < 12; i++) {
                    btnPousser[i].setEnabled(false);
                }
                for (int i = 0; i < 4; i++) {
                    btnDeplacer[i].setEnabled(true);
                }
                repaint();
            };

            pousse.addActionListener(ecouteur);
            pousse.setOpaque(false);
            pousse.setContentAreaFilled(false);
            pousse.setBorderPainted(false);

            boutonSurvole(pousse);

            BoutonsPousser[k + 3].setPreferredSize(new Dimension(x, x));
            BoutonsPousser[k + 3].setBounds(36, 110 + imp * x, x, x);
            BoutonsPousser[k + 3].add(pousse);
            layeredPane2.add(BoutonsPousser[k + 3], JLayeredPane.DEFAULT_LAYER);

        }
        for (int k = 0; k < 3; k++) {

            int imp = (2 * k) + 1;

            JButton pousse = new JButton();
            btnPousser[k + 6] = pousse;
            ActionListener ecouteur = (ActionEvent e) -> {
                plateau.PousserLigneG(imp);
                Actualiser(layeredPane, layeredPane2);
                for (int i = 0; i < 12; i++) {
                    btnPousser[i].setEnabled(false);
                }
                for (int i = 0; i < 4; i++) {
                    btnDeplacer[i].setEnabled(true);
                }
                repaint();
            };

            pousse.addActionListener(ecouteur);
            pousse.setOpaque(false);
            pousse.setContentAreaFilled(false);
            pousse.setBorderPainted(false);

            boutonSurvole(pousse);

            BoutonsPousser[k + 6].setPreferredSize(new Dimension(x, x));
            BoutonsPousser[k + 6].setBounds(9 * x - 8, 110 + imp * x, x, x);
            BoutonsPousser[k + 6].add(pousse);
            layeredPane2.add(BoutonsPousser[k + 6], JLayeredPane.DEFAULT_LAYER);

        }
        for (int k = 0; k < 3; k++) {

            int imp = (2 * k) + 1;

            JButton pousse = new JButton();
            btnPousser[k + 9] = pousse;

            ActionListener ecouteur = (ActionEvent e) -> {
                plateau.PousserColonneH(imp);
                Actualiser(layeredPane, layeredPane2);
                for (int i = 0; i < 12; i++) {
                    btnPousser[i].setEnabled(false);
                }
                for (int i = 0; i < 4; i++) {
                    btnDeplacer[i].setEnabled(true);
                }
                repaint();
            };

            pousse.addActionListener(ecouteur);
            pousse.setOpaque(false);
            pousse.setContentAreaFilled(false);
            pousse.setBorderPainted(false);

            boutonSurvole(pousse);

            BoutonsPousser[k + 9].setPreferredSize(new Dimension(x, x));
            BoutonsPousser[k + 9].setBounds(110 + imp * x, 9 * x - 8, x, x);
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
        TuileEnPlus.setBounds(30, 275, x, x);
        TuileEnPlus.setLayout(new GridLayout(1, 1));
        TuileEnPlus.setOpaque(false);
        TuileEnPlus.add(plateau.TuilePoussoire.Tuile);
        commandes.add(TuileEnPlus, JLayeredPane.DEFAULT_LAYER); // Utilisez DEFAULT_LAYER ou un autre entier pour spécifier la couche
        JPanel BtnTourner = new JPanel();
        BtnTourner.setBounds(42, 350, 40, 40);
        BtnTourner.setLayout(new GridLayout(1, 1));
        JButton droite = new JButton();
         ImageIcon imageIcon;

        imageIcon = new ImageIcon("C:\\OneDrive - Fondation EPF\\Documents\\Cours\\2ème année\\CPO\\CPO_PROJET_LAB\\tournerCarte.png");

        ImageIcon imageRedimenssionne = new ImageIcon( imageIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        
        
        
        droite.setIcon(imageRedimenssionne);
        droite.setOpaque(false);
            droite.setContentAreaFilled(false);
            droite.setBorderPainted(false);
        BtnTourner.setOpaque(false);

        ActionListener ecouteurClick = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                plateau.TuilePoussoire.TournerTuileD();

                repaint();

            }
        };
        droite.addActionListener(ecouteurClick);
        BtnTourner.add(droite);
        commandes.add(BtnTourner, JLayeredPane.DEFAULT_LAYER); 

    }

    public void defPion() {
        ImageIcon[] Images = new ImageIcon[4];
        ImageIcon Haut= new ImageIcon("C:\\OneDrive - Fondation EPF\\Documents\\Cours\\2ème année\\CPO\\CPO_PROJET_LAB\\haut.png");
        ImageIcon haut= new ImageIcon( Haut.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        ImageIcon Bas= new ImageIcon("C:\\OneDrive - Fondation EPF\\Documents\\Cours\\2ème année\\CPO\\CPO_PROJET_LAB\\bas.png");
        ImageIcon bas= new ImageIcon( Bas.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        ImageIcon Gauche= new ImageIcon("C:\\OneDrive - Fondation EPF\\Documents\\Cours\\2ème année\\CPO\\CPO_PROJET_LAB\\gauche.png");
        ImageIcon GAUCHE= new ImageIcon( Gauche.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        ImageIcon Droite= new ImageIcon("C:\\OneDrive - Fondation EPF\\Documents\\Cours\\2ème année\\CPO\\CPO_PROJET_LAB\\droite.png");
        ImageIcon DROITE= new ImageIcon( Droite.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        for (int n = 0; n < nbJoueurs; n++) {
            layeredPane.add(plateau.Pions[n], JLayeredPane.PALETTE_LAYER);
        }
        JPanel BoutonsY = new JPanel();
        BoutonsY.setOpaque(false);
        BoutonsY.setLayout(new GridLayout(2, 1));
        JButton btnMonte = new JButton();
        btnMonte.setIcon(haut);
        btnMonte.setOpaque(false);
            btnMonte.setContentAreaFilled(false);
            btnMonte.setBorderPainted(false);
        
        ActionListener Monte = new ActionListener() {
        
            @Override
            public void actionPerformed(ActionEvent e) {
                plateau.avancerHaut(plateau.Pions[tourDe]);

                layeredPane.remove(plateau.Pions[tourDe]);
                layeredPane.add(plateau.Pions[tourDe], JLayeredPane.PALETTE_LAYER);

                repaint();
                for (int i = 0; i < 7; i++) {
                    for (int j = 0; j < 7; j++) {
                        System.out.println(plateau.Labyrinthe[i][j].CoteBas + " " + plateau.Labyrinthe[i][j].CoteGauche + " " + plateau.Labyrinthe[i][j].CoteHaut + " " + plateau.Labyrinthe[i][j].CoteDroit + "ligne" + i);
                    }
                }

            }
        };
        btnMonte.addActionListener(Monte);
        JButton btnDesc = new JButton();
        btnDesc.setIcon(bas);
        btnDesc.setOpaque(false);
            btnDesc.setContentAreaFilled(false);
            btnDesc.setBorderPainted(false);
        ActionListener Descendre = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                plateau.avancerBas(plateau.Pions[tourDe]);

                layeredPane.remove(plateau.Pions[tourDe]);
                layeredPane.add(plateau.Pions[tourDe], JLayeredPane.PALETTE_LAYER);

                repaint();
                for (int i = 0; i < 7; i++) {
                    for (int j = 0; j < 7; j++) {
                        System.out.println(plateau.Labyrinthe[i][j].CoteBas + " " + plateau.Labyrinthe[i][j].CoteGauche + " " + plateau.Labyrinthe[i][j].CoteHaut + " " + plateau.Labyrinthe[i][j].CoteDroit + "ligne" + i);
                    }
                }

            }
        };
        btnDesc.addActionListener(Descendre);

        BoutonsY.setBounds(102, 500, 40, 80);

        BoutonsY.add(btnMonte);
        BoutonsY.add(btnDesc);
        JPanel btnDroit = new JPanel();
        btnDroit.setOpaque(false);
        btnDroit.setLayout(new GridLayout(1, 1));
        JButton JbtnDroit = new JButton();
        JbtnDroit.setIcon(DROITE);
        JbtnDroit.setOpaque(false);
            JbtnDroit.setContentAreaFilled(false);
            JbtnDroit.setBorderPainted(false);
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
        btnDroit.setBounds(142, 540, 40, 40);

        JPanel btnGauche = new JPanel();
        btnGauche.setOpaque(false);
        btnGauche.setLayout(new GridLayout(1, 1));
        JButton JbtnGauche = new JButton();
        JbtnGauche.setOpaque(false);
            JbtnGauche.setContentAreaFilled(false);
            JbtnGauche.setBorderPainted(false);
        JbtnGauche.setIcon(GAUCHE);
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
        btnGauche.setBounds(62, 540, 40, 40);

        commandes.add(BoutonsY, JLayeredPane.DEFAULT_LAYER);
        commandes.add(btnDroit, JLayeredPane.DEFAULT_LAYER);// Utilisez DEFAULT_LAYER ou un autre entier pour spécifier la couche
        commandes.add(btnGauche, JLayeredPane.DEFAULT_LAYER);// Utilisez DEFAULT_LAYER ou un autre entier pour spécifier la couche
        btnDeplacer[0] = JbtnGauche;
        btnDeplacer[1] = JbtnDroit;
        btnDeplacer[2] = btnDesc;
        btnDeplacer[3] = btnMonte;
        for (int i = 0; i < 4; i++) {
            btnDeplacer[i].setEnabled(false);
        }
    }

    public void defBtnValider() {
        JPanel BoutonValider = new JPanel();
        BoutonValider.setOpaque(false);
        BoutonValider.setBounds(62, 580, 120, 40);
        BoutonValider.setLayout(new GridLayout(1, 1));
        JButton btnValide = new JButton();
        ImageIcon Droite= new ImageIcon("C:\\OneDrive - Fondation EPF\\Documents\\Cours\\2ème année\\CPO\\CPO_PROJET_LAB\\valider.png");
        ImageIcon DROITE= new ImageIcon( Droite.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        btnValide.setIcon(DROITE);
        btnValide.setOpaque(false);
            btnValide.setContentAreaFilled(false);
            btnValide.setBorderPainted(false);
        ActionListener valider;
        valider = new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {

                plateau.Pions[tourDe].CarteValide();
                afficherClassement();
                if (plateau.Pions[tourDe].gagné()==true){
                    new fenetreGagné(plateau.classement).setVisible(true);
                }
               
                if (tourDe == nbJoueurs - 1) {
                    tourDe = 0;
                } else {
                    tourDe += 1;
                }
                for (int i = 0; i < 12; i++) {
                    btnPousser[i].setEnabled(true);
                }
                for (int i = 0; i < 4; i++) {
                    btnDeplacer[i].setEnabled(false);
                }
                plateau.Pions[tourDe].CarteValide();
                commandes.remove(CarteObj);
                defCarteObj();

                defCouleurJ();
                
                
                
                
                
                repaint();

            }
        };
        btnValide.addActionListener(valider);
        BoutonValider.add(btnValide);
        
        commandes.add(BoutonValider, JLayeredPane.DEFAULT_LAYER);

    }

    public void defCouleurJ() {
        commandes.remove(clrJoueur);
        if (plateau.Pions[tourDe].clr == "R") {
            clrJoueur.setBackground(Color.red);

        }
        if (plateau.Pions[tourDe].clr == "J") {
            clrJoueur.setBackground(Color.YELLOW);

        }
        if (plateau.Pions[tourDe].clr == "V") {
            clrJoueur.setBackground(Color.green);

        }
        if (plateau.Pions[tourDe].clr == "B") {
            clrJoueur.setBackground(Color.blue);

        }
        clrJoueur.setBounds(10, 41, 224, 17);
        commandes.remove(clrJoueur);
        commandes.add(clrJoueur, JLayeredPane.DEFAULT_LAYER);

    }

    public void afficherClassement() {
        plateau.classement();
        for (int i = 0; i < nbJoueurs; i++) {
            for (int j = 0; j < nbJoueurs; j++) {
                if (plateau.classement[i][j] != null) {
                    plateau.classement[i][j].pionPodium.setBounds(90 + j * 65, i * 40 + 95, 25, 25);
                    commandes.add(plateau.classement[i][j].pionPodium, JLayeredPane.PALETTE_LAYER);
                    plateau.classement[i][j].Score.setText(String.valueOf(plateau.classement[i][j].compt) + " pts");
                    plateau.classement[i][j].Score.setBounds(115 + j * 65, i * 40 + 90, 50, 20);
                    Font policeAgrandie = new Font(plateau.classement[i][j].Score.getFont().getName(), Font.PLAIN, 10);
                    commandes.remove(plateau.classement[i][j].Score);
                    plateau.classement[i][j].Score.setFont(policeAgrandie);
                    commandes.add(plateau.classement[i][j].Score, JLayeredPane.DEFAULT_LAYER);
                }
            }
        }
    }
        public void defInstruction(String Chemin ){
        ImageIcon imageIcon;

        imageIcon = new ImageIcon(Chemin);

        Image imageRedimenssionne = imageIcon.getImage().getScaledInstance(227, 40, Image.SCALE_SMOOTH);

        ImageIcon imageADessiner = new ImageIcon(imageRedimenssionne);
    
        instruction =new JLabel();
        instruction.setIcon(imageADessiner);
        
        instruction.setBounds(10,420,227,40);
        
       
        commandes.add(instruction, JLayeredPane.DEFAULT_LAYER);
    repaint();
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 898, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 574, Short.MAX_VALUE)
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
            new PlateauGraphique(4).setVisible(true);
        });
    }

    private void setContentAreaFilled(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
