package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaLogin extends JFrame{
    private JPanel panelMain;
    private JTextField insertName;
    private JButton btnOk;

    public TelaLogin() {
        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            //JOptionPane.showMessageDialog(btnOk, "Seja bem vindo(a) " +  insertName.getText());
                System.out.println("ola");
            }
        });


    }

    public static void main(String[] args) {
        TelaLogin telaLogin = new TelaLogin();
        telaLogin.setContentPane(telaLogin.panelMain);
        telaLogin.setTitle("Página de Login");
        telaLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        telaLogin.pack();
        telaLogin.setLocationRelativeTo(null); // opcional: centraliza a janela
        telaLogin.setVisible(true);
    }
}
