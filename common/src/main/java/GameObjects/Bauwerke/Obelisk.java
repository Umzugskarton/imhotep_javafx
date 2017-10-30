package GameObjects.Bauwerke;


import GameObjects.Stone;

import java.util.ArrayList;


public class Obelisk {
    private ArrayList<Stone>[] obelisks;

    public Obelisk(int size){
        obelisks = new ArrayList[size];
    }

    public void add(Stone stone){
           this.obelisks[stone.getOwner()].add(stone);
    }

    public void addAll(Stone[] stones){
        for (Stone stone: stones){
            add(stone);
        }
    }

    public ArrayList<Stone>[] getObelisks() {
        return this.obelisks;
    }
}
