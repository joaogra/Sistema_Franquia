package org.example;

import controller.Arquivo;
import model.Pessoas.Dono;
import view.TelaLogin;

import java.util.ArrayList;
/*
Autores do Trabalho:
    Aluno: João Victor Granatto de Carvalho - 202465045A
    Aluno: Adrian Maciel Rocha - 202465001A

*/

public class Main {
    public static void main(String[] args) {
        //Dono dono = new Dono("adrian","14518498690","a@emial.com","admin",new ArrayList<>());
        Arquivo arquivo = new Arquivo();
        Dono dono = arquivo.inicializa();
        new TelaLogin(dono).setVisible(true);
        //arquivo.salvar(dono);
    }
}