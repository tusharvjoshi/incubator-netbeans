/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 2013 Oracle and/or its affiliates. All rights reserved.
 *
 * Oracle and Java are registered trademarks of Oracle and/or its affiliates.
 * Other names may be trademarks of their respective owners.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common
 * Development and Distribution License("CDDL") (collectively, the
 * "License"). You may not use this file except in compliance with the
 * License. You can obtain a copy of the License at
 * http://www.netbeans.org/cddl-gplv2.html
 * or nbbuild/licenses/CDDL-GPL-2-CP. See the License for the
 * specific language governing permissions and limitations under the
 * License.  When distributing the software, include this License Header
 * Notice in each file and include the License file at
 * nbbuild/licenses/CDDL-GPL-2-CP.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the GPL Version 2 section of the License file that
 * accompanied this code. If applicable, add the following below the
 * License Header, with the fields enclosed by brackets [] replaced by
 * your own identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
 *
 * If you wish your version of this file to be governed by only the CDDL
 * or only the GPL Version 2, indicate your decision by adding
 * "[Contributor] elects to include this software in this distribution
 * under the [CDDL or GPL Version 2] license." If you do not indicate a
 * single choice of license, a recipient has the option to distribute
 * your version of this file under either the CDDL, the GPL Version 2 or
 * to extend the choice of license to its licensees as provided above.
 * However, if you add GPL Version 2 code and therefore, elected the GPL
 * Version 2 license, then the option applies only if the new code is
 * made subject to such option by the copyright holder.
 *
 * Contributor(s):
 *
 * Portions Copyrighted 2013 Sun Microsystems, Inc.
 */
package org.netbeans.modules.web.jsf.wizards;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.netbeans.api.project.Project;
import org.netbeans.api.project.ProjectUtils;
import org.netbeans.modules.web.jsf.wizards.ResourceLibraryIterator.ProjectType;
import org.openide.filesystems.FileObject;
import org.openide.util.ChangeSupport;

/**
 *
 * @author Martin Fousek <marfous@netbeans.org>
 */
public class ResourceLibraryIteratorPanelVisual extends javax.swing.JPanel implements ChangeListener {

    private static final long serialVersionUID = 1L;
    private TemplatePanelVisual templatePanel;
    private final ChangeSupport changeSupport = new ChangeSupport(this);
    private final Project project;
    private final ProjectType projectType;
    private final FileObject contractsParent;

