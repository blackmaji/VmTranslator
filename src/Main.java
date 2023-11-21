import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Main {
    private static String fromFile(File file) {        

        byte[] bytes;
        try {
            bytes = Files.readAllBytes(file.toPath());
            String textoDoArquivo = new String(bytes, "UTF-8");
            return textoDoArquivo;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    } 

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Please provide a single file path argument.");
            System.exit(1);
        }

    }

    private static void translateFile (File file, CodeWriter code) {

        String input = fromFile(file);
        Parser p = new Parser(input);
        while (p.hasMoreCommands()) {
            var command = p.nextCommand();
            switch (command.type) {

                case ADD:
                    code.writeArithmeticAdd();
                    break;

                case SUB:
                    code.writeArithmeticSub();
                    break;

                case NEG:
                    code.writeArithmeticNeg();
                    break;

                case NOT:
                    code.writeArithmeticNot();
                    break;
                
                case EQ:
                    code.writeArithmeticEq();
                    break;

                case LT:
                    code.writeArithmeticLt();
                    break;
                
                case GT:
                    code.writeArithmeticGt();
                    break;
                
                case AND:
                    code.writeArithmeticAnd();
                    break;

                            
                case OR:
                    code.writeArithmeticOr();
                    break;


                case PUSH:
                    code.writePush(command.args.get(0), Integer.parseInt(command.args.get(1)));
                    break;
                
                case POP:
                    code.writePop(command.args.get(0), Integer.parseInt(command.args.get(1)));
                    break;

                default:
                    System.out.println(command.type.toString()+" not implemented");
            }

    
        } 
       

    }
}