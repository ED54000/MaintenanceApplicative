package trivia;

public class Player {

    private String name;
    private int playerPosition = 1;
    private int score = 0;
    private boolean inPenaltyBox = false;
    private int failedAttempts = 0;
    private int successfulAttempts = 0;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public int getPlayerPosition() {
        return this.playerPosition;
    }

    public void setPlayerPosition(int playerPosition) {
        this.playerPosition = playerPosition;
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void incrementScore() {
        if (successfulAttempts >= 3) {
            this.score += 2;
        } else {
            this.score++;
        }
    }

    public boolean getInPenaltyBox() {
        return this.inPenaltyBox;
    }

    public void setInPenaltyBox(boolean inPenaltyBox) {
        this.inPenaltyBox = inPenaltyBox;
    }

    @Override
    public String toString() {
        return name;
    }

    public int getFailedAttempts() {
        return failedAttempts;
    }

    public void addFailedAttempts() {
        this.failedAttempts++;
    }

    public void setFailedAttempts(int failedAttempts) {
        this.failedAttempts = failedAttempts;
    }

    public int getSuccessfulAttempts() {
        return successfulAttempts;
    }

    public void setSuccessfulAttempts(int successfulAttempts) {
        this.successfulAttempts = successfulAttempts;
    }

    public void addSuccessfulAttempts() {
        this.successfulAttempts++;
    }
}


