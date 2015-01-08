package com.idenesc.file;

import exceptions.InvalidFileExeption;
import code.FileManagement;
import code.Lexer;
import com.idenesc.parser.ParserTests;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import org.testng.annotations.*;

/**
 *
 * @author Héctor Guillermo Rodríguez Fuentes <hectr.rodgz@gmail.com>
 */
public class FileManagementTests {
    public static final String s = "SUCCESS";
    public static final String e = "ERROR";
    
    @BeforeClass
    public void setUp() {
    }
    
    @AfterClass
    public void tearDown() {
    }
    
    @Test(groups = {s})
    public void SaveNCFile() throws InvalidFileExeption, IOException  {
        URL oUrl = ParserTests.class.getResource("/file/save.nc");
        String sPath = oUrl.getPath();
        
        FileManagement oFileManagement = new FileManagement();
        boolean result = oFileManagement.saveFile(sPath,"configuration Blink {\n" +
            "int c;\n" +
            "}\n" +
            "implementation {\n" +
            "  components Main, BlinkM, SingleTimer, LedsC;\n" +
            " int c= 2;\n" +
            "int d = 3;\n" +
            "c = d;\n" +
            "  Main.StdControl -> BlinkM.StdControl;\n" +
            "  Main.StdControl -> SingleTimer.StdControl;\n" +
            "  BlinkM.Timer -> SingleTimer.Timer;\n" +
            "  BlinkM.Leds -> LedsC;\n" +
            "}\n" +
            "");
        if(result == false)
        {
            throw new IOException();
        }
    }
    
    @Test(groups = {s})
    public void OpenValidNCFile() throws InvalidFileExeption  {
        URL oUrl = ParserTests.class.getResource("/valid/Timer.nc");
        String sPath = oUrl.getPath();
        File oFile = new File(sPath);
        
        FileManagement oFileManagement = new FileManagement();
        String sResult = oFileManagement.readFile(oFile);
        if(sResult.isEmpty())
        {
            throw new InvalidFileExeption();
        }
    }
    
    @Test(groups = {e}, expectedExceptions = InvalidFileExeption.class, description = "Archivo no valido")
    public void OpenInvalidNCFile() throws InvalidFileExeption  {
        URL oUrl = ParserTests.class.getResource("/avroraz/test.txt");
        String sPath = oUrl.getPath();
        File oFile = new File(sPath);
        
        ///TODO: Se agrego codigo para pruebas
        String ext = FileManagement.getExtension(oFile);
        if(ext.isEmpty())
        {
            throw new InvalidFileExeption();
        }
        else if(!("nc".equals(ext) || "ob".equals(ext)))
        {
            throw new InvalidFileExeption();
        }
        
        FileManagement oFileManagement = new FileManagement();
        oFileManagement.readFile(oFile);
    }
    
    @Test(groups = {e}, expectedExceptions = InvalidFileExeption.class, description = "Archivo no valido")
    public void OpenInvalidFile() throws InvalidFileExeption  {
        File oFile = new File("");
        
        FileManagement oFileManagement = new FileManagement();
        String sResult = oFileManagement.readFile(oFile);
        if(sResult.isEmpty())
        {
            throw new InvalidFileExeption();
        }
    }
}
