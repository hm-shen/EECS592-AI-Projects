import java.io.*;

public class Test {
    public static void main(String[] args) {
        double[] wasp = {10, 12, 13, 15, 17, 34, 38, 40};
        try {
            FileWriter writer = new FileWriter("./test.csv");
            
            for (int j = 0; j < wasp.length; j++) {
                writer.append(String.valueOf(wasp[j]));
                writer.append(",");
            }
            writer.close();
        } catch (IOException ioe) {
            System.err.println("IOException: " + ioe.getMessage());
            System.exit(0);
        }
    }
}
