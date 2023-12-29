package Model.Cards;

import Model.Board.Board;
import Model.Cards.Card;
import Model.Pawn.PlayerPawn;

/**
 * @version 1.0
 * @Author V1dalTheAbidal
 */

public class NumberCard implements Card{
    private int number;
    private int movementRange;


    /**
     * <b>Constructor:</b> Creates a new NumberCard
     * <b>Postcondition:</b> A new NumberCard has been created
     * @param number
     */
    public NumberCard(int number){
        this.number = number;
    }

    /**
     * <b>Transformer:</b> sets the card's number
     * <b>Postcondition:</b> card's number has been set
     * @param int value
     */
    @Override
    public int getNumber(){
        return number;
    }

    /**
     * <b>Transformer:</b> sets the card's number
     * <b>Postcondition:</b> card's number has been set
     * @param int value
     */
    @Override
    public void setNumber(int number){
        this.number = number;
    }

    /**
     * <b>Accessor:</b> returns the cards movement range
     * <b>Postcondition:</b> card's movement range has been returned
     * @return int tempValue
     */
    @Override
    public int getMovementRange(){
        return movementRange;
    }

    /**
     * <b>Transformer:</b> sets the card's movement range
     * <b>Postcondition:</b> the card's movement range has been set
     * @param int j
     */
    @Override
    public void setMovementRange(int movementRange){
        this.movementRange = movementRange;
    }



}
