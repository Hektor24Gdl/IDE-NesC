package com.idenesc.parser;

import code.Lexer;
import code.Parser;
import exceptions.LexerError;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.testng.annotations.*;

/**
 *
 * @author Héctor Guillermo Rodríguez Fuentes <hectr.rodgz@gmail.com>
 */
public class ParserTests {
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
    public void successBlink() throws LexerError, Exception {
        ejecutar(ParserTests.class.getResourceAsStream("/valid/Blink.nc"));
    }
    
    @Test(groups = {s})
    public void successBlinkM() throws LexerError, Exception {
        ejecutar(ParserTests.class.getResourceAsStream("/valid/BlinkM.nc"));
    }
    
    @Test(groups = {s})
    public void successRadioCountToLedsC() throws LexerError, Exception {
        ejecutar(ParserTests.class.getResourceAsStream("/valid/RadioCountToLedsC.nc"));
    }
    
    @Test(groups = {s})
    public void successRadioCountToLedsC_1() throws LexerError, Exception {
        ejecutar(ParserTests.class.getResourceAsStream("/valid/RadioCountToLedsC_1.nc"));
    }
    
    @Test(groups = {s})
    public void successLinkEstimator() throws LexerError, Exception {
        ejecutar(ParserTests.class.getResourceAsStream("/valid/LinkEstimator.nc"));
    }
    
    @Test(groups = {s})
    public void successLinkEstimatorP() throws LexerError, Exception {
        ejecutar(ParserTests.class.getResourceAsStream("/valid/LinkEstimatorP.nc"));
    }
    
    @Test(groups = {s})
    public void successCtpForwardingEngineP() throws LexerError, Exception {
        ejecutar(ParserTests.class.getResourceAsStream("/valid/CtpForwardingEngineP.nc"));
    }
    
    @Test(groups = {s})
    public void successCtpInstrumentation() throws LexerError, Exception {
        ejecutar(ParserTests.class.getResourceAsStream("/valid/CtpInstrumentation.nc"));
    }
    
    @Test(groups = {s})
    public void successCtpInstrumentationP() throws LexerError, Exception {
        ejecutar(ParserTests.class.getResourceAsStream("/valid/CtpInstrumentationP.nc"));
    }
    
    @Test(groups = {s})
    public void successCtpP() throws LexerError, Exception {
        ejecutar(ParserTests.class.getResourceAsStream("/valid/CtpP.nc"));
    }
    
    @Test(groups = {s})
    public void successCtpRoutingEngineP() throws LexerError, Exception {
        ejecutar(ParserTests.class.getResourceAsStream("/valid/CtpRoutingEngineP.nc"));
    }
    
    @Test(groups = {s})
    public void successPowerNetBaseC() throws LexerError, Exception {
        ejecutar(ParserTests.class.getResourceAsStream("/valid/PowerNetBaseC.nc"));
    }
    
    @Test(groups = {s})
    public void successPowerNetBaseP() throws LexerError, Exception {
        ejecutar(ParserTests.class.getResourceAsStream("/valid/PowerNetBaseP.nc"));
    }

    /*@Test(groups = {e}, expectedExceptions = Error.class, description = "Identificar inválido. int 1v1 = 5;")
    public void invalidIdentifier() throws IOException, LexerError, Exception {
        ejecutar(ParserTests.class.getResourceAsStream("/avroraz/invalidId.txt"));
    }*/
    
    public void ejecutar(InputStream file) throws LexerError, Exception {
        Lexer lx = new Lexer(new InputStreamReader(file));
        lx.setToParser(true);
        Parser parser = new Parser(lx);
        if(parser.getError().getMessage() != null)
        {
            throw new Exception(parser.getError().toString());
        }
        System.err.println(parser.getError().toString());
        System.out.println("Done..");
    }
}
