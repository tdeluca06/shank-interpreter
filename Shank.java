import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Shank {
    public static void main(String[] args) throws IOException, SyntaxErrorException {
        if (args.length != 1) {
            System.err.println("Usage: java Shank <prog-name>");
            return;
        }

        String fName = args[0];
        if (!fName.endsWith(".shank")) {
            System.err.println("Error: please pass through a .shank file");
            return;
        }

        Path fPath = Paths.get(fName);

        if (!Files.exists(fPath)) {
            System.err.println("Error: file not found:" + fPath.toString());
            return;
        }

        List <String> lines = Files.readAllLines(fPath);

        Lexer lexData = new Lexer();
        lines.forEach(line -> {
            try {
                lexData.lex(line);
            }
            catch (SyntaxErrorException e) {
                e.printStackTrace();
            }
        });

    }
}