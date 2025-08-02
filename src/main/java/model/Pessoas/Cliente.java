package model.Pessoas;

public class Cliente extends Pessoa {
    private int quantidadeCompras;//vai ser por quantidade de pedidos feitos
    private float gastoTotal;
    public Cliente(String nome, String CPF){
        super(nome, CPF);
        quantidadeCompras = 0;
        gastoTotal = 0;
    }
    public void setQuantidadeCompras() {
        this.quantidadeCompras++;
    }
    public void setGastoTotal(float gasto) {
        this.gastoTotal = gastoTotal + gasto;
    }
    public int getQuantidadeCompras() {
        return quantidadeCompras;
    }
    public float getGastoTotal() {
        return gastoTotal;
    }
    @Override
    public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Cliente cliente = (Cliente) obj;
            return getCPF().equals(cliente.getCPF());
    }
    @Override
    public int hashCode() {
        return getCPF().hashCode();
    }
}
