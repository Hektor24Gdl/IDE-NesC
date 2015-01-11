/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package code;

import exceptions.LexerError;
import exceptions.SemanticError;
import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java_cup.runtime.Symbol;
import javax.swing.JTextPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 * Class for make a JTextPane with personalized fonts for the lexer.
 *
 * @author orlando
 */
public class DocumentNC {

    ReadXML xml;
    static HashMap<String, Triplet> styles;
    private Console console;

    public DocumentNC() throws ParserConfigurationException, SAXException, IOException {
        xml = new ReadXML();

        styles = xml.attributes;
    }

    /**
     * Function that do the lexer and call the parser function.
     *
     * @param file to parse
     * @param textPane the principal text pane
     * @param console the console pane
     * @return jTextPane
     * @throws FileNotFoundException
     * @throws java.lang.IllegalAccessException
     */
    public JTextPane lexer(File file, JTextPane textPane, JTextPane console) throws FileNotFoundException, IllegalArgumentException, IllegalAccessException {
        int caretPosition = textPane.getCaretPosition(); //Posision del cursor

        this.console = new Console(console);
        this.console.clear();
        textPane.setText(null);

        //Crear lexer
        FileReader fileReader = new FileReader(file);
        Lexer lx = new Lexer(fileReader);
        boolean flagErrorLexer = false;

        StyledDocument doc = textPane.getStyledDocument();
        Style style = textPane.addStyle("I'm a Style", null);
        String idStyle;

        while (true) {
            Symbol token = null;
            try {
                token = lx.next_token();
            } 
            catch (LexerError ex) {
                try {
                    //Se inserta la cadena con errores
                    flagErrorLexer = true;
                    StyleConstants.setForeground(style, Color.BLACK);
                    doc.insertString(doc.getLength(), lx.yytext(), style);
                    this.console.writeLexerError(ex.getMessage() + " Line: " + (lx.yyline() + 1));

                } catch (BadLocationException ex1) {
                    Logger.getLogger(DocumentNC.class.getName()).log(Level.SEVERE, null, ex1);
                }
            } 
            catch (IOException ex) {
                flagErrorLexer = true;
                this.console.writeUnexpectedError(ex.getMessage());
                Logger.getLogger(DocumentNC.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if (token != null && 1 >= token.sym && -2 != token.sym) {
                break;
            }

            //Se pinta el token
            if (token != null) {
                switch (lx.getKeywordClass()) {
                    case "COMMENT": {
                        idStyle = "COMMENT";
                        break;
                    }
                    case "common_define": {
                        idStyle = "INSTRUCTION WORD";
                        break;
                    }
                    case "type": {
                        idStyle = "TYPE WORD";
                        break;
                    }
                    case "String":
                    case "Character": {
                        idStyle = "STRING";
                        break;
                    }
                    case "Char": {
                        idStyle = "STRING";
                        break;
                    }
                    case "ID": {
                        idStyle = "IDENTIFIER";
                        break;
                    }
                    default: {
                        idStyle = "DEFAULT";
                        break;
                    }
                }

                try {
                    if (!"".equals(idStyle)) {
                        doc.insertString(doc.getLength(), lx.yytext(), (AttributeSet) ((Triplet) styles.get(idStyle)).get2());
                    } else {
                        StyleConstants.setForeground(style, Color.BLACK);
                        doc.insertString(doc.getLength(), lx.yytext(), style);
                    }
                } 
                catch (BadLocationException ex) {
                    this.console.writeUnexpectedError(ex.getMessage());
                }
            }
        }
        if (!flagErrorLexer) {
            this.console.writeLexerSuccess();
        }
        textPane.setCaretPosition(caretPosition);
        return textPane;

    }

    public JTextPane parser(File file, JTextPane textPane, JTextPane console) throws FileNotFoundException, IllegalArgumentException, IllegalAccessException {
        int caretPosition = textPane.getCaretPosition(); //Posision del cursor

        this.console = new Console(console);
        FileReader fileReader = new FileReader(file);
        SymbolTable symbols = new SymbolTable(); 
        
        boolean semantic = true;
        this.console.clear();
        textPane.setText(null);
        //JTextPane textPane = new JTextPane();
        Lexer lx = new Lexer(fileReader);
        boolean flagErrorLexer = false;

        StyledDocument doc = textPane.getStyledDocument();
        Style style = textPane.addStyle("I'm a Style", null);
        String idStyle = "";

   

        //Lexer lx = new Lexer(new StringReader("int boolean true"));
        while (true) {
            Symbol token = null;
            try {
                token = lx.next_token();
            } catch (LexerError ex) {
                try {
                    flagErrorLexer = true;
                    symbols.abort();
                    semantic = false;
                    
                    StyleConstants.setForeground(style, Color.BLACK);
                    doc.insertString(doc.getLength(), lx.yytext(), style);
                    
                    this.console.writeLexerError( ex.getMessage() + " Line: " + (lx.yyline() + 1));
                   // Logger.getLogger(DocumentNC.class.getName()).log(Level.SEVERE, null, ex);
                    
                } catch (BadLocationException ex1) {
                    Logger.getLogger(DocumentNC.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }
            catch (IOException ex) {
                flagErrorLexer = true;
                this.console.writeUnexpectedError(ex.getMessage());
                Logger.getLogger(DocumentNC.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (token != null && 1 >= token.sym && -2 != token.sym) {
                break;
            }

            if (token != null) {
                if(-2 != token.sym && semantic){
                    symbols.addToken(token, lx.getKeywordClass(), this.console);
                }
                switch (lx.getKeywordClass()) {
                    case "COMMENT": {
                        idStyle = "COMMENT";
                        break;
                    }
                    case "common_define": {
                        idStyle = "INSTRUCTION WORD";
                        break;
                    }
                    case "type": {
                        idStyle = "TYPE WORD";
                        break;
                    }
                    case "String":
                    case "Character":{
                        idStyle = "STRING";
                        break;
                    }
                    case "Char": {
                        idStyle = "STRING";
                        break;
                    }
                    case "ID": {
                        
                        idStyle = "IDENTIFIER";
                        break;

                    }
                    default: {
                        idStyle = "DEFAULT";
                        break;
                    }
                }
                   
                try {
                    if(!"".equals(idStyle))
                        doc.insertString(doc.getLength(), lx.yytext(), (AttributeSet)((Triplet) styles.get(idStyle)).get2());
                    else{
                        StyleConstants.setForeground(style, Color.BLACK);
                        doc.insertString(doc.getLength(), lx.yytext(), style);
                    }
                } catch (BadLocationException ex) {
                    this.console.writeUnexpectedError(ex.getMessage());
                }

            }
        }
        if (!flagErrorLexer) {

            this.console.writeLexerSuccess();
            
            try {
                lx = new Lexer(new FileReader(file.getAbsolutePath()));
                lx.setToParser(true);
                
                GenericError pError = this.doParser(lx);
                if( pError!= null && !(pError.getLine()>=0)){
                    symbols = null;
                    semantic = false;
                }
                else{
                    try{
                    symbols.getErrors();
                    }
                    catch(SemanticError ex){
                        this.console.write("  --Semantic >> " +ex.getMessage(), Color.red);
                    }
                }
                    
                
            } catch (Exception ex) {
                this.console.writeUnexpectedError(ex.getMessage());
                Logger.getLogger(DocumentNC.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        textPane.setCaretPosition(caretPosition);
        return textPane;

    }

    /**
     * Private function that is call in the public function lexer.
     *
     * @param lexer
     * @return parserError
     * @throws FileNotFoundException
     * @throws Exception
     */
    private GenericError doParser(Lexer lx) {
        Parser parser = new Parser(lx);
        try {
            try {
                parser.parse();
            } catch (Exception ex) {
                this.console.writeUnexpectedError(ex.getMessage());
                Logger.getLogger(DocumentNC.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (parser.getError().getLine() == 0) {
                this.console.writeParserSuccess();
            }
        } catch (Error e) {
            this.console.writeParserError(parser.getError().toString());
            return parser.getError();
        }
        return null;
    }
}
