//25-Escriba un programa que defina una clase con un método estático que acepte dos
//argumentos y devuelva un valor calculado basado en los argumentos.
public class Suma {
    public static int getSumaNumeros(int a, int b) {
        return a + b;
    }
    public static void main(String[] args) {
        if (args.length <= 1) {
            System.out.println("No se han proporcionado 2 numeros como argumentos.");
            
        }
        else {
            int numero1 = Integer.parseInt(args[0]),numero2 = Integer.parseInt(args[1]);
            int resultado = getSumaNumeros(numero1,numero2);
            System.out.println("Resultado de la suma: " + resultado);
        }
    }
}
