package Spill;
import java.lang.reflect.Array;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;

/**
  * A Grid is a 2-dimensional array of Cells representing a surface 
  * <BR>DO NOT MODIFY THIS CLASS EXCEPT FOR SPILL METHOD
  * @author Sophie Quigley
  */
public class Grid3 {
    /**
     * Random number generator to create obstacles
     */
    static Random rand = new Random(); 
    /**
     * Number of rows in grid
     */
    int rows;
    /**
     * Number of columns in grid
     */
    int columns;
       /**
     * actual grid
     */
    Cell grid[][];
    
    



    /**
     * Returns a new Grid with specified rows and columns of EMPTY cells
     * <br>Note: there is no error checking of parameters
     * @param rows number of rows in new Grid
     * @param columns  number of rows in new Grid
     */
    Grid3 (int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        initGrid();
    }
    /**
     * Returns a new Grid with specified rows and columns 
     * of cells whose values are randomly generated
     * to be either CLEAR or contain an OBSTACLE
     * <br>Note: no error checking of parameters
     * @param rows number of rows in new Grid
     * @param columns  number of rows in new Grid
     * @param obstacles (0-100) is the percentage of obstacle cells in grid
     */
    Grid3 ( int rows, int columns, int obstacles) {
        int i, j;
        this.rows = rows;
        this.columns = columns;
        initGrid();
        for (i=0; i<rows; i++)
            for (j=0; j<columns; j++) {
                if (rand.nextInt(100) < obstacles)
                    grid[i][j].putObstacle();
            }
    }
    /**
     * Returns a new Grid whose rows and columns are read from the Scanner
     * <br>The format of the input is:
     * <br>rows, columns
     * <br>rows of Xs and 0s where
     * <br>X means obstacle and 0 means clear
     * <br>Note: no error checking of input.
     * @param in Scanner (standard input)
     */
    Grid3 (Scanner in) {
        rows = in.nextInt();
        columns = in.nextInt();
        initGrid();
        in.nextLine();
        for (int i=0; i<rows; i++) {
            String line = in.nextLine();
            for (int j=0; j<columns; j++) {
                if (line.charAt(j) == 'X')
                    grid[i][j].putObstacle();
            }
        }
    }
   
    /**
     * initializes a new rows by columns Grid containing CLEAR cells
     */   
    private void initGrid() {
        int i, j;
        grid = new Cell[rows][columns];
        for (i=0; i<rows; i++)
            for (j=0; j<columns; j++) 
                grid[i][j] = new Cell(i,j);  
    }
     
    /**
     * Returns a string describing the Grid
     * @return Contents of Grid
     */
    @Override
    public String toString() {
        String result = "";
        for (int i=0; i<rows; i++) {
            for (int j=0; j<columns; j++)
                result += grid[i][j];
            result +=  "\n";
        }
        return result;
    }
    
    /**
     * Getter for rows of Grid
     * @return The Grid's rows
     */
    public int getRows() {
        return rows;
    }
    
    /**
     * Getter for columns of Grid
     * @return The Grid's columns 
     */
    public int getColumns() {
        return columns;
    }
    
    /**
     * Getter for Grid cell at (row,col)
     * @param row row of cell
     * @param col column of cell
     * @return value of the cell at location (row,col) 
     */
    public Cell getCell(int row, int col) {
        if (isCell(row,col))
            return grid[row][col];
        else
            return null;
    }
   
    /**
     * Setter for the cell at (row,col) of the Grid
     * @param row row of cell
     * @param col column of cell
     * @param value to set the cell at location (row,col) 
     */
    public void setCell(int row, int col, int value) {
        if (isCell(row,col))
            grid[row][col].setValue(value);
    }

    /**
     * Puts an obstacle in the cell at (row,col) of the Grid
     * @param row row of cell
     * @param col column of cell
     */
    public void putObstacle(int row, int col) {
        if (isCell(row,col))
            grid[row][col].putObstacle();
    }

    /**
     * Check whether the cell at (row,col) contains an obstacle
     * @param row row of cell
     * @param col column of cell
     * @return true iff there is an obstacle in the cell (row,col)
     */
    public boolean isObstacle(int row, int col) {
        if (isCell(row,col))
            return grid[row][col].isObstacle();
        else
            return false;
    }

    /**
     * Check whether the cell at (row,col) is clear of obstacles and spills
     * @param row row of cell
     * @param col column of cell
     * @return true iff the cell at (row,col) is clear of obstacles and spills
     */
    public boolean isClear(int row, int col) {
    if (isCell(row,col))
        return grid[row][col].isClear();
    else
        return false;
    }

