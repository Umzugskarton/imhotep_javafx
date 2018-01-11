package GameEvents;

import SRVevents.Event;

import java.util.Date;

//TODO
public class PyramidEvent implements Event {
  private String event = "pyramid";
  // private Pyramid pyramid;
  private Date date;

  /* public PyramidEvent(Pyramid pyramid) {
     this.pyramid = pyramid;
   }

 */
  public Date getDate() {
    return this.date;
  }
/*
  public Pyramid getPyramid() {
    return pyramid;
  }

  public void setPyramid(Pyramid pyramid) {
    this.pyramid = pyramid;
  }
  */

}
