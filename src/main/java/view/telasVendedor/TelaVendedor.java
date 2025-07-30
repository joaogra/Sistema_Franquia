package view.telasVendedor;

import model.Pessoas.Funcionario;
import model.Pessoas.Vendedor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class  TelaVendedor extends JDialog {
    private JPanel telaVendedor;
    private JLabel titulo;
    private JButton btnSair;
    private JButton btnNovaVenda;
    private JButton btnAlteraPedido;
    private JButton btnHistorico;
    private JLabel boasVindasTxt;

    public TelaVendedor(Vendedor vendedor) {

        boasVindasTxt.setText("Boas vindas " + vendedor.getNome() );

        btnSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        btnHistorico.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaHistoricoPedidos telaHistoricoPedidos = new TelaHistoricoPedidos(TelaVendedor.this ,vendedor.getHistoricoPedidos());
                telaHistoricoPedidos.setVisible(true);
            }
        });
        btnNovaVenda.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaNovaVenda telaNovaVenda = new TelaNovaVenda(TelaVendedor.this, vendedor);
                telaNovaVenda.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        Vendedor v1 = new Vendedor("Joao","14945740712","quantidadeff@gmail.com","12345");
        TelaVendedor telaVendedor = new TelaVendedor(v1);
        telaVendedor.setTitle("Vendedor");
        telaVendedor.setContentPane(telaVendedor.telaVendedor);
        telaVendedor.setMinimumSize(new Dimension(650,500));
        telaVendedor.setModal(true);
        telaVendedor.setLocationRelativeTo(null);
        telaVendedor.setVisible(true);
    }
}
