package view.telasVendedor;

import controller.Arquivo;
import controller.gerente.PedidoController;
import controller.vendedor.VendedorOperaController;
import model.*;
import model.Pessoas.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class  TelaVendedor extends JFrame {
    private JPanel telaVendedor;
    private JLabel titulo;
    private JButton btnSair;
    private JButton btnNovaVenda;
    private JButton btnHistorico;
    private JLabel boasVindasTxt;
    private Vendedor vendedor2;

    public TelaVendedor(Dono dono, Vendedor vendedor) {
        this.vendedor2 = vendedor;
        setTitle("Vendedor");
        setContentPane(telaVendedor);
        setMinimumSize(new Dimension(650,500));
        setLocationRelativeTo(null);
        setVisible(true);
        boasVindasTxt.setText("Boas vindas " + vendedor.getNome() );

        btnSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Arquivo arquivo = new Arquivo();
                arquivo.salvar(dono);
                dispose();
            }
        });

        btnHistorico.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PedidoController controller = new PedidoController(vendedor.getFranquia());
                new TelaHistoricoPedidos(TelaVendedor.this,vendedor, vendedor.getHistoricoPedidos(),vendedor.getFranquia()).setVisible(true);
            }
        });

        btnNovaVenda.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TelaNovaVenda(TelaVendedor.this, vendedor2).setVisible(true);
            }
        });
    }

    /*public static void main(String[] args) {
        Gerente gerente1 =  new Gerente("Adrian","14518498690" ,"adra","adrian");
        Estoque estoque = new Estoque();
        Produto p1 = new Produto( 10.0f, 51,"001", "Produto A");
        Produto p2 = new Produto( 20.5f, 33,"002","Produto B");
        estoque.adicionaProduto(p1);
        estoque.adicionaProduto(p2);
        Pedido p5 = new Pedido("1234",new Cliente("aaaa","12345678909"),new Date(),"Dinheiro",
                0.05f, Map.of(p1.getCod(),15),1000d);
        Franquia franquia = new Franquia("nome",new Endereco(),gerente1, List.of(),estoque);
        Vendedor v1 = new Vendedor("Joao","14945740712","quantidadeff@gmail.com","12345", franquia);
        v1.adicionaPedido(p5);
        gerente1.associaGerenteFranquia(franquia);
        Dono dono1 = new Dono("Pericles","14518498690","@blalbalba","1234567",List.of(franquia));
        TelaVendedor telaVendedor = new TelaVendedor(dono1,v1);
    }*/
}
