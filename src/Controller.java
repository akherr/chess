
/**
 * Controller class for the chess game
 * 
 * @author Austin Herr 
 * @version 1.0
 */
public class Controller
{
    //2d array of ChessPieces
    ChessPiece[][] board = new ChessPiece[8][8];
    //queue to be used to store piece positions
    CircularArrayQueue<Integer> xyint = new CircularArrayQueue<Integer>();
    //if move is odd, move is white, if move is even, move is black
    int move = 1;
    
    /**
     * Controller method that calls the initialize method
     */
    public Controller()
    {
        init();
    }
    
    /**
     * Method that initializes board pieces to the beginning positions when the game starts
     */
    public void init()
    {
        for(int i = 0; i < 8; i++)
        {
            for(int j = 0; j < 8; j++)
            {
                if(!(j > 1 && j < 6))
                {
                if((i == 0 && j == 0 )||(i == 7 && j == 0))
                {
                    board[i][j] = new ChessPiece("Rook", "Black", i, j);
                }
                if((i == 1 && j == 0)||(i == 6 && j == 0))
                {
                    board[i][j] = new ChessPiece("Knight", "Black", i, j);
                }
                if((i == 2 && j == 0)||(i == 5 && j == 0))
                {
                    board[i][j] = new ChessPiece("Bishop", "Black", i, j);
                }
                if(i == 3 && j == 0)
                {
                    board[i][j] = new ChessPiece("King", "Black", i, j);
                }
                if(i == 4 && j == 0)
                {
                    board[i][j] = new ChessPiece("Queen", "Black", i, j);
                }
                if((i == 0 && j == 1)||(i == 1 && j == 1)||(i == 2 && j == 1)||(i == 3 && j == 1)||(i == 4 && j == 1)||(i == 5 && j == 1)||(i == 6 && j == 1)||(i == 7 && j == 1))
                {
                    board[i][j] = new ChessPiece("Pawn", "Black", i, j); 
                }
                if((i == 0 && j == 7)||(i == 7 && j == 7))
                {
                    board[i][j] = new ChessPiece("Rook", "White", i, j);
                }
                if((i == 1 && j == 7)||(i == 6 && j == 7))
                {
                    board[i][j] = new ChessPiece("Knight", "White", i, j);
                }
                if((i == 2 && j == 7)||(i == 5 && j == 7))
                {
                    board[i][j] = new ChessPiece("Bishop", "White", i, j);
                }
                if(i == 3 && j == 7)
                {
                    board[i][j] = new ChessPiece("Queen", "White", i, j);
                }
                if(i == 4 && j == 7)
                {
                    board[i][j] = new ChessPiece("King", "White", i, j);
                }
                if((i == 0 && j == 6)||(i == 1 && j == 6)||(i == 2 && j == 6)||(i == 3 && j == 6)||(i == 4 && j == 6)||(i == 5 && j == 6)||(i == 6 && j == 6)||(i == 7 && j == 6))
                {
                    board[i][j] = new ChessPiece("Pawn", "White", i, j);
                }
                } 
                else
                {
                    board[i][j] = new ChessPiece();
                }
            }
        }
    }
    
    /**
     * Move method that is called from ChessGUI to store the position of the clicked piece
     * @param int x the x position of the pressed button
     * @param int y the y position of the pressed button
     */
    public void move(int x, int y)
    {
        //enqueues the x and y position to be used later
        xyint.enqueue(x);
        xyint.enqueue(y);
    }
    
