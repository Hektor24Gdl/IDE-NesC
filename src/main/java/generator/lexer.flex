
/* --------------------------Codigo de Usuario----------------------- */
package code;

import java_cup.runtime.*;
import java.lang.reflect.Field;
import java.util.HashMap;

import exceptions.*;
      
%% //inicio de opciones
   
/* ------ Seccion de opciones y declaraciones de JFlex -------------- */  
   
/* 
    Cambiamos el nombre de la clase del analizador a Lexer
*/
%class Lexer
%public
%unicode
%type java_cup.runtime.Symbol
/*
    Activar el contador de lineas, variable yyline
    Activar el contador de columna, variable yycolumn
*/
%line
%column
%cup
  
/* 
   Activamos la compatibilidad con Java CUP para analizadores
   sintacticos(parser)
*/

%function next_token
%scanerror LexerError

/*
    Declaraciones

    El codigo entre %{  y %} sera copiado integramente en el 
    analizador generado.
*/
%{
	
    public Lexer(){
    }

    private StringBuffer string           = new StringBuffer();
    private StringBuffer preProcessorText = new StringBuffer();
    private boolean      preProcessorCont = false;
    private StringBuffer braceContent     = new StringBuffer();
    private int          braceCount       = 0; 
    private Triplet beforeToken = new Triplet(null,null,null);
    private String keywordClass;
    private String keyword;
    private ParserError lexerError = new ParserError();
    private boolean toParser = false;
    //public ArrayList<Triplet> ignore = new ArrayList<>();
    /*public Lexer(File filename) throws FileNotFoundException {
        this(new FileInputStream(filename));
    }*/

    /*  Generamos un java_cup.Symbol para guardar el tipo de token 
        encontrado */
    public void setToParser(boolean toParser){
	this.toParser = toParser;
    }
    public ParserError getLexerError(){
        return this.lexerError;
    }
    public int yyline(){
        return this.yyline;
    }
    public String getKeywordClass(){
        return this.keywordClass;
    }
    public String getKeyword(){
        return this.keyword;
    }
    private Symbol symbol(int type, String keywordClass)  throws LexerError{
        //System.err.println("get1: " + beforeToken.get1() + " type: " +type + " keyword: " + keywordClass);
	if(beforeToken.get1() != null && ((int)beforeToken.get1() == sym.CONSTANT || (int)beforeToken.get1() == sym.CONSTANT) && ("type".equals(keywordClass) || sym.IDENTIFIER ==  type )){
	    this.lexerError.setLine(((int)beforeToken.get3()));
	    this.lexerError.setColumn(yycolumn);
            
            String error = (String)beforeToken.get2();
            beforeToken = new Triplet(null,null,null);
	    this.lexerError.setMessage("Illegal character <"+ error + yytext()+">");
	    throw new LexerError("Illegal character <"+ error + yytext()+">");
	}	
	
	
	this.keyword = yytext();
	this.keywordClass = keywordClass;
        beforeToken = new Triplet(type,yytext(),yyline);
        return new Symbol(type, yyline, yycolumn);
        
    }
    
    /* Generamos un Symbol para el tipo de token encontrado 
       junto con su valor */
    private Symbol symbol(int type, Object value,String keywordClass) throws LexerError {
        if(beforeToken.get1() != null && ((int)beforeToken.get1() == sym.CONSTANT || (int)beforeToken.get1() == sym.CONSTANT) && ("type".equals(keywordClass) || sym.IDENTIFIER ==  type )){
	    this.lexerError.setLine(((int)beforeToken.get3()));
	    this.lexerError.setColumn(yycolumn);
            
            String error = (String)beforeToken.get2();
            beforeToken = new Triplet(null,null,null);
	    this.lexerError.setMessage("Illegal character <"+ error + yytext()+">");
	    throw new LexerError("Illegal character <"+ error + yytext()+">");
	}
        
	this.keyword = yytext();
	this.keywordClass = keywordClass;
	beforeToken = new Triplet(type,yytext(),yyline);
        return new Symbol(type, yyline, yycolumn, value);
    }

    public HashMap<Integer, String> getTokens()  throws IllegalArgumentException, IllegalAccessException{
	Field fld[] = sym.class.getDeclaredFields();
        HashMap<Integer, String> tokens = new HashMap<>();
        sym test = null;
        for (Field fld1 : fld) {
            //System.out.println("Variable: " + fld1.getName() + " Value: " + fld1.get(test));
            tokens.put((int) fld1.get(test), fld1.getName());
        }
	return tokens;
    }


%}
   

