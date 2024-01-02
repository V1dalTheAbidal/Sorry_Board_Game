package View;

import Model.Board.COLOR;
import Model.Board.Square;
import Model.Board.SquareSubClasses.SlideSquareSubClasses.EndSlideSquare;
import Model.Board.SquareSubClasses.SlideSquareSubClasses.StartSlideSquare;
import Model.Board.SquareSubClasses.StartSquare;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 *  @version 2.0
 *  @Author V1dalTheAbidal
 */

public class DragDrop extends MouseAdapter{
    private double labelX1, labelY1;
    private double labelX2, labelY2;
    private Point labelPosition1;
    private Point labelPosition2;
    private double offsetX, offsetY;
    private boolean isDragging = false;
    private final double squareSize = 50;
    private GUI gui;
    private JLabel activeLabel = null;


    JLabel labelToDrag1;
    JLabel labelToDrag2;

    /**
     * <b>Constructor</b>: Constructs a new DragDrop
     * <b>Postcondition:</b> A new DragDrop has been created.
     */

    DragDrop(GUI gui, JLabel label1, JLabel label2){
        this.gui= gui;
        labelToDrag1 = gui.getLabel(label1);
        labelToDrag2 = gui.getLabel(label2);
        labelPosition1 = gui.getLabelPosition(labelToDrag1);
        labelPosition2 = gui.getLabelPosition(labelToDrag2);
        labelX1 = labelPosition1.getX();
        labelY1 = labelPosition1.getY();
        labelX2 = labelPosition2.getX();
        labelY2 = labelPosition2.getY();
    }


