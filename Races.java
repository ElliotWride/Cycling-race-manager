package cycling;

import java.util.ArrayList;

public class Races {
    private static ArrayList<Race> races = new ArrayList<Race>();

    public void addRace(Race newRace){
        races.add(newRace);
    }

    public Race getRace(int ID){
        int i;
        for(i = 0; i < races.size();i++){
            if (races.get(i).getRaceID() == ID) {
                break;
            }
        }
        return races.get(i);
    }

    public void deleteRace(int ID){
        int i;
        for(i = 0; i < races.size();i++) {
            if (races.get(i).getRaceID() == ID) {
                races.remove(i);
            }
        }
    }

    public int[] getRaceIDs(){
        int[] raceIds = new int[races.size()];
        for (int i = 0; i<races.size();i++) {
            raceIds[i] = races.get(i).getRaceID();
        }
        return raceIds;
    }
}