    /**
     * Method that is called from ChessGUI to check if the pressed position is valid
     * @param int x the x position of the pressed button
     * @param int y the y position of the pressed button
     */
    public void nextPawnMove(int x, int y)
    {
        boolean done = false;
        //x coordinate from the clicked piece that is to be moved
        int xco = xyint.dequeue();
        //y coordinate from the clicked piece that is to be moved
        int yco = xyint.dequeue();
        //to be called if the current move is black's
        if(move%2 == 0 && (!(board[x][y].getColor().equals("Black"))))
        {
            if(yco == 1 && (!(y == 0)))
            {
                if(((y - yco) <=2) && (xco - x == 0) && (board[x][yco + 1].getName().equals("dummy")))
                {
                    if(board[x][y].getColor().equals("White") && (y - yco == 1) && (xco - x == 1))
                    {
                        board[xco][yco] = new ChessPiece();
                        board[x][y] = new ChessPiece("Pawn", "Black", x, y);
                        done = true;
                    }
                    else
                    {
                        board[xco][yco] = new ChessPiece();
                        board[x][y] = new ChessPiece("Pawn", "Black", x, y);
                        done = true;
                    }
                }
            }
            else
            {
                if(((y - yco) == 1) && (xco - x == 0))
                {
                    if(board[x][y].getName().equals("dummy"))
                    {
                        board[xco][yco] = new ChessPiece();
                        board[x][y] = new ChessPiece("Pawn", "Black", x, y);
                        done = true;
                    }      
                }
                if(((y - yco) == 1) && ((xco - x == -1) || (xco -x == 1)))
                {
                    if(board[x][y].getColor().equals("White"))
                    {
                        board[xco][yco] = new ChessPiece();
                        board[x][y] = new ChessPiece("Pawn", "Black", x, y);
                        done = true;
                    }
                }
            }
        }
        //to be called if the current move is white's
        if(move%2 == 1 && (!(board[x][y].getColor().equals("White"))))
        {
            if(yco == 6 && (!(y == 7)))
            {
                if(((yco - y) <=2) && (xco - x == 0) && (board[x][yco - 1].getName().equals("dummy")))
                {
                    if(board[x][y].getColor().equals("Black") && (yco - y == 1) && (xco - x == 1))
                    {
                        board[xco][yco] = new ChessPiece();
                        board[x][y] = new ChessPiece("Pawn", "White", x, y);
                        done = true;
                    }
                    else
                    {
                        board[xco][yco] = new ChessPiece();
                        board[x][y] = new ChessPiece("Pawn", "White", x, y);
                        done = true;
                    }
                }
            }
            else
            {
                if(((yco - y) == 1) && (xco - x == 0))
                {
                    if(board[x][y].getName().equals("dummy"))
                    {
                        board[xco][yco] = new ChessPiece();
                        board[x][y] = new ChessPiece("Pawn", "White", x, y);
                        done = true;
                    }        
                }
                if(((yco - y) == 1) && ((xco - x == -1) || (xco -x == 1)))
                {
                    if(board[x][y].getColor().equals("Black"))
                    {
                        board[xco][yco] = new ChessPiece();
                        board[x][y] = new ChessPiece("Pawn", "White", x, y);
                        done = true;
                    }
                }
            }
        }
        //if a move has been made, increases move
        if(done == true)
        {
            move++;
        }
    }
    
