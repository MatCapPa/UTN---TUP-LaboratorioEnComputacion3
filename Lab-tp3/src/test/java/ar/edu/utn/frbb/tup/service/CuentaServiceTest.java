package ar.edu.utn.frbb.tup.service;

import ar.edu.utn.frbb.tup.model.*;
import ar.edu.utn.frbb.tup.model.exception.ClienteAlreadyExistsException;
import ar.edu.utn.frbb.tup.model.exception.CuentaAlreadyExistsException;
import ar.edu.utn.frbb.tup.model.exception.TipoCuentaAlreadyExistsException;
import ar.edu.utn.frbb.tup.persistence.ClienteDao;
import ar.edu.utn.frbb.tup.persistence.CuentaDao;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CuentaServiceTest {

    @Mock
    private CuentaDao cuentaDao;

    @Mock
    private ClienteDao clienteDao;

    @Mock
    private ClienteService clienteService;

    @InjectMocks
    private CuentaService cuentaService;

    @BeforeAll
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCuentaAgregadaExitosamente() throws TipoCuentaAlreadyExistsException, CuentaAlreadyExistsException, ClienteAlreadyExistsException{
        Cliente pepeRino = new Cliente();
        pepeRino.setDni(26456439);
        pepeRino.setFechaNacimiento(LocalDate.of(1978, 3, 25));
        pepeRino.setTipoPersona(TipoPersona.PERSONA_FISICA);
        clienteService.darDeAltaCliente(pepeRino);
        verify(clienteDao, times(1)).save(pepeRino);

        Cuenta cuenta = new Cuenta();
        cuenta.setTipoCuenta(TipoCuenta.CAJA_AHORRO);
        cuenta.setMoneda(TipoMoneda.PESOS);
        cuenta.setBalance(500000);
        cuenta.setNumeroCuenta(123);
        cuentaService.darDeAltaCuenta(cuenta, pepeRino.getDni());

        verify(cuentaDao, times(1)).save(cuenta);

        //when(clienteDao.find(26456439, true)).thenReturn(pepeRino);

        //
    }

    //    1 - cuenta existente
    @Test
    public void testCuentaExistente() throws CuentaAlreadyExistsException, TipoCuentaAlreadyExistsException {
        Cuenta cuenta = new Cuenta();
        cuenta.setTipoCuenta(TipoCuenta.CAJA_AHORRO);
        cuenta.setMoneda(TipoMoneda.PESOS);
        cuenta.setBalance(500000);
        cuenta.setNumeroCuenta(321);
        cuentaService.darDeAltaCuenta(cuenta, 26456439);

        verify(cuentaDao, times(1)).save(cuenta);

    }
    
    //    2 - cuenta no soportada
    @Test
    public void testCuentaNoSoportada() throws CuentaAlreadyExistsException, TipoCuentaAlreadyExistsException, ClienteAlreadyExistsException {
        Cuenta cuenta = new Cuenta();
        cuenta.setTipoCuenta(TipoCuenta.CAJA_SANTANDER);
        assertThrows(TipoCuentaAlreadyExistsException.class, () -> cuentaService.darDeAltaCuenta(cuenta, 26456439));

    }

    //    3 - cliente ya tiene cuenta de ese tipo
    @Test
    public void testCuentaYaExistente() throws CuentaAlreadyExistsException, TipoCuentaAlreadyExistsException, ClienteAlreadyExistsException {
        Cuenta cuenta = new Cuenta();
        cuenta.setTipoCuenta(TipoCuenta.CAJA_AHORRO);
        cuenta.setMoneda(TipoMoneda.PESOS);
        cuenta.setBalance(500000);
        cuenta.setNumeroCuenta(123);
        cuentaService.darDeAltaCuenta(cuenta, 26456439);

        Cuenta cuenta2 = new Cuenta();
        cuenta2.setTipoCuenta(TipoCuenta.CAJA_AHORRO);
        cuenta2.setNumeroCuenta(1234);
        cuentaService.darDeAltaCuenta(cuenta2,26456439);
        assertThrows(CuentaAlreadyExistsException.class, () -> cuentaService.darDeAltaCuenta(cuenta2, 26456439));
    }
}
