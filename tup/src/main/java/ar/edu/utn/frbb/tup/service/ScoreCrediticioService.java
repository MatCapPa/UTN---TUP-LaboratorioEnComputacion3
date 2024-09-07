package ar.edu.utn.frbb.tup.service;

import ar.edu.utn.frbb.tup.model.Prestamo;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class ScoreCrediticioService {

    public void verificarCalificacionCreditaria(Prestamo prestamo) {
//        Random random = new Random();
//        int nro = random.nextInt(90) +10; nro <= 10 || nro >= 90
        if (prestamo.getMontoDelPrestamo() > 15000){
            prestamo.setEstado("RECHAZADO");
        }
        else prestamo.setEstado("APROBADO");
    }
}
