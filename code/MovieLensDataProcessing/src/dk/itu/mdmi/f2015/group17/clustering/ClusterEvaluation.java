package dk.itu.mdmi.f2015.group17.clustering;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * TODO add class documentation.
 *
 * @author Janus Varmarken
 */
public class ClusterEvaluation {

    public static void main(String[] args) throws IOException {
        // Overwrite file each run.
        BufferedWriter writer = new BufferedWriter(new FileWriter("/Users/varmarken/Desktop/elbow.csv", false));
        // Write header.
//        writer.write("k,withinClusterVarianceSum");
        writer.write("k,sumOfWithinClusterVariances");
        writer.write(System.getProperty("line.separator"));
        for (int k = 2; k < 201; k++) {
//            if (k == 133 || k == 145 || k == 198)
//                continue;
            writer.write(getOutputForK(k));
            writer.write(System.getProperty("line.separator"));
        }
        writer.flush();
        writer.close();
    }

    private static String getOutputForK(int k) throws IOException {
        BufferedReader r1 = new BufferedReader(new FileReader("/Users/varmarken/Desktop/ClusterFiles/clustering" + k + "centroids.csv"));
        BufferedReader r2 = new BufferedReader(new FileReader("/Users/varmarken/Desktop/ClusterFiles/clustering" + k + ".csv"));
        // Skip headers
        r1.readLine();
        r2.readLine();

        Map<String, Cluster> clusterMap = new HashMap<String, Cluster>();
        String line = null;

        // Read cluster-centroids
        while((line = r1.readLine()) != null) {
            String[] elements = line.split(",");
            User u = User.fromArray(elements);
            Cluster c = new Cluster(elements[elements.length -1], u);
            clusterMap.put(c.mId, c);
        }

        line = null;
        // Read users and put them into their respective clusters
        while((line = r2.readLine()) != null) {
            String[] elements = line.split(",");
            User u = User.fromArray(elements);
            // Read what cluster the user belongs to.
            String clusterId = elements[elements.length -1];
            clusterMap.get(clusterId).mItems.add(u);
        }

        // sum of within cluster variance
//        double sumWithinClusterVar = 0.0;

//        for (String key : clusterMap.keySet()) {
//            Cluster c = clusterMap.get(key);
//            System.out.println("Cluster with ID="+c.mId+" has a variance of " + c.getVariance());
//            sumWithinClusterVar += c.getVariance();
//        }

//        System.out.println("[k=" + k + "] Sum of within cluster variance: " + sumWithinClusterVar);
//        return k + "," + sumWithinClusterVar;

        double withinClusterSumOfSquaredDistances = 0.0;
        for (String key : clusterMap.keySet()) {
            Cluster c = clusterMap.get(key);
            withinClusterSumOfSquaredDistances += c.getSumOfWithinSquaredDistances() / (double) c.mItems.size();
        }
        return k + "," + withinClusterSumOfSquaredDistances;
    }

    private static class Cluster {
        private final String mId;
        private List<ClusterItem> mItems = new ArrayList<ClusterItem>();
        private final ClusterItem mCentroid;

        public Cluster(String clusterId, ClusterItem centroid) {
            mId = clusterId;
            mCentroid = centroid;
        }

        public double getSumOfWithinSquaredDistances() {
            double result = 0.0;
            for (ClusterItem item : mItems) {
                result += item.getDistance(mCentroid);
            }
            return result;
        }

        public double getVariance() {
            double result = 0.0;
            for (ClusterItem item : mItems) {
                result += item.getDistance(mCentroid) * 1.0 / (double) mItems.size();
            }
            return result;
//            return result / (double) mItems.size();
        }
    }

    /**
     * Contract that items placed in a {@link ClusterEvaluation.Cluster} must fulfill.
     */
    interface ClusterItem {

        /**
         * Calculates the squared distance between this {@code ClusterItem} and another {@code ClusterItem}
         * @param other The {@code ClusterItem} to compare {@code this} against.
         * @return The squared distance between {@code this} and {@code other}.
         */
        double getDistance(ClusterItem other);
    }


