package ar.edu.utn.frbb.tup.inputs;
import java.util.Scanner;

import ar.edu.utn.frbb.tup.clases.Banco;
import ar.edu.utn.frbb.tup.clases.Cuentas;
public class MovimientoInput {
    
    public void menuMovimiento(Banco banco,int nroCuenta) {
        Scanner entrada = new Scanner(System.in);
        boolean repetir = true;
        while (repetir) {
            System.out.println("1. Deposito");
            System.out.println("2. Retiro");
            System.out.println("3. Transferencia");
            System.out.println("4. Salir");
            System.out.print("Ingrese su elección (1-4): ");
            int eleccion = entrada.nextInt();
            entrada.nextLine();

            switch (eleccion) {
                case 1:
                    for (Cuentas cta : banco.getCuentas()){
                        if (cta.getNroCuenta() == nroCuenta)
                        {
                            System.out.println("Ingrese el monto a depositar: ");
                            double monto = entrada.nextInt();
                            cta.setSaldo(cta.getSaldo() + monto);
                        }
                    } 
                    break;
                case 2:
                    for (Cuentas cta : banco.getCuentas()){
                        if (cta.getNroCuenta() == nroCuenta)
                        {
                            System.out.println("Ingrese el monto a retirar: ");
                            double retiro = entrada.nextInt();
                            cta.setSaldo(cta.getSaldo() - retiro);
                        }
                    }
                    break;
                case 3:
                    for (Cuentas cta : banco.getCuentas()){
                        if (cta.getNroCuenta() == nroCuenta)
                        {
                            System.out.println("Ingrese el monto a transferir: ");
                            double transferencia = entrada.nextInt();
                            System.out.println("Ingrese el nro de cuenta a la que quiere transferir:");
                            int nroCuentaTransferencia = entrada.nextInt();
                            for (Cuentas cta2 : banco.getCuentas()){
                                if (cta2.getNroCuenta() == nroCuentaTransferencia)
                                {
                                    cta.setSaldo(cta.getSaldo() - transferencia);
                                    cta2.setSaldo(cta2.getSaldo() + transferencia);
                                }
                            }
                        }
                    }
                    break;
                case 4:
                    repetir = false;
                    entrada.close();
                    break;
                default:
            }
        }
    }
}
