package practica12;

import java.time.LocalDate;

public abstract class Cuenta {
    private String numero;
    private double saldo;
    private LocalDate fechaApartura;
    private LocalDate fechaCancelacion;

    public Cuenta(String numero, double saldo, LocalDate fechaApartura, LocalDate fechaCancelacion) {
        this.numero = numero;
        this.saldo = saldo;
        this.fechaApartura = fechaApartura;
        this.fechaCancelacion = fechaCancelacion;
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

    public LocalDate getFechaApartura() {
        return fechaApartura;
    }

    public void setFechaApartura(LocalDate fechaApartura) {
        this.fechaApartura = fechaApartura;
    }

    @Override
    public String toString() {
        return "Cuenta{" +
                "numero='" + numero + '\'' +
                ", saldo=" + saldo +
                ", fechaApartura=" + fechaApartura +
                ", fechaCancelacion=" + fechaCancelacion +
                "}";
    }
}
