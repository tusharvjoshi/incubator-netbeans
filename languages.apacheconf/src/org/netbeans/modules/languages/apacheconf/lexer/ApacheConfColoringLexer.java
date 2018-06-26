/* The following code was generated by JFlex 1.4.3 on 1.6.16 7:29 */

/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 2016 Oracle and/or its affiliates. All rights reserved.
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
 * Portions Copyrighted 2016 Sun Microsystems, Inc.
 */

package org.netbeans.modules.languages.apacheconf.lexer;

import org.netbeans.spi.lexer.LexerInput;
import org.netbeans.spi.lexer.LexerRestartInfo;
import org.netbeans.modules.web.common.api.ByteStack;

@org.netbeans.api.annotations.common.SuppressWarnings({"SF_SWITCH_FALLTHROUGH", "URF_UNREAD_FIELD", "DLS_DEAD_LOCAL_STORE", "DM_DEFAULT_ENCODING"})

/**
 * This class is a scanner generated by
 * <a href="http://www.jflex.de/">JFlex</a> 1.4.3
 * on 1.6.16 7:29 from the specification file
 * <tt>/home/gapon/worx/sun/nb-main/languages.apacheconf/tools/ApacheConfColoringLexer.flex</tt>
 */
public class ApacheConfColoringLexer {

  /** This character denotes the end of file */
  public static final int YYEOF = LexerInput.EOF;

  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int ST_HIGHLIGHTING_ERROR = 2;
  public static final int ST_IN_OPEN_TAG = 4;
  public static final int YYINITIAL = 0;
  public static final int ST_IN_DIRECTIVE = 6;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = {
     0,  0,  1,  1,  2,  2,  3, 3
  };

