package edu.touro.mco364;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


enum CellState {NONE, BLACK, WHITE}

class OthelloGUI extends JFrame
{
    private JButton button;
    OthelloGUI(){
        setTitle("MCO 364");
        super.setSize(600,200);

        this.setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );

        button = new JButton("Tickle me, Elmo"); // event source
        add(button);

        button.addActionListener(new MyButtonHandler() );

        setVisible(true);
    }

    class MyButtonHandler implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            button.setText("hahahaha" + button.getText());

        }
    }

}


public class Main {
    public static void main2(String[] args) {
new OthelloGUI();

    }
    public static void main(String[] args) {
//        OthelloModel othelloModel = new OthelloModel();
//
//        while (! othelloModel.isGameOver())
//        {
//            System.out.println(othelloModel.boardToString());
//            System.out.println(othelloModel.currentPlayer());
//            // prompt user for Row/Col (e.g. B7)
//            othelloModel.makeMove(row, col, othelloModel.currentPlayer());
//
//        }
        new OthelloWindow();
    }
}
