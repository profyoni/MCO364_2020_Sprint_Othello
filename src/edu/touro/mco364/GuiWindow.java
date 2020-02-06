package edu.touro.mco364;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GuiWindow extends JFrame {

    private JButton button = new JButton("Press Me");
    private JLabel statusBar = new JLabel(" I ama statusBar");

    private JPanel centerPanel = new JPanel();
    public GuiWindow()
    {
        setTitle("");
        super.setSize(500, 500);

        // setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE);

        this.addWindowListener(new MyWindowListener());
        add(button, BorderLayout.NORTH);
        add(statusBar, BorderLayout.SOUTH);
        add(centerPanel, BorderLayout.CENTER);
        centerPanel.setLayout(new FlowLayout());
        this.addWindowListener(new MyWindowListener());

        this.addMouseMotionListener( new PanelMouseMotionListener()   );

        this.setVisible(true); //also arranges all components
    }

    private class PanelMouseMotionListener extends MouseMotionAdapter {

        @Override
        public void mouseMoved(MouseEvent mouseEvent) {
            Point p = mouseEvent.getPoint();
            System.out.println(p);
            statusBar.setText(p.toString());

            // modal window = blobks program from continuing until window is dismissed
            JOptionPane.showInputDialog(statusBar, "Hello", "Class May Now Leave", JOptionPane.ERROR_MESSAGE);

        }
    }

    private class MyWindowListener extends WindowAdapter {
        @Override
        public void windowIconified(WindowEvent windowEvent) {
            new GuiWindow(); // non-modal window, lives on its own .. does not block other window

        }        @Override
        public void windowClosed(WindowEvent windowEvent) {
        }
        @Override
        public void windowClosing(WindowEvent windowEvent) {
        }


    }
}
