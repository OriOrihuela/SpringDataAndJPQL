package org.formacio.repositori;

import java.util.List;


import org.formacio.domain.Persona;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Repository
public class InformesCens {

    /* ---- Properties of the class ---- */
    @PersistenceContext
    private EntityManager entityManager = null;


    /* ---- Getters ---- */
    public EntityManager getEntityManager() {
        return entityManager;
    }

    /**
     * Retorna la llista de persones que viuen al municipi indicat
     */
    public List<Persona> habitantsMunicipi(String municipi) {
        TypedQuery<Persona> query = getEntityManager().createQuery("select persona from Persona persona where " +
                "persona.municipi.nom = :id", Persona.class);
        query.setParameter("id", municipi);
        List<Persona> personaList = query.getResultList();
        return personaList;
    }

    /**
     * Retorna el nombre d'habitants de la illa
     */
    public int nombreHabitantsIlla(String illa) {
        TypedQuery<Persona> query = getEntityManager().createQuery("select persona from Persona persona " +
                "where persona.municipi.illa.nom = :illa", Persona.class);
        query.setParameter("illa", illa);
        List<Persona> personaList = query.getResultList();
        int nombreHabitants = personaList.size();
        return nombreHabitants;
    }

    /**
     * Retorna el nombre d'habitants del municipi que son menors d'edat
     */
    public int nombreHabitantsMenorsEdat(String municipi) {
        TypedQuery<Persona> query = getEntityManager().createQuery("select persona from Persona persona " +
                "where persona.edat < :edat and persona.municipi.nom = :municipi", Persona.class);
        query.setParameter("edat", 18);
        query.setParameter("municipi", municipi);
        List<Persona> personaList = query.getResultList();
        int nombreHabitants = personaList.size();
        return nombreHabitants;
    }

    /**
     * Retorna la llista de persones que no tenen informat de quin municipi son
     */
    public List<Persona> llistaPersonesSenseMunicipi() {
        TypedQuery<Persona> query = getEntityManager().createQuery("select persona from Persona persona " +
                "where persona.municipi is null", Persona.class);
        List<Persona> personaList = query.getResultList();
        return personaList;
    }

    /**
     * Retorna la llista de noms de persones d'una illa ordenats per l'edat
     * siii, ja ho se ..., no hem vist com ordenar, pero emprau order by i la vostra intuicio ;-)
     */
    public List<String> llistaNomsPersonesOrdenatPerEdat(String illa) {
        TypedQuery<String> query = getEntityManager().createQuery("select persona.nom from Persona persona " +
                "where persona.municipi.illa.nom = :illa order by persona.edat", String.class);
        query.setParameter("illa", illa);
        List<String> personaList = query.getResultList();
        return personaList;
    }

}
