package view.TelaGerente;

import controller.gerente.VendedorController;
import model.Franquia;
import model.Pessoas.Gerente;
import model.Pessoas.Vendedor;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class TelaListaVendedores extends JDialog{
    private JPanel painelListaVendedor;
    private JTable tabelaVendedores;
    private JButton cadastrarBtn;
    private JButton editarBtn;
    private JButton removerBtn;
    private JButton voltarBtn;
    private JLabel titulo;
    private JLabel infoTabela;
    private VendedorController vendedorController;
    private Franquia franquia;
    public TelaListaVendedores(JFrame parent, Franquia franquia) {
        super(parent,"Lista de Vendedores",true);
        this.franquia = franquia;
        if(franquia == null){
            System.out.println("aaaaaabbbcc");
        }
        setContentPane(painelListaVendedor);
        setSize(800,600);
        setLocationRelativeTo(parent);
        vendedorController = new VendedorController(franquia);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        atualizarTabela();

        cadastrarBtn.addActionListener(e -> {
            new TelaCadastrarVendedor(parent,franquia).setVisible(true);
            atualizarTabela();
        });
        editarBtn.addActionListener(e -> {
            int linhaEscolhida = tabelaVendedores.getSelectedRow();
            if (linhaEscolhida != -1) {
                Vendedor vendedorEscolhido = (Vendedor)  tabelaVendedores.getValueAt(linhaEscolhida,2);
                new TelaEditarVendedor(parent,franquia,vendedorEscolhido).setVisible(true);
                atualizarTabela();
            }
            else{
                JOptionPane.showMessageDialog(null, "Selecione um vendedor para editar!");
            }

        });
        removerBtn.addActionListener(e -> {

            int linhaEscolhida = tabelaVendedores.getSelectedRow();
            if (linhaEscolhida != -1) {
                int resposta = JOptionPane.showConfirmDialog(null,
                        "Tem certeza que deseja remover o vendedor?","Confirmação",
                        JOptionPane.YES_NO_OPTION);
                if(resposta == JOptionPane.YES_OPTION) {
                    Vendedor vendedorEscolhido = (Vendedor)  tabelaVendedores.getValueAt(linhaEscolhida,2);
                    vendedorController.removerVendedor(vendedorEscolhido);
                    atualizarTabela();
                    JOptionPane.showMessageDialog(null,"Vendedor removido com sucesso!");
                }
                else{
                    return;
                }
            } else {
                JOptionPane.showMessageDialog(null, "Selecione um vendedor para remover!");
            }

        });
        voltarBtn.addActionListener(e -> {
            dispose();
        });
    }
    private void atualizarTabela() {
        String[] colunas = {"Nome", "N° de Vendas","Vendedor"};
        DefaultTableModel tabela = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        List<Object[]> produtos = vendedorController.listarVendedorParaTabela();
        for(Object[] linha : produtos){
            tabela.addRow(linha);
        }
        tabelaVendedores.setModel(tabela);//aplica os dados a tabela
        tabelaVendedores.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        //deixa o object vendedor invisivel na tabela
        tabelaVendedores.getColumnModel().getColumn(2).setMinWidth(0);
        tabelaVendedores.getColumnModel().getColumn(2).setMaxWidth(0);
        tabelaVendedores.getColumnModel().getColumn(2).setWidth(0);
        //centraliza os itens
        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);
        centralizado.setVerticalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < tabelaVendedores.getColumnCount(); i++) {
            tabelaVendedores.getColumnModel().getColumn(i).setCellRenderer(centralizado);
        }

        //coloca fontes no cabecalho e nos itens
        tabelaVendedores.setFont(new Font("SansSerif", Font.PLAIN, 13));
        tabelaVendedores.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));
    }
}