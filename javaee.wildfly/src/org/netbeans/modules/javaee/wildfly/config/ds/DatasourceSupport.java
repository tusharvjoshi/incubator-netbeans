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
 * Software is Sun Microsystems, Inc. Portions Copyright 1997-2007 Sun
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
package org.netbeans.modules.javaee.wildfly.config.ds;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;
import org.netbeans.modules.j2ee.deployment.common.api.ConfigurationException;
import org.netbeans.modules.j2ee.deployment.common.api.Datasource;
import org.netbeans.modules.j2ee.deployment.common.api.DatasourceAlreadyExistsException;
import org.netbeans.modules.javaee.wildfly.config.ResourceConfigurationHelper;
import org.netbeans.modules.javaee.wildfly.config.WildflyDatasource;
import org.netbeans.modules.javaee.wildfly.config.ds.gen.DatasourceType;
import org.netbeans.modules.javaee.wildfly.config.ds.gen.Datasources;
import org.netbeans.modules.javaee.wildfly.config.ds.gen.DsSecurityType;
import org.netbeans.modules.javaee.wildfly.config.ds.gen.PoolType;
import org.netbeans.modules.javaee.wildfly.config.xml.ConfigurationParser;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.cookies.EditorCookie;
import org.openide.cookies.SaveCookie;
import org.openide.filesystems.FileChangeAdapter;
import org.openide.filesystems.FileEvent;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.loaders.DataObject;
import org.openide.loaders.DataObjectNotFoundException;
import org.openide.util.Exceptions;
import org.openide.util.NbBundle;

/**
 *
 * @author Libor Kotouc
 */
public class DatasourceSupport {

    private static final String DS_RESOURCE_NAME = "jboss-ds.xml"; // NOI18N

    // the directory with resources - supplied by the configuration support in the construction time
    private File resourceDir;

    //model of the data source file
    private Datasources datasources;

    // data source file (placed in the resourceDir)
    private File datasourcesFile;

    //destination service file object
    private FileObject datasourcesFO;

    public DatasourceSupport(File resourceDir) {
        assert resourceDir != null : "Resource directory can't be null"; // NOI18N

        this.resourceDir = resourceDir;
        this.datasourcesFile = new File(resourceDir, DS_RESOURCE_NAME);
        ensureDatasourcesFOExists();
    }

    /**
     * Listener of jboss-ds.xml document changes.
     */
    private class DatasourceFileListener extends FileChangeAdapter {

        @Override
        public void fileChanged(FileEvent fe) {
            assert (fe.getSource() == datasourcesFO) : fe.getSource() + ":" + datasourcesFO;
            datasources = null;
        }

        @Override
        public void fileDeleted(FileEvent fe) {
            assert ((FileObject) fe.getSource()).getPath().equals(datasourcesFO.getPath()) : fe.getSource() + ":" + datasourcesFO;
            datasources = null;
        }
    }

    private void ensureDatasourcesFOExists() {
        if (!datasourcesFile.exists()) {
            return;
        }
        if (datasourcesFO == null || !datasourcesFO.isValid()) {
            datasourcesFO = FileUtil.toFileObject(datasourcesFile);
            assert (datasourcesFO != null);
            datasourcesFO.addFileChangeListener(new DatasourceFileListener());
        }
    }

//---------------------------------------- READING --------------------------------------
    public Set<Datasource> getDatasources() throws ConfigurationException {
        try {
            if (datasourcesFile.exists()) {
                return ConfigurationParser.INSTANCE.listDatasources(
                        FileUtil.toFileObject(datasourcesFile));
            }
        } catch (Exception ex) {
            throw new ConfigurationException("Wrong configuration in " + datasourcesFile, ex);
        }

        HashSet<Datasource> projectDS = new HashSet<Datasource>();
        Datasources dss = getDatasourcesGraph(false);
        if (dss != null) {
            DatasourceType ltxds[] = datasources.getDatasource();
            for (int i = 0; i < ltxds.length; i++) {
                if (ltxds[i].getJndiName().length() > 0) {
                    projectDS.add(new WildflyDatasource(
                            ltxds[i].getJndiName(),
                            ltxds[i].getConnectionUrl(),
                            ltxds[i].getSecurity().getUserName(),
                            ltxds[i].getSecurity().getPassword(),
                            ltxds[i].getDriverClass()));
                }
            }
        }

        return projectDS;
    }

    /**
     * Return Datasources graph. If it was not created yet, load it from the
     * file and cache it. If the file does not exist, generate it.
     *
     * @return Datasources graph or null if the jboss-ds.xml file is not
     * parseable.
     */
    private synchronized Datasources getDatasourcesGraph(boolean create) {

        try {
            if (datasourcesFile.exists()) {
                // load configuration if already exists
                try {
                    if (datasources == null) {
                        datasources = Datasources.createGraph(datasourcesFile);
                    }
                } catch (IOException ioe) {
                    Exceptions.printStackTrace(ioe);
                } catch (RuntimeException re) {
                    // jboss-ds.xml is not parseable, do nothing
                }
            } else if (create) {
                // create jboss-ds.xml if it does not exist yet
                datasources = new Datasources();
                ResourceConfigurationHelper.writeFile(datasourcesFile, datasources);
                ensureDatasourcesFOExists();
            } else {
                datasources = null;
            }
        } catch (ConfigurationException ce) {
            Exceptions.printStackTrace(ce);
        }

        return datasources;
    }

//---------------------------------------- WRITING --------------------------------------
    private abstract class DSResourceModifier {

