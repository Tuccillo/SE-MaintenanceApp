/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.team14.se.maintenanceapp.Competence;
import java.util.Arrays;
import java.util.LinkedList;
import javax.swing.DefaultListModel;
import javax.swing.event.ListSelectionEvent;

/**
 * Custom panel to add or remove competence to an entity
 * 
 * @author mario
 */
public class CompetencePanel extends javax.swing.JPanel {
    
    private LinkedList<Competence> competences;
    private LinkedList<Competence> selectedCompetences = null;
    private DefaultListModel<String> competencesListModel;

    /**
     * Creates new CompetencePanel
     */
    public CompetencePanel() {
        this.competencesListModel = new DefaultListModel<>();
        initComponents();
        
        this.competencesJList.addListSelectionListener((ListSelectionEvent event) -> { 
            this.removeCompetencesJButton.setEnabled(true);
        });
    }
    
    /**
     * Return the list of the names of the selected competencies
     * 
     * @return the list of the names of the selected competencies
     */
    public LinkedList<String> getSelectedCompetences() {
        LinkedList<String> list = new LinkedList<>();
        
        for (int i = 0; i < this.competencesJList.getModel().getSize(); i++) {
             list.add(String.valueOf(this.competencesJList.getModel().getElementAt(i)));
        }
        
        return list;
    }
    
    /**
     * Activate the pane and fill the list with the entityCompetences
     * and the combo box with the totalCompetencies minus the entityCompetences.
     * Reset the components if already initialized.
     * 
     * @param entityCompetences the competencies associated to the entry
     * @param totalCompetences  the total competencies available
     */
    public void activate(
            LinkedList<Competence> entityCompetences, 
            LinkedList<Competence> totalCompetences) {
        
        this.addCompetencesJComboBox.removeAllItems();
        this.competencesListModel.clear();
        
        super.setEnabled(true);
        this.selectedCompetences = entityCompetences;
        this.competences = totalCompetences;
        
        this.competencesListModel = new DefaultListModel<>();
        
        this.selectedCompetences.forEach(competence -> {
            this.competencesListModel.addElement(competence.getName());
        });
        this.competencesJList.setModel(competencesListModel);
        
        this.competences.removeAll(this.selectedCompetences);

        this.competences.forEach(competence -> {
            addCompetencesJComboBox.addItem(competence.getName());
        });
        
        this.competencesJScrollPane.setEnabled(true);
        this.competencesJList.setEnabled(true);
        
        this.addCompetencesJPanel.setEnabled(true);
        this.addCompetencesJComboBox.setEnabled(true);
        
        if (this.competences.size() > 0){
            this.addCompetencesJButton.setEnabled(true);
        }
    }
    
    /**
     * Enable or disable the panel and its components 
     * depending on the value of the parameter status.
     * 
     * @param status If true, this component is enabled;
     * otherwise this component is disabled
     */
    @Override
    public void setEnabled(boolean status){
        if (status){
            super.setEnabled(true);
            this.competencesJScrollPane.setEnabled(true);
            this.competencesJList.setEnabled(true);

            this.addCompetencesJPanel.setEnabled(true);
            this.addCompetencesJComboBox.setEnabled(true);

            if (this.addCompetencesJComboBox.getItemCount() > 0){
                this.addCompetencesJButton.setEnabled(true);
            }
            if (this.competencesJList.getModel().getSize() > 0){
                this.removeCompetencesJButton.setEnabled(true);
            }
        } else {
            super.setEnabled(false);
            this.competencesJScrollPane.setEnabled(false);
            this.competencesJList.setEnabled(false);

            this.addCompetencesJPanel.setEnabled(false);
            this.addCompetencesJComboBox.setEnabled(false);
            this.addCompetencesJButton.setEnabled(false);
            this.removeCompetencesJButton.setEnabled(false);
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

        competencesJScrollPane = new javax.swing.JScrollPane();
        competencesJList = new javax.swing.JList<>();
        removeCompetencesJButton = new javax.swing.JButton();
        addCompetencesJPanel = new javax.swing.JPanel();
        addCompetencesJButton = new javax.swing.JButton();
        addCompetencesJComboBox = new javax.swing.JComboBox<>();

        setBorder(javax.swing.BorderFactory.createTitledBorder("Competences"));

        competencesJList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        competencesJList.setEnabled(false);
        competencesJScrollPane.setViewportView(competencesJList);

        removeCompetencesJButton.setText("Remove Selected");
        removeCompetencesJButton.setEnabled(false);
        removeCompetencesJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeCompetencesJButtonActionPerformed(evt);
            }
        });

        addCompetencesJPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Add Competences"));
        addCompetencesJPanel.setEnabled(false);

        addCompetencesJButton.setText("Add ");
        addCompetencesJButton.setEnabled(false);
        addCompetencesJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addCompetencesJButtonActionPerformed(evt);
            }
        });

        addCompetencesJComboBox.setEnabled(false);

        javax.swing.GroupLayout addCompetencesJPanelLayout = new javax.swing.GroupLayout(addCompetencesJPanel);
        addCompetencesJPanel.setLayout(addCompetencesJPanelLayout);
        addCompetencesJPanelLayout.setHorizontalGroup(
            addCompetencesJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addCompetencesJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(addCompetencesJComboBox, 0, 230, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addCompetencesJButton)
                .addContainerGap())
        );
        addCompetencesJPanelLayout.setVerticalGroup(
            addCompetencesJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addCompetencesJPanelLayout.createSequentialGroup()
                .addGroup(addCompetencesJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addCompetencesJButton)
                    .addComponent(addCompetencesJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(competencesJScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(88, 88, 88))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(addCompetencesJPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(removeCompetencesJButton)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(competencesJScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(removeCompetencesJButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addCompetencesJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Move a competence from combo box to the list
     * 
     * @param evt not used
     */
    private void addCompetencesJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addCompetencesJButtonActionPerformed
        this.competencesListModel.addElement(this.addCompetencesJComboBox.getSelectedItem().toString());
        this.addCompetencesJComboBox.removeItem(this.addCompetencesJComboBox.getSelectedItem());
        if (this.addCompetencesJComboBox.getItemCount() < 1){
            this.addCompetencesJButton.setEnabled(false);
        }
        this.removeCompetencesJButton.setEnabled(false);
    }//GEN-LAST:event_addCompetencesJButtonActionPerformed

    /**
     * Move a competence from list to the combo box
     * 
     * @param evt not used
     */
    private void removeCompetencesJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeCompetencesJButtonActionPerformed
        this.addCompetencesJComboBox.addItem(this.competencesJList.getSelectedValue());
        this.competencesListModel.removeElement(this.competencesJList.getSelectedValue());
        if (this.addCompetencesJComboBox.getItemCount() > 0){
            this.addCompetencesJButton.setEnabled(true);
        }
        this.removeCompetencesJButton.setEnabled(false);
    }//GEN-LAST:event_removeCompetencesJButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addCompetencesJButton;
    private javax.swing.JComboBox<String> addCompetencesJComboBox;
    private javax.swing.JPanel addCompetencesJPanel;
    private javax.swing.JList<String> competencesJList;
    private javax.swing.JScrollPane competencesJScrollPane;
    private javax.swing.JButton removeCompetencesJButton;
    // End of variables declaration//GEN-END:variables
}
