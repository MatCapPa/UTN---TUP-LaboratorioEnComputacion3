package ar.edu.utn.frbb.tup.inputs;

import ar.edu.utn.frbb.tup.clases.Cuentas;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CuentasInput {
    private static List<Cuentas> cuentas = new ArrayList<>();
    Scanner entrada = new Scanner(System.in);
    public Cuentas ingresarCuenta(){
        
        
        Cuentas cuenta = new Cuentas();

        System.out.println("Ingrese el numero de la cuenta");
        cuenta.setNroCuenta(entrada.nextInt());
        System.out.println("Ingrese el tipo de la cuenta");
        cuenta.setTipoCuenta(entrada.nextLine());
        System.out.println("Ingrese el balance de la cuenta");
        cuenta.setSaldo(Integer.parseInt(entrada.nextLine()));
        System.out.println("Ingrese la fecha de creacion de la cuenta (Formato: YYYY-MM-DD):");
        LocalDate fechaCreacion = null;
        boolean fechaValida = false;
        while (!fechaValida) {
            try {
                fechaCreacion = LocalDate.parse(entrada.nextLine());
                fechaValida = true;
            } catch (Exception e) {
                System.out.println("Formato de fecha inválido. Ingrese la fecha en formato YYYY-MM-DD:");
            }
        }
        cuenta.setFechaApertura(fechaCreacion);
        return cuenta;
    }
}
