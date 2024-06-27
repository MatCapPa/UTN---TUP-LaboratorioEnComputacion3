//23-Escriba un programa que defina una clase con un constructor y un método que devuelva un valor calculado 
//basado en los argumentos proporcionados al constructor.
public class Saludar {
    private String a, b;

    public Saludar(String a1 ,String a2){
        a=a1;
        b=a2;
    }
    public String getSaludo(){
        return a+" "+b;
    }

    public static void main(String[] args) {
        Saludar objeto = new Saludar("Hola","Mundo");
        String saludo = objeto.getSaludo();
        System.out.println(saludo);
    }
}
