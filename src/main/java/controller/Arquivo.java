package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.Pessoas.Dono;

import javax.swing.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Arquivo {

    public void salvar(Dono dono){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try(FileWriter writer = new FileWriter("out/artifacts/Sistema_Franquia_jar/data/funcionarios.json");){
            gson.toJson(dono,writer);
        } catch(IOException e){
            JOptionPane.showMessageDialog(null, "Erro ao salvar dados!");
        }
    }

    public Dono inicializa(){
        Gson gson = new Gson();

        try(FileReader reader = new FileReader("out/artifacts/Sistema_Franquia_jar/data/funcionarios.json")) {
            Dono dono = gson.fromJson(reader, Dono.class);
            //JOptionPane.showMessageDialog(null, "Dados inicializados com sucesso!");
            return dono;
        } catch(IOException e){
            //JOptionPane.showMessageDialog(null, "Erro ao inicializar dados!");
        }

    return null;
    }
}