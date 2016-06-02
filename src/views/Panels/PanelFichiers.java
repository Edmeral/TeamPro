package views.Panels;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.*;
import java.io.File;
import models.Fichier;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneLayout;
import javax.swing.table.DefaultTableModel;
import models.Project;

import App.config;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import java.net.URI;
import java.net.URL;

public class PanelFichiers extends javax.swing.JPanel {

    Project project;

    Socket socket;

    public List<Fichier> files = new ArrayList<>();

    public PanelFichiers(Project project, Socket socket) {
        this.socket = socket;
        initComponents();
        this.project = project;
        initComponents2();

        PanelFichiers self = this;
        try {
            socket.on("files-change-broadcast", new Emitter.Listener() {

                @Override
                public void call(Object... args) {
                    System.out.println("Adding file socket event");
                    project.update();
                    self.updateView();
                }

            });

        } catch (Exception e) {
            System.out.println("error in Adding task socket event");
            System.out.println(e);
        }
    }

    private void initComponents2() {
        MyDragDropListener myDragDropListener = new MyDragDropListener();
        new DropTarget(pnlGlissze, myDragDropListener);
        updateView();
    }

    public ImageIcon getIcon(String ext) {
        if (ext.equals("pdf")) {
            return new ImageIcon(getClass().getResource("/assets/icoPdf.png"));
        } else if (ext.equals("ppt")) {
            return new ImageIcon(getClass().getResource("/assets/icoPpt.png"));
        } else if (ext.equals("jpg") || ext.equals("png") || ext.equals("gif")) {
            return new ImageIcon(getClass().getResource("/assets/icoImg.png"));
        } else if (ext.equals("c") || ext.equals("java") || ext.equals("cpp") || ext.equals("html") || ext.equals("php")) {
            return new ImageIcon(getClass().getResource("/assets/icoCode.png"));
        } else if (ext.equals("txt")) {
            return new ImageIcon(getClass().getResource("/assets/icoTxt.png"));
        } else if (ext.equals("zip") || ext.equals("rar")) {
            return new ImageIcon(getClass().getResource("/assets/icoZip.png"));
        } else {
            return new ImageIcon(getClass().getResource("/assets/icoAutre.png"));
        }
    }

    public void updateView() {
        String[] columnNames = {"", "Nom du fichier"};
        files = project.getFiles();
        System.out.println(files.size());
        Object[][] data = new Object[files.size()][2];

        for (int i = 0; i < files.size(); i++) {
            data[i][0] = files.get(i).getNom();
            data[i][1] = getIcon(files.get(i).getExtension());
            System.out.println(files.get(i).getNom() + " | " + files.get(i).getExtension());
        }

        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        JTable table = new JTable(model) {
            public Class getColumnClass(int column) {
                return getValueAt(0, column).getClass();
            }
        };
        table.setRowHeight(45);
        table.setVisible(true);
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = table.rowAtPoint(evt.getPoint());
                if (row >= 0 && row < files.size()) {
                    try {
                        String url = config.serverURL + "/uploads/" + project.getId() + "/" + files.get(row).getName();
                        url = url.replace(" ", "%20");
                        System.out.println(url);
                        Desktop.getDesktop().browse(new URI(url));
                    } catch (Exception ex) {
                        Logger.getLogger(PanelFichiers.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });

        lstFiles.removeAll();
        lstFiles.setLayout(new BoxLayout(lstFiles, BoxLayout.Y_AXIS));
        lstFiles.add(table);
        lstFiles.revalidate();
        lstFiles.repaint();
    }

    class MyDragDropListener implements DropTargetListener {

        @Override
        public void drop(DropTargetDropEvent event) {
            event.acceptDrop(DnDConstants.ACTION_COPY);
            Transferable transferable = event.getTransferable();
            DataFlavor[] flavors = transferable.getTransferDataFlavors();

            for (DataFlavor flavor : flavors) {
                try {
                    if (flavor.isFlavorJavaFileListType()) {
                        List<File> files = (List) transferable.getTransferData(flavor);

                        for (File file : files) {
                            System.out.println(file.getPath());
                            project.addFile(file.getPath());
                            updateView();
                            try {
                                socket.emit("files-change", "");
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            // Inform that the drop is complete
            event.dropComplete(true);

        }

        @Override
        public void dragEnter(DropTargetDragEvent event) {
        }

        @Override
        public void dragExit(DropTargetEvent event) {
        }

        @Override
        public void dragOver(DropTargetDragEvent event) {
        }

        @Override
        public void dropActionChanged(DropTargetDragEvent event) {
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlGlissze = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstFiles = new javax.swing.JList();

        setBackground(new java.awt.Color(106, 206, 201));

        pnlGlissze.setBackground(new java.awt.Color(75, 190, 190));
        pnlGlissze.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102)));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/MessageGlissez.png"))); // NOI18N

        javax.swing.GroupLayout pnlGlisszeLayout = new javax.swing.GroupLayout(pnlGlissze);
        pnlGlissze.setLayout(pnlGlisszeLayout);
        pnlGlisszeLayout.setHorizontalGroup(
            pnlGlisszeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlGlisszeLayout.createSequentialGroup()
                .addGap(101, 101, 101)
                .addComponent(jLabel1)
                .addContainerGap(109, Short.MAX_VALUE))
        );
        pnlGlisszeLayout.setVerticalGroup(
            pnlGlisszeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlGlisszeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lstFiles.setForeground(new java.awt.Color(0, 102, 102));
        jScrollPane1.setViewportView(lstFiles);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(116, 116, 116)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addComponent(pnlGlissze, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(64, 64, 64))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(pnlGlissze, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList lstFiles;
    private javax.swing.JPanel pnlGlissze;
    // End of variables declaration//GEN-END:variables
}
