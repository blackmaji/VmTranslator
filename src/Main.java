public class Main {
        public static void main(String[] args) {
            testCommandType();
            testArg1();
            testArg2();

            String input = "push const 10";
            Parser parser = new Parser(input);

            while (parser.hasMoreCommands()) {
                System.out.println("Command Type: " + parser.commandType());
                System.out.println("Arg1: " + parser.arg1());
                System.out.println("Arg2: " + parser.arg2());
                System.out.println("------------");
                parser.advance();
            }

        }
    
        public static void testCommandType() {
        String input = "push constant 7";
        Parser parser = new Parser(input);

        while (parser.hasMoreCommands()) {
            String commandType = parser.commandType();
            System.out.println("Command Type: " + commandType);
            parser.advance();
        }
    }

    public static void testArg1() {
        String input = "push local 2";
        Parser parser = new Parser(input);

        while (parser.hasMoreCommands()) {
            String arg1 = parser.arg1();
            System.out.println("Arg1: " + arg1);
            parser.advance();
        }
    }

    public static void testArg2() {
        String input = "push constant 7";
        Parser parser = new Parser(input);

        while (parser.hasMoreCommands()) {
            String arg2 = parser.arg2();
            System.out.println("Arg2: " + arg2);
            parser.advance();
        }
    }
    
}
