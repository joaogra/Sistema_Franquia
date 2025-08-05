package view.telasVendedor;

import controller.gerente.PedidoController;
import controller.vendedor.VendedorOperaController;
import model.Endereco;
import model.Franquia;
import model.Pessoas.Funcionario;
import model.Pessoas.Gerente;
import model.Pessoas.Vendedor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class  TelaVendedor extends JDialog {
    private JPanel telaVendedor;
    private JLabel titulo;
    private JButton btnSair;
    private JButton btnNovaVenda;
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
                PedidoController controller = new PedidoController(vendedor);
                //new TelaHistoricoPedidos(vendedor, controller.getListaPedidos()).setVisible(true);
            }
        });
        btnNovaVenda.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TelaNovaVenda(TelaVendedor.this, vendedor).setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        Gerente gerente1 =  new Gerente("Adrian","14518498690" ,"adra","adrian");
        Franquia franquia = new Franquia("nome",new Endereco(),gerente1, List.of(),null);
        Vendedor v1 = new Vendedor("Joao","14945740712","quantidadeff@gmail.com","12345", franquia);
        gerente1.associaGerenteFranquia(franquia);
        TelaVendedor telaVendedor = new TelaVendedor(v1);
        telaVendedor.setTitle("Vendedor");
        telaVendedor.setContentPane(telaVendedor.telaVendedor);
        telaVendedor.setMinimumSize(new Dimension(650,500));
        telaVendedor.setModal(true);
        telaVendedor.setLocationRelativeTo(null);
        telaVendedor.setVisible(true);
    }
}
