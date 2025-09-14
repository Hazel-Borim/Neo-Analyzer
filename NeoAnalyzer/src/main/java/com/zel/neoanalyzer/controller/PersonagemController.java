package com.zel.neoanalyzer.controller;

import com.zel.neoanalyzer.dao.DAOFactory;
import com.zel.neoanalyzer.dao.PersonagemDAO;
import java.util.List;

public class PersonagemController {
     private final DAOFactory factory;

    public PersonagemController(DAOFactory factory) {
        this.factory = factory;
    }

    public List<String> listarPersonagens() {
        return factory.getPersonagemDAO().getAllPersonagens();
    }
    
}
