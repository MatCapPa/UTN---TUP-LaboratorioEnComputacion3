package ar.edu.utn.frbb.tup.presentation.modelDto;

public class PersonaDto {
    private String nombre;
    private String apellido;
    private long dni;
    private String fechaNacimiento;

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setDni(long dni) {
        this.dni = dni;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public long getDni() {
        return dni;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

}
