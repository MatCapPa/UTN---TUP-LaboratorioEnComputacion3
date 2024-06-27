import java.util.Arrays;
//TP1 - (15)
public class OrdenarNumeros {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("No se han proporcionado numeros como argumentos.");
            
        }
        else {
            double[] numeros = new double[args.length];
            for (int i = 0; i < args.length; i++) {
                try {
                    numeros[i] = Double.parseDouble(args[i]);
                } catch (NumberFormatException e) {
                    System.out.println("El argumento \"" + args[i] + "\" no es un numero valido.");
                    return;
                }
            }

            Arrays.sort(numeros);

            System.out.println("Numeros ordenados de menor a mayor:");
            for (double num : numeros) {
                System.out.println(num);
            }
        }
    }
}