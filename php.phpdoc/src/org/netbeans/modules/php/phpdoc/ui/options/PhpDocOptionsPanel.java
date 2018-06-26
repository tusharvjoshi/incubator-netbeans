/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 2010 Oracle and/or its affiliates. All rights reserved.
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
 * Portions Copyrighted 2010 Sun Microsystems, Inc.
 */

package org.netbeans.modules.php.phpdoc.ui.options;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.netbeans.modules.php.api.util.FileUtils;
import org.netbeans.modules.php.api.util.UiUtils;
import org.netbeans.modules.php.phpdoc.PhpDocScript;
import org.netbeans.spi.options.OptionsPanelController;
import org.openide.awt.HtmlBrowser;
import org.openide.awt.Mnemonics;
import org.openide.filesystems.FileChooserBuilder;
import org.openide.filesystems.FileUtil;
import org.openide.util.ChangeSupport;
import org.openide.util.Exceptions;
import org.openide.util.NbBundle;

@NbBundle.Messages({"PhpDocOptionsPanel.keywords.documentation=documentation", "PhpDocOptionsPanel.keywords.TabTitle=Frameworks & Tools"})
@OptionsPanelController.Keywords(keywords={"php", "phpdoc", "phpdocumentor", "#PhpDocOptionsPanel.keywords.documentation"},
        location=UiUtils.OPTIONS_PATH, tabTitle= "#PhpDocOptionsPanel.keywords.TabTitle")
public final class PhpDocOptionsPanel extends JPanel {
    private static final long serialVersionUID = 18784654654113L;
    private static final String PHPDOC_LAST_FOLDER_SUFFIX = ".phpdoc"; // NOI18N

    private final ChangeSupport changeSupport = new ChangeSupport(this);

