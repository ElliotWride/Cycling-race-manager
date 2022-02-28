package cycling;


public class Rider {
    private static int numMembers;
    private int teamID, riderID, GC, sprint, climb, yearOfBirth;
    private String name;

    public Rider(String name,int teamID,int yearOfBirth){
        this.name = name;
        this.riderID = ++numMembers;
        this.teamID = teamID;
        this.yearOfBirth = yearOfBirth;
    }

    public int getRiderID() {
        return riderID;
    }
}
