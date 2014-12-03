/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generator;

import code.*;
import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java_cup.runtime.Symbol;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

/**
 *
 * @author orlando
 */
public class main {
    public final static int GENERAR = 1;
    public final static int EJECUTAR = 2;
    public final static int SALIR = 4;
    public final static int LEXER = 3;
    private final static String pathGenerator = "src/main/java/generator/";
    private final static String pathCode = "src/main/java/code/";
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        java.util.Scanner in = new Scanner(System.in);
        int valor = 0;
        do {
            System.out.println("Elija una opcion: ");
            System.out.println("1) Generar");
            System.out.println("2) Ejecutar");
            System.out.println("3) Lexer");
            System.out.println("4) Salir");
            System.out.print("Opcion: ");
            valor = in.nextInt();
            switch (valor) {
                /*  Generamos el analizador lexico y sintactico.
                 lcalc.flex contiene la definicion del analizador lexico
                 ycalc.cup contiene la definicion del analizador sintactico
                 */
                case GENERAR: {
                    System.out.println("\n*** Generando ***\n");
                    String archLexico = "";
                    String archSintactico = "";
                    if (args.length > 0) {
                        System.out.println("\n*** Procesando archivos custom ***\n");
                        archLexico = args[0];
                        archSintactico = args[1];
                    } else {
                        System.out.println("\n*** Procesando archivo default ***\n");
                        archLexico = pathGenerator + "lexer.flex";
                        archSintactico = pathGenerator + "parser.cup";
                    }
                    String[] alexico = {archLexico};
                    String[] asintactico = {"-parser", "Parser", archSintactico};
                    jflex.Main.main(alexico);
                    try {
                        java_cup.Main.main(asintactico);
                    } catch (Exception ex) {
                        Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    //movemos los archivos generados
                    boolean mvAL = moveFile(pathGenerator + "Lexer.java", pathCode);
                    boolean mvAS = moveFile("Parser.java", pathCode);
                    boolean mvSym= moveFile("sym.java", pathCode);
                    if(mvAL && mvAS && mvSym){
                        System.exit(0);
                    }
                    System.out.println("OK");
                    break;
                }
                case EJECUTAR: {
                    /*  Ejecutamos el analizador lexico y sintactico
                     sobre un archivo de pruebas. 
                     */
                    ejecutar();
                    System.out.println("Ejecutado!");
                    break;
                }
                case LEXER: {
                    lexer();
                    break;
                }
                case SALIR: {
                    System.out.println("Adios!");
                    break;
                }
                default: {
                    System.out.println("Opcion no valida!");
                    break;
                }
            }
        } while (valor != SALIR);
    }
    private static void lexer() throws FileNotFoundException, Exception{
        JTextPane textPane = new JTextPane();
        StyledDocument doc = textPane.getStyledDocument();

        Style style = textPane.addStyle("I'm a Style", null);
        
        
        //Lexer lx = new Lexer(new FileReader("test.txt"));     
        
        Lexer lx = new Lexer(new StringReader("module SenseC {\n" +
                            " uses interface Boot;\n" +
                            " uses interface Leds;\n" +
                            " uses interface Timer<TMilli>;\n" +
                            " uses interface Read<uint16_t>;\n" +
                            "}"
                             + "//comment"));
        ReadXML xml = new ReadXML();
        HashMap<String, Triplet> styles = xml.getAttributes();
        while(true){
            Symbol token = null;
            try{
                token = lx.next_token();
            }catch(Error e){
                System.err.println(e.getMessage());
                try { 
                    StyleConstants.setForeground(style, Color.RED);
                    doc.insertString(doc.getLength(), "Error: " + e.getMessage() + " Line: " + lx.getLexerError().getLine() +  " " + lx.yyline()+ "\n", style); 
                }
                catch (BadLocationException e2){}
            }
            /*if(lx.ignore.get(lx.ignore.size()-1).get1()!=null){
                int key = lx.ignore.size()-1;
                try { 
                    StyleConstants.setForeground(style, Color.GRAY);
                    doc.insertString(doc.getLength(), "Keyword: " + lx.ignore.get(key-1).toString()+ " " + "KeywordClass: " +(String)lx.ignore.get(key-1).get3() + " Line: " +lx.yyline()+ "\n", style); 
                }
                catch (BadLocationException e){}
               System.err.println(lx.ignore.get(lx.ignore.size()-1).toString());
               lx.ignore.remove(key);
            }*/
            
            if(token != null && 1 >= token.sym && -2 != token.sym){
                //lx.ignore = new ArrayList<>();
                break;
            }
            if(token != null){
                switch(lx.getKeywordClass()){
                    case "COMMENT":{
                        StyleConstants.setForeground(style, Color.RED);
                        break;
                    }
                    case "common_define":{
                        //System.err.println((String)((Triplet)styles.get("INSTRUCTION WORD")).get1() + "|" + (String)((Triplet)styles.get("INSTRUCTION WORD")).get1());
                        if((lx.getKeyword()).matches((String)((Triplet)styles.get("INSTRUCTION WORD")).get1() + "|" + (String)((Triplet)styles.get("TYPE WORD")).get1()))
                            StyleConstants.setForeground(style, Color.BLUE);
                        else
                            StyleConstants.setForeground(style, Color.BLACK);
                        
                        StyleConstants.setForeground(style, Color.BLUE);
                        break;
                    }

                    default:{
                        StyleConstants.setForeground(style, Color.BLACK);
                        break;
                    }
                }

                try { 
                    System.out.println(lx.getKeywordClass()+ " " + lx.yytext());
                    doc.insertString(doc.getLength(), "Keyword: " + lx.getKeyword() + " " + "KeywordClass: " +lx.getKeywordClass() + " Line: " +token.left+ "\n", style); 
                }
                catch (BadLocationException e){}
            }
            //System.err.println(lx.getKeywordClass()+ " " + lx.yytext());
        }
        JScrollPane js = new JScrollPane(textPane);
        JFrame frame = new JFrame();
        frame.add(js);
        frame.pack();
        frame.setSize(600, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    private static void ejecutar() throws FileNotFoundException, Exception{
       
        Parser parser = new Parser(new Lexer(new StringReader("configuration Blink {\n" +
        "}\n" +
        "implementation {\n" +
        "  components Main, BlinkM, SingleTimer, LedsC;\n" +
        "\n" +
        "  Main.StdControl -> BlinkM.StdControl;\n" +
        "  Main.StdControl -> SingleTimer.StdControl;\n" +
        "  BlinkM.Timer -> SingleTimer.Timer;\n" +
        "  BlinkM.Leds -> LedsC;\n" +
        "}")));
        

	
        try{
            Symbol result = parser.debug_parse();
            //System.err.println(parser.getError().toString());
        }
        catch(Error e){
            
             System.err.println(parser.getError().toString());
        }
       
        //System.out.println(parser.result[0] + " - " + parser.result[0] );
      
       /* while(true){
            Symbol token = parser.scan();
            if(1 >= token.sym){
                return;
            }
            if(token.sym == sym.error)
                System.out.println("Error: " + parser.parse().toString() + parser.yytext() + " [" + parser.yyline() + ", " + parser.yycolumn() + "] ");
            else
                System.out.println("Ok: " + parser.error_sym() + " - "+ parser.yytext() + " [" + parser.yyline() + ", " + parser.yycolumn() + "]" );
            switch(token.sym){
                case sym.CONFIGURATION:{
                    System.out.println("confi: " + parser.lexeme() + );
                }
                case sym.IDENTIFIER:{
                    System.out.println();
                }
                default:{
                    System.out.println(token.toString());
                }
                
            }
        }*/
        
    }
    private static boolean moveFile(String pathFrom,String pathTo){
        try{
 
    	   File afile =new File(pathFrom);
 
    	   if(afile.renameTo(new File(pathTo + afile.getName()))){
    		return true;
    	   }
    	}catch(Exception e){
    	}
        return false;
    }
}
