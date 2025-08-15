package PrimeiroSistema.Model;


public class MotorEletrico extends Equipamento {
    private double potencia;

    public MotorEletrico() {
        super();
        this.potencia = 0.0;
    }

    public MotorEletrico(String codigo, String nome, int quantidade, double preco, double potencia) {
        super(codigo, nome, quantidade, preco);
        this.potencia = potencia >= 0 ? potencia : 0.0;
    }

    public double getPotencia() {
        return potencia;
    }

    public void setPotencia(double potencia) {
        if (potencia >= 0) {
            this.potencia = potencia;
        }
    }

    @Override
    public String toString() {
        return "=== MOTOR ELÉTRICO ===\n" +
                super.toString() + "\n" +
                String.format("Potência: %.2f HP", potencia);
    }
}