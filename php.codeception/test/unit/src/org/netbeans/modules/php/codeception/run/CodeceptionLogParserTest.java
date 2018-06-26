/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 2015 Oracle and/or its affiliates. All rights reserved.
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
 * Portions Copyrighted 2015 Sun Microsystems, Inc.
 */
package org.netbeans.modules.php.codeception.run;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import org.netbeans.junit.NbTestCase;
import org.netbeans.modules.php.spi.testing.run.TestCase;

public class CodeceptionLogParserTest extends NbTestCase {

    public CodeceptionLogParserTest(String name) {
        super(name);
    }

    public void testParseLogWithOneCodeceptionSuite() throws Exception {
        Reader reader = createReader("codeception-log-one-codeception-suite.xml");
        TestSessionVo testSession = new TestSessionVo();

        CodeceptionLogParser.parse(reader, testSession);
        assertEquals(4, testSession.getTime());
        assertEquals(1, testSession.getTests());

        // test suites & test cases
        assertEquals(1, testSession.getTestSuites().size());

        // 1st
        TestSuiteVo testSuite = testSession.getTestSuites().get(0);
        assertEquals("unit", testSuite.getName());
        assertEquals(null, testSuite.getLocation());
        assertEquals(4, testSuite.getTime());
        assertEquals(1, testSuite.getTestCases().size());

        TestCaseVo testCase = testSuite.getTestCases().get(0);
        assertEquals("App\\FizzBuzzTest", testCase.getClassName());
        assertEquals("testExec", testCase.getName());
        assertEquals("/home/junichi11/NetBeansProjects/codeception/tests/unit/App/FizzBuzzTest.php", testCase.getFile());
        assertEquals(-1, testCase.getLine());
        assertEquals(4, testCase.getTime());
    }

    public void testParseLogWithOnePhpUnitSuite() throws Exception {
        Reader reader = createReader("codeception-log-one-phpunit-suite.xml");
        TestSessionVo testSession = new TestSessionVo();

        CodeceptionLogParser.parse(reader, testSession);
        assertEquals(5, testSession.getTime());
        assertEquals(1, testSession.getTests());

        // test suites & test cases
        assertEquals(1, testSession.getTestSuites().size());

        // 1st
        TestSuiteVo testSuite = testSession.getTestSuites().get(0);
        assertEquals("unit", testSuite.getName());
        assertEquals(null, testSuite.getLocation());
        assertEquals(5, testSuite.getTime());
        assertEquals(1, testSuite.getTestCases().size());

        TestCaseVo testCase = testSuite.getTestCases().get(0);
        assertEquals("App\\FizzBuzzPhpUnitTest", testCase.getClassName());
        assertEquals("testExec", testCase.getName());
        assertEquals("/home/junichi11/NetBeansProjects/codeception/tests/unit/App/FizzBuzzPhpUnitTest.php", testCase.getFile());
        assertEquals(17, testCase.getLine());
        assertEquals(5, testCase.getTime());
    }

