package game;


import user.User;

public class Player {
    private int points = 0;
    private User user;

    public Player(User user){

    }

    public User getUser() {
        return user;
    }

    public void  addPoints(int points){
        this.points+=points;
    }

    public int getPoints() {
        return points;
    }
}
