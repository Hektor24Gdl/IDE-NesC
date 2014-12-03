/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package code;

/**
 * Class for interpret an error.
 * @author orlando
 */
public class ParserError{
    private int line;
    private int column;
    private String message;

    public ParserError() {
    }
    /**
     * Function thar return the line number where the error has been occur.
     * @return line
     */
    public int getLine() {
        return line;
    }
    
    /**
     * Function to update the line.
     * WARNING: Do no touch it for correctness
     * @param line 
     */
    public void setLine(int line) {
        this.line = line;
    }
    
    /**
     * Function that return the column number where the error has been occur.
     * @return column
     */
    public int getColumn() {
        return column;
    }

    /**
     * Function to update the column.
     * WARNING: Do no touch it for correctness
     * @param column 
     */
    public void setColumn(int column) {
        this.column = column;
    }
    
    /**
     * Function that return the error message.
     * @return 
     */
    public String getMessage() {
        return message;
    }
    
    /**
     * Function to update the message.
     * WARNING: Do no touch it for correctness
     * @param message 
     */
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ParserError{" + "line=" + line + ", column=" + column + ", message=" + message + '}';
    }
    
}