    /**
     * Method to be called from ChessGUI to check if the pressed position is valid
     * @param int x the x position of the pressed button
     * @param int y the y position of the pressed button
     */
    public void nextRookMove(int x, int y)
    {
        boolean done = false;
        boolean collision = false;
        int xco = xyint.dequeue();
        int yco = xyint.dequeue();
        if(move%2 == 1)
        {
            if(board[x][y].getColor().equals("White"))
            {
                collision = true;
            }
            if(collision == false && done == false && (yco > y))
            {
                if(!(xco - x == 0))
                {
                    collision = true;
                }
                for(int i = yco; i > y; i--)
                {
                    if(board[xco][i-1].getColor().equals("White"))
                    {
                        collision = true;
                    }
                }
                for(int i = yco-1; i>y; i--)
                {
                    if(board[xco][i].getColor().equals("Black"))
                    {
                        collision = true;
                    }
                }
            }
            if(collision == false && done == false && (yco < y))
            {
                if(!(xco - x == 0))
                {
                    collision = true;
                }
                for(int i = yco; i < y; i++)
                {
                    if(board[xco][i+1].getColor().equals("White"))
                    {
                        collision = true;
                    }
                }
                for(int i = yco+1; i <y; i++)
                {
                    if(board[xco][i].getColor().equals("Black"))
                    {
                        collision = true;
                    }
                }
            }
            if(collision == false && done == false && (xco < x))
            {
                if(!(yco - y == 0))
                {
                    collision = true;
                }
                for(int i = xco; i < x; i++)
                {
                    if(board[i+1][yco].getColor().equals("White"))
                    {
                        collision = true;
                    }
                }
                for(int i = xco+1; i<x; i++)
                {
                    if(board[i][yco].getColor().equals("Black"))
                    {
                        collision = true;
                    }
                }
            }
            if(collision == false && done == false && (xco > x))
            {
                if(!(yco - y == 0))
                {
                    collision = true;
                }
                for(int i = xco; i > x; i--)
                {
                    if(board[i-1][yco].getColor().equals("White"))
                    {
                        collision = true;
                    }
                }
                for(int i = xco-1; i>x; i--)
                {
                    if(board[i][yco].getColor().equals("Black"))
                    {
                        collision = true;
                    }
                }
            }
        }
        if(move%2 == 0)
        {
            if(board[x][y].getColor().equals("Black"))
            {
                collision = true;
            }
            if(collision == false && done == false && (yco > y))
            {
                if(!(xco - x == 0))
                {
                    collision = true;
                }
                for(int i = yco; i > y; i--)
                {
                    if(board[xco][i-1].getColor().equals("Black"))
                    {
                        collision = true;
                    }
                }
                for(int i = yco-1; i>y; i--)
                {
                    if(board[xco][i].getColor().equals("White"))
                    {
                        collision = true;
                    }
                }
            }
            if(collision == false && done == false && (yco < y))
            {
                if(!(xco - x == 0))
                {
                    collision = true;
                }
                for(int i = yco; i < y; i++)
                {
                    if(board[xco][i+1].getColor().equals("Black"))
                    {
                        collision = true;
                    }
                }
                for(int i = yco+1; i <y; i++)
                {
                    if(board[xco][i].getColor().equals("White"))
                    {
                        collision = true;
                    }
                }
            }
            if(collision == false && done == false && (xco < x))
            {
                if(!(yco - y == 0))
                {
                    collision = true;
                }
                for(int i = xco; i < x; i++)
                {
                    if(board[i+1][yco].getColor().equals("Black"))
                    {
                        collision = true;
                    }
                }
                for(int i = xco+1; i<x; i++)
                {
                    if(board[i][yco].getColor().equals("White"))
                    {
                        collision = true;
                    }
                }
            }
            if(collision == false && done == false && (xco > x))
            {
                if(!(yco - y == 0))
                {
                    collision = true;
                }
                for(int i = xco; i > x; i--)
                {
                    if(board[i-1][yco].getColor().equals("Black"))
                    {
                        collision = true;
                    }
                }
                for(int i = xco-1; i>x; i--)
                {
                    if(board[i][yco].getColor().equals("White"))
                    {
                        collision = true;
                    }
                }
            }
        }
        //places a white rook if the move is valid and sets the previous position to default
        if(collision == false && move%2 == 1)
        {
            board[xco][yco] = new ChessPiece();
            board[x][y] = new ChessPiece("Rook","White",x,y);
            done = true;
        }
        //places a black rook if the move is valid and sets the previous position to default
        if(collision == false && move%2 == 0)
        {
            board[xco][yco] = new ChessPiece();
            board[x][y] = new ChessPiece("Rook","Black",x,y);
            done = true;
        }
        //increments the move value if a move was made
        if(done == true)
        {
            move++;
        }
    }
    
