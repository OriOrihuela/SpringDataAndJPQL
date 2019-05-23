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
    public List<Persona> habitantsMunicipi(@Param("municipi") String municipi) {
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
        return 0;
    }

    /**
     * Retorna el nombre d'habitants del municipi que son menors d'edat
     */
    public int nombreHabitantsMenorsEdat(String municipi) {
        return 0;
    }

    /**
     * Retorna la llista de persones que no tenen informat de quin municipi son
     */
    public List<Persona> llistaPersonesSenseMunicipi() {
        return null;
    }

    /**
     * Retorna la llista de noms de persones d'una illa ordenats per l'edat
     * siii, ja ho se ..., no hem vist com ordenar, pero emprau order by i la vostra intuicio ;-)
     */
    public List<String> llistaNomsPersonesOrdenatPerEdat(String illa) {
        return null;
    }

}
