package cycling;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;

public class Stage {
    private static int numStages;
    private int stageID;
    private ArrayList<Segment> segments = new ArrayList<Segment>();

    private String name, description;
    private LocalDateTime startTime;
    private  double length;
    private StageType type;
    private String state = "Preparing";
    private Dictionary results = new Hashtable();

    public Stage (int raceID, String stageName, String description, double length, LocalDateTime startTime, StageType type){
        this.stageID = ++numStages;
        this.type = type;
        this.name = stageName;
        this.description = description;
        this.length = length;
        this.startTime = startTime;
    }

    public int getStageID(){
        return stageID;
    }

    public double getLength() {
        return length;
    }

    public void addSegment(Segment segment){
        segments.add(segment);
    }

    public void removeSegment(int segmentID) {
        for (int i = 0; i < segments.size(); i++){
            if (segments.get(i).getSegmentID() == segmentID) {
                segments.remove(i);
            }
        }
    }

    public int addCategorizedClimbToStage(int stageID, double location,SegmentType type, double avgGradient,double length){
        Climb climb = new Climb(location,type,length,avgGradient);
        segments.add(climb);
        return climb.getSegmentID();
    }

    public int addIntermediateSprintToStage(int stageID, double location){
        Sprint sprint = new Sprint(location);
        segments.add(sprint);
        return sprint.getSegmentID();
    }

    public void concludeStatePreparation(){
        this.state = "Waiting for results";
    }

    public int[] getStageSegmentsIDs() {
        int[] segmentIDs = new int[segments.size()];
        for (int i = 0; i < segments.size(); i++){
            segmentIDs[i] = segments.get(i).getSegmentID();
        }
        return segmentIDs;
    }

    public ArrayList<Segment> getStageSegments(){
        return segments;
    }

    public void registerRiderResult (int riderID, LocalTime... checkpoints){
        this.results.put(riderID,checkpoints);
    }

    public LocalTime[] getRiderResults(int riderID){
        return (LocalTime[]) results.get(riderID);
    }

    public LocalTime getRiderElapsedTime(int riderID){

        return (LocalTime) elapsedTime;
    }


}
