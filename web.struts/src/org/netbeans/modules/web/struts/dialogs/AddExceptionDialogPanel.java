/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 1997-2010 Oracle and/or its affiliates. All rights reserved.
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
 * Contributor(s):
 *
 * The Original Software is NetBeans. The Initial Developer of the Original
 * Software is Sun Microsystems, Inc. Portions Copyright 1997-2006 Sun
 * Microsystems, Inc. All Rights Reserved.
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
 */

package org.netbeans.modules.web.struts.dialogs;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.lang.model.element.TypeElement;
import javax.swing.DefaultComboBoxModel;
import javax.swing.text.JTextComponent;
import org.netbeans.api.java.source.ClassIndex.NameKind;
import org.netbeans.api.java.source.ClassIndex.SearchScope;
import org.netbeans.api.java.source.ClasspathInfo;
import org.netbeans.api.java.source.ElementHandle;
import org.netbeans.api.java.source.ui.TypeElementFinder;
import org.netbeans.modules.web.struts.StrutsConfigDataObject;
import org.netbeans.modules.web.struts.StrutsConfigUtilities;
import org.netbeans.modules.web.struts.config.model.Action;
import org.netbeans.modules.web.struts.config.model.MessageResources;
import org.openide.util.NbBundle;

/**
 *
 * @author  radko
 */
public class AddExceptionDialogPanel extends javax.swing.JPanel implements ValidatingPanel {
    private static final String DEFAULT_BUNDLE_KEY="org.apache.struts.Globals.MESSAGES_KEY"; //NOI18N
    StrutsConfigDataObject config;
    /** Creates new form AddForwardDialog */
    public AddExceptionDialogPanel(StrutsConfigDataObject config, String targetActionPath) {
        this.config=config;
        initComponents();
        List actions = StrutsConfigUtilities.getAllActionsInModule(config);
        DefaultComboBoxModel model = (DefaultComboBoxModel)jComboBoxCallAction.getModel();
        DefaultComboBoxModel model1 = (DefaultComboBoxModel)jComboBoxActionExc.getModel();
        Iterator iter = actions.iterator();
        while (iter.hasNext()) {
            String actionPath=((Action)iter.next()).getAttributeValue("path"); //NOI18N
            model.addElement(actionPath);
            model1.addElement(actionPath);
        }
        List messageResources = StrutsConfigUtilities.getAllMessageResourcesInModule(config);
        model = (DefaultComboBoxModel)jComboBoxBundleKey.getModel();
        iter = messageResources.iterator();
        while (iter.hasNext()) {
            String key=((MessageResources)iter.next()).getAttributeValue("key"); //NOI18N
            model.addElement(key==null?DEFAULT_BUNDLE_KEY:key); //NOI18N
        }
        if (targetActionPath != null) {
            jRadioButtonActionExc.setSelected(true);
            jComboBoxActionExc.setSelectedItem(targetActionPath);
        }
    }
    
    public AddExceptionDialogPanel(StrutsConfigDataObject config) {
        this(config, null);
    }

    public javax.swing.AbstractButton[] getStateChangeComponents() {
        return new javax.swing.AbstractButton[]{jRadioButtonResFile, jRadioButtonGlobalExc};
    }

    public JTextComponent[] getDocumentChangeComponents() {
        return new JTextComponent[]{jTextFieldExcKey, jTextFieldResFile, (JTextComponent)jComboBoxExcType.getEditor().getEditorComponent()};
    }

