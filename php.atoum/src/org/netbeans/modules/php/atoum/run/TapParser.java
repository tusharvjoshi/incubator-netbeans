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
package org.netbeans.modules.php.atoum.run;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.netbeans.modules.php.spi.testing.run.TestCase;

public final class TapParser {

    private static enum State {
        OK,
        OK_SKIP,
        NOT_OK,
    }


    static final Logger LOGGER = Logger.getLogger(TapParser.class.getName());

    private static final Pattern FILE_LINE_PATTERN = Pattern.compile("(.+):(\\d+)"); // NOI18N
    private static final Pattern SUITE_TEST_PATTERN = Pattern.compile("([^:\\s]+)::([^\\(]+)\\(\\)"); // NOI18N

    private final List<TestSuiteVo> testSuites = new ArrayList<>();
    private final List<String> commentLines = new ArrayList<>();

    private TestSuiteVo testSuite = null;
    private TestCaseVo testCase = null;
    private int testCaseCount = 0;
    private State state = null;


    public TapParser() {
    }

    public static boolean isTestCaseStart(String line) {
        return line.startsWith("ok ") // NOI18N
                || line.startsWith("not ok "); // NOI18N
    }

    public List<TestSuiteVo> parse(String input, long runtime) {
        for (String line : input.split("\\r?\\n|\\r")) { // NOI18N
            parseLine(line.trim());
        }
        processNotOkLines();
        setTimes(runtime);
        return testSuites;
    }

    private void parseLine(String line) {
        if (line.startsWith("1..")) { // NOI18N
            return;
        }
        if (line.startsWith("ok ")) { // NOI18N
            processNotOkLines();
            assert state == null : state;
            if (checkSkipped(line)) {
                state = State.OK_SKIP;
                testCase.setStatus(TestCase.Status.SKIPPED);
            } else {
                state = State.OK;
                testCase = null;
            }
        } else if (line.startsWith("not ok ")) { // NOI18N
            processNotOkLines();
            assert state == null : state;
            state = State.NOT_OK;
            setSuiteTest(line);
            testCase.setStatus(TestCase.Status.FAILED);
            checkTodo(line);
        } else {
            processComment(line);
        }
    }

    private boolean checkSkipped(String line) {
        assert state == null : state;
        if (line.contains("# SKIP ")) { // NOI18N
            setSuiteTest(line);
            return true;
        }
        return false;
    }

    private void checkTodo(String line) {
        assert state == State.NOT_OK : state;
        if (line.contains("# TODO ")) { // NOI18N
            testCase.setStatus(TestCase.Status.PENDING);
        }
    }

    private void processComment(String line) {
        assert line.startsWith("#") : line;
        line = line.substring(1).trim();
        switch (state) {
            case OK:
                setSuiteTest(line);
                testCase.setStatus(TestCase.Status.PASSED);
                state = null;
                break;
            case OK_SKIP:
                commentLines.add(line);
                break;
            case NOT_OK:
                commentLines.add(line);
                break;
            default:
                assert false : "Unknown state: " + state;
        }
    }

    private void processNotOkLines() {
        if (commentLines.isEmpty()) {
            return;
        }
        assert testCase != null;
        // last line
        int lastIndex = commentLines.size() - 1;
        String lastLine = commentLines.get(lastIndex);
        if (setFileLine(lastLine)) {
            commentLines.remove(lastIndex);
        } else {
            // XXX
            // aborted test
            if (lastLine.toLowerCase().endsWith(".php")) { // NOI18N
                // php file
                commentLines.remove(lastIndex);
                testCase.setFile(lastLine);
            }
            if (testCase.getStatus() == TestCase.Status.FAILED) {
                testCase.setStatus(TestCase.Status.ERROR);
            }
        }
        // rest
        StringBuilder message = null;
        List<String> stackTrace = new ArrayList<>();
        while (!commentLines.isEmpty()) {
            String firstLine = commentLines.get(0);
            commentLines.remove(0);
            if (firstLine.equals("Stack trace:")) { // NOI18N
                testCase.setStatus(TestCase.Status.ERROR);
                stackTrace.addAll(processStackTrace(commentLines));
                commentLines.clear();
            } else if (firstLine.equals("-Reference") // NOI18N
                    || firstLine.equals("-Expected")) { // NOI18N
                processDiff(commentLines);
                commentLines.clear();
            } else {
                if (message == null) {
                    message = new StringBuilder(200);
                }
                if (message.length() > 0) {
                    // unfortunately, \n not supported in the ui
                    message.append("; "); // NOI18N
                }
                message.append(firstLine);
            }
        }
        String msg = null;
        if (message != null) {
            msg = message.toString();
            testCase.setMessage(msg);
        }
        // append file with line number
        // XXX remove if once aborted method contains file with line number as well
        if (!lastLine.equals(msg)) {
            stackTrace.add(lastLine);
        }
        testCase.setStackTrace(stackTrace);
        // reset
        state = null;
    }

    private List<String> processStackTrace(List<String> lines) {
        List<String> stackTrace = new ArrayList<>(lines.size());
        for (String line : lines) {
            assert line.startsWith("#") : line;
            stackTrace.add(line.substring(3));
        }
        return stackTrace;
    }

    private void processDiff(List<String> lines) {
        StringBuilder diffExpected = new StringBuilder(200);
        StringBuilder diffActual = new StringBuilder(200);
        boolean diff = false;
        for (String line : lines) {
            if (line.startsWith("@@")) { // NOI18N
                diff = true;
                continue;
            }
            if (!diff) {
                continue;
            }
            if (line.startsWith("+")) { // NOI18N
                if (diffActual.length() > 0) {
                    diffActual.append("\n"); // NOI18N
                }
                diffActual.append(line.substring(1));
            } else if (line.startsWith("-")) {
                if (diffExpected.length() > 0) {
                    diffExpected.append("\n"); // NOI18N
                }
                diffExpected.append(line.substring(1));
            } else {
                // unknown line?
                LOGGER.log(Level.INFO, "Unexpected DIFF line {0}", line);
            }
        }
        testCase.setDiff(new TestCase.Diff(diffExpected.toString(), diffActual.toString()));
    }

    private void setSuiteTest(String line) {
        Matcher matcher = SUITE_TEST_PATTERN.matcher(line);
        boolean found = matcher.find();
        assert found : line;
        String suiteName = matcher.group(1);
        String testName = matcher.group(2);
        if (testSuite == null
                || !testSuite.getName().equals(suiteName)) {
            testSuite = new TestSuiteVo(suiteName);
            testSuites.add(testSuite);
        }
        assert testSuite != null;
        assert suiteName.equals(testSuite.getName()) : testSuite;
        testCase = new TestCaseVo(testName);
        testSuite.addTestCase(testCase);
        testCaseCount++;
    }

    private boolean setFileLine(String line) {
        Matcher matcher = FILE_LINE_PATTERN.matcher(line);
        if (!matcher.matches()) {
            return false;
        }
        assert testCase != null;
        String file = matcher.group(1);
        String fileLine = matcher.group(2);
        assert file != null : line;
        testCase.setFile(file);
        assert fileLine != null : line;
        testCase.setLine(Integer.parseInt(fileLine));
        return true;
    }

    private void setTimes(long runtime) {
        long time = 0;
        if (testCaseCount > 0) {
            time = runtime / testCaseCount;
        }
        for (TestSuiteVo suite : testSuites) {
            for (TestCaseVo kase : suite.getTestCases()) {
                kase.setTime(time);
            }
        }
    }

}
