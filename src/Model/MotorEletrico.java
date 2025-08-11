package Model;

public class MotorEletrico extends Equipamento{
    private double potencia;

    public MotorEletrico() {
        this.potencia = 0.0;
    }

    public MotorEletrico(String codigo, String nome, int qtd, double preco, double potencia) {
        super(codigo, nome, qtd, preco);
        this.potencia = potencia;
    }

    public double getPotencia() {
        return potencia;
    }

    public void setPotencia(double potencia) {
        this.potencia = potencia;
    }

    @Override
    public String toString() {
        return "Equipamento [\n codigo: " +getCodigo()+"\n nome: " +getNome()+ "\n quantidade: "+getQtd()+ " \n preço: "+getPreco()+"\n potencia: "+getPotencia()+"]";
    }
}
