/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 2013 Oracle and/or its affiliates. All rights reserved.
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
 * Portions Copyrighted 2013 Sun Microsystems, Inc.
 */
package org.netbeans.modules.glassfish.javaee.db;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.netbeans.modules.glassfish.tooling.TaskState;
import org.netbeans.modules.glassfish.tooling.admin.CommandGetProperty;
import org.netbeans.modules.glassfish.tooling.admin.ResultMap;
import org.netbeans.modules.glassfish.tooling.data.GlassFishServer;
import org.netbeans.modules.glassfish.tooling.data.GlassFishVersion;
import org.netbeans.modules.glassfish.tooling.utils.StringPrefixTree;
import org.netbeans.modules.j2ee.deployment.common.api.Datasource;

/**
 * Reads data sources from GlassFish server.
 * <p/>
 * @author Tomas Kraus
 */
public class DataSourcesReader {

    ////////////////////////////////////////////////////////////////////////////
    // Inner classes                                                          //
    ////////////////////////////////////////////////////////////////////////////

    /**
     * GlassFish server <code>resource</code> property type.
     */
    private static enum ResourceType {
        /** Unknown resource type. */
        UNKNOWN,

        /** JDBC connection pool. */
        CONNECTION_POOL,

        /** JDBC data source. */
        DATA_SOURCE;

        /** A <code>String</code> representation of UNKNOWN value. */
        private static final String UNKNOWN_STR = "UNKNOWN";

        /** A <code>String</code> representation of CONNECTION_POOL value. */
        private static final String CONNECTION_POOL_STR
                = "jdbc-connection-pool";

        /** A <code>String</code> representation of DATA_SOURCE value. */
        private static final String DATA_SOURCE_STR = "jdbc-resource";

        /** Stored <code>String</code> values for backward <code>String</code>
         *  conversion. */
        private static final StringPrefixTree<ResourceType> stringValues
                = new StringPrefixTree<>(false);

        static {
            stringValues.add(CONNECTION_POOL.toString(), CONNECTION_POOL);
            stringValues.add(DATA_SOURCE.toString(), DATA_SOURCE);
        }

        /**
         * Returns a <code>ResourceType</code> with a value represented
         * by the specified <code>String</code>.
         * <p/>
         * The <code>ResourceType</code> returned represents existing value
         * only if specified <code>String</code> matches any <code>String</code>
         * returned by <code>toString</code> method. Otherwise <code>null</code>
         * value is returned.
         * <p>
         * @param name Value containing <code>ResourceType</code>
         *             <code>toString</code> representation.
         * @return <code>ResourceType</code> value represented
         *         by <code>String</code> or <code>null</code> if value
         *         was not recognized.
         */
        public static ResourceType toValue(final String name) {
            if (name != null) {
                ResourceType type = stringValues.match(name.toLowerCase());
                return type != null ? type : UNKNOWN;
            } else {
                return null;
            }
        }

        /**
         * Convert <code>ResourceType</code> value to <code>String</code>.
         * <p/>
         * @return A <code>String</code> representation of the value
         *         of this object.
         */
        @Override
        public String toString() {
            switch (this) {
                case UNKNOWN:         return UNKNOWN_STR;
                case CONNECTION_POOL: return CONNECTION_POOL_STR;
                case DATA_SOURCE:     return DATA_SOURCE_STR;
                // This is unrecheable. Being here means this class does not handle
                // all possible values correctly.
                default:
                    throw new IllegalStateException("Invalid ResourceType value");
            }
        }

    }

    ////////////////////////////////////////////////////////////////////////////
    // Class attributes                                                       //
    ////////////////////////////////////////////////////////////////////////////

    /** GlassFish server JDBC data sources properties search pattern. */
    public static final String DATA_SOURCES_PATTERN = "resources.*";

    /** GlassFish server property key split pattern. */
    public static final String PROPERTY_SPLIT_PATTERN = "\\.";

    /** GlassFish server resource property identifier. */
    public static final String PROPERTY_IDENT = "property";

    /** Default JDBC data source registered in GF. */
    static final String DEFAULT_DATA_SOURCE = "jdbc/__default";

