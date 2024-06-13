package practica;

public class CuentaDeCheque extends Cuenta {

    private double CostoMadejoMensual;

    public CuentaDeCheque(String numero, double saldo, String fechaApartura, double costoMadejoMensual) {
        super(numero, saldo, fechaApartura);
        CostoMadejoMensual = costoMadejoMensual;
    }

    @Override
    public String toString() {
        return "CuentaDeCheque{" +
                "CostoMadejoMensual=" + CostoMadejoMensual +
                '}'+ super.toString();
    }
}
