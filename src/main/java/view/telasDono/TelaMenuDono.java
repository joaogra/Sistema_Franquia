package view.telasDono;

import controller.DonoController;
import controller.gerente.FranquiaController;
import model.*;
import model.Pessoas.Cliente;
import model.Pessoas.Dono;
import model.Pessoas.Gerente;
import model.Pessoas.Vendedor;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class TelaMenuDono extends JFrame {
    private JPanel painelMenu;
    private JLabel titulo;
    private JLabel boasVindas;
    private JTable tabelaFranquias;
    private JButton cadastraFranquiaBtn;
    private JButton cadastrarGerenteBtn;
    private JButton removerFranquiaButton;
    private JButton removerGerenteBtn;
    private JButton resumoFinanceiroBtn;
    private JButton rankingVendedoresBtn;
    private JButton logoutBtn;
    private DonoController donoController;

    public TelaMenuDono(JFrame parent, Dono dono) {
        super("Menu Dono");
        setContentPane(painelMenu);
        setSize(1000,640);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.donoController = new DonoController(dono);

        atualizarTabela();

        cadastraFranquiaBtn.addActionListener(e -> {
            new TelaCadastroFranquia(this, dono).setVisible(true);
            atualizarTabela();
        });
        removerFranquiaButton.addActionListener(e -> {

        });
        cadastrarGerenteBtn.addActionListener(e -> {

        });
        rankingVendedoresBtn.addActionListener(e -> {

        });
        removerGerenteBtn.addActionListener(e -> {

        });
        logoutBtn.addActionListener(e -> {
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
        Vendedor v1 = new Vendedor("Carlos Silva","123.456.789-09", "carlos@empresa.com", "senha123");
        Pedido pedido1 = new Pedido("001",c1 ,new Date(),"din",0.05f, Map.of(p1,15,p2,5),1000d);
        v1.adicionaPedido(pedido1);
        Vendedor v2 = new Vendedor("Marina Costa", "987.654.321-00", "marina@empresa.com", "senha456");
        Vendedor v3 = new Vendedor("João Pereira", "111.444.777-35", "joao@empresa.com", "senha789");
        v1.setNumVendas(25);
        v2.setNumVendas(0);
        v3.setNumVendas(40);
        Gerente gerente1 =  new Gerente("Adrian","14518498690" ,"adra","adrian");
        Franquia franquia = new Franquia("nome",new Endereco(),gerente1, List.of(v1,v2,v3),estoque);
        gerente1.associaGerenteFranquia(franquia);
        Dono dono1 = new Dono("Pericles","14518498690","@blalbalba","1234567",List.of(franquia));
        new TelaMenuDono(new JFrame(),dono1).setVisible(true);
    }
    public void atualizarTabela() {
        String[] colunas = {"Nome", "Endereço", "Gerente", "Lucro",""};
        DefaultTableModel tabela = new DefaultTableModel(colunas,0){
            @Override public boolean isCellEditable(int linha, int coluna) {
                return false;
            }
        };

        List<Object[]> franquias = donoController.listaFranquias();
        for(Object[] linha : franquias){
            tabela.addRow(linha);
        }
        tabelaFranquias.setModel(tabela);
        tabelaFranquias.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabelaFranquias.getTableHeader().setReorderingAllowed(false);

        tabelaFranquias.getColumnModel().getColumn(4).setMaxWidth(0);
        tabelaFranquias.getColumnModel().getColumn(4).setWidth(0);
        tabelaFranquias.getColumnModel().getColumn(4).setMinWidth(0);



        //centraliza os itens
        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);
        centralizado.setVerticalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < tabelaFranquias.getColumnCount(); i++) {
            tabelaFranquias.getColumnModel().getColumn(i).setCellRenderer(centralizado);
        }

        //coloca fontes no cabecalho e nos itens
        tabelaFranquias.setFont(new Font("SansSerif", Font.PLAIN, 13));
        tabelaFranquias.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));
    }

}