/*}
    Macro declaraciones
  
    Declaramos expresiones regulares que despues usaremos en las
    reglas lexicas.
*/
 D           = [0-9]
L           = [a-zA-Z_]
H           = [a-fA-F0-9]
E           = [Ee][+-]?{D}+
FS          = (f|F|l|L)
IS          = (u|U|l|L)*

LineTerminator       = \r
                     |\n
                     |\r\n|\u2028|\u2029|\u000B|\u000C|\u0085
WhiteSpace           = {LineTerminator}
                     | [ \t\f]

InputCharacter       = [^\r\n]

Comment              = {TraditionalComment}
                     | {EndOfLineComment}
                     | {DocumentationComment}
TraditionalComment   = "/*" [^*] ~"*/"
                     | "/*" "*"+ "/"
EndOfLineComment     = "//" {InputCharacter}* {LineTerminator}?
DocumentationComment = "/**" {CommentContent} "*"+ "/"
CommentContent       = ( [^*] | \*+ [^/*] )*
/* */
hexadecimal_digit           = [0-9a-fA-F]
octal_digit         = [0-7]
hex_quad            = ({hexadecimal_digit}{4})
universal_character_name        = (("\\u" | "\\U") {hex_quad})

simple_escape_sequence      = ("\\" ("'" | "\\" | "\"" | "?" | [abfnrtv] ))
octal_escape_sequence       = ("\\" {octal_digit}{1,3})
hexadecimal_escape_sequence = ("\\x" {hexadecimal_digit}+)
escape_sequence         = ({simple_escape_sequence} | {octal_escape_sequence} | {hexadecimal_escape_sequence} | {universal_character_name})
newline             = (\r|\n|\r\n|\u2028|\u2029|\u000B|\u000C|\u0085)
c_char              = ({escape_sequence} | (!((. (.|{newline})+)|( ("'"|"\\"|{newline}) (.|{newline})* ))))
c_char_sequence         = ({c_char}+)
Character               = (("L")? "'" {c_char_sequence} "'")
Invalid_Character           = (("L")? "'" {c_char_sequence} {newline})

// A.1.6 String literals
s_char              = ({escape_sequence} | (!((. (.|{newline})+)|( ("\""|"\\"|{newline}) (.|{newline})* ))))
s_char_sequence             = ({s_char}+)
String                  = (("L")? "\"" {s_char_sequence} "\"")
Invalid_String          = (("L")? "\"" {s_char_sequence} {newline})

Invalid_Brace           = ("[" {s_char_sequence} {newline})

%state STRING
%state BRACE
%state PREPROCESSOR

%%

<YYINITIAL> {
    //LENGUAJE C
    //"/*"            { comment(); }
    "break"         { return symbol(sym.BREAK,"common_define");}
    "case"          { return symbol(sym.CASE,"common_define"); }
    "char"          { return symbol(sym.CHAR,"type");}
    "const"         { return symbol(sym.CONST,"type"); }
    "extern"         { return symbol(sym.EXTERN,"type"); }
    "continue"      { return symbol(sym.CONTINUE,"common_define"); }
    "default"       { return symbol(sym.DEFAULT,"common_define"); }
    "do"            { return symbol(sym.DO,"type"); }
    "double"        { return symbol(sym.DOUBLE,"type");}
    "else"          { return symbol(sym.ELSE,"common_define"); }
    "enum"          { return symbol(sym.ENUM,"type"); }
    "float"         { return symbol(sym.FLOAT,"type"); }
    "for"           { return symbol(sym.FOR,"common_define"); }
    "goto"          { return symbol(sym.GOTO,"common_define"); }
    "if"            { return symbol(sym.IF,"common_define"); }
    "int"           { return symbol(sym.INT,"type"); }
    "long"          { return symbol(sym.LONG,"common_define"); }
    "return"        { return symbol(sym.RETURN,"common_define");}
    "short"         { return symbol(sym.SHORT,"type"); }
    "signed"        { return symbol(sym.SIGNED,"type"); }
    "sizeof"        { return symbol(sym.SIZEOF,"type"); }
    "static"        { return symbol(sym.STATIC,"type"); }
    "struct"        { return symbol(sym.STRUCT,"common_define"); }
    "switch"        { return symbol(sym.SWITCH,"common_define"); }
    "union"         { return symbol(sym.UNION,"common_define"); }
    "unsigned"      { return symbol(sym.UNSIGNED,"type");}
    "void"          { return symbol(sym.VOID,"type"); }
    "volatile"      { return symbol(sym.VOLATILE,"common_define");  }
    "while"         { return symbol(sym.WHILE,"common_define"); }

    //NESC
    "includes"     {return symbol(sym.INCLUDES,"common_define"); }
    "#include"      {return symbol(sym.INCLUDE,"common_define"); }
    "provides"      {return symbol(sym.PROVIDES,"common_define");}
    "uses"          {return symbol(sym.USES,"common_define");}
    "components"    {return symbol(sym.COMPONENTS,"common_define");}
    "atomic"        {return symbol(sym.ATOMIC,"common_define");}
    "as"            {return symbol(sym.AS,"common_define");}
    "FALSE"         {return symbol(sym.FALSE,"common_define");}
    "new"           {return symbol(sym.NEW,"common_define");}
    "this"          {return symbol(sym.THIS,"common_define");}
    "TRUE"          {return symbol(sym.TRUE,"common_define");}
    "using"         {return symbol(sym.USING,"common_define");}
    "task"          {return symbol(sym.TASK,"common_define");}
    "post"          {return symbol(sym.POST,"common_define");}
    "namespace"     {return symbol(sym.NAMESPACE,"common_define");}
    "event"         {return symbol(sym.EVENT,"common_define");}
    "command"       {return symbol(sym.COMMAND,"common_define");}
    "module"        {return symbol(sym.MODULE,"common_define");}
    "typename"      {return symbol(sym.TYPENAME,"common_define");}
    "template"      {return symbol(sym.TEMPLATE,"common_define");}
    "virtual"       {return symbol(sym.VIRTUAL,"common_define");}
    "configuration" {return symbol(sym.CONFIGURATION,"common_define");}
    "implementation" {return symbol(sym.IMPLEMENTATION,"common_define");}
    "interface"     {return symbol(sym.INTERFACE,"type");}
    "uint8_t"       {return symbol(sym.UINT8_T,"type");}
    "uint16_t"      {return symbol(sym.UINT16_T,"type");}
    "uint32_t"      {return symbol(sym.UINT32_T,"type");}
    "int8_t"        {return symbol(sym.INT8_T,"type");}
    "int16_t"       {return symbol(sym.INT16_T,"type");}
    "int32_t"       {return symbol(sym.INT32_T,"type");}
    "bool"          {return symbol(sym.BOOL,"type");}
    "explicit"      {return symbol(sym.EXPLICIT,"type");}
    "export"        {return symbol(sym.EXPORT,"type");}
    "inline"        {return symbol(sym.INLINE,"type");}
    "error_t"       {return symbol(sym.ERROR_T,"type");}
    "result_t"      {return symbol(sym.RESULT_T,"type");}
    "packet_t"      {return symbol(sym.PACKET_T,"type");}
    "message_t"     {return symbol(sym.MESSAGE_T,"type");}

    "typedef"     {return symbol(sym.TYPEDEF,"common_define");}
    "__attribute__" {return symbol(sym.ATTRIBUTE,"common_define");}
    
    "async"     {return symbol(sym.ASYNC,"common_define");}
    "call"     {return symbol(sym.CALL,"common_define");}
    "component"     {return symbol(sym.COMPONENT,"common_define");}
    "generic"     {return symbol(sym.GENERIC,"common_define");}
    "norace"     {return symbol(sym.NORACE,"common_define");}
    "nx_struct"     {return symbol(sym.NX_STRUCT,"common_define");}
    "nx_union"     {return symbol(sym.NX_UNION,"common_define");}
    "generic"     {return symbol(sym.GENERIC,"common_define");}
    "signal"     {return symbol(sym.SIGNAL,"common_define");}
    /*nesC keywords reserved for future use*/
    "abstract"     {return symbol(sym.ABSTRACT,"common_define");}
    "extends"     {return symbol(sym.EXTENDS,"common_define");}

    {Comment} {
        //System.err.println(yytext());
        //ignore.add(new Triplet(-3,String.copyValueOf(yytext().toCharArray()),"COMMENT"));
        if(!toParser)
            return symbol(-2, yytext() ,"COMMENT");
            /* ignore */
    }
    {WhiteSpace} {        
        //ignore.add(new Triplet(-2,String.copyValueOf(yytext().toCharArray()),""));
        if(!toParser)   
            return symbol(-2, yytext(),"WHITE_SPACE");
            /* ignore */
    }
    {LineTerminator} {
        //ignore.add(new Triplet(-2,String.copyValueOf(yytext().toCharArray()),""));
        if(!toParser)
            return symbol(-2, yytext(),"LINE_TERMINATOR");
            /* ignore */
    }

    {L}({L}|{D})*           { return symbol(sym.IDENTIFIER, yytext(),"ID"); }
    0[xX]{H}+{IS}?          { return symbol(sym.CONSTANT, yytext(),"Number"); }
    0{D}+{IS}?              { return symbol(sym.CONSTANT, yytext(),"Number");}
    {D}+{IS}?               { return symbol(sym.CONSTANT, yytext(),"Number");}
    L?'(\\.|[^\\'])+'       { /*'*/ return symbol(sym.CONSTANT, yytext(),"Char");}
    {D}+{E}{FS}?            { return symbol(sym.CONSTANT, yytext(),"Number");}
    {D}*"."{D}+({E})?{FS}?  { return symbol(sym.CONSTANT, yytext(),"Number");}
    {D}+"."{D}*({E})?{FS}?  { return symbol(sym.CONSTANT, yytext(),"Number");}
    {String}                { return symbol(sym.STRING_LITERAL, yytext(),"String"); }

    {Invalid_String} {
        this.lexerError.setLine(yyline);
        this.lexerError.setColumn(yycolumn);
        this.lexerError.setMessage("Illegal String <" +yytext()+ ">");  
            throw new LexerError("Illegal String <"+ yytext()+">");
        
    }   
    {Invalid_Character} {
        this.lexerError.setLine(yyline);
        this.lexerError.setColumn(yycolumn);
        this.lexerError.setMessage("Illegal character <"+ yytext()+">");    
        throw new LexerError("Illegal character <"+ yytext()+">");
    }

    //OPERADORES
    "..."           { return symbol(sym.ELLIPSIS,"assignment_operators"); }
    ">>="           { return symbol(sym.RIGHT_ASSIGN,"assignment_operators"); }
    "<<="           { return symbol(sym.LEFT_ASSIGN,"assignment_operators"); }
    "+="            { return symbol(sym.ADD_ASSIGN,"assignment_operators"); }
    "-="            { return symbol(sym.SUB_ASSIGN,"assignment_operators"); }
    "*="            { return symbol(sym.MUL_ASSIGN,"assignment_operators"); }
    "/="            { return symbol(sym.DIV_ASSIGN,"assignment_operators"); }
    "%="            { return symbol(sym.MOD_ASSIGN,"assignment_operators"); }
    "&="            { return symbol(sym.AND_ASSIGN,"assignment_operators"); }
    "^="            { return symbol(sym.XOR_ASSIGN,"assignment_operators"); }
    "|="            { return symbol(sym.OR_ASSIGN,"assignment_operators"); }
    ">>"            { return symbol(sym.RIGHT_OP,"bitwise_operator"); }
    "<<"            { return symbol(sym.LEFT_OP,"bitwise_operator"); }
    "++"            { return symbol(sym.INC_OP,"assignment_operators"); }
    "--"            { return symbol(sym.DEC_OP,"assignment_operators"); }
 //   "->"            { return symbol(sym.PTR_OP, "pointer_operator"); }
    "&&"            { return symbol(sym.AND_OP,"logical_operator"); }
    "||"            { return symbol(sym.OR_OP,"logical_operator"); }
    "<="            { return symbol(sym.LE_OP,"comparison_operator"); }
    ">="            { return symbol(sym.GE_OP,"comparison_operator"); }
    "=="            { return symbol(sym.EQ_OP,"comparison_operator"); }
    "!="            { return symbol(sym.NE_OP,"comparison_operator"); }
    ";"             { return symbol(sym.SEMI_COLON,"end_expressions");}
    ("{"|"<%")      { return symbol(sym.OPEN_CURLY,"braces"); }
    ("}"|"%>")      { return symbol(sym.CLOSE_CURLY,"braces"); }
    ","             { return symbol(sym.COMMA,"comma_operator"); }
    ":"             { return symbol(sym.COLON,"ternary_conditional"); }
    "="             { return symbol(sym.EQUALS, "arithmetic_operator"); }
    "("             { return symbol(sym.OPEN_PAREN,"function_call");}
    ")"             { return symbol(sym.CLOSE_PAREN,"function_call");}
    ("["|"<:")      { return symbol(sym.OPEN_BRACE,"array_member");}
    ("]"|":>")      { return symbol(sym.CLOSE_BRACE,"array_member");}
    "."             { return symbol(sym.DOT,"member_selection_operator"); }
    "&"             { return symbol(sym.BITWISE_AND,"bitwise_operator"); }
    "!"             { return symbol(sym.NOT,"logical_operator");}
    "~"             { return symbol(sym.BITWISE_NOT,"bitwise_operator"); }
    "-"             { return symbol(sym.MINUS,"arithmetic_operator"); }
    "+"             { return symbol(sym.PLUS,"arithmetic_operator"); }
    "*"             { return symbol(sym.MULTIPLICATION,"arithmetic_operator"); }
    "/"             { return symbol(sym.DIVIDE,"arithmetic_operator"); }
    "%"             { return symbol(sym.MOD,"arithmetic_operator");}
    "<"             { return symbol(sym.LESS_THAN,"comparison_operator"); }
    ">"             { return symbol(sym.GREATER_THAN,"comparison_operator");}
    "^"             { return symbol(sym.BITWISE_XOR,"bitwise_operator"); }
    "|"             { return symbol(sym.BITWISE_OR,"bitwise_operator"); }
    "?"             { return symbol(sym.TERNARY,"ternary_conditional"); }

    /*nesC operators*/
    "->"             { return symbol(sym.WIRES_TO,"wire"); }
    "<-"             { return symbol(sym.WIRES_FROM,"wire"); }
    "@"             { return symbol(sym.AT,"at"); }
}

