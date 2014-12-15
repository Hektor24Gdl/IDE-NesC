/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package code;

import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

/**
 *
 * @author Manuel Orlando Muñoz Sandoval <p>momunoz@gdl.cinvestav.mx</p>
 */
public class Console {
    StyledDocument doc;
    Style style;
    JTextPane console;
    
    /**
     * Constructor
     * @param console 
     */
    public Console(JTextPane console){
        this.console = console;
        this.style = this.console.addStyle("", null);
        this.doc = this.console.getStyledDocument();
    }
    
    /**
     * Function that write a text with a color
     * @param text
     * @param color 
     */
    public void write(String text, Color color){
        StyleConstants.setForeground(this.style, color);
        try {
            this.doc.insertString(this.doc.getLength(), text + "\n", this.style);
        } catch (BadLocationException ex) {
            Logger.getLogger(Console.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Fucntion that write a lexer success
     */
    public void writeLexerSuccess(){
         this.write("  --Lexer >> SUCCESS" , Color.decode("0x04B404"));
    }
    
    /**
     * Function that write a lexer error
     * @param message 
     */
    public void writeLexerError(String message){
        this.write("  --Lexer >> " + message, Color.RED);
    }
    
    /**
     * Function that write a parser success
     */
    public void writeParserSuccess(){
         this.write("  --Parser >> SUCCESS" , Color.decode("0x04B404"));
    }
    
    /**
     * Function that write a parser error
     * @param message 
     */
    public void writeParserError(String message){
        this.write("  --Parser >> " + message, Color.RED);
    }
    
    /**
     * Function that write a unexpected error
     * @param message 
     */
    public void writeUnexpectedError(String message){
        this.write("Unexpected Error: " + message, Color.RED);
    }
    
    /**
     * Function that clear the console
     */
    public void clear(){
        this.console.setText(null);
    }
}
