/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package code;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java_cup.runtime.Symbol;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 * Class for make a JTextPane with personalized fonts for the lexer.
 * @author orlando
 */
public class DocumentNC {
    ReadXML xml;
    static HashMap<String, Triplet> styles;
    public DocumentNC() throws ParserConfigurationException, SAXException, IOException {
        xml = new ReadXML();
        styles = xml.attributes;
    }
    
    /**
     * Function that do the lexer and call the parser function.
     * @param file to parse
     * @param console
     * @return jTextPane
     * @throws FileNotFoundException
     * @throws Exception 
     */
    public JTextPane lexer(FileReader file,JTextPane textPane,JTextPane console) {
        console.setText(null);
        textPane.setText(null);
        //JTextPane textPane = new JTextPane();
        Lexer lx = new Lexer(file);
        
        
        StyledDocument doc = textPane.getStyledDocument();
        Style style = textPane.addStyle("I'm a Style", null);
        
        StyledDocument docConsole = console.getStyledDocument();
        Style styleConsole = console.addStyle("I'm a Style", null);
        

        //Lexer lx = new Lexer(new StringReader("int boolean true"));

        while(true){
            Symbol token = null; 
            try{
                token = lx.next_token();
            }
            catch(Error e ){
                try {
                    StyleConstants.setForeground(style, Color.BLACK);
                    doc.insertString(doc.getLength(), lx.yytext(), style);
                    StyleConstants.setForeground(styleConsole, Color.RED);
                    System.out.println(lx.yytext());
                    docConsole.insertString(docConsole.getLength(),e.getMessage() + " Line: " + (lx.yyline()+1) + "\n", styleConsole);
                    System.out.println(lx.getLexerError().getMessage());
                } catch (BadLocationException ex) {
                    Logger.getLogger(DocumentNC.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (IOException ex) {
                 try {
                    docConsole.insertString(docConsole.getLength(), ex.getMessage()+"\n", styleConsole);
                } catch (BadLocationException ex2) {
                    Logger.getLogger(DocumentNC.class.getName()).log(Level.SEVERE, null, ex2);
                }
            }
            /** while(!lx.ignore.isEmpty()){
                int key = lx.ignore.size()-1;
                String extra = "";
                if("COMMENT".equals((String)lx.ignore.get(key).get3())){
                   StyleConstants.setForeground(style, Color.GRAY);
                }
                else
                   StyleConstants.setForeground(style, Color.BLACK);
                try { 
                    doc.insertString(doc.getLength(), (String)lx.ignore.get(key).get2(), style); 
                }
                catch (BadLocationException e){}
                //System.err.println(lx.ignore);
                lx.ignore.remove(key);
            }*/
            
            
            if(token !=null && 1 >= token.sym && -2 != token.sym){
               // lx.ignore = new ArrayList<>();
                break;
            }
                
            
            
            if(token != null){
                switch(lx.getKeywordClass()){
                    case "COMMENT":{
                        StyleConstants.setForeground(style, Color.GRAY);
                        break;
                    }
                    case "common_define": case "type":{
                        StyleConstants.setForeground(style, Color.BLUE);
                        break;
                    }
                    case "String": case "Character": case "Char":{
                        StyleConstants.setForeground(style, Color.ORANGE);
                        break;
                    }
                    case "ID":{
                        //System.err.println((String)((Triplet)styles.get("INSTRUCTION WORD")).get1() + "|" + (String)((Triplet)styles.get("INSTRUCTION WORD")).get1());
                        if((lx.getKeyword()).matches((String)((Triplet)styles.get("INSTRUCTION WORD")).get1() + "|" + (String)((Triplet)styles.get("TYPE WORD")).get1()))
                            StyleConstants.setForeground(style, Color.BLUE);
                        else
                            StyleConstants.setForeground(style, Color.BLACK);
                        StyleConstants.setForeground(style, Color.GREEN);
                        break;

                    }
                    default:{
                        StyleConstants.setForeground(style, Color.BLACK);
                        break;
                    }
                }

                try { 
                    doc.insertString(doc.getLength(), lx.yytext(), style); 
                    if(token.sym != -2){
                        StyleConstants.setForeground(styleConsole, Color.BLACK);
                        docConsole.insertString(docConsole.getLength(), "Class: " + lx.getKeywordClass() + " Keyword: <" + lx.yytext() + ">\n", styleConsole); 
                    }
                }
                catch (BadLocationException e){}
                
            }
           
            //System.err.println(lx.getKeywordClass()+ " " + lx.yytext());
        }
         try {
             //lx.yyreset(file);
             //ParserError pError= new ParserError();
            //pError = this.parser(lx,console);
        }  catch (Exception ex) {
            Logger.getLogger(DocumentNC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return textPane;
        
    }
    /**
     * Private function that is call in the public function lexer.
     * @param lexer
     * @return parserError
     * @throws FileNotFoundException
     * @throws Exception 
     */
    private ParserError parser(Lexer lx,JTextPane console) throws FileNotFoundException, Exception{
        StyledDocument docConsole = console.getStyledDocument();
        Style styleConsole = console.addStyle("I'm a Style", null);
        StyleConstants.setForeground(styleConsole, Color.RED);

        Parser parser = new Parser(lx);
        try{
            Symbol result = parser.parse(); 
            
            
        }
        catch(Error e){
            docConsole.insertString(docConsole.getLength(), parser.getError().toString(), styleConsole);
            return parser.getError();
        }
        return null;
    }
}