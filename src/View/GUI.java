package View;

import Control.Controller;
import Model.Board.COLOR;
import Model.Board.Square;
import Model.Board.SquareSubClasses.HomeSquare;
import Model.Board.SquareSubClasses.SafetyZoneSquare;
import Model.Board.SquareSubClasses.SlideSquareSubClasses.EndSlideSquare;
import Model.Board.SquareSubClasses.SlideSquareSubClasses.InternalSlideSquare;
import Model.Board.SquareSubClasses.SlideSquareSubClasses.StartSlideSquare;
import Model.Board.SquareSubClasses.StartSquare;
import Model.Cards.Card;
import Model.Pawn.PlayerPawn;
import Model.Player.Player;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 *  @version 1.0
 *  @Author V1dalTheAbidal
 */

public class GUI extends JFrame{
    private Controller controller;
    private final JLabel backroundImage;
    private final JLabel ReceiveCard;
    private final JLabel CurrentCard;
    private final JLabel infoBoxTXT;
    private final JLabel infoBoxTurn;
    private final JLabel infoBoxCardsLeft;
    private final JLabel cyanMID;
    private final JLabel sorryImage;
    private JLabel card1;
    private JLabel card2;
    private JLabel card3;
    private JLabel card4;
    private JLabel card5;
    private JLabel card7;
    private JLabel card8;
    private JLabel card10;
    private JLabel card11;
    private JLabel card12;
    private JLabel sorryCard;
    JLabel player1Pawn1;
    JLabel player1Pawn2;
    JLabel player2Pawn1;
    JLabel player2Pawn2;
    private JLabel CurrentCardDisplay; //this is the card that is currently being displayed
    private final JButton FoldButton; //this is the button that folds the card
    private final JButton ReceiveCardButton; //this is the button that draws a card
    JFrame frame;
    JPanel Board;
    private final JPanel rightMenu;
    private final JPanel infoBox;
    private boolean isFoldButtonPressed;
    private JLabel pawn1ForDragNdrop;
    private JLabel pawn2ForDragNdrop;
    private JLabel enemyPawn1ForDragNdrop;
    private JLabel enemyPawn2ForDragNdrop;
    private boolean isCardInPlay = false;
    private Card numberCardInPlay;
    private PlayerPawn pawn1InPlay;
    private PlayerPawn pawn2InPlay;
    private int P11index;
    private int P12index;
    private int P21index;
    private int P22index;
    private int activeIndex1;
    private int activeIndex2;
    private int enemyIndex1;
    private int enemyIndex2;
    private PlayerPawn enemyPawn1;
    private PlayerPawn enemyPawn2;
    DragDrop dragDrop;


