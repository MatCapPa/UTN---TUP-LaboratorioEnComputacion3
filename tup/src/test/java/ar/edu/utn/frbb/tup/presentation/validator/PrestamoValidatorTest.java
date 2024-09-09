package ar.edu.utn.frbb.tup.presentation.validator;

import ar.edu.utn.frbb.tup.presentation.controller.validator.PrestamoValidator;
import ar.edu.utn.frbb.tup.presentation.modelDto.PrestamoDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PrestamoValidatorTest {

    PrestamoValidator prestamoValidator;

    @BeforeEach
    public void iniciar(){
        prestamoValidator = new PrestamoValidator();
    }

    @Test
    public void testPrestamoValidatorSuccess(){
        PrestamoDto prestamoDto = new PrestamoDto();
        prestamoDto.setPlazosMeses(3);
        prestamoDto.setMoneda("P");
        prestamoDto.setNroCuenta(3214);
        prestamoDto.setMontoDelPrestamo(2000);

        assertDoesNotThrow(() -> prestamoValidator.validate(prestamoDto));
    }

    @Test
    public void testPrestamoSinPlazosMesesException(){
        PrestamoDto prestamoDto = new PrestamoDto();
        //prestamoDto.setPlazosMeses(3);
        prestamoDto.setMoneda("P");
        prestamoDto.setNroCuenta(3214);
        prestamoDto.setMontoDelPrestamo(2000);

        assertThrows(IllegalArgumentException.class, () -> prestamoValidator.validate(prestamoDto));
    }

    @Test
    public void testPrestamoSinTipoMonedaException(){
        PrestamoDto prestamoDto = new PrestamoDto();
        prestamoDto.setPlazosMeses(3);
        //prestamoDto.setMoneda("P");
        prestamoDto.setNroCuenta(3214);
        prestamoDto.setMontoDelPrestamo(2000);

        assertThrows(IllegalArgumentException.class, () -> prestamoValidator.validate(prestamoDto));
    }

    @Test
    public void testPrestamoSinNroCuentaException(){
        PrestamoDto prestamoDto = new PrestamoDto();
        prestamoDto.setPlazosMeses(3);
        prestamoDto.setMoneda("P");
        //prestamoDto.setNroCuenta(3214);
        prestamoDto.setMontoDelPrestamo(2000);

        assertThrows(IllegalArgumentException.class, () -> prestamoValidator.validate(prestamoDto));
    }

    @Test
    public void testPrestamoSinMontoDelPrestamoException(){
        PrestamoDto prestamoDto = new PrestamoDto();
        prestamoDto.setPlazosMeses(3);
        prestamoDto.setMoneda("P");
        prestamoDto.setNroCuenta(3214);
        //prestamoDto.setMontoDelPrestamo(2000);

        assertThrows(IllegalArgumentException.class, () -> prestamoValidator.validate(prestamoDto));
    }
}
