/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thegameoflife;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.Timer;

/**
 * Draw rectangles to the screen to appear as a grid showing dead and alive
 * cells. Animate the cells by starting, stopping, and clearing the boards. Draw
 * well known default patterns to the screen as well. The global variables
 * include: TheGameOfLife class that has the engine for the game, the size of
 * the cell array, the square size of the cell, and the timer for the animation.
 *
 * @author 348676487
 */
public class DrawingArea1 extends javax.swing.JPanel implements MouseListener {

    //TheGameOfLife class used for initializing array and checking neighbours
    TheGameOfLife game = new TheGameOfLife();
    //Set the size of the board
    int size = 20;
    //To hold the dimension of a square on the grid
    int squareSize = 0;
    //Initialize the timer
    Timer t1 = new Timer(50, new TimerListener());

    /**
     * Creates new form GamePanel
     */
    public DrawingArea1() {
        initComponents();
        addMouseListener(this);
    }

    /**
     * Look through the orignal array of cells and output rectangles with
     * dimensions called at the start only once. A empty rectangle is a dead
     * cell. A filled rectangle is a living cell.
     *
     * @param g The Graphics class to allow an application to draw onto
     * components
     */
    public void paintComponent(Graphics g) {
        //Set the dimension of each square at the start
        if (squareSize == 0) {
            squareSize = this.getWidth() / size;
        }
        super.paintComponent(g);
        //Set the color for the living squares to be black
        g.setColor(Color.BLACK);

        int xArrayPosition = -1;
        //Loop through each cell in the original array to determine what type of rectangle to output
        for (int x = 0; x < this.getWidth() - 1; x += squareSize) {
            //Add to the row index of the array
            xArrayPosition++;
            int yArrayPosition = -1;
            for (int y = 0; y < this.getHeight() - 1; y += squareSize) {
                //Add to the column index of the array
                yArrayPosition++;
                //Draw a empty rectangle if the cell is dead
                if (game.originalCells[xArrayPosition][yArrayPosition] == 0) {
                    g.drawRect(x, y, squareSize, squareSize);

                } //Draw a filled rectangle if the cell is alive
                else {
                    g.fillRect(x, y, squareSize, squareSize);
                }
            }
        }
    }

    /**
     * Start the timer to start the animation.
     */
    public void start() {
        t1.start();
    }

    /**
     * Stop the timer to stop the animation.
     */
    public void stop() {
        t1.stop();
    }

    /**
     * Clear and update the board by filling the original array cells with dead
     * cells.
     */
    public void clearboard() {
        //Stop the timer
        t1.stop();
        //Clear the board
        for (int x = 0; x < game.originalCells.length; x++) {
            for (int y = 0; y < game.originalCells.length; y++) {
                game.originalCells[x][y] = 0;
            }
        }
        repaint();
    }

    public void nextStep() {
        game.checkSquares();
        repaint();
    }

    /**
     * Update and output the array to the screen with set patterns.
     *
     * @param pattern The pattern chosen from the choices in the combo box
     */
    public void pattern(String pattern) {
        //Clear the board
        clearboard();

        //Set the Glider pattern
        if (pattern.equals("Glider")) {
            game.originalCells[9][10] = 1;
            game.originalCells[10][10] = 1;
            game.originalCells[11][10] = 1;
            game.originalCells[11][9] = 1;
            game.originalCells[10][8] = 1;
        } //Set the exploder pattern
        else if (pattern.equals("Exploder")) {
            game.originalCells[8][7] = 1;
            game.originalCells[8][8] = 1;
            game.originalCells[8][9] = 1;
            game.originalCells[8][10] = 1;
            game.originalCells[8][11] = 1;

            game.originalCells[10][7] = 1;
            game.originalCells[10][11] = 1;

            game.originalCells[12][7] = 1;
            game.originalCells[12][8] = 1;
            game.originalCells[12][9] = 1;
            game.originalCells[12][10] = 1;
            game.originalCells[12][11] = 1;
        } //Set the 3 Line Cell pattern
        else if (pattern.equals("3 Line Cell")) {
            game.originalCells[10][8] = 1;
            game.originalCells[10][9] = 1;
            game.originalCells[10][10] = 1;
        }
        repaint();
    }

    /**
     * Timer for running the animation. Update the screen after calling the
     * neighbour method.
     */
    private class TimerListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            game.checkSquares();
            repaint();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    /**
     * Determine where a mouse was clicked on the screen. Determine which cell
     * it was and update it. If it was alive, it becomes dead. If it is dead, it
     * becomes alive.
     *
     * @param e An event that indicates a mouse action occurred in a component.
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX() / squareSize;
        int y = e.getY() / squareSize;
        if (game.originalCells[x][y] == 1) {
            game.originalCells[x][y] = 0;
        } else {
            game.originalCells[x][y] = 1;
        }
        repaint();
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    }
}
