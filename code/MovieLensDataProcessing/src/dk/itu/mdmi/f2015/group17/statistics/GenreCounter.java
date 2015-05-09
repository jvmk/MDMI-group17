package dk.itu.mdmi.f2015.group17.statistics;

import java.io.*;
import java.util.Arrays;
import java.util.Objects;

/**
 * TODO add class documentation.
 *
 * @author Janus Varmarken
 */
public class GenreCounter {


    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("/Users/varmarken/Desktop/GenresSplitOutput.csv"));
        GenreCounter gc = new GenreCounter(reader);
        gc.count();
        gc.printResult();
    }

    private final BufferedReader mInput;
    /**
     * Array index = number of genres.
     * Value at index = occurrences for that number of genres.
     */
    private final int[] mGenresCount = new int[100];

    public GenreCounter(BufferedReader input) {
        mInput = Objects.requireNonNull(input);
    }

    public void count() throws IOException {
        // Reset.
        Arrays.fill(mGenresCount, 0, mGenresCount.length, 0);

        String line = mInput.readLine();
        int lineCount = 1;

        while ((line = mInput.readLine()) != null) {
            lineCount++;

            // RapidMiner for some reason adds quotes, remove them.
            line = line.replaceAll("\"", "");

            int genres = 0;
            String[] items = line.split(",");
            for (String item : items) {
                if (item.equals("true")) {
                    genres++;
                } else if (item.equals("false")) {
                    // do nothing
                } else {
                    throw new IllegalArgumentException("Unknown value '" + item + "' at line " + lineCount);
                }
            }

            mGenresCount[genres] = mGenresCount[genres] + 1;
        }

        mInput.close();
    }

    public void printResult() {
        for (int i = 0; i < mGenresCount.length; i++) {
            if (mGenresCount[i] == 0) {
                continue;
            }
            System.out.println("Number of movies with " + i + " genres listed: " + mGenresCount[i]);
        }
    }

}
