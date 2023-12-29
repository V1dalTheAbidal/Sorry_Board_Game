package Model.Turn;

import Model.Player.Player;

import java.util.ArrayList;

/**
 *  @version 1.0
 *  @Author V1dalTheAbidal
 */

public class Turn {
    private int currentPlayer;
    private int numberOfPlayers;
    private int nextPlayer, lastPlayer;

    /**
     * <b>Constructor:</b> Creates a new Turn
     * <b>Postcondition:</b> A new Turn has been created
     */
    public Turn(){
        this.currentPlayer = 0;
        this.lastPlayer = 0;
        this.numberOfPlayers = 2;
        this.nextPlayer = 1;
    }

    /**
     * <b>Constructor:</b> Creates a new Turn
     * <b>Postcondition:</b> A new Turn has been created
     * @param numberOfPlayers
     */

    public Turn(int numberOfPlayers){
        currentPlayer = 0;
        if (numberOfPlayers <= 4 && numberOfPlayers >= 2){
            this.numberOfPlayers = numberOfPlayers;
        }
        this.lastPlayer =0;
        this.nextPlayer = 1;
    }

    /**
     * <b>Accessor:</b> Get the numbers of players playing
     * <b>Postcondition:</b> returns the number of players playing
     * @return int numberOfPlayers
     */
    public int getCurrentPlayer(){
        return this.currentPlayer;
    }

    /**
     * <b>Transformer:</b> Sets the current player playing
     * <b>Postcondition:</b> The current player playing has been set
     * @param players
     */
    public void setCurrentPlayer(ArrayList <Player> players) {
        if (this.currentPlayer == 1 && !players.get(0).isFinished()) {
            this.currentPlayer = 0;
        }
        else if (this.currentPlayer == 0 && !players.get(1).isFinished()){
           this.currentPlayer = 1;
        }
    }


    /**
     * <b>Transformer:</b> Sets the turn for the next player
     * <b>Postcondition:</b> The turn of the next player has been set
     * @param players
     */
    public void setNextPlayerTurn(ArrayList<Player> players) {
        if (this.currentPlayer == 1 && !players.get(0).isFinished()) {
            this.nextPlayer = 0;
        }
        else if (this.currentPlayer == 0 && !players.get(1).isFinished()){
            this.nextPlayer = 0;
        }
    }

    /**
     * <b>Accessor:</b> Get the next players turn
     * <b>Postcondition:</b> returns the next players turn
     * @return nextPlayer
     */
    public int  getNextPlayerTurn(){

        return nextPlayer;
    }

    /**
     * <b>Observer:</b> Checks if a player has finished
     * <b>Postcondition:</b> returns true if a player has finished, else false
     * @return true if a player has finished, else false
     */
    public boolean checkIfPlayerFinished(Player playing){
        return playing.isFinished();
    }


    /**
     * <b>Transformer:</b> Sets the last player played
     * <b>Postcondition:</b> The last player played has been set
     * @param lastPlayer
     */
    public void setLastPlayer(ArrayList <Player> players) {
        if (currentPlayer == 1 && !players.get(0).isFinished()) {
            lastPlayer = 1;
        }
        else if (currentPlayer == 0 && !players.get(1).isFinished()){
            lastPlayer = 0;
        }
    }

    /**
     * <b>Accessor:</b> Get the last player played
     * <b>Postcondition:</b> returns the last player played
     * @return lastPlayer
     */
    public int getLastPlayer() {
        return this.lastPlayer;
    }

    /**
     * <b>Accessor:</b> Get the number of players playing
     * <b>Postcondition:</b> returns the number of players playing
     * @return numberOfPlayers
     */
    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }
    /**
     * <b>Transformer:</b> Sets the number of players playing
     * <b>Postcondition:</b> The number of players has been set
     */
    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

}
