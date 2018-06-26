/* The following code was generated by JFlex 1.4.3 on 13.2.12 13:43 */

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

package org.netbeans.modules.php.editor.lexer;

import org.netbeans.spi.lexer.LexerInput;
import org.netbeans.spi.lexer.LexerRestartInfo;

@org.netbeans.api.annotations.common.SuppressWarnings({"SF_SWITCH_FALLTHROUGH", "URF_UNREAD_FIELD", "DLS_DEAD_LOCAL_STORE", "DM_DEFAULT_ENCODING"})
/**
 * This class is a scanner generated by
 * <a href="http://www.jflex.de/">JFlex</a> 1.4.3
 * on 13.2.12 13:43 from the specification file
 * <tt>/home/warden/NetBeansProjects/web-main/php.editor/tools/DocumentorColoringScanner.flex</tt>
 */
public class DocumentorColoringScanner {

  /** This character denotes the end of file */
  public static final int YYEOF = LexerInput.EOF;

  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int ST_HTML_TAG = 6;
  public static final int ST_IN_TAG = 2;
  public static final int ST_NO_TAG = 4;
  public static final int YYINITIAL = 0;

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
    "\12\0\1\1\2\0\1\1\37\0\1\2\2\0\12\2\2\0\1\3"+
    "\1\0\1\4\1\0\1\5\32\2\1\0\1\2\2\0\1\2\1\0"+
    "\32\2\57\0\1\2\12\0\1\2\4\0\1\2\5\0\27\2\1\0"+
    "\37\2\1\0\u013f\2\31\0\162\2\4\0\14\2\16\0\5\2\11\0"+
    "\1\2\213\0\1\2\13\0\1\2\1\0\3\2\1\0\1\2\1\0"+
    "\24\2\1\0\54\2\1\0\46\2\1\0\5\2\4\0\202\2\10\0"+
    "\105\2\1\0\46\2\2\0\2\2\6\0\20\2\41\0\46\2\2\0"+
    "\1\2\7\0\47\2\110\0\33\2\5\0\3\2\56\0\32\2\5\0"+
    "\13\2\25\0\12\2\4\0\2\2\1\0\143\2\1\0\1\2\17\0"+
    "\2\2\7\0\17\2\2\0\1\2\20\0\1\2\1\0\36\2\35\0"+
    "\3\2\60\0\46\2\13\0\1\2\u0152\0\66\2\3\0\1\2\22\0"+
    "\1\2\7\0\12\2\4\0\12\2\25\0\10\2\2\0\2\2\2\0"+
    "\26\2\1\0\7\2\1\0\1\2\3\0\4\2\3\0\1\2\36\0"+
    "\2\2\1\0\3\2\4\0\14\2\23\0\6\2\4\0\2\2\2\0"+
    "\26\2\1\0\7\2\1\0\2\2\1\0\2\2\1\0\2\2\37\0"+
    "\4\2\1\0\1\2\7\0\12\2\2\0\3\2\20\0\11\2\1\0"+
    "\3\2\1\0\26\2\1\0\7\2\1\0\2\2\1\0\5\2\3\0"+
    "\1\2\22\0\1\2\17\0\2\2\4\0\12\2\25\0\10\2\2\0"+
    "\2\2\2\0\26\2\1\0\7\2\1\0\2\2\1\0\5\2\3\0"+
    "\1\2\36\0\2\2\1\0\3\2\4\0\12\2\1\0\1\2\21\0"+
    "\1\2\1\0\6\2\3\0\3\2\1\0\4\2\3\0\2\2\1\0"+
    "\1\2\1\0\2\2\3\0\2\2\3\0\3\2\3\0\10\2\1\0"+
    "\3\2\55\0\11\2\25\0\10\2\1\0\3\2\1\0\27\2\1\0"+
    "\12\2\1\0\5\2\46\0\2\2\4\0\12\2\25\0\10\2\1\0"+
    "\3\2\1\0\27\2\1\0\12\2\1\0\5\2\3\0\1\2\40\0"+
    "\1\2\1\0\2\2\4\0\12\2\25\0\10\2\1\0\3\2\1\0"+
    "\27\2\1\0\20\2\46\0\2\2\4\0\12\2\25\0\22\2\3\0"+
    "\30\2\1\0\11\2\1\0\1\2\2\0\7\2\72\0\60\2\1\0"+
    "\2\2\14\0\7\2\11\0\12\2\47\0\2\2\1\0\1\2\2\0"+
    "\2\2\1\0\1\2\2\0\1\2\6\0\4\2\1\0\7\2\1\0"+
    "\3\2\1\0\1\2\1\0\1\2\2\0\2\2\1\0\4\2\1\0"+
    "\2\2\11\0\1\2\2\0\5\2\1\0\1\2\11\0\12\2\2\0"+
    "\2\2\42\0\1\2\37\0\12\2\26\0\10\2\1\0\42\2\35\0"+
    "\4\2\164\0\42\2\1\0\5\2\1\0\2\2\25\0\12\2\6\0"+
    "\6\2\112\0\46\2\12\0\51\2\7\0\132\2\5\0\104\2\5\0"+
    "\122\2\6\0\7\2\1\0\77\2\1\0\1\2\1\0\4\2\2\0"+
    "\7\2\1\0\1\2\1\0\4\2\2\0\47\2\1\0\1\2\1\0"+
    "\4\2\2\0\37\2\1\0\1\2\1\0\4\2\2\0\7\2\1\0"+
    "\1\2\1\0\4\2\2\0\7\2\1\0\7\2\1\0\27\2\1\0"+
    "\37\2\1\0\1\2\1\0\4\2\2\0\7\2\1\0\47\2\1\0"+
    "\23\2\16\0\11\2\56\0\125\2\14\0\u026c\2\2\0\10\2\12\0"+
    "\32\2\5\0\113\2\25\0\15\2\1\0\4\2\16\0\22\2\16\0"+
    "\22\2\16\0\15\2\1\0\3\2\17\0\64\2\43\0\1\2\4\0"+
    "\1\2\3\0\12\2\46\0\12\2\6\0\130\2\10\0\51\2\127\0"+
    "\35\2\51\0\50\2\2\0\5\2\u038b\0\154\2\224\0\234\2\4\0"+
    "\132\2\6\0\26\2\2\0\6\2\2\0\46\2\2\0\6\2\2\0"+
    "\10\2\1\0\1\2\1\0\1\2\1\0\1\2\1\0\37\2\2\0"+
    "\65\2\1\0\7\2\1\0\1\2\3\0\3\2\1\0\7\2\3\0"+
    "\4\2\2\0\6\2\4\0\15\2\5\0\3\2\1\0\7\2\164\0"+
    "\1\2\15\0\1\2\202\0\1\2\4\0\1\2\2\0\12\2\1\0"+
    "\1\2\3\0\5\2\6\0\1\2\1\0\1\2\1\0\1\2\1\0"+
    "\4\2\1\0\3\2\1\0\7\2\3\0\3\2\5\0\5\2\u0ebb\0"+
    "\2\2\52\0\5\2\5\0\2\2\4\0\126\2\6\0\3\2\1\0"+
    "\132\2\1\0\4\2\5\0\50\2\4\0\136\2\21\0\30\2\70\0"+
    "\20\2\u0200\0\u19b6\2\112\0\u51a6\2\132\0\u048d\2\u0773\0\u2ba4\2\u215c\0"+
    "\u012e\2\2\0\73\2\225\0\7\2\14\0\5\2\5\0\1\2\1\0"+
    "\12\2\1\0\15\2\1\0\5\2\1\0\1\2\1\0\2\2\1\0"+
    "\2\2\1\0\154\2\41\0\u016b\2\22\0\100\2\2\0\66\2\50\0"+
    "\14\2\164\0\5\2\1\0\207\2\23\0\12\2\7\0\32\2\6\0"+
    "\32\2\13\0\131\2\3\0\6\2\2\0\6\2\2\0\6\2\2\0"+
    "\3\2\43\0";

