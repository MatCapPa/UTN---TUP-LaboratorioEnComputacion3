package ar.edu.utn.frbb.tup.presentation.controller.validator;

import ar.edu.utn.frbb.tup.presentation.modelDto.PrestamoDto;
import org.springframework.stereotype.Component;

@Component
public class PrestamoValidator {
    public void validate(PrestamoDto prestamoDto){

        //Nro Cuenta
        if (prestamoDto.getNroCuenta()<=999 || prestamoDto.getNroCuenta() >=10000) throw new IllegalArgumentException("Error al ingresar el Numero de Cuenta");
        //Monto del Prestamo
        if (prestamoDto.getMontoDelPrestamo() <=0) throw new IllegalArgumentException("Error al ingresar el prestamo. No puede ser menor a 0");
        //Plazos Meses
        if (prestamoDto.getPlazosMeses() <= 0)throw new IllegalArgumentException("Error al ingresar la cantidad de meses a pagar el prestamo");
        //Tipo de Moneda
        if (prestamoDto.getMoneda() == null || prestamoDto.getMoneda().isEmpty()) throw new IllegalArgumentException("Error al ingresar un tipo de moneda");
    }
}
