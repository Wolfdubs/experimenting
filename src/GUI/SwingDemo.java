package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class SwingDemo implements ActionListener {

    private int buttonCount = 0;
    private JLabel jLabel;  //put these in scope of the other methods not just inside main()
    private JFrame jFrame;
    private JPanel jPanel;
    private JButton jButton;
    private JButton jButtonDecrement;
    private JButton jButtonMultiply;
    private JButton jButtonDivide;
    private JButton jButtonRandomize;

    public SwingDemo(){
        jFrame = new JFrame();   //is the GUI window

        jButton = new JButton("Click me to increment");
        jButton.setSize(20,10);
        jButton.addActionListener(this);    //actionlistener looking inside the current class to execute the method actionPerformed()
        jButtonDecrement = new JButton("Click me to decrement");
        jButtonDecrement.setSize(20,10);
        jButtonDecrement.addActionListener(this::actionPerformedDecrement);
        jButtonMultiply = new JButton("Click me to multiply");
        jButtonMultiply.setSize(20,10);
        jButtonMultiply.addActionListener(this::actionPerformedMultiply);
        jButtonDivide = new JButton("Click me to divide");
        jButtonDivide.setSize(20,10);
        jButtonDivide.addActionListener(this::actionPerformedDivide);
        jButtonRandomize = new JButton("Click me to randomize (0-100)");
        jButtonRandomize.setSize(20,10);
        jButtonRandomize.addActionListener(this::actionPerformedRandomize);

        jLabel = new JLabel("Number of clicks: 0");
        jPanel = new JPanel();   //panel where content sits
        //set the border from the frame, set the layout, add elements to the layout
        jPanel.setBorder(BorderFactory.createEmptyBorder(100,100,100,100));  //in pixels
        jPanel.setLayout(new GridLayout(0,1));

        jPanel.add(jButton); jPanel.add(jButtonDecrement); jPanel.add(jButtonMultiply); jPanel.add(jButtonDivide); jPanel.add(jButtonRandomize);
        jPanel.add(jLabel);

        jFrame.add(jPanel, BorderLayout.CENTER);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setTitle("Womble GUI");
        jFrame.pack();
        jFrame.setVisible(true);
        ImageIcon img = new ImageIcon("src/GUI/womble.png");
        jFrame.setIconImage(img.getImage());

    }

    public static void main(String[] args) {
        new SwingDemo();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        buttonCount++;
        jLabel.setText("Number of clicks = " + buttonCount);
    }

    public void actionPerformedDecrement(ActionEvent e) {
        buttonCount--;
        jLabel.setText("Number of clicks = " + buttonCount);
    }

    public void actionPerformedMultiply(ActionEvent e) {
        buttonCount*=buttonCount;
        jLabel.setText("Number of clicks = " + buttonCount);
    }

    public void actionPerformedDivide(ActionEvent e) {
        buttonCount/=Math.sqrt(buttonCount);
        jLabel.setText("Number of clicks = " + buttonCount);
    }

    public void actionPerformedRandomize(ActionEvent e) {
        buttonCount = new Random().nextInt(101);
        jLabel.setText("Number of clicks = " + buttonCount);
    }
}
