package ar.edu.utn.frbb.tup.presentation.controller;

import ar.edu.utn.frbb.tup.model.Cuenta;
import ar.edu.utn.frbb.tup.model.exception.ClienteNoEncontradoException;
import ar.edu.utn.frbb.tup.model.exception.CuentaExistenteException;
import ar.edu.utn.frbb.tup.model.exception.CuentaNoEncontradaException;
import ar.edu.utn.frbb.tup.model.exception.TipoCuentaExistenteException;
import ar.edu.utn.frbb.tup.presentation.controller.validator.CuentaValidator;
import ar.edu.utn.frbb.tup.presentation.modelDto.CuentaDto;
import ar.edu.utn.frbb.tup.service.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/cuenta")
public class CuentaController {
    @Autowired
    private CuentaValidator cuentaValidator;
    @Autowired
    private CuentaService cuentaService;

    public CuentaController(CuentaValidator cuentaValidator, CuentaService cuentaService) {
        this.cuentaValidator = cuentaValidator;
        this.cuentaService = cuentaService;
        cuentaService.inicializarCuentas();
    }

    @GetMapping("/{dni}")
    public ResponseEntity<Set<Cuenta>> getAllCuentas(@PathVariable long dni) throws CuentaNoEncontradaException, ClienteNoEncontradoException {
        return new ResponseEntity<>(cuentaService.mostrarCuenta(dni), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Cuenta> createCuenta(@RequestBody CuentaDto cuentaDto) throws TipoCuentaExistenteException, CuentaExistenteException, ClienteNoEncontradoException {
        cuentaValidator.validate(cuentaDto);
        Cuenta cuenta = cuentaService.crearCuenta(cuentaDto);
        return new ResponseEntity<>(cuenta, HttpStatus.CREATED);
    }
}
