/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2016 Oracle and/or its affiliates. All rights reserved.
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
package org.netbeans.modules.php.twig.editor.typinghooks;

import javax.swing.text.BadLocationException;
import org.netbeans.editor.BaseDocument;
import org.netbeans.modules.csl.api.EditorOptions;
import org.netbeans.modules.php.twig.editor.gsf.TwigLanguage;

public final class TypingHooksUtils {

    private TypingHooksUtils() {
    }

    public static boolean isInsertMatchingEnabled() {
        EditorOptions options = EditorOptions.get(TwigLanguage.TWIG_MIME_TYPE);
        if (options != null) {
            return options.getMatchBrackets();
        }
        return true;
    }

    public static boolean sameAsExistingChar(BaseDocument doc, char c, int dotPos) throws BadLocationException {
        if (dotPos <= 0 || doc.getLength() <= dotPos) {
            return false;
        }
        char[] nextChars = doc.getChars(dotPos, 1);
        return nextChars.length > 0 && nextChars[0] == c;
    }

    public static boolean isEscapeSequence(BaseDocument doc, int dotPos) throws BadLocationException {
        if (dotPos <= 0) {
            return false;
        }
        char[] previousChars = doc.getChars(dotPos - 1, 1);
        return previousChars.length > 0 && previousChars[0] == '\\';
    }

    public static boolean isOpeningDelimiterChar(char c) {
        return c == '{' || c == '%';
    }

}