    /** JavaEE 7 new default data source linked to old default data source. */
    static final String DEFAULT_DATA_SOURCE_EE7
            = "java:comp/DefaultDataSource";

    ////////////////////////////////////////////////////////////////////////////
    // Instance attributes                                                    //
    ////////////////////////////////////////////////////////////////////////////

    /** GlassFish server to read data sources from. */
    private final GlassFishServer server;

    ////////////////////////////////////////////////////////////////////////////
    // Constructors                                                           //
    ////////////////////////////////////////////////////////////////////////////

    /**
     * Creates an instance of GlassFish server data sources reader.
     * <p/>
     * @param server GlassFish server to read data sources from.
     */
    public DataSourcesReader(final GlassFishServer server) {
        this.server = server;
    }

    ////////////////////////////////////////////////////////////////////////////
    // Methods                                                                //
    ////////////////////////////////////////////////////////////////////////////

    /**
     * Retrieve data sources from GlassFish server.
     * <p/>
     * @return Data sources from GlassFish server or <code>null</code>
     *         when retrieving of data sources failed.
     */
    public Set<Datasource> getDataSourcesFromServer() {
        Map<String, String> properties = getPropertiesFromServer();
        if (properties != null) {
            Map<String, JDBCConnectionPool> pools = new HashMap<>();
            Map<String, JDBCResource> resources = new HashMap<>();
            buildJDBCContentObjects(properties, pools, resources);
            assignConnectionPoolsToResources(pools, resources);
            // Add Java EE 7 comp/DefaultDataSource data source (since GF 4).
            if (server.getVersion().ordinal()
                    >= GlassFishVersion.GF_4.ordinal()) {
                addNewJavaEE7dataSource(resources);
            }
            return new HashSet<Datasource>(resources.values());
        } else {
            return null;
        }
    }

    /**
     * Build JDBC connection pools and resources content objects
     * from server properties.
     * <p/>
     * @param properties Server <code>resources.*</code> properties map.
     * @param pools      Existing JDBC connection pools map.
     * @param resources  Existing JDBC resources map.
     */
    private void buildJDBCContentObjects(final Map<String, String> properties,
            final Map<String, JDBCConnectionPool> pools,
            final Map<String, JDBCResource> resources) {
        for (Map.Entry<String, String> entry : properties.entrySet()) {
            String key = entry.getKey();
            if (key != null && key.length() > 0) {
                String[] elements = key.split(PROPERTY_SPLIT_PATTERN);
                if (elements.length >= 4) {
                    JDBCConnectionPool.PropertyType propertyType;
                    ResourceType type = ResourceType.toValue(elements[1]);
                    switch (type) {
                        case CONNECTION_POOL:
                            buildJDBCConnectionPool(
                                    pools, elements, entry.getValue());
                            break;
                        case DATA_SOURCE:
                            buildJDBCResource(
                                    resources, elements, entry.getValue());
                            break;
                    }
                }
            }
        }
    }

    /**
     * Assign JDBC connection pools to resources (data sources).
     * <p/>
     * @param pools          Existing JDBC connection pools map.
     * @param resources      Existing JDBC resources map.
     */
    private void assignConnectionPoolsToResources(
            final Map<String, JDBCConnectionPool> pools,
            final Map<String, JDBCResource> resources) {
        for (Map.Entry<String, JDBCResource> entry : resources.entrySet()) {
            JDBCResource resource = entry.getValue();
            if (resource != null && resource.getPoolName() != null) {
                resource.setPool(pools.get(resource.getPoolName()));
            }
        }
    }

    /**
     * Add new Java EE 7 <code>comp/DefaultDataSource</code> data source as old
     * default <code>jdbc/__default</code> data source clone.
     * <p/>
     * Data source will be added only if <code>jdbc/__default</code> data source
     * exists and Java EE 7 new data source is not registered yet. Old data
     * source must be already fully initialized and linked with connection pool.
     * <p/>
     * @param resources Existing JDBC resources map.
     */
    private void addNewJavaEE7dataSource(
            final Map<String, JDBCResource> resources) {
        JDBCResource defaultResource = resources.get(DEFAULT_DATA_SOURCE);
        if (defaultResource != null
                && !resources.containsKey(DEFAULT_DATA_SOURCE_EE7)) {
            resources.put(DEFAULT_DATA_SOURCE_EE7,
                    defaultResource.copy(DEFAULT_DATA_SOURCE_EE7));
        }
    }

