package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginDemo implements ActionListener {
    private JFrame jFrame;
    private JPanel jPanel;
    private JLabel jLabelUsername;
    private JLabel jLabelPassword;
    private JTextField jTextFieldUsername;
    private JPasswordField jPasswordField;
    private JButton jButton;
    private JLabel loginSuccess;

    public LoginDemo(){
        jFrame = new JFrame();
        jFrame.setSize(800, 400);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jPanel = new JPanel();
        jPanel.setLayout(null);
        jLabelUsername = new JLabel("Username");
        jLabelUsername.setBounds(10, 20, 80, 25);
        jPanel.add(jLabelUsername);

        jTextFieldUsername = new JTextField(20);
        jTextFieldUsername.setBounds(100, 20, 165, 25);
        jPanel.add(jTextFieldUsername);

        jLabelPassword = new JLabel("Password");
        jLabelPassword.setBounds(10, 50, 80, 25);
        jPanel.add(jLabelPassword);

        jPasswordField = new JPasswordField(20);
        jPasswordField.setBounds(100, 50, 165, 25);
        jPanel.add(jPasswordField);

        jButton = new JButton("Login");
        jButton.setBounds(10,80,80,25);
        jButton.addActionListener(this::actionPerformed);
        jPanel.add(jButton);
        loginSuccess = new JLabel("");
        loginSuccess.setBounds(10, 110, 300, 25);
        jPanel.add(loginSuccess);

        ImageIcon imageIcon = new ImageIcon("src/GUI/womble.png");
        jFrame.setIconImage(imageIcon.getImage());

        jFrame.add(jPanel);
        jFrame.setVisible(true);




    }

    public static void main(String[] args) {
        new LoginDemo();
    }

    @Override
    @SuppressWarnings({"deprecation"})
    public void actionPerformed(ActionEvent e){
        String username = jTextFieldUsername.getText();
        String password = jPasswordField.getText();
        System.out.println("username = " + username + ", password = " + password);
        if (username.equals("womble") && (password.equals("mungo"))) {
            loginSuccess.setText("You have successfully logged in");
            System.out.println("womble user has logged in");
        } else {
            loginSuccess.setText("Incorrect credentials");
        }
    }
}













