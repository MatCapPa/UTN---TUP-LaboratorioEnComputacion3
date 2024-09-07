package ar.edu.utn.frbb.tup.service;

import ar.edu.utn.frbb.tup.model.Cliente;
import ar.edu.utn.frbb.tup.model.exception.ClienteExistenteException;
import ar.edu.utn.frbb.tup.model.exception.ClienteMenorDeEdadException;
import ar.edu.utn.frbb.tup.persistence.ClienteDao;
import ar.edu.utn.frbb.tup.presentation.modelDto.ClienteDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ClienteServiceTest {

    @Mock
    ClienteDao clienteDao;

    @InjectMocks
    ClienteService clienteService;

    @BeforeEach
    public void iniciar(){
        MockitoAnnotations.openMocks(this);
        clienteService = new ClienteService(clienteDao);
    }

    @Test
    public void crearClienteSuccess() throws ClienteExistenteException, ClienteMenorDeEdadException {
        ClienteDto clienteDto = new ClienteDto();
        clienteDto.setNombre("Nicolas");
        clienteDto.setApellido("Gonzalez");
        clienteDto.setTipoPersona("F");
        clienteDto.setBanco("Provincia");
        clienteDto.setFechaNacimiento("2002-09-15");
        clienteDto.setDni(44111222);

        when(clienteDao.findCliente(clienteDto.getDni())).thenReturn(null);

        Cliente cliente = clienteService.crearCliente(clienteDto);

        verify(clienteDao, times(1)).saveCliente(cliente);
        verify(clienteDao, times(1)).findCliente(clienteDto.getDni());

        assertEquals(cliente.getDni(), clienteDto.getDni());
        assertNotNull(cliente);
    }

    @Test
    public void testClienteExistente(){
        ClienteDto clienteDto = new ClienteDto();
        clienteDto.setNombre("Nicolas");
        clienteDto.setApellido("Gonzalez");
        clienteDto.setTipoPersona("F");
        clienteDto.setBanco("Provincia");
        clienteDto.setFechaNacimiento("2002-09-15");
        clienteDto.setDni(44111222);

        when(clienteDao.findCliente(clienteDto.getDni())).thenReturn(new Cliente());

        assertThrows(ClienteExistenteException.class, () -> clienteService.crearCliente(clienteDto));
    }

    @Test
    public void testClienteMenorDeEdad(){
        ClienteDto clienteDto = new ClienteDto();
        clienteDto.setNombre("Nicolas");
        clienteDto.setApellido("Gonzalez");
        clienteDto.setTipoPersona("F");
        clienteDto.setBanco("Provincia");
        clienteDto.setDni(44111222);
        clienteDto.setFechaNacimiento("2020-09-15");  //15-09-2020

        when(clienteDao.findCliente(clienteDto.getDni())).thenReturn(null);

        assertThrows(ClienteMenorDeEdadException.class, () -> clienteService.crearCliente(clienteDto));
    }
}
