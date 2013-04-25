import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;
import java.lang.*;
/**
 * ChessGUI class to hold JButtons and action listeners
 * 
 * @author Austin Herr
 * @version 1.0
 */
public class ChessGUI
{
    //creates a 2d array of JButtons
    JButton[][] buttons = new JButton[8][8];
    //JFrame to hold the buttons
    JFrame frame = new JFrame("Chess");
    //instance of the Controller class
    Controller bob = new Controller();
    //boolean if the initial move piece has been gotten
    boolean gotPiece = false;
    //String to hold the initial piece to move
    String currPiece = "";
    
    /**
     * Display method to set layout and size of the frame and makes the GUI
     */
    public void display()
    {
        frame.setSize(400,400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(8,8));
        
        ini();
        setColors();
        updateBoard();
        for(int i = 0; i < 8; i++)
        {
            for(int j = 0; j < 8; j++)
            {
                frame.add(buttons[i][j]);
            }
        }
        
        frame.setVisible(true);
    }
    /**
     * Method to add buttons to the 2d buttons array
     */
    public void ini()
    {
        for(int i = 0; i < 8; i++)
        {
            for(int j = 0; j < 8; j++)
            {
                buttons[i][j] = new JButton();
                buttons[i][j].addActionListener(new buttonListener());
            }
        }
    }
    /**
     * Method that updates the board icons when called
     */
    public void updateBoard()
    {
        for(int i = 0; i < 8; i++)
        {
            for(int j = 0; j < 8; j++)
            {
                if(!(bob.board[i][j].getName().equals("dummy")))
                {
                if((bob.board[i][j].getName().equals("Pawn")) && (bob.board[i][j].getColor().equals("White")))
                {
                    ImageIcon pawnwhite = new ImageIcon("whitepawn.png");
                    buttons[i][j].setIcon(pawnwhite);
                }
                if((bob.board[i][j].getName().equals("Pawn")) && (bob.board[i][j].getColor().equals("Black")))
                {
                    ImageIcon pawnblack = new ImageIcon("blackpawn2.png");
                    buttons[i][j].setIcon(pawnblack);
                }
                if(bob.board[i][j].getName().equals("Rook") && bob.board[i][j].getColor().equals("White"))
                {
                    ImageIcon rookwhite = new ImageIcon("whiterook.png");
                    buttons[i][j].setIcon(rookwhite);
                }
                if(bob.board[i][j].getName().equals("Rook") && bob.board[i][j].getColor().equals("Black"))
                {
                    ImageIcon rookblack = new ImageIcon("blackrook.png");
                    buttons[i][j].setIcon(rookblack);
                }
                if(bob.board[i][j].getName().equals("Knight") && bob.board[i][j].getColor().equals("White"))
                {
                    ImageIcon knightwhite = new ImageIcon("whiteknight.png");
                    buttons[i][j].setIcon(knightwhite);
                }
                if(bob.board[i][j].getName().equals("Knight") && bob.board[i][j].getColor().equals("Black"))
                {
                    ImageIcon knightblack = new ImageIcon("blackknight.png");
                    buttons[i][j].setIcon(knightblack);
                }
                if(bob.board[i][j].getName().equals("Bishop") && bob.board[i][j].getColor().equals("White"))
                {
                    ImageIcon bishopwhite = new ImageIcon("whitebishop.png");
                    buttons[i][j].setIcon(bishopwhite);
                }
                if(bob.board[i][j].getName().equals("Bishop") && bob.board[i][j].getColor().equals("Black"))
                {
                    ImageIcon bishopblack = new ImageIcon("blackbishop.png");
                    buttons[i][j].setIcon(bishopblack);
                }
                if(bob.board[i][j].getName().equals("King") && bob.board[i][j].getColor().equals("White"))
                {
                    ImageIcon kingwhite = new ImageIcon("whiteking.png");
                    buttons[i][j].setIcon(kingwhite);
                }
                if(bob.board[i][j].getName().equals("King") && bob.board[i][j].getColor().equals("Black"))
                {
                    ImageIcon kingblack = new ImageIcon("blackking.png");
                    buttons[i][j].setIcon(kingblack);
                }
                if(bob.board[i][j].getName().equals("Queen") && bob.board[i][j].getColor().equals("White"))
                {
                    ImageIcon queenwhite = new ImageIcon("whitequeen.png");
                    buttons[i][j].setIcon(queenwhite);
                }
                if(bob.board[i][j].getName().equals("Queen") && bob.board[i][j].getColor().equals("Black"))
                {
                    ImageIcon queenblack = new ImageIcon("blackqueen.png");
                    buttons[i][j].setIcon(queenblack);
                }
                }
                else
                {
                    buttons[i][j].setIcon(null);
                }

            }
        }
    }
    
