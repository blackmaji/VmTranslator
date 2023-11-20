import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class CodeWriter {
    private BufferedWriter writer;
    private StringBuilder output = new StringBuilder();

    public CodeWriter(String fileName) {
        try {
            writer = new BufferedWriter(new FileWriter(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }  
    public void writeArithmetic(String command) {

        switch (command) {
            case "add":
                write("@SP // add");
                write("M=M-1");
                write("A=M");
                write("D=M");
                write("A=A-1");
                write("M=D+M");
                break;
            case "sub":
                write("@SP // sub");
                write("M=M-1");
                write("A=M");
                write("D=M");
                write("A=A-1");
                write("M=M-D");
                break;
            default:
                break;
        }
    }
    
    public void close() {
        try {
            if (writer != null) {
                writer.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void write(String s) {
        output.append(String.format("%s\n", s));
    }  
}
