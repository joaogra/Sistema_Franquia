package model.Pessoas;

public class Funcionario extends Pessoa {

    private String email;
    private String senha;

    public Funcionario(String nome, String CPF, String email,String senha) {
        super(nome, CPF);
        this.email = email;
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() { return senha;}
}
