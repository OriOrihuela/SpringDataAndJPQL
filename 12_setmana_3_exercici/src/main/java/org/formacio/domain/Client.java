package org.formacio.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_clients")
public class Client {

    /* ---- Properties of the class ---- */
    @Id
    @Column(name = "cli_nom")
    private String nom;

    @Column(name = "cli_email")
    private String email;


    /* ---- Getters ---- */
    public String getNom() {
        return nom;
    }

    public String getEmail() {
        return email;
    }


    /* ---- Setters ---- */
    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
