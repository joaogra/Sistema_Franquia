package view;

import model.Pessoas.Funcionario;
import model.Pessoas.Vendedor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaVendedor extends JDialog {
    private JPanel telaVendedor;
    private JLabel titulo;
    private JButton btnSair;
    private JButton btnNovaVenda;
    private JButton btnAlteraPedido;
    private JButton btnHistorico;
    private JLabel boasVindasTxt;

    public TelaVendedor(Funcionario vendedor) {

        boasVindasTxt.setText("Boas vindas " + vendedor.getNome() );

        btnSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    public static void main(String[] args) {
        Vendedor v1 = new Vendedor("Joao","14945740712","quantidadeff@gmail.com","12345");
        TelaVendedor telaVendedor = new TelaVendedor(v1);
        telaVendedor.setTitle("Vendedor");
        telaVendedor.setContentPane(telaVendedor.telaVendedor);
        telaVendedor.setMinimumSize(new Dimension(450,500));
        telaVendedor.setModal(true);
        telaVendedor.setLocationRelativeTo(null);
        telaVendedor.setVisible(true);
    }
}
