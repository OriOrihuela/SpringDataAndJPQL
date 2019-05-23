package org.formacio.servei;

import org.formacio.domain.Factura;
import org.formacio.repositori.FacturesRepositori;
import org.springframework.stereotype.Service;

@Service
public class FacturesService {

    /* ---- Properties of the class ---- */
    private FacturesRepositori facturesRepositori = null;


    /* ---- Getters ---- */
    public FacturesRepositori getFacturesRepositori() {
        return facturesRepositori;
    }

    /*
     * Aquest metode ha de carregar la factura amb id idFactura i afegir una nova linia amb les dades
     * passades (producte i totalProducte)
     *
     * S'ha de retornar la factura modificada
     *
     * Per implementar aquest metode necessitareu una referencia (dependencia) a FacturesRepositori
     */
    public Factura afegirProducte(long idFactura, String producte, int totalProducte) {

        return null;
    }
}
