package view.telasVendedor;

import controller.gerente.PedidoController;
import controller.vendedor.VendedorOperaController;
import model.Pedido;
import model.Pessoas.Cliente;
import model.Pessoas.Vendedor;
import model.Produto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class TelaNovaVenda extends JDialog {
    private JTextField clienteTxt;
    private JComboBox <Produto >produtoCombo;
    private JTextField qtdTxt;
    private JButton adicionarButton;
    private JButton finalizarButton;
    private JButton cancelarButton;
    private JButton voltarButton;
    private JComboBox pgtoCombo;
    private JPanel telaNovaVenda;
    private JLabel total;
    private JLabel totalTxt;
    private JTextField cpfTxt;
    private JTextField codVendaTxt;
    private JTable table1;
    private JButton removerBtn;
    private JLabel titulo;
    private DefaultTableModel tabela;
    private JScrollPane scrollPane;


    private Double totalAtual;
    private Map<Produto, Integer> listProd;
    private Date horaPedido;
    private String codigoVenda;
    private VendedorOperaController vendedorOperaController;

    public TelaNovaVenda(JDialog pai, Vendedor vendedor) {

        setContentPane(telaNovaVenda);
        setTitle("Nova Venda");
        setMinimumSize(new Dimension(650,500));
        setModal(true);
        setLocationRelativeTo(null);
        adicionaItensPgto();
        adicionaItensProd();
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.totalAtual = 0.0;
        vendedorOperaController = new VendedorOperaController(vendedor);
        totalTxt.setText("0.00");
        listProd = new HashMap<>();
        String [] colunas = {
                "Produto", "Quantidade", "Subtotal", ""};
        tabela = new DefaultTableModel(colunas, 0){
            @Override
            public boolean isCellEditable(int row, int column) {return false;}

        };

                adicionarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizaResumo();

            }
        });
        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clean();
            }
        });
        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        finalizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                finalizaVenda(vendedor);
            }
        });
        removerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeLinha();
            }
        });
    }

    public TelaNovaVenda(JDialog pai, Vendedor vendedor, Pedido pedido) {
        setContentPane(telaNovaVenda);
        setTitle("Alteração de Pedido");
        titulo.setText("Alteração de Pedido");
        setMinimumSize(new Dimension(650,500));
        setModal(true);
        setLocationRelativeTo(null);
        adicionaItensPgto();
        adicionaItensProd();
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.totalAtual = pedido.getTotal();
        vendedorOperaController = new VendedorOperaController(vendedor);
        PedidoController pedidoController = new PedidoController(pedido);
        clienteTxt.setText(pedido.getCliente().getNome());
        clienteTxt.setEditable(false);
        cpfTxt.setText(pedido.getCliente().getCPF());
        cpfTxt.setEditable(false);
        codVendaTxt.setText(pedido.getCod());
        codVendaTxt.setEditable(false);

        totalTxt.setText(totalAtual.toString());
        listProd = pedido.getMapaPedidos();
        String [] colunas = {
               "Código" ,"Produto", "Quantidade", "Preço", ""};
        tabela = new DefaultTableModel(colunas, 0){
            @Override
            public boolean isCellEditable(int row, int column) {return false;}

        };

        preencheTabela(pedido);

        finalizarButton.setText("Solicitar Alteração");

        adicionarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizaResumo();

            }
        });
        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clean();
            }
        });
        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        finalizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                finalizaVenda(vendedor);
            }
        });
        removerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeLinha();
            }
        });
    }



    public static void main(String[] args) {

    }

    public void adicionaItensPgto(){
        pgtoCombo.addItem("Dinheiro");
        pgtoCombo.addItem("Cartão de Crédito");
        pgtoCombo.addItem("Cartão de Débito");
        pgtoCombo.addItem("PIX");
    }

    public void adicionaItensProd(){
        Produto produto = new Produto(15,15,"001","Do");
        Produto produto1 = new Produto(5,15,"002","Re");
        Produto produto2 = new Produto(10,15,"003","Mi");

        produtoCombo.addItem(produto);
        produtoCombo.addItem(produto1);
        produtoCombo.addItem(produto2);
    }

    public void atualizaResumo(){
        Produto produtoAtual = (Produto) produtoCombo.getSelectedItem();
        Integer quantidade = Integer.valueOf(qtdTxt.getText());
        listProd.put(produtoAtual, quantidade);
        float subTotal = produtoAtual.getPreco() * quantidade;
        Object []  linha = {produtoAtual.getNome(), quantidade, subTotal, produtoAtual};
        tabela.addRow(linha);
        table1.setModel(tabela);
        TableColumn colunaInv = table1.getColumnModel().getColumn(3);
        colunaInv.setMinWidth(0);
        colunaInv.setMaxWidth(0);
        colunaInv.setWidth(0);
        colunaInv.setPreferredWidth(0);
        totalAtual+= produtoAtual.getPreco() * quantidade;
        totalTxt.setText(Double.toString(totalAtual));
    }

    public void removeLinha(){
        int linhaSelecionada = table1.getSelectedRow();
        Object produto = tabela.getValueAt(linhaSelecionada, 3);
        listProd.remove(produto);

        if(linhaSelecionada == -1){
            JOptionPane.showMessageDialog(null, "Selecione uma linha para remover");
        }else{
            Object valorSubtotal = tabela.getValueAt(linhaSelecionada, 2);
            totalAtual -= Float.parseFloat(valorSubtotal.toString());
            totalTxt.setText(totalAtual+"");
            tabela.removeRow(linhaSelecionada);
        }
    }

    public void clean(){
        this.totalAtual = 0.0;
        totalTxt.setText("R$: 0.00");
        clienteTxt.setText("");
        qtdTxt.setText("");
        cpfTxt.setText("");
        codVendaTxt.setText("");
        tabela.setRowCount(0);
    }

    public void preencheTabela(Pedido pedido){

        PedidoController pedidoController = new PedidoController();
        List<Object[]> lista = pedidoController.listaProdutosUnicoPedido(pedido);
        for(Object[] linha : lista){
            tabela.addRow(linha);
        }


        table1.setModel(tabela);
    }

    private void finalizaVenda(Vendedor  vendedor){
        String cliente = clienteTxt.getText();
        String cpf = cpfTxt.getText();
        codigoVenda = codVendaTxt.getText();
        if(cliente.isBlank() || cpf.isBlank() || codigoVenda.isBlank() ){
            JOptionPane.showMessageDialog(null, "Preencha os campos");
            return;
        }

        Cliente cliente1;
        try {
             cliente1 = new Cliente(cliente, cpf);
        } catch(IllegalArgumentException e){
            JOptionPane.showMessageDialog(null,"CPF Inválido!");
            return;
        }
        String formaPagamento = pgtoCombo.getSelectedItem().toString();
        Double valorVenda = Double.parseDouble(totalTxt.getText());
        horaPedido = new Date();

        Pedido venda = new Pedido(codigoVenda,cliente1,horaPedido, formaPagamento, 0, listProd, valorVenda);
        vendedorOperaController.adicionarVenda(venda);
        JOptionPane.showMessageDialog(TelaNovaVenda.this, "Venda realizada com sucesso!");
        clean();
    }

}
