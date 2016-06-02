package views;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import models.User;

public class Connexion extends javax.swing.JFrame {

    public Connexion() {
        // ne rien écrire dans le standard output (pour désactiver l'écriture en console)
//        System.setOut(new PrintStream(new OutputStream() {
//            public void write(int b) {
//            }
//        }));
//        System.setErr(new PrintStream(new OutputStream() {
//            public void write(int b) {
//            }
//        }));
        pack();
        initComponents();
        initComponents2();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnConnexion = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txtLogin = new javax.swing.JTextField();
        msgErreur = new javax.swing.JLabel();
        txtPass = new javax.swing.JPasswordField();
        btnCreerCompte = new javax.swing.JButton();

        jLabel4.setText("jLabel4");

        jRadioButton1.setText("jRadioButton1");

        jLabel3.setText("jLabel3");

        jPasswordField1.setText("jPasswordField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(153, 204, 255));

        jPanel1.setBackground(new java.awt.Color(106, 206, 201));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(141, 223, 223), 6));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/logo.png"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Lao UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(18, 115, 110));
        jLabel2.setText("Nom de l'utilisateur :");

        btnConnexion.setBackground(new java.awt.Color(255, 255, 255));
        btnConnexion.setFont(new java.awt.Font("Lato", 0, 18)); // NOI18N
        btnConnexion.setForeground(new java.awt.Color(0, 102, 102));
        btnConnexion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/BoutonConnexion.png"))); // NOI18N
        btnConnexion.setBorder(null);
        btnConnexion.setBorderPainted(false);
        btnConnexion.setContentAreaFilled(false);
        btnConnexion.setNextFocusableComponent(txtLogin);
        btnConnexion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConnexionActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Lao UI", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(18, 115, 110));
        jLabel6.setText("Mot de passe :");

        txtLogin.setFont(new java.awt.Font("Lao UI", 0, 14)); // NOI18N
        txtLogin.setToolTipText("");
        txtLogin.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(50, 157, 157)));
        txtLogin.setMargin(new java.awt.Insets(5, 5, 5, 5));
        txtLogin.setNextFocusableComponent(txtPass);
        txtLogin.setSelectionColor(new java.awt.Color(0, 153, 153));
        txtLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLoginActionPerformed(evt);
            }
        });

        msgErreur.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/erreurVide.png"))); // NOI18N

        txtPass.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(50, 157, 157)));
        txtPass.setNextFocusableComponent(btnConnexion);

        btnCreerCompte.setBackground(new java.awt.Color(255, 255, 255));
        btnCreerCompte.setFont(new java.awt.Font("Lato", 0, 18)); // NOI18N
        btnCreerCompte.setForeground(new java.awt.Color(0, 102, 102));
        btnCreerCompte.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/créerCompte.png"))); // NOI18N
        btnCreerCompte.setBorder(null);
        btnCreerCompte.setBorderPainted(false);
        btnCreerCompte.setContentAreaFilled(false);
        btnCreerCompte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreerCompteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtLogin)
                                .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel6)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(btnConnexion)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(btnCreerCompte))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(msgErreur))))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(msgErreur)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(61, 61, 61)
                .addComponent(btnConnexion, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 76, Short.MAX_VALUE)
                .addComponent(btnCreerCompte, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void initComponents2() {
        setLocationRelativeTo(null);
        setTitle("TeamPro - Connexion");

        txtLogin.setBorder(BorderFactory.createCompoundBorder(
                txtLogin.getBorder(),
                BorderFactory.createEmptyBorder(5, 8, 5, 8)));
        txtPass.setBorder(txtLogin.getBorder());
    }

    private void btnConnexionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConnexionActionPerformed
        JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this); // getting the parent frame
        try {
            // -1 : mdp incorrect   0 : email inexistant    1 : connexion réussite   

            // Champs non remplies
            if (txtLogin.getText().equals("") || txtPass.getText().equals("")) {
                msgErreur.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/erreurRemplir.png")));
                if (txtLogin.getText().equals("")) {
                    txtLogin.requestFocus();
                } else {
                    txtPass.requestFocus();
                }
                return;
            }

            String email = txtLogin.getText(), password = txtPass.getText();
            User newUser = new User(email, password);

            int codeConnexion = Integer.parseInt(newUser.connect());

            // -1 : mdp incorrect
            if (codeConnexion == -1) {
                msgErreur.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/erreurIncorrect.png")));
                txtPass.requestFocus();
            } // 0 : email inexistant
            else if (codeConnexion == 0) {
                msgErreur.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/erreurInexistant.png")));
                txtLogin.requestFocus();
            } // 1 : connexion réussite   
            else {
                // Connexion réussite
                msgErreur.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/erreurVide.png")));
                Accueil accueil = new Accueil(newUser);
                accueil.setVisible(true);
                this.dispose();
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(topFrame, "Erreur: IOException", "Erreur :(", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(Inscription.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btnConnexionActionPerformed

    private void btnCreerCompteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreerCompteActionPerformed
        setVisible(false);
        Inscription FrInscription = new Inscription();
        FrInscription.setParent(this);
        FrInscription.setVisible(true);
    }//GEN-LAST:event_btnCreerCompteActionPerformed

    private void txtLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLoginActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLoginActionPerformed

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
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Connexion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Connexion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Connexion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Connexion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Connexion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConnexion;
    private javax.swing.JButton btnCreerCompte;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JLabel msgErreur;
    private javax.swing.JTextField txtLogin;
    private javax.swing.JPasswordField txtPass;
    // End of variables declaration//GEN-END:variables
}
