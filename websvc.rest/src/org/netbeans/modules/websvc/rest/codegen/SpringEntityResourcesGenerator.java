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
 * Portions Copyrighted 2008 Sun Microsystems, Inc.
 */
package org.netbeans.modules.websvc.rest.codegen;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.lang.model.element.Modifier;

import org.netbeans.api.java.source.JavaSource;
import org.netbeans.api.java.source.JavaSource.Phase;
import org.netbeans.api.java.source.Task;
import org.netbeans.api.java.source.TreeMaker;
import org.netbeans.api.java.source.WorkingCopy;
import org.netbeans.modules.j2ee.core.api.support.java.GenerationUtils;
import org.netbeans.modules.j2ee.core.api.support.java.JavaIdentifiers;
import org.netbeans.modules.websvc.rest.codegen.model.EntityClassInfo;
import org.netbeans.modules.websvc.rest.codegen.model.EntityClassInfo.FieldInfo;
import org.netbeans.modules.websvc.rest.model.api.RestConstants;
import org.netbeans.modules.websvc.rest.support.JavaSourceHelper;
import org.netbeans.modules.websvc.rest.support.SpringHelper;
import org.openide.filesystems.FileObject;

import com.sun.source.tree.ClassTree;
import com.sun.source.tree.CompilationUnitTree;
import com.sun.source.tree.ModifiersTree;

/**
 *
 * @author ads
 */
public class SpringEntityResourcesGenerator extends EntityResourcesGenerator {
    
    SpringEntityResourcesGenerator( boolean hasAopAlliance ){
        this.hasAopAlliance = hasAopAlliance;
    }

    @Override
    protected void configurePersistence() {
        new SpringHelper(getProject(), getPersistenceUnit()).configure();
    }
    
    @Override
    protected void createFolders() {
        createFolders( false );
    }
    
    /* (non-Javadoc)
     * @see org.netbeans.modules.websvc.rest.codegen.EntityResourcesGenerator#generateInfrastracture(java.util.Set, java.lang.String, org.openide.filesystems.FileObject)
     */
    @Override
    protected boolean generateInfrastracture( Set<FileObject> createdFiles,
            String entityFqn, FileObject facade ) throws IOException
    {
        if ( !super.generateInfrastracture(createdFiles, entityFqn, facade) ){
            return false;
        }
        
        // Inject EntityManager
        JavaSource javaSource = JavaSource.forFileObject( facade );
        if ( javaSource == null ){
            return false;
        }
        Task<WorkingCopy> task = new Task<WorkingCopy>() {
            
            @Override
            public void run(WorkingCopy workingCopy) throws Exception {
                workingCopy.toPhase(Phase.RESOLVED);
                CompilationUnitTree tree = workingCopy.getCompilationUnit();
                
                
                String[] annotations ;
                Object[] values ;
                if ( hasAopAlliance ){
                    annotations = new String[]{Constants.PERSISTENCE_CONTEXT_ANNOTATION};
                    values = new Object[]{JavaSourceHelper.createAssignmentTree(
                            workingCopy, "unitName",                    // NOI18N
                            getPersistenceUnit().getName())};
                }
                else {
                    annotations = new String[]{Constants.PERSISTENCE_CONTEXT_ANNOTATION,
                            "Error"};                                   // NOI18N
                    values = new Object[]{JavaSourceHelper.createAssignmentTree(
                            workingCopy, "unitName",                    // NOI18N
                            getPersistenceUnit().getName()),
                            "Please fix your project manually, for instructions see " +
                            "http://wiki.netbeans.org/SpringWithAopalliance"    // NOI18N
                            };
                }
                
                ClassTree classTree = (ClassTree)tree.getTypeDecls().get(0);
                ClassTree newTree = JavaSourceHelper.addField(workingCopy, classTree, 
                        new Modifier[]{Modifier.PROTECTED},
                        annotations , values , "entityManager", 
                        Constants.ENTITY_MANAGER_TYPE);  //NOI18N
                workingCopy.rewrite(classTree, newTree);
            }
        };
        javaSource.runModificationTask(task).commit();
        return true;
    }
    
