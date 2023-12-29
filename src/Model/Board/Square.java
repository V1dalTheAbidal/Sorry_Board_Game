package Model.Board;

import Model.Player.Player;

/**
 * @version 1.0
 * @Author V1dalTheAbidal
 */
public class Square{
    private boolean isOccupied;
    private COLOR squareColor;
    private Player hasPermission;
    private Player onSquare;
    private int category;

    /**
     * <b>Constructor</b>: Constructs a new Square
     * <b>Postcondition:</b> A new Square has been created.
     * @param squareColor
     */
    public Square(COLOR squareColor) {
        this.squareColor = squareColor;
        this.isOccupied = false;
        this.hasPermission = null;
        this.onSquare = null;
    }

    /**
     * <b>Transformer:</b> Sets the square's color.
     * <b>Postcondition:</b> Square's color has been set.
     * @param squareColor
     */
    public void setSquareColor(COLOR squareColor) {
        this.squareColor = squareColor;
    }

    /**
     * <b>Transformer:</b> Sets if the square is occupied by another pawn.
     * <b>Postcondition:</b> Square's occupied status has been set.
     * @param isOccupied
     */
    public void setOccupied(boolean isOccupied) {
        this.isOccupied = isOccupied;
    }
    /**
     * <b>Transformer:</b> Sets the square's permission.
     * <b>Postcondition:</b> Square's permission has been set.
     * @param hasPermission
     */
    public void setHasPermission(Player hasPermission) {
        this.hasPermission = hasPermission;
    }
    /**
     * <b>Transformer:</b> Sets if a pawn is on a square or not.
     * <b>Postcondition:</b> Square's status has been set.
     * @param onSquare
     */
    public void setOnSquare(Player onSquare) {
        this.onSquare = onSquare;
    }
    /**
     * <b>Accessor:</b> returns the square's color.
     * <b>Postcondition:</b> the square's color has been returned
     * @return the square's color
     */
    public COLOR getSquareColor() {
        return squareColor;
    }
    /**
     * <b>Accessor:</b> returns the square's permission.
     * <b>Postcondition:</b> the square's permission has been returned
     * @return the square's permission
     */
    public Player getHasPermission() {
        return hasPermission;
    }
    /**
     * <b>Accessor:</b> returns if the square is occupied by another pawn.
     * <b>Postcondition:</b> the square's occupied status has been returned
     * @return the square's occupied status
     */
    public boolean isOccupied() {
        return isOccupied;
    }
    /**
     * <b>Accessor:</b> returns if a pawn is on a square or not.
     * <b>Postcondition:</b> the square's status has been returned
     * @return the square's status
     */
    public Player getOnSquare() {
        return onSquare;
    }


}
