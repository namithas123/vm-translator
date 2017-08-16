/** Translates VM code into Assembly code
 * 
 * Usage: java VMTranslator <file-name/path-to-directory>
 * 
 * Dependicies: Parser.java, CodeWriter.java
 * 
 * - Constructs a Parser from input file
 * - Construct a CodeWriter to generate code in output file
 * - Read VM commands and generate corresponding Assembly code
 * 
 **/
import java.io.*;

public class VMTranslator {
    
    public Parser parser;
    public CodeWriter code;
    
    // take a filename, create parser and
    // translate each vm commands in file 
    private void parse(in) {
        // construct a parser with the file
        parser = new Parser(in);
        
        // iterate through each command
        while (hasMoreCommands()) {
            String command = parser.advance();
            
            String ctype = parser.commandType();
            
            // artithmetic command
            if (ctype.equals("C_ARITHMETIC")) {
                code.writeArithmetic(parser.arg1());
            }
            else if (ctype.equals("C_PUSH") || ctype.equals("C_POP")) {
                code.writePushPop(ctype, parser.arg1(), Integer.parseInt(parser.arg2()));
            }
        }
    }
    
    public static void main(String[] args) {
        
        try {
            File path = new File(args[0]);
            
            // create CodeWriter
            code = new CodeWriter(args[0]);
            
            // argument could be a file or directory containing multiple files
            File[] files = path.listFiles();
            // if path is a directory
            if (files != null) {
                for (File file : files) {
                  parse(file);
                }
            }
            // single file
            else {
                parse(file);
            }
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }
}