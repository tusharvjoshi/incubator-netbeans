/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 2012 Oracle and/or its affiliates. All rights reserved.
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
 * Portions Copyrighted 2012 Sun Microsystems, Inc.
 */
package org.netbeans.modules.php.editor.indent.ui;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.netbeans.modules.options.editor.spi.PreferencesCustomizer;
import org.netbeans.modules.php.editor.indent.FmtOptions;
import org.netbeans.modules.php.editor.indent.FmtOptions.CategorySupport;
import static org.netbeans.modules.php.editor.indent.FmtOptions.CategorySupport.OPTION_ID;

/**
 *
 * @author Ondrej Brejla <obrejla@netbeans.org>
 */
public class FmtUses extends javax.swing.JPanel {

    private static final Logger LOGGER = Logger.getLogger(FmtUses.class.getName());

    public FmtUses() {
        initComponents();
        preferFullyQualifiedNamesCheckBox.putClientProperty(OPTION_ID, FmtOptions.PREFER_FULLY_QUALIFIED_NAMES);
        preferMultipleUseStatementsCombinedCheckBox.putClientProperty(OPTION_ID, FmtOptions.PREFER_MULTIPLE_USE_STATEMENTS_COMBINED);
        preferGroupUsesCheckBox.putClientProperty(OPTION_ID, FmtOptions.PREFER_GROUP_USES);
        startUseWithNamespaceSeparatorCheckBox.putClientProperty(OPTION_ID, FmtOptions.START_USE_WITH_NAMESPACE_SEPARATOR);
        aliasesCapitalsOfNamespacesCheckBox.putClientProperty(OPTION_ID, FmtOptions.ALIASES_CAPITALS_OF_NAMESPACES);
    }

    public static PreferencesCustomizer.Factory getController() {
        String preview = "";
        try {
            preview = Utils.loadPreviewText(FmtUses.class.getClassLoader().getResourceAsStream("org/netbeans/modules/php/editor/indent/ui/Uses.php"));
        } catch (IOException ex) {
            LOGGER.log(Level.WARNING, null, ex);
        }
        return new CategorySupport.Factory("uses", FmtUses.class, preview);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        preferFullyQualifiedNamesCheckBox = new javax.swing.JCheckBox();
        preferMultipleUseStatementsCombinedCheckBox = new javax.swing.JCheckBox();
        preferGroupUsesCheckBox = new javax.swing.JCheckBox();
        startUseWithNamespaceSeparatorCheckBox = new javax.swing.JCheckBox();
        aliasesCapitalsOfNamespacesCheckBox = new javax.swing.JCheckBox();

        setName(org.openide.util.NbBundle.getMessage(FmtUses.class, "LBL_Uses")); // NOI18N
        setOpaque(false);

        preferFullyQualifiedNamesCheckBox.setMnemonic('F');
        org.openide.awt.Mnemonics.setLocalizedText(preferFullyQualifiedNamesCheckBox, org.openide.util.NbBundle.getMessage(FmtUses.class, "FmtUses.preferFullyQualifiedNamesCheckBox.text")); // NOI18N

        preferMultipleUseStatementsCombinedCheckBox.setMnemonic('M');
        org.openide.awt.Mnemonics.setLocalizedText(preferMultipleUseStatementsCombinedCheckBox, org.openide.util.NbBundle.getMessage(FmtUses.class, "FmtUses.preferMultipleUseStatementsCombinedCheckBox.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(preferGroupUsesCheckBox, org.openide.util.NbBundle.getMessage(FmtUses.class, "FmtUses.preferGroupUsesCheckBox.text")); // NOI18N

        startUseWithNamespaceSeparatorCheckBox.setMnemonic('S');
        org.openide.awt.Mnemonics.setLocalizedText(startUseWithNamespaceSeparatorCheckBox, org.openide.util.NbBundle.getMessage(FmtUses.class, "FmtUses.startUseWithNamespaceSeparatorCheckBox.text")); // NOI18N

        aliasesCapitalsOfNamespacesCheckBox.setMnemonic('g');
        org.openide.awt.Mnemonics.setLocalizedText(aliasesCapitalsOfNamespacesCheckBox, org.openide.util.NbBundle.getMessage(FmtUses.class, "FmtUses.aliasesCapitalsOfNamespacesCheckBox.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(preferFullyQualifiedNamesCheckBox)
                    .addComponent(preferMultipleUseStatementsCombinedCheckBox)
                    .addComponent(startUseWithNamespaceSeparatorCheckBox)
                    .addComponent(aliasesCapitalsOfNamespacesCheckBox)
                    .addComponent(preferGroupUsesCheckBox))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(preferFullyQualifiedNamesCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(preferMultipleUseStatementsCombinedCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(preferGroupUsesCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(startUseWithNamespaceSeparatorCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(aliasesCapitalsOfNamespacesCheckBox)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox aliasesCapitalsOfNamespacesCheckBox;
    private javax.swing.JCheckBox preferFullyQualifiedNamesCheckBox;
    private javax.swing.JCheckBox preferGroupUsesCheckBox;
    private javax.swing.JCheckBox preferMultipleUseStatementsCombinedCheckBox;
    private javax.swing.JCheckBox startUseWithNamespaceSeparatorCheckBox;
    // End of variables declaration//GEN-END:variables
}
