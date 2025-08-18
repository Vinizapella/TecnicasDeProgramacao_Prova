package SegundoSistema.View;

import SegundoSistema.Model.*;
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
        System.out.println("      SISTEMA DE CONTROLE WEG 2.0");
        System.out.println("=".repeat(50));
        System.out.println("1 - Cadastrar Equipamento");
        System.out.println("2 - Listar Todos os Equipamentos");
        System.out.println("3 - Listar Equipamentos por Tipo");
        System.out.println("4 - Pesquisar Equipamento por Código");
        System.out.println("5 - Remover Equipamento por Código");
        System.out.println("6 - Movimentar Estoque (Adicionar/Retirar Quantidade)");
        System.out.println();
        System.out.println("7 - Relatórios de Estoque");
        System.out.println("8 - Busca Avançada por Nome");
        System.out.println("9 - Busca Avançada por Preço");
        System.out.println();
        System.out.println("0 - Sair");
        System.out.println("=".repeat(50));

        return lerOpcaoMenu(0, 9);
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

    public String lerParteNome() {
        return lerTexto("Digite parte do nome do equipamento: ");
    }

    public double lerPrecoMinimo() {
        return lerDouble("Digite o preço mínimo: R$ ");
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

    public void mostrarEquipamentoEncontrado(Equipamento equipamento) {
        System.out.println("\n=== EQUIPAMENTO ENCONTRADO ===");
        mostrarEquipamento(equipamento);
    }

    public void mostrarSucessoMovimentacao(String operacao, int quantidadeAtual) {
        System.out.println(" Estoque " + operacao + "! Quantidade atual: " + quantidadeAtual);
    }

    public void mostrarErroEstoqueInsuficiente(int quantidadeDisponivel) {
        System.out.println(" Erro: Estoque insuficiente! Disponível: " + quantidadeDisponivel);
    }

    public void mostrarListaEquipamentos(List<Equipamento> equipamentos, String titulo) {
        if (equipamentos.isEmpty()) {
            System.out.println("Nenhum equipamento encontrado.");
            return;
        }

        System.out.println("\n=== " + titulo + " ===");
        for (Equipamento eq : equipamentos) {
            mostrarEquipamento(eq);
        }
    }

    public void mostrarRelatorios(int totalEquipamentos, int totalItens,
                                  Equipamento maiorPreco, Equipamento maiorQuantidade,
                                  List<Equipamento> estoqueBaixo) {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("           RELATÓRIOS DE ESTOQUE");
        System.out.println("=".repeat(50));

        System.out.println("Quantidade total de equipamentos cadastrados: " + totalEquipamentos);
        System.out.println("Quantidade total de itens em estoque: " + totalItens);

        if (maiorPreco != null) {
            System.out.println("\nEQUIPAMENTO COM MAIOR PREÇO:");
            mostrarEquipamento(maiorPreco);
        }

        if (maiorQuantidade != null) {
            System.out.println("\nEQUIPAMENTO COM MAIOR QUANTIDADE:");
            mostrarEquipamento(maiorQuantidade);
        }

        if (!estoqueBaixo.isEmpty()) {
            mostrarListaEquipamentos(estoqueBaixo, "EQUIPAMENTOS COM ESTOQUE BAIXO (< 5 unidades)");
        } else {
            System.out.println("\nNenhum equipamento com estoque baixo!");
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
                System.out.print("Digite a opção desejada: ");
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