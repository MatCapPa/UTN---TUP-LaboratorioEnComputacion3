package ar.edu.utn.frbb.tup.service;

import ar.edu.utn.frbb.tup.model.Cliente;
import ar.edu.utn.frbb.tup.model.exception.ClienteExistenteException;
import ar.edu.utn.frbb.tup.model.exception.ClienteMenorDeEdadException;
import ar.edu.utn.frbb.tup.model.exception.ClienteNoEncontradoException;
import ar.edu.utn.frbb.tup.persistence.ClienteDao;
import ar.edu.utn.frbb.tup.presentation.modelDto.ClienteDto;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;

@Service
public class ClienteService {
    private final ClienteDao clienteDao;

    public ClienteService(ClienteDao clienteDao) {
        this.clienteDao = clienteDao;
    }

    public void inicializarClientes(){clienteDao.initializeClientes();}

    public Cliente crearCliente(ClienteDto clienteDto) throws ClienteExistenteException, ClienteMenorDeEdadException {
        Cliente cliente = new Cliente(clienteDto);

        //valido que exista el cliente
        Cliente clienteEncontrado = clienteDao.findCliente(cliente.getDni());
        if (clienteEncontrado != null){
            throw new ClienteExistenteException("Ya existe un cliente con el DNI ingresado");
        }

        //valido que el cliente sea mayor de 18 años
        Period periodo = Period.between(cliente.getFechaNacimiento(), LocalDate.now());
        if (periodo.getYears() < 18){
            throw new ClienteMenorDeEdadException("Tiene que ser mayor de edad para ser cliente");
        }

        //agrega el cliente al archivo
        clienteDao.saveCliente(cliente);

        return cliente;

    }

    public Cliente mostrarCliente(long dni) throws ClienteNoEncontradoException {

        //funcion que devuelve el cliente encontrado o vuelve Null si no lo encontro
        Cliente cliente = clienteDao.findCliente(dni);

        if (cliente == null){
            throw new ClienteNoEncontradoException("No se encontro el cliente con el DNI: " + dni);
        }

        return cliente;
    }
}
