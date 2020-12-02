/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.team14.se.maintenanceapp.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;

/**
 * Main GUI for system administrator
 * 
 * @author mario
 */
public class AdminGUI extends javax.swing.JFrame {
    
    private User loggedUser;
    private Connection connection;  // DataBase connection
    
    // database data lists
    private LinkedList<User> usersList;
    private LinkedList<Procedure> proceduresList;
    private LinkedList<Competence> competencesList;
    private LinkedList<Site> sitesList;
    private LinkedList<Material> materialsList;
    private LinkedList<Typology> typesList;
    
    /**
     * Creates new form AdminGUI
     */
    public AdminGUI() {
        this.typesList = null;
        this.materialsList = null;
        this.sitesList = null;
        this.competencesList = null;
        this.proceduresList = null;
        this.usersList = null;
        
        initComponents();
    }
    
    /**
     * Creates new form AdminGUI for specific user and database connection
     * 
     * @param loggedUser the logged user
     * @param connection the database connection
     */
    public AdminGUI(User loggedUser, Connection connection) {
        this.typesList = null;
        this.materialsList = null;
        this.sitesList = null;
        this.competencesList = null;
        this.proceduresList = null;
        this.usersList = null;
        
        this.loggedUser = loggedUser;
        this.connection = connection;
        
        initComponents();
        
       //set event listner for data tables and lists
        usersTableJTable.getSelectionModel().addListSelectionListener((ListSelectionEvent event) -> {
            usersTableJTableActionPerformed();
        });
        
        proceduresTableJTable.getSelectionModel().addListSelectionListener((ListSelectionEvent event) -> {
            proceduresTableJTableActionPerformed();
        });
        
        competencesTableJTable.getSelectionModel().addListSelectionListener((ListSelectionEvent event) -> {
            competencesTableJTableActionPerformed();
        });

        // load data from database
        this.refreshUsersList();
        this.refreshProceduresList();
        this.refreshCompetencesList();
        this.refreshSitesList();
        this.refreshMaterialsList();
        this.refreshTypesList();

        setVisible(true);
    }
    