    public String validatePanel() {
        if (getExceptionKey().length()==0)
            return NbBundle.getMessage(AddExceptionDialogPanel.class,"MSG_EmptyExcKey");
        if (getExceptionType().length()==0)
            return NbBundle.getMessage(AddExceptionDialogPanel.class,"MSG_EmptyExcType");
        if (jRadioButtonResFile.isSelected()) {
            String resourceFile = jTextFieldResFile.getText().trim();
            if (resourceFile.length()==0 || resourceFile.equals("/")) //NOI18N
                return NbBundle.getMessage(AddExceptionDialogPanel.class,"MSG_EmptyResourceFile");
        } else if (jComboBoxCallAction.getSelectedItem()==null) {
            return NbBundle.getMessage(AddExceptionDialogPanel.class,"MSG_EmptyAction");
        }
        if (!jRadioButtonGlobalExc.isSelected() && jComboBoxActionExc.getSelectedItem()==null) {
            return NbBundle.getMessage(AddExceptionDialogPanel.class,"MSG_EmptyAction");
        }
        return null;
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        jLabelBundleKey = new javax.swing.JLabel();
        jComboBoxBundleKey = new javax.swing.JComboBox();
        jLabelExcKey = new javax.swing.JLabel();
        jComboBoxExcType = new javax.swing.JComboBox();
        jLabelExcType = new javax.swing.JLabel();
        jTextFieldExcKey = new javax.swing.JTextField();
        jButtonExcType = new javax.swing.JButton();
        jLabelCall = new javax.swing.JLabel();
        jRadioButtonResFile = new javax.swing.JRadioButton();
        jTextFieldResFile = new javax.swing.JTextField();
        jButtonBrowse = new javax.swing.JButton();
        jRadioButtonCallAction = new javax.swing.JRadioButton();
        jComboBoxCallAction = new javax.swing.JComboBox();
        jLabelScope = new javax.swing.JLabel();
        jLabelLocation = new javax.swing.JLabel();
        jRadioButtonGlobalExc = new javax.swing.JRadioButton();
        jRadioButtonActionExc = new javax.swing.JRadioButton();
        jComboBoxActionExc = new javax.swing.JComboBox();
        jPanel1 = new javax.swing.JPanel();
        jRadioButtonSession = new javax.swing.JRadioButton();
        jRadioButtonRequest = new javax.swing.JRadioButton();

        setBorder(javax.swing.BorderFactory.createEmptyBorder(12, 12, 11, 11));
        setLayout(new java.awt.GridBagLayout());

        jLabelBundleKey.setDisplayedMnemonic(org.openide.util.NbBundle.getMessage(AddExceptionDialogPanel.class, "MNE_AddExcDialog_BundleKey").charAt(0));
        jLabelBundleKey.setLabelFor(jComboBoxBundleKey);
        jLabelBundleKey.setText(org.openide.util.NbBundle.getMessage(AddExceptionDialogPanel.class, "LBL_AddExcDialog_Bundle Key")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 12);
        add(jLabelBundleKey, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        add(jComboBoxBundleKey, gridBagConstraints);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("org/netbeans/modules/web/struts/dialogs/Bundle"); // NOI18N
        jComboBoxBundleKey.getAccessibleContext().setAccessibleDescription(bundle.getString("ACSD_jComboBoxBundleKey")); // NOI18N

        jLabelExcKey.setDisplayedMnemonic(org.openide.util.NbBundle.getMessage(AddExceptionDialogPanel.class, "MNE_AddExcDialog_ExcKey").charAt(0));
        jLabelExcKey.setLabelFor(jTextFieldExcKey);
        jLabelExcKey.setText(org.openide.util.NbBundle.getMessage(AddExceptionDialogPanel.class, "LBL_AddExcDialog_ExcKey")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 12);
        add(jLabelExcKey, gridBagConstraints);

        jComboBoxExcType.setEditable(true);
        jComboBoxExcType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "java.lang.NumberFormatException", "java.lang.NullPointerException", "java.lang.ArrayIndexOutOfBoundsException", "java.lang.StringIndexOutOfBoundsException", "java.lang.RuntimeException", "java.lang.Exception" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 0);
        add(jComboBoxExcType, gridBagConstraints);
        jComboBoxExcType.getAccessibleContext().setAccessibleDescription(bundle.getString("ACSD_jComboBoxExcType")); // NOI18N

        jLabelExcType.setDisplayedMnemonic(org.openide.util.NbBundle.getMessage(AddExceptionDialogPanel.class, "MNE_AddExcDialog_ExcType").charAt(0));
        jLabelExcType.setLabelFor(jComboBoxExcType);
        jLabelExcType.setText(org.openide.util.NbBundle.getMessage(AddExceptionDialogPanel.class, "LBL_AddExcDialog_ExcType")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 12);
        add(jLabelExcType, gridBagConstraints);

        jTextFieldExcKey.setColumns(30);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        add(jTextFieldExcKey, gridBagConstraints);
        jTextFieldExcKey.getAccessibleContext().setAccessibleDescription(bundle.getString("ACSD_jTextFieldExcKey")); // NOI18N

        jButtonExcType.setMnemonic(org.openide.util.NbBundle.getMessage(AddExceptionDialogPanel.class, "MNE_AddFwdDialog_Browse").charAt(0));
        jButtonExcType.setText(org.openide.util.NbBundle.getMessage(AddExceptionDialogPanel.class, "LBL_BrowseButton")); // NOI18N
        jButtonExcType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExcTypeActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.insets = new java.awt.Insets(0, 12, 5, 0);
        add(jButtonExcType, gridBagConstraints);
        jButtonExcType.getAccessibleContext().setAccessibleDescription(bundle.getString("ACSD_jButtonExcType")); // NOI18N

        jLabelCall.setText(org.openide.util.NbBundle.getMessage(AddExceptionDialogPanel.class, "LBL_AddExcDialog_Call")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        add(jLabelCall, gridBagConstraints);

        buttonGroup1.add(jRadioButtonResFile);
        jRadioButtonResFile.setMnemonic(org.openide.util.NbBundle.getMessage(AddExceptionDialogPanel.class, "MNE_AddFwdDialog_ResFile").charAt(0));
        jRadioButtonResFile.setSelected(true);
        jRadioButtonResFile.setText(org.openide.util.NbBundle.getMessage(AddExceptionDialogPanel.class, "RB_ResourceFile")); // NOI18N
        jRadioButtonResFile.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jRadioButtonResFile.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jRadioButtonResFileItemStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 12, 5, 12);
        add(jRadioButtonResFile, gridBagConstraints);
        jRadioButtonResFile.getAccessibleContext().setAccessibleDescription(bundle.getString("ACSD_jRadioButtonResFile")); // NOI18N

        jTextFieldResFile.setColumns(30);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        add(jTextFieldResFile, gridBagConstraints);
        jTextFieldResFile.getAccessibleContext().setAccessibleName(bundle.getString("ACSN_jTextFieldResFile")); // NOI18N
        jTextFieldResFile.getAccessibleContext().setAccessibleDescription(bundle.getString("ACSD_jTextFieldResFile")); // NOI18N

        jButtonBrowse.setMnemonic(org.openide.util.NbBundle.getMessage(AddExceptionDialogPanel.class, "MNE_AddExcDialog_ResFileBrowse").charAt(0));
        jButtonBrowse.setText(org.openide.util.NbBundle.getMessage(AddExceptionDialogPanel.class, "LBL_BrowseButton")); // NOI18N
        jButtonBrowse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBrowseActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.insets = new java.awt.Insets(0, 12, 5, 0);
        add(jButtonBrowse, gridBagConstraints);
        jButtonBrowse.getAccessibleContext().setAccessibleDescription(bundle.getString("ACSD_jButtonBrowse")); // NOI18N

        buttonGroup1.add(jRadioButtonCallAction);
        jRadioButtonCallAction.setMnemonic(org.openide.util.NbBundle.getMessage(AddExceptionDialogPanel.class, "MNE_AddFwdDialog_FwdAction").charAt(0));
        jRadioButtonCallAction.setText(org.openide.util.NbBundle.getMessage(AddExceptionDialogPanel.class, "RB_Action")); // NOI18N
        jRadioButtonCallAction.setMargin(new java.awt.Insets(0, 0, 0, 0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 12, 10, 12);
        add(jRadioButtonCallAction, gridBagConstraints);
        jRadioButtonCallAction.getAccessibleContext().setAccessibleDescription(bundle.getString("ACSD_jRadioButtonCallAction")); // NOI18N

        jComboBoxCallAction.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 0);
        add(jComboBoxCallAction, gridBagConstraints);
        jComboBoxCallAction.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(AddExceptionDialogPanel.class, "ACSN_EDAction")); // NOI18N
        jComboBoxCallAction.getAccessibleContext().setAccessibleDescription(bundle.getString("ACSD_jComboBoxCallAction")); // NOI18N

