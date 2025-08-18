package SegundoSistema.Model;

public class PainelControle extends Equipamento {
    private String tensao;

    public PainelControle() {
        super();
        this.tensao = "";
    }

    public PainelControle(String codigo, String nome, int quantidade, double preco, String tensao) {
        super(codigo, nome, quantidade, preco);
        if (tensao != null && !tensao.trim().isEmpty()) {
            this.tensao = tensao.trim();
        } else {
            this.tensao = "220V";
        }
    }

    public String getTensao() {
        return tensao;
    }

    public void setTensao(String tensao) {
        if (tensao != null && !tensao.trim().isEmpty()) {
            this.tensao = tensao.trim();
        }
    }

    @Override
    public String toString() {
        return "=== PAINEL DE CONTROLE ===\n" +
                super.toString() + "\n" +
                String.format("Tensão: %s", tensao);
    }
}