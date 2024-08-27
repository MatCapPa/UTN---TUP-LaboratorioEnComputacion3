package ar.edu.utn.frbb.tup.persistence;

import ar.edu.utn.frbb.tup.model.Cuenta;
import ar.edu.utn.frbb.tup.model.Prestamo;
import ar.edu.utn.frbb.tup.model.TipoCuenta;
import ar.edu.utn.frbb.tup.model.TipoMoneda;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Repository
public class CuentaDao extends BaseDao<Cuenta>{
    private final String RUTA_ARCHIVO = "src/main/java/ar/edu/utn/frbb/tup/persistence/data/cuenta.txt";

    private final PrestamoDao prestamoDao;

    public CuentaDao(PrestamoDao prestamoDao) {
        this.prestamoDao = prestamoDao;
    }

    public void initializeCuentas(){
        String encabezado = "Nro Cuenta, dni titular ,saldo, fecha creacion, tipo de cuenta , tipo de moneda";
        initializeArchivo(encabezado, RUTA_ARCHIVO);
    }

    public void saveCuenta(Cuenta cuenta){
        String infoAguardar = cuenta.getNumeroCuenta() + "," + cuenta.getDniTitular() + "," + cuenta.getSaldo() + "," + cuenta.getFechaCreacion() + "," + cuenta.getTipoCuenta() + "," + cuenta.getMoneda();

        saveInfo(infoAguardar, RUTA_ARCHIVO);
    }


    public Cuenta findCuenta(long nroCuenta){
        Cuenta cuenta = findInfo(nroCuenta, RUTA_ARCHIVO);
        Set<Prestamo> prestamos = prestamoDao.findAllPrestamosDeLaCuenta(nroCuenta);

        if (!prestamos.isEmpty()){
            cuenta.setPrestamos(prestamos);
        }
        return cuenta;
    }

    public void deleteCuenta(long nroCuenta){
        try {
            StringBuilder contenido = new StringBuilder(); //Creo el contenido para guardar todo lo leido

            FileReader fileReader = new FileReader(RUTA_ARCHIVO);
            BufferedReader reader = new BufferedReader(fileReader);

            //Primero agrego el encabezado al contenido,
            String linea = reader.readLine();
            contenido.append(linea).append("\n");

            while ((linea = reader.readLine()) != null) { //Condicion para que lea el archivo hasta el final y lo guarde en la variable linea
                String[] datos = linea.split(",");

                if (Long.parseLong(datos[0]) != nroCuenta){ //Voy agregando todas las lineas del Archivo excepto las lineas que tengo que eliminar con el DNI dado
                    contenido.append(linea).append("\n"); //Agrego la linea al contenido
                }
            }

            FileWriter fileWriter = new FileWriter(RUTA_ARCHIVO);
            PrintWriter writer = new PrintWriter(fileWriter);

            //Escribo el contenido excepto lo que quiero eliminar
            writer.write(contenido.toString());

            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Set<Cuenta> findAllCuentasDelCliente(long dni) {

        Set<Cuenta> cuentasDelCliente = new HashSet<>();
        try {
            File file = new File(RUTA_ARCHIVO);

            FileReader fileReader = new FileReader(file);
            BufferedReader reader = new BufferedReader(fileReader);

            //Primero agrego el encabezado al contenido,
            String linea = reader.readLine();

            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(",");

                if (Long.parseLong(datos[1]) == dni) {
                    cuentasDelCliente.add(parseDatosToObjet(datos));
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return cuentasDelCliente;
    }

    @Override
    public Cuenta parseDatosToObjet(String[] datos){
        Cuenta cuenta = new Cuenta();
        cuenta.setNumeroCuenta(Long.parseLong(datos[0]));
        cuenta.setDniTitular(Long.parseLong(datos[1]));
        cuenta.setSaldo(Long.parseLong(datos[2]));
        cuenta.setFechaCreacion(LocalDate.parse(datos[3]));
        cuenta.setTipoCuenta(TipoCuenta.valueOf(datos[4]));
        cuenta.setMoneda(TipoMoneda.valueOf(datos[5]));
        return cuenta;
    }
}