        jLabelScope.setText(org.openide.util.NbBundle.getMessage(AddExceptionDialogPanel.class, "LBL_AddExcDialog_Scope")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 0);
        add(jLabelScope, gridBagConstraints);

        jLabelLocation.setText(org.openide.util.NbBundle.getMessage(AddExceptionDialogPanel.class, "LBL_AddFwdDialog_Location")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        add(jLabelLocation, gridBagConstraints);

        buttonGroup3.add(jRadioButtonGlobalExc);
        jRadioButtonGlobalExc.setMnemonic(org.openide.util.NbBundle.getMessage(AddExceptionDialogPanel.class, "MNE_AddExcDialog_GlobalExc").charAt(0));
        jRadioButtonGlobalExc.setSelected(true);
        jRadioButtonGlobalExc.setText(org.openide.util.NbBundle.getMessage(AddExceptionDialogPanel.class, "LBL_AddExcDialog_GlobalExc")); // NOI18N
        jRadioButtonGlobalExc.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jRadioButtonGlobalExc.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jRadioButtonGlobalExcItemStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 12, 5, 12);
        add(jRadioButtonGlobalExc, gridBagConstraints);
        jRadioButtonGlobalExc.getAccessibleContext().setAccessibleDescription(bundle.getString("ACSD_jRadioButtonGlobalExc")); // NOI18N

