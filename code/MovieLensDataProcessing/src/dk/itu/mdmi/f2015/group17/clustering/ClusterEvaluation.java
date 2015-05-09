package dk.itu.mdmi.f2015.group17.clustering;

import java.io.*;
import java.util.HashMap;
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
            clusterMap.put(c.getId(), c);
        }

        line = null;
        // Read users and put them into their respective clusters
        while((line = r2.readLine()) != null) {
            String[] elements = line.split(",");
            User u = User.fromArray(elements);
            // Read what cluster the user belongs to.
            String clusterId = elements[elements.length -1];
            clusterMap.get(clusterId).addItem(u);
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
            withinClusterSumOfSquaredDistances += c.getSumOfWithinSquaredDistances() / (double) c.getCount();
        }
        return k + "," + withinClusterSumOfSquaredDistances;
    }


}
