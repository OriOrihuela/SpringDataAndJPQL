package org.formacio.repositori;


import org.formacio.domain.Factura;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface FacturesRepositori extends CrudRepository<Factura, Long> {


    Number totalClient(String client);

    List<Factura> findByTotalClient_Nom(String nom);

}
