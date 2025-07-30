package view.TelaGerente;

import controller.Arquivo;
import model.Estoque;
import model.Franquia;
import model.Pessoas.Dono;
import model.Pessoas.Gerente;
import model.Pessoas.Vendedor;
import model.Produto;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class TelaMenuGerente extends JFrame{
    private Gerente gerente;
    private JPanel menuGerente;//Cria painel para servir como container para o botao
    private JLabel titulo;

    private JButton vendedorBtn;
    private JButton visualizaPedidosBtn;
    private JButton editaPedidosBtn;
    private JButton visualizaEstoqueBtn;
    private JButton relatorioBtn;
    private JButton logoutBtn;

    public TelaMenuGerente(Gerente gerente, Dono dono) {
        this.gerente = gerente;

        setTitle("Menu Gerente");
        setContentPane(menuGerente);
        setSize(800,600);
        setLocationRelativeTo(null);//Centraliza a janela
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        vendedorBtn.addActionListener(event ->{
           new TelaListaVendedores(this,gerente).setVisible(true);
        });
        visualizaPedidosBtn.addActionListener(event ->{
           new TelaListaPedidos(this,gerente).setVisible(true);
        });
        visualizaEstoqueBtn.addActionListener(event ->{
            new TelaVisualizaEstoque(this,gerente).setVisible(true);
        });
        logoutBtn.addActionListener(event ->{
            this.dispose();
        });

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e){
                Arquivo arquivo = new Arquivo();
                arquivo.salvar(dono);
            }
        });

    }

    public static void main(String[] args) {
        Produto p1 = new Produto( 10.0f, 51,"001", "Produto A");
        Produto p2 = new Produto( 20.5f, 33,"002","Produto B");

        Estoque estoque = new Estoque();
        estoque.getEstoque().put(p1.getCod(), p1);
        estoque.getEstoque().put(p2.getCod(), p2);
        Gerente gerente1 =  new Gerente("a","14518498690" ,"adra","adrian");
        Vendedor v1 = new Vendedor("Carlos Silva", "12345678909", "carlos@empresa.com", "senha123");
        Vendedor v2 = new Vendedor("Marina Costa", "98765432100", "marina@empresa.com", "senha456");
        Vendedor v3 = new Vendedor("João Pereira", "11144477735", "joao@empresa.com", "senha789");
        Franquia franquia = new Franquia("nome","1234",1000f, List.of(v1,v2,v3),estoque);
        Dono dono1 = new Dono("Jorge", "14945740712", "jorge@empresa.com", "senha123", List.of(franquia));
        v1.setNumVendas(25);
        v2.setNumVendas(0);
        v3.setNumVendas(40);

        gerente1.associaGerenteFranquia(franquia);
        new TelaMenuGerente(gerente1,dono1).setVisible(true);

    }
}
