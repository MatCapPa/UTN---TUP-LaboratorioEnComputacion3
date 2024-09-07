package ar.edu.utn.frbb.tup.service;

import ar.edu.utn.frbb.tup.model.Cliente;
import ar.edu.utn.frbb.tup.model.Cuenta;
import ar.edu.utn.frbb.tup.model.Prestamo;
import ar.edu.utn.frbb.tup.model.exception.ClienteNoEncontradoException;
import ar.edu.utn.frbb.tup.model.exception.CuentaExistenteException;
import ar.edu.utn.frbb.tup.model.exception.CuentaNoEncontradaException;
import ar.edu.utn.frbb.tup.model.exception.TipoCuentaExistenteException;
import ar.edu.utn.frbb.tup.persistence.ClienteDao;
import ar.edu.utn.frbb.tup.persistence.CuentaDao;
import ar.edu.utn.frbb.tup.persistence.PrestamoDao;
import ar.edu.utn.frbb.tup.presentation.modelDto.CuentaDto;
import ar.edu.utn.frbb.tup.presentation.modelDto.PrestamoDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PrestamoServiceTest {

    @Mock
    CuentaDao cuentaDao;

    @Mock
    PrestamoDao prestamoDao;

    @Mock
    ScoreCrediticioService scoreCrediticioService;

    @InjectMocks
    PrestamoService prestamoService;

    @Mock
    ClienteDao clienteDao;

    @InjectMocks
    CuentaService cuentaService;

    @BeforeEach
    public void iniciar(){
        MockitoAnnotations.openMocks(this);
        prestamoService = new PrestamoService(cuentaDao,prestamoDao,scoreCrediticioService);
        cuentaService = new CuentaService(clienteDao, cuentaDao);
    }

    @Test
    public void testCrearPrestamoSuccess() throws CuentaNoEncontradaException, TipoCuentaExistenteException, CuentaExistenteException, ClienteNoEncontradoException {
        CuentaDto cuentaDto = new CuentaDto();
        cuentaDto.setDniTitular(44111222);
        cuentaDto.setSaldo(2000);
        cuentaDto.setTipoCuenta("C");
        cuentaDto.setTipoMoneda("P");

        when(clienteDao.findCliente(cuentaDto.getDniTitular())).thenReturn(new Cliente());
        when(cuentaDao.findCuenta(any(Long.class))).thenReturn(null); // como el numero de cuenta se genera de forma aleatoria, acepta cualquier parametro
        when(cuentaDao.findAllCuentasDelCliente(cuentaDto.getDniTitular())).thenReturn(new HashSet<>());

        Cuenta nuevaCuenta = cuentaService.crearCuenta(cuentaDto);
        verify(clienteDao, times(1)).findCliente(cuentaDto.getDniTitular());
        verify(cuentaDao, times(1)).findCuenta(nuevaCuenta.getNumeroCuenta());
        verify(cuentaDao, times(1)).findAllCuentasDelCliente(cuentaDto.getDniTitular());
        verify(cuentaDao, times(1)).saveCuenta(nuevaCuenta);

        assertNotNull(nuevaCuenta);

        PrestamoDto prestamoDto = new PrestamoDto();
        prestamoDto.setMoneda("P");
        prestamoDto.setMontoDelPrestamo(200);
        prestamoDto.setNroCuenta(nuevaCuenta.getNumeroCuenta());
        prestamoDto.setPlazosMeses(4);

        when(cuentaDao.findCuenta(prestamoDto.getNroCuenta())).thenReturn(nuevaCuenta);
        when(prestamoDao.findAllPrestamosDeLaCuenta(prestamoDto.getNroCuenta())).thenReturn(new HashSet<>());

        Prestamo nuevoPrestamo = prestamoService.crearPrestamo(prestamoDto);
        verify(cuentaDao,times(2)).findCuenta(nuevaCuenta.getNumeroCuenta());


        assertNotNull(nuevoPrestamo);
    }

    @Test
    public void testCuentaNoEncontradaException() {
        PrestamoDto prestamoDto = new PrestamoDto();
        prestamoDto.setMoneda("P");
        prestamoDto.setMontoDelPrestamo(200);
        prestamoDto.setNroCuenta(1221);
        prestamoDto.setPlazosMeses(4);

        when(cuentaDao.findCuenta(prestamoDto.getNroCuenta())).thenReturn(null);

        assertThrows(CuentaNoEncontradaException.class, () -> prestamoService.crearPrestamo(prestamoDto));

        verify(cuentaDao,times(1)).findCuenta(prestamoDto.getNroCuenta());
    }
}
