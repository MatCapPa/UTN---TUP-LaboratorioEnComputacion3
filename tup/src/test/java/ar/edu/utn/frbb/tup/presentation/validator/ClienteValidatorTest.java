package ar.edu.utn.frbb.tup.presentation.validator;

import ar.edu.utn.frbb.tup.presentation.controller.validator.ClienteValidator;
import ar.edu.utn.frbb.tup.presentation.modelDto.ClienteDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ClienteValidatorTest {

    ClienteValidator clienteValidator;

    @BeforeEach
    public void iniciar(){clienteValidator = new ClienteValidator();}


    @Test
    public void testClienteSuccess(){
        ClienteDto clienteDto = new ClienteDto();
        clienteDto.setNombre("Nicolas");
        clienteDto.setApellido("Gonzalez");
        clienteDto.setTipoPersona("F");
        clienteDto.setBanco("Provincia");
        clienteDto.setFechaNacimiento("2002-09-15");
        clienteDto.setDni(44111222);

        assertDoesNotThrow(() -> clienteValidator.validateCliente(clienteDto));
    }

    @Test
    public void testClienteSinNombreException(){
        ClienteDto clienteDto = new ClienteDto();
        //clienteDto.setNombre("Nicolas");
        clienteDto.setApellido("Gonzalez");
        clienteDto.setTipoPersona("F");
        clienteDto.setBanco("Provincia");
        clienteDto.setFechaNacimiento("2002-09-15");
        clienteDto.setDni(44111222);

        assertThrows(IllegalArgumentException.class, () -> clienteValidator.validateCliente(clienteDto));
    }

    @Test
    public void testClienteSinApellidoException(){
        ClienteDto clienteDto = new ClienteDto();
        clienteDto.setNombre("Nicolas");
        //clienteDto.setApellido("Gonzalez");
        clienteDto.setTipoPersona("F");
        clienteDto.setBanco("Provincia");
        clienteDto.setFechaNacimiento("2002-09-15");
        clienteDto.setDni(44111222);

        assertThrows(IllegalArgumentException.class, () -> clienteValidator.validateCliente(clienteDto));
    }

    @Test
    public void testClienteSinTipoPersonaException(){
        ClienteDto clienteDto = new ClienteDto();
        clienteDto.setNombre("Nicolas");
        clienteDto.setApellido("Gonzalez");
        //clienteDto.setTipoPersona("F");
        clienteDto.setBanco("Provincia");
        clienteDto.setFechaNacimiento("2002-09-15");
        clienteDto.setDni(44111222);

        assertThrows(IllegalArgumentException.class, () -> clienteValidator.validateCliente(clienteDto));
    }

    @Test
    public void testClienteSinBancoException(){
        ClienteDto clienteDto = new ClienteDto();
        clienteDto.setNombre("Nicolas");
        clienteDto.setApellido("Gonzalez");
        clienteDto.setTipoPersona("F");
        //clienteDto.setBanco("Provincia");
        clienteDto.setFechaNacimiento("2002-09-15");
        clienteDto.setDni(44111222);

        assertThrows(IllegalArgumentException.class, () -> clienteValidator.validateCliente(clienteDto));
    }

    @Test
    public void testClienteSinFechaDeNacimientoException(){
        ClienteDto clienteDto = new ClienteDto();
        clienteDto.setNombre("Nicolas");
        clienteDto.setApellido("Gonzalez");
        clienteDto.setTipoPersona("F");
        clienteDto.setBanco("Provincia");
        //clienteDto.setFechaNacimiento("2002-09-15");
        clienteDto.setDni(44111222);

        assertThrows(IllegalArgumentException.class, () -> clienteValidator.validateCliente(clienteDto));
    }

    @Test
    public void testClienteSinDNI(){
        ClienteDto clienteDto = new ClienteDto();
        clienteDto.setNombre("Nicolas");
        clienteDto.setApellido("Gonzalez");
        clienteDto.setTipoPersona("F");
        clienteDto.setBanco("Provincia");
        clienteDto.setFechaNacimiento("2002-09-15");
        //clienteDto.setDni(44111222);

        assertThrows(IllegalArgumentException.class, () -> clienteValidator.validateCliente(clienteDto));
    }

    @Test
    public void testClienteConDniInvalido(){
        ClienteDto clienteDto = new ClienteDto();
        clienteDto.setNombre("Nicolas");
        clienteDto.setApellido("Gonzalez");
        clienteDto.setTipoPersona("F");
        clienteDto.setBanco("Provincia");
        clienteDto.setFechaNacimiento("2002-09-15");
        clienteDto.setDni(84111222); //el dni tiene que ser mayor de 10.000.000 y menor de 80.000.000

        assertThrows(IllegalArgumentException.class, () -> clienteValidator.validateCliente(clienteDto));
    }
}
