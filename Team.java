package cycling;

import java.util.ArrayList;

public class Team {
    private static int numTeams;
    private int teamID;
    private String name, desc = null;
    private ArrayList<Rider> riders = new ArrayList<Rider>();

    public Team(String name, String desc) {
        this.name = name;
        this.desc = desc;
        this.teamID = ++numTeams;
    }

    public int getTeamID() {
        return teamID;
    }

    public void addRider(Rider rider){
        riders.add(rider);
    }

    public int[] getRiderIDs(){
        int[] riderIDs = new int[riders.size()];
        for (int i = 0; i<riders.size(); i++){
            riderIDs[i] = riders.get(i).getRiderID();
        }
        return riderIDs;
    }

    public ArrayList<Rider> getRiders(){
        return  riders;
    }

    public void removeRider(Rider rider){
        riders.remove(rider);
    }

}
