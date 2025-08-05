package view;

import Exceptions.SenhaInvalidaException;
import Exceptions.UsuarioNaoCadastradoException;
import controller.Arquivo;
import controller.LoginController;
import model.Pessoas.Dono;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaLogin extends JFrame {
    private JLabel titulo;
    private JLabel email;
    private JPanel telaLogin;
    private JTextField cpfTxt;
    private JPasswordField passwordField1;
    private JLabel senha;
    private JButton sairBtn;
    private JButton logarBtn;

    public TelaLogin(Dono dono){
        setTitle("LOGIN");
        setContentPane(telaLogin);
        setMinimumSize(new Dimension(600,550));
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        LoginController loginController = new LoginController(dono);

        sairBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Arquivo arquivo = new Arquivo();
                arquivo.salvar(dono);
                dispose();
            }
        });
        logarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cpf = cpfTxt.getText();
                String senha = String.valueOf(passwordField1.getPassword());
                try {
                    if (loginController.verificaNoSitema(cpf, senha)) {
                        loginController.logar(cpf);
                    }
                }
                catch (UsuarioNaoCadastradoException | SenhaInvalidaException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }

            }
        });
    }

    public static void main(String[] args) {
        Arquivo arquivo = new Arquivo();
        Dono dono = arquivo.inicializa();

        TelaLogin telaLogin = new TelaLogin(dono);
    }



}
