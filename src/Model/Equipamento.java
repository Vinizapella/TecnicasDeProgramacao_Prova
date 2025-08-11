package Model;

public class Equipamento {

    protected String codigo;
    protected String nome;
    protected int qtd;
    protected double preco;

    public Equipamento(){
        this.codigo = "";
        this.qtd = 0;
        this.nome = "";
        this.preco = 0.0;
    }

    public Equipamento(String codigo, String nome, int qtd, double preco){
        this.preco = preco;
        this.nome = nome;
        this.qtd = qtd;
        this.codigo = codigo;
    }

    public double getPreco() {
        return preco;
    }

    public int getQtd() {
        return qtd;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    @Override
    public String toString() {
        return "Equipamento [\n codigo: " +getCodigo()+"\n nome: " +getNome()+ "\n quantidade: "+getQtd()+ " \n preço: "+getPreco()+"]";
    }
}
