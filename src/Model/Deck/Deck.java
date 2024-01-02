package Model.Deck;

import Model.Cards.Card;
import Control.Controller;
import Model.Cards.NumberCard;
import Model.Cards.NumberCards.*;
import Model.Cards.SorryCard;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * @version 1.0
 * @Author V1dalTheAbidal
 */
public class Deck {
    private ArrayList<Card> deck;

    /**
     * <b>Constructor</b>: Constructs a new Deck
     * <b>Postcondition:</b> A new Deck has been created.
     */
    public Deck(){
        //initializing the deck
        deck = new ArrayList<Card>();

    }

    /**
     * <b>Transformer:</b> Initializes the deck with the cards.
     * <b>Postcondition:</b> The deck has been initialized.
     */


    public void initializeCards(){
        for (int i = 0; i < 4; i++){
            deck.add(new NumberOneCard());
            deck.add(new NumberTwoCard());
            deck.add(new SimpleNumberCard(3));
            deck.add(new NumberFourCard());
            deck.add(new SimpleNumberCard(5));
            deck.add(new NumberSevenCard());
            deck.add(new SimpleNumberCard(8));
            deck.add(new NumberTenCard());
            deck.add(new NumberElevenCard());
            deck.add(new SimpleNumberCard(12));
            deck.add(new SorryCard());
        }


        Collections.shuffle(deck);
        System.out.println("Deck size: " + deck.size());
    }








/*
    public void initializeCards(){
        for (int i = 0; i < 10; i++){
            deck.add(new NumberOneCard());

        }


        Collections.shuffle(deck);
        System.out.println("Deck size: " + deck.size());
    }


 */







    /**
     * <b>Observer:</b> Sees if the deck is empty.
     * <b>Postcondition:</b> True is returned if the deck is empty, false otherwise.
     */
    public boolean isEmpty(){
        return deck.isEmpty();
    }

    /**
     * <b>Transformer:</b> Adds a card to the deck.
     * <b>Postcondition:</b> A card has been added to the deck
     */
    public void addCard(Card toAdd){
        this.deck.add(toAdd); //add card to deck
    }

    /**
     * <b>Transformer:</b> Removes a card from the deck.
     * <b>Postcondition:</b> A card has been removed from the deck
     * @param toRemove
     */
    public void removeCard(Card toRemove){
        this.deck.remove(toRemove);
    }

    /**
     * <b>Accessor:</b> Returns a specific card number from the deck.
     * <b>Postcondition:</b> The card with number num has been returned.
     * @param num
     * @return size of the deck
     */
    public int getNumber(int num){
        return deck.get(num).getNumber();
    }
    /**
     * <b>Accessor:</b> Returns a specific card form the dekc.
     * <b>Postcondition:</b> The card has been returned.
     * @return card
     */
    public Card getCard(int i){
        return deck.get(i);
    }
    /**
     * <b>Accessor:</b> Returns the size of the deck.
     * <b>Postcondition:</b> The size of the deck has been returned.
     * @return size of the deck
     */
    public int getDeckSize(){
        return deck.size();
    }

    /**
     * <b>Transformer:</b> Clears the deck.
     * <b>Postcondition:</b> The deck has been cleared.
     */
    public void clearAll(){
        this.deck.clear();
    }

    /**
     * <b>Accessor:</b> Returns the deck.
     * <b>Postcondition:</b> The deck has been returned.
     * @return deck
     */
    public ArrayList<Card> getCards(){
        return this.deck;
    }






}
