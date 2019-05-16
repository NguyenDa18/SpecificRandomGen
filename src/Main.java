import java.util.*;
import java.util.stream.*;
import java.io.*;

/**
 * SOURCES USED
 * https://stackoverflow.com/questions/17750575/filewriter-in-java-not-writing-to-txt-file
 * https://www.deadcoderising.com/how-to-generate-a-stream-of-random-numbers-in-java/
 * https://stackoverflow.com/questions/20058366/shuffle-a-list-of-integers-with-java-8-streams-api
 */

public class Main {
    public static final int firstDistribution = 83000;
    public static final int secondDistribution = 1000;

    public static void main(String[] args) {
        List<Integer> nums = getDataset();
//        for (int num : nums) {
//            System.out.println(num + "");
//        }

        try {

            FileWriter writer = new FileWriter("/Users/newowner/Documents/RandomGen/src/output.txt", true);
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

    /**
     * Print all lines where '20' occurs
     * @throws IOException
     */
    public static void readLines() throws IOException {
        File file = new File("/Users/newowner/Documents/RandomGen/src/output.txt");

        BufferedReader br = new BufferedReader(new FileReader(file));
        int lineCount = 0;

        String st;
        while ((st = br.readLine()) != null) {
            lineCount += 1;

            if (Integer.parseInt(st) > 11) {
                System.out.println("Number " + st + " found at line " + lineCount);

            }
        }
    }

    public static List<Integer> getDataset() {
        Random gen = new Random();

        IntStream firstDistribution = gen.ints(20, 1, 13);
        IntStream secondDis = gen.ints(10, 13, 14);

        IntStream total = IntStream.concat(firstDistribution, secondDis);

        List<Integer> integers =
                        total                                   // <-- take our combined IntStream dataset
                        .boxed()                                // <-- converts them to Integers
                        .collect(Collectors.toList());          // <-- collects the values to a list

        Collections.shuffle(integers, new Random(System.nanoTime()));

        return integers;

    }
}
