package Model.Cards;
import Model.Board.Board;
import Model.Pawn.PlayerPawn;

/**
 * @version 1.0
 * @author V1dalTheAbidal
 */

public abstract interface Card {
    /**
     * <b>Accessor:</b> returns the card's number
     * <b>Postcondition:</b> card's number has been returned
     * @return int value
     */
    public int getNumber();

    /**
     * <b>Transformer:</b> sets the card's number
     * <b>Postcondition:</b> card's number has been set
     * @param int value
     */
    public void setNumber(int number);

    /**
     * <b>Accessor:</b> returns the cards movement range
     * <b>Postcondition:</b> card's movement range has been returned
     * @return int tempValue
     */
    public int getMovementRange();


    /**
     * <b>Transformer:</b> sets the card's movement range
     * <b>Postcondition:</b> the card's movement range has been set
     * @param int j
     */
    public void setMovementRange(int movementRange);





}
