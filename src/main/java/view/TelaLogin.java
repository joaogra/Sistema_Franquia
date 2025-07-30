package view;

import controller.Arquivo;
import controller.FuncionarioController;
import model.Pessoas.Dono;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaLogin extends JDialog {
    private JLabel titulo;
    private JLabel email;
    private JPanel telaLogin;
    private JTextField cpfTxt;
    private JPasswordField passwordField1;
    private JLabel senha;
    private JButton sairBtn;
    private JButton logarBtn;

    public TelaLogin(Dono dono){

        FuncionarioController funcionarioController = new FuncionarioController(dono);

        sairBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        logarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cpf = cpfTxt.getText();
                String senha = String.valueOf(passwordField1.getPassword());
                funcionarioController.verificaNoSitema(cpf, senha);
            }
        });
    }

    public static void main(String[] args) {
        Arquivo arquivo = new Arquivo();
        Dono dono = arquivo.inicializa();

        TelaLogin telaLogin = new TelaLogin(dono);
        telaLogin.setTitle("LOGIN");
        telaLogin.setContentPane(telaLogin.telaLogin);
        telaLogin.setMinimumSize(new Dimension(600,550));
        telaLogin.setModal(true);
        telaLogin.setLocationRelativeTo(null);
        telaLogin.setVisible(true);
    }



}
