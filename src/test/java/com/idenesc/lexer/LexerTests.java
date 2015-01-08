package com.idenesc.lexer;

import code.GenericError;
import code.Lexer;
import code.Parser;
import exceptions.LexerError;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java_cup.runtime.Symbol;
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
    }

    @Test(groups = {e}, expectedExceptions = Error.class, description = "Identificar inválido. int 1v1 = 5;")
    public void invalidIdentifier() throws IOException, LexerError {
        reloadFile(LexerTests.class.getResourceAsStream("/avroraz/invalidId.txt"));
    }
    
    @Test(groups = {e}, expectedExceptions = LexerError.class, description = "Suma incorrecta. v2 = v1++v1;")
    public void invalidVariable() throws IOException, LexerError {
        reloadFile(LexerTests.class.getResourceAsStream("/avroraz/invalidVariable.txt"));
    }
    
    @Test(groups = {e}, expectedExceptions = Error.class, description = "Tipo de dato incorrecto. 1int v2;")
    public void invalidType() throws IOException, LexerError {
        reloadFile(LexerTests.class.getResourceAsStream("/avroraz/invalidType.txt"));
    }

    @Test(groups = {e}, expectedExceptions = Error.class, description = "Corchete sin cierre. int[ v2;")
    public void invalidSquareBrackets() throws IOException, LexerError {
        reloadFile(LexerTests.class.getResourceAsStream("/avroraz/invalidSquareBrackets.txt"));
    }
    
    public void reloadFile(InputStream file) throws IOException, LexerError{
        lexer = new Lexer(new InputStreamReader(file));
        Symbol oSymbol = lexer.next_token();
        while(oSymbol.sym > 0 || oSymbol.sym == -2)
        {
            oSymbol = lexer.next_token();
        }
    }
}
