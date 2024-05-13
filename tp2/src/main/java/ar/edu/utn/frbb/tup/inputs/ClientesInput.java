package ar.edu.utn.frbb.tup.inputs;

import ar.edu.utn.frbb.tup.clases.Clientes;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ClientesInput {
    private static List<Clientes> clientes = new ArrayList<>();
    Scanner entrada = new Scanner(System.in);

    public Clientes ingresarCliente(){
        Clientes cliente = new Clientes();
        System.out.println("Ingrese el nombre del cliente: ");
        String nombre = entrada.nextLine();
        System.out.println("Ingrese el apellido del cliente: ");
        String apellido = entrada.nextLine();
        System.out.println("Ingrese el dni del cliente: ");
        int dni = entrada.nextInt();
        cliente.setNombre(nombre);
        cliente.setApellido(apellido);
        cliente.setDni(dni);
        return cliente;
    }
}
