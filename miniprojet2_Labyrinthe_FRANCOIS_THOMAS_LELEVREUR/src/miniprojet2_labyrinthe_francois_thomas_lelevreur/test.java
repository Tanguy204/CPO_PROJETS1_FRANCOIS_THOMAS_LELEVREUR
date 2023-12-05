/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package miniprojet2_labyrinthe_francois_thomas_lelevreur;

import java.awt.Dimension;
import java.awt.GridLayout;

/**
 *
 * @author Tanguy
 */
public class test extends javax.swing.JFrame {

    /**
     * Creates new form test
     */
    public test() {
        initComponents();
        pousse.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    // Charger et définir une nouvelle image lorsque la souris survole le bouton
                    ImageIcon icon = new ImageIcon(plateau.TuilePoussoire.Tuile.imageADessiner);
                    pousse.setIcon(icon);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    // Réinitialiser l'image lorsque la souris quitte le bouton
                    pousse.setIcon(null);
                }
            });
            pousse.addActionListener(ecouteur);
            BoutonsPousser[k].setPreferredSize(new Dimension(x, x));
            BoutonsPousser[k].setBounds(128+imp*x,  x-10, x, x);
            BoutonsPousser[k].add(pousse);
            layeredPane2.add(BoutonsPousser[k], JLayeredPane.DEFAULT_LAYER);
            
            
        }
        
        
        PlateauGrph.setLayout(new GridLayout(7, 7));
        TuileGraphique bouton_cellule = new TuileGraphique("C:\\OneDrive - Fondation EPF\\Documents\\Cours\\2ème année\\CPO\\CPO_PROJET_LAB\\araignee.png");
        PlateauGrph.add(bouton_cellule); // ajout au Jpanel PanneauGrille
        
        
            
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout PlateauGrphLayout = new javax.swing.GroupLayout(PlateauGrph);
        PlateauGrph.setLayout(PlateauGrphLayout);
        PlateauGrphLayout.setHorizontalGroup(
            PlateauGrphLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        PlateauGrphLayout.setVerticalGroup(
            PlateauGrphLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(PlateauGrph, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(149, 149, 149))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(84, 84, 84)
                .addComponent(PlateauGrph, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(116, Short.MAX_VALUE))
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
            java.util.logging.Logger.getLogger(test.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(test.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(test.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(test.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new test().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PlateauGrph;
    // End of variables declaration//GEN-END:variables
}
