import java.io.BufferedWriter;
import java.io.IOException;

public class CodeWriter {
    private BufferedWriter writer;
    private StringBuilder output = new StringBuilder();
    private String moduleName = "Main";
    private String outputFileName;


    public CodeWriter(String fname) {
        outputFileName = fname;
    }

    
    String registerName(String segment, int index) {

        if (segment.equals("local"))
            return "LCL";
        if (segment.equals("argument"))
            return "ARG";
        if (segment.equals("this"))
            return "THIS";
        if (segment.equals("that"))
            return "THAT";
        if (segment.equals("pointer"))
            return "R" + (3 + index);
        if (segment.equals("temp"))
            return "R" + (5 + index);

        return moduleName + "." + index;
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

    void writePush(String seg, int index) {
        if (seg.equals("constant")) {
            write("@" + index + " // push " + seg + " " + index);
            write("D=A");
            write("@SP");
            write("A=M");
            write("M=D");
            write("@SP");
            write("M=M+1");
        } else if (seg.equals("static") || seg.equals("temp") || seg.equals("pointer")) {
            write("@" + registerName(seg, index) + " // push " + seg + " " + index);
            write("D=M");
            write("@SP");
            write("A=M");
            write("M=D");
            write("@SP");
            write("M=M+1");
        }

        else {
            write("@" + registerName(seg, 0) + " // push " + seg + " " + index);
            write("D=M");
            write("@" + index);
            write("A=D+A");
            write("D=M");
            write("@SP");
            write("A=M");
            write("M=D");
            write("@SP");
            write("M=M+1");
        }
    }

    void writePop(String seg, int index) {
        if (seg.equals("static") || seg.equals("temp") || seg.equals("pointer")) {

            write("@SP // pop " + seg + " " + index);
            write("M=M-1");
            write("A=M");
            write("D=M");
            write("@" + registerName(seg, index));
            write("M=D");
        } else {
            write("@" + registerName(seg, 0) + " // pop " + seg + " " + index);
            write("D=M");
            write("@" + index);
            write("D=D+A");
            write("@R13");
            write("M=D");
            write("@SP");
            write("M=M-1");
            write("A=M");
            write("D=M");
            write("@R13");
            write("A=M");
            write("M=D");
        }
    }


    public void close() throws IOException {
        if (writer != null) {
            writer.close();
        }
    }

    public void write(String s) {
        output.append(String.format("%s\n", s));
    }  
}