    /**
     * <b>Constructor:</b> Creates a new GUI
     * <b>Postcondition:</b> A new GUI has been created
     */
    public GUI(){
        //some essential java swing components initializations
        frame = new JFrame("Sorry!");
        Board = new JPanel();
        rightMenu = new JPanel();
        infoBox = new JPanel();
        ReceiveCard = new JLabel("Receive Card");
        CurrentCard = new JLabel("Current Card");
        infoBoxTXT = new JLabel("Info Box");
        infoBoxTurn = new JLabel("Turn:");
        infoBoxCardsLeft = new JLabel("Cards Left:");
        FoldButton = new JButton("Fold Button");
        ReceiveCardButton = new JButton();
        CurrentCardDisplay = new JLabel();



        //player pawns initializations
        initializePawnStartingPositions();
        //boared squares initializations
        initializeBoard();
        //initializing All card skins
        initializeAllCards();
        //player playing info
        playerPlayingInfo();
        //cards left info
        cardsLeftInfo();

        pawn1InPlay = controller.getPawn1Red();
        pawn2InPlay = controller.getPawn2Red();
        enemyPawn1 = controller.getPawn1Yellow();
        enemyPawn2 = controller.getPawn2Yellow();

        //set middle cyan square
        cyanMID = new JLabel();
        cyanMID.setBackground(Color.cyan);
        cyanMID.setOpaque(true);
        cyanMID.setSize(709,689);
        cyanMID.setLocation(50,50);

        //sorry image
        sorryImage = new JLabel(new ImageIcon("src/Utlity/images/sorryImage.png"));
        sorryImage.setSize(408,122);
        sorryImage.setLocation(204,332);

        pawn1ForDragNdrop = player1Pawn1;
        pawn2ForDragNdrop = player1Pawn2;


        dragNdropFunc();


        //Action listeners for drawCard
        ReceiveCardButton.addActionListener(new CardListener());
        FoldButton.addActionListener(new FoldListener());
        //set transparent background

        rightMenu.setBackground(new Color(255, 255, 255, 0));

        //set black outline
        Border blackline = BorderFactory.createLineBorder(Color.black,3);

        //set background image
        backroundImage = new JLabel();
        ImageIcon background = new ImageIcon("src/Utlity/images/background.png");
        Image scalingBackground = background.getImage().getScaledInstance(1107,857,Image.SCALE_SMOOTH);
        ImageIcon scaledBackground = new ImageIcon(scalingBackground);
        backroundImage.setIcon(scaledBackground);

        //Receive Card Button photo set
        ImageIcon backOfCard = new ImageIcon("src/Utlity/images/cards/backCard.png");
        Image scaling = backOfCard.getImage().getScaledInstance(110,164,Image.SCALE_SMOOTH);
        ImageIcon scaledBackOfCard = new ImageIcon(scaling);
        ReceiveCardButton.setIcon(scaledBackOfCard);
        ReceiveCardButton.setBackground(new Color(255, 255, 255, 0));

        //create font
        Font arialBold = new Font("Arial", Font.BOLD,15);

        //board components
        Board.setLayout(null);
        Board.setSize(809,787);
        Board.add(sorryImage);
        Board.add(cyanMID);

        //right menu components
        rightMenu.setLayout(null);
        rightMenu.setSize(298,787);
        rightMenu.setLocation(809,0);

        //mid is 149

        //right menu components
        CurrentCard.setFont(arialBold);
        CurrentCard.setSize(100,22);
        CurrentCard.setLocation(150,376);

        ReceiveCard.setFont(arialBold);
        ReceiveCard.setSize(100,22);
        ReceiveCard.setLocation(35,376);

        FoldButton.setSize(215,52);
        FoldButton.setLocation(35, 406);
        FoldButton.setBackground(Color.red);

        //right menu button initializations for Receive Card
        ReceiveCardButton.setSize(110,164);
        ReceiveCardButton.setLocation(25, 200);

        //infobox inside right menu initializations
        infoBox.setSize(215,125);
        infoBox.setLocation(35, 476);
        infoBox.setBackground(Color.WHITE);
        infoBox.setLayout(null);

        //infobox info initializations
        infoBoxTXT.setFont(arialBold);
        infoBoxTXT.setSize(190,22);
        infoBoxTXT.setLocation(5,0);

        infoBoxTurn.setFont(arialBold);
        infoBoxTurn.setSize(190,22);
        infoBoxTurn.setLocation(5,42);

        infoBoxCardsLeft.setFont(arialBold);
        infoBoxCardsLeft.setSize(190,22);
        infoBoxCardsLeft.setLocation(5,64);

        //info box fields
        infoBox.add(infoBoxTXT);
        infoBox.add(infoBoxTurn);
        infoBox.add(infoBoxCardsLeft);
        infoBox.setBorder(blackline);

        //right menu add components
        rightMenu.add(ReceiveCard);
        rightMenu.add(CurrentCard);
        rightMenu.add(FoldButton);
        rightMenu.add(ReceiveCardButton);
        rightMenu.add(infoBox);


        //frame window add components
        frame.add(rightMenu);
        frame.add(Board);
        frame.add(backroundImage);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1107,857); //39pixels frame top
        frame.setVisible(true);
    }

    /**
     * <b>Transformer:</b> Initializes all the card graphics.
     * <b>Postcondition:</b> All the card graphics have been initialized.
     */
    void initializeAllCards(){
        card1 = new JLabel();
        card2 = new JLabel();
        card3 = new JLabel();
        card4 = new JLabel();
        card5 = new JLabel();
        card7 = new JLabel();
        card8 = new JLabel();
        card10 = new JLabel();
        card11 = new JLabel();
        card12 = new JLabel();
        sorryCard = new JLabel();

        ImageIcon card1Icon = new ImageIcon("src/Utlity/images/cards/card1.png");
        ImageIcon card2Icon = new ImageIcon("src/Utlity/images/cards/card2.png");
        ImageIcon card3Icon = new ImageIcon("src/Utlity/images/cards/card3.png");
        ImageIcon card4Icon = new ImageIcon("src/Utlity/images/cards/card4.png");
        ImageIcon card5Icon = new ImageIcon("src/Utlity/images/cards/card5.png");
        ImageIcon card7Icon = new ImageIcon("src/Utlity/images/cards/card7.png");
        ImageIcon card8Icon = new ImageIcon("src/Utlity/images/cards/card8.png");
        ImageIcon card10Icon = new ImageIcon("src/Utlity/images/cards/card10.png");
        ImageIcon card11Icon = new ImageIcon("src/Utlity/images/cards/card11.png");
        ImageIcon card12Icon = new ImageIcon("src/Utlity/images/cards/card12.png");
        ImageIcon sorryCardIcon = new ImageIcon("src/Utlity/images/cards/cardSorry.png");

        Image scalingCard1 = card1Icon.getImage().getScaledInstance(110,164,Image.SCALE_SMOOTH);
        Image scalingCard2 = card2Icon.getImage().getScaledInstance(110,164,Image.SCALE_SMOOTH);
        Image scalingCard3 = card3Icon.getImage().getScaledInstance(110,164,Image.SCALE_SMOOTH);
        Image scalingCard4 = card4Icon.getImage().getScaledInstance(110,164,Image.SCALE_SMOOTH);
        Image scalingCard5 = card5Icon.getImage().getScaledInstance(110,164,Image.SCALE_SMOOTH);
        Image scalingCard7 = card7Icon.getImage().getScaledInstance(110,164,Image.SCALE_SMOOTH);
        Image scalingCard8 = card8Icon.getImage().getScaledInstance(110,164,Image.SCALE_SMOOTH);
        Image scalingCard10 = card10Icon.getImage().getScaledInstance(110,164,Image.SCALE_SMOOTH);
        Image scalingCard11 = card11Icon.getImage().getScaledInstance(110,164,Image.SCALE_SMOOTH);
        Image scalingCard12 = card12Icon.getImage().getScaledInstance(110,164,Image.SCALE_SMOOTH);
        Image scalingSorryCard = sorryCardIcon.getImage().getScaledInstance(110,164,Image.SCALE_SMOOTH);

        ImageIcon scaledCard1 = new ImageIcon(scalingCard1);
        ImageIcon scaledCard2 = new ImageIcon(scalingCard2);
        ImageIcon scaledCard3 = new ImageIcon(scalingCard3);
        ImageIcon scaledCard4 = new ImageIcon(scalingCard4);
        ImageIcon scaledCard5 = new ImageIcon(scalingCard5);
        ImageIcon scaledCard7 = new ImageIcon(scalingCard7);
        ImageIcon scaledCard8 = new ImageIcon(scalingCard8);
        ImageIcon scaledCard10 = new ImageIcon(scalingCard10);
        ImageIcon scaledCard11 = new ImageIcon(scalingCard11);
        ImageIcon scaledCard12 = new ImageIcon(scalingCard12);
        ImageIcon scaledSorryCard = new ImageIcon(scalingSorryCard);

        card1.setIcon(scaledCard1);
        card2.setIcon(scaledCard2);
        card3.setIcon(scaledCard3);
        card4.setIcon(scaledCard4);
        card5.setIcon(scaledCard5);
        card7.setIcon(scaledCard7);
        card8.setIcon(scaledCard8);
        card10.setIcon(scaledCard10);
        card11.setIcon(scaledCard11);
        card12.setIcon(scaledCard12);
        sorryCard.setIcon(scaledSorryCard);

        card1.setSize(110,164);
        card2.setSize(110,164);
        card3.setSize(110,164);
        card4.setSize(110,164);
        card5.setSize(110,164);
        card7.setSize(110,164);
        card8.setSize(110,164);
        card10.setSize(110,164);
        card11.setSize(110,164);
        card12.setSize(110,164);
        sorryCard.setSize(110,164);

    }

    /**
     * <b>Transformer:</b> Displays the card drawn.
     * <b>Postcondition:</b> The card drawn has been displayed.
     * @param cardDrawn The card drawn.
     */
    void displayCard(Card cardDrawn){
        numberCardInPlay = cardDrawn;
        rightMenu.remove(CurrentCardDisplay);
        frame.revalidate();
        frame.repaint();
        if (cardDrawn.getNumber() == 1){
            System.out.println("Card 1");
            CurrentCardDisplay = card1;
        }else if (cardDrawn.getNumber() == 2){
            System.out.println("Card 2");
            CurrentCardDisplay = card2;
        }else if (cardDrawn.getNumber() == 3){
            System.out.println("Card 3");
            CurrentCardDisplay = card3;
        }else if (cardDrawn.getNumber() == 4){
            System.out.println("Card 4");
            CurrentCardDisplay = card4;
        }else if (cardDrawn.getNumber() == 5){
            System.out.println("Card 5");
            CurrentCardDisplay = card5;
        }else if (cardDrawn.getNumber() == 7){
            System.out.println("Card 7");
            CurrentCardDisplay = card7;
        }else if (cardDrawn.getNumber() == 8){
            System.out.println("Card 8");
            CurrentCardDisplay = card8;
        }else if (cardDrawn.getNumber() == 10){
            System.out.println("Card 10");
            CurrentCardDisplay = card10;
        }else if (cardDrawn.getNumber() == 11){
            System.out.println("Card 11");
            CurrentCardDisplay = card11;
        }else if (cardDrawn.getNumber() == 12){
            System.out.println("Card 12");
            CurrentCardDisplay = card12;
        }else if (cardDrawn.getNumber() == -1){
            System.out.println("Card Sorry");
            CurrentCardDisplay = sorryCard;
        }

        CurrentCardDisplay.setSize(110,164);
        CurrentCardDisplay.setLocation(150,200);

        rightMenu.add(CurrentCardDisplay);
        CurrentCardDisplay.setVisible(true);
        frame.revalidate();
        frame.repaint();
        System.out.println("Card Added");
    }


    /**
     * <b>Observer:</b> Checks if the Receive Card button is pressed.
     * <b>Postcondition:</b> Does some actions when the Receive Card button is pressed.
     */

    class CardListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!isCardInPlay){
                if (controller.cardsLeftToPlay.isEmpty()){
                    controller.createNewDeck();
                }

                if (e.getSource() == ReceiveCardButton) {
                    displayCard(controller.drawCard());
                    controller.addCardToPlayed();
                    cardsLeftInfo();
                    isCardInPlay = true;
                    movement();
                }
            }
        }
    }

    /**
     * <b>Transformer:</b> Folds the card.
     * <b>Postcondition:</b> The card has been folded.
     */
    public void fold(){
        controller.setFold();
        CurrentCardDisplay.setVisible(false);
        //rightMenu.remove(CurrentCardDisplay);
        frame.revalidate();
        frame.repaint();
    }

    /**
     * <b>Observer:</b> Checks if the Fold button is pressed.
     * <b>Postcondition:</b> Does some actions when the Fold button is pressed.
     */
    private class FoldListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
           if (isCardInPlay){
               if (e.getSource() == FoldButton){
                   fold();
                   isFoldButtonPressed = true;
                   updateGameInfo();
                   isCardInPlay = false;
               }
           }
        }
    }

    /**
     * <b>Accessor:</b> Accessing the label
     * <b>Postcondition:</b> The label has been returned.
     * @param label
     * @return label
     */
    public JLabel getLabel(JLabel label){
        return label;
    }


    /**
     * <b>Accessor:</b> Accessing the label position
     * <b>Postcondition:</b> The label position has been returned.
     * @param label
     * @return label.getLocation()
     */
    public Point getLabelPosition(JLabel label){
        return label.getLocation();
    }

    /**
     * <b>Transformer:</b> Initializes the board.
     * <b>Postcondition:</b> The board has been initialized.
     */
    private ArrayList<Point> squarePositions = new ArrayList<Point>();
    public void initializeBoard(){
        controller = new Controller();
        ArrayList<Square> boardSquares = controller.getBoardSquares();
        int x = 0;
        int y = 2;
        int count1 = 0;
        int count2 = 0;

        for(int i =0; i<boardSquares.size(); i++){
            if(boardSquares.get(i).getSquareColor() == COLOR.RED){
                if(boardSquares.get(i) instanceof SafetyZoneSquare){
                    if (count1 == 0){
                        y = 46;
                    }
                    count1++;
                    JLabel redSafetyZone = new JLabel(new ImageIcon("src/Utlity/images/redSafe.png"));
                    redSafetyZone.setSize(51,50);
                    redSafetyZone.setLocation(102,y);
                    y=y+45;
                    squarePositions.add(redSafetyZone.getLocation());
                    Board.add(redSafetyZone);
                    boardSquares.get(i).setSquareColor(COLOR.RED);
                }else if(boardSquares.get(i) instanceof HomeSquare){
                    JLabel redHome = new JLabel(new ImageIcon("src/Utlity/images/redHome.png"));
                    redHome.setSize(100,100);
                    redHome.setLocation(76,274);
                    squarePositions.add(redHome.getLocation());
                    Board.add(redHome);
                    boardSquares.get(i).setSquareColor(COLOR.RED);
                }else if (boardSquares.get(i) instanceof StartSquare){
                    JLabel redStart = new JLabel(new ImageIcon("src/Utlity/images/redStart.png"));
                    redStart.setSize(100,100);
                    redStart.setLocation(180,47);
                    squarePositions.add(redStart.getLocation());
                    Board.add(redStart);
                }else if (boardSquares.get(i) instanceof InternalSlideSquare){
                    JLabel redInternalSlide = new JLabel(new ImageIcon("src/Utlity/images/slides/redSlideMedium.png"));
                    redInternalSlide.setSize(51,50);
                    redInternalSlide.setLocation(x-50,y-50);
                    x=x-51;
                    squarePositions.add(redInternalSlide.getLocation());
                    Board.add(redInternalSlide);
                }else if(boardSquares.get(i) instanceof EndSlideSquare){
                    JLabel redEndSlide = new JLabel(new ImageIcon("src/Utlity/images/slides/redSlideEnd.png"));
                    redEndSlide.setSize(51,50);
                    redEndSlide.setLocation(x-50,y-50);
                    x=x-51;
                    squarePositions.add(redEndSlide.getLocation());
                    Board.add(redEndSlide);
                    boardSquares.get(i).setSquareColor(COLOR.RED);
                } else if (boardSquares.get(i) instanceof StartSlideSquare){
                    JLabel redStartSlide = new JLabel(new ImageIcon("src/Utlity/images/slides/redSlideStart.png"));
                    redStartSlide.setSize(51,50);
                    redStartSlide.setLocation(x-50,y-50);
                    x=x-51;
                    squarePositions.add(redStartSlide.getLocation());
                    boardSquares.get(i).setSquareColor(COLOR.RED);
                    Board.add(redStartSlide);
                }
            }else if(boardSquares.get(i).getSquareColor() == COLOR.YELLOW){
                if(boardSquares.get(i) instanceof SafetyZoneSquare){
                    if (count2 == 0){
                        y = 691;
                    }
                    count2++;
                    JLabel yellowSafetyZone = new JLabel(new ImageIcon("src/Utlity/images/yellowSafe.png"));
                    yellowSafetyZone.setSize(51,50);
                    yellowSafetyZone.setLocation(658,y);
                    y=y-45;
                    squarePositions.add(yellowSafetyZone.getLocation());
                    Board.add(yellowSafetyZone);
                    boardSquares.get(i).setSquareColor(COLOR.YELLOW);

                }else if(boardSquares.get(i) instanceof HomeSquare){
                    JLabel yellowHome = new JLabel(new ImageIcon("src/Utlity/images/yellowHome.png"));
                    yellowHome.setSize(100,100);
                    yellowHome.setLocation(633,415);
                    squarePositions.add(yellowHome.getLocation());
                    Board.add(yellowHome);
                    boardSquares.get(i).setSquareColor(COLOR.YELLOW);

                }else if (boardSquares.get(i) instanceof StartSquare){
                    JLabel yellowStart = new JLabel(new ImageIcon("src/Utlity/images/yellowStart.png"));
                    yellowStart.setSize(100,100);
                    yellowStart.setLocation(535,638);
                    squarePositions.add(yellowStart.getLocation());
                    Board.add(yellowStart);
                }else if(boardSquares.get(i) instanceof InternalSlideSquare){
                    JLabel yellowInternalSlide = new JLabel(new ImageIcon("src/Utlity/images/slides/yellowSlideMedium.png"));
                    yellowInternalSlide.setSize(51,50);
                    yellowInternalSlide.setLocation(x+51,y-50);
                    x=x+51;
                    squarePositions.add(yellowInternalSlide.getLocation());
                    Board.add(yellowInternalSlide);
                }else if(boardSquares.get(i) instanceof EndSlideSquare){
                    JLabel yellowEndSlide = new JLabel(new ImageIcon("src/Utlity/images/slides/yellowSlideEnd.png"));
                    yellowEndSlide.setSize(51,50);
                    yellowEndSlide.setLocation(x+51,y-50);
                    x=x+51;
                    squarePositions.add(yellowEndSlide.getLocation());
                    Board.add(yellowEndSlide);
                    boardSquares.get(i).setSquareColor(COLOR.YELLOW);
                }else if(boardSquares.get(i) instanceof StartSlideSquare){
                    JLabel yellowStartSlide = new JLabel(new ImageIcon("src/Utlity/images/slides/yellowSlideStart.png"));
                    yellowStartSlide.setSize(51,50);
                    yellowStartSlide.setLocation(x+51,y-50);
                    x=x+51;
                    squarePositions.add(yellowStartSlide.getLocation());
                    Board.add(yellowStartSlide);
                    boardSquares.get(i).setSquareColor(COLOR.YELLOW);
                }
            }else if(boardSquares.get(i).getSquareColor() == COLOR.GREEN){
                if (boardSquares.get(i) instanceof InternalSlideSquare){
                    JLabel greenInternalSlide = new JLabel(new ImageIcon("src/Utlity/images/slides/greenSlideMedium.png"));
                    greenInternalSlide.setSize(51,50);
                    greenInternalSlide.setLocation(x,y-1);
                    y=y+50;
                    squarePositions.add(greenInternalSlide.getLocation());
                    Board.add(greenInternalSlide);
                } else if (boardSquares.get(i) instanceof StartSlideSquare) {
                    JLabel greenStartSlide = new JLabel(new ImageIcon("src/Utlity/images/slides/greenSlideStart.png"));
                    greenStartSlide.setSize(51,50);
                    greenStartSlide.setLocation(x,y-2);
                    y=y+50;
                    squarePositions.add(greenStartSlide.getLocation());
                    Board.add(greenStartSlide);
                    boardSquares.get(i).setSquareColor(COLOR.GREEN);
                }else if (boardSquares.get(i) instanceof EndSlideSquare) {
                    JLabel greenEndSlide = new JLabel(new ImageIcon("src/Utlity/images/slides/greenSlideEnd.png"));
                    greenEndSlide.setSize(51,50);
                    greenEndSlide.setLocation(x,y-1);
                    y=y+50;
                    squarePositions.add(greenEndSlide.getLocation());
                    Board.add(greenEndSlide);
                    boardSquares.get(i).setSquareColor(COLOR.GREEN);
                }

            }else if(boardSquares.get(i).getSquareColor() == COLOR.BLUE){
                if (boardSquares.get(i) instanceof InternalSlideSquare){
                    JLabel blueInternalSlide = new JLabel(new ImageIcon("src/Utlity/images/slides/blueSlideMedium.png"));
                    blueInternalSlide.setSize(51,50);
                    y=y-50;
                    blueInternalSlide.setLocation(x,y-48);
                    squarePositions.add(blueInternalSlide.getLocation());
                    Board.add(blueInternalSlide);
                }else if (boardSquares.get(i) instanceof StartSlideSquare) {
                    JLabel blueStartSlide = new JLabel(new ImageIcon("src/Utlity/images/slides/blueSlideStart.png"));
                    blueStartSlide.setSize(51,50);
                    y=y-50;
                    blueStartSlide.setLocation(x,y-48);
                    squarePositions.add(blueStartSlide.getLocation());
                    Board.add(blueStartSlide);
                    boardSquares.get(i).setSquareColor(COLOR.BLUE);
                }else if (boardSquares.get(i) instanceof EndSlideSquare) {
                    JLabel blueEndSlide = new JLabel(new ImageIcon("src/Utlity/images/slides/blueSlideEnd.png"));
                    blueEndSlide.setSize(51,50);
                    y=y-50;
                    blueEndSlide.setLocation(x,y-48);
                    squarePositions.add(blueEndSlide.getLocation());
                    Board.add(blueEndSlide);
                    boardSquares.get(i).setSquareColor(COLOR.BLUE);
                }
            }else if(boardSquares.get(i).getSquareColor() == COLOR.WHITE){
                JLabel whiteSquare = new JLabel(new ImageIcon("src/Utlity/images/whiteSquare.png"));
                whiteSquare.setSize(51,50);
                if (i < 16){
                    whiteSquare.setLocation(x,y-2);
                    y=y+48;
                    squarePositions.add(whiteSquare.getLocation());
                    Board.add(whiteSquare);
                }else if(i < 31){
                    x=x+50;
                    whiteSquare.setLocation(x,y-50);
                    squarePositions.add(whiteSquare.getLocation());
                    Board.add(whiteSquare);
                }else if (i < 46) {
                    y = y - 48;
                    whiteSquare.setLocation(x, y-50);
                    squarePositions.add(whiteSquare.getLocation());
                    Board.add(whiteSquare);

                }else if ( i < 61){
                    x = x - 50;
                    whiteSquare.setLocation(x, y-50);
                    squarePositions.add(whiteSquare.getLocation());
                    Board.add(whiteSquare);

                }
            }
        }
    }

    /**
     * <b>Accessor:</b> Accessing the square positions
     * <b>Postcondition:</b> The square positions have been returned.
     * @return squarePositions
     */
    public ArrayList<Point> getSquarePositions(){

        return squarePositions;
    }

    /**
     * <b>Transformer:</b> Initializes the pawns starting positions.
     * <b>Postcondition:</b> The pawns starting positions have been initialized.
     */
    public void initializePawnStartingPositions(){
        player1Pawn1 = new JLabel();
        player1Pawn2 = new JLabel();
        player2Pawn1 = new JLabel();
        player2Pawn2 = new JLabel();

        ImageIcon pawn11Icon = new ImageIcon("src/Utlity/images/pawns/redPawn1.png");
        ImageIcon pawn12Icon = new ImageIcon("src/Utlity/images/pawns/redPawn2.png");
        ImageIcon pawn21Icon = new ImageIcon("src/Utlity/images/pawns/yellowPawn1.png");
        ImageIcon pawn22Icon = new ImageIcon("src/Utlity/images/pawns/yellowPawn2.png");


        Image scalingPawn11 = pawn11Icon.getImage().getScaledInstance(50,50,Image.SCALE_SMOOTH);
        Image scalingPawn12 = pawn12Icon.getImage().getScaledInstance(50,50,Image.SCALE_SMOOTH);
        Image scalingPawn21 = pawn21Icon.getImage().getScaledInstance(50,50,Image.SCALE_SMOOTH);
        Image scalingPawn22 = pawn22Icon.getImage().getScaledInstance(50,50,Image.SCALE_SMOOTH);


        ImageIcon scaledPawn11 = new ImageIcon(scalingPawn11);
        ImageIcon scaledPawn12 = new ImageIcon(scalingPawn12);
        ImageIcon scaledPawn21 = new ImageIcon(scalingPawn21);
        ImageIcon scaledPawn22 = new ImageIcon(scalingPawn22);

        player1Pawn1.setIcon(scaledPawn11);
        player1Pawn2.setIcon(scaledPawn12);
        player2Pawn1.setIcon(scaledPawn21);
        player2Pawn2.setIcon(scaledPawn22);

        player1Pawn1.setSize(50,50);
        player1Pawn2.setSize(50,50);
        player2Pawn1.setSize(50,50);
        player2Pawn2.setSize(50,50);

        player1Pawn1.setLocation(180,60);
        player1Pawn2.setLocation(227,60);
        player2Pawn1.setLocation(535,668);
        player2Pawn2.setLocation(583,668);



        Board.add(player1Pawn1);
        Board.add(player1Pawn2);
        Board.add(player2Pawn1);
        Board.add(player2Pawn2);
    }

    /**
     * <b>Transformer:</b> Sets the player playing info on the info box.
     * <b>Postcondition:</b> The player playing info has been set on the info box.
     */
    void playerPlayingInfo(){
        if (controller.getPlayerPlayingNow() == 0){
            infoBoxTurn.setText("Turn: Player1");


        } else if (controller.getPlayerPlayingNow() == 1) {
            infoBoxTurn.setText("Turn: Player2");

        }


    }

    /**
     * <b>Transformer:</b> Sets the cards left info on the info box.
     * <b>Postcondition:</b> The cards left info has been set on the info box.
     */
    public void cardsLeftInfo(){
        int deckSize = controller.getDeckSize();
        infoBoxCardsLeft.setText("Cards Left: "+deckSize);
    }

    /**
     * <b>Transformer:</b> Ends the turn and set the new turn
     * <b>Postcondition:</b> Turn has ended and the new turn has been set
     */
    public void turn(){
            controller.setTurn();
            playerPlayingInfo();
            updateActivePawnsForDragDrop();
    }

    /**
     * <b>Transformer:</b> Checks if the player has finished the game.
     * <b>Postcondition:</b> The player has finished the game.
     */
    public void checkIfPlayerHasFinishedGUI(){
        if (isFoldButtonPressed){

            isFoldButtonPressed = false;
        }else{
            controller.setPlayerFinished(false);
        }
    }

    /**
     * <b>Transformer:</b> Updates the active pawns for drag and drop and for gui.
     * <b>Postcondition:</b> The active pawns for drag and drop and gui have been updated.
     */
    public void updateActivePawnsForDragDrop() {
        if (controller.getPlayerPlayingNow() == 0) {
            pawn1ForDragNdrop = player1Pawn1;
            pawn2ForDragNdrop = player1Pawn2;

            enemyPawn1ForDragNdrop = player2Pawn1;
            enemyPawn2ForDragNdrop = player2Pawn2;

            pawn1InPlay = controller.getPawn1Red();
            pawn2InPlay = controller.getPawn2Red();

            activeIndex1 = P11index;
            activeIndex2 = P12index;

            enemyIndex1 = P21index;
            enemyIndex2 = P22index;

            enemyPawn1 = controller.getPawn1Yellow();
            enemyPawn2 = controller.getPawn2Yellow();

        } else if (controller.getPlayerPlayingNow() == 1) {
            pawn1ForDragNdrop = player2Pawn1;
            pawn2ForDragNdrop = player2Pawn2;

            pawn1InPlay = controller.getPawn1Yellow();
            pawn2InPlay = controller.getPawn2Yellow();

            activeIndex1 = P21index;
            activeIndex2 = P22index;

            enemyIndex1 = P11index;
            enemyIndex2 = P12index;

            enemyPawn1 = controller.getPawn1Red();
            enemyPawn2 = controller.getPawn2Red();
        }
        dragNdropFunc();
    }


    /**
     * <b>Transformer:</b> Sets the Drag and Drop function.
     * <b>Postcondition:</b> The Drag and Drop function has been set.
     *
     */
    public void dragNdropFunc(){
        //drag and drop function
        for (MouseListener listener : Board.getMouseListeners()) {
            if (listener instanceof DragDrop) {
                Board.removeMouseListener(listener);
            }
        }
        for (MouseMotionListener listener : Board.getMouseMotionListeners()) {
            if (listener instanceof DragDrop) {
                Board.removeMouseMotionListener(listener);
            }
        }

        dragDrop = new DragDrop(this,pawn1ForDragNdrop,pawn2ForDragNdrop);
        Board.addMouseListener(dragDrop);
        Board.addMouseMotionListener(dragDrop);

    }

    /**
     * <b>Transformer:</b> Updates the game info.
     * <b>Postcondition:</b> The game info has been updated.
     */
    public void updateGameInfo(){
        checkIfPlayerHasFinishedGUI();
        turn();
        playerPlayingInfo();
        cardsLeftInfo();
        updateActivePawnsForDragDrop();
        numberCardInPlay = null;
        rightMenu.remove(CurrentCardDisplay);
        frame.revalidate();
        frame.repaint();
        isCardInPlay= false;
    }

    /**
     * <b>Transformer:</b> Updates the game info for when redraw card is needed.
     * <b>Postcondition:</b> The game info has been updated for redraw.
     */
    public void updateGameInfoForReDraw(){
        checkIfPlayerHasFinishedGUI();
        cardsLeftInfo();
        updateActivePawnsForDragDrop();
        System.out.println("Player Playing Now: " + controller.getPlayerPlayingNow());
        rightMenu.remove(CurrentCardDisplay);
        frame.revalidate();
        frame.repaint();
        isCardInPlay= false;
    }

    /**
     * <b>Transformer:</b> Main for running and testing my GUI
     * <b>Postcondition:</b> Runs the GUI
     */
    public static void main(String[] args){
        new GUI();
    }

    private final ArrayList<Point> boardSquaresAllowedMovementForP1 = new ArrayList<Point>();
    private final ArrayList<Point> boardSquaresAllowedMovementForP2 = new ArrayList<Point>();

    /**
     * <b>Transformer:</b> Sets the movement of the pawns.
     * <b>Postcondition:</b> The movement of the pawns has been set.
     */
    public void movement(){
        ArrayList<Square> boardSquares = controller.getBoardSquares();
        //emptying the array list
        boardSquaresAllowedMovementForP1.clear();
        boardSquaresAllowedMovementForP2.clear();

        if (isCardInPlay){
            if (pawn1InPlay.getStart() && pawn2InPlay.getStart()){
                if (numberCardInPlay.getNumber() != 1 && numberCardInPlay.getNumber() != 2){
                    canMove = false;
                }else{
                    canMove = true;
                }
            }

            if (numberCardInPlay.getNumber() == 1){
                if (pawn1InPlay.getStart() && pawn2InPlay.getStart()){
                    if (pawn1InPlay.getColor() == Model.Pawn.COLOR.RED){
                        boardSquaresAllowedMovementForP1.add(squarePositions.get(56));
                        boardSquaresAllowedMovementForP2.add(squarePositions.get(56));

                    }else{
                        boardSquaresAllowedMovementForP1.add(squarePositions.get(26));
                        boardSquaresAllowedMovementForP2.add(squarePositions.get(26));
                    }

                }else if(pawn1InPlay.getStart() && !pawn2InPlay.getStart()){
                    if (pawn1InPlay.getColor() == Model.Pawn.COLOR.RED) {
                        boardSquaresAllowedMovementForP1.add(squarePositions.get(56));
                        if(activeIndex2-1 < 0){
                            activeIndex2 = 61 + (activeIndex2-1);
                        }
                        boardSquaresAllowedMovementForP2.add(squarePositions.get(activeIndex2 - 1));
                    }else{
                        boardSquaresAllowedMovementForP1.add(squarePositions.get(26));
                        if(activeIndex2-1 < 0){
                            activeIndex2 = 61 + (activeIndex2-1);
                        }
                        boardSquaresAllowedMovementForP2.add(squarePositions.get(activeIndex2-1));
                    }


                }else if (!pawn1InPlay.getStart() && pawn2InPlay.getStart()){
                    if (pawn1InPlay.getColor() == Model.Pawn.COLOR.RED) {
                        boardSquaresAllowedMovementForP2.add(squarePositions.get(56));
                        if(activeIndex1-1 < 0){
                            activeIndex1 = 61 + (activeIndex1-1);
                        }
                        boardSquaresAllowedMovementForP1.add(squarePositions.get(activeIndex1 - 1));

                    }else{
                        boardSquaresAllowedMovementForP2.add(squarePositions.get(26));
                        if(activeIndex1-1 < 0){
                            activeIndex1 = 61 + (activeIndex1-1);
                        }
                        boardSquaresAllowedMovementForP1.add(squarePositions.get(activeIndex1-1));

                    }

                }else if (!pawn1InPlay.getStart() && !pawn2InPlay.getStart()) {
                    boardSquaresAllowedMovementForP1.add(squarePositions.get(activeIndex1-1));
                    boardSquaresAllowedMovementForP2.add(squarePositions.get(activeIndex2-1));
                }

            } else if (numberCardInPlay.getNumber() == 2){//TODO: END OF CARD 1 BEGGING OF CARD 2
                if (pawn1InPlay.getStart() && pawn2InPlay.getStart()){
                    if (pawn1InPlay.getColor() == Model.Pawn.COLOR.RED){
                        boardSquaresAllowedMovementForP1.add(squarePositions.get(56));
                        boardSquaresAllowedMovementForP2.add(squarePositions.get(56));


                    }else{
                        boardSquaresAllowedMovementForP1.add(squarePositions.get(26));
                        boardSquaresAllowedMovementForP2.add(squarePositions.get(26));

                    }

                }else if(pawn1InPlay.getStart() && !pawn2InPlay.getStart()){
                    if (pawn1InPlay.getColor() == Model.Pawn.COLOR.RED) {
                        boardSquaresAllowedMovementForP1.add(squarePositions.get(56));
                        if(activeIndex2-2 < 0){
                            activeIndex2 = 61 + (activeIndex2-2);
                        }
                        boardSquaresAllowedMovementForP2.add(squarePositions.get(activeIndex2 - 2));

                    }else{
                        boardSquaresAllowedMovementForP1.add(squarePositions.get(26));
                        if(activeIndex2-2 < 0){
                            activeIndex2 = 61 + (activeIndex2-2);
                        }
                        boardSquaresAllowedMovementForP2.add(squarePositions.get(activeIndex2-2));
                    }

                }else if (!pawn1InPlay.getStart() && pawn2InPlay.getStart()){
                    if (pawn1InPlay.getColor() == Model.Pawn.COLOR.RED) {
                        boardSquaresAllowedMovementForP2.add(squarePositions.get(56));
                        if(activeIndex1-2 < 0){
                            activeIndex1 = 61 + (activeIndex1-2);
                        }
                        boardSquaresAllowedMovementForP1.add(squarePositions.get(activeIndex1 - 2));
                    }else{
                        boardSquaresAllowedMovementForP2.add(squarePositions.get(26));
                        if(activeIndex1-2 < 0){
                            activeIndex1 = 61 + (activeIndex1-2);
                        }
                        boardSquaresAllowedMovementForP1.add(squarePositions.get(activeIndex1-2));
                    }
                }else if (!pawn1InPlay.getStart() && !pawn2InPlay.getStart()) {
                    if(activeIndex1-2 < 0){
                        activeIndex1 = 61 + (activeIndex1-2);
                    }
                    if(activeIndex2-2 < 0){
                        activeIndex2 = 61 + (activeIndex2-2);
                    }
                    boardSquaresAllowedMovementForP1.add(squarePositions.get(activeIndex1-2));
                    boardSquaresAllowedMovementForP2.add(squarePositions.get(activeIndex2-2));
                }
            } else if (numberCardInPlay.getNumber() == 3){
                if (pawn1InPlay.getStart() && pawn2InPlay.getStart()){
                    return;
                }else if(pawn1InPlay.getStart() && !pawn2InPlay.getStart()){
                    if (pawn1InPlay.getColor() == Model.Pawn.COLOR.RED) {
                        if(activeIndex2-3 < 0){
                            activeIndex2 = 61 + (activeIndex2-3);
                        }
                        boardSquaresAllowedMovementForP2.add(squarePositions.get(activeIndex2 - 3));

                    }else{
                        if(activeIndex2-3 < 0){
                            activeIndex2 = 61 + (activeIndex2-3);
                        }
                        boardSquaresAllowedMovementForP2.add(squarePositions.get(activeIndex2-3));

                    }

                }else if (!pawn1InPlay.getStart() && pawn2InPlay.getStart()){
                    if (pawn1InPlay.getColor() == Model.Pawn.COLOR.RED) {
                        if(activeIndex1-3 < 0){
                            activeIndex1 = 61 + (activeIndex1-3);
                        }
                        boardSquaresAllowedMovementForP1.add(squarePositions.get(activeIndex1 - 3));


                    }else{
                        if(activeIndex1-3 < 0){
                            activeIndex1 = 61 + (activeIndex1-3);
                        }
                        boardSquaresAllowedMovementForP1.add(squarePositions.get(activeIndex1-3));


                    }

                }else if (!pawn1InPlay.getStart() && !pawn2InPlay.getStart()) {
                    if(activeIndex1-3 < 0){
                        activeIndex1 = 61 + (activeIndex1-3);
                    }
                    if(activeIndex2-3 < 0){
                        activeIndex2 = 61 + (activeIndex2-3);
                    }
                    boardSquaresAllowedMovementForP1.add(squarePositions.get(activeIndex1-3));
                    boardSquaresAllowedMovementForP2.add(squarePositions.get(activeIndex2-3));
                }

            } else if (numberCardInPlay.getNumber() == 4){//TODO: CANNOT MOVE IN TO SAFES SQUARES(HOME SQUARES) BACKWARDS ONLY FORWARDS!
                if (pawn1InPlay.getStart() && pawn2InPlay.getStart()){

                }else if(pawn1InPlay.getStart() && !pawn2InPlay.getStart()){
                    if (pawn1InPlay.getColor() == Model.Pawn.COLOR.RED) {
                        if(activeIndex2+4 > 61){
                            activeIndex2 = 0 + (activeIndex2+4);
                        }
                        boardSquaresAllowedMovementForP2.add(squarePositions.get(activeIndex2 + 4));

                        for (int i = 0; i < boardSquares.size(); i++) {
                            if (boardSquaresAllowedMovementForP2.get(0).equals(squarePositions.get(i) ) ){
                                if (boardSquares.get(i) instanceof SafetyZoneSquare || boardSquares.get(i) instanceof HomeSquare){
                                    boardSquaresAllowedMovementForP2.clear();
                                }
                            }
                        }

                    }else{
                        if(activeIndex2+4 > 61){
                            activeIndex2 = 0 + (activeIndex2+4);
                        }
                        boardSquaresAllowedMovementForP2.add(squarePositions.get(activeIndex2+4));
                        for (int i = 0; i < boardSquares.size(); i++) {
                            if (boardSquaresAllowedMovementForP2.get(0).equals(squarePositions.get(i) ) ){
                                if (boardSquares.get(i) instanceof SafetyZoneSquare || boardSquares.get(i) instanceof HomeSquare){
                                    boardSquaresAllowedMovementForP2.clear();
                                }
                            }
                        }

                    }

                }else if (!pawn1InPlay.getStart() && pawn2InPlay.getStart()){
                    if (pawn1InPlay.getColor() == Model.Pawn.COLOR.RED) {
                        if(activeIndex1+4 > 61){
                            activeIndex1 = 0 + (activeIndex1+4);
                        }
                        boardSquaresAllowedMovementForP1.add(squarePositions.get(activeIndex1 + 4));

                        for (int i = 0; i < boardSquares.size(); i++) {
                            if (boardSquaresAllowedMovementForP1.get(0).equals(squarePositions.get(i) ) ){
                                if (boardSquares.get(i) instanceof SafetyZoneSquare || boardSquares.get(i) instanceof HomeSquare){
                                    boardSquaresAllowedMovementForP1.clear();
                                }
                            }
                        }

                    }else{
                        if(activeIndex1+4 > 61){
                            activeIndex1 = 0 + (activeIndex1+4);
                        }
                        boardSquaresAllowedMovementForP1.add(squarePositions.get(activeIndex1 + 4));

                        for (int i = 0; i < boardSquares.size(); i++) {
                            if (boardSquaresAllowedMovementForP1.get(0).equals(squarePositions.get(i) ) ){
                                if (boardSquares.get(i) instanceof SafetyZoneSquare || boardSquares.get(i) instanceof HomeSquare){
                                    boardSquaresAllowedMovementForP1.clear();
                                }
                            }
                        }

                    }

                }else if (!pawn1InPlay.getStart() && !pawn2InPlay.getStart()) {
                    if(activeIndex1+4 > 61){
                        activeIndex1 = 0 + (activeIndex1+4);
                    }
                    if(activeIndex2+4 > 61){
                        activeIndex2 = 0 + (activeIndex2+4);
                    }
                    boardSquaresAllowedMovementForP1.add(squarePositions.get(activeIndex1+4));
                    boardSquaresAllowedMovementForP2.add(squarePositions.get(activeIndex2+4));

                    for (int i = 0; i < boardSquares.size(); i++) {
                        if (boardSquaresAllowedMovementForP1.get(0).equals(squarePositions.get(i) ) ){
                            if (boardSquares.get(i) instanceof SafetyZoneSquare || boardSquares.get(i) instanceof HomeSquare){
                                boardSquaresAllowedMovementForP1.clear();
                            }
                        }
                    }

                    for (int i = 0; i < boardSquares.size(); i++) {
                        if (boardSquaresAllowedMovementForP2.get(0).equals(squarePositions.get(i) ) ){
                            if (boardSquares.get(i) instanceof SafetyZoneSquare || boardSquares.get(i) instanceof HomeSquare){
                                boardSquaresAllowedMovementForP2.clear();
                            }
                        }
                    }

                }

            } else if (numberCardInPlay.getNumber() == 5){
                if (pawn1InPlay.getStart() && pawn2InPlay.getStart()){

                }else if(pawn1InPlay.getStart() && !pawn2InPlay.getStart()){
                    if (pawn1InPlay.getColor() == Model.Pawn.COLOR.RED) {
                        if(activeIndex2-5 < 0){
                            activeIndex2 = 61 + (activeIndex2-5);
                        }
                        boardSquaresAllowedMovementForP2.add(squarePositions.get(activeIndex2 - 5));

                    }else{
                        if(activeIndex2-5 < 0){
                            activeIndex2 = 61 + (activeIndex2-5);
                        }
                        boardSquaresAllowedMovementForP2.add(squarePositions.get(activeIndex2-5));

                    }

                }else if (!pawn1InPlay.getStart() && pawn2InPlay.getStart()){
                    if (pawn1InPlay.getColor() == Model.Pawn.COLOR.RED) {
                        if(activeIndex1-5 < 0){
                            activeIndex1 = 61 + (activeIndex1-5);
                        }
                        boardSquaresAllowedMovementForP1.add(squarePositions.get(activeIndex1 - 5));

                    }else{
                        if(activeIndex1-5 < 0){
                            activeIndex1 = 61 + (activeIndex1-5);
                        }
                        boardSquaresAllowedMovementForP1.add(squarePositions.get(activeIndex1-5));

                    }

                }else if (!pawn1InPlay.getStart() && !pawn2InPlay.getStart()) {
                    if(activeIndex1-5 < 0){
                        activeIndex1 = 61 + (activeIndex1-5);
                    }
                    if(activeIndex2-5 < 0){
                        activeIndex2 = 61 + (activeIndex2-5);
                    }
                    boardSquaresAllowedMovementForP1.add(squarePositions.get(activeIndex1-5));
                    boardSquaresAllowedMovementForP2.add(squarePositions.get(activeIndex2-5));
                }




            } else if (numberCardInPlay.getNumber() == 7){
                if (pawn1InPlay.getStart() && pawn2InPlay.getStart()){

                }else if(pawn1InPlay.getStart() && !pawn2InPlay.getStart()){
                    if (pawn1InPlay.getColor() == Model.Pawn.COLOR.RED) {
                        if(activeIndex2-7 < 0){
                            activeIndex2 = 61 + (activeIndex2-7);
                        }
                        boardSquaresAllowedMovementForP2.add(squarePositions.get(activeIndex2 - 7));

                    }else{
                        if(activeIndex2-7 < 0){
                            activeIndex2 = 61 + (activeIndex2-7);
                        }
                        boardSquaresAllowedMovementForP2.add(squarePositions.get(activeIndex2-7));

                    }

                }else if (!pawn1InPlay.getStart() && pawn2InPlay.getStart()){
                    if (pawn1InPlay.getColor() == Model.Pawn.COLOR.RED) {
                        if(activeIndex1-7 < 0){
                            activeIndex1 = 61 + (activeIndex1-7);
                        }
                        boardSquaresAllowedMovementForP1.add(squarePositions.get(activeIndex1 - 7));

                    }else{
                        if(activeIndex1-7 < 0){
                            activeIndex1 = 61 + (activeIndex1-7);
                        }
                        boardSquaresAllowedMovementForP1.add(squarePositions.get(activeIndex1-7));
                    }
                }else if (!pawn1InPlay.getStart() && !pawn2InPlay.getStart()) {
                    if(activeIndex1-7 < 0){
                        activeIndex1 = 61 + (activeIndex1-7);
                    }
                    if(activeIndex2-7 < 0){
                        activeIndex2 = 61 + (activeIndex2-7);
                    }
                    boardSquaresAllowedMovementForP1.add(squarePositions.get(activeIndex1-7));
                    boardSquaresAllowedMovementForP2.add(squarePositions.get(activeIndex2-7));
                }

            } else if (numberCardInPlay.getNumber() == 8){
                if (pawn1InPlay.getStart() && pawn2InPlay.getStart()){

                }else if(pawn1InPlay.getStart() && !pawn2InPlay.getStart()){
                    if (pawn1InPlay.getColor() == Model.Pawn.COLOR.RED) {
                        if(activeIndex2-8 < 0){
                            activeIndex2 = 61 + (activeIndex2-8);
                        }
                        boardSquaresAllowedMovementForP2.add(squarePositions.get(activeIndex2 - 8));

                    }else{
                        if(activeIndex2-8 < 0){
                            activeIndex2 = 61 + (activeIndex2-8);
                        }
                        boardSquaresAllowedMovementForP2.add(squarePositions.get(activeIndex2-8));

                    }

                }else if (!pawn1InPlay.getStart() && pawn2InPlay.getStart()){
                    if (pawn1InPlay.getColor() == Model.Pawn.COLOR.RED) {
                        boardSquaresAllowedMovementForP1.add(squarePositions.get(activeIndex1 - 8));
                        if(activeIndex1-8 < 0){
                            activeIndex1 = 61 + (activeIndex1-8);
                        }

                    }else{
                        if(activeIndex1-8 < 0){
                            activeIndex1 = 61 + (activeIndex1-8);
                        }
                        boardSquaresAllowedMovementForP1.add(squarePositions.get(activeIndex1-8));

                    }

                }else if (!pawn1InPlay.getStart() && !pawn2InPlay.getStart()) {
                    if(activeIndex1-8 < 0){
                        activeIndex1 = 61 + (activeIndex1-8);
                    }
                    if(activeIndex2-8 < 0){
                        activeIndex2 = 61 + (activeIndex2-8);
                    }
                    boardSquaresAllowedMovementForP1.add(squarePositions.get(activeIndex1-8));
                    boardSquaresAllowedMovementForP2.add(squarePositions.get(activeIndex2-8));
                }
                //TODO: OR CHOOSE A NEW CARD

            } else if (numberCardInPlay.getNumber() == 10){
                if (pawn1InPlay.getStart() && pawn2InPlay.getStart()){

                }else if(pawn1InPlay.getStart() && !pawn2InPlay.getStart()){
                    if (pawn1InPlay.getColor() == Model.Pawn.COLOR.RED) {
                        if(activeIndex2-10 < 0){
                            activeIndex2 = 61 + (activeIndex2-10);
                        }
                        boardSquaresAllowedMovementForP2.add(squarePositions.get(activeIndex2 - 10));
                        boardSquaresAllowedMovementForP2.add(squarePositions.get(activeIndex2 + 1));

                    }else{
                        if(activeIndex2-10 < 0){
                            activeIndex2 = 61 + (activeIndex2-10);
                        }
                        boardSquaresAllowedMovementForP2.add(squarePositions.get(activeIndex2-10));
                        boardSquaresAllowedMovementForP2.add(squarePositions.get(activeIndex2+1));

                    }

                }else if (!pawn1InPlay.getStart() && pawn2InPlay.getStart()){
                    if (pawn1InPlay.getColor() == Model.Pawn.COLOR.RED) {
                        if(activeIndex1-10 < 0){
                            activeIndex1 = 61 + (activeIndex1-10);
                        }
                        boardSquaresAllowedMovementForP1.add(squarePositions.get(activeIndex1 - 10));
                        boardSquaresAllowedMovementForP1.add(squarePositions.get(activeIndex1 +1));

                    }else{
                        if(activeIndex1-10 < 0){
                            activeIndex1 = 61 + (activeIndex1-10);
                        }
                        boardSquaresAllowedMovementForP1.add(squarePositions.get(activeIndex1-10));
                        boardSquaresAllowedMovementForP1.add(squarePositions.get(activeIndex1+1));

                    }

                }else if (!pawn1InPlay.getStart() && !pawn2InPlay.getStart()) {
                    if(activeIndex1-10 < 0){
                        activeIndex1 = 61 + (activeIndex1-10);
                    }
                    if(activeIndex2-10 < 0){
                        activeIndex2 = 61 + (activeIndex2-10);
                    }
                    if(activeIndex1+1 > 61){
                        activeIndex1 = 0 + (activeIndex1+1);
                    }
                    if(activeIndex2+1 > 61){
                        activeIndex2 = 0 + (activeIndex2+1);
                    }
                    boardSquaresAllowedMovementForP1.add(squarePositions.get(activeIndex1-10));
                    boardSquaresAllowedMovementForP1.add(squarePositions.get(activeIndex1+1));
                    boardSquaresAllowedMovementForP2.add(squarePositions.get(activeIndex2-10));
                    boardSquaresAllowedMovementForP2.add(squarePositions.get(activeIndex2+1));
                }

            } else if (numberCardInPlay.getNumber() == 11){
                if (pawn1InPlay.getStart() && pawn2InPlay.getStart()){

                }else if(pawn1InPlay.getStart() && !pawn2InPlay.getStart()){
                    if (pawn1InPlay.getColor() == Model.Pawn.COLOR.RED) {
                        if(activeIndex2-11 < 0){
                            activeIndex2 = 61 + (activeIndex2-10);
                        }

                        boardSquaresAllowedMovementForP2.add(squarePositions.get(activeIndex2 - 11));

                    }else{
                        if(activeIndex2-11 < 0){
                            activeIndex2 = 61 + (activeIndex2-10);
                        }
                        boardSquaresAllowedMovementForP2.add(squarePositions.get(activeIndex2-11));

                    }

                }else if (!pawn1InPlay.getStart() && pawn2InPlay.getStart()){
                    if (pawn1InPlay.getColor() == Model.Pawn.COLOR.RED) {
                        if(activeIndex1-11 < 0){
                            activeIndex1 = 61 + (activeIndex1-10);
                        }
                        boardSquaresAllowedMovementForP1.add(squarePositions.get(activeIndex1 - 11));

                    }else{
                        if(activeIndex1-11 < 0){
                            activeIndex1 = 61 + (activeIndex1-10);
                        }
                        boardSquaresAllowedMovementForP1.add(squarePositions.get(activeIndex1-11));

                    }

                }else if (!pawn1InPlay.getStart() && !pawn2InPlay.getStart()) {
                    if(activeIndex1-11 < 0){
                        activeIndex1 = 61 + (activeIndex1-10);
                    }
                    if(activeIndex2-11 < 0){
                        activeIndex2 = 61 + (activeIndex2-10);
                    }
                    boardSquaresAllowedMovementForP1.add(squarePositions.get(activeIndex1-11));
                    boardSquaresAllowedMovementForP2.add(squarePositions.get(activeIndex2-11));
                }
                //TODO: OR SWAP PAWNS

            } else if (numberCardInPlay.getNumber() == 12){
                if (pawn1InPlay.getStart() && pawn2InPlay.getStart()){

                }else if(pawn1InPlay.getStart() && !pawn2InPlay.getStart()){
                    if (pawn1InPlay.getColor() == Model.Pawn.COLOR.RED) {
                        if(activeIndex2-12 < 0){
                            activeIndex2 = 61 + (activeIndex2-12);
                        }
                        boardSquaresAllowedMovementForP2.add(squarePositions.get(activeIndex2 - 12));


                    }else{
                        if(activeIndex2-12 < 0){
                            activeIndex2 = 61 + (activeIndex2-12);
                        }
                        boardSquaresAllowedMovementForP2.add(squarePositions.get(activeIndex2-12));


                    }

                }else if (!pawn1InPlay.getStart() && pawn2InPlay.getStart()){
                    if (pawn1InPlay.getColor() == Model.Pawn.COLOR.RED) {
                        if(activeIndex1-12 < 0){
                            activeIndex1 = 61 + (activeIndex1-12);
                        }
                        boardSquaresAllowedMovementForP1.add(squarePositions.get(activeIndex1 - 12));
                    }else{
                        if(activeIndex1-12 < 0){
                            activeIndex1 = 61 + (activeIndex1-12);
                        }
                        boardSquaresAllowedMovementForP1.add(squarePositions.get(activeIndex1-12));
                    }
                }else if (!pawn1InPlay.getStart() && !pawn2InPlay.getStart()) {
                    if(activeIndex1-12 < 0){
                        activeIndex1 = 61 + (activeIndex1-12);
                    }
                    if(activeIndex2-12 < 0){
                        activeIndex2 = 61 + (activeIndex2-12);
                    }
                    boardSquaresAllowedMovementForP1.add(squarePositions.get(activeIndex1-12));
                    boardSquaresAllowedMovementForP2.add(squarePositions.get(activeIndex2-12));
                }
                //TODO: OR CHOOSE A NEW CARD

            } else if (numberCardInPlay.getNumber() == -1){
                    if (pawn1InPlay.getStart() && !pawn2InPlay.getStart() && !enemyPawn1.getSafe() && enemyPawn2.getSafe()){
                        //swap pawn 1 in play and enemy pawn 1
                    }else if(!pawn1InPlay.getStart() && pawn2InPlay.getStart() && !enemyPawn1.getSafe() && enemyPawn2.getSafe()){
                        //swap pawn 2 in play and enemy pawn 1
                    }else if (pawn1InPlay.getStart() && !pawn2InPlay.getStart() && enemyPawn1.getSafe() && !enemyPawn2.getSafe()){
                        //swap pawn 1 in play and enemy pawn 2
                    }else if( !pawn1InPlay.getStart() && pawn2InPlay.getStart() && enemyPawn1.getSafe() && !enemyPawn2.getSafe()){
                        //swap pawn 2 in play and enemy pawn 2
                    }else if( pawn1InPlay.getStart() && !pawn2InPlay.getStart() && !enemyPawn1.getSafe() && !enemyPawn2.getSafe()){
                        //swap pawn 1 in play and enemy pawn 1 or enemy pawn 2
                    }else if( !pawn1InPlay.getStart() && pawn2InPlay.getStart() && !enemyPawn1.getSafe() && !enemyPawn2.getSafe()) {
                        //swap pawn 2 in play and enemy pawn 1 or enemy pawn 2
                    }else{
                        JOptionPane.showMessageDialog(frame,"Cannot swap with enemy pawn that is in safe zone");
                        System.out.println("Cannot swap with enemy pawn that is in safe zone");
                    }
            }
        }
    }

    /**
     * <b>Accessor:</b> Accesses the array list for the allowed movements that the pawn 1 is allowed to do
     * <b>Postcondition:</b> Returns the array list for the allowed movements that the pawn 1 is allowed to do
     */
    public ArrayList<Point> getBoardSquaresAllowedMovementForP1(){
        return boardSquaresAllowedMovementForP1;
    }

    /**
     * <b>Accessor:</b> Accesses the array list for the allowed movements that the pawn 2 is allowed to do
     * <b>Postcondition:</b> Returns the array list for the allowed movements that the pawn 2 is allowed to do
     */
    public ArrayList<Point> getBoardSquaresAllowedMovementForP2(){
        return boardSquaresAllowedMovementForP2;
    }

    /**
     * <b>Transformer:</b> Sets the active index of pawn1
     * <b>Postcondition:</b> The active index of pawn1 is set
     */
    public void setActiveIndex1(int index){
        if (controller.getPlayerPlayingNow() == 0){
            P11index = index;
        }else if (controller.getPlayerPlayingNow() == 1){
            P21index = index;
        }
    }

    /**
     * <b>Transformer:</b> Sets the active index of pawn2
     * <b>Postcondition:</b> The active index of pawn2 is set
     */
    public void setActiveIndex2(int index){
        if (controller.getPlayerPlayingNow() == 0){
            P12index = index;
        }else if (controller.getPlayerPlayingNow() == 1){
            P22index = index;
        }
    }

    /**
     * <b>Accessor:</b> Accesses the arraylist with the object with the squares
     * <b>Postcondition:</b> Returns the arraylist with the object with the squares
     */
    public  ArrayList<Square> getSquareOBJ(){
        ArrayList<Square> boardSquares = controller.getBoardSquares();
        return boardSquares;
    }

    /**
     * <b>Accessor:</b> Accesses the plaeyr that is playing now
     * <b>Postcondition:</b> Returns the player that is playing now
     */
    public Player getPlayer(){
        if (controller.getPlayerPlayingNow() == 0) {
            return controller.getP1();
        }else{
            return controller.getP2();
        }
    }

    /**
     * <b>Accessor:</b> Accesses the pawn 1 that is playing now
     * <b>Postcondition:</b> Returns the pawn 1 that is playing now
     */
    public PlayerPawn getPawn1(){
        return pawn1InPlay;
    }

    /**
     * <b>Accessor:</b> Accesses the pawn 2 that is playing now
     * <b>Postcondition:</b> Returns the pawn 2 that is playing now
     */
    public PlayerPawn getPawn2(){
        return pawn2InPlay;
    }

    /**
     * <b>Accessor:</b> Accesses the number card that is playing now
     * <b>Postcondition:</b> Returns the number card that is playing now
     */
    public boolean isCardInPlay(){
        return isCardInPlay;
    }


    private boolean canMove = true;

