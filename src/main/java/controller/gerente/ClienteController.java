package controller.gerente;

import model.Pedido;
import model.Pessoas.Cliente;
import model.Pessoas.Gerente;
import model.Pessoas.Vendedor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ClienteController {
    Gerente gerente;
    public ClienteController(Gerente gerente) {
        this.gerente = gerente;
    }
    public List<Cliente> listaClientes(){
        HashSet<Cliente> lista = new HashSet<>();
        for(Vendedor vendedor: gerente.getFranquia().getVendedores()){
            for(Pedido pedido : vendedor.getHistoricoPedidos().values()){
                lista.add(pedido.getCliente());
            }
        }
        return new ArrayList<>(lista);
    }
    public List<Object[]> listaOrdenadaClientesNumCompras(boolean modoOrdenar){
        List<Cliente> lista = listaClientes();
        if(modoOrdenar) {
            lista.sort((c1, c2) -> Integer.compare(c2.getQuantidadeCompras(), c1.getQuantidadeCompras()));
        }
        else {
            lista.sort((c1, c2) -> Float.compare(c2.getGastoTotal(), c1.getGastoTotal()));
        }
        List<Object[]> listaOrdenada = new ArrayList<>();
        for (Cliente cliente : lista) {
            float gastoMedio = cliente.getQuantidadeCompras() == 0 ? 0 : cliente.getGastoTotal()/cliente.getQuantidadeCompras();
            Object[] linha = {
                    cliente.getNome(),
                    cliente.getQuantidadeCompras(),
                    "R$" + String.format("%.2f" , cliente.getGastoTotal()),
                    "R$" + String.format("%.2f ",gastoMedio)
            };
            listaOrdenada.add(linha);
        }
        return listaOrdenada;
    }
}
