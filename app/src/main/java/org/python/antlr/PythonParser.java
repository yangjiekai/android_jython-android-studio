// $ANTLR 3.1.3 Mar 17, 2009 19:23:44 /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g 2011-07-07 17:07:55

package org.python.antlr;

import org.antlr.runtime.CommonToken;

import org.python.antlr.ParseException;
import org.python.antlr.PythonTree;
import org.python.antlr.ast.alias;
import org.python.antlr.ast.arguments;
import org.python.antlr.ast.Assert;
import org.python.antlr.ast.Assign;
import org.python.antlr.ast.Attribute;
import org.python.antlr.ast.AugAssign;
import org.python.antlr.ast.BinOp;
import org.python.antlr.ast.BoolOp;
import org.python.antlr.ast.boolopType;
import org.python.antlr.ast.Break;
import org.python.antlr.ast.Call;
import org.python.antlr.ast.ClassDef;
import org.python.antlr.ast.cmpopType;
import org.python.antlr.ast.Compare;
import org.python.antlr.ast.comprehension;
import org.python.antlr.ast.Context;
import org.python.antlr.ast.Continue;
import org.python.antlr.ast.Delete;
import org.python.antlr.ast.Dict;
import org.python.antlr.ast.Ellipsis;
import org.python.antlr.ast.ErrorMod;
import org.python.antlr.ast.ExceptHandler;
import org.python.antlr.ast.Exec;
import org.python.antlr.ast.Expr;
import org.python.antlr.ast.Expression;
import org.python.antlr.ast.expr_contextType;
import org.python.antlr.ast.ExtSlice;
import org.python.antlr.ast.For;
import org.python.antlr.ast.GeneratorExp;
import org.python.antlr.ast.Global;
import org.python.antlr.ast.If;
import org.python.antlr.ast.IfExp;
import org.python.antlr.ast.Import;
import org.python.antlr.ast.ImportFrom;
import org.python.antlr.ast.Index;
import org.python.antlr.ast.Interactive;
import org.python.antlr.ast.keyword;
import org.python.antlr.ast.ListComp;
import org.python.antlr.ast.Lambda;
import org.python.antlr.ast.Module;
import org.python.antlr.ast.Name;
import org.python.antlr.ast.Num;
import org.python.antlr.ast.operatorType;
import org.python.antlr.ast.Pass;
import org.python.antlr.ast.Print;
import org.python.antlr.ast.Raise;
import org.python.antlr.ast.Repr;
import org.python.antlr.ast.Return;
import org.python.antlr.ast.Slice;
import org.python.antlr.ast.Str;
import org.python.antlr.ast.Subscript;
import org.python.antlr.ast.TryExcept;
import org.python.antlr.ast.TryFinally;
import org.python.antlr.ast.Tuple;
import org.python.antlr.ast.unaryopType;
import org.python.antlr.ast.UnaryOp;
import org.python.antlr.ast.While;
import org.python.antlr.ast.With;
import org.python.antlr.ast.Yield;
import org.python.antlr.base.excepthandler;
import org.python.antlr.base.expr;
import org.python.antlr.base.mod;
import org.python.antlr.base.slice;
import org.python.antlr.base.stmt;
import org.python.core.Py;
import org.python.core.PyObject;
import org.python.core.PyString;
import org.python.core.PyUnicode;

import java.math.BigInteger;
import java.util.Collections;
import java.util.Iterator;
import java.util.ListIterator;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import org.antlr.runtime.tree.*;

/** Python 2.3.3 Grammar
 *
 *  Terence Parr and Loring Craymer
 *  February 2004
 *
 *  Converted to ANTLR v3 November 2005 by Terence Parr.
 *
 *  This grammar was derived automatically from the Python 2.3.3
 *  parser grammar to get a syntactically correct ANTLR grammar
 *  for Python.  Then Terence hand tweaked it to be semantically
 *  correct; i.e., removed lookahead issues etc...  It is LL(1)
 *  except for the (sometimes optional) trailing commas and semi-colons.
 *  It needs two symbols of lookahead in this case.
 *
 *  Starting with Loring's preliminary lexer for Python, I modified it
 *  to do my version of the whole nasty INDENT/DEDENT issue just so I
 *  could understand the problem better.  This grammar requires
 *  PythonTokenStream.java to work.  Also I used some rules from the
 *  semi-formal grammar on the web for Python (automatically
 *  translated to ANTLR format by an ANTLR grammar, naturally <grin>).
 *  The lexical rules for python are particularly nasty and it took me
 *  a long time to get it 'right'; i.e., think about it in the proper
 *  way.  Resist changing the lexer unless you've used ANTLR a lot. ;)
 *
 *  I (Terence) tested this by running it on the jython-2.1/Lib
 *  directory of 40k lines of Python.
 *
 *  REQUIRES ANTLR v3
 *
 *
 *  Updated the original parser for Python 2.5 features. The parser has been
 *  altered to produce an AST - the AST work started from tne newcompiler
 *  grammar from Jim Baker.  The current parsing and compiling strategy looks
 *  like this:
 *
 *  Python source->Python.g->AST (org/python/parser/ast/*)->CodeCompiler(ASM)->.class
 */
public class PythonParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "INDENT", "DEDENT", "TRAILBACKSLASH", "NEWLINE", "LEADING_WS", "NAME", "DOT", "AND", "AS", "ASSERT", "BREAK", "CLASS", "CONTINUE", "DEF", "DELETE", "ELIF", "EXCEPT", "EXEC", "FINALLY", "FROM", "FOR", "GLOBAL", "IF", "IMPORT", "IN", "IS", "LAMBDA", "NOT", "OR", "ORELSE", "PASS", "PRINT", "RAISE", "RETURN", "TRY", "WHILE", "WITH", "YIELD", "AT", "LPAREN", "RPAREN", "COLON", "ASSIGN", "COMMA", "STAR", "DOUBLESTAR", "SEMI", "PLUSEQUAL", "MINUSEQUAL", "STAREQUAL", "SLASHEQUAL", "PERCENTEQUAL", "AMPEREQUAL", "VBAREQUAL", "CIRCUMFLEXEQUAL", "LEFTSHIFTEQUAL", "RIGHTSHIFTEQUAL", "DOUBLESTAREQUAL", "DOUBLESLASHEQUAL", "RIGHTSHIFT", "LESS", "GREATER", "EQUAL", "GREATEREQUAL", "LESSEQUAL", "ALT_NOTEQUAL", "NOTEQUAL", "VBAR", "CIRCUMFLEX", "AMPER", "LEFTSHIFT", "PLUS", "MINUS", "SLASH", "PERCENT", "DOUBLESLASH", "TILDE", "LBRACK", "RBRACK", "LCURLY", "RCURLY", "BACKQUOTE", "INT", "LONGINT", "FLOAT", "COMPLEX", "STRING", "DIGITS", "Exponent", "TRIAPOS", "TRIQUOTE", "ESC", "COMMENT", "CONTINUED_LINE", "WS"
    };
    public static final int SLASHEQUAL=54;
    public static final int BACKQUOTE=85;
    public static final int STAR=48;
    public static final int CIRCUMFLEXEQUAL=58;
    public static final int WHILE=39;
    public static final int TRIAPOS=93;
    public static final int ORELSE=33;
    public static final int GREATEREQUAL=67;
    public static final int COMPLEX=89;
    public static final int NOT=31;
    public static final int EXCEPT=20;
    public static final int EOF=-1;
    public static final int BREAK=14;
    public static final int PASS=34;
    public static final int LEADING_WS=8;
    public static final int NOTEQUAL=70;
    public static final int MINUSEQUAL=52;
    public static final int VBAR=71;
    public static final int RPAREN=44;
    public static final int IMPORT=27;
    public static final int NAME=9;
    public static final int GREATER=65;
    public static final int DOUBLESTAREQUAL=61;
    public static final int RETURN=37;
    public static final int LESS=64;
    public static final int RAISE=36;
    public static final int COMMENT=96;
    public static final int RBRACK=82;
    public static final int LCURLY=83;
    public static final int INT=86;
    public static final int DELETE=18;
    public static final int RIGHTSHIFT=63;
    public static final int ASSERT=13;
    public static final int TRY=38;
    public static final int DOUBLESLASHEQUAL=62;
    public static final int ELIF=19;
    public static final int WS=98;
    public static final int VBAREQUAL=57;
    public static final int OR=32;
    public static final int LONGINT=87;
    public static final int FROM=23;
    public static final int PERCENTEQUAL=55;
    public static final int LESSEQUAL=68;
    public static final int DOUBLESLASH=79;
    public static final int CLASS=15;
    public static final int CONTINUED_LINE=97;
    public static final int LBRACK=81;
    public static final int DEF=17;
    public static final int DOUBLESTAR=49;
    public static final int ESC=95;
    public static final int DIGITS=91;
    public static final int Exponent=92;
    public static final int FOR=24;
    public static final int DEDENT=5;
    public static final int FLOAT=88;
    public static final int AND=11;
    public static final int RIGHTSHIFTEQUAL=60;
    public static final int LPAREN=43;
    public static final int INDENT=4;
    public static final int IF=26;
    public static final int PLUSEQUAL=51;
    public static final int AT=42;
    public static final int AS=12;
    public static final int SLASH=77;
    public static final int IN=28;
    public static final int CONTINUE=16;
    public static final int COMMA=47;
    public static final int IS=29;
    public static final int AMPER=73;
    public static final int EQUAL=66;
    public static final int YIELD=41;
    public static final int TILDE=80;
    public static final int LEFTSHIFTEQUAL=59;
    public static final int LEFTSHIFT=74;
    public static final int PLUS=75;
    public static final int LAMBDA=30;
    public static final int DOT=10;
    public static final int WITH=40;
    public static final int PERCENT=78;
    public static final int EXEC=21;
    public static final int MINUS=76;
    public static final int SEMI=50;
    public static final int PRINT=35;
    public static final int TRIQUOTE=94;
    public static final int COLON=45;
    public static final int TRAILBACKSLASH=6;
    public static final int NEWLINE=7;
    public static final int AMPEREQUAL=56;
    public static final int FINALLY=22;
    public static final int RCURLY=84;
    public static final int ASSIGN=46;
    public static final int GLOBAL=25;
    public static final int STAREQUAL=53;
    public static final int CIRCUMFLEX=72;
    public static final int STRING=90;
    public static final int ALT_NOTEQUAL=69;

    // delegates
    // delegators


        public PythonParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public PythonParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        
    protected TreeAdaptor adaptor = new CommonTreeAdaptor();

    public void setTreeAdaptor(TreeAdaptor adaptor) {
        this.adaptor = adaptor;
    }
    public TreeAdaptor getTreeAdaptor() {
        return adaptor;
    }

    public String[] getTokenNames() { return PythonParser.tokenNames; }
    public String getGrammarFileName() { return "/home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g"; }


        private ErrorHandler errorHandler;

        private GrammarActions actions = new GrammarActions();

        private String encoding;

        public void setErrorHandler(ErrorHandler eh) {
            this.errorHandler = eh;
            actions.setErrorHandler(eh);
        }

        protected Object recoverFromMismatchedToken(IntStream input, int ttype, BitSet follow)
            throws RecognitionException {

            Object o = errorHandler.recoverFromMismatchedToken(this, input, ttype, follow);
            if (o != null) {
                return o;
            }
            return super.recoverFromMismatchedToken(input, ttype, follow);
        }

        public PythonParser(TokenStream input, String encoding) {
            this(input);
            this.encoding = encoding;
        }



    public static class single_input_return extends ParserRuleReturnScope {
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "single_input"
    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:273:1: single_input : ( ( NEWLINE )* EOF | simple_stmt ( NEWLINE )* EOF | compound_stmt ( NEWLINE )+ EOF );
    public final PythonParser.single_input_return single_input() throws RecognitionException {
        PythonParser.single_input_return retval = new PythonParser.single_input_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token NEWLINE1=null;
        Token EOF2=null;
        Token NEWLINE4=null;
        Token EOF5=null;
        Token NEWLINE7=null;
        Token EOF8=null;
        PythonParser.simple_stmt_return simple_stmt3 = null;

        PythonParser.compound_stmt_return compound_stmt6 = null;


        PythonTree NEWLINE1_tree=null;
        PythonTree EOF2_tree=null;
        PythonTree NEWLINE4_tree=null;
        PythonTree EOF5_tree=null;
        PythonTree NEWLINE7_tree=null;
        PythonTree EOF8_tree=null;


            mod mtype = null;

        try {
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:280:5: ( ( NEWLINE )* EOF | simple_stmt ( NEWLINE )* EOF | compound_stmt ( NEWLINE )+ EOF )
            int alt4=3;
            switch ( input.LA(1) ) {
            case EOF:
            case NEWLINE:
                {
                alt4=1;
                }
                break;
            case NAME:
            case ASSERT:
            case BREAK:
            case CONTINUE:
            case DELETE:
            case EXEC:
            case FROM:
            case GLOBAL:
            case IMPORT:
            case LAMBDA:
            case NOT:
            case PASS:
            case PRINT:
            case RAISE:
            case RETURN:
            case YIELD:
            case LPAREN:
            case PLUS:
            case MINUS:
            case TILDE:
            case LBRACK:
            case LCURLY:
            case BACKQUOTE:
            case INT:
            case LONGINT:
            case FLOAT:
            case COMPLEX:
            case STRING:
                {
                alt4=2;
                }
                break;
            case CLASS:
            case DEF:
            case FOR:
            case IF:
            case TRY:
            case WHILE:
            case WITH:
            case AT:
                {
                alt4=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;
            }

            switch (alt4) {
                case 1 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:280:7: ( NEWLINE )* EOF
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:280:7: ( NEWLINE )*
                    loop1:
                    do {
                        int alt1=2;
                        int LA1_0 = input.LA(1);

                        if ( (LA1_0==NEWLINE) ) {
                            alt1=1;
                        }


                        switch (alt1) {
                    	case 1 :
                    	    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:280:7: NEWLINE
                    	    {
                    	    NEWLINE1=(Token)match(input,NEWLINE,FOLLOW_NEWLINE_in_single_input118); if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) {
                    	    NEWLINE1_tree = (PythonTree)adaptor.create(NEWLINE1);
                    	    adaptor.addChild(root_0, NEWLINE1_tree);
                    	    }

                    	    }
                    	    break;

                    	default :
                    	    break loop1;
                        }
                    } while (true);

                    EOF2=(Token)match(input,EOF,FOLLOW_EOF_in_single_input121); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    EOF2_tree = (PythonTree)adaptor.create(EOF2);
                    adaptor.addChild(root_0, EOF2_tree);
                    }
                    if ( state.backtracking==0 ) {

                              mtype = new Interactive(((Token)retval.start), new ArrayList<stmt>());
                            
                    }

                    }
                    break;
                case 2 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:284:7: simple_stmt ( NEWLINE )* EOF
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    pushFollow(FOLLOW_simple_stmt_in_single_input137);
                    simple_stmt3=simple_stmt();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, simple_stmt3.getTree());
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:284:19: ( NEWLINE )*
                    loop2:
                    do {
                        int alt2=2;
                        int LA2_0 = input.LA(1);

                        if ( (LA2_0==NEWLINE) ) {
                            alt2=1;
                        }


                        switch (alt2) {
                    	case 1 :
                    	    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:284:19: NEWLINE
                    	    {
                    	    NEWLINE4=(Token)match(input,NEWLINE,FOLLOW_NEWLINE_in_single_input139); if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) {
                    	    NEWLINE4_tree = (PythonTree)adaptor.create(NEWLINE4);
                    	    adaptor.addChild(root_0, NEWLINE4_tree);
                    	    }

                    	    }
                    	    break;

                    	default :
                    	    break loop2;
                        }
                    } while (true);

                    EOF5=(Token)match(input,EOF,FOLLOW_EOF_in_single_input142); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    EOF5_tree = (PythonTree)adaptor.create(EOF5);
                    adaptor.addChild(root_0, EOF5_tree);
                    }
                    if ( state.backtracking==0 ) {

                              mtype = new Interactive(((Token)retval.start), actions.castStmts((simple_stmt3!=null?simple_stmt3.stypes:null)));
                            
                    }

                    }
                    break;
                case 3 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:288:7: compound_stmt ( NEWLINE )+ EOF
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    pushFollow(FOLLOW_compound_stmt_in_single_input158);
                    compound_stmt6=compound_stmt();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, compound_stmt6.getTree());
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:288:21: ( NEWLINE )+
                    int cnt3=0;
                    loop3:
                    do {
                        int alt3=2;
                        int LA3_0 = input.LA(1);

                        if ( (LA3_0==NEWLINE) ) {
                            alt3=1;
                        }


                        switch (alt3) {
                    	case 1 :
                    	    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:288:21: NEWLINE
                    	    {
                    	    NEWLINE7=(Token)match(input,NEWLINE,FOLLOW_NEWLINE_in_single_input160); if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) {
                    	    NEWLINE7_tree = (PythonTree)adaptor.create(NEWLINE7);
                    	    adaptor.addChild(root_0, NEWLINE7_tree);
                    	    }

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt3 >= 1 ) break loop3;
                    	    if (state.backtracking>0) {state.failed=true; return retval;}
                                EarlyExitException eee =
                                    new EarlyExitException(3, input);
                                throw eee;
                        }
                        cnt3++;
                    } while (true);

                    EOF8=(Token)match(input,EOF,FOLLOW_EOF_in_single_input163); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    EOF8_tree = (PythonTree)adaptor.create(EOF8);
                    adaptor.addChild(root_0, EOF8_tree);
                    }
                    if ( state.backtracking==0 ) {

                              mtype = new Interactive(((Token)retval.start), actions.castStmts((compound_stmt6!=null?((PythonTree)compound_stmt6.tree):null)));
                            
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {

                  retval.tree = mtype;

            }
        }
        catch (RecognitionException re) {

                    errorHandler.reportError(this, re);
                    errorHandler.recover(this, input,re);
                    PythonTree badNode = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
                    retval.tree = new ErrorMod(badNode);
                
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "single_input"

    public static class file_input_return extends ParserRuleReturnScope {
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "file_input"
    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:302:1: file_input : ( NEWLINE | stmt )* EOF ;
    public final PythonParser.file_input_return file_input() throws RecognitionException {
        PythonParser.file_input_return retval = new PythonParser.file_input_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token NEWLINE9=null;
        Token EOF11=null;
        PythonParser.stmt_return stmt10 = null;


        PythonTree NEWLINE9_tree=null;
        PythonTree EOF11_tree=null;


            mod mtype = null;
            List stypes = new ArrayList();

        try {
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:320:5: ( ( NEWLINE | stmt )* EOF )
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:320:7: ( NEWLINE | stmt )* EOF
            {
            root_0 = (PythonTree)adaptor.nil();

            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:320:7: ( NEWLINE | stmt )*
            loop5:
            do {
                int alt5=3;
                int LA5_0 = input.LA(1);

                if ( (LA5_0==NEWLINE) ) {
                    alt5=1;
                }
                else if ( (LA5_0==NAME||(LA5_0>=ASSERT && LA5_0<=DELETE)||LA5_0==EXEC||(LA5_0>=FROM && LA5_0<=IMPORT)||(LA5_0>=LAMBDA && LA5_0<=NOT)||(LA5_0>=PASS && LA5_0<=LPAREN)||(LA5_0>=PLUS && LA5_0<=MINUS)||(LA5_0>=TILDE && LA5_0<=LBRACK)||LA5_0==LCURLY||(LA5_0>=BACKQUOTE && LA5_0<=STRING)) ) {
                    alt5=2;
                }


                switch (alt5) {
            	case 1 :
            	    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:320:8: NEWLINE
            	    {
            	    NEWLINE9=(Token)match(input,NEWLINE,FOLLOW_NEWLINE_in_file_input215); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    NEWLINE9_tree = (PythonTree)adaptor.create(NEWLINE9);
            	    adaptor.addChild(root_0, NEWLINE9_tree);
            	    }

            	    }
            	    break;
            	case 2 :
            	    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:321:9: stmt
            	    {
            	    pushFollow(FOLLOW_stmt_in_file_input225);
            	    stmt10=stmt();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, stmt10.getTree());
            	    if ( state.backtracking==0 ) {

            	                if ((stmt10!=null?stmt10.stypes:null) != null) {
            	                    stypes.addAll((stmt10!=null?stmt10.stypes:null));
            	                }
            	            
            	    }

            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);

            EOF11=(Token)match(input,EOF,FOLLOW_EOF_in_file_input244); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            EOF11_tree = (PythonTree)adaptor.create(EOF11);
            adaptor.addChild(root_0, EOF11_tree);
            }
            if ( state.backtracking==0 ) {

                           mtype = new Module(((Token)retval.start), actions.castStmts(stypes));
                       
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {

                  if (!stypes.isEmpty()) {
                      //The EOF token messes up the end offsets, so set them manually.
                      //XXX: this may no longer be true now that PythonTokenSource is
                      //     adjusting EOF offsets -- but needs testing before I remove
                      //     this.
                      PythonTree stop = (PythonTree)stypes.get(stypes.size() -1);
                      mtype.setCharStopIndex(stop.getCharStopIndex());
                      mtype.setTokenStopIndex(stop.getTokenStopIndex());
                  }

                  retval.tree = mtype;

            }
        }
        catch (RecognitionException re) {

                    errorHandler.reportError(this, re);
                    errorHandler.recover(this, input,re);
                    PythonTree badNode = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
                    retval.tree = new ErrorMod(badNode);
                
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "file_input"

    public static class eval_input_return extends ParserRuleReturnScope {
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "eval_input"
    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:341:1: eval_input : ( LEADING_WS )? ( NEWLINE )* testlist[expr_contextType.Load] ( NEWLINE )* EOF ;
    public final PythonParser.eval_input_return eval_input() throws RecognitionException {
        PythonParser.eval_input_return retval = new PythonParser.eval_input_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token LEADING_WS12=null;
        Token NEWLINE13=null;
        Token NEWLINE15=null;
        Token EOF16=null;
        PythonParser.testlist_return testlist14 = null;


        PythonTree LEADING_WS12_tree=null;
        PythonTree NEWLINE13_tree=null;
        PythonTree NEWLINE15_tree=null;
        PythonTree EOF16_tree=null;


            mod mtype = null;

        try {
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:348:5: ( ( LEADING_WS )? ( NEWLINE )* testlist[expr_contextType.Load] ( NEWLINE )* EOF )
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:348:7: ( LEADING_WS )? ( NEWLINE )* testlist[expr_contextType.Load] ( NEWLINE )* EOF
            {
            root_0 = (PythonTree)adaptor.nil();

            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:348:7: ( LEADING_WS )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==LEADING_WS) ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:348:7: LEADING_WS
                    {
                    LEADING_WS12=(Token)match(input,LEADING_WS,FOLLOW_LEADING_WS_in_eval_input298); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    LEADING_WS12_tree = (PythonTree)adaptor.create(LEADING_WS12);
                    adaptor.addChild(root_0, LEADING_WS12_tree);
                    }

                    }
                    break;

            }

            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:348:19: ( NEWLINE )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( (LA7_0==NEWLINE) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:348:20: NEWLINE
            	    {
            	    NEWLINE13=(Token)match(input,NEWLINE,FOLLOW_NEWLINE_in_eval_input302); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    NEWLINE13_tree = (PythonTree)adaptor.create(NEWLINE13);
            	    adaptor.addChild(root_0, NEWLINE13_tree);
            	    }

            	    }
            	    break;

            	default :
            	    break loop7;
                }
            } while (true);

            pushFollow(FOLLOW_testlist_in_eval_input306);
            testlist14=testlist(expr_contextType.Load);

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, testlist14.getTree());
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:348:62: ( NEWLINE )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( (LA8_0==NEWLINE) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:348:63: NEWLINE
            	    {
            	    NEWLINE15=(Token)match(input,NEWLINE,FOLLOW_NEWLINE_in_eval_input310); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    NEWLINE15_tree = (PythonTree)adaptor.create(NEWLINE15);
            	    adaptor.addChild(root_0, NEWLINE15_tree);
            	    }

            	    }
            	    break;

            	default :
            	    break loop8;
                }
            } while (true);

            EOF16=(Token)match(input,EOF,FOLLOW_EOF_in_eval_input314); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            EOF16_tree = (PythonTree)adaptor.create(EOF16);
            adaptor.addChild(root_0, EOF16_tree);
            }
            if ( state.backtracking==0 ) {

                      mtype = new Expression(((Token)retval.start), actions.castExpr((testlist14!=null?((PythonTree)testlist14.tree):null)));
                    
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {

                  retval.tree = mtype;

            }
        }
        catch (RecognitionException re) {

                    errorHandler.reportError(this, re);
                    errorHandler.recover(this, input,re);
                    PythonTree badNode = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
                    retval.tree = new ErrorMod(badNode);
                
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "eval_input"

    public static class dotted_attr_return extends ParserRuleReturnScope {
        public expr etype;
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "dotted_attr"
    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:363:1: dotted_attr returns [expr etype] : n1= NAME ( ( DOT n2+= NAME )+ | ) ;
    public final PythonParser.dotted_attr_return dotted_attr() throws RecognitionException {
        PythonParser.dotted_attr_return retval = new PythonParser.dotted_attr_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token n1=null;
        Token DOT17=null;
        Token n2=null;
        List list_n2=null;

        PythonTree n1_tree=null;
        PythonTree DOT17_tree=null;
        PythonTree n2_tree=null;

        try {
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:365:5: (n1= NAME ( ( DOT n2+= NAME )+ | ) )
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:365:7: n1= NAME ( ( DOT n2+= NAME )+ | )
            {
            root_0 = (PythonTree)adaptor.nil();

            n1=(Token)match(input,NAME,FOLLOW_NAME_in_dotted_attr366); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            n1_tree = (PythonTree)adaptor.create(n1);
            adaptor.addChild(root_0, n1_tree);
            }
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:366:7: ( ( DOT n2+= NAME )+ | )
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==DOT) ) {
                alt10=1;
            }
            else if ( (LA10_0==NEWLINE||LA10_0==LPAREN) ) {
                alt10=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 10, 0, input);

                throw nvae;
            }
            switch (alt10) {
                case 1 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:366:9: ( DOT n2+= NAME )+
                    {
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:366:9: ( DOT n2+= NAME )+
                    int cnt9=0;
                    loop9:
                    do {
                        int alt9=2;
                        int LA9_0 = input.LA(1);

                        if ( (LA9_0==DOT) ) {
                            alt9=1;
                        }


                        switch (alt9) {
                    	case 1 :
                    	    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:366:10: DOT n2+= NAME
                    	    {
                    	    DOT17=(Token)match(input,DOT,FOLLOW_DOT_in_dotted_attr377); if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) {
                    	    DOT17_tree = (PythonTree)adaptor.create(DOT17);
                    	    adaptor.addChild(root_0, DOT17_tree);
                    	    }
                    	    n2=(Token)match(input,NAME,FOLLOW_NAME_in_dotted_attr381); if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) {
                    	    n2_tree = (PythonTree)adaptor.create(n2);
                    	    adaptor.addChild(root_0, n2_tree);
                    	    }
                    	    if (list_n2==null) list_n2=new ArrayList();
                    	    list_n2.add(n2);


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt9 >= 1 ) break loop9;
                    	    if (state.backtracking>0) {state.failed=true; return retval;}
                                EarlyExitException eee =
                                    new EarlyExitException(9, input);
                                throw eee;
                        }
                        cnt9++;
                    } while (true);

                    if ( state.backtracking==0 ) {

                                  retval.etype = actions.makeDottedAttr(n1, list_n2);
                              
                    }

                    }
                    break;
                case 2 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:371:9: 
                    {
                    if ( state.backtracking==0 ) {

                                  retval.etype = actions.makeNameNode(n1);
                              
                    }

                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException re) {
            errorHandler.reportError(this, re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "dotted_attr"

    public static class attr_return extends ParserRuleReturnScope {
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "attr"
    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:380:1: attr : ( NAME | AND | AS | ASSERT | BREAK | CLASS | CONTINUE | DEF | DELETE | ELIF | EXCEPT | EXEC | FINALLY | FROM | FOR | GLOBAL | IF | IMPORT | IN | IS | LAMBDA | NOT | OR | ORELSE | PASS | PRINT | RAISE | RETURN | TRY | WHILE | WITH | YIELD );
    public final PythonParser.attr_return attr() throws RecognitionException {
        PythonParser.attr_return retval = new PythonParser.attr_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token set18=null;

        PythonTree set18_tree=null;

        try {
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:381:5: ( NAME | AND | AS | ASSERT | BREAK | CLASS | CONTINUE | DEF | DELETE | ELIF | EXCEPT | EXEC | FINALLY | FROM | FOR | GLOBAL | IF | IMPORT | IN | IS | LAMBDA | NOT | OR | ORELSE | PASS | PRINT | RAISE | RETURN | TRY | WHILE | WITH | YIELD )
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:
            {
            root_0 = (PythonTree)adaptor.nil();

            set18=(Token)input.LT(1);
            if ( input.LA(1)==NAME||(input.LA(1)>=AND && input.LA(1)<=YIELD) ) {
                input.consume();
                if ( state.backtracking==0 ) adaptor.addChild(root_0, (PythonTree)adaptor.create(set18));
                state.errorRecovery=false;state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException re) {
            errorHandler.reportError(this, re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "attr"

    public static class decorator_return extends ParserRuleReturnScope {
        public expr etype;
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "decorator"
    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:416:1: decorator returns [expr etype] : AT dotted_attr ( LPAREN ( arglist | ) RPAREN | ) NEWLINE ;
    public final PythonParser.decorator_return decorator() throws RecognitionException {
        PythonParser.decorator_return retval = new PythonParser.decorator_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token AT19=null;
        Token LPAREN21=null;
        Token RPAREN23=null;
        Token NEWLINE24=null;
        PythonParser.dotted_attr_return dotted_attr20 = null;

        PythonParser.arglist_return arglist22 = null;


        PythonTree AT19_tree=null;
        PythonTree LPAREN21_tree=null;
        PythonTree RPAREN23_tree=null;
        PythonTree NEWLINE24_tree=null;

        try {
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:421:5: ( AT dotted_attr ( LPAREN ( arglist | ) RPAREN | ) NEWLINE )
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:421:7: AT dotted_attr ( LPAREN ( arglist | ) RPAREN | ) NEWLINE
            {
            root_0 = (PythonTree)adaptor.nil();

            AT19=(Token)match(input,AT,FOLLOW_AT_in_decorator718); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            AT19_tree = (PythonTree)adaptor.create(AT19);
            adaptor.addChild(root_0, AT19_tree);
            }
            pushFollow(FOLLOW_dotted_attr_in_decorator720);
            dotted_attr20=dotted_attr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, dotted_attr20.getTree());
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:422:5: ( LPAREN ( arglist | ) RPAREN | )
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==LPAREN) ) {
                alt12=1;
            }
            else if ( (LA12_0==NEWLINE) ) {
                alt12=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 12, 0, input);

                throw nvae;
            }
            switch (alt12) {
                case 1 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:422:7: LPAREN ( arglist | ) RPAREN
                    {
                    LPAREN21=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_decorator728); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    LPAREN21_tree = (PythonTree)adaptor.create(LPAREN21);
                    adaptor.addChild(root_0, LPAREN21_tree);
                    }
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:423:7: ( arglist | )
                    int alt11=2;
                    int LA11_0 = input.LA(1);

                    if ( (LA11_0==NAME||(LA11_0>=LAMBDA && LA11_0<=NOT)||LA11_0==LPAREN||(LA11_0>=STAR && LA11_0<=DOUBLESTAR)||(LA11_0>=PLUS && LA11_0<=MINUS)||(LA11_0>=TILDE && LA11_0<=LBRACK)||LA11_0==LCURLY||(LA11_0>=BACKQUOTE && LA11_0<=STRING)) ) {
                        alt11=1;
                    }
                    else if ( (LA11_0==RPAREN) ) {
                        alt11=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 11, 0, input);

                        throw nvae;
                    }
                    switch (alt11) {
                        case 1 :
                            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:423:9: arglist
                            {
                            pushFollow(FOLLOW_arglist_in_decorator738);
                            arglist22=arglist();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, arglist22.getTree());
                            if ( state.backtracking==0 ) {

                                          retval.etype = actions.makeCall(LPAREN21, (dotted_attr20!=null?dotted_attr20.etype:null), (arglist22!=null?arglist22.args:null), (arglist22!=null?arglist22.keywords:null),
                                                   (arglist22!=null?arglist22.starargs:null), (arglist22!=null?arglist22.kwargs:null));
                                      
                            }

                            }
                            break;
                        case 2 :
                            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:429:9: 
                            {
                            if ( state.backtracking==0 ) {

                                          retval.etype = actions.makeCall(LPAREN21, (dotted_attr20!=null?dotted_attr20.etype:null));
                                      
                            }

                            }
                            break;

                    }

                    RPAREN23=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_decorator782); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    RPAREN23_tree = (PythonTree)adaptor.create(RPAREN23);
                    adaptor.addChild(root_0, RPAREN23_tree);
                    }

                    }
                    break;
                case 2 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:435:7: 
                    {
                    if ( state.backtracking==0 ) {

                                retval.etype = (dotted_attr20!=null?dotted_attr20.etype:null);
                            
                    }

                    }
                    break;

            }

            NEWLINE24=(Token)match(input,NEWLINE,FOLLOW_NEWLINE_in_decorator804); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            NEWLINE24_tree = (PythonTree)adaptor.create(NEWLINE24);
            adaptor.addChild(root_0, NEWLINE24_tree);
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {

                 retval.tree = retval.etype;

            }
        }

        catch (RecognitionException re) {
            errorHandler.reportError(this, re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "decorator"

    public static class decorators_return extends ParserRuleReturnScope {
        public List etypes;
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "decorators"
    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:442:1: decorators returns [List etypes] : (d+= decorator )+ ;
    public final PythonParser.decorators_return decorators() throws RecognitionException {
        PythonParser.decorators_return retval = new PythonParser.decorators_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        List list_d=null;
        PythonParser.decorator_return d = null;
         d = null;

        try {
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:444:5: ( (d+= decorator )+ )
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:444:7: (d+= decorator )+
            {
            root_0 = (PythonTree)adaptor.nil();

            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:444:8: (d+= decorator )+
            int cnt13=0;
            loop13:
            do {
                int alt13=2;
                int LA13_0 = input.LA(1);

                if ( (LA13_0==AT) ) {
                    alt13=1;
                }


                switch (alt13) {
            	case 1 :
            	    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:444:8: d+= decorator
            	    {
            	    pushFollow(FOLLOW_decorator_in_decorators832);
            	    d=decorator();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, d.getTree());
            	    if (list_d==null) list_d=new ArrayList();
            	    list_d.add(d.getTree());


            	    }
            	    break;

            	default :
            	    if ( cnt13 >= 1 ) break loop13;
            	    if (state.backtracking>0) {state.failed=true; return retval;}
                        EarlyExitException eee =
                            new EarlyExitException(13, input);
                        throw eee;
                }
                cnt13++;
            } while (true);

            if ( state.backtracking==0 ) {

                        retval.etypes = list_d;
                    
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException re) {
            errorHandler.reportError(this, re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "decorators"

    public static class funcdef_return extends ParserRuleReturnScope {
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "funcdef"
    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:451:1: funcdef : ( decorators )? DEF NAME parameters COLON suite[false] ;
    public final PythonParser.funcdef_return funcdef() throws RecognitionException {
        PythonParser.funcdef_return retval = new PythonParser.funcdef_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token DEF26=null;
        Token NAME27=null;
        Token COLON29=null;
        PythonParser.decorators_return decorators25 = null;

        PythonParser.parameters_return parameters28 = null;

        PythonParser.suite_return suite30 = null;


        PythonTree DEF26_tree=null;
        PythonTree NAME27_tree=null;
        PythonTree COLON29_tree=null;


            stmt stype = null;

        try {
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:459:5: ( ( decorators )? DEF NAME parameters COLON suite[false] )
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:459:7: ( decorators )? DEF NAME parameters COLON suite[false]
            {
            root_0 = (PythonTree)adaptor.nil();

            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:459:7: ( decorators )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==AT) ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:459:7: decorators
                    {
                    pushFollow(FOLLOW_decorators_in_funcdef870);
                    decorators25=decorators();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, decorators25.getTree());

                    }
                    break;

            }

            DEF26=(Token)match(input,DEF,FOLLOW_DEF_in_funcdef873); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            DEF26_tree = (PythonTree)adaptor.create(DEF26);
            adaptor.addChild(root_0, DEF26_tree);
            }
            NAME27=(Token)match(input,NAME,FOLLOW_NAME_in_funcdef875); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            NAME27_tree = (PythonTree)adaptor.create(NAME27);
            adaptor.addChild(root_0, NAME27_tree);
            }
            pushFollow(FOLLOW_parameters_in_funcdef877);
            parameters28=parameters();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, parameters28.getTree());
            COLON29=(Token)match(input,COLON,FOLLOW_COLON_in_funcdef879); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            COLON29_tree = (PythonTree)adaptor.create(COLON29);
            adaptor.addChild(root_0, COLON29_tree);
            }
            pushFollow(FOLLOW_suite_in_funcdef881);
            suite30=suite(false);

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, suite30.getTree());
            if ( state.backtracking==0 ) {

                      Token t = DEF26;
                      if ((decorators25!=null?((Token)decorators25.start):null) != null) {
                          t = (decorators25!=null?((Token)decorators25.start):null);
                      }
                      stype = actions.makeFuncdef(t, NAME27, (parameters28!=null?parameters28.args:null), (suite30!=null?suite30.stypes:null), (decorators25!=null?decorators25.etypes:null));
                  
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {

                  retval.tree = stype;

            }
        }

        catch (RecognitionException re) {
            errorHandler.reportError(this, re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "funcdef"

    public static class parameters_return extends ParserRuleReturnScope {
        public arguments args;
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "parameters"
    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:470:1: parameters returns [arguments args] : LPAREN ( varargslist | ) RPAREN ;
    public final PythonParser.parameters_return parameters() throws RecognitionException {
        PythonParser.parameters_return retval = new PythonParser.parameters_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token LPAREN31=null;
        Token RPAREN33=null;
        PythonParser.varargslist_return varargslist32 = null;


        PythonTree LPAREN31_tree=null;
        PythonTree RPAREN33_tree=null;

        try {
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:472:5: ( LPAREN ( varargslist | ) RPAREN )
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:472:7: LPAREN ( varargslist | ) RPAREN
            {
            root_0 = (PythonTree)adaptor.nil();

            LPAREN31=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_parameters914); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            LPAREN31_tree = (PythonTree)adaptor.create(LPAREN31);
            adaptor.addChild(root_0, LPAREN31_tree);
            }
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:473:7: ( varargslist | )
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==NAME||LA15_0==LPAREN||(LA15_0>=STAR && LA15_0<=DOUBLESTAR)) ) {
                alt15=1;
            }
            else if ( (LA15_0==RPAREN) ) {
                alt15=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 15, 0, input);

                throw nvae;
            }
            switch (alt15) {
                case 1 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:473:8: varargslist
                    {
                    pushFollow(FOLLOW_varargslist_in_parameters923);
                    varargslist32=varargslist();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, varargslist32.getTree());
                    if ( state.backtracking==0 ) {

                                    retval.args = (varargslist32!=null?varargslist32.args:null);
                              
                    }

                    }
                    break;
                case 2 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:478:9: 
                    {
                    if ( state.backtracking==0 ) {

                                  retval.args = new arguments(((Token)retval.start), new ArrayList<expr>(), null, null, new ArrayList<expr>());
                              
                    }

                    }
                    break;

            }

            RPAREN33=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_parameters967); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            RPAREN33_tree = (PythonTree)adaptor.create(RPAREN33);
            adaptor.addChild(root_0, RPAREN33_tree);
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException re) {
            errorHandler.reportError(this, re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "parameters"

    public static class defparameter_return extends ParserRuleReturnScope {
        public expr etype;
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "defparameter"
    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:486:1: defparameter[List defaults] returns [expr etype] : fpdef[expr_contextType.Param] ( ASSIGN test[expr_contextType.Load] )? ;
    public final PythonParser.defparameter_return defparameter(List defaults) throws RecognitionException {
        PythonParser.defparameter_return retval = new PythonParser.defparameter_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token ASSIGN35=null;
        PythonParser.fpdef_return fpdef34 = null;

        PythonParser.test_return test36 = null;


        PythonTree ASSIGN35_tree=null;

        try {
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:491:5: ( fpdef[expr_contextType.Param] ( ASSIGN test[expr_contextType.Load] )? )
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:491:7: fpdef[expr_contextType.Param] ( ASSIGN test[expr_contextType.Load] )?
            {
            root_0 = (PythonTree)adaptor.nil();

            pushFollow(FOLLOW_fpdef_in_defparameter1000);
            fpdef34=fpdef(expr_contextType.Param);

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, fpdef34.getTree());
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:491:37: ( ASSIGN test[expr_contextType.Load] )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==ASSIGN) ) {
                alt16=1;
            }
            switch (alt16) {
                case 1 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:491:38: ASSIGN test[expr_contextType.Load]
                    {
                    ASSIGN35=(Token)match(input,ASSIGN,FOLLOW_ASSIGN_in_defparameter1004); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    ASSIGN35_tree = (PythonTree)adaptor.create(ASSIGN35);
                    adaptor.addChild(root_0, ASSIGN35_tree);
                    }
                    pushFollow(FOLLOW_test_in_defparameter1006);
                    test36=test(expr_contextType.Load);

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, test36.getTree());

                    }
                    break;

            }

            if ( state.backtracking==0 ) {

                        retval.etype = actions.castExpr((fpdef34!=null?((PythonTree)fpdef34.tree):null));
                        if (ASSIGN35 != null) {
                            defaults.add((test36!=null?((PythonTree)test36.tree):null));
                        } else if (!defaults.isEmpty()) {
                            throw new ParseException("non-default argument follows default argument", (fpdef34!=null?((PythonTree)fpdef34.tree):null));
                        }
                    
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {

                 retval.tree = retval.etype;

            }
        }

        catch (RecognitionException re) {
            errorHandler.reportError(this, re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "defparameter"

    public static class varargslist_return extends ParserRuleReturnScope {
        public arguments args;
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "varargslist"
    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:505:1: varargslist returns [arguments args] : (d+= defparameter[defaults] ( options {greedy=true; } : COMMA d+= defparameter[defaults] )* ( COMMA ( STAR starargs= NAME ( COMMA DOUBLESTAR kwargs= NAME )? | DOUBLESTAR kwargs= NAME )? )? | STAR starargs= NAME ( COMMA DOUBLESTAR kwargs= NAME )? | DOUBLESTAR kwargs= NAME );
    public final PythonParser.varargslist_return varargslist() throws RecognitionException {
        PythonParser.varargslist_return retval = new PythonParser.varargslist_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token starargs=null;
        Token kwargs=null;
        Token COMMA37=null;
        Token COMMA38=null;
        Token STAR39=null;
        Token COMMA40=null;
        Token DOUBLESTAR41=null;
        Token DOUBLESTAR42=null;
        Token STAR43=null;
        Token COMMA44=null;
        Token DOUBLESTAR45=null;
        Token DOUBLESTAR46=null;
        List list_d=null;
        PythonParser.defparameter_return d = null;
         d = null;
        PythonTree starargs_tree=null;
        PythonTree kwargs_tree=null;
        PythonTree COMMA37_tree=null;
        PythonTree COMMA38_tree=null;
        PythonTree STAR39_tree=null;
        PythonTree COMMA40_tree=null;
        PythonTree DOUBLESTAR41_tree=null;
        PythonTree DOUBLESTAR42_tree=null;
        PythonTree STAR43_tree=null;
        PythonTree COMMA44_tree=null;
        PythonTree DOUBLESTAR45_tree=null;
        PythonTree DOUBLESTAR46_tree=null;


            List defaults = new ArrayList();

        try {
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:510:5: (d+= defparameter[defaults] ( options {greedy=true; } : COMMA d+= defparameter[defaults] )* ( COMMA ( STAR starargs= NAME ( COMMA DOUBLESTAR kwargs= NAME )? | DOUBLESTAR kwargs= NAME )? )? | STAR starargs= NAME ( COMMA DOUBLESTAR kwargs= NAME )? | DOUBLESTAR kwargs= NAME )
            int alt22=3;
            switch ( input.LA(1) ) {
            case NAME:
            case LPAREN:
                {
                alt22=1;
                }
                break;
            case STAR:
                {
                alt22=2;
                }
                break;
            case DOUBLESTAR:
                {
                alt22=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 22, 0, input);

                throw nvae;
            }

            switch (alt22) {
                case 1 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:510:7: d+= defparameter[defaults] ( options {greedy=true; } : COMMA d+= defparameter[defaults] )* ( COMMA ( STAR starargs= NAME ( COMMA DOUBLESTAR kwargs= NAME )? | DOUBLESTAR kwargs= NAME )? )?
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    pushFollow(FOLLOW_defparameter_in_varargslist1052);
                    d=defparameter(defaults);

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, d.getTree());
                    if (list_d==null) list_d=new ArrayList();
                    list_d.add(d.getTree());

                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:510:33: ( options {greedy=true; } : COMMA d+= defparameter[defaults] )*
                    loop17:
                    do {
                        int alt17=2;
                        int LA17_0 = input.LA(1);

                        if ( (LA17_0==COMMA) ) {
                            int LA17_1 = input.LA(2);

                            if ( (LA17_1==NAME||LA17_1==LPAREN) ) {
                                alt17=1;
                            }


                        }


                        switch (alt17) {
                    	case 1 :
                    	    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:510:57: COMMA d+= defparameter[defaults]
                    	    {
                    	    COMMA37=(Token)match(input,COMMA,FOLLOW_COMMA_in_varargslist1063); if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) {
                    	    COMMA37_tree = (PythonTree)adaptor.create(COMMA37);
                    	    adaptor.addChild(root_0, COMMA37_tree);
                    	    }
                    	    pushFollow(FOLLOW_defparameter_in_varargslist1067);
                    	    d=defparameter(defaults);

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) adaptor.addChild(root_0, d.getTree());
                    	    if (list_d==null) list_d=new ArrayList();
                    	    list_d.add(d.getTree());


                    	    }
                    	    break;

                    	default :
                    	    break loop17;
                        }
                    } while (true);

                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:511:7: ( COMMA ( STAR starargs= NAME ( COMMA DOUBLESTAR kwargs= NAME )? | DOUBLESTAR kwargs= NAME )? )?
                    int alt20=2;
                    int LA20_0 = input.LA(1);

                    if ( (LA20_0==COMMA) ) {
                        alt20=1;
                    }
                    switch (alt20) {
                        case 1 :
                            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:511:8: COMMA ( STAR starargs= NAME ( COMMA DOUBLESTAR kwargs= NAME )? | DOUBLESTAR kwargs= NAME )?
                            {
                            COMMA38=(Token)match(input,COMMA,FOLLOW_COMMA_in_varargslist1079); if (state.failed) return retval;
                            if ( state.backtracking==0 ) {
                            COMMA38_tree = (PythonTree)adaptor.create(COMMA38);
                            adaptor.addChild(root_0, COMMA38_tree);
                            }
                            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:512:11: ( STAR starargs= NAME ( COMMA DOUBLESTAR kwargs= NAME )? | DOUBLESTAR kwargs= NAME )?
                            int alt19=3;
                            int LA19_0 = input.LA(1);

                            if ( (LA19_0==STAR) ) {
                                alt19=1;
                            }
                            else if ( (LA19_0==DOUBLESTAR) ) {
                                alt19=2;
                            }
                            switch (alt19) {
                                case 1 :
                                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:512:12: STAR starargs= NAME ( COMMA DOUBLESTAR kwargs= NAME )?
                                    {
                                    STAR39=(Token)match(input,STAR,FOLLOW_STAR_in_varargslist1092); if (state.failed) return retval;
                                    if ( state.backtracking==0 ) {
                                    STAR39_tree = (PythonTree)adaptor.create(STAR39);
                                    adaptor.addChild(root_0, STAR39_tree);
                                    }
                                    starargs=(Token)match(input,NAME,FOLLOW_NAME_in_varargslist1096); if (state.failed) return retval;
                                    if ( state.backtracking==0 ) {
                                    starargs_tree = (PythonTree)adaptor.create(starargs);
                                    adaptor.addChild(root_0, starargs_tree);
                                    }
                                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:512:31: ( COMMA DOUBLESTAR kwargs= NAME )?
                                    int alt18=2;
                                    int LA18_0 = input.LA(1);

                                    if ( (LA18_0==COMMA) ) {
                                        alt18=1;
                                    }
                                    switch (alt18) {
                                        case 1 :
                                            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:512:32: COMMA DOUBLESTAR kwargs= NAME
                                            {
                                            COMMA40=(Token)match(input,COMMA,FOLLOW_COMMA_in_varargslist1099); if (state.failed) return retval;
                                            if ( state.backtracking==0 ) {
                                            COMMA40_tree = (PythonTree)adaptor.create(COMMA40);
                                            adaptor.addChild(root_0, COMMA40_tree);
                                            }
                                            DOUBLESTAR41=(Token)match(input,DOUBLESTAR,FOLLOW_DOUBLESTAR_in_varargslist1101); if (state.failed) return retval;
                                            if ( state.backtracking==0 ) {
                                            DOUBLESTAR41_tree = (PythonTree)adaptor.create(DOUBLESTAR41);
                                            adaptor.addChild(root_0, DOUBLESTAR41_tree);
                                            }
                                            kwargs=(Token)match(input,NAME,FOLLOW_NAME_in_varargslist1105); if (state.failed) return retval;
                                            if ( state.backtracking==0 ) {
                                            kwargs_tree = (PythonTree)adaptor.create(kwargs);
                                            adaptor.addChild(root_0, kwargs_tree);
                                            }

                                            }
                                            break;

                                    }


                                    }
                                    break;
                                case 2 :
                                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:513:13: DOUBLESTAR kwargs= NAME
                                    {
                                    DOUBLESTAR42=(Token)match(input,DOUBLESTAR,FOLLOW_DOUBLESTAR_in_varargslist1121); if (state.failed) return retval;
                                    if ( state.backtracking==0 ) {
                                    DOUBLESTAR42_tree = (PythonTree)adaptor.create(DOUBLESTAR42);
                                    adaptor.addChild(root_0, DOUBLESTAR42_tree);
                                    }
                                    kwargs=(Token)match(input,NAME,FOLLOW_NAME_in_varargslist1125); if (state.failed) return retval;
                                    if ( state.backtracking==0 ) {
                                    kwargs_tree = (PythonTree)adaptor.create(kwargs);
                                    adaptor.addChild(root_0, kwargs_tree);
                                    }

                                    }
                                    break;

                            }


                            }
                            break;

                    }

                    if ( state.backtracking==0 ) {

                                retval.args = actions.makeArgumentsType(((Token)retval.start), list_d, starargs, kwargs, defaults);
                            
                    }

                    }
                    break;
                case 2 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:519:7: STAR starargs= NAME ( COMMA DOUBLESTAR kwargs= NAME )?
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    STAR43=(Token)match(input,STAR,FOLLOW_STAR_in_varargslist1163); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    STAR43_tree = (PythonTree)adaptor.create(STAR43);
                    adaptor.addChild(root_0, STAR43_tree);
                    }
                    starargs=(Token)match(input,NAME,FOLLOW_NAME_in_varargslist1167); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    starargs_tree = (PythonTree)adaptor.create(starargs);
                    adaptor.addChild(root_0, starargs_tree);
                    }
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:519:26: ( COMMA DOUBLESTAR kwargs= NAME )?
                    int alt21=2;
                    int LA21_0 = input.LA(1);

                    if ( (LA21_0==COMMA) ) {
                        alt21=1;
                    }
                    switch (alt21) {
                        case 1 :
                            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:519:27: COMMA DOUBLESTAR kwargs= NAME
                            {
                            COMMA44=(Token)match(input,COMMA,FOLLOW_COMMA_in_varargslist1170); if (state.failed) return retval;
                            if ( state.backtracking==0 ) {
                            COMMA44_tree = (PythonTree)adaptor.create(COMMA44);
                            adaptor.addChild(root_0, COMMA44_tree);
                            }
                            DOUBLESTAR45=(Token)match(input,DOUBLESTAR,FOLLOW_DOUBLESTAR_in_varargslist1172); if (state.failed) return retval;
                            if ( state.backtracking==0 ) {
                            DOUBLESTAR45_tree = (PythonTree)adaptor.create(DOUBLESTAR45);
                            adaptor.addChild(root_0, DOUBLESTAR45_tree);
                            }
                            kwargs=(Token)match(input,NAME,FOLLOW_NAME_in_varargslist1176); if (state.failed) return retval;
                            if ( state.backtracking==0 ) {
                            kwargs_tree = (PythonTree)adaptor.create(kwargs);
                            adaptor.addChild(root_0, kwargs_tree);
                            }

                            }
                            break;

                    }

                    if ( state.backtracking==0 ) {

                                retval.args = actions.makeArgumentsType(((Token)retval.start), list_d, starargs, kwargs, defaults);
                            
                    }

                    }
                    break;
                case 3 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:523:7: DOUBLESTAR kwargs= NAME
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    DOUBLESTAR46=(Token)match(input,DOUBLESTAR,FOLLOW_DOUBLESTAR_in_varargslist1194); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    DOUBLESTAR46_tree = (PythonTree)adaptor.create(DOUBLESTAR46);
                    adaptor.addChild(root_0, DOUBLESTAR46_tree);
                    }
                    kwargs=(Token)match(input,NAME,FOLLOW_NAME_in_varargslist1198); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    kwargs_tree = (PythonTree)adaptor.create(kwargs);
                    adaptor.addChild(root_0, kwargs_tree);
                    }
                    if ( state.backtracking==0 ) {

                                retval.args = actions.makeArgumentsType(((Token)retval.start), list_d, null, kwargs, defaults);
                            
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException re) {
            errorHandler.reportError(this, re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "varargslist"

    public static class fpdef_return extends ParserRuleReturnScope {
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "fpdef"
    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:530:1: fpdef[expr_contextType ctype] : ( NAME | ( LPAREN fpdef[null] COMMA )=> LPAREN fplist RPAREN | LPAREN fplist RPAREN );
    public final PythonParser.fpdef_return fpdef(expr_contextType ctype) throws RecognitionException {
        PythonParser.fpdef_return retval = new PythonParser.fpdef_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token NAME47=null;
        Token LPAREN48=null;
        Token RPAREN50=null;
        Token LPAREN51=null;
        Token RPAREN53=null;
        PythonParser.fplist_return fplist49 = null;

        PythonParser.fplist_return fplist52 = null;


        PythonTree NAME47_tree=null;
        PythonTree LPAREN48_tree=null;
        PythonTree RPAREN50_tree=null;
        PythonTree LPAREN51_tree=null;
        PythonTree RPAREN53_tree=null;


            expr etype = null;

        try {
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:540:5: ( NAME | ( LPAREN fpdef[null] COMMA )=> LPAREN fplist RPAREN | LPAREN fplist RPAREN )
            int alt23=3;
            int LA23_0 = input.LA(1);

            if ( (LA23_0==NAME) ) {
                alt23=1;
            }
            else if ( (LA23_0==LPAREN) ) {
                int LA23_2 = input.LA(2);

                if ( (synpred1_Python()) ) {
                    alt23=2;
                }
                else if ( (true) ) {
                    alt23=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 23, 2, input);

                    throw nvae;
                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 23, 0, input);

                throw nvae;
            }
            switch (alt23) {
                case 1 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:540:7: NAME
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    NAME47=(Token)match(input,NAME,FOLLOW_NAME_in_fpdef1235); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    NAME47_tree = (PythonTree)adaptor.create(NAME47);
                    adaptor.addChild(root_0, NAME47_tree);
                    }
                    if ( state.backtracking==0 ) {

                                etype = new Name(NAME47, (NAME47!=null?NAME47.getText():null), ctype);
                            
                    }

                    }
                    break;
                case 2 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:544:7: ( LPAREN fpdef[null] COMMA )=> LPAREN fplist RPAREN
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    LPAREN48=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_fpdef1262); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    LPAREN48_tree = (PythonTree)adaptor.create(LPAREN48);
                    adaptor.addChild(root_0, LPAREN48_tree);
                    }
                    pushFollow(FOLLOW_fplist_in_fpdef1264);
                    fplist49=fplist();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, fplist49.getTree());
                    RPAREN50=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_fpdef1266); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    RPAREN50_tree = (PythonTree)adaptor.create(RPAREN50);
                    adaptor.addChild(root_0, RPAREN50_tree);
                    }
                    if ( state.backtracking==0 ) {

                                etype = new Tuple((fplist49!=null?((Token)fplist49.start):null), actions.castExprs((fplist49!=null?fplist49.etypes:null)), expr_contextType.Store);
                            
                    }

                    }
                    break;
                case 3 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:548:7: LPAREN fplist RPAREN
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    LPAREN51=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_fpdef1282); if (state.failed) return retval;
                    pushFollow(FOLLOW_fplist_in_fpdef1285);
                    fplist52=fplist();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, fplist52.getTree());
                    RPAREN53=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_fpdef1287); if (state.failed) return retval;

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {

                  if (etype != null) {
                      retval.tree = etype;
                  }
                  actions.checkAssign(actions.castExpr(((PythonTree)retval.tree)));

            }
        }

        catch (RecognitionException re) {
            errorHandler.reportError(this, re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "fpdef"

    public static class fplist_return extends ParserRuleReturnScope {
        public List etypes;
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "fplist"
    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:552:1: fplist returns [List etypes] : f+= fpdef[expr_contextType.Store] ( options {greedy=true; } : COMMA f+= fpdef[expr_contextType.Store] )* ( COMMA )? ;
    public final PythonParser.fplist_return fplist() throws RecognitionException {
        PythonParser.fplist_return retval = new PythonParser.fplist_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token COMMA54=null;
        Token COMMA55=null;
        List list_f=null;
        PythonParser.fpdef_return f = null;
         f = null;
        PythonTree COMMA54_tree=null;
        PythonTree COMMA55_tree=null;

        try {
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:554:5: (f+= fpdef[expr_contextType.Store] ( options {greedy=true; } : COMMA f+= fpdef[expr_contextType.Store] )* ( COMMA )? )
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:554:7: f+= fpdef[expr_contextType.Store] ( options {greedy=true; } : COMMA f+= fpdef[expr_contextType.Store] )* ( COMMA )?
            {
            root_0 = (PythonTree)adaptor.nil();

            pushFollow(FOLLOW_fpdef_in_fplist1316);
            f=fpdef(expr_contextType.Store);

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, f.getTree());
            if (list_f==null) list_f=new ArrayList();
            list_f.add(f.getTree());

            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:555:7: ( options {greedy=true; } : COMMA f+= fpdef[expr_contextType.Store] )*
            loop24:
            do {
                int alt24=2;
                int LA24_0 = input.LA(1);

                if ( (LA24_0==COMMA) ) {
                    int LA24_1 = input.LA(2);

                    if ( (LA24_1==NAME||LA24_1==LPAREN) ) {
                        alt24=1;
                    }


                }


                switch (alt24) {
            	case 1 :
            	    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:555:31: COMMA f+= fpdef[expr_contextType.Store]
            	    {
            	    COMMA54=(Token)match(input,COMMA,FOLLOW_COMMA_in_fplist1333); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    COMMA54_tree = (PythonTree)adaptor.create(COMMA54);
            	    adaptor.addChild(root_0, COMMA54_tree);
            	    }
            	    pushFollow(FOLLOW_fpdef_in_fplist1337);
            	    f=fpdef(expr_contextType.Store);

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, f.getTree());
            	    if (list_f==null) list_f=new ArrayList();
            	    list_f.add(f.getTree());


            	    }
            	    break;

            	default :
            	    break loop24;
                }
            } while (true);

            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:555:72: ( COMMA )?
            int alt25=2;
            int LA25_0 = input.LA(1);

            if ( (LA25_0==COMMA) ) {
                alt25=1;
            }
            switch (alt25) {
                case 1 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:555:73: COMMA
                    {
                    COMMA55=(Token)match(input,COMMA,FOLLOW_COMMA_in_fplist1343); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    COMMA55_tree = (PythonTree)adaptor.create(COMMA55);
                    adaptor.addChild(root_0, COMMA55_tree);
                    }

                    }
                    break;

            }

            if ( state.backtracking==0 ) {

                        retval.etypes = list_f;
                    
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException re) {
            errorHandler.reportError(this, re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "fplist"

    public static class stmt_return extends ParserRuleReturnScope {
        public List stypes;
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "stmt"
    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:562:1: stmt returns [List stypes] : ( simple_stmt | compound_stmt );
    public final PythonParser.stmt_return stmt() throws RecognitionException {
        PythonParser.stmt_return retval = new PythonParser.stmt_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        PythonParser.simple_stmt_return simple_stmt56 = null;

        PythonParser.compound_stmt_return compound_stmt57 = null;



        try {
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:564:5: ( simple_stmt | compound_stmt )
            int alt26=2;
            int LA26_0 = input.LA(1);

            if ( (LA26_0==NAME||(LA26_0>=ASSERT && LA26_0<=BREAK)||LA26_0==CONTINUE||LA26_0==DELETE||LA26_0==EXEC||LA26_0==FROM||LA26_0==GLOBAL||LA26_0==IMPORT||(LA26_0>=LAMBDA && LA26_0<=NOT)||(LA26_0>=PASS && LA26_0<=RETURN)||LA26_0==YIELD||LA26_0==LPAREN||(LA26_0>=PLUS && LA26_0<=MINUS)||(LA26_0>=TILDE && LA26_0<=LBRACK)||LA26_0==LCURLY||(LA26_0>=BACKQUOTE && LA26_0<=STRING)) ) {
                alt26=1;
            }
            else if ( (LA26_0==CLASS||LA26_0==DEF||LA26_0==FOR||LA26_0==IF||(LA26_0>=TRY && LA26_0<=WITH)||LA26_0==AT) ) {
                alt26=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 26, 0, input);

                throw nvae;
            }
            switch (alt26) {
                case 1 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:564:7: simple_stmt
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    pushFollow(FOLLOW_simple_stmt_in_stmt1379);
                    simple_stmt56=simple_stmt();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, simple_stmt56.getTree());
                    if ( state.backtracking==0 ) {

                                retval.stypes = (simple_stmt56!=null?simple_stmt56.stypes:null);
                            
                    }

                    }
                    break;
                case 2 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:568:7: compound_stmt
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    pushFollow(FOLLOW_compound_stmt_in_stmt1395);
                    compound_stmt57=compound_stmt();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, compound_stmt57.getTree());
                    if ( state.backtracking==0 ) {

                                retval.stypes = new ArrayList();
                                retval.stypes.add((compound_stmt57!=null?((PythonTree)compound_stmt57.tree):null));
                            
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException re) {
            errorHandler.reportError(this, re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "stmt"

    public static class simple_stmt_return extends ParserRuleReturnScope {
        public List stypes;
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "simple_stmt"
    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:576:1: simple_stmt returns [List stypes] : s+= small_stmt ( options {greedy=true; } : SEMI s+= small_stmt )* ( SEMI )? NEWLINE ;
    public final PythonParser.simple_stmt_return simple_stmt() throws RecognitionException {
        PythonParser.simple_stmt_return retval = new PythonParser.simple_stmt_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token SEMI58=null;
        Token SEMI59=null;
        Token NEWLINE60=null;
        List list_s=null;
        PythonParser.small_stmt_return s = null;
         s = null;
        PythonTree SEMI58_tree=null;
        PythonTree SEMI59_tree=null;
        PythonTree NEWLINE60_tree=null;

        try {
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:578:5: (s+= small_stmt ( options {greedy=true; } : SEMI s+= small_stmt )* ( SEMI )? NEWLINE )
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:578:7: s+= small_stmt ( options {greedy=true; } : SEMI s+= small_stmt )* ( SEMI )? NEWLINE
            {
            root_0 = (PythonTree)adaptor.nil();

            pushFollow(FOLLOW_small_stmt_in_simple_stmt1431);
            s=small_stmt();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, s.getTree());
            if (list_s==null) list_s=new ArrayList();
            list_s.add(s.getTree());

            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:578:21: ( options {greedy=true; } : SEMI s+= small_stmt )*
            loop27:
            do {
                int alt27=2;
                int LA27_0 = input.LA(1);

                if ( (LA27_0==SEMI) ) {
                    int LA27_1 = input.LA(2);

                    if ( (LA27_1==NAME||(LA27_1>=ASSERT && LA27_1<=BREAK)||LA27_1==CONTINUE||LA27_1==DELETE||LA27_1==EXEC||LA27_1==FROM||LA27_1==GLOBAL||LA27_1==IMPORT||(LA27_1>=LAMBDA && LA27_1<=NOT)||(LA27_1>=PASS && LA27_1<=RETURN)||LA27_1==YIELD||LA27_1==LPAREN||(LA27_1>=PLUS && LA27_1<=MINUS)||(LA27_1>=TILDE && LA27_1<=LBRACK)||LA27_1==LCURLY||(LA27_1>=BACKQUOTE && LA27_1<=STRING)) ) {
                        alt27=1;
                    }


                }


                switch (alt27) {
            	case 1 :
            	    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:578:45: SEMI s+= small_stmt
            	    {
            	    SEMI58=(Token)match(input,SEMI,FOLLOW_SEMI_in_simple_stmt1441); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    SEMI58_tree = (PythonTree)adaptor.create(SEMI58);
            	    adaptor.addChild(root_0, SEMI58_tree);
            	    }
            	    pushFollow(FOLLOW_small_stmt_in_simple_stmt1445);
            	    s=small_stmt();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, s.getTree());
            	    if (list_s==null) list_s=new ArrayList();
            	    list_s.add(s.getTree());


            	    }
            	    break;

            	default :
            	    break loop27;
                }
            } while (true);

            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:578:66: ( SEMI )?
            int alt28=2;
            int LA28_0 = input.LA(1);

            if ( (LA28_0==SEMI) ) {
                alt28=1;
            }
            switch (alt28) {
                case 1 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:578:67: SEMI
                    {
                    SEMI59=(Token)match(input,SEMI,FOLLOW_SEMI_in_simple_stmt1450); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    SEMI59_tree = (PythonTree)adaptor.create(SEMI59);
                    adaptor.addChild(root_0, SEMI59_tree);
                    }

                    }
                    break;

            }

            NEWLINE60=(Token)match(input,NEWLINE,FOLLOW_NEWLINE_in_simple_stmt1454); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            NEWLINE60_tree = (PythonTree)adaptor.create(NEWLINE60);
            adaptor.addChild(root_0, NEWLINE60_tree);
            }
            if ( state.backtracking==0 ) {

                        retval.stypes = list_s;
                    
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException re) {
            errorHandler.reportError(this, re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "simple_stmt"

    public static class small_stmt_return extends ParserRuleReturnScope {
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "small_stmt"
    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:586:1: small_stmt : ( expr_stmt | print_stmt | del_stmt | pass_stmt | flow_stmt | import_stmt | global_stmt | exec_stmt | assert_stmt );
    public final PythonParser.small_stmt_return small_stmt() throws RecognitionException {
        PythonParser.small_stmt_return retval = new PythonParser.small_stmt_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        PythonParser.expr_stmt_return expr_stmt61 = null;

        PythonParser.print_stmt_return print_stmt62 = null;

        PythonParser.del_stmt_return del_stmt63 = null;

        PythonParser.pass_stmt_return pass_stmt64 = null;

        PythonParser.flow_stmt_return flow_stmt65 = null;

        PythonParser.import_stmt_return import_stmt66 = null;

        PythonParser.global_stmt_return global_stmt67 = null;

        PythonParser.exec_stmt_return exec_stmt68 = null;

        PythonParser.assert_stmt_return assert_stmt69 = null;



        try {
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:586:12: ( expr_stmt | print_stmt | del_stmt | pass_stmt | flow_stmt | import_stmt | global_stmt | exec_stmt | assert_stmt )
            int alt29=9;
            alt29 = dfa29.predict(input);
            switch (alt29) {
                case 1 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:586:14: expr_stmt
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    pushFollow(FOLLOW_expr_stmt_in_small_stmt1477);
                    expr_stmt61=expr_stmt();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, expr_stmt61.getTree());

                    }
                    break;
                case 2 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:587:14: print_stmt
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    pushFollow(FOLLOW_print_stmt_in_small_stmt1492);
                    print_stmt62=print_stmt();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, print_stmt62.getTree());

                    }
                    break;
                case 3 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:588:14: del_stmt
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    pushFollow(FOLLOW_del_stmt_in_small_stmt1507);
                    del_stmt63=del_stmt();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, del_stmt63.getTree());

                    }
                    break;
                case 4 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:589:14: pass_stmt
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    pushFollow(FOLLOW_pass_stmt_in_small_stmt1522);
                    pass_stmt64=pass_stmt();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, pass_stmt64.getTree());

                    }
                    break;
                case 5 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:590:14: flow_stmt
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    pushFollow(FOLLOW_flow_stmt_in_small_stmt1537);
                    flow_stmt65=flow_stmt();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, flow_stmt65.getTree());

                    }
                    break;
                case 6 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:591:14: import_stmt
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    pushFollow(FOLLOW_import_stmt_in_small_stmt1552);
                    import_stmt66=import_stmt();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, import_stmt66.getTree());

                    }
                    break;
                case 7 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:592:14: global_stmt
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    pushFollow(FOLLOW_global_stmt_in_small_stmt1567);
                    global_stmt67=global_stmt();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, global_stmt67.getTree());

                    }
                    break;
                case 8 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:593:14: exec_stmt
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    pushFollow(FOLLOW_exec_stmt_in_small_stmt1582);
                    exec_stmt68=exec_stmt();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, exec_stmt68.getTree());

                    }
                    break;
                case 9 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:594:14: assert_stmt
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    pushFollow(FOLLOW_assert_stmt_in_small_stmt1597);
                    assert_stmt69=assert_stmt();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, assert_stmt69.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException re) {
            errorHandler.reportError(this, re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "small_stmt"

    public static class expr_stmt_return extends ParserRuleReturnScope {
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "expr_stmt"
    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:599:1: expr_stmt : ( ( testlist[null] augassign )=>lhs= testlist[expr_contextType.AugStore] ( (aay= augassign y1= yield_expr ) | (aat= augassign rhs= testlist[expr_contextType.Load] ) ) | ( testlist[null] ASSIGN )=>lhs= testlist[expr_contextType.Store] ( | ( (at= ASSIGN t+= testlist[expr_contextType.Store] )+ ) | ( (ay= ASSIGN y2+= yield_expr )+ ) ) | lhs= testlist[expr_contextType.Load] ) ;
    public final PythonParser.expr_stmt_return expr_stmt() throws RecognitionException {
        PythonParser.expr_stmt_return retval = new PythonParser.expr_stmt_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token at=null;
        Token ay=null;
        List list_t=null;
        List list_y2=null;
        PythonParser.testlist_return lhs = null;

        PythonParser.augassign_return aay = null;

        PythonParser.yield_expr_return y1 = null;

        PythonParser.augassign_return aat = null;

        PythonParser.testlist_return rhs = null;

        PythonParser.testlist_return t = null;
         t = null;
        PythonParser.yield_expr_return y2 = null;
         y2 = null;
        PythonTree at_tree=null;
        PythonTree ay_tree=null;


            stmt stype = null;

        try {
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:608:5: ( ( ( testlist[null] augassign )=>lhs= testlist[expr_contextType.AugStore] ( (aay= augassign y1= yield_expr ) | (aat= augassign rhs= testlist[expr_contextType.Load] ) ) | ( testlist[null] ASSIGN )=>lhs= testlist[expr_contextType.Store] ( | ( (at= ASSIGN t+= testlist[expr_contextType.Store] )+ ) | ( (ay= ASSIGN y2+= yield_expr )+ ) ) | lhs= testlist[expr_contextType.Load] ) )
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:608:7: ( ( testlist[null] augassign )=>lhs= testlist[expr_contextType.AugStore] ( (aay= augassign y1= yield_expr ) | (aat= augassign rhs= testlist[expr_contextType.Load] ) ) | ( testlist[null] ASSIGN )=>lhs= testlist[expr_contextType.Store] ( | ( (at= ASSIGN t+= testlist[expr_contextType.Store] )+ ) | ( (ay= ASSIGN y2+= yield_expr )+ ) ) | lhs= testlist[expr_contextType.Load] )
            {
            root_0 = (PythonTree)adaptor.nil();

            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:608:7: ( ( testlist[null] augassign )=>lhs= testlist[expr_contextType.AugStore] ( (aay= augassign y1= yield_expr ) | (aat= augassign rhs= testlist[expr_contextType.Load] ) ) | ( testlist[null] ASSIGN )=>lhs= testlist[expr_contextType.Store] ( | ( (at= ASSIGN t+= testlist[expr_contextType.Store] )+ ) | ( (ay= ASSIGN y2+= yield_expr )+ ) ) | lhs= testlist[expr_contextType.Load] )
            int alt34=3;
            alt34 = dfa34.predict(input);
            switch (alt34) {
                case 1 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:608:8: ( testlist[null] augassign )=>lhs= testlist[expr_contextType.AugStore] ( (aay= augassign y1= yield_expr ) | (aat= augassign rhs= testlist[expr_contextType.Load] ) )
                    {
                    pushFollow(FOLLOW_testlist_in_expr_stmt1645);
                    lhs=testlist(expr_contextType.AugStore);

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, lhs.getTree());
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:609:9: ( (aay= augassign y1= yield_expr ) | (aat= augassign rhs= testlist[expr_contextType.Load] ) )
                    int alt30=2;
                    alt30 = dfa30.predict(input);
                    switch (alt30) {
                        case 1 :
                            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:609:11: (aay= augassign y1= yield_expr )
                            {
                            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:609:11: (aay= augassign y1= yield_expr )
                            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:609:12: aay= augassign y1= yield_expr
                            {
                            pushFollow(FOLLOW_augassign_in_expr_stmt1661);
                            aay=augassign();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, aay.getTree());
                            pushFollow(FOLLOW_yield_expr_in_expr_stmt1665);
                            y1=yield_expr();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, y1.getTree());
                            if ( state.backtracking==0 ) {

                                             actions.checkAssign(actions.castExpr((lhs!=null?((PythonTree)lhs.tree):null)));
                                             stype = new AugAssign((lhs!=null?((PythonTree)lhs.tree):null), actions.castExpr((lhs!=null?((PythonTree)lhs.tree):null)), (aay!=null?aay.op:null), actions.castExpr((y1!=null?y1.etype:null)));
                                         
                            }

                            }


                            }
                            break;
                        case 2 :
                            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:615:11: (aat= augassign rhs= testlist[expr_contextType.Load] )
                            {
                            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:615:11: (aat= augassign rhs= testlist[expr_contextType.Load] )
                            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:615:12: aat= augassign rhs= testlist[expr_contextType.Load]
                            {
                            pushFollow(FOLLOW_augassign_in_expr_stmt1705);
                            aat=augassign();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, aat.getTree());
                            pushFollow(FOLLOW_testlist_in_expr_stmt1709);
                            rhs=testlist(expr_contextType.Load);

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, rhs.getTree());
                            if ( state.backtracking==0 ) {

                                             actions.checkAssign(actions.castExpr((lhs!=null?((PythonTree)lhs.tree):null)));
                                             stype = new AugAssign((lhs!=null?((PythonTree)lhs.tree):null), actions.castExpr((lhs!=null?((PythonTree)lhs.tree):null)), (aat!=null?aat.op:null), actions.castExpr((rhs!=null?((PythonTree)rhs.tree):null)));
                                         
                            }

                            }


                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:622:7: ( testlist[null] ASSIGN )=>lhs= testlist[expr_contextType.Store] ( | ( (at= ASSIGN t+= testlist[expr_contextType.Store] )+ ) | ( (ay= ASSIGN y2+= yield_expr )+ ) )
                    {
                    pushFollow(FOLLOW_testlist_in_expr_stmt1764);
                    lhs=testlist(expr_contextType.Store);

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, lhs.getTree());
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:623:9: ( | ( (at= ASSIGN t+= testlist[expr_contextType.Store] )+ ) | ( (ay= ASSIGN y2+= yield_expr )+ ) )
                    int alt33=3;
                    int LA33_0 = input.LA(1);

                    if ( (LA33_0==NEWLINE||LA33_0==SEMI) ) {
                        alt33=1;
                    }
                    else if ( (LA33_0==ASSIGN) ) {
                        int LA33_2 = input.LA(2);

                        if ( (LA33_2==YIELD) ) {
                            alt33=3;
                        }
                        else if ( (LA33_2==NAME||(LA33_2>=LAMBDA && LA33_2<=NOT)||LA33_2==LPAREN||(LA33_2>=PLUS && LA33_2<=MINUS)||(LA33_2>=TILDE && LA33_2<=LBRACK)||LA33_2==LCURLY||(LA33_2>=BACKQUOTE && LA33_2<=STRING)) ) {
                            alt33=2;
                        }
                        else {
                            if (state.backtracking>0) {state.failed=true; return retval;}
                            NoViableAltException nvae =
                                new NoViableAltException("", 33, 2, input);

                            throw nvae;
                        }
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 33, 0, input);

                        throw nvae;
                    }
                    switch (alt33) {
                        case 1 :
                            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:624:9: 
                            {
                            }
                            break;
                        case 2 :
                            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:624:11: ( (at= ASSIGN t+= testlist[expr_contextType.Store] )+ )
                            {
                            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:624:11: ( (at= ASSIGN t+= testlist[expr_contextType.Store] )+ )
                            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:624:12: (at= ASSIGN t+= testlist[expr_contextType.Store] )+
                            {
                            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:624:12: (at= ASSIGN t+= testlist[expr_contextType.Store] )+
                            int cnt31=0;
                            loop31:
                            do {
                                int alt31=2;
                                int LA31_0 = input.LA(1);

                                if ( (LA31_0==ASSIGN) ) {
                                    alt31=1;
                                }


                                switch (alt31) {
                            	case 1 :
                            	    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:624:13: at= ASSIGN t+= testlist[expr_contextType.Store]
                            	    {
                            	    at=(Token)match(input,ASSIGN,FOLLOW_ASSIGN_in_expr_stmt1791); if (state.failed) return retval;
                            	    if ( state.backtracking==0 ) {
                            	    at_tree = (PythonTree)adaptor.create(at);
                            	    adaptor.addChild(root_0, at_tree);
                            	    }
                            	    pushFollow(FOLLOW_testlist_in_expr_stmt1795);
                            	    t=testlist(expr_contextType.Store);

                            	    state._fsp--;
                            	    if (state.failed) return retval;
                            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, t.getTree());
                            	    if (list_t==null) list_t=new ArrayList();
                            	    list_t.add(t.getTree());


                            	    }
                            	    break;

                            	default :
                            	    if ( cnt31 >= 1 ) break loop31;
                            	    if (state.backtracking>0) {state.failed=true; return retval;}
                                        EarlyExitException eee =
                                            new EarlyExitException(31, input);
                                        throw eee;
                                }
                                cnt31++;
                            } while (true);

                            if ( state.backtracking==0 ) {

                                              stype = new Assign((lhs!=null?((PythonTree)lhs.tree):null), actions.makeAssignTargets(
                                                  actions.castExpr((lhs!=null?((PythonTree)lhs.tree):null)), list_t), actions.makeAssignValue(list_t));
                                          
                            }

                            }


                            }
                            break;
                        case 3 :
                            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:630:11: ( (ay= ASSIGN y2+= yield_expr )+ )
                            {
                            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:630:11: ( (ay= ASSIGN y2+= yield_expr )+ )
                            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:630:12: (ay= ASSIGN y2+= yield_expr )+
                            {
                            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:630:12: (ay= ASSIGN y2+= yield_expr )+
                            int cnt32=0;
                            loop32:
                            do {
                                int alt32=2;
                                int LA32_0 = input.LA(1);

                                if ( (LA32_0==ASSIGN) ) {
                                    alt32=1;
                                }


                                switch (alt32) {
                            	case 1 :
                            	    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:630:13: ay= ASSIGN y2+= yield_expr
                            	    {
                            	    ay=(Token)match(input,ASSIGN,FOLLOW_ASSIGN_in_expr_stmt1840); if (state.failed) return retval;
                            	    if ( state.backtracking==0 ) {
                            	    ay_tree = (PythonTree)adaptor.create(ay);
                            	    adaptor.addChild(root_0, ay_tree);
                            	    }
                            	    pushFollow(FOLLOW_yield_expr_in_expr_stmt1844);
                            	    y2=yield_expr();

                            	    state._fsp--;
                            	    if (state.failed) return retval;
                            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, y2.getTree());
                            	    if (list_y2==null) list_y2=new ArrayList();
                            	    list_y2.add(y2.getTree());


                            	    }
                            	    break;

                            	default :
                            	    if ( cnt32 >= 1 ) break loop32;
                            	    if (state.backtracking>0) {state.failed=true; return retval;}
                                        EarlyExitException eee =
                                            new EarlyExitException(32, input);
                                        throw eee;
                                }
                                cnt32++;
                            } while (true);

                            if ( state.backtracking==0 ) {

                                              stype = new Assign((lhs!=null?((Token)lhs.start):null), actions.makeAssignTargets(
                                                  actions.castExpr((lhs!=null?((PythonTree)lhs.tree):null)), list_y2), actions.makeAssignValue(list_y2));
                                          
                            }

                            }


                            }
                            break;

                    }


                    }
                    break;
                case 3 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:637:7: lhs= testlist[expr_contextType.Load]
                    {
                    pushFollow(FOLLOW_testlist_in_expr_stmt1892);
                    lhs=testlist(expr_contextType.Load);

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, lhs.getTree());
                    if ( state.backtracking==0 ) {

                                stype = new Expr((lhs!=null?((Token)lhs.start):null), actions.castExpr((lhs!=null?((PythonTree)lhs.tree):null)));
                            
                    }

                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {

                  if (stype != null) {
                      retval.tree = stype;
                  }

            }
        }

        catch (RecognitionException re) {
            errorHandler.reportError(this, re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "expr_stmt"

    public static class augassign_return extends ParserRuleReturnScope {
        public operatorType op;
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "augassign"
    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:646:1: augassign returns [operatorType op] : ( PLUSEQUAL | MINUSEQUAL | STAREQUAL | SLASHEQUAL | PERCENTEQUAL | AMPEREQUAL | VBAREQUAL | CIRCUMFLEXEQUAL | LEFTSHIFTEQUAL | RIGHTSHIFTEQUAL | DOUBLESTAREQUAL | DOUBLESLASHEQUAL );
    public final PythonParser.augassign_return augassign() throws RecognitionException {
        PythonParser.augassign_return retval = new PythonParser.augassign_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token PLUSEQUAL70=null;
        Token MINUSEQUAL71=null;
        Token STAREQUAL72=null;
        Token SLASHEQUAL73=null;
        Token PERCENTEQUAL74=null;
        Token AMPEREQUAL75=null;
        Token VBAREQUAL76=null;
        Token CIRCUMFLEXEQUAL77=null;
        Token LEFTSHIFTEQUAL78=null;
        Token RIGHTSHIFTEQUAL79=null;
        Token DOUBLESTAREQUAL80=null;
        Token DOUBLESLASHEQUAL81=null;

        PythonTree PLUSEQUAL70_tree=null;
        PythonTree MINUSEQUAL71_tree=null;
        PythonTree STAREQUAL72_tree=null;
        PythonTree SLASHEQUAL73_tree=null;
        PythonTree PERCENTEQUAL74_tree=null;
        PythonTree AMPEREQUAL75_tree=null;
        PythonTree VBAREQUAL76_tree=null;
        PythonTree CIRCUMFLEXEQUAL77_tree=null;
        PythonTree LEFTSHIFTEQUAL78_tree=null;
        PythonTree RIGHTSHIFTEQUAL79_tree=null;
        PythonTree DOUBLESTAREQUAL80_tree=null;
        PythonTree DOUBLESLASHEQUAL81_tree=null;

        try {
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:648:5: ( PLUSEQUAL | MINUSEQUAL | STAREQUAL | SLASHEQUAL | PERCENTEQUAL | AMPEREQUAL | VBAREQUAL | CIRCUMFLEXEQUAL | LEFTSHIFTEQUAL | RIGHTSHIFTEQUAL | DOUBLESTAREQUAL | DOUBLESLASHEQUAL )
            int alt35=12;
            switch ( input.LA(1) ) {
            case PLUSEQUAL:
                {
                alt35=1;
                }
                break;
            case MINUSEQUAL:
                {
                alt35=2;
                }
                break;
            case STAREQUAL:
                {
                alt35=3;
                }
                break;
            case SLASHEQUAL:
                {
                alt35=4;
                }
                break;
            case PERCENTEQUAL:
                {
                alt35=5;
                }
                break;
            case AMPEREQUAL:
                {
                alt35=6;
                }
                break;
            case VBAREQUAL:
                {
                alt35=7;
                }
                break;
            case CIRCUMFLEXEQUAL:
                {
                alt35=8;
                }
                break;
            case LEFTSHIFTEQUAL:
                {
                alt35=9;
                }
                break;
            case RIGHTSHIFTEQUAL:
                {
                alt35=10;
                }
                break;
            case DOUBLESTAREQUAL:
                {
                alt35=11;
                }
                break;
            case DOUBLESLASHEQUAL:
                {
                alt35=12;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 35, 0, input);

                throw nvae;
            }

            switch (alt35) {
                case 1 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:648:7: PLUSEQUAL
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    PLUSEQUAL70=(Token)match(input,PLUSEQUAL,FOLLOW_PLUSEQUAL_in_augassign1934); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    PLUSEQUAL70_tree = (PythonTree)adaptor.create(PLUSEQUAL70);
                    adaptor.addChild(root_0, PLUSEQUAL70_tree);
                    }
                    if ( state.backtracking==0 ) {

                                  retval.op = operatorType.Add;
                              
                    }

                    }
                    break;
                case 2 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:652:7: MINUSEQUAL
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    MINUSEQUAL71=(Token)match(input,MINUSEQUAL,FOLLOW_MINUSEQUAL_in_augassign1952); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    MINUSEQUAL71_tree = (PythonTree)adaptor.create(MINUSEQUAL71);
                    adaptor.addChild(root_0, MINUSEQUAL71_tree);
                    }
                    if ( state.backtracking==0 ) {

                                  retval.op = operatorType.Sub;
                              
                    }

                    }
                    break;
                case 3 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:656:7: STAREQUAL
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    STAREQUAL72=(Token)match(input,STAREQUAL,FOLLOW_STAREQUAL_in_augassign1970); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    STAREQUAL72_tree = (PythonTree)adaptor.create(STAREQUAL72);
                    adaptor.addChild(root_0, STAREQUAL72_tree);
                    }
                    if ( state.backtracking==0 ) {

                                  retval.op = operatorType.Mult;
                              
                    }

                    }
                    break;
                case 4 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:660:7: SLASHEQUAL
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    SLASHEQUAL73=(Token)match(input,SLASHEQUAL,FOLLOW_SLASHEQUAL_in_augassign1988); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    SLASHEQUAL73_tree = (PythonTree)adaptor.create(SLASHEQUAL73);
                    adaptor.addChild(root_0, SLASHEQUAL73_tree);
                    }
                    if ( state.backtracking==0 ) {

                                  retval.op = operatorType.Div;
                              
                    }

                    }
                    break;
                case 5 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:664:7: PERCENTEQUAL
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    PERCENTEQUAL74=(Token)match(input,PERCENTEQUAL,FOLLOW_PERCENTEQUAL_in_augassign2006); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    PERCENTEQUAL74_tree = (PythonTree)adaptor.create(PERCENTEQUAL74);
                    adaptor.addChild(root_0, PERCENTEQUAL74_tree);
                    }
                    if ( state.backtracking==0 ) {

                                  retval.op = operatorType.Mod;
                              
                    }

                    }
                    break;
                case 6 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:668:7: AMPEREQUAL
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    AMPEREQUAL75=(Token)match(input,AMPEREQUAL,FOLLOW_AMPEREQUAL_in_augassign2024); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    AMPEREQUAL75_tree = (PythonTree)adaptor.create(AMPEREQUAL75);
                    adaptor.addChild(root_0, AMPEREQUAL75_tree);
                    }
                    if ( state.backtracking==0 ) {

                                  retval.op = operatorType.BitAnd;
                              
                    }

                    }
                    break;
                case 7 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:672:7: VBAREQUAL
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    VBAREQUAL76=(Token)match(input,VBAREQUAL,FOLLOW_VBAREQUAL_in_augassign2042); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    VBAREQUAL76_tree = (PythonTree)adaptor.create(VBAREQUAL76);
                    adaptor.addChild(root_0, VBAREQUAL76_tree);
                    }
                    if ( state.backtracking==0 ) {

                                  retval.op = operatorType.BitOr;
                              
                    }

                    }
                    break;
                case 8 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:676:7: CIRCUMFLEXEQUAL
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    CIRCUMFLEXEQUAL77=(Token)match(input,CIRCUMFLEXEQUAL,FOLLOW_CIRCUMFLEXEQUAL_in_augassign2060); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    CIRCUMFLEXEQUAL77_tree = (PythonTree)adaptor.create(CIRCUMFLEXEQUAL77);
                    adaptor.addChild(root_0, CIRCUMFLEXEQUAL77_tree);
                    }
                    if ( state.backtracking==0 ) {

                                  retval.op = operatorType.BitXor;
                              
                    }

                    }
                    break;
                case 9 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:680:7: LEFTSHIFTEQUAL
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    LEFTSHIFTEQUAL78=(Token)match(input,LEFTSHIFTEQUAL,FOLLOW_LEFTSHIFTEQUAL_in_augassign2078); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    LEFTSHIFTEQUAL78_tree = (PythonTree)adaptor.create(LEFTSHIFTEQUAL78);
                    adaptor.addChild(root_0, LEFTSHIFTEQUAL78_tree);
                    }
                    if ( state.backtracking==0 ) {

                                  retval.op = operatorType.LShift;
                              
                    }

                    }
                    break;
                case 10 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:684:7: RIGHTSHIFTEQUAL
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    RIGHTSHIFTEQUAL79=(Token)match(input,RIGHTSHIFTEQUAL,FOLLOW_RIGHTSHIFTEQUAL_in_augassign2096); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    RIGHTSHIFTEQUAL79_tree = (PythonTree)adaptor.create(RIGHTSHIFTEQUAL79);
                    adaptor.addChild(root_0, RIGHTSHIFTEQUAL79_tree);
                    }
                    if ( state.backtracking==0 ) {

                                  retval.op = operatorType.RShift;
                              
                    }

                    }
                    break;
                case 11 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:688:7: DOUBLESTAREQUAL
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    DOUBLESTAREQUAL80=(Token)match(input,DOUBLESTAREQUAL,FOLLOW_DOUBLESTAREQUAL_in_augassign2114); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    DOUBLESTAREQUAL80_tree = (PythonTree)adaptor.create(DOUBLESTAREQUAL80);
                    adaptor.addChild(root_0, DOUBLESTAREQUAL80_tree);
                    }
                    if ( state.backtracking==0 ) {

                                  retval.op = operatorType.Pow;
                              
                    }

                    }
                    break;
                case 12 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:692:7: DOUBLESLASHEQUAL
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    DOUBLESLASHEQUAL81=(Token)match(input,DOUBLESLASHEQUAL,FOLLOW_DOUBLESLASHEQUAL_in_augassign2132); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    DOUBLESLASHEQUAL81_tree = (PythonTree)adaptor.create(DOUBLESLASHEQUAL81);
                    adaptor.addChild(root_0, DOUBLESLASHEQUAL81_tree);
                    }
                    if ( state.backtracking==0 ) {

                                  retval.op = operatorType.FloorDiv;
                              
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException re) {
            errorHandler.reportError(this, re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "augassign"

    public static class print_stmt_return extends ParserRuleReturnScope {
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "print_stmt"
    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:700:1: print_stmt : PRINT (t1= printlist | RIGHTSHIFT t2= printlist2 | ) ;
    public final PythonParser.print_stmt_return print_stmt() throws RecognitionException {
        PythonParser.print_stmt_return retval = new PythonParser.print_stmt_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token PRINT82=null;
        Token RIGHTSHIFT83=null;
        PythonParser.printlist_return t1 = null;

        PythonParser.printlist2_return t2 = null;


        PythonTree PRINT82_tree=null;
        PythonTree RIGHTSHIFT83_tree=null;


            stmt stype = null;

        try {
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:708:5: ( PRINT (t1= printlist | RIGHTSHIFT t2= printlist2 | ) )
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:708:7: PRINT (t1= printlist | RIGHTSHIFT t2= printlist2 | )
            {
            root_0 = (PythonTree)adaptor.nil();

            PRINT82=(Token)match(input,PRINT,FOLLOW_PRINT_in_print_stmt2172); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            PRINT82_tree = (PythonTree)adaptor.create(PRINT82);
            adaptor.addChild(root_0, PRINT82_tree);
            }
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:709:7: (t1= printlist | RIGHTSHIFT t2= printlist2 | )
            int alt36=3;
            switch ( input.LA(1) ) {
            case NAME:
            case LAMBDA:
            case NOT:
            case LPAREN:
            case PLUS:
            case MINUS:
            case TILDE:
            case LBRACK:
            case LCURLY:
            case BACKQUOTE:
            case INT:
            case LONGINT:
            case FLOAT:
            case COMPLEX:
            case STRING:
                {
                alt36=1;
                }
                break;
            case RIGHTSHIFT:
                {
                alt36=2;
                }
                break;
            case NEWLINE:
            case SEMI:
                {
                alt36=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 36, 0, input);

                throw nvae;
            }

            switch (alt36) {
                case 1 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:709:8: t1= printlist
                    {
                    pushFollow(FOLLOW_printlist_in_print_stmt2183);
                    t1=printlist();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, t1.getTree());
                    if ( state.backtracking==0 ) {

                                 stype = new Print(PRINT82, null, actions.castExprs((t1!=null?t1.elts:null)), (t1!=null?t1.newline:false));
                             
                    }

                    }
                    break;
                case 2 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:713:9: RIGHTSHIFT t2= printlist2
                    {
                    RIGHTSHIFT83=(Token)match(input,RIGHTSHIFT,FOLLOW_RIGHTSHIFT_in_print_stmt2202); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    RIGHTSHIFT83_tree = (PythonTree)adaptor.create(RIGHTSHIFT83);
                    adaptor.addChild(root_0, RIGHTSHIFT83_tree);
                    }
                    pushFollow(FOLLOW_printlist2_in_print_stmt2206);
                    t2=printlist2();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, t2.getTree());
                    if ( state.backtracking==0 ) {

                                 stype = new Print(PRINT82, actions.castExpr((t2!=null?t2.elts:null).get(0)), actions.castExprs((t2!=null?t2.elts:null), 1), (t2!=null?t2.newline:false));
                             
                    }

                    }
                    break;
                case 3 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:718:8: 
                    {
                    if ( state.backtracking==0 ) {

                                 stype = new Print(PRINT82, null, new ArrayList<expr>(), true);
                             
                    }

                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {

                  retval.tree = stype;

            }
        }

        catch (RecognitionException re) {
            errorHandler.reportError(this, re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "print_stmt"

    public static class printlist_return extends ParserRuleReturnScope {
        public boolean newline;
        public List elts;
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "printlist"
    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:725:1: printlist returns [boolean newline, List elts] : ( ( test[null] COMMA )=>t+= test[expr_contextType.Load] ( options {k=2; } : COMMA t+= test[expr_contextType.Load] )* (trailcomma= COMMA )? | t+= test[expr_contextType.Load] );
    public final PythonParser.printlist_return printlist() throws RecognitionException {
        PythonParser.printlist_return retval = new PythonParser.printlist_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token trailcomma=null;
        Token COMMA84=null;
        List list_t=null;
        PythonParser.test_return t = null;
         t = null;
        PythonTree trailcomma_tree=null;
        PythonTree COMMA84_tree=null;

        try {
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:727:5: ( ( test[null] COMMA )=>t+= test[expr_contextType.Load] ( options {k=2; } : COMMA t+= test[expr_contextType.Load] )* (trailcomma= COMMA )? | t+= test[expr_contextType.Load] )
            int alt39=2;
            alt39 = dfa39.predict(input);
            switch (alt39) {
                case 1 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:727:7: ( test[null] COMMA )=>t+= test[expr_contextType.Load] ( options {k=2; } : COMMA t+= test[expr_contextType.Load] )* (trailcomma= COMMA )?
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    pushFollow(FOLLOW_test_in_printlist2286);
                    t=test(expr_contextType.Load);

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, t.getTree());
                    if (list_t==null) list_t=new ArrayList();
                    list_t.add(t.getTree());

                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:728:39: ( options {k=2; } : COMMA t+= test[expr_contextType.Load] )*
                    loop37:
                    do {
                        int alt37=2;
                        alt37 = dfa37.predict(input);
                        switch (alt37) {
                    	case 1 :
                    	    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:728:56: COMMA t+= test[expr_contextType.Load]
                    	    {
                    	    COMMA84=(Token)match(input,COMMA,FOLLOW_COMMA_in_printlist2298); if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) {
                    	    COMMA84_tree = (PythonTree)adaptor.create(COMMA84);
                    	    adaptor.addChild(root_0, COMMA84_tree);
                    	    }
                    	    pushFollow(FOLLOW_test_in_printlist2302);
                    	    t=test(expr_contextType.Load);

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) adaptor.addChild(root_0, t.getTree());
                    	    if (list_t==null) list_t=new ArrayList();
                    	    list_t.add(t.getTree());


                    	    }
                    	    break;

                    	default :
                    	    break loop37;
                        }
                    } while (true);

                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:728:95: (trailcomma= COMMA )?
                    int alt38=2;
                    int LA38_0 = input.LA(1);

                    if ( (LA38_0==COMMA) ) {
                        alt38=1;
                    }
                    switch (alt38) {
                        case 1 :
                            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:728:96: trailcomma= COMMA
                            {
                            trailcomma=(Token)match(input,COMMA,FOLLOW_COMMA_in_printlist2310); if (state.failed) return retval;
                            if ( state.backtracking==0 ) {
                            trailcomma_tree = (PythonTree)adaptor.create(trailcomma);
                            adaptor.addChild(root_0, trailcomma_tree);
                            }

                            }
                            break;

                    }

                    if ( state.backtracking==0 ) {

                                 retval.elts =list_t;
                                 if (trailcomma == null) {
                                     retval.newline = true;
                                 } else {
                                     retval.newline = false;
                                 }
                             
                    }

                    }
                    break;
                case 2 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:737:7: t+= test[expr_contextType.Load]
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    pushFollow(FOLLOW_test_in_printlist2331);
                    t=test(expr_contextType.Load);

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, t.getTree());
                    if (list_t==null) list_t=new ArrayList();
                    list_t.add(t.getTree());

                    if ( state.backtracking==0 ) {

                                retval.elts =list_t;
                                retval.newline = true;
                            
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException re) {
            errorHandler.reportError(this, re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "printlist"

    public static class printlist2_return extends ParserRuleReturnScope {
        public boolean newline;
        public List elts;
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "printlist2"
    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:746:1: printlist2 returns [boolean newline, List elts] : ( ( test[null] COMMA test[null] )=>t+= test[expr_contextType.Load] ( options {k=2; } : COMMA t+= test[expr_contextType.Load] )* (trailcomma= COMMA )? | t+= test[expr_contextType.Load] );
    public final PythonParser.printlist2_return printlist2() throws RecognitionException {
        PythonParser.printlist2_return retval = new PythonParser.printlist2_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token trailcomma=null;
        Token COMMA85=null;
        List list_t=null;
        PythonParser.test_return t = null;
         t = null;
        PythonTree trailcomma_tree=null;
        PythonTree COMMA85_tree=null;

        try {
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:748:5: ( ( test[null] COMMA test[null] )=>t+= test[expr_contextType.Load] ( options {k=2; } : COMMA t+= test[expr_contextType.Load] )* (trailcomma= COMMA )? | t+= test[expr_contextType.Load] )
            int alt42=2;
            alt42 = dfa42.predict(input);
            switch (alt42) {
                case 1 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:748:7: ( test[null] COMMA test[null] )=>t+= test[expr_contextType.Load] ( options {k=2; } : COMMA t+= test[expr_contextType.Load] )* (trailcomma= COMMA )?
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    pushFollow(FOLLOW_test_in_printlist22388);
                    t=test(expr_contextType.Load);

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, t.getTree());
                    if (list_t==null) list_t=new ArrayList();
                    list_t.add(t.getTree());

                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:749:39: ( options {k=2; } : COMMA t+= test[expr_contextType.Load] )*
                    loop40:
                    do {
                        int alt40=2;
                        alt40 = dfa40.predict(input);
                        switch (alt40) {
                    	case 1 :
                    	    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:749:56: COMMA t+= test[expr_contextType.Load]
                    	    {
                    	    COMMA85=(Token)match(input,COMMA,FOLLOW_COMMA_in_printlist22400); if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) {
                    	    COMMA85_tree = (PythonTree)adaptor.create(COMMA85);
                    	    adaptor.addChild(root_0, COMMA85_tree);
                    	    }
                    	    pushFollow(FOLLOW_test_in_printlist22404);
                    	    t=test(expr_contextType.Load);

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) adaptor.addChild(root_0, t.getTree());
                    	    if (list_t==null) list_t=new ArrayList();
                    	    list_t.add(t.getTree());


                    	    }
                    	    break;

                    	default :
                    	    break loop40;
                        }
                    } while (true);

                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:749:95: (trailcomma= COMMA )?
                    int alt41=2;
                    int LA41_0 = input.LA(1);

                    if ( (LA41_0==COMMA) ) {
                        alt41=1;
                    }
                    switch (alt41) {
                        case 1 :
                            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:749:96: trailcomma= COMMA
                            {
                            trailcomma=(Token)match(input,COMMA,FOLLOW_COMMA_in_printlist22412); if (state.failed) return retval;
                            if ( state.backtracking==0 ) {
                            trailcomma_tree = (PythonTree)adaptor.create(trailcomma);
                            adaptor.addChild(root_0, trailcomma_tree);
                            }

                            }
                            break;

                    }

                    if ( state.backtracking==0 ) {
                       retval.elts =list_t;
                                 if (trailcomma == null) {
                                     retval.newline = true;
                                 } else {
                                     retval.newline = false;
                                 }
                             
                    }

                    }
                    break;
                case 2 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:757:7: t+= test[expr_contextType.Load]
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    pushFollow(FOLLOW_test_in_printlist22433);
                    t=test(expr_contextType.Load);

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, t.getTree());
                    if (list_t==null) list_t=new ArrayList();
                    list_t.add(t.getTree());

                    if ( state.backtracking==0 ) {

                                retval.elts =list_t;
                                retval.newline = true;
                            
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException re) {
            errorHandler.reportError(this, re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "printlist2"

    public static class del_stmt_return extends ParserRuleReturnScope {
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "del_stmt"
    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:765:1: del_stmt : DELETE del_list ;
    public final PythonParser.del_stmt_return del_stmt() throws RecognitionException {
        PythonParser.del_stmt_return retval = new PythonParser.del_stmt_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token DELETE86=null;
        PythonParser.del_list_return del_list87 = null;


        PythonTree DELETE86_tree=null;


            stmt stype = null;

        try {
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:772:5: ( DELETE del_list )
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:772:7: DELETE del_list
            {
            root_0 = (PythonTree)adaptor.nil();

            DELETE86=(Token)match(input,DELETE,FOLLOW_DELETE_in_del_stmt2470); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            DELETE86_tree = (PythonTree)adaptor.create(DELETE86);
            adaptor.addChild(root_0, DELETE86_tree);
            }
            pushFollow(FOLLOW_del_list_in_del_stmt2472);
            del_list87=del_list();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, del_list87.getTree());
            if ( state.backtracking==0 ) {

                        stype = new Delete(DELETE86, (del_list87!=null?del_list87.etypes:null));
                    
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {

                 retval.tree = stype;

            }
        }

        catch (RecognitionException re) {
            errorHandler.reportError(this, re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "del_stmt"

    public static class pass_stmt_return extends ParserRuleReturnScope {
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "pass_stmt"
    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:779:1: pass_stmt : PASS ;
    public final PythonParser.pass_stmt_return pass_stmt() throws RecognitionException {
        PythonParser.pass_stmt_return retval = new PythonParser.pass_stmt_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token PASS88=null;

        PythonTree PASS88_tree=null;


            stmt stype = null;

        try {
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:786:5: ( PASS )
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:786:7: PASS
            {
            root_0 = (PythonTree)adaptor.nil();

            PASS88=(Token)match(input,PASS,FOLLOW_PASS_in_pass_stmt2508); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            PASS88_tree = (PythonTree)adaptor.create(PASS88);
            adaptor.addChild(root_0, PASS88_tree);
            }
            if ( state.backtracking==0 ) {

                        stype = new Pass(PASS88);
                    
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {

                 retval.tree = stype;

            }
        }

        catch (RecognitionException re) {
            errorHandler.reportError(this, re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "pass_stmt"

    public static class flow_stmt_return extends ParserRuleReturnScope {
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "flow_stmt"
    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:793:1: flow_stmt : ( break_stmt | continue_stmt | return_stmt | raise_stmt | yield_stmt );
    public final PythonParser.flow_stmt_return flow_stmt() throws RecognitionException {
        PythonParser.flow_stmt_return retval = new PythonParser.flow_stmt_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        PythonParser.break_stmt_return break_stmt89 = null;

        PythonParser.continue_stmt_return continue_stmt90 = null;

        PythonParser.return_stmt_return return_stmt91 = null;

        PythonParser.raise_stmt_return raise_stmt92 = null;

        PythonParser.yield_stmt_return yield_stmt93 = null;



        try {
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:794:5: ( break_stmt | continue_stmt | return_stmt | raise_stmt | yield_stmt )
            int alt43=5;
            switch ( input.LA(1) ) {
            case BREAK:
                {
                alt43=1;
                }
                break;
            case CONTINUE:
                {
                alt43=2;
                }
                break;
            case RETURN:
                {
                alt43=3;
                }
                break;
            case RAISE:
                {
                alt43=4;
                }
                break;
            case YIELD:
                {
                alt43=5;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 43, 0, input);

                throw nvae;
            }

            switch (alt43) {
                case 1 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:794:7: break_stmt
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    pushFollow(FOLLOW_break_stmt_in_flow_stmt2534);
                    break_stmt89=break_stmt();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, break_stmt89.getTree());

                    }
                    break;
                case 2 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:795:7: continue_stmt
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    pushFollow(FOLLOW_continue_stmt_in_flow_stmt2542);
                    continue_stmt90=continue_stmt();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, continue_stmt90.getTree());

                    }
                    break;
                case 3 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:796:7: return_stmt
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    pushFollow(FOLLOW_return_stmt_in_flow_stmt2550);
                    return_stmt91=return_stmt();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, return_stmt91.getTree());

                    }
                    break;
                case 4 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:797:7: raise_stmt
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    pushFollow(FOLLOW_raise_stmt_in_flow_stmt2558);
                    raise_stmt92=raise_stmt();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, raise_stmt92.getTree());

                    }
                    break;
                case 5 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:798:7: yield_stmt
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    pushFollow(FOLLOW_yield_stmt_in_flow_stmt2566);
                    yield_stmt93=yield_stmt();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, yield_stmt93.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException re) {
            errorHandler.reportError(this, re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "flow_stmt"

    public static class break_stmt_return extends ParserRuleReturnScope {
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "break_stmt"
    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:802:1: break_stmt : BREAK ;
    public final PythonParser.break_stmt_return break_stmt() throws RecognitionException {
        PythonParser.break_stmt_return retval = new PythonParser.break_stmt_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token BREAK94=null;

        PythonTree BREAK94_tree=null;


            stmt stype = null;

        try {
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:809:5: ( BREAK )
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:809:7: BREAK
            {
            root_0 = (PythonTree)adaptor.nil();

            BREAK94=(Token)match(input,BREAK,FOLLOW_BREAK_in_break_stmt2594); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            BREAK94_tree = (PythonTree)adaptor.create(BREAK94);
            adaptor.addChild(root_0, BREAK94_tree);
            }
            if ( state.backtracking==0 ) {

                        stype = new Break(BREAK94);
                    
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {

                 retval.tree = stype;

            }
        }

        catch (RecognitionException re) {
            errorHandler.reportError(this, re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "break_stmt"

    public static class continue_stmt_return extends ParserRuleReturnScope {
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "continue_stmt"
    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:816:1: continue_stmt : CONTINUE ;
    public final PythonParser.continue_stmt_return continue_stmt() throws RecognitionException {
        PythonParser.continue_stmt_return retval = new PythonParser.continue_stmt_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token CONTINUE95=null;

        PythonTree CONTINUE95_tree=null;


            stmt stype = null;

        try {
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:823:5: ( CONTINUE )
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:823:7: CONTINUE
            {
            root_0 = (PythonTree)adaptor.nil();

            CONTINUE95=(Token)match(input,CONTINUE,FOLLOW_CONTINUE_in_continue_stmt2630); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            CONTINUE95_tree = (PythonTree)adaptor.create(CONTINUE95);
            adaptor.addChild(root_0, CONTINUE95_tree);
            }
            if ( state.backtracking==0 ) {

                        if (!suite_stack.isEmpty() && ((suite_scope)suite_stack.peek()).continueIllegal) {
                            errorHandler.error("'continue' not supported inside 'finally' clause", new PythonTree(((Token)retval.start)));
                        }
                        stype = new Continue(CONTINUE95);
                    
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {

                 retval.tree = stype;

            }
        }

        catch (RecognitionException re) {
            errorHandler.reportError(this, re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "continue_stmt"

    public static class return_stmt_return extends ParserRuleReturnScope {
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "return_stmt"
    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:833:1: return_stmt : RETURN ( testlist[expr_contextType.Load] | ) ;
    public final PythonParser.return_stmt_return return_stmt() throws RecognitionException {
        PythonParser.return_stmt_return retval = new PythonParser.return_stmt_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token RETURN96=null;
        PythonParser.testlist_return testlist97 = null;


        PythonTree RETURN96_tree=null;


            stmt stype = null;

        try {
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:840:5: ( RETURN ( testlist[expr_contextType.Load] | ) )
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:840:7: RETURN ( testlist[expr_contextType.Load] | )
            {
            root_0 = (PythonTree)adaptor.nil();

            RETURN96=(Token)match(input,RETURN,FOLLOW_RETURN_in_return_stmt2666); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            RETURN96_tree = (PythonTree)adaptor.create(RETURN96);
            adaptor.addChild(root_0, RETURN96_tree);
            }
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:841:7: ( testlist[expr_contextType.Load] | )
            int alt44=2;
            int LA44_0 = input.LA(1);

            if ( (LA44_0==NAME||(LA44_0>=LAMBDA && LA44_0<=NOT)||LA44_0==LPAREN||(LA44_0>=PLUS && LA44_0<=MINUS)||(LA44_0>=TILDE && LA44_0<=LBRACK)||LA44_0==LCURLY||(LA44_0>=BACKQUOTE && LA44_0<=STRING)) ) {
                alt44=1;
            }
            else if ( (LA44_0==NEWLINE||LA44_0==SEMI) ) {
                alt44=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 44, 0, input);

                throw nvae;
            }
            switch (alt44) {
                case 1 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:841:8: testlist[expr_contextType.Load]
                    {
                    pushFollow(FOLLOW_testlist_in_return_stmt2675);
                    testlist97=testlist(expr_contextType.Load);

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, testlist97.getTree());
                    if ( state.backtracking==0 ) {

                                 stype = new Return(RETURN96, actions.castExpr((testlist97!=null?((PythonTree)testlist97.tree):null)));
                             
                    }

                    }
                    break;
                case 2 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:846:8: 
                    {
                    if ( state.backtracking==0 ) {

                                 stype = new Return(RETURN96, null);
                             
                    }

                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {

                 retval.tree = stype;

            }
        }

        catch (RecognitionException re) {
            errorHandler.reportError(this, re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "return_stmt"

    public static class yield_stmt_return extends ParserRuleReturnScope {
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "yield_stmt"
    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:853:1: yield_stmt : yield_expr ;
    public final PythonParser.yield_stmt_return yield_stmt() throws RecognitionException {
        PythonParser.yield_stmt_return retval = new PythonParser.yield_stmt_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        PythonParser.yield_expr_return yield_expr98 = null;




            stmt stype = null;

        try {
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:860:5: ( yield_expr )
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:860:7: yield_expr
            {
            root_0 = (PythonTree)adaptor.nil();

            pushFollow(FOLLOW_yield_expr_in_yield_stmt2740);
            yield_expr98=yield_expr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, yield_expr98.getTree());
            if ( state.backtracking==0 ) {

                      stype = new Expr((yield_expr98!=null?((Token)yield_expr98.start):null), actions.castExpr((yield_expr98!=null?yield_expr98.etype:null)));
                    
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {

                 retval.tree = stype;

            }
        }

        catch (RecognitionException re) {
            errorHandler.reportError(this, re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "yield_stmt"

    public static class raise_stmt_return extends ParserRuleReturnScope {
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "raise_stmt"
    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:867:1: raise_stmt : RAISE (t1= test[expr_contextType.Load] ( COMMA t2= test[expr_contextType.Load] ( COMMA t3= test[expr_contextType.Load] )? )? )? ;
    public final PythonParser.raise_stmt_return raise_stmt() throws RecognitionException {
        PythonParser.raise_stmt_return retval = new PythonParser.raise_stmt_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token RAISE99=null;
        Token COMMA100=null;
        Token COMMA101=null;
        PythonParser.test_return t1 = null;

        PythonParser.test_return t2 = null;

        PythonParser.test_return t3 = null;


        PythonTree RAISE99_tree=null;
        PythonTree COMMA100_tree=null;
        PythonTree COMMA101_tree=null;


            stmt stype = null;

        try {
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:874:5: ( RAISE (t1= test[expr_contextType.Load] ( COMMA t2= test[expr_contextType.Load] ( COMMA t3= test[expr_contextType.Load] )? )? )? )
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:874:7: RAISE (t1= test[expr_contextType.Load] ( COMMA t2= test[expr_contextType.Load] ( COMMA t3= test[expr_contextType.Load] )? )? )?
            {
            root_0 = (PythonTree)adaptor.nil();

            RAISE99=(Token)match(input,RAISE,FOLLOW_RAISE_in_raise_stmt2776); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            RAISE99_tree = (PythonTree)adaptor.create(RAISE99);
            adaptor.addChild(root_0, RAISE99_tree);
            }
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:874:13: (t1= test[expr_contextType.Load] ( COMMA t2= test[expr_contextType.Load] ( COMMA t3= test[expr_contextType.Load] )? )? )?
            int alt47=2;
            int LA47_0 = input.LA(1);

            if ( (LA47_0==NAME||(LA47_0>=LAMBDA && LA47_0<=NOT)||LA47_0==LPAREN||(LA47_0>=PLUS && LA47_0<=MINUS)||(LA47_0>=TILDE && LA47_0<=LBRACK)||LA47_0==LCURLY||(LA47_0>=BACKQUOTE && LA47_0<=STRING)) ) {
                alt47=1;
            }
            switch (alt47) {
                case 1 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:874:14: t1= test[expr_contextType.Load] ( COMMA t2= test[expr_contextType.Load] ( COMMA t3= test[expr_contextType.Load] )? )?
                    {
                    pushFollow(FOLLOW_test_in_raise_stmt2781);
                    t1=test(expr_contextType.Load);

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, t1.getTree());
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:874:45: ( COMMA t2= test[expr_contextType.Load] ( COMMA t3= test[expr_contextType.Load] )? )?
                    int alt46=2;
                    int LA46_0 = input.LA(1);

                    if ( (LA46_0==COMMA) ) {
                        alt46=1;
                    }
                    switch (alt46) {
                        case 1 :
                            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:874:46: COMMA t2= test[expr_contextType.Load] ( COMMA t3= test[expr_contextType.Load] )?
                            {
                            COMMA100=(Token)match(input,COMMA,FOLLOW_COMMA_in_raise_stmt2785); if (state.failed) return retval;
                            if ( state.backtracking==0 ) {
                            COMMA100_tree = (PythonTree)adaptor.create(COMMA100);
                            adaptor.addChild(root_0, COMMA100_tree);
                            }
                            pushFollow(FOLLOW_test_in_raise_stmt2789);
                            t2=test(expr_contextType.Load);

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, t2.getTree());
                            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:875:9: ( COMMA t3= test[expr_contextType.Load] )?
                            int alt45=2;
                            int LA45_0 = input.LA(1);

                            if ( (LA45_0==COMMA) ) {
                                alt45=1;
                            }
                            switch (alt45) {
                                case 1 :
                                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:875:10: COMMA t3= test[expr_contextType.Load]
                                    {
                                    COMMA101=(Token)match(input,COMMA,FOLLOW_COMMA_in_raise_stmt2801); if (state.failed) return retval;
                                    if ( state.backtracking==0 ) {
                                    COMMA101_tree = (PythonTree)adaptor.create(COMMA101);
                                    adaptor.addChild(root_0, COMMA101_tree);
                                    }
                                    pushFollow(FOLLOW_test_in_raise_stmt2805);
                                    t3=test(expr_contextType.Load);

                                    state._fsp--;
                                    if (state.failed) return retval;
                                    if ( state.backtracking==0 ) adaptor.addChild(root_0, t3.getTree());

                                    }
                                    break;

                            }


                            }
                            break;

                    }


                    }
                    break;

            }

            if ( state.backtracking==0 ) {

                        stype = new Raise(RAISE99, actions.castExpr((t1!=null?((PythonTree)t1.tree):null)), actions.castExpr((t2!=null?((PythonTree)t2.tree):null)), actions.castExpr((t3!=null?((PythonTree)t3.tree):null)));
                    
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {

                 retval.tree = stype;

            }
        }

        catch (RecognitionException re) {
            errorHandler.reportError(this, re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "raise_stmt"

    public static class import_stmt_return extends ParserRuleReturnScope {
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "import_stmt"
    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:882:1: import_stmt : ( import_name | import_from );
    public final PythonParser.import_stmt_return import_stmt() throws RecognitionException {
        PythonParser.import_stmt_return retval = new PythonParser.import_stmt_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        PythonParser.import_name_return import_name102 = null;

        PythonParser.import_from_return import_from103 = null;



        try {
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:883:5: ( import_name | import_from )
            int alt48=2;
            int LA48_0 = input.LA(1);

            if ( (LA48_0==IMPORT) ) {
                alt48=1;
            }
            else if ( (LA48_0==FROM) ) {
                alt48=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 48, 0, input);

                throw nvae;
            }
            switch (alt48) {
                case 1 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:883:7: import_name
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    pushFollow(FOLLOW_import_name_in_import_stmt2838);
                    import_name102=import_name();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, import_name102.getTree());

                    }
                    break;
                case 2 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:884:7: import_from
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    pushFollow(FOLLOW_import_from_in_import_stmt2846);
                    import_from103=import_from();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, import_from103.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException re) {
            errorHandler.reportError(this, re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "import_stmt"

    public static class import_name_return extends ParserRuleReturnScope {
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "import_name"
    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:888:1: import_name : IMPORT dotted_as_names ;
    public final PythonParser.import_name_return import_name() throws RecognitionException {
        PythonParser.import_name_return retval = new PythonParser.import_name_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token IMPORT104=null;
        PythonParser.dotted_as_names_return dotted_as_names105 = null;


        PythonTree IMPORT104_tree=null;


            stmt stype = null;

        try {
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:895:5: ( IMPORT dotted_as_names )
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:895:7: IMPORT dotted_as_names
            {
            root_0 = (PythonTree)adaptor.nil();

            IMPORT104=(Token)match(input,IMPORT,FOLLOW_IMPORT_in_import_name2874); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            IMPORT104_tree = (PythonTree)adaptor.create(IMPORT104);
            adaptor.addChild(root_0, IMPORT104_tree);
            }
            pushFollow(FOLLOW_dotted_as_names_in_import_name2876);
            dotted_as_names105=dotted_as_names();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, dotted_as_names105.getTree());
            if ( state.backtracking==0 ) {

                        stype = new Import(IMPORT104, (dotted_as_names105!=null?dotted_as_names105.atypes:null));
                    
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {

                 retval.tree = stype;

            }
        }

        catch (RecognitionException re) {
            errorHandler.reportError(this, re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "import_name"

    public static class import_from_return extends ParserRuleReturnScope {
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "import_from"
    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:903:1: import_from : FROM ( (d+= DOT )* dotted_name | (d+= DOT )+ ) IMPORT ( STAR | i1= import_as_names | LPAREN i2= import_as_names ( COMMA )? RPAREN ) ;
    public final PythonParser.import_from_return import_from() throws RecognitionException {
        PythonParser.import_from_return retval = new PythonParser.import_from_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token FROM106=null;
        Token IMPORT108=null;
        Token STAR109=null;
        Token LPAREN110=null;
        Token COMMA111=null;
        Token RPAREN112=null;
        Token d=null;
        List list_d=null;
        PythonParser.import_as_names_return i1 = null;

        PythonParser.import_as_names_return i2 = null;

        PythonParser.dotted_name_return dotted_name107 = null;


        PythonTree FROM106_tree=null;
        PythonTree IMPORT108_tree=null;
        PythonTree STAR109_tree=null;
        PythonTree LPAREN110_tree=null;
        PythonTree COMMA111_tree=null;
        PythonTree RPAREN112_tree=null;
        PythonTree d_tree=null;


            stmt stype = null;

        try {
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:910:5: ( FROM ( (d+= DOT )* dotted_name | (d+= DOT )+ ) IMPORT ( STAR | i1= import_as_names | LPAREN i2= import_as_names ( COMMA )? RPAREN ) )
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:910:7: FROM ( (d+= DOT )* dotted_name | (d+= DOT )+ ) IMPORT ( STAR | i1= import_as_names | LPAREN i2= import_as_names ( COMMA )? RPAREN )
            {
            root_0 = (PythonTree)adaptor.nil();

            FROM106=(Token)match(input,FROM,FOLLOW_FROM_in_import_from2913); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            FROM106_tree = (PythonTree)adaptor.create(FROM106);
            adaptor.addChild(root_0, FROM106_tree);
            }
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:910:12: ( (d+= DOT )* dotted_name | (d+= DOT )+ )
            int alt51=2;
            alt51 = dfa51.predict(input);
            switch (alt51) {
                case 1 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:910:13: (d+= DOT )* dotted_name
                    {
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:910:14: (d+= DOT )*
                    loop49:
                    do {
                        int alt49=2;
                        int LA49_0 = input.LA(1);

                        if ( (LA49_0==DOT) ) {
                            alt49=1;
                        }


                        switch (alt49) {
                    	case 1 :
                    	    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:910:14: d+= DOT
                    	    {
                    	    d=(Token)match(input,DOT,FOLLOW_DOT_in_import_from2918); if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) {
                    	    d_tree = (PythonTree)adaptor.create(d);
                    	    adaptor.addChild(root_0, d_tree);
                    	    }
                    	    if (list_d==null) list_d=new ArrayList();
                    	    list_d.add(d);


                    	    }
                    	    break;

                    	default :
                    	    break loop49;
                        }
                    } while (true);

                    pushFollow(FOLLOW_dotted_name_in_import_from2921);
                    dotted_name107=dotted_name();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, dotted_name107.getTree());

                    }
                    break;
                case 2 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:910:35: (d+= DOT )+
                    {
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:910:36: (d+= DOT )+
                    int cnt50=0;
                    loop50:
                    do {
                        int alt50=2;
                        int LA50_0 = input.LA(1);

                        if ( (LA50_0==DOT) ) {
                            alt50=1;
                        }


                        switch (alt50) {
                    	case 1 :
                    	    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:910:36: d+= DOT
                    	    {
                    	    d=(Token)match(input,DOT,FOLLOW_DOT_in_import_from2927); if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) {
                    	    d_tree = (PythonTree)adaptor.create(d);
                    	    adaptor.addChild(root_0, d_tree);
                    	    }
                    	    if (list_d==null) list_d=new ArrayList();
                    	    list_d.add(d);


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt50 >= 1 ) break loop50;
                    	    if (state.backtracking>0) {state.failed=true; return retval;}
                                EarlyExitException eee =
                                    new EarlyExitException(50, input);
                                throw eee;
                        }
                        cnt50++;
                    } while (true);


                    }
                    break;

            }

            IMPORT108=(Token)match(input,IMPORT,FOLLOW_IMPORT_in_import_from2931); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            IMPORT108_tree = (PythonTree)adaptor.create(IMPORT108);
            adaptor.addChild(root_0, IMPORT108_tree);
            }
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:911:9: ( STAR | i1= import_as_names | LPAREN i2= import_as_names ( COMMA )? RPAREN )
            int alt53=3;
            switch ( input.LA(1) ) {
            case STAR:
                {
                alt53=1;
                }
                break;
            case NAME:
                {
                alt53=2;
                }
                break;
            case LPAREN:
                {
                alt53=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 53, 0, input);

                throw nvae;
            }

            switch (alt53) {
                case 1 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:911:10: STAR
                    {
                    STAR109=(Token)match(input,STAR,FOLLOW_STAR_in_import_from2942); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    STAR109_tree = (PythonTree)adaptor.create(STAR109);
                    adaptor.addChild(root_0, STAR109_tree);
                    }
                    if ( state.backtracking==0 ) {

                                   stype = new ImportFrom(FROM106, actions.makeFromText(list_d, (dotted_name107!=null?dotted_name107.names:null)),
                                       actions.makeModuleNameNode(list_d, (dotted_name107!=null?dotted_name107.names:null)),
                                       actions.makeStarAlias(STAR109), actions.makeLevel(list_d));
                               
                    }

                    }
                    break;
                case 2 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:917:11: i1= import_as_names
                    {
                    pushFollow(FOLLOW_import_as_names_in_import_from2967);
                    i1=import_as_names();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, i1.getTree());
                    if ( state.backtracking==0 ) {

                                   stype = new ImportFrom(FROM106, actions.makeFromText(list_d, (dotted_name107!=null?dotted_name107.names:null)),
                                       actions.makeModuleNameNode(list_d, (dotted_name107!=null?dotted_name107.names:null)),
                                       actions.makeAliases((i1!=null?i1.atypes:null)), actions.makeLevel(list_d));
                               
                    }

                    }
                    break;
                case 3 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:923:11: LPAREN i2= import_as_names ( COMMA )? RPAREN
                    {
                    LPAREN110=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_import_from2990); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    LPAREN110_tree = (PythonTree)adaptor.create(LPAREN110);
                    adaptor.addChild(root_0, LPAREN110_tree);
                    }
                    pushFollow(FOLLOW_import_as_names_in_import_from2994);
                    i2=import_as_names();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, i2.getTree());
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:923:37: ( COMMA )?
                    int alt52=2;
                    int LA52_0 = input.LA(1);

                    if ( (LA52_0==COMMA) ) {
                        alt52=1;
                    }
                    switch (alt52) {
                        case 1 :
                            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:923:37: COMMA
                            {
                            COMMA111=(Token)match(input,COMMA,FOLLOW_COMMA_in_import_from2996); if (state.failed) return retval;
                            if ( state.backtracking==0 ) {
                            COMMA111_tree = (PythonTree)adaptor.create(COMMA111);
                            adaptor.addChild(root_0, COMMA111_tree);
                            }

                            }
                            break;

                    }

                    RPAREN112=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_import_from2999); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    RPAREN112_tree = (PythonTree)adaptor.create(RPAREN112);
                    adaptor.addChild(root_0, RPAREN112_tree);
                    }
                    if ( state.backtracking==0 ) {

                                   stype = new ImportFrom(FROM106, actions.makeFromText(list_d, (dotted_name107!=null?dotted_name107.names:null)),
                                       actions.makeModuleNameNode(list_d, (dotted_name107!=null?dotted_name107.names:null)),
                                       actions.makeAliases((i2!=null?i2.atypes:null)), actions.makeLevel(list_d));
                               
                    }

                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {

                 retval.tree = stype;

            }
        }

        catch (RecognitionException re) {
            errorHandler.reportError(this, re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "import_from"

    public static class import_as_names_return extends ParserRuleReturnScope {
        public List<alias> atypes;
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "import_as_names"
    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:933:1: import_as_names returns [List<alias> atypes] : n+= import_as_name ( COMMA n+= import_as_name )* ;
    public final PythonParser.import_as_names_return import_as_names() throws RecognitionException {
        PythonParser.import_as_names_return retval = new PythonParser.import_as_names_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token COMMA113=null;
        List list_n=null;
        PythonParser.import_as_name_return n = null;
         n = null;
        PythonTree COMMA113_tree=null;

        try {
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:935:5: (n+= import_as_name ( COMMA n+= import_as_name )* )
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:935:7: n+= import_as_name ( COMMA n+= import_as_name )*
            {
            root_0 = (PythonTree)adaptor.nil();

            pushFollow(FOLLOW_import_as_name_in_import_as_names3048);
            n=import_as_name();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, n.getTree());
            if (list_n==null) list_n=new ArrayList();
            list_n.add(n.getTree());

            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:935:25: ( COMMA n+= import_as_name )*
            loop54:
            do {
                int alt54=2;
                int LA54_0 = input.LA(1);

                if ( (LA54_0==COMMA) ) {
                    int LA54_2 = input.LA(2);

                    if ( (LA54_2==NAME) ) {
                        alt54=1;
                    }


                }


                switch (alt54) {
            	case 1 :
            	    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:935:26: COMMA n+= import_as_name
            	    {
            	    COMMA113=(Token)match(input,COMMA,FOLLOW_COMMA_in_import_as_names3051); if (state.failed) return retval;
            	    pushFollow(FOLLOW_import_as_name_in_import_as_names3056);
            	    n=import_as_name();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, n.getTree());
            	    if (list_n==null) list_n=new ArrayList();
            	    list_n.add(n.getTree());


            	    }
            	    break;

            	default :
            	    break loop54;
                }
            } while (true);

            if ( state.backtracking==0 ) {

                      retval.atypes = list_n;
                  
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException re) {
            errorHandler.reportError(this, re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "import_as_names"

    public static class import_as_name_return extends ParserRuleReturnScope {
        public alias atype;
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "import_as_name"
    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:942:1: import_as_name returns [alias atype] : name= NAME ( AS asname= NAME )? ;
    public final PythonParser.import_as_name_return import_as_name() throws RecognitionException {
        PythonParser.import_as_name_return retval = new PythonParser.import_as_name_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token name=null;
        Token asname=null;
        Token AS114=null;

        PythonTree name_tree=null;
        PythonTree asname_tree=null;
        PythonTree AS114_tree=null;

        try {
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:947:5: (name= NAME ( AS asname= NAME )? )
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:947:7: name= NAME ( AS asname= NAME )?
            {
            root_0 = (PythonTree)adaptor.nil();

            name=(Token)match(input,NAME,FOLLOW_NAME_in_import_as_name3097); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            name_tree = (PythonTree)adaptor.create(name);
            adaptor.addChild(root_0, name_tree);
            }
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:947:17: ( AS asname= NAME )?
            int alt55=2;
            int LA55_0 = input.LA(1);

            if ( (LA55_0==AS) ) {
                alt55=1;
            }
            switch (alt55) {
                case 1 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:947:18: AS asname= NAME
                    {
                    AS114=(Token)match(input,AS,FOLLOW_AS_in_import_as_name3100); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    AS114_tree = (PythonTree)adaptor.create(AS114);
                    adaptor.addChild(root_0, AS114_tree);
                    }
                    asname=(Token)match(input,NAME,FOLLOW_NAME_in_import_as_name3104); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    asname_tree = (PythonTree)adaptor.create(asname);
                    adaptor.addChild(root_0, asname_tree);
                    }

                    }
                    break;

            }

            if ( state.backtracking==0 ) {

                      retval.atype = new alias(actions.makeNameNode(name), actions.makeNameNode(asname));
                  
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {

                  retval.tree = retval.atype;

            }
        }

        catch (RecognitionException re) {
            errorHandler.reportError(this, re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "import_as_name"

    public static class dotted_as_name_return extends ParserRuleReturnScope {
        public alias atype;
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "dotted_as_name"
    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:955:1: dotted_as_name returns [alias atype] : dotted_name ( AS asname= NAME )? ;
    public final PythonParser.dotted_as_name_return dotted_as_name() throws RecognitionException {
        PythonParser.dotted_as_name_return retval = new PythonParser.dotted_as_name_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token asname=null;
        Token AS116=null;
        PythonParser.dotted_name_return dotted_name115 = null;


        PythonTree asname_tree=null;
        PythonTree AS116_tree=null;

        try {
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:960:5: ( dotted_name ( AS asname= NAME )? )
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:960:7: dotted_name ( AS asname= NAME )?
            {
            root_0 = (PythonTree)adaptor.nil();

            pushFollow(FOLLOW_dotted_name_in_dotted_as_name3144);
            dotted_name115=dotted_name();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, dotted_name115.getTree());
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:960:19: ( AS asname= NAME )?
            int alt56=2;
            int LA56_0 = input.LA(1);

            if ( (LA56_0==AS) ) {
                alt56=1;
            }
            switch (alt56) {
                case 1 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:960:20: AS asname= NAME
                    {
                    AS116=(Token)match(input,AS,FOLLOW_AS_in_dotted_as_name3147); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    AS116_tree = (PythonTree)adaptor.create(AS116);
                    adaptor.addChild(root_0, AS116_tree);
                    }
                    asname=(Token)match(input,NAME,FOLLOW_NAME_in_dotted_as_name3151); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    asname_tree = (PythonTree)adaptor.create(asname);
                    adaptor.addChild(root_0, asname_tree);
                    }

                    }
                    break;

            }

            if ( state.backtracking==0 ) {

                      retval.atype = new alias((dotted_name115!=null?dotted_name115.names:null), actions.makeNameNode(asname));
                  
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {

                  retval.tree = retval.atype;

            }
        }

        catch (RecognitionException re) {
            errorHandler.reportError(this, re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "dotted_as_name"

    public static class dotted_as_names_return extends ParserRuleReturnScope {
        public List<alias> atypes;
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "dotted_as_names"
    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:967:1: dotted_as_names returns [List<alias> atypes] : d+= dotted_as_name ( COMMA d+= dotted_as_name )* ;
    public final PythonParser.dotted_as_names_return dotted_as_names() throws RecognitionException {
        PythonParser.dotted_as_names_return retval = new PythonParser.dotted_as_names_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token COMMA117=null;
        List list_d=null;
        PythonParser.dotted_as_name_return d = null;
         d = null;
        PythonTree COMMA117_tree=null;

        try {
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:969:5: (d+= dotted_as_name ( COMMA d+= dotted_as_name )* )
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:969:7: d+= dotted_as_name ( COMMA d+= dotted_as_name )*
            {
            root_0 = (PythonTree)adaptor.nil();

            pushFollow(FOLLOW_dotted_as_name_in_dotted_as_names3187);
            d=dotted_as_name();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, d.getTree());
            if (list_d==null) list_d=new ArrayList();
            list_d.add(d.getTree());

            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:969:25: ( COMMA d+= dotted_as_name )*
            loop57:
            do {
                int alt57=2;
                int LA57_0 = input.LA(1);

                if ( (LA57_0==COMMA) ) {
                    alt57=1;
                }


                switch (alt57) {
            	case 1 :
            	    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:969:26: COMMA d+= dotted_as_name
            	    {
            	    COMMA117=(Token)match(input,COMMA,FOLLOW_COMMA_in_dotted_as_names3190); if (state.failed) return retval;
            	    pushFollow(FOLLOW_dotted_as_name_in_dotted_as_names3195);
            	    d=dotted_as_name();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, d.getTree());
            	    if (list_d==null) list_d=new ArrayList();
            	    list_d.add(d.getTree());


            	    }
            	    break;

            	default :
            	    break loop57;
                }
            } while (true);

            if ( state.backtracking==0 ) {

                      retval.atypes = list_d;
                  
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException re) {
            errorHandler.reportError(this, re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "dotted_as_names"

    public static class dotted_name_return extends ParserRuleReturnScope {
        public List<Name> names;
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "dotted_name"
    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:976:1: dotted_name returns [List<Name> names] : NAME ( DOT dn+= attr )* ;
    public final PythonParser.dotted_name_return dotted_name() throws RecognitionException {
        PythonParser.dotted_name_return retval = new PythonParser.dotted_name_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token NAME118=null;
        Token DOT119=null;
        List list_dn=null;
        PythonParser.attr_return dn = null;
         dn = null;
        PythonTree NAME118_tree=null;
        PythonTree DOT119_tree=null;

        try {
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:978:5: ( NAME ( DOT dn+= attr )* )
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:978:7: NAME ( DOT dn+= attr )*
            {
            root_0 = (PythonTree)adaptor.nil();

            NAME118=(Token)match(input,NAME,FOLLOW_NAME_in_dotted_name3229); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            NAME118_tree = (PythonTree)adaptor.create(NAME118);
            adaptor.addChild(root_0, NAME118_tree);
            }
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:978:12: ( DOT dn+= attr )*
            loop58:
            do {
                int alt58=2;
                int LA58_0 = input.LA(1);

                if ( (LA58_0==DOT) ) {
                    alt58=1;
                }


                switch (alt58) {
            	case 1 :
            	    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:978:13: DOT dn+= attr
            	    {
            	    DOT119=(Token)match(input,DOT,FOLLOW_DOT_in_dotted_name3232); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    DOT119_tree = (PythonTree)adaptor.create(DOT119);
            	    adaptor.addChild(root_0, DOT119_tree);
            	    }
            	    pushFollow(FOLLOW_attr_in_dotted_name3236);
            	    dn=attr();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, dn.getTree());
            	    if (list_dn==null) list_dn=new ArrayList();
            	    list_dn.add(dn.getTree());


            	    }
            	    break;

            	default :
            	    break loop58;
                }
            } while (true);

            if ( state.backtracking==0 ) {

                      retval.names = actions.makeDottedName(NAME118, list_dn);
                  
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException re) {
            errorHandler.reportError(this, re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "dotted_name"

    public static class global_stmt_return extends ParserRuleReturnScope {
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "global_stmt"
    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:985:1: global_stmt : GLOBAL n+= NAME ( COMMA n+= NAME )* ;
    public final PythonParser.global_stmt_return global_stmt() throws RecognitionException {
        PythonParser.global_stmt_return retval = new PythonParser.global_stmt_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token GLOBAL120=null;
        Token COMMA121=null;
        Token n=null;
        List list_n=null;

        PythonTree GLOBAL120_tree=null;
        PythonTree COMMA121_tree=null;
        PythonTree n_tree=null;


            stmt stype = null;

        try {
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:992:5: ( GLOBAL n+= NAME ( COMMA n+= NAME )* )
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:992:7: GLOBAL n+= NAME ( COMMA n+= NAME )*
            {
            root_0 = (PythonTree)adaptor.nil();

            GLOBAL120=(Token)match(input,GLOBAL,FOLLOW_GLOBAL_in_global_stmt3272); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            GLOBAL120_tree = (PythonTree)adaptor.create(GLOBAL120);
            adaptor.addChild(root_0, GLOBAL120_tree);
            }
            n=(Token)match(input,NAME,FOLLOW_NAME_in_global_stmt3276); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            n_tree = (PythonTree)adaptor.create(n);
            adaptor.addChild(root_0, n_tree);
            }
            if (list_n==null) list_n=new ArrayList();
            list_n.add(n);

            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:992:22: ( COMMA n+= NAME )*
            loop59:
            do {
                int alt59=2;
                int LA59_0 = input.LA(1);

                if ( (LA59_0==COMMA) ) {
                    alt59=1;
                }


                switch (alt59) {
            	case 1 :
            	    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:992:23: COMMA n+= NAME
            	    {
            	    COMMA121=(Token)match(input,COMMA,FOLLOW_COMMA_in_global_stmt3279); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    COMMA121_tree = (PythonTree)adaptor.create(COMMA121);
            	    adaptor.addChild(root_0, COMMA121_tree);
            	    }
            	    n=(Token)match(input,NAME,FOLLOW_NAME_in_global_stmt3283); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    n_tree = (PythonTree)adaptor.create(n);
            	    adaptor.addChild(root_0, n_tree);
            	    }
            	    if (list_n==null) list_n=new ArrayList();
            	    list_n.add(n);


            	    }
            	    break;

            	default :
            	    break loop59;
                }
            } while (true);

            if ( state.backtracking==0 ) {

                        stype = new Global(GLOBAL120, actions.makeNames(list_n), actions.makeNameNodes(list_n));
                    
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {

                 retval.tree = stype;

            }
        }

        catch (RecognitionException re) {
            errorHandler.reportError(this, re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "global_stmt"

    public static class exec_stmt_return extends ParserRuleReturnScope {
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "exec_stmt"
    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:999:1: exec_stmt : EXEC expr[expr_contextType.Load] ( IN t1= test[expr_contextType.Load] ( COMMA t2= test[expr_contextType.Load] )? )? ;
    public final PythonParser.exec_stmt_return exec_stmt() throws RecognitionException {
        PythonParser.exec_stmt_return retval = new PythonParser.exec_stmt_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token EXEC122=null;
        Token IN124=null;
        Token COMMA125=null;
        PythonParser.test_return t1 = null;

        PythonParser.test_return t2 = null;

        PythonParser.expr_return expr123 = null;


        PythonTree EXEC122_tree=null;
        PythonTree IN124_tree=null;
        PythonTree COMMA125_tree=null;


            stmt stype = null;

        try {
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1006:5: ( EXEC expr[expr_contextType.Load] ( IN t1= test[expr_contextType.Load] ( COMMA t2= test[expr_contextType.Load] )? )? )
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1006:7: EXEC expr[expr_contextType.Load] ( IN t1= test[expr_contextType.Load] ( COMMA t2= test[expr_contextType.Load] )? )?
            {
            root_0 = (PythonTree)adaptor.nil();

            EXEC122=(Token)match(input,EXEC,FOLLOW_EXEC_in_exec_stmt3321); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            EXEC122_tree = (PythonTree)adaptor.create(EXEC122);
            adaptor.addChild(root_0, EXEC122_tree);
            }
            pushFollow(FOLLOW_expr_in_exec_stmt3323);
            expr123=expr(expr_contextType.Load);

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, expr123.getTree());
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1006:40: ( IN t1= test[expr_contextType.Load] ( COMMA t2= test[expr_contextType.Load] )? )?
            int alt61=2;
            int LA61_0 = input.LA(1);

            if ( (LA61_0==IN) ) {
                alt61=1;
            }
            switch (alt61) {
                case 1 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1006:41: IN t1= test[expr_contextType.Load] ( COMMA t2= test[expr_contextType.Load] )?
                    {
                    IN124=(Token)match(input,IN,FOLLOW_IN_in_exec_stmt3327); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    IN124_tree = (PythonTree)adaptor.create(IN124);
                    adaptor.addChild(root_0, IN124_tree);
                    }
                    pushFollow(FOLLOW_test_in_exec_stmt3331);
                    t1=test(expr_contextType.Load);

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, t1.getTree());
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1006:75: ( COMMA t2= test[expr_contextType.Load] )?
                    int alt60=2;
                    int LA60_0 = input.LA(1);

                    if ( (LA60_0==COMMA) ) {
                        alt60=1;
                    }
                    switch (alt60) {
                        case 1 :
                            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1006:76: COMMA t2= test[expr_contextType.Load]
                            {
                            COMMA125=(Token)match(input,COMMA,FOLLOW_COMMA_in_exec_stmt3335); if (state.failed) return retval;
                            if ( state.backtracking==0 ) {
                            COMMA125_tree = (PythonTree)adaptor.create(COMMA125);
                            adaptor.addChild(root_0, COMMA125_tree);
                            }
                            pushFollow(FOLLOW_test_in_exec_stmt3339);
                            t2=test(expr_contextType.Load);

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, t2.getTree());

                            }
                            break;

                    }


                    }
                    break;

            }

            if ( state.backtracking==0 ) {

                       stype = new Exec(EXEC122, actions.castExpr((expr123!=null?((PythonTree)expr123.tree):null)), actions.castExpr((t1!=null?((PythonTree)t1.tree):null)), actions.castExpr((t2!=null?((PythonTree)t2.tree):null)));
                    
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {

                 retval.tree = stype;

            }
        }

        catch (RecognitionException re) {
            errorHandler.reportError(this, re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "exec_stmt"

    public static class assert_stmt_return extends ParserRuleReturnScope {
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "assert_stmt"
    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1013:1: assert_stmt : ASSERT t1= test[expr_contextType.Load] ( COMMA t2= test[expr_contextType.Load] )? ;
    public final PythonParser.assert_stmt_return assert_stmt() throws RecognitionException {
        PythonParser.assert_stmt_return retval = new PythonParser.assert_stmt_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token ASSERT126=null;
        Token COMMA127=null;
        PythonParser.test_return t1 = null;

        PythonParser.test_return t2 = null;


        PythonTree ASSERT126_tree=null;
        PythonTree COMMA127_tree=null;


            stmt stype = null;

        try {
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1020:5: ( ASSERT t1= test[expr_contextType.Load] ( COMMA t2= test[expr_contextType.Load] )? )
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1020:7: ASSERT t1= test[expr_contextType.Load] ( COMMA t2= test[expr_contextType.Load] )?
            {
            root_0 = (PythonTree)adaptor.nil();

            ASSERT126=(Token)match(input,ASSERT,FOLLOW_ASSERT_in_assert_stmt3380); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            ASSERT126_tree = (PythonTree)adaptor.create(ASSERT126);
            adaptor.addChild(root_0, ASSERT126_tree);
            }
            pushFollow(FOLLOW_test_in_assert_stmt3384);
            t1=test(expr_contextType.Load);

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, t1.getTree());
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1020:45: ( COMMA t2= test[expr_contextType.Load] )?
            int alt62=2;
            int LA62_0 = input.LA(1);

            if ( (LA62_0==COMMA) ) {
                alt62=1;
            }
            switch (alt62) {
                case 1 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1020:46: COMMA t2= test[expr_contextType.Load]
                    {
                    COMMA127=(Token)match(input,COMMA,FOLLOW_COMMA_in_assert_stmt3388); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    COMMA127_tree = (PythonTree)adaptor.create(COMMA127);
                    adaptor.addChild(root_0, COMMA127_tree);
                    }
                    pushFollow(FOLLOW_test_in_assert_stmt3392);
                    t2=test(expr_contextType.Load);

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, t2.getTree());

                    }
                    break;

            }

            if ( state.backtracking==0 ) {

                        stype = new Assert(ASSERT126, actions.castExpr((t1!=null?((PythonTree)t1.tree):null)), actions.castExpr((t2!=null?((PythonTree)t2.tree):null)));
                    
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {

                 retval.tree = stype;

            }
        }

        catch (RecognitionException re) {
            errorHandler.reportError(this, re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "assert_stmt"

    public static class compound_stmt_return extends ParserRuleReturnScope {
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "compound_stmt"
    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1027:1: compound_stmt : ( if_stmt | while_stmt | for_stmt | try_stmt | with_stmt | ( ( decorators )? DEF )=> funcdef | classdef );
    public final PythonParser.compound_stmt_return compound_stmt() throws RecognitionException {
        PythonParser.compound_stmt_return retval = new PythonParser.compound_stmt_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        PythonParser.if_stmt_return if_stmt128 = null;

        PythonParser.while_stmt_return while_stmt129 = null;

        PythonParser.for_stmt_return for_stmt130 = null;

        PythonParser.try_stmt_return try_stmt131 = null;

        PythonParser.with_stmt_return with_stmt132 = null;

        PythonParser.funcdef_return funcdef133 = null;

        PythonParser.classdef_return classdef134 = null;



        try {
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1028:5: ( if_stmt | while_stmt | for_stmt | try_stmt | with_stmt | ( ( decorators )? DEF )=> funcdef | classdef )
            int alt63=7;
            int LA63_0 = input.LA(1);

            if ( (LA63_0==IF) ) {
                alt63=1;
            }
            else if ( (LA63_0==WHILE) ) {
                alt63=2;
            }
            else if ( (LA63_0==FOR) ) {
                alt63=3;
            }
            else if ( (LA63_0==TRY) ) {
                alt63=4;
            }
            else if ( (LA63_0==WITH) ) {
                alt63=5;
            }
            else if ( (LA63_0==AT) ) {
                int LA63_6 = input.LA(2);

                if ( (synpred6_Python()) ) {
                    alt63=6;
                }
                else if ( (true) ) {
                    alt63=7;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 63, 6, input);

                    throw nvae;
                }
            }
            else if ( (LA63_0==DEF) && (synpred6_Python())) {
                alt63=6;
            }
            else if ( (LA63_0==CLASS) ) {
                alt63=7;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 63, 0, input);

                throw nvae;
            }
            switch (alt63) {
                case 1 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1028:7: if_stmt
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    pushFollow(FOLLOW_if_stmt_in_compound_stmt3421);
                    if_stmt128=if_stmt();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, if_stmt128.getTree());

                    }
                    break;
                case 2 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1029:7: while_stmt
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    pushFollow(FOLLOW_while_stmt_in_compound_stmt3429);
                    while_stmt129=while_stmt();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, while_stmt129.getTree());

                    }
                    break;
                case 3 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1030:7: for_stmt
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    pushFollow(FOLLOW_for_stmt_in_compound_stmt3437);
                    for_stmt130=for_stmt();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, for_stmt130.getTree());

                    }
                    break;
                case 4 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1031:7: try_stmt
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    pushFollow(FOLLOW_try_stmt_in_compound_stmt3445);
                    try_stmt131=try_stmt();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, try_stmt131.getTree());

                    }
                    break;
                case 5 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1032:7: with_stmt
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    pushFollow(FOLLOW_with_stmt_in_compound_stmt3453);
                    with_stmt132=with_stmt();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, with_stmt132.getTree());

                    }
                    break;
                case 6 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1033:7: ( ( decorators )? DEF )=> funcdef
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    pushFollow(FOLLOW_funcdef_in_compound_stmt3470);
                    funcdef133=funcdef();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, funcdef133.getTree());

                    }
                    break;
                case 7 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1034:7: classdef
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    pushFollow(FOLLOW_classdef_in_compound_stmt3478);
                    classdef134=classdef();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, classdef134.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException re) {
            errorHandler.reportError(this, re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "compound_stmt"

    public static class if_stmt_return extends ParserRuleReturnScope {
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "if_stmt"
    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1038:1: if_stmt : IF test[expr_contextType.Load] COLON ifsuite= suite[false] ( elif_clause )? ;
    public final PythonParser.if_stmt_return if_stmt() throws RecognitionException {
        PythonParser.if_stmt_return retval = new PythonParser.if_stmt_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token IF135=null;
        Token COLON137=null;
        PythonParser.suite_return ifsuite = null;

        PythonParser.test_return test136 = null;

        PythonParser.elif_clause_return elif_clause138 = null;


        PythonTree IF135_tree=null;
        PythonTree COLON137_tree=null;


            stmt stype = null;

        try {
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1045:5: ( IF test[expr_contextType.Load] COLON ifsuite= suite[false] ( elif_clause )? )
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1045:7: IF test[expr_contextType.Load] COLON ifsuite= suite[false] ( elif_clause )?
            {
            root_0 = (PythonTree)adaptor.nil();

            IF135=(Token)match(input,IF,FOLLOW_IF_in_if_stmt3506); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            IF135_tree = (PythonTree)adaptor.create(IF135);
            adaptor.addChild(root_0, IF135_tree);
            }
            pushFollow(FOLLOW_test_in_if_stmt3508);
            test136=test(expr_contextType.Load);

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, test136.getTree());
            COLON137=(Token)match(input,COLON,FOLLOW_COLON_in_if_stmt3511); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            COLON137_tree = (PythonTree)adaptor.create(COLON137);
            adaptor.addChild(root_0, COLON137_tree);
            }
            pushFollow(FOLLOW_suite_in_if_stmt3515);
            ifsuite=suite(false);

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, ifsuite.getTree());
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1045:65: ( elif_clause )?
            int alt64=2;
            int LA64_0 = input.LA(1);

            if ( (LA64_0==ELIF||LA64_0==ORELSE) ) {
                alt64=1;
            }
            switch (alt64) {
                case 1 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1045:65: elif_clause
                    {
                    pushFollow(FOLLOW_elif_clause_in_if_stmt3518);
                    elif_clause138=elif_clause();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, elif_clause138.getTree());

                    }
                    break;

            }

            if ( state.backtracking==0 ) {

                        stype = new If(IF135, actions.castExpr((test136!=null?((PythonTree)test136.tree):null)), actions.castStmts((ifsuite!=null?ifsuite.stypes:null)),
                            actions.makeElse((elif_clause138!=null?elif_clause138.stypes:null), (elif_clause138!=null?((PythonTree)elif_clause138.tree):null)));
                    
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {

                 retval.tree = stype;

            }
        }

        catch (RecognitionException re) {
            errorHandler.reportError(this, re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "if_stmt"

    public static class elif_clause_return extends ParserRuleReturnScope {
        public List stypes;
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "elif_clause"
    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1053:1: elif_clause returns [List stypes] : ( else_clause | ELIF test[expr_contextType.Load] COLON suite[false] (e2= elif_clause | ) );
    public final PythonParser.elif_clause_return elif_clause() throws RecognitionException {
        PythonParser.elif_clause_return retval = new PythonParser.elif_clause_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token ELIF140=null;
        Token COLON142=null;
        PythonParser.elif_clause_return e2 = null;

        PythonParser.else_clause_return else_clause139 = null;

        PythonParser.test_return test141 = null;

        PythonParser.suite_return suite143 = null;


        PythonTree ELIF140_tree=null;
        PythonTree COLON142_tree=null;


            stmt stype = null;

        try {
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1063:5: ( else_clause | ELIF test[expr_contextType.Load] COLON suite[false] (e2= elif_clause | ) )
            int alt66=2;
            int LA66_0 = input.LA(1);

            if ( (LA66_0==ORELSE) ) {
                alt66=1;
            }
            else if ( (LA66_0==ELIF) ) {
                alt66=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 66, 0, input);

                throw nvae;
            }
            switch (alt66) {
                case 1 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1063:7: else_clause
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    pushFollow(FOLLOW_else_clause_in_elif_clause3563);
                    else_clause139=else_clause();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, else_clause139.getTree());
                    if ( state.backtracking==0 ) {

                                retval.stypes = (else_clause139!=null?else_clause139.stypes:null);
                            
                    }

                    }
                    break;
                case 2 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1067:7: ELIF test[expr_contextType.Load] COLON suite[false] (e2= elif_clause | )
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    ELIF140=(Token)match(input,ELIF,FOLLOW_ELIF_in_elif_clause3579); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    ELIF140_tree = (PythonTree)adaptor.create(ELIF140);
                    adaptor.addChild(root_0, ELIF140_tree);
                    }
                    pushFollow(FOLLOW_test_in_elif_clause3581);
                    test141=test(expr_contextType.Load);

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, test141.getTree());
                    COLON142=(Token)match(input,COLON,FOLLOW_COLON_in_elif_clause3584); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    COLON142_tree = (PythonTree)adaptor.create(COLON142);
                    adaptor.addChild(root_0, COLON142_tree);
                    }
                    pushFollow(FOLLOW_suite_in_elif_clause3586);
                    suite143=suite(false);

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, suite143.getTree());
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1068:7: (e2= elif_clause | )
                    int alt65=2;
                    int LA65_0 = input.LA(1);

                    if ( (LA65_0==ELIF||LA65_0==ORELSE) ) {
                        alt65=1;
                    }
                    else if ( (LA65_0==EOF||LA65_0==DEDENT||LA65_0==NEWLINE||LA65_0==NAME||(LA65_0>=ASSERT && LA65_0<=DELETE)||LA65_0==EXEC||(LA65_0>=FROM && LA65_0<=IMPORT)||(LA65_0>=LAMBDA && LA65_0<=NOT)||(LA65_0>=PASS && LA65_0<=LPAREN)||(LA65_0>=PLUS && LA65_0<=MINUS)||(LA65_0>=TILDE && LA65_0<=LBRACK)||LA65_0==LCURLY||(LA65_0>=BACKQUOTE && LA65_0<=STRING)) ) {
                        alt65=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 65, 0, input);

                        throw nvae;
                    }
                    switch (alt65) {
                        case 1 :
                            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1068:8: e2= elif_clause
                            {
                            pushFollow(FOLLOW_elif_clause_in_elif_clause3598);
                            e2=elif_clause();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, e2.getTree());
                            if ( state.backtracking==0 ) {

                                         stype = new If((test141!=null?((Token)test141.start):null), actions.castExpr((test141!=null?((PythonTree)test141.tree):null)), actions.castStmts((suite143!=null?suite143.stypes:null)), actions.makeElse((e2!=null?e2.stypes:null), (e2!=null?((PythonTree)e2.tree):null)));
                                     
                            }

                            }
                            break;
                        case 2 :
                            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1073:8: 
                            {
                            if ( state.backtracking==0 ) {

                                         stype = new If((test141!=null?((Token)test141.start):null), actions.castExpr((test141!=null?((PythonTree)test141.tree):null)), actions.castStmts((suite143!=null?suite143.stypes:null)), new ArrayList<stmt>());
                                     
                            }

                            }
                            break;

                    }


                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {

                 if (stype != null) {
                     retval.tree = stype;
                 }

            }
        }

        catch (RecognitionException re) {
            errorHandler.reportError(this, re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "elif_clause"

    public static class else_clause_return extends ParserRuleReturnScope {
        public List stypes;
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "else_clause"
    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1080:1: else_clause returns [List stypes] : ORELSE COLON elsesuite= suite[false] ;
    public final PythonParser.else_clause_return else_clause() throws RecognitionException {
        PythonParser.else_clause_return retval = new PythonParser.else_clause_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token ORELSE144=null;
        Token COLON145=null;
        PythonParser.suite_return elsesuite = null;


        PythonTree ORELSE144_tree=null;
        PythonTree COLON145_tree=null;

        try {
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1082:5: ( ORELSE COLON elsesuite= suite[false] )
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1082:7: ORELSE COLON elsesuite= suite[false]
            {
            root_0 = (PythonTree)adaptor.nil();

            ORELSE144=(Token)match(input,ORELSE,FOLLOW_ORELSE_in_else_clause3658); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            ORELSE144_tree = (PythonTree)adaptor.create(ORELSE144);
            adaptor.addChild(root_0, ORELSE144_tree);
            }
            COLON145=(Token)match(input,COLON,FOLLOW_COLON_in_else_clause3660); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            COLON145_tree = (PythonTree)adaptor.create(COLON145);
            adaptor.addChild(root_0, COLON145_tree);
            }
            pushFollow(FOLLOW_suite_in_else_clause3664);
            elsesuite=suite(false);

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, elsesuite.getTree());
            if ( state.backtracking==0 ) {

                        retval.stypes = (elsesuite!=null?elsesuite.stypes:null);
                    
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException re) {
            errorHandler.reportError(this, re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "else_clause"

    public static class while_stmt_return extends ParserRuleReturnScope {
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "while_stmt"
    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1089:1: while_stmt : WHILE test[expr_contextType.Load] COLON s1= suite[false] ( ORELSE COLON s2= suite[false] )? ;
    public final PythonParser.while_stmt_return while_stmt() throws RecognitionException {
        PythonParser.while_stmt_return retval = new PythonParser.while_stmt_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token WHILE146=null;
        Token COLON148=null;
        Token ORELSE149=null;
        Token COLON150=null;
        PythonParser.suite_return s1 = null;

        PythonParser.suite_return s2 = null;

        PythonParser.test_return test147 = null;


        PythonTree WHILE146_tree=null;
        PythonTree COLON148_tree=null;
        PythonTree ORELSE149_tree=null;
        PythonTree COLON150_tree=null;


            stmt stype = null;

        try {
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1096:5: ( WHILE test[expr_contextType.Load] COLON s1= suite[false] ( ORELSE COLON s2= suite[false] )? )
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1096:7: WHILE test[expr_contextType.Load] COLON s1= suite[false] ( ORELSE COLON s2= suite[false] )?
            {
            root_0 = (PythonTree)adaptor.nil();

            WHILE146=(Token)match(input,WHILE,FOLLOW_WHILE_in_while_stmt3701); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            WHILE146_tree = (PythonTree)adaptor.create(WHILE146);
            adaptor.addChild(root_0, WHILE146_tree);
            }
            pushFollow(FOLLOW_test_in_while_stmt3703);
            test147=test(expr_contextType.Load);

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, test147.getTree());
            COLON148=(Token)match(input,COLON,FOLLOW_COLON_in_while_stmt3706); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            COLON148_tree = (PythonTree)adaptor.create(COLON148);
            adaptor.addChild(root_0, COLON148_tree);
            }
            pushFollow(FOLLOW_suite_in_while_stmt3710);
            s1=suite(false);

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, s1.getTree());
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1096:63: ( ORELSE COLON s2= suite[false] )?
            int alt67=2;
            int LA67_0 = input.LA(1);

            if ( (LA67_0==ORELSE) ) {
                alt67=1;
            }
            switch (alt67) {
                case 1 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1096:64: ORELSE COLON s2= suite[false]
                    {
                    ORELSE149=(Token)match(input,ORELSE,FOLLOW_ORELSE_in_while_stmt3714); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    ORELSE149_tree = (PythonTree)adaptor.create(ORELSE149);
                    adaptor.addChild(root_0, ORELSE149_tree);
                    }
                    COLON150=(Token)match(input,COLON,FOLLOW_COLON_in_while_stmt3716); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    COLON150_tree = (PythonTree)adaptor.create(COLON150);
                    adaptor.addChild(root_0, COLON150_tree);
                    }
                    pushFollow(FOLLOW_suite_in_while_stmt3720);
                    s2=suite(false);

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, s2.getTree());

                    }
                    break;

            }

            if ( state.backtracking==0 ) {

                        stype = actions.makeWhile(WHILE146, actions.castExpr((test147!=null?((PythonTree)test147.tree):null)), (s1!=null?s1.stypes:null), (s2!=null?s2.stypes:null));
                    
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {

                 retval.tree = stype;

            }
        }

        catch (RecognitionException re) {
            errorHandler.reportError(this, re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "while_stmt"

    public static class for_stmt_return extends ParserRuleReturnScope {
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "for_stmt"
    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1103:1: for_stmt : FOR exprlist[expr_contextType.Store] IN testlist[expr_contextType.Load] COLON s1= suite[false] ( ORELSE COLON s2= suite[false] )? ;
    public final PythonParser.for_stmt_return for_stmt() throws RecognitionException {
        PythonParser.for_stmt_return retval = new PythonParser.for_stmt_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token FOR151=null;
        Token IN153=null;
        Token COLON155=null;
        Token ORELSE156=null;
        Token COLON157=null;
        PythonParser.suite_return s1 = null;

        PythonParser.suite_return s2 = null;

        PythonParser.exprlist_return exprlist152 = null;

        PythonParser.testlist_return testlist154 = null;


        PythonTree FOR151_tree=null;
        PythonTree IN153_tree=null;
        PythonTree COLON155_tree=null;
        PythonTree ORELSE156_tree=null;
        PythonTree COLON157_tree=null;


            stmt stype = null;

        try {
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1110:5: ( FOR exprlist[expr_contextType.Store] IN testlist[expr_contextType.Load] COLON s1= suite[false] ( ORELSE COLON s2= suite[false] )? )
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1110:7: FOR exprlist[expr_contextType.Store] IN testlist[expr_contextType.Load] COLON s1= suite[false] ( ORELSE COLON s2= suite[false] )?
            {
            root_0 = (PythonTree)adaptor.nil();

            FOR151=(Token)match(input,FOR,FOLLOW_FOR_in_for_stmt3759); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            FOR151_tree = (PythonTree)adaptor.create(FOR151);
            adaptor.addChild(root_0, FOR151_tree);
            }
            pushFollow(FOLLOW_exprlist_in_for_stmt3761);
            exprlist152=exprlist(expr_contextType.Store);

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, exprlist152.getTree());
            IN153=(Token)match(input,IN,FOLLOW_IN_in_for_stmt3764); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            IN153_tree = (PythonTree)adaptor.create(IN153);
            adaptor.addChild(root_0, IN153_tree);
            }
            pushFollow(FOLLOW_testlist_in_for_stmt3766);
            testlist154=testlist(expr_contextType.Load);

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, testlist154.getTree());
            COLON155=(Token)match(input,COLON,FOLLOW_COLON_in_for_stmt3769); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            COLON155_tree = (PythonTree)adaptor.create(COLON155);
            adaptor.addChild(root_0, COLON155_tree);
            }
            pushFollow(FOLLOW_suite_in_for_stmt3773);
            s1=suite(false);

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, s1.getTree());
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1111:9: ( ORELSE COLON s2= suite[false] )?
            int alt68=2;
            int LA68_0 = input.LA(1);

            if ( (LA68_0==ORELSE) ) {
                alt68=1;
            }
            switch (alt68) {
                case 1 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1111:10: ORELSE COLON s2= suite[false]
                    {
                    ORELSE156=(Token)match(input,ORELSE,FOLLOW_ORELSE_in_for_stmt3785); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    ORELSE156_tree = (PythonTree)adaptor.create(ORELSE156);
                    adaptor.addChild(root_0, ORELSE156_tree);
                    }
                    COLON157=(Token)match(input,COLON,FOLLOW_COLON_in_for_stmt3787); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    COLON157_tree = (PythonTree)adaptor.create(COLON157);
                    adaptor.addChild(root_0, COLON157_tree);
                    }
                    pushFollow(FOLLOW_suite_in_for_stmt3791);
                    s2=suite(false);

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, s2.getTree());

                    }
                    break;

            }

            if ( state.backtracking==0 ) {

                        stype = actions.makeFor(FOR151, (exprlist152!=null?exprlist152.etype:null), actions.castExpr((testlist154!=null?((PythonTree)testlist154.tree):null)), (s1!=null?s1.stypes:null), (s2!=null?s2.stypes:null));
                    
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {

                 retval.tree = stype;

            }
        }

        catch (RecognitionException re) {
            errorHandler.reportError(this, re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "for_stmt"

    public static class try_stmt_return extends ParserRuleReturnScope {
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "try_stmt"
    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1122:1: try_stmt : TRY COLON trysuite= suite[!$suite.isEmpty() && $suite::continueIllegal] ( (e+= except_clause )+ ( ORELSE COLON elsesuite= suite[!$suite.isEmpty() && $suite::continueIllegal] )? ( FINALLY COLON finalsuite= suite[true] )? | FINALLY COLON finalsuite= suite[true] ) ;
    public final PythonParser.try_stmt_return try_stmt() throws RecognitionException {
        PythonParser.try_stmt_return retval = new PythonParser.try_stmt_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token TRY158=null;
        Token COLON159=null;
        Token ORELSE160=null;
        Token COLON161=null;
        Token FINALLY162=null;
        Token COLON163=null;
        Token FINALLY164=null;
        Token COLON165=null;
        List list_e=null;
        PythonParser.suite_return trysuite = null;

        PythonParser.suite_return elsesuite = null;

        PythonParser.suite_return finalsuite = null;

        PythonParser.except_clause_return e = null;
         e = null;
        PythonTree TRY158_tree=null;
        PythonTree COLON159_tree=null;
        PythonTree ORELSE160_tree=null;
        PythonTree COLON161_tree=null;
        PythonTree FINALLY162_tree=null;
        PythonTree COLON163_tree=null;
        PythonTree FINALLY164_tree=null;
        PythonTree COLON165_tree=null;


            stmt stype = null;

        try {
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1129:5: ( TRY COLON trysuite= suite[!$suite.isEmpty() && $suite::continueIllegal] ( (e+= except_clause )+ ( ORELSE COLON elsesuite= suite[!$suite.isEmpty() && $suite::continueIllegal] )? ( FINALLY COLON finalsuite= suite[true] )? | FINALLY COLON finalsuite= suite[true] ) )
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1129:7: TRY COLON trysuite= suite[!$suite.isEmpty() && $suite::continueIllegal] ( (e+= except_clause )+ ( ORELSE COLON elsesuite= suite[!$suite.isEmpty() && $suite::continueIllegal] )? ( FINALLY COLON finalsuite= suite[true] )? | FINALLY COLON finalsuite= suite[true] )
            {
            root_0 = (PythonTree)adaptor.nil();

            TRY158=(Token)match(input,TRY,FOLLOW_TRY_in_try_stmt3834); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            TRY158_tree = (PythonTree)adaptor.create(TRY158);
            adaptor.addChild(root_0, TRY158_tree);
            }
            COLON159=(Token)match(input,COLON,FOLLOW_COLON_in_try_stmt3836); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            COLON159_tree = (PythonTree)adaptor.create(COLON159);
            adaptor.addChild(root_0, COLON159_tree);
            }
            pushFollow(FOLLOW_suite_in_try_stmt3840);
            trysuite=suite(!suite_stack.isEmpty() && ((suite_scope)suite_stack.peek()).continueIllegal);

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, trysuite.getTree());
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1130:7: ( (e+= except_clause )+ ( ORELSE COLON elsesuite= suite[!$suite.isEmpty() && $suite::continueIllegal] )? ( FINALLY COLON finalsuite= suite[true] )? | FINALLY COLON finalsuite= suite[true] )
            int alt72=2;
            int LA72_0 = input.LA(1);

            if ( (LA72_0==EXCEPT) ) {
                alt72=1;
            }
            else if ( (LA72_0==FINALLY) ) {
                alt72=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 72, 0, input);

                throw nvae;
            }
            switch (alt72) {
                case 1 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1130:9: (e+= except_clause )+ ( ORELSE COLON elsesuite= suite[!$suite.isEmpty() && $suite::continueIllegal] )? ( FINALLY COLON finalsuite= suite[true] )?
                    {
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1130:10: (e+= except_clause )+
                    int cnt69=0;
                    loop69:
                    do {
                        int alt69=2;
                        int LA69_0 = input.LA(1);

                        if ( (LA69_0==EXCEPT) ) {
                            alt69=1;
                        }


                        switch (alt69) {
                    	case 1 :
                    	    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1130:10: e+= except_clause
                    	    {
                    	    pushFollow(FOLLOW_except_clause_in_try_stmt3853);
                    	    e=except_clause();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) adaptor.addChild(root_0, e.getTree());
                    	    if (list_e==null) list_e=new ArrayList();
                    	    list_e.add(e.getTree());


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt69 >= 1 ) break loop69;
                    	    if (state.backtracking>0) {state.failed=true; return retval;}
                                EarlyExitException eee =
                                    new EarlyExitException(69, input);
                                throw eee;
                        }
                        cnt69++;
                    } while (true);

                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1130:27: ( ORELSE COLON elsesuite= suite[!$suite.isEmpty() && $suite::continueIllegal] )?
                    int alt70=2;
                    int LA70_0 = input.LA(1);

                    if ( (LA70_0==ORELSE) ) {
                        alt70=1;
                    }
                    switch (alt70) {
                        case 1 :
                            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1130:28: ORELSE COLON elsesuite= suite[!$suite.isEmpty() && $suite::continueIllegal]
                            {
                            ORELSE160=(Token)match(input,ORELSE,FOLLOW_ORELSE_in_try_stmt3857); if (state.failed) return retval;
                            if ( state.backtracking==0 ) {
                            ORELSE160_tree = (PythonTree)adaptor.create(ORELSE160);
                            adaptor.addChild(root_0, ORELSE160_tree);
                            }
                            COLON161=(Token)match(input,COLON,FOLLOW_COLON_in_try_stmt3859); if (state.failed) return retval;
                            if ( state.backtracking==0 ) {
                            COLON161_tree = (PythonTree)adaptor.create(COLON161);
                            adaptor.addChild(root_0, COLON161_tree);
                            }
                            pushFollow(FOLLOW_suite_in_try_stmt3863);
                            elsesuite=suite(!suite_stack.isEmpty() && ((suite_scope)suite_stack.peek()).continueIllegal);

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, elsesuite.getTree());

                            }
                            break;

                    }

                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1130:105: ( FINALLY COLON finalsuite= suite[true] )?
                    int alt71=2;
                    int LA71_0 = input.LA(1);

                    if ( (LA71_0==FINALLY) ) {
                        alt71=1;
                    }
                    switch (alt71) {
                        case 1 :
                            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1130:106: FINALLY COLON finalsuite= suite[true]
                            {
                            FINALLY162=(Token)match(input,FINALLY,FOLLOW_FINALLY_in_try_stmt3869); if (state.failed) return retval;
                            if ( state.backtracking==0 ) {
                            FINALLY162_tree = (PythonTree)adaptor.create(FINALLY162);
                            adaptor.addChild(root_0, FINALLY162_tree);
                            }
                            COLON163=(Token)match(input,COLON,FOLLOW_COLON_in_try_stmt3871); if (state.failed) return retval;
                            if ( state.backtracking==0 ) {
                            COLON163_tree = (PythonTree)adaptor.create(COLON163);
                            adaptor.addChild(root_0, COLON163_tree);
                            }
                            pushFollow(FOLLOW_suite_in_try_stmt3875);
                            finalsuite=suite(true);

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, finalsuite.getTree());

                            }
                            break;

                    }

                    if ( state.backtracking==0 ) {

                                  stype = actions.makeTryExcept(TRY158, (trysuite!=null?trysuite.stypes:null), list_e, (elsesuite!=null?elsesuite.stypes:null), (finalsuite!=null?finalsuite.stypes:null));
                              
                    }

                    }
                    break;
                case 2 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1134:9: FINALLY COLON finalsuite= suite[true]
                    {
                    FINALLY164=(Token)match(input,FINALLY,FOLLOW_FINALLY_in_try_stmt3898); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    FINALLY164_tree = (PythonTree)adaptor.create(FINALLY164);
                    adaptor.addChild(root_0, FINALLY164_tree);
                    }
                    COLON165=(Token)match(input,COLON,FOLLOW_COLON_in_try_stmt3900); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    COLON165_tree = (PythonTree)adaptor.create(COLON165);
                    adaptor.addChild(root_0, COLON165_tree);
                    }
                    pushFollow(FOLLOW_suite_in_try_stmt3904);
                    finalsuite=suite(true);

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, finalsuite.getTree());
                    if ( state.backtracking==0 ) {

                                  stype = actions.makeTryFinally(TRY158, (trysuite!=null?trysuite.stypes:null), (finalsuite!=null?finalsuite.stypes:null));
                              
                    }

                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {

                 retval.tree = stype;

            }
        }

        catch (RecognitionException re) {
            errorHandler.reportError(this, re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "try_stmt"

    public static class with_stmt_return extends ParserRuleReturnScope {
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "with_stmt"
    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1142:1: with_stmt : WITH test[expr_contextType.Load] ( with_var )? COLON suite[false] ;
    public final PythonParser.with_stmt_return with_stmt() throws RecognitionException {
        PythonParser.with_stmt_return retval = new PythonParser.with_stmt_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token WITH166=null;
        Token COLON169=null;
        PythonParser.test_return test167 = null;

        PythonParser.with_var_return with_var168 = null;

        PythonParser.suite_return suite170 = null;


        PythonTree WITH166_tree=null;
        PythonTree COLON169_tree=null;


            stmt stype = null;

        try {
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1149:5: ( WITH test[expr_contextType.Load] ( with_var )? COLON suite[false] )
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1149:7: WITH test[expr_contextType.Load] ( with_var )? COLON suite[false]
            {
            root_0 = (PythonTree)adaptor.nil();

            WITH166=(Token)match(input,WITH,FOLLOW_WITH_in_with_stmt3953); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            WITH166_tree = (PythonTree)adaptor.create(WITH166);
            adaptor.addChild(root_0, WITH166_tree);
            }
            pushFollow(FOLLOW_test_in_with_stmt3955);
            test167=test(expr_contextType.Load);

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, test167.getTree());
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1149:40: ( with_var )?
            int alt73=2;
            int LA73_0 = input.LA(1);

            if ( (LA73_0==NAME||LA73_0==AS) ) {
                alt73=1;
            }
            switch (alt73) {
                case 1 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1149:41: with_var
                    {
                    pushFollow(FOLLOW_with_var_in_with_stmt3959);
                    with_var168=with_var();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, with_var168.getTree());

                    }
                    break;

            }

            COLON169=(Token)match(input,COLON,FOLLOW_COLON_in_with_stmt3963); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            COLON169_tree = (PythonTree)adaptor.create(COLON169);
            adaptor.addChild(root_0, COLON169_tree);
            }
            pushFollow(FOLLOW_suite_in_with_stmt3965);
            suite170=suite(false);

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, suite170.getTree());
            if ( state.backtracking==0 ) {

                        stype = new With(WITH166, actions.castExpr((test167!=null?((PythonTree)test167.tree):null)), (with_var168!=null?with_var168.etype:null),
                            actions.castStmts((suite170!=null?suite170.stypes:null)));
                    
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {

                 retval.tree = stype;

            }
        }

        catch (RecognitionException re) {
            errorHandler.reportError(this, re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "with_stmt"

    public static class with_var_return extends ParserRuleReturnScope {
        public expr etype;
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "with_var"
    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1157:1: with_var returns [expr etype] : ( AS | NAME ) expr[expr_contextType.Store] ;
    public final PythonParser.with_var_return with_var() throws RecognitionException {
        PythonParser.with_var_return retval = new PythonParser.with_var_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token set171=null;
        PythonParser.expr_return expr172 = null;


        PythonTree set171_tree=null;

        try {
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1159:5: ( ( AS | NAME ) expr[expr_contextType.Store] )
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1159:7: ( AS | NAME ) expr[expr_contextType.Store]
            {
            root_0 = (PythonTree)adaptor.nil();

            set171=(Token)input.LT(1);
            if ( input.LA(1)==NAME||input.LA(1)==AS ) {
                input.consume();
                if ( state.backtracking==0 ) adaptor.addChild(root_0, (PythonTree)adaptor.create(set171));
                state.errorRecovery=false;state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }

            pushFollow(FOLLOW_expr_in_with_var4008);
            expr172=expr(expr_contextType.Store);

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, expr172.getTree());
            if ( state.backtracking==0 ) {

                        retval.etype = actions.castExpr((expr172!=null?((PythonTree)expr172.tree):null));
                        actions.checkAssign(retval.etype);
                    
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException re) {
            errorHandler.reportError(this, re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "with_var"

    public static class except_clause_return extends ParserRuleReturnScope {
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "except_clause"
    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1167:1: except_clause : EXCEPT (t1= test[expr_contextType.Load] ( ( COMMA | AS ) t2= test[expr_contextType.Store] )? )? COLON suite[!$suite.isEmpty() && $suite::continueIllegal] ;
    public final PythonParser.except_clause_return except_clause() throws RecognitionException {
        PythonParser.except_clause_return retval = new PythonParser.except_clause_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token EXCEPT173=null;
        Token set174=null;
        Token COLON175=null;
        PythonParser.test_return t1 = null;

        PythonParser.test_return t2 = null;

        PythonParser.suite_return suite176 = null;


        PythonTree EXCEPT173_tree=null;
        PythonTree set174_tree=null;
        PythonTree COLON175_tree=null;


            excepthandler extype = null;

        try {
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1174:5: ( EXCEPT (t1= test[expr_contextType.Load] ( ( COMMA | AS ) t2= test[expr_contextType.Store] )? )? COLON suite[!$suite.isEmpty() && $suite::continueIllegal] )
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1174:7: EXCEPT (t1= test[expr_contextType.Load] ( ( COMMA | AS ) t2= test[expr_contextType.Store] )? )? COLON suite[!$suite.isEmpty() && $suite::continueIllegal]
            {
            root_0 = (PythonTree)adaptor.nil();

            EXCEPT173=(Token)match(input,EXCEPT,FOLLOW_EXCEPT_in_except_clause4045); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            EXCEPT173_tree = (PythonTree)adaptor.create(EXCEPT173);
            adaptor.addChild(root_0, EXCEPT173_tree);
            }
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1174:14: (t1= test[expr_contextType.Load] ( ( COMMA | AS ) t2= test[expr_contextType.Store] )? )?
            int alt75=2;
            int LA75_0 = input.LA(1);

            if ( (LA75_0==NAME||(LA75_0>=LAMBDA && LA75_0<=NOT)||LA75_0==LPAREN||(LA75_0>=PLUS && LA75_0<=MINUS)||(LA75_0>=TILDE && LA75_0<=LBRACK)||LA75_0==LCURLY||(LA75_0>=BACKQUOTE && LA75_0<=STRING)) ) {
                alt75=1;
            }
            switch (alt75) {
                case 1 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1174:15: t1= test[expr_contextType.Load] ( ( COMMA | AS ) t2= test[expr_contextType.Store] )?
                    {
                    pushFollow(FOLLOW_test_in_except_clause4050);
                    t1=test(expr_contextType.Load);

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, t1.getTree());
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1174:46: ( ( COMMA | AS ) t2= test[expr_contextType.Store] )?
                    int alt74=2;
                    int LA74_0 = input.LA(1);

                    if ( (LA74_0==AS||LA74_0==COMMA) ) {
                        alt74=1;
                    }
                    switch (alt74) {
                        case 1 :
                            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1174:47: ( COMMA | AS ) t2= test[expr_contextType.Store]
                            {
                            set174=(Token)input.LT(1);
                            if ( input.LA(1)==AS||input.LA(1)==COMMA ) {
                                input.consume();
                                if ( state.backtracking==0 ) adaptor.addChild(root_0, (PythonTree)adaptor.create(set174));
                                state.errorRecovery=false;state.failed=false;
                            }
                            else {
                                if (state.backtracking>0) {state.failed=true; return retval;}
                                MismatchedSetException mse = new MismatchedSetException(null,input);
                                throw mse;
                            }

                            pushFollow(FOLLOW_test_in_except_clause4064);
                            t2=test(expr_contextType.Store);

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, t2.getTree());

                            }
                            break;

                    }


                    }
                    break;

            }

            COLON175=(Token)match(input,COLON,FOLLOW_COLON_in_except_clause4071); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            COLON175_tree = (PythonTree)adaptor.create(COLON175);
            adaptor.addChild(root_0, COLON175_tree);
            }
            pushFollow(FOLLOW_suite_in_except_clause4073);
            suite176=suite(!suite_stack.isEmpty() && ((suite_scope)suite_stack.peek()).continueIllegal);

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, suite176.getTree());
            if ( state.backtracking==0 ) {

                        extype = new ExceptHandler(EXCEPT173, actions.castExpr((t1!=null?((PythonTree)t1.tree):null)), actions.castExpr((t2!=null?((PythonTree)t2.tree):null)),
                            actions.castStmts((suite176!=null?suite176.stypes:null)));
                    
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {

                 retval.tree = extype;

            }
        }

        catch (RecognitionException re) {
            errorHandler.reportError(this, re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "except_clause"

    protected static class suite_scope {
        boolean continueIllegal;
    }
    protected Stack suite_stack = new Stack();

    public static class suite_return extends ParserRuleReturnScope {
        public List stypes;
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "suite"
    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1182:1: suite[boolean fromFinally] returns [List stypes] : ( simple_stmt | NEWLINE INDENT ( stmt )+ DEDENT );
    public final PythonParser.suite_return suite(boolean fromFinally) throws RecognitionException {
        suite_stack.push(new suite_scope());
        PythonParser.suite_return retval = new PythonParser.suite_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token NEWLINE178=null;
        Token INDENT179=null;
        Token DEDENT181=null;
        PythonParser.simple_stmt_return simple_stmt177 = null;

        PythonParser.stmt_return stmt180 = null;


        PythonTree NEWLINE178_tree=null;
        PythonTree INDENT179_tree=null;
        PythonTree DEDENT181_tree=null;


            if (((suite_scope)suite_stack.peek()).continueIllegal || fromFinally) {
                ((suite_scope)suite_stack.peek()).continueIllegal = true;
            } else {
                ((suite_scope)suite_stack.peek()).continueIllegal = false;
            }
            retval.stypes = new ArrayList();

        try {
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1195:5: ( simple_stmt | NEWLINE INDENT ( stmt )+ DEDENT )
            int alt77=2;
            int LA77_0 = input.LA(1);

            if ( (LA77_0==NAME||(LA77_0>=ASSERT && LA77_0<=BREAK)||LA77_0==CONTINUE||LA77_0==DELETE||LA77_0==EXEC||LA77_0==FROM||LA77_0==GLOBAL||LA77_0==IMPORT||(LA77_0>=LAMBDA && LA77_0<=NOT)||(LA77_0>=PASS && LA77_0<=RETURN)||LA77_0==YIELD||LA77_0==LPAREN||(LA77_0>=PLUS && LA77_0<=MINUS)||(LA77_0>=TILDE && LA77_0<=LBRACK)||LA77_0==LCURLY||(LA77_0>=BACKQUOTE && LA77_0<=STRING)) ) {
                alt77=1;
            }
            else if ( (LA77_0==NEWLINE) ) {
                alt77=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 77, 0, input);

                throw nvae;
            }
            switch (alt77) {
                case 1 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1195:7: simple_stmt
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    pushFollow(FOLLOW_simple_stmt_in_suite4119);
                    simple_stmt177=simple_stmt();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, simple_stmt177.getTree());
                    if ( state.backtracking==0 ) {

                                retval.stypes = (simple_stmt177!=null?simple_stmt177.stypes:null);
                            
                    }

                    }
                    break;
                case 2 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1199:7: NEWLINE INDENT ( stmt )+ DEDENT
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    NEWLINE178=(Token)match(input,NEWLINE,FOLLOW_NEWLINE_in_suite4135); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    NEWLINE178_tree = (PythonTree)adaptor.create(NEWLINE178);
                    adaptor.addChild(root_0, NEWLINE178_tree);
                    }
                    INDENT179=(Token)match(input,INDENT,FOLLOW_INDENT_in_suite4137); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    INDENT179_tree = (PythonTree)adaptor.create(INDENT179);
                    adaptor.addChild(root_0, INDENT179_tree);
                    }
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1200:7: ( stmt )+
                    int cnt76=0;
                    loop76:
                    do {
                        int alt76=2;
                        int LA76_0 = input.LA(1);

                        if ( (LA76_0==NAME||(LA76_0>=ASSERT && LA76_0<=DELETE)||LA76_0==EXEC||(LA76_0>=FROM && LA76_0<=IMPORT)||(LA76_0>=LAMBDA && LA76_0<=NOT)||(LA76_0>=PASS && LA76_0<=LPAREN)||(LA76_0>=PLUS && LA76_0<=MINUS)||(LA76_0>=TILDE && LA76_0<=LBRACK)||LA76_0==LCURLY||(LA76_0>=BACKQUOTE && LA76_0<=STRING)) ) {
                            alt76=1;
                        }


                        switch (alt76) {
                    	case 1 :
                    	    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1200:8: stmt
                    	    {
                    	    pushFollow(FOLLOW_stmt_in_suite4146);
                    	    stmt180=stmt();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) adaptor.addChild(root_0, stmt180.getTree());
                    	    if ( state.backtracking==0 ) {

                    	                 if ((stmt180!=null?stmt180.stypes:null) != null) {
                    	                     retval.stypes.addAll((stmt180!=null?stmt180.stypes:null));
                    	                 }
                    	             
                    	    }

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt76 >= 1 ) break loop76;
                    	    if (state.backtracking>0) {state.failed=true; return retval;}
                                EarlyExitException eee =
                                    new EarlyExitException(76, input);
                                throw eee;
                        }
                        cnt76++;
                    } while (true);

                    DEDENT181=(Token)match(input,DEDENT,FOLLOW_DEDENT_in_suite4166); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    DEDENT181_tree = (PythonTree)adaptor.create(DEDENT181);
                    adaptor.addChild(root_0, DEDENT181_tree);
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException re) {
            errorHandler.reportError(this, re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
            suite_stack.pop();
        }
        return retval;
    }
    // $ANTLR end "suite"

    public static class test_return extends ParserRuleReturnScope {
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "test"
    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1210:1: test[expr_contextType ctype] : (o1= or_test[ctype] ( ( IF or_test[null] ORELSE )=> IF o2= or_test[ctype] ORELSE e= test[expr_contextType.Load] | -> or_test ) | lambdef );
    public final PythonParser.test_return test(expr_contextType ctype) throws RecognitionException {
        PythonParser.test_return retval = new PythonParser.test_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token IF182=null;
        Token ORELSE183=null;
        PythonParser.or_test_return o1 = null;

        PythonParser.or_test_return o2 = null;

        PythonParser.test_return e = null;

        PythonParser.lambdef_return lambdef184 = null;


        PythonTree IF182_tree=null;
        PythonTree ORELSE183_tree=null;
        RewriteRuleTokenStream stream_ORELSE=new RewriteRuleTokenStream(adaptor,"token ORELSE");
        RewriteRuleTokenStream stream_IF=new RewriteRuleTokenStream(adaptor,"token IF");
        RewriteRuleSubtreeStream stream_test=new RewriteRuleSubtreeStream(adaptor,"rule test");
        RewriteRuleSubtreeStream stream_or_test=new RewriteRuleSubtreeStream(adaptor,"rule or_test");

            expr etype = null;

        try {
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1219:5: (o1= or_test[ctype] ( ( IF or_test[null] ORELSE )=> IF o2= or_test[ctype] ORELSE e= test[expr_contextType.Load] | -> or_test ) | lambdef )
            int alt79=2;
            int LA79_0 = input.LA(1);

            if ( (LA79_0==NAME||LA79_0==NOT||LA79_0==LPAREN||(LA79_0>=PLUS && LA79_0<=MINUS)||(LA79_0>=TILDE && LA79_0<=LBRACK)||LA79_0==LCURLY||(LA79_0>=BACKQUOTE && LA79_0<=STRING)) ) {
                alt79=1;
            }
            else if ( (LA79_0==LAMBDA) ) {
                alt79=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 79, 0, input);

                throw nvae;
            }
            switch (alt79) {
                case 1 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1219:6: o1= or_test[ctype] ( ( IF or_test[null] ORELSE )=> IF o2= or_test[ctype] ORELSE e= test[expr_contextType.Load] | -> or_test )
                    {
                    pushFollow(FOLLOW_or_test_in_test4196);
                    o1=or_test(ctype);

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_or_test.add(o1.getTree());
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1220:7: ( ( IF or_test[null] ORELSE )=> IF o2= or_test[ctype] ORELSE e= test[expr_contextType.Load] | -> or_test )
                    int alt78=2;
                    alt78 = dfa78.predict(input);
                    switch (alt78) {
                        case 1 :
                            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1220:9: ( IF or_test[null] ORELSE )=> IF o2= or_test[ctype] ORELSE e= test[expr_contextType.Load]
                            {
                            IF182=(Token)match(input,IF,FOLLOW_IF_in_test4218); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_IF.add(IF182);

                            pushFollow(FOLLOW_or_test_in_test4222);
                            o2=or_test(ctype);

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_or_test.add(o2.getTree());
                            ORELSE183=(Token)match(input,ORELSE,FOLLOW_ORELSE_in_test4225); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_ORELSE.add(ORELSE183);

                            pushFollow(FOLLOW_test_in_test4229);
                            e=test(expr_contextType.Load);

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_test.add(e.getTree());
                            if ( state.backtracking==0 ) {

                                           etype = new IfExp((o1!=null?((Token)o1.start):null), actions.castExpr((o2!=null?((PythonTree)o2.tree):null)), actions.castExpr((o1!=null?((PythonTree)o1.tree):null)), actions.castExpr((e!=null?((PythonTree)e.tree):null)));
                                       
                            }

                            }
                            break;
                        case 2 :
                            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1225:6: 
                            {

                            // AST REWRITE
                            // elements: or_test
                            // token labels: 
                            // rule labels: retval
                            // token list labels: 
                            // rule list labels: 
                            // wildcard labels: 
                            if ( state.backtracking==0 ) {
                            retval.tree = root_0;
                            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                            root_0 = (PythonTree)adaptor.nil();
                            // 1225:6: -> or_test
                            {
                                adaptor.addChild(root_0, stream_or_test.nextTree());

                            }

                            retval.tree = root_0;}
                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1227:7: lambdef
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    pushFollow(FOLLOW_lambdef_in_test4274);
                    lambdef184=lambdef();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, lambdef184.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {

                 if (etype != null) {
                     retval.tree = etype;
                 }

            }
        }

        catch (RecognitionException re) {
            errorHandler.reportError(this, re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "test"

    public static class or_test_return extends ParserRuleReturnScope {
        public Token leftTok;
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "or_test"
    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1231:1: or_test[expr_contextType ctype] returns [Token leftTok] : left= and_test[ctype] ( (or= OR right+= and_test[ctype] )+ | -> $left) ;
    public final PythonParser.or_test_return or_test(expr_contextType ctype) throws RecognitionException {
        PythonParser.or_test_return retval = new PythonParser.or_test_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token or=null;
        List list_right=null;
        PythonParser.and_test_return left = null;

        PythonParser.and_test_return right = null;
         right = null;
        PythonTree or_tree=null;
        RewriteRuleTokenStream stream_OR=new RewriteRuleTokenStream(adaptor,"token OR");
        RewriteRuleSubtreeStream stream_and_test=new RewriteRuleSubtreeStream(adaptor,"rule and_test");
        try {
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1242:5: (left= and_test[ctype] ( (or= OR right+= and_test[ctype] )+ | -> $left) )
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1242:7: left= and_test[ctype] ( (or= OR right+= and_test[ctype] )+ | -> $left)
            {
            pushFollow(FOLLOW_and_test_in_or_test4309);
            left=and_test(ctype);

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_and_test.add(left.getTree());
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1243:9: ( (or= OR right+= and_test[ctype] )+ | -> $left)
            int alt81=2;
            int LA81_0 = input.LA(1);

            if ( (LA81_0==OR) ) {
                alt81=1;
            }
            else if ( (LA81_0==EOF||LA81_0==NEWLINE||LA81_0==NAME||LA81_0==AS||LA81_0==FOR||LA81_0==IF||LA81_0==ORELSE||(LA81_0>=RPAREN && LA81_0<=COMMA)||(LA81_0>=SEMI && LA81_0<=DOUBLESLASHEQUAL)||LA81_0==RBRACK||(LA81_0>=RCURLY && LA81_0<=BACKQUOTE)) ) {
                alt81=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 81, 0, input);

                throw nvae;
            }
            switch (alt81) {
                case 1 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1243:11: (or= OR right+= and_test[ctype] )+
                    {
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1243:11: (or= OR right+= and_test[ctype] )+
                    int cnt80=0;
                    loop80:
                    do {
                        int alt80=2;
                        int LA80_0 = input.LA(1);

                        if ( (LA80_0==OR) ) {
                            alt80=1;
                        }


                        switch (alt80) {
                    	case 1 :
                    	    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1243:12: or= OR right+= and_test[ctype]
                    	    {
                    	    or=(Token)match(input,OR,FOLLOW_OR_in_or_test4325); if (state.failed) return retval; 
                    	    if ( state.backtracking==0 ) stream_OR.add(or);

                    	    pushFollow(FOLLOW_and_test_in_or_test4329);
                    	    right=and_test(ctype);

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) stream_and_test.add(right.getTree());
                    	    if (list_right==null) list_right=new ArrayList();
                    	    list_right.add(right.getTree());


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt80 >= 1 ) break loop80;
                    	    if (state.backtracking>0) {state.failed=true; return retval;}
                                EarlyExitException eee =
                                    new EarlyExitException(80, input);
                                throw eee;
                        }
                        cnt80++;
                    } while (true);


                    }
                    break;
                case 2 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1246:8: 
                    {

                    // AST REWRITE
                    // elements: left
                    // token labels: 
                    // rule labels: retval, left
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
                    RewriteRuleSubtreeStream stream_left=new RewriteRuleSubtreeStream(adaptor,"rule left",left!=null?left.tree:null);

                    root_0 = (PythonTree)adaptor.nil();
                    // 1246:8: -> $left
                    {
                        adaptor.addChild(root_0, stream_left.nextTree());

                    }

                    retval.tree = root_0;}
                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {

                  if (or != null) {
                      Token tok = (left!=null?((Token)left.start):null);
                      if ((left!=null?left.leftTok:null) != null) {
                          tok = (left!=null?left.leftTok:null);
                      }
                      retval.tree = actions.makeBoolOp(tok, (left!=null?((PythonTree)left.tree):null), boolopType.Or, list_right);
                  }

            }
        }

        catch (RecognitionException re) {
            errorHandler.reportError(this, re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "or_test"

    public static class and_test_return extends ParserRuleReturnScope {
        public Token leftTok;
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "and_test"
    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1251:1: and_test[expr_contextType ctype] returns [Token leftTok] : left= not_test[ctype] ( (and= AND right+= not_test[ctype] )+ | -> $left) ;
    public final PythonParser.and_test_return and_test(expr_contextType ctype) throws RecognitionException {
        PythonParser.and_test_return retval = new PythonParser.and_test_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token and=null;
        List list_right=null;
        PythonParser.not_test_return left = null;

        PythonParser.not_test_return right = null;
         right = null;
        PythonTree and_tree=null;
        RewriteRuleTokenStream stream_AND=new RewriteRuleTokenStream(adaptor,"token AND");
        RewriteRuleSubtreeStream stream_not_test=new RewriteRuleSubtreeStream(adaptor,"rule not_test");
        try {
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1262:5: (left= not_test[ctype] ( (and= AND right+= not_test[ctype] )+ | -> $left) )
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1262:7: left= not_test[ctype] ( (and= AND right+= not_test[ctype] )+ | -> $left)
            {
            pushFollow(FOLLOW_not_test_in_and_test4410);
            left=not_test(ctype);

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_not_test.add(left.getTree());
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1263:9: ( (and= AND right+= not_test[ctype] )+ | -> $left)
            int alt83=2;
            int LA83_0 = input.LA(1);

            if ( (LA83_0==AND) ) {
                alt83=1;
            }
            else if ( (LA83_0==EOF||LA83_0==NEWLINE||LA83_0==NAME||LA83_0==AS||LA83_0==FOR||LA83_0==IF||(LA83_0>=OR && LA83_0<=ORELSE)||(LA83_0>=RPAREN && LA83_0<=COMMA)||(LA83_0>=SEMI && LA83_0<=DOUBLESLASHEQUAL)||LA83_0==RBRACK||(LA83_0>=RCURLY && LA83_0<=BACKQUOTE)) ) {
                alt83=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 83, 0, input);

                throw nvae;
            }
            switch (alt83) {
                case 1 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1263:11: (and= AND right+= not_test[ctype] )+
                    {
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1263:11: (and= AND right+= not_test[ctype] )+
                    int cnt82=0;
                    loop82:
                    do {
                        int alt82=2;
                        int LA82_0 = input.LA(1);

                        if ( (LA82_0==AND) ) {
                            alt82=1;
                        }


                        switch (alt82) {
                    	case 1 :
                    	    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1263:12: and= AND right+= not_test[ctype]
                    	    {
                    	    and=(Token)match(input,AND,FOLLOW_AND_in_and_test4426); if (state.failed) return retval; 
                    	    if ( state.backtracking==0 ) stream_AND.add(and);

                    	    pushFollow(FOLLOW_not_test_in_and_test4430);
                    	    right=not_test(ctype);

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) stream_not_test.add(right.getTree());
                    	    if (list_right==null) list_right=new ArrayList();
                    	    list_right.add(right.getTree());


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt82 >= 1 ) break loop82;
                    	    if (state.backtracking>0) {state.failed=true; return retval;}
                                EarlyExitException eee =
                                    new EarlyExitException(82, input);
                                throw eee;
                        }
                        cnt82++;
                    } while (true);


                    }
                    break;
                case 2 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1266:8: 
                    {

                    // AST REWRITE
                    // elements: left
                    // token labels: 
                    // rule labels: retval, left
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
                    RewriteRuleSubtreeStream stream_left=new RewriteRuleSubtreeStream(adaptor,"rule left",left!=null?left.tree:null);

                    root_0 = (PythonTree)adaptor.nil();
                    // 1266:8: -> $left
                    {
                        adaptor.addChild(root_0, stream_left.nextTree());

                    }

                    retval.tree = root_0;}
                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {

                  if (and != null) {
                      Token tok = (left!=null?((Token)left.start):null);
                      if ((left!=null?left.leftTok:null) != null) {
                          tok = (left!=null?left.leftTok:null);
                      }
                      retval.tree = actions.makeBoolOp(tok, (left!=null?((PythonTree)left.tree):null), boolopType.And, list_right);
                  }

            }
        }

        catch (RecognitionException re) {
            errorHandler.reportError(this, re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "and_test"

    public static class not_test_return extends ParserRuleReturnScope {
        public Token leftTok;
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "not_test"
    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1271:1: not_test[expr_contextType ctype] returns [Token leftTok] : ( NOT nt= not_test[ctype] | comparison[ctype] );
    public final PythonParser.not_test_return not_test(expr_contextType ctype) throws RecognitionException {
        PythonParser.not_test_return retval = new PythonParser.not_test_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token NOT185=null;
        PythonParser.not_test_return nt = null;

        PythonParser.comparison_return comparison186 = null;


        PythonTree NOT185_tree=null;


            expr etype = null;

        try {
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1281:5: ( NOT nt= not_test[ctype] | comparison[ctype] )
            int alt84=2;
            int LA84_0 = input.LA(1);

            if ( (LA84_0==NOT) ) {
                alt84=1;
            }
            else if ( (LA84_0==NAME||LA84_0==LPAREN||(LA84_0>=PLUS && LA84_0<=MINUS)||(LA84_0>=TILDE && LA84_0<=LBRACK)||LA84_0==LCURLY||(LA84_0>=BACKQUOTE && LA84_0<=STRING)) ) {
                alt84=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 84, 0, input);

                throw nvae;
            }
            switch (alt84) {
                case 1 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1281:7: NOT nt= not_test[ctype]
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    NOT185=(Token)match(input,NOT,FOLLOW_NOT_in_not_test4514); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    NOT185_tree = (PythonTree)adaptor.create(NOT185);
                    adaptor.addChild(root_0, NOT185_tree);
                    }
                    pushFollow(FOLLOW_not_test_in_not_test4518);
                    nt=not_test(ctype);

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, nt.getTree());
                    if ( state.backtracking==0 ) {

                                etype = new UnaryOp(NOT185, unaryopType.Not, actions.castExpr((nt!=null?((PythonTree)nt.tree):null)));
                            
                    }

                    }
                    break;
                case 2 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1285:7: comparison[ctype]
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    pushFollow(FOLLOW_comparison_in_not_test4535);
                    comparison186=comparison(ctype);

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, comparison186.getTree());
                    if ( state.backtracking==0 ) {

                                retval.leftTok = (comparison186!=null?comparison186.leftTok:null);
                            
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {

                 if (etype != null) {
                     retval.tree = etype;
                 }

            }
        }

        catch (RecognitionException re) {
            errorHandler.reportError(this, re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "not_test"

    public static class comparison_return extends ParserRuleReturnScope {
        public Token leftTok;
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "comparison"
    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1292:1: comparison[expr_contextType ctype] returns [Token leftTok] : left= expr[ctype] ( ( comp_op right+= expr[ctype] )+ | -> $left) ;
    public final PythonParser.comparison_return comparison(expr_contextType ctype) throws RecognitionException {
        PythonParser.comparison_return retval = new PythonParser.comparison_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        List list_right=null;
        PythonParser.expr_return left = null;

        PythonParser.comp_op_return comp_op187 = null;

        PythonParser.expr_return right = null;
         right = null;
        RewriteRuleSubtreeStream stream_expr=new RewriteRuleSubtreeStream(adaptor,"rule expr");
        RewriteRuleSubtreeStream stream_comp_op=new RewriteRuleSubtreeStream(adaptor,"rule comp_op");

            List cmps = new ArrayList();

        try {
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1304:5: (left= expr[ctype] ( ( comp_op right+= expr[ctype] )+ | -> $left) )
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1304:7: left= expr[ctype] ( ( comp_op right+= expr[ctype] )+ | -> $left)
            {
            pushFollow(FOLLOW_expr_in_comparison4584);
            left=expr(ctype);

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_expr.add(left.getTree());
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1305:8: ( ( comp_op right+= expr[ctype] )+ | -> $left)
            int alt86=2;
            int LA86_0 = input.LA(1);

            if ( ((LA86_0>=IN && LA86_0<=IS)||LA86_0==NOT||(LA86_0>=LESS && LA86_0<=NOTEQUAL)) ) {
                alt86=1;
            }
            else if ( (LA86_0==EOF||LA86_0==NEWLINE||LA86_0==NAME||(LA86_0>=AND && LA86_0<=AS)||LA86_0==FOR||LA86_0==IF||(LA86_0>=OR && LA86_0<=ORELSE)||(LA86_0>=RPAREN && LA86_0<=COMMA)||(LA86_0>=SEMI && LA86_0<=DOUBLESLASHEQUAL)||LA86_0==RBRACK||(LA86_0>=RCURLY && LA86_0<=BACKQUOTE)) ) {
                alt86=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 86, 0, input);

                throw nvae;
            }
            switch (alt86) {
                case 1 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1305:10: ( comp_op right+= expr[ctype] )+
                    {
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1305:10: ( comp_op right+= expr[ctype] )+
                    int cnt85=0;
                    loop85:
                    do {
                        int alt85=2;
                        int LA85_0 = input.LA(1);

                        if ( ((LA85_0>=IN && LA85_0<=IS)||LA85_0==NOT||(LA85_0>=LESS && LA85_0<=NOTEQUAL)) ) {
                            alt85=1;
                        }


                        switch (alt85) {
                    	case 1 :
                    	    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1305:12: comp_op right+= expr[ctype]
                    	    {
                    	    pushFollow(FOLLOW_comp_op_in_comparison4598);
                    	    comp_op187=comp_op();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) stream_comp_op.add(comp_op187.getTree());
                    	    pushFollow(FOLLOW_expr_in_comparison4602);
                    	    right=expr(ctype);

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) stream_expr.add(right.getTree());
                    	    if (list_right==null) list_right=new ArrayList();
                    	    list_right.add(right.getTree());

                    	    if ( state.backtracking==0 ) {

                    	                     cmps.add((comp_op187!=null?comp_op187.op:null));
                    	                 
                    	    }

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt85 >= 1 ) break loop85;
                    	    if (state.backtracking>0) {state.failed=true; return retval;}
                                EarlyExitException eee =
                                    new EarlyExitException(85, input);
                                throw eee;
                        }
                        cnt85++;
                    } while (true);


                    }
                    break;
                case 2 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1311:7: 
                    {

                    // AST REWRITE
                    // elements: left
                    // token labels: 
                    // rule labels: retval, left
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
                    RewriteRuleSubtreeStream stream_left=new RewriteRuleSubtreeStream(adaptor,"rule left",left!=null?left.tree:null);

                    root_0 = (PythonTree)adaptor.nil();
                    // 1311:7: -> $left
                    {
                        adaptor.addChild(root_0, stream_left.nextTree());

                    }

                    retval.tree = root_0;}
                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {

                  retval.leftTok = (left!=null?left.leftTok:null);
                  if (!cmps.isEmpty()) {
                      retval.tree = new Compare((left!=null?((Token)left.start):null), actions.castExpr((left!=null?((PythonTree)left.tree):null)), actions.makeCmpOps(cmps),
                          actions.castExprs(list_right));
                  }

            }
        }

        catch (RecognitionException re) {
            errorHandler.reportError(this, re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "comparison"

    public static class comp_op_return extends ParserRuleReturnScope {
        public cmpopType op;
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "comp_op"
    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1316:1: comp_op returns [cmpopType op] : ( LESS | GREATER | EQUAL | GREATEREQUAL | LESSEQUAL | ALT_NOTEQUAL | NOTEQUAL | IN | NOT IN | IS | IS NOT );
    public final PythonParser.comp_op_return comp_op() throws RecognitionException {
        PythonParser.comp_op_return retval = new PythonParser.comp_op_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token LESS188=null;
        Token GREATER189=null;
        Token EQUAL190=null;
        Token GREATEREQUAL191=null;
        Token LESSEQUAL192=null;
        Token ALT_NOTEQUAL193=null;
        Token NOTEQUAL194=null;
        Token IN195=null;
        Token NOT196=null;
        Token IN197=null;
        Token IS198=null;
        Token IS199=null;
        Token NOT200=null;

        PythonTree LESS188_tree=null;
        PythonTree GREATER189_tree=null;
        PythonTree EQUAL190_tree=null;
        PythonTree GREATEREQUAL191_tree=null;
        PythonTree LESSEQUAL192_tree=null;
        PythonTree ALT_NOTEQUAL193_tree=null;
        PythonTree NOTEQUAL194_tree=null;
        PythonTree IN195_tree=null;
        PythonTree NOT196_tree=null;
        PythonTree IN197_tree=null;
        PythonTree IS198_tree=null;
        PythonTree IS199_tree=null;
        PythonTree NOT200_tree=null;

        try {
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1318:5: ( LESS | GREATER | EQUAL | GREATEREQUAL | LESSEQUAL | ALT_NOTEQUAL | NOTEQUAL | IN | NOT IN | IS | IS NOT )
            int alt87=11;
            alt87 = dfa87.predict(input);
            switch (alt87) {
                case 1 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1318:7: LESS
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    LESS188=(Token)match(input,LESS,FOLLOW_LESS_in_comp_op4683); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    LESS188_tree = (PythonTree)adaptor.create(LESS188);
                    adaptor.addChild(root_0, LESS188_tree);
                    }
                    if ( state.backtracking==0 ) {

                                retval.op = cmpopType.Lt;
                            
                    }

                    }
                    break;
                case 2 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1322:7: GREATER
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    GREATER189=(Token)match(input,GREATER,FOLLOW_GREATER_in_comp_op4699); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    GREATER189_tree = (PythonTree)adaptor.create(GREATER189);
                    adaptor.addChild(root_0, GREATER189_tree);
                    }
                    if ( state.backtracking==0 ) {

                                retval.op = cmpopType.Gt;
                            
                    }

                    }
                    break;
                case 3 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1326:7: EQUAL
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    EQUAL190=(Token)match(input,EQUAL,FOLLOW_EQUAL_in_comp_op4715); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    EQUAL190_tree = (PythonTree)adaptor.create(EQUAL190);
                    adaptor.addChild(root_0, EQUAL190_tree);
                    }
                    if ( state.backtracking==0 ) {

                                retval.op = cmpopType.Eq;
                            
                    }

                    }
                    break;
                case 4 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1330:7: GREATEREQUAL
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    GREATEREQUAL191=(Token)match(input,GREATEREQUAL,FOLLOW_GREATEREQUAL_in_comp_op4731); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    GREATEREQUAL191_tree = (PythonTree)adaptor.create(GREATEREQUAL191);
                    adaptor.addChild(root_0, GREATEREQUAL191_tree);
                    }
                    if ( state.backtracking==0 ) {

                                retval.op = cmpopType.GtE;
                            
                    }

                    }
                    break;
                case 5 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1334:7: LESSEQUAL
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    LESSEQUAL192=(Token)match(input,LESSEQUAL,FOLLOW_LESSEQUAL_in_comp_op4747); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    LESSEQUAL192_tree = (PythonTree)adaptor.create(LESSEQUAL192);
                    adaptor.addChild(root_0, LESSEQUAL192_tree);
                    }
                    if ( state.backtracking==0 ) {

                                retval.op = cmpopType.LtE;
                            
                    }

                    }
                    break;
                case 6 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1338:7: ALT_NOTEQUAL
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    ALT_NOTEQUAL193=(Token)match(input,ALT_NOTEQUAL,FOLLOW_ALT_NOTEQUAL_in_comp_op4763); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    ALT_NOTEQUAL193_tree = (PythonTree)adaptor.create(ALT_NOTEQUAL193);
                    adaptor.addChild(root_0, ALT_NOTEQUAL193_tree);
                    }
                    if ( state.backtracking==0 ) {

                                retval.op = cmpopType.NotEq;
                            
                    }

                    }
                    break;
                case 7 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1342:7: NOTEQUAL
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    NOTEQUAL194=(Token)match(input,NOTEQUAL,FOLLOW_NOTEQUAL_in_comp_op4779); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    NOTEQUAL194_tree = (PythonTree)adaptor.create(NOTEQUAL194);
                    adaptor.addChild(root_0, NOTEQUAL194_tree);
                    }
                    if ( state.backtracking==0 ) {

                                retval.op = cmpopType.NotEq;
                            
                    }

                    }
                    break;
                case 8 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1346:7: IN
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    IN195=(Token)match(input,IN,FOLLOW_IN_in_comp_op4795); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    IN195_tree = (PythonTree)adaptor.create(IN195);
                    adaptor.addChild(root_0, IN195_tree);
                    }
                    if ( state.backtracking==0 ) {

                                retval.op = cmpopType.In;
                            
                    }

                    }
                    break;
                case 9 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1350:7: NOT IN
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    NOT196=(Token)match(input,NOT,FOLLOW_NOT_in_comp_op4811); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    NOT196_tree = (PythonTree)adaptor.create(NOT196);
                    adaptor.addChild(root_0, NOT196_tree);
                    }
                    IN197=(Token)match(input,IN,FOLLOW_IN_in_comp_op4813); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    IN197_tree = (PythonTree)adaptor.create(IN197);
                    adaptor.addChild(root_0, IN197_tree);
                    }
                    if ( state.backtracking==0 ) {

                                retval.op = cmpopType.NotIn;
                            
                    }

                    }
                    break;
                case 10 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1354:7: IS
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    IS198=(Token)match(input,IS,FOLLOW_IS_in_comp_op4829); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    IS198_tree = (PythonTree)adaptor.create(IS198);
                    adaptor.addChild(root_0, IS198_tree);
                    }
                    if ( state.backtracking==0 ) {

                                retval.op = cmpopType.Is;
                            
                    }

                    }
                    break;
                case 11 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1358:7: IS NOT
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    IS199=(Token)match(input,IS,FOLLOW_IS_in_comp_op4845); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    IS199_tree = (PythonTree)adaptor.create(IS199);
                    adaptor.addChild(root_0, IS199_tree);
                    }
                    NOT200=(Token)match(input,NOT,FOLLOW_NOT_in_comp_op4847); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    NOT200_tree = (PythonTree)adaptor.create(NOT200);
                    adaptor.addChild(root_0, NOT200_tree);
                    }
                    if ( state.backtracking==0 ) {

                                retval.op = cmpopType.IsNot;
                            
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException re) {
            errorHandler.reportError(this, re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "comp_op"

    protected static class expr_scope {
        expr_contextType ctype;
    }
    protected Stack expr_stack = new Stack();

    public static class expr_return extends ParserRuleReturnScope {
        public Token leftTok;
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "expr"
    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1365:1: expr[expr_contextType ect] returns [Token leftTok] : left= xor_expr ( (op= VBAR right+= xor_expr )+ | -> $left) ;
    public final PythonParser.expr_return expr(expr_contextType ect) throws RecognitionException {
        expr_stack.push(new expr_scope());
        PythonParser.expr_return retval = new PythonParser.expr_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token op=null;
        List list_right=null;
        PythonParser.xor_expr_return left = null;

        PythonParser.xor_expr_return right = null;
         right = null;
        PythonTree op_tree=null;
        RewriteRuleTokenStream stream_VBAR=new RewriteRuleTokenStream(adaptor,"token VBAR");
        RewriteRuleSubtreeStream stream_xor_expr=new RewriteRuleSubtreeStream(adaptor,"rule xor_expr");

            ((expr_scope)expr_stack.peek()).ctype = ect;

        try {
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1383:5: (left= xor_expr ( (op= VBAR right+= xor_expr )+ | -> $left) )
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1383:7: left= xor_expr ( (op= VBAR right+= xor_expr )+ | -> $left)
            {
            pushFollow(FOLLOW_xor_expr_in_expr4899);
            left=xor_expr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_xor_expr.add(left.getTree());
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1384:9: ( (op= VBAR right+= xor_expr )+ | -> $left)
            int alt89=2;
            int LA89_0 = input.LA(1);

            if ( (LA89_0==VBAR) ) {
                alt89=1;
            }
            else if ( (LA89_0==EOF||LA89_0==NEWLINE||LA89_0==NAME||(LA89_0>=AND && LA89_0<=AS)||LA89_0==FOR||LA89_0==IF||(LA89_0>=IN && LA89_0<=IS)||(LA89_0>=NOT && LA89_0<=ORELSE)||(LA89_0>=RPAREN && LA89_0<=COMMA)||(LA89_0>=SEMI && LA89_0<=DOUBLESLASHEQUAL)||(LA89_0>=LESS && LA89_0<=NOTEQUAL)||LA89_0==RBRACK||(LA89_0>=RCURLY && LA89_0<=BACKQUOTE)) ) {
                alt89=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 89, 0, input);

                throw nvae;
            }
            switch (alt89) {
                case 1 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1384:11: (op= VBAR right+= xor_expr )+
                    {
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1384:11: (op= VBAR right+= xor_expr )+
                    int cnt88=0;
                    loop88:
                    do {
                        int alt88=2;
                        int LA88_0 = input.LA(1);

                        if ( (LA88_0==VBAR) ) {
                            alt88=1;
                        }


                        switch (alt88) {
                    	case 1 :
                    	    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1384:12: op= VBAR right+= xor_expr
                    	    {
                    	    op=(Token)match(input,VBAR,FOLLOW_VBAR_in_expr4914); if (state.failed) return retval; 
                    	    if ( state.backtracking==0 ) stream_VBAR.add(op);

                    	    pushFollow(FOLLOW_xor_expr_in_expr4918);
                    	    right=xor_expr();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) stream_xor_expr.add(right.getTree());
                    	    if (list_right==null) list_right=new ArrayList();
                    	    list_right.add(right.getTree());


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt88 >= 1 ) break loop88;
                    	    if (state.backtracking>0) {state.failed=true; return retval;}
                                EarlyExitException eee =
                                    new EarlyExitException(88, input);
                                throw eee;
                        }
                        cnt88++;
                    } while (true);


                    }
                    break;
                case 2 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1387:8: 
                    {

                    // AST REWRITE
                    // elements: left
                    // token labels: 
                    // rule labels: retval, left
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
                    RewriteRuleSubtreeStream stream_left=new RewriteRuleSubtreeStream(adaptor,"rule left",left!=null?left.tree:null);

                    root_0 = (PythonTree)adaptor.nil();
                    // 1387:8: -> $left
                    {
                        adaptor.addChild(root_0, stream_left.nextTree());

                    }

                    retval.tree = root_0;}
                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {

                  retval.leftTok = (left!=null?left.lparen:null);
                  if (op != null) {
                      Token tok = (left!=null?((Token)left.start):null);
                      if ((left!=null?left.lparen:null) != null) {
                          tok = (left!=null?left.lparen:null);
                      }
                      retval.tree = actions.makeBinOp(tok, (left!=null?((PythonTree)left.tree):null), operatorType.BitOr, list_right);
                  }

            }
        }

        catch (RecognitionException re) {
            errorHandler.reportError(this, re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
            expr_stack.pop();
        }
        return retval;
    }
    // $ANTLR end "expr"

    public static class xor_expr_return extends ParserRuleReturnScope {
        public Token lparen = null;
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "xor_expr"
    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1393:1: xor_expr returns [Token lparen = null] : left= and_expr ( (op= CIRCUMFLEX right+= and_expr )+ | -> $left) ;
    public final PythonParser.xor_expr_return xor_expr() throws RecognitionException {
        PythonParser.xor_expr_return retval = new PythonParser.xor_expr_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token op=null;
        List list_right=null;
        PythonParser.and_expr_return left = null;

        PythonParser.and_expr_return right = null;
         right = null;
        PythonTree op_tree=null;
        RewriteRuleTokenStream stream_CIRCUMFLEX=new RewriteRuleTokenStream(adaptor,"token CIRCUMFLEX");
        RewriteRuleSubtreeStream stream_and_expr=new RewriteRuleSubtreeStream(adaptor,"rule and_expr");
        try {
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1405:5: (left= and_expr ( (op= CIRCUMFLEX right+= and_expr )+ | -> $left) )
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1405:7: left= and_expr ( (op= CIRCUMFLEX right+= and_expr )+ | -> $left)
            {
            pushFollow(FOLLOW_and_expr_in_xor_expr4997);
            left=and_expr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_and_expr.add(left.getTree());
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1406:9: ( (op= CIRCUMFLEX right+= and_expr )+ | -> $left)
            int alt91=2;
            int LA91_0 = input.LA(1);

            if ( (LA91_0==CIRCUMFLEX) ) {
                alt91=1;
            }
            else if ( (LA91_0==EOF||LA91_0==NEWLINE||LA91_0==NAME||(LA91_0>=AND && LA91_0<=AS)||LA91_0==FOR||LA91_0==IF||(LA91_0>=IN && LA91_0<=IS)||(LA91_0>=NOT && LA91_0<=ORELSE)||(LA91_0>=RPAREN && LA91_0<=COMMA)||(LA91_0>=SEMI && LA91_0<=DOUBLESLASHEQUAL)||(LA91_0>=LESS && LA91_0<=VBAR)||LA91_0==RBRACK||(LA91_0>=RCURLY && LA91_0<=BACKQUOTE)) ) {
                alt91=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 91, 0, input);

                throw nvae;
            }
            switch (alt91) {
                case 1 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1406:11: (op= CIRCUMFLEX right+= and_expr )+
                    {
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1406:11: (op= CIRCUMFLEX right+= and_expr )+
                    int cnt90=0;
                    loop90:
                    do {
                        int alt90=2;
                        int LA90_0 = input.LA(1);

                        if ( (LA90_0==CIRCUMFLEX) ) {
                            alt90=1;
                        }


                        switch (alt90) {
                    	case 1 :
                    	    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1406:12: op= CIRCUMFLEX right+= and_expr
                    	    {
                    	    op=(Token)match(input,CIRCUMFLEX,FOLLOW_CIRCUMFLEX_in_xor_expr5012); if (state.failed) return retval; 
                    	    if ( state.backtracking==0 ) stream_CIRCUMFLEX.add(op);

                    	    pushFollow(FOLLOW_and_expr_in_xor_expr5016);
                    	    right=and_expr();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) stream_and_expr.add(right.getTree());
                    	    if (list_right==null) list_right=new ArrayList();
                    	    list_right.add(right.getTree());


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt90 >= 1 ) break loop90;
                    	    if (state.backtracking>0) {state.failed=true; return retval;}
                                EarlyExitException eee =
                                    new EarlyExitException(90, input);
                                throw eee;
                        }
                        cnt90++;
                    } while (true);


                    }
                    break;
                case 2 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1409:8: 
                    {

                    // AST REWRITE
                    // elements: left
                    // token labels: 
                    // rule labels: retval, left
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
                    RewriteRuleSubtreeStream stream_left=new RewriteRuleSubtreeStream(adaptor,"rule left",left!=null?left.tree:null);

                    root_0 = (PythonTree)adaptor.nil();
                    // 1409:8: -> $left
                    {
                        adaptor.addChild(root_0, stream_left.nextTree());

                    }

                    retval.tree = root_0;}
                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {

                  if (op != null) {
                      Token tok = (left!=null?((Token)left.start):null);
                      if ((left!=null?left.lparen:null) != null) {
                          tok = (left!=null?left.lparen:null);
                      }
                      retval.tree = actions.makeBinOp(tok, (left!=null?((PythonTree)left.tree):null), operatorType.BitXor, list_right);
                  }
                  retval.lparen = (left!=null?left.lparen:null);

            }
        }

        catch (RecognitionException re) {
            errorHandler.reportError(this, re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "xor_expr"

    public static class and_expr_return extends ParserRuleReturnScope {
        public Token lparen = null;
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "and_expr"
    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1414:1: and_expr returns [Token lparen = null] : left= shift_expr ( (op= AMPER right+= shift_expr )+ | -> $left) ;
    public final PythonParser.and_expr_return and_expr() throws RecognitionException {
        PythonParser.and_expr_return retval = new PythonParser.and_expr_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token op=null;
        List list_right=null;
        PythonParser.shift_expr_return left = null;

        PythonParser.shift_expr_return right = null;
         right = null;
        PythonTree op_tree=null;
        RewriteRuleTokenStream stream_AMPER=new RewriteRuleTokenStream(adaptor,"token AMPER");
        RewriteRuleSubtreeStream stream_shift_expr=new RewriteRuleSubtreeStream(adaptor,"rule shift_expr");
        try {
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1426:5: (left= shift_expr ( (op= AMPER right+= shift_expr )+ | -> $left) )
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1426:7: left= shift_expr ( (op= AMPER right+= shift_expr )+ | -> $left)
            {
            pushFollow(FOLLOW_shift_expr_in_and_expr5094);
            left=shift_expr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_shift_expr.add(left.getTree());
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1427:9: ( (op= AMPER right+= shift_expr )+ | -> $left)
            int alt93=2;
            int LA93_0 = input.LA(1);

            if ( (LA93_0==AMPER) ) {
                alt93=1;
            }
            else if ( (LA93_0==EOF||LA93_0==NEWLINE||LA93_0==NAME||(LA93_0>=AND && LA93_0<=AS)||LA93_0==FOR||LA93_0==IF||(LA93_0>=IN && LA93_0<=IS)||(LA93_0>=NOT && LA93_0<=ORELSE)||(LA93_0>=RPAREN && LA93_0<=COMMA)||(LA93_0>=SEMI && LA93_0<=DOUBLESLASHEQUAL)||(LA93_0>=LESS && LA93_0<=CIRCUMFLEX)||LA93_0==RBRACK||(LA93_0>=RCURLY && LA93_0<=BACKQUOTE)) ) {
                alt93=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 93, 0, input);

                throw nvae;
            }
            switch (alt93) {
                case 1 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1427:11: (op= AMPER right+= shift_expr )+
                    {
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1427:11: (op= AMPER right+= shift_expr )+
                    int cnt92=0;
                    loop92:
                    do {
                        int alt92=2;
                        int LA92_0 = input.LA(1);

                        if ( (LA92_0==AMPER) ) {
                            alt92=1;
                        }


                        switch (alt92) {
                    	case 1 :
                    	    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1427:12: op= AMPER right+= shift_expr
                    	    {
                    	    op=(Token)match(input,AMPER,FOLLOW_AMPER_in_and_expr5109); if (state.failed) return retval; 
                    	    if ( state.backtracking==0 ) stream_AMPER.add(op);

                    	    pushFollow(FOLLOW_shift_expr_in_and_expr5113);
                    	    right=shift_expr();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) stream_shift_expr.add(right.getTree());
                    	    if (list_right==null) list_right=new ArrayList();
                    	    list_right.add(right.getTree());


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt92 >= 1 ) break loop92;
                    	    if (state.backtracking>0) {state.failed=true; return retval;}
                                EarlyExitException eee =
                                    new EarlyExitException(92, input);
                                throw eee;
                        }
                        cnt92++;
                    } while (true);


                    }
                    break;
                case 2 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1430:8: 
                    {

                    // AST REWRITE
                    // elements: left
                    // token labels: 
                    // rule labels: retval, left
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
                    RewriteRuleSubtreeStream stream_left=new RewriteRuleSubtreeStream(adaptor,"rule left",left!=null?left.tree:null);

                    root_0 = (PythonTree)adaptor.nil();
                    // 1430:8: -> $left
                    {
                        adaptor.addChild(root_0, stream_left.nextTree());

                    }

                    retval.tree = root_0;}
                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {

                  if (op != null) {
                      Token tok = (left!=null?((Token)left.start):null);
                      if ((left!=null?left.lparen:null) != null) {
                          tok = (left!=null?left.lparen:null);
                      }
                      retval.tree = actions.makeBinOp(tok, (left!=null?((PythonTree)left.tree):null), operatorType.BitAnd, list_right);
                  }
                  retval.lparen = (left!=null?left.lparen:null);

            }
        }

        catch (RecognitionException re) {
            errorHandler.reportError(this, re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "and_expr"

    public static class shift_expr_return extends ParserRuleReturnScope {
        public Token lparen = null;
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "shift_expr"
    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1435:1: shift_expr returns [Token lparen = null] : left= arith_expr ( ( shift_op right+= arith_expr )+ | -> $left) ;
    public final PythonParser.shift_expr_return shift_expr() throws RecognitionException {
        PythonParser.shift_expr_return retval = new PythonParser.shift_expr_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        List list_right=null;
        PythonParser.arith_expr_return left = null;

        PythonParser.shift_op_return shift_op201 = null;

        PythonParser.arith_expr_return right = null;
         right = null;
        RewriteRuleSubtreeStream stream_arith_expr=new RewriteRuleSubtreeStream(adaptor,"rule arith_expr");
        RewriteRuleSubtreeStream stream_shift_op=new RewriteRuleSubtreeStream(adaptor,"rule shift_op");

            List ops = new ArrayList();
            List toks = new ArrayList();

        try {
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1451:5: (left= arith_expr ( ( shift_op right+= arith_expr )+ | -> $left) )
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1451:7: left= arith_expr ( ( shift_op right+= arith_expr )+ | -> $left)
            {
            pushFollow(FOLLOW_arith_expr_in_shift_expr5196);
            left=arith_expr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_arith_expr.add(left.getTree());
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1452:9: ( ( shift_op right+= arith_expr )+ | -> $left)
            int alt95=2;
            int LA95_0 = input.LA(1);

            if ( (LA95_0==RIGHTSHIFT||LA95_0==LEFTSHIFT) ) {
                alt95=1;
            }
            else if ( (LA95_0==EOF||LA95_0==NEWLINE||LA95_0==NAME||(LA95_0>=AND && LA95_0<=AS)||LA95_0==FOR||LA95_0==IF||(LA95_0>=IN && LA95_0<=IS)||(LA95_0>=NOT && LA95_0<=ORELSE)||(LA95_0>=RPAREN && LA95_0<=COMMA)||(LA95_0>=SEMI && LA95_0<=DOUBLESLASHEQUAL)||(LA95_0>=LESS && LA95_0<=AMPER)||LA95_0==RBRACK||(LA95_0>=RCURLY && LA95_0<=BACKQUOTE)) ) {
                alt95=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 95, 0, input);

                throw nvae;
            }
            switch (alt95) {
                case 1 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1452:11: ( shift_op right+= arith_expr )+
                    {
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1452:11: ( shift_op right+= arith_expr )+
                    int cnt94=0;
                    loop94:
                    do {
                        int alt94=2;
                        int LA94_0 = input.LA(1);

                        if ( (LA94_0==RIGHTSHIFT||LA94_0==LEFTSHIFT) ) {
                            alt94=1;
                        }


                        switch (alt94) {
                    	case 1 :
                    	    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1452:13: shift_op right+= arith_expr
                    	    {
                    	    pushFollow(FOLLOW_shift_op_in_shift_expr5210);
                    	    shift_op201=shift_op();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) stream_shift_op.add(shift_op201.getTree());
                    	    pushFollow(FOLLOW_arith_expr_in_shift_expr5214);
                    	    right=arith_expr();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) stream_arith_expr.add(right.getTree());
                    	    if (list_right==null) list_right=new ArrayList();
                    	    list_right.add(right.getTree());

                    	    if ( state.backtracking==0 ) {

                    	                      ops.add((shift_op201!=null?shift_op201.op:null));
                    	                      toks.add((shift_op201!=null?((Token)shift_op201.start):null));
                    	                  
                    	    }

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt94 >= 1 ) break loop94;
                    	    if (state.backtracking>0) {state.failed=true; return retval;}
                                EarlyExitException eee =
                                    new EarlyExitException(94, input);
                                throw eee;
                        }
                        cnt94++;
                    } while (true);


                    }
                    break;
                case 2 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1459:8: 
                    {

                    // AST REWRITE
                    // elements: left
                    // token labels: 
                    // rule labels: retval, left
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
                    RewriteRuleSubtreeStream stream_left=new RewriteRuleSubtreeStream(adaptor,"rule left",left!=null?left.tree:null);

                    root_0 = (PythonTree)adaptor.nil();
                    // 1459:8: -> $left
                    {
                        adaptor.addChild(root_0, stream_left.nextTree());

                    }

                    retval.tree = root_0;}
                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {

                  if (!ops.isEmpty()) {
                      Token tok = (left!=null?((Token)left.start):null);
                      if ((left!=null?left.lparen:null) != null) {
                          tok = (left!=null?left.lparen:null);
                      }
                      retval.tree = actions.makeBinOp(tok, (left!=null?((PythonTree)left.tree):null), ops, list_right, toks);
                  }
                  retval.lparen = (left!=null?left.lparen:null);

            }
        }

        catch (RecognitionException re) {
            errorHandler.reportError(this, re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "shift_expr"

    public static class shift_op_return extends ParserRuleReturnScope {
        public operatorType op;
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "shift_op"
    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1463:1: shift_op returns [operatorType op] : ( LEFTSHIFT | RIGHTSHIFT );
    public final PythonParser.shift_op_return shift_op() throws RecognitionException {
        PythonParser.shift_op_return retval = new PythonParser.shift_op_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token LEFTSHIFT202=null;
        Token RIGHTSHIFT203=null;

        PythonTree LEFTSHIFT202_tree=null;
        PythonTree RIGHTSHIFT203_tree=null;

        try {
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1465:5: ( LEFTSHIFT | RIGHTSHIFT )
            int alt96=2;
            int LA96_0 = input.LA(1);

            if ( (LA96_0==LEFTSHIFT) ) {
                alt96=1;
            }
            else if ( (LA96_0==RIGHTSHIFT) ) {
                alt96=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 96, 0, input);

                throw nvae;
            }
            switch (alt96) {
                case 1 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1465:7: LEFTSHIFT
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    LEFTSHIFT202=(Token)match(input,LEFTSHIFT,FOLLOW_LEFTSHIFT_in_shift_op5298); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    LEFTSHIFT202_tree = (PythonTree)adaptor.create(LEFTSHIFT202);
                    adaptor.addChild(root_0, LEFTSHIFT202_tree);
                    }
                    if ( state.backtracking==0 ) {

                                retval.op = operatorType.LShift;
                            
                    }

                    }
                    break;
                case 2 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1469:7: RIGHTSHIFT
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    RIGHTSHIFT203=(Token)match(input,RIGHTSHIFT,FOLLOW_RIGHTSHIFT_in_shift_op5314); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    RIGHTSHIFT203_tree = (PythonTree)adaptor.create(RIGHTSHIFT203);
                    adaptor.addChild(root_0, RIGHTSHIFT203_tree);
                    }
                    if ( state.backtracking==0 ) {

                                retval.op = operatorType.RShift;
                            
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException re) {
            errorHandler.reportError(this, re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "shift_op"

    public static class arith_expr_return extends ParserRuleReturnScope {
        public Token lparen = null;
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "arith_expr"
    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1476:1: arith_expr returns [Token lparen = null] : left= term ( ( arith_op right+= term )+ | -> $left) ;
    public final PythonParser.arith_expr_return arith_expr() throws RecognitionException {
        PythonParser.arith_expr_return retval = new PythonParser.arith_expr_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        List list_right=null;
        PythonParser.term_return left = null;

        PythonParser.arith_op_return arith_op204 = null;

        PythonParser.term_return right = null;
         right = null;
        RewriteRuleSubtreeStream stream_arith_op=new RewriteRuleSubtreeStream(adaptor,"rule arith_op");
        RewriteRuleSubtreeStream stream_term=new RewriteRuleSubtreeStream(adaptor,"rule term");

            List ops = new ArrayList();
            List toks = new ArrayList();

        try {
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1492:5: (left= term ( ( arith_op right+= term )+ | -> $left) )
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1492:7: left= term ( ( arith_op right+= term )+ | -> $left)
            {
            pushFollow(FOLLOW_term_in_arith_expr5360);
            left=term();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_term.add(left.getTree());
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1493:9: ( ( arith_op right+= term )+ | -> $left)
            int alt98=2;
            int LA98_0 = input.LA(1);

            if ( ((LA98_0>=PLUS && LA98_0<=MINUS)) ) {
                alt98=1;
            }
            else if ( (LA98_0==EOF||LA98_0==NEWLINE||LA98_0==NAME||(LA98_0>=AND && LA98_0<=AS)||LA98_0==FOR||LA98_0==IF||(LA98_0>=IN && LA98_0<=IS)||(LA98_0>=NOT && LA98_0<=ORELSE)||(LA98_0>=RPAREN && LA98_0<=COMMA)||(LA98_0>=SEMI && LA98_0<=LEFTSHIFT)||LA98_0==RBRACK||(LA98_0>=RCURLY && LA98_0<=BACKQUOTE)) ) {
                alt98=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 98, 0, input);

                throw nvae;
            }
            switch (alt98) {
                case 1 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1493:11: ( arith_op right+= term )+
                    {
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1493:11: ( arith_op right+= term )+
                    int cnt97=0;
                    loop97:
                    do {
                        int alt97=2;
                        int LA97_0 = input.LA(1);

                        if ( ((LA97_0>=PLUS && LA97_0<=MINUS)) ) {
                            alt97=1;
                        }


                        switch (alt97) {
                    	case 1 :
                    	    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1493:12: arith_op right+= term
                    	    {
                    	    pushFollow(FOLLOW_arith_op_in_arith_expr5373);
                    	    arith_op204=arith_op();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) stream_arith_op.add(arith_op204.getTree());
                    	    pushFollow(FOLLOW_term_in_arith_expr5377);
                    	    right=term();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) stream_term.add(right.getTree());
                    	    if (list_right==null) list_right=new ArrayList();
                    	    list_right.add(right.getTree());

                    	    if ( state.backtracking==0 ) {

                    	                     ops.add((arith_op204!=null?arith_op204.op:null));
                    	                     toks.add((arith_op204!=null?((Token)arith_op204.start):null));
                    	                 
                    	    }

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt97 >= 1 ) break loop97;
                    	    if (state.backtracking>0) {state.failed=true; return retval;}
                                EarlyExitException eee =
                                    new EarlyExitException(97, input);
                                throw eee;
                        }
                        cnt97++;
                    } while (true);


                    }
                    break;
                case 2 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1500:8: 
                    {

                    // AST REWRITE
                    // elements: left
                    // token labels: 
                    // rule labels: retval, left
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
                    RewriteRuleSubtreeStream stream_left=new RewriteRuleSubtreeStream(adaptor,"rule left",left!=null?left.tree:null);

                    root_0 = (PythonTree)adaptor.nil();
                    // 1500:8: -> $left
                    {
                        adaptor.addChild(root_0, stream_left.nextTree());

                    }

                    retval.tree = root_0;}
                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {

                  if (!ops.isEmpty()) {
                      Token tok = (left!=null?((Token)left.start):null);
                      if ((left!=null?left.lparen:null) != null) {
                          tok = (left!=null?left.lparen:null);
                      }
                      retval.tree = actions.makeBinOp(tok, (left!=null?((PythonTree)left.tree):null), ops, list_right, toks);
                  }
                  retval.lparen = (left!=null?left.lparen:null);

            }
        }
        catch (RewriteCardinalityException rce) {

                    PythonTree badNode = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), null);
                    retval.tree = badNode;
                    errorHandler.error("Internal Parser Error", badNode);
                
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "arith_expr"

    public static class arith_op_return extends ParserRuleReturnScope {
        public operatorType op;
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "arith_op"
    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1512:1: arith_op returns [operatorType op] : ( PLUS | MINUS );
    public final PythonParser.arith_op_return arith_op() throws RecognitionException {
        PythonParser.arith_op_return retval = new PythonParser.arith_op_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token PLUS205=null;
        Token MINUS206=null;

        PythonTree PLUS205_tree=null;
        PythonTree MINUS206_tree=null;

        try {
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1514:5: ( PLUS | MINUS )
            int alt99=2;
            int LA99_0 = input.LA(1);

            if ( (LA99_0==PLUS) ) {
                alt99=1;
            }
            else if ( (LA99_0==MINUS) ) {
                alt99=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 99, 0, input);

                throw nvae;
            }
            switch (alt99) {
                case 1 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1514:7: PLUS
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    PLUS205=(Token)match(input,PLUS,FOLLOW_PLUS_in_arith_op5485); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    PLUS205_tree = (PythonTree)adaptor.create(PLUS205);
                    adaptor.addChild(root_0, PLUS205_tree);
                    }
                    if ( state.backtracking==0 ) {

                                retval.op = operatorType.Add;
                            
                    }

                    }
                    break;
                case 2 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1518:7: MINUS
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    MINUS206=(Token)match(input,MINUS,FOLLOW_MINUS_in_arith_op5501); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    MINUS206_tree = (PythonTree)adaptor.create(MINUS206);
                    adaptor.addChild(root_0, MINUS206_tree);
                    }
                    if ( state.backtracking==0 ) {

                                retval.op = operatorType.Sub;
                            
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException re) {
            errorHandler.reportError(this, re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "arith_op"

    public static class term_return extends ParserRuleReturnScope {
        public Token lparen = null;
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "term"
    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1525:1: term returns [Token lparen = null] : left= factor ( ( term_op right+= factor )+ | -> $left) ;
    public final PythonParser.term_return term() throws RecognitionException {
        PythonParser.term_return retval = new PythonParser.term_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        List list_right=null;
        PythonParser.factor_return left = null;

        PythonParser.term_op_return term_op207 = null;

        PythonParser.factor_return right = null;
         right = null;
        RewriteRuleSubtreeStream stream_term_op=new RewriteRuleSubtreeStream(adaptor,"rule term_op");
        RewriteRuleSubtreeStream stream_factor=new RewriteRuleSubtreeStream(adaptor,"rule factor");

            List ops = new ArrayList();
            List toks = new ArrayList();

        try {
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1541:5: (left= factor ( ( term_op right+= factor )+ | -> $left) )
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1541:7: left= factor ( ( term_op right+= factor )+ | -> $left)
            {
            pushFollow(FOLLOW_factor_in_term5547);
            left=factor();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_factor.add(left.getTree());
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1542:9: ( ( term_op right+= factor )+ | -> $left)
            int alt101=2;
            int LA101_0 = input.LA(1);

            if ( (LA101_0==STAR||(LA101_0>=SLASH && LA101_0<=DOUBLESLASH)) ) {
                alt101=1;
            }
            else if ( (LA101_0==EOF||LA101_0==NEWLINE||LA101_0==NAME||(LA101_0>=AND && LA101_0<=AS)||LA101_0==FOR||LA101_0==IF||(LA101_0>=IN && LA101_0<=IS)||(LA101_0>=NOT && LA101_0<=ORELSE)||(LA101_0>=RPAREN && LA101_0<=COMMA)||(LA101_0>=SEMI && LA101_0<=MINUS)||LA101_0==RBRACK||(LA101_0>=RCURLY && LA101_0<=BACKQUOTE)) ) {
                alt101=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 101, 0, input);

                throw nvae;
            }
            switch (alt101) {
                case 1 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1542:11: ( term_op right+= factor )+
                    {
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1542:11: ( term_op right+= factor )+
                    int cnt100=0;
                    loop100:
                    do {
                        int alt100=2;
                        int LA100_0 = input.LA(1);

                        if ( (LA100_0==STAR||(LA100_0>=SLASH && LA100_0<=DOUBLESLASH)) ) {
                            alt100=1;
                        }


                        switch (alt100) {
                    	case 1 :
                    	    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1542:12: term_op right+= factor
                    	    {
                    	    pushFollow(FOLLOW_term_op_in_term5560);
                    	    term_op207=term_op();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) stream_term_op.add(term_op207.getTree());
                    	    pushFollow(FOLLOW_factor_in_term5564);
                    	    right=factor();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) stream_factor.add(right.getTree());
                    	    if (list_right==null) list_right=new ArrayList();
                    	    list_right.add(right.getTree());

                    	    if ( state.backtracking==0 ) {

                    	                    ops.add((term_op207!=null?term_op207.op:null));
                    	                    toks.add((term_op207!=null?((Token)term_op207.start):null));
                    	                
                    	    }

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt100 >= 1 ) break loop100;
                    	    if (state.backtracking>0) {state.failed=true; return retval;}
                                EarlyExitException eee =
                                    new EarlyExitException(100, input);
                                throw eee;
                        }
                        cnt100++;
                    } while (true);


                    }
                    break;
                case 2 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1549:8: 
                    {

                    // AST REWRITE
                    // elements: left
                    // token labels: 
                    // rule labels: retval, left
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
                    RewriteRuleSubtreeStream stream_left=new RewriteRuleSubtreeStream(adaptor,"rule left",left!=null?left.tree:null);

                    root_0 = (PythonTree)adaptor.nil();
                    // 1549:8: -> $left
                    {
                        adaptor.addChild(root_0, stream_left.nextTree());

                    }

                    retval.tree = root_0;}
                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {

                  retval.lparen = (left!=null?left.lparen:null);
                  if (!ops.isEmpty()) {
                      Token tok = (left!=null?((Token)left.start):null);
                      if ((left!=null?left.lparen:null) != null) {
                          tok = (left!=null?left.lparen:null);
                      }
                      retval.tree = actions.makeBinOp(tok, (left!=null?((PythonTree)left.tree):null), ops, list_right, toks);
                  }

            }
        }

        catch (RecognitionException re) {
            errorHandler.reportError(this, re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "term"

    public static class term_op_return extends ParserRuleReturnScope {
        public operatorType op;
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "term_op"
    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1553:1: term_op returns [operatorType op] : ( STAR | SLASH | PERCENT | DOUBLESLASH );
    public final PythonParser.term_op_return term_op() throws RecognitionException {
        PythonParser.term_op_return retval = new PythonParser.term_op_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token STAR208=null;
        Token SLASH209=null;
        Token PERCENT210=null;
        Token DOUBLESLASH211=null;

        PythonTree STAR208_tree=null;
        PythonTree SLASH209_tree=null;
        PythonTree PERCENT210_tree=null;
        PythonTree DOUBLESLASH211_tree=null;

        try {
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1555:5: ( STAR | SLASH | PERCENT | DOUBLESLASH )
            int alt102=4;
            switch ( input.LA(1) ) {
            case STAR:
                {
                alt102=1;
                }
                break;
            case SLASH:
                {
                alt102=2;
                }
                break;
            case PERCENT:
                {
                alt102=3;
                }
                break;
            case DOUBLESLASH:
                {
                alt102=4;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 102, 0, input);

                throw nvae;
            }

            switch (alt102) {
                case 1 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1555:7: STAR
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    STAR208=(Token)match(input,STAR,FOLLOW_STAR_in_term_op5646); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    STAR208_tree = (PythonTree)adaptor.create(STAR208);
                    adaptor.addChild(root_0, STAR208_tree);
                    }
                    if ( state.backtracking==0 ) {

                                retval.op = operatorType.Mult;
                            
                    }

                    }
                    break;
                case 2 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1559:7: SLASH
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    SLASH209=(Token)match(input,SLASH,FOLLOW_SLASH_in_term_op5662); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    SLASH209_tree = (PythonTree)adaptor.create(SLASH209);
                    adaptor.addChild(root_0, SLASH209_tree);
                    }
                    if ( state.backtracking==0 ) {

                                retval.op = operatorType.Div;
                            
                    }

                    }
                    break;
                case 3 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1563:7: PERCENT
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    PERCENT210=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_term_op5678); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    PERCENT210_tree = (PythonTree)adaptor.create(PERCENT210);
                    adaptor.addChild(root_0, PERCENT210_tree);
                    }
                    if ( state.backtracking==0 ) {

                                retval.op = operatorType.Mod;
                            
                    }

                    }
                    break;
                case 4 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1567:7: DOUBLESLASH
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    DOUBLESLASH211=(Token)match(input,DOUBLESLASH,FOLLOW_DOUBLESLASH_in_term_op5694); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    DOUBLESLASH211_tree = (PythonTree)adaptor.create(DOUBLESLASH211);
                    adaptor.addChild(root_0, DOUBLESLASH211_tree);
                    }
                    if ( state.backtracking==0 ) {

                                retval.op = operatorType.FloorDiv;
                            
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException re) {
            errorHandler.reportError(this, re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "term_op"

    public static class factor_return extends ParserRuleReturnScope {
        public expr etype;
        public Token lparen = null;
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "factor"
    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1574:1: factor returns [expr etype, Token lparen = null] : ( PLUS p= factor | MINUS m= factor | TILDE t= factor | power );
    public final PythonParser.factor_return factor() throws RecognitionException {
        PythonParser.factor_return retval = new PythonParser.factor_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token PLUS212=null;
        Token MINUS213=null;
        Token TILDE214=null;
        PythonParser.factor_return p = null;

        PythonParser.factor_return m = null;

        PythonParser.factor_return t = null;

        PythonParser.power_return power215 = null;


        PythonTree PLUS212_tree=null;
        PythonTree MINUS213_tree=null;
        PythonTree TILDE214_tree=null;

        try {
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1579:5: ( PLUS p= factor | MINUS m= factor | TILDE t= factor | power )
            int alt103=4;
            switch ( input.LA(1) ) {
            case PLUS:
                {
                alt103=1;
                }
                break;
            case MINUS:
                {
                alt103=2;
                }
                break;
            case TILDE:
                {
                alt103=3;
                }
                break;
            case NAME:
            case LPAREN:
            case LBRACK:
            case LCURLY:
            case BACKQUOTE:
            case INT:
            case LONGINT:
            case FLOAT:
            case COMPLEX:
            case STRING:
                {
                alt103=4;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 103, 0, input);

                throw nvae;
            }

            switch (alt103) {
                case 1 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1579:7: PLUS p= factor
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    PLUS212=(Token)match(input,PLUS,FOLLOW_PLUS_in_factor5733); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    PLUS212_tree = (PythonTree)adaptor.create(PLUS212);
                    adaptor.addChild(root_0, PLUS212_tree);
                    }
                    pushFollow(FOLLOW_factor_in_factor5737);
                    p=factor();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, p.getTree());
                    if ( state.backtracking==0 ) {

                                retval.etype = new UnaryOp(PLUS212, unaryopType.UAdd, (p!=null?p.etype:null));
                            
                    }

                    }
                    break;
                case 2 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1583:7: MINUS m= factor
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    MINUS213=(Token)match(input,MINUS,FOLLOW_MINUS_in_factor5753); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    MINUS213_tree = (PythonTree)adaptor.create(MINUS213);
                    adaptor.addChild(root_0, MINUS213_tree);
                    }
                    pushFollow(FOLLOW_factor_in_factor5757);
                    m=factor();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, m.getTree());
                    if ( state.backtracking==0 ) {

                                retval.etype = actions.negate(MINUS213, (m!=null?m.etype:null));
                            
                    }

                    }
                    break;
                case 3 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1587:7: TILDE t= factor
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    TILDE214=(Token)match(input,TILDE,FOLLOW_TILDE_in_factor5773); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    TILDE214_tree = (PythonTree)adaptor.create(TILDE214);
                    adaptor.addChild(root_0, TILDE214_tree);
                    }
                    pushFollow(FOLLOW_factor_in_factor5777);
                    t=factor();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, t.getTree());
                    if ( state.backtracking==0 ) {

                                retval.etype = new UnaryOp(TILDE214, unaryopType.Invert, (t!=null?t.etype:null));
                            
                    }

                    }
                    break;
                case 4 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1591:7: power
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    pushFollow(FOLLOW_power_in_factor5793);
                    power215=power();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, power215.getTree());
                    if ( state.backtracking==0 ) {

                                retval.etype = actions.castExpr((power215!=null?((PythonTree)power215.tree):null));
                                retval.lparen = (power215!=null?power215.lparen:null);
                            
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {

                  retval.tree = retval.etype;

            }
        }

        catch (RecognitionException re) {
            errorHandler.reportError(this, re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "factor"

    public static class power_return extends ParserRuleReturnScope {
        public expr etype;
        public Token lparen = null;
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "power"
    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1599:1: power returns [expr etype, Token lparen = null] : atom (t+= trailer[$atom.start, $atom.tree] )* ( options {greedy=true; } : d= DOUBLESTAR factor )? ;
    public final PythonParser.power_return power() throws RecognitionException {
        PythonParser.power_return retval = new PythonParser.power_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token d=null;
        List list_t=null;
        PythonParser.atom_return atom216 = null;

        PythonParser.factor_return factor217 = null;

        PythonParser.trailer_return t = null;
         t = null;
        PythonTree d_tree=null;

        try {
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1604:5: ( atom (t+= trailer[$atom.start, $atom.tree] )* ( options {greedy=true; } : d= DOUBLESTAR factor )? )
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1604:7: atom (t+= trailer[$atom.start, $atom.tree] )* ( options {greedy=true; } : d= DOUBLESTAR factor )?
            {
            root_0 = (PythonTree)adaptor.nil();

            pushFollow(FOLLOW_atom_in_power5832);
            atom216=atom();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, atom216.getTree());
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1604:12: (t+= trailer[$atom.start, $atom.tree] )*
            loop104:
            do {
                int alt104=2;
                int LA104_0 = input.LA(1);

                if ( (LA104_0==DOT||LA104_0==LPAREN||LA104_0==LBRACK) ) {
                    alt104=1;
                }


                switch (alt104) {
            	case 1 :
            	    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1604:13: t+= trailer[$atom.start, $atom.tree]
            	    {
            	    pushFollow(FOLLOW_trailer_in_power5837);
            	    t=trailer((atom216!=null?((Token)atom216.start):null), (atom216!=null?((PythonTree)atom216.tree):null));

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, t.getTree());
            	    if (list_t==null) list_t=new ArrayList();
            	    list_t.add(t.getTree());


            	    }
            	    break;

            	default :
            	    break loop104;
                }
            } while (true);

            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1604:51: ( options {greedy=true; } : d= DOUBLESTAR factor )?
            int alt105=2;
            int LA105_0 = input.LA(1);

            if ( (LA105_0==DOUBLESTAR) ) {
                alt105=1;
            }
            switch (alt105) {
                case 1 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1604:75: d= DOUBLESTAR factor
                    {
                    d=(Token)match(input,DOUBLESTAR,FOLLOW_DOUBLESTAR_in_power5852); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    d_tree = (PythonTree)adaptor.create(d);
                    adaptor.addChild(root_0, d_tree);
                    }
                    pushFollow(FOLLOW_factor_in_power5854);
                    factor217=factor();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, factor217.getTree());

                    }
                    break;

            }

            if ( state.backtracking==0 ) {

                        retval.lparen = (atom216!=null?atom216.lparen:null);
                        //XXX: This could be better.
                        retval.etype = actions.castExpr((atom216!=null?((PythonTree)atom216.tree):null));
                        if (list_t != null) {
                            for(Object o : list_t) {
                                if (retval.etype instanceof Context) {
                                    ((Context)retval.etype).setContext(expr_contextType.Load);
                                }
                                if (o instanceof Call) {
                                    Call c = (Call)o;
                                    c.setFunc((PyObject)retval.etype);
                                    retval.etype = c;
                                } else if (o instanceof Subscript) {
                                    Subscript c = (Subscript)o;
                                    c.setValue((PyObject)retval.etype);
                                    retval.etype = c;
                                } else if (o instanceof Attribute) {
                                    Attribute c = (Attribute)o;
                                    c.setCharStartIndex(retval.etype.getCharStartIndex());
                                    c.setValue((PyObject)retval.etype);
                                    retval.etype = c;
                                }
                            }
                        }
                        if (d != null) {
                            List right = new ArrayList();
                            right.add((factor217!=null?((PythonTree)factor217.tree):null));
                            retval.etype = actions.makeBinOp((atom216!=null?((Token)atom216.start):null), retval.etype, operatorType.Pow, right);
                        }
                    
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {

                  retval.tree = retval.etype;

            }
        }

        catch (RecognitionException re) {
            errorHandler.reportError(this, re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "power"

    public static class atom_return extends ParserRuleReturnScope {
        public Token lparen = null;
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "atom"
    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1643:1: atom returns [Token lparen = null] : ( LPAREN ( yield_expr | testlist_gexp -> testlist_gexp | ) RPAREN | LBRACK ( listmaker[$LBRACK] -> listmaker | ) RBRACK | LCURLY ( dictmaker | ) RCURLY | lb= BACKQUOTE testlist[expr_contextType.Load] rb= BACKQUOTE | NAME | INT | LONGINT | FLOAT | COMPLEX | (S+= STRING )+ );
    public final PythonParser.atom_return atom() throws RecognitionException {
        PythonParser.atom_return retval = new PythonParser.atom_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token lb=null;
        Token rb=null;
        Token LPAREN218=null;
        Token RPAREN221=null;
        Token LBRACK222=null;
        Token RBRACK224=null;
        Token LCURLY225=null;
        Token RCURLY227=null;
        Token NAME229=null;
        Token INT230=null;
        Token LONGINT231=null;
        Token FLOAT232=null;
        Token COMPLEX233=null;
        Token S=null;
        List list_S=null;
        PythonParser.yield_expr_return yield_expr219 = null;

        PythonParser.testlist_gexp_return testlist_gexp220 = null;

        PythonParser.listmaker_return listmaker223 = null;

        PythonParser.dictmaker_return dictmaker226 = null;

        PythonParser.testlist_return testlist228 = null;


        PythonTree lb_tree=null;
        PythonTree rb_tree=null;
        PythonTree LPAREN218_tree=null;
        PythonTree RPAREN221_tree=null;
        PythonTree LBRACK222_tree=null;
        PythonTree RBRACK224_tree=null;
        PythonTree LCURLY225_tree=null;
        PythonTree RCURLY227_tree=null;
        PythonTree NAME229_tree=null;
        PythonTree INT230_tree=null;
        PythonTree LONGINT231_tree=null;
        PythonTree FLOAT232_tree=null;
        PythonTree COMPLEX233_tree=null;
        PythonTree S_tree=null;
        RewriteRuleTokenStream stream_RBRACK=new RewriteRuleTokenStream(adaptor,"token RBRACK");
        RewriteRuleTokenStream stream_RPAREN=new RewriteRuleTokenStream(adaptor,"token RPAREN");
        RewriteRuleTokenStream stream_LBRACK=new RewriteRuleTokenStream(adaptor,"token LBRACK");
        RewriteRuleTokenStream stream_LPAREN=new RewriteRuleTokenStream(adaptor,"token LPAREN");
        RewriteRuleSubtreeStream stream_testlist_gexp=new RewriteRuleSubtreeStream(adaptor,"rule testlist_gexp");
        RewriteRuleSubtreeStream stream_yield_expr=new RewriteRuleSubtreeStream(adaptor,"rule yield_expr");
        RewriteRuleSubtreeStream stream_listmaker=new RewriteRuleSubtreeStream(adaptor,"rule listmaker");

            expr etype = null;

        try {
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1653:5: ( LPAREN ( yield_expr | testlist_gexp -> testlist_gexp | ) RPAREN | LBRACK ( listmaker[$LBRACK] -> listmaker | ) RBRACK | LCURLY ( dictmaker | ) RCURLY | lb= BACKQUOTE testlist[expr_contextType.Load] rb= BACKQUOTE | NAME | INT | LONGINT | FLOAT | COMPLEX | (S+= STRING )+ )
            int alt110=10;
            switch ( input.LA(1) ) {
            case LPAREN:
                {
                alt110=1;
                }
                break;
            case LBRACK:
                {
                alt110=2;
                }
                break;
            case LCURLY:
                {
                alt110=3;
                }
                break;
            case BACKQUOTE:
                {
                alt110=4;
                }
                break;
            case NAME:
                {
                alt110=5;
                }
                break;
            case INT:
                {
                alt110=6;
                }
                break;
            case LONGINT:
                {
                alt110=7;
                }
                break;
            case FLOAT:
                {
                alt110=8;
                }
                break;
            case COMPLEX:
                {
                alt110=9;
                }
                break;
            case STRING:
                {
                alt110=10;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 110, 0, input);

                throw nvae;
            }

            switch (alt110) {
                case 1 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1653:7: LPAREN ( yield_expr | testlist_gexp -> testlist_gexp | ) RPAREN
                    {
                    LPAREN218=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_atom5904); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LPAREN.add(LPAREN218);

                    if ( state.backtracking==0 ) {

                                retval.lparen = LPAREN218;
                            
                    }
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1657:7: ( yield_expr | testlist_gexp -> testlist_gexp | )
                    int alt106=3;
                    switch ( input.LA(1) ) {
                    case YIELD:
                        {
                        alt106=1;
                        }
                        break;
                    case NAME:
                    case LAMBDA:
                    case NOT:
                    case LPAREN:
                    case PLUS:
                    case MINUS:
                    case TILDE:
                    case LBRACK:
                    case LCURLY:
                    case BACKQUOTE:
                    case INT:
                    case LONGINT:
                    case FLOAT:
                    case COMPLEX:
                    case STRING:
                        {
                        alt106=2;
                        }
                        break;
                    case RPAREN:
                        {
                        alt106=3;
                        }
                        break;
                    default:
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 106, 0, input);

                        throw nvae;
                    }

                    switch (alt106) {
                        case 1 :
                            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1657:9: yield_expr
                            {
                            pushFollow(FOLLOW_yield_expr_in_atom5922);
                            yield_expr219=yield_expr();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_yield_expr.add(yield_expr219.getTree());
                            if ( state.backtracking==0 ) {

                                          etype = (yield_expr219!=null?yield_expr219.etype:null);
                                      
                            }

                            }
                            break;
                        case 2 :
                            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1661:9: testlist_gexp
                            {
                            pushFollow(FOLLOW_testlist_gexp_in_atom5942);
                            testlist_gexp220=testlist_gexp();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_testlist_gexp.add(testlist_gexp220.getTree());


                            // AST REWRITE
                            // elements: testlist_gexp
                            // token labels: 
                            // rule labels: retval
                            // token list labels: 
                            // rule list labels: 
                            // wildcard labels: 
                            if ( state.backtracking==0 ) {
                            retval.tree = root_0;
                            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                            root_0 = (PythonTree)adaptor.nil();
                            // 1662:6: -> testlist_gexp
                            {
                                adaptor.addChild(root_0, stream_testlist_gexp.nextTree());

                            }

                            retval.tree = root_0;}
                            }
                            break;
                        case 3 :
                            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1664:9: 
                            {
                            if ( state.backtracking==0 ) {

                                          etype = new Tuple(LPAREN218, new ArrayList<expr>(), ((expr_scope)expr_stack.peek()).ctype);
                                      
                            }

                            }
                            break;

                    }

                    RPAREN221=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_atom5985); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RPAREN.add(RPAREN221);


                    }
                    break;
                case 2 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1669:7: LBRACK ( listmaker[$LBRACK] -> listmaker | ) RBRACK
                    {
                    LBRACK222=(Token)match(input,LBRACK,FOLLOW_LBRACK_in_atom5993); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACK.add(LBRACK222);

                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1670:7: ( listmaker[$LBRACK] -> listmaker | )
                    int alt107=2;
                    int LA107_0 = input.LA(1);

                    if ( (LA107_0==NAME||(LA107_0>=LAMBDA && LA107_0<=NOT)||LA107_0==LPAREN||(LA107_0>=PLUS && LA107_0<=MINUS)||(LA107_0>=TILDE && LA107_0<=LBRACK)||LA107_0==LCURLY||(LA107_0>=BACKQUOTE && LA107_0<=STRING)) ) {
                        alt107=1;
                    }
                    else if ( (LA107_0==RBRACK) ) {
                        alt107=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 107, 0, input);

                        throw nvae;
                    }
                    switch (alt107) {
                        case 1 :
                            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1670:8: listmaker[$LBRACK]
                            {
                            pushFollow(FOLLOW_listmaker_in_atom6002);
                            listmaker223=listmaker(LBRACK222);

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_listmaker.add(listmaker223.getTree());


                            // AST REWRITE
                            // elements: listmaker
                            // token labels: 
                            // rule labels: retval
                            // token list labels: 
                            // rule list labels: 
                            // wildcard labels: 
                            if ( state.backtracking==0 ) {
                            retval.tree = root_0;
                            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                            root_0 = (PythonTree)adaptor.nil();
                            // 1671:6: -> listmaker
                            {
                                adaptor.addChild(root_0, stream_listmaker.nextTree());

                            }

                            retval.tree = root_0;}
                            }
                            break;
                        case 2 :
                            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1673:8: 
                            {
                            if ( state.backtracking==0 ) {

                                         etype = new org.python.antlr.ast.List(LBRACK222, new ArrayList<expr>(), ((expr_scope)expr_stack.peek()).ctype);
                                     
                            }

                            }
                            break;

                    }

                    RBRACK224=(Token)match(input,RBRACK,FOLLOW_RBRACK_in_atom6045); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACK.add(RBRACK224);


                    }
                    break;
                case 3 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1678:7: LCURLY ( dictmaker | ) RCURLY
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    LCURLY225=(Token)match(input,LCURLY,FOLLOW_LCURLY_in_atom6053); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    LCURLY225_tree = (PythonTree)adaptor.create(LCURLY225);
                    adaptor.addChild(root_0, LCURLY225_tree);
                    }
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1679:8: ( dictmaker | )
                    int alt108=2;
                    int LA108_0 = input.LA(1);

                    if ( (LA108_0==NAME||(LA108_0>=LAMBDA && LA108_0<=NOT)||LA108_0==LPAREN||(LA108_0>=PLUS && LA108_0<=MINUS)||(LA108_0>=TILDE && LA108_0<=LBRACK)||LA108_0==LCURLY||(LA108_0>=BACKQUOTE && LA108_0<=STRING)) ) {
                        alt108=1;
                    }
                    else if ( (LA108_0==RCURLY) ) {
                        alt108=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 108, 0, input);

                        throw nvae;
                    }
                    switch (alt108) {
                        case 1 :
                            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1679:9: dictmaker
                            {
                            pushFollow(FOLLOW_dictmaker_in_atom6063);
                            dictmaker226=dictmaker();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, dictmaker226.getTree());
                            if ( state.backtracking==0 ) {

                                          etype = new Dict(LCURLY225, actions.castExprs((dictmaker226!=null?dictmaker226.keys:null)),
                                            actions.castExprs((dictmaker226!=null?dictmaker226.values:null)));
                                      
                            }

                            }
                            break;
                        case 2 :
                            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1685:9: 
                            {
                            if ( state.backtracking==0 ) {

                                          etype = new Dict(LCURLY225, new ArrayList<expr>(), new ArrayList<expr>());
                                      
                            }

                            }
                            break;

                    }

                    RCURLY227=(Token)match(input,RCURLY,FOLLOW_RCURLY_in_atom6110); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    RCURLY227_tree = (PythonTree)adaptor.create(RCURLY227);
                    adaptor.addChild(root_0, RCURLY227_tree);
                    }

                    }
                    break;
                case 4 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1690:8: lb= BACKQUOTE testlist[expr_contextType.Load] rb= BACKQUOTE
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    lb=(Token)match(input,BACKQUOTE,FOLLOW_BACKQUOTE_in_atom6121); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    lb_tree = (PythonTree)adaptor.create(lb);
                    adaptor.addChild(root_0, lb_tree);
                    }
                    pushFollow(FOLLOW_testlist_in_atom6123);
                    testlist228=testlist(expr_contextType.Load);

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, testlist228.getTree());
                    rb=(Token)match(input,BACKQUOTE,FOLLOW_BACKQUOTE_in_atom6128); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    rb_tree = (PythonTree)adaptor.create(rb);
                    adaptor.addChild(root_0, rb_tree);
                    }
                    if ( state.backtracking==0 ) {

                                 etype = new Repr(lb, actions.castExpr((testlist228!=null?((PythonTree)testlist228.tree):null)));
                             
                    }

                    }
                    break;
                case 5 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1694:8: NAME
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    NAME229=(Token)match(input,NAME,FOLLOW_NAME_in_atom6146); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    NAME229_tree = (PythonTree)adaptor.create(NAME229);
                    adaptor.addChild(root_0, NAME229_tree);
                    }
                    if ( state.backtracking==0 ) {

                                 etype = new Name(NAME229, (NAME229!=null?NAME229.getText():null), ((expr_scope)expr_stack.peek()).ctype);
                             
                    }

                    }
                    break;
                case 6 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1698:8: INT
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    INT230=(Token)match(input,INT,FOLLOW_INT_in_atom6164); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    INT230_tree = (PythonTree)adaptor.create(INT230);
                    adaptor.addChild(root_0, INT230_tree);
                    }
                    if ( state.backtracking==0 ) {

                                 etype = new Num(INT230, actions.makeInt(INT230));
                             
                    }

                    }
                    break;
                case 7 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1702:8: LONGINT
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    LONGINT231=(Token)match(input,LONGINT,FOLLOW_LONGINT_in_atom6182); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    LONGINT231_tree = (PythonTree)adaptor.create(LONGINT231);
                    adaptor.addChild(root_0, LONGINT231_tree);
                    }
                    if ( state.backtracking==0 ) {

                                 etype = new Num(LONGINT231, actions.makeInt(LONGINT231));
                             
                    }

                    }
                    break;
                case 8 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1706:8: FLOAT
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    FLOAT232=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_atom6200); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    FLOAT232_tree = (PythonTree)adaptor.create(FLOAT232);
                    adaptor.addChild(root_0, FLOAT232_tree);
                    }
                    if ( state.backtracking==0 ) {

                                 etype = new Num(FLOAT232, actions.makeFloat(FLOAT232));
                             
                    }

                    }
                    break;
                case 9 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1710:8: COMPLEX
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    COMPLEX233=(Token)match(input,COMPLEX,FOLLOW_COMPLEX_in_atom6218); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    COMPLEX233_tree = (PythonTree)adaptor.create(COMPLEX233);
                    adaptor.addChild(root_0, COMPLEX233_tree);
                    }
                    if ( state.backtracking==0 ) {

                                 etype = new Num(COMPLEX233, actions.makeComplex(COMPLEX233));
                             
                    }

                    }
                    break;
                case 10 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1714:8: (S+= STRING )+
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1714:8: (S+= STRING )+
                    int cnt109=0;
                    loop109:
                    do {
                        int alt109=2;
                        int LA109_0 = input.LA(1);

                        if ( (LA109_0==STRING) ) {
                            alt109=1;
                        }


                        switch (alt109) {
                    	case 1 :
                    	    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1714:9: S+= STRING
                    	    {
                    	    S=(Token)match(input,STRING,FOLLOW_STRING_in_atom6239); if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) {
                    	    S_tree = (PythonTree)adaptor.create(S);
                    	    adaptor.addChild(root_0, S_tree);
                    	    }
                    	    if (list_S==null) list_S=new ArrayList();
                    	    list_S.add(S);


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt109 >= 1 ) break loop109;
                    	    if (state.backtracking>0) {state.failed=true; return retval;}
                                EarlyExitException eee =
                                    new EarlyExitException(109, input);
                                throw eee;
                        }
                        cnt109++;
                    } while (true);

                    if ( state.backtracking==0 ) {

                                 etype = new Str(actions.extractStringToken(list_S), actions.extractStrings(list_S, encoding));
                             
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {

                 if (etype != null) {
                     retval.tree = etype;
                 }

            }
        }

        catch (RecognitionException re) {
            errorHandler.reportError(this, re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "atom"

    public static class listmaker_return extends ParserRuleReturnScope {
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "listmaker"
    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1721:1: listmaker[Token lbrack] : t+= test[$expr::ctype] ( list_for[gens] | ( options {greedy=true; } : COMMA t+= test[$expr::ctype] )* ) ( COMMA )? ;
    public final PythonParser.listmaker_return listmaker(Token lbrack) throws RecognitionException {
        PythonParser.listmaker_return retval = new PythonParser.listmaker_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token COMMA235=null;
        Token COMMA236=null;
        List list_t=null;
        PythonParser.list_for_return list_for234 = null;

        PythonParser.test_return t = null;
         t = null;
        PythonTree COMMA235_tree=null;
        PythonTree COMMA236_tree=null;


            List gens = new ArrayList();
            expr etype = null;

        try {
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1729:5: (t+= test[$expr::ctype] ( list_for[gens] | ( options {greedy=true; } : COMMA t+= test[$expr::ctype] )* ) ( COMMA )? )
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1729:7: t+= test[$expr::ctype] ( list_for[gens] | ( options {greedy=true; } : COMMA t+= test[$expr::ctype] )* ) ( COMMA )?
            {
            root_0 = (PythonTree)adaptor.nil();

            pushFollow(FOLLOW_test_in_listmaker6282);
            t=test(((expr_scope)expr_stack.peek()).ctype);

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, t.getTree());
            if (list_t==null) list_t=new ArrayList();
            list_t.add(t.getTree());

            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1730:9: ( list_for[gens] | ( options {greedy=true; } : COMMA t+= test[$expr::ctype] )* )
            int alt112=2;
            int LA112_0 = input.LA(1);

            if ( (LA112_0==FOR) ) {
                alt112=1;
            }
            else if ( (LA112_0==COMMA||LA112_0==RBRACK) ) {
                alt112=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 112, 0, input);

                throw nvae;
            }
            switch (alt112) {
                case 1 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1730:10: list_for[gens]
                    {
                    pushFollow(FOLLOW_list_for_in_listmaker6294);
                    list_for234=list_for(gens);

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, list_for234.getTree());
                    if ( state.backtracking==0 ) {

                                   Collections.reverse(gens);
                                   List<comprehension> c = gens;
                                   etype = new ListComp(((Token)retval.start), actions.castExpr(list_t.get(0)), c);
                               
                    }

                    }
                    break;
                case 2 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1736:11: ( options {greedy=true; } : COMMA t+= test[$expr::ctype] )*
                    {
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1736:11: ( options {greedy=true; } : COMMA t+= test[$expr::ctype] )*
                    loop111:
                    do {
                        int alt111=2;
                        int LA111_0 = input.LA(1);

                        if ( (LA111_0==COMMA) ) {
                            int LA111_1 = input.LA(2);

                            if ( (LA111_1==NAME||(LA111_1>=LAMBDA && LA111_1<=NOT)||LA111_1==LPAREN||(LA111_1>=PLUS && LA111_1<=MINUS)||(LA111_1>=TILDE && LA111_1<=LBRACK)||LA111_1==LCURLY||(LA111_1>=BACKQUOTE && LA111_1<=STRING)) ) {
                                alt111=1;
                            }


                        }


                        switch (alt111) {
                    	case 1 :
                    	    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1736:35: COMMA t+= test[$expr::ctype]
                    	    {
                    	    COMMA235=(Token)match(input,COMMA,FOLLOW_COMMA_in_listmaker6326); if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) {
                    	    COMMA235_tree = (PythonTree)adaptor.create(COMMA235);
                    	    adaptor.addChild(root_0, COMMA235_tree);
                    	    }
                    	    pushFollow(FOLLOW_test_in_listmaker6330);
                    	    t=test(((expr_scope)expr_stack.peek()).ctype);

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) adaptor.addChild(root_0, t.getTree());
                    	    if (list_t==null) list_t=new ArrayList();
                    	    list_t.add(t.getTree());


                    	    }
                    	    break;

                    	default :
                    	    break loop111;
                        }
                    } while (true);

                    if ( state.backtracking==0 ) {

                                     etype = new org.python.antlr.ast.List(lbrack, actions.castExprs(list_t), ((expr_scope)expr_stack.peek()).ctype);
                                 
                    }

                    }
                    break;

            }

            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1740:11: ( COMMA )?
            int alt113=2;
            int LA113_0 = input.LA(1);

            if ( (LA113_0==COMMA) ) {
                alt113=1;
            }
            switch (alt113) {
                case 1 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1740:12: COMMA
                    {
                    COMMA236=(Token)match(input,COMMA,FOLLOW_COMMA_in_listmaker6359); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    COMMA236_tree = (PythonTree)adaptor.create(COMMA236);
                    adaptor.addChild(root_0, COMMA236_tree);
                    }

                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {

                 retval.tree = etype;

            }
        }

        catch (RecognitionException re) {
            errorHandler.reportError(this, re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "listmaker"

    public static class testlist_gexp_return extends ParserRuleReturnScope {
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "testlist_gexp"
    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1744:1: testlist_gexp : t+= test[$expr::ctype] ( ( options {k=2; } : c1= COMMA t+= test[$expr::ctype] )* (c2= COMMA )? {...}? | -> test | ( gen_for[gens] ) ) ;
    public final PythonParser.testlist_gexp_return testlist_gexp() throws RecognitionException {
        PythonParser.testlist_gexp_return retval = new PythonParser.testlist_gexp_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token c1=null;
        Token c2=null;
        List list_t=null;
        PythonParser.gen_for_return gen_for237 = null;

        PythonParser.test_return t = null;
         t = null;
        PythonTree c1_tree=null;
        PythonTree c2_tree=null;
        RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
        RewriteRuleSubtreeStream stream_test=new RewriteRuleSubtreeStream(adaptor,"rule test");
        RewriteRuleSubtreeStream stream_gen_for=new RewriteRuleSubtreeStream(adaptor,"rule gen_for");

            expr etype = null;
            List gens = new ArrayList();

        try {
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1754:5: (t+= test[$expr::ctype] ( ( options {k=2; } : c1= COMMA t+= test[$expr::ctype] )* (c2= COMMA )? {...}? | -> test | ( gen_for[gens] ) ) )
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1754:7: t+= test[$expr::ctype] ( ( options {k=2; } : c1= COMMA t+= test[$expr::ctype] )* (c2= COMMA )? {...}? | -> test | ( gen_for[gens] ) )
            {
            pushFollow(FOLLOW_test_in_testlist_gexp6391);
            t=test(((expr_scope)expr_stack.peek()).ctype);

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_test.add(t.getTree());
            if (list_t==null) list_t=new ArrayList();
            list_t.add(t.getTree());

            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1755:9: ( ( options {k=2; } : c1= COMMA t+= test[$expr::ctype] )* (c2= COMMA )? {...}? | -> test | ( gen_for[gens] ) )
            int alt116=3;
            switch ( input.LA(1) ) {
            case COMMA:
                {
                alt116=1;
                }
                break;
            case RPAREN:
                {
                int LA116_2 = input.LA(2);

                if ( (( c1 != null || c2 != null )) ) {
                    alt116=1;
                }
                else if ( (true) ) {
                    alt116=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 116, 2, input);

                    throw nvae;
                }
                }
                break;
            case FOR:
                {
                alt116=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 116, 0, input);

                throw nvae;
            }

            switch (alt116) {
                case 1 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1755:11: ( options {k=2; } : c1= COMMA t+= test[$expr::ctype] )* (c2= COMMA )? {...}?
                    {
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1755:11: ( options {k=2; } : c1= COMMA t+= test[$expr::ctype] )*
                    loop114:
                    do {
                        int alt114=2;
                        alt114 = dfa114.predict(input);
                        switch (alt114) {
                    	case 1 :
                    	    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1755:28: c1= COMMA t+= test[$expr::ctype]
                    	    {
                    	    c1=(Token)match(input,COMMA,FOLLOW_COMMA_in_testlist_gexp6415); if (state.failed) return retval; 
                    	    if ( state.backtracking==0 ) stream_COMMA.add(c1);

                    	    pushFollow(FOLLOW_test_in_testlist_gexp6419);
                    	    t=test(((expr_scope)expr_stack.peek()).ctype);

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) stream_test.add(t.getTree());
                    	    if (list_t==null) list_t=new ArrayList();
                    	    list_t.add(t.getTree());


                    	    }
                    	    break;

                    	default :
                    	    break loop114;
                        }
                    } while (true);

                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1755:61: (c2= COMMA )?
                    int alt115=2;
                    int LA115_0 = input.LA(1);

                    if ( (LA115_0==COMMA) ) {
                        alt115=1;
                    }
                    switch (alt115) {
                        case 1 :
                            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1755:62: c2= COMMA
                            {
                            c2=(Token)match(input,COMMA,FOLLOW_COMMA_in_testlist_gexp6427); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_COMMA.add(c2);


                            }
                            break;

                    }

                    if ( !(( c1 != null || c2 != null )) ) {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        throw new FailedPredicateException(input, "testlist_gexp", " $c1 != null || $c2 != null ");
                    }
                    if ( state.backtracking==0 ) {

                                     etype = new Tuple(((Token)retval.start), actions.castExprs(list_t), ((expr_scope)expr_stack.peek()).ctype);
                                 
                    }

                    }
                    break;
                case 2 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1760:11: 
                    {

                    // AST REWRITE
                    // elements: test
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (PythonTree)adaptor.nil();
                    // 1760:11: -> test
                    {
                        adaptor.addChild(root_0, stream_test.nextTree());

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 3 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1761:11: ( gen_for[gens] )
                    {
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1761:11: ( gen_for[gens] )
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1761:12: gen_for[gens]
                    {
                    pushFollow(FOLLOW_gen_for_in_testlist_gexp6481);
                    gen_for237=gen_for(gens);

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_gen_for.add(gen_for237.getTree());
                    if ( state.backtracking==0 ) {

                                     Collections.reverse(gens);
                                     List<comprehension> c = gens;
                                     expr e = actions.castExpr(list_t.get(0));
                                     if (e instanceof Context) {
                                         ((Context)e).setContext(expr_contextType.Load);
                                     }
                                     etype = new GeneratorExp(((Token)retval.start), actions.castExpr(list_t.get(0)), c);
                                 
                    }

                    }


                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {

                  if (etype != null) {
                      retval.tree = etype;
                  }

            }
        }

        catch (RecognitionException re) {
            errorHandler.reportError(this, re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "testlist_gexp"

    public static class lambdef_return extends ParserRuleReturnScope {
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "lambdef"
    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1776:1: lambdef : LAMBDA ( varargslist )? COLON test[expr_contextType.Load] ;
    public final PythonParser.lambdef_return lambdef() throws RecognitionException {
        PythonParser.lambdef_return retval = new PythonParser.lambdef_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token LAMBDA238=null;
        Token COLON240=null;
        PythonParser.varargslist_return varargslist239 = null;

        PythonParser.test_return test241 = null;


        PythonTree LAMBDA238_tree=null;
        PythonTree COLON240_tree=null;


            expr etype = null;

        try {
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1783:5: ( LAMBDA ( varargslist )? COLON test[expr_contextType.Load] )
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1783:7: LAMBDA ( varargslist )? COLON test[expr_contextType.Load]
            {
            root_0 = (PythonTree)adaptor.nil();

            LAMBDA238=(Token)match(input,LAMBDA,FOLLOW_LAMBDA_in_lambdef6545); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            LAMBDA238_tree = (PythonTree)adaptor.create(LAMBDA238);
            adaptor.addChild(root_0, LAMBDA238_tree);
            }
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1783:14: ( varargslist )?
            int alt117=2;
            int LA117_0 = input.LA(1);

            if ( (LA117_0==NAME||LA117_0==LPAREN||(LA117_0>=STAR && LA117_0<=DOUBLESTAR)) ) {
                alt117=1;
            }
            switch (alt117) {
                case 1 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1783:15: varargslist
                    {
                    pushFollow(FOLLOW_varargslist_in_lambdef6548);
                    varargslist239=varargslist();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, varargslist239.getTree());

                    }
                    break;

            }

            COLON240=(Token)match(input,COLON,FOLLOW_COLON_in_lambdef6552); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            COLON240_tree = (PythonTree)adaptor.create(COLON240);
            adaptor.addChild(root_0, COLON240_tree);
            }
            pushFollow(FOLLOW_test_in_lambdef6554);
            test241=test(expr_contextType.Load);

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, test241.getTree());
            if ( state.backtracking==0 ) {

                        arguments a = (varargslist239!=null?varargslist239.args:null);
                        if (a == null) {
                            a = new arguments(LAMBDA238, new ArrayList<expr>(), null, null, new ArrayList<expr>());
                        }
                        etype = new Lambda(LAMBDA238, a, actions.castExpr((test241!=null?((PythonTree)test241.tree):null)));
                    
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {

                  retval.tree = etype;

            }
        }

        catch (RecognitionException re) {
            errorHandler.reportError(this, re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "lambdef"

    public static class trailer_return extends ParserRuleReturnScope {
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "trailer"
    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1794:1: trailer[Token begin, PythonTree ptree] : ( LPAREN ( arglist | ) RPAREN | LBRACK subscriptlist[$begin] RBRACK | DOT attr );
    public final PythonParser.trailer_return trailer(Token begin, PythonTree ptree) throws RecognitionException {
        PythonParser.trailer_return retval = new PythonParser.trailer_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token LPAREN242=null;
        Token RPAREN244=null;
        Token LBRACK245=null;
        Token RBRACK247=null;
        Token DOT248=null;
        PythonParser.arglist_return arglist243 = null;

        PythonParser.subscriptlist_return subscriptlist246 = null;

        PythonParser.attr_return attr249 = null;


        PythonTree LPAREN242_tree=null;
        PythonTree RPAREN244_tree=null;
        PythonTree LBRACK245_tree=null;
        PythonTree RBRACK247_tree=null;
        PythonTree DOT248_tree=null;


            expr etype = null;

        try {
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1803:5: ( LPAREN ( arglist | ) RPAREN | LBRACK subscriptlist[$begin] RBRACK | DOT attr )
            int alt119=3;
            switch ( input.LA(1) ) {
            case LPAREN:
                {
                alt119=1;
                }
                break;
            case LBRACK:
                {
                alt119=2;
                }
                break;
            case DOT:
                {
                alt119=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 119, 0, input);

                throw nvae;
            }

            switch (alt119) {
                case 1 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1803:7: LPAREN ( arglist | ) RPAREN
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    LPAREN242=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_trailer6593); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    LPAREN242_tree = (PythonTree)adaptor.create(LPAREN242);
                    adaptor.addChild(root_0, LPAREN242_tree);
                    }
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1804:7: ( arglist | )
                    int alt118=2;
                    int LA118_0 = input.LA(1);

                    if ( (LA118_0==NAME||(LA118_0>=LAMBDA && LA118_0<=NOT)||LA118_0==LPAREN||(LA118_0>=STAR && LA118_0<=DOUBLESTAR)||(LA118_0>=PLUS && LA118_0<=MINUS)||(LA118_0>=TILDE && LA118_0<=LBRACK)||LA118_0==LCURLY||(LA118_0>=BACKQUOTE && LA118_0<=STRING)) ) {
                        alt118=1;
                    }
                    else if ( (LA118_0==RPAREN) ) {
                        alt118=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 118, 0, input);

                        throw nvae;
                    }
                    switch (alt118) {
                        case 1 :
                            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1804:8: arglist
                            {
                            pushFollow(FOLLOW_arglist_in_trailer6602);
                            arglist243=arglist();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, arglist243.getTree());
                            if ( state.backtracking==0 ) {

                                         etype = new Call(begin, actions.castExpr(ptree), actions.castExprs((arglist243!=null?arglist243.args:null)),
                                           actions.makeKeywords((arglist243!=null?arglist243.keywords:null)), (arglist243!=null?arglist243.starargs:null), (arglist243!=null?arglist243.kwargs:null));
                                     
                            }

                            }
                            break;
                        case 2 :
                            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1810:8: 
                            {
                            if ( state.backtracking==0 ) {

                                         etype = new Call(begin, actions.castExpr(ptree), new ArrayList<expr>(), new ArrayList<keyword>(), null, null);
                                     
                            }

                            }
                            break;

                    }

                    RPAREN244=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_trailer6644); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    RPAREN244_tree = (PythonTree)adaptor.create(RPAREN244);
                    adaptor.addChild(root_0, RPAREN244_tree);
                    }

                    }
                    break;
                case 2 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1815:7: LBRACK subscriptlist[$begin] RBRACK
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    LBRACK245=(Token)match(input,LBRACK,FOLLOW_LBRACK_in_trailer6652); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    LBRACK245_tree = (PythonTree)adaptor.create(LBRACK245);
                    adaptor.addChild(root_0, LBRACK245_tree);
                    }
                    pushFollow(FOLLOW_subscriptlist_in_trailer6654);
                    subscriptlist246=subscriptlist(begin);

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, subscriptlist246.getTree());
                    RBRACK247=(Token)match(input,RBRACK,FOLLOW_RBRACK_in_trailer6657); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    RBRACK247_tree = (PythonTree)adaptor.create(RBRACK247);
                    adaptor.addChild(root_0, RBRACK247_tree);
                    }
                    if ( state.backtracking==0 ) {

                                etype = new Subscript(begin, actions.castExpr(ptree), actions.castSlice((subscriptlist246!=null?((PythonTree)subscriptlist246.tree):null)), ((expr_scope)expr_stack.peek()).ctype);
                            
                    }

                    }
                    break;
                case 3 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1819:7: DOT attr
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    DOT248=(Token)match(input,DOT,FOLLOW_DOT_in_trailer6673); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    DOT248_tree = (PythonTree)adaptor.create(DOT248);
                    adaptor.addChild(root_0, DOT248_tree);
                    }
                    pushFollow(FOLLOW_attr_in_trailer6675);
                    attr249=attr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, attr249.getTree());
                    if ( state.backtracking==0 ) {

                                etype = new Attribute(begin, actions.castExpr(ptree), new Name((attr249!=null?((PythonTree)attr249.tree):null), (attr249!=null?input.toString(attr249.start,attr249.stop):null), expr_contextType.Load), ((expr_scope)expr_stack.peek()).ctype);
                            
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {

                  if (etype != null) {
                      retval.tree = etype;
                  }

            }
        }

        catch (RecognitionException re) {
            errorHandler.reportError(this, re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "trailer"

    public static class subscriptlist_return extends ParserRuleReturnScope {
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "subscriptlist"
    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1826:1: subscriptlist[Token begin] : sub+= subscript ( options {greedy=true; } : c1= COMMA sub+= subscript )* (c2= COMMA )? ;
    public final PythonParser.subscriptlist_return subscriptlist(Token begin) throws RecognitionException {
        PythonParser.subscriptlist_return retval = new PythonParser.subscriptlist_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token c1=null;
        Token c2=null;
        List list_sub=null;
        PythonParser.subscript_return sub = null;
         sub = null;
        PythonTree c1_tree=null;
        PythonTree c2_tree=null;


            slice sltype = null;

        try {
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1833:5: (sub+= subscript ( options {greedy=true; } : c1= COMMA sub+= subscript )* (c2= COMMA )? )
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1833:7: sub+= subscript ( options {greedy=true; } : c1= COMMA sub+= subscript )* (c2= COMMA )?
            {
            root_0 = (PythonTree)adaptor.nil();

            pushFollow(FOLLOW_subscript_in_subscriptlist6714);
            sub=subscript();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, sub.getTree());
            if (list_sub==null) list_sub=new ArrayList();
            list_sub.add(sub.getTree());

            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1833:22: ( options {greedy=true; } : c1= COMMA sub+= subscript )*
            loop120:
            do {
                int alt120=2;
                int LA120_0 = input.LA(1);

                if ( (LA120_0==COMMA) ) {
                    int LA120_1 = input.LA(2);

                    if ( ((LA120_1>=NAME && LA120_1<=DOT)||(LA120_1>=LAMBDA && LA120_1<=NOT)||LA120_1==LPAREN||LA120_1==COLON||(LA120_1>=PLUS && LA120_1<=MINUS)||(LA120_1>=TILDE && LA120_1<=LBRACK)||LA120_1==LCURLY||(LA120_1>=BACKQUOTE && LA120_1<=STRING)) ) {
                        alt120=1;
                    }


                }


                switch (alt120) {
            	case 1 :
            	    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1833:46: c1= COMMA sub+= subscript
            	    {
            	    c1=(Token)match(input,COMMA,FOLLOW_COMMA_in_subscriptlist6726); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    c1_tree = (PythonTree)adaptor.create(c1);
            	    adaptor.addChild(root_0, c1_tree);
            	    }
            	    pushFollow(FOLLOW_subscript_in_subscriptlist6730);
            	    sub=subscript();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, sub.getTree());
            	    if (list_sub==null) list_sub=new ArrayList();
            	    list_sub.add(sub.getTree());


            	    }
            	    break;

            	default :
            	    break loop120;
                }
            } while (true);

            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1833:72: (c2= COMMA )?
            int alt121=2;
            int LA121_0 = input.LA(1);

            if ( (LA121_0==COMMA) ) {
                alt121=1;
            }
            switch (alt121) {
                case 1 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1833:73: c2= COMMA
                    {
                    c2=(Token)match(input,COMMA,FOLLOW_COMMA_in_subscriptlist6737); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    c2_tree = (PythonTree)adaptor.create(c2);
                    adaptor.addChild(root_0, c2_tree);
                    }

                    }
                    break;

            }

            if ( state.backtracking==0 ) {

                        sltype = actions.makeSliceType(begin, c1, c2, list_sub);
                    
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {

                 retval.tree = sltype;

            }
        }

        catch (RecognitionException re) {
            errorHandler.reportError(this, re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "subscriptlist"

    public static class subscript_return extends ParserRuleReturnScope {
        public slice sltype;
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "subscript"
    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1840:1: subscript returns [slice sltype] : (d1= DOT DOT DOT | ( test[null] COLON )=>lower= test[expr_contextType.Load] (c1= COLON (upper1= test[expr_contextType.Load] )? ( sliceop )? )? | ( COLON )=>c2= COLON (upper2= test[expr_contextType.Load] )? ( sliceop )? | test[expr_contextType.Load] );
    public final PythonParser.subscript_return subscript() throws RecognitionException {
        PythonParser.subscript_return retval = new PythonParser.subscript_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token d1=null;
        Token c1=null;
        Token c2=null;
        Token DOT250=null;
        Token DOT251=null;
        PythonParser.test_return lower = null;

        PythonParser.test_return upper1 = null;

        PythonParser.test_return upper2 = null;

        PythonParser.sliceop_return sliceop252 = null;

        PythonParser.sliceop_return sliceop253 = null;

        PythonParser.test_return test254 = null;


        PythonTree d1_tree=null;
        PythonTree c1_tree=null;
        PythonTree c2_tree=null;
        PythonTree DOT250_tree=null;
        PythonTree DOT251_tree=null;

        try {
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1845:5: (d1= DOT DOT DOT | ( test[null] COLON )=>lower= test[expr_contextType.Load] (c1= COLON (upper1= test[expr_contextType.Load] )? ( sliceop )? )? | ( COLON )=>c2= COLON (upper2= test[expr_contextType.Load] )? ( sliceop )? | test[expr_contextType.Load] )
            int alt127=4;
            alt127 = dfa127.predict(input);
            switch (alt127) {
                case 1 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1845:7: d1= DOT DOT DOT
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    d1=(Token)match(input,DOT,FOLLOW_DOT_in_subscript6780); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    d1_tree = (PythonTree)adaptor.create(d1);
                    adaptor.addChild(root_0, d1_tree);
                    }
                    DOT250=(Token)match(input,DOT,FOLLOW_DOT_in_subscript6782); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    DOT250_tree = (PythonTree)adaptor.create(DOT250);
                    adaptor.addChild(root_0, DOT250_tree);
                    }
                    DOT251=(Token)match(input,DOT,FOLLOW_DOT_in_subscript6784); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    DOT251_tree = (PythonTree)adaptor.create(DOT251);
                    adaptor.addChild(root_0, DOT251_tree);
                    }
                    if ( state.backtracking==0 ) {

                                retval.sltype = new Ellipsis(d1);
                            
                    }

                    }
                    break;
                case 2 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1849:7: ( test[null] COLON )=>lower= test[expr_contextType.Load] (c1= COLON (upper1= test[expr_contextType.Load] )? ( sliceop )? )?
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    pushFollow(FOLLOW_test_in_subscript6814);
                    lower=test(expr_contextType.Load);

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, lower.getTree());
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1850:41: (c1= COLON (upper1= test[expr_contextType.Load] )? ( sliceop )? )?
                    int alt124=2;
                    int LA124_0 = input.LA(1);

                    if ( (LA124_0==COLON) ) {
                        alt124=1;
                    }
                    switch (alt124) {
                        case 1 :
                            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1850:42: c1= COLON (upper1= test[expr_contextType.Load] )? ( sliceop )?
                            {
                            c1=(Token)match(input,COLON,FOLLOW_COLON_in_subscript6820); if (state.failed) return retval;
                            if ( state.backtracking==0 ) {
                            c1_tree = (PythonTree)adaptor.create(c1);
                            adaptor.addChild(root_0, c1_tree);
                            }
                            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1850:51: (upper1= test[expr_contextType.Load] )?
                            int alt122=2;
                            int LA122_0 = input.LA(1);

                            if ( (LA122_0==NAME||(LA122_0>=LAMBDA && LA122_0<=NOT)||LA122_0==LPAREN||(LA122_0>=PLUS && LA122_0<=MINUS)||(LA122_0>=TILDE && LA122_0<=LBRACK)||LA122_0==LCURLY||(LA122_0>=BACKQUOTE && LA122_0<=STRING)) ) {
                                alt122=1;
                            }
                            switch (alt122) {
                                case 1 :
                                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1850:52: upper1= test[expr_contextType.Load]
                                    {
                                    pushFollow(FOLLOW_test_in_subscript6825);
                                    upper1=test(expr_contextType.Load);

                                    state._fsp--;
                                    if (state.failed) return retval;
                                    if ( state.backtracking==0 ) adaptor.addChild(root_0, upper1.getTree());

                                    }
                                    break;

                            }

                            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1850:89: ( sliceop )?
                            int alt123=2;
                            int LA123_0 = input.LA(1);

                            if ( (LA123_0==COLON) ) {
                                alt123=1;
                            }
                            switch (alt123) {
                                case 1 :
                                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1850:90: sliceop
                                    {
                                    pushFollow(FOLLOW_sliceop_in_subscript6831);
                                    sliceop252=sliceop();

                                    state._fsp--;
                                    if (state.failed) return retval;
                                    if ( state.backtracking==0 ) adaptor.addChild(root_0, sliceop252.getTree());

                                    }
                                    break;

                            }


                            }
                            break;

                    }

                    if ( state.backtracking==0 ) {

                                retval.sltype = actions.makeSubscript((lower!=null?((PythonTree)lower.tree):null), c1, (upper1!=null?((PythonTree)upper1.tree):null), (sliceop252!=null?((PythonTree)sliceop252.tree):null));
                            
                    }

                    }
                    break;
                case 3 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1854:7: ( COLON )=>c2= COLON (upper2= test[expr_contextType.Load] )? ( sliceop )?
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    c2=(Token)match(input,COLON,FOLLOW_COLON_in_subscript6862); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    c2_tree = (PythonTree)adaptor.create(c2);
                    adaptor.addChild(root_0, c2_tree);
                    }
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1855:16: (upper2= test[expr_contextType.Load] )?
                    int alt125=2;
                    int LA125_0 = input.LA(1);

                    if ( (LA125_0==NAME||(LA125_0>=LAMBDA && LA125_0<=NOT)||LA125_0==LPAREN||(LA125_0>=PLUS && LA125_0<=MINUS)||(LA125_0>=TILDE && LA125_0<=LBRACK)||LA125_0==LCURLY||(LA125_0>=BACKQUOTE && LA125_0<=STRING)) ) {
                        alt125=1;
                    }
                    switch (alt125) {
                        case 1 :
                            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1855:17: upper2= test[expr_contextType.Load]
                            {
                            pushFollow(FOLLOW_test_in_subscript6867);
                            upper2=test(expr_contextType.Load);

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, upper2.getTree());

                            }
                            break;

                    }

                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1855:54: ( sliceop )?
                    int alt126=2;
                    int LA126_0 = input.LA(1);

                    if ( (LA126_0==COLON) ) {
                        alt126=1;
                    }
                    switch (alt126) {
                        case 1 :
                            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1855:55: sliceop
                            {
                            pushFollow(FOLLOW_sliceop_in_subscript6873);
                            sliceop253=sliceop();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, sliceop253.getTree());

                            }
                            break;

                    }

                    if ( state.backtracking==0 ) {

                                retval.sltype = actions.makeSubscript(null, c2, (upper2!=null?((PythonTree)upper2.tree):null), (sliceop253!=null?((PythonTree)sliceop253.tree):null));
                            
                    }

                    }
                    break;
                case 4 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1859:7: test[expr_contextType.Load]
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    pushFollow(FOLLOW_test_in_subscript6891);
                    test254=test(expr_contextType.Load);

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, test254.getTree());
                    if ( state.backtracking==0 ) {

                                retval.sltype = new Index((test254!=null?((Token)test254.start):null), actions.castExpr((test254!=null?((PythonTree)test254.tree):null)));
                            
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {

                  retval.tree = retval.sltype;

            }
        }

        catch (RecognitionException re) {
            errorHandler.reportError(this, re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "subscript"

    public static class sliceop_return extends ParserRuleReturnScope {
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "sliceop"
    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1866:1: sliceop : COLON ( test[expr_contextType.Load] -> test | ) ;
    public final PythonParser.sliceop_return sliceop() throws RecognitionException {
        PythonParser.sliceop_return retval = new PythonParser.sliceop_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token COLON255=null;
        PythonParser.test_return test256 = null;


        PythonTree COLON255_tree=null;
        RewriteRuleTokenStream stream_COLON=new RewriteRuleTokenStream(adaptor,"token COLON");
        RewriteRuleSubtreeStream stream_test=new RewriteRuleSubtreeStream(adaptor,"rule test");

            expr etype = null;

        try {
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1875:5: ( COLON ( test[expr_contextType.Load] -> test | ) )
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1875:7: COLON ( test[expr_contextType.Load] -> test | )
            {
            COLON255=(Token)match(input,COLON,FOLLOW_COLON_in_sliceop6928); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_COLON.add(COLON255);

            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1876:6: ( test[expr_contextType.Load] -> test | )
            int alt128=2;
            int LA128_0 = input.LA(1);

            if ( (LA128_0==NAME||(LA128_0>=LAMBDA && LA128_0<=NOT)||LA128_0==LPAREN||(LA128_0>=PLUS && LA128_0<=MINUS)||(LA128_0>=TILDE && LA128_0<=LBRACK)||LA128_0==LCURLY||(LA128_0>=BACKQUOTE && LA128_0<=STRING)) ) {
                alt128=1;
            }
            else if ( (LA128_0==COMMA||LA128_0==RBRACK) ) {
                alt128=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 128, 0, input);

                throw nvae;
            }
            switch (alt128) {
                case 1 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1876:7: test[expr_contextType.Load]
                    {
                    pushFollow(FOLLOW_test_in_sliceop6936);
                    test256=test(expr_contextType.Load);

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_test.add(test256.getTree());


                    // AST REWRITE
                    // elements: test
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (PythonTree)adaptor.nil();
                    // 1877:5: -> test
                    {
                        adaptor.addChild(root_0, stream_test.nextTree());

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 2 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1879:8: 
                    {
                    if ( state.backtracking==0 ) {

                                 etype = new Name(COLON255, "None", expr_contextType.Load);
                             
                    }

                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {

                  if (etype != null) {
                      retval.tree = etype;
                  }

            }
        }

        catch (RecognitionException re) {
            errorHandler.reportError(this, re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "sliceop"

    public static class exprlist_return extends ParserRuleReturnScope {
        public expr etype;
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "exprlist"
    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1886:1: exprlist[expr_contextType ctype] returns [expr etype] : ( ( expr[null] COMMA )=>e+= expr[ctype] ( options {k=2; } : COMMA e+= expr[ctype] )* ( COMMA )? | expr[ctype] );
    public final PythonParser.exprlist_return exprlist(expr_contextType ctype) throws RecognitionException {
        PythonParser.exprlist_return retval = new PythonParser.exprlist_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token COMMA257=null;
        Token COMMA258=null;
        List list_e=null;
        PythonParser.expr_return expr259 = null;

        PythonParser.expr_return e = null;
         e = null;
        PythonTree COMMA257_tree=null;
        PythonTree COMMA258_tree=null;

        try {
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1888:5: ( ( expr[null] COMMA )=>e+= expr[ctype] ( options {k=2; } : COMMA e+= expr[ctype] )* ( COMMA )? | expr[ctype] )
            int alt131=2;
            alt131 = dfa131.predict(input);
            switch (alt131) {
                case 1 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1888:7: ( expr[null] COMMA )=>e+= expr[ctype] ( options {k=2; } : COMMA e+= expr[ctype] )* ( COMMA )?
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    pushFollow(FOLLOW_expr_in_exprlist7007);
                    e=expr(ctype);

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, e.getTree());
                    if (list_e==null) list_e=new ArrayList();
                    list_e.add(e.getTree());

                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1888:44: ( options {k=2; } : COMMA e+= expr[ctype] )*
                    loop129:
                    do {
                        int alt129=2;
                        alt129 = dfa129.predict(input);
                        switch (alt129) {
                    	case 1 :
                    	    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1888:61: COMMA e+= expr[ctype]
                    	    {
                    	    COMMA257=(Token)match(input,COMMA,FOLLOW_COMMA_in_exprlist7019); if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) {
                    	    COMMA257_tree = (PythonTree)adaptor.create(COMMA257);
                    	    adaptor.addChild(root_0, COMMA257_tree);
                    	    }
                    	    pushFollow(FOLLOW_expr_in_exprlist7023);
                    	    e=expr(ctype);

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) adaptor.addChild(root_0, e.getTree());
                    	    if (list_e==null) list_e=new ArrayList();
                    	    list_e.add(e.getTree());


                    	    }
                    	    break;

                    	default :
                    	    break loop129;
                        }
                    } while (true);

                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1888:84: ( COMMA )?
                    int alt130=2;
                    int LA130_0 = input.LA(1);

                    if ( (LA130_0==COMMA) ) {
                        alt130=1;
                    }
                    switch (alt130) {
                        case 1 :
                            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1888:85: COMMA
                            {
                            COMMA258=(Token)match(input,COMMA,FOLLOW_COMMA_in_exprlist7029); if (state.failed) return retval;
                            if ( state.backtracking==0 ) {
                            COMMA258_tree = (PythonTree)adaptor.create(COMMA258);
                            adaptor.addChild(root_0, COMMA258_tree);
                            }

                            }
                            break;

                    }

                    if ( state.backtracking==0 ) {

                                 retval.etype = new Tuple(((Token)retval.start), actions.castExprs(list_e), ctype);
                             
                    }

                    }
                    break;
                case 2 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1892:7: expr[ctype]
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    pushFollow(FOLLOW_expr_in_exprlist7048);
                    expr259=expr(ctype);

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, expr259.getTree());
                    if ( state.backtracking==0 ) {

                              retval.etype = actions.castExpr((expr259!=null?((PythonTree)expr259.tree):null));
                            
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException re) {
            errorHandler.reportError(this, re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "exprlist"

    public static class del_list_return extends ParserRuleReturnScope {
        public List<expr> etypes;
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "del_list"
    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1900:1: del_list returns [List<expr> etypes] : e+= expr[expr_contextType.Del] ( options {k=2; } : COMMA e+= expr[expr_contextType.Del] )* ( COMMA )? ;
    public final PythonParser.del_list_return del_list() throws RecognitionException {
        PythonParser.del_list_return retval = new PythonParser.del_list_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token COMMA260=null;
        Token COMMA261=null;
        List list_e=null;
        PythonParser.expr_return e = null;
         e = null;
        PythonTree COMMA260_tree=null;
        PythonTree COMMA261_tree=null;

        try {
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1902:5: (e+= expr[expr_contextType.Del] ( options {k=2; } : COMMA e+= expr[expr_contextType.Del] )* ( COMMA )? )
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1902:7: e+= expr[expr_contextType.Del] ( options {k=2; } : COMMA e+= expr[expr_contextType.Del] )* ( COMMA )?
            {
            root_0 = (PythonTree)adaptor.nil();

            pushFollow(FOLLOW_expr_in_del_list7086);
            e=expr(expr_contextType.Del);

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, e.getTree());
            if (list_e==null) list_e=new ArrayList();
            list_e.add(e.getTree());

            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1902:37: ( options {k=2; } : COMMA e+= expr[expr_contextType.Del] )*
            loop132:
            do {
                int alt132=2;
                alt132 = dfa132.predict(input);
                switch (alt132) {
            	case 1 :
            	    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1902:54: COMMA e+= expr[expr_contextType.Del]
            	    {
            	    COMMA260=(Token)match(input,COMMA,FOLLOW_COMMA_in_del_list7098); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    COMMA260_tree = (PythonTree)adaptor.create(COMMA260);
            	    adaptor.addChild(root_0, COMMA260_tree);
            	    }
            	    pushFollow(FOLLOW_expr_in_del_list7102);
            	    e=expr(expr_contextType.Del);

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, e.getTree());
            	    if (list_e==null) list_e=new ArrayList();
            	    list_e.add(e.getTree());


            	    }
            	    break;

            	default :
            	    break loop132;
                }
            } while (true);

            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1902:92: ( COMMA )?
            int alt133=2;
            int LA133_0 = input.LA(1);

            if ( (LA133_0==COMMA) ) {
                alt133=1;
            }
            switch (alt133) {
                case 1 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1902:93: COMMA
                    {
                    COMMA261=(Token)match(input,COMMA,FOLLOW_COMMA_in_del_list7108); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    COMMA261_tree = (PythonTree)adaptor.create(COMMA261);
                    adaptor.addChild(root_0, COMMA261_tree);
                    }

                    }
                    break;

            }

            if ( state.backtracking==0 ) {

                        retval.etypes = actions.makeDeleteList(list_e);
                    
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException re) {
            errorHandler.reportError(this, re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "del_list"

    public static class testlist_return extends ParserRuleReturnScope {
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "testlist"
    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1909:1: testlist[expr_contextType ctype] : ( ( test[null] COMMA )=>t+= test[ctype] ( options {k=2; } : COMMA t+= test[ctype] )* ( COMMA )? | test[ctype] );
    public final PythonParser.testlist_return testlist(expr_contextType ctype) throws RecognitionException {
        PythonParser.testlist_return retval = new PythonParser.testlist_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token COMMA262=null;
        Token COMMA263=null;
        List list_t=null;
        PythonParser.test_return test264 = null;

        PythonParser.test_return t = null;
         t = null;
        PythonTree COMMA262_tree=null;
        PythonTree COMMA263_tree=null;


            expr etype = null;

        try {
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1918:5: ( ( test[null] COMMA )=>t+= test[ctype] ( options {k=2; } : COMMA t+= test[ctype] )* ( COMMA )? | test[ctype] )
            int alt136=2;
            alt136 = dfa136.predict(input);
            switch (alt136) {
                case 1 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1918:7: ( test[null] COMMA )=>t+= test[ctype] ( options {k=2; } : COMMA t+= test[ctype] )* ( COMMA )?
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    pushFollow(FOLLOW_test_in_testlist7161);
                    t=test(ctype);

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, t.getTree());
                    if (list_t==null) list_t=new ArrayList();
                    list_t.add(t.getTree());

                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1919:22: ( options {k=2; } : COMMA t+= test[ctype] )*
                    loop134:
                    do {
                        int alt134=2;
                        alt134 = dfa134.predict(input);
                        switch (alt134) {
                    	case 1 :
                    	    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1919:39: COMMA t+= test[ctype]
                    	    {
                    	    COMMA262=(Token)match(input,COMMA,FOLLOW_COMMA_in_testlist7173); if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) {
                    	    COMMA262_tree = (PythonTree)adaptor.create(COMMA262);
                    	    adaptor.addChild(root_0, COMMA262_tree);
                    	    }
                    	    pushFollow(FOLLOW_test_in_testlist7177);
                    	    t=test(ctype);

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) adaptor.addChild(root_0, t.getTree());
                    	    if (list_t==null) list_t=new ArrayList();
                    	    list_t.add(t.getTree());


                    	    }
                    	    break;

                    	default :
                    	    break loop134;
                        }
                    } while (true);

                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1919:62: ( COMMA )?
                    int alt135=2;
                    int LA135_0 = input.LA(1);

                    if ( (LA135_0==COMMA) ) {
                        alt135=1;
                    }
                    switch (alt135) {
                        case 1 :
                            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1919:63: COMMA
                            {
                            COMMA263=(Token)match(input,COMMA,FOLLOW_COMMA_in_testlist7183); if (state.failed) return retval;
                            if ( state.backtracking==0 ) {
                            COMMA263_tree = (PythonTree)adaptor.create(COMMA263);
                            adaptor.addChild(root_0, COMMA263_tree);
                            }

                            }
                            break;

                    }

                    if ( state.backtracking==0 ) {

                                etype = new Tuple(((Token)retval.start), actions.castExprs(list_t), ctype);
                            
                    }

                    }
                    break;
                case 2 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1923:7: test[ctype]
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    pushFollow(FOLLOW_test_in_testlist7201);
                    test264=test(ctype);

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, test264.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {

                  if (etype != null) {
                      retval.tree = etype;
                  }

            }
        }

        catch (RecognitionException re) {
            errorHandler.reportError(this, re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "testlist"

    public static class dictmaker_return extends ParserRuleReturnScope {
        public List keys;
        public List values;
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "dictmaker"
    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1927:1: dictmaker returns [List keys, List values] : k+= test[expr_contextType.Load] COLON v+= test[expr_contextType.Load] ( options {k=2; } : COMMA k+= test[expr_contextType.Load] COLON v+= test[expr_contextType.Load] )* ( COMMA )? ;
    public final PythonParser.dictmaker_return dictmaker() throws RecognitionException {
        PythonParser.dictmaker_return retval = new PythonParser.dictmaker_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token COLON265=null;
        Token COMMA266=null;
        Token COLON267=null;
        Token COMMA268=null;
        List list_k=null;
        List list_v=null;
        PythonParser.test_return k = null;
         k = null;
        PythonParser.test_return v = null;
         v = null;
        PythonTree COLON265_tree=null;
        PythonTree COMMA266_tree=null;
        PythonTree COLON267_tree=null;
        PythonTree COMMA268_tree=null;

        try {
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1929:5: (k+= test[expr_contextType.Load] COLON v+= test[expr_contextType.Load] ( options {k=2; } : COMMA k+= test[expr_contextType.Load] COLON v+= test[expr_contextType.Load] )* ( COMMA )? )
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1929:7: k+= test[expr_contextType.Load] COLON v+= test[expr_contextType.Load] ( options {k=2; } : COMMA k+= test[expr_contextType.Load] COLON v+= test[expr_contextType.Load] )* ( COMMA )?
            {
            root_0 = (PythonTree)adaptor.nil();

            pushFollow(FOLLOW_test_in_dictmaker7230);
            k=test(expr_contextType.Load);

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, k.getTree());
            if (list_k==null) list_k=new ArrayList();
            list_k.add(k.getTree());

            COLON265=(Token)match(input,COLON,FOLLOW_COLON_in_dictmaker7233); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            COLON265_tree = (PythonTree)adaptor.create(COLON265);
            adaptor.addChild(root_0, COLON265_tree);
            }
            pushFollow(FOLLOW_test_in_dictmaker7237);
            v=test(expr_contextType.Load);

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, v.getTree());
            if (list_v==null) list_v=new ArrayList();
            list_v.add(v.getTree());

            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1930:9: ( options {k=2; } : COMMA k+= test[expr_contextType.Load] COLON v+= test[expr_contextType.Load] )*
            loop137:
            do {
                int alt137=2;
                alt137 = dfa137.predict(input);
                switch (alt137) {
            	case 1 :
            	    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1930:25: COMMA k+= test[expr_contextType.Load] COLON v+= test[expr_contextType.Load]
            	    {
            	    COMMA266=(Token)match(input,COMMA,FOLLOW_COMMA_in_dictmaker7256); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    COMMA266_tree = (PythonTree)adaptor.create(COMMA266);
            	    adaptor.addChild(root_0, COMMA266_tree);
            	    }
            	    pushFollow(FOLLOW_test_in_dictmaker7260);
            	    k=test(expr_contextType.Load);

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, k.getTree());
            	    if (list_k==null) list_k=new ArrayList();
            	    list_k.add(k.getTree());

            	    COLON267=(Token)match(input,COLON,FOLLOW_COLON_in_dictmaker7263); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    COLON267_tree = (PythonTree)adaptor.create(COLON267);
            	    adaptor.addChild(root_0, COLON267_tree);
            	    }
            	    pushFollow(FOLLOW_test_in_dictmaker7267);
            	    v=test(expr_contextType.Load);

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, v.getTree());
            	    if (list_v==null) list_v=new ArrayList();
            	    list_v.add(v.getTree());


            	    }
            	    break;

            	default :
            	    break loop137;
                }
            } while (true);

            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1931:9: ( COMMA )?
            int alt138=2;
            int LA138_0 = input.LA(1);

            if ( (LA138_0==COMMA) ) {
                alt138=1;
            }
            switch (alt138) {
                case 1 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1931:10: COMMA
                    {
                    COMMA268=(Token)match(input,COMMA,FOLLOW_COMMA_in_dictmaker7281); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    COMMA268_tree = (PythonTree)adaptor.create(COMMA268);
                    adaptor.addChild(root_0, COMMA268_tree);
                    }

                    }
                    break;

            }

            if ( state.backtracking==0 ) {

                        retval.keys = list_k;
                        retval.values = list_v;
                    
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException re) {
            errorHandler.reportError(this, re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "dictmaker"

    public static class classdef_return extends ParserRuleReturnScope {
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "classdef"
    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1939:1: classdef : ( decorators )? CLASS NAME ( LPAREN ( testlist[expr_contextType.Load] )? RPAREN )? COLON suite[false] ;
    public final PythonParser.classdef_return classdef() throws RecognitionException {
        PythonParser.classdef_return retval = new PythonParser.classdef_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token CLASS270=null;
        Token NAME271=null;
        Token LPAREN272=null;
        Token RPAREN274=null;
        Token COLON275=null;
        PythonParser.decorators_return decorators269 = null;

        PythonParser.testlist_return testlist273 = null;

        PythonParser.suite_return suite276 = null;


        PythonTree CLASS270_tree=null;
        PythonTree NAME271_tree=null;
        PythonTree LPAREN272_tree=null;
        PythonTree RPAREN274_tree=null;
        PythonTree COLON275_tree=null;


            stmt stype = null;

        try {
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1946:5: ( ( decorators )? CLASS NAME ( LPAREN ( testlist[expr_contextType.Load] )? RPAREN )? COLON suite[false] )
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1946:7: ( decorators )? CLASS NAME ( LPAREN ( testlist[expr_contextType.Load] )? RPAREN )? COLON suite[false]
            {
            root_0 = (PythonTree)adaptor.nil();

            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1946:7: ( decorators )?
            int alt139=2;
            int LA139_0 = input.LA(1);

            if ( (LA139_0==AT) ) {
                alt139=1;
            }
            switch (alt139) {
                case 1 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1946:7: decorators
                    {
                    pushFollow(FOLLOW_decorators_in_classdef7319);
                    decorators269=decorators();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, decorators269.getTree());

                    }
                    break;

            }

            CLASS270=(Token)match(input,CLASS,FOLLOW_CLASS_in_classdef7322); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            CLASS270_tree = (PythonTree)adaptor.create(CLASS270);
            adaptor.addChild(root_0, CLASS270_tree);
            }
            NAME271=(Token)match(input,NAME,FOLLOW_NAME_in_classdef7324); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            NAME271_tree = (PythonTree)adaptor.create(NAME271);
            adaptor.addChild(root_0, NAME271_tree);
            }
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1946:30: ( LPAREN ( testlist[expr_contextType.Load] )? RPAREN )?
            int alt141=2;
            int LA141_0 = input.LA(1);

            if ( (LA141_0==LPAREN) ) {
                alt141=1;
            }
            switch (alt141) {
                case 1 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1946:31: LPAREN ( testlist[expr_contextType.Load] )? RPAREN
                    {
                    LPAREN272=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_classdef7327); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    LPAREN272_tree = (PythonTree)adaptor.create(LPAREN272);
                    adaptor.addChild(root_0, LPAREN272_tree);
                    }
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1946:38: ( testlist[expr_contextType.Load] )?
                    int alt140=2;
                    int LA140_0 = input.LA(1);

                    if ( (LA140_0==NAME||(LA140_0>=LAMBDA && LA140_0<=NOT)||LA140_0==LPAREN||(LA140_0>=PLUS && LA140_0<=MINUS)||(LA140_0>=TILDE && LA140_0<=LBRACK)||LA140_0==LCURLY||(LA140_0>=BACKQUOTE && LA140_0<=STRING)) ) {
                        alt140=1;
                    }
                    switch (alt140) {
                        case 1 :
                            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1946:38: testlist[expr_contextType.Load]
                            {
                            pushFollow(FOLLOW_testlist_in_classdef7329);
                            testlist273=testlist(expr_contextType.Load);

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, testlist273.getTree());

                            }
                            break;

                    }

                    RPAREN274=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_classdef7333); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    RPAREN274_tree = (PythonTree)adaptor.create(RPAREN274);
                    adaptor.addChild(root_0, RPAREN274_tree);
                    }

                    }
                    break;

            }

            COLON275=(Token)match(input,COLON,FOLLOW_COLON_in_classdef7337); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            COLON275_tree = (PythonTree)adaptor.create(COLON275);
            adaptor.addChild(root_0, COLON275_tree);
            }
            pushFollow(FOLLOW_suite_in_classdef7339);
            suite276=suite(false);

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, suite276.getTree());
            if ( state.backtracking==0 ) {

                        Token t = CLASS270;
                        if ((decorators269!=null?((Token)decorators269.start):null) != null) {
                            t = (decorators269!=null?((Token)decorators269.start):null);
                        }
                        stype = new ClassDef(t, actions.cantBeNoneName(NAME271),
                            actions.makeBases(actions.castExpr((testlist273!=null?((PythonTree)testlist273.tree):null))),
                            actions.castStmts((suite276!=null?suite276.stypes:null)),
                            actions.castExprs((decorators269!=null?decorators269.etypes:null)));
                    
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {

                 retval.tree = stype;

            }
        }

        catch (RecognitionException re) {
            errorHandler.reportError(this, re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "classdef"

    public static class arglist_return extends ParserRuleReturnScope {
        public List args;
        public List keywords;
        public expr starargs;
        public expr kwargs;
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "arglist"
    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1962:1: arglist returns [List args, List keywords, expr starargs, expr kwargs] : ( argument[arguments, kws, gens, true, false] ( COMMA argument[arguments, kws, gens, false, false] )* ( COMMA ( STAR s= test[expr_contextType.Load] ( COMMA argument[arguments, kws, gens, false, true] )* ( COMMA DOUBLESTAR k= test[expr_contextType.Load] )? | DOUBLESTAR k= test[expr_contextType.Load] )? )? | STAR s= test[expr_contextType.Load] ( COMMA argument[arguments, kws, gens, false, true] )* ( COMMA DOUBLESTAR k= test[expr_contextType.Load] )? | DOUBLESTAR k= test[expr_contextType.Load] );
    public final PythonParser.arglist_return arglist() throws RecognitionException {
        PythonParser.arglist_return retval = new PythonParser.arglist_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token COMMA278=null;
        Token COMMA280=null;
        Token STAR281=null;
        Token COMMA282=null;
        Token COMMA284=null;
        Token DOUBLESTAR285=null;
        Token DOUBLESTAR286=null;
        Token STAR287=null;
        Token COMMA288=null;
        Token COMMA290=null;
        Token DOUBLESTAR291=null;
        Token DOUBLESTAR292=null;
        PythonParser.test_return s = null;

        PythonParser.test_return k = null;

        PythonParser.argument_return argument277 = null;

        PythonParser.argument_return argument279 = null;

        PythonParser.argument_return argument283 = null;

        PythonParser.argument_return argument289 = null;


        PythonTree COMMA278_tree=null;
        PythonTree COMMA280_tree=null;
        PythonTree STAR281_tree=null;
        PythonTree COMMA282_tree=null;
        PythonTree COMMA284_tree=null;
        PythonTree DOUBLESTAR285_tree=null;
        PythonTree DOUBLESTAR286_tree=null;
        PythonTree STAR287_tree=null;
        PythonTree COMMA288_tree=null;
        PythonTree COMMA290_tree=null;
        PythonTree DOUBLESTAR291_tree=null;
        PythonTree DOUBLESTAR292_tree=null;


            List arguments = new ArrayList();
            List kws = new ArrayList();
            List gens = new ArrayList();

        try {
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1969:5: ( argument[arguments, kws, gens, true, false] ( COMMA argument[arguments, kws, gens, false, false] )* ( COMMA ( STAR s= test[expr_contextType.Load] ( COMMA argument[arguments, kws, gens, false, true] )* ( COMMA DOUBLESTAR k= test[expr_contextType.Load] )? | DOUBLESTAR k= test[expr_contextType.Load] )? )? | STAR s= test[expr_contextType.Load] ( COMMA argument[arguments, kws, gens, false, true] )* ( COMMA DOUBLESTAR k= test[expr_contextType.Load] )? | DOUBLESTAR k= test[expr_contextType.Load] )
            int alt149=3;
            switch ( input.LA(1) ) {
            case NAME:
            case LAMBDA:
            case NOT:
            case LPAREN:
            case PLUS:
            case MINUS:
            case TILDE:
            case LBRACK:
            case LCURLY:
            case BACKQUOTE:
            case INT:
            case LONGINT:
            case FLOAT:
            case COMPLEX:
            case STRING:
                {
                alt149=1;
                }
                break;
            case STAR:
                {
                alt149=2;
                }
                break;
            case DOUBLESTAR:
                {
                alt149=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 149, 0, input);

                throw nvae;
            }

            switch (alt149) {
                case 1 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1969:7: argument[arguments, kws, gens, true, false] ( COMMA argument[arguments, kws, gens, false, false] )* ( COMMA ( STAR s= test[expr_contextType.Load] ( COMMA argument[arguments, kws, gens, false, true] )* ( COMMA DOUBLESTAR k= test[expr_contextType.Load] )? | DOUBLESTAR k= test[expr_contextType.Load] )? )?
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    pushFollow(FOLLOW_argument_in_arglist7381);
                    argument277=argument(arguments, kws, gens, true, false);

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, argument277.getTree());
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1969:51: ( COMMA argument[arguments, kws, gens, false, false] )*
                    loop142:
                    do {
                        int alt142=2;
                        int LA142_0 = input.LA(1);

                        if ( (LA142_0==COMMA) ) {
                            int LA142_1 = input.LA(2);

                            if ( (LA142_1==NAME||(LA142_1>=LAMBDA && LA142_1<=NOT)||LA142_1==LPAREN||(LA142_1>=PLUS && LA142_1<=MINUS)||(LA142_1>=TILDE && LA142_1<=LBRACK)||LA142_1==LCURLY||(LA142_1>=BACKQUOTE && LA142_1<=STRING)) ) {
                                alt142=1;
                            }


                        }


                        switch (alt142) {
                    	case 1 :
                    	    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1969:52: COMMA argument[arguments, kws, gens, false, false]
                    	    {
                    	    COMMA278=(Token)match(input,COMMA,FOLLOW_COMMA_in_arglist7385); if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) {
                    	    COMMA278_tree = (PythonTree)adaptor.create(COMMA278);
                    	    adaptor.addChild(root_0, COMMA278_tree);
                    	    }
                    	    pushFollow(FOLLOW_argument_in_arglist7387);
                    	    argument279=argument(arguments, kws, gens, false, false);

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) adaptor.addChild(root_0, argument279.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop142;
                        }
                    } while (true);

                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1970:11: ( COMMA ( STAR s= test[expr_contextType.Load] ( COMMA argument[arguments, kws, gens, false, true] )* ( COMMA DOUBLESTAR k= test[expr_contextType.Load] )? | DOUBLESTAR k= test[expr_contextType.Load] )? )?
                    int alt146=2;
                    int LA146_0 = input.LA(1);

                    if ( (LA146_0==COMMA) ) {
                        alt146=1;
                    }
                    switch (alt146) {
                        case 1 :
                            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1970:12: COMMA ( STAR s= test[expr_contextType.Load] ( COMMA argument[arguments, kws, gens, false, true] )* ( COMMA DOUBLESTAR k= test[expr_contextType.Load] )? | DOUBLESTAR k= test[expr_contextType.Load] )?
                            {
                            COMMA280=(Token)match(input,COMMA,FOLLOW_COMMA_in_arglist7403); if (state.failed) return retval;
                            if ( state.backtracking==0 ) {
                            COMMA280_tree = (PythonTree)adaptor.create(COMMA280);
                            adaptor.addChild(root_0, COMMA280_tree);
                            }
                            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1971:15: ( STAR s= test[expr_contextType.Load] ( COMMA argument[arguments, kws, gens, false, true] )* ( COMMA DOUBLESTAR k= test[expr_contextType.Load] )? | DOUBLESTAR k= test[expr_contextType.Load] )?
                            int alt145=3;
                            int LA145_0 = input.LA(1);

                            if ( (LA145_0==STAR) ) {
                                alt145=1;
                            }
                            else if ( (LA145_0==DOUBLESTAR) ) {
                                alt145=2;
                            }
                            switch (alt145) {
                                case 1 :
                                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1971:17: STAR s= test[expr_contextType.Load] ( COMMA argument[arguments, kws, gens, false, true] )* ( COMMA DOUBLESTAR k= test[expr_contextType.Load] )?
                                    {
                                    STAR281=(Token)match(input,STAR,FOLLOW_STAR_in_arglist7421); if (state.failed) return retval;
                                    if ( state.backtracking==0 ) {
                                    STAR281_tree = (PythonTree)adaptor.create(STAR281);
                                    adaptor.addChild(root_0, STAR281_tree);
                                    }
                                    pushFollow(FOLLOW_test_in_arglist7425);
                                    s=test(expr_contextType.Load);

                                    state._fsp--;
                                    if (state.failed) return retval;
                                    if ( state.backtracking==0 ) adaptor.addChild(root_0, s.getTree());
                                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1971:52: ( COMMA argument[arguments, kws, gens, false, true] )*
                                    loop143:
                                    do {
                                        int alt143=2;
                                        int LA143_0 = input.LA(1);

                                        if ( (LA143_0==COMMA) ) {
                                            int LA143_1 = input.LA(2);

                                            if ( (LA143_1==NAME||(LA143_1>=LAMBDA && LA143_1<=NOT)||LA143_1==LPAREN||(LA143_1>=PLUS && LA143_1<=MINUS)||(LA143_1>=TILDE && LA143_1<=LBRACK)||LA143_1==LCURLY||(LA143_1>=BACKQUOTE && LA143_1<=STRING)) ) {
                                                alt143=1;
                                            }


                                        }


                                        switch (alt143) {
                                    	case 1 :
                                    	    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1971:53: COMMA argument[arguments, kws, gens, false, true]
                                    	    {
                                    	    COMMA282=(Token)match(input,COMMA,FOLLOW_COMMA_in_arglist7429); if (state.failed) return retval;
                                    	    if ( state.backtracking==0 ) {
                                    	    COMMA282_tree = (PythonTree)adaptor.create(COMMA282);
                                    	    adaptor.addChild(root_0, COMMA282_tree);
                                    	    }
                                    	    pushFollow(FOLLOW_argument_in_arglist7431);
                                    	    argument283=argument(arguments, kws, gens, false, true);

                                    	    state._fsp--;
                                    	    if (state.failed) return retval;
                                    	    if ( state.backtracking==0 ) adaptor.addChild(root_0, argument283.getTree());

                                    	    }
                                    	    break;

                                    	default :
                                    	    break loop143;
                                        }
                                    } while (true);

                                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1971:105: ( COMMA DOUBLESTAR k= test[expr_contextType.Load] )?
                                    int alt144=2;
                                    int LA144_0 = input.LA(1);

                                    if ( (LA144_0==COMMA) ) {
                                        alt144=1;
                                    }
                                    switch (alt144) {
                                        case 1 :
                                            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1971:106: COMMA DOUBLESTAR k= test[expr_contextType.Load]
                                            {
                                            COMMA284=(Token)match(input,COMMA,FOLLOW_COMMA_in_arglist7437); if (state.failed) return retval;
                                            if ( state.backtracking==0 ) {
                                            COMMA284_tree = (PythonTree)adaptor.create(COMMA284);
                                            adaptor.addChild(root_0, COMMA284_tree);
                                            }
                                            DOUBLESTAR285=(Token)match(input,DOUBLESTAR,FOLLOW_DOUBLESTAR_in_arglist7439); if (state.failed) return retval;
                                            if ( state.backtracking==0 ) {
                                            DOUBLESTAR285_tree = (PythonTree)adaptor.create(DOUBLESTAR285);
                                            adaptor.addChild(root_0, DOUBLESTAR285_tree);
                                            }
                                            pushFollow(FOLLOW_test_in_arglist7443);
                                            k=test(expr_contextType.Load);

                                            state._fsp--;
                                            if (state.failed) return retval;
                                            if ( state.backtracking==0 ) adaptor.addChild(root_0, k.getTree());

                                            }
                                            break;

                                    }


                                    }
                                    break;
                                case 2 :
                                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1972:17: DOUBLESTAR k= test[expr_contextType.Load]
                                    {
                                    DOUBLESTAR286=(Token)match(input,DOUBLESTAR,FOLLOW_DOUBLESTAR_in_arglist7464); if (state.failed) return retval;
                                    if ( state.backtracking==0 ) {
                                    DOUBLESTAR286_tree = (PythonTree)adaptor.create(DOUBLESTAR286);
                                    adaptor.addChild(root_0, DOUBLESTAR286_tree);
                                    }
                                    pushFollow(FOLLOW_test_in_arglist7468);
                                    k=test(expr_contextType.Load);

                                    state._fsp--;
                                    if (state.failed) return retval;
                                    if ( state.backtracking==0 ) adaptor.addChild(root_0, k.getTree());

                                    }
                                    break;

                            }


                            }
                            break;

                    }

                    if ( state.backtracking==0 ) {

                                if (arguments.size() > 1 && gens.size() > 0) {
                                    actions.errorGenExpNotSoleArg(new PythonTree(((Token)retval.start)));
                                }
                                retval.args =arguments;
                                retval.keywords =kws;
                                retval.starargs =actions.castExpr((s!=null?((PythonTree)s.tree):null));
                                retval.kwargs =actions.castExpr((k!=null?((PythonTree)k.tree):null));
                            
                    }

                    }
                    break;
                case 2 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1984:7: STAR s= test[expr_contextType.Load] ( COMMA argument[arguments, kws, gens, false, true] )* ( COMMA DOUBLESTAR k= test[expr_contextType.Load] )?
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    STAR287=(Token)match(input,STAR,FOLLOW_STAR_in_arglist7515); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    STAR287_tree = (PythonTree)adaptor.create(STAR287);
                    adaptor.addChild(root_0, STAR287_tree);
                    }
                    pushFollow(FOLLOW_test_in_arglist7519);
                    s=test(expr_contextType.Load);

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, s.getTree());
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1984:42: ( COMMA argument[arguments, kws, gens, false, true] )*
                    loop147:
                    do {
                        int alt147=2;
                        int LA147_0 = input.LA(1);

                        if ( (LA147_0==COMMA) ) {
                            int LA147_1 = input.LA(2);

                            if ( (LA147_1==NAME||(LA147_1>=LAMBDA && LA147_1<=NOT)||LA147_1==LPAREN||(LA147_1>=PLUS && LA147_1<=MINUS)||(LA147_1>=TILDE && LA147_1<=LBRACK)||LA147_1==LCURLY||(LA147_1>=BACKQUOTE && LA147_1<=STRING)) ) {
                                alt147=1;
                            }


                        }


                        switch (alt147) {
                    	case 1 :
                    	    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1984:43: COMMA argument[arguments, kws, gens, false, true]
                    	    {
                    	    COMMA288=(Token)match(input,COMMA,FOLLOW_COMMA_in_arglist7523); if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) {
                    	    COMMA288_tree = (PythonTree)adaptor.create(COMMA288);
                    	    adaptor.addChild(root_0, COMMA288_tree);
                    	    }
                    	    pushFollow(FOLLOW_argument_in_arglist7525);
                    	    argument289=argument(arguments, kws, gens, false, true);

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) adaptor.addChild(root_0, argument289.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop147;
                        }
                    } while (true);

                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1984:95: ( COMMA DOUBLESTAR k= test[expr_contextType.Load] )?
                    int alt148=2;
                    int LA148_0 = input.LA(1);

                    if ( (LA148_0==COMMA) ) {
                        alt148=1;
                    }
                    switch (alt148) {
                        case 1 :
                            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1984:96: COMMA DOUBLESTAR k= test[expr_contextType.Load]
                            {
                            COMMA290=(Token)match(input,COMMA,FOLLOW_COMMA_in_arglist7531); if (state.failed) return retval;
                            if ( state.backtracking==0 ) {
                            COMMA290_tree = (PythonTree)adaptor.create(COMMA290);
                            adaptor.addChild(root_0, COMMA290_tree);
                            }
                            DOUBLESTAR291=(Token)match(input,DOUBLESTAR,FOLLOW_DOUBLESTAR_in_arglist7533); if (state.failed) return retval;
                            if ( state.backtracking==0 ) {
                            DOUBLESTAR291_tree = (PythonTree)adaptor.create(DOUBLESTAR291);
                            adaptor.addChild(root_0, DOUBLESTAR291_tree);
                            }
                            pushFollow(FOLLOW_test_in_arglist7537);
                            k=test(expr_contextType.Load);

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, k.getTree());

                            }
                            break;

                    }

                    if ( state.backtracking==0 ) {

                                retval.starargs =actions.castExpr((s!=null?((PythonTree)s.tree):null));
                                retval.keywords =kws;
                                retval.kwargs =actions.castExpr((k!=null?((PythonTree)k.tree):null));
                            
                    }

                    }
                    break;
                case 3 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1990:7: DOUBLESTAR k= test[expr_contextType.Load]
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    DOUBLESTAR292=(Token)match(input,DOUBLESTAR,FOLLOW_DOUBLESTAR_in_arglist7556); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    DOUBLESTAR292_tree = (PythonTree)adaptor.create(DOUBLESTAR292);
                    adaptor.addChild(root_0, DOUBLESTAR292_tree);
                    }
                    pushFollow(FOLLOW_test_in_arglist7560);
                    k=test(expr_contextType.Load);

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, k.getTree());
                    if ( state.backtracking==0 ) {

                                retval.kwargs =actions.castExpr((k!=null?((PythonTree)k.tree):null));
                            
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException re) {
            errorHandler.reportError(this, re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "arglist"

    public static class argument_return extends ParserRuleReturnScope {
        public boolean genarg;
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "argument"
    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1997:1: argument[List arguments, List kws, List gens, boolean first, boolean afterStar] returns [boolean genarg] : t1= test[expr_contextType.Load] ( ( ASSIGN t2= test[expr_contextType.Load] ) | gen_for[$gens] | ) ;
    public final PythonParser.argument_return argument(List arguments, List kws, List gens, boolean first, boolean afterStar) throws RecognitionException {
        PythonParser.argument_return retval = new PythonParser.argument_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token ASSIGN293=null;
        PythonParser.test_return t1 = null;

        PythonParser.test_return t2 = null;

        PythonParser.gen_for_return gen_for294 = null;


        PythonTree ASSIGN293_tree=null;

        try {
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1999:5: (t1= test[expr_contextType.Load] ( ( ASSIGN t2= test[expr_contextType.Load] ) | gen_for[$gens] | ) )
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1999:7: t1= test[expr_contextType.Load] ( ( ASSIGN t2= test[expr_contextType.Load] ) | gen_for[$gens] | )
            {
            root_0 = (PythonTree)adaptor.nil();

            pushFollow(FOLLOW_test_in_argument7599);
            t1=test(expr_contextType.Load);

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, t1.getTree());
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:2000:9: ( ( ASSIGN t2= test[expr_contextType.Load] ) | gen_for[$gens] | )
            int alt150=3;
            switch ( input.LA(1) ) {
            case ASSIGN:
                {
                alt150=1;
                }
                break;
            case FOR:
                {
                alt150=2;
                }
                break;
            case RPAREN:
            case COMMA:
                {
                alt150=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 150, 0, input);

                throw nvae;
            }

            switch (alt150) {
                case 1 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:2000:10: ( ASSIGN t2= test[expr_contextType.Load] )
                    {
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:2000:10: ( ASSIGN t2= test[expr_contextType.Load] )
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:2000:11: ASSIGN t2= test[expr_contextType.Load]
                    {
                    ASSIGN293=(Token)match(input,ASSIGN,FOLLOW_ASSIGN_in_argument7612); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    ASSIGN293_tree = (PythonTree)adaptor.create(ASSIGN293);
                    adaptor.addChild(root_0, ASSIGN293_tree);
                    }
                    pushFollow(FOLLOW_test_in_argument7616);
                    t2=test(expr_contextType.Load);

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, t2.getTree());

                    }

                    if ( state.backtracking==0 ) {

                                    expr newkey = actions.castExpr((t1!=null?((PythonTree)t1.tree):null));
                                    //Loop through all current keys and fail on duplicate.
                                    for(Object o: kws) {
                                        List list = (List)o;
                                        Object oldkey = list.get(0);
                                        if (oldkey instanceof Name && newkey instanceof Name) {
                                            if (((Name)oldkey).getId().equals(((Name)newkey).getId())) {
                                                errorHandler.error("keyword arguments repeated", (t1!=null?((PythonTree)t1.tree):null));
                                            }
                                        }
                                    }
                                    List<expr> exprs = new ArrayList<expr>();
                                    exprs.add(newkey);
                                    exprs.add(actions.castExpr((t2!=null?((PythonTree)t2.tree):null)));
                                    kws.add(exprs);
                                
                    }

                    }
                    break;
                case 2 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:2018:11: gen_for[$gens]
                    {
                    pushFollow(FOLLOW_gen_for_in_argument7642);
                    gen_for294=gen_for(gens);

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, gen_for294.getTree());
                    if ( state.backtracking==0 ) {

                                    if (!first) {
                                        actions.errorGenExpNotSoleArg((gen_for294!=null?((PythonTree)gen_for294.tree):null));
                                    }
                                    retval.genarg = true;
                                    Collections.reverse(gens);
                                    List<comprehension> c = gens;
                                    arguments.add(new GeneratorExp((t1!=null?((Token)t1.start):null), actions.castExpr((t1!=null?((PythonTree)t1.tree):null)), c));
                                
                    }

                    }
                    break;
                case 3 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:2029:11: 
                    {
                    if ( state.backtracking==0 ) {

                                    if (kws.size() > 0) {
                                        errorHandler.error("non-keyword arg after keyword arg", (t1!=null?((PythonTree)t1.tree):null));
                                    } else if (afterStar) {
                                        errorHandler.error("only named arguments may follow *expression", (t1!=null?((PythonTree)t1.tree):null));
                                    }
                                    arguments.add((t1!=null?((PythonTree)t1.tree):null));
                                
                    }

                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException re) {
            errorHandler.reportError(this, re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "argument"

    public static class list_iter_return extends ParserRuleReturnScope {
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "list_iter"
    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:2041:1: list_iter[List gens, List ifs] : ( list_for[gens] | list_if[gens, ifs] );
    public final PythonParser.list_iter_return list_iter(List gens, List ifs) throws RecognitionException {
        PythonParser.list_iter_return retval = new PythonParser.list_iter_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        PythonParser.list_for_return list_for295 = null;

        PythonParser.list_if_return list_if296 = null;



        try {
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:2042:5: ( list_for[gens] | list_if[gens, ifs] )
            int alt151=2;
            int LA151_0 = input.LA(1);

            if ( (LA151_0==FOR) ) {
                alt151=1;
            }
            else if ( (LA151_0==IF) ) {
                alt151=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 151, 0, input);

                throw nvae;
            }
            switch (alt151) {
                case 1 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:2042:7: list_for[gens]
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    pushFollow(FOLLOW_list_for_in_list_iter7707);
                    list_for295=list_for(gens);

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, list_for295.getTree());

                    }
                    break;
                case 2 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:2043:7: list_if[gens, ifs]
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    pushFollow(FOLLOW_list_if_in_list_iter7716);
                    list_if296=list_if(gens, ifs);

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, list_if296.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException re) {
            errorHandler.reportError(this, re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "list_iter"

    public static class list_for_return extends ParserRuleReturnScope {
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "list_for"
    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:2047:1: list_for[List gens] : FOR exprlist[expr_contextType.Store] IN testlist[expr_contextType.Load] ( list_iter[gens, ifs] )? ;
    public final PythonParser.list_for_return list_for(List gens) throws RecognitionException {
        PythonParser.list_for_return retval = new PythonParser.list_for_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token FOR297=null;
        Token IN299=null;
        PythonParser.exprlist_return exprlist298 = null;

        PythonParser.testlist_return testlist300 = null;

        PythonParser.list_iter_return list_iter301 = null;


        PythonTree FOR297_tree=null;
        PythonTree IN299_tree=null;


            List ifs = new ArrayList();

        try {
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:2051:5: ( FOR exprlist[expr_contextType.Store] IN testlist[expr_contextType.Load] ( list_iter[gens, ifs] )? )
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:2051:7: FOR exprlist[expr_contextType.Store] IN testlist[expr_contextType.Load] ( list_iter[gens, ifs] )?
            {
            root_0 = (PythonTree)adaptor.nil();

            FOR297=(Token)match(input,FOR,FOLLOW_FOR_in_list_for7742); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            FOR297_tree = (PythonTree)adaptor.create(FOR297);
            adaptor.addChild(root_0, FOR297_tree);
            }
            pushFollow(FOLLOW_exprlist_in_list_for7744);
            exprlist298=exprlist(expr_contextType.Store);

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, exprlist298.getTree());
            IN299=(Token)match(input,IN,FOLLOW_IN_in_list_for7747); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            IN299_tree = (PythonTree)adaptor.create(IN299);
            adaptor.addChild(root_0, IN299_tree);
            }
            pushFollow(FOLLOW_testlist_in_list_for7749);
            testlist300=testlist(expr_contextType.Load);

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, testlist300.getTree());
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:2051:79: ( list_iter[gens, ifs] )?
            int alt152=2;
            int LA152_0 = input.LA(1);

            if ( (LA152_0==FOR||LA152_0==IF) ) {
                alt152=1;
            }
            switch (alt152) {
                case 1 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:2051:80: list_iter[gens, ifs]
                    {
                    pushFollow(FOLLOW_list_iter_in_list_for7753);
                    list_iter301=list_iter(gens, ifs);

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, list_iter301.getTree());

                    }
                    break;

            }

            if ( state.backtracking==0 ) {

                        Collections.reverse(ifs);
                        gens.add(new comprehension(FOR297, (exprlist298!=null?exprlist298.etype:null), actions.castExpr((testlist300!=null?((PythonTree)testlist300.tree):null)), ifs));
                    
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException re) {
            errorHandler.reportError(this, re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "list_for"

    public static class list_if_return extends ParserRuleReturnScope {
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "list_if"
    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:2059:1: list_if[List gens, List ifs] : IF test[expr_contextType.Load] ( list_iter[gens, ifs] )? ;
    public final PythonParser.list_if_return list_if(List gens, List ifs) throws RecognitionException {
        PythonParser.list_if_return retval = new PythonParser.list_if_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token IF302=null;
        PythonParser.test_return test303 = null;

        PythonParser.list_iter_return list_iter304 = null;


        PythonTree IF302_tree=null;

        try {
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:2060:5: ( IF test[expr_contextType.Load] ( list_iter[gens, ifs] )? )
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:2060:7: IF test[expr_contextType.Load] ( list_iter[gens, ifs] )?
            {
            root_0 = (PythonTree)adaptor.nil();

            IF302=(Token)match(input,IF,FOLLOW_IF_in_list_if7783); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            IF302_tree = (PythonTree)adaptor.create(IF302);
            adaptor.addChild(root_0, IF302_tree);
            }
            pushFollow(FOLLOW_test_in_list_if7785);
            test303=test(expr_contextType.Load);

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, test303.getTree());
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:2060:38: ( list_iter[gens, ifs] )?
            int alt153=2;
            int LA153_0 = input.LA(1);

            if ( (LA153_0==FOR||LA153_0==IF) ) {
                alt153=1;
            }
            switch (alt153) {
                case 1 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:2060:39: list_iter[gens, ifs]
                    {
                    pushFollow(FOLLOW_list_iter_in_list_if7789);
                    list_iter304=list_iter(gens, ifs);

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, list_iter304.getTree());

                    }
                    break;

            }

            if ( state.backtracking==0 ) {

                      ifs.add(actions.castExpr((test303!=null?((PythonTree)test303.tree):null)));
                    
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException re) {
            errorHandler.reportError(this, re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "list_if"

    public static class gen_iter_return extends ParserRuleReturnScope {
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "gen_iter"
    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:2067:1: gen_iter[List gens, List ifs] : ( gen_for[gens] | gen_if[gens, ifs] );
    public final PythonParser.gen_iter_return gen_iter(List gens, List ifs) throws RecognitionException {
        PythonParser.gen_iter_return retval = new PythonParser.gen_iter_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        PythonParser.gen_for_return gen_for305 = null;

        PythonParser.gen_if_return gen_if306 = null;



        try {
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:2068:5: ( gen_for[gens] | gen_if[gens, ifs] )
            int alt154=2;
            int LA154_0 = input.LA(1);

            if ( (LA154_0==FOR) ) {
                alt154=1;
            }
            else if ( (LA154_0==IF) ) {
                alt154=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 154, 0, input);

                throw nvae;
            }
            switch (alt154) {
                case 1 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:2068:7: gen_for[gens]
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    pushFollow(FOLLOW_gen_for_in_gen_iter7820);
                    gen_for305=gen_for(gens);

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, gen_for305.getTree());

                    }
                    break;
                case 2 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:2069:7: gen_if[gens, ifs]
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    pushFollow(FOLLOW_gen_if_in_gen_iter7829);
                    gen_if306=gen_if(gens, ifs);

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, gen_if306.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException re) {
            errorHandler.reportError(this, re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "gen_iter"

    public static class gen_for_return extends ParserRuleReturnScope {
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "gen_for"
    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:2073:1: gen_for[List gens] : FOR exprlist[expr_contextType.Store] IN or_test[expr_contextType.Load] ( gen_iter[gens, ifs] )? ;
    public final PythonParser.gen_for_return gen_for(List gens) throws RecognitionException {
        PythonParser.gen_for_return retval = new PythonParser.gen_for_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token FOR307=null;
        Token IN309=null;
        PythonParser.exprlist_return exprlist308 = null;

        PythonParser.or_test_return or_test310 = null;

        PythonParser.gen_iter_return gen_iter311 = null;


        PythonTree FOR307_tree=null;
        PythonTree IN309_tree=null;


            List ifs = new ArrayList();

        try {
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:2077:5: ( FOR exprlist[expr_contextType.Store] IN or_test[expr_contextType.Load] ( gen_iter[gens, ifs] )? )
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:2077:7: FOR exprlist[expr_contextType.Store] IN or_test[expr_contextType.Load] ( gen_iter[gens, ifs] )?
            {
            root_0 = (PythonTree)adaptor.nil();

            FOR307=(Token)match(input,FOR,FOLLOW_FOR_in_gen_for7855); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            FOR307_tree = (PythonTree)adaptor.create(FOR307);
            adaptor.addChild(root_0, FOR307_tree);
            }
            pushFollow(FOLLOW_exprlist_in_gen_for7857);
            exprlist308=exprlist(expr_contextType.Store);

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, exprlist308.getTree());
            IN309=(Token)match(input,IN,FOLLOW_IN_in_gen_for7860); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            IN309_tree = (PythonTree)adaptor.create(IN309);
            adaptor.addChild(root_0, IN309_tree);
            }
            pushFollow(FOLLOW_or_test_in_gen_for7862);
            or_test310=or_test(expr_contextType.Load);

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, or_test310.getTree());
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:2077:78: ( gen_iter[gens, ifs] )?
            int alt155=2;
            int LA155_0 = input.LA(1);

            if ( (LA155_0==FOR||LA155_0==IF) ) {
                alt155=1;
            }
            switch (alt155) {
                case 1 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:2077:78: gen_iter[gens, ifs]
                    {
                    pushFollow(FOLLOW_gen_iter_in_gen_for7865);
                    gen_iter311=gen_iter(gens, ifs);

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, gen_iter311.getTree());

                    }
                    break;

            }

            if ( state.backtracking==0 ) {

                        Collections.reverse(ifs);
                        gens.add(new comprehension(FOR307, (exprlist308!=null?exprlist308.etype:null), actions.castExpr((or_test310!=null?((PythonTree)or_test310.tree):null)), ifs));
                    
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException re) {
            errorHandler.reportError(this, re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "gen_for"

    public static class gen_if_return extends ParserRuleReturnScope {
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "gen_if"
    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:2085:1: gen_if[List gens, List ifs] : IF test[expr_contextType.Load] ( gen_iter[gens, ifs] )? ;
    public final PythonParser.gen_if_return gen_if(List gens, List ifs) throws RecognitionException {
        PythonParser.gen_if_return retval = new PythonParser.gen_if_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token IF312=null;
        PythonParser.test_return test313 = null;

        PythonParser.gen_iter_return gen_iter314 = null;


        PythonTree IF312_tree=null;

        try {
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:2086:5: ( IF test[expr_contextType.Load] ( gen_iter[gens, ifs] )? )
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:2086:7: IF test[expr_contextType.Load] ( gen_iter[gens, ifs] )?
            {
            root_0 = (PythonTree)adaptor.nil();

            IF312=(Token)match(input,IF,FOLLOW_IF_in_gen_if7894); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            IF312_tree = (PythonTree)adaptor.create(IF312);
            adaptor.addChild(root_0, IF312_tree);
            }
            pushFollow(FOLLOW_test_in_gen_if7896);
            test313=test(expr_contextType.Load);

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, test313.getTree());
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:2086:38: ( gen_iter[gens, ifs] )?
            int alt156=2;
            int LA156_0 = input.LA(1);

            if ( (LA156_0==FOR||LA156_0==IF) ) {
                alt156=1;
            }
            switch (alt156) {
                case 1 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:2086:38: gen_iter[gens, ifs]
                    {
                    pushFollow(FOLLOW_gen_iter_in_gen_if7899);
                    gen_iter314=gen_iter(gens, ifs);

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, gen_iter314.getTree());

                    }
                    break;

            }

            if ( state.backtracking==0 ) {

                      ifs.add(actions.castExpr((test313!=null?((PythonTree)test313.tree):null)));
                    
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException re) {
            errorHandler.reportError(this, re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "gen_if"

    public static class yield_expr_return extends ParserRuleReturnScope {
        public expr etype;
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "yield_expr"
    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:2093:1: yield_expr returns [expr etype] : YIELD ( testlist[expr_contextType.Load] )? ;
    public final PythonParser.yield_expr_return yield_expr() throws RecognitionException {
        PythonParser.yield_expr_return retval = new PythonParser.yield_expr_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token YIELD315=null;
        PythonParser.testlist_return testlist316 = null;


        PythonTree YIELD315_tree=null;

        try {
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:2099:5: ( YIELD ( testlist[expr_contextType.Load] )? )
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:2099:7: YIELD ( testlist[expr_contextType.Load] )?
            {
            root_0 = (PythonTree)adaptor.nil();

            YIELD315=(Token)match(input,YIELD,FOLLOW_YIELD_in_yield_expr7940); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            YIELD315_tree = (PythonTree)adaptor.create(YIELD315);
            adaptor.addChild(root_0, YIELD315_tree);
            }
            // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:2099:13: ( testlist[expr_contextType.Load] )?
            int alt157=2;
            int LA157_0 = input.LA(1);

            if ( (LA157_0==NAME||(LA157_0>=LAMBDA && LA157_0<=NOT)||LA157_0==LPAREN||(LA157_0>=PLUS && LA157_0<=MINUS)||(LA157_0>=TILDE && LA157_0<=LBRACK)||LA157_0==LCURLY||(LA157_0>=BACKQUOTE && LA157_0<=STRING)) ) {
                alt157=1;
            }
            switch (alt157) {
                case 1 :
                    // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:2099:13: testlist[expr_contextType.Load]
                    {
                    pushFollow(FOLLOW_testlist_in_yield_expr7942);
                    testlist316=testlist(expr_contextType.Load);

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, testlist316.getTree());

                    }
                    break;

            }

            if ( state.backtracking==0 ) {

                        retval.etype = new Yield(YIELD315, actions.castExpr((testlist316!=null?((PythonTree)testlist316.tree):null)));
                    
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {

                  //needed for y2+=yield_expr
                  retval.tree = retval.etype;

            }
        }

        catch (RecognitionException re) {
            errorHandler.reportError(this, re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "yield_expr"

    // $ANTLR start synpred1_Python
    public final void synpred1_Python_fragment() throws RecognitionException {   
        // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:544:7: ( LPAREN fpdef[null] COMMA )
        // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:544:8: LPAREN fpdef[null] COMMA
        {
        match(input,LPAREN,FOLLOW_LPAREN_in_synpred1_Python1252); if (state.failed) return ;
        pushFollow(FOLLOW_fpdef_in_synpred1_Python1254);
        fpdef(null);

        state._fsp--;
        if (state.failed) return ;
        match(input,COMMA,FOLLOW_COMMA_in_synpred1_Python1257); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred1_Python

    // $ANTLR start synpred2_Python
    public final void synpred2_Python_fragment() throws RecognitionException {   
        // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:608:8: ( testlist[null] augassign )
        // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:608:9: testlist[null] augassign
        {
        pushFollow(FOLLOW_testlist_in_synpred2_Python1635);
        testlist(null);

        state._fsp--;
        if (state.failed) return ;
        pushFollow(FOLLOW_augassign_in_synpred2_Python1638);
        augassign();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred2_Python

    // $ANTLR start synpred3_Python
    public final void synpred3_Python_fragment() throws RecognitionException {   
        // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:622:7: ( testlist[null] ASSIGN )
        // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:622:8: testlist[null] ASSIGN
        {
        pushFollow(FOLLOW_testlist_in_synpred3_Python1754);
        testlist(null);

        state._fsp--;
        if (state.failed) return ;
        match(input,ASSIGN,FOLLOW_ASSIGN_in_synpred3_Python1757); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred3_Python

    // $ANTLR start synpred4_Python
    public final void synpred4_Python_fragment() throws RecognitionException {   
        // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:727:7: ( test[null] COMMA )
        // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:727:8: test[null] COMMA
        {
        pushFollow(FOLLOW_test_in_synpred4_Python2269);
        test(null);

        state._fsp--;
        if (state.failed) return ;
        match(input,COMMA,FOLLOW_COMMA_in_synpred4_Python2272); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred4_Python

    // $ANTLR start synpred5_Python
    public final void synpred5_Python_fragment() throws RecognitionException {   
        // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:748:7: ( test[null] COMMA test[null] )
        // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:748:8: test[null] COMMA test[null]
        {
        pushFollow(FOLLOW_test_in_synpred5_Python2368);
        test(null);

        state._fsp--;
        if (state.failed) return ;
        match(input,COMMA,FOLLOW_COMMA_in_synpred5_Python2371); if (state.failed) return ;
        pushFollow(FOLLOW_test_in_synpred5_Python2373);
        test(null);

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred5_Python

    // $ANTLR start synpred6_Python
    public final void synpred6_Python_fragment() throws RecognitionException {   
        // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1033:7: ( ( decorators )? DEF )
        // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1033:8: ( decorators )? DEF
        {
        // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1033:8: ( decorators )?
        int alt158=2;
        int LA158_0 = input.LA(1);

        if ( (LA158_0==AT) ) {
            alt158=1;
        }
        switch (alt158) {
            case 1 :
                // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1033:8: decorators
                {
                pushFollow(FOLLOW_decorators_in_synpred6_Python3462);
                decorators();

                state._fsp--;
                if (state.failed) return ;

                }
                break;

        }

        match(input,DEF,FOLLOW_DEF_in_synpred6_Python3465); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred6_Python

    // $ANTLR start synpred7_Python
    public final void synpred7_Python_fragment() throws RecognitionException {   
        // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1220:9: ( IF or_test[null] ORELSE )
        // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1220:10: IF or_test[null] ORELSE
        {
        match(input,IF,FOLLOW_IF_in_synpred7_Python4208); if (state.failed) return ;
        pushFollow(FOLLOW_or_test_in_synpred7_Python4210);
        or_test(null);

        state._fsp--;
        if (state.failed) return ;
        match(input,ORELSE,FOLLOW_ORELSE_in_synpred7_Python4213); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred7_Python

    // $ANTLR start synpred8_Python
    public final void synpred8_Python_fragment() throws RecognitionException {   
        // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1849:7: ( test[null] COLON )
        // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1849:8: test[null] COLON
        {
        pushFollow(FOLLOW_test_in_synpred8_Python6801);
        test(null);

        state._fsp--;
        if (state.failed) return ;
        match(input,COLON,FOLLOW_COLON_in_synpred8_Python6804); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred8_Python

    // $ANTLR start synpred9_Python
    public final void synpred9_Python_fragment() throws RecognitionException {   
        // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1854:7: ( COLON )
        // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1854:8: COLON
        {
        match(input,COLON,FOLLOW_COLON_in_synpred9_Python6852); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred9_Python

    // $ANTLR start synpred10_Python
    public final void synpred10_Python_fragment() throws RecognitionException {   
        // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1888:7: ( expr[null] COMMA )
        // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1888:8: expr[null] COMMA
        {
        pushFollow(FOLLOW_expr_in_synpred10_Python6997);
        expr(null);

        state._fsp--;
        if (state.failed) return ;
        match(input,COMMA,FOLLOW_COMMA_in_synpred10_Python7000); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred10_Python

    // $ANTLR start synpred11_Python
    public final void synpred11_Python_fragment() throws RecognitionException {   
        // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1918:7: ( test[null] COMMA )
        // /home/alson/tmp/full_build/work/checkout/jython/grammar/Python.g:1918:8: test[null] COMMA
        {
        pushFollow(FOLLOW_test_in_synpred11_Python7148);
        test(null);

        state._fsp--;
        if (state.failed) return ;
        match(input,COMMA,FOLLOW_COMMA_in_synpred11_Python7151); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred11_Python

    // Delegated rules

    public final boolean synpred5_Python() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred5_Python_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred7_Python() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred7_Python_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred11_Python() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred11_Python_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred4_Python() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred4_Python_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred3_Python() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred3_Python_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred1_Python() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred1_Python_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred10_Python() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred10_Python_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred9_Python() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred9_Python_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred6_Python() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred6_Python_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred2_Python() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred2_Python_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred8_Python() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred8_Python_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }


    protected DFA29 dfa29 = new DFA29(this);
    protected DFA34 dfa34 = new DFA34(this);
    protected DFA30 dfa30 = new DFA30(this);
    protected DFA39 dfa39 = new DFA39(this);
    protected DFA37 dfa37 = new DFA37(this);
    protected DFA42 dfa42 = new DFA42(this);
    protected DFA40 dfa40 = new DFA40(this);
    protected DFA51 dfa51 = new DFA51(this);
    protected DFA78 dfa78 = new DFA78(this);
    protected DFA87 dfa87 = new DFA87(this);
    protected DFA114 dfa114 = new DFA114(this);
    protected DFA127 dfa127 = new DFA127(this);
    protected DFA131 dfa131 = new DFA131(this);
    protected DFA129 dfa129 = new DFA129(this);
    protected DFA132 dfa132 = new DFA132(this);
    protected DFA136 dfa136 = new DFA136(this);
    protected DFA134 dfa134 = new DFA134(this);
    protected DFA137 dfa137 = new DFA137(this);
    static final String DFA29_eotS =
        "\12\uffff";
    static final String DFA29_eofS =
        "\12\uffff";
    static final String DFA29_minS =
        "\1\11\11\uffff";
    static final String DFA29_maxS =
        "\1\132\11\uffff";
    static final String DFA29_acceptS =
        "\1\uffff\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11";
    static final String DFA29_specialS =
        "\12\uffff}>";
    static final String[] DFA29_transitionS = {
            "\1\1\3\uffff\1\11\1\5\1\uffff\1\5\1\uffff\1\3\2\uffff\1\10\1"+
            "\uffff\1\6\1\uffff\1\7\1\uffff\1\6\2\uffff\2\1\2\uffff\1\4\1"+
            "\2\2\5\3\uffff\1\5\1\uffff\1\1\37\uffff\2\1\3\uffff\2\1\1\uffff"+
            "\1\1\1\uffff\6\1",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA29_eot = DFA.unpackEncodedString(DFA29_eotS);
    static final short[] DFA29_eof = DFA.unpackEncodedString(DFA29_eofS);
    static final char[] DFA29_min = DFA.unpackEncodedStringToUnsignedChars(DFA29_minS);
    static final char[] DFA29_max = DFA.unpackEncodedStringToUnsignedChars(DFA29_maxS);
    static final short[] DFA29_accept = DFA.unpackEncodedString(DFA29_acceptS);
    static final short[] DFA29_special = DFA.unpackEncodedString(DFA29_specialS);
    static final short[][] DFA29_transition;

    static {
        int numStates = DFA29_transitionS.length;
        DFA29_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA29_transition[i] = DFA.unpackEncodedString(DFA29_transitionS[i]);
        }
    }

    class DFA29 extends DFA {

        public DFA29(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 29;
            this.eot = DFA29_eot;
            this.eof = DFA29_eof;
            this.min = DFA29_min;
            this.max = DFA29_max;
            this.accept = DFA29_accept;
            this.special = DFA29_special;
            this.transition = DFA29_transition;
        }
        public String getDescription() {
            return "586:1: small_stmt : ( expr_stmt | print_stmt | del_stmt | pass_stmt | flow_stmt | import_stmt | global_stmt | exec_stmt | assert_stmt );";
        }
    }
    static final String DFA34_eotS =
        "\23\uffff";
    static final String DFA34_eofS =
        "\23\uffff";
    static final String DFA34_minS =
        "\1\11\17\0\3\uffff";
    static final String DFA34_maxS =
        "\1\132\17\0\3\uffff";
    static final String DFA34_acceptS =
        "\20\uffff\1\1\1\2\1\3";
    static final String DFA34_specialS =
        "\1\uffff\1\0\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13\1\14"+
        "\1\15\1\16\3\uffff}>";
    static final String[] DFA34_transitionS = {
            "\1\11\24\uffff\1\17\1\1\13\uffff\1\5\37\uffff\1\2\1\3\3\uffff"+
            "\1\4\1\6\1\uffff\1\7\1\uffff\1\10\1\12\1\13\1\14\1\15\1\16",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "",
            "",
            ""
    };

    static final short[] DFA34_eot = DFA.unpackEncodedString(DFA34_eotS);
    static final short[] DFA34_eof = DFA.unpackEncodedString(DFA34_eofS);
    static final char[] DFA34_min = DFA.unpackEncodedStringToUnsignedChars(DFA34_minS);
    static final char[] DFA34_max = DFA.unpackEncodedStringToUnsignedChars(DFA34_maxS);
    static final short[] DFA34_accept = DFA.unpackEncodedString(DFA34_acceptS);
    static final short[] DFA34_special = DFA.unpackEncodedString(DFA34_specialS);
    static final short[][] DFA34_transition;

    static {
        int numStates = DFA34_transitionS.length;
        DFA34_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA34_transition[i] = DFA.unpackEncodedString(DFA34_transitionS[i]);
        }
    }

    class DFA34 extends DFA {

        public DFA34(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 34;
            this.eot = DFA34_eot;
            this.eof = DFA34_eof;
            this.min = DFA34_min;
            this.max = DFA34_max;
            this.accept = DFA34_accept;
            this.special = DFA34_special;
            this.transition = DFA34_transition;
        }
        public String getDescription() {
            return "608:7: ( ( testlist[null] augassign )=>lhs= testlist[expr_contextType.AugStore] ( (aay= augassign y1= yield_expr ) | (aat= augassign rhs= testlist[expr_contextType.Load] ) ) | ( testlist[null] ASSIGN )=>lhs= testlist[expr_contextType.Store] ( | ( (at= ASSIGN t+= testlist[expr_contextType.Store] )+ ) | ( (ay= ASSIGN y2+= yield_expr )+ ) ) | lhs= testlist[expr_contextType.Load] )";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA34_1 = input.LA(1);

                         
                        int index34_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred2_Python()) ) {s = 16;}

                        else if ( (synpred3_Python()) ) {s = 17;}

                        else if ( (true) ) {s = 18;}

                         
                        input.seek(index34_1);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA34_2 = input.LA(1);

                         
                        int index34_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred2_Python()) ) {s = 16;}

                        else if ( (synpred3_Python()) ) {s = 17;}

                        else if ( (true) ) {s = 18;}

                         
                        input.seek(index34_2);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA34_3 = input.LA(1);

                         
                        int index34_3 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred2_Python()) ) {s = 16;}

                        else if ( (synpred3_Python()) ) {s = 17;}

                        else if ( (true) ) {s = 18;}

                         
                        input.seek(index34_3);
                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA34_4 = input.LA(1);

                         
                        int index34_4 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred2_Python()) ) {s = 16;}

                        else if ( (synpred3_Python()) ) {s = 17;}

                        else if ( (true) ) {s = 18;}

                         
                        input.seek(index34_4);
                        if ( s>=0 ) return s;
                        break;
                    case 4 : 
                        int LA34_5 = input.LA(1);

                         
                        int index34_5 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred2_Python()) ) {s = 16;}

                        else if ( (synpred3_Python()) ) {s = 17;}

                        else if ( (true) ) {s = 18;}

                         
                        input.seek(index34_5);
                        if ( s>=0 ) return s;
                        break;
                    case 5 : 
                        int LA34_6 = input.LA(1);

                         
                        int index34_6 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred2_Python()) ) {s = 16;}

                        else if ( (synpred3_Python()) ) {s = 17;}

                        else if ( (true) ) {s = 18;}

                         
                        input.seek(index34_6);
                        if ( s>=0 ) return s;
                        break;
                    case 6 : 
                        int LA34_7 = input.LA(1);

                         
                        int index34_7 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred2_Python()) ) {s = 16;}

                        else if ( (synpred3_Python()) ) {s = 17;}

                        else if ( (true) ) {s = 18;}

                         
                        input.seek(index34_7);
                        if ( s>=0 ) return s;
                        break;
                    case 7 : 
                        int LA34_8 = input.LA(1);

                         
                        int index34_8 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred2_Python()) ) {s = 16;}

                        else if ( (synpred3_Python()) ) {s = 17;}

                        else if ( (true) ) {s = 18;}

                         
                        input.seek(index34_8);
                        if ( s>=0 ) return s;
                        break;
                    case 8 : 
                        int LA34_9 = input.LA(1);

                         
                        int index34_9 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred2_Python()) ) {s = 16;}

                        else if ( (synpred3_Python()) ) {s = 17;}

                        else if ( (true) ) {s = 18;}

                         
                        input.seek(index34_9);
                        if ( s>=0 ) return s;
                        break;
                    case 9 : 
                        int LA34_10 = input.LA(1);

                         
                        int index34_10 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred2_Python()) ) {s = 16;}

                        else if ( (synpred3_Python()) ) {s = 17;}

                        else if ( (true) ) {s = 18;}

                         
                        input.seek(index34_10);
                        if ( s>=0 ) return s;
                        break;
                    case 10 : 
                        int LA34_11 = input.LA(1);

                         
                        int index34_11 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred2_Python()) ) {s = 16;}

                        else if ( (synpred3_Python()) ) {s = 17;}

                        else if ( (true) ) {s = 18;}

                         
                        input.seek(index34_11);
                        if ( s>=0 ) return s;
                        break;
                    case 11 : 
                        int LA34_12 = input.LA(1);

                         
                        int index34_12 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred2_Python()) ) {s = 16;}

                        else if ( (synpred3_Python()) ) {s = 17;}

                        else if ( (true) ) {s = 18;}

                         
                        input.seek(index34_12);
                        if ( s>=0 ) return s;
                        break;
                    case 12 : 
                        int LA34_13 = input.LA(1);

                         
                        int index34_13 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred2_Python()) ) {s = 16;}

                        else if ( (synpred3_Python()) ) {s = 17;}

                        else if ( (true) ) {s = 18;}

                         
                        input.seek(index34_13);
                        if ( s>=0 ) return s;
                        break;
                    case 13 : 
                        int LA34_14 = input.LA(1);

                         
                        int index34_14 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred2_Python()) ) {s = 16;}

                        else if ( (synpred3_Python()) ) {s = 17;}

                        else if ( (true) ) {s = 18;}

                         
                        input.seek(index34_14);
                        if ( s>=0 ) return s;
                        break;
                    case 14 : 
                        int LA34_15 = input.LA(1);

                         
                        int index34_15 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred2_Python()) ) {s = 16;}

                        else if ( (synpred3_Python()) ) {s = 17;}

                        else if ( (true) ) {s = 18;}

                         
                        input.seek(index34_15);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 34, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA30_eotS =
        "\17\uffff";
    static final String DFA30_eofS =
        "\17\uffff";
    static final String DFA30_minS =
        "\1\63\14\11\2\uffff";
    static final String DFA30_maxS =
        "\1\76\14\132\2\uffff";
    static final String DFA30_acceptS =
        "\15\uffff\1\2\1\1";
    static final String DFA30_specialS =
        "\17\uffff}>";
    static final String[] DFA30_transitionS = {
            "\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13\1\14",
            "\1\15\24\uffff\2\15\11\uffff\1\16\1\uffff\1\15\37\uffff\2\15"+
            "\3\uffff\2\15\1\uffff\1\15\1\uffff\6\15",
            "\1\15\24\uffff\2\15\11\uffff\1\16\1\uffff\1\15\37\uffff\2\15"+
            "\3\uffff\2\15\1\uffff\1\15\1\uffff\6\15",
            "\1\15\24\uffff\2\15\11\uffff\1\16\1\uffff\1\15\37\uffff\2\15"+
            "\3\uffff\2\15\1\uffff\1\15\1\uffff\6\15",
            "\1\15\24\uffff\2\15\11\uffff\1\16\1\uffff\1\15\37\uffff\2\15"+
            "\3\uffff\2\15\1\uffff\1\15\1\uffff\6\15",
            "\1\15\24\uffff\2\15\11\uffff\1\16\1\uffff\1\15\37\uffff\2\15"+
            "\3\uffff\2\15\1\uffff\1\15\1\uffff\6\15",
            "\1\15\24\uffff\2\15\11\uffff\1\16\1\uffff\1\15\37\uffff\2\15"+
            "\3\uffff\2\15\1\uffff\1\15\1\uffff\6\15",
            "\1\15\24\uffff\2\15\11\uffff\1\16\1\uffff\1\15\37\uffff\2\15"+
            "\3\uffff\2\15\1\uffff\1\15\1\uffff\6\15",
            "\1\15\24\uffff\2\15\11\uffff\1\16\1\uffff\1\15\37\uffff\2\15"+
            "\3\uffff\2\15\1\uffff\1\15\1\uffff\6\15",
            "\1\15\24\uffff\2\15\11\uffff\1\16\1\uffff\1\15\37\uffff\2\15"+
            "\3\uffff\2\15\1\uffff\1\15\1\uffff\6\15",
            "\1\15\24\uffff\2\15\11\uffff\1\16\1\uffff\1\15\37\uffff\2\15"+
            "\3\uffff\2\15\1\uffff\1\15\1\uffff\6\15",
            "\1\15\24\uffff\2\15\11\uffff\1\16\1\uffff\1\15\37\uffff\2\15"+
            "\3\uffff\2\15\1\uffff\1\15\1\uffff\6\15",
            "\1\15\24\uffff\2\15\11\uffff\1\16\1\uffff\1\15\37\uffff\2\15"+
            "\3\uffff\2\15\1\uffff\1\15\1\uffff\6\15",
            "",
            ""
    };

    static final short[] DFA30_eot = DFA.unpackEncodedString(DFA30_eotS);
    static final short[] DFA30_eof = DFA.unpackEncodedString(DFA30_eofS);
    static final char[] DFA30_min = DFA.unpackEncodedStringToUnsignedChars(DFA30_minS);
    static final char[] DFA30_max = DFA.unpackEncodedStringToUnsignedChars(DFA30_maxS);
    static final short[] DFA30_accept = DFA.unpackEncodedString(DFA30_acceptS);
    static final short[] DFA30_special = DFA.unpackEncodedString(DFA30_specialS);
    static final short[][] DFA30_transition;

    static {
        int numStates = DFA30_transitionS.length;
        DFA30_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA30_transition[i] = DFA.unpackEncodedString(DFA30_transitionS[i]);
        }
    }

    class DFA30 extends DFA {

        public DFA30(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 30;
            this.eot = DFA30_eot;
            this.eof = DFA30_eof;
            this.min = DFA30_min;
            this.max = DFA30_max;
            this.accept = DFA30_accept;
            this.special = DFA30_special;
            this.transition = DFA30_transition;
        }
        public String getDescription() {
            return "609:9: ( (aay= augassign y1= yield_expr ) | (aat= augassign rhs= testlist[expr_contextType.Load] ) )";
        }
    }
    static final String DFA39_eotS =
        "\22\uffff";
    static final String DFA39_eofS =
        "\22\uffff";
    static final String DFA39_minS =
        "\1\11\17\0\2\uffff";
    static final String DFA39_maxS =
        "\1\132\17\0\2\uffff";
    static final String DFA39_acceptS =
        "\20\uffff\1\1\1\2";
    static final String DFA39_specialS =
        "\1\uffff\1\0\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13\1\14"+
        "\1\15\1\16\2\uffff}>";
    static final String[] DFA39_transitionS = {
            "\1\11\24\uffff\1\17\1\1\13\uffff\1\5\37\uffff\1\2\1\3\3\uffff"+
            "\1\4\1\6\1\uffff\1\7\1\uffff\1\10\1\12\1\13\1\14\1\15\1\16",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "",
            ""
    };

    static final short[] DFA39_eot = DFA.unpackEncodedString(DFA39_eotS);
    static final short[] DFA39_eof = DFA.unpackEncodedString(DFA39_eofS);
    static final char[] DFA39_min = DFA.unpackEncodedStringToUnsignedChars(DFA39_minS);
    static final char[] DFA39_max = DFA.unpackEncodedStringToUnsignedChars(DFA39_maxS);
    static final short[] DFA39_accept = DFA.unpackEncodedString(DFA39_acceptS);
    static final short[] DFA39_special = DFA.unpackEncodedString(DFA39_specialS);
    static final short[][] DFA39_transition;

    static {
        int numStates = DFA39_transitionS.length;
        DFA39_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA39_transition[i] = DFA.unpackEncodedString(DFA39_transitionS[i]);
        }
    }

    class DFA39 extends DFA {

        public DFA39(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 39;
            this.eot = DFA39_eot;
            this.eof = DFA39_eof;
            this.min = DFA39_min;
            this.max = DFA39_max;
            this.accept = DFA39_accept;
            this.special = DFA39_special;
            this.transition = DFA39_transition;
        }
        public String getDescription() {
            return "725:1: printlist returns [boolean newline, List elts] : ( ( test[null] COMMA )=>t+= test[expr_contextType.Load] ( options {k=2; } : COMMA t+= test[expr_contextType.Load] )* (trailcomma= COMMA )? | t+= test[expr_contextType.Load] );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA39_1 = input.LA(1);

                         
                        int index39_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred4_Python()) ) {s = 16;}

                        else if ( (true) ) {s = 17;}

                         
                        input.seek(index39_1);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA39_2 = input.LA(1);

                         
                        int index39_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred4_Python()) ) {s = 16;}

                        else if ( (true) ) {s = 17;}

                         
                        input.seek(index39_2);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA39_3 = input.LA(1);

                         
                        int index39_3 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred4_Python()) ) {s = 16;}

                        else if ( (true) ) {s = 17;}

                         
                        input.seek(index39_3);
                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA39_4 = input.LA(1);

                         
                        int index39_4 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred4_Python()) ) {s = 16;}

                        else if ( (true) ) {s = 17;}

                         
                        input.seek(index39_4);
                        if ( s>=0 ) return s;
                        break;
                    case 4 : 
                        int LA39_5 = input.LA(1);

                         
                        int index39_5 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred4_Python()) ) {s = 16;}

                        else if ( (true) ) {s = 17;}

                         
                        input.seek(index39_5);
                        if ( s>=0 ) return s;
                        break;
                    case 5 : 
                        int LA39_6 = input.LA(1);

                         
                        int index39_6 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred4_Python()) ) {s = 16;}

                        else if ( (true) ) {s = 17;}

                         
                        input.seek(index39_6);
                        if ( s>=0 ) return s;
                        break;
                    case 6 : 
                        int LA39_7 = input.LA(1);

                         
                        int index39_7 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred4_Python()) ) {s = 16;}

                        else if ( (true) ) {s = 17;}

                         
                        input.seek(index39_7);
                        if ( s>=0 ) return s;
                        break;
                    case 7 : 
                        int LA39_8 = input.LA(1);

                         
                        int index39_8 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred4_Python()) ) {s = 16;}

                        else if ( (true) ) {s = 17;}

                         
                        input.seek(index39_8);
                        if ( s>=0 ) return s;
                        break;
                    case 8 : 
                        int LA39_9 = input.LA(1);

                         
                        int index39_9 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred4_Python()) ) {s = 16;}

                        else if ( (true) ) {s = 17;}

                         
                        input.seek(index39_9);
                        if ( s>=0 ) return s;
                        break;
                    case 9 : 
                        int LA39_10 = input.LA(1);

                         
                        int index39_10 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred4_Python()) ) {s = 16;}

                        else if ( (true) ) {s = 17;}

                         
                        input.seek(index39_10);
                        if ( s>=0 ) return s;
                        break;
                    case 10 : 
                        int LA39_11 = input.LA(1);

                         
                        int index39_11 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred4_Python()) ) {s = 16;}

                        else if ( (true) ) {s = 17;}

                         
                        input.seek(index39_11);
                        if ( s>=0 ) return s;
                        break;
                    case 11 : 
                        int LA39_12 = input.LA(1);

                         
                        int index39_12 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred4_Python()) ) {s = 16;}

                        else if ( (true) ) {s = 17;}

                         
                        input.seek(index39_12);
                        if ( s>=0 ) return s;
                        break;
                    case 12 : 
                        int LA39_13 = input.LA(1);

                         
                        int index39_13 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred4_Python()) ) {s = 16;}

                        else if ( (true) ) {s = 17;}

                         
                        input.seek(index39_13);
                        if ( s>=0 ) return s;
                        break;
                    case 13 : 
                        int LA39_14 = input.LA(1);

                         
                        int index39_14 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred4_Python()) ) {s = 16;}

                        else if ( (true) ) {s = 17;}

                         
                        input.seek(index39_14);
                        if ( s>=0 ) return s;
                        break;
                    case 14 : 
                        int LA39_15 = input.LA(1);

                         
                        int index39_15 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred4_Python()) ) {s = 16;}

                        else if ( (true) ) {s = 17;}

                         
                        input.seek(index39_15);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 39, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA37_eotS =
        "\25\uffff";
    static final String DFA37_eofS =
        "\25\uffff";
    static final String DFA37_minS =
        "\2\7\23\uffff";
    static final String DFA37_maxS =
        "\1\62\1\132\23\uffff";
    static final String DFA37_acceptS =
        "\2\uffff\1\2\1\uffff\1\1\20\uffff";
    static final String DFA37_specialS =
        "\25\uffff}>";
    static final String[] DFA37_transitionS = {
            "\1\2\47\uffff\1\1\2\uffff\1\2",
            "\1\2\1\uffff\1\4\24\uffff\2\4\13\uffff\1\4\6\uffff\1\2\30\uffff"+
            "\2\4\3\uffff\2\4\1\uffff\1\4\1\uffff\6\4",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA37_eot = DFA.unpackEncodedString(DFA37_eotS);
    static final short[] DFA37_eof = DFA.unpackEncodedString(DFA37_eofS);
    static final char[] DFA37_min = DFA.unpackEncodedStringToUnsignedChars(DFA37_minS);
    static final char[] DFA37_max = DFA.unpackEncodedStringToUnsignedChars(DFA37_maxS);
    static final short[] DFA37_accept = DFA.unpackEncodedString(DFA37_acceptS);
    static final short[] DFA37_special = DFA.unpackEncodedString(DFA37_specialS);
    static final short[][] DFA37_transition;

    static {
        int numStates = DFA37_transitionS.length;
        DFA37_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA37_transition[i] = DFA.unpackEncodedString(DFA37_transitionS[i]);
        }
    }

    class DFA37 extends DFA {

        public DFA37(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 37;
            this.eot = DFA37_eot;
            this.eof = DFA37_eof;
            this.min = DFA37_min;
            this.max = DFA37_max;
            this.accept = DFA37_accept;
            this.special = DFA37_special;
            this.transition = DFA37_transition;
        }
        public String getDescription() {
            return "()* loopback of 728:39: ( options {k=2; } : COMMA t+= test[expr_contextType.Load] )*";
        }
    }
    static final String DFA42_eotS =
        "\22\uffff";
    static final String DFA42_eofS =
        "\22\uffff";
    static final String DFA42_minS =
        "\1\11\17\0\2\uffff";
    static final String DFA42_maxS =
        "\1\132\17\0\2\uffff";
    static final String DFA42_acceptS =
        "\20\uffff\1\1\1\2";
    static final String DFA42_specialS =
        "\1\uffff\1\0\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13\1\14"+
        "\1\15\1\16\2\uffff}>";
    static final String[] DFA42_transitionS = {
            "\1\11\24\uffff\1\17\1\1\13\uffff\1\5\37\uffff\1\2\1\3\3\uffff"+
            "\1\4\1\6\1\uffff\1\7\1\uffff\1\10\1\12\1\13\1\14\1\15\1\16",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "",
            ""
    };

    static final short[] DFA42_eot = DFA.unpackEncodedString(DFA42_eotS);
    static final short[] DFA42_eof = DFA.unpackEncodedString(DFA42_eofS);
    static final char[] DFA42_min = DFA.unpackEncodedStringToUnsignedChars(DFA42_minS);
    static final char[] DFA42_max = DFA.unpackEncodedStringToUnsignedChars(DFA42_maxS);
    static final short[] DFA42_accept = DFA.unpackEncodedString(DFA42_acceptS);
    static final short[] DFA42_special = DFA.unpackEncodedString(DFA42_specialS);
    static final short[][] DFA42_transition;

    static {
        int numStates = DFA42_transitionS.length;
        DFA42_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA42_transition[i] = DFA.unpackEncodedString(DFA42_transitionS[i]);
        }
    }

    class DFA42 extends DFA {

        public DFA42(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 42;
            this.eot = DFA42_eot;
            this.eof = DFA42_eof;
            this.min = DFA42_min;
            this.max = DFA42_max;
            this.accept = DFA42_accept;
            this.special = DFA42_special;
            this.transition = DFA42_transition;
        }
        public String getDescription() {
            return "746:1: printlist2 returns [boolean newline, List elts] : ( ( test[null] COMMA test[null] )=>t+= test[expr_contextType.Load] ( options {k=2; } : COMMA t+= test[expr_contextType.Load] )* (trailcomma= COMMA )? | t+= test[expr_contextType.Load] );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA42_1 = input.LA(1);

                         
                        int index42_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred5_Python()) ) {s = 16;}

                        else if ( (true) ) {s = 17;}

                         
                        input.seek(index42_1);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA42_2 = input.LA(1);

                         
                        int index42_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred5_Python()) ) {s = 16;}

                        else if ( (true) ) {s = 17;}

                         
                        input.seek(index42_2);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA42_3 = input.LA(1);

                         
                        int index42_3 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred5_Python()) ) {s = 16;}

                        else if ( (true) ) {s = 17;}

                         
                        input.seek(index42_3);
                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA42_4 = input.LA(1);

                         
                        int index42_4 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred5_Python()) ) {s = 16;}

                        else if ( (true) ) {s = 17;}

                         
                        input.seek(index42_4);
                        if ( s>=0 ) return s;
                        break;
                    case 4 : 
                        int LA42_5 = input.LA(1);

                         
                        int index42_5 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred5_Python()) ) {s = 16;}

                        else if ( (true) ) {s = 17;}

                         
                        input.seek(index42_5);
                        if ( s>=0 ) return s;
                        break;
                    case 5 : 
                        int LA42_6 = input.LA(1);

                         
                        int index42_6 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred5_Python()) ) {s = 16;}

                        else if ( (true) ) {s = 17;}

                         
                        input.seek(index42_6);
                        if ( s>=0 ) return s;
                        break;
                    case 6 : 
                        int LA42_7 = input.LA(1);

                         
                        int index42_7 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred5_Python()) ) {s = 16;}

                        else if ( (true) ) {s = 17;}

                         
                        input.seek(index42_7);
                        if ( s>=0 ) return s;
                        break;
                    case 7 : 
                        int LA42_8 = input.LA(1);

                         
                        int index42_8 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred5_Python()) ) {s = 16;}

                        else if ( (true) ) {s = 17;}

                         
                        input.seek(index42_8);
                        if ( s>=0 ) return s;
                        break;
                    case 8 : 
                        int LA42_9 = input.LA(1);

                         
                        int index42_9 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred5_Python()) ) {s = 16;}

                        else if ( (true) ) {s = 17;}

                         
                        input.seek(index42_9);
                        if ( s>=0 ) return s;
                        break;
                    case 9 : 
                        int LA42_10 = input.LA(1);

                         
                        int index42_10 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred5_Python()) ) {s = 16;}

                        else if ( (true) ) {s = 17;}

                         
                        input.seek(index42_10);
                        if ( s>=0 ) return s;
                        break;
                    case 10 : 
                        int LA42_11 = input.LA(1);

                         
                        int index42_11 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred5_Python()) ) {s = 16;}

                        else if ( (true) ) {s = 17;}

                         
                        input.seek(index42_11);
                        if ( s>=0 ) return s;
                        break;
                    case 11 : 
                        int LA42_12 = input.LA(1);

                         
                        int index42_12 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred5_Python()) ) {s = 16;}

                        else if ( (true) ) {s = 17;}

                         
                        input.seek(index42_12);
                        if ( s>=0 ) return s;
                        break;
                    case 12 : 
                        int LA42_13 = input.LA(1);

                         
                        int index42_13 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred5_Python()) ) {s = 16;}

                        else if ( (true) ) {s = 17;}

                         
                        input.seek(index42_13);
                        if ( s>=0 ) return s;
                        break;
                    case 13 : 
                        int LA42_14 = input.LA(1);

                         
                        int index42_14 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred5_Python()) ) {s = 16;}

                        else if ( (true) ) {s = 17;}

                         
                        input.seek(index42_14);
                        if ( s>=0 ) return s;
                        break;
                    case 14 : 
                        int LA42_15 = input.LA(1);

                         
                        int index42_15 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred5_Python()) ) {s = 16;}

                        else if ( (true) ) {s = 17;}

                         
                        input.seek(index42_15);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 42, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA40_eotS =
        "\25\uffff";
    static final String DFA40_eofS =
        "\25\uffff";
    static final String DFA40_minS =
        "\2\7\23\uffff";
    static final String DFA40_maxS =
        "\1\62\1\132\23\uffff";
    static final String DFA40_acceptS =
        "\2\uffff\1\2\1\uffff\1\1\20\uffff";
    static final String DFA40_specialS =
        "\25\uffff}>";
    static final String[] DFA40_transitionS = {
            "\1\2\47\uffff\1\1\2\uffff\1\2",
            "\1\2\1\uffff\1\4\24\uffff\2\4\13\uffff\1\4\6\uffff\1\2\30\uffff"+
            "\2\4\3\uffff\2\4\1\uffff\1\4\1\uffff\6\4",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA40_eot = DFA.unpackEncodedString(DFA40_eotS);
    static final short[] DFA40_eof = DFA.unpackEncodedString(DFA40_eofS);
    static final char[] DFA40_min = DFA.unpackEncodedStringToUnsignedChars(DFA40_minS);
    static final char[] DFA40_max = DFA.unpackEncodedStringToUnsignedChars(DFA40_maxS);
    static final short[] DFA40_accept = DFA.unpackEncodedString(DFA40_acceptS);
    static final short[] DFA40_special = DFA.unpackEncodedString(DFA40_specialS);
    static final short[][] DFA40_transition;

    static {
        int numStates = DFA40_transitionS.length;
        DFA40_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA40_transition[i] = DFA.unpackEncodedString(DFA40_transitionS[i]);
        }
    }

    class DFA40 extends DFA {

        public DFA40(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 40;
            this.eot = DFA40_eot;
            this.eof = DFA40_eof;
            this.min = DFA40_min;
            this.max = DFA40_max;
            this.accept = DFA40_accept;
            this.special = DFA40_special;
            this.transition = DFA40_transition;
        }
        public String getDescription() {
            return "()* loopback of 749:39: ( options {k=2; } : COMMA t+= test[expr_contextType.Load] )*";
        }
    }
    static final String DFA51_eotS =
        "\4\uffff";
    static final String DFA51_eofS =
        "\4\uffff";
    static final String DFA51_minS =
        "\2\11\2\uffff";
    static final String DFA51_maxS =
        "\1\12\1\33\2\uffff";
    static final String DFA51_acceptS =
        "\2\uffff\1\1\1\2";
    static final String DFA51_specialS =
        "\4\uffff}>";
    static final String[] DFA51_transitionS = {
            "\1\2\1\1",
            "\1\2\1\1\20\uffff\1\3",
            "",
            ""
    };

    static final short[] DFA51_eot = DFA.unpackEncodedString(DFA51_eotS);
    static final short[] DFA51_eof = DFA.unpackEncodedString(DFA51_eofS);
    static final char[] DFA51_min = DFA.unpackEncodedStringToUnsignedChars(DFA51_minS);
    static final char[] DFA51_max = DFA.unpackEncodedStringToUnsignedChars(DFA51_maxS);
    static final short[] DFA51_accept = DFA.unpackEncodedString(DFA51_acceptS);
    static final short[] DFA51_special = DFA.unpackEncodedString(DFA51_specialS);
    static final short[][] DFA51_transition;

    static {
        int numStates = DFA51_transitionS.length;
        DFA51_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA51_transition[i] = DFA.unpackEncodedString(DFA51_transitionS[i]);
        }
    }

    class DFA51 extends DFA {

        public DFA51(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 51;
            this.eot = DFA51_eot;
            this.eof = DFA51_eof;
            this.min = DFA51_min;
            this.max = DFA51_max;
            this.accept = DFA51_accept;
            this.special = DFA51_special;
            this.transition = DFA51_transition;
        }
        public String getDescription() {
            return "910:12: ( (d+= DOT )* dotted_name | (d+= DOT )+ )";
        }
    }
    static final String DFA78_eotS =
        "\34\uffff";
    static final String DFA78_eofS =
        "\1\2\33\uffff";
    static final String DFA78_minS =
        "\1\7\1\0\32\uffff";
    static final String DFA78_maxS =
        "\1\125\1\0\32\uffff";
    static final String DFA78_acceptS =
        "\2\uffff\1\2\30\uffff\1\1";
    static final String DFA78_specialS =
        "\1\uffff\1\0\32\uffff}>";
    static final String[] DFA78_transitionS = {
            "\1\2\1\uffff\1\2\2\uffff\1\2\13\uffff\1\2\1\uffff\1\1\21\uffff"+
            "\4\2\2\uffff\15\2\23\uffff\1\2\1\uffff\2\2",
            "\1\uffff",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA78_eot = DFA.unpackEncodedString(DFA78_eotS);
    static final short[] DFA78_eof = DFA.unpackEncodedString(DFA78_eofS);
    static final char[] DFA78_min = DFA.unpackEncodedStringToUnsignedChars(DFA78_minS);
    static final char[] DFA78_max = DFA.unpackEncodedStringToUnsignedChars(DFA78_maxS);
    static final short[] DFA78_accept = DFA.unpackEncodedString(DFA78_acceptS);
    static final short[] DFA78_special = DFA.unpackEncodedString(DFA78_specialS);
    static final short[][] DFA78_transition;

    static {
        int numStates = DFA78_transitionS.length;
        DFA78_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA78_transition[i] = DFA.unpackEncodedString(DFA78_transitionS[i]);
        }
    }

    class DFA78 extends DFA {

        public DFA78(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 78;
            this.eot = DFA78_eot;
            this.eof = DFA78_eof;
            this.min = DFA78_min;
            this.max = DFA78_max;
            this.accept = DFA78_accept;
            this.special = DFA78_special;
            this.transition = DFA78_transition;
        }
        public String getDescription() {
            return "1220:7: ( ( IF or_test[null] ORELSE )=> IF o2= or_test[ctype] ORELSE e= test[expr_contextType.Load] | -> or_test )";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA78_1 = input.LA(1);

                         
                        int index78_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred7_Python()) ) {s = 27;}

                        else if ( (true) ) {s = 2;}

                         
                        input.seek(index78_1);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 78, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA87_eotS =
        "\15\uffff";
    static final String DFA87_eofS =
        "\15\uffff";
    static final String DFA87_minS =
        "\1\34\11\uffff\1\11\2\uffff";
    static final String DFA87_maxS =
        "\1\106\11\uffff\1\132\2\uffff";
    static final String DFA87_acceptS =
        "\1\uffff\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\uffff\1\13\1\12";
    static final String DFA87_specialS =
        "\15\uffff}>";
    static final String[] DFA87_transitionS = {
            "\1\10\1\12\1\uffff\1\11\40\uffff\1\1\1\2\1\3\1\4\1\5\1\6\1\7",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\14\25\uffff\1\13\13\uffff\1\14\37\uffff\2\14\3\uffff\2\14"+
            "\1\uffff\1\14\1\uffff\6\14",
            "",
            ""
    };

    static final short[] DFA87_eot = DFA.unpackEncodedString(DFA87_eotS);
    static final short[] DFA87_eof = DFA.unpackEncodedString(DFA87_eofS);
    static final char[] DFA87_min = DFA.unpackEncodedStringToUnsignedChars(DFA87_minS);
    static final char[] DFA87_max = DFA.unpackEncodedStringToUnsignedChars(DFA87_maxS);
    static final short[] DFA87_accept = DFA.unpackEncodedString(DFA87_acceptS);
    static final short[] DFA87_special = DFA.unpackEncodedString(DFA87_specialS);
    static final short[][] DFA87_transition;

    static {
        int numStates = DFA87_transitionS.length;
        DFA87_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA87_transition[i] = DFA.unpackEncodedString(DFA87_transitionS[i]);
        }
    }

    class DFA87 extends DFA {

        public DFA87(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 87;
            this.eot = DFA87_eot;
            this.eof = DFA87_eof;
            this.min = DFA87_min;
            this.max = DFA87_max;
            this.accept = DFA87_accept;
            this.special = DFA87_special;
            this.transition = DFA87_transition;
        }
        public String getDescription() {
            return "1316:1: comp_op returns [cmpopType op] : ( LESS | GREATER | EQUAL | GREATEREQUAL | LESSEQUAL | ALT_NOTEQUAL | NOTEQUAL | IN | NOT IN | IS | IS NOT );";
        }
    }
    static final String DFA114_eotS =
        "\23\uffff";
    static final String DFA114_eofS =
        "\23\uffff";
    static final String DFA114_minS =
        "\1\54\1\11\21\uffff";
    static final String DFA114_maxS =
        "\1\57\1\132\21\uffff";
    static final String DFA114_acceptS =
        "\2\uffff\1\2\1\1\17\uffff";
    static final String DFA114_specialS =
        "\23\uffff}>";
    static final String[] DFA114_transitionS = {
            "\1\2\2\uffff\1\1",
            "\1\3\24\uffff\2\3\13\uffff\1\3\1\2\36\uffff\2\3\3\uffff\2\3"+
            "\1\uffff\1\3\1\uffff\6\3",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA114_eot = DFA.unpackEncodedString(DFA114_eotS);
    static final short[] DFA114_eof = DFA.unpackEncodedString(DFA114_eofS);
    static final char[] DFA114_min = DFA.unpackEncodedStringToUnsignedChars(DFA114_minS);
    static final char[] DFA114_max = DFA.unpackEncodedStringToUnsignedChars(DFA114_maxS);
    static final short[] DFA114_accept = DFA.unpackEncodedString(DFA114_acceptS);
    static final short[] DFA114_special = DFA.unpackEncodedString(DFA114_specialS);
    static final short[][] DFA114_transition;

    static {
        int numStates = DFA114_transitionS.length;
        DFA114_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA114_transition[i] = DFA.unpackEncodedString(DFA114_transitionS[i]);
        }
    }

    class DFA114 extends DFA {

        public DFA114(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 114;
            this.eot = DFA114_eot;
            this.eof = DFA114_eof;
            this.min = DFA114_min;
            this.max = DFA114_max;
            this.accept = DFA114_accept;
            this.special = DFA114_special;
            this.transition = DFA114_transition;
        }
        public String getDescription() {
            return "()* loopback of 1755:11: ( options {k=2; } : c1= COMMA t+= test[$expr::ctype] )*";
        }
    }
    static final String DFA127_eotS =
        "\24\uffff";
    static final String DFA127_eofS =
        "\24\uffff";
    static final String DFA127_minS =
        "\1\11\1\uffff\17\0\3\uffff";
    static final String DFA127_maxS =
        "\1\132\1\uffff\17\0\3\uffff";
    static final String DFA127_acceptS =
        "\1\uffff\1\1\17\uffff\1\3\1\2\1\4";
    static final String DFA127_specialS =
        "\1\0\1\uffff\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13\1\14"+
        "\1\15\1\16\1\17\3\uffff}>";
    static final String[] DFA127_transitionS = {
            "\1\12\1\1\23\uffff\1\20\1\2\13\uffff\1\6\1\uffff\1\21\35\uffff"+
            "\1\3\1\4\3\uffff\1\5\1\7\1\uffff\1\10\1\uffff\1\11\1\13\1\14"+
            "\1\15\1\16\1\17",
            "",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "",
            "",
            ""
    };

    static final short[] DFA127_eot = DFA.unpackEncodedString(DFA127_eotS);
    static final short[] DFA127_eof = DFA.unpackEncodedString(DFA127_eofS);
    static final char[] DFA127_min = DFA.unpackEncodedStringToUnsignedChars(DFA127_minS);
    static final char[] DFA127_max = DFA.unpackEncodedStringToUnsignedChars(DFA127_maxS);
    static final short[] DFA127_accept = DFA.unpackEncodedString(DFA127_acceptS);
    static final short[] DFA127_special = DFA.unpackEncodedString(DFA127_specialS);
    static final short[][] DFA127_transition;

    static {
        int numStates = DFA127_transitionS.length;
        DFA127_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA127_transition[i] = DFA.unpackEncodedString(DFA127_transitionS[i]);
        }
    }

    class DFA127 extends DFA {

        public DFA127(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 127;
            this.eot = DFA127_eot;
            this.eof = DFA127_eof;
            this.min = DFA127_min;
            this.max = DFA127_max;
            this.accept = DFA127_accept;
            this.special = DFA127_special;
            this.transition = DFA127_transition;
        }
        public String getDescription() {
            return "1840:1: subscript returns [slice sltype] : (d1= DOT DOT DOT | ( test[null] COLON )=>lower= test[expr_contextType.Load] (c1= COLON (upper1= test[expr_contextType.Load] )? ( sliceop )? )? | ( COLON )=>c2= COLON (upper2= test[expr_contextType.Load] )? ( sliceop )? | test[expr_contextType.Load] );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA127_0 = input.LA(1);

                         
                        int index127_0 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA127_0==DOT) ) {s = 1;}

                        else if ( (LA127_0==NOT) ) {s = 2;}

                        else if ( (LA127_0==PLUS) ) {s = 3;}

                        else if ( (LA127_0==MINUS) ) {s = 4;}

                        else if ( (LA127_0==TILDE) ) {s = 5;}

                        else if ( (LA127_0==LPAREN) ) {s = 6;}

                        else if ( (LA127_0==LBRACK) ) {s = 7;}

                        else if ( (LA127_0==LCURLY) ) {s = 8;}

                        else if ( (LA127_0==BACKQUOTE) ) {s = 9;}

                        else if ( (LA127_0==NAME) ) {s = 10;}

                        else if ( (LA127_0==INT) ) {s = 11;}

                        else if ( (LA127_0==LONGINT) ) {s = 12;}

                        else if ( (LA127_0==FLOAT) ) {s = 13;}

                        else if ( (LA127_0==COMPLEX) ) {s = 14;}

                        else if ( (LA127_0==STRING) ) {s = 15;}

                        else if ( (LA127_0==LAMBDA) ) {s = 16;}

                        else if ( (LA127_0==COLON) && (synpred9_Python())) {s = 17;}

                         
                        input.seek(index127_0);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA127_2 = input.LA(1);

                         
                        int index127_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred8_Python()) ) {s = 18;}

                        else if ( (true) ) {s = 19;}

                         
                        input.seek(index127_2);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA127_3 = input.LA(1);

                         
                        int index127_3 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred8_Python()) ) {s = 18;}

                        else if ( (true) ) {s = 19;}

                         
                        input.seek(index127_3);
                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA127_4 = input.LA(1);

                         
                        int index127_4 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred8_Python()) ) {s = 18;}

                        else if ( (true) ) {s = 19;}

                         
                        input.seek(index127_4);
                        if ( s>=0 ) return s;
                        break;
                    case 4 : 
                        int LA127_5 = input.LA(1);

                         
                        int index127_5 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred8_Python()) ) {s = 18;}

                        else if ( (true) ) {s = 19;}

                         
                        input.seek(index127_5);
                        if ( s>=0 ) return s;
                        break;
                    case 5 : 
                        int LA127_6 = input.LA(1);

                         
                        int index127_6 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred8_Python()) ) {s = 18;}

                        else if ( (true) ) {s = 19;}

                         
                        input.seek(index127_6);
                        if ( s>=0 ) return s;
                        break;
                    case 6 : 
                        int LA127_7 = input.LA(1);

                         
                        int index127_7 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred8_Python()) ) {s = 18;}

                        else if ( (true) ) {s = 19;}

                         
                        input.seek(index127_7);
                        if ( s>=0 ) return s;
                        break;
                    case 7 : 
                        int LA127_8 = input.LA(1);

                         
                        int index127_8 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred8_Python()) ) {s = 18;}

                        else if ( (true) ) {s = 19;}

                         
                        input.seek(index127_8);
                        if ( s>=0 ) return s;
                        break;
                    case 8 : 
                        int LA127_9 = input.LA(1);

                         
                        int index127_9 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred8_Python()) ) {s = 18;}

                        else if ( (true) ) {s = 19;}

                         
                        input.seek(index127_9);
                        if ( s>=0 ) return s;
                        break;
                    case 9 : 
                        int LA127_10 = input.LA(1);

                         
                        int index127_10 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred8_Python()) ) {s = 18;}

                        else if ( (true) ) {s = 19;}

                         
                        input.seek(index127_10);
                        if ( s>=0 ) return s;
                        break;
                    case 10 : 
                        int LA127_11 = input.LA(1);

                         
                        int index127_11 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred8_Python()) ) {s = 18;}

                        else if ( (true) ) {s = 19;}

                         
                        input.seek(index127_11);
                        if ( s>=0 ) return s;
                        break;
                    case 11 : 
                        int LA127_12 = input.LA(1);

                         
                        int index127_12 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred8_Python()) ) {s = 18;}

                        else if ( (true) ) {s = 19;}

                         
                        input.seek(index127_12);
                        if ( s>=0 ) return s;
                        break;
                    case 12 : 
                        int LA127_13 = input.LA(1);

                         
                        int index127_13 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred8_Python()) ) {s = 18;}

                        else if ( (true) ) {s = 19;}

                         
                        input.seek(index127_13);
                        if ( s>=0 ) return s;
                        break;
                    case 13 : 
                        int LA127_14 = input.LA(1);

                         
                        int index127_14 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred8_Python()) ) {s = 18;}

                        else if ( (true) ) {s = 19;}

                         
                        input.seek(index127_14);
                        if ( s>=0 ) return s;
                        break;
                    case 14 : 
                        int LA127_15 = input.LA(1);

                         
                        int index127_15 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred8_Python()) ) {s = 18;}

                        else if ( (true) ) {s = 19;}

                         
                        input.seek(index127_15);
                        if ( s>=0 ) return s;
                        break;
                    case 15 : 
                        int LA127_16 = input.LA(1);

                         
                        int index127_16 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred8_Python()) ) {s = 18;}

                        else if ( (true) ) {s = 19;}

                         
                        input.seek(index127_16);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 127, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA131_eotS =
        "\20\uffff";
    static final String DFA131_eofS =
        "\20\uffff";
    static final String DFA131_minS =
        "\1\11\15\0\2\uffff";
    static final String DFA131_maxS =
        "\1\132\15\0\2\uffff";
    static final String DFA131_acceptS =
        "\16\uffff\1\1\1\2";
    static final String DFA131_specialS =
        "\1\uffff\1\0\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13\1\14"+
        "\2\uffff}>";
    static final String[] DFA131_transitionS = {
            "\1\10\41\uffff\1\4\37\uffff\1\1\1\2\3\uffff\1\3\1\5\1\uffff"+
            "\1\6\1\uffff\1\7\1\11\1\12\1\13\1\14\1\15",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "",
            ""
    };

    static final short[] DFA131_eot = DFA.unpackEncodedString(DFA131_eotS);
    static final short[] DFA131_eof = DFA.unpackEncodedString(DFA131_eofS);
    static final char[] DFA131_min = DFA.unpackEncodedStringToUnsignedChars(DFA131_minS);
    static final char[] DFA131_max = DFA.unpackEncodedStringToUnsignedChars(DFA131_maxS);
    static final short[] DFA131_accept = DFA.unpackEncodedString(DFA131_acceptS);
    static final short[] DFA131_special = DFA.unpackEncodedString(DFA131_specialS);
    static final short[][] DFA131_transition;

    static {
        int numStates = DFA131_transitionS.length;
        DFA131_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA131_transition[i] = DFA.unpackEncodedString(DFA131_transitionS[i]);
        }
    }

    class DFA131 extends DFA {

        public DFA131(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 131;
            this.eot = DFA131_eot;
            this.eof = DFA131_eof;
            this.min = DFA131_min;
            this.max = DFA131_max;
            this.accept = DFA131_accept;
            this.special = DFA131_special;
            this.transition = DFA131_transition;
        }
        public String getDescription() {
            return "1886:1: exprlist[expr_contextType ctype] returns [expr etype] : ( ( expr[null] COMMA )=>e+= expr[ctype] ( options {k=2; } : COMMA e+= expr[ctype] )* ( COMMA )? | expr[ctype] );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA131_1 = input.LA(1);

                         
                        int index131_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred10_Python()) ) {s = 14;}

                        else if ( (true) ) {s = 15;}

                         
                        input.seek(index131_1);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA131_2 = input.LA(1);

                         
                        int index131_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred10_Python()) ) {s = 14;}

                        else if ( (true) ) {s = 15;}

                         
                        input.seek(index131_2);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA131_3 = input.LA(1);

                         
                        int index131_3 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred10_Python()) ) {s = 14;}

                        else if ( (true) ) {s = 15;}

                         
                        input.seek(index131_3);
                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA131_4 = input.LA(1);

                         
                        int index131_4 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred10_Python()) ) {s = 14;}

                        else if ( (true) ) {s = 15;}

                         
                        input.seek(index131_4);
                        if ( s>=0 ) return s;
                        break;
                    case 4 : 
                        int LA131_5 = input.LA(1);

                         
                        int index131_5 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred10_Python()) ) {s = 14;}

                        else if ( (true) ) {s = 15;}

                         
                        input.seek(index131_5);
                        if ( s>=0 ) return s;
                        break;
                    case 5 : 
                        int LA131_6 = input.LA(1);

                         
                        int index131_6 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred10_Python()) ) {s = 14;}

                        else if ( (true) ) {s = 15;}

                         
                        input.seek(index131_6);
                        if ( s>=0 ) return s;
                        break;
                    case 6 : 
                        int LA131_7 = input.LA(1);

                         
                        int index131_7 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred10_Python()) ) {s = 14;}

                        else if ( (true) ) {s = 15;}

                         
                        input.seek(index131_7);
                        if ( s>=0 ) return s;
                        break;
                    case 7 : 
                        int LA131_8 = input.LA(1);

                         
                        int index131_8 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred10_Python()) ) {s = 14;}

                        else if ( (true) ) {s = 15;}

                         
                        input.seek(index131_8);
                        if ( s>=0 ) return s;
                        break;
                    case 8 : 
                        int LA131_9 = input.LA(1);

                         
                        int index131_9 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred10_Python()) ) {s = 14;}

                        else if ( (true) ) {s = 15;}

                         
                        input.seek(index131_9);
                        if ( s>=0 ) return s;
                        break;
                    case 9 : 
                        int LA131_10 = input.LA(1);

                         
                        int index131_10 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred10_Python()) ) {s = 14;}

                        else if ( (true) ) {s = 15;}

                         
                        input.seek(index131_10);
                        if ( s>=0 ) return s;
                        break;
                    case 10 : 
                        int LA131_11 = input.LA(1);

                         
                        int index131_11 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred10_Python()) ) {s = 14;}

                        else if ( (true) ) {s = 15;}

                         
                        input.seek(index131_11);
                        if ( s>=0 ) return s;
                        break;
                    case 11 : 
                        int LA131_12 = input.LA(1);

                         
                        int index131_12 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred10_Python()) ) {s = 14;}

                        else if ( (true) ) {s = 15;}

                         
                        input.seek(index131_12);
                        if ( s>=0 ) return s;
                        break;
                    case 12 : 
                        int LA131_13 = input.LA(1);

                         
                        int index131_13 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred10_Python()) ) {s = 14;}

                        else if ( (true) ) {s = 15;}

                         
                        input.seek(index131_13);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 131, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA129_eotS =
        "\21\uffff";
    static final String DFA129_eofS =
        "\21\uffff";
    static final String DFA129_minS =
        "\1\34\1\11\17\uffff";
    static final String DFA129_maxS =
        "\1\57\1\132\17\uffff";
    static final String DFA129_acceptS =
        "\2\uffff\1\2\1\uffff\1\1\14\uffff";
    static final String DFA129_specialS =
        "\21\uffff}>";
    static final String[] DFA129_transitionS = {
            "\1\2\22\uffff\1\1",
            "\1\4\22\uffff\1\2\16\uffff\1\4\37\uffff\2\4\3\uffff\2\4\1\uffff"+
            "\1\4\1\uffff\6\4",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA129_eot = DFA.unpackEncodedString(DFA129_eotS);
    static final short[] DFA129_eof = DFA.unpackEncodedString(DFA129_eofS);
    static final char[] DFA129_min = DFA.unpackEncodedStringToUnsignedChars(DFA129_minS);
    static final char[] DFA129_max = DFA.unpackEncodedStringToUnsignedChars(DFA129_maxS);
    static final short[] DFA129_accept = DFA.unpackEncodedString(DFA129_acceptS);
    static final short[] DFA129_special = DFA.unpackEncodedString(DFA129_specialS);
    static final short[][] DFA129_transition;

    static {
        int numStates = DFA129_transitionS.length;
        DFA129_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA129_transition[i] = DFA.unpackEncodedString(DFA129_transitionS[i]);
        }
    }

    class DFA129 extends DFA {

        public DFA129(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 129;
            this.eot = DFA129_eot;
            this.eof = DFA129_eof;
            this.min = DFA129_min;
            this.max = DFA129_max;
            this.accept = DFA129_accept;
            this.special = DFA129_special;
            this.transition = DFA129_transition;
        }
        public String getDescription() {
            return "()* loopback of 1888:44: ( options {k=2; } : COMMA e+= expr[ctype] )*";
        }
    }
    static final String DFA132_eotS =
        "\23\uffff";
    static final String DFA132_eofS =
        "\23\uffff";
    static final String DFA132_minS =
        "\2\7\21\uffff";
    static final String DFA132_maxS =
        "\1\62\1\132\21\uffff";
    static final String DFA132_acceptS =
        "\2\uffff\1\2\3\uffff\1\1\14\uffff";
    static final String DFA132_specialS =
        "\23\uffff}>";
    static final String[] DFA132_transitionS = {
            "\1\2\47\uffff\1\1\2\uffff\1\2",
            "\1\2\1\uffff\1\6\41\uffff\1\6\6\uffff\1\2\30\uffff\2\6\3\uffff"+
            "\2\6\1\uffff\1\6\1\uffff\6\6",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA132_eot = DFA.unpackEncodedString(DFA132_eotS);
    static final short[] DFA132_eof = DFA.unpackEncodedString(DFA132_eofS);
    static final char[] DFA132_min = DFA.unpackEncodedStringToUnsignedChars(DFA132_minS);
    static final char[] DFA132_max = DFA.unpackEncodedStringToUnsignedChars(DFA132_maxS);
    static final short[] DFA132_accept = DFA.unpackEncodedString(DFA132_acceptS);
    static final short[] DFA132_special = DFA.unpackEncodedString(DFA132_specialS);
    static final short[][] DFA132_transition;

    static {
        int numStates = DFA132_transitionS.length;
        DFA132_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA132_transition[i] = DFA.unpackEncodedString(DFA132_transitionS[i]);
        }
    }

    class DFA132 extends DFA {

        public DFA132(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 132;
            this.eot = DFA132_eot;
            this.eof = DFA132_eof;
            this.min = DFA132_min;
            this.max = DFA132_max;
            this.accept = DFA132_accept;
            this.special = DFA132_special;
            this.transition = DFA132_transition;
        }
        public String getDescription() {
            return "()* loopback of 1902:37: ( options {k=2; } : COMMA e+= expr[expr_contextType.Del] )*";
        }
    }
    static final String DFA136_eotS =
        "\22\uffff";
    static final String DFA136_eofS =
        "\22\uffff";
    static final String DFA136_minS =
        "\1\11\17\0\2\uffff";
    static final String DFA136_maxS =
        "\1\132\17\0\2\uffff";
    static final String DFA136_acceptS =
        "\20\uffff\1\1\1\2";
    static final String DFA136_specialS =
        "\1\uffff\1\0\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13\1\14"+
        "\1\15\1\16\2\uffff}>";
    static final String[] DFA136_transitionS = {
            "\1\11\24\uffff\1\17\1\1\13\uffff\1\5\37\uffff\1\2\1\3\3\uffff"+
            "\1\4\1\6\1\uffff\1\7\1\uffff\1\10\1\12\1\13\1\14\1\15\1\16",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "",
            ""
    };

    static final short[] DFA136_eot = DFA.unpackEncodedString(DFA136_eotS);
    static final short[] DFA136_eof = DFA.unpackEncodedString(DFA136_eofS);
    static final char[] DFA136_min = DFA.unpackEncodedStringToUnsignedChars(DFA136_minS);
    static final char[] DFA136_max = DFA.unpackEncodedStringToUnsignedChars(DFA136_maxS);
    static final short[] DFA136_accept = DFA.unpackEncodedString(DFA136_acceptS);
    static final short[] DFA136_special = DFA.unpackEncodedString(DFA136_specialS);
    static final short[][] DFA136_transition;

    static {
        int numStates = DFA136_transitionS.length;
        DFA136_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA136_transition[i] = DFA.unpackEncodedString(DFA136_transitionS[i]);
        }
    }

    class DFA136 extends DFA {

        public DFA136(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 136;
            this.eot = DFA136_eot;
            this.eof = DFA136_eof;
            this.min = DFA136_min;
            this.max = DFA136_max;
            this.accept = DFA136_accept;
            this.special = DFA136_special;
            this.transition = DFA136_transition;
        }
        public String getDescription() {
            return "1909:1: testlist[expr_contextType ctype] : ( ( test[null] COMMA )=>t+= test[ctype] ( options {k=2; } : COMMA t+= test[ctype] )* ( COMMA )? | test[ctype] );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA136_1 = input.LA(1);

                         
                        int index136_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred11_Python()) ) {s = 16;}

                        else if ( (true) ) {s = 17;}

                         
                        input.seek(index136_1);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA136_2 = input.LA(1);

                         
                        int index136_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred11_Python()) ) {s = 16;}

                        else if ( (true) ) {s = 17;}

                         
                        input.seek(index136_2);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA136_3 = input.LA(1);

                         
                        int index136_3 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred11_Python()) ) {s = 16;}

                        else if ( (true) ) {s = 17;}

                         
                        input.seek(index136_3);
                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA136_4 = input.LA(1);

                         
                        int index136_4 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred11_Python()) ) {s = 16;}

                        else if ( (true) ) {s = 17;}

                         
                        input.seek(index136_4);
                        if ( s>=0 ) return s;
                        break;
                    case 4 : 
                        int LA136_5 = input.LA(1);

                         
                        int index136_5 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred11_Python()) ) {s = 16;}

                        else if ( (true) ) {s = 17;}

                         
                        input.seek(index136_5);
                        if ( s>=0 ) return s;
                        break;
                    case 5 : 
                        int LA136_6 = input.LA(1);

                         
                        int index136_6 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred11_Python()) ) {s = 16;}

                        else if ( (true) ) {s = 17;}

                         
                        input.seek(index136_6);
                        if ( s>=0 ) return s;
                        break;
                    case 6 : 
                        int LA136_7 = input.LA(1);

                         
                        int index136_7 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred11_Python()) ) {s = 16;}

                        else if ( (true) ) {s = 17;}

                         
                        input.seek(index136_7);
                        if ( s>=0 ) return s;
                        break;
                    case 7 : 
                        int LA136_8 = input.LA(1);

                         
                        int index136_8 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred11_Python()) ) {s = 16;}

                        else if ( (true) ) {s = 17;}

                         
                        input.seek(index136_8);
                        if ( s>=0 ) return s;
                        break;
                    case 8 : 
                        int LA136_9 = input.LA(1);

                         
                        int index136_9 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred11_Python()) ) {s = 16;}

                        else if ( (true) ) {s = 17;}

                         
                        input.seek(index136_9);
                        if ( s>=0 ) return s;
                        break;
                    case 9 : 
                        int LA136_10 = input.LA(1);

                         
                        int index136_10 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred11_Python()) ) {s = 16;}

                        else if ( (true) ) {s = 17;}

                         
                        input.seek(index136_10);
                        if ( s>=0 ) return s;
                        break;
                    case 10 : 
                        int LA136_11 = input.LA(1);

                         
                        int index136_11 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred11_Python()) ) {s = 16;}

                        else if ( (true) ) {s = 17;}

                         
                        input.seek(index136_11);
                        if ( s>=0 ) return s;
                        break;
                    case 11 : 
                        int LA136_12 = input.LA(1);

                         
                        int index136_12 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred11_Python()) ) {s = 16;}

                        else if ( (true) ) {s = 17;}

                         
                        input.seek(index136_12);
                        if ( s>=0 ) return s;
                        break;
                    case 12 : 
                        int LA136_13 = input.LA(1);

                         
                        int index136_13 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred11_Python()) ) {s = 16;}

                        else if ( (true) ) {s = 17;}

                         
                        input.seek(index136_13);
                        if ( s>=0 ) return s;
                        break;
                    case 13 : 
                        int LA136_14 = input.LA(1);

                         
                        int index136_14 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred11_Python()) ) {s = 16;}

                        else if ( (true) ) {s = 17;}

                         
                        input.seek(index136_14);
                        if ( s>=0 ) return s;
                        break;
                    case 14 : 
                        int LA136_15 = input.LA(1);

                         
                        int index136_15 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred11_Python()) ) {s = 16;}

                        else if ( (true) ) {s = 17;}

                         
                        input.seek(index136_15);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 136, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA134_eotS =
        "\75\uffff";
    static final String DFA134_eofS =
        "\2\2\73\uffff";
    static final String DFA134_minS =
        "\2\7\73\uffff";
    static final String DFA134_maxS =
        "\1\125\1\132\73\uffff";
    static final String DFA134_acceptS =
        "\2\uffff\1\2\25\uffff\1\1\6\uffff\1\1\35\uffff";
    static final String DFA134_specialS =
        "\75\uffff}>";
    static final String[] DFA134_transitionS = {
            "\1\2\20\uffff\1\2\1\uffff\1\2\21\uffff\3\2\1\1\2\uffff\15\2"+
            "\23\uffff\1\2\2\uffff\1\2",
            "\1\2\1\uffff\1\30\16\uffff\1\2\1\uffff\1\2\3\uffff\2\30\13"+
            "\uffff\1\30\4\2\2\uffff\15\2\14\uffff\2\30\3\uffff\2\30\1\2"+
            "\1\30\1\uffff\1\37\5\30",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA134_eot = DFA.unpackEncodedString(DFA134_eotS);
    static final short[] DFA134_eof = DFA.unpackEncodedString(DFA134_eofS);
    static final char[] DFA134_min = DFA.unpackEncodedStringToUnsignedChars(DFA134_minS);
    static final char[] DFA134_max = DFA.unpackEncodedStringToUnsignedChars(DFA134_maxS);
    static final short[] DFA134_accept = DFA.unpackEncodedString(DFA134_acceptS);
    static final short[] DFA134_special = DFA.unpackEncodedString(DFA134_specialS);
    static final short[][] DFA134_transition;

    static {
        int numStates = DFA134_transitionS.length;
        DFA134_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA134_transition[i] = DFA.unpackEncodedString(DFA134_transitionS[i]);
        }
    }

    class DFA134 extends DFA {

        public DFA134(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 134;
            this.eot = DFA134_eot;
            this.eof = DFA134_eof;
            this.min = DFA134_min;
            this.max = DFA134_max;
            this.accept = DFA134_accept;
            this.special = DFA134_special;
            this.transition = DFA134_transition;
        }
        public String getDescription() {
            return "()* loopback of 1919:22: ( options {k=2; } : COMMA t+= test[ctype] )*";
        }
    }
    static final String DFA137_eotS =
        "\23\uffff";
    static final String DFA137_eofS =
        "\23\uffff";
    static final String DFA137_minS =
        "\1\57\1\11\21\uffff";
    static final String DFA137_maxS =
        "\1\124\1\132\21\uffff";
    static final String DFA137_acceptS =
        "\2\uffff\1\2\1\1\17\uffff";
    static final String DFA137_specialS =
        "\23\uffff}>";
    static final String[] DFA137_transitionS = {
            "\1\1\44\uffff\1\2",
            "\1\3\24\uffff\2\3\13\uffff\1\3\37\uffff\2\3\3\uffff\2\3\1\uffff"+
            "\1\3\1\2\6\3",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA137_eot = DFA.unpackEncodedString(DFA137_eotS);
    static final short[] DFA137_eof = DFA.unpackEncodedString(DFA137_eofS);
    static final char[] DFA137_min = DFA.unpackEncodedStringToUnsignedChars(DFA137_minS);
    static final char[] DFA137_max = DFA.unpackEncodedStringToUnsignedChars(DFA137_maxS);
    static final short[] DFA137_accept = DFA.unpackEncodedString(DFA137_acceptS);
    static final short[] DFA137_special = DFA.unpackEncodedString(DFA137_specialS);
    static final short[][] DFA137_transition;

    static {
        int numStates = DFA137_transitionS.length;
        DFA137_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA137_transition[i] = DFA.unpackEncodedString(DFA137_transitionS[i]);
        }
    }

    class DFA137 extends DFA {

        public DFA137(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 137;
            this.eot = DFA137_eot;
            this.eof = DFA137_eof;
            this.min = DFA137_min;
            this.max = DFA137_max;
            this.accept = DFA137_accept;
            this.special = DFA137_special;
            this.transition = DFA137_transition;
        }
        public String getDescription() {
            return "()* loopback of 1930:9: ( options {k=2; } : COMMA k+= test[expr_contextType.Load] COLON v+= test[expr_contextType.Load] )*";
        }
    }
 

    public static final BitSet FOLLOW_NEWLINE_in_single_input118 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_EOF_in_single_input121 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simple_stmt_in_single_input137 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_NEWLINE_in_single_input139 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_EOF_in_single_input142 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_compound_stmt_in_single_input158 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_NEWLINE_in_single_input160 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_EOF_in_single_input163 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NEWLINE_in_file_input215 = new BitSet(new long[]{0x00000FFCCFA7E280L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_stmt_in_file_input225 = new BitSet(new long[]{0x00000FFCCFA7E280L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_EOF_in_file_input244 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LEADING_WS_in_eval_input298 = new BitSet(new long[]{0x00000800C0000280L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_NEWLINE_in_eval_input302 = new BitSet(new long[]{0x00000800C0000280L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_testlist_in_eval_input306 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_NEWLINE_in_eval_input310 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_EOF_in_eval_input314 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NAME_in_dotted_attr366 = new BitSet(new long[]{0x0000000000000402L});
    public static final BitSet FOLLOW_DOT_in_dotted_attr377 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_NAME_in_dotted_attr381 = new BitSet(new long[]{0x0000000000000402L});
    public static final BitSet FOLLOW_set_in_attr0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_AT_in_decorator718 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_dotted_attr_in_decorator720 = new BitSet(new long[]{0x0000080000000080L});
    public static final BitSet FOLLOW_LPAREN_in_decorator728 = new BitSet(new long[]{0x00031800C0000200L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_arglist_in_decorator738 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_RPAREN_in_decorator782 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_NEWLINE_in_decorator804 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_decorator_in_decorators832 = new BitSet(new long[]{0x0000040000000002L});
    public static final BitSet FOLLOW_decorators_in_funcdef870 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_DEF_in_funcdef873 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_NAME_in_funcdef875 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_parameters_in_funcdef877 = new BitSet(new long[]{0x0000200000000000L});
    public static final BitSet FOLLOW_COLON_in_funcdef879 = new BitSet(new long[]{0x00000A3CCAA56280L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_suite_in_funcdef881 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_parameters914 = new BitSet(new long[]{0x0003180000000200L});
    public static final BitSet FOLLOW_varargslist_in_parameters923 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_RPAREN_in_parameters967 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_fpdef_in_defparameter1000 = new BitSet(new long[]{0x0000400000000002L});
    public static final BitSet FOLLOW_ASSIGN_in_defparameter1004 = new BitSet(new long[]{0x00000800C0000200L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_test_in_defparameter1006 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_defparameter_in_varargslist1052 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_COMMA_in_varargslist1063 = new BitSet(new long[]{0x0000080000000200L});
    public static final BitSet FOLLOW_defparameter_in_varargslist1067 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_COMMA_in_varargslist1079 = new BitSet(new long[]{0x0003000000000002L});
    public static final BitSet FOLLOW_STAR_in_varargslist1092 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_NAME_in_varargslist1096 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_COMMA_in_varargslist1099 = new BitSet(new long[]{0x0002000000000000L});
    public static final BitSet FOLLOW_DOUBLESTAR_in_varargslist1101 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_NAME_in_varargslist1105 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOUBLESTAR_in_varargslist1121 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_NAME_in_varargslist1125 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STAR_in_varargslist1163 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_NAME_in_varargslist1167 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_COMMA_in_varargslist1170 = new BitSet(new long[]{0x0002000000000000L});
    public static final BitSet FOLLOW_DOUBLESTAR_in_varargslist1172 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_NAME_in_varargslist1176 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOUBLESTAR_in_varargslist1194 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_NAME_in_varargslist1198 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NAME_in_fpdef1235 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_fpdef1262 = new BitSet(new long[]{0x0000080000000200L});
    public static final BitSet FOLLOW_fplist_in_fpdef1264 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_RPAREN_in_fpdef1266 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_fpdef1282 = new BitSet(new long[]{0x0000080000000200L});
    public static final BitSet FOLLOW_fplist_in_fpdef1285 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_RPAREN_in_fpdef1287 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_fpdef_in_fplist1316 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_COMMA_in_fplist1333 = new BitSet(new long[]{0x0000080000000200L});
    public static final BitSet FOLLOW_fpdef_in_fplist1337 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_COMMA_in_fplist1343 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simple_stmt_in_stmt1379 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_compound_stmt_in_stmt1395 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_small_stmt_in_simple_stmt1431 = new BitSet(new long[]{0x0004000000000080L});
    public static final BitSet FOLLOW_SEMI_in_simple_stmt1441 = new BitSet(new long[]{0x00000A3CCAA56200L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_small_stmt_in_simple_stmt1445 = new BitSet(new long[]{0x0004000000000080L});
    public static final BitSet FOLLOW_SEMI_in_simple_stmt1450 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_NEWLINE_in_simple_stmt1454 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expr_stmt_in_small_stmt1477 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_print_stmt_in_small_stmt1492 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_del_stmt_in_small_stmt1507 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pass_stmt_in_small_stmt1522 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_flow_stmt_in_small_stmt1537 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_import_stmt_in_small_stmt1552 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_global_stmt_in_small_stmt1567 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_exec_stmt_in_small_stmt1582 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_assert_stmt_in_small_stmt1597 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_testlist_in_expr_stmt1645 = new BitSet(new long[]{0x7FF8000000000000L});
    public static final BitSet FOLLOW_augassign_in_expr_stmt1661 = new BitSet(new long[]{0x0000023000014000L});
    public static final BitSet FOLLOW_yield_expr_in_expr_stmt1665 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_augassign_in_expr_stmt1705 = new BitSet(new long[]{0x00000800C0000200L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_testlist_in_expr_stmt1709 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_testlist_in_expr_stmt1764 = new BitSet(new long[]{0x0000400000000002L});
    public static final BitSet FOLLOW_ASSIGN_in_expr_stmt1791 = new BitSet(new long[]{0x00000800C0000200L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_testlist_in_expr_stmt1795 = new BitSet(new long[]{0x0000400000000002L});
    public static final BitSet FOLLOW_ASSIGN_in_expr_stmt1840 = new BitSet(new long[]{0x0000023000014000L});
    public static final BitSet FOLLOW_yield_expr_in_expr_stmt1844 = new BitSet(new long[]{0x0000400000000002L});
    public static final BitSet FOLLOW_testlist_in_expr_stmt1892 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PLUSEQUAL_in_augassign1934 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUSEQUAL_in_augassign1952 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STAREQUAL_in_augassign1970 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SLASHEQUAL_in_augassign1988 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PERCENTEQUAL_in_augassign2006 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_AMPEREQUAL_in_augassign2024 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VBAREQUAL_in_augassign2042 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CIRCUMFLEXEQUAL_in_augassign2060 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LEFTSHIFTEQUAL_in_augassign2078 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RIGHTSHIFTEQUAL_in_augassign2096 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOUBLESTAREQUAL_in_augassign2114 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOUBLESLASHEQUAL_in_augassign2132 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PRINT_in_print_stmt2172 = new BitSet(new long[]{0x80000800C0000202L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_printlist_in_print_stmt2183 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RIGHTSHIFT_in_print_stmt2202 = new BitSet(new long[]{0x00000800C0000200L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_printlist2_in_print_stmt2206 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_test_in_printlist2286 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_COMMA_in_printlist2298 = new BitSet(new long[]{0x00000800C0000200L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_test_in_printlist2302 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_COMMA_in_printlist2310 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_test_in_printlist2331 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_test_in_printlist22388 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_COMMA_in_printlist22400 = new BitSet(new long[]{0x00000800C0000200L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_test_in_printlist22404 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_COMMA_in_printlist22412 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_test_in_printlist22433 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DELETE_in_del_stmt2470 = new BitSet(new long[]{0x0000080080000200L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_del_list_in_del_stmt2472 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PASS_in_pass_stmt2508 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_break_stmt_in_flow_stmt2534 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_continue_stmt_in_flow_stmt2542 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_return_stmt_in_flow_stmt2550 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_raise_stmt_in_flow_stmt2558 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_yield_stmt_in_flow_stmt2566 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BREAK_in_break_stmt2594 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CONTINUE_in_continue_stmt2630 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RETURN_in_return_stmt2666 = new BitSet(new long[]{0x00000800C0000202L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_testlist_in_return_stmt2675 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_yield_expr_in_yield_stmt2740 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RAISE_in_raise_stmt2776 = new BitSet(new long[]{0x00000800C0000202L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_test_in_raise_stmt2781 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_COMMA_in_raise_stmt2785 = new BitSet(new long[]{0x00000800C0000200L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_test_in_raise_stmt2789 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_COMMA_in_raise_stmt2801 = new BitSet(new long[]{0x00000800C0000200L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_test_in_raise_stmt2805 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_import_name_in_import_stmt2838 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_import_from_in_import_stmt2846 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IMPORT_in_import_name2874 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_dotted_as_names_in_import_name2876 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FROM_in_import_from2913 = new BitSet(new long[]{0x0000000000000600L});
    public static final BitSet FOLLOW_DOT_in_import_from2918 = new BitSet(new long[]{0x0000000000000600L});
    public static final BitSet FOLLOW_dotted_name_in_import_from2921 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_DOT_in_import_from2927 = new BitSet(new long[]{0x0000000008000400L});
    public static final BitSet FOLLOW_IMPORT_in_import_from2931 = new BitSet(new long[]{0x0001080000000200L});
    public static final BitSet FOLLOW_STAR_in_import_from2942 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_import_as_names_in_import_from2967 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_import_from2990 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_import_as_names_in_import_from2994 = new BitSet(new long[]{0x0000900000000000L});
    public static final BitSet FOLLOW_COMMA_in_import_from2996 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_RPAREN_in_import_from2999 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_import_as_name_in_import_as_names3048 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_COMMA_in_import_as_names3051 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_import_as_name_in_import_as_names3056 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_NAME_in_import_as_name3097 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_AS_in_import_as_name3100 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_NAME_in_import_as_name3104 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_dotted_name_in_dotted_as_name3144 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_AS_in_dotted_as_name3147 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_NAME_in_dotted_as_name3151 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_dotted_as_name_in_dotted_as_names3187 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_COMMA_in_dotted_as_names3190 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_dotted_as_name_in_dotted_as_names3195 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_NAME_in_dotted_name3229 = new BitSet(new long[]{0x0000000000000402L});
    public static final BitSet FOLLOW_DOT_in_dotted_name3232 = new BitSet(new long[]{0x000003FFFFFFFA00L});
    public static final BitSet FOLLOW_attr_in_dotted_name3236 = new BitSet(new long[]{0x0000000000000402L});
    public static final BitSet FOLLOW_GLOBAL_in_global_stmt3272 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_NAME_in_global_stmt3276 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_COMMA_in_global_stmt3279 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_NAME_in_global_stmt3283 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_EXEC_in_exec_stmt3321 = new BitSet(new long[]{0x0000080080000200L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_expr_in_exec_stmt3323 = new BitSet(new long[]{0x0000000010000002L});
    public static final BitSet FOLLOW_IN_in_exec_stmt3327 = new BitSet(new long[]{0x00000800C0000200L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_test_in_exec_stmt3331 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_COMMA_in_exec_stmt3335 = new BitSet(new long[]{0x00000800C0000200L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_test_in_exec_stmt3339 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ASSERT_in_assert_stmt3380 = new BitSet(new long[]{0x00000800C0000200L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_test_in_assert_stmt3384 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_COMMA_in_assert_stmt3388 = new BitSet(new long[]{0x00000800C0000200L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_test_in_assert_stmt3392 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_if_stmt_in_compound_stmt3421 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_while_stmt_in_compound_stmt3429 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_for_stmt_in_compound_stmt3437 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_try_stmt_in_compound_stmt3445 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_with_stmt_in_compound_stmt3453 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_funcdef_in_compound_stmt3470 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_classdef_in_compound_stmt3478 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IF_in_if_stmt3506 = new BitSet(new long[]{0x00000800C0000200L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_test_in_if_stmt3508 = new BitSet(new long[]{0x0000200000000000L});
    public static final BitSet FOLLOW_COLON_in_if_stmt3511 = new BitSet(new long[]{0x00000A3CCAA56280L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_suite_in_if_stmt3515 = new BitSet(new long[]{0x0000000200080002L});
    public static final BitSet FOLLOW_elif_clause_in_if_stmt3518 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_else_clause_in_elif_clause3563 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ELIF_in_elif_clause3579 = new BitSet(new long[]{0x00000800C0000200L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_test_in_elif_clause3581 = new BitSet(new long[]{0x0000200000000000L});
    public static final BitSet FOLLOW_COLON_in_elif_clause3584 = new BitSet(new long[]{0x00000A3CCAA56280L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_suite_in_elif_clause3586 = new BitSet(new long[]{0x0000000200080002L});
    public static final BitSet FOLLOW_elif_clause_in_elif_clause3598 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ORELSE_in_else_clause3658 = new BitSet(new long[]{0x0000200000000000L});
    public static final BitSet FOLLOW_COLON_in_else_clause3660 = new BitSet(new long[]{0x00000A3CCAA56280L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_suite_in_else_clause3664 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WHILE_in_while_stmt3701 = new BitSet(new long[]{0x00000800C0000200L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_test_in_while_stmt3703 = new BitSet(new long[]{0x0000200000000000L});
    public static final BitSet FOLLOW_COLON_in_while_stmt3706 = new BitSet(new long[]{0x00000A3CCAA56280L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_suite_in_while_stmt3710 = new BitSet(new long[]{0x0000000200000002L});
    public static final BitSet FOLLOW_ORELSE_in_while_stmt3714 = new BitSet(new long[]{0x0000200000000000L});
    public static final BitSet FOLLOW_COLON_in_while_stmt3716 = new BitSet(new long[]{0x00000A3CCAA56280L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_suite_in_while_stmt3720 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FOR_in_for_stmt3759 = new BitSet(new long[]{0x0000080080000200L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_exprlist_in_for_stmt3761 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_IN_in_for_stmt3764 = new BitSet(new long[]{0x00000800C0000200L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_testlist_in_for_stmt3766 = new BitSet(new long[]{0x0000200000000000L});
    public static final BitSet FOLLOW_COLON_in_for_stmt3769 = new BitSet(new long[]{0x00000A3CCAA56280L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_suite_in_for_stmt3773 = new BitSet(new long[]{0x0000000200000002L});
    public static final BitSet FOLLOW_ORELSE_in_for_stmt3785 = new BitSet(new long[]{0x0000200000000000L});
    public static final BitSet FOLLOW_COLON_in_for_stmt3787 = new BitSet(new long[]{0x00000A3CCAA56280L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_suite_in_for_stmt3791 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TRY_in_try_stmt3834 = new BitSet(new long[]{0x0000200000000000L});
    public static final BitSet FOLLOW_COLON_in_try_stmt3836 = new BitSet(new long[]{0x00000A3CCAA56280L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_suite_in_try_stmt3840 = new BitSet(new long[]{0x0000000000500000L});
    public static final BitSet FOLLOW_except_clause_in_try_stmt3853 = new BitSet(new long[]{0x0000000200500002L});
    public static final BitSet FOLLOW_ORELSE_in_try_stmt3857 = new BitSet(new long[]{0x0000200000000000L});
    public static final BitSet FOLLOW_COLON_in_try_stmt3859 = new BitSet(new long[]{0x00000A3CCAA56280L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_suite_in_try_stmt3863 = new BitSet(new long[]{0x0000000000400002L});
    public static final BitSet FOLLOW_FINALLY_in_try_stmt3869 = new BitSet(new long[]{0x0000200000000000L});
    public static final BitSet FOLLOW_COLON_in_try_stmt3871 = new BitSet(new long[]{0x00000A3CCAA56280L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_suite_in_try_stmt3875 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FINALLY_in_try_stmt3898 = new BitSet(new long[]{0x0000200000000000L});
    public static final BitSet FOLLOW_COLON_in_try_stmt3900 = new BitSet(new long[]{0x00000A3CCAA56280L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_suite_in_try_stmt3904 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WITH_in_with_stmt3953 = new BitSet(new long[]{0x00000800C0000200L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_test_in_with_stmt3955 = new BitSet(new long[]{0x0000200000001200L});
    public static final BitSet FOLLOW_with_var_in_with_stmt3959 = new BitSet(new long[]{0x0000200000000000L});
    public static final BitSet FOLLOW_COLON_in_with_stmt3963 = new BitSet(new long[]{0x00000A3CCAA56280L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_suite_in_with_stmt3965 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_with_var4000 = new BitSet(new long[]{0x0000080080000200L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_expr_in_with_var4008 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EXCEPT_in_except_clause4045 = new BitSet(new long[]{0x00002800C0000200L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_test_in_except_clause4050 = new BitSet(new long[]{0x0000A00000001000L});
    public static final BitSet FOLLOW_set_in_except_clause4054 = new BitSet(new long[]{0x00000800C0000200L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_test_in_except_clause4064 = new BitSet(new long[]{0x0000200000000000L});
    public static final BitSet FOLLOW_COLON_in_except_clause4071 = new BitSet(new long[]{0x00000A3CCAA56280L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_suite_in_except_clause4073 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simple_stmt_in_suite4119 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NEWLINE_in_suite4135 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_INDENT_in_suite4137 = new BitSet(new long[]{0x00000FFCCFA7E280L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_stmt_in_suite4146 = new BitSet(new long[]{0x00000FFCCFA7E2A0L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_DEDENT_in_suite4166 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_or_test_in_test4196 = new BitSet(new long[]{0x0000000004000002L});
    public static final BitSet FOLLOW_IF_in_test4218 = new BitSet(new long[]{0x0000080080000200L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_or_test_in_test4222 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_ORELSE_in_test4225 = new BitSet(new long[]{0x00000800C0000200L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_test_in_test4229 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_lambdef_in_test4274 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_and_test_in_or_test4309 = new BitSet(new long[]{0x0000000100000002L});
    public static final BitSet FOLLOW_OR_in_or_test4325 = new BitSet(new long[]{0x0000080080000200L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_and_test_in_or_test4329 = new BitSet(new long[]{0x0000000100000002L});
    public static final BitSet FOLLOW_not_test_in_and_test4410 = new BitSet(new long[]{0x0000000000000802L});
    public static final BitSet FOLLOW_AND_in_and_test4426 = new BitSet(new long[]{0x0000080080000200L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_not_test_in_and_test4430 = new BitSet(new long[]{0x0000000000000802L});
    public static final BitSet FOLLOW_NOT_in_not_test4514 = new BitSet(new long[]{0x0000080080000200L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_not_test_in_not_test4518 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_comparison_in_not_test4535 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expr_in_comparison4584 = new BitSet(new long[]{0x00000000B0000002L,0x000000000000007FL});
    public static final BitSet FOLLOW_comp_op_in_comparison4598 = new BitSet(new long[]{0x0000080080000200L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_expr_in_comparison4602 = new BitSet(new long[]{0x00000000B0000002L,0x000000000000007FL});
    public static final BitSet FOLLOW_LESS_in_comp_op4683 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GREATER_in_comp_op4699 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EQUAL_in_comp_op4715 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GREATEREQUAL_in_comp_op4731 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LESSEQUAL_in_comp_op4747 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ALT_NOTEQUAL_in_comp_op4763 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NOTEQUAL_in_comp_op4779 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IN_in_comp_op4795 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NOT_in_comp_op4811 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_IN_in_comp_op4813 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IS_in_comp_op4829 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IS_in_comp_op4845 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_NOT_in_comp_op4847 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_xor_expr_in_expr4899 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000080L});
    public static final BitSet FOLLOW_VBAR_in_expr4914 = new BitSet(new long[]{0x0000080080000200L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_xor_expr_in_expr4918 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000080L});
    public static final BitSet FOLLOW_and_expr_in_xor_expr4997 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000100L});
    public static final BitSet FOLLOW_CIRCUMFLEX_in_xor_expr5012 = new BitSet(new long[]{0x0000080080000200L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_and_expr_in_xor_expr5016 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000100L});
    public static final BitSet FOLLOW_shift_expr_in_and_expr5094 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000200L});
    public static final BitSet FOLLOW_AMPER_in_and_expr5109 = new BitSet(new long[]{0x0000080080000200L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_shift_expr_in_and_expr5113 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000200L});
    public static final BitSet FOLLOW_arith_expr_in_shift_expr5196 = new BitSet(new long[]{0x8000000000000002L,0x0000000000000400L});
    public static final BitSet FOLLOW_shift_op_in_shift_expr5210 = new BitSet(new long[]{0x0000080080000200L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_arith_expr_in_shift_expr5214 = new BitSet(new long[]{0x8000000000000002L,0x0000000000000400L});
    public static final BitSet FOLLOW_LEFTSHIFT_in_shift_op5298 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RIGHTSHIFT_in_shift_op5314 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_term_in_arith_expr5360 = new BitSet(new long[]{0x0000000000000002L,0x0000000000001800L});
    public static final BitSet FOLLOW_arith_op_in_arith_expr5373 = new BitSet(new long[]{0x0000080080000200L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_term_in_arith_expr5377 = new BitSet(new long[]{0x0000000000000002L,0x0000000000001800L});
    public static final BitSet FOLLOW_PLUS_in_arith_op5485 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_arith_op5501 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_factor_in_term5547 = new BitSet(new long[]{0x0001000000000002L,0x000000000000E000L});
    public static final BitSet FOLLOW_term_op_in_term5560 = new BitSet(new long[]{0x0000080080000200L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_factor_in_term5564 = new BitSet(new long[]{0x0001000000000002L,0x000000000000E000L});
    public static final BitSet FOLLOW_STAR_in_term_op5646 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SLASH_in_term_op5662 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PERCENT_in_term_op5678 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOUBLESLASH_in_term_op5694 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PLUS_in_factor5733 = new BitSet(new long[]{0x0000080080000200L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_factor_in_factor5737 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_factor5753 = new BitSet(new long[]{0x0000080080000200L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_factor_in_factor5757 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TILDE_in_factor5773 = new BitSet(new long[]{0x0000080080000200L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_factor_in_factor5777 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_power_in_factor5793 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_atom_in_power5832 = new BitSet(new long[]{0x0002080000000402L,0x0000000000020000L});
    public static final BitSet FOLLOW_trailer_in_power5837 = new BitSet(new long[]{0x0002080000000402L,0x0000000000020000L});
    public static final BitSet FOLLOW_DOUBLESTAR_in_power5852 = new BitSet(new long[]{0x0000080080000200L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_factor_in_power5854 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_atom5904 = new BitSet(new long[]{0x00001A30C0014200L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_yield_expr_in_atom5922 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_testlist_gexp_in_atom5942 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_RPAREN_in_atom5985 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LBRACK_in_atom5993 = new BitSet(new long[]{0x00000800C0000200L,0x0000000007EF1800L});
    public static final BitSet FOLLOW_listmaker_in_atom6002 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_RBRACK_in_atom6045 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LCURLY_in_atom6053 = new BitSet(new long[]{0x00000800C0000200L,0x0000000007FB1800L});
    public static final BitSet FOLLOW_dictmaker_in_atom6063 = new BitSet(new long[]{0x0000000000000000L,0x0000000000100000L});
    public static final BitSet FOLLOW_RCURLY_in_atom6110 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BACKQUOTE_in_atom6121 = new BitSet(new long[]{0x00000800C0000200L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_testlist_in_atom6123 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_BACKQUOTE_in_atom6128 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NAME_in_atom6146 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INT_in_atom6164 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LONGINT_in_atom6182 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FLOAT_in_atom6200 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COMPLEX_in_atom6218 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_in_atom6239 = new BitSet(new long[]{0x0000000000000002L,0x0000000004000000L});
    public static final BitSet FOLLOW_test_in_listmaker6282 = new BitSet(new long[]{0x0000800001000002L});
    public static final BitSet FOLLOW_list_for_in_listmaker6294 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_COMMA_in_listmaker6326 = new BitSet(new long[]{0x00000800C0000200L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_test_in_listmaker6330 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_COMMA_in_listmaker6359 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_test_in_testlist_gexp6391 = new BitSet(new long[]{0x0000800001000002L});
    public static final BitSet FOLLOW_COMMA_in_testlist_gexp6415 = new BitSet(new long[]{0x00000800C0000200L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_test_in_testlist_gexp6419 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_COMMA_in_testlist_gexp6427 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_gen_for_in_testlist_gexp6481 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LAMBDA_in_lambdef6545 = new BitSet(new long[]{0x0003280000000200L});
    public static final BitSet FOLLOW_varargslist_in_lambdef6548 = new BitSet(new long[]{0x0000200000000000L});
    public static final BitSet FOLLOW_COLON_in_lambdef6552 = new BitSet(new long[]{0x00000800C0000200L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_test_in_lambdef6554 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_trailer6593 = new BitSet(new long[]{0x00031800C0000200L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_arglist_in_trailer6602 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_RPAREN_in_trailer6644 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LBRACK_in_trailer6652 = new BitSet(new long[]{0x00002800C0000600L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_subscriptlist_in_trailer6654 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_RBRACK_in_trailer6657 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOT_in_trailer6673 = new BitSet(new long[]{0x000003FFFFFFFA00L});
    public static final BitSet FOLLOW_attr_in_trailer6675 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_subscript_in_subscriptlist6714 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_COMMA_in_subscriptlist6726 = new BitSet(new long[]{0x00002800C0000600L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_subscript_in_subscriptlist6730 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_COMMA_in_subscriptlist6737 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOT_in_subscript6780 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_DOT_in_subscript6782 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_DOT_in_subscript6784 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_test_in_subscript6814 = new BitSet(new long[]{0x0000200000000002L});
    public static final BitSet FOLLOW_COLON_in_subscript6820 = new BitSet(new long[]{0x00002800C0000202L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_test_in_subscript6825 = new BitSet(new long[]{0x0000200000000002L});
    public static final BitSet FOLLOW_sliceop_in_subscript6831 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COLON_in_subscript6862 = new BitSet(new long[]{0x00002800C0000202L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_test_in_subscript6867 = new BitSet(new long[]{0x0000200000000002L});
    public static final BitSet FOLLOW_sliceop_in_subscript6873 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_test_in_subscript6891 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COLON_in_sliceop6928 = new BitSet(new long[]{0x00000800C0000202L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_test_in_sliceop6936 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expr_in_exprlist7007 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_COMMA_in_exprlist7019 = new BitSet(new long[]{0x0000080080000200L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_expr_in_exprlist7023 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_COMMA_in_exprlist7029 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expr_in_exprlist7048 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expr_in_del_list7086 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_COMMA_in_del_list7098 = new BitSet(new long[]{0x0000080080000200L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_expr_in_del_list7102 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_COMMA_in_del_list7108 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_test_in_testlist7161 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_COMMA_in_testlist7173 = new BitSet(new long[]{0x00000800C0000200L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_test_in_testlist7177 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_COMMA_in_testlist7183 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_test_in_testlist7201 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_test_in_dictmaker7230 = new BitSet(new long[]{0x0000200000000000L});
    public static final BitSet FOLLOW_COLON_in_dictmaker7233 = new BitSet(new long[]{0x00000800C0000200L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_test_in_dictmaker7237 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_COMMA_in_dictmaker7256 = new BitSet(new long[]{0x00000800C0000200L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_test_in_dictmaker7260 = new BitSet(new long[]{0x0000200000000000L});
    public static final BitSet FOLLOW_COLON_in_dictmaker7263 = new BitSet(new long[]{0x00000800C0000200L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_test_in_dictmaker7267 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_COMMA_in_dictmaker7281 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_decorators_in_classdef7319 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_CLASS_in_classdef7322 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_NAME_in_classdef7324 = new BitSet(new long[]{0x0000280000000000L});
    public static final BitSet FOLLOW_LPAREN_in_classdef7327 = new BitSet(new long[]{0x00001800C0000200L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_testlist_in_classdef7329 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_RPAREN_in_classdef7333 = new BitSet(new long[]{0x0000200000000000L});
    public static final BitSet FOLLOW_COLON_in_classdef7337 = new BitSet(new long[]{0x00000A3CCAA56280L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_suite_in_classdef7339 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_argument_in_arglist7381 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_COMMA_in_arglist7385 = new BitSet(new long[]{0x00000800C0000200L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_argument_in_arglist7387 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_COMMA_in_arglist7403 = new BitSet(new long[]{0x0003000000000002L});
    public static final BitSet FOLLOW_STAR_in_arglist7421 = new BitSet(new long[]{0x00000800C0000200L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_test_in_arglist7425 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_COMMA_in_arglist7429 = new BitSet(new long[]{0x00000800C0000200L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_argument_in_arglist7431 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_COMMA_in_arglist7437 = new BitSet(new long[]{0x0002000000000000L});
    public static final BitSet FOLLOW_DOUBLESTAR_in_arglist7439 = new BitSet(new long[]{0x00000800C0000200L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_test_in_arglist7443 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOUBLESTAR_in_arglist7464 = new BitSet(new long[]{0x00000800C0000200L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_test_in_arglist7468 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STAR_in_arglist7515 = new BitSet(new long[]{0x00000800C0000200L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_test_in_arglist7519 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_COMMA_in_arglist7523 = new BitSet(new long[]{0x00000800C0000200L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_argument_in_arglist7525 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_COMMA_in_arglist7531 = new BitSet(new long[]{0x0002000000000000L});
    public static final BitSet FOLLOW_DOUBLESTAR_in_arglist7533 = new BitSet(new long[]{0x00000800C0000200L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_test_in_arglist7537 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOUBLESTAR_in_arglist7556 = new BitSet(new long[]{0x00000800C0000200L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_test_in_arglist7560 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_test_in_argument7599 = new BitSet(new long[]{0x0000C00001000000L});
    public static final BitSet FOLLOW_ASSIGN_in_argument7612 = new BitSet(new long[]{0x00000800C0000200L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_test_in_argument7616 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_gen_for_in_argument7642 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_list_for_in_list_iter7707 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_list_if_in_list_iter7716 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FOR_in_list_for7742 = new BitSet(new long[]{0x0000080080000200L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_exprlist_in_list_for7744 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_IN_in_list_for7747 = new BitSet(new long[]{0x00000800C0000200L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_testlist_in_list_for7749 = new BitSet(new long[]{0x0000000005000002L});
    public static final BitSet FOLLOW_list_iter_in_list_for7753 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IF_in_list_if7783 = new BitSet(new long[]{0x00000800C0000200L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_test_in_list_if7785 = new BitSet(new long[]{0x0000000005000002L});
    public static final BitSet FOLLOW_list_iter_in_list_if7789 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_gen_for_in_gen_iter7820 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_gen_if_in_gen_iter7829 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FOR_in_gen_for7855 = new BitSet(new long[]{0x0000080080000200L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_exprlist_in_gen_for7857 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_IN_in_gen_for7860 = new BitSet(new long[]{0x0000080080000200L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_or_test_in_gen_for7862 = new BitSet(new long[]{0x0000800005000002L});
    public static final BitSet FOLLOW_gen_iter_in_gen_for7865 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IF_in_gen_if7894 = new BitSet(new long[]{0x00000800C0000200L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_test_in_gen_if7896 = new BitSet(new long[]{0x0000800005000002L});
    public static final BitSet FOLLOW_gen_iter_in_gen_if7899 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_YIELD_in_yield_expr7940 = new BitSet(new long[]{0x00000800C0000202L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_testlist_in_yield_expr7942 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_synpred1_Python1252 = new BitSet(new long[]{0x0000080000000200L});
    public static final BitSet FOLLOW_fpdef_in_synpred1_Python1254 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_COMMA_in_synpred1_Python1257 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_testlist_in_synpred2_Python1635 = new BitSet(new long[]{0x7FF8000000000000L});
    public static final BitSet FOLLOW_augassign_in_synpred2_Python1638 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_testlist_in_synpred3_Python1754 = new BitSet(new long[]{0x0000400000000000L});
    public static final BitSet FOLLOW_ASSIGN_in_synpred3_Python1757 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_test_in_synpred4_Python2269 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_COMMA_in_synpred4_Python2272 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_test_in_synpred5_Python2368 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_COMMA_in_synpred5_Python2371 = new BitSet(new long[]{0x00000800C0000200L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_test_in_synpred5_Python2373 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_decorators_in_synpred6_Python3462 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_DEF_in_synpred6_Python3465 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IF_in_synpred7_Python4208 = new BitSet(new long[]{0x0000080080000200L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_or_test_in_synpred7_Python4210 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_ORELSE_in_synpred7_Python4213 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_test_in_synpred8_Python6801 = new BitSet(new long[]{0x0000200000000000L});
    public static final BitSet FOLLOW_COLON_in_synpred8_Python6804 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COLON_in_synpred9_Python6852 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expr_in_synpred10_Python6997 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_COMMA_in_synpred10_Python7000 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_test_in_synpred11_Python7148 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_COMMA_in_synpred11_Python7151 = new BitSet(new long[]{0x0000000000000002L});

}