    /**
     * Creates new form ResourceLibraryIteratorPanelVisual.
     */
    public ResourceLibraryIteratorPanelVisual(Project project, FileObject contractsParent, ProjectType projectType) {
        this.project = project;
        this.contractsParent = contractsParent;
        this.projectType = projectType;
        initComponents();
        initPanelDefaultValues();
        initTemplatesPanel();
        initListeners();
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The
     * content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        contactNameLabel = new javax.swing.JLabel();
        contactNameTextField = new javax.swing.JTextField();
        projectLabel = new javax.swing.JLabel();
        projectTextField = new javax.swing.JTextField();
        folderLabel = new javax.swing.JLabel();
        folderTextField = new javax.swing.JTextField();
        createdFolderLabel = new javax.swing.JLabel();
        createdFolderTextField = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        createTemplateCheckBox = new javax.swing.JCheckBox();
        templateNameLabel = new javax.swing.JLabel();
        templateNameTextField = new javax.swing.JTextField();
        templatePanelHolder = new javax.swing.JPanel();
        templateFileLabel = new javax.swing.JLabel();
        templateFileTextField = new javax.swing.JTextField();

        setName(org.openide.util.NbBundle.getMessage(ResourceLibraryIteratorPanelVisual.class, "LBL_ResourceLibraryPanel.name")); // NOI18N

        contactNameLabel.setLabelFor(contactNameTextField);
        org.openide.awt.Mnemonics.setLocalizedText(contactNameLabel, org.openide.util.NbBundle.getMessage(ResourceLibraryIteratorPanelVisual.class, "ResourceLibraryIteratorPanelVisual.contractNameLabel.text")); // NOI18N

        projectLabel.setLabelFor(projectTextField);
        org.openide.awt.Mnemonics.setLocalizedText(projectLabel, org.openide.util.NbBundle.getMessage(ResourceLibraryIteratorPanelVisual.class, "ResourceLibraryIteratorPanelVisual.projectLabel.text")); // NOI18N

        projectTextField.setEditable(false);

        folderLabel.setLabelFor(folderTextField);
        org.openide.awt.Mnemonics.setLocalizedText(folderLabel, org.openide.util.NbBundle.getMessage(ResourceLibraryIteratorPanelVisual.class, "ResourceLibraryIteratorPanelVisual.folderNameLabel.text")); // NOI18N

        folderTextField.setEditable(false);
        folderTextField.setText("contracts"); // NOI18N

        createdFolderLabel.setLabelFor(createdFolderTextField);
        org.openide.awt.Mnemonics.setLocalizedText(createdFolderLabel, org.openide.util.NbBundle.getMessage(ResourceLibraryIteratorPanelVisual.class, "ResourceLibraryIteratorPanelVisual.createdFolderLabel.text")); // NOI18N

        createdFolderTextField.setEditable(false);

        org.openide.awt.Mnemonics.setLocalizedText(createTemplateCheckBox, org.openide.util.NbBundle.getMessage(ResourceLibraryIteratorPanelVisual.class, "ResourceLibraryIteratorPanelVisual.createTemplateCheckBox.text")); // NOI18N

        templateNameLabel.setLabelFor(templateNameTextField);
        org.openide.awt.Mnemonics.setLocalizedText(templateNameLabel, org.openide.util.NbBundle.getMessage(ResourceLibraryIteratorPanelVisual.class, "ResourceLibraryIteratorPanelVisual.templateNameLabel.text")); // NOI18N

        templateNameTextField.setText("template"); // NOI18N

        templatePanelHolder.setPreferredSize(new java.awt.Dimension(0, 180));

        javax.swing.GroupLayout templatePanelHolderLayout = new javax.swing.GroupLayout(templatePanelHolder);
        templatePanelHolder.setLayout(templatePanelHolderLayout);
        templatePanelHolderLayout.setHorizontalGroup(
            templatePanelHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        templatePanelHolderLayout.setVerticalGroup(
            templatePanelHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 180, Short.MAX_VALUE)
        );

        templateFileLabel.setLabelFor(templateFileTextField);
        org.openide.awt.Mnemonics.setLocalizedText(templateFileLabel, org.openide.util.NbBundle.getMessage(ResourceLibraryIteratorPanelVisual.class, "ResourceLibraryIteratorPanelVisual.templateFileLabel.text")); // NOI18N

        templateFileTextField.setEditable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(contactNameLabel)
                    .addComponent(projectLabel)
                    .addComponent(folderLabel)
                    .addComponent(createdFolderLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(contactNameTextField)
                    .addComponent(projectTextField)
                    .addComponent(folderTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 462, Short.MAX_VALUE)
                    .addComponent(createdFolderTextField)))
            .addComponent(jSeparator1)
            .addComponent(templatePanelHolder, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(createTemplateCheckBox)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(templateFileLabel)
                    .addComponent(templateNameLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(templateNameTextField)
                    .addComponent(templateFileTextField)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(contactNameLabel)
                    .addComponent(contactNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(projectLabel)
                    .addComponent(projectTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(folderLabel)
                    .addComponent(folderTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(createdFolderLabel)
                    .addComponent(createdFolderTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(createTemplateCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(templateNameLabel)
                    .addComponent(templateNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(templateFileLabel)
                    .addComponent(templateFileTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(templatePanelHolder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel contactNameLabel;
    private javax.swing.JTextField contactNameTextField;
    private javax.swing.JCheckBox createTemplateCheckBox;
    private javax.swing.JLabel createdFolderLabel;
    private javax.swing.JTextField createdFolderTextField;
    private javax.swing.JLabel folderLabel;
    private javax.swing.JTextField folderTextField;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel projectLabel;
    private javax.swing.JTextField projectTextField;
    private javax.swing.JLabel templateFileLabel;
    private javax.swing.JTextField templateFileTextField;
    private javax.swing.JLabel templateNameLabel;
    private javax.swing.JTextField templateNameTextField;
    private javax.swing.JPanel templatePanelHolder;
    // End of variables declaration//GEN-END:variables

    private void initTemplatesPanel() {
        templatePanel = new TemplatePanelVisual();
        templatePanelHolder.setLayout(new GridLayout(1, 1));
        templatePanelHolder.add(templatePanel);
        templatePanel.setVisible(true);
        templatePanelHolder.setEnabled(false);
        repaint();
        revalidate();
        setVisibleTemplateInfo(false);
    }

    private void setVisibleTemplateInfo(boolean visible) {
        templatePanelHolder.setVisible(visible);
        templateNameLabel.setVisible(visible);
        templateNameTextField.setVisible(visible);
        templateFileLabel.setVisible(visible);
        templateFileTextField.setVisible(visible);
    }

    private void initPanelDefaultValues() {
        projectTextField.setText(ProjectUtils.getInformation(project).getDisplayName());
        updateCreatedContractPath();
        updateCreatedTemplatePath();
    }

    private void initListeners() {
        createTemplateCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeSupport.fireChange();
            }
        });
        contactNameTextField.getDocument().addDocumentListener(new PanelDocumentListener());
        templateNameTextField.getDocument().addDocumentListener(new PanelDocumentListener());
        changeSupport.addChangeListener(this);
    }

    public void addChangeListener(ChangeListener listener) {
        changeSupport.addChangeListener(listener);
    }

    public void removeChangeListener(ChangeListener listener) {
        changeSupport.removeChangeListener(listener);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        setVisibleTemplateInfo(createTemplateCheckBox.isSelected());
        updateCreatedContractPath();
        updateCreatedTemplatePath();
    }

    private void updateCreatedTemplatePath() {
        templateFileTextField.setText(getContractPath() + "/" + templateNameTextField.getText());
    }

    private void updateCreatedContractPath() {
        createdFolderTextField.setText(getContractPath());
    }

    private String getContractPath() {
        StringBuilder path = new StringBuilder(contractsParent.getPath());
        if (projectType == ProjectType.J2SE && !ResourceLibraryIterator.META_INF.equals(contractsParent.getName())) {
            path.append("/").append(ResourceLibraryIterator.META_INF); //NOI18N
        }
        path.append("/").append(folderTextField.getText()).append("/").append(contactNameTextField.getText()); //NOI18N
        return path.toString();
    }

    public String getTemplateName() {
        return templateNameTextField.getText();
    }

    public TemplatePanelVisual getTemplatePanel() {
        return templatePanel;
    }

    public String getContractName() {
        return contactNameTextField.getText();
    }

    public boolean isCreateInitialTemplate() {
        return createTemplateCheckBox.isSelected();
    }

    private class PanelDocumentListener implements DocumentListener {

        @Override
        public void insertUpdate(DocumentEvent e) {
            fireUpdate();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            fireUpdate();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            fireUpdate();
        }

        private void fireUpdate() {
            ResourceLibraryIteratorPanelVisual.this.changeSupport.fireChange();
        }
    }
}
