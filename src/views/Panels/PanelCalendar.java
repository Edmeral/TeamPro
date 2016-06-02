
package views.Panels;

import io.socket.client.Socket;
import models.Project;
import views.AjouterEvenement;
import views.Panels.calendar.CalendarJPanel;
import java.awt.FlowLayout;
import javax.swing.JPanel;

public class PanelCalendar extends javax.swing.JPanel {
    Project project;
    JPanel CalenPanel;
    
    Socket socket;
    
    public PanelCalendar(Project project, Socket socket) {
        this.socket = socket;
        initComponents();
        this.project = project;
        initComponents2();
    }
    
    private void initComponents2() {
        panelCalendrier.setLayout(new FlowLayout());
        CalenPanel = new CalendarJPanel(project, socket);
        panelCalendrier.add(CalenPanel);
        panelCalendrier.repaint();
        panelCalendrier.revalidate();
        repaint();
        revalidate();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelCalendrier = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();

        setBackground(new java.awt.Color(106, 206, 201));

        panelCalendrier.setBackground(new java.awt.Color(106, 206, 201));

        javax.swing.GroupLayout panelCalendrierLayout = new javax.swing.GroupLayout(panelCalendrier);
        panelCalendrier.setLayout(panelCalendrierLayout);
        panelCalendrierLayout.setHorizontalGroup(
            panelCalendrierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelCalendrierLayout.setVerticalGroup(
            panelCalendrierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 264, Short.MAX_VALUE)
        );

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/BoutonAjouterEvent.png"))); // NOI18N
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelCalendrier, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(169, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(168, 168, 168))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelCalendrier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        AjouterEvenement FrAjouterEvenement = new AjouterEvenement(project, socket);
        FrAjouterEvenement.setParent(CalenPanel);
        FrAjouterEvenement.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel panelCalendrier;
    // End of variables declaration//GEN-END:variables
}
