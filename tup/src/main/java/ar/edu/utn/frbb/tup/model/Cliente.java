package ar.edu.utn.frbb.tup.model;

import ar.edu.utn.frbb.tup.presentation.modelDto.ClienteDto;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Cliente extends Persona{
    private String banco;
    private TipoPersona tipoPersona;
    private LocalDate fechaAlta;
    private Set<Cuenta> cuentas = new HashSet<>();

    public Cliente(){
        super();
        this.fechaAlta = LocalDate.now();
    }

    public Cliente(ClienteDto clienteDto){
        super(
                clienteDto.getDni(),
                clienteDto.getApellido(),
                clienteDto.getNombre(),
                clienteDto.getFechaNacimiento() != null ? LocalDate.parse(clienteDto.getFechaNacimiento()) : null
        );
        this.banco = clienteDto.getBanco().toLowerCase();
        this.tipoPersona = clienteDto.getTipoPersona() != null ? TipoPersona.fromString(clienteDto.getTipoPersona()) : null;
        this.fechaAlta = LocalDate.now();
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public void setTipoPersona(TipoPersona tipoPersona) {
        this.tipoPersona = tipoPersona;
    }

    public void setFechaAlta(LocalDate fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public String getBanco() {
        return banco;
    }

    public TipoPersona getTipoPersona() {
        return tipoPersona;
    }

    public LocalDate getFechaAlta() {
        return fechaAlta;
    }

    public Set<Cuenta> getCuentas() {
        return cuentas;
    }
    public void setCuentas(Set<Cuenta> cuentas) {
        this.cuentas = cuentas;
    }

    public void addCuenta(Cuenta cuenta){
        this.cuentas.add(cuenta);
    }
}
