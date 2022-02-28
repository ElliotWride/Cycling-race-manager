package cycling;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Race {
    private static int numRaces;
    private int raceID;
    private String name, description;
    private ArrayList<Stage> stages = new ArrayList<Stage>();
    private String[] competitors;

    public Race(String name, String description){
        this.raceID = ++numRaces;
        this.name = name;
        this.description = description;
    }

    public int getRaceID() {
        return raceID;
    }

    public int getNumberOfStages(){
        return  this.stages.size();
    }

    public int addStageToRace(String stageName, String description, double length, LocalDateTime startTime, StageType type){
        Stage newStage = new Stage(this.raceID, stageName,description,length,startTime,type);
        this.stages.add(newStage);
        return this.raceID;
    }

    public int[] getStageIDs(){
        int numStages = this.getNumberOfStages();
        int[] stageIDs = new int[numStages];
        for (int i = 0; i<numStages; i++){
            stageIDs[i]=stages.get(i).getStageID();

        }
        return stageIDs;
    }

    public ArrayList<Stage> getStages(){
        return this.stages;
    }

    public void removeStageFromRace(int stageID){
        for (int i = 0; i< stages.size(); i++)
            if (stages.get(i).getStageID()==stageID){
            stages.remove(i);
        }
    }

    public int[] getStageSegments(){
        int i;
        for (i=0; i< stages.size(); i++) {
            if (stages.get(i).getStageID() == this.raceID) {
                break;
            }
        }
        return stages.get(i).getStageSegmentsIDs();
    }

    public String getNameAndDescription(){
        String desc = this.name +" "+ this.description;
        return desc;
    }
}
