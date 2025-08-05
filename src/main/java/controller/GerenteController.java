package controller;

import model.Pessoas.Dono;
import model.Pessoas.Gerente;
import view.TelaGerente.TelaMenuGerente;

public class GerenteController extends FuncionarioController {
    private Gerente gerente;
    private Dono dono;
    public GerenteController(Dono dono,Gerente gerente) {
        this.gerente = gerente;
        this.dono = dono;
    }
    @Override
    protected void abrirTela() {
        new TelaMenuGerente(dono,gerente,dono.getListaFranquias().getFirst()).setVisible(true);

    }
}
