package cycling;


public class Competitor {
    private static int numCompetitiors;
    private int ID, GC, points, mountain;
    private String name;

    public Competitor(String name){
        this.name = name;
        this.ID = ++numCompetitiors;
    }
}
