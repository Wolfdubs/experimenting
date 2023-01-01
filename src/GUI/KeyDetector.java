package GUI;

import Concepts.Basics.Keywords;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyDetector {
    JFrame jFrame;
    JPanel jPanel;
    JLabel jLabelUp;
    JLabel jLabelDown;
    JLabel jLabelLeft;
    JLabel jLabelRight;
    JButton jButton;

    public KeyDetector(){
        jFrame = new JFrame("Detecting Keys");
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(400,300);
        jFrame.setFocusable(true);



        jPanel = new JPanel();
        jFrame.add(jPanel);
        jLabelUp = new JLabel("UP"); jLabelDown = new JLabel("DOWN"); jLabelLeft = new JLabel(); jLabelRight = new JLabel();
        jLabelLeft.setText("LEFT"); jLabelRight.setText("RIGHT");
        jPanel.add(jLabelUp); jPanel.add(jLabelDown); jPanel.add(jLabelLeft); jPanel.add(jLabelRight);
        final int[] upCount = {0};
        final int[] downCount = {0};
        final int[] leftCount = {0};
        final int[] rightCount = {0};

        ImageIcon ii = new ImageIcon("src/GUI/womble.png");
        jFrame.setIconImage(ii.getImage());

        jFrame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                switch(keyCode){
                    case KeyEvent.VK_UP -> jLabelUp.setText("UP " + ++upCount[0]);
                    case KeyEvent.VK_DOWN -> jLabelDown.setText("DOWN " + ++downCount[0]);
                    case KeyEvent.VK_LEFT -> jLabelLeft.setText("LEFT " + ++leftCount[0]);
                    case KeyEvent.VK_RIGHT -> jLabelRight.setText("RIGHT " + ++rightCount[0]);
                }

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });


    }

    public static void main(String[] args) {
        new KeyDetector();
    }
}
