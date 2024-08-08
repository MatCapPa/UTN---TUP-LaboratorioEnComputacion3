package ar.edu.utn.frbb.tup.model;

import ar.edu.utn.frbb.tup.presentation.modelDto.CuentaDto;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Cuenta {
    private long numeroCuenta;
    private LocalDate fechaCreacion;
    private TipoCuenta tipoCuenta;
    private TipoMoneda moneda;
    private long saldo;
    private long dniTitular;
    private Set<Prestamo> prestamos = new HashSet<>();

    public Set<Prestamo> getPrestamos() {
        return prestamos;
    }

    public void setPrestamos(Set<Prestamo> prestamos) {
        this.prestamos = prestamos;
    }

    public Cuenta(){
        Random nro =new Random();
        this.fechaCreacion = LocalDate.now();
        this.numeroCuenta = nro.nextInt(9000) + 1000;
    }

    public Cuenta(CuentaDto cuentaDto){
        Random nro =new Random();
        this.saldo = cuentaDto.getSaldo();
        this.fechaCreacion = LocalDate.now();
        this.numeroCuenta = nro.nextInt(9000) + 1000;
        this.dniTitular = cuentaDto.getDniTitular();
        this.tipoCuenta = TipoCuenta.fromString(cuentaDto.getTipoCuenta());
        this.moneda = TipoMoneda.fromString(cuentaDto.getTipoMoneda());
    }

    public long getSaldo() {
        return saldo;
    }
    public Cuenta setSaldo(long saldo) {
        this.saldo = saldo;
        return this;
    }

    public void setDniTitular(long dniTitular) {
        this.dniTitular = dniTitular;
    }

    public long getDniTitular() {
        return dniTitular;
    }

    public Cuenta setNumeroCuenta(long numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
        return this;
    }

    public Cuenta setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
        return this;
    }

    public void setMoneda(TipoMoneda moneda) {
        this.moneda = moneda;
    }

    public TipoMoneda getMoneda() {
        return moneda;
    }

    public long getNumeroCuenta() {
        return numeroCuenta;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public TipoCuenta getTipoCuenta() {
        return tipoCuenta;
    }
    public void setTipoCuenta(TipoCuenta tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }


}
