//26-Escriba un programa que defina una clase con una variable final y un método que
//muestre el valor de la variable en la consola.
public class VFinal {
    private final int numero;

    public VFinal(int valor) {
        numero = valor;
    }
    public void mostrarValor() {
        System.out.println(numero);
    }

    public static void main(String[] args) {
        VFinal objeto = new VFinal(6);
        objeto.mostrarValor();
    }
}