/**
     * <b>Transformer:</b> Sets the boolean if the player can move or not
     * <b>Postcondition:</b> The boolean if the player can move or not is set
     */
    public int getCardInPlayNumber(){
        return numberCardInPlay.getNumber();
    }

    /**
     * <b>Accessor:</b> gets the enemy pawn label 1
     * <b>Postcondition:</b> the enemy pawn label 1 is returned
     */
    public JLabel getEnemyPawn1Label(){
        return enemyPawn1ForDragNdrop;
    }

    /**
     * <b>Accessor:</b> gets the enemy pawn label 2
     * <b>Postcondition:</b> the enemy pawn label 2 is returned
     */
    public JLabel getEnemyPawn2Label(){
        return enemyPawn2ForDragNdrop;
    }

    /**
     * <b>Transformer:</b> swapping pawn positions
     * <b>Postcondition:</b> the pawn positions are swapped
     */
    public void swapPawnPositions(JLabel playerPawnLabel, JLabel enemyPawnLabel) {
        System.out.println("1Before Swap: Active Pawn Position: " + playerPawnLabel.getLocation() + ", Enemy Pawn Position: " + enemyPawnLabel.getLocation());
        System.out.println("2Before Swap lastLocationBeforeSnap:  " + dragDrop.getLastPositionBeforeSnap() + ", Enemy Pawn Index: " + getIndexFromLabel(enemyPawnLabel));

        if (playerPawnLabel == enemyPawnLabel) {
            System.out.println("Attempted to swap pawn with itself, which is not allowed.");
            return;
        }


        // Save original locations
        Point playerOriginalLocation = dragDrop.getLastPositionBeforeSnap();
        Point enemyOriginalLocation = enemyPawnLabel.getLocation();

        // Swap locations
        playerPawnLabel.setLocation(enemyOriginalLocation);
        enemyPawnLabel.setLocation(playerOriginalLocation);

        // Update indexes
        updateIndexesAfterSwap(playerPawnLabel, enemyPawnLabel);

        System.out.println("After Swap: Active Pawn Position: " + playerPawnLabel.getLocation() + ", Enemy Pawn Position: " + enemyPawnLabel.getLocation());

        frame.revalidate();
        frame.repaint();
    }

    /**
     * <b>Transformer:</b> Updates the indexes after the swap
     * <b>Postcondition:</b> The indexes after the swap are updated
     */
    private void updateIndexesAfterSwap(JLabel playerPawnLabel, JLabel enemyPawnLabel) {
        int playerIndex = getIndexFromLabel(playerPawnLabel);
        int enemyIndex = getIndexFromLabel(enemyPawnLabel);

        setActiveIndexForLabel(playerPawnLabel, enemyIndex);
        setActiveIndexForLabel(enemyPawnLabel, playerIndex);
    }

    /**
     * <b>Transformer:</b> Updates the pawn indexes after the swap
     * <b>Postcondition:</b> The pawn indexes after the swap are updated
     */
    public int getIndexFromLabel(JLabel label) {
        if (label.equals(player1Pawn1)) {
            return P11index;
        } else if (label.equals(player1Pawn2)) {
            return P12index;
        } else if (label.equals(player2Pawn1)) {
            return P21index;
        } else if (label.equals(player2Pawn2)) {
            return P22index;
        }
        return -1;
    }

    /**
     * <b>Transformer:</b> Updates the active indexes for the pawns
     * <b>Postcondition:</b> The active indexes for the pawns are updated
     */
    public void setActiveIndexForLabel(JLabel label, int index) {
        if (label.equals(player1Pawn1)) {
            P11index = index;
        } else if (label.equals(player1Pawn2)) {
            P12index = index;
        } else if (label.equals(player2Pawn1)) {
            P21index = index;
        } else if (label.equals(player2Pawn2)) {
            P22index = index;
        }
    }
}