  /**
   * Translates characters to character classes
   */
  private static final String ZZ_CMAP_PACKED =
    "\11\0\1\3\1\5\2\0\1\4\22\0\1\3\1\0\1\20\1\23"+
    "\1\0\1\24\1\0\1\22\2\16\1\0\1\17\1\31\1\7\1\15"+
    "\1\34\1\6\7\11\2\10\2\0\1\33\1\31\1\35\1\0\1\16"+
    "\4\13\1\14\1\13\21\30\1\12\2\30\1\27\1\21\1\32\1\0"+
    "\1\1\1\0\4\13\1\14\1\13\21\30\1\12\2\30\1\25\1\0"+
    "\1\26\1\0\53\1\1\30\12\1\1\30\4\0\1\30\5\0\27\30"+
    "\1\0\37\30\1\0\u01ca\30\4\0\14\30\16\0\5\30\7\0\1\30"+
    "\1\0\1\30\201\0\5\30\1\0\2\30\2\0\4\30\10\0\1\30"+
    "\1\0\3\30\1\0\1\30\1\0\24\30\1\0\123\30\1\0\213\30"+
    "\10\0\236\30\11\0\46\30\2\0\1\30\7\0\47\30\110\0\33\30"+
    "\5\0\3\30\55\0\53\30\25\0\12\2\4\0\2\30\1\0\143\30"+
    "\1\0\1\30\17\0\2\30\7\0\2\30\12\2\3\30\2\0\1\30"+
    "\20\0\1\30\1\0\36\30\35\0\131\30\13\0\1\30\16\0\12\2"+
    "\41\30\11\0\2\30\4\0\1\30\5\0\26\30\4\0\1\30\11\0"+
    "\1\30\3\0\1\30\27\0\31\30\107\0\1\30\1\0\13\30\127\0"+
    "\66\30\3\0\1\30\22\0\1\30\7\0\12\30\4\0\12\2\1\0"+
    "\7\30\1\0\7\30\5\0\10\30\2\0\2\30\2\0\26\30\1\0"+
    "\7\30\1\0\1\30\3\0\4\30\3\0\1\30\20\0\1\30\15\0"+
    "\2\30\1\0\3\30\4\0\12\2\2\30\23\0\6\30\4\0\2\30"+
    "\2\0\26\30\1\0\7\30\1\0\2\30\1\0\2\30\1\0\2\30"+
    "\37\0\4\30\1\0\1\30\7\0\12\2\2\0\3\30\20\0\11\30"+
    "\1\0\3\30\1\0\26\30\1\0\7\30\1\0\2\30\1\0\5\30"+
    "\3\0\1\30\22\0\1\30\17\0\2\30\4\0\12\2\25\0\10\30"+
    "\2\0\2\30\2\0\26\30\1\0\7\30\1\0\2\30\1\0\5\30"+
    "\3\0\1\30\36\0\2\30\1\0\3\30\4\0\12\2\1\0\1\30"+
    "\21\0\1\30\1\0\6\30\3\0\3\30\1\0\4\30\3\0\2\30"+
    "\1\0\1\30\1\0\2\30\3\0\2\30\3\0\3\30\3\0\14\30"+
    "\26\0\1\30\25\0\12\2\25\0\10\30\1\0\3\30\1\0\27\30"+
    "\1\0\12\30\1\0\5\30\3\0\1\30\32\0\2\30\6\0\2\30"+
    "\4\0\12\2\25\0\10\30\1\0\3\30\1\0\27\30\1\0\12\30"+
    "\1\0\5\30\3\0\1\30\40\0\1\30\1\0\2\30\4\0\12\2"+
    "\1\0\2\30\22\0\10\30\1\0\3\30\1\0\51\30\2\0\1\30"+
    "\20\0\1\30\21\0\2\30\4\0\12\2\12\0\6\30\5\0\22\30"+
    "\3\0\30\30\1\0\11\30\1\0\1\30\2\0\7\30\72\0\60\30"+
    "\1\0\2\30\14\0\7\30\11\0\12\2\47\0\2\30\1\0\1\30"+
    "\2\0\2\30\1\0\1\30\2\0\1\30\6\0\4\30\1\0\7\30"+
    "\1\0\3\30\1\0\1\30\1\0\1\30\2\0\2\30\1\0\4\30"+
    "\1\0\2\30\11\0\1\30\2\0\5\30\1\0\1\30\11\0\12\2"+
    "\2\0\4\30\40\0\1\30\37\0\12\2\26\0\10\30\1\0\44\30"+
    "\33\0\5\30\163\0\53\30\24\0\1\30\12\2\6\0\6\30\4\0"+
    "\4\30\3\0\1\30\3\0\2\30\7\0\3\30\4\0\15\30\14\0"+
    "\1\30\1\0\12\2\6\0\46\30\1\0\1\30\5\0\1\30\2\0"+
    "\53\30\1\0\u014d\30\1\0\4\30\2\0\7\30\1\0\1\30\1\0"+
    "\4\30\2\0\51\30\1\0\4\30\2\0\41\30\1\0\4\30\2\0"+
    "\7\30\1\0\1\30\1\0\4\30\2\0\17\30\1\0\71\30\1\0"+
    "\4\30\2\0\103\30\45\0\20\30\20\0\125\30\14\0\u026c\30\2\0"+
    "\21\30\1\0\32\30\5\0\113\30\25\0\15\30\1\0\4\30\16\0"+
    "\22\30\16\0\22\30\16\0\15\30\1\0\3\30\17\0\64\30\43\0"+
    "\1\30\4\0\1\30\3\0\12\2\46\0\12\2\6\0\130\30\10\0"+
    "\51\30\1\0\1\30\5\0\106\30\12\0\35\30\51\0\12\2\36\30"+
    "\2\0\5\30\13\0\54\30\25\0\7\30\10\0\12\2\46\0\27\30"+
    "\11\0\65\30\53\0\12\2\6\0\12\2\15\0\1\30\135\0\57\30"+
    "\21\0\7\30\4\0\12\2\51\0\36\30\15\0\2\30\12\2\54\30"+
    "\32\0\44\30\34\0\12\2\3\0\3\30\12\2\44\30\153\0\4\30"+
    "\1\0\4\30\3\0\2\30\11\0\300\30\100\0\u0116\30\2\0\6\30"+
    "\2\0\46\30\2\0\6\30\2\0\10\30\1\0\1\30\1\0\1\30"+
    "\1\0\1\30\1\0\37\30\2\0\65\30\1\0\7\30\1\0\1\30"+
    "\3\0\3\30\1\0\7\30\3\0\4\30\2\0\6\30\4\0\15\30"+
    "\5\0\3\30\1\0\7\30\164\0\1\30\15\0\1\30\20\0\15\30"+
    "\145\0\1\30\4\0\1\30\2\0\12\30\1\0\1\30\3\0\5\30"+
    "\6\0\1\30\1\0\1\30\1\0\1\30\1\0\4\30\1\0\13\30"+
    "\2\0\4\30\5\0\5\30\4\0\1\30\64\0\2\30\u0a7b\0\57\30"+
    "\1\0\57\30\1\0\205\30\6\0\4\30\3\0\2\30\14\0\46\30"+
    "\1\0\1\30\5\0\1\30\2\0\70\30\7\0\1\30\20\0\27\30"+
    "\11\0\7\30\1\0\7\30\1\0\7\30\1\0\7\30\1\0\7\30"+
    "\1\0\7\30\1\0\7\30\1\0\7\30\120\0\1\30\u01d5\0\2\30"+
    "\52\0\5\30\5\0\2\30\4\0\126\30\6\0\3\30\1\0\132\30"+
    "\1\0\4\30\5\0\51\30\3\0\136\30\21\0\33\30\65\0\20\30"+
    "\u0200\0\u19b6\30\112\0\u51cd\30\63\0\u048d\30\103\0\56\30\2\0\u010d\30"+
    "\3\0\20\30\12\2\2\30\24\0\57\30\20\0\31\30\10\0\106\30"+
    "\61\0\11\30\2\0\147\30\2\0\4\30\1\0\4\30\14\0\13\30"+
    "\115\0\12\30\1\0\3\30\1\0\4\30\1\0\27\30\35\0\64\30"+
    "\16\0\62\30\34\0\12\2\30\0\6\30\3\0\1\30\4\0\12\2"+
    "\34\30\12\0\27\30\31\0\35\30\7\0\57\30\34\0\1\30\12\2"+
    "\46\0\51\30\27\0\3\30\1\0\10\30\4\0\12\2\6\0\27\30"+
    "\3\0\1\30\5\0\60\30\1\0\1\30\3\0\2\30\2\0\5\30"+
    "\2\0\1\30\1\0\1\30\30\0\3\30\2\0\13\30\7\0\3\30"+
    "\14\0\6\30\2\0\6\30\2\0\6\30\11\0\7\30\1\0\7\30"+
    "\221\0\43\30\15\0\12\2\6\0\u2ba4\30\14\0\27\30\4\0\61\30"+
    "\u2104\0\u016e\30\2\0\152\30\46\0\7\30\14\0\5\30\5\0\1\30"+
    "\1\0\12\30\1\0\15\30\1\0\5\30\1\0\1\30\1\0\2\30"+
    "\1\0\2\30\1\0\154\30\41\0\u016b\30\22\0\100\30\2\0\66\30"+
    "\50\0\14\30\164\0\5\30\1\0\207\30\23\0\12\2\7\0\32\30"+
    "\6\0\32\30\13\0\131\30\3\0\6\30\2\0\6\30\2\0\6\30"+
    "\2\0\3\30\43\0";

