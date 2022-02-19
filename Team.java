package cycling;

public class Team {
    private static int numTeams;
    private int ID;
    private String name;
    private int[] memberID;

    public Team(String name) {
        this.name = name;
        this.ID = ++numTeams;
    }
}
