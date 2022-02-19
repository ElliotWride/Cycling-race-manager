package cycling;

import java.util.ArrayList;

public class Race {
    private static int numRaces;
    private Races races = new Races();
    private int ID;
    private String name, description;
    private Object[] stages;
    private String[] competitors;

    public Race(String name, String description){
        this.ID = ++numRaces;
        this.name = name;
        this.description = description;
        races.addRace(this);
    }

    public int getRaceID() {
        return ID;
    }


}
