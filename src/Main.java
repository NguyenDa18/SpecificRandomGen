import org.omg.CORBA.Object;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.*;
import java.io.*;


/**
 * SOURCES USED
 * https://stackoverflow.com/questions/17750575/filewriter-in-java-not-writing-to-txt-file
 * https://www.deadcoderising.com/how-to-generate-a-stream-of-random-numbers-in-java/
 * https://stackoverflow.com/questions/20058366/shuffle-a-list-of-integers-with-java-8-streams-api
 * https://www.geeksforgeeks.org/program-to-convert-array-to-list-in-java/
 */

public class Main {
    public static final int initialDistribution = 83000;
    public static final int remainingDistribution = 1000;
//    public static final String filePath = "/Users/newowner/Documents/RandomGen/src/test.output.txt";
    public static final String filePath = "CHANGE_ME_ABSOLUTE_FILEPATH/src/test.output.txt";

    public static void main(String[] args) {
        List<Integer> nums = genDataset();

        try {

            FileWriter writer = new FileWriter(filePath, true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            // Loop over the elements in the string array and write each line.
            for (int line : nums) {
                bufferedWriter.write(line + "");
                bufferedWriter.newLine();
            }

            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            readLines();
        }
        catch (FileNotFoundException fne) {

        }
        catch (IOException io) {

        }

    }

    public static List<Integer> genDataset() {
        ArrayList<Integer> dataset = new ArrayList<>();
        for (int i = 1; i < 13; i++) {
            dataset.addAll(numBatch(initialDistribution, i));
        }
        dataset.addAll(numBatch(1000, 13));
        dataset.addAll(numBatch(500, 14));
        dataset.addAll(numBatch(250, 15));
        dataset.addAll(numBatch(100, 16));
        dataset.addAll(numBatch(50, 17));
        dataset.addAll(numBatch(25, 18));
        dataset.addAll(numBatch(10, 19));
        dataset.addAll(numBatch(5, 20));

        Collections.shuffle(dataset);

        // checking for consecutives
        List<Integer> sublist = new ArrayList<>();
        for (int i = 0; i < dataset.size() - 1; i++) {
            if (dataset.get(i) == dataset.get(i + 1)) {
                sublist = dataset.subList(0, i + 1);
                Collections.reverse(sublist);
            }
        }

        // checking one more time for consecs
        for (int i = 0; i < dataset.size() - 1; i++) {
            if (dataset.get(i) == dataset.get(i + 1)) {
                sublist = dataset.subList(0, i + 1);
                Collections.reverse(sublist);
            }
        }

        for (int i = 0; i < dataset.size() - 1; i++) {
            System.out.println("Element: " + dataset.get(i));
            if (dataset.get(i) == dataset.get(i + 1)) {
                System.out.println("STILL FOUND CONSECUTIVES");
            }
        }

        return dataset;

    }

    public static List<Integer> numBatch(int amt, int num) {
        List<Integer> array = new ArrayList<>(Collections.nCopies(amt, num));
        return array;
    }

    /**
     * Print all lines where '20' occurs
     * @throws IOException
     */
    public static void readLines() throws IOException {
        File file = new File(filePath);

        BufferedReader br = new BufferedReader(new FileReader(file));
        int lineCount = 0;
        String st;
        while ((st = br.readLine()) != null) {
            lineCount += 1;

            if (Integer.parseInt(st) > 19) {
                System.out.println("Number " + st + " found at line " + lineCount);

            }
        }
    }
}
