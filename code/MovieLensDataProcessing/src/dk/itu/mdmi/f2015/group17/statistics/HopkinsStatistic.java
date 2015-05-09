package dk.itu.mdmi.f2015.group17.statistics;

import dk.itu.mdmi.f2015.group17.clustering.User;
import dk.itu.mdmi.f2015.group17.clustering.ClusterItem;
import java.io.IOException;
import java.util.List;

/**
 * TODO add class documentation.
 *
 * @author Janus Varmarken
 */
public class HopkinsStatistic {

    // 1 Read full data set.
    // 2 Read artificial samples set.
    // 3 Read experimental samples set.

    public static HopkinsStatistic fromFiles(String fullDataSetFilePath,
                                             String realSamplesFilePath,
                                             String artificialSamplesFilePath) throws IOException {
        UserFileReader reader = new UserFileReader();
        List<User> fullDataset = reader.readerUsersFile(fullDataSetFilePath);
        List<User> realSamples = reader.readerUsersFile(realSamplesFilePath);
        List<User> artificialSamples = reader.readerUsersFile(artificialSamplesFilePath);
        HopkinsStatistic hs = new HopkinsStatistic(fullDataset, realSamples, artificialSamples);
        return hs;
    }

    private final List<User> mFullDataset;
    private final List<User> mRealSamples;
    private final List<User> mArtificialSamples;

    private HopkinsStatistic(List<User> fullDataset,
                             List<User> realSamples,
                             List<User> artificialSamples) {
        mFullDataset = fullDataset;
        mRealSamples = realSamples;
        mArtificialSamples = artificialSamples;
    }

    public double computeHopkinsValue() {
        // Sum distance from artificial objects to nearest neighbor in full dataset.
//        double artificialDistances = 0.0;
//        for (User artificial : mArtificialSamples) {
//            artificialDistances +=
//        }
        double artificialDistances = sumDistancesToNearestNeighbors(mArtificialSamples);
        double realDistances = sumDistancesToNearestNeighbors(mRealSamples);
        return artificialDistances / (artificialDistances + realDistances);
    }

    private double sumDistancesToNearestNeighbors(List<User> input) {
        double sum = 0.0;
        for (User user : input) {
            sum += findDistanceToNearestNeighbor(user);
        }
        return sum;
    }

    /**
     * Calculates the Euclidean distance between two {@code ClusterItem}s.
     * @param item1 The first item.
     * @param item2 The second item.
     * @return The Euclidean distance between {@code item1} and {@code item2}.
     */
    private double getEuclideanDistance(ClusterItem item1, ClusterItem item2) {
        return Math.sqrt(item1.getDistance(item2));
    }

    /**
     * Finds the nearest neighbor of {@code user} in the full dataset.
     * @param user The {@code User} for which the nearest neighbor is to be found.
     * @return the nearest neighbor of {@code user} in the full dataset.
     */
    private User findNearestNeighbor(User user) {
        double minDist = Double.MAX_VALUE;
        User nearestNeighbor = null;
        for (User u : mFullDataset) {
            // Ignore self when searching for nearest neighbor.
            if (user.getId() == u.getId()) {
                continue;
            }

            double dist = getEuclideanDistance(user, u);
            if (dist < minDist) {
                minDist = dist;
                nearestNeighbor = u;
            }
        }
        return nearestNeighbor;
    }

    /**
     * Finds the distance from {@code user} to its nearest neighbor in the full dataset.
     * @param user The {@code User} for which the distance to its nearest neighbor is to be found.
     * @return the distance to the nearest neighbor of {@code user} (in the full dataset).
     */
    private double findDistanceToNearestNeighbor(User user) {
        double minDist = Double.MAX_VALUE;
        for (User u : mFullDataset) {
            // Ignore self when searching for nearest neighbor.
            if (user.getId() == u.getId()) {
                continue;
            }

            double dist = getEuclideanDistance(user, u);
            if (dist < minDist) {
                minDist = dist;
            }
        }
        return minDist;
    }
}
