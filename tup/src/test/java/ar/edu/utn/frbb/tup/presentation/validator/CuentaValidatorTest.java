package ar.edu.utn.frbb.tup.presentation.validator;

import ar.edu.utn.frbb.tup.presentation.controller.validator.CuentaValidator;
import ar.edu.utn.frbb.tup.presentation.modelDto.CuentaDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CuentaValidatorTest {

    CuentaValidator cuentaValidator;

    @BeforeEach
    public void iniciar(){
        cuentaValidator = new CuentaValidator();
    }

    @Test
    public void testCuentaValidatorSuccess(){
        CuentaDto cuentaDto = new CuentaDto();
        cuentaDto.setSaldo(2000);
        cuentaDto.setDniTitular(44111222);
        cuentaDto.setTipoCuenta("A");
        cuentaDto.setTipoMoneda("P");

        assertDoesNotThrow(() -> cuentaValidator.validate(cuentaDto));
    }

    @Test
    public void testCuentaSinSaldoException(){
        CuentaDto cuentaDto = new CuentaDto();
        //cuentaDto.setSaldo(2000);
        cuentaDto.setDniTitular(44111222);
        cuentaDto.setTipoCuenta("A");
        cuentaDto.setTipoMoneda("P");

        assertThrows(IllegalArgumentException.class, () -> cuentaValidator.validate(cuentaDto));
    }

    @Test
    public void testCuentaConDniTitularErroneoException(){
        CuentaDto cuentaDto = new CuentaDto();
        cuentaDto.setSaldo(2000);
        cuentaDto.setDniTitular(84111222);
        cuentaDto.setTipoCuenta("A");
        cuentaDto.setTipoMoneda("P");

        assertThrows(IllegalArgumentException.class, () -> cuentaValidator.validate(cuentaDto));
    }

    @Test
    public void testCuentaSinTipoCuentaException(){
        CuentaDto cuentaDto = new CuentaDto();
        cuentaDto.setSaldo(2000);
        cuentaDto.setDniTitular(44111222);
        //cuentaDto.setTipoCuenta("A");
        cuentaDto.setTipoMoneda("P");

        assertThrows(IllegalArgumentException.class, () -> cuentaValidator.validate(cuentaDto));
    }

    @Test
    public void testCuentaSinTipoMonedaException(){
        CuentaDto cuentaDto = new CuentaDto();
        cuentaDto.setSaldo(2000);
        cuentaDto.setDniTitular(44111222);
        cuentaDto.setTipoCuenta("A");
        //cuentaDto.setTipoMoneda("P");

        assertThrows(IllegalArgumentException.class, () -> cuentaValidator.validate(cuentaDto));
    }
}
