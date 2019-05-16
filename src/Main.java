import java.util.*;
import java.util.stream.*;
import java.io.*;
import java.nio.file.*;

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
//        IntStream mine = getRandomStream();
//        int [] mi = mine.toArray();
//        for (int i: mi) {
//            System.out.println(i);
//
//        }
//
        String[] array = new String[3];
        array[0] = "1";
        array[1] = "2";
        array[2] = "3";

        List<Integer> nums = getDataset();
        for (int num : nums) {
            System.out.println(num);
        }

        try {

            FileWriter writer = new FileWriter("/Users/newowner/Documents/RandomGen/src/output.txt", true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            // Loop over the elements in the string array and write each line.
            for (String line : array) {
                bufferedWriter.write(line);
                bufferedWriter.newLine();
            }

            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
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

        Collections.shuffle(integers);

        return integers;

    }

    public static IntStream getRandomStream() {
        return new Random().ints(50,1,20 + 1);
    }
}
