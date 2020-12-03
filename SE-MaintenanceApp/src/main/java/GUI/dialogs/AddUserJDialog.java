/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.dialogs;

import GUI.Messages;
import com.team14.se.maintenanceapp.Competence;
import com.team14.se.maintenanceapp.User;
import java.awt.Component;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
/**
 *
 * @author mario
 */
public class AddUserJDialog extends javax.swing.JDialog {
    
    private final AddUserJDialog frame = this;
    
    private Connection connection;
    private User newUser;

    /**
     * Creates new form AddUserJDialog
     * 
     * @param parent the parent frame
     * @param modal control if the dialog should be modal
     */
    public AddUserJDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        this.newUser = null;
        initComponents();
    }
    
    /**
     * Creates new form AddUserJDialog
     * 
     * @param parent the parent frame
     * @param modal control if the dialog should be modal
     * @param connection the database connection
     * @param competencesList list of total competence available
     */
    public AddUserJDialog(java.awt.Frame parent, 
            boolean modal, 
            Connection connection, 
            LinkedList<Competence> competencesList) {
        
        super(parent, modal);
        this.newUser = null;
        
        this.connection = connection;
        
        initComponents();
        this.competencePanel.activate(new LinkedList<>(), competencesList);
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
        surnameJLabel = new javax.swing.JLabel();
        usernameJLabel = new javax.swing.JLabel();
        passwordJLabel = new javax.swing.JLabel();
        password2JLabel = new javax.swing.JLabel();
        competencePanel = new GUI.CompetencePanel();
        nameJTextField = new javax.swing.JTextField();
        surnameJTextField = new javax.swing.JTextField();
        usernameJTextField = new javax.swing.JTextField();
        jPasswordField1 = new javax.swing.JPasswordField();
        jPasswordField2 = new javax.swing.JPasswordField();
        addUserJButton = new javax.swing.JButton();
        cancelJButton = new javax.swing.JButton();
        roleJLabel = new javax.swing.JLabel();
        roleJComboBox = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Add New User");
        setResizable(false);

        nameJLabel.setText("Name");

        surnameJLabel.setText("Surname");

        usernameJLabel.setText("Username");

        passwordJLabel.setText("Password");

        password2JLabel.setText("Repet Password");

        addUserJButton.setText("Add New User");
        addUserJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addUserJButtonActionPerformed(evt);
            }
        });

        cancelJButton.setText("Cancel");
        cancelJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelJButtonActionPerformed(evt);
            }
        });

        roleJLabel.setText("Role");

        roleJComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Maintainer", "Planner", "SystemAdministrator" }));
        roleJComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roleJComboBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(competencePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(cancelJButton)
                        .addGap(18, 18, 18)
                        .addComponent(addUserJButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(password2JLabel)
                            .addComponent(passwordJLabel)
                            .addComponent(usernameJLabel)
                            .addComponent(surnameJLabel)
                            .addComponent(nameJLabel)
                            .addComponent(roleJLabel))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nameJTextField)
                            .addComponent(surnameJTextField)
                            .addComponent(usernameJTextField)
                            .addComponent(jPasswordField1)
                            .addComponent(jPasswordField2)
                            .addComponent(roleJComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameJLabel)
                    .addComponent(nameJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(surnameJLabel)
                    .addComponent(surnameJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(usernameJLabel)
                    .addComponent(usernameJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(passwordJLabel)
                    .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(password2JLabel)
                    .addComponent(jPasswordField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(roleJLabel)
                    .addComponent(roleJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(competencePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addUserJButton)
                    .addComponent(cancelJButton))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cancelJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelJButtonActionPerformed
        this.dispose();
    }//GEN-LAST:event_cancelJButtonActionPerformed
 
    private void addUserJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addUserJButtonActionPerformed
        for(Component component:this.getContentPane().getComponents()){
            component.setEnabled(false);
        }
        
        String name = this.nameJTextField.getText();
        String surname = this.surnameJTextField.getText();
        String username = this.usernameJTextField.getText();
        String password1 = new String(this.jPasswordField1.getPassword());
        String password2 = new String(this.jPasswordField2.getPassword());
        String role = (String)roleJComboBox.getSelectedItem();
        
        if (name.isBlank()) {
            Messages.showErrorEmptyfield(this, "Name");
        } else if (surname.isBlank()) {
            Messages.showErrorEmptyfield(this, "Surname");
        } else if (username.isBlank()) {
            Messages.showErrorEmptyfield(this, "Username");
        } else if (password1.isBlank()) {
            Messages.showErrorEmptyfield(this, "Passord");
        } else if (password2.isBlank()) {
            Messages.showErrorEmptyfield(this, "Password 2");
        } else if (!password1.equals(password2)) {
            JOptionPane.showMessageDialog(this,
            "Passwords not equals",
            "Error",
            JOptionPane.ERROR_MESSAGE);
        } else {
            newUser = new User(name, surname, username, password1, true, role);
            new AddUserWorker().execute();
            return;
        }
        
        for(Component component:this.getContentPane().getComponents()){
            component.setEnabled(true);
        }
    }//GEN-LAST:event_addUserJButtonActionPerformed

    class AddUserWorker extends SwingWorker<Boolean , Void> {
        @Override
        protected Boolean doInBackground() throws Exception {
            try {
                newUser.addUser(connection, newUser);
                return true;
            } catch(SQLException ex){
                Logger.getLogger(AddUserJDialog.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        }

        @Override
        protected void done() {
            try {
                boolean result = get();
                if (result) {
                    JOptionPane.showMessageDialog(frame, "User Created!");
                    if (newUser.getRole().equals("Maintainer")){
                        JOptionPane.showMessageDialog(frame, "Competences managment not implementad yet");
                    }
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
    
    private void roleJComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roleJComboBoxActionPerformed
        if (this.roleJComboBox.getSelectedItem().equals("Maintainer")){
            this.competencePanel.setEnabled(true);
        } else {
            this.competencePanel.setEnabled(false);
        }
    }//GEN-LAST:event_roleJComboBoxActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addUserJButton;
    private javax.swing.JButton cancelJButton;
    private GUI.CompetencePanel competencePanel;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JPasswordField jPasswordField2;
    private javax.swing.JLabel nameJLabel;
    private javax.swing.JTextField nameJTextField;
    private javax.swing.JLabel password2JLabel;
    private javax.swing.JLabel passwordJLabel;
    private javax.swing.JComboBox<String> roleJComboBox;
    private javax.swing.JLabel roleJLabel;
    private javax.swing.JLabel surnameJLabel;
    private javax.swing.JTextField surnameJTextField;
    private javax.swing.JLabel usernameJLabel;
    private javax.swing.JTextField usernameJTextField;
    // End of variables declaration//GEN-END:variables
}
