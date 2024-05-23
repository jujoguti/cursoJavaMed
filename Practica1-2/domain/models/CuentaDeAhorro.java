package domain.models;

public class CuentaDeAhorro extends Cuenta {

    private double tasaInteresMensual; 

    public CuentaDeAhorro(int numero, String fechaApertura, double saldo, String fechaCancelacion,
            double tasaInteresMensual) {
        super(numero, fechaApertura, saldo, fechaCancelacion);
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
