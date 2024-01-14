package Model.Pawn;
/**
 * @version 1.0
 * @Author V1dalTheAbidal
 */
public class PlayerPawn {
    private int StartingPosition;
    private int CurrentPosition;
    private COLOR Color;
    private boolean isSafe;
    private boolean isHome;
    private boolean isStart;
    private boolean isOut;
    private boolean isOnBoard;

    /**
     * <b>Constructor</b>: Constructs a new PlayerPawn
     * <b>Postcondition:</b> A new PlayerPawn has been created.
     */
    public PlayerPawn(int StartingPosition, COLOR Color){
        this.StartingPosition = StartingPosition;
        this.Color = Color;

    }

    /**
     * <b>Transformer:</b> Sets the current position of the pawn
     * <b>Postcondition:</b> The current position of the pawn has been set.
     * @param CurrentPosition
     */
    public void move(int CurrentPosition){
        this.CurrentPosition = CurrentPosition;
    }

    /**
     * <b>Transformer:</b> Sets the Color of the pawn
     * <b>Postcondition:</b> The Color of the pawn has been set.
     * @param Color
     */
    public void setColor(COLOR Color){
        this.Color = Color;
    }

    /**
     * <b>Transformer:</b> Sets the safe status of the pawn
     * <b>Postcondition:</b> The safe status of the pawn has been set.
     * @param isSafe
     */
    public void setSafe(boolean isSafe){
        this.isSafe = isSafe;
    }
    /**
     * <b>Transformer:</b> Sets the home status of the pawn
     * <b>Postcondition:</b> The home status of the pawn has been set.
     * @param isHome
     */
    public void setHome(boolean isHome){
        this.isHome = isHome;
    }
    /**
     * <b>Transformer:</b> Sets the start status of the pawn
     * <b>Postcondition:</b> The start status of the pawn has been set.
     * @param isStart
     */
    public void setStart(boolean isStart){
        this.isStart = isStart;
    }
    /**
     * <b>Transformer:</b> Sets the out status of the pawn
     * <b>Postcondition:</b> The out status of the pawn has been set.
     * @param isOut
     */
    public void setOut(boolean isOut){
        this.isOut = isOut;
    }
    /**
     * <b>Transformer:</b> Sets the on board status of the pawn(If a pawn is on board or not)
     * <b>Postcondition:</b> The on board status of the pawn has been set.
     * @param isOnBoard
     */
    public void setOnBoard(boolean isOnBoard){
        this.isOnBoard = isOnBoard;
    }
    /**
     * <b>Transformer:</b> set the starting position of the pawn
     * <b>Postcondition:</b> the starting position of the pawn has been set
     * @param StartingPosition
     */
   public void setStartingPosition(int StartingPosition){
       this.StartingPosition = StartingPosition;
   }

    /**
     * <b>Accessor:</b> returns the starting position of the pawn
     * <b>Postcondition:</b> the starting position of the pawn has been returned
     * @return the starting position of the pawn
     */
    public int getStartingPosition(){
         return StartingPosition;
    }
    /**
     * <b>Accessor:</b> returns the current position of the pawn
     * <b>Postcondition:</b> the current position of the pawn has been returned
     * @return the current position of the pawn
     */
    public int getCurrentPosition(){
        return CurrentPosition;
    }
    /**
     * <b>Accessor:</b> returns the color of the pawn
     * <b>Postcondition:</b> the color of the pawn has been returned
     * @return the color of the pawn
     */
    public COLOR getColor(){
        return Color;
    }
    /**
     * <b>Accessor:</b> returns the safe status of the pawn
     * <b>Postcondition:</b> the safe status of the pawn has been returned
     * @return the safe status of the pawn
     */
    public boolean getSafe(){
        return isSafe;
    }

    /**
     * <b>Accessor:</b> returns the home status of the pawn
     * <b>Postcondition:</b> the home status of the pawn has been returned
     * @return the home status of the pawn
     */
    public boolean getHome(){
        return isHome;
    }
    /**
     * <b>Accessor:</b> returns the start status of the pawn
     * <b>Postcondition:</b> the start status of the pawn has been returned
     * @return the start status of the pawn
     */
    public boolean getStart(){
        return isStart;
    }
    /**
     * <b>Accessor:</b> returns the out status of the pawn
     * <b>Postcondition:</b> the out status of the pawn has been returned
     * @return the out status of the pawn
     */
    public boolean getOut(){
        return isOut;
    }
    /**
     * <b>Accessor:</b> returns the on board status of the pawn
     * <b>Postcondition:</b> the on board status of the pawn has been returned
     * @return the on board status of the pawn
     */
    public boolean getOnBoard(){
        return isOnBoard;
    }





}
