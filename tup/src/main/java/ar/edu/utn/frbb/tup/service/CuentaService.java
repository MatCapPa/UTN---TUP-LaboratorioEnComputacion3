package ar.edu.utn.frbb.tup.service;

import ar.edu.utn.frbb.tup.model.Cliente;
import ar.edu.utn.frbb.tup.model.Cuenta;
import ar.edu.utn.frbb.tup.model.TipoCuenta;
import ar.edu.utn.frbb.tup.model.TipoMoneda;
import ar.edu.utn.frbb.tup.model.exception.ClienteNoEncontradoException;
import ar.edu.utn.frbb.tup.model.exception.CuentaExistenteException;
import ar.edu.utn.frbb.tup.model.exception.CuentaNoEncontradaException;
import ar.edu.utn.frbb.tup.model.exception.TipoCuentaExistenteException;
import ar.edu.utn.frbb.tup.persistence.ClienteDao;
import ar.edu.utn.frbb.tup.persistence.CuentaDao;
import ar.edu.utn.frbb.tup.presentation.modelDto.CuentaDto;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CuentaService {
    private final ClienteDao clienteDao;
    private final CuentaDao cuentaDao;

    public CuentaService(ClienteDao clienteDao, CuentaDao cuentaDao) {
        this.clienteDao = clienteDao;
        this.cuentaDao = cuentaDao;
    }

    public void inicializarCuentas() {
        cuentaDao.initializeCuentas();
    }

    public Cuenta crearCuenta(CuentaDto cuentaDto) throws ClienteNoEncontradoException, CuentaExistenteException , TipoCuentaExistenteException{
        Cuenta cuenta = new Cuenta(cuentaDto);

        //valido que exista el cliente
        Cliente cliente = clienteDao.findCliente(cuenta.getDniTitular());

        if (cliente == null) {
            throw new ClienteNoEncontradoException("No se encontro el cliente con el DNI: " + cuenta.getDniTitular());
        }

        //valido que la cuenta mandada ya existia
        Cuenta cuentaExiste = cuentaDao.findCuenta(cuenta.getNumeroCuenta());

        if (cuentaExiste != null) {
            throw new CuentaExistenteException("Ya tiene una cuenta con ese CVU");
        }
        //valido que no exista una cuenta con el mismo tipo de cuenta y tipo de moneda
        tieneCuenta(cuenta.getTipoCuenta(), cuenta.getMoneda(), cuenta.getDniTitular());

        //agrega la cuenta al archivo
        cuentaDao.saveCuenta(cuenta);
        return cuenta;

    }

    private void tieneCuenta(TipoCuenta tipoCuenta, TipoMoneda tipoMoneda, long dniTitular) throws TipoCuentaExistenteException {
        Set<Cuenta> cuentasClientes = cuentaDao.findAllCuentasDelCliente(dniTitular);

        for (Cuenta cuenta: cuentasClientes) {
            if (tipoCuenta.equals(cuenta.getTipoCuenta()) || tipoMoneda.equals(cuenta.getMoneda())) {
                throw new TipoCuentaExistenteException("Ya tiene una cuenta con ese tipo de cuenta y tipo de moneda");
            }
        }
    }

    public Set<Cuenta> mostrarCuenta(long dni) throws ClienteNoEncontradoException, CuentaNoEncontradaException {

        //busco al cliente, si no esta en el archivo devuelve null
        Cliente cliente = clienteDao.findCliente(dni);

        if (cliente == null) {
            throw new ClienteNoEncontradoException("No se encontro el cliente con el DNI: " + dni);
        }

        //establece la lista de cuentas asociados al cliente
        Set<Cuenta> cuentas = cuentaDao.findAllCuentasDelCliente(dni);

        //si la lista esta vacia, entonces el cliente no posee cuentas
        if (cuentas.isEmpty()){
            throw new CuentaNoEncontradaException("No hay cuentas asociadas al cliente con DNI: " + dni);
        }

        return cuentas;

    }
}
