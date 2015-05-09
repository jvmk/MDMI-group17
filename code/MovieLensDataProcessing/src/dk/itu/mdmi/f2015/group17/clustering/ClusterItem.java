package dk.itu.mdmi.f2015.group17.clustering;

/**
 * Contract that items placed in a {@link dk.itu.mdmi.f2015.group17.clustering.Cluster} must fulfill.
 */
public interface ClusterItem {

    /**
     * Calculates the squared distance between this {@code ClusterItem} and another {@code ClusterItem}
     * @param other The {@code ClusterItem} to compare {@code this} against.
     * @return The squared distance between {@code this} and {@code other}.
     */
    double getDistance(ClusterItem other);
}