    /* (non-Javadoc)
     * @see org.netbeans.modules.websvc.rest.codegen.EntityResourcesGenerator#getRestFacadeMethodOptions(java.lang.String, java.lang.String)
     */
    @Override
    protected List<RestGenerationOptions> getRestFacadeMethodOptions(
            String entityFQN, String idClass )
    {
        List<RestGenerationOptions> original = super.getRestFacadeMethodOptions(
                entityFQN, idClass);
        List<RestGenerationOptions> result = new ArrayList<RestGenerationOptions>(
                original.size() + 1);
        result.addAll( original );
        RestGenerationOptions option = new RestGenerationOptions();
        RestMethod method = new FindMethod();
        option.setRestMethod(method);
        option.setParameterNames(new String[]{"all", "maxResults", "firstResult"});// NOI18N
        option.setParameterTypes(new String[]{"boolean" , "int", "int"});       // NOI18N    
        StringBuilder returnType = new StringBuilder(List.class.getCanonicalName());
        returnType.append('<');
        returnType.append(JavaIdentifiers.unqualify(entityFQN));
        returnType.append('>');
        option.setReturnType(returnType.toString());
        StringBuilder body = new StringBuilder("try { ");                       // NOI18N
        body.append("Query query = entityManager.createQuery(");                // NOI18N
        body.append('"');
        body.append("SELECT object(o) FROM ");                                  // NOI18N
        body.append(getModel().getEntityInfo(entityFQN).getName());
        body.append(" AS o\");");                                               // NOI18N
        body.append("if (!all) {");                                             // NOI18N
        body.append(" query.setMaxResults(maxResults);");                       // NOI18N
        body.append(" query.setFirstResult(firstResult);}");                    // NOI18N
        body.append(" return  query.getResultList();");                         // NOI18N
        body.append("} finally { entityManager.close();}");                     // NOI18N
        option.setBody(body.toString());
        result.add(option);
        return result ;
    }
    
    /* (non-Javadoc)
     * @see org.netbeans.modules.websvc.rest.codegen.EntityResourcesGenerator#addRestMethodAnnotations(org.netbeans.modules.j2ee.core.api.support.java.GenerationUtils, org.netbeans.api.java.source.TreeMaker, org.netbeans.modules.websvc.rest.codegen.RestGenerationOptions, com.sun.source.tree.ModifiersTree)
     */
    @Override
    protected ModifiersTree addRestMethodAnnotations( GenerationUtils genUtils,
            TreeMaker maker, RestGenerationOptions option,
            ModifiersTree modifiers )
    {
        ModifiersTree tree = super.addRestMethodAnnotations(genUtils, maker, 
                option, modifiers);
        if ( option.getRestMethod().getMethod() != null ){
            tree = maker.addModifiersAnnotation(tree, genUtils.createAnnotation(
                    SpringConstants.TRANSACTIONAL));
        }
        return tree;
    }

    /* (non-Javadoc)
     * @see org.netbeans.modules.websvc.rest.codegen.EntityResourcesGenerator#getResourceImports(java.lang.String)
     */
    @Override
    protected List<String> getResourceImports( String entityFqn ) {
        List<String> original = super.getResourceImports(entityFqn);
        List<String> result = new ArrayList<String>( original.size() +1 );
        result.addAll( original );
        result.add("javax.persistence.Query");                  // NOI18N
        result.add(Constants.PERSISTENCE_CONTEXT);
        return result;
    }
    
    /* (non-Javadoc)
     * @see org.netbeans.modules.websvc.rest.codegen.EntityResourcesGenerator#addResourceAnnotation(java.lang.String, com.sun.source.tree.ClassTree, org.netbeans.modules.j2ee.core.api.support.java.GenerationUtils, org.netbeans.api.java.source.TreeMaker)
     */
    @Override
    protected ModifiersTree addResourceAnnotation( String entityFQN,
            ClassTree classTree, GenerationUtils genUtils, TreeMaker maker )
    {
        ModifiersTree tree = super.addResourceAnnotation(entityFQN, classTree, 
                genUtils, maker);
        
        tree = maker.addModifiersAnnotation( tree, genUtils.createAnnotation(
                RestConstants.SINGLETON));
        tree = maker.addModifiersAnnotation( tree, genUtils.createAnnotation(
                SpringConstants.AUTOWIRE));
        return tree;
    }
    
