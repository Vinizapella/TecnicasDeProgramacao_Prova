package SegundoSistema.Service;

import SegundoSistema.Model.*;
import SegundoSistema.View.InterfaceUsuario;
import java.util.ArrayList;
import java.util.List;

public class EstoqueService {
    private List<Equipamento> equipamentos;
    private InterfaceUsuario ui;

    public EstoqueService(InterfaceUsuario ui) {
        this.equipamentos = new ArrayList<>();
        this.ui = ui;
    }

    public void gerenciarEstoque(int opcao) {
        switch (opcao) {
            case 1:
                cadastrarEquipamento();
                break;
            case 2:
                listarTodos();
                break;
            case 3:
                listarPorTipo();
                break;
            case 4:
                pesquisarPorCodigo();
                break;
            case 5:
                removerEquipamento();
                break;
            case 6:
                movimentarEstoque();
                break;
            case 7:
                mostrarRelatorios();
                break;
            case 8:
                buscaPorNome();
                break;
            case 9:
                buscaPorPreco();
                break;
            case 0:
                break;
            default:
                ui.mostrarErro("Opção inválida!");
        }
    }

    private void cadastrarEquipamento() {
        int tipo = ui.mostrarMenuTipoEquipamento();
        Equipamento equipamento = ui.criarEquipamento(tipo);

        if (adicionarEquipamento(equipamento)) {
            ui.mostrarSucesso("Equipamento cadastrado com sucesso!");
        } else {
            ui.mostrarErro("Erro: Já existe um equipamento com este código!");
        }
    }

    private void listarTodos() {
        ui.mostrarListaEquipamentos(equipamentos, "TODOS OS EQUIPAMENTOS");
    }

    private void listarPorTipo() {
        int tipo = ui.mostrarMenuTipoEquipamento();
        List<Equipamento> equipamentosTipo = listarPorTipo(tipo);

        String titulo = tipo == 1 ? "PAINÉIS DE CONTROLE" : "MOTORES ELÉTRICOS";
        ui.mostrarListaEquipamentos(equipamentosTipo, titulo);
    }

    private void pesquisarPorCodigo() {
        String codigo = ui.lerCodigo();
        Equipamento equipamento = buscarPorCodigo(codigo);

        if (equipamento != null) {
            ui.mostrarEquipamentoEncontrado(equipamento);
        } else {
            ui.mostrarErro("Equipamento não encontrado!");
        }
    }

    private void removerEquipamento() {
        String codigo = ui.lerCodigo();

        if (removerPorCodigo(codigo)) {
            ui.mostrarSucesso("Equipamento removido com sucesso!");
        } else {
            ui.mostrarErro("Equipamento não encontrado!");
        }
    }

    private void movimentarEstoque() {
        String codigo = ui.lerCodigo();
        Equipamento equipamento = buscarPorCodigo(codigo);

        if (equipamento == null) {
            ui.mostrarErro("Equipamento não encontrado!");
            return;
        }

        int tipoMovimentacao = ui.mostrarMenuMovimentacao();
        int quantidade = ui.lerQuantidade();

        if (tipoMovimentacao == 1) {
            if (adicionarEstoque(codigo, quantidade)) {
                ui.mostrarSucessoMovimentacao("adicionado", equipamento.getQuantidade());
            } else {
                ui.mostrarErro("Erro ao adicionar estoque!");
            }
        } else {
            if (retirarEstoque(codigo, quantidade)) {
                ui.mostrarSucessoMovimentacao("retirado", equipamento.getQuantidade());
            } else {
                ui.mostrarErroEstoqueInsuficiente(equipamento.getQuantidade());
            }
        }
    }

    private void mostrarRelatorios() {
        int quantidadeTotalEquipamentos = equipamentos.size();
        int quantidadeTotalItens = calcularQuantidadeTotalItens();
        Equipamento equipamentoMaiorPreco = buscarEquipamentoMaiorPreco();
        Equipamento equipamentoMaiorQuantidade = buscarEquipamentoMaiorQuantidade();
        List<Equipamento> equipamentosEstoqueBaixo = buscarEquipamentosEstoqueBaixo();

        ui.mostrarRelatorios(quantidadeTotalEquipamentos, quantidadeTotalItens,
                equipamentoMaiorPreco, equipamentoMaiorQuantidade,
                equipamentosEstoqueBaixo);
    }

    private void buscaPorNome() {
        String parteNome = ui.lerParteNome();
        List<Equipamento> equipamentosEncontrados = buscarPorParteNome(parteNome);

        if (!equipamentosEncontrados.isEmpty()) {
            String titulo = "BUSCA POR NOME: \"" + parteNome + "\"";
            ui.mostrarListaEquipamentos(equipamentosEncontrados, titulo);
        } else {
            ui.mostrarErro("Nenhum equipamento encontrado com o nome informado!");
        }
    }

