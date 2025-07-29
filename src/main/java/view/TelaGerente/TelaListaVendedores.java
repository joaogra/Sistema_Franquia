package view.TelaGerente;

import controller.gerente.VendedorController;
import model.Pessoas.Gerente;
import model.Pessoas.Vendedor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class TelaListaVendedores extends JDialog{
    private JPanel painelListaVendedor;
    private JTable tabelaVendedores;
    private JButton cadastrarVendedorButton;
    private JButton editarVendedorButton;
    private JButton removerVendedorButton;
    private JButton voltarBtn;
    private JLabel titulo;
    private JLabel explicaTabela;
    private VendedorController vendedorController;

    public TelaListaVendedores(JFrame parent,Gerente gerente) {
        super(parent,"Lista de Vendedores",true);
        setContentPane(painelListaVendedor);
        setSize(800,600);
        setLocationRelativeTo(parent);
        vendedorController = new VendedorController(gerente);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        atualizarTabela();

        cadastrarVendedorButton.addActionListener(e -> {
            new TelaCadastrarVendedor(TelaListaVendedores.this,gerente).setVisible(true);
            atualizarTabela();
        });
        editarVendedorButton.addActionListener(e -> {
            int linhaEscolhida = tabelaVendedores.getSelectedRow();
            if (linhaEscolhida != -1) {
                Vendedor vendedorEscolhido = (Vendedor)  tabelaVendedores.getValueAt(linhaEscolhida,2);
                new TelaEditarVendedor(TelaListaVendedores.this,gerente,vendedorEscolhido).setVisible(true);
                atualizarTabela();
            }
            else{
                JOptionPane.showMessageDialog(null, "Selecione um vendedor para editar!");
            }

        });
        removerVendedorButton.addActionListener(e -> {

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
    }
}
//Lista Ordenada de Vendedores por volume de vendas OK
//Cadastrar Vendedores OK
//Remover Vendedores OK
//Editar informaçoes de Vendedores OK