    public PhpDocOptionsPanel() {
        initComponents();

        // not set in Design because of windows (panel too wide then)
        phpDocUsageLabel.setText(NbBundle.getMessage(PhpDocOptionsPanel.class, "LBL_PhpDocUsage",
                PhpDocScript.SCRIPT_NAME, PhpDocScript.SCRIPT_NAME_LONG, PhpDocScript.SCRIPT_NAME_PHAR));
        errorLabel.setText(" "); // NOI18N

        phpDocTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                processUpdate();
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                processUpdate();
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                processUpdate();
            }
            private void processUpdate() {
                fireChange();
            }
        });
    }

    public String getPhpDoc() {
        return phpDocTextField.getText();
    }

    public void setPhpDoc(String phpDoc) {
        phpDocTextField.setText(phpDoc);
    }

    public void setError(String message) {
        errorLabel.setText(" "); // NOI18N
        errorLabel.setForeground(UIManager.getColor("nb.errorForeground")); // NOI18N
        errorLabel.setText(message);
    }

    public void setWarning(String message) {
        errorLabel.setText(" "); // NOI18N
        errorLabel.setForeground(UIManager.getColor("nb.warningForeground")); // NOI18N
        errorLabel.setText(message);
    }

    public void clearError() {
        setError(" "); // NOI18N
    }

    public void addChangeListener(ChangeListener listener) {
        changeSupport.addChangeListener(listener);
    }

    public void removeChangeListener(ChangeListener listener) {
        changeSupport.removeChangeListener(listener);
    }

    void fireChange() {
        changeSupport.fireChange();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        phpDocLabel = new JLabel();
        phpDocTextField = new JTextField();
        browseButton = new JButton();
        searchButton = new JButton();
        phpDocUsageLabel = new JLabel();
        installationInfoLabel = new JLabel();
        installationLearnMoreLabel = new JLabel();
        errorLabel = new JLabel();
        versionInfoLabel = new JLabel();

        Mnemonics.setLocalizedText(phpDocLabel, NbBundle.getMessage(PhpDocOptionsPanel.class, "PhpDocOptionsPanel.phpDocLabel.text")); // NOI18N

        Mnemonics.setLocalizedText(browseButton, NbBundle.getMessage(PhpDocOptionsPanel.class, "PhpDocOptionsPanel.browseButton.text")); // NOI18N
        browseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                browseButtonActionPerformed(evt);
            }
        });

        Mnemonics.setLocalizedText(searchButton, NbBundle.getMessage(PhpDocOptionsPanel.class, "PhpDocOptionsPanel.searchButton.text")); // NOI18N
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });

        Mnemonics.setLocalizedText(phpDocUsageLabel, "HINT"); // NOI18N

        Mnemonics.setLocalizedText(installationInfoLabel, NbBundle.getMessage(PhpDocOptionsPanel.class, "PhpDocOptionsPanel.installationInfoLabel.text")); // NOI18N

        Mnemonics.setLocalizedText(installationLearnMoreLabel, NbBundle.getMessage(PhpDocOptionsPanel.class, "PhpDocOptionsPanel.installationLearnMoreLabel.text")); // NOI18N
        installationLearnMoreLabel.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                installationLearnMoreLabelMouseEntered(evt);
            }
            public void mousePressed(MouseEvent evt) {
                installationLearnMoreLabelMousePressed(evt);
            }
        });

        Mnemonics.setLocalizedText(errorLabel, "ERROR"); // NOI18N

        Mnemonics.setLocalizedText(versionInfoLabel, NbBundle.getMessage(PhpDocOptionsPanel.class, "PhpDocOptionsPanel.versionInfoLabel.text")); // NOI18N

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(phpDocLabel)
                .addPreferredGap(ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(phpDocUsageLabel)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(phpDocTextField)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(browseButton)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(searchButton))))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(Alignment.LEADING)
                    .addComponent(errorLabel)
                    .addComponent(installationInfoLabel)
                    .addComponent(installationLearnMoreLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(versionInfoLabel))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                    .addComponent(phpDocLabel)
                    .addComponent(phpDocTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchButton)
                    .addComponent(browseButton))
                .addPreferredGap(ComponentPlacement.RELATED)
                .addComponent(phpDocUsageLabel)
                .addGap(18, 18, 18)
                .addComponent(versionInfoLabel)
                .addPreferredGap(ComponentPlacement.RELATED)
                .addComponent(installationInfoLabel)
                .addPreferredGap(ComponentPlacement.RELATED)
                .addComponent(installationLearnMoreLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(errorLabel))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void browseButtonActionPerformed(ActionEvent evt) {//GEN-FIRST:event_browseButtonActionPerformed
        File phpDoc = new FileChooserBuilder(PhpDocOptionsPanel.class.getName() + PHPDOC_LAST_FOLDER_SUFFIX)
                .setTitle(NbBundle.getMessage(PhpDocOptionsPanel.class, "LBL_SelectPhpDoc"))
                .setFilesOnly(true)
                .showOpenDialog();
        if (phpDoc != null) {
            phpDoc = FileUtil.normalizeFile(phpDoc);
            phpDocTextField.setText(phpDoc.getAbsolutePath());
        }
    }//GEN-LAST:event_browseButtonActionPerformed

    private void searchButtonActionPerformed(ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
        String phpDoc = UiUtils.SearchWindow.search(new UiUtils.SearchWindow.SearchWindowSupport() {
            @Override
            public List<String> detect() {
                return FileUtils.findFileOnUsersPath(PhpDocScript.SCRIPT_NAME, PhpDocScript.SCRIPT_NAME_LONG);
            }

            @Override
            public String getWindowTitle() {
                return NbBundle.getMessage(PhpDocOptionsPanel.class, "LBL_PhpDocsTitle");
            }

            @Override
            public String getListTitle() {
                return NbBundle.getMessage(PhpDocOptionsPanel.class, "LBL_PhpDocs");
            }

            @Override
            public String getPleaseWaitPart() {
                return NbBundle.getMessage(PhpDocOptionsPanel.class, "LBL_PhpDocsPleaseWaitPart");
            }

            @Override
            public String getNoItemsFound() {
                return NbBundle.getMessage(PhpDocOptionsPanel.class, "LBL_NoPhpDocsFound");
            }
        });
        if (phpDoc != null) {
            phpDocTextField.setText(phpDoc);
        }
    }//GEN-LAST:event_searchButtonActionPerformed

    private void installationLearnMoreLabelMouseEntered(MouseEvent evt) {//GEN-FIRST:event_installationLearnMoreLabelMouseEntered
        evt.getComponent().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_installationLearnMoreLabelMouseEntered

    private void installationLearnMoreLabelMousePressed(MouseEvent evt) {//GEN-FIRST:event_installationLearnMoreLabelMousePressed
        try {
            URL url = new URL("http://www.phpdoc.org/"); // NOI18N
            HtmlBrowser.URLDisplayer.getDefault().showURL(url);
        } catch (MalformedURLException ex) {
            Exceptions.printStackTrace(ex);
        }
    }//GEN-LAST:event_installationLearnMoreLabelMousePressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JButton browseButton;
    private JLabel errorLabel;
    private JLabel installationInfoLabel;
    private JLabel installationLearnMoreLabel;
    private JLabel phpDocLabel;
    private JTextField phpDocTextField;
    private JLabel phpDocUsageLabel;
    private JButton searchButton;
    private JLabel versionInfoLabel;
    // End of variables declaration//GEN-END:variables

}
