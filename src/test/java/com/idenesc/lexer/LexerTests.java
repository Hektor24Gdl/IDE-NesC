package com.idenesc.lexer;

import code.Lexer;
import exceptions.LexerError;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.testng.annotations.*;

/**
 *
 * @author Héctor Guillermo Rodríguez Fuentes <hectr.rodgz@gmail.com>
 */
public class LexerTests {
    public static final String s = "SUCCESS";
    public static final String e = "ERROR";
    public static Lexer lexer;
    
    @BeforeClass
    public void setUp() {
        lexer = new Lexer(null);
    }
    
    @AfterClass
    public void tearDown() {
        
    }

    @Test(groups = {s})
    public void success() throws IOException, LexerError {
        reloadFile(LexerTests.class.getResourceAsStream("/avroraz/test.txt"));
        iterateTokens();
    }

    @Test(groups = {e}, expectedExceptions = Error.class, description = "Identificar inválido. int 1v1 = 5;")
    public void invalidIdentifier() throws IOException, LexerError {
        reloadFile(LexerTests.class.getResourceAsStream("/avroraz/invalidId.txt"));
        iterateTokens();
    }
    
    @Test(groups = {e}, expectedExceptions = Error.class, description = "Suma incorrecta. v2 = v1++v1;")
    public void invalidVariable() throws IOException, LexerError {
        reloadFile(LexerTests.class.getResourceAsStream("/avroraz/invalidVariable.txt"));
        iterateTokens();
    }
    
    @Test(groups = {e}, expectedExceptions = Error.class, description = "Tipo de dato incorrecto. 1int v2;")
    public void invalidType() throws IOException, LexerError {
        reloadFile(LexerTests.class.getResourceAsStream("/avroraz/invalidType.txt"));
        iterateTokens();
    }

    @Test(groups = {e}, expectedExceptions = Error.class, description = "Corchete sin cierre. int[ v2;")
    public void invalidSquareBrackets() throws IOException, LexerError {
        reloadFile(LexerTests.class.getResourceAsStream("/avroraz/invalidSquareBrackets.txt"));
        iterateTokens();
    }
    
    public void iterateTokens() throws LexerError, IOException {
        while(lexer.next_token().sym > 0){}
    }
    
    public void reloadFile(InputStream file){
        lexer.yyreset(new InputStreamReader(file));
    }
}
