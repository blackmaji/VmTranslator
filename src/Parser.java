import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Parser {
    List<String[]> commands;
    int currentIndex;

    Parser (String input) {
        final String eol = System.getProperty("line.separator");
        var output = input.split(eol);
        commands = Arrays.stream(output)
        .map(String::strip)
        .filter(  (s) ->  s.indexOf("//") != 0 && s != "")
        .map ( (s) ->s.split(" ")  )
        .collect(Collectors.toList());
    }

    public boolean hasMoreCommands () {
        return currentIndex < commands.size();
    }

    public void advance() {
        if (hasMoreCommands()) {
            currentIndex++;
        }
    }
    public String commandType() {
        if (hasMoreCommands()) {
            String[] currentCommand = commands.get(currentIndex);
    
            switch (currentCommand[0]) {
                case "add":
                case "sub":
                    return "Arithmetic";
                case "push":
                case "pop":
                case "label":
                case "goto":
                case "if":
                case "function":
                case "return":
                case "call":
                    return currentCommand[0].substring(0, 1).toUpperCase() + currentCommand[0].substring(1);
                default:
                    return "Arithmetic";
            }
        }
        return null;
    }
    
}
