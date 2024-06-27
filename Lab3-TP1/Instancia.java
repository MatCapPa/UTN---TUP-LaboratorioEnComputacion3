//22-Escriba un programa que defina una clase con una variable de instancia y un método que muestre el valor de la variable en la consola.
public class Instancia {
    private int variable;

    public Instancia(int valor) {
        variable = valor;
    }
    public void mostrarValor(){
        System.out.println("El valor de la variable es: " + variable);
    }
    public static void main(String[] args) {
        Instancia objeto = new Instancia(10);
        objeto.mostrarValor();
    }
}
