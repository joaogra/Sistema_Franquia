package view;

import controller.Arquivo;
import model.Pessoas.Dono;
import model.Pessoas.Funcionario;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public abstract class TelaMenu extends JFrame{
    private JButton logoutBtn;
    private JButton button1;
    private JButton button4;
    private JButton button2;
    private JButton button3;
    private JLabel titulo;
    private JLabel boasVindas;
    private JPanel painelMenu;

    public TelaMenu(JFrame parent, Dono dono,Funcionario funcionario) {
        super("Tela Menu");
        setContentPane(painelMenu);
        boasVindas.setText("Seja bem vindo " + funcionario.getNome().split(" ")[0]);
        setSize(900,600);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

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
            //Arquivo arquivo = new Arquivo();
            //arquivo.salvar(dono);
            dispose();
            //Salvar arquivos
        });
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e){
                //Arquivo arquivo = new Arquivo();
                //arquivo.salvar(dono);
                dispose();
            }
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
