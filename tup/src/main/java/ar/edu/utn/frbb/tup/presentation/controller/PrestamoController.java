package ar.edu.utn.frbb.tup.presentation.controller;


import ar.edu.utn.frbb.tup.model.Prestamo;
import ar.edu.utn.frbb.tup.model.exception.CuentaNoEncontradaException;
import ar.edu.utn.frbb.tup.model.exception.PrestamosVacioException;
import ar.edu.utn.frbb.tup.presentation.controller.validator.PrestamoValidator;
import ar.edu.utn.frbb.tup.presentation.modelDto.PrestamoDto;
import ar.edu.utn.frbb.tup.service.PrestamoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/prestamo")
public class PrestamoController {

    @Autowired
    private PrestamoService prestamoService;

    @Autowired
    private PrestamoValidator prestamoValidator;

    public PrestamoController(PrestamoService prestamoService, PrestamoValidator prestamoValidator){
        this.prestamoService = prestamoService;
        this.prestamoValidator = prestamoValidator;
        prestamoService.inicializarPrestamo();
    }

    @PostMapping
    public ResponseEntity<Prestamo> createPrestamo(@RequestBody PrestamoDto prestamoDto) throws CuentaNoEncontradaException{
        prestamoValidator.validate(prestamoDto);
        Prestamo prestamo = prestamoService.crearPrestamo(prestamoDto);
        return new ResponseEntity<>(prestamo, HttpStatus.CREATED);
    }

    @GetMapping("/{idCliente}")
    public ResponseEntity<Set<Prestamo>> mostrarPrestamosDelCliente(@PathVariable long idCliente) throws CuentaNoEncontradaException , PrestamosVacioException {
        return new ResponseEntity<>(prestamoService.mostrarPrestamo(idCliente),HttpStatus.OK);
    }

}
