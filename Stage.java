package cycling;

public class Stage {
    private static int numStages;
    private int ID;
    private boolean points, mountain;
    private String type;
    private int[] times, IDs;
    private String climb;

    public Stage (boolean points, boolean mountain, String type, String climb){
        this.ID = ++numStages;
        this.points = points;
        this.mountain = mountain;
        this.type = type;
        this.climb = climb;
    }
}
