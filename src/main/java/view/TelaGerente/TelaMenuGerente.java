package view.TelaGerente;

import model.Pessoas.Dono;
import model.Pessoas.Gerente;
import view.TelaMenu;


public class TelaMenuGerente extends TelaMenu {
    private Gerente gerente;
    public TelaMenuGerente(Dono dono,Gerente gerente) {
        super(null,dono,gerente);
        setTitle("Menu Tela Gerente");
        this.gerente = gerente;
        getTitulo().setText("Menu Principal");
        getButton1().setText("Vendedores");
        getButton2().setText("Pedidos");
        getButton3().setText("Estoque");
        getButton4().setText("Relatorio");


    }
    @Override
    public void confirmaBtn1(){
        new TelaListaVendedores(TelaMenuGerente.this,gerente.getFranquia()).setVisible(true);
    }
    @Override
    public void confirmaBtn2(){
        new TelaListaPedidos(TelaMenuGerente.this,gerente.getFranquia()).setVisible(true);
    }
    @Override
    public void confirmaBtn3(){new TelaVisualizaEstoque(TelaMenuGerente.this,gerente.getFranquia()).setVisible(true);}
    @Override
    public void confirmaBtn4(){new TelaRelatorioFranquia(TelaMenuGerente.this,gerente.getFranquia()).setVisible(true);}

}
