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
 * Portions Copyrighted 2010 Sun Microsystems, Inc.
 */

package org.netbeans.modules.web.el.completion;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import javax.swing.ImageIcon;
import org.netbeans.modules.csl.api.Documentation;
import org.netbeans.modules.csl.api.ElementHandle;
import org.netbeans.modules.csl.api.ElementKind;
import org.netbeans.modules.csl.api.HtmlFormatter;
import org.netbeans.modules.csl.api.Modifier;
import org.netbeans.modules.csl.api.OffsetRange;
import org.netbeans.modules.csl.spi.DefaultCompletionProposal;
import org.netbeans.modules.csl.spi.ParserResult;
import org.openide.filesystems.FileObject;
import org.openide.util.ImageUtilities;

/**
 * Code completion item for EL functions.
 *
 * @author Erno Mononen
 * @author Martin Fousek
 */
final class ELFunctionCompletionItem extends DefaultCompletionProposal {

    private static final String ICON_PATH = "org/netbeans/modules/web/el/completion/resources/function_16.png"; //NOI18N

    private final String name;
    private final String returnType;
    private final List<String> parameters;
    private final String description;

    public ELFunctionCompletionItem(String name, String returnType, List<String> parameters, String description) {
        this.name = name;
        this.returnType = returnType;
        this.parameters = parameters;
        this.description = description;
    }

    @Override
    public ElementHandle getElement() {
        return new FunctionElementHandle();
    }

    @Override
    public ElementKind getKind() {
        return ElementKind.METHOD;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<String> getInsertParams() {
        return parameters;
    }

    @Override
    public String[] getParamListDelimiters() {
        return new String[]{"(", ")"}; //NOI18N
    }

    @Override
    public String getRhsHtml(HtmlFormatter formatter) {
        return returnType;
    }

    @Override
    public ImageIcon getIcon() {
        return ImageUtilities.loadImageIcon(ICON_PATH, false);
    }

    private class FunctionElementHandle extends ELElementHandle {

        @Override
        Documentation document(ParserResult info, Callable<Boolean> cancel) {
            // in case the description of the EL function was not found
            if (description == null) {
                return null;
            }

            return Documentation.create(description);
        }

        @Override
        public FileObject getFileObject() {
            return null;
        }

        @Override
        public String getMimeType() {
            return null;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public String getIn() {
            return null;
        }

        @Override
        public ElementKind getKind() {
            return ElementKind.METHOD;
        }

        @Override
        public Set<Modifier> getModifiers() {
            return Collections.emptySet();
        }

        @Override
        public boolean signatureEquals(ElementHandle handle) {
            return false;
        }

        @Override
        public OffsetRange getOffsetRange(ParserResult result) {
            return OffsetRange.NONE;
        }

    }

}
