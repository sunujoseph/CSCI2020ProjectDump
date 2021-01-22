package lab8;

import java.io.FileOutputStream;
import java.io.PrintStream;

public class main {
    private static String delimiter = ",";
    public static String fileName = "myfile.csv";

    public static void main(String[] args) {
        PrintStream out = null;
        int myInt[] = new int[20];
        for (int i = 0; i < 20; i++) {
            myInt[i] = i;
        }

        try {
            out = new PrintStream(new FileOutputStream(fileName));
            int count = 0;

            for(int i = 0; i < 5; i ++) {
                for(int j = 0; j < 4; j++) {
                    out.print(myInt[count++] + delimiter);
                }
                out.print("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
        System.out.println("We have saved to file..");
    }

}
