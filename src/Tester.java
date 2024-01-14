import Model.Cards.Card;
import View.GUI;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.Assert.*;


public class Tester{
        private GUI gui;

        @Before
        public void setUp() {
            gui = new GUI();
        }

        @Test
        public void testInitializeAllCards() {
            gui.initializeAllCards();
            assertNotNull("Card 1 label should not be null", gui.card1);
            assertNotNull("Card 2 label should not be null", gui.card2);
            assertNotNull("Card 3 label should not be null", gui.card3);
            assertNotNull("Card 4 label should not be null", gui.card4);
            assertNotNull("Card 5 label should not be null", gui.card5);
            assertNotNull("Card 7 label should not be null", gui.card7);
            assertNotNull("Card 8 label should not be null", gui.card8);
            assertNotNull("Card 10 label should not be null", gui.card10);
            assertNotNull("Card 11 label should not be null", gui.card11);
            assertNotNull("Card 12 label should not be null", gui.card12);
            assertNotNull("Sorry Card label should not be null", gui.sorryCard);
            assertNotNull("Card 1 should have an icon", gui.card1.getIcon());
            assertNotNull("Card 2 should have an icon", gui.card2.getIcon());
        }

    @Test
    public void testInitializeBoard() {
        gui.initializeBoard();

        assertNotNull("Board should not be null after initialization", gui.Board);
        assertFalse("Board should have components after initialization", gui.Board.getComponents().length == 0);

    }

    @Test
    public void testFold() {
        gui.fold();

        assertFalse("CurrentCardDisplay should be invisible after fold", gui.CurrentCardDisplay.isVisible());


    }

    @Test
    public void testGetLabel() {
        JLabel testLabel = new JLabel();
        gui.player1Pawn1 = testLabel;

        assertEquals("getLabel should return the correct JLabel", testLabel, gui.getLabel(gui.player1Pawn1));
    }

    @Test
    public void testGetLabelPosition() {
        JLabel testLabel = new JLabel();
        testLabel.setLocation(100, 100);
        gui.player1Pawn1 = testLabel;

        assertEquals("getLabelPosition should return the correct position", new Point(100, 100), gui.getLabelPosition(gui.player1Pawn1));
    }

    @Test
    public void testGetSquarePositions() {
        gui.initializeBoard();
        ArrayList<Point> positions = gui.getSquarePositions();

        assertNotNull("squarePositions should not be null", positions);
        assertFalse("squarePositions should not be empty", positions.isEmpty());

    }

}