  /**
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

  /**
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\2\0\1\1\1\0\1\2\1\3\3\4\1\5\1\2"+
    "\1\6\1\7\1\6\1\1\1\2\1\10\1\11\2\7"+
    "\1\12\1\11\1\12\5\11\1\13\1\0\1\12\3\0"+
    "\1\12\2\0\1\14\6\0\2\12\1\0\2\14\1\0"+
    "\1\15\1\16\1\17";

  private static int [] zzUnpackAction() {
    int [] result = new int[53];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /**
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\36\0\74\0\132\0\170\0\226\0\264\0\322"+
    "\0\170\0\360\0\u010e\0\170\0\u012c\0\u014a\0\u0168\0\u0186"+
    "\0\170\0\170\0\u01a4\0\170\0\u01c2\0\u01e0\0\u01fe\0\u021c"+
    "\0\u023a\0\u0258\0\u0276\0\u0294\0\u02b2\0\u02d0\0\u02ee\0\u030c"+
    "\0\u032a\0\u0348\0\u0366\0\u021c\0\u023a\0\170\0\u0384\0\u0258"+
    "\0\u03a2\0\u03c0\0\u03de\0\u03fc\0\u032a\0\u041a\0\u041a\0\u023a"+
    "\0\u0258\0\u0438\0\170\0\170\0\170";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[53];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /**
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\5\1\6\1\5\1\7\1\10\1\11\4\5\3\6"+
    "\6\5\1\12\4\5\1\6\2\5\1\13\2\5\3\14"+
    "\1\15\1\16\31\14\3\17\1\7\1\20\1\5\25\17"+
    "\1\5\1\17\1\21\3\22\1\7\1\23\1\24\1\25"+
    "\1\26\2\27\3\22\1\30\1\22\1\26\1\31\1\22"+
    "\1\32\1\22\1\33\2\22\1\34\6\22\37\0\2\6"+
    "\3\0\1\6\1\0\5\6\13\0\1\6\10\0\1\7"+
    "\37\0\1\11\30\0\4\12\2\0\30\12\1\0\1\35"+
    "\10\0\3\35\13\0\1\35\3\0\1\36\4\0\1\15"+
    "\37\0\1\14\30\0\3\17\3\0\25\17\1\0\1\17"+
    "\6\0\1\5\35\0\1\24\36\0\1\37\1\0\1\40"+
    "\1\37\1\41\1\0\1\42\1\43\26\0\1\40\1\0"+
    "\2\27\3\0\1\44\26\0\1\27\1\0\2\27\2\0"+
    "\1\42\1\43\26\0\1\43\1\0\2\43\24\0\4\45"+
    "\2\0\12\45\1\46\1\47\14\45\4\50\2\0\13\50"+
    "\1\51\1\46\13\50\25\0\1\52\12\0\1\53\3\0"+
    "\1\53\1\0\5\53\13\0\2\53\5\0\2\35\3\0"+
    "\1\35\1\0\5\35\13\0\1\35\6\0\1\54\10\0"+
    "\3\54\13\0\1\54\13\0\1\37\1\0\1\40\1\37"+
    "\2\0\1\42\1\43\26\0\1\40\1\0\2\40\2\0"+
    "\1\42\1\43\26\0\1\55\1\0\2\55\1\0\2\55"+
    "\27\0\1\56\1\57\2\56\5\0\1\57\24\0\1\43"+
    "\1\0\2\43\2\0\1\42\21\0\4\45\2\0\12\45"+
    "\1\60\1\47\14\45\4\50\2\0\13\50\1\51\1\61"+
    "\13\50\3\62\3\0\10\62\3\0\1\62\6\0\1\62"+
    "\3\0\1\62\3\0\1\53\3\0\1\53\1\0\5\53"+
    "\13\0\2\53\1\63\4\0\2\54\3\0\1\54\1\0"+
    "\5\54\13\0\1\54\4\0\1\64\6\0\1\56\1\0"+
    "\2\56\24\0\3\62\3\0\10\62\3\0\1\62\4\0"+
    "\1\65\1\0\1\62\3\0\1\62\1\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[1110];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /* error codes */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /* error messages for the codes above */
  private static final String ZZ_ERROR_MSG[] = {
    "Unkown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\2\0\1\1\1\0\1\11\3\1\1\11\2\1\1\11"+
    "\4\1\2\11\1\1\1\11\11\1\1\0\1\1\3\0"+
    "\1\1\2\0\1\11\6\0\2\1\1\0\2\1\1\0"+
    "\3\11";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[53];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** the input device */
  private java.io.Reader zzReader;

  /** the current state of the DFA */
  private int zzState;

  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private char zzBuffer[] = new char[ZZ_BUFFERSIZE];

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

  /** the textposition at the last state to be included in yytext */
  private int zzPushbackPos;

  /** the current text position in the buffer */
  private int zzCurrentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
  private int zzEndRead;

  /** number of newlines encountered up to the start of the matched text */
  private int yyline;

  /** the number of characters up to the start of the matched text */
  private int yychar;

  /**
   * the number of characters from the last newline up to the start of the
   * matched text
   */
  private int yycolumn;

  /**
   * zzAtBOL == true <=> the scanner is currently at the beginning of a line
   */
  private boolean zzAtBOL = true;

  /** zzAtEOF == true <=> the scanner is at the EOF */
  private boolean zzAtEOF = false;

  /* user code: */

    private ByteStack stack = new ByteStack();

    private LexerInput input;

    public ApacheConfColoringLexer(LexerRestartInfo info) {
        this.input = info.input();
        if(info.state() != null) {
            //reset state
            setState((LexerState) info.state());
        } else {
            zzState = zzLexicalState = YYINITIAL;
            stack.clear();
        }

    }

    public static final class LexerState  {
        final ByteStack stack;
        /** the current state of the DFA */
        final int zzState;
        /** the current lexical state */
        final int zzLexicalState;

        LexerState(ByteStack stack, int zzState, int zzLexicalState) {
            this.stack = stack;
            this.zzState = zzState;
            this.zzLexicalState = zzLexicalState;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || obj.getClass() != this.getClass()) {
                return false;
            }
            LexerState state = (LexerState) obj;
            return (this.stack.equals(state.stack)
                && (this.zzState == state.zzState)
                && (this.zzLexicalState == state.zzLexicalState));
        }

        @Override
        public int hashCode() {
            int hash = 11;
            hash = 31 * hash + this.zzState;
            hash = 31 * hash + this.zzLexicalState;
            if (stack != null) {
                hash = 31 * hash + this.stack.hashCode();
            }
            return hash;
        }
    }