        String rawName;
        String url;
        String username;
        String password;
        String driver;

        DSResourceModifier(String jndiName, String url, String username, String password, String driver) {
            this.rawName = WildflyDatasource.getRawName(jndiName);
            this.url = url;
            this.username = username;
            this.password = password;
            this.driver = driver;
        }

        abstract WildflyDatasource modify(Datasources datasources) throws DatasourceAlreadyExistsException;
    }

    public WildflyDatasource createDatasource(String jndiName, String url, String username, String password, String driver)
            throws UnsupportedOperationException, ConfigurationException, DatasourceAlreadyExistsException {
        WildflyDatasource ds = modifyDSResource(new DSResourceModifier(jndiName, url, username, password, driver) {
            WildflyDatasource modify(Datasources datasources) throws DatasourceAlreadyExistsException {

                DatasourceType ltxds[] = datasources.getDatasource();
                for (int i = 0; i < ltxds.length; i++) {
                    String jndiName = ltxds[i].getJndiName();
                    if (rawName.equals(WildflyDatasource.getRawName(jndiName))) {
                        //already exists
                        WildflyDatasource ds = new WildflyDatasource(
                                jndiName,
                                ltxds[i].getConnectionUrl(),
                                ltxds[i].getSecurity().getUserName(),
                                ltxds[i].getSecurity().getPassword(),
                                ltxds[i].getDriverClass());

                        throw new DatasourceAlreadyExistsException(ds);
                    }
                }

                DatasourceType lds = new DatasourceType();
                lds.setJndiName(rawName);
                lds.setConnectionUrl(url);
                lds.setDriverClass(driver);
                DsSecurityType security = new DsSecurityType();
                security.setUserName(username);
                security.setPassword(password);
                lds.setSecurity(security);
                PoolType pool = new PoolType();
                pool.setMinPoolSize(5L);
                pool.setMaxPoolSize(20L);
                lds.setPool(pool);

                datasources.addDatasource(lds);

                return new WildflyDatasource(rawName, url, username, password, driver);
            }
        });

        return ds;
    }

    /**
     * Perform datasources graph changes defined by the jbossWeb modifier.
     * Update editor content and save changes, if appropriate.
     *
     * @param modifier
     */
    private WildflyDatasource modifyDSResource(DSResourceModifier modifier)
            throws ConfigurationException, DatasourceAlreadyExistsException {

        WildflyDatasource ds = null;

        try {
            ensureResourceDirExists();
            ensureDatasourcesFileExists();

            DataObject datasourcesDO = DataObject.find(datasourcesFO);

            EditorCookie editor = (EditorCookie) datasourcesDO.getCookie(EditorCookie.class);
            StyledDocument doc = editor.getDocument();
            if (doc == null) {
                doc = editor.openDocument();
            }

            Datasources newDatasources = null;
            try {  // get the up-to-date model
                // try to create a graph from the editor content
                byte[] docString = doc.getText(0, doc.getLength()).getBytes();
                newDatasources = Datasources.createGraph(new ByteArrayInputStream(docString));
            } catch (RuntimeException e) {
                Datasources oldDatasources = getDatasourcesGraph(true);
                if (oldDatasources == null) {
                    // neither the old graph is parseable, there is not much we can do here
                    // TODO: should we notify the user?
                    throw new ConfigurationException(
                            NbBundle.getMessage(DatasourceSupport.class, "MSG_datasourcesXmlCannotParse", DS_RESOURCE_NAME)); // NOI18N
                }
                // current editor content is not parseable, ask whether to override or not
                NotifyDescriptor notDesc = new NotifyDescriptor.Confirmation(
                        NbBundle.getMessage(DatasourceSupport.class, "MSG_datasourcesXmlNotValid", DS_RESOURCE_NAME), // NOI18N
                        NotifyDescriptor.OK_CANCEL_OPTION);
                Object result = DialogDisplayer.getDefault().notify(notDesc);
                if (result == NotifyDescriptor.CANCEL_OPTION) {
                    // keep the old content
                    return null;
                }
                // use the old graph
                newDatasources = oldDatasources;
            }

            // perform changes
            ds = modifier.modify(newDatasources);

            // save if appropriate
            boolean modified = datasourcesDO.isModified();
            ResourceConfigurationHelper.replaceDocument(doc, newDatasources);
            if (!modified) {
                SaveCookie cookie = (SaveCookie) datasourcesDO.getCookie(SaveCookie.class);
                cookie.save();
            }

            datasources = newDatasources;

        } catch (DataObjectNotFoundException donfe) {
            Exceptions.printStackTrace(donfe);
        } catch (BadLocationException ble) {
            // this should not occur, just log it if it happens
            Exceptions.printStackTrace(ble);
        } catch (IOException ioe) {
            String msg = NbBundle.getMessage(DatasourceSupport.class, "MSG_CannotUpdateFile", datasourcesFile.getAbsolutePath());
            throw new ConfigurationException(msg, ioe);
        }

        return ds;
    }

//---------------------------------------- HELPERS --------------------------------------
    private void ensureResourceDirExists() {
        if (!resourceDir.exists()) {
            resourceDir.mkdir();
        }
    }

    private void ensureDatasourcesFileExists() {
        if (!datasourcesFile.exists()) {
            getDatasourcesGraph(true);
        }
    }

}