    /**
     * Sets the background colors of the JButtons to look like a chess board
     */
    public void setColors()
    {
        for(int i = 0; i < 8; i++)
        {
            for(int j = 0; j < 8; j++)
            {
                if(j%2 == 0)
                {
                    if(i%2 == 1)
                    {
                        buttons[i][j].setBackground(Color.darkGray);
                    }
                    else
                    {
                        buttons[i][j].setBackground(Color.WHITE);
                    }
                       
                }
                else
                {
                    if(j%2 == 1)
                    {
                        if(i%2 == 0)
                        {
                            buttons[i][j].setBackground(Color.darkGray);
                        }
                        else
                        {
                            buttons[i][j].setBackground(Color.WHITE);
                        }
                    }
                }
            }
        }
    }
    /**
     * button listener that is added to all buttons in the button 2d array
     */
    public class buttonListener implements ActionListener
    {
        JOptionPane bleh = new JOptionPane();
        
        public void actionPerformed(ActionEvent event)
        {
            JButton pressedButton = (JButton)event.getSource();
            for(int i = 0; i < 8; i++)
            {
                for(int j = 0; j < 8; j++)
                {
                    if(pressedButton == buttons[i][j])
                    {
                        //String that stores the name of the pressed button
                        String selNam= bob.board[i][j].getName();
                        //String that stores the color of the pressed button
                        String selCol= bob.board[i][j].getColor();
                        if(gotPiece == false)
                        {
                            if(selNam.equals("Pawn"))
                            {
                                if(bob.getMove()%2 == 1)
                                {
                                    if(selCol.equals("Black"))
                                    {
                                        bleh.showMessageDialog(null, "Invalid Piece Selected", "Message", JOptionPane.INFORMATION_MESSAGE);
                                    }
                                    else
                                    {
                                        
                                        bob.move(i,j);
                                        currPiece = selNam;
                                        gotPiece = true;
                                    }
                                }
                                else
                                {
                                    if(selCol.equals("White"))
                                    {
                                        bleh.showMessageDialog(null, "Invalid Piece Selected", "Message", JOptionPane.INFORMATION_MESSAGE);
                                    }
                                    else
                                    {
                                        bob.move(i,j);
                                        currPiece = selNam;
                                        gotPiece = true;
                                    }
                                }
                            }
                            if(selNam.equals("Rook"))
                            {
                                if(bob.getMove()%2 ==1)
                                {
                                    if(selCol.equals("Black"))
                                    {
                                        bleh.showMessageDialog(null, "Invalid Piece Selected", "Message", JOptionPane.INFORMATION_MESSAGE);
                                    }
                                    else
                                    {
                                        bob.move(i,j);
                                        currPiece = selNam;
                                        gotPiece = true;
                                    }
                                }
                                else
                                {
                                    if(selCol.equals("White"))
                                    {
                                        bleh.showMessageDialog(null, "Invalid Piece Selected", "Message", JOptionPane.INFORMATION_MESSAGE);
                                    }
                                    else
                                    {
                                        bob.move(i,j);
                                        currPiece = selNam;
                                        gotPiece = true;
                                    }
                                }
                            }
                            if(selNam.equals("Knight"))
                            {
                                if(bob.getMove()%2 == 1)
                                {
                                    if(selCol.equals("Black"))
                                    {
                                        bleh.showMessageDialog(null, "Invalid Piece Selected", "Message", JOptionPane.INFORMATION_MESSAGE);
                                    }
                                    else
                                    {
                                        bob.move(i,j);
                                        currPiece = selNam;
                                        gotPiece = true;
                                    }
                                }
                                else
                                {
                                    if(selCol.equals("White"))
                                    {
                                        bleh.showMessageDialog(null, "Invalid Piece Selected", "Message", JOptionPane.INFORMATION_MESSAGE);
                                    }
                                    else
                                    {
                                        bob.move(i,j);
                                        currPiece = selNam;
                                        gotPiece = true;
                                    }
                                }
                            }
                            if(selNam.equals("Bishop"))
                            {
                                if(bob.getMove()%2 == 1)
                                {
                                    if(selCol.equals("Black"))
                                    {
                                        bleh.showMessageDialog(null, "Invalid Piece Selected", "Message", JOptionPane.INFORMATION_MESSAGE);
                                    }
                                    else
                                    {
                                        bob.move(i,j);
                                        currPiece = selNam;
                                        gotPiece = true;
                                    }
                                }
                                else
                                {
                                    if(selCol.equals("White"))
                                    {
                                        bleh.showMessageDialog(null, "Invalid Piece Selected", "Message", JOptionPane.INFORMATION_MESSAGE);
                                    }
                                    else
                                    {
                                        bob.move(i,j);
                                        currPiece = selNam;
                                        gotPiece = true;
                                    }
                                }
                            }
                        }
                        else
                        {
                            //will be called if the event queue is not empty, which means the piece to be moved has been stored
                            if((bob.xyint.isEmpty()==false) && (gotPiece == true))
                            {
                                if(currPiece.equals("Pawn"))
                                {
                                    bob.nextPawnMove(i,j);
                                    gotPiece = false;
                                }
                                if(currPiece.equals("Rook"))
                                {
                                    bob.nextRookMove(i,j);
                                    gotPiece = false;
                                }
                                if(currPiece.equals("Knight"))
                                {
                                    bob.nextKnightMove(i,j);
                                    gotPiece = false;
                                }
                                if(currPiece.equals("Bishop"))
                                {
                                    bob.nextBishopMove(i,j);
                                    gotPiece = false;
                                }
                            }
                            updateBoard();
                        }
                    }
                }
            }
        }
    }
}
