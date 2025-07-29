package view.TelaGerente;

import model.Estoque;
import model.Franquia;
import model.Pedido;
import model.Pessoas.Cliente;
import model.Pessoas.Gerente;
import model.Pessoas.Vendedor;
import model.Produto;

import javax.swing.*;
import java.util.Date;
import java.util.List;

public class TelaMenuGerente extends JFrame{
    private Gerente gerente;
    private JPanel menuGerente;//Cria painel para servir como container para o botao
    private JLabel titulo;

    private JButton vendedorBtn;
    private JButton visualizaPedidosBtn;
    private JButton visualizaEstoqueBtn;
    private JButton relatorioBtn;
    private JButton logoutBtn;

    public TelaMenuGerente(Gerente gerente) {
        this.gerente = gerente;

        setTitle("Menu Gerente");
        setContentPane(menuGerente);
        setSize(800,600);
        setLocationRelativeTo(null);//Centraliza a janela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        vendedorBtn.addActionListener(event ->{
           new TelaListaVendedores(TelaMenuGerente.this,gerente).setVisible(true);
        });
        visualizaPedidosBtn.addActionListener(event ->{
           new TelaListaPedidos(TelaMenuGerente.this,gerente).setVisible(true);
        });
        visualizaEstoqueBtn.addActionListener(event ->{
            new TelaVisualizaEstoque(TelaMenuGerente.this,gerente).setVisible(true);
        });
        logoutBtn.addActionListener(event ->{
            this.dispose();
        });

    }

    public static void main(String[] args) {
        Cliente c1 = new Cliente("adrian", "14518498690");
        Produto p1 = new Produto( 10.0f, 51,"001", "Produto A");
        Produto p2 = new Produto( 20.5f, 33,"002","Produto B");
        Estoque estoque = new Estoque();
        estoque.getEstoque().put(p1.getCod(), p1);
        estoque.getEstoque().put(p2.getCod(), p2);
        Gerente gerente1 =  new Gerente("a","14518498690" ,"adra","adrian");
        Vendedor v1 = new Vendedor("Carlos Silva","123.456.789-09", "carlos@empresa.com", "senha123");
        p1.setQuantidadePedido(15);
        p2.setQuantidadePedido(5);
        Pedido pedido1 = new Pedido("001",c1 ,new Date(),"din",0.05f,List.of(p1,p2),1000d);
        v1.adicionaPedido(pedido1);
        Vendedor v2 = new Vendedor("Marina Costa", "987.654.321-00", "marina@empresa.com", "senha456");
        Vendedor v3 = new Vendedor("João Pereira", "111.444.777-35", "joao@empresa.com", "senha789");
        v1.setNumVendas(25);
        v2.setNumVendas(0);
        v3.setNumVendas(40);
        Franquia franquia = new Franquia("nome","1234",1000f, List.of(v1,v2,v3),estoque);
        gerente1.associaGerenteFranquia(franquia);
        new TelaMenuGerente(gerente1).setVisible(true);
    }
}