    public LexerState getState() {
        return new LexerState(stack.copyOf(), zzState, zzLexicalState);
    }

    public void setState(LexerState state) {
        this.stack.copyFrom(state.stack);
        this.zzState = state.zzState;
        this.zzLexicalState = state.zzLexicalState;
    }

    protected int getZZLexicalState() {
        return zzLexicalState;
    }

    protected void popState() {
		yybegin(stack.pop());
	}

	protected void pushState(final int state) {
		stack.push(getZZLexicalState());
		yybegin(state);
	}


 // End user code



  /**
   * Creates a new scanner
   * There is also a java.io.InputStream version of this constructor.
   *
   * @param   in  the java.io.Reader to read input from.
   */
  public ApacheConfColoringLexer(java.io.Reader in) {
    this.zzReader = in;
  }

  /**
   * Creates a new scanner.
   * There is also java.io.Reader version of this constructor.
   *
   * @param   in  the java.io.Inputstream to read input from.
   */
  public ApacheConfColoringLexer(java.io.InputStream in) {
    this(new java.io.InputStreamReader(in));
  }

  /**
   * Unpacks the compressed character translation table.
   *
   * @param packed   the packed character translation table
   * @return         the unpacked character translation table
   */
  private static char [] zzUnpackCMap(String packed) {
    char [] map = new char[0x10000];
    int i = 0;  /* index in packed string  */
    int j = 0;  /* index in unpacked array */
    while (i < 1686) {
      int  count = packed.charAt(i++);
      char value = packed.charAt(i++);
      do map[j++] = value; while (--count > 0);
    }
    return map;
  }



