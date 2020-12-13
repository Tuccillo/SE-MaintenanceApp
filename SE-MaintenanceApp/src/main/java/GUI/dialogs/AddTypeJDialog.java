/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.dialogs;

import GUI.Messages;
import com.team14.se.maintenanceapp.Typology;
import java.awt.Component;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

/**
 *
 * @author mario
 */
public class AddTypeJDialog extends javax.swing.JDialog {
    
    private final AddTypeJDialog frame = this;
    
    private Connection connection;
    private Typology newType;

    /**
     * Creates new form AddTypeJDialog
     * 
     * @param parent the parent frame
     * @param modal control if the dialog should be modal
     */
    public AddTypeJDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
    
    /**
     * Creates new form AddTypeJDialog
     * 
     * @param parent the parent frame
     * @param modal control if the dialog should be modal
     * @param connection the database connection
     */
    public AddTypeJDialog(java.awt.Frame parent, boolean modal, Connection connection) {
        super(parent, modal);
        this.newType = null;
        this.connection = connection;
        initComponents();
        setLocationRelativeTo(parent);
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        nameJLabel = new javax.swing.JLabel();
        nameJTextField = new javax.swing.JTextField();
        cancelJButton = new javax.swing.JButton();
        addJButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Add New Type");
        setResizable(false);

        nameJLabel.setText("Name");

        cancelJButton.setText("Cancel");
        cancelJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelJButtonActionPerformed(evt);
            }
        });

        addJButton.setText("Add Type");
        addJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addJButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(nameJLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nameJTextField))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 214, Short.MAX_VALUE)
                        .addComponent(cancelJButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(addJButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameJLabel)
                    .addComponent(nameJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addJButton)
                    .addComponent(cancelJButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cancelJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelJButtonActionPerformed
        this.dispose();
    }//GEN-LAST:event_cancelJButtonActionPerformed

    private void addJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addJButtonActionPerformed
        for(Component component:this.getContentPane().getComponents()){
            component.setEnabled(false);
        }

        String name = this.nameJTextField.getText();

        if (name.isBlank()) {
            Messages.showErrorEmptyfield(this, "Name");
        } else {
            newType = new Typology(name);
            new AddTypeWorker().execute();
            return;
        }

        for(Component component:this.getContentPane().getComponents()){
            component.setEnabled(true);
        }
    }//GEN-LAST:event_addJButtonActionPerformed

    private class AddTypeWorker extends SwingWorker<Boolean , Void> {
        @Override
        protected Boolean doInBackground() throws Exception {
            try {
                Typology.addTypology(connection, newType);
                return true;
            } catch(SQLException ex){
                Logger.getLogger(AddCompetenceJDialog.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        }

        @Override
        protected void done() {
            try {
                boolean result = get();
                if (result) {
                    JOptionPane.showMessageDialog(frame, "Type Created!");
                    frame.dispose();
                } else {
                    JOptionPane.showMessageDialog(frame,
                        "An error has occurred",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                    for(Component component:frame.getContentPane().getComponents()){
                        component.setEnabled(true);
                    }
                }
            } catch (InterruptedException | ExecutionException ex) {
                Logger.getLogger(AddUserJDialog.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addJButton;
    private javax.swing.JButton cancelJButton;
    private javax.swing.JLabel nameJLabel;
    private javax.swing.JTextField nameJTextField;
    // End of variables declaration//GEN-END:variables
}
