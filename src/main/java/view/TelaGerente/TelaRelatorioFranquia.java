package view.TelaGerente;

import controller.gerente.ClienteController;
import model.Franquia;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class TelaRelatorioFranquia extends JDialog {
    private JPanel painelRelatorio;
    private JLabel titulo;
    private JLabel totalVendasTxt;
    private JLabel lucroTxt;
    private JLabel mediaLucro;
    private JTable tabelaClientes;
    private JButton fecharBtn;
    private JLabel infoTabela;
    private JButton ordenaBtn;
    private JLabel nomeFranquiaLabel;
    private ClienteController clienteController;
    private boolean modoOrdenar;

    public TelaRelatorioFranquia(JFrame parent, Franquia franquia) {
        super(parent, "Relatório da Franquia", true);
        setContentPane(painelRelatorio);
        setSize(1280,720);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        modoOrdenar = true;

        tabelaClientes.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));//fonte cabeçalho
        tabelaClientes.setFont(new Font("SansSerif", Font.PLAIN, 13));//fonte itens
        nomeFranquiaLabel.setText( "Franquia: "+ franquia.getNome());

        clienteController = new ClienteController(franquia);

        int totalPedidos = franquia.getTotalPedidos();
        float faturamentoBruto = franquia.getFaturamentoBruto();
        float ticketMedio = franquia.getTicketMedio();
        totalVendasTxt.setText("Total de vendas realizados: " + totalPedidos);
        lucroTxt.setText("Soma total das vendas: R$" + String.format("%.2f", faturamentoBruto));
        mediaLucro.setText("Media de valor por venda: R$" +  String.format("%.2f", ticketMedio));

        infoTabela.setText("Clientes mais recorrentes(Ordenados pela QUANTIDADE DE COMPRAS efetuadas)");
        atualizarTabela();

        ordenaBtn.addActionListener(e -> {
            String info = modoOrdenar ? "Clientes mais recorrentes(Ordenados pela QUANTIDADE DE COMPRAS efetuadas)"
                    :"Clientes mais recorrentes(Ordenados pelo GASTO TOTAL nas compras efetuadas)";
            atualizarTabela();
            infoTabela.setText(info);
        });
        fecharBtn.addActionListener(e -> {
            this.dispose();
        });
    }
    public void atualizarTabela() {
        String[] colunas = {"Cliente", "Quantidade de Compras", "Gasto Total", "Ticket Médio"};
        DefaultTableModel tabela = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int linha, int coluna) {
                return false;
            }
        };

        List<Object[]> produtos = clienteController.listaOrdenadaClientesNumCompras(modoOrdenar);
        for (Object[] linha : produtos) {
            tabela.addRow(linha);
        }
        modoOrdenar = !modoOrdenar;

        tabelaClientes.setModel(tabela);
        tabelaClientes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabelaClientes.getTableHeader().setReorderingAllowed(false);

        //centraliza os itens
        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);
        centralizado.setVerticalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < tabelaClientes.getColumnCount(); i++) {
            tabelaClientes.getColumnModel().getColumn(i).setCellRenderer(centralizado);
        }

        //coloca fontes no cabecalho e nos itens
        tabelaClientes.setFont(new Font("SansSerif", Font.PLAIN, 13));
        tabelaClientes.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));
    }
}
