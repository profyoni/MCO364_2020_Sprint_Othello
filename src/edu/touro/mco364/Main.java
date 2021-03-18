package edu.touro.mco364;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class OthelloGUI extends JFrame
{
    private OthelloModel model;
    private JButton[][] buttons;
    private JLabel statusBar = new JLabel(" ");
    private JButton computerMoveButton = new JButton("Computer Go!");
    OthelloGUI(OthelloModel model){
        this.model = model;
        setTitle("Othello");
        super.setSize(600,620);

        this.setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
        int size = model.GRID_SIZE;

        JPanel panel = new JPanel();
        this.add(panel);
        JPanel panel2 = new JPanel();
        panel2.setLayout(new BorderLayout());
        this.add(panel2, BorderLayout.SOUTH);
        panel2.add(statusBar, BorderLayout.CENTER);
        panel2.add(computerMoveButton, BorderLayout.EAST);
        computerMoveButton.addActionListener( ae -> {
            Point move = model.findBestMove();;
            CellState player = model.getCurrentPlayer();
            int flips = model.makeMove(move.x, move.y, player, true);
            updateGuiBoard();
            statusBar.setText(String.format("%s went %s and flipped %d pieces", player, move, flips));
        });
        panel.setLayout(new GridLayout(size,size));
        ActionListener al = new ButtonListener();
        buttons = new MyJButton[size][size];
        Font f = new Font("", Font.BOLD, 40);
        for (int i=0;i<size;i++)
        for (int j=0;j<size;j++)
        {
            panel.add( buttons[i][j] = new MyJButton(new Point(i,j)) );
            buttons[i][j].addActionListener(al);
            buttons[i][j].setFont(f);
            buttons[i][j].setBackground(new Color(0,145,10));
        }
        updateGuiBoard();
        setVisible(true);
    }

    private int q;
    void updateGuiBoard()
    {
        int size = model.GRID_SIZE;
        for (int i=0;i<size;i++)
        for (int j=0;j<size;j++) {
            CellState c = model.getCellState(i,j);
            buttons[i][j].setText(c.toIcon());
        }
    }

    private class ButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            Point move;
            CellState player = model.getCurrentPlayer();
            if ( player== CellState.WHITE) {
                MyJButton b = (MyJButton) e.getSource();
                move = b.p;
            }
            else{
                move = model.findBestMove();
            }
            int flips = model.makeMove(move.x, move.y, player, true);
            updateGuiBoard();
            statusBar.setText(String.format("%s went %s and flipped %d pieces",player, move, flips));
        }
    }
    private static class MyJButton extends JButton
    {
        MyJButton(Point p)
        {
            this.p = p;
        }
        Point p;
    }
}
interface OthelloModelInterface
{
    boolean makeMove(int row, int col, CellState state);
    boolean isMoveLegal(int row, int col, CellState state);

    boolean isMoveFlippable(int row, int col, CellState state) ;

    boolean isLocationInBoundsAndUnoccupied(int row, int col);
    CellState getCellState(int row, int col);
}

public class Main {
    public static void main(String[] args) {
        OthelloModel model = new OthelloModel();
        new OthelloGUI(model); // dependency injection ...c-tor injection
    }

}
