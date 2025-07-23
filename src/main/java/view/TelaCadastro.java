package view;

import javax.swing.*;
import java.awt.*;

public class TelaCadastro extends JDialog {
    private JLabel titulo;
    private JTextField nomeTxt;
    private JTextField cpfTxt;
    private JTextField emailTxt;
    private JTextField senhaTxt;
    private JLabel nome;
    private JLabel cpf;
    private JLabel email;
    private JLabel senha;
    private JButton cadastrarBtn;
    private JButton cancelarBtn;
    private JPanel tela_Cadastro;

    public TelaCadastro(JFrame parent) {
        super(parent);
        setTitle("Cadastro");
        setContentPane(tela_Cadastro);
        setMinimumSize(new Dimension(450,500));
        setModal(true);
        setLocationRelativeTo(parent);
        setVisible(true);
    }

    public static void main(String[] args) {
        TelaCadastro telaCadastro = new TelaCadastro(null);
    }


}
