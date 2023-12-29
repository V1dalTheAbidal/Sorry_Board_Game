package Model.Cards;

import Model.Pawn.PlayerPawn;

/**
 * @version 1.0
 * @Author V1dalTheAbidal
 */

public class SorryCard implements Card {
    private int number;
    private int movementRange;
    private PlayerPawn enemyPawn;
    private PlayerPawn Player;

    /**
     * <b>Constructor:</b> Creates a new NumberCard
     * <b>Postcondition:</b> A new NumberCard has been created
     * @param number
     */
    public SorryCard(){
        this.number = -1;
    }

    /**
     * <b>Transformer:</b> sets the card's number
     * <b>Postcondition:</b> card's number has been set
     * @param int value
     */
    public void sorry(PlayerPawn enemyPawn, PlayerPawn player){
        this.enemyPawn = enemyPawn;

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
