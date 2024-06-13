package practica10;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private int id;
    private String name;
    private LocalDate dateBirth;
    private List<Cuenta> cuentas;
    
    public Cliente(int id, String name, LocalDate dateBirth) {
        this.id = id;
        this.name = name;
        this.dateBirth = dateBirth;
        this.cuentas = new ArrayList<>();
    }

    public Cliente(int id, String name, LocalDate dateBirth, List<Cuenta> cuentas) {
        this.id = id;
        this.name = name;
        this.dateBirth = dateBirth;
        this.cuentas = cuentas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(LocalDate dateBirth) {
        this.dateBirth = dateBirth;
    }

    @Override
    public String toString() {
        return "Cliente [id=" + id + ", name=" + name + ", dateBirth=" + dateBirth + ", cuentas=" + cuentas + "]";
    }

    public List<Cuenta> getCuentas() {
        return this.cuentas;
    }

    public void agregarCuenta(Cuenta cuenta) {
        this.cuentas.add(cuenta);
    }

}
