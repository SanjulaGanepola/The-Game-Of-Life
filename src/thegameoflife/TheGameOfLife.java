/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thegameoflife;

/**
 * Create and update an array to simulate The Game of Life. Update the entire
 * array after checking a cell's neighbours to determine which cells should live
 * or die. The global variables include: the new and original array to hold
 * whether each cell is alive or dead.
 *
 * @author 348676487
 */
public class TheGameOfLife {

    //Create an original array of cells and a editing board array of cells
    int[][] newCells = new int[20][20];
    int[][] originalCells = new int[20][20];

    /**
     * @param args the command line arguments
     */
    public void main(String[] args) {
        // TODO code application logic here
    }

    public TheGameOfLife() {
    }

    /**
     * Check whether a cell should be alive or dead based on the number of
     * neighbours it has. Update the original cells array and the new cells
     * array based on all the cells.
     */
    public void checkSquares() {
        //Update the new cells with the old cells
        for (int r = 0; r <= originalCells.length - 1; r++) {
            for (int c = 0; c <= originalCells.length - 1; c++) {
                newCells[r][c] = originalCells[r][c];
            }
        }

        //Loop through each original cell in the original array
        for (int r = 0; r < originalCells.length; r++) {
            for (int c = 0; c < originalCells.length; c++) {

                //Rest the number of neighbours a square has
                int neighbours = 0;

                //Loop through the cell's neighbours
                for (int x = -1; x < 2; x++) {
                    for (int y = -1; y < 2; y++) {
                        try {
                            //Add to the neighbour countering when the cell is alive
                            if (originalCells[r + x][c + y] == 1) {
                                neighbours++;
                            }
                        } catch (ArrayIndexOutOfBoundsException e) {
                        }
                    }
                }

                //Populated cell
                if (originalCells[r][c] == 1) {
                    neighbours -= 1;
                    //Cell dies
                    if (neighbours <= 1 || neighbours >= 4) {
                        newCells[r][c] = 0;
                    }
                    //Cell lives
                    if (neighbours == 2 || neighbours == 3) {
                        newCells[r][c] = 1;
                    }
                } //Unpopulated cell
                else {
                    //Cell comes to life
                    if (neighbours == 3) {
                        newCells[r][c] = 1;
                    }
                }
            }
        }

        //Update the original cells with the new cells after checking neighbours
        for (int r = 0; r <= originalCells.length - 1; r++) {
            for (int c = 0; c <= originalCells.length - 1; c++) {
                originalCells[r][c] = newCells[r][c];
            }
        }
    }

}
