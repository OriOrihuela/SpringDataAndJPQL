package org.formacio.domain;

import javax.persistence.*;

@Entity
@Table(name = "t_liniesfact")
public class LiniaFactura {

    /* ---- Properties of the class ---- */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lin_id")
    private Long id;

    @Column(name = "lin_producte")
    private String producte;

    @Column(name = "lin_total")
    private Integer total;


    /* ---- Getters ---- */
    public Long getId() {
        return id;
    }

    public String getProducte() {
        return producte;
    }

    public Integer getTotal() {
        return total;
    }


    /* ---- Setters ---- */
    public void setId(Long id) {
        this.id = id;
    }

    public void setProducte(String producte) {
        this.producte = producte;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
