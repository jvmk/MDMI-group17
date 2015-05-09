package dk.itu.mdmi.f2015.group17.clustering;

/**
* TODO add class documentation.
*
* @author Janus Varmarken
*/
public class User implements ClusterItem {

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
    private double mId = -1.0;

    /**
     * Equivalent to {@code fromArray(ratings, 0)}
     * @param ratings The attributes that describe the user's average rating.
     * @return a {@code User} object with the data values found in {@code ratings}.
     */
    public static User fromArray(String[] ratings) {
        return fromArray(ratings, 0);
    }

    /**
     * Produce a {@code User} from a string array.
     * @param attributeValues The user attribute values.
     * @param offset The index (inclusive) in {@code data} where the attributes describing the user's average ratings begin.
     * @return a {@code User} object with the data values found in {@code data}.
     */
    public static User fromArray(String[] attributeValues, int offset) {
        User u = new User();
        int i = offset;
        u.mRatingAdventure = Double.parseDouble(attributeValues[i++]);
        u.mRatingAnimation = Double.parseDouble(attributeValues[i++]);
        u.mRatingChildren = Double.parseDouble(attributeValues[i++]);
        u.mRatingComedy = Double.parseDouble(attributeValues[i++]);
        u.mRatingFantasy = Double.parseDouble(attributeValues[i++]);
        u.mRatingRomance = Double.parseDouble(attributeValues[i++]);
        u.mRatingDrama = Double.parseDouble(attributeValues[i++]);
        u.mRatingAction = Double.parseDouble(attributeValues[i++]);
        u.mRatingCrime = Double.parseDouble(attributeValues[i++]);
        u.mRatingThriller = Double.parseDouble(attributeValues[i++]);
        u.mRatingHorror = Double.parseDouble(attributeValues[i++]);
        u.mRatingMystery = Double.parseDouble(attributeValues[i++]);
        u.mRatingScifi = Double.parseDouble(attributeValues[i++]);
        u.mRatingImax = Double.parseDouble(attributeValues[i++]);
        u.mRatingDocumentary = Double.parseDouble(attributeValues[i++]);
        u.mRatingWar = Double.parseDouble(attributeValues[i++]);
        u.mRatingMusical = Double.parseDouble(attributeValues[i++]);
        u.mRatingWestern = Double.parseDouble(attributeValues[i++]);
        u.mRatingFilmnoir = Double.parseDouble(attributeValues[i++]);
        return u;
    }

    /**
     * Equivalent to {@code fromArray(ratings, 0)}, but also sets the user's id.
     * @param attributeValues See {@link #fromArray(String[], int)}.
     * @param offset See {@link #fromArray(String[], int)}.
     * @param userId The ID of the user.
     * @return The user returned by {@link #fromArray(String[], int)} with its ID field set to {@code userId}.
     */
    public static User fromArray(String[] attributeValues, int offset, double userId) {
        User u = fromArray(attributeValues, offset);
        u.mId = userId;
        return u;
    }

    public double getId() {
        return mId;
    }

    /**
     * {@inheritDoc}
     */
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