    /**
     * Check whether the coordinates correspond to a cell
     * @param row row of cell
     * @param col column of cell
     * @return true iff row and col are within grid boundaries
     */
    public boolean isCell(int row, int col) {
        return(row>=0 && col>=0 && row<rows && col<columns);
    }
    
    /**
     * Cleans up a grid after a spill.
     * The obstacles remain unchanged, 
     * and the other surfaces are cleared
     */
    public void CleanSpill() {
        int row, col;
        for (row=0; row<rows; row++)
            for (col=0; col<columns; col++)
                if (! grid[row][col].isObstacle())
                    grid[row][col].clear();
    }
    
    /**
     * THIS IS THE ONLY METHOD THAT SHOULD BE MODIFIED
     * Simulates a spill that spreads from cell (row,col) of the grid outwards
     * @param row row of cell receiving spill
     * @param col column of cell receiving spill
     * @param strength strength(concentration) of the spill
     */
 
  
 // Spill simulates the spread of a viscous spill on a surface at a single source
 // It is assumed that the input is correct
 // The surface is an m by n grid called surface
 // Each cell in this grid is initially CLEAR (0)
 // or contains an OBSTACLE (-1) which is resistant to the spill and hinders its spread
 // The spill starts at intensity strength and cell (x,y) of the grid
 // and diminishes in strength by 1 as it spills to adjacent cells
 // until it dissipates to nothing (0)
// // The spill doesn�t go past the boundaries of the surface and doesn�t bounce on anything.
// Global int m, n, surface[m,n]
// Initialise(surface) // fills surface with initial values of CLEAR or OBSTACLE
// Global Queue Q // This is a FIFO queue. Its elements are Points (x,y)
// // Elements are added to the back with Q.enqueue(x,y)
// // and removed from the front with Q.dequeue()
// Initialise (Q) // Q is initially empty
// 
// void Spill(int centerx, int centery, int originalstrength) {
// SpillInCell(centerx,centery,originalstrength)
// While Q is not empty {
// (x, y) = Q.dequeue()
// strength = surface(x,y)
// SpillInCell (x-1,y-1,strength-1)
// SpillInCell(x-1,y,strength-1)
// SpillInCell (x-1,y+1,strength-1)
// SpillInCell (x,y-1,strength-1)
// SpillInCell (x,y+1,strength-1)
// SpillInCell (x+1,y-1,strength-1)
// SpillInCell (x+1,y,strength-1)
// SpillInCell (x+1,y+1,strength-1)
// } }
// void SpillInCell(int x, int y, int strength) {
// if strength = 0 return
// If x<0 or x>=m or y<0 or y>=n return
// Int cell = surface(x,y)
// if cell is an OBSTACLE return
// If cell >= strength return
// surface(x,y) = strength
// Q.enqueue(x,y)
// }
 
    // This is a FIFO queue. Its elements are Points (x,y)
    // Elements are added to the back with Q.enqueue(x,y)
    // and removed from the front with Q.dequeue()
    
    Deque<Cell> Q = new ArrayDeque<Cell>();
    
    void SpillInCell(int row, int col, int strength) {
    	
    	if (strength  == 0) { 
    		return; 
    	}
    	
    	if (row < 0 || row >= rows || col < 0 || col >= columns ) {
    		return;
    	}
    	
    	Cell cell = grid[row][col];
    	
    	if (cell.isObstacle()){
			return;
		}

    	if (cell.value >= strength){
    		return;
    	}
    		
    	grid[row][col].setValue(strength);
    	Q.add(grid[row][col]);
    	  
    	
    }
    	

    public void Spill (int centerx, int centery, int originalstrength) {
    
    	SpillInCell(centerx, centery, originalstrength);
    	
   
    	while (Q.size() != 0) {
			Cell cell = Q.remove();
			int row = cell.getRow();
			int col = cell.getCol();
			int strength = cell.value;
			SpillInCell(row-1,col-1,strength-1);
			SpillInCell(row-1,col,strength-1);
			SpillInCell(row-1,col+1,strength-1);
			SpillInCell(row,col-1,strength-1);
			SpillInCell(row,col+1,strength-1);
			SpillInCell(row+1,col-1,strength-1);
			SpillInCell(row+1,col,strength-1);
			SpillInCell(row+1,col+1,strength-1);
    	}
    	
 
    	
    }
    

    	

}
    