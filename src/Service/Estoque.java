package Service;
import Model.Equipamento;
import Model.MotorEletrico;
import Model.PainelControle;
import View.Atendente;

import java.util.ArrayList;
import java.util.List;

public class Estoque {
    List<Equipamento> listaEquipamento;
    List<MotorEletrico> listaMotor;
    List<PainelControle> listaPainel;

    public Estoque() {
        listaEquipamento = new ArrayList<>();
        listaMotor = new ArrayList<>();
        listaPainel = new ArrayList<>();
    }

    public void gerenciarEstoque(int opcao, Atendente atendente) {
        switch (opcao) {
            case 1:
                int opcaoEquipamento = atendente.menuEscolhaEquipamento();
                cadastrarEquipamento(opcaoEquipamento, atendente);
                break;
            case 2:
                for(Equipamento equipamento : listaEquipamento){
                    if(equipamento instanceof MotorEletrico){
                        atendente.visualizarMotor((MotorEletrico) equipamento);
                    } else if(equipamento instanceof PainelControle){
                        atendente.visualizarPainel((PainelControle) equipamento);
                    }
                }
                break;
            case 3:
                int escolhaTipoP = atendente.menuEscolhaEquipamento();

        }
    }


    private void cadastrarEquipamento(int opcaoEquipamento, Atendente atendente) {
        String codigo, nome;
        int qtd;
        double preco;

        switch (opcaoEquipamento) {
            case 1:
                codigo = atendente.equipamentoCodigo();
                nome = atendente.equipamentoNome();
                qtd = atendente.equipamentoQtd();
                preco = atendente.equipamentoPreco();
                double potencia = atendente.motorPotencia();

                MotorEletrico motor = new MotorEletrico(codigo, nome, qtd, preco, potencia);
                listaMotor.add(motor);
                listaEquipamento.add(motor);
                System.out.println("Motor elétrico cadastrado com sucesso!");
                break;

            case 2:
                codigo = atendente.equipamentoCodigo();
                nome = atendente.equipamentoNome();
                qtd = atendente.equipamentoQtd();
                preco = atendente.equipamentoPreco();
                String tensao = atendente.painelTensao();

                PainelControle painel = new PainelControle(codigo, nome, qtd, preco, tensao);
                listaPainel.add(painel);
                listaEquipamento.add(painel);
                System.out.println("Painel de controle cadastrado com sucesso!");
                break;

            default:
                System.out.println("Opção de equipamento inválida!");
                break;
        }
    }


}
