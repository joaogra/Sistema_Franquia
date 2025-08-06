package view.telasVendedor;

import Exceptions.CodigoPedidoJaCadastradoException;
import Exceptions.QuantidadeProdutoInsuficienteException;
import controller.gerente.PedidoController;
import controller.vendedor.VendedorOperaController;
import model.Pedido;
import model.Pessoas.Cliente;
import model.Pessoas.Vendedor;
import model.Produto;
import validadores.ValidadorCampoVazio;
import validadores.ValidadorEntradas;

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
    private Map<String, Integer> listProd;
    private Date horaPedido;
    private String codigoVenda;
    private VendedorOperaController vendedorOperaController;
    private Vendedor vendedor;

    public TelaNovaVenda(JFrame parent, Vendedor vendedor) {
        this.vendedor = vendedor;
        codVendaTxt.setEditable(false);
        setContentPane(telaNovaVenda);
        setTitle("Nova Venda");
        setMinimumSize(new Dimension(650,500));
        setModal(true);
        setLocationRelativeTo(null);
        adicionaItensPgto();
        adicionaItensProd();
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.totalAtual = 0.0;
        this.vendedorOperaController = new VendedorOperaController(vendedor);
        totalTxt.setText("0.00");
        listProd = new HashMap<>();
        String [] colunas = {
                "Código" ,"Produto", "Quantidade", "Preço", ""};
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
                finalizaVenda();
            }
        });
        removerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeLinha();
            }
        });
    }

    public TelaNovaVenda(JFrame pai, Vendedor vendedor, Pedido pedido) {
        this.vendedor = vendedor;
        setContentPane(telaNovaVenda);
        setTitle("Alteração de Pedido");
        titulo.setText("Alteração de Pedido");
        setMinimumSize(new Dimension(650,500));
        setModal(true);
        setLocationRelativeTo(null);
        adicionaItensPgto();
        adicionaItensProd();
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.totalAtual = pedido.getValVenda();
        vendedorOperaController = new VendedorOperaController(vendedor);
        clienteTxt.setText(pedido.getCliente().getNome());
        clienteTxt.setEditable(false);
        cpfTxt.setText(pedido.getCliente().getCPF());
        cpfTxt.setEditable(false);
        codVendaTxt.setText(pedido.getCod());
        codVendaTxt.setEditable(false);
        cancelarButton.setVisible(false);
        totalTxt.setText(totalAtual.toString());
        listProd = new HashMap<>(pedido.getMapProdutos());
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
                lancaAlteracaoPedido();
            }
        });

        removerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeLinha();
            }
        });
    }

    public void adicionaItensPgto(){
        pgtoCombo.addItem("Dinheiro");
        pgtoCombo.addItem("Cartão de Crédito");
        pgtoCombo.addItem("Cartão de Débito");
        pgtoCombo.addItem("PIX");
    }

    public void adicionaItensProd(){
        for(Produto p : vendedor.getFranquia().getEstoque().getProdutos()){
            produtoCombo.addItem(p);
        }
    }

    public void atualizaResumo(){
        Produto produtoAtual = (Produto) produtoCombo.getSelectedItem();
        int quantidade = Integer.valueOf(qtdTxt.getText());
        if(listProd.containsKey(produtoAtual.getCod())){
            listProd.put(produtoAtual.getCod(),listProd.get(produtoAtual.getCod()) + quantidade);
        }
        else {
            listProd.put(produtoAtual.getCod(), quantidade);
        }
        Object []  linha = {produtoAtual.getCod() ,produtoAtual.getNome(), quantidade, produtoAtual.getPreco(), produtoAtual};
        tabela.addRow(linha);
        table1.setModel(tabela);
        TableColumn colunaInv = table1.getColumnModel().getColumn(4);
        colunaInv.setMinWidth(0);
        colunaInv.setMaxWidth(0);
        colunaInv.setWidth(0);
        colunaInv.setPreferredWidth(0);
        totalAtual+= produtoAtual.getPreco() * quantidade;
        totalTxt.setText(Double.toString(totalAtual));
    }

    public void removeLinha(){
        int linhaSelecionada = table1.getSelectedRow();
        Produto produto = (Produto) tabela.getValueAt(linhaSelecionada, 4);
        if(linhaSelecionada == -1){
            JOptionPane.showMessageDialog(null, "Selecione uma linha para remover");
        }else{
            Object quantidade = tabela.getValueAt(linhaSelecionada, 2);
            String preco = tabela.getValueAt(linhaSelecionada, 3)
                    .toString().replace("R","").replace(",",".").replace("$","");
            Integer qtd = Integer.valueOf(quantidade.toString());
            Double precoO = Double.valueOf(preco.toString());
            Double subtotal = qtd * precoO;
            totalAtual -= Float.parseFloat(subtotal.toString());
            totalTxt.setText(totalAtual+"");
            tabela.removeRow(linhaSelecionada);
        }
        listProd.remove(produto.getCod());
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

        PedidoController pedidoController = new PedidoController(vendedor.getFranquia());
        List<Object[]> lista = pedidoController.listaProdutosUnicoPedido(pedido);
        for(Object[] linha : lista){
            tabela.addRow(linha);
        }


        table1.setModel(tabela);
        TableColumn colunaInv = table1.getColumnModel().getColumn(4);
        colunaInv.setMinWidth(0);
        colunaInv.setMaxWidth(0);
        colunaInv.setWidth(0);
        colunaInv.setPreferredWidth(0);

    }

    private void finalizaVenda() {
        ValidadorEntradas validadorCampoVazio = new ValidadorCampoVazio();
        String cliente = clienteTxt.getText().trim().toUpperCase();
        String cpf = cpfTxt.getText().trim().replaceAll("\\D", "");

        if (validadorCampoVazio.validar(cliente) || validadorCampoVazio.validar(cpf)) {
            JOptionPane.showMessageDialog(null, validadorCampoVazio.getMensagemErro());
            return;
        }

        Cliente cliente1;
        try {
            cliente1 = new Cliente(cliente, cpf);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, "CPF Inválido!");
            return;
        }

        String formaPagamento = pgtoCombo.getSelectedItem().toString();
        Double valorVenda = Double.parseDouble(totalTxt.getText());
        horaPedido = new Date();

        // Gerar código único e garantir que não exista
        do {
            codigoVenda = gerarCodigoUnico();
        } while (vendedorOperaController.jaExistePedido(codigoVenda));

        Pedido venda = new Pedido(codigoVenda, cliente1, horaPedido, formaPagamento, 0, listProd, valorVenda);
        try {
            if (vendedorOperaController.adicionarVenda(venda)) {
                JOptionPane.showMessageDialog(TelaNovaVenda.this, "Venda realizada com sucesso!");
                clean();
            }
        }
        catch (CodigoPedidoJaCadastradoException | QuantidadeProdutoInsuficienteException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
    }


    public void lancaAlteracaoPedido(){
        String cliente = clienteTxt.getText().trim();
        String cpf = cpfTxt.getText().trim().replaceAll("\\D","");
        codigoVenda = codVendaTxt.getText().trim();

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

        Pedido solicita = new Pedido(codigoVenda,cliente1,horaPedido, formaPagamento, 0, listProd, valorVenda);
        new TelaMotivo(this.vendedorOperaController, solicita).setVisible(true);
        dispose();
    }
    private String gerarCodigoUnico() {
        return String.valueOf(System.currentTimeMillis());
    }
}
