package view.TelaGerente;

import model.Pessoas.Gerente;
import model.Produto;

import javax.swing.*;

public abstract class TelaProduto extends JDialog {
    private JPanel painelNovoProduto;
    private JLabel titulo;
    private JTextField nomeTxt;
    private JTextField codigoTxt;
    private JTextField precoTxt;
    private JTextField quantidadeTxt;

    private JLabel nome;
    private JLabel codigo;
    private JLabel preco;
    private JLabel quantidade;
    private JButton confirmarBtn;
    private JButton fecharButton;
    private JButton limparBtn;
    private Gerente gerente;
    public TelaProduto(JDialog parent,String titulo, Gerente gerente) {
        super(parent,titulo,true);
        this.gerente = gerente;
        setContentPane(painelNovoProduto);
        setSize(800,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        confirmarBtn.addActionListener(event ->{
            //validar textos
            confirmar();
        });

        limparBtn.addActionListener(event ->{
            clean();
        });
        fecharButton.addActionListener(event ->{
            this.dispose();
        });

    }
    public void clean(){
        nomeTxt.setText("");
        codigoTxt.setText("");
        precoTxt.setText("");
        quantidadeTxt.setText("");
    }
    //JLABELS
    public JLabel getNome() {
        return nome;
    }
    public JLabel getCodigo() {
        return codigo;
    }
    public JLabel getPreco() {
        return preco;
    }
    public JLabel getQuantidade() {
        return quantidade;
    }
    public JLabel getTitulo() {
        return titulo;
    }
    public Gerente getGerente() {
        return gerente;
    }
    //TEXTOS
    public JTextField getNomeTxt() {
        return nomeTxt;
    }
    public JTextField getCodigoTxt() {
        return codigoTxt;
    }
    public JTextField getPrecoTxt() {
        return precoTxt;
    }
    public JTextField getQuantidadeTxt() {
        return quantidadeTxt;
    }
    public abstract void confirmar();
}
