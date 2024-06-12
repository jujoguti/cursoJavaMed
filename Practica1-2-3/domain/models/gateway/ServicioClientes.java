package domain.models.gateway;

import java.util.ArrayList;
import domain.models.Cliente;

public interface ServicioClientes {

    public boolean agregarCliente (Cliente cliente);
    public boolean eliminarCliente (int numero);
    public Cliente consultarCliente (int numero);
    public ArrayList<Cliente> obtenerClientes();
    public Cliente buscarClientePorRFC (String rfc);
}
