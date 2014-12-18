/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 *
 * @author linux
 */
public class SemanticError extends Exception{
    private final String message;
    // Constructor
    public SemanticError(String message){
        this.message = message;
    }
 
    // Excepcion: Error Provocado
    @Override
    public String getMessage(){
        return message;
    }
}