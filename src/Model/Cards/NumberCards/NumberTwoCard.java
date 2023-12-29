package Model.Cards.NumberCards;

import Model.Cards.NumberCard;
/**
 * @version 1.0
 * @Author V1dalTheAbidal
 */
public class NumberTwoCard extends NumberCard{
    private int number;
    /**
     * <b>Constructor</b>: Constructs a new NumberTwoCard
     * <b>Postcondition:</b> A new NumberTwoCard has been created.
     */
    public NumberTwoCard(){
        super(2);
        this.number = 2;
    }

      /**
         * <b>Accessor:</b> Returns the card's range
         * <b>Postcondition:</b> The card's range has been returned
         * @return int 1
         */
    public int getRange(){
        return 2;
    }


}
