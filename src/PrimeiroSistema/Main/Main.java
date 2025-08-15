package PrimeiroSistema.Main;

import PrimeiroSistema.Service.EstoqueService;
import PrimeiroSistema.View.InterfaceUsuario;

public class Main {
    public static void main(String[] args) {
        InterfaceUsuario ui = new InterfaceUsuario();
        EstoqueService estoque = new EstoqueService(ui);

        int opcao;
        do {
            opcao = ui.mostrarMenuPrincipal();
            estoque.gerenciarEstoque(opcao);
        } while (opcao != 0);

        ui.fechar();
    }
}