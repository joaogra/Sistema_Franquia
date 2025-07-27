package view.telasVendedor;

import model.Pedido;
import model.Pessoas.Cliente;
import model.Pessoas.Vendedor;
import model.Produto;

import javax.swing.*;
import javax.xml.crypto.Data;
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
    private JTextArea resumoTxT;
    private JButton finalizarButton;
    private JButton cancelarButton;
    private JButton voltarButton;
    private JComboBox pgtoCombo;
    private JPanel telaNovaVenda;
    private JLabel total;
    private JLabel totalTxt;
    private JTextField cpfTxt;
    private float totalAtual;
    private List<Produto> listProd;
    private Date horaPedido;
    private Integer codigoVenda;

    public TelaNovaVenda(JDialog pai, Vendedor vendedor) {

        setContentPane(telaNovaVenda);
        setTitle("Nova Venda");
        setMinimumSize(new Dimension(650,500));
        setModal(true);
        setLocationRelativeTo(null);
        adicionaItensPgto();
        adicionaItensProd();
        this.totalAtual = 0;
        totalTxt.setText("R$: 0.00");
        listProd = new ArrayList<>();
        this.codigoVenda = 0;


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
                adicionaVenda(vendedor);
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
        listProd.add(produtoAtual);
        Integer quantidade = Integer.valueOf(qtdTxt.getText());
        resumoTxT.append(produtoAtual.toString() + " x "  + quantidade +  " = R$: " + (produtoAtual.getPreco() * quantidade + "\n"));
        totalAtual+= produtoAtual.getPreco() * quantidade;
        totalTxt.setText("R$: " + Double.toString(totalAtual));
    }

    public void clean(){
        resumoTxT.setText("");
        this.totalAtual = 0;
        totalTxt.setText("R$: 0.00");
        clienteTxt.setText("");
        qtdTxt.setText("");
        cpfTxt.setText("");
    }

    private void adicionaVenda(Vendedor  vendedor){
        String cliente = clienteTxt.getText();
        String cpf = cpfTxt.getText();
        Cliente cliente1 = new Cliente(cliente,cpf);
        String formaPagamento = pgtoCombo.getSelectedItem().toString();
        Double valorVenda = Double.parseDouble(qtdTxt.getText());
        horaPedido = new Date();
        codigoVenda++;
        Pedido venda = new Pedido(codigoVenda.toString(),cliente1,horaPedido, formaPagamento, 0, listProd, valorVenda);
        vendedor.getHistoricoPedidos().put(codigoVenda.toString(), venda);
        JOptionPane.showMessageDialog(TelaNovaVenda.this, "Venda realizada com sucesso!");
        clean();
    }

}
