package PrimeiroSistema.View;

import PrimeiroSistema.Model.*;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class InterfaceUsuario {
    private Scanner scanner;

    public InterfaceUsuario() {
        this.scanner = new Scanner(System.in);
    }

    public int mostrarMenuPrincipal() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("    SISTEMA DE CONTROLE DE EQUIPAMENTOS WEG");
        System.out.println("=".repeat(50));
        System.out.println("1 - Cadastrar equipamento");
        System.out.println("2 - Listar todos os equipamentos");
        System.out.println("3 - Listar por tipo");
        System.out.println("4 - Pesquisar por código");
        System.out.println("5 - Remover equipamento");
        System.out.println("6 - Movimentar estoque");
        System.out.println("0 - Sair");
        System.out.println("=".repeat(50));

        return lerOpcaoMenu(0, 6);
    }

    public int mostrarMenuTipoEquipamento() {
        System.out.println("\n=== ESCOLHA O TIPO DE EQUIPAMENTO ===");
        System.out.println("1 - Painel de Controle");
        System.out.println("2 - Motor Elétrico");

        return lerOpcaoMenu(1, 2);
    }

    public int mostrarMenuMovimentacao() {
        System.out.println("\n=== MOVIMENTAÇÃO DE ESTOQUE ===");
        System.out.println("1 - Adicionar unidades");
        System.out.println("2 - Retirar unidades");

        return lerOpcaoMenu(1, 2);
    }

    public Equipamento criarEquipamento(int tipo) {
        System.out.println("\n=== CADASTRO DE EQUIPAMENTO ===");

        String codigo = lerTexto("Código do equipamento: ");
        String nome = lerTexto("Nome do equipamento: ");
        int quantidade = lerInteiro("Quantidade em estoque: ");
        double preco = lerDouble("Preço unitário: R$ ");

        if (tipo == 1) {
            String tensao = lerTexto("Tensão do painel: ");
            return new PainelControle(codigo, nome, quantidade, preco, tensao);
        } else {
            double potencia = lerDouble("Potência do motor (HP): ");
            return new MotorEletrico(codigo, nome, quantidade, preco, potencia);
        }
    }

    public String lerCodigo() {
        return lerTexto("Digite o código do equipamento: ");
    }

    public int lerQuantidade() {
        return lerInteiro("Digite a quantidade: ");
    }

    public void mostrarMensagem(String mensagem) {
        System.out.println(mensagem);
    }

    public void mostrarSucesso(String mensagem) {
        System.out.println(" " + mensagem);
    }

    public void mostrarErro(String mensagem) {
        System.out.println(" " + mensagem);
    }

    public void mostrarEquipamento(Equipamento equipamento) {
        System.out.println("\n" + equipamento.toString());
        System.out.println("-".repeat(40));
    }

    public void mostrarListaEquipamentos(List<Equipamento> equipamentos, String titulo) {
        if (equipamentos.isEmpty()) {
            mostrarMensagem("Nenhum equipamento encontrado.");
            return;
        }

        mostrarMensagem("\n=== " + titulo + " ===");
        for (Equipamento eq : equipamentos) {
            mostrarEquipamento(eq);
        }
    }

    public void fechar() {
        scanner.close();
        System.out.println("Sistema finalizado!");
    }

    private String lerTexto(String mensagem) {
        String texto = "";
        while (texto.isEmpty()) {
            System.out.print(mensagem);
            texto = scanner.nextLine().trim();
            if (texto.isEmpty()) {
                System.out.println("Erro: Campo não pode estar vazio!");
            }
        }
        return texto;
    }

    private int lerInteiro(String mensagem) {
        int numero = -1;
        boolean valido = false;

        while (!valido) {
            try {
                System.out.print(mensagem);
                numero = scanner.nextInt();
                scanner.nextLine();
                if (numero >= 0) {
                    valido = true;
                } else {
                    System.out.println("Erro: O valor deve ser positivo!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Erro: Digite apenas números!");
                scanner.nextLine();
            }
        }
        return numero;
    }

    private double lerDouble(String mensagem) {
        double numero = -1;
        boolean valido = false;

        while (!valido) {
            try {
                System.out.print(mensagem);
                numero = scanner.nextDouble();
                scanner.nextLine();
                if (numero >= 0) {
                    valido = true;
                } else {
                    System.out.println("Erro: O valor deve ser positivo!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Erro: Digite apenas números!");
                scanner.nextLine();
            }
        }
        return numero;
    }

    private int lerOpcaoMenu(int min, int max) {
        int opcao = -1;
        boolean valido = false;

        while (!valido) {
            try {
                System.out.print("Escolha uma opção: ");
                opcao = scanner.nextInt();
                scanner.nextLine();
                if (opcao >= min && opcao <= max) {
                    valido = true;
                } else {
                    System.out.println("Erro: Opção inválida!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Erro: Digite apenas números!");
                scanner.nextLine();
            }
        }
        return opcao;
    }
}