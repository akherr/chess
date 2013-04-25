
/**
 * ChessPiece class which represents a chess piece
 * 
 * @author Austin Herr
 * @version 1.0
 */
public class ChessPiece
{
    private String name;
    private String color;
    private int xco;
    private int yco;
    
    /**
     * creates a chess piece if all parameters are met
     * @param String n the name of the piece
     * @param String c the color of the piece
     * @param int x the x position of the piece
     * @param int y the y position of the piece
     */
    public ChessPiece(String n, String c, int x, int y)
    {
        name = n;
        color = c;
    }
    
    /**
     * Creates a dummy chess piece if no parameters are present
     */
    public ChessPiece()
    {
        name = "dummy";
        color = "dummy";
        
    }
    /**
     * method which returns the name of the piece
     * @return String the name
     */
    public String getName()
    {
        return name;
    }
    /**
     * method which returns the color of the piece
     * @return String the color
     */
    public String getColor()
    {
        return color;
    }
    /**
     * method which returns the x position of the piece
     * @return int the x position
     */
    public int getX()
    {
        return xco;
    }
    /**
     * method which returns the y position of the piece
     * @return int the y position
     */
    public int getY()
    {
        return yco;
    }
    
}
