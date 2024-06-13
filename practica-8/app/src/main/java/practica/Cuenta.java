package practica;

public abstract class Cuenta {
    private String numero;
    private double saldo;
    private String fechaApartura;

    public Cuenta(String numero, double saldo, String fechaApartura) {
        this.numero = numero;
        this.saldo = saldo;
        this.fechaApartura = fechaApartura;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getFechaApartura() {
        return fechaApartura;
    }

    public void setFechaApartura(String fechaApartura) {
        this.fechaApartura = fechaApartura;
    }

    @Override
    public String toString() {
        return "Cuenta{" +
                "numero='" + numero + '\'' +
                ", saldo=" + saldo +
                ", fechaApartura='" + fechaApartura + '\'' +
                '}';
    }
}
