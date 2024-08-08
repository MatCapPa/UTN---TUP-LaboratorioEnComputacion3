package ar.edu.utn.frbb.tup.persistence;

import ar.edu.utn.frbb.tup.model.Cliente;
import ar.edu.utn.frbb.tup.model.Cuenta;
import ar.edu.utn.frbb.tup.model.TipoPersona;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Set;

@Repository
public class ClienteDao extends BaseDao<Cliente>{
    private final String RUTA_ARCHIVO = "src/main/java/ar/edu/utn/frbb/tup/persistence/data/cliente.txt";

    private final CuentaDao cuentaDao;

    public ClienteDao(CuentaDao cuentaDao) {
        this.cuentaDao = cuentaDao;
    }

    public void initializeClientes(){
        String encabezado = "DNI, Nombre, Apellido, Fecha nacimiento, Banco, Tipo Persona, Fecha alta";
        initializeArchivo(encabezado, RUTA_ARCHIVO);
    }

    public void saveCliente(Cliente cliente) {
        String infoAguardar = cliente.getDni() + "," + cliente.getNombre() + "," + cliente.getApellido() + "," + cliente.getFechaNacimiento()  + "," + cliente.getBanco() + "," + cliente.getTipoPersona() + "," + cliente.getFechaAlta();
        saveInfo(infoAguardar, RUTA_ARCHIVO);
    }

    public Cliente findCliente(long dni) {
        Cliente cliente = findInfo(dni, RUTA_ARCHIVO);
        Set<Cuenta> cuentas = cuentaDao.findAllCuentasDelCliente(dni);

        if (!cuentas.isEmpty()){
            cliente.setCuentas(cuentas);
        }

        return cliente;
    }

    @Override
    public Cliente parseDatosToObjet(String[] datos) {
        Cliente cliente = new Cliente();

        cliente.setDni(Long.parseLong(datos[0]));
        cliente.setNombre(datos[1]);
        cliente.setApellido(datos[2]);
        cliente.setFechaNacimiento(LocalDate.parse(datos[3]));
        cliente.setBanco(datos[4]);
        cliente.setTipoPersona(TipoPersona.valueOf(datos[5]));
        cliente.setFechaAlta(LocalDate.parse(datos[6]));

        return cliente;
    }
}
