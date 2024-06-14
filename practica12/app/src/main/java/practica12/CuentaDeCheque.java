package practica12;

import java.time.LocalDate;

public class CuentaDeCheque extends Cuenta {

    private double CostoMadejoMensual;

    public CuentaDeCheque(String numero, double saldo, LocalDate fechaApartura, LocalDate fechaCancelacion,
                          double costoMadejoMensual) {
        super(numero, saldo, fechaApartura, fechaCancelacion);
        CostoMadejoMensual = costoMadejoMensual;
    }

    @Override
    public String toString() {
        return "CuentaDeCheque{" +
                "CostoMadejoMensual=" + CostoMadejoMensual +
                '}'+ super.toString();
    }
}
