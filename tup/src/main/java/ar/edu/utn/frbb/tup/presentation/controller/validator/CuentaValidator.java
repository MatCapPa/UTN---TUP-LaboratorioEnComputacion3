package ar.edu.utn.frbb.tup.presentation.controller.validator;

import ar.edu.utn.frbb.tup.presentation.modelDto.CuentaDto;
import org.springframework.stereotype.Component;

@Component
public class CuentaValidator {
    public void validate(CuentaDto cuentaDto ){

        //Tipo de Cuenta
        if (cuentaDto.getTipoCuenta() == null || cuentaDto.getTipoCuenta().isEmpty()) throw new IllegalArgumentException("Error al ingresar un tipo de cuenta");
        //Tipo de Moneda
        if (cuentaDto.getTipoMoneda() == null || cuentaDto.getTipoMoneda().isEmpty()) throw new IllegalArgumentException("Error al ingresar un tipo de moneda");
        //Saldo de la Cuenta
        if (cuentaDto.getSaldo() <=0) throw new IllegalArgumentException("Error al ingresar el monto de su saldo");
        //DNI Titular
        if (cuentaDto.getDniTitular() <= 10000000 || cuentaDto.getDniTitular() >=80000000)throw new IllegalArgumentException("Error: El nro de DNI valido es entre '10.000.000' a '79.999.999'");

    }
}
