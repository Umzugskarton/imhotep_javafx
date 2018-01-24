package GameEvents;


import SRVevents.Event;

public class VoyageToStoneSiteManualDumpEvent implements Event {

  public VoyageToStoneSiteManualDumpEvent(){}

  public String getType(){
    return "VoyageToStoneSiteManualDump";
  }
}
