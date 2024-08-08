package ar.edu.utn.frbb.tup.model;

import java.time.LocalDate;

public class Persona {
    private String nombre;
    private String apellido;
    private long dni;
    private LocalDate fechaNacimiento;

    public Persona(){}

    public Persona(long dni, String apellido, String nombre, LocalDate fechaNacimiento) {
        this.dni = dni;
        this.apellido = apellido;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setDni(long dni) {
        this.dni = dni;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public long getDni() {
        return dni;
    }
}
