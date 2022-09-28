import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static final String COMMAND = "D:\\intellij\\sdk\\bin\\java.exe \"-javaagent:D:\\intellij\\IntelliJ IDEA Community Edition 2022.2\\lib\\idea_rt.jar=58076:D:\\intellij\\IntelliJ IDEA Community Edition 2022.2\\bin\" -Dfile.encoding=UTF-8 -classpath D:\\university\\testing\\lw1.1\\out\\production\\lw1.1 Triangle ";
    public static final String SUCCESS = "sucсess";
    public static final String ERROR = "error";

    public static void main(String[] args) throws IOException {

        File outputFile = new File("D:\\university\\testing\\lw1.1_test\\src\\output_data.txt");
        if (outputFile.createNewFile())
        {
        }
        PrintWriter output = new PrintWriter(outputFile);
        InputStream inputFile = new FileInputStream("D:\\university\\testing\\lw1.1_test\\src\\input_data.txt");
        testProg(output, inputFile);
    }

    private static void testProg(PrintWriter output, InputStream inputFile) throws IOException
    {
        String[] args;
        String params = "";
        Scanner obj = new Scanner(inputFile);
        while (obj.hasNextLine())
        {

            args = obj.nextLine().split(" ");
            String expected = args[args.length-1];
            args = Arrays.copyOf(args, args.length-1);
            for (String arg : args)
            {
                params += arg + " ";
            }
            Process process = Runtime.getRuntime().exec(COMMAND + params);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String response = reader.readLine();
            if (response.equals(expected))
            {
                writeToFile(output, true);
            } else
            {
                writeToFile(output, false);
            }
            params = "";
        }
        output.close();
    }

    private static void writeToFile(PrintWriter file, boolean succes) {
        if (succes)
        {
            file.println(SUCCESS);
        } else
        {
            file.println(ERROR);
        }
    }
}