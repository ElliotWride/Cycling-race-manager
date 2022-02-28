package cycling;

public class Climb extends Segment{
    SegmentType type;
    double averageGradient, length;

    public Climb( Double location, SegmentType type, Double averageGradient, Double length){
        super(location);
        this.type = type;
        this.length = length;
        this.averageGradient = averageGradient;
    }
}
