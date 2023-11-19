public class Main {
    public static void main(String[] args) {
        String inputCommands = "add 7,EXA";
        
        Parser parser = new Parser(inputCommands);

        while (parser.hasMoreCommands()) {
            System.out.println("Command Type: " + parser.commandType());
            parser.advance();
        }
    }
}
