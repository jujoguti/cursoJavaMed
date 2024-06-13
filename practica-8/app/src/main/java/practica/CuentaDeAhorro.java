package practica;

public class CuentaDeAhorro extends Cuenta {

    private double tasaInteresMensual;

    public CuentaDeAhorro(String numero, double saldo, String fechaApertura,
                          double tasaInteresMensual) {
        super(numero, saldo, fechaApertura);
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
