package view;

import com.google.gson.Gson;
import model.Pessoas.Vendedor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;

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
    private JButton voltarBtn;

    public TelaCadastro(JFrame parent) {

        cadastrarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = nomeTxt.getText();
                String cpf = cpfTxt.getText();
                String email = emailTxt.getText();
                String senha = senhaTxt.getText();

                Vendedor vendedor = new Vendedor(nome,cpf,email,senha);
                System.out.println(vendedor.getNome() + vendedor.getCPF());

                Gson gson = new Gson();
                String json = gson.toJson(vendedor);

                try{
                    FileWriter writer = new FileWriter("data/funcionarios",true);
                    writer.write(json + System.lineSeparator());
                    writer.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!");
                clean();
            }
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

    public static void main(String[] args) {
        TelaCadastro telaCadastro = new TelaCadastro(null);
        telaCadastro.setTitle("Novo Produto");
        telaCadastro.setContentPane(telaCadastro.tela_Cadastro);
        telaCadastro.setMinimumSize(new Dimension(450,500));
        telaCadastro.setModal(true);
        telaCadastro.setLocationRelativeTo(null);
        telaCadastro.setVisible(true);

    }


}
