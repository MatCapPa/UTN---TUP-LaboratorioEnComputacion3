package ar.edu.utn.frbb.tup.clases;

import java.util.ArrayList;
import java.util.List;

public class Banco {
    private List<Clientes> clientes = new ArrayList<>();
    private List<Cuentas> cuentas = new ArrayList<>();

    public List<Clientes> getClientes() {
        return clientes;
    }
    public void setClientes(List<Clientes> clientes) {
        this.clientes = clientes;
    }
    public List<Cuentas> getCuentas() {
        return cuentas;
    }
    public void setCuentas(List<Cuentas> cuentas) {
        this.cuentas = cuentas;
    }
    public String toStringClientes() {
        for (Clientes c : this.clientes) {
            System.out.println("Nombre: " + c.getNombre() 
            + " Apellido: " + c.getApellido() 
            + " DNI: " + c.getDni());
        }
        return null;
    }
    public String toStringCuentas() {
        for (Cuentas c : this.cuentas) {
            System.out.println("Nro Cuenta: " + c.getNroCuenta() 
            + " Tipo Cuenta: " + c.getTipoCuenta() 
            + " Saldo: " + c.getSaldo()
            + " Fecha Apertura: " + c.getFechaApertura());
        }
        return null;
    } 
}
