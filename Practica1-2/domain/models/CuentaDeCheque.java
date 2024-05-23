package domain.models;

public class CuentaDeCheque extends Cuenta {

    private double CostoMadejoMensual;

    public CuentaDeCheque(int numero, String fechaApertura, double saldo, String fechaCancelacion,
            double costoMadejoMensual) {
        super(numero, fechaApertura, saldo, fechaCancelacion);
        CostoMadejoMensual = costoMadejoMensual;
    }

    @Override
    public String toString() {
        return "CuentaDeCheque [CostoMadejoMensual=" + CostoMadejoMensual + "]"+ super.toString();
    }
    
}
