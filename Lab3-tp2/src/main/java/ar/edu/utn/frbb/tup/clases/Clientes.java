package ar.edu.utn.frbb.tup.clases;

public class Clientes{
    
    private String nombre, apellido;
    private int dni; //<--- id
    //private int nroTelefono;
    //private String direccion;


    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellido() {
        return apellido;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public int getDni() {
        return dni;
    }
    public void setDni(int dni) {
        this.dni = dni;
    }
}
