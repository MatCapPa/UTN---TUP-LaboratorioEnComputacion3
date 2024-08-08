package ar.edu.utn.frbb.tup.persistence;

import java.io.*;

public abstract class BaseDao<T> {

    public void initializeArchivo(String encabezado, String rutaArchivo){
        PrintWriter writer = null;

        try {
            File archivo = new File(rutaArchivo);

            if (!archivo.exists()) {
                //Si no existe, lo creo y guardo el Encabezado para saber el orden de los datos
                FileWriter fileWriter = new FileWriter(rutaArchivo, true);
                writer = new PrintWriter(fileWriter);
                writer.println(encabezado);
            }

        } catch (IOException e) {
            System.out.println("No se pudo abrir el archivo");
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    public void saveInfo(String info, String rutaArchivo){
        //En el try abre directamente el archivo, y cuando termina cierra automaticamente el recurso

        try (FileWriter archivo = new FileWriter(rutaArchivo, true);){

            PrintWriter writer = new PrintWriter(archivo);

            writer.println(info);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public T findInfo(long id, String rutaArchivo){
        try {
            File file = new File(rutaArchivo);

            FileReader fileReader = new FileReader(file);
            BufferedReader reader = new BufferedReader(fileReader);

            String linea; //Leo el encabezado
            linea = reader.readLine(); //Salto encabezado

            while ((linea = reader.readLine()) != null) { //Leo todas las lineas de info hasta la ultima
                String[] datos = linea.split(",");

                if (Long.parseLong(datos[0]) == id){ //Si cumple condicion significa que la info buscada fue encontrada
                    reader.close();
                    return parseDatosToObjet(datos);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public abstract T parseDatosToObjet(String[] datos);

}
