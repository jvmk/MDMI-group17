package dk.itu.mdmi.f2015.group17.clustering;

import java.util.ArrayList;
import java.util.List;

/**
* TODO add class documentation.
*
* @author Janus Varmarken
*/
public class Cluster {
    private final String mId;
    private List<ClusterItem> mItems = new ArrayList<ClusterItem>();
    private final ClusterItem mCentroid;

    public Cluster(String clusterId, ClusterItem centroid) {
        mId = clusterId;
        mCentroid = centroid;
    }

    public String getId() {
        return mId;
    }

    public void addItem(ClusterItem item) {
        mItems.add(item);
    }

    /**
     * Get number of items in this cluster (excluding the centroid).
     * @return The number of items in this cluster.
     */
    public int getCount() {
        return mItems.size();
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
