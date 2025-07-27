package model;

public class Produto {

    private float preco;
    private int quantidade;
    private String cod;
    private String nome;

    public Produto(float preco, int quantidade, String cod, String nome){
        this.preco = preco;
        this.quantidade = quantidade;
        this.cod = cod;
        this.nome = nome;
    }

    public String toString(){
        return nome;
    }

    public float getPreco() {return preco;}

    public int getQuantidade() {return quantidade;}

    public String getCod() {return cod;}

    public String getNome() {return nome;}

    public void setPreco(float preco) {this.preco = preco;}

    public void setQuantidade(int quantidade) {this.quantidade = quantidade;}

    public void setCod(String cod) {this.cod = cod;}

    public void setNome(String nome) {this.nome = nome;}

}
