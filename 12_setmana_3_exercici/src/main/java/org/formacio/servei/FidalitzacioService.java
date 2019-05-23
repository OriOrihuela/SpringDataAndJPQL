package org.formacio.servei;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

/**
 * Aquesta classe NO s'ha de modificar
 */
@Service
public class FidalitzacioService {

    /* ---- Properties of the class ---- */
    private List<String> emailsPremiats = new ArrayList<>();


    /* ---- Getters ---- */
    public List<String> getEmailsPremiats() {
        return emailsPremiats;
    }


    /* ---- Behaviours ---- */
    public void notificaRegal(String email) {
        emailsPremiats.add(email);
    }
}
