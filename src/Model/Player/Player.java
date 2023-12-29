package Model.Player;
/**
 * @version 1.0
 * @Author V1dalTheAbidal
 */
public class Player {
    private String name;
    private int playerNumber;
    private boolean hasPlayed;
    private boolean finished;
    private COLOR playerColor;



    /**
     * <b>Constructor</b>: Constructs a new Player
     * <b>Postcondition:</b> A new Player has been created.
     */

    public Player(String name, int playerNumber, COLOR playerColor){
        this.name = name;
        this.playerNumber = playerNumber;
        this.playerColor = playerColor;
    }

    /**
     * <b>Transformer:</b> Some initialisation of the player
     * <b>Postcondition:</b> The player has been initialised.
     *
     */
    public void initializePlayer(){
        this.hasPlayed = false;
        this.finished = false;
        this.playerColor = getPlayerColor();
    }

    //getters
    /**
     * <b>Observer:</b> Checks if the player has played
     * <b>Postcondition:</b> Returns true if the player has played, false otherwise.
     */
    public boolean isHasPlayed() {
        return hasPlayed;
    }

    /**
     * <b>Observer:</b> Checks if the player has finished
     * <b>Postcondition:</b> Returns true if the player has finished, false otherwise.
     */
    public boolean isFinished() {
        return finished;
    }

    /**
     * <b>Accessor:</b> Returns the player color
     * @return playerColor
     */
    public COLOR getPlayerColor() {
        return playerColor;
    }

    //setters
    /**
     * <b>Transformer:</b> Sets the player color
     * <b>Postcondition:</b> The player color has been set.
     * @param playerColor
     */
    public void setHasPlayed (boolean hasPlayed){
        this.hasPlayed = hasPlayed;
    }
    /**
     * <b>Transformer:</b> Sets the player color
     * <b>Postcondition:</b> The player color has been set.
     * @param playerColor
     */
    public void setFinished(boolean finished) {
        this.finished = finished;
    }
}
