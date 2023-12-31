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
 * @version 1.0
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
            turn.setCurrentPlayer(players);
        }
        else{
            turn.setCurrentPlayer(players);
        }
    }

    /**
     * <b>transformer(mutative)</b>: sets the variable  to true
     * <b>Postcondition:</b>  sets the variable hasStarted to true
     *
     */

    public void setHasStarted() {
        this.hasStarted = true;
    }

    /**
     * <b>Accessor:</b> returns the variable hasStarted
     * <b>Postcondition:</b> the variable hasStarted has been returned
     * @return the variable hasStarted
     */

    public boolean hasNotStarted() {
        return !hasStarted;
    }

    /**
     * <b>Observer:</b> returns true if the game has finished
     * <b>Postcondition:</b> returns true if the game is finished otherwise false
     * @return true or false depending on whether the game has finished or not
     */

    public boolean gameHasFinished(){

        return false;
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
     * <b>Accessor:</b> sets the numbers of players playing
     * <b>Postcondition:</b> the players playing has been set
     *
     */
    public void setPlayersPlaying(int playersPlaying){
        this.playersPlaying = playersPlaying;
    }

    /**
     * <b>Accessor:</b> returns the number of players playing
     * <b>Postcondition:</b> the number of players playing has been returned
     * @return the number of players playing
     */

    public int getPlayersPlaying(){
        return this.playersPlaying;
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
     * <b>Transformer:</b> sets the Board Squares
     * <b>Postcondition:</b> the Board Squares has been set
     * @param boardSquares
     */
    public void setBoardSquares(ArrayList<Square> boardSquares){
        this.boardSquares = boardSquares;
    }



    /**
     * <b>Transformer:</b> Initialize the main board squares of the game(excluding the safe squares and the home squares and the start squares)
     * <b>Postcondition:</b> the Board Squares has been initialized
     *
     */

    public void initializeBoardSquares(){
        for (int i = 0; i < 64; i++){

            //adding white squares all around the board                                                                                                                                     starting red here
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
     * <b>Transformer:</b> set some things about the card Sorry
     * <b>Postcondition:</b> the cards sorry has been set
     *
     */
    public void setSorryCardPlayed(){

    }

    public void endTurn(){
        int i = turn.getCurrentPlayer();
        if (players.get(i).isFinished()){
            setTurn();//sets the next turn
        }
        else{
            //kane kati
        }

    }

    public void setPlayerFinished(boolean finished){
        int i = turn.getCurrentPlayer();
        players.get(i).setFinished(finished);
    }

    public void setPlayerHasPlayed(){
        int i = turn.getCurrentPlayer();
        players.get(i).setHasPlayed(true);
    }

    public void setPlayerHasNotPlayed(){
        int i = turn.getCurrentPlayer();
        players.get(i).setHasPlayed(false);
    }

    public boolean checkIfPlayerHasPlayed(){
        int i = turn.getCurrentPlayer();
        return players.get(i).isHasPlayed();
    }

    public boolean checkIfPlayerHasFinished(){
        int i = turn.getCurrentPlayer();
        return players.get(i).isFinished();
    }

    public void setPlayerHasNotFinished(){
        int i = turn.getCurrentPlayer();
        players.get(i).setFinished(false);
    }

    public void setPlayerHasNotFinished(int i){
        players.get(i).setFinished(false);
    }
    public void setPlayerHasFinished(int i){
        players.get(i).setFinished(true);
    }

    public void setPlayersTurn(int i) {
       turn.setCurrentPlayer(players);
    }

    public int getPlayerPlayingNow() {
       return turn.getCurrentPlayer();
    }
    public int getDeckSize(){
       return cardsLeftToPlay.size();
    }
    public boolean getPlayerHasFinished(){
        int i = turn.getCurrentPlayer();
        return players.get(i).isFinished();
    }

    public void createNewDeck(){
        allCards.initializeCards();
        cardsLeftToPlay = allCards.getCards();
    }

    public void setPawn(){
        pawn1Red.setHome(true);
        pawn2Red.setHome(true);
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

    public PlayerPawn getPawn1Red(){
        return pawn1Red;
    }

    public PlayerPawn getPawn2Red(){
        return pawn2Red;
    }

    public PlayerPawn getPawn1Yellow(){
        return pawn1Yellow;
    }

    public PlayerPawn getPawn2Yellow(){
        return pawn2Yellow;
    }

    public Model.Pawn.COLOR getPawn1RedColor(){
        return pawn1Red.getColor();
    }

    public Model.Pawn.COLOR getPawn2RedColor(){
        return pawn2Red.getColor();
    }

    public Model.Pawn.COLOR getPawn1YellowColor(){
        return pawn1Yellow.getColor();
    }

    public Model.Pawn.COLOR getPawn2YellowColor(){
        return pawn2Yellow.getColor();
    }

    public Model.Player.Player getP1() {
        return P1;
    }

    public Model.Player.Player getP2() {
        return P2;
    }
}
