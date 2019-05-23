package org.formacio.repositori;


import org.formacio.domain.Factura;
import org.springframework.data.repository.Repository;

public interface FacturesRepositori extends Repository<Factura, Long> {

    Number totalClient(String client);

}
