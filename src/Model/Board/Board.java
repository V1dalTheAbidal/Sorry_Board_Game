package Model.Board;

import java.util.ArrayList;
/**
 * @version 1.0
 * @Author V1dalTheAbidal
 */
public class Board{
    private boolean gameStatus = false;

    /**
     * <b>transformer:</b> Initializes the Board.
     * <b>Postcondition:</b> the board has been initialized.
     */

    private void initializeBoard(){

    }

    /**
     * <b>transformer:</b> Sets status of the game
     * <b>Postcondition:</b> the game status has been set to started
     *
     */
    public void startGame(){
        gameStatus = true;
    }

    /**
     * <b>Accessor:</b> returns the status of the game
     * <b>Postcondition:</b> the game status has been returned
     * @return the status of the game
     */
    public boolean getGameStatus(){
        return gameStatus;
    }

}