    /**
     * <b>Transformer:</b> Snaps the label to the tile
     * <b>Postcondition:</b> The label has been snapped to the tile
     */
    private void snapToTile(){
        if (activeLabel == null) {// If no label is active, do nothing
            return;
        }

        ArrayList<Square> boardSquares = gui.getSquareOBJ();
        //ArrayList<Point> sqrPositions = gui.getSquarePositions();
        ArrayList<Point> sqrAllowedPositionsForP1 = gui.getBoardSquaresAllowedMovementForP1();
        ArrayList<Point> sqrAllowedPositionsForP2 = gui.getBoardSquaresAllowedMovementForP2();
        Point labelPosition = activeLabel.getLocation(); // Get the current position of the active label
        Point nearestSquareForP1 = findNearestAllowedSquare(labelPosition, sqrAllowedPositionsForP1);
        Point nearestSquareForP2 = findNearestAllowedSquare(labelPosition, sqrAllowedPositionsForP2);



        ArrayList<Point> allSquarePositions = gui.getSquarePositions();
        int size= allSquarePositions.size() -1;
        int index = 0;

        System.out.println("Is ArrayList for Pawn1 empty: " + sqrAllowedPositionsForP1.isEmpty());
        System.out.println("Is ArrayList for Pawn2 empty: " + sqrAllowedPositionsForP2.isEmpty());
        if (sqrAllowedPositionsForP1.isEmpty()){
            activeLabel = labelToDrag2;
        }

        if (sqrAllowedPositionsForP2.isEmpty()){
            activeLabel = labelToDrag1;
        }


        if (activeLabel == labelToDrag1){
            index =0;
            if (nearestSquareForP1 != null) {
                // Calculate the new position for the label to be centered on the nearest square
                int newX = (int) (nearestSquareForP1.x + (squareSize - activeLabel.getWidth()) / 2);
                int newY = (int) (nearestSquareForP1.y + (squareSize - activeLabel.getHeight()) / 2);

                // Set the new position of the active label
                activeLabel.setLocation(newX, newY);



            }

            while (size >= 0){
                if (allSquarePositions.get(size).equals(nearestSquareForP1)){
                    index = size;
                    break;
                }
                size--;
            }

            if (boardSquares.get(index) instanceof StartSlideSquare){
                if (boardSquares.get(index).getSquareColor() == COLOR.RED && gui.getPawn1().getColor() == Model.Pawn.COLOR.RED){


                    boardSquares.get(index).setOccupied(true);
                    boardSquares.get(index).setOnSquare(gui.getPlayer());
                    System.out.println("Index inside dragNdrop: " + index);
                    gui.setActiveIndex1(index);
                    gui.getPawn1().setStart(false);
                    gui.getPawn1().setHome(false);
                    return;


                }else if ( boardSquares.get(index).getSquareColor() == COLOR.YELLOW && gui.getPawn1().getColor() == Model.Pawn.COLOR.YELLOW){
                    boardSquares.get(index).setOccupied(true);
                    boardSquares.get(index).setOnSquare(gui.getPlayer());
                    gui.setActiveIndex1(index);


                    return;
                }

                int endSlideIndex = findNearestEndSlideSquareIndex(index);

                if (endSlideIndex != -1){
                    Point endSlidePositions = allSquarePositions.get(endSlideIndex);
                    boardSquares.get(endSlideIndex).setOccupied(true);
                    boardSquares.get(endSlideIndex).setOnSquare(gui.getPlayer());
                    gui.setActiveIndex1(endSlideIndex);
                    activeLabel.setLocation(endSlidePositions);
                }

            }

            boardSquares.get(index).setOccupied(true);
            boardSquares.get(index).setOnSquare(gui.getPlayer());
            gui.setActiveIndex1(index);
            System.out.println("Index inside dragNdrop for P1: " + index);
            gui.getPawn1().setStart(false);
            gui.getPawn1().setHome(false);
        }

        if (activeLabel == labelToDrag2){
            index=0;
            if (nearestSquareForP2 != null) {
                // Calculate the new position for the label to be centered on the nearest square
                int newX = (int) (nearestSquareForP2.x + (squareSize - activeLabel.getWidth()) / 2);
                int newY = (int) (nearestSquareForP2.y + (squareSize - activeLabel.getHeight()) / 2);

                // Set the new position of the active label
                activeLabel.setLocation(newX, newY);

            }

            while (size >= 0){
                if (allSquarePositions.get(size).equals(nearestSquareForP2)){
                    index = size;
                    break;
                }
                size--;
            }

            if (boardSquares.get(index) instanceof StartSlideSquare){
                if (boardSquares.get(index).getSquareColor() == COLOR.RED && gui.getPawn1().getColor() == Model.Pawn.COLOR.RED){


                    boardSquares.get(index).setOccupied(true);
                    boardSquares.get(index).setOnSquare(gui.getPlayer());
                    gui.setActiveIndex2(index);
                    gui.getPawn2().setHome(false);
                    gui.getPawn2().setStart(false);
                    return;


                }else if ( boardSquares.get(index).getSquareColor() == COLOR.YELLOW && gui.getPawn1().getColor() == Model.Pawn.COLOR.YELLOW){


                    boardSquares.get(index).setOccupied(true);
                    boardSquares.get(index).setOnSquare(gui.getPlayer());
                    gui.setActiveIndex2(index);
                    gui.getPawn2().setHome(false);
                    gui.getPawn2().setStart(false);
                    return;
                }

                int endSlideIndex = findNearestEndSlideSquareIndex(index);

                if (endSlideIndex != -1){
                    Point endSlidePositions = allSquarePositions.get(endSlideIndex);
                    boardSquares.get(endSlideIndex).setOccupied(true);
                    boardSquares.get(endSlideIndex).setOnSquare(gui.getPlayer());
                    gui.setActiveIndex1(endSlideIndex);
                    activeLabel.setLocation(endSlidePositions);
                }

            }

            boardSquares.get(index).setOccupied(true);
            boardSquares.get(index).setOnSquare(gui.getPlayer());
            gui.setActiveIndex2(index);
            System.out.println("Index inside dragNdrop for P2: " + index);
            gui.getPawn2().setHome(false);
            gui.getPawn2().setStart(false);
        }


        // Repaint the GUI
        gui.frame.revalidate();
        gui.frame.repaint();
    }


    private Point findNearestAllowedSquare(Point labelPosition, ArrayList<Point> allowedPositions) {
        double minDistance = Double.MAX_VALUE;
        Point nearestSquare = null;

        for (Point square : allowedPositions) {
            double distance = labelPosition.distance(square);
            if (distance < minDistance) {
                minDistance = distance;
                nearestSquare = square;
            }
        }

        return nearestSquare;
    }

