package org.formacio.servei;

import org.formacio.domain.Factura;
import org.formacio.domain.LiniaFactura;
import org.formacio.repositori.FacturesRepositori;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class FacturesService {

    /* ---- "Properties" of the class ---- */
    @Autowired
    private FacturesRepositori facturesRepositori = null;

    @Autowired
    private FidalitzacioService fidalitzacioService = null;

    /*
     * Aquest metode ha de carregar la factura amb id idFactura i afegir una nova linia amb les dades
     * passades (producte i totalProducte)
     *
     * S'ha de retornar la factura modificada
     *
     * Per implementar aquest metode necessitareu una referencia (dependencia) a FacturesRepositori
     */
    public Factura afegirProducte(long idFactura, String producte, int totalProducte) {

        Optional<Factura> facturaOptional = facturesRepositori.findById(idFactura);

        if (facturaOptional.isPresent()) {
            Factura factura = facturaOptional.get();
            LiniaFactura liniaFactura = new LiniaFactura();
            liniaFactura.setProducte(producte);
            liniaFactura.setTotal(totalProducte);
            factura.getLinies().add(liniaFactura);
            facturesRepositori.save(factura);

            if (factura.getLinies().size() >= 4) {
                notificarPremiAlClient(facturaOptional.get());
            }
            return factura;
        }
        System.out.println("This product cannot be added");
        return null;
    }

    private void notificarPremiAlClient (Factura factura) {
        fidalitzacioService.notificaRegal(factura.getClient().getEmail());
    }
}
