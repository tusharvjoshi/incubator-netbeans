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
/*
 * TargetPanel.java
 *
 * Created on February 6, 2004, 2:54 PM
 */

package org.netbeans.modules.j2ee.sun.ide.sunresources.wizards;

import org.openide.util.HelpCtx;

import java.awt.Component;
import javax.swing.JPanel;
import javax.swing.event.ChangeListener;

import org.netbeans.modules.j2ee.sun.api.restricted.ResourceUtils;

import org.netbeans.modules.j2ee.sun.sunresources.beans.WizardConstants;

/**
 *
 * @author  nityad
 */
public class TargetPanel extends ResourceWizardPanel implements WizardConstants{
    
    private org.openide.WizardDescriptor.Panel panel;
    private ResourceConfigHelper helper;
    
    /** Creates a new instance of TargetPanel */
    public TargetPanel(ResourceConfigHelper helper) {
        this.helper = helper;
    }
    
    public void setPanel(org.openide.WizardDescriptor.Panel panel) {
        this.panel = panel;
    }
    
    public TargetPanel getPanel() {
        return this;
    }

    // Get the visual component for the panel. In this template, the component
    // is kept separate. This can be more efficient: if the wizard is created
    // but never displayed, or not all panels are displayed, it is better to
    // create only those which really need to be visible.
    public Component getComponent() {
       return panel.getComponent();
    }
    
    public HelpCtx getHelp() {
      return new HelpCtx("AS_Wiz_Target"); //NOI18N
    }
     
    public boolean isValid() {
        try{
            //Fix for bug# 5025573 - Check for invalid file names
            Component comp[] = ((JPanel)getComponent()).getComponents();
            JPanel pane = (JPanel)comp[0];
            Component paneComp[] = pane.getComponents();
            String targetName = ((javax.swing.JTextField)paneComp[1]).getText();
            if (targetName != null && targetName.length() != 0 && (! targetName.equals("<default name>"))){
               if (! ResourceUtils.isFriendlyFilename(targetName)) 
                    return false;
            }
        }catch(Exception ex){
        }    
        return panel.isValid();
    }
    
    public synchronized void addChangeListener(ChangeListener listener) {
        panel.addChangeListener(listener);
    }

    public synchronized void removeChangeListener(ChangeListener listener) {
        panel.removeChangeListener(listener);
    }

    // You can use a settings object to keep track of state.155
    // Normally the settings object will be the WizardDescriptor,
    // so you can use WizardDescriptor.getProperty & putProperty
    // to store information entered by the user.
    public void readSettings(Object settings) {
       panel.readSettings(settings);
    }
    
    public void storeSettings(Object settings) {
        panel.storeSettings(settings);
    }
}
