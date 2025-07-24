package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaLogin extends JDialog {
    private JLabel titulo;
    private JLabel email;
    private JPanel telaLogin;
    private JTextField emailTxt;
    private JPasswordField passwordField1;
    private JLabel senha;
    private JButton sairBtn;
    private JButton logarBtn;

    public TelaLogin(){


        sairBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    public static void main(String[] args) {
        TelaLogin telaLogin = new TelaLogin();
        telaLogin.setTitle("LOGIN");
        telaLogin.setContentPane(telaLogin.telaLogin);
        telaLogin.setMinimumSize(new Dimension(300,350));
        telaLogin.setModal(true);
        telaLogin.setLocationRelativeTo(null);
        telaLogin.setVisible(true);
    }

}
