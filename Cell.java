package Spill;

/**
 * a Cell is an element of a Grid
 * <BR>DO NOT MODIFY THIS CLASS
 * @author Sophie Quigley
 */
public class Cell {
     /**
     * value of clear (empty) cell
     */   
    static int CLEAR = 0;
     /**
     * value of cell containing an obstacle
     */   
    static int OBSTACLE = -1;
    /**
     * row location of cell in its grid
     */
    int row;
    /**
     * column location of cell in its grid
     */
    int col;
    /**
     * cell value
     */
    int value;
    /**
     * Returns a new CLEAR cell at specified row and column of its grid
     * @param row row of cell in its Grid
     * @param col column of cell in its Grid
     */
    Cell (int row, int col) {
        this.row = row;
        this.col = col;
        value = CLEAR;
    }
    /**
     * Returns a string describing the Cell
     * @return value of Cell or "X" if Cell contains an obstacle
     */
    @Override
    public String toString() {
        if (value==OBSTACLE)    return "X";
        else    return Integer.toString(value);
    }
    /**
     * Getter for cell row
     * @return row of the cell 
     */
    public int getRow() {
        return row;
    }
    /**
     * Getter for cell column
     * @return column of the cell 
     */
    public int getCol() {
        return col;
    }
    /**
     * Getter for cell value
     * @return value of the cell 
     */
    public int getValue() {
        return value;
    }
    /**
     * Setter for cell value
     * @param value of the cell 
     */
    public void setValue(int value) {
        this.value = value;
    }
     /**
     * Puts an obstacle in the cell
     */
    public void putObstacle() {
        value = OBSTACLE;
    }
    /**
     * Clears a cell
     */
    public void clear() {
        value = CLEAR;
    }
    /**
     * Check whether the cell contains an obstacle
     * @return true iff the cell contains an obstacle
     */
    public boolean isObstacle() {
        return( value == OBSTACLE);
    }

    /**
     * Check whether the cell is clear of obstacles and spills
     * @return true iff the cell is clear of obstacles and spills
     */
    public boolean isClear() {
        return( value == CLEAR);
    }


}