package ar.edu.utn.frbb.tup.service;

import ar.edu.utn.frbb.tup.model.Cliente;
import ar.edu.utn.frbb.tup.model.Cuenta;
import ar.edu.utn.frbb.tup.model.exception.ClienteNoEncontradoException;
import ar.edu.utn.frbb.tup.model.exception.CuentaExistenteException;
import ar.edu.utn.frbb.tup.model.exception.TipoCuentaExistenteException;
import ar.edu.utn.frbb.tup.persistence.ClienteDao;
import ar.edu.utn.frbb.tup.persistence.CuentaDao;
import ar.edu.utn.frbb.tup.presentation.modelDto.CuentaDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CuentaServiceTest {

    @Mock
    ClienteDao clienteDao;

    @Mock
    CuentaDao cuentaDao;

    @InjectMocks
    CuentaService cuentaService;

    @BeforeEach
    public void iniciar(){
        MockitoAnnotations.openMocks(this);
        cuentaService = new CuentaService(clienteDao,cuentaDao);
    }

    @Test
    public void testCrearCuentaSuccess() throws TipoCuentaExistenteException, CuentaExistenteException, ClienteNoEncontradoException {
        CuentaDto cuentaDto = new CuentaDto();
        cuentaDto.setDniTitular(44111222);
        cuentaDto.setSaldo(2000);
        cuentaDto.setTipoCuenta("C");
        cuentaDto.setTipoMoneda("D");

        when(clienteDao.findCliente(cuentaDto.getDniTitular())).thenReturn(new Cliente());
        when(cuentaDao.findCuenta(any(Long.class))).thenReturn(null); // como el numero de cuenta se genera de forma aleatoria, acepta cualquier parametro
        when(cuentaDao.findAllCuentasDelCliente(cuentaDto.getDniTitular())).thenReturn(new HashSet<>());

        Cuenta nuevaCuenta = cuentaService.crearCuenta(cuentaDto);
        verify(clienteDao, times(1)).findCliente(cuentaDto.getDniTitular());
        verify(cuentaDao, times(1)).findCuenta(nuevaCuenta.getNumeroCuenta());
        verify(cuentaDao, times(1)).findAllCuentasDelCliente(cuentaDto.getDniTitular());
        verify(cuentaDao, times(1)).saveCuenta(nuevaCuenta);

        assertNotNull(nuevaCuenta);
    }

    @Test
    public void testCuentaClienteNoEncontrado(){
        CuentaDto cuentaDto = new CuentaDto();
        cuentaDto.setDniTitular(44111222);
        cuentaDto.setSaldo(2000);
        cuentaDto.setTipoCuenta("C");
        cuentaDto.setTipoMoneda("D");

        when(clienteDao.findCliente(cuentaDto.getDniTitular())).thenReturn(null);

        assertThrows(ClienteNoEncontradoException.class, () -> cuentaService.crearCuenta(cuentaDto));

        verify(clienteDao,times(1)).findCliente(cuentaDto.getDniTitular());
    }

    @Test
    public void testCuentaExistente(){
        CuentaDto cuentaDto = new CuentaDto();
        cuentaDto.setDniTitular(44111222);
        cuentaDto.setSaldo(2000);
        cuentaDto.setTipoCuenta("C");
        cuentaDto.setTipoMoneda("D");

        when(clienteDao.findCliente(cuentaDto.getDniTitular())).thenReturn(new Cliente());
        when(cuentaDao.findCuenta(any(Long.class))).thenReturn(new Cuenta());

        assertThrows(CuentaExistenteException.class, () -> cuentaService.crearCuenta(cuentaDto));

        verify(clienteDao, times(1)).findCliente(cuentaDto.getDniTitular());
        verify(cuentaDao, times(1)).findCuenta(any(Long.class));
    }

    @Test
    public void testTipoCuentaExistente(){
        CuentaDto cuentaDto = new CuentaDto();
        cuentaDto.setDniTitular(44111222);
        cuentaDto.setSaldo(2000);
        cuentaDto.setTipoCuenta("C");
        cuentaDto.setTipoMoneda("D");
        Cuenta cuenta = new Cuenta(cuentaDto);

        Set<Cuenta> cuentas = new HashSet<>();
        cuentas.add(cuenta);

        when(clienteDao.findCliente(cuentaDto.getDniTitular())).thenReturn(new Cliente());
        when(cuentaDao.findCuenta(any(Long.class))).thenReturn(null);
        when(cuentaDao.findAllCuentasDelCliente(cuentaDto.getDniTitular())).thenReturn(cuentas);

        assertThrows(TipoCuentaExistenteException.class, () -> cuentaService.crearCuenta(cuentaDto));

        verify(clienteDao, times(1)).findCliente(cuentaDto.getDniTitular());
        verify(cuentaDao, times(1)).findCuenta(any(Long.class));
        verify(cuentaDao, times(1)).findAllCuentasDelCliente(cuentaDto.getDniTitular());
    }
}
