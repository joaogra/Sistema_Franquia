package view.telasVendedor;

import controller.vendedor.VendedorOperaController;
import model.Pedido;
import model.Pessoas.Cliente;
import model.Pessoas.Vendedor;
import model.Produto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
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
    private JList <Produto>listaProdutos;
    private DefaultTableModel tabela;


    private float totalAtual;
    private List<Produto> listProd;
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
        this.totalAtual = 0;
        vendedorOperaController = new VendedorOperaController(vendedor);
        totalTxt.setText("0.00");
        listProd = new ArrayList<>();
        listaProdutos = new JList<>();
        String [] colunas = {
                "Produto", "Quantidade", "Subtotal"
        };
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
        float subTotal = produtoAtual.getPreco() * quantidade;
        Object []  linha = {produtoAtual.getNome(), quantidade, subTotal};
        tabela.addRow(linha);
        table1.setModel(tabela);
        totalAtual+= produtoAtual.getPreco() * quantidade;
        totalTxt.setText(Double.toString(totalAtual));
    }

    public void clean(){
        this.totalAtual = 0;
        totalTxt.setText("R$: 0.00");
        clienteTxt.setText("");
        qtdTxt.setText("");
        cpfTxt.setText("");
        codVendaTxt.setText("");
    }

    private void finalizaVenda(Vendedor  vendedor){
        String cliente = clienteTxt.getText();
        String cpf = cpfTxt.getText();
        codigoVenda = codVendaTxt.getText();
        if(cliente.isBlank() || cpf.isBlank() || codigoVenda.isBlank() ){

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
