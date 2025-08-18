package SegundoSistema.Model;

public class Equipamento {
    protected String codigo;
    protected String nome;
    protected int quantidade;
    protected double preco;

    public Equipamento() {
        this.codigo = "";
        this.nome = "";
        this.quantidade = 0;
        this.preco = 0.0;
    }

    public Equipamento(String codigo, String nome, int quantidade, double preco) {
        if (codigo == null || codigo.trim().isEmpty()) {
            this.codigo = "SEM_CODIGO";
        } else {
            this.codigo = codigo.trim();
        }

        if (nome == null || nome.trim().isEmpty()) {
            this.nome = "SEM_NOME";
        } else {
            this.nome = nome.trim();
        }

        this.quantidade = quantidade >= 0 ? quantidade : 0;
        this.preco = preco >= 0 ? preco : 0.0;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double getPreco() {
        return preco;
    }

    public void setCodigo(String codigo) {
        if (codigo != null && !codigo.trim().isEmpty()) {
            this.codigo = codigo.trim();
        }
    }

    public void setNome(String nome) {
        if (nome != null && !nome.trim().isEmpty()) {
            this.nome = nome.trim();
        }
    }

    public void setQuantidade(int quantidade) {
        if (quantidade >= 0) {
            this.quantidade = quantidade;
        }
    }

    public void setPreco(double preco) {
        if (preco >= 0) {
            this.preco = preco;
        }
    }

    @Override
    public String toString() {
        return String.format(
                "Código: %s\n" +
                        "Nome: %s\n" +
                        "Quantidade: %d\n" +
                        "Preço: R$ %.2f",
                codigo, nome, quantidade, preco
        );
    }
}

