import java.io.*;
import java.util.UUID;

/**
 * TODO add class documentation.
 *
 * @author Janus Varmarken
 */
public class TagsParser {

    private final String mSeperatorChar = ",";
    private final String mQuotationMark = "\"";

    private int mCleanedTuples = 0;
    private int mSkippedTuples = 0;

    private int mColumns;
    private BufferedReader mReader;
    private BufferedWriter mWriter;

    public static void main(String[] args) {
        File input = new File("/Users/varmarken/Desktop/ml-20m/tags.csv");
        File output = new File("/Users/varmarken/Desktop/" + UUID.randomUUID().toString() + ".csv");

        try {
            output.createNewFile();
            TagsParser tp = new TagsParser(4, new BufferedReader(new FileReader(input)),new BufferedWriter(new FileWriter(output)));
            tp.produceCleanFile();
            System.out.println("Cleaned tuples: " + tp.mCleanedTuples + ". Skipped tuples: " + tp.mSkippedTuples);
//            int badLines = 0;

//            System.out.println("# lines where there are not separators present: " + tp.mColumns - 1 );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public TagsParser(int columns, BufferedReader srcFile, BufferedWriter destFile) {
        mColumns = columns;
        mReader = srcFile;
        mWriter = destFile;
    }

    public void produceCleanFile() {
        try {
            String line = null;
            while ((line = mReader.readLine()) != null) {
                String[] parts = line.split(mSeperatorChar);
                if (parts.length == mColumns && !hasQuotationMarks(parts)) {
                    // valid line, dump it to cleaned file as is
                    writeCleanData(parts);
                } else if (parts.length != mColumns && hasQuotationMarks(parts)) {
                    // Commas caused separations in places where there shouldn't be.
                    // However quotation marks are present, so we should be able to remove commas and reconstruct the line.
                    String reconstructed = reconstruct(parts);
                    if (reconstructed.split(mSeperatorChar).length != mColumns) {
                        // Could not properly reconstruct columns.
                        // Skip this tuple.
                        mSkippedTuples++;
                        continue;
                    }
                    writeCleanData(reconstructed.split(mSeperatorChar));
                } else {
                    // Cannot reconstruct this tuple as commas were used but not wrapped in quotation marks.
                    mSkippedTuples++;
                    continue;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                mWriter.flush();
                mWriter.close();
                mReader.close();
            } catch (IOException e) {
                // too bad
                e.printStackTrace();
            }
        }
    }


    private void writeCleanData (String[] values) {
        if (values.length != mColumns) {
            throw new IllegalArgumentException();
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < values.length; i++) {
            if (values[i].contains("'")) {
                // Wrap strings with single quotation marks in double quotation marks.
                values[i] = "\"" + values[i] + "\"";
            }
            sb.append(values[i]);
            if(i == values.length - 1) {
                // if last element, add linebreak and leave out comma.
                sb.append(System.lineSeparator());
            } else {
                // for all other lines add comma
                sb.append(mSeperatorChar);
            }
        }

        try {
            mWriter.write(sb.toString());
            mCleanedTuples++; /* successfully wrote cleaned tuple. */
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private String reconstruct(String[] parts) {
        StringBuilder sb = new StringBuilder();
        boolean awaitingClosingQtm = false; /* Are we expecting a closing quotation mark? */

        for (int i = 0; i < parts.length; i++) {
            if (parts[i].contains(mQuotationMark) && awaitingClosingQtm) {
                // Closing quotation mark
                awaitingClosingQtm = false;
                sb.append(parts[i]);
                if (i == parts.length - 1) {
                    /* Add linebreak if last element. */
                    sb.append(System.lineSeparator());
                } else {
                    /* Add separator if not last element. */
                    sb.append(mSeperatorChar);
                }
            } else if (parts[i].contains(mQuotationMark) && !awaitingClosingQtm) {
                // Opening quotation mark
                sb.append(parts[i]);
                awaitingClosingQtm = true;
            } else if (awaitingClosingQtm) {
                // Inside quoted string, append but do not append comma.
                sb.append(parts[i]);
            } else {
                // A regular value (value not wrapped in quotes).
                sb.append(parts[i]);
                if (i == parts.length - 1) {
                    /* Add linebreak if last element. */
                    sb.append(System.lineSeparator());
                } else {
                    /* Add separator if not last element. */
                    sb.append(mSeperatorChar);
                }
            }
        }

        return sb.toString();
    }

    private boolean hasQuotationMarks(String[] values) {
        for (int i = 0; i < values.length; i++) {
            if (values[i].contains(mQuotationMark)) {
                return true;
            }
        }
        return false;
    }
}
