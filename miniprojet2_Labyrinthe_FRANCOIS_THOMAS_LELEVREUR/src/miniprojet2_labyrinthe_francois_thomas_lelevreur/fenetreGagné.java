package miniprojet2_labyrinthe_francois_thomas_lelevreur;


import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author Tanguy
 */
public class fenetreGagné extends javax.swing.JFrame {
    Plateau plateau;
    Rond[] Classement;
    Pion[][] classement1;
    
    /**
     * Creates new form fenetreGagné
     */
    public fenetreGagné(Pion[][] classement) {
        
        initComponents();
        classement1=classement;
        Classement= new Rond[4];
        setSize(448, 448);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int L = (int) screenSize.getWidth();
        int H = (int) screenSize.getHeight();
        
        setLocation((L-1000)/2+100,(H-700)/2+ 100);
        
        Image image = new ImageIcon(getClass().getResource("/images/victoire.png")).getImage();
        Image imageRedimenssionne = image.getScaledInstance(448, 448, Image.SCALE_SMOOTH);

        Image imageADessiner = new ImageIcon(imageRedimenssionne).getImage();
        JLayeredPane layeredPane = new JLayeredPane() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(imageADessiner, 0, 0, 448, 448, (ImageObserver) this);
            }
        };
        layeredPane.setBounds(0, 0, 448, 448);
        add(layeredPane);
        int rg = 0;
        for (int i = 0; i<classement1.length;i++){
            for (int j = 0; j<classement1.length;j++){
                if (classement1[i][j]!=null){
                    Classement[rg]=classement1[i][j].pionClassement;
                    rg+=1;
                }
            }
        }
        Classement[0].setBounds(210,155,30,30);
        if (Classement[1]!=null){
        Classement[1].setBounds(157,175,30,30);
        }
        if (Classement[2]!=null){
            Classement[2].setBounds(255,193,30,30);
        }
        for (int t=0 ; t<4;t++){
            if (Classement[t]!=null){
            layeredPane.add(Classement[t], JLayeredPane.DEFAULT_LAYER);
            }
        }
        JPanel btnGagné = new JPanel();
        btnGagné.setOpaque(false);
        btnGagné.setLayout(new GridLayout(1, 2));
        JButton Jbtncontinuer = new JButton();
        
        
        ActionListener droite = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                

                repaint();

            }
        };
        Jbtncontinuer.addActionListener(droite);
        btnGagné.add(Jbtncontinuer);
        Jbtncontinuer.setBounds(142, 540, 40, 40);
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
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
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
            java.util.logging.Logger.getLogger(fenetreGagné.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(fenetreGagné.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(fenetreGagné.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(fenetreGagné.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
