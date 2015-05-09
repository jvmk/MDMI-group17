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
