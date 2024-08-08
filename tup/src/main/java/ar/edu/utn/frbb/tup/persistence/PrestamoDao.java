package ar.edu.utn.frbb.tup.persistence;

import ar.edu.utn.frbb.tup.model.Prestamo;
import ar.edu.utn.frbb.tup.model.TipoMoneda;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Repository
public class PrestamoDao extends BaseDao<Prestamo> {
    private final String RUTA_ARCHIVO = "src/main/java/ar/edu/utn/frbb/tup/persistence/data/prestamo.txt";

    public void initializePrestamo(){
        String encabezado = "nro de la cuenta, estado, mesaje, numero de cuotas, monto de las cuotas , plazos meses, monto del prestamo, moneda asociada la prestamo";
        initializeArchivo(encabezado, RUTA_ARCHIVO);
    }

    public void savePrestamo(Prestamo prestamo){
        String infoAguardar = prestamo.getNroCuenta() + "," + prestamo.getEstado() + "," + prestamo.getMensaje() + "," + prestamo.getNroCuota() + "," + prestamo.getMontoCuota() + "," + prestamo.getPlazosMeses() + "," + prestamo.getMontoDelPrestamo() + "," + prestamo.getMoneda();
        saveInfo(infoAguardar, RUTA_ARCHIVO);
    }

//    public Prestamo findPrestamo(long nroCuenta){
//        return findInfo(nroCuenta, RUTA_ARCHIVO);
//    }

    public Set<Prestamo> findAllPrestamosDeLaCuenta(long nroCuenta) {

        Set<Prestamo> prestamosDeLaCuenta = new HashSet<>();
        try {
            File file = new File(RUTA_ARCHIVO);

            FileReader fileReader = new FileReader(file);
            BufferedReader reader = new BufferedReader(fileReader);

            //Primero agrego el encabezado al contenido,
            String linea = reader.readLine();

            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(",");

                if (Long.parseLong(datos[0]) == nroCuenta) {
                    prestamosDeLaCuenta.add(parseDatosToObjet(datos));
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return prestamosDeLaCuenta;
    }


    @Override
    public Prestamo parseDatosToObjet(String[] datos){
        Prestamo prestamo = new Prestamo();
        prestamo.setNroCuenta(Long.parseLong(datos[0]));
        prestamo.setEstado(datos[1]);
        prestamo.setMensaje(datos[2]);
        prestamo.setNroCuota(Integer.parseInt(datos[3]));
        prestamo.setMontoCuota(Long.parseLong(datos[4]));
        prestamo.setPlazosMeses(Integer.parseInt(datos[5]));
        prestamo.setMontoDelPrestamo(Long.parseLong(datos[6]));
        prestamo.setMoneda(TipoMoneda.valueOf(datos[7]));
        return prestamo;
    }

}