    public void testParseLogWithMoreSuites() throws Exception {
        Reader reader = createReader("codeception-log-more-suites.xml");
        TestSessionVo testSession = new TestSessionVo();

        CodeceptionLogParser.parse(reader, testSession);

        assertEquals(268 + 175 + 19, testSession.getTime());
        assertEquals(4 + 4 + 1, testSession.getTests());

        assertEquals(3, testSession.getTestSuites().size());

        // 1st
        TestSuiteVo testSuite = testSession.getTestSuites().get(0);
        assertEquals("functional", testSuite.getName());
        assertEquals(268, testSuite.getTime());
        assertEquals(4, testSuite.getTestCases().size());

        TestCaseVo testCase = testSuite.getTestCases().get(0);
        assertEquals("About", testCase.getName());
        assertEquals("/home/junichi11/NetBeansProjects/yii2-codeception/tests/codeception/functional/AboutCept.php", testCase.getFile());
        assertEquals(-1, testCase.getLine());
        assertEquals(28, testCase.getTime());

        // 2nd
        testSuite = testSession.getTestSuites().get(1);
        assertEquals("unit", testSuite.getName());
        assertEquals(175, testSuite.getTime());
        assertEquals(4, testSuite.getTestCases().size());

        testCase = testSuite.getTestCases().get(0);
        assertEquals("testExec", testCase.getName());
        assertEquals("/home/junichi11/NetBeansProjects/codeception/tests/unit/App/FizzBuzz2Test.php", testCase.getFile());
        assertEquals(-1, testCase.getLine());
        assertEquals(56, testCase.getTime());

        testCase = testSuite.getTestCases().get(1);
        assertEquals("testFailure", testCase.getName());
        assertTrue(testCase.isFailure());
        assertFalse(testCase.isError());
        assertEquals(TestCase.Status.FAILED, testCase.getStatus());
        assertEquals(2, testCase.getStackTrace().length);
        assertEquals("Failed asserting that two objects are equal.\n"
                + "--- Expected\n"
                + "+++ Actual\n"
                + "@@ @@\n"
                + " App\\FizzBuzz Object (\n"
                + "     'start' => 0\n"
                + "-    'end' => 200\n"
                + "+    'end' => 300\n"
                + " )",
                testCase.getStackTrace()[0]);
        assertEquals("/home/junichi11/NetBeansProjects/codeception/tests/unit/App/FizzBuzz2Test.php:33", testCase.getStackTrace()[1]);

        testCase = testSuite.getTestCases().get(2);
        assertEquals("testFailure2", testCase.getName());
        assertTrue(testCase.isFailure());
        assertFalse(testCase.isError());
        assertEquals(TestCase.Status.FAILED, testCase.getStatus());
        assertEquals(2, testCase.getStackTrace().length);
        assertEquals("Failed asserting that 2 matches expected 1.", testCase.getStackTrace()[0]);
        assertEquals("/home/junichi11/NetBeansProjects/codeception/tests/unit/App/FizzBuzz2Test.php:37", testCase.getStackTrace()[1]);

        testCase = testSuite.getTestCases().get(3);
        assertEquals("testError", testCase.getName());
        assertTrue(testCase.isError());
        assertFalse(testCase.isFailure());
        assertEquals(TestCase.Status.ERROR, testCase.getStatus());
        assertEquals(2, testCase.getStackTrace().length);
        assertEquals("Exception: my exception", testCase.getStackTrace()[0]);
        assertEquals("/home/junichi11/NetBeansProjects/codeception/tests/unit/App/FizzBuzz2Test.php:42", testCase.getStackTrace()[1]);

        // 3rd
        testSuite = testSession.getTestSuites().get(2);
        assertEquals("acceptance", testSuite.getName());
        assertEquals(19, testSuite.getTime());
        assertEquals(1, testSuite.getTestCases().size());

        testCase = testSuite.getTestCases().get(0);
        assertEquals("Welcome", testCase.getName());
        assertTrue(testCase.isFailure());
        assertFalse(testCase.isError());
        assertEquals(TestCase.Status.FAILED, testCase.getStatus());
        assertEquals(3, testCase.getStackTrace().length);
        assertEquals("Failed asserting that   <bold>/</bold>\n"
                + "--> <info><!DOCTYPE html>\n"
                + "<html>\n"
                + "  <head>\n"
                + "    <meta charset=\"UTF-8\">\n"
                + "    <title>Home</title>\n"
                + "  </head>\n"
                + "  <body>\n"
                + "        </body>\n"
                + "</html>\n"
                + "</info>\n"
                + "--> contains \"welcome\".",
                testCase.getStackTrace()[0]);
        assertEquals("/home/junichi11/NetBeansProjects/codeception/tests/_support/_generated/AcceptanceTesterActions.php:257", testCase.getStackTrace()[1]);
        assertEquals("/home/junichi11/NetBeansProjects/codeception/tests/acceptance/WelcomeCept.php:6", testCase.getStackTrace()[2]);
    }

    private Reader createReader(String filename) throws FileNotFoundException {
        return new BufferedReader(new FileReader(new File(getDataDir(), filename)));
    }

}
