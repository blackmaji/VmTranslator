public class Main {
        public static void main(String[] args) {
            testCommandType();
            testArg1();
            testArg2();
        }
    
        public static void testCommandType() {
        String input = "push constant 7\nadd\nsub\nlabel LOOP\nif-goto END\nfunction SimpleFunction 0\nreturn";
        Parser parser = new Parser(input);

        while (parser.hasMoreCommands()) {
            String commandType = parser.commandType();
            System.out.println("Command Type: " + commandType);
            parser.advance();
        }
    }

    public static void testArg1() {
        String input = "push local 2\npop argument 0\nlabel LOOP\nif-goto END\nfunction SimpleFunction 0\nreturn";
        Parser parser = new Parser(input);

        while (parser.hasMoreCommands()) {
            String arg1 = parser.arg1();
            System.out.println("Arg1: " + arg1);
            parser.advance();
        }
    }

    public static void testArg2() {
        String input = "push constant 7\npop local 2\nlabel LOOP\nif-goto END\nfunction SimpleFunction 0\nreturn";
        Parser parser = new Parser(input);

        while (parser.hasMoreCommands()) {
            String arg2 = parser.arg2();
            System.out.println("Arg2: " + arg2);
            parser.advance();
        }
    }
    
}
