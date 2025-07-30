package view;

import model.Pessoas.Funcionario;

import javax.swing.*;

public abstract class TelaMenu extends JFrame{
    private JButton logoutBtn;
    private JButton button1;
    private JButton button4;
    private JButton button2;
    private JButton button3;
    private JLabel titulo;
    private JLabel boasVindas;
    private JPanel painelMenu;

    public TelaMenu(JFrame parent,Funcionario funcionario) {
        super("Tela Menu");
        setContentPane(painelMenu);
        boasVindas.setText("Seja bem vindo " + funcionario.getNome().split(" ")[0]);
        setSize(900,600);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        button1.addActionListener(e -> {
            confirmaBtn1();
        });
        button2.addActionListener(e -> {
            confirmaBtn2();
        });
        button3.addActionListener(e -> {
            confirmaBtn3();
        });
        button4.addActionListener(e -> {
            confirmaBtn4();
        });
        logoutBtn.addActionListener(e -> {
            dispose();
            //Salvar arquivos
        });
    }
    //BUTTONS
    public JButton getButton1() {
        return button1;
    }
    public JButton getButton2() {
        return button2;
    }
    public JButton getButton3() {
        return button3;
    }
    public JButton getButton4() {
        return button4;
    }

    public JLabel getTitulo() {
        return titulo;
    }

    public abstract void confirmaBtn1();
    public abstract void confirmaBtn2();
    public abstract void confirmaBtn3();
    public abstract void confirmaBtn4();
}
