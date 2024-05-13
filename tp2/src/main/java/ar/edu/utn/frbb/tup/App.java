package ar.edu.utn.frbb.tup;

import ar.edu.utn.frbb.tup.clases.Banco;
import ar.edu.utn.frbb.tup.inputs.MenuInput;
public class App {

    public static void main(String[] args) {
        Banco banco = new Banco();
        MenuInput menuInput = new MenuInput();
        menuInput.menu(banco);
    }
}
