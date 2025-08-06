package view.telasDono;

import Exceptions.CPFJaCadastradoException;
import Exceptions.FranquiaNaoPossuiGerenteException;
import controller.Arquivo;
import controller.DonoController;
import model.*;
import model.Pessoas.Cliente;
import model.Pessoas.Dono;
import model.Pessoas.Gerente;
import model.Pessoas.Vendedor;
import view.TelaGerente.TelaRelatorioFranquia;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class TelaMenuDono extends JFrame {
    private JPanel painelMenu;
    private JLabel titulo;
    private JTable tabelaFranquias;
    private JButton cadastraFranquiaBtn;
    private JButton cadastrarGerenteBtn;
    private JButton removerFranquiaButton;
    private JButton removerGerenteBtn;
    private JButton resumoFinanceiroBtn;
    private JButton rankingVendedoresBtn;
    private JButton logoutBtn;
    private JLabel boasVindasTxt;
    private DonoController donoController;

    public TelaMenuDono(Dono dono) {
        super("Menu Dono");
        setContentPane(painelMenu);
        setSize(1000,640);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        boasVindasTxt.setText("Seja bem vindo " + dono.getNome().split(" ")[0]);
        this.donoController = new DonoController(dono);

        atualizarTabela();
        if(donoController.notificaGerenteFaltando()){
            JOptionPane.showMessageDialog(null, "Existem franquias sem gerente associado!");
        }
        cadastraFranquiaBtn.addActionListener(e -> {
            new TelaCadastroFranquia(this, dono).setVisible(true);
            atualizarTabela();
        });

        removerFranquiaButton.addActionListener(e -> {
            int linhaEscolhida = tabelaFranquias.getSelectedRow();
            if(linhaEscolhida != -1){
                int resposta = JOptionPane.showConfirmDialog(null,
                        "Tem certeza que deseja remover a franquia?", "Confirmação",
                        JOptionPane.YES_NO_OPTION);
                if (resposta == JOptionPane.YES_OPTION) {
                    Franquia franquia = (Franquia) tabelaFranquias.getValueAt(linhaEscolhida, 4);
                    donoController.removeFranquia(franquia);
                    atualizarTabela();
                    JOptionPane.showMessageDialog(null, "Franquia removida com sucesso!");
                }
            }
            else  {
                JOptionPane.showMessageDialog(null, "Escolha uma franquia!");
            }
        });

        cadastrarGerenteBtn.addActionListener(e -> {
            int linhaEscolhida = tabelaFranquias.getSelectedRow();
            if (linhaEscolhida == -1) {
                JOptionPane.showMessageDialog(null, "Escolha uma franquia!");
                return;
            }

            Franquia franquia = (Franquia) tabelaFranquias.getValueAt(linhaEscolhida, 4);
            if (franquia.getGerente() != null) {
                JOptionPane.showMessageDialog(null, "Esta franquia já possui um gerente!");
                return;
            }

            TelaCadastroGerente telaCadastroGerente = new TelaCadastroGerente(TelaMenuDono.this);
            telaCadastroGerente.setVisible(true);
            Gerente gerente = telaCadastroGerente.getGerenteCadastrado();
            if(gerente != null) {
                try {
                    donoController.cadastrarGerente(franquia, gerente);
                    atualizarTabela();
                    JOptionPane.showMessageDialog(null, "Gerente cadastrado com sucesso!");
                } catch (CPFJaCadastradoException exception) {
                    JOptionPane.showMessageDialog(null, exception.getMessage());
                }
            }
        });

        rankingVendedoresBtn.addActionListener(e -> {
            int linhaEscolhida = tabelaFranquias.getSelectedRow();
            if(linhaEscolhida != -1){
                Franquia franquia = (Franquia) tabelaFranquias.getValueAt(linhaEscolhida,4);
                new TelaRankingVendedores(TelaMenuDono.this,franquia).setVisible(true);
            }
            else{
                JOptionPane.showMessageDialog(null,"Escolha uma franquia!");
            }
        });

        removerGerenteBtn.addActionListener(e -> {
            int linhaEscolhida = tabelaFranquias.getSelectedRow();
            if(linhaEscolhida != -1){
                int resposta = JOptionPane.showConfirmDialog(null,
                        "Tem certeza que deseja remover o gerente?", "Confirmação",
                        JOptionPane.YES_NO_OPTION);
                if (resposta == JOptionPane.YES_OPTION) {
                    Franquia franquia = (Franquia) tabelaFranquias.getValueAt(linhaEscolhida, 4);
                    try {
                        donoController.removeGerente(franquia);
                        atualizarTabela();
                    }
                    catch (FranquiaNaoPossuiGerenteException exception) {
                        JOptionPane.showMessageDialog(null, exception.getMessage());
                    }
                }
            }
            else{
                JOptionPane.showMessageDialog(null, "Escolha uma franquia!");
            }
        });

        resumoFinanceiroBtn.addActionListener(e -> {
            int linhaEscolhida = tabelaFranquias.getSelectedRow();
            if(linhaEscolhida != -1) {
                Franquia franquia = (Franquia) tabelaFranquias.getValueAt(linhaEscolhida, 4);
                new TelaResumoFinanceiro(TelaMenuDono.this, franquia).setVisible(true);
            }
            else{
                JOptionPane.showMessageDialog(null,"Escolha uma franquia para vizualizar o relatório!");
            }
        });

        logoutBtn.addActionListener(e -> {
            Arquivo arquivo = new Arquivo();
            arquivo.salvar(dono);
            this.dispose();
        });

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e){
                Arquivo arquivo = new Arquivo();
                arquivo.salvar(dono);
                dispose();
            }
        });
    }

    /*public static void main(String[] args) {
        Cliente c1 = new Cliente("adrian", "14518498690");
        Produto p1 = new Produto( 10.0f, 51,"001", "Produto A");
        Produto p2 = new Produto( 20.5f, 33,"002","Produto B");
        Estoque estoque = new Estoque();
        estoque.getEstoque().put(p1.getCod(), p1);
        estoque.getEstoque().put(p2.getCod(), p2);
        Vendedor v1 = new Vendedor("Carlos Silva","123.456.789-09", "carlos@empresa.com", "senha123",null);
        Pedido pedido1 = new Pedido("001",c1 ,new Date(),"din",0.05f, Map.of(p1.getCod(),15,p2.getCod(),5),1000d);
        v1.adicionaPedido(pedido1);
        Vendedor v2 = new Vendedor("Marina Costa", "987.654.321-00", "marina@empresa.com", "senha456",null);
        Vendedor v3 = new Vendedor("João Pereira", "111.444.777-35", "joao@empresa.com", "senha789",null);
        v1.setNumVendas(25);
        v2.setNumVendas(0);
        v3.setNumVendas(40);
        Gerente gerente1 =  new Gerente("Adrian","14518498690" ,"adra","adrian");
        Endereco endereco = new Endereco();
        endereco.setCep("0000000");
        Franquia franquia = new Franquia("nome",endereco,gerente1, List.of(v1,v2,v3),estoque);
        gerente1.associaGerenteFranquia(franquia);
        Dono dono1 = new Dono("Pericles","14518498690","@blalbalba","1234567",List.of(franquia));
        new TelaMenuDono(dono1).setVisible(true);
    }*/
    public void atualizarTabela() {
        String[] colunas = {"Franquia", "Endereço", "Gerente", "Lucro","Franquia"};
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
