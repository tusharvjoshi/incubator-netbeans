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
package org.netbeans.modules.glassfish.tooling.admin;

import java.util.logging.Level;
import org.netbeans.modules.glassfish.tooling.data.GlassFishServer;
import org.netbeans.modules.glassfish.tooling.logging.Logger;
import org.netbeans.modules.glassfish.tooling.utils.Utils;

/**
 * GlassFish instance and cluster administration command with
 * <code>DEFAULT=&lt;target&gt;</code> query execution using HTTP interface.
 * <p/>
 * Contains common code for commands that are called with
 * <code>DEFAULT=&lt;target&gt;</code> query string. Individual child classes
 * are not needed at this stage.
 * Class implements GlassFish server administration functionality trough HTTP
 * interface.
 * <p/>
 * @author Tomas Kraus, Peter Benedikovic
 */
public class RunnerHttpCreateInstance extends RunnerHttp {

    ////////////////////////////////////////////////////////////////////////////
    // Class attributes                                                       //
    ////////////////////////////////////////////////////////////////////////////

    /** Logger instance for this class. */
    private static final Logger LOGGER
            = new Logger(RunnerHttpCreateInstance.class);

    /** Start/Stop command <code>DEFAULT</code> param name. */
    private static final String DEFAULT_PARAM = "DEFAULT";

    /** Start/Stop command <code>node</code> param name. */
    private static final String NODE_PARAM = "node";

    /** Start/Stop command <code>cluster</code> param name. */
    private static final String CLUSTER_PARAM = "cluster";

    ////////////////////////////////////////////////////////////////////////////
    // Static methods                                                         //
    ////////////////////////////////////////////////////////////////////////////

    /**
     * Builds enable/disable query string for given command.
     * <p/>
     * <code>QUERY :: "DEFAULT" '=' &lt;name&gt; '&' "node" '=' &lt;node&gt;
     *                ['&' "cluster" '=' &lt;cluster&gt; ]</code>
     * <p/>
     * @param command GlassFish Server Admin Command Entity.
     *                <code>CommandDisable</code> instance is expected.
     * @return Enable/Disable query string for given command.
     */
    private static String query(Command command) {
        final String METHOD = "query";
        String name;
        String cluster;
        String node;
        if (command instanceof CommandCreateInstance) {
            cluster = Utils.sanitizeName(
                    ((CommandCreateInstance)command).target);
            if (((CommandTargetName)command).name == null
                    || ((CommandCreateInstance)command).node == null) {
                throw new CommandException(LOGGER.excMsg(METHOD, "nullValue"));
            }
            name = Utils.sanitizeName(((CommandCreateInstance)command).name);
            node = Utils.sanitizeName(((CommandCreateInstance)command).node);
        }
        else {
            throw new CommandException(
                    LOGGER.excMsg(METHOD, "illegalInstance"));
        }
        StringBuilder sb = new StringBuilder(
                DEFAULT_PARAM.length() + 1 + name.length()
                + 1 + NODE_PARAM.length() + 1 + node.length() + (
                    cluster != null
                        ? 1 + CLUSTER_PARAM.length() + 1 + cluster.length()
                        : 0
                )                );
        sb.append(DEFAULT_PARAM).append(PARAM_ASSIGN_VALUE).append(name);
        sb.append(PARAM_SEPARATOR);
        sb.append(NODE_PARAM).append(PARAM_ASSIGN_VALUE).append(node);
        if (cluster != null) {
            sb.append(PARAM_SEPARATOR);
            sb.append(CLUSTER_PARAM).append(PARAM_ASSIGN_VALUE).append(cluster);
        }
        return sb.toString();
    }

    ////////////////////////////////////////////////////////////////////////////
    // Constructors                                                           //
    ////////////////////////////////////////////////////////////////////////////

    /**
     * Constructs an instance of administration command executor using
     * HTTP interface.
     * <p/>
     * @param server  GlassFish server entity object.
     * @param command GlassFish server administration command entity.
     */
    public RunnerHttpCreateInstance(final GlassFishServer server,
            final Command command) {
        super(server, command, query(command));
    }

    ////////////////////////////////////////////////////////////////////////////
    // Implemented Abstract Methods                                           //
    ////////////////////////////////////////////////////////////////////////////

    /**
     * Extracts result value from internal <code>Manifest</code> object.
     * Value of <i>message</i> attribute in <code>Manifest</code> object is
     * stored as <i>value</i> into <code>ResultString</code> result object.
     * <p/>
     * @return true if result was extracted correctly. <code>null</code>
     *         <i>message</i>value is considered as failure.
     */
    @Override
    protected boolean processResponse() {
        final String METHOD = "processResponse";
        try {
            result.value = manifest.getMainAttributes().getValue("message");
            result.value = result.value.replace("%%%EOL%%%", "\n");
        } catch (IllegalArgumentException iae) {
            LOGGER.log(Level.WARNING, METHOD, "illegalArgument", iae);
        }
        return result.value != null;
    }

}
