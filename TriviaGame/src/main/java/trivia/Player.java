package trivia;

public class Player {

    private String name;
    private int playerPosition = 1;
    private int score = 0;
    private boolean inPenaltyBox = false;

    public Player(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public int getPlayerPosition(){
        return this.playerPosition;
    }

    public void setPlayerPosition(int playerPosition){
        this.playerPosition = playerPosition;
    }

    public int getScore(){
        return this.score;
    }

    public void setScore(int score){
        this.score = score;
    }

    public void incrementScore(){
        this.score++;
    }

    public boolean getInPenaltyBox(){
        return this.inPenaltyBox;
    }

    public void setInPenaltyBox(boolean inPenaltyBox){
        this.inPenaltyBox = inPenaltyBox;
    }

    @Override
    public String toString() {
        return name;
    }
}
