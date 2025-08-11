package Model;

public class PainelControle extends Equipamento {

    private String tensao;

    public PainelControle(String tensao) {
        this.tensao = tensao;
    }

    public PainelControle(String codigo, String nome, int qtd, double preco, String tensao) {
        super(codigo, nome, qtd, preco);
        this.tensao = tensao;
    }

    public PainelControle(){
        this.tensao = "";
    }

    public String getTensao() {
        return tensao;
    }

    public void setTensao(String tensao) {
        this.tensao = tensao;
    }

    @Override
    public String toString() {
        return "Equipamento [\n codigo: " +getCodigo()+"\n nome: " +getNome()+ "\n quantidade: "+getQtd()+ " \n preço: "+getPreco()+"\n tensão: "+getTensao()+"]";
    }
}
