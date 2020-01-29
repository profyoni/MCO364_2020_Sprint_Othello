package edu.touro.mco364;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OthelloWindow extends JFrame {

    JButton button;
    public OthelloWindow()
    {
        setTitle("Othello");
        super.setSize(800, 300);

        setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE);
        setLayout( new GridLayout(8,8));

        ActionListener actionListener = new ActionListener() {// anonymous inner class
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

               JButton b= ((JButton)actionEvent.getSource());
               b.setText("Ahhh");
               b.setBackground(new Color(198,23,87));
            }
        };

        for (int i=1;i<=64;i++)
        {
            JButtonWithPoint b =new JButtonWithPoint();
            b.setText(i+"");
            b.x = i % 8;
            b.y = i / 8;

            add(b);
            final int ii = i;
            b.addActionListener(actionListener); // registering event handler
        }

        this.setVisible(true);
    }

    class JButtonWithPoint extends JButton
    {
        int x, y;
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
