package views;

import App.config;
import views.Panels.PanelChat;
import views.Panels.PanelFichiers;
import views.Panels.PanelTaches;
import App.utils;
import views.Panels.PanelAccueil;
import views.Panels.PanelCalendar;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import models.Project;
import models.User;
import org.json.JSONObject;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class Gestion extends javax.swing.JFrame {

    JPanel views;
    JPanel FrAccueil;
    JPanel FrTaches;
    JPanel FrChat;
    JPanel FrFichiers;
    JPanel FrCalendar;
    List<JButton> Sidebar;
    List<String> labels;
    JFrame Parent;

    User user;

    Project project;
    
    Socket socket;

    public void setProjet(String projectId) {
        try {
            project = new Project(new JSONObject(utils.get("/project/" + projectId)));
            project.display();

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Erreur: IOException", "Erreur :(", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(Inscription.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            IO.Options opts = new IO.Options();
            opts.forceNew = true;
            opts.query = "projectId=" + this.project.getId();
            socket = IO.socket(config.serverURL, opts);
        } catch (Exception e) {
            System.out.println("error when setting up web sockets");
            System.out.println(e);
        }
        
        initViews();
    }

    public void initViews() {
        pack();
        setLocationRelativeTo(null);
        setTitle("TeamPro - " + project.getName() + " - " + user.getEmail());
        FrAccueil = new PanelAccueil(project, socket);
        FrTaches   = new PanelTaches(project, socket);
        FrChat     = new PanelChat(project, user, socket);
        FrCalendar = new PanelCalendar(project, socket);
        FrFichiers = new PanelFichiers(project, socket);
        
        socket.connect();
    

        ((PanelAccueil) FrAccueil).setParent(this);

        views = new JPanel(new CardLayout());
        views.add(FrAccueil, "Progres");
        views.add(FrTaches, "Taches");
        views.add(FrChat, "Chat");
        views.add(FrFichiers, "Fichiers");
        views.add(FrCalendar, "Calendrier");

        switchViewTo("Progres");

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(views);
        mainPanel.repaint();
        mainPanel.revalidate();
        repaint();
        revalidate();
    }

    public Gestion(User user) {
        this.user = user;
        initComponents();
        initSidebar();
        closingListener();
    }

    public void initSidebar() {
        this.Sidebar = new ArrayList();
        Sidebar.add(btnTableauBord);
        Sidebar.add(btnTaches);
        Sidebar.add(btnDiscussion);
        Sidebar.add(btnCalendrier);
        Sidebar.add(btnFichiers);

        labels = new ArrayList();
        labels.add("Progres");
        labels.add("Taches");
        labels.add("Chat");
        labels.add("Calendrier");
        labels.add("Fichiers");
    }

    public void closingListener() {
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        JFrame frame = this;
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if (JOptionPane.showConfirmDialog(frame,
                        "Voulez-vous vraiment quitter?", "Quitter?",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                    setVisible(false);
                     System.exit(0);
                    if (Parent != null) {
                        Parent.setVisible(true);
                       
                    }
                }
            }
        });

    }

    public void setParent(JFrame Parent) {
        this.Parent = Parent;
    }

    public void switchViewTo(String view) {
        String adr = "/assets/";
        ((CardLayout) views.getLayout()).show(views, view);
        for (int i = 0; i < Sidebar.size(); i++) {
            if (view.equals(labels.get(i))) {
                Sidebar.get(i).setIcon(new javax.swing.ImageIcon(getClass().getResource(adr + labels.get(i) + "2.png")));
            } else {
                Sidebar.get(i).setIcon(new javax.swing.ImageIcon(getClass().getResource(adr + labels.get(i) + "1.png")));
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelAccueil = new javax.swing.JPanel();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 32767));
        jPanel2 = new javax.swing.JPanel();
        btnTaches = new javax.swing.JButton();
        btnTableauBord = new javax.swing.JButton();
        btnDiscussion = new javax.swing.JButton();
        btnCalendrier = new javax.swing.JButton();
        btnFichiers = new javax.swing.JButton();
        filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        jLabel1 = new javax.swing.JLabel();
        mainPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(217, 239, 237));

        jPanelAccueil.setBackground(new java.awt.Color(106, 206, 201));

        jPanel2.setBackground(new java.awt.Color(217, 239, 237));

        btnTaches.setBackground(new java.awt.Color(255, 255, 255));
        btnTaches.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/Taches1.png"))); // NOI18N
        btnTaches.setBorder(null);
        btnTaches.setBorderPainted(false);
        btnTaches.setContentAreaFilled(false);
        btnTaches.setFocusPainted(false);
        btnTaches.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTachesActionPerformed(evt);
            }
        });

        btnTableauBord.setBackground(new java.awt.Color(255, 255, 255));
        btnTableauBord.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/Progres1.png"))); // NOI18N
        btnTableauBord.setBorder(null);
        btnTableauBord.setBorderPainted(false);
        btnTableauBord.setContentAreaFilled(false);
        btnTableauBord.setFocusPainted(false);
        btnTableauBord.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTableauBordActionPerformed(evt);
            }
        });

        btnDiscussion.setBackground(new java.awt.Color(255, 255, 255));
        btnDiscussion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/Chat1.png"))); // NOI18N
        btnDiscussion.setBorder(null);
        btnDiscussion.setBorderPainted(false);
        btnDiscussion.setContentAreaFilled(false);
        btnDiscussion.setFocusPainted(false);
        btnDiscussion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDiscussionActionPerformed(evt);
            }
        });

        btnCalendrier.setBackground(new java.awt.Color(255, 255, 255));
        btnCalendrier.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/Calendrier1.png"))); // NOI18N
        btnCalendrier.setBorder(null);
        btnCalendrier.setBorderPainted(false);
        btnCalendrier.setContentAreaFilled(false);
        btnCalendrier.setFocusPainted(false);
        btnCalendrier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCalendrierActionPerformed(evt);
            }
        });

        btnFichiers.setBackground(new java.awt.Color(255, 255, 255));
        btnFichiers.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/Fichiers1.png"))); // NOI18N
        btnFichiers.setBorder(null);
        btnFichiers.setBorderPainted(false);
        btnFichiers.setContentAreaFilled(false);
        btnFichiers.setFocusPainted(false);
        btnFichiers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFichiersActionPerformed(evt);
            }
        });

        jLabel1.setText("jLabel1");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(filler3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(btnTableauBord)
                .addGap(31, 31, 31)
                .addComponent(btnTaches)
                .addGap(29, 29, 29)
                .addComponent(btnDiscussion)
                .addGap(34, 34, 34)
                .addComponent(btnCalendrier)
                .addGap(28, 28, 28)
                .addComponent(btnFichiers)
                .addContainerGap(51, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnTableauBord, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCalendrier, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFichiers, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTaches, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDiscussion, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addGap(14, 14, 14)
                .addComponent(filler3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        mainPanel.setBackground(new java.awt.Color(106, 206, 201));

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 669, Short.MAX_VALUE)
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 338, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanelAccueilLayout = new javax.swing.GroupLayout(jPanelAccueil);
        jPanelAccueil.setLayout(jPanelAccueilLayout);
        jPanelAccueilLayout.setHorizontalGroup(
            jPanelAccueilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAccueilLayout.createSequentialGroup()
                .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(filler2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanelAccueilLayout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanelAccueilLayout.setVerticalGroup(
            jPanelAccueilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanelAccueilLayout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelAccueilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelAccueilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanelAccueilLayout.createSequentialGroup()
                            .addGap(285, 285, 285)
                            .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(filler2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelAccueilLayout.createSequentialGroup()
                        .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelAccueil, javax.swing.GroupLayout.PREFERRED_SIZE, 671, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelAccueil, javax.swing.GroupLayout.PREFERRED_SIZE, 440, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnFichiersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFichiersActionPerformed
        switchViewTo("Fichiers");
    }//GEN-LAST:event_btnFichiersActionPerformed

    private void btnCalendrierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCalendrierActionPerformed
        switchViewTo("Calendrier");
    }//GEN-LAST:event_btnCalendrierActionPerformed

    private void btnDiscussionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDiscussionActionPerformed
        switchViewTo("Chat");
    }//GEN-LAST:event_btnDiscussionActionPerformed

    private void btnTableauBordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTableauBordActionPerformed
        ((PanelAccueil)FrAccueil).updateInfos();
        switchViewTo("Progres");
    }//GEN-LAST:event_btnTableauBordActionPerformed

    private void btnTachesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTachesActionPerformed
        switchViewTo("Taches");
    }//GEN-LAST:event_btnTachesActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Gestion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Gestion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Gestion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Gestion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Gestion gestion = new Gestion(new User("edmeral.aissam@gmail.com", "123456"));
                gestion.setProjet("5693a5bf72c714705f94a39c");
                gestion.setSize(735, 465);
                gestion.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCalendrier;
    private javax.swing.JButton btnDiscussion;
    private javax.swing.JButton btnFichiers;
    private javax.swing.JButton btnTableauBord;
    private javax.swing.JButton btnTaches;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.Box.Filler filler3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanelAccueil;
    private javax.swing.JPanel mainPanel;
    // End of variables declaration//GEN-END:variables
}
