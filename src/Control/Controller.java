package Control;

import Model.Board.Board;
import Model.Board.COLOR;
import Model.Board.Square;
import Model.Board.SquareSubClasses.HomeSquare;
import Model.Board.SquareSubClasses.SafetyZoneSquare;
import Model.Board.SquareSubClasses.SlideSquare;
import Model.Board.SquareSubClasses.SlideSquareSubClasses.EndSlideSquare;
import Model.Board.SquareSubClasses.SlideSquareSubClasses.InternalSlideSquare;
import Model.Board.SquareSubClasses.SlideSquareSubClasses.StartSlideSquare;
import Model.Board.SquareSubClasses.StartSquare;
import Model.Cards.Card;
import Model.Deck.Deck;
import Model.Pawn.PlayerPawn;
import Model.Player.Player;
import Model.Turn.Turn;
import View.GUI;

import java.util.ArrayList;
import java.util.IllegalFormatCodePointException;

/**
 * @version 2.0
 * @Author V1dalTheAbidal
 */

public class Controller {
    private int fold;
    private boolean hasStarted, emptyBoard;
    private Player P1 =new Player("Player 1", 0,Model.Player.COLOR.RED);
    private Player P2 =new Player("Player 2", 1,Model.Player.COLOR.YELLOW);
    private Player P3 = new Player("Player 3", 2,Model.Player.COLOR.GREEN);
    private Player P4 =new Player("Player 4", 3,Model.Player.COLOR.BLUE);
    private int playersPlaying;
    private ArrayList<Player> players = new ArrayList<Player>();
    private ArrayList<Square> boardSquares = new ArrayList<Square>();
    private ArrayList<Square> safeSquares = new ArrayList<Square>();
    private ArrayList<Card> cardsPlayed = new ArrayList<Card>();
    public ArrayList<Card> cardsLeftToPlay = new ArrayList<Card>();
    private Turn turn = new Turn();
    private Deck allCards = new Deck();
    private PlayerPawn pawn1Red = new PlayerPawn(0, Model.Pawn.COLOR.RED);
    private PlayerPawn pawn2Red = new PlayerPawn(0, Model.Pawn.COLOR.RED);
    private PlayerPawn pawn1Yellow = new PlayerPawn(0, Model.Pawn.COLOR.YELLOW);
    private PlayerPawn pawn2Yellow = new PlayerPawn(0, Model.Pawn.COLOR.YELLOW);

    /**
     * <b>Constructor:</b> Creates a new Controller
     * <b>Postcondition:</b> A new Controller has been created
     */
    public Controller(){
        players.add(P1);
        players.add(P2);
        allCards.initializeCards();

        cardsLeftToPlay = allCards.getCards();
        this.emptyBoard = true;
        this.hasStarted = false;
        this.fold = 0;
        initializeBoardSquares();
        setPawn();
    }

    /**
     * <b>Transformer:</b> Sets the player's turn.
     * <b>Postcondition:</b> Player's turn has been set.
     */
    public int playersTurn(){
        return turn.getCurrentPlayer();
    }

    /**
     * <b>Transformer:</b> sets the fold
     * <b>Postcondition:</b> fold has been set
     *
     */

    public void setFold(){
        //cardsLeftToPlay.

        if (this.fold == 0){
            this.fold++;

        }

    }


    /**
     * <b>Transformer:</b> sets the turn for the player playing
     * <b>Postcondition:</b> the turn for the player playing has been set
     *
     */
    public void setTurn(){
            turn.setLastPlayer(players);
            turn.setNextPlayerTurn(players);
            turn.setCurrentPlayer(players);
    }

    /**
     * <b>Accessor:</b> returns the Board Squares
     * <b>Postcondition:</b> the Board Squares has been returned
     * @return board squares
     */
    public ArrayList<Square> getBoardSquares(){
        return this.boardSquares;
    }


    /**
     * <b>Transformer:</b> Initialize the main board squares of the game(excluding the safe squares and the home squares and the start squares)
     * <b>Postcondition:</b> the Board Squares has been initialized
     *
     */
    public void initializeBoardSquares(){
        for (int i = 0; i < 64; i++){
            //adding white squares all around the board
            if(i < 2 || (i > 6 && i < 11) || (i == 15 )|| (i==16) || (i > 21 && i < 26) || (i == 31) || (i == 32) || (i > 37 && i < 42) || (i == 46) || (i == 47) || (i > 52 && i < 57)){
                boardSquares.add(new Square(COLOR.WHITE));

            }

            //adding green slide squares vertically on the left side
            if(i>1 && i<7 || i>10 && i<15){
                if (i == 2 || i == 11){
                    boardSquares.add(new EndSlideSquare(COLOR.GREEN));
                } else if (i == 6 || i == 14) {
                    boardSquares.add(new StartSlideSquare(COLOR.GREEN));
                }else{
                    boardSquares.add(new InternalSlideSquare(COLOR.GREEN));
                }
            }

            //adding yellow slide squares horizontally on the bottom side
            if(i>16 && i<22 || i>26 && i<31){
                if (i == 17 || i == 27){
                    boardSquares.add(new EndSlideSquare(COLOR.YELLOW));
                } else if (i == 21 || i == 30) {
                    boardSquares.add(new StartSlideSquare(COLOR.YELLOW));
                }else{
                  boardSquares.add(new InternalSlideSquare(COLOR.YELLOW));
                }
            }

            //adding blue squares vertically on the right side
            if(i>32 && i<38 || i>40 && i<45){
                if (i == 33 || i == 41){
                    boardSquares.add(new EndSlideSquare(COLOR.BLUE));
                } else if (i == 37 || i == 44) {
                    boardSquares.add(new StartSlideSquare(COLOR.BLUE));
                }else{
                    boardSquares.add(new InternalSlideSquare(COLOR.BLUE));
                }
            }

            //adding red slide squares horizontally on the top side
            if(i>46 && i<52 || i>57 && i<62){
                if (i == 47 || i == 58){
                    boardSquares.add(new EndSlideSquare(COLOR.RED));
                } else if (i == 51 || i == 61) {
                    boardSquares.add(new StartSlideSquare(COLOR.RED));
                }else{
                    boardSquares.add(new InternalSlideSquare(COLOR.RED));
                }
            }
        }

        for (int i=0; i<11; i++){
            if(i<5){
                boardSquares.add(new SafetyZoneSquare(COLOR.RED));
            }else if(i>4 && i<10){
                boardSquares.add(new SafetyZoneSquare(COLOR.YELLOW));
            }else if(i==10){
                boardSquares.add(new HomeSquare(COLOR.YELLOW));
                boardSquares.add(new StartSquare(COLOR.YELLOW));
                boardSquares.add(new StartSquare(COLOR.RED));
                boardSquares.add(new HomeSquare(COLOR.RED));
            }
        }
    }