    /**
     * Method to be called from ChessGUI to check if the pressed position is valid
     * @param int x the x position of the pressed button
     * @param int y the y position of the pressed button
     */
    public void nextKnightMove(int x, int y)
    {
        boolean done = false;
        boolean collision = false;
        boolean initialCheck = false;
        int xco = xyint.dequeue();
        int yco = xyint.dequeue();
        if(((xco-x == 1) && (yco-y == 2))||((xco-x == 2) && (yco-y == 1))||((x-xco == -1) && (y-yco == 2))||((x-xco == -2) && (y-yco == 1))||((x-xco == 2) && (y-yco == 1))||((x-xco == 1) && (y-yco == 2))||((x-xco == 2) && (y - yco == -1))||((x-xco == 1) && (y - yco == -2)))
        {
            initialCheck = true;
        }
        else
        {
            collision = true;
        }
        if(move%2 == 1 && initialCheck == true)
        {
            if((xco-x == 1) && (yco-y == 2))
            {
                if(board[x][y].getColor().equals("White"))
                {
                    collision = true;
                }
            }
            if((xco-x == 2) && (yco-y == 1))
            {
                if(board[x][y].getColor().equals("White"))
                {
                    collision = true;
                }
            }
            if((x-xco == -1) && (y-yco == 2))
            {
                if(board[x][y].getColor().equals("White"))
                {
                    collision = true;
                }
            }
            if((x-xco == -2) && (y-yco == 1))
            {
                if(board[x][y].getColor().equals("White"))
                {
                    collision = true;
                }
            }
            if((x-xco == 2) && (y-yco == 1))
            {
                if(board[x][y].getColor().equals("White"))
                {
                    collision = true;
                }
            }
            if((x-xco == 1) && (y-yco == 2))
            {
                if(board[x][y].getColor().equals("White"))
                {
                    collision = true;
                }
            }
            if((x-xco == 2) && (y - yco == -1))
            {
                if(board[x][y].getColor().equals("White"))
                {
                    collision = true;
                }
            }
            if((x-xco == 1) && (y - yco == -2))
            {
                if(board[x][y].getColor().equals("White"))
                {
                    collision = true;
                }
            }
                    
        }
        if(move%2 == 0 && initialCheck == true)
        {
            if((xco-x == 1) && (yco-y == 2))
            {
                if(board[x][y].getColor().equals("Black"))
                {
                    collision = true;
                }
            }
            if((xco-x == 2) && (yco-y == 1))
            {
                if(board[x][y].getColor().equals("Black"))
                {
                    collision = true;
                }
            }
            if((x-xco == -1) && (y-yco == 2))
            {
                if(board[x][y].getColor().equals("Black"))
                {
                    collision = true;
                }
            }
            if((x-xco == -2) && (y-yco == 1))
            {
                if(board[x][y].getColor().equals("Black"))
                {
                    collision = true;
                }
            }
            if((x-xco == 2) && (y-yco == 1))
            {
                if(board[x][y].getColor().equals("Black"))
                {
                    collision = true;
                }
            }
            if((x-xco == 1) && (y-yco == 2))
            {
                if(board[x][y].getColor().equals("Black"))
                {
                    collision = true;
                }
            }
            if((x-xco == 2) && (y - yco == -1))
            {
                if(board[x][y].getColor().equals("Black"))
                {
                    collision = true;
                }
            }
            if((x-xco == 1) && (y - yco == -2))
            {
                if(board[x][y].getColor().equals("Black"))
                {
                    collision = true;
                }
            }
            
        }
        if(collision == false && move%2 == 1)
        {
            board[xco][yco] = new ChessPiece();
            board[x][y] = new ChessPiece("Knight","White",x,y);
            done = true;
        }
        if(collision == false && move%2 == 0)
        {
            board[xco][yco] = new ChessPiece();
            board[x][y] = new ChessPiece("Knight","Black",x,y);
            done = true;
        }
        if(done == true)
        {
            move++;
        }
    }
    
