package edu.touro.mco364;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OthelloWindow extends JFrame {

    private JButton button = new JButton("Press Me");
    public OthelloWindow()
    {
        setTitle("Othello");
        super.setSize(500, 500);

        setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE);

        setLayout(new GridLayout(8,8));

        // anonymous inner class
        ActionListener al = actionEvent -> {
            JButton b =  (JButton) actionEvent.getSource();
            b.setText("Woohoo");
            b.setBackground(new Color(98,0,123));
        };
        for (int i=0;i<64;i++)
        {
            JButton b = new JButton("" +i);
            this.add(b);
            b.addActionListener(al);
        }

        this.setVisible(true); //also arranges all components
    }

    // event handler
    class  ButtonPressedListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent)
        {
            button.setText("Ooohh");
        }
    }
}
