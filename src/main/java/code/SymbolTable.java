/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package code;

import exceptions.SemanticError;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java_cup.runtime.Symbol;

/**
 *
 * @author linux
 */
class Token {
    
    private Symbol token;
    private String tokenName;
    private Object type;
    //private enum Category { CONSTANT, SRING, VARIABLE, CHAR };
    private int scope;
    

    public Token(Symbol token,String tokenName, int scope) {
        this.token = token;
        this.tokenName = tokenName;
        this.scope = scope;
        
    }

    public void setToken(Symbol token) {
        this.token = token;
    }

    public void setType(Object type) {
        this.type = type;
    }

    public void setScope(int scope) {
        this.scope = scope;
    }

    public Symbol getToken() {
        return token;
    }

    public Object getType() {
        return type;
    }

    public int getScope() {
        return scope;
    }
    
    public Object getValue(){
        return this.token.value;
    }
    
    public int getLine(){
        return this.token.left;
    }
    
    public int getColumn(){
        return this.token.right;
    }
    
    public String toString(){
        return "Token{sym: " + this.token.sym + ", Name: " + this.tokenName + ", Type: " + this.type + ", Line: " + this.token.left + ", Column: " + this.token.right + ", Value: " + this.token.value + ", Scope: " + this.scope +"}";
    }
}

public class SymbolTable {
    
    private HashMap<Integer,Object> symbolTable;
    private HashMap<Integer, String> tokens;	
    private int count;
    private int qScopes;
    private ArrayList<Object> qVariables;
    private ArrayList<GenericError> errorList;
    private ArrayList<Token> beforeToken;
    
    private Console console;

    public SymbolTable() throws IllegalArgumentException, IllegalAccessException {
        this.symbolTable = new HashMap<>();
        this.tokens = new Lexer().getTokens();
        this.count = 0;
        this.qScopes = 0;
        this.qVariables = new ArrayList<>();
        this.errorList = new ArrayList<>();
        this.beforeToken = new ArrayList<>();
    }
    
    
    public String getTokenName(int id){
        return this.tokens.get(id);
    }
    public void addToken(Symbol symbol, String type,Console console) {
        this.console = console;
        if(symbol.sym == sym.OPEN_CURLY){
            this.qScopes++;
            this.qVariables = new ArrayList<>();
        }
        if(symbol.sym == sym.CLOSE_CURLY){
            this.qScopes--;
        }
        if(symbol.sym != sym.OPEN_CURLY && symbol.sym != sym.CLOSE_CURLY){
            boolean ignore = false;
            Token token = new Token(symbol, this.getTokenName(symbol.sym), this.qScopes);
            this.beforeToken.add(token);
            if("type".equals(type) || symbol.sym == sym.COMPONENTS || symbol.sym == sym.CONFIGURATION || symbol.sym == sym.MODULE ){
                this.qVariables.add(symbol.sym);
            }
            if(!this.qVariables.isEmpty() && symbol.sym == sym.IDENTIFIER){
                token.setType(this.qVariables.get(0));
            } 
            else if(this.qVariables.isEmpty() && symbol.sym == sym.IDENTIFIER && this.beforeToken.size()>3 && (this.beforeToken.get(this.beforeToken.size()-2).getToken().sym != sym.DOT)){
                if(!this.isDefined(symbol, this.qScopes)){
                    this.errorList.add(new GenericError(symbol.left,symbol.right,"Undifinied Variable <"+symbol.value+">"));
                    //console.write("Undifinied Variable <"+symbol.value+">", Color.RED);
                    ignore = true;
                }
                
            }
            if((symbol.sym == sym.CONSTANT || symbol.sym == sym.STRING_LITERAL) && !this.beforeIsForConstant()){
                this.errorList.add(new GenericError(symbol.left,symbol.right,"Invalid Operation <"+symbol.value+">"));
                    //console.write("Invalid Operation <"+symbol.value+">", Color.RED);
            }
            if(symbol.sym == sym.SEMI_COLON || symbol.sym == sym.EQUALS){
                this.qVariables = new ArrayList<>();
            }
            if(this.isForSemantic(symbol,type) && !ignore){
                this.symbolTable.put(this.count, token);
                //console.write("ID: " + this.count + "  --   " + token.toString(), Color.BLUE);
                this.count++;
            }
        }
    }
    public void getErrors() throws SemanticError{
        for (GenericError error: this.errorList){
            throw new SemanticError(error.toString());
            
        }
    }
    private boolean isCorrectOperation(Symbol symbol){
       // if(symbol.sym == sym.CONSTANT || symbol.sym == sym.STRING_LITERAL || symbol.sym == sym.TRUE || symbol.sym == sym.FALSE || symbol.sym == sym.CHAR){
            
        //}
        return this.beforeIsForConstant();
    }
    
    private boolean beforeIsForConstant(){
        int c = 2;
        Token aux = this.beforeToken.get(this.beforeToken.size()-c);
        //this.console.write((String)aux.getType(), Color.yellow);
        if( aux.getToken().sym == sym.INCLUDE) return true;
        while(aux.getType() == null && aux.getToken().sym != sym.CONSTANT && aux.getToken().sym != sym.STRING_LITERAL){
            //this.console.write((String)aux.toString(), Color.yellow);
            if(!(aux.getToken().sym == sym.PLUS || aux.getToken().sym == sym.MINUS || aux.getToken().sym == sym.MULTIPLICATION || 
               aux.getToken().sym == sym.DIVIDE || aux.getToken().sym == sym.EQUALS)){
               return false;
            }
            c--;
            aux = this.beforeToken.get(this.beforeToken.size()-c);
        }
        //this.console.write((String)aux.getType(), Color.MAGENTA);
        if((aux.getType()!=null && (int)aux.getType() != sym.INT) && aux.getToken().sym != sym.CONSTANT){
            if(this.beforeToken.get(this.beforeToken.size()-1).getToken().sym != sym.CONSTANT)
                return false;
        }
        return true;
    }
    
    private Object returnDataType(Symbol symbol){
        if(symbol.sym == sym.IDENTIFIER && this.isDefined(symbol, this.qScopes)){
            for (Map.Entry<Integer, Object> entry : this.symbolTable.entrySet()) {
                Token current = (Token)entry.getValue();
                //this.console.write((String)current.getType(), Color.yellow);
                if(((String)current.getValue()).equals((String)symbol.value))
                    return current.getType();
            }
        }
        return -1;
    }
    
    private boolean isDefined(Symbol symbol, int scope){
        for (Map.Entry<Integer, Object> entry : this.symbolTable.entrySet()) {
            Token current = (Token)entry.getValue();
            if(symbol.value!= null && current.getValue() != null && ((String)current.getValue()).equals((String)symbol.value) && current.getScope() <= scope)
                return true;
        }
        return false;
    }
    
    private boolean isForSemantic(Symbol symbol, String type){
        return symbol.sym == sym.IDENTIFIER || symbol.sym == sym.CONSTANT
            || symbol.sym == sym.STRING_LITERAL || symbol.sym == sym.CHAR || symbol.sym == sym.TRUE
            || symbol.sym == sym.FALSE ;
    }
    
    public void abort(){
        this.count = 0;
        this.symbolTable = new HashMap<>();
    }
    
}