package ar.edu.utn.frbb.tup.clases;
import java.time.LocalDate;
public class Cuentas extends Clientes{
    //debe estar asociada a un unico cliente por su id
    private int nroCuenta;
    private String tipoCuenta;
    private double saldo;
    private LocalDate fechaApertura;

    public int getNroCuenta() {
        return nroCuenta;
    }
    public void setNroCuenta(int nroCuenta) {
        this.nroCuenta = nroCuenta;
    }
    public String getTipoCuenta() {
        return tipoCuenta;
    }
    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }
    public double getSaldo() {
        return saldo;
    }
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
    public LocalDate getFechaApertura() {
        return fechaApertura;
    }
    public void setFechaApertura(LocalDate fechaApertura) {
        this.fechaApertura = fechaApertura;
    }
}