  /**
   * Closes the input stream.
   */
  public final void yyclose() throws java.io.IOException {
    zzAtEOF = true;            /* indicate end of file */
    zzEndRead = zzStartRead;  /* invalidate buffer    */

    if (zzReader != null)
      zzReader.close();
  }


  /**
   * Resets the scanner to read from a new input stream.
   * Does not close the old reader.
   *
   * All internal variables are reset, the old input stream
   * <b>cannot</b> be reused (internal buffer is discarded and lost).
   * Lexical state is set to <tt>ZZ_INITIAL</tt>.
   *
   * @param reader   the new input stream
   */
  public final void yyreset(java.io.Reader reader) {
    zzReader = reader;
    zzAtBOL  = true;
    zzAtEOF  = false;
    zzEndRead = zzStartRead = 0;
    zzCurrentPos = zzMarkedPos = zzPushbackPos = 0;
    yyline = yychar = yycolumn = 0;
    zzLexicalState = YYINITIAL;
  }


  /**
   * Returns the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  public final String yytext() {
    return input.readText().toString();
  }


  /**
   * Returns the character at position <tt>pos</tt> from the
   * matched text.
   *
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch.
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   */
  public final char yycharat(int pos) {
     return input.readText().charAt(pos);
  }


  /**
   * Returns the length of the matched text region.
   */
  public final int yylength() {
    return input.readLength();
  }


