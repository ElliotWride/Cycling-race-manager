package cycling;

import java.awt.image.AreaAveragingScaleFilter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public class CyclingPortal implements CyclingPortalInterface {
    private static ArrayList<Race> races = new ArrayList<Race>();
    private static ArrayList<Team> teams = new ArrayList<Team>();

    @Override
    public int[] getRaceIds() {
        int[] raceIDs = new int[races.size()];
        for (int i = 0; i<races.size();i++) {
            raceIDs[i] = races.get(i).getRaceID();
        }
        return raceIDs;
    }

    @Override
    public int createRace(String name, String description) throws IllegalNameException, InvalidNameException {
        Race newRace = new Race(name,description);
        races.add(newRace);
        return newRace.getRaceID();
    }

    public Race getRace(int raceID){
        int i;
        for(i = 0; i < races.size();i++){
            if (races.get(i).getRaceID() == raceID) {
                break;
            }
        }
        return races.get(i);
    }

    @Override
    public String viewRaceDetails(int raceId) throws IDNotRecognisedException {
        Race race = getRace(raceId);
        String desc = race.getNameAndDescription();
        desc += race.getNumberOfStages();
        ArrayList<Stage> stages = race.getStages();
        double len = 0;
        for (int i=0; i<stages.size(); i++){
            len += stages.get(i).getLength();
        }
        desc += len;
        return desc;
    }

    @Override
    public void removeRaceById(int raceId) throws IDNotRecognisedException {
        for (int i = 0; i < races.size(); i++){
            if (raceId == races.get(i).getRaceID()){
                races.remove(i);
            }
        }
    }

    @Override
    public int getNumberOfStages(int raceId) throws IDNotRecognisedException {
        Race race = getRace(raceId);
        return race.getNumberOfStages();
    }

    @Override
    public int addStageToRace(int raceId, String stageName, String description, double length, LocalDateTime startTime, StageType type) throws IDNotRecognisedException, IllegalNameException, InvalidNameException, InvalidLengthException {
        Race race = getRace(raceId);
        return  race.addStageToRace(stageName,description,length,startTime,type);
    }

    @Override
    public int[] getRaceStages(int raceId) throws IDNotRecognisedException {
        Race race = getRace(raceId);
        return race.getStageIDs();
    }

    public Stage getStage(int stageId){
        Stage stage = null;
        for(int i = 0; i<races.size(); i++){
            Race race = races.get(i);
            ArrayList<Stage> stages = race.getStages();
            for (int j = 0; j<race.getNumberOfStages(); j++){
                stage = stages.get(i);
            }
        }
        return stage;
    }

    @Override
    public double getStageLength(int stageId) throws IDNotRecognisedException {
        double len = 0;
        Stage stage = getStage(stageId);
        return stage.getLength();
    }

    @Override
    public void removeStageById(int stageId) throws IDNotRecognisedException {
        for(int i = 0; i<races.size(); i++){
            Race race = races.get(i);
            ArrayList<Stage> stages = race.getStages();
            for (int j = 0; j<race.getNumberOfStages(); j++){
                stages.remove(i);
            }
        }
    }

    @Override
    public int addCategorizedClimbToStage(int stageId, Double location, SegmentType type, Double averageGradient, Double length) throws IDNotRecognisedException, InvalidLocationException, InvalidStageStateException, InvalidStageTypeException {
        Stage stage = getStage(stageId);
        return stage.addCategorizedClimbToStage(stageId,location,type,averageGradient,length);
    }

    @Override
    public int addIntermediateSprintToStage(int stageId, double location) throws IDNotRecognisedException,
            InvalidLocationException, InvalidStageStateException, InvalidStageTypeException {
        Stage stage = getStage(stageId);
        return stage.addIntermediateSprintToStage(stageId, location);
    }

    public Segment getSegment(int segmentID){
        Segment segment = null;
        for (int i =0; i<races.size(); i++) {
            Race race = races.get(i);
            ArrayList<Stage> stages = race.getStages();
            for (int j = 0; j < race.getNumberOfStages(); j++) {
                Stage stage = stages.get(i);
                ArrayList<Segment> segments = stage.getStageSegments();
                for (int k = 0; k < segments.size(); k++) {
                    segment = segments.get(i);
                    if (segmentID == segment.getSegmentID()){
                        break;
                    }
                }
            }
        }
        return segment;
    }



    @Override
    public void removeSegment(int segmentId) throws IDNotRecognisedException, InvalidStageStateException {
        Segment segment = null;
        for (int i =0; i<races.size(); i++) {
            Race race = races.get(i);
            ArrayList<Stage> stages = race.getStages();
            for (int j = 0; j < race.getNumberOfStages(); j++) {
                Stage stage = stages.get(i);
                ArrayList<Segment> segments = stage.getStageSegments();
                for (int k = 0; k < segments.size(); k++) {
                    segment = segments.get(i);
                    if (segmentId == segment.getSegmentID()){
                        stage.removeSegment(i);
                    }
                }
            }
        }
    }

    @Override
    public void concludeStagePreparation(int stageId) throws IDNotRecognisedException, InvalidStageStateException {
        Stage stage = getStage(stageId);
        stage.concludeStatePreparation();
    }

    @Override
    public int[] getStageSegments(int stageId) throws IDNotRecognisedException {
        Stage stage = getStage(stageId);
        return stage.getStageSegmentsIDs();
    }

    @Override
    public int createTeam(String name, String description) throws IllegalNameException, InvalidNameException {
        Team team = new Team(name, description);
        teams.add(team);
        return team.getTeamID();
    }

    @Override
    public void removeTeam(int teamId) throws IDNotRecognisedException {
        for (int i = 0; i<teams.size(); i++){
            if (teams.get(i).getTeamID() == teamId){
                teams.remove(i);
            }
        }
    }

    @Override
    public int[] getTeams() {
        int[] teamIDs = new int[teams.size()];
        for (int i = 0; i < teams.size(); i++){
            teamIDs[i] = teams.get(i).getTeamID();
        }
        return teamIDs;
    }

    @Override
    public int[] getTeamRiders(int teamId) throws IDNotRecognisedException {
        Team team = null;
        for (int i = 0; i<teams.size(); i++){
            if (teams.get(i).getTeamID() == teamId){
                team = teams.get(i);
            }
        }
        return team.getRiderIDs();
    }

    @Override
    public int createRider(int teamID, String name, int yearOfBirth)
            throws IDNotRecognisedException, IllegalArgumentException {
        Rider rider = new Rider(name,teamID,yearOfBirth);
        for (int i = 0; i<teams.size(); i++){
            if (teams.get(i).getTeamID() == teamID){
                teams.get(i).addRider(rider);
            }
        }
        return rider.getRiderID();

    }

    @Override
    public void removeRider(int riderId) throws IDNotRecognisedException {
        for (int i = 0; i<teams.size(); i++){
            Team team = teams.get(i);
            ArrayList<Rider> riders = team.getRiders();
            for(int j = 0; j<team.getRiders().size(); j++){
                Rider rider = riders.get(i);
                if(riders.get(j).getRiderID()==riderId){
                    team.removeRider(rider);
                }
            }
        }
    }

    @Override
    public void registerRiderResultsInStage(int stageId, int riderId, LocalTime... checkpoints)
            throws IDNotRecognisedException, DuplicatedResultException, InvalidCheckpointsException,
            InvalidStageStateException {
        getStage(stageId).registerRiderResult(riderId,checkpoints);


    }

    @Override
    public LocalTime[] getRiderResultsInStage(int stageId, int riderId) throws IDNotRecognisedException {
        return getStage(stageId).getRiderResults(riderId);
    }

    @Override
    public LocalTime getRiderAdjustedElapsedTimeInStage(int stageId, int riderId) throws IDNotRecognisedException {
        getStage(stageId).getRiderElapsedTime(riderId);

        return null;
    }

    @Override
    public void deleteRiderResultsInStage(int stageId, int riderId) throws IDNotRecognisedException {
        // TODO Auto-generated method stub

    }

    @Override
    public int[] getRidersRankInStage(int stageId) throws IDNotRecognisedException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public LocalTime[] getRankedAdjustedElapsedTimesInStage(int stageId) throws IDNotRecognisedException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int[] getRidersPointsInStage(int stageId) throws IDNotRecognisedException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int[] getRidersMountainPointsInStage(int stageId) throws IDNotRecognisedException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void eraseCyclingPortal() {
        // TODO Auto-generated method stub

    }

    @Override
    public void saveCyclingPortal(String filename) throws IOException {
        // TODO Auto-generated method stub

    }

    @Override
    public void loadCyclingPortal(String filename) throws IOException, ClassNotFoundException {
        // TODO Auto-generated method stub

    }

    @Override
    public void removeRaceByName(String name) throws NameNotRecognisedException {
        // TODO Auto-generated method stub

    }

    @Override
    public LocalTime[] getGeneralClassificationTimesInRace(int raceId) throws IDNotRecognisedException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int[] getRidersPointsInRace(int raceId) throws IDNotRecognisedException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int[] getRidersMountainPointsInRace(int raceId) throws IDNotRecognisedException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int[] getRidersGeneralClassificationRank(int raceId) throws IDNotRecognisedException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int[] getRidersPointClassificationRank(int raceId) throws IDNotRecognisedException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int[] getRidersMountainPointClassificationRank(int raceId) throws IDNotRecognisedException {
        // TODO Auto-generated method stub
        return null;
    }

}

