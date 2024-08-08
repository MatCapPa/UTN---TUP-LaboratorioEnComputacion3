package ar.edu.utn.frbb.tup.presentation.controller.validator;

import ar.edu.utn.frbb.tup.presentation.modelDto.ClienteDto;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@Component
public class ClienteValidator {
    public void validateCliente(ClienteDto clienteDto){
        validateEntrada(clienteDto);
        validateFechaNacimiento(clienteDto.getFechaNacimiento());
    }

    public void validateEntrada(ClienteDto clienteDto){
        //Nombre
        if (clienteDto.getNombre() == null || clienteDto.getNombre().isEmpty()) throw new IllegalArgumentException("Error al ingresar el nombre");
        //Apellido
        if (clienteDto.getApellido() == null || clienteDto.getApellido().isEmpty()) throw new IllegalArgumentException("Error al ingresar el apellido");
        //Fecha de nacimiento
        if (clienteDto.getFechaNacimiento() == null || clienteDto.getFechaNacimiento().isEmpty()) throw new IllegalArgumentException("Error al ingresar la fecha de nacimiento");
        //Banco
        if (clienteDto.getBanco() == null || clienteDto.getBanco().isEmpty()) throw new IllegalArgumentException("Error al ingresar el banco");
        //Tipo de Persona
        if (clienteDto.getTipoPersona() == null || clienteDto.getTipoPersona().isEmpty()) throw new IllegalArgumentException("Error al ingresar un Tipo Persona");
        //DNI
        if (clienteDto.getDni() <= 10000000 || clienteDto.getDni() >=80000000)throw new IllegalArgumentException("Error: El nro de DNI valido es entre '10.000.000' a '79.999.999'");
    }

    private void validateFechaNacimiento(String fechaNacimiento) {
        try{
            LocalDate.parse(fechaNacimiento);
        } catch (DateTimeParseException e){
            throw new IllegalArgumentException("Error: La fecha de nacimiento no es correcta");
        }
    }
}