    private void buscaPorPreco() {
        double precoMinimo = ui.lerPrecoMinimo();
        List<Equipamento> equipamentosEncontrados = buscarPorPrecoMinimo(precoMinimo);

        if (!equipamentosEncontrados.isEmpty()) {
            String titulo = "BUSCA POR PREÇO ACIMA DE R$ " + String.format("%.2f", precoMinimo);
            ui.mostrarListaEquipamentos(equipamentosEncontrados, titulo);
        } else {
            ui.mostrarErro("Nenhum equipamento encontrado com o preço informado!");
        }
    }

    private boolean adicionarEquipamento(Equipamento equipamento) {
        if (equipamento == null) {
            return false;
        }

        for (Equipamento eq : equipamentos) {
            if (eq.getCodigo().equalsIgnoreCase(equipamento.getCodigo())) {
                return false;
            }
        }

        equipamentos.add(equipamento);
        return true;
    }

    private Equipamento buscarPorCodigo(String codigo) {
        if (codigo == null || codigo.trim().isEmpty()) {
            return null;
        }

        for (Equipamento equipamento : equipamentos) {
            if (equipamento.getCodigo().equalsIgnoreCase(codigo.trim())) {
                return equipamento;
            }
        }
        return null;
    }

    private boolean removerPorCodigo(String codigo) {
        if (codigo == null || codigo.trim().isEmpty()) {
            return false;
        }

        for (int i = 0; i < equipamentos.size(); i++) {
            if (equipamentos.get(i).getCodigo().equalsIgnoreCase(codigo.trim())) {
                equipamentos.remove(i);
                return true;
            }
        }
        return false;
    }

    private List<Equipamento> listarPorTipo(int tipo) {
        List<Equipamento> resultado = new ArrayList<>();

        for (Equipamento eq : equipamentos) {
            if (tipo == 1 && eq instanceof PainelControle) {
                resultado.add(eq);
            } else if (tipo == 2 && eq instanceof MotorEletrico) {
                resultado.add(eq);
            }
        }

        return resultado;
    }

    private boolean adicionarEstoque(String codigo, int quantidade) {
        Equipamento equipamento = buscarPorCodigo(codigo);
        if (equipamento != null && quantidade > 0) {
            equipamento.setQuantidade(equipamento.getQuantidade() + quantidade);
            return true;
        }
        return false;
    }

    private boolean retirarEstoque(String codigo, int quantidade) {
        Equipamento equipamento = buscarPorCodigo(codigo);
        if (equipamento != null && quantidade > 0 && equipamento.getQuantidade() >= quantidade) {
            equipamento.setQuantidade(equipamento.getQuantidade() - quantidade);
            return true;
        }
        return false;
    }

    private int calcularQuantidadeTotalItens() {
        int total = 0;
        for (Equipamento eq : equipamentos) {
            total += eq.getQuantidade();
        }
        return total;
    }

    private Equipamento buscarEquipamentoMaiorPreco() {
        if (equipamentos.isEmpty()) {
            return null;
        }

        Equipamento equipamentoMaiorPreco = equipamentos.get(0);
        for (Equipamento eq : equipamentos) {
            if (eq.getPreco() > equipamentoMaiorPreco.getPreco()) {
                equipamentoMaiorPreco = eq;
            }
        }
        return equipamentoMaiorPreco;
    }

    private Equipamento buscarEquipamentoMaiorQuantidade() {
        if (equipamentos.isEmpty()) {
            return null;
        }

        Equipamento equipamentoMaiorQuantidade = equipamentos.get(0);
        for (Equipamento eq : equipamentos) {
            if (eq.getQuantidade() > equipamentoMaiorQuantidade.getQuantidade()) {
                equipamentoMaiorQuantidade = eq;
            }
        }
        return equipamentoMaiorQuantidade;
    }

    private List<Equipamento> buscarEquipamentosEstoqueBaixo() {
        List<Equipamento> resultado = new ArrayList<>();
        for (Equipamento eq : equipamentos) {
            if (eq.getQuantidade() < 5) {
                resultado.add(eq);
            }
        }
        return resultado;
    }

    private List<Equipamento> buscarPorParteNome(String parteNome) {
        List<Equipamento> resultado = new ArrayList<>();
        if (parteNome == null || parteNome.trim().isEmpty()) {
            return resultado;
        }

        String parteNomeLower = parteNome.trim().toLowerCase();
        for (Equipamento eq : equipamentos) {
            if (eq.getNome().toLowerCase().contains(parteNomeLower)) {
                resultado.add(eq);
            }
        }
        return resultado;
    }

    private List<Equipamento> buscarPorPrecoMinimo(double precoMinimo) {
        List<Equipamento> resultado = new ArrayList<>();
        for (Equipamento eq : equipamentos) {
            if (eq.getPreco() >= precoMinimo) {
                resultado.add(eq);
            }
        }
        return resultado;
    }
}