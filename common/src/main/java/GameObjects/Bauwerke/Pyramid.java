package GameObjects.Bauwerke;


import GameObjects.Stone;

import java.util.ArrayList;
import java.util.List;

public class Pyramid {
    private Stone[][] base = new Stone[3][3];
    private Stone[][] middle = new Stone[2][2];
    private int state = 3;
    private List<Object> pyramid = new ArrayList<>();

    public Pyramid(){
        this.pyramid.add(null);
        this.pyramid.add(middle);
        this.pyramid.add(base);
    }

    public void add(Stone stone){
            if (this.state > 1) {
                Stone[][] temp = ((Stone[][]) pyramid.get(this.state));
                for (int i = 0; i <= temp.length; i++) {
                    for (int j = 0; j <= temp[i].length; j++) {
                        if (temp[i][j] == null){
                            temp[i][j] = stone;
                            this.pyramid.set(this.state, temp);
                            if (i == this.state && j == this.state){
                                this.state--;
                            }
                            break;
                        }
                    }
                }
            }
            else{
                if (this.pyramid.get(1) == null){
                    this.pyramid.set(1, stone);
                }
            }
    }

    public void addAll(Stone[] stones){
        for (Stone stone: stones){
            add(stone);
        }
    }


}
