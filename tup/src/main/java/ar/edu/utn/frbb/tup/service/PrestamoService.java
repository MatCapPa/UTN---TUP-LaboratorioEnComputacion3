package ar.edu.utn.frbb.tup.service;

import ar.edu.utn.frbb.tup.model.Cuenta;
import ar.edu.utn.frbb.tup.model.Prestamo;
import ar.edu.utn.frbb.tup.model.exception.CuentaNoEncontradaException;
import ar.edu.utn.frbb.tup.model.exception.PrestamosVacioException;
import ar.edu.utn.frbb.tup.persistence.CuentaDao;
import ar.edu.utn.frbb.tup.persistence.PrestamoDao;
import ar.edu.utn.frbb.tup.presentation.modelDto.PrestamoDto;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Set;

@Service
public class PrestamoService {
    private final CuentaDao cuentaDao;
    private final PrestamoDao prestamoDao;
    private final ScoreCrediticioService scoreCrediticioService;

    public PrestamoService(CuentaDao cuentaDao,PrestamoDao prestamoDao, ScoreCrediticioService scoreCrediticioService) {
        this.cuentaDao = cuentaDao;
        this.prestamoDao = prestamoDao;
        this.scoreCrediticioService = scoreCrediticioService;
    }

    public void inicializarPrestamo() {
        prestamoDao.initializePrestamo();
    }

    public Prestamo crearPrestamo(PrestamoDto prestamoDto) throws CuentaNoEncontradaException{
        Prestamo prestamo = new Prestamo(prestamoDto);

        //verifico que la cuenta exista
        Cuenta cuenta = cuentaDao.findCuenta(prestamo.getNroCuenta());
        if (cuenta == null){
            throw new CuentaNoEncontradaException("No se encontro una cuenta con el Numero "+ prestamo.getNroCuenta());
        }

        //verifico que el prestamo sea en la misma moneda
        if (prestamo.getMoneda() != cuenta.getMoneda()){
            throw new CuentaNoEncontradaException("Error. Esta realizando un prestamo con una moneda distinta. Moneda de la cuenta: " + cuenta.getMoneda());
        }

        //establesco el valor de cada cuota
        establecerCuotas(prestamo);

        //el scoreCrediticioService genera un numero random y segun ese numero se realiza el prestamo o no
        scoreCrediticioService.verificarCalificacionCreditaria(prestamo);
        if (Objects.equals(prestamo.getEstado(), "APROBADO")){
            prestamo.setMensaje("El monto del préstamo fue acreditado en su cuenta");

            //se suma el dinero del prestamo a la cuenta
            cobrarPrestamo(cuenta, prestamo);

            prestamoDao.savePrestamo(prestamo);
        }
        else{
            prestamo.setMensaje("No fue posiblie acreditar el prestamo a su cuenta");
        }

        return prestamo;
    }

    private void cobrarPrestamo(Cuenta cuenta, Prestamo prestamo) {

        //sumo a la cuenta la cantidad del prestamo adquirido
        cuenta.setSaldo(cuenta.getSaldo() +  prestamo.getMontoDelPrestamo());

        cuentaDao.deleteCuenta(cuenta.getNumeroCuenta());
        cuentaDao.saveCuenta(cuenta);

    }

    private void establecerCuotas(Prestamo prestamo){
        double cuotaOriginal = prestamo.getMontoDelPrestamo() * 1.05;
        double cuotaAPagar = cuotaOriginal / prestamo.getPlazosMeses();

        prestamo.setMontoCuota((long) cuotaAPagar);
        prestamo.setNroCuota(1);

    }

    public Set<Prestamo> mostrarPrestamo(long nroCuenta) throws CuentaNoEncontradaException , PrestamosVacioException {
        Cuenta cuenta = cuentaDao.findCuenta(nroCuenta);

        //verifico que exista la cuenta que se recibio del endpoint /{idCliente}
        if (cuenta == null){
            throw new CuentaNoEncontradaException("No se encontro una cuenta con el numero " + nroCuenta);
        }

        //establece la lista de prestamos asociados a la cuenta
        Set<Prestamo> prestamos = prestamoDao.findAllPrestamosDeLaCuenta(nroCuenta);

        //si la lista esta vacia, entonces no hay prestamos en la cuenta
        if (prestamos.isEmpty()){
            throw new PrestamosVacioException("No hay prestamos asociados a su cuenta");
        }

        return prestamos;
    }
}