  /**
   * Reports an error that occured while scanning.
   *
   * In a wellformed scanner (no or only correct usage of
   * yypushback(int) and a match-all fallback rule) this method
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   */
  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  }


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    input.backup(number);
    //zzMarkedPos -= number;
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public ApacheConfTokenId nextToken() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    //int zzCurrentPosL;
    //int zzMarkedPosL;
    //int zzEndReadL = zzEndRead;
    //char [] zzBufferL = zzBuffer;
    char [] zzCMapL = ZZ_CMAP;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      //zzMarkedPosL = zzMarkedPos;

      zzAction = -1;

      //zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;
      int tokenLength = 0;

      zzState = ZZ_LEXSTATE[zzLexicalState];


      zzForAction: {
        while (true) {
            zzInput = input.read();

            if(zzInput == LexerInput.EOF) {
                //end of input reached
                zzInput = YYEOF;
                break zzForAction;
                //notice: currently LexerInput.EOF == YYEOF
            }

          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMapL[zzInput] ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          int zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            tokenLength = input.readLength();
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      if(zzInput != YYEOF) {
         input.backup(input.readLength() - tokenLength);
      }

      switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
        case 13:
          { return ApacheConfTokenId.AC_FLAG;
          }
        case 16: break;
        case 4:
          { return ApacheConfTokenId.AC_WHITESPACE;
          }
        case 17: break;
        case 7:
          { popState();
        return ApacheConfTokenId.AC_WHITESPACE;
          }
        case 18: break;
        case 9:
          { return ApacheConfTokenId.AC_DIRECTIVE_PARAM_TOKEN;
          }
        case 19: break;
        case 11:
          { pushState(ST_IN_OPEN_TAG);
        return ApacheConfTokenId.AC_TAG;
          }
        case 20: break;
        case 15:
          { return ApacheConfTokenId.AC_VARIABLE;
          }
        case 21: break;
        case 1:
          { return ApacheConfTokenId.AC_TAG_PARAM;
          }
        case 22: break;
        case 5:
          { return ApacheConfTokenId.AC_COMMENT;
          }
        case 23: break;
        case 3:
          { pushState(ST_IN_DIRECTIVE);
        return ApacheConfTokenId.AC_DIRECTIVE;
          }
        case 24: break;
        case 6:
          { return ApacheConfTokenId.AC_UNKNOWN;
          }
        case 25: break;
        case 14:
          { return ApacheConfTokenId.AC_TAG;
          }
        case 26: break;
        case 12:
          { return ApacheConfTokenId.AC_STRING;
          }
        case 27: break;
        case 8:
          { popState();
        return ApacheConfTokenId.AC_TAG;
          }
        case 28: break;
        case 10:
          { return ApacheConfTokenId.AC_NUMBER;
          }
        case 29: break;
        case 2:
          { yypushback(yylength());
        pushState(ST_HIGHLIGHTING_ERROR);
          }
        case 30: break;
        default:
          if (zzInput == YYEOF)
            //zzAtEOF = true;
              {         if(input.readLength() > 0) {
            // backup eof
            input.backup(1);
            //and return the text as error token
            return ApacheConfTokenId.AC_UNKNOWN;
        } else {
            return null;
        }
 }

          else {
            zzScanError(ZZ_NO_MATCH);
          }
      }
    }
  }


}
