package views;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import models.User;

public class Inscription extends javax.swing.JFrame {
    JFrame Parent;
    
    public Inscription() {
        pack();
        initComponents();
        initComponents2();
    }

    public void setParent(JFrame Parent) {
        this.Parent = Parent;
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
        btnInscription = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txtNewLogin = new javax.swing.JTextField();
        txtNewPass = new javax.swing.JPasswordField();
        txtNewPass2 = new javax.swing.JPasswordField();
        jLabel7 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        msgErreur = new javax.swing.JLabel();

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

        btnInscription.setBackground(new java.awt.Color(255, 255, 255));
        btnInscription.setFont(new java.awt.Font("Lato", 0, 18)); // NOI18N
        btnInscription.setForeground(new java.awt.Color(0, 102, 102));
        btnInscription.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/BoutonInscription.png"))); // NOI18N
        btnInscription.setBorder(null);
        btnInscription.setBorderPainted(false);
        btnInscription.setContentAreaFilled(false);
        btnInscription.setNextFocusableComponent(txtNewLogin);
        btnInscription.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInscriptionActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Lao UI", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(18, 115, 110));
        jLabel6.setText("Mot de passe :");

        txtNewLogin.setFont(new java.awt.Font("Lao UI", 0, 14)); // NOI18N
        txtNewLogin.setToolTipText("");
        txtNewLogin.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(50, 157, 157)));
        txtNewLogin.setMargin(new java.awt.Insets(5, 5, 5, 5));
        txtNewLogin.setNextFocusableComponent(txtNewPass);
        txtNewLogin.setSelectionColor(new java.awt.Color(0, 153, 153));
        txtNewLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNewLoginActionPerformed(evt);
            }
        });

        txtNewPass.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(50, 157, 157)));
        txtNewPass.setNextFocusableComponent(txtNewPass2);

        txtNewPass2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(50, 157, 157)));
        txtNewPass2.setNextFocusableComponent(btnInscription);

        jLabel7.setFont(new java.awt.Font("Lao UI", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(18, 115, 110));
        jLabel7.setText("Réécrire le mot de passe :");

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Lao UI", 0, 36)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Inscription");

        msgErreur.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/erreurVide.png"))); // NOI18N

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
                                .addComponent(txtNewLogin)
                                .addComponent(txtNewPass, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel6)
                            .addComponent(txtNewPass2, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(msgErreur)
                            .addComponent(jLabel1)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(103, 103, 103)
                        .addComponent(jLabel5))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(84, 84, 84)
                        .addComponent(btnInscription)))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addGap(24, 24, 24)
                .addComponent(msgErreur)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNewLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNewPass, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNewPass2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52)
                .addComponent(btnInscription, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(57, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void initComponents2(){
        setLocationRelativeTo(null);
        setTitle("TeamPro - Inscription");
        
        txtNewLogin.setBorder(BorderFactory.createCompoundBorder(
            txtNewLogin.getBorder(), 
            BorderFactory.createEmptyBorder(5, 8, 5, 8)));
        txtNewPass.setBorder(txtNewLogin.getBorder());
        txtNewPass2.setBorder(txtNewLogin.getBorder());
    }
    
    private void btnInscriptionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInscriptionActionPerformed
        JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this); // getting the parent frame
        try {
            // -1 : mdp incorrect   0 : email inexistant    1 : connexion réussite   
            
            // Champs non remplies
            if(txtNewLogin.getText().equals("") || txtNewPass.getText().equals("") || txtNewPass2.getText().equals(""))
            {
                msgErreur.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/erreurRemplir.png")));
                if(txtNewLogin.getText().equals(""))
                    txtNewLogin.requestFocus();
                else if(txtNewPass.getText().equals(""))
                    txtNewPass.requestFocus();
                else
                    txtNewPass2.requestFocus();
                return;
            }
            
            // 2 mots de passes ne sont pas identiques
            if(!txtNewPass.getText().equals(txtNewPass2.getText()))
            {
                msgErreur.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/erreurIdentiques.png")));
                txtNewPass.setText("");
                txtNewPass2.setText("");
                txtNewPass.requestFocus();
                return;
            }
            
            // 1 : login déjà pris      OK : créé avec succès
            String email = txtNewLogin.getText(), password = txtNewPass.getText();
            User newUser = new User(email, password);
            
            String codeConnexion = newUser.save();
            System.out.println(codeConnexion);
            
            if(codeConnexion.equals("1"))
            {
                msgErreur.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/erreurPris.png")));
                txtNewLogin.setText("");
                txtNewPass.requestFocus();
            }
            else if(codeConnexion.equals("OK"))// Compte Créé
            {
                JOptionPane.showMessageDialog(this, "Votre compte a été créé avec succès!");
                Accueil accueil = new Accueil(newUser);
                accueil.setVisible(true);
                this.dispose();
//                if(Parent != null)
//                    Parent.setVisible(true);
//                setVisible(false);
//                dispose();
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(topFrame, "Erreur: IOException", "Erreur :(", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(Inscription.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }//GEN-LAST:event_btnInscriptionActionPerformed

    public void closingListener(){
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        JFrame frame = this;
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if(Parent != null)
                    Parent.setVisible(true);
            }
        });

    }
    
    private void txtNewLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNewLoginActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNewLoginActionPerformed

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
            java.util.logging.Logger.getLogger(Inscription.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Inscription.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Inscription.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Inscription.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Inscription().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnInscription;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JLabel msgErreur;
    private javax.swing.JTextField txtNewLogin;
    private javax.swing.JPasswordField txtNewPass;
    private javax.swing.JPasswordField txtNewPass2;
    // End of variables declaration//GEN-END:variables
}
