package Infraestructura.adaptadores;

import java.util.ArrayList;

import domain.models.Cliente;
import domain.models.gateway.ServicioClientes;

public class ClienteImplementacion implements ServicioClientes {

    ArrayList<Cliente> listaClientes = new ArrayList<>();

    @Override
    public boolean agregarCliente(Cliente cliente) {
        listaClientes.add(cliente);
        return true;
    }

    @Override
    public boolean eliminarCliente(int numero) {
        for (Cliente cliente : listaClientes) {
            if (cliente.getNumero()==numero) {
                listaClientes.remove(cliente);
                return true;
            }
        };

        return false;
    }

    @Override
    public Cliente consultarCliente(int numero) {

        for (Cliente cliente : listaClientes) {
            if (cliente.getNumero()==numero) {
                return cliente;
            }
        };

        return null;
    }

    @Override
    public ArrayList<Cliente> obtenerClientes() {
        return listaClientes;
    }

    @Override
    public Cliente buscarClientePorRFC(String rfc) {
        for (Cliente cliente : listaClientes) {
            if (cliente.getRfc().equals(rfc)) {
                return cliente;
            }
        };

        return null;
    }


}
