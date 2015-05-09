package dk.itu.mdmi.f2015.group17.statistics;

import dk.itu.mdmi.f2015.group17.clustering.User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO add class documentation.
 *
 * @author Janus Varmarken
 */
public class UserFileReader {

    public List<User> readerUsersFile(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));

        // Skip header.
        reader.readLine();


        ArrayList<User> result = new ArrayList<>();
        String line = null;
        while ((line = reader.readLine()) != null) {
            String[] elements = line.split(",");
            // First element is the user's id.
            double id = Double.parseDouble(elements[0]);
            result.add(User.fromArray(elements, 1, id));
        }

        return result;
    }

}
