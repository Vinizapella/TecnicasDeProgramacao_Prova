package View;
import Model.PainelControle;
import Model.Equipamento;
import Model.MotorEletrico;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Atendente {
    Scanner leia;

    public Atendente(){
        leia = new Scanner(System.in);
    }

    public int menuPrincipal(){
        int opcao = -1;
        try{
            System.out.println("1 - Cadastrar equipamento");
            System.out.println("2 - Listar todos");
            System.out.println("3 - Listar por tipo");
            System.out.println("4 - Pesquisar por código");
            System.out.println("5 - Remover por código");
            System.out.println("6 - Movimentar estoque");
            System.out.println("0 - Sair");
            opcao = leia.nextInt();
            leia.nextLine();
        } catch (InputMismatchException e){
            System.out.println("Erro tente novamente");
            leia.nextLine();
        }
        return opcao;
    }

    public int menuEscolhaEquipamento(){
        int opcaoEquipamento = -1;
        try{
            System.out.println("Escolha qual tipo de equipamento gostaria de cadastrar: ");
            System.out.println("1 - Painel de Controle");
            System.out.println("2 - Motor elétrico");
            opcaoEquipamento = leia.nextInt();
            leia.nextLine();
        }catch (InputMismatchException e){
            System.out.println("Erro tente novamente");
            leia.nextLine();
        }
        return opcaoEquipamento;
    }

    public String equipamentoCodigo() {
        while (true) {
            System.out.println("Digite o código do equipamento: ");
            String codigo = leia.nextLine();
            if (!codigo.isEmpty()) {
                return codigo;
            } else {
                System.out.println("Erro tente novamente");
            }
        }
    }

    public String equipamentoNome() {
        while (true) {
            System.out.println("Digite o nome do equipamento: ");
            String nome = leia.nextLine();
            if (!nome.isEmpty()) {
                return nome;
            } else {
                System.out.println("Erro tente novamente");
            }
        }
    }

    public int equipamentoQtd() {
        while (true) {
            try {
                System.out.println("Digite a quantidade do equipamento: ");
                int quantidade = leia.nextInt();
                leia.nextLine();
                if (quantidade >= 0) {
                    return quantidade;
                } else {
                    System.out.println("Quantidade deve ser positiva");
                }
            } catch (InputMismatchException e) {
                System.out.println("Erro tente novamente");
                leia.nextLine();
            }
        }
    }

    public double equipamentoPreco() {
        while (true) {
            try {
                System.out.println("Digite o preço do equipamento: ");
                double preco = leia.nextDouble();
                leia.nextLine();
                if (preco >= 0) {
                    return preco;
                } else {
                    System.out.println("O preço deve ser positivo");
                }
            } catch (InputMismatchException e) {
                System.out.println("Erro tente novamente");
                leia.nextLine();
            }
        }
    }

    public double motorPotencia() {
        while (true) {
            try {
                System.out.println("Digite a potência do motor: ");
                double potencia = leia.nextDouble();
                leia.nextLine();
                if (potencia >= 0) {
                    return potencia;
                } else {
                    System.out.println("A potência não pode ser negativa");
                }
            } catch (InputMismatchException e) {
                System.out.println("Erro tente novamente");
                leia.nextLine();
            }
        }
    }

    public String painelTensao() {
        while (true) {
            System.out.println("Digite a tensão do painel: ");
            String tensao = leia.nextLine();
            if (!tensao.isEmpty()) {
                return tensao;
            } else {
                System.out.println("Erro tente novamente");
            }
        }
    }

    public void visualizarMotor(MotorEletrico motor){
        System.out.println(motor.toString());
    }

    public void visualizarPainel(PainelControle painel){
        System.out.println(painel.toString());
    }

    public int escolhaTipo(){
        int escolhaTipoP = -1;
        boolean entradaValida = false;
        while(!entradaValida){
            try {
                System.out.println("Escolha o tipo de produto no qual deseja procurar: ");
                System.out.println("1 - Painel de Controle");
                System.out.println("2 - Motor elétrico");
                escolhaTipoP = leia.nextInt();
                leia.nextLine();
                if(escolhaTipoP == 1 || escolhaTipoP == 2){
                    entradaValida = true;
                } else {
                    System.out.println("Opção inválida. Escolha 1 ou 2.");
                }
            } catch (InputMismatchException e){
                System.out.println("Erro: Digite apenas números. Tente novamente.");
                leia.nextLine();
            }
        }
        return escolhaTipoP;
    }



    public void finalizarApp(){
        System.out.println("Finalizando o app");
    }
}
