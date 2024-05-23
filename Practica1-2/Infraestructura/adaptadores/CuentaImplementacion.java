package Infraestructura.adaptadores;

import java.time.LocalDate;
import java.util.ArrayList;

import domain.models.Cuenta;
import domain.models.gateway.ServicioCuentas;

public class CuentaImplementacion implements ServicioCuentas{

    ArrayList<Cuenta> listaCuentas = new ArrayList<>();

    @Override
    public boolean agregarCuenta(Cuenta cuenta) {
       listaCuentas.add(cuenta);
       return true;
    }

    @Override
    public boolean cancelarCuenta(int numero) {
       for (Cuenta cuenta : listaCuentas) {
            if (cuenta.getNumero() == numero) {
                cuenta.setFechaCancelacion(LocalDate.now().toString());
                return true;
            }
        }
       return false;
    }

    @Override
    public void abonarCuenta(int numero, double abono) {
        for (Cuenta cuenta : listaCuentas) {
            if (cuenta.getNumero() == numero) {
                double actual = cuenta.getSaldo();
                cuenta.setSaldo(actual+abono);
            }
        }
    }

    @Override
    public void retirar(int numero, double retiro) {
        for (Cuenta cuenta : listaCuentas) {
            if (cuenta.getNumero() == numero) {
                double actual = cuenta.getSaldo();
                cuenta.setSaldo(actual-retiro);
            }
        }
    }

    @Override
    public ArrayList<Cuenta> obtenerCuentas() {
        return listaCuentas;
    }
    
}
