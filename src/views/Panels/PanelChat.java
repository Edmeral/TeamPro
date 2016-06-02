package views.Panels;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import models.Project;
import models.User;
import App.config;
import java.util.ArrayList;
import models.Message;
import org.json.JSONObject;

public class PanelChat extends javax.swing.JPanel {

    Project project;
    User user;

    public Socket socket;

    public PanelChat(Project project, User user, Socket socket) {
        this.socket = socket;
        this.project = project;
        this.user = user;
        initComponents();
        initComponents2();
        PanelChat self = this;

        ArrayList<Message> messages = project.getConversation();
        for (Message message : messages) {
            String txt = message.getEmail() + " : " + message.getContent();
            this.setMsgArea(self.getMsgArea() + txt + " \n ---------------------------- \n");
        }
        try {
            socket.on("message-broadcast", new Emitter.Listener() {

                @Override
                public void call(Object... args) {
//                    String msg = (String) args[0];
//                    JSONObject msgObj = new JSONObject(msg);
//                    String txt = msgObj.getString("user") + " : " + msgObj.getString("msg");
//                    self.setMsgArea(self.getMsgArea() + txt + " \n ---------------------------- \n");
                    project.updateConversation();
                    ArrayList<Message> messages = project.getConversation();
                    self.setMsgArea("");
                    for (Message message : messages) {
                        String txt = message.getEmail() + " : " + message.getContent();
                        self.setMsgArea(self.getMsgArea() + txt + " \n ---------------------------- \n");
                    }
                }

            }).on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {

                @Override
                public void call(Object... args) {
                }

            });

        } catch (Exception e) {
            System.out.println("error in Main");
            System.out.println(e);
        }
    }

    private void initComponents2() {

    }

    public String getMsgArea() {
        return messagesArea.getText();
    }

    public void setMsgArea(String txt) {
        messagesArea.setText(txt);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        messageField = new javax.swing.JTextField();
        btnEnvoyer = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        messagesArea = new javax.swing.JTextArea();

        setBackground(new java.awt.Color(106, 206, 201));

        messageField.setForeground(new java.awt.Color(102, 102, 102));
        messageField.setText("   ");
        messageField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 153)));

        btnEnvoyer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/BoutonEnvoyer.png"))); // NOI18N
        btnEnvoyer.setBorderPainted(false);
        btnEnvoyer.setContentAreaFilled(false);
        btnEnvoyer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnvoyerActionPerformed(evt);
            }
        });

        messagesArea.setColumns(20);
        messagesArea.setRows(5);
        jScrollPane1.setViewportView(messagesArea);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(messageField, javax.swing.GroupLayout.DEFAULT_SIZE, 472, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(btnEnvoyer, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 610, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(messageField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEnvoyer, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnEnvoyerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnvoyerActionPerformed
        // TODO add your handling code here:
        String msg = messageField.getText();
        messageField.setText("");
        messagesArea.setText(messagesArea.getText() + user.getEmail() + " : " + msg + " \n ---------------------------- \n");
        try {
            socket.emit("message", new JSONObject().put("user", user.getEmail()).put("msg", msg).toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_btnEnvoyerActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEnvoyer;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField messageField;
    private javax.swing.JTextArea messagesArea;
    // End of variables declaration//GEN-END:variables
}
