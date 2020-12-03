/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.team14.se.maintenanceapp.MaintenanceActivity;
import com.team14.se.maintenanceapp.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mario
 */
public class MaintainerGUI extends javax.swing.JFrame {
    
    private User loggedUser;
    private Connection connection;

    /**
     * Creates new form PlannerGUI
     */
    public MaintainerGUI() {
        initComponents();
    }
    
    /**
     * Creates new form PlannerGUI
     * 
     * @param loggedUser the logged user
     * @param connection the database connection
     */
    public MaintainerGUI(User loggedUser, Connection connection) {
        this.loggedUser = loggedUser;
        this.connection = connection;
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainJPanel = new javax.swing.JPanel();
        topJPanel = new javax.swing.JPanel();
        refreshJButton = new javax.swing.JButton();
        weekNumberJLabel = new javax.swing.JLabel();
        weekJSpinner = new javax.swing.JSpinner();
        detailsJPanel = new javax.swing.JPanel();
        detailsWeekJLabel = new javax.swing.JLabel();
        detailsActivityJLabel = new javax.swing.JLabel();
        detailsSMPJLabel = new javax.swing.JLabel();
        detailsWeekJTextField = new javax.swing.JTextField();
        detailsActivityJTextField = new javax.swing.JTextField();
        detailsSMPJTextField = new javax.swing.JTextField();
        detailsDescriptionJScrollPane = new javax.swing.JScrollPane();
        detailsDescriptionJTextArea = new javax.swing.JTextArea();
        detailsSkillsJScrollPane = new javax.swing.JScrollPane();
        detailsSkillsJList = new javax.swing.JList<>();
        detailsNotesJScrollPane = new javax.swing.JScrollPane();
        detailsNotesJTextArea = new javax.swing.JTextArea();
        activitiesJScrollPane = new javax.swing.JScrollPane();
        activitiesJTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        mainJPanel.setLayout(new java.awt.BorderLayout());

        refreshJButton.setText("Refresh List");
        refreshJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshJButtonActionPerformed(evt);
            }
        });

        weekNumberJLabel.setText("Week N.");

        weekJSpinner.setModel(new javax.swing.SpinnerNumberModel(0, 0, 53, 1));

        javax.swing.GroupLayout topJPanelLayout = new javax.swing.GroupLayout(topJPanel);
        topJPanel.setLayout(topJPanelLayout);
        topJPanelLayout.setHorizontalGroup(
            topJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(weekNumberJLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(weekJSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(refreshJButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        topJPanelLayout.setVerticalGroup(
            topJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(topJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(refreshJButton)
                    .addComponent(weekNumberJLabel)
                    .addComponent(weekJSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        mainJPanel.add(topJPanel, java.awt.BorderLayout.PAGE_START);

        detailsJPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Selected Activity Details"));

        detailsWeekJLabel.setText("Week N.");

        detailsActivityJLabel.setText("Activity");

        detailsSMPJLabel.setText("SMP");

        detailsWeekJTextField.setText("--");
        detailsWeekJTextField.setEnabled(false);
        detailsWeekJTextField.setFocusable(false);

        detailsActivityJTextField.setEnabled(false);
        detailsActivityJTextField.setFocusable(false);

        detailsSMPJTextField.setEnabled(false);
        detailsSMPJTextField.setFocusable(false);

        detailsDescriptionJScrollPane.setBorder(javax.swing.BorderFactory.createTitledBorder("Description"));

        detailsDescriptionJTextArea.setColumns(20);
        detailsDescriptionJTextArea.setRows(5);
        detailsDescriptionJTextArea.setEnabled(false);
        detailsDescriptionJTextArea.setFocusable(false);
        detailsDescriptionJScrollPane.setViewportView(detailsDescriptionJTextArea);

        detailsSkillsJScrollPane.setBorder(javax.swing.BorderFactory.createTitledBorder("Skills Nedded"));

        detailsSkillsJList.setEnabled(false);
        detailsSkillsJScrollPane.setViewportView(detailsSkillsJList);

        detailsNotesJScrollPane.setBorder(javax.swing.BorderFactory.createTitledBorder("Workspace Notes"));

        detailsNotesJTextArea.setColumns(20);
        detailsNotesJTextArea.setRows(5);
        detailsNotesJTextArea.setEnabled(false);
        detailsNotesJTextArea.setFocusable(false);
        detailsNotesJScrollPane.setViewportView(detailsNotesJTextArea);

        javax.swing.GroupLayout detailsJPanelLayout = new javax.swing.GroupLayout(detailsJPanel);
        detailsJPanel.setLayout(detailsJPanelLayout);
        detailsJPanelLayout.setHorizontalGroup(
            detailsJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(detailsJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(detailsJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(detailsJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(detailsDescriptionJScrollPane)
                        .addGroup(detailsJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(detailsJPanelLayout.createSequentialGroup()
                                .addComponent(detailsWeekJLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(detailsWeekJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(detailsJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, detailsJPanelLayout.createSequentialGroup()
                                    .addComponent(detailsSMPJLabel)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(detailsSMPJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, detailsJPanelLayout.createSequentialGroup()
                                    .addComponent(detailsActivityJLabel)
                                    .addGap(18, 18, 18)
                                    .addComponent(detailsActivityJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addComponent(detailsSkillsJScrollPane))
                    .addComponent(detailsNotesJScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        detailsJPanelLayout.setVerticalGroup(
            detailsJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(detailsJPanelLayout.createSequentialGroup()
                .addGroup(detailsJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(detailsWeekJLabel)
                    .addComponent(detailsWeekJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(detailsJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(detailsActivityJLabel)
                    .addComponent(detailsActivityJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(detailsJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(detailsSMPJLabel)
                    .addComponent(detailsSMPJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(detailsDescriptionJScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(detailsSkillsJScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(detailsNotesJScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        mainJPanel.add(detailsJPanel, java.awt.BorderLayout.LINE_END);

        activitiesJScrollPane.setBorder(javax.swing.BorderFactory.createTitledBorder("Assigned Mantainance Activities"));

        activitiesJTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Area", "Type", "EIT"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
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
        activitiesJTable.getTableHeader().setReorderingAllowed(false);
        activitiesJTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                activitiesJTableMouseClicked(evt);
            }
        });
        activitiesJScrollPane.setViewportView(activitiesJTable);

        mainJPanel.add(activitiesJScrollPane, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 802, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(mainJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 802, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 580, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(mainJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 580, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void refreshJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshJButtonActionPerformed
        int week_n = (int) weekJSpinner.getValue();
        initTable(week_n);
    }//GEN-LAST:event_refreshJButtonActionPerformed

    private void activitiesJTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_activitiesJTableMouseClicked
        JTable source = (JTable)evt.getSource();
        int row = source.rowAtPoint( evt.getPoint() );
        String id=source.getModel().getValueAt(row, 0)+"";
        /* statement for 1 maint activity */
        try {
            Statement statement = connection.createStatement();
            String querySingleActivity = "SELECT * FROM ATTIVITA_MANUTENZIONE, PROCEDURA "
                    + "WHERE ATTIVITA_MANUTENZIONE.procedura = PROCEDURA.nome "
                    + "AND activity_id ="+id+";";
            ResultSet rs = statement.executeQuery(querySingleActivity);
            
            if(rs.next()){
                detailsActivityJTextField.setText(rs.getString("nome"));
                detailsWeekJTextField.setText(String.valueOf(rs.getInt("settimana")));
                detailsSMPJTextField.setText(rs.getString("smp"));
                detailsDescriptionJTextArea.setText(rs.getString("descrizione"));
                DefaultListModel listModel = new DefaultListModel();
                listModel.addElement(rs.getString("competenza"));
                detailsSkillsJList.setModel(listModel);
                detailsNotesJTextArea.setEnabled(true);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PlannerGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
           
    }//GEN-LAST:event_activitiesJTableMouseClicked
    
    private void initTable(int n_week) {
        try {
            LinkedList<MaintenanceActivity> activityList = MaintenanceActivity.getMaintenanceActivities(connection);
            DefaultTableModel activitiesTabelModel = (DefaultTableModel) activitiesJTable.getModel();
            activitiesTabelModel.setNumRows(0);
            activitiesTabelModel.fireTableDataChanged();
            activityList.forEach((MaintenanceActivity activity) -> {
                if(activity.getWeek() == n_week){
                    String queryCheckIfAssigned = "SELECT * FROM ESECUZIONE WHERE activity_id ='"+activity.getActivityId()+"'"
                            + "AND maintainer='"+loggedUser.getUsername()+"';";
                    try {
                        PreparedStatement statementForCheck = connection.prepareStatement(queryCheckIfAssigned);
                        ResultSet rsCheck = statementForCheck.executeQuery();
                        if(rsCheck.next()){
                            activitiesTabelModel.addRow(new Object[]{activity.getActivityId(), activity.getSite().getName(), activity.getTypology().getName(), activity.getEstimatedIntervention() + " min."});
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(MaintainerGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                    
                }
            });
            
        } catch (SQLException ex) {
            Logger.getLogger(PlannerGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
           
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
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MaintainerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MaintainerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MaintainerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MaintainerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MaintainerGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane activitiesJScrollPane;
    private javax.swing.JTable activitiesJTable;
    private javax.swing.JLabel detailsActivityJLabel;
    private javax.swing.JTextField detailsActivityJTextField;
    private javax.swing.JScrollPane detailsDescriptionJScrollPane;
    private javax.swing.JTextArea detailsDescriptionJTextArea;
    private javax.swing.JPanel detailsJPanel;
    private javax.swing.JScrollPane detailsNotesJScrollPane;
    private javax.swing.JTextArea detailsNotesJTextArea;
    private javax.swing.JLabel detailsSMPJLabel;
    private javax.swing.JTextField detailsSMPJTextField;
    private javax.swing.JList<String> detailsSkillsJList;
    private javax.swing.JScrollPane detailsSkillsJScrollPane;
    private javax.swing.JLabel detailsWeekJLabel;
    private javax.swing.JTextField detailsWeekJTextField;
    private javax.swing.JPanel mainJPanel;
    private javax.swing.JButton refreshJButton;
    private javax.swing.JPanel topJPanel;
    private javax.swing.JSpinner weekJSpinner;
    private javax.swing.JLabel weekNumberJLabel;
    // End of variables declaration//GEN-END:variables
}

