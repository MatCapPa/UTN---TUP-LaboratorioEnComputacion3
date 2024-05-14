package ar.edu.utn.frbb.tup.inputs;

import ar.edu.utn.frbb.tup.clases.Banco;
import ar.edu.utn.frbb.tup.clases.Clientes;
import ar.edu.utn.frbb.tup.clases.Cuentas;


import java.util.List;
import java.util.Scanner;
public class MenuInput {
    ClientesInput clientesInput = new ClientesInput();
    CuentasInput cuentasInput = new CuentasInput();
    Scanner entrada = new Scanner(System.in);
    boolean repetir = true;

    public void menu(Banco banco){
        while (repetir)
        {
            System.out.println("Bienveido a la aplicación de Banco!");
            System.out.println("1. Crear un nuevo Cliente");
            System.out.println("2. Crear una nueva Cuenta");
            System.out.println("3. Generar un movimiento");  
            System.out.println("4. Salir");
            System.out.print("Ingrese su opción (1-4): ");
            int opcion = entrada.nextInt();
            entrada.nextLine(); 

            switch(opcion){
                case 1:
                    Clientes c = clientesInput.ingresarCliente();
                    banco.getClientes().add(c);
                    banco.toStringClientes();
                    break;
                case 2:
                    System.out.println("Ingrese su dni para crear su cuenta: ");
                    int dni = entrada.nextInt();
                    if (idCliente(banco.getClientes(),dni)){
                        Cuentas cta = cuentasInput.ingresarCuenta();
                        banco.getCuentas().add(cta);
                        banco.toStringCuentas();
                    }else {
                        System.out.println("No existe un cliente con ese dni");
                    }
                    break;
                case 3:
                    System.out.println("Ingrese el nro de cuenta: ");
                    int nroCuenta = entrada.nextInt();
                    if (idCuenta(banco.getCuentas(),nroCuenta)){
                        MovimientoInput menuMovimiento = new MovimientoInput();
                        menuMovimiento.menuMovimiento(banco,nroCuenta);
                    }else{
                        System.out.println("No existe una cuenta con ese nro de cuenta");
                    }
                    
                    break;
                case 4:
                    repetir = false;
                    break;
                default:
                    System.out.println("Opción inválida. Por favor seleccione 1-4.");
            }
        }
    }

    public boolean idCliente(List<Clientes> lista,int dniABuscar){
        for (Clientes c : lista){
            if (c.getDni() == dniABuscar){
                return true;
            }
        }
        return false;         
    }

    public boolean idCuenta(List<Cuentas> lista,int nroCuentaABuscar){
        for (Cuentas cta : lista){
            if (cta.getNroCuenta() == nroCuentaABuscar){
                return true;
            }
        }
        return false;         
    }
}
