package practica10;

import java.time.LocalDate;

public class CuentaDeAhorro extends Cuenta {

    private double tasaInteresMensual;

    public CuentaDeAhorro(String numero, double saldo, LocalDate fechaApertura, LocalDate fechaCancelacion,
                          double tasaInteresMensual) {
        super(numero, saldo, fechaApertura,fechaCancelacion);
        this.tasaInteresMensual = tasaInteresMensual;
    }

    public double calcularIntereses() {
        return super.getSaldo()*tasaInteresMensual;
    }

    @Override
    public String toString() {
        return "CuentaDeAhorro [tasaInteresMensual=" + tasaInteresMensual + "]"+ super.toString();
    }
}
