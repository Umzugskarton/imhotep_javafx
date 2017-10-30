package GameEvents;

import GameObjects.Bauwerke.Pyramid;
import SRVevents.Event;
import java.util.Date;


public class pyramidEvent implements Event {
    private String event = "pyramid";
    private Pyramid pyramid;
    private Date date;

    public pyramidEvent(Pyramid pyramid){
        this.pyramid=pyramid;
    }


    public Date getDate(){
        return this.date;
    }

    public Pyramid getPyramid() {
        return pyramid;
    }

    public void setPyramid(Pyramid pyramid) {
        this.pyramid = pyramid;
    }

}
