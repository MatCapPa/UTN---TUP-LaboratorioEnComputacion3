package ar.edu.utn.frbb.tup.presentation.modelDto;

public class PrestamoDto {
    private long nroCuenta;
    private int plazosMeses;
    private long montoDelPrestamo;
    private String moneda;

    public void setNroCuenta(long nroCuenta) {
        this.nroCuenta = nroCuenta;
    }

    public void setPlazosMeses(int plazosMeses) {
        this.plazosMeses = plazosMeses;
    }

    public void setMontoDelPrestamo(long montoDelPrestamo) {
        this.montoDelPrestamo = montoDelPrestamo;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public long getNroCuenta() {
        return nroCuenta;
    }

    public int getPlazosMeses() {
        return plazosMeses;
    }

    public String getMoneda() {
        return moneda;
    }

    public long getMontoDelPrestamo() {
        return montoDelPrestamo;
    }
}