  /**
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

  /**
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\1\1\3\0\1\1\1\2\1\3\2\4\1\5\2\6"+
    "\1\7\1\0\1\10";

  private static int [] zzUnpackAction() {
    int [] result = new int[15];
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
    "\0\0\0\6\0\14\0\22\0\30\0\36\0\36\0\36"+
    "\0\44\0\52\0\36\0\60\0\44\0\66\0\36";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[15];
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
    "\3\5\1\6\1\5\1\7\5\10\1\11\5\0\1\12"+
    "\3\13\1\14\2\13\3\5\1\0\1\5\11\0\1\15"+
    "\3\0\5\12\1\0\1\16\1\0\2\16\1\0\2\16"+
    "\1\0\2\16\1\17\1\16";

  private static int [] zzUnpackTrans() {
    int [] result = new int[60];
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
    "\1\1\3\0\1\1\3\11\2\1\1\11\2\1\1\0"+
    "\1\11";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[15];
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
        private LexerInput input;

        DocumentorColoringScanner (LexerRestartInfo info) {
            this.input = info.input();

            if(info.state() != null) {
                //reset state
                setState((LexerState)info.state());
            } else {
                //initial state
                zzState = zzLexicalState = YYINITIAL;
            }
       }


        public int getTokenLength() {
            return yylength();
        }

        public class LexerState  {
            /** the current state of the DFA */
            final int zzState;
            /** the current lexical state */
            final int zzLexicalState;

