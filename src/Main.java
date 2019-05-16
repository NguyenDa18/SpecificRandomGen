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
    public static final String filePath = "/Users/newowner/Documents/RandomGen/src/output.txt";

    public static void main(String[] args) {
        List<Integer> nums = getDataset();
        for (int num : nums) {
            System.out.println(num);
        }

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

    public static List<Integer> getDataset() {
        Random gen = new Random();

        IntStream firstDistribution = gen.ints(initialDistribution, 1, 13);
        IntStream secondDis = gen.ints(remainingDistribution, 13, 14);
        IntStream thirdDis = gen.ints(500, 14, 15);
        IntStream fourthDis = gen.ints(250, 15,16);
        IntStream fifthDis = gen.ints(100, 16,17);
        IntStream sixthDis = gen.ints(50, 17,18);
        IntStream seventhDis = gen.ints(25, 18,19);
        IntStream eighthDis = gen.ints(10, 19,20);
        IntStream lastDis = gen.ints(5, 20,21);


        IntStream total = IntStream.concat(firstDistribution, secondDis);
        total = IntStream.concat(total, thirdDis);
        total = IntStream.concat(total, fourthDis);
        total = IntStream.concat(total, fifthDis);
        total = IntStream.concat(total, sixthDis);
        total = IntStream.concat(total, seventhDis);
        total = IntStream.concat(total, eighthDis);
        total = IntStream.concat(total, lastDis);


        List<Integer> integers =
                        total       // <-- take our combined IntStream dataset
                        .boxed() // <-- converts them to Integers
                        .collect(Collectors.toList());          // <-- collects the values to a list

        Collections.sort(integers);

        Collections.shuffle(integers, new Random(System.nanoTime()));

        // shuffle twice for assurance of no consecutives
        Collections.shuffle(integers, new Random(System.nanoTime()));

        return integers;

    }
}
