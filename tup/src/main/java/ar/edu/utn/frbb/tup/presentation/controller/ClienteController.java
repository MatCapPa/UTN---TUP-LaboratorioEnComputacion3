package ar.edu.utn.frbb.tup.presentation.controller;

import ar.edu.utn.frbb.tup.model.Cliente;
import ar.edu.utn.frbb.tup.model.exception.ClienteExistenteException;
import ar.edu.utn.frbb.tup.model.exception.ClienteMenorDeEdadException;
import ar.edu.utn.frbb.tup.model.exception.ClienteNoEncontradoException;
import ar.edu.utn.frbb.tup.presentation.controller.validator.ClienteValidator;
import ar.edu.utn.frbb.tup.presentation.modelDto.ClienteDto;
import ar.edu.utn.frbb.tup.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;
    @Autowired
    private ClienteValidator clienteValidator;


    public ClienteController( ClienteService clienteService,ClienteValidator clienteValidator){
        this.clienteService = clienteService;
        this.clienteValidator = clienteValidator;
        clienteService.inicializarClientes();
    }

    @GetMapping("/{dni}")
    public ResponseEntity<Cliente> mostrarClientePorDNI(@PathVariable long dni) throws ClienteNoEncontradoException {
        return new ResponseEntity<>(clienteService.mostrarCliente(dni), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Cliente> crearCliente(@RequestBody ClienteDto clienteDto) throws ClienteExistenteException, ClienteMenorDeEdadException {
        clienteValidator.validateCliente(clienteDto);
        Cliente cliente = clienteService.crearCliente(clienteDto);
        return new ResponseEntity<>(cliente, HttpStatus.CREATED);
    }
}