            LexerState () {
                zzState =  DocumentorColoringScanner.this.zzState;
                zzLexicalState = DocumentorColoringScanner.this.zzLexicalState;
            }

        }

        public LexerState getState() {
            return new LexerState();
        }

        public void setState(LexerState state) {
            this.zzState = state.zzState;
            this.zzLexicalState = state.zzLexicalState;
        }

   // End user code



  /**
   * Creates a new scanner
   * There is also a java.io.InputStream version of this constructor.
   *
   * @param   in  the java.io.Reader to read input from.
   */
  public DocumentorColoringScanner(java.io.Reader in) {
    this.zzReader = in;
  }

  /**
   * Creates a new scanner.
   * There is also java.io.Reader version of this constructor.
   *
   * @param   in  the java.io.Inputstream to read input from.
   */
  public DocumentorColoringScanner(java.io.InputStream in) {
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
    while (i < 1204) {
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
  public PHPDocCommentTokenId nextToken() throws java.io.IOException {
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

      //yychar+= zzMarkedPosL-zzStartRead;

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
        case 5:
          { yybegin(YYINITIAL);
    return PHPDocCommentTokenId.PHPDOC_COMMENT;
          }
        case 9: break;
        case 6:
          { yybegin(YYINITIAL);
        return PHPDocCommentTokenId.PHPDOC_COMMENT;
          }
        case 10: break;
        case 3:
          { yybegin(ST_IN_TAG);
        yypushback(1);
          }
        case 11: break;
        case 1:
          { return PHPDocCommentTokenId.PHPDOC_COMMENT;
          }
        case 12: break;
        case 2:
          { yybegin(ST_HTML_TAG);
        yypushback(1);
          }
        case 13: break;
        case 8:
          { yybegin(YYINITIAL);
        return PHPDocCommentTokenId.PHPDOC_HTML_TAG;
          }
        case 14: break;
        case 7:
          { yybegin(YYINITIAL); return PHPDocCommentTokenId.PHPDOC_ANNOTATION;
          }
        case 15: break;
        case 4:
          { yybegin(ST_NO_TAG); yypushback(1);
          }
        case 16: break;
        default:
          if (zzInput == YYEOF)
            //zzAtEOF = true;
              {           if(input.readLength() > 0) {
              return PHPDocCommentTokenId.PHPDOC_COMMENT;
          }
          else {
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
