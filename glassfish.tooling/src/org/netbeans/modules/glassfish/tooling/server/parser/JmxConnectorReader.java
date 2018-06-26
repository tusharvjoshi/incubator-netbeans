/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2015, 2016 Oracle and/or its affiliates. All rights reserved.
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
 */
package org.netbeans.modules.glassfish.tooling.server.parser;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import org.netbeans.modules.glassfish.tooling.logging.Logger;
import org.netbeans.modules.glassfish.tooling.server.parser.TreeParser.Path;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 * Reader for jmx connector port number as string from domain.xml.
 * <p/>
 * @author Peter Benedikovic, Tomas Kraus
 */
public class JmxConnectorReader extends TargetConfigReader implements XMLReader {

    ////////////////////////////////////////////////////////////////////////////
    // Class attributes                                                       //
    ////////////////////////////////////////////////////////////////////////////

    /** Logger instance for this class. */
    private static final Logger LOGGER = new Logger(JmxConnectorReader.class);

    public static final String DEFAULT_PATH =
            "/domain/configs/config/admin-service/jmx-connector";

    private String path;

    private String result;

    public JmxConnectorReader(String path, String targetConfigName) {
        super(targetConfigName);
        this.path = path;
    }
    
    public JmxConnectorReader(String targetConfigName) {
        this(DEFAULT_PATH, targetConfigName);
    }

    @Override
    public void readAttributes(String qname, Attributes attributes)
            throws SAXException {
        final String METHOD = "getServerConfig";
        /*
         <admin-service type="das-and-server" system-jmx-connector-name="system">
         <jmx-connector ..... port="8686" />
         */
        if (readData) {
            String jmxAttr = attributes.getValue("port");
            try {
                int port = Integer.parseInt(jmxAttr);
                result = "" + port;	//$NON-NLS-1$
                LOGGER.log(Level.INFO, METHOD, "port", result);
            } catch (NumberFormatException ex) {
                LOGGER.log(Level.SEVERE, METHOD, "error", ex);
            }
        }

    }

    @Override
    public List<TreeParser.Path> getPathsToListen() {
        LinkedList<TreeParser.Path> paths = new LinkedList<TreeParser.Path>();
        paths.add(new Path(path, this));
        paths.add(new Path(CONFIG_PATH, new TargetConfigMarker()));
        return paths;
    }

    public String getResult() {
        return result;
    }
}
