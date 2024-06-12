package ar.edu.utn.frbb.tup.service;

import ar.edu.utn.frbb.tup.model.Cuenta;
import ar.edu.utn.frbb.tup.model.TipoCuenta;
import ar.edu.utn.frbb.tup.model.TipoMoneda;
import ar.edu.utn.frbb.tup.model.exception.CuentaAlreadyExistsException;
import ar.edu.utn.frbb.tup.model.exception.TipoCuentaAlreadyExistsException;
import ar.edu.utn.frbb.tup.persistence.CuentaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CuentaService {
    CuentaDao cuentaDao = new CuentaDao();

    @Autowired
    ClienteService clienteService;

    //Generar casos de test para darDeAltaCuenta
    //    1 - cuenta existente
    //    2 - cuenta no soportada
    //    3 - cliente ya tiene cuenta de ese tipo
    //    4 - cuenta creada exitosamente
    public void darDeAltaCuenta(Cuenta cuenta, long dniTitular) throws CuentaAlreadyExistsException, TipoCuentaAlreadyExistsException {
        if(cuentaDao.find(cuenta.getNumeroCuenta()) != null) { //originalmente era == null , cambio a != null
            throw new CuentaAlreadyExistsException("La cuenta " + cuenta.getNumeroCuenta() + " ya existe.");
        }

        //Chequear cuentas soportadas por el banco CA$ CC$ CAU$S
        // if (!tipoCuentaEstaSoportada(cuenta)) {...}
        if (!tipoCuentaEstaSoportada(cuenta)) {
            throw new TipoCuentaAlreadyExistsException("La cuenta " + cuenta.getTipoCuenta() + " no es soportada por el banco.");
        }

        clienteService.agregarCuenta(cuenta, dniTitular);
        cuentaDao.save(cuenta);
    }

    public boolean tipoCuentaEstaSoportada(Cuenta cuenta){
        if ((cuenta.getTipoCuenta().equals(TipoCuenta.CUENTA_CORRIENTE) || cuenta.getTipoCuenta().equals(TipoCuenta.CAJA_AHORRO)) && cuenta.getMoneda().equals(TipoMoneda.PESOS)) {
            return true;    //condicion CA$ Y CC$ soportadas
        }
        if (cuenta.getTipoCuenta().equals(TipoCuenta.CAJA_AHORRO) && cuenta.getMoneda().equals(TipoMoneda.DOLARES)) {
            return true;    //condicion CAU$S soportada
        }
        return false;   //si no es ninguna de las anteriores, no es soportada
    }
   

    public Cuenta find(long id) {
        return cuentaDao.find(id);
    }
}
