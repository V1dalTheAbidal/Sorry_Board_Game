package View;

import Model.Board.Square;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 *  @version 1.0
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
    DragDrop(){

    }
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

            boardSquares.get(index).setOccupied(true);
            boardSquares.get(index).setOnSquare(gui.getPlayer());
            System.out.println("Index inside dragNdrop: " + index);
            gui.setActiveIndex1(index);
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

            boardSquares.get(index).setOccupied(true);
            boardSquares.get(index).setOnSquare(gui.getPlayer());
            gui.setActiveIndex2(index);
            gui.getPawn2().setHome(false);
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

        if (isWithinBounds(e,labelToDrag1)){
            activeLabel = labelToDrag1;
            offsetX = e.getX() - (labelToDrag1.getX());
            offsetY = e.getY() - (labelToDrag1.getY());

            isDragging = true;
        } else if (isWithinBounds(e,labelToDrag2)){
            activeLabel = labelToDrag2;

            offsetX = e.getX() - (labelToDrag2.getX());
            offsetY = e.getY() - (labelToDrag2.getY());

            isDragging = true;
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

    public int findSquareIndex(Point squarePosition){


        return 0;
    }


}


