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
 * Portions Copyrighted 2009 Sun Microsystems, Inc.
 */

package org.netbeans.modules.javascript2.requirejs.ui;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import org.openide.util.NbBundle;

public class LocalPathCell extends JPanel {
    private static final long serialVersionUID = -2414911804161573026L;

    LocalPathCell(String path) {
        assert path != null;
        initComponents();
        localPathTextField.setText(path);
    }

    String getPath() {
        return localPathTextField.getText();
    }

    void setPath(String path) {
        localPathTextField.setText(path);
    }

    JTextField getTextField() {
        return localPathTextField;
    }

    JButton getButton() {
        return localPathBrowseButton;
    }

    void setBgColor(Color color) {
        localPathTextField.setBackground(color);
    }

    void setFgColor(Color color) {
        localPathTextField.setForeground(color);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        localPathTextField = new JTextField();
        localPathBrowseButton = new JButton();

        setMinimumSize(new Dimension(100, 0));

        localPathTextField.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));

        localPathBrowseButton.setText(NbBundle.getMessage(LocalPathCell.class, "LocalPathCell.localPathBrowseButton.text")); // NOI18N
        localPathBrowseButton.setBorder(BorderFactory.createLineBorder(UIManager.getDefaults().getColor("activeCaptionBorder")));

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(localPathTextField)
                .addGap(0, 0, 0)
                .addComponent(localPathBrowseButton))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(Alignment.LEADING)
            .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                .addComponent(localPathTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(localPathBrowseButton))
        );

        localPathTextField.getAccessibleContext().setAccessibleName(NbBundle.getMessage(LocalPathCell.class, "LocalPathCell.localPathTextField.AccessibleContext.accessibleName")); // NOI18N
        localPathTextField.getAccessibleContext().setAccessibleDescription(NbBundle.getMessage(LocalPathCell.class, "LocalPathCell.localPathTextField.AccessibleContext.accessibleDescription")); // NOI18N
        localPathBrowseButton.getAccessibleContext().setAccessibleName(NbBundle.getMessage(LocalPathCell.class, "LocalPathCell.localPathBrowseButton.AccessibleContext.accessibleName")); // NOI18N
        localPathBrowseButton.getAccessibleContext().setAccessibleDescription(NbBundle.getMessage(LocalPathCell.class, "LocalPathCell.localPathBrowseButton.AccessibleContext.accessibleDescription")); // NOI18N

        getAccessibleContext().setAccessibleName(NbBundle.getMessage(LocalPathCell.class, "LocalPathCell.AccessibleContext.accessibleName")); // NOI18N
        getAccessibleContext().setAccessibleDescription(NbBundle.getMessage(LocalPathCell.class, "LocalPathCell.AccessibleContext.accessibleDescription")); // NOI18N
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JButton localPathBrowseButton;
    private JTextField localPathTextField;
    // End of variables declaration//GEN-END:variables

}
