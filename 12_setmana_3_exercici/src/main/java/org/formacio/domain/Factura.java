package org.formacio.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "t_factures")
public class Factura {

    /* ---- Properties of the class ---- */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fac_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "fac_client")
    private Client totalClient;

    @OneToMany
    @JoinColumn(name = "lin_factura")
    private Set<LiniaFactura> linies = new HashSet<>();


    /* ---- Getters ---- */
    public Long getId() {
        return id;
    }

    public Client getClient() {
        return totalClient;
    }

    public Set<LiniaFactura> getLinies() {
        return linies;
    }


    /* ---- Setters ---- */
    public void setId(Long id) {
        this.id = id;
    }

    public void setClient(Client client) {
        this.totalClient = client;
    }

    public void setLinies(Set<LiniaFactura> linies) {
        this.linies = linies;
    }
}
