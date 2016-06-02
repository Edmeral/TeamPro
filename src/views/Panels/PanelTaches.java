package views.Panels;

import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import models.Task;
import models.Project;
import views.AjouterTache;
import views.ModifierTache;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;

public class PanelTaches extends javax.swing.JPanel {

    Project project;

    Socket socket;

    public PanelTaches(Project project, Socket socket) {
        initComponents();
        this.socket = socket;
        this.project = project;
        System.out.println("PanelTaches : " + project);
        initComponents2();

        PanelTaches self = this;

        // Setting up Socket.io
        try {
            socket.on("tasks-change-broadcast", new Emitter.Listener() {

                @Override
                public void call(Object... args) {
                    System.out.println("Adding task socket event");
                    project.update();
                    self.updateTaskList();
                }

            });

        } catch (Exception e) {
            System.out.println("error in Adding task socket event");
            System.out.println(e);
        }
    }

    private void initComponents2() {
        updateTaskList();
        JPanel parent = this;
        lstTaches.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                JList list = (JList) evt.getSource();
                if (evt.getClickCount() == 2) {
                    int index = list.locationToIndex(evt.getPoint());
                    try {
                        ModifierTache FrModifierTache = new ModifierTache(socket);
                        FrModifierTache.setParent(parent);
                        FrModifierTache.setTache(project, index);
                        FrModifierTache.setVisible(true);
                    } catch (SQLException ex) {
                        Logger.getLogger(PanelTaches.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstTaches = new javax.swing.JList();

        jTextField1.setText("jTextField1");

        setBackground(new java.awt.Color(106, 206, 201));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/BoutonNouvelleTache.png"))); // NOI18N
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        lstTaches.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 153)));
        lstTaches.setFont(new java.awt.Font("Lao UI", 0, 20)); // NOI18N
        lstTaches.setForeground(new java.awt.Color(29, 99, 92));
        lstTaches.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Hi" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        lstTaches.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane1.setViewportView(lstTaches);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(120, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 443, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(106, 106, 106))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(185, 185, 185))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(47, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        AjouterTache FrAjouterTache = new AjouterTache(socket);
        FrAjouterTache.setParent(this);
        FrAjouterTache.setProjet(project);
        FrAjouterTache.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JList lstTaches;
    // End of variables declaration//GEN-END:variables

    public void updateTaskList() {
        lstTaches.removeAll();

        ArrayList<Task> taches = project.getTasks();
        DefaultListModel listModel = new DefaultListModel();

        for (Task tache : taches) {
            listModel.addElement("<html>&nbsp;&nbsp;<b>&nbsp;&nbsp;&nbsp;" + tache.getDescription() + "</b>&nbsp;&nbsp; (" + tache.getProgress() + "%)</html>");
        }

        lstTaches.setModel(listModel);

    }
}