        buttonGroup3.add(jRadioButtonActionExc);
        jRadioButtonActionExc.setMnemonic(org.openide.util.NbBundle.getMessage(AddExceptionDialogPanel.class, "MNE_AddExcDialog_ActionExc").charAt(0));
        jRadioButtonActionExc.setText(org.openide.util.NbBundle.getMessage(AddExceptionDialogPanel.class, "LBL_AddExcDialog_ActionExc")); // NOI18N
        jRadioButtonActionExc.setMargin(new java.awt.Insets(0, 0, 0, 0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 12, 0, 12);
        add(jRadioButtonActionExc, gridBagConstraints);
        jRadioButtonActionExc.getAccessibleContext().setAccessibleDescription(bundle.getString("ACSD_jRadioButtonActionExc")); // NOI18N

        jComboBoxActionExc.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        add(jComboBoxActionExc, gridBagConstraints);
        jComboBoxActionExc.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(AddExceptionDialogPanel.class, "ACSN_EDAction")); // NOI18N
        jComboBoxActionExc.getAccessibleContext().setAccessibleDescription(bundle.getString("ACSD_jComboBoxActionExc")); // NOI18N

        buttonGroup2.add(jRadioButtonSession);
        jRadioButtonSession.setMnemonic(org.openide.util.NbBundle.getMessage(AddExceptionDialogPanel.class, "MNE_AddExcDialog_Session").charAt(0));
        jRadioButtonSession.setSelected(true);
        jRadioButtonSession.setText(org.openide.util.NbBundle.getMessage(AddExceptionDialogPanel.class, "LBL_AddExcDialog_Session")); // NOI18N
        jRadioButtonSession.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jPanel1.add(jRadioButtonSession);
        jRadioButtonSession.getAccessibleContext().setAccessibleDescription(bundle.getString("ACSD_jRadioButtonSession")); // NOI18N

        buttonGroup2.add(jRadioButtonRequest);
        jRadioButtonRequest.setMnemonic(org.openide.util.NbBundle.getMessage(AddExceptionDialogPanel.class, "MNE_AddExcDialog_Request").charAt(0));
        jRadioButtonRequest.setText(org.openide.util.NbBundle.getMessage(AddExceptionDialogPanel.class, "LBL_AddExcDialog_Request")); // NOI18N
        jRadioButtonRequest.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jPanel1.add(jRadioButtonRequest);
        jRadioButtonRequest.getAccessibleContext().setAccessibleDescription(bundle.getString("ACSD_jRadioButtonReques")); // NOI18N

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 0);
        add(jPanel1, gridBagConstraints);

        getAccessibleContext().setAccessibleDescription(bundle.getString("ACSD_AddExceptionDialogPanel")); // NOI18N
    }// </editor-fold>//GEN-END:initComponents

    private void jRadioButtonGlobalExcItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jRadioButtonGlobalExcItemStateChanged
// TODO add your handling code here:
        jComboBoxActionExc.setEnabled(!jRadioButtonGlobalExc.isSelected());
    }//GEN-LAST:event_jRadioButtonGlobalExcItemStateChanged

    private void jRadioButtonResFileItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jRadioButtonResFileItemStateChanged
