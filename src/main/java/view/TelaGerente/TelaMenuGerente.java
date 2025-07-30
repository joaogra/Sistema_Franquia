package view.TelaGerente;

import model.Estoque;
import model.Franquia;
import model.Pedido;
import model.Pessoas.Cliente;
import model.Pessoas.Dono;
import model.Pessoas.Gerente;
import model.Pessoas.Vendedor;
import model.Produto;
import view.TelaMenu;

import javax.swing.*;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class TelaMenuGerente extends TelaMenu {
    private Gerente gerente;
    public TelaMenuGerente(JFrame parent,Dono dono,Gerente gerente) {
        super(parent,dono,gerente);
        this.gerente = gerente;
        getTitulo().setText("Menu Principal");
        getButton1().setText("Vendedores");
        getButton2().setText("Pedidos");
        getButton3().setText("Estoque");
        getButton4().setText("Relatorio");

    }
    @Override
    public void confirmaBtn1(){
        new TelaListaVendedores(TelaMenuGerente.this,gerente).setVisible(true);
    }
    @Override
    public void confirmaBtn2(){
        new TelaListaPedidos(TelaMenuGerente.this,gerente).setVisible(true);
    }
    @Override
    public void confirmaBtn3(){
        new TelaVisualizaEstoque(TelaMenuGerente.this,gerente).setVisible(true);
    }
    @Override
    public void confirmaBtn4(){
        new TelaRelatorioFranquia(TelaMenuGerente.this,gerente).setVisible(true);
    }



    public static void main(String[] args) {
        Cliente c1 = new Cliente("adrian", "14518498690");
        Produto p1 = new Produto( 10.0f, 51,"001", "Produto A");
        Produto p2 = new Produto( 20.5f, 33,"002","Produto B");
        Estoque estoque = new Estoque();
        estoque.getEstoque().put(p1.getCod(), p1);
        estoque.getEstoque().put(p2.getCod(), p2);
        Gerente gerente1 =  new Gerente("Adrian","14518498690" ,"adra","adrian");
        Vendedor v1 = new Vendedor("Carlos Silva","123.456.789-09", "carlos@empresa.com", "senha123");
        p1.setQuantidadePedido(15);
        p2.setQuantidadePedido(5);
        Pedido pedido1 = new Pedido("001",c1 ,new Date(),"din",0.05f, Map.of(p1,15,p2,5),1000d);
        v1.adicionaPedido(pedido1);
        pedido1.setMotivoSolicitacao("motibvo aleatorio");
        gerente1.adicionarSolicitaoPedido(pedido1);
        Vendedor v2 = new Vendedor("Marina Costa", "987.654.321-00", "marina@empresa.com", "senha456");
        Vendedor v3 = new Vendedor("João Pereira", "111.444.777-35", "joao@empresa.com", "senha789");
        v1.setNumVendas(25);
        v2.setNumVendas(0);
        v3.setNumVendas(40);
        Franquia franquia = new Franquia("nome","1234",1000f,gerente1, List.of(v1,v2,v3),estoque);
        gerente1.associaGerenteFranquia(franquia);
        Dono dono1 = new Dono("Jorge", "14945740712", "jorge@empresa.com", "senha123", List.of(franquia));
        new TelaMenuGerente(new JFrame(),dono1,gerente1).setVisible(true);
    }
}
