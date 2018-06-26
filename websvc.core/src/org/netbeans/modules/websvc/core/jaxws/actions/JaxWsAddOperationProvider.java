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

package org.netbeans.modules.websvc.core.jaxws.actions;

import java.util.List;
import org.netbeans.api.java.project.JavaProjectConstants;
import org.netbeans.api.project.FileOwnerQuery;
import org.netbeans.api.project.Project;
import org.netbeans.api.project.ProjectUtils;
import org.netbeans.api.project.SourceGroup;
import org.netbeans.modules.websvc.api.jaxws.project.config.Service;
import org.netbeans.modules.websvc.spi.support.AddOperationActionProvider;
import org.netbeans.modules.websvc.api.support.AddOperationCookie;
import org.netbeans.modules.websvc.jaxws.api.JAXWSSupport;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;

@org.openide.util.lookup.ServiceProvider(service=org.netbeans.modules.websvc.spi.support.AddOperationActionProvider.class)
public class JaxWsAddOperationProvider implements AddOperationActionProvider {
    
    public AddOperationCookie getAddOperationCookie(FileObject fileObject) {
        if ( !fileObject.isValid() ){
            return null;
        }
        JAXWSSupport support = JAXWSSupport.getJAXWSSupport(fileObject);
        if (support != null) {
            String packageName = getPackageName(fileObject);
            if (packageName != null) {
                Service service = getService(support, packageName);
                if (service != null && service.getWsdlUrl() == null) return new JaxWsAddOperation(fileObject);
            }
        }
        return null;
    }
        
    private Service getService(JAXWSSupport support, String packageName) {
        List services = support.getServices();
        for (Object service:services) {
            if (packageName.equals(((Service)service).getImplementationClass())) {
                return (Service)service;
            } 
        }
        return null;
    }
    
    private String getPackageName(FileObject fo) {
        Project project = FileOwnerQuery.getOwner(fo);
        SourceGroup[] groups = ProjectUtils.getSources(project).getSourceGroups(JavaProjectConstants.SOURCES_TYPE_JAVA);
        if (groups!=null) {
            for (SourceGroup group: groups) {
                FileObject rootFolder = group.getRootFolder();
                if (FileUtil.isParentOf(rootFolder, fo)) {
                    String relativePath = FileUtil.getRelativePath(rootFolder, fo).replace('/', '.');
                    return (relativePath.endsWith(".java")? //NOI18N
                        relativePath.substring(0,relativePath.length()-5):
                        relativePath);
                }
            }
        }
        return null;
    }


}
