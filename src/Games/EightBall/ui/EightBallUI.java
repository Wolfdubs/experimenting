package Games.EightBall.ui;

import Games.EightBall.model.EightBallGame;
import Games.EightBall.model.*;
import javax.swing.*;
import java.awt.*;

public class EightBallUI extends JFrame{

    public static final int WIDTH =600;
    public static final int HEIGHT = 400;
    private JPanel myPane;

    public static void main(String[] args) throws InterruptedException {
        new EightBallUI();
    }

    private EightBallUI(){
        super("Eight Ball Console");
        setSize(WIDTH,HEIGHT);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        myPane = new JPanel();
        //need to place the greeting


        placeGreeting("Would you like to ask the eight ball any questions?");
        placeButtons();
        EightBallGame myGame = new EightBallGame();
        EightBallGame.playEightBallGame();
        setVisible(true);
    }

    private void placeGreeting(String greetingMessage){
        JLabel greeting = new JLabel(greetingMessage, JLabel.CENTER);
        greeting.setSize(WIDTH, HEIGHT / 2);
        this.add(greeting);
    }

    private void placeButtons(){
        JButton rollButton = new JButton(ButtonNames.ROLL.getButtonName());
        JButton exitButton = new JButton(ButtonNames.EXIT.getButtonName());

        //adding the buttons to JPanel
        JPanel buttonRow = formatButtonRow(rollButton);
        buttonRow.add(exitButton);
        buttonRow.setSize(WIDTH, HEIGHT/6);

        rollButton.addActionListener((e) -> {
            String buttonPressed = e.getActionCommand();
            if (buttonPressed.equals(ButtonNames.ROLL.getButtonName())){
                //create new JPanel to display new page content on
                JPanel playPanel = new JPanel();
                playPanel.setLayout(new FlowLayout());
                placeGreeting("Time to ask the eight ball");
              //  EightBallGame.playEightBallGame();
            }
            if (buttonPressed.equals(ButtonNames.EXIT.getButtonName())){

                placeGreeting("See you next time");
                System.exit(0);
            }
        });

        this.add(buttonRow);
    }

    public JPanel formatButtonRow(JButton b){
        JPanel p = new JPanel();
        p.setLayout(new FlowLayout());
        p.add(b);

        return p;
    }
}

