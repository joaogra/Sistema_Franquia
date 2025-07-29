package model;

public class Produto {

    private float preco;
    private int quantidadePedido;
    private int quantidadeEstoque;
    private String cod;
    private String nome;

    public Produto(float preco, int quantidadeEstoque, String cod, String nome){
        this.preco = preco;
        this.quantidadeEstoque = quantidadeEstoque;
        this.cod = cod;
        this.nome = nome;
        this.quantidadePedido = 0;
    }

    public String toString(){
        return nome;
    }
    //GETTERS
    public float getPreco() {return preco;}
    public int getQuantidadeEstoque() {return quantidadeEstoque;}
    public String getCod() {return cod;}
    public String getNome() {return nome;}
    public int  getQuantidadePedido() {return quantidadePedido;}
    //SETTERS
    public void setPreco(float preco) {this.preco = preco;}
    //tratar se nn tiver a quantidade desejada disponivel
    public void setQuantidadeEstoque(int quantidade) {
        this.quantidadeEstoque = quantidadeEstoque-quantidade;
    }
    public void setCod(String cod) {this.cod = cod;}
    public void setNome(String nome) {this.nome = nome;}
    //tratar excessao tbm
    public void setQuantidadePedido(int quantidade) {this.quantidadePedido = quantidade;}
}
