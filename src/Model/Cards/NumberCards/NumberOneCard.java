package Model.Cards.NumberCards;

import Model.Cards.NumberCard;
/**
 * @version 1.0
 * @Author V1dalTheAbidal
 */
public class NumberOneCard extends NumberCard{
    private int number;
    /**
     * <b>Constructor</b>: Constructs a new NumberOneCard
     * <b>Postcondition:</b> A new NumberOneCard has been created.
     */
    public NumberOneCard(){
        super(1);
        this.number = 1;
    }

    /**
     * <b>Accessor:</b> Returns the card's range
     * <b>Postcondition:</b> The card's range has been returned
     * @return int 1
     */
    public int getRange(){
        return 1;
    }

}