<PREPROCESSOR> {
    "." {
        preProcessorText.append(yytext());
        preProcessorCont = false;
    }

    "\\" {
        preProcessorCont = true;
        preProcessorText.append(yytext());
    }

    "\n" {
        if (preProcessorCont) {
            preProcessorCont = false;
        } else {
            yybegin(YYINITIAL);
        }
        preProcessorText.append(yytext());
        return symbol(sym.PREPROCESSOR, preProcessorText.toString(),"keyword");
    }
}

<STRING> {
    \" {
        yybegin(YYINITIAL);
        return symbol(sym.STRING_LITERAL, string.toString(),"String");
    }

    [^\n\r\"\\]+ {
        string.append( yytext() );
    }

    \\t {
        string.append('\t');
    }

    \\n {
        string.append('\n');
    }

    \\r {
        string.append('\r');
    }

    \\\" {
        string.append('\"');
    }

    \\ {
        string.append('\\');
    }
}


<BRACE> {
    "[" {
        braceContent.append(yytext());
        braceCount++;
    }

    "]" {
        --braceCount;
        if (braceCount == 0) {
            yybegin(YYINITIAL);
            return symbol(sym.CLOSE_BRACE, braceContent.toString(),"keyword");
        } else {
        braceContent.append(yytext());
        }
    }

    . {
        braceContent.append(yytext());
    }
}
/* error fallback */
.|\n {
    //return symbol(sym.error);

    this.lexerError.setLine(((int)beforeToken.get3())+1);
this.lexerError.setColumn(yycolumn);
    this.lexerError.setMessage("Illegal character <"+ yytext()+">");    
    throw new LexerError("Illegal character <"+ yytext()+">");
}
/* vim: :set ft=java: */
