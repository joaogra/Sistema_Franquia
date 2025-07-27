package view.TelaGerente;

import model.Estoque;
import model.Franquia;
import model.Pessoas.Gerente;
import model.Produto;
import view.TelaCadastro;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class TelaMenuGerente extends JFrame{
    private Gerente gerente;
    private JPanel menuGerente;//Cria painel para servir como container para o botao
    private JLabel titulo;

    private JButton editaVendedorBtn;
    private JButton cadastraVendedorBtn;
    private JButton vendedoresOrdenadosBtn;
    private JButton visualizaPedidosBtn;
    private JButton editaPedidosBtn;
    private JButton visualizaEstoqueBtn;
    private JButton relatorioBtn;

    public TelaMenuGerente(Gerente gerente) {
        this.gerente = gerente;

        setTitle("Menu Gerente");
        setContentPane(menuGerente);
        setSize(450,500);
        setLocationRelativeTo(null);//Centraliza a janela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        visualizaEstoqueBtn.addActionListener(event ->{
            new TelaVisualizaEstoque(this,gerente).setVisible(true);
        });


    }

    public static void main(String[] args) {
        Produto p1 = new Produto( 10.0f, 5,"001", "Produto A");
        Produto p2 = new Produto( 20.5f, 3,"002","Produto B");
        Estoque estoque = new Estoque();
        estoque.getEstoque().put(p1.getCod(), p1);
        estoque.getEstoque().put(p2.getCod(), p2);
        Gerente gerente1 =  new Gerente("a","14518498690" ,"adra","adrian");
        Franquia franquia = new Franquia("nome","1234",1000f, List.of(),estoque);
        gerente1.associaGerenteFranquia(franquia);
        new TelaMenuGerente(gerente1).setVisible(true);
    }
}
//