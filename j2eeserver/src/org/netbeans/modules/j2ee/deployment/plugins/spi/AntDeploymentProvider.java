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


package org.netbeans.modules.j2ee.deployment.plugins.spi;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

/**
 * This interface represents a "provider" for the Ant deployment.
 * <p>
 * The j2eeserver uses {@link OptionalDeploymentManagerFactory} to optains an 
 * instance of the AntDeploymentProvider from the server integration plugin.
 *
 * @author sherold
 *
 * @since 1.18
 */
public interface AntDeploymentProvider {
    
    /**
     * Generates the Ant deployment build script for the given module type to 
     * the specified output stream.
     * <p>
     * See {@link org.netbeans.modules.j2ee.deployment.devmodules.api.AntDeploymentHelper#writeDeploymentScript(File,Object,String)}
     * for what targets and properties is the Ant deployment build script required 
     * to define and what predefined properties it might use.
     *
     * @param os the output stream the deployment build script should be generated 
     *           to.
     * @param moduleType the module type the build script should handle. Use the
     *                   constants defined in the {@link org.netbeans.modules.j2ee.deployment.devmodules.api.J2eeModule}.
     * @throws IOException if a problem during generating the build script occurs.
     */
    void writeDeploymentScript(OutputStream os, Object moduleType) throws IOException;
    
    /**
     * Return the server instance specific deployment properties file used by 
     * the deployment build script generated by the {@link #writeDeploymentScript(OutputStream,Object)}.
     *
     * @return the deployment properties file.
     */
    File getDeploymentPropertiesFile();
}