    /**
     * Process one step of building <code>Map</code> of JDBC connection pool
     * content objects.
     * <p/>
     * @param pools          Existing JDBC connection pools map.
     * @param elements       GlassFish server JDBC connection pool property key
     *                       elements.
     * @param attributeValue GlassFish server JDBC connection pool property
     *                       value.
     */
    private void buildJDBCConnectionPool(
            final Map<String, JDBCConnectionPool> pools,
            final String[] elements, final String attributeValue) {
        JDBCConnectionPool.PropertyType propertyType = null;
        final String name = elements[2];
        switch(elements.length) {
            case 4:
                propertyType = null;
                break;
            case 5:
                if (PROPERTY_IDENT.equalsIgnoreCase(elements[3])) {
                    propertyType = JDBCConnectionPool
                            .PropertyType.toValue(elements[4]);
                } else {
                    propertyType = null;
                }
                break;
        }
        if (propertyType != null
                && propertyType != JDBCConnectionPool.PropertyType.UNKNOWN) {
            JDBCConnectionPool pool = getJDBCConnectionPool(pools, name);
            pool.setProperty(propertyType, attributeValue);
        }
        
    }

    /**
     * Process one step of building <code>Map</code> of JDBC resource
     * content objects.
     * <p/>
     * @param resources      Existing JDBC resources map.
     * @param elements       GlassFish server JDBC resource property key
     *                       elements.
     * @param attributeValue GlassFish server JDBC resource property value.
     */
     private void buildJDBCResource(final Map<String, JDBCResource> resources,
             final String[] elements, final String attributeValue) {
         JDBCResource.AttrType attrType = null;
         final String name = elements[2];
         if (elements.length == 4) {
             attrType = JDBCResource.AttrType.toValue(elements[3]);
         }
         if (attrType != null && attrType != JDBCResource.AttrType.UNKNOWN) {
             JDBCResource resource = getJDBCResource(resources, name);
             resource.setProperty(attrType, attributeValue);
         }
     }

     /**
     * Get existing JDBC connection pool content object from pools
     * <code>Map</code> or create new one and put it into pools <code>Map</code>
     * when no such object exists.
     * <p/>
     * @param pools Existing JDBC connection pools map.
     * @param name  JDBC connection pool name (<code>Map</code> key).
     * @return JDBC connection pool content object. Never returns
     *         <code>null</code>.
     */
    private JDBCConnectionPool getJDBCConnectionPool(
            final Map<String, JDBCConnectionPool> pools, final String name) {
        JDBCConnectionPool pool = pools.get(name);
        if (pool == null) {
            pool = new JDBCConnectionPool();
            pools.put(name, pool);
        }
        return pool;
    }

    /**
     * Get existing JDBC resources content object from resources
     * <code>Map</code> or create new one and put it into resources
     * <code>Map</code> when no such object exists.
     * <p/>
     * @param pools Existing JDBC resources map.
     * @param name  JDBC resource name (<code>Map</code> key).
     * @return JDBC resource content object. Never returns <code>null</code>.
     */
    private JDBCResource getJDBCResource(
            final Map<String, JDBCResource> resources, final String name) {
        JDBCResource resource = resources.get(name);
        if (resource == null) {
            resource = new JDBCResource();
            resources.put(name, resource);
        }
        return resource;
    }

    /**
     * Retrieve data sources properties from GlassFish server.
     * <p/>
     * @return Data sources properties from GlassFish server
     *         or <code>null</code> when retrieving using server
     *         <code>get</code> administration command failed.
     */
    private Map<String, String> getPropertiesFromServer() {
        ResultMap<String, String> result = CommandGetProperty
                .getProperties(server, DATA_SOURCES_PATTERN);
        if (result != null && result.getState() == TaskState.COMPLETED) {
            return result.getValue();
        } else {
            return null;
        }
    }

}
