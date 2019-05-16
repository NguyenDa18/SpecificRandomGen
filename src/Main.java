import java.util.Random;
import java.io.*;
import java.nio.file.*;


public class Main {

    public static void main(String[] args) {
//        try (BufferedWriter out = new BufferedWriter(
//                new FileWriter("/Users/newowner/Documents/random_java/output.txt"))) {
//            out.write("Hello World");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        try {

            FileWriter writer = new FileWriter("/Users/newowner/Documents/RandomGen/src/output.txt", true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            bufferedWriter.write("Hello World!");
            bufferedWriter.newLine();
            bufferedWriter.write("See You!");

            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