    /**
     * <b>Observer:</b> Checks if the label is dragged
     * <b>Postcondition:</b> returns true if the label is dragged otherwise false
     */
    public void mousePressed(MouseEvent e){
        if (!gui.isCardInPlay()) {
            // If no card is in play, prevent pawn movement
            JOptionPane.showMessageDialog(gui.frame, "You must draw a card before moving a pawn!");
            return;
        }

        System.out.println("Label position: " + labelPosition1);
        System.out.println("Label position: " + labelPosition2);
        System.out.println("Mouse pressed at X: " + e.getX() + ", Y: " + e.getY());
        System.out.println("Label1 position X: " + labelX1);
        System.out.println("Label1 position Y: " + labelY1);
        System.out.println("Label2 position X: " + labelX2);
        System.out.println("Label2 position Y: " + labelY2);
        System.out.println("Square size: " + squareSize);

        ArrayList<Point> allowedPositionsForP1 = gui.getBoardSquaresAllowedMovementForP1();
        ArrayList<Point> allowedPositionsForP2 = gui.getBoardSquaresAllowedMovementForP2();

        // Determine which pawn the user is trying to move
        if (isWithinBounds(e, labelToDrag1)) {
            // Pawn 1 is being clicked
            if (!allowedPositionsForP1.isEmpty()) {
                // Pawn 1 can be moved
                activeLabel = labelToDrag1;
                offsetX = e.getX() - labelToDrag1.getX();
                offsetY = e.getY() - labelToDrag1.getY();
                isDragging = true;
            } else {
                JOptionPane.showMessageDialog(gui.frame, "Pawn 1 cannot be moved right now!");

            }
        } else if (isWithinBounds(e, labelToDrag2)) {
            // Pawn 2 is being clicked
            if (!allowedPositionsForP2.isEmpty()) {
                // Pawn 2 can be moved
                activeLabel = labelToDrag2;
                offsetX = e.getX() - labelToDrag2.getX();
                offsetY = e.getY() - labelToDrag2.getY();
                isDragging = true;
            } else {
                JOptionPane.showMessageDialog(gui.frame, "Pawn 2 cannot be moved right now!");
            }
        }else{
            JOptionPane.showMessageDialog(gui.frame, "You cannot move neither pawns till you draw 1 or 2!");
        }
    }

    /**
     * <b>Observer:</b> Checks if the label is released
     * <b>Postcondition:</b> returns true if the label is released otherwise false
     */
    public void mouseReleased(MouseEvent e){
        if(isDragging && activeLabel != null){
            snapToTile();
            isDragging = false;
            activeLabel = null;

            //TODO CHECK IF MOVE IS VALID
            //TODO CHECK IF CARD IS VALID
            if (gui.isCardInPlay() && gui.getCardInPlayNumber() == 2){
                gui.updateGameInfoForReDraw();

            }

            if (gui.isCardInPlay()){
                gui.updateGameInfo();
            }

        }
    }

    /**
     * <b>Observer:</b> Checks if the label is dragged
     * <b>Postcondition:</b> returns true if the label is dragged otherwise false
     */
    public void mouseDragged(MouseEvent e){
        if (isDragging) {
            // Translate mouse coordinates to the Board JPanel's coordinate system
            Point point = SwingUtilities.convertPoint(e.getComponent(), e.getPoint(), activeLabel.getParent());

            int newX =(int) (point.getX() -  offsetX);
            int newY = (int) (point.getY() -  offsetY);

            activeLabel.setLocation(newX, newY);

            gui.frame.revalidate();
            gui.frame.repaint();
        }
    }

    private boolean isWithinBounds(MouseEvent e, JLabel label) {
        Rectangle bounds = label.getBounds();

        Point mousePoint = SwingUtilities.convertPoint(e.getComponent(), e.getPoint(), label.getParent());

        return bounds.contains(mousePoint);
    }

    public int findNearestEndSlideSquareIndex(int startSlideIndex) {
        ArrayList<Square> boardSquares = gui.getSquareOBJ();

        // Loop from the startSlideIndex to find the nearest EndSlideSquare
        for (int i = startSlideIndex; i < boardSquares.size(); i--) {
            if (boardSquares.get(i) instanceof EndSlideSquare) {
                return i;
            }
        }

        return -1;  // If not found, return an invalid index
    }
    public void swapPawnPositions(){

    }




}


