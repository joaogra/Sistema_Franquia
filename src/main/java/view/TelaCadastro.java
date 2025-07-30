package view;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class TelaCadastro extends JDialog {
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
    private JButton voltarBtn;

    public TelaCadastro(JDialog parent, String titulo) {
        super(parent,titulo,true);
        setContentPane(tela_Cadastro);
        setSize(800,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        cadastrarBtn.addActionListener(e ->  {
            cadastrar();
        });
        cancelarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clean();
            }
        });
    }

    private void clean() {
        nomeTxt.setText("");
        cpfTxt.setText("");
        emailTxt.setText("");
        senhaTxt.setText("");
    }
    //JLABEL
    public JLabel getTitulo() {
        return titulo;
    }

    public JLabel getSenha() {
        return senha;
    }

    //TEXTOS
    public JTextField getNomeTxt() {
        return nomeTxt;
    }
    public JTextField getCpfTxt() {
        return cpfTxt;
    }
    public JTextField getEmailTxt() {
        return emailTxt;
    }
    public JTextField getSenhaTxt() {
        return senhaTxt;
    }
    public abstract void cadastrar();
    //BUTTONS
    public JButton getCadastrarBtn() {
        return  cadastrarBtn;
    }
}
