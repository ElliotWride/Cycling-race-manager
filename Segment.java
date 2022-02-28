package cycling;

import java.util.Dictionary;
import java.util.Hashtable;

public class Segment {
    private static int numSegments;
    private int segmentID;
    private double location;


    public Segment(double location) {
        this.location = location;
        this.segmentID = ++numSegments;
    }


    public int getSegmentID(){
        return segmentID;
    }


}
