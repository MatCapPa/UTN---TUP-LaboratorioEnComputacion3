package ar.edu.utn.frbb.tup.model;

import ar.edu.utn.frbb.tup.presentation.modelDto.PrestamoDto;

public class Prestamo {
    private String estado;
    private String mensaje;
    private int nroCuota;
    private long montoCuota;

    private long nroCuenta;
    private int plazosMeses;
    private long montoDelPrestamo;
    private TipoMoneda moneda;

    public Prestamo(){

    }

    public Prestamo(PrestamoDto prestamoDto){
        this.moneda = TipoMoneda.fromString(prestamoDto.getMoneda());
        this.montoDelPrestamo = prestamoDto.getMontoDelPrestamo();
        this.plazosMeses = prestamoDto.getPlazosMeses();
        this.nroCuenta = prestamoDto.getNroCuenta();
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public void setNroCuota(int nroCuota) {
        this.nroCuota = nroCuota;
    }

    public void setMontoCuota(long montoCuota) {
        this.montoCuota = montoCuota;
    }

    public String getEstado() {
        return estado;
    }

    public String getMensaje() {
        return mensaje;
    }

    public int getNroCuota() {
        return nroCuota;
    }

    public long getMontoCuota() {
        return montoCuota;
    }



    public void setNroCuenta(long nroCuenta) {
        this.nroCuenta = nroCuenta;
    }

    public void setPlazosMeses(int plazosMeses) {
        this.plazosMeses = plazosMeses;
    }

    public void setMontoDelPrestamo(long montoDelPrestamo) {
        this.montoDelPrestamo = montoDelPrestamo;
    }

    public void setMoneda(TipoMoneda moneda) {
        this.moneda = moneda;
    }

    public long getNroCuenta() {
        return nroCuenta;
    }

    public int getPlazosMeses() {
        return plazosMeses;
    }

    public TipoMoneda getMoneda() {
        return moneda;
    }

    public long getMontoDelPrestamo() {
        return montoDelPrestamo;
    }
}