    public void occupideInit(){
        int size = boardSquares.size() -1;
        while(size <= 0){
            boardSquares.get(size).setOccupied(false);
            size--;
        }
    }


    /**
     * <b>Transformer:</b> when a card has been played it is added to the cards played and removed from the cards left to play
     * <b>Postcondition:</b> the card that has been played has been removed from the remaining cards to play and added to the cards played
     *
     */
    public void addCardToPlayed(){
        cardsPlayed.add(cardsLeftToPlay.get(0));
        cardsLeftToPlay.remove(0);
    }

    /**
     * <b>Accessor:</b> draws a card from the cards left to play and returns it
     * <b>Postcondition:</b> the cards has been drawn
     * @return card that's currently active for play
     */
    public Card drawCard(){
        return cardsLeftToPlay.get(0);
    }

    /**
     * <b>Transformer:</b> sets the players statue to finished
     * <b>Postcondition:</b> the players statue has been set to finished
     * @param finished
     *
     */
    public void setPlayerFinished(boolean finished){
        int i = turn.getCurrentPlayer();
        players.get(i).setFinished(finished);
    }


    /**
     * <b>Accessor:</b> returns the player playing now
     * <b>Postcondition:</b> the players playing now has been returned
     */
    public int getPlayerPlayingNow() {
       return turn.getCurrentPlayer();
    }

    /**
     * <b>Accessor:</b> returns the deck size
     * <b>Postcondition:</b> the deck size has been returned
     */
    public int getDeckSize(){
       return cardsLeftToPlay.size();
    }

    /**
     * <b>Transformer:</b> Creates a new deck
     * <b>Postcondition:</b> A new deck has been created
     */
    public void createNewDeck(){
        allCards.initializeCards();
        cardsLeftToPlay = allCards.getCards();
    }

    /**
     * <b>Transformer:</b> Set some pawn initial values
     * <b>Postcondition:</b> Pawns has been set
     */
    public void setPawn(){
        //setting the colors
        pawn1Red.setColor(Model.Pawn.COLOR.RED);
        pawn2Red.setColor(Model.Pawn.COLOR.RED);
        pawn1Yellow.setColor(Model.Pawn.COLOR.YELLOW);
        pawn2Yellow.setColor(Model.Pawn.COLOR.YELLOW);
        pawn1Red.setHome(false);
        pawn2Red.setHome(false);
        pawn1Yellow.setHome(false);
        pawn2Yellow.setHome(false);
        pawn1Red.setSafe(true);
        pawn2Red.setSafe(true);
        pawn1Yellow.setSafe(true);
        pawn2Yellow.setSafe(true);
        pawn1Red.setStart(true);
        pawn2Red.setStart(true);
        pawn1Yellow.setStart(true);
        pawn2Yellow.setStart(true);
    }

    /**
     * <b>Accessor:</b> Accesses the pawn 1 with red color
     * <b>Postcondition:</b> the red pawn 1 has been returned
     */
    public PlayerPawn getPawn1Red(){
        return pawn1Red;
    }

    /**
     * <b>Accessor:</b> Accesses the pawn 2 with red color
     * <b>Postcondition:</b> the red pawn 2 has been returned
     */
    public PlayerPawn getPawn2Red(){
        return pawn2Red;
    }

    /**
     * <b>Accessor:</b> Accesses the pawn 1 with yellow color
     * <b>Postcondition:</b> the yellow pawn 1 has been returned
     */
    public PlayerPawn getPawn1Yellow(){
        return pawn1Yellow;
    }

    /**
     * <b>Accessor:</b> Accesses the pawn 2 with yellow color
     * <b>Postcondition:</b> the yellow pawn 2 has been returned
     */
    public PlayerPawn getPawn2Yellow(){
        return pawn2Yellow;
    }

    /**
     * <b>Accessor:</b> Accesses the player 1(red)
     * <b>Postcondition:</b> returned the player 1
     */
    public Model.Player.Player getP1() {
        return P1;
    }

    /**
     * <b>Accessor:</b> Accesses the player 2(yellow)
     * <b>Postcondition:</b> returned the player 2
     */
    public Model.Player.Player getP2() {
        return P2;
    }
}
