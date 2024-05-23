package domain.models.gateway;

import java.util.ArrayList;
import domain.models.Cuenta;

public interface ServicioCuentas {

    public boolean agregarCuenta (Cuenta cuenta);
    public boolean cancelarCuenta (int numero);
    public void abonarCuenta (int numero, double abono);
    public void retirar (int numero, double retiro);
    public ArrayList<Cuenta> obtenerCuentas();
}