    /**
     * Update users table from the database
     * 
     */
    private void refreshUsersList(){
        this.usersTableJTable.setEnabled(false);
        
        // disable sude form until a new row is selected
        if (this.editUsersJPanel.isEnabled()){
            this.editUsersJPanel.setEnabled(false);
            
            this.userNameJLabel.setEnabled(false);
            this.userNameJTextField.setEnabled(false);
            
            this.userSurnameJLabel.setEnabled(false);
            this.userSurnameJTextField.setEnabled(false);
            
            this.usernameJLabel.setEnabled(false);
            this.usernameJTextField.setEnabled(false);
                   
            this.userRoleJLabel.setEnabled(false);
            this.userRoleJComboBox.setEnabled(false);
            userCompetencePanel.deactivate();
        }
        
        // clear table
        DefaultTableModel usersTableModel = (DefaultTableModel) usersTableJTable.getModel();
        usersTableModel.setRowCount(0);
        
        // update list from database
        try {
            this.usersList = User.getUsers(connection);
        } catch (SQLException ex) {
            Logger.getLogger(AdminGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // update table whith new data
        usersList.forEach(user -> {
            usersTableModel.addRow(new String[]{
                user.getName(),
                user.getSurname(), 
                user.getUsername(),
                user.getRole()
            });
        });
        this.usersTableJTable.setEnabled(true);
    }
    
    /**
     * Update procedures table from the database
     * 
     */
    private void refreshProceduresList(){
        this.proceduresTableJTable.setEnabled(false);
        
        // disable sude form until a new row is selected
        if (this.editProceduresJPanel.isEnabled()){
            this.editProceduresJPanel.setEnabled(false);
            
            this.procedureNameJLabel.setEnabled(false);
            this.procedureNameJTextField.setEnabled(false);
            
            this.SMPJLabel.setEnabled(false);
            this.SMPJTextField.setEnabled(false);
            procedureCompetencePanel.deactivate();
        }
        
        // clear table
        DefaultTableModel proceduresTableModel  = (DefaultTableModel) proceduresTableJTable.getModel();
        proceduresTableModel.setRowCount(0);
        
        // update list from database
        try {
            this.proceduresList = Procedure.getProcedures(connection);
        } catch (SQLException ex) {
            Logger.getLogger(AdminGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // update table whith new data
        proceduresList.forEach(procedure -> {
            proceduresTableModel.addRow(new String[]{
                procedure.getName(), 
                procedure.getSmpName()
            });
        });
        this.proceduresTableJTable.setEnabled(true);
    }
    
    private void refreshCompetencesList(){
        this.competencesTableJTable.setEnabled(false);
        
        if (this.editCompetencesJPanel.isEnabled()){
            this.editCompetencesJPanel.setEnabled(false);
            
            this.competenceNameJLabel.setEnabled(false);
            this.competenceNameJTextField.setEnabled(false);
            
            this.competenceIDJLabel.setEnabled(false);
            this.competenceIDJTextField.setEnabled(false);
        }
        
        DefaultTableModel competencesTableModel  = (DefaultTableModel) competencesTableJTable.getModel();
        competencesTableModel.setRowCount(0);
        
        try {
            this.competencesList = Competence.getCompetences(connection);
        } catch (SQLException ex) {
            Logger.getLogger(AdminGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        competencesList.forEach(competence -> {
            competencesTableModel.addRow(new String[]{
                String.valueOf(competence.getId()),
                competence.getName()
            });
        });
        this.competencesTableJTable.setEnabled(true);
    }
    
    private void refreshSitesList(){
        this.sitesJList.setEnabled(false);
        
        if (this.editSitesJPanel.isEnabled()){
            this.editSitesJPanel.setEnabled(false);
            
            this.siteNameJLabel.setEnabled(false);
            this.siteNameJTextField.setEnabled(false);
        }
        
        DefaultListModel<String> siteslistModel  = new DefaultListModel<>();
        
        try {
            this.sitesList = Site.getSites(connection);
        } catch (SQLException ex) {
            Logger.getLogger(AdminGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        sitesList.forEach(site -> {
            siteslistModel.addElement(site.getName());
        });
        
        this.sitesJList.setModel(siteslistModel);
        this.sitesJList.setEnabled(true);
    }
    
    private void refreshMaterialsList(){
        this.materialsTableJTable.setEnabled(false);
        
        if (this.editCompetencesJPanel.isEnabled()){
            this.editCompetencesJPanel.setEnabled(false);
            
            this.materialNameJLabel.setEnabled(false);
            this.materialNameJTextField.setEnabled(false);
            
            this.materialDescriptionJScrollPane.setEnabled(false);
            this.materialDescriptionJTextArea.setEnabled(false);
        }
        
        DefaultTableModel materialsTableModel  = (DefaultTableModel) materialsTableJTable.getModel();
        materialsTableModel.setRowCount(0);
        
        try {
            this.materialsList = Material.getMaterials(connection);
        } catch (SQLException ex) {
            Logger.getLogger(AdminGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        materialsList.forEach(material -> {
            materialsTableModel.addRow(new String[]{
                material.getName(),
                material.getDescription()
            });
        });
        this.materialsTableJTable.setEnabled(true);
    }
    
    private void refreshTypesList(){
        this.typesJList.setEnabled(false);
        
        if (this.editTypeJPanel.isEnabled()){
            this.editTypeJPanel.setEnabled(false);
            
            this.typeNameJLabel.setEnabled(false);
            this.typeNameJTextField.setEnabled(false);
        }
        
        DefaultListModel<String> typeslistModel  = new DefaultListModel<>();
        
        try {
            this.typesList = Typology.getTypologies(connection);
        } catch (SQLException ex) {
            Logger.getLogger(AdminGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        typesList.forEach(type -> {
            typeslistModel.addElement(type.getName());
        });
        
        this.typesJList.setModel(typeslistModel);
        this.typesJList.setEnabled(true);
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try{
            Connection conn = new MyConnection("jdbc:postgresql://localhost/maintenanceDB", "postgres", "postgressse2020").getConnection();
            System.out.println("Connession to " + conn + " successfully created");
            
            java.awt.EventQueue.invokeLater(() -> {
                AdminGUI adminGUI = new AdminGUI(null, conn);
            });
        }catch(SQLException | ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        adminMainJTabbedPane = new javax.swing.JTabbedPane();
        usersJPanel = new javax.swing.JPanel();
        usersTopJPanel = new javax.swing.JPanel();
        refreshUsersJButton = new javax.swing.JButton();
        usersDetailsJPanel = new javax.swing.JPanel();
        addUserJButton = new javax.swing.JButton();
        editUsersJPanel = new javax.swing.JPanel();
        userNameJLabel = new javax.swing.JLabel();
        userSurnameJLabel = new javax.swing.JLabel();
        usernameJLabel = new javax.swing.JLabel();
        userRoleJLabel = new javax.swing.JLabel();
        changePasswordJButton = new javax.swing.JButton();
        userNameJTextField = new javax.swing.JTextField();
        userSurnameJTextField = new javax.swing.JTextField();
        usernameJTextField = new javax.swing.JTextField();
        userRoleJComboBox = new javax.swing.JComboBox<>();
        updateUserJButton = new javax.swing.JButton();
        userCompetencePanel = new GUI.CompetencePanel(connection);
        removeUserJButton = new javax.swing.JButton();
        usersTableJScrollPane = new javax.swing.JScrollPane();
        usersTableJTable = new javax.swing.JTable();
        proceduresJPanel = new javax.swing.JPanel();
        proceduresTopJPanel = new javax.swing.JPanel();
        refreshProceduresJButton = new javax.swing.JButton();
        proceduresDetailsJPanel = new javax.swing.JPanel();
        addProcedureJButton = new javax.swing.JButton();
        editProceduresJPanel = new javax.swing.JPanel();
        procedureNameJLabel = new javax.swing.JLabel();
        procedureNameJTextField = new javax.swing.JTextField();
        SMPJLabel = new javax.swing.JLabel();
        SMPJTextField = new javax.swing.JTextField();
        updateProcedureJButton = new javax.swing.JButton();
        procedureCompetencePanel = new GUI.CompetencePanel(connection);
        removeProcedureJButton = new javax.swing.JButton();
        proceduresTableJScrollPane = new javax.swing.JScrollPane();
        proceduresTableJTable = new javax.swing.JTable();
        competencesJPanel = new javax.swing.JPanel();
        competencesTopJPanel = new javax.swing.JPanel();
        refreshCompetencesJButton = new javax.swing.JButton();
        competencesDetailsJPanel = new javax.swing.JPanel();
        addCompetenceJButton = new javax.swing.JButton();
        editCompetencesJPanel = new javax.swing.JPanel();
        competenceIDJLabel = new javax.swing.JLabel();
        competenceIDJTextField = new javax.swing.JTextField();
        competenceNameJLabel = new javax.swing.JLabel();
        competenceNameJTextField = new javax.swing.JTextField();
        updateCompetenceJButton = new javax.swing.JButton();
        removeCompetenceJButton = new javax.swing.JButton();
        competencesTableJScrollPane = new javax.swing.JScrollPane();
        competencesTableJTable = new javax.swing.JTable();
        sitesJPanel = new javax.swing.JPanel();
        sitesTopJPanel = new javax.swing.JPanel();
        refreshSitesJButton = new javax.swing.JButton();
        sitesDetailsJPanel = new javax.swing.JPanel();
        addSiteJButton = new javax.swing.JButton();
        editSitesJPanel = new javax.swing.JPanel();
        siteNameJLabel = new javax.swing.JLabel();
        siteNameJTextField = new javax.swing.JTextField();
        updateSiteJButton = new javax.swing.JButton();
        removeSiteJButton = new javax.swing.JButton();
        sitesListJScrollPane = new javax.swing.JScrollPane();
        sitesJList = new javax.swing.JList<>();
        MaterialJPanel = new javax.swing.JPanel();
        MaterialTopJPanel = new javax.swing.JPanel();
        refreshMaterialsJButton = new javax.swing.JButton();
        materialsDetailsJPanel = new javax.swing.JPanel();
        addMaterialJButton = new javax.swing.JButton();
        editMaterialJPanel = new javax.swing.JPanel();
        materialNameJLabel = new javax.swing.JLabel();
        materialNameJTextField = new javax.swing.JTextField();
        updateMaterialJButton = new javax.swing.JButton();
        materialDescriptionJScrollPane = new javax.swing.JScrollPane();
        materialDescriptionJTextArea = new javax.swing.JTextArea();
        removeMaterialJButton = new javax.swing.JButton();
        materialsTableJScrollPane = new javax.swing.JScrollPane();
        materialsTableJTable = new javax.swing.JTable();
        TypeJPanel = new javax.swing.JPanel();
        typesTopJPanel = new javax.swing.JPanel();
        refreshTypesJButton = new javax.swing.JButton();
        typesDetailsjPanel = new javax.swing.JPanel();
        addTypeJButton = new javax.swing.JButton();
        editTypeJPanel = new javax.swing.JPanel();
        typeNameJLabel = new javax.swing.JLabel();
        typeNameJTextField = new javax.swing.JTextField();
        updateTypeJButton = new javax.swing.JButton();
        removeTypeJButton = new javax.swing.JButton();
        typesListJScrollPane2 = new javax.swing.JScrollPane();
        typesJList = new javax.swing.JList<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Admin");
        setMinimumSize(new java.awt.Dimension(806, 589));

        usersJPanel.setLayout(new java.awt.BorderLayout());

        refreshUsersJButton.setText("Refresh List");
        refreshUsersJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshUsersListJButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout usersTopJPanelLayout = new javax.swing.GroupLayout(usersTopJPanel);
        usersTopJPanel.setLayout(usersTopJPanelLayout);
        usersTopJPanelLayout.setHorizontalGroup(
            usersTopJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(usersTopJPanelLayout.createSequentialGroup()
                .addComponent(refreshUsersJButton)
                .addContainerGap(744, Short.MAX_VALUE))
        );
        usersTopJPanelLayout.setVerticalGroup(
            usersTopJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(usersTopJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(refreshUsersJButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        usersJPanel.add(usersTopJPanel, java.awt.BorderLayout.PAGE_START);

        addUserJButton.setText("Add New User");

        editUsersJPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Edit Selected User"));
        editUsersJPanel.setEnabled(false);

        userNameJLabel.setText("Name");
        userNameJLabel.setEnabled(false);

        userSurnameJLabel.setText("Surname");
        userSurnameJLabel.setEnabled(false);

        usernameJLabel.setText("Username");
        usernameJLabel.setEnabled(false);

        userRoleJLabel.setText("Role");
        userRoleJLabel.setEnabled(false);

        changePasswordJButton.setText("Change Password");
        changePasswordJButton.setEnabled(false);

        userNameJTextField.setEnabled(false);
        userNameJTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userNameJTextFieldActionPerformed(evt);
            }
        });

        userSurnameJTextField.setEnabled(false);

        usernameJTextField.setEnabled(false);

        userRoleJComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Maintainer", "Planner", "System Administrator" }));
        userRoleJComboBox.setEnabled(false);
        userRoleJComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userRoleJComboBoxActionPerformed(evt);
            }
        });

        updateUserJButton.setText("Update User");
        updateUserJButton.setEnabled(false);

        userCompetencePanel.setEnabled(false);

        javax.swing.GroupLayout editUsersJPanelLayout = new javax.swing.GroupLayout(editUsersJPanel);
        editUsersJPanel.setLayout(editUsersJPanelLayout);
        editUsersJPanelLayout.setHorizontalGroup(
            editUsersJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editUsersJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(editUsersJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(editUsersJPanelLayout.createSequentialGroup()
                        .addGroup(editUsersJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(userSurnameJLabel)
                            .addComponent(userNameJLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(editUsersJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(userSurnameJTextField)
                            .addComponent(userNameJTextField)))
                    .addGroup(editUsersJPanelLayout.createSequentialGroup()
                        .addGroup(editUsersJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(usernameJLabel)
                            .addComponent(userRoleJLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(editUsersJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(editUsersJPanelLayout.createSequentialGroup()
                                .addComponent(userRoleJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(usernameJTextField)))
                    .addGroup(editUsersJPanelLayout.createSequentialGroup()
                        .addComponent(changePasswordJButton)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, editUsersJPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(updateUserJButton)))
                .addContainerGap())
            .addGroup(editUsersJPanelLayout.createSequentialGroup()
                .addComponent(userCompetencePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        editUsersJPanelLayout.setVerticalGroup(
            editUsersJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, editUsersJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(editUsersJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(userNameJLabel)
                    .addComponent(userNameJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(editUsersJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(userSurnameJLabel)
                    .addComponent(userSurnameJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(editUsersJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(usernameJLabel)
                    .addComponent(usernameJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(editUsersJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(userRoleJLabel)
                    .addComponent(userRoleJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(changePasswordJButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(userCompetencePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addComponent(updateUserJButton)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        removeUserJButton.setText("Remove Selected User");
        removeUserJButton.setEnabled(false);

        javax.swing.GroupLayout usersDetailsJPanelLayout = new javax.swing.GroupLayout(usersDetailsJPanel);
        usersDetailsJPanel.setLayout(usersDetailsJPanelLayout);
        usersDetailsJPanelLayout.setHorizontalGroup(
            usersDetailsJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(usersDetailsJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(usersDetailsJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(editUsersJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addUserJButton)
                    .addComponent(removeUserJButton))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        usersDetailsJPanelLayout.setVerticalGroup(
            usersDetailsJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(usersDetailsJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(addUserJButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(editUsersJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(removeUserJButton)
                .addContainerGap(41, Short.MAX_VALUE))
        );

        usersJPanel.add(usersDetailsJPanel, java.awt.BorderLayout.LINE_END);

        usersTableJScrollPane.setBorder(javax.swing.BorderFactory.createTitledBorder("Users"));

        usersTableJTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Surname", "Username", "Role"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        usersTableJTable.getTableHeader().setReorderingAllowed(false);
        usersTableJScrollPane.setViewportView(usersTableJTable);
        if (usersTableJTable.getColumnModel().getColumnCount() > 0) {
            usersTableJTable.getColumnModel().getColumn(2).setHeaderValue("Username");
            usersTableJTable.getColumnModel().getColumn(3).setHeaderValue("Role");
        }

        usersJPanel.add(usersTableJScrollPane, java.awt.BorderLayout.CENTER);

        adminMainJTabbedPane.addTab("Users", usersJPanel);

        proceduresJPanel.setLayout(new java.awt.BorderLayout());

        refreshProceduresJButton.setText("Refresh List");
        refreshProceduresJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshProceduresJButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout proceduresTopJPanelLayout = new javax.swing.GroupLayout(proceduresTopJPanel);
        proceduresTopJPanel.setLayout(proceduresTopJPanelLayout);
        proceduresTopJPanelLayout.setHorizontalGroup(
            proceduresTopJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(proceduresTopJPanelLayout.createSequentialGroup()
                .addComponent(refreshProceduresJButton)
                .addContainerGap(744, Short.MAX_VALUE))
        );
        proceduresTopJPanelLayout.setVerticalGroup(
            proceduresTopJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(proceduresTopJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(refreshProceduresJButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        proceduresJPanel.add(proceduresTopJPanel, java.awt.BorderLayout.PAGE_START);

        addProcedureJButton.setText("Add New Procedure");

        editProceduresJPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Edit Selected Procedure"));
        editProceduresJPanel.setEnabled(false);

        procedureNameJLabel.setText("Name");
        procedureNameJLabel.setEnabled(false);

        procedureNameJTextField.setEnabled(false);
        procedureNameJTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                procedureNameJTextFieldActionPerformed(evt);
            }
        });

        SMPJLabel.setText("SMP");
        SMPJLabel.setEnabled(false);

        SMPJTextField.setEnabled(false);

        updateProcedureJButton.setText("Update Procedure");
        updateProcedureJButton.setEnabled(false);

        javax.swing.GroupLayout editProceduresJPanelLayout = new javax.swing.GroupLayout(editProceduresJPanel);
        editProceduresJPanel.setLayout(editProceduresJPanelLayout);
        editProceduresJPanelLayout.setHorizontalGroup(
            editProceduresJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editProceduresJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(editProceduresJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(editProceduresJPanelLayout.createSequentialGroup()
                        .addGroup(editProceduresJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(procedureNameJLabel)
                            .addComponent(SMPJLabel))
                        .addGap(26, 26, 26)
                        .addGroup(editProceduresJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(procedureNameJTextField)
                            .addComponent(SMPJTextField)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, editProceduresJPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(updateProcedureJButton)))
                .addContainerGap())
            .addGroup(editProceduresJPanelLayout.createSequentialGroup()
                .addComponent(procedureCompetencePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        editProceduresJPanelLayout.setVerticalGroup(
            editProceduresJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, editProceduresJPanelLayout.createSequentialGroup()
                .addGroup(editProceduresJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(procedureNameJLabel)
                    .addComponent(procedureNameJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(editProceduresJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SMPJLabel)
                    .addComponent(SMPJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(procedureCompetencePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(updateProcedureJButton))
        );

        removeProcedureJButton.setText("Remove Selected Procedure");
        removeProcedureJButton.setEnabled(false);

        javax.swing.GroupLayout proceduresDetailsJPanelLayout = new javax.swing.GroupLayout(proceduresDetailsJPanel);
        proceduresDetailsJPanel.setLayout(proceduresDetailsJPanelLayout);
        proceduresDetailsJPanelLayout.setHorizontalGroup(
            proceduresDetailsJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(proceduresDetailsJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(proceduresDetailsJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(editProceduresJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addProcedureJButton)
                    .addComponent(removeProcedureJButton))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        proceduresDetailsJPanelLayout.setVerticalGroup(
            proceduresDetailsJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(proceduresDetailsJPanelLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(addProcedureJButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(editProceduresJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(removeProcedureJButton)
                .addContainerGap(174, Short.MAX_VALUE))
        );

        proceduresJPanel.add(proceduresDetailsJPanel, java.awt.BorderLayout.LINE_END);

        proceduresTableJScrollPane.setBorder(javax.swing.BorderFactory.createTitledBorder("Procedures"));

        proceduresTableJTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "SMP"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        proceduresTableJTable.getTableHeader().setReorderingAllowed(false);
        proceduresTableJScrollPane.setViewportView(proceduresTableJTable);

        proceduresJPanel.add(proceduresTableJScrollPane, java.awt.BorderLayout.CENTER);

        adminMainJTabbedPane.addTab("Procedures", proceduresJPanel);

        competencesJPanel.setLayout(new java.awt.BorderLayout());

        refreshCompetencesJButton.setText("Refresh List");

        javax.swing.GroupLayout competencesTopJPanelLayout = new javax.swing.GroupLayout(competencesTopJPanel);
        competencesTopJPanel.setLayout(competencesTopJPanelLayout);
        competencesTopJPanelLayout.setHorizontalGroup(
            competencesTopJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(competencesTopJPanelLayout.createSequentialGroup()
                .addComponent(refreshCompetencesJButton)
                .addContainerGap(744, Short.MAX_VALUE))
        );
        competencesTopJPanelLayout.setVerticalGroup(
            competencesTopJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(competencesTopJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(refreshCompetencesJButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        competencesJPanel.add(competencesTopJPanel, java.awt.BorderLayout.PAGE_START);

        addCompetenceJButton.setText("Add New Competence");

        editCompetencesJPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Edit Selected Competence"));
        editCompetencesJPanel.setEnabled(false);

        competenceIDJLabel.setText("ID");
        competenceIDJLabel.setEnabled(false);

        competenceIDJTextField.setEnabled(false);
        competenceIDJTextField.setFocusable(false);

        competenceNameJLabel.setText("Name");
        competenceNameJLabel.setEnabled(false);

        competenceNameJTextField.setEnabled(false);
        competenceNameJTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                competenceNameJTextFieldActionPerformed(evt);
            }
        });

        updateCompetenceJButton.setText("Update Competence");
        updateCompetenceJButton.setEnabled(false);

        javax.swing.GroupLayout editCompetencesJPanelLayout = new javax.swing.GroupLayout(editCompetencesJPanel);
        editCompetencesJPanel.setLayout(editCompetencesJPanelLayout);
        editCompetencesJPanelLayout.setHorizontalGroup(
            editCompetencesJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editCompetencesJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(editCompetencesJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, editCompetencesJPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(updateCompetenceJButton))
                    .addGroup(editCompetencesJPanelLayout.createSequentialGroup()
                        .addGroup(editCompetencesJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(competenceNameJLabel)
                            .addComponent(competenceIDJLabel))
                        .addGap(32, 32, 32)
                        .addGroup(editCompetencesJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(competenceNameJTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
                            .addComponent(competenceIDJTextField))))
                .addContainerGap())
        );
        editCompetencesJPanelLayout.setVerticalGroup(
            editCompetencesJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, editCompetencesJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(editCompetencesJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(competenceIDJLabel)
                    .addComponent(competenceIDJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(editCompetencesJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(competenceNameJLabel)
                    .addComponent(competenceNameJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addComponent(updateCompetenceJButton)
                .addContainerGap())
        );

        removeCompetenceJButton.setText("Remove Selected Competence");
        removeCompetenceJButton.setEnabled(false);

        javax.swing.GroupLayout competencesDetailsJPanelLayout = new javax.swing.GroupLayout(competencesDetailsJPanel);
        competencesDetailsJPanel.setLayout(competencesDetailsJPanelLayout);
        competencesDetailsJPanelLayout.setHorizontalGroup(
            competencesDetailsJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(competencesDetailsJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(competencesDetailsJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(editCompetencesJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addCompetenceJButton)
                    .addComponent(removeCompetenceJButton))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        competencesDetailsJPanelLayout.setVerticalGroup(
            competencesDetailsJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(competencesDetailsJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(addCompetenceJButton)
                .addGap(14, 14, 14)
                .addComponent(editCompetencesJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(removeCompetenceJButton)
                .addContainerGap(398, Short.MAX_VALUE))
        );

        competencesJPanel.add(competencesDetailsJPanel, java.awt.BorderLayout.LINE_END);

        competencesTableJScrollPane.setBorder(javax.swing.BorderFactory.createTitledBorder("Competences"));

        competencesTableJTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        competencesTableJTable.getTableHeader().setReorderingAllowed(false);
        competencesTableJScrollPane.setViewportView(competencesTableJTable);

        competencesJPanel.add(competencesTableJScrollPane, java.awt.BorderLayout.CENTER);

        adminMainJTabbedPane.addTab("Competences", competencesJPanel);

        sitesJPanel.setLayout(new java.awt.BorderLayout());

        refreshSitesJButton.setText("Refresh List");
        refreshSitesJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshSitesJButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout sitesTopJPanelLayout = new javax.swing.GroupLayout(sitesTopJPanel);
        sitesTopJPanel.setLayout(sitesTopJPanelLayout);
        sitesTopJPanelLayout.setHorizontalGroup(
            sitesTopJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sitesTopJPanelLayout.createSequentialGroup()
                .addComponent(refreshSitesJButton)
                .addContainerGap(744, Short.MAX_VALUE))
        );
        sitesTopJPanelLayout.setVerticalGroup(
            sitesTopJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sitesTopJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(refreshSitesJButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        sitesJPanel.add(sitesTopJPanel, java.awt.BorderLayout.PAGE_START);

        addSiteJButton.setText("Add New Site");

        editSitesJPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Edit Selected Site"));
        editSitesJPanel.setEnabled(false);

        siteNameJLabel.setText("Name");
        siteNameJLabel.setEnabled(false);

        siteNameJTextField.setEnabled(false);
        siteNameJTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                siteNameJTextFieldActionPerformed(evt);
            }
        });

        updateSiteJButton.setText("Update Site");
        updateSiteJButton.setEnabled(false);

        javax.swing.GroupLayout editSitesJPanelLayout = new javax.swing.GroupLayout(editSitesJPanel);
        editSitesJPanel.setLayout(editSitesJPanelLayout);
        editSitesJPanelLayout.setHorizontalGroup(
            editSitesJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editSitesJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(editSitesJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(editSitesJPanelLayout.createSequentialGroup()
                        .addComponent(siteNameJLabel)
                        .addGap(32, 32, 32)
                        .addComponent(siteNameJTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, editSitesJPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(updateSiteJButton)))
                .addContainerGap())
        );
        editSitesJPanelLayout.setVerticalGroup(
            editSitesJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editSitesJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(editSitesJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(siteNameJLabel)
                    .addComponent(siteNameJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(updateSiteJButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        removeSiteJButton.setText("Remove Selected Site");
        removeSiteJButton.setEnabled(false);

        javax.swing.GroupLayout sitesDetailsJPanelLayout = new javax.swing.GroupLayout(sitesDetailsJPanel);
        sitesDetailsJPanel.setLayout(sitesDetailsJPanelLayout);
        sitesDetailsJPanelLayout.setHorizontalGroup(
            sitesDetailsJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sitesDetailsJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(sitesDetailsJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(editSitesJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addSiteJButton)
                    .addComponent(removeSiteJButton))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        sitesDetailsJPanelLayout.setVerticalGroup(
            sitesDetailsJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sitesDetailsJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(addSiteJButton)
                .addGap(14, 14, 14)
                .addComponent(editSitesJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(removeSiteJButton)
                .addContainerGap(450, Short.MAX_VALUE))
        );

        sitesJPanel.add(sitesDetailsJPanel, java.awt.BorderLayout.LINE_END);

        sitesListJScrollPane.setBorder(javax.swing.BorderFactory.createTitledBorder("Sites"));

        sitesListJScrollPane.setViewportView(sitesJList);

        sitesJPanel.add(sitesListJScrollPane, java.awt.BorderLayout.CENTER);

        adminMainJTabbedPane.addTab("Sites", sitesJPanel);

        MaterialJPanel.setLayout(new java.awt.BorderLayout());

        refreshMaterialsJButton.setText("Refresh List");
        refreshMaterialsJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshMaterialsJButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout MaterialTopJPanelLayout = new javax.swing.GroupLayout(MaterialTopJPanel);
        MaterialTopJPanel.setLayout(MaterialTopJPanelLayout);
        MaterialTopJPanelLayout.setHorizontalGroup(
            MaterialTopJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MaterialTopJPanelLayout.createSequentialGroup()
                .addComponent(refreshMaterialsJButton)
                .addContainerGap(744, Short.MAX_VALUE))
        );
        MaterialTopJPanelLayout.setVerticalGroup(
            MaterialTopJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MaterialTopJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(refreshMaterialsJButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        MaterialJPanel.add(MaterialTopJPanel, java.awt.BorderLayout.PAGE_START);

        addMaterialJButton.setText("Add New Material");

        editMaterialJPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Edit Selected Material"));
        editMaterialJPanel.setEnabled(false);

        materialNameJLabel.setText("Name");
        materialNameJLabel.setEnabled(false);

        materialNameJTextField.setEnabled(false);
        materialNameJTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                materialNameJTextFieldActionPerformed(evt);
            }
        });

        updateMaterialJButton.setText("Update Material");
        updateMaterialJButton.setEnabled(false);

        materialDescriptionJScrollPane.setBorder(javax.swing.BorderFactory.createTitledBorder("Desription"));
        materialDescriptionJScrollPane.setEnabled(false);

        materialDescriptionJTextArea.setColumns(20);
        materialDescriptionJTextArea.setRows(5);
        materialDescriptionJTextArea.setEnabled(false);
        materialDescriptionJScrollPane.setViewportView(materialDescriptionJTextArea);

        javax.swing.GroupLayout editMaterialJPanelLayout = new javax.swing.GroupLayout(editMaterialJPanel);
        editMaterialJPanel.setLayout(editMaterialJPanelLayout);
        editMaterialJPanelLayout.setHorizontalGroup(
            editMaterialJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, editMaterialJPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(updateMaterialJButton))
            .addGroup(editMaterialJPanelLayout.createSequentialGroup()
                .addGroup(editMaterialJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(editMaterialJPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(materialNameJLabel)
                        .addGap(32, 32, 32)
                        .addComponent(materialNameJTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE))
                    .addComponent(materialDescriptionJScrollPane))
                .addContainerGap())
        );
        editMaterialJPanelLayout.setVerticalGroup(
            editMaterialJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editMaterialJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(editMaterialJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(materialNameJLabel)
                    .addComponent(materialNameJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(materialDescriptionJScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(updateMaterialJButton)
                .addContainerGap())
        );

        removeMaterialJButton.setText("Remove Selected Material");
        removeMaterialJButton.setEnabled(false);

        javax.swing.GroupLayout materialsDetailsJPanelLayout = new javax.swing.GroupLayout(materialsDetailsJPanel);
        materialsDetailsJPanel.setLayout(materialsDetailsJPanelLayout);
        materialsDetailsJPanelLayout.setHorizontalGroup(
            materialsDetailsJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(materialsDetailsJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(materialsDetailsJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(editMaterialJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addMaterialJButton)
                    .addComponent(removeMaterialJButton))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        materialsDetailsJPanelLayout.setVerticalGroup(
            materialsDetailsJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(materialsDetailsJPanelLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(addMaterialJButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(editMaterialJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(removeMaterialJButton)
                .addContainerGap(292, Short.MAX_VALUE))
        );

        MaterialJPanel.add(materialsDetailsJPanel, java.awt.BorderLayout.LINE_END);

        materialsTableJScrollPane.setBorder(javax.swing.BorderFactory.createTitledBorder("Materials"));

        materialsTableJTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Description"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        materialsTableJTable.getTableHeader().setReorderingAllowed(false);
        materialsTableJScrollPane.setViewportView(materialsTableJTable);

        MaterialJPanel.add(materialsTableJScrollPane, java.awt.BorderLayout.CENTER);

        adminMainJTabbedPane.addTab("Materials", MaterialJPanel);

        TypeJPanel.setLayout(new java.awt.BorderLayout());

        refreshTypesJButton.setText("Refresh List");
        refreshTypesJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshTypesJButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout typesTopJPanelLayout = new javax.swing.GroupLayout(typesTopJPanel);
        typesTopJPanel.setLayout(typesTopJPanelLayout);
        typesTopJPanelLayout.setHorizontalGroup(
            typesTopJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(typesTopJPanelLayout.createSequentialGroup()
                .addComponent(refreshTypesJButton)
                .addContainerGap(744, Short.MAX_VALUE))
        );
        typesTopJPanelLayout.setVerticalGroup(
            typesTopJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(typesTopJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(refreshTypesJButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        TypeJPanel.add(typesTopJPanel, java.awt.BorderLayout.PAGE_START);

        addTypeJButton.setText("Add New Type");

        editTypeJPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Edit Selected Type"));
        editTypeJPanel.setEnabled(false);

        typeNameJLabel.setText("Name");
        typeNameJLabel.setEnabled(false);

        typeNameJTextField.setEnabled(false);
        typeNameJTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                typeNameJTextFieldActionPerformed(evt);
            }
        });

        updateTypeJButton.setText("Update Type");
        updateTypeJButton.setEnabled(false);

        javax.swing.GroupLayout editTypeJPanelLayout = new javax.swing.GroupLayout(editTypeJPanel);
        editTypeJPanel.setLayout(editTypeJPanelLayout);
        editTypeJPanelLayout.setHorizontalGroup(
            editTypeJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editTypeJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(editTypeJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(editTypeJPanelLayout.createSequentialGroup()
                        .addComponent(typeNameJLabel)
                        .addGap(32, 32, 32)
                        .addComponent(typeNameJTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, editTypeJPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(updateTypeJButton)))
                .addContainerGap())
        );
        editTypeJPanelLayout.setVerticalGroup(
            editTypeJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editTypeJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(editTypeJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(typeNameJLabel)
                    .addComponent(typeNameJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(updateTypeJButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        removeTypeJButton.setText("Remove Selected Type");
        removeTypeJButton.setEnabled(false);

        javax.swing.GroupLayout typesDetailsjPanelLayout = new javax.swing.GroupLayout(typesDetailsjPanel);
        typesDetailsjPanel.setLayout(typesDetailsjPanelLayout);
        typesDetailsjPanelLayout.setHorizontalGroup(
            typesDetailsjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(typesDetailsjPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(typesDetailsjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(editTypeJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addTypeJButton)
                    .addComponent(removeTypeJButton))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        typesDetailsjPanelLayout.setVerticalGroup(
            typesDetailsjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(typesDetailsjPanelLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(addTypeJButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(editTypeJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(removeTypeJButton)
                .addContainerGap(450, Short.MAX_VALUE))
        );

        TypeJPanel.add(typesDetailsjPanel, java.awt.BorderLayout.LINE_END);

        typesListJScrollPane2.setBorder(javax.swing.BorderFactory.createTitledBorder("Maintenance Types"));

        typesListJScrollPane2.setViewportView(typesJList);

        TypeJPanel.add(typesListJScrollPane2, java.awt.BorderLayout.CENTER);

        adminMainJTabbedPane.addTab("Maintenance Types", TypeJPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(adminMainJTabbedPane)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(adminMainJTabbedPane)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    
    private void typeNameJTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_typeNameJTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_typeNameJTextFieldActionPerformed

    private void materialNameJTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_materialNameJTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_materialNameJTextFieldActionPerformed

    private void siteNameJTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_siteNameJTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_siteNameJTextFieldActionPerformed

    private void competenceNameJTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_competenceNameJTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_competenceNameJTextFieldActionPerformed

    private void procedureNameJTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_procedureNameJTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_procedureNameJTextFieldActionPerformed

    private void userNameJTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userNameJTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_userNameJTextFieldActionPerformed

    private void refreshUsersListJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshUsersListJButtonActionPerformed
        this.refreshUsersList();
    }//GEN-LAST:event_refreshUsersListJButtonActionPerformed

    private void refreshProceduresJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshProceduresJButtonActionPerformed
        this.refreshProceduresList();
    }//GEN-LAST:event_refreshProceduresJButtonActionPerformed

    /**
     * Enable the competences panel if maintainer is selected in 
     * the user role combo box
     * 
     * @param evt 
     */
    private void userRoleJComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userRoleJComboBoxActionPerformed
        if (this.userRoleJComboBox.getSelectedItem().equals("Maintainer")){
            try {
                this.userCompetencePanel.activate(new LinkedList<>());
            } catch (SQLException ex) {
                Logger.getLogger(AdminGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            this.userCompetencePanel.deactivate();
        }
    }//GEN-LAST:event_userRoleJComboBoxActionPerformed

    private void refreshSitesJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshSitesJButtonActionPerformed
        this.refreshSitesList();
    }//GEN-LAST:event_refreshSitesJButtonActionPerformed

    private void refreshTypesJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshTypesJButtonActionPerformed
        this.refreshTypesList();
    }//GEN-LAST:event_refreshTypesJButtonActionPerformed

    private void refreshMaterialsJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshMaterialsJButtonActionPerformed
        this.refreshMaterialsList();
    }//GEN-LAST:event_refreshMaterialsJButtonActionPerformed

    
    /**
     * Show the details of the selected user in the side panel
     * 
     */
    private void usersTableJTableActionPerformed() {
        int selectedUserIndex = usersTableJTable.getSelectedRow();
        
        // return if no row selected
        if (selectedUserIndex == -1) return;
        
        // enable side panel if disabled
        if (!this.editUsersJPanel.isEnabled()){
            this.editUsersJPanel.setEnabled(true);
            
            this.userNameJLabel.setEnabled(true);
            this.userNameJTextField.setEnabled(true);
            
            this.userSurnameJLabel.setEnabled(true);
            this.userSurnameJTextField.setEnabled(true);
            
            this.usernameJLabel.setEnabled(true);
            this.usernameJTextField.setEnabled(true);
                   
            this.userRoleJLabel.setEnabled(true);
            this.userRoleJComboBox.setEnabled(true);
        }
        
        // fill form whit the data of the selected user
        this.userNameJTextField.setText(usersList.get(selectedUserIndex).getName());

        this.userSurnameJTextField.setText(usersList.get(selectedUserIndex).getSurname());
        
        this.usernameJTextField.setText(usersList.get(selectedUserIndex).getUsername());

        this.userRoleJComboBox.setSelectedItem(usersList.get(selectedUserIndex).getRole());
        
        // if the user is a maintainer enable the enable the competences panel
        if (usersList.get(selectedUserIndex).getRole().equals("Maintainer")){
            try {
                //TODO: pass user's competences list instead of null
                userCompetencePanel.activate(new LinkedList<>());
            } catch (SQLException ex) {
                Logger.getLogger(AdminGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            userCompetencePanel.deactivate();
        }
    }
    
    
    /**
     * Show the details of the selected user in the side panel
     * 
     */
    private void proceduresTableJTableActionPerformed() {
        int selectedProcedureIndex = proceduresTableJTable.getSelectedRow();
        
        // return if no row selected
        if (selectedProcedureIndex == -1) return;
        
        // enable side panel if disabled
        if (!this.editProceduresJPanel.isEnabled()){
            this.editProceduresJPanel.setEnabled(true);
            
            this.procedureNameJLabel.setEnabled(true);
            this.procedureNameJTextField.setEnabled(true);
            
            this.SMPJLabel.setEnabled(true);
            this.SMPJTextField.setEnabled(true);
        }

        // fill form whit the data of the selected procedure
        this.procedureNameJTextField.setText(proceduresList.get(selectedProcedureIndex).getName());

        this.SMPJTextField.setText(proceduresList.get(selectedProcedureIndex).getSmpName());
        
        // fill the competences panel
        try {
            //LinkedList<Competence> procedureCompetences = Competence.getCompetences(connection);
            //procedureCompetences.remove(proceduresList.get(selectedUserIndex).getCompetence());
            LinkedList<Competence> procedureCompetences = new LinkedList<>();
            procedureCompetences.add(proceduresList.get(selectedProcedureIndex).getCompetence());
            procedureCompetencePanel.activate(procedureCompetences);
        } catch (SQLException ex) {
            Logger.getLogger(AdminGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Show the details of the selected user in the side panel
     * 
     */
    private void competencesTableJTableActionPerformed() {
        int selectedCompetenceIndex = competencesTableJTable.getSelectedRow();
        
        // return if no row selected
        if (selectedCompetenceIndex == -1) return;
        
        // enable side panel if disabled
        if (!this.editCompetencesJPanel.isEnabled()){
            this.editCompetencesJPanel.setEnabled(true);
            
            this.competenceIDJLabel.setEnabled(true);
            
            this.competenceNameJLabel.setEnabled(true);
            this.competenceNameJTextField.setEnabled(true);
        }

        // fill form whit the data of the selected competence
        this.competenceNameJTextField.setText(competencesList.get(selectedCompetenceIndex).getName());

        this.competenceIDJTextField.setText(String.valueOf(competencesList.get(selectedCompetenceIndex).getId()));
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel MaterialJPanel;
    private javax.swing.JPanel MaterialTopJPanel;
    private javax.swing.JLabel SMPJLabel;
    private javax.swing.JTextField SMPJTextField;
    private javax.swing.JPanel TypeJPanel;
    private javax.swing.JButton addCompetenceJButton;
    private javax.swing.JButton addMaterialJButton;
    private javax.swing.JButton addProcedureJButton;
    private javax.swing.JButton addSiteJButton;
    private javax.swing.JButton addTypeJButton;
    private javax.swing.JButton addUserJButton;
    private javax.swing.JTabbedPane adminMainJTabbedPane;
    private javax.swing.JButton changePasswordJButton;
    private javax.swing.JLabel competenceIDJLabel;
    private javax.swing.JTextField competenceIDJTextField;
    private javax.swing.JLabel competenceNameJLabel;
    private javax.swing.JTextField competenceNameJTextField;
    private javax.swing.JPanel competencesDetailsJPanel;
    private javax.swing.JPanel competencesJPanel;
    private javax.swing.JScrollPane competencesTableJScrollPane;
    private javax.swing.JTable competencesTableJTable;
    private javax.swing.JPanel competencesTopJPanel;
    private javax.swing.JPanel editCompetencesJPanel;
    private javax.swing.JPanel editMaterialJPanel;
    private javax.swing.JPanel editProceduresJPanel;
    private javax.swing.JPanel editSitesJPanel;
    private javax.swing.JPanel editTypeJPanel;
    private javax.swing.JPanel editUsersJPanel;
    private javax.swing.JScrollPane materialDescriptionJScrollPane;
    private javax.swing.JTextArea materialDescriptionJTextArea;
    private javax.swing.JLabel materialNameJLabel;
    private javax.swing.JTextField materialNameJTextField;
    private javax.swing.JPanel materialsDetailsJPanel;
    private javax.swing.JScrollPane materialsTableJScrollPane;
    private javax.swing.JTable materialsTableJTable;
    private GUI.CompetencePanel procedureCompetencePanel;
    private javax.swing.JLabel procedureNameJLabel;
    private javax.swing.JTextField procedureNameJTextField;
    private javax.swing.JPanel proceduresDetailsJPanel;
    private javax.swing.JPanel proceduresJPanel;
    private javax.swing.JScrollPane proceduresTableJScrollPane;
    private javax.swing.JTable proceduresTableJTable;
    private javax.swing.JPanel proceduresTopJPanel;
    private javax.swing.JButton refreshCompetencesJButton;
    private javax.swing.JButton refreshMaterialsJButton;
    private javax.swing.JButton refreshProceduresJButton;
    private javax.swing.JButton refreshSitesJButton;
    private javax.swing.JButton refreshTypesJButton;
    private javax.swing.JButton refreshUsersJButton;
    private javax.swing.JButton removeCompetenceJButton;
    private javax.swing.JButton removeMaterialJButton;
    private javax.swing.JButton removeProcedureJButton;
    private javax.swing.JButton removeSiteJButton;
    private javax.swing.JButton removeTypeJButton;
    private javax.swing.JButton removeUserJButton;
    private javax.swing.JLabel siteNameJLabel;
    private javax.swing.JTextField siteNameJTextField;
    private javax.swing.JPanel sitesDetailsJPanel;
    private javax.swing.JList<String> sitesJList;
    private javax.swing.JPanel sitesJPanel;
    private javax.swing.JScrollPane sitesListJScrollPane;
    private javax.swing.JPanel sitesTopJPanel;
    private javax.swing.JLabel typeNameJLabel;
    private javax.swing.JTextField typeNameJTextField;
    private javax.swing.JPanel typesDetailsjPanel;
    private javax.swing.JList<String> typesJList;
    private javax.swing.JScrollPane typesListJScrollPane2;
    private javax.swing.JPanel typesTopJPanel;
    private javax.swing.JButton updateCompetenceJButton;
    private javax.swing.JButton updateMaterialJButton;
    private javax.swing.JButton updateProcedureJButton;
    private javax.swing.JButton updateSiteJButton;
    private javax.swing.JButton updateTypeJButton;
    private javax.swing.JButton updateUserJButton;
    private GUI.CompetencePanel userCompetencePanel;
    private javax.swing.JLabel userNameJLabel;
    private javax.swing.JTextField userNameJTextField;
    private javax.swing.JComboBox<String> userRoleJComboBox;
    private javax.swing.JLabel userRoleJLabel;
    private javax.swing.JLabel userSurnameJLabel;
    private javax.swing.JTextField userSurnameJTextField;
    private javax.swing.JLabel usernameJLabel;
    private javax.swing.JTextField usernameJTextField;
    private javax.swing.JPanel usersDetailsJPanel;
    private javax.swing.JPanel usersJPanel;
    private javax.swing.JScrollPane usersTableJScrollPane;
    private javax.swing.JTable usersTableJTable;
    private javax.swing.JPanel usersTopJPanel;
    // End of variables declaration//GEN-END:variables
}