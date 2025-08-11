package Main;

import Service.Estoque;
import View.Atendente;

public class Main {
    public static void main(String[] args) {
        Atendente atendente = new Atendente();
        Estoque estoque = new Estoque();

        int opcao;

        do {
            System.out.println("\n=== MENU PRINCIPAL ===");
            opcao = atendente.menuPrincipal();

            if (opcao != 0) {
                estoque.gerenciarEstoque(opcao, atendente);
            }

        } while (opcao != 0);

        System.out.println("Sistema encerrado");
    }
}