// TODO add your handling code here:
        boolean selected = jRadioButtonResFile.isSelected();
        jTextFieldResFile.setEditable(selected);
        jButtonBrowse.setEnabled(selected);
        jComboBoxCallAction.setEnabled(!selected);
    }//GEN-LAST:event_jRadioButtonResFileItemStateChanged

    private void jButtonExcTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExcTypeActionPerformed
        ClasspathInfo cpInfo = ClasspathInfo.create(config.getPrimaryFile());
        final ElementHandle<TypeElement> handle = TypeElementFinder.find(cpInfo, new TypeElementFinder.Customizer() {
            public Set<ElementHandle<TypeElement>> query(ClasspathInfo classpathInfo, String textForQuery, NameKind nameKind, Set<SearchScope> searchScopes) {                                            
                return classpathInfo.getClassIndex().getDeclaredTypes(textForQuery, nameKind, searchScopes);
            }

            public boolean accept(ElementHandle<TypeElement> typeHandle) {
                return true;
            }
        });
        if (handle != null) {
            jComboBoxExcType.setSelectedItem(handle.getQualifiedName());
        }
    }//GEN-LAST:event_jButtonExcTypeActionPerformed

    private void jButtonBrowseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBrowseActionPerformed
// TODO add your handling code here:
        try{
        org.netbeans.api.project.SourceGroup[] groups = StrutsConfigUtilities.getDocBaseGroups(config.getPrimaryFile());
            org.openide.filesystems.FileObject fo = BrowseFolders.showDialog(groups);
            if (fo!=null) {
                String res = "/"+StrutsConfigUtilities.getResourcePath(groups,fo,'/',true);
                jTextFieldResFile.setText(res);
            }
        } catch (java.io.IOException ex) {}
    }//GEN-LAST:event_jButtonBrowseActionPerformed
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JButton jButtonBrowse;
    private javax.swing.JButton jButtonExcType;
    private javax.swing.JComboBox jComboBoxActionExc;
    private javax.swing.JComboBox jComboBoxBundleKey;
    private javax.swing.JComboBox jComboBoxCallAction;
    private javax.swing.JComboBox jComboBoxExcType;
    private javax.swing.JLabel jLabelBundleKey;
    private javax.swing.JLabel jLabelCall;
    private javax.swing.JLabel jLabelExcKey;
    private javax.swing.JLabel jLabelExcType;
    private javax.swing.JLabel jLabelLocation;
    private javax.swing.JLabel jLabelScope;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton jRadioButtonActionExc;
    private javax.swing.JRadioButton jRadioButtonCallAction;
    private javax.swing.JRadioButton jRadioButtonGlobalExc;
    private javax.swing.JRadioButton jRadioButtonRequest;
    private javax.swing.JRadioButton jRadioButtonResFile;
    private javax.swing.JRadioButton jRadioButtonSession;
    private javax.swing.JTextField jTextFieldExcKey;
    private javax.swing.JTextField jTextFieldResFile;
    // End of variables declaration//GEN-END:variables

    public String getResourceBundle() {
        String key = (String)jComboBoxBundleKey.getSelectedItem();
        return DEFAULT_BUNDLE_KEY.equals(key)?null:key;
    }
    
    public String getExceptionKey() {
        String key = jTextFieldExcKey.getText().trim();
        return key==null?null:key;
    }
    
    public String getExceptionType() {
        javax.swing.text.Document doc = ((JTextComponent)jComboBoxExcType.getEditor().getEditorComponent()).getDocument();
        try {
            String exceptionType = doc.getText(0,doc.getLength());
            return exceptionType==null?null:exceptionType;
        } catch (javax.swing.text.BadLocationException ex) {
            return null;
        }
    }
    
    public String getScope() {
        return (jRadioButtonSession.isSelected()?null:"request"); //NOI18N
    }
    
    public String getForwardTo() {
        if (jRadioButtonResFile.isSelected()) {
            return jTextFieldResFile.getText().trim();
        } else {
            return (String)jComboBoxCallAction.getSelectedItem();
        }
    }

    public boolean isGlobal() {
        return jRadioButtonGlobalExc.isSelected();
    }
    
    public String getLocationAction() {
        return (String)jComboBoxActionExc.getSelectedItem();
    }
    
}
