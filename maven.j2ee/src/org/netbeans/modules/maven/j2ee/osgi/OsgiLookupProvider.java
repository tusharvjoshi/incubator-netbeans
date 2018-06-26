/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 2011 Oracle and/or its affiliates. All rights reserved.
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
 * Portions Copyrighted 2011 Sun Microsystems, Inc.
 */
package org.netbeans.modules.maven.j2ee.osgi;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.netbeans.api.project.Project;
import org.netbeans.modules.j2ee.persistence.spi.entitymanagergenerator.EntityManagerGenerationStrategyResolverFactory;
import org.netbeans.modules.javaee.project.api.PersistenceProviderSupplierImpl;
import org.netbeans.modules.javaee.project.spi.JavaEEProjectSettingsImplementation;
import org.netbeans.modules.j2ee.persistence.spi.entitymanagergenerator.EntityManagerGenerationStrategyResolver;
import org.netbeans.modules.j2ee.persistence.spi.provider.PersistenceProviderSupplier;
import org.netbeans.modules.maven.api.NbMavenProject;
import org.netbeans.modules.maven.j2ee.CopyOnSave;
import org.netbeans.modules.maven.j2ee.JPAStuffImpl;
import org.netbeans.modules.maven.j2ee.JavaEEProjectSettingsImpl;
import org.netbeans.modules.maven.j2ee.JsfSupportHandleImpl;
import org.netbeans.modules.maven.j2ee.utils.MavenProjectSupport;
import org.netbeans.modules.maven.j2ee.web.EntRefContainerImpl;
import org.netbeans.modules.maven.j2ee.web.WebProjectWebRootProvider;
import org.netbeans.modules.maven.j2ee.web.WebCopyOnSave;
import org.netbeans.modules.maven.j2ee.web.WebModuleProviderImpl;
import org.netbeans.modules.maven.j2ee.web.WebReplaceTokenProvider;
import org.netbeans.modules.web.jsfapi.spi.JsfSupportHandle;
import org.netbeans.spi.project.LookupProvider;
import org.openide.util.Lookup;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;

/**
 * Provide additional lookup objects for bundle (OSGI) type (see #179584 for more detail).
 * @author Martin Janicek
 */
@LookupProvider.Registration(projectType = {"org-netbeans-modules-maven/" + NbMavenProject.TYPE_OSGI})
public class OsgiLookupProvider implements LookupProvider, PropertyChangeListener {

    // More logging for issue: #216942
    private static final Logger LOGGER = Logger.getLogger(OsgiLookupProvider.class.getName());
    private StackTraceElement[] stackTrace;

    private Project project;
    private InstanceContent ic;

    private PersistenceProviderSupplier persistenceProviderSupplier;
    private WebProjectWebRootProvider mavenWebProjectWebRootProvider;
    private WebReplaceTokenProvider webReplaceTokenProvider;
    private EntRefContainerImpl entRefContainerImpl;
    private EntityManagerGenerationStrategyResolver eMGSResolverImpl;
    private JsfSupportHandle jsfSupportHandle;
    private WebModuleProviderImpl provider;
    private JPAStuffImpl jPAStuffImpl;
    private CopyOnSave copyOnSave;
    private JavaEEProjectSettingsImplementation javaEEProjectSettingsImpl;

    @Override
    public synchronized Lookup createAdditionalLookup(Lookup baseLookup) {
        project = baseLookup.lookup(Project.class);
        ic = new InstanceContent();

        persistenceProviderSupplier = new PersistenceProviderSupplierImpl(project);
        mavenWebProjectWebRootProvider = new WebProjectWebRootProvider(project);
        webReplaceTokenProvider = new WebReplaceTokenProvider(project);
        entRefContainerImpl = new EntRefContainerImpl(project);
        eMGSResolverImpl = EntityManagerGenerationStrategyResolverFactory.createInstance(project);
        jsfSupportHandle = new JsfSupportHandleImpl(project);
        jPAStuffImpl = new JPAStuffImpl(project);
        copyOnSave = new WebCopyOnSave(project);
        provider = new WebModuleProviderImpl(project);
        javaEEProjectSettingsImpl = new JavaEEProjectSettingsImpl(project);

        addLookupInstances();
        NbMavenProject.addPropertyChangeListener(project, this);

        if (stackTrace == null) {
            // Save the stackTrace for the first access
            stackTrace = Thread.currentThread().getStackTrace();
        } else {
            // If the second access occurs, log it (it most probably will lead to the ISA - see #216942)
            LOGGER.log(Level.WARNING, "When the first InstanceContent was created, the StackTrace was: \n\n");
            logStackTrace(stackTrace);

            LOGGER.log(Level.WARNING, "When the second InstanceContent was created, the StackTrace was: \n\n");
            logStackTrace(Thread.currentThread().getStackTrace());
        }

        return new AbstractLookup(ic);
    }

    private void logStackTrace(StackTraceElement[] stackTraceElements) {
        for (StackTraceElement element : stackTraceElements) {
            LOGGER.log(Level.WARNING, "Line: {2}, ClassName.methodName: {0}.{1}\n", new Object[] {element.getClassName(), element.getMethodName(), element.getLineNumber()});
        }
    }

    @Override
    public synchronized void propertyChange(PropertyChangeEvent propertyChangeEvent) {
        if (NbMavenProject.PROP_PROJECT.equals(propertyChangeEvent.getPropertyName())) {
            changeAdditionalLookups();
        }
    }

    /*
     * At the moment there is no way to conditionaly register some objects using @ProjectServiceProvider annotation.
     * Because of issue #179584 we want to provide some Java EE instances only in certain situations for OSGI type.
     * So the only way to do so (for now) is to add these instances manualy when changing packaging type to bundle
     * type - which is the reason why this weird method is here.
     *
     * In the future this should be removed and replaced by some kind of multiple @PSP registrator which allows to
     * combine more than one packaging type and merges registrated lookup instances
     */
    private synchronized void changeAdditionalLookups() {
        removeLookupInstances();
        addLookupInstances();
    }

    private synchronized void removeLookupInstances() {
        ic.remove(javaEEProjectSettingsImpl);
        ic.remove(persistenceProviderSupplier);
        ic.remove(mavenWebProjectWebRootProvider);
        ic.remove(webReplaceTokenProvider);
        ic.remove(entRefContainerImpl);
        ic.remove(eMGSResolverImpl);
        ic.remove(jsfSupportHandle);
        ic.remove(jPAStuffImpl);
        ic.remove(provider);

        if (copyOnSave != null) {
            copyOnSave.cleanup();
            ic.remove(copyOnSave);
        }
    }

    private synchronized void addLookupInstances() {
        String packaging = project.getLookup().lookup(NbMavenProject.class).getPackagingType();

        if (MavenProjectSupport.isBundlePackaging(project, packaging)) {
            copyOnSave.initialize();

            ic.add(copyOnSave);
            ic.add(provider);
            ic.add(webReplaceTokenProvider);
            ic.add(entRefContainerImpl);
            ic.add(jsfSupportHandle);
            ic.add(jPAStuffImpl);
            ic.add(eMGSResolverImpl);
            ic.add(persistenceProviderSupplier);
            ic.add(mavenWebProjectWebRootProvider);
            ic.add(javaEEProjectSettingsImpl);
        }
    }
}
