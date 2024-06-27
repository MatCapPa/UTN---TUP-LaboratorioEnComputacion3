//TP1 - (16)
public class ConvertirBinario {
    public static void main(String[] args) {
        if ((args.length == 0) || (args.length >1)){
            System.out.println("No se han proporcionado numeros como argumentos.");
            
        }else{
            //int a = Integer.parseInt(args[0]);
            //System.out.println(a);
            //System.out.println(args[0]);
            boolean cociente_distintode_Cero=true;
            int cociente =Integer.parseInt(args[0]),resto;
            String numero_Bianrio= "";
            while (cociente_distintode_Cero){
                resto = cociente % 2;
                cociente = cociente / 2;
                
                numero_Bianrio = resto + numero_Bianrio;
                if (cociente == 0){
                    cociente_distintode_Cero = false;
                }
            }
            System.out.println("Numero ingresado: ("+args[0]+")10\nNumero en binario: ("+numero_Bianrio+")2");
        }
    }
}
