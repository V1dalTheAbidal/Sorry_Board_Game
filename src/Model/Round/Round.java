package Model.Round;

import Model.Cards.Card;
import Model.Deck.Deck;
/**
 * @version 1.0
 * @Author V1dalTheAbidal
 */
public class Round {

    private boolean started;

    /**
     * <b>Constructor</b>: Constructs a new Round
     * <b>Postcondition:</b> A new Round has been created.
     */
    public Round(){

    }
    /**
     * <b>Transformer:</b> Starts the round
     * <b>Postcondition:</b> The round has started
     *
     */
    public void roundStarted(){
        this.started = true;
    }

    /**
     * <b>Observer:</b> Ckech if the round has started
     * <b>Postcondition:</b> returns true if the round has started otherwise false
     *
     */
    public boolean isRoundStarted(){
        return started;
    }


}
