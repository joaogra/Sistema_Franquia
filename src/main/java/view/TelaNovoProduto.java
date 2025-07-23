package view;

import model.Produto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaNovoProduto extends JDialog {
    private JPanel telaProduto;
    private JTextField nomeTxt;
    private JTextField codigoTxt;
    private JTextField precoTxt;
    private JTextField qtdTxt;
    private JLabel qtd;
    private JLabel preco;
    private JLabel codigo;
    private JLabel nome;
    private JButton cancelarButton;
    private JButton confirmarButton;

    public TelaNovoProduto(){



        confirmarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e){
                String nome = nomeTxt.getText();
                String codigo = codigoTxt.getText();
                float preco = Float.parseFloat(precoTxt.getText());
                int qtd = Integer.parseInt(qtdTxt.getText());

                Produto produto = new Produto(preco, qtd, codigo, nome);
                System.out.println(produto.getNome() + produto.getCod());
            }
        });
        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nomeTxt.setText("");
                codigoTxt.setText("");
                precoTxt.setText("");
                qtdTxt.setText("");
            }
        });
    }

    public static void main(String[] args) {
        TelaNovoProduto telaProduto = new TelaNovoProduto();
        telaProduto.setTitle("Novo Produto");
        telaProduto.setContentPane(telaProduto.telaProduto);
        telaProduto.setMinimumSize(new Dimension(450,500));
        telaProduto.setModal(true);
        telaProduto.setLocationRelativeTo(null);
        telaProduto.setVisible(true);
    }
}