    private static class User implements ClusterItem {

        private double mRatingAdventure;
        private double mRatingAnimation;
        private double mRatingChildren;
        private double mRatingComedy;
        private double mRatingFantasy;
        private double mRatingRomance;
        private double mRatingDrama;
        private double mRatingAction;
        private double mRatingCrime;
        private double mRatingThriller;
        private double mRatingHorror;
        private double mRatingMystery;
        private double mRatingScifi;
        private double mRatingImax;
        private double mRatingDocumentary;
        private double mRatingWar;
        private double mRatingMusical;
        private double mRatingWestern;
        private double mRatingFilmnoir;

        public static User fromArray(String[] ratings) {
            User u = new User();
            int i = 0;
            u.mRatingAdventure = Double.parseDouble(ratings[i++]);
            u.mRatingAnimation = Double.parseDouble(ratings[i++]);
            u.mRatingChildren = Double.parseDouble(ratings[i++]);
            u.mRatingComedy = Double.parseDouble(ratings[i++]);
            u.mRatingFantasy = Double.parseDouble(ratings[i++]);
            u.mRatingRomance = Double.parseDouble(ratings[i++]);
            u.mRatingDrama = Double.parseDouble(ratings[i++]);
            u.mRatingAction = Double.parseDouble(ratings[i++]);
            u.mRatingCrime = Double.parseDouble(ratings[i++]);
            u.mRatingThriller = Double.parseDouble(ratings[i++]);
            u.mRatingHorror = Double.parseDouble(ratings[i++]);
            u.mRatingMystery = Double.parseDouble(ratings[i++]);
            u.mRatingScifi = Double.parseDouble(ratings[i++]);
            u.mRatingImax = Double.parseDouble(ratings[i++]);
            u.mRatingDocumentary = Double.parseDouble(ratings[i++]);
            u.mRatingWar = Double.parseDouble(ratings[i++]);
            u.mRatingMusical = Double.parseDouble(ratings[i++]);
            u.mRatingWestern = Double.parseDouble(ratings[i++]);
            u.mRatingFilmnoir = Double.parseDouble(ratings[i++]);
            return u;
        }

        @Override
        public double getDistance(ClusterItem other) {
            User usr = (User) other;
            return Math.pow(mRatingAction - usr.mRatingAction, 2.0) +
                    Math.pow(mRatingAdventure - usr.mRatingAdventure, 2.0) +
                    Math.pow(mRatingAnimation - usr.mRatingAnimation, 2.0) +
                    Math.pow(mRatingChildren - usr.mRatingChildren, 2.0) +
                    Math.pow(mRatingComedy - usr.mRatingComedy, 2.0) +
                    Math.pow(mRatingFantasy - usr.mRatingFantasy, 2.0) +
                    Math.pow(mRatingRomance - usr.mRatingRomance, 2.0) +
                    Math.pow(mRatingDrama - usr.mRatingDrama, 2.0) +
                    Math.pow(mRatingCrime - usr.mRatingCrime, 2.0) +
                    Math.pow(mRatingThriller - usr.mRatingThriller, 2.0) +
                    Math.pow(mRatingHorror - usr.mRatingHorror, 2.0) +
                    Math.pow(mRatingMystery - usr.mRatingMystery, 2.0) +
                    Math.pow(mRatingScifi - usr.mRatingScifi, 2.0) +
                    Math.pow(mRatingImax - usr.mRatingImax, 2.0) +
                    Math.pow(mRatingDocumentary - usr.mRatingDocumentary, 2.0) +
                    Math.pow(mRatingWar - usr.mRatingWar, 2.0) +
                    Math.pow(mRatingMusical - usr.mRatingMusical, 2.0) +
                    Math.pow(mRatingWestern - usr.mRatingWestern, 2.0) +
                    Math.pow(mRatingFilmnoir - usr.mRatingFilmnoir, 2.0);
        }

    }
}