    @Override
    protected RestGenerationOptions getGenerationOptions(
            RestFacadeMethod method, String entityFQN, String paramArg,
            String idType )
    {
        String entitySimpleName = JavaIdentifiers.unqualify(entityFQN);
        RestGenerationOptions options = super.getGenerationOptions(method, 
                entityFQN, paramArg, idType);
        boolean needPathSegment = false;
        EntityClassInfo entityInfo = getModel().getEntityInfo(entityFQN);
        if ( entityInfo!= null ){
            FieldInfo idFieldInfo = entityInfo.getIdFieldInfo();
            needPathSegment = idFieldInfo!= null && idFieldInfo.isEmbeddedId()&& 
                    idFieldInfo.getType()!= null;
        }
        StringBuilder builder ;
        switch ( method ){
            case CREATE:
                builder = new StringBuilder("entityManager.persist(entity);");  // NOI18N
                builder.append("return Response.created(");                     // NOI18N
                builder.append("URI.create(");                                  // NOI18N
                builder.append(getIdFieldToUriStmt(getModel().getEntityInfo(entityFQN).
                        getIdFieldInfo()));        
                builder.append(".toString())).build();");                       // NOI18N
                options.setBody(builder.toString());
                return options;
            case EDIT:
                options.setReturnType("void");                                  // NOI18N
                builder = new StringBuilder("entityManager.merge(entity);");    // NOI18N
                options.setBody(builder.toString());
                return options;
            case REMOVE:
                options.setReturnType("void");                                  // NOI18N
                builder = new StringBuilder();
                if ( needPathSegment ){
                    builder.append(idType);
                    builder.append( " key=getPrimaryKey(id);\n");               // NOI18N
                    paramArg = "key";                                           // NOI18N
                }
                builder.append(entitySimpleName);
                builder.append(" entity = entityManager.getReference(");        // NOI18N
                builder.append(entitySimpleName);
                builder.append(".class, ");                                     // NOI18N
                builder.append(paramArg);
                builder.append(");");                                           // NOI18N
                builder.append("entityManager.remove(entity);");                // NOI18N
                options.setBody(builder.toString());
                return options;
            case FIND:
                builder = new StringBuilder();
                if ( needPathSegment ){
                    builder.append(idType);
                    builder.append( " key=getPrimaryKey(id);\n");               // NOI18N
                    paramArg = "key";                                           // NOI18N
                }
                builder.append("return entityManager.find(");                   // NOI18N
                builder.append(entitySimpleName);   
                builder.append(".class, ");                                     // NOI18N
                builder.append(paramArg);
                builder.append(");");                                           // NOI18N
                options.setBody(builder.toString());
                return options;
            case FIND_ALL:
                options.setBody("return find(true , -1 , -1);");                // NOI18N
                return options;
            case FIND_RANGE:
                options.setBody("return find( false , max, first)");            // NOI18N
                return options;
            case COUNT:
                builder = new StringBuilder("try {");                           // NOI18N
                builder.append("Query query = entityManager.createQuery(");     // NOI18N
                builder.append('"');
                builder.append("SELECT count(o) FROM ");                        // NOI18N
                builder.append(getModel().getEntityInfo( entityFQN).getName());
                builder.append(" AS o\");");                                    // NOI18N
                builder.append("return query.getSingleResult().toString();");   // NOI18N
                builder.append("} finally { entityManager.close(); }");         // NOI18N
                options.setBody( builder.toString());
                return options;
        }
        return null;
    }
    
    private static final class FindMethod implements RestMethod {

        @Override
        public boolean overrides() {
            return false;
        }

        @Override
        public String getUriPath() {
            return null;
        }

        @Override
        public String getMethodName() {
            return "find";                          // NOI18N
        }

        @Override
        public String getMethod() {
            return null;
        }
    }

    private boolean hasAopAlliance;
}
