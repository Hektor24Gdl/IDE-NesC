/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 *
 * @author Manuel Orlando Muñoz Sandoval
 */
public class LexerError extends Exception {
    private final String message;
    // Constructor
    public LexerError(String message){
        this.message = message;
    }
 
    // Excepcion: Error Provocado
    @Override
    public String getMessage(){
        return message;
    }
}
