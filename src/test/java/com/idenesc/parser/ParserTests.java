package com.idenesc.parser;

import code.Lexer;
import code.Parser;
import exceptions.LexerError;
import java.io.FileNotFoundException;
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
    public void RadioCountToLedsAppC() throws LexerError, Exception {
        ejecutar(ParserTests.class.getResourceAsStream("/valid/RadioCountToLedsAppC.nc"));
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
    
    @Test(groups = {s})
    public void CC2420RssiC() throws LexerError, Exception {
        ejecutar(ParserTests.class.getResourceAsStream("/valid/CC2420RssiC.nc"));
    }
    
    @Test(groups = {s})
    public void NoiseAppC() throws LexerError, Exception {
        ejecutar(ParserTests.class.getResourceAsStream("/valid/NoiseAppC.nc"));
    }
    
    @Test(groups = {s})
    public void NoiseSampleP() throws LexerError, Exception {
        ejecutar(ParserTests.class.getResourceAsStream("/valid/NoiseSampleP.nc"));
    }
    
    @Test(groups = {s})
    public void StdControl() throws LexerError, Exception {
        ejecutar(ParserTests.class.getResourceAsStream("/valid/StdControl.nc"));
    }
    
    @Test(groups = {s})
    public void Timer() throws LexerError, Exception {
        ejecutar(ParserTests.class.getResourceAsStream("/valid/Timer.nc"));
    }

    @Test(groups = {e}, expectedExceptions = FileNotFoundException.class, description = "Archivo no valido")
    public void invalidIdentifier() throws LexerError, Exception {
        ejecutar(ParserTests.class.getResourceAsStream("/invalid/invalidId.nc"));
    }
    
    public void ejecutar(InputStream file) throws FileNotFoundException, LexerError, Exception {
        if(file == null)
        {
            throw new FileNotFoundException("Error el archivo no existe.");
        }
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