     /**
     * Method to be called from ChessGUI to check if the pressed position is valid
     * @param int x the x position of the pressed button
     * @param int y the y position of the pressed button
     */
    public void nextBishopMove(int x, int y)
    {
        boolean done = false;
        boolean collision = false;
        boolean initialCheck = false;
        int xco = xyint.dequeue();
        int yco = xyint.dequeue();
        if(xco == x && yco == y)
        {
            collision = true;
        }
        for(int i = 0; i < 4; i++)
        {
            if(i == 0)
            {
                if(((x - xco) == (y-yco)))
                {
                    initialCheck = true;
                }
            }
            if(i == 1)
            {
                
                if(((yco - x) == (y - xco)))
                {
                    initialCheck = true;
                }
                
            }
            if(i == 2)
            {
                
                if(((xco - x) == (yco - y)))
                {
                    initialCheck = true;
                }
            }
            if(i == 3)
            {
                if(((x-yco) == (xco-y)))
                {
                    initialCheck = true;
                }
            }
            else
            {
            }
        }
        if(initialCheck == false)
        {
            collision = true;
        }
        if(move%2 == 1 && initialCheck == true)
        {
            if((x-xco >= 1) && (y-yco >= 1))
            {
                int count =1;
                for(int i = xco; i < x; i++)
                {
                    if(board[i+1][yco+count].getColor().equals("White"))
                    {
                        collision = true;
                    }
                    count++;
                }
                count = 1;
                for(int i = xco+1; i<x; i++)
                {
                    if(board[i][yco+count].getColor().equals("Black"))
                    {
                        collision = true;
                    }
                    count++;
                }
            }
            if((x-xco >= 1) && (y-yco <=-1))
            {
                int count = 1;
                for(int i = xco; i < x; i++)
                {
                    if(board[i+1][yco-count].getColor().equals("White"))
                    {
                        collision = true;
                    }
                    count++;
                }
                count = 1;
                for(int i = xco+1; i<x; i++)
                {
                    if(board[i][yco-count].getColor().equals("Black"))
                    {
                        collision = true;
                    }
                    count++;
                }
            }
            if((yco-y >=1) && (xco-x >=1))
            {
                int count = 1;
                for(int i = xco; i > x; i--)
                {
                    if(board[i-1][yco-count].getColor().equals("White"))
                    {
                        collision = true;
                    }
                    count++;
                }
                count = 1;
                for(int i = xco-1; i>x; i--)
                {
                    if(board[i][yco-count].getColor().equals("Black"))
                    {
                        collision = true;
                    }
                    count++;
                }
            }
            if((y-yco >=1) && (xco-x >=1))
            {
                int count = 1;
                for(int i = xco; i > x ; i--)
                {
                    if(board[i-1][yco+count].getColor().equals("White"))
                    {
                        collision = true;
                    }
                    count++;
                }
                count = 1;
                for(int i = xco-1; i>x; i--)
                {
                    if(board[i][yco+count].getColor().equals("Black"))
                    {
                        collision = true;
                    }
                    count++;
                }
            }
        }
        if(move%2 == 0 && initialCheck == true)
        {
            if((x-xco >= 1) && (y-yco >= 1))
            {
                int count =1;
                for(int i = xco; i < x; i++)
                {
                    if(board[i+1][yco+count].getColor().equals("Black"))
                    {
                        collision = true;
                    }
                    count++;
                }
                count = 1;
                for(int i = xco+1; i<x; i++)
                {
                    if(board[i][yco+count].getColor().equals("White"))
                    {
                        collision = true;
                    }
                    count++;
                }
            }
            if((x-xco >= 1) && (y-yco <=-1))
            {
                int count = 1;
                for(int i = xco; i < x; i++)
                {
                    if(board[i+1][yco-count].getColor().equals("Black"))
                    {
                        collision = true;
                    }
                    count++;
                }
                count = 1;
                for(int i = xco+1; i<x; i++)
                {
                    if(board[i][yco-count].getColor().equals("White"))
                    {
                        collision = true;
                    }
                    count++;
                }
            }
            if((yco-y >=1) && (xco-x >=1))
            {
                int count = 1;
                for(int i = xco; i > x; i--)
                {
                    if(board[i-1][yco-count].getColor().equals("Black"))
                    {
                        collision = true;
                    }
                    count++;
                }
                count = 1;
                for(int i = xco-1; i>x; i--)
                {
                    if(board[i][yco-count].getColor().equals("White"))
                    {
                        collision = true;
                    }
                    count++;
                }
            }
            if((y-yco >=1) && (xco-x >=1))
            {
                int count = 1;
                for(int i = xco; i > x ; i--)
                {
                    if(board[i-1][yco+count].getColor().equals("Black"))
                    {
                        collision = true;
                    }
                    count++;
                }
                count = 1;
                for(int i = xco-1; i>x; i--)
                {
                    if(board[i][yco+count].getColor().equals("White"))
                    {
                        collision = true;
                    }
                    count++;
                }
            }
        }
        if(collision == false && move%2 == 1)
        {
            board[xco][yco] = new ChessPiece();
            board[x][y] = new ChessPiece("Bishop","White",x,y);
            done = true;
        }
        if(collision == false && move%2 == 0)
        {
            board[xco][yco] = new ChessPiece();
            board[x][y] = new ChessPiece("Bishop","Black",x,y);
            done = true;
        }
        if(done == true)
        {
            move++;
        }
    }
    
    /**
     * Method to be called to ChessGUI to check if the position is valid
     * @param int x the x position of the pressed position
     * @param int y the y position of the pressed position
     */
    public void nextKingMove(int x, int y)
    {
        boolean done = false;
        boolean collision = false;
        int xco = xyint.dequeue();
        int yco = xyint.dequeue();
        
        
        if(collision == false && move%2 == 1)
        {
            board[xco][yco] = new ChessPiece();
            board[x][y] = new ChessPiece("Bishop","White",x,y);
            done = true;
        }
        if(collision == false && move%2 == 0)
        {
            board[xco][yco] = new ChessPiece();
            board[x][y] = new ChessPiece("Bishop","Black",x,y);
            done = true;
        }
        if(done == true)
        {
            move++;
        }
    }
    /**
     * returns the int representation of the move attribute
     * @return int
     */
    public int getMove()
    {
        return move;
    }
}
