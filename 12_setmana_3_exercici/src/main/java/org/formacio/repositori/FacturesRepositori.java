package org.formacio.repositori;


import org.formacio.domain.Factura;
import org.springframework.data.repository.CrudRepository;


public interface FacturesRepositori extends CrudRepository<Factura, Long> {


    Number totalClient(String client);

}
