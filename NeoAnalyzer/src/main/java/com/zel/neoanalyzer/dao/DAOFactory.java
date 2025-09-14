package com.zel.neoanalyzer.dao;

import com.zel.neoanalyzer.db.Neo4jConnection;

public class DAOFactory {
    private final Neo4jConnection neo;

    public DAOFactory(Neo4jConnection neo) {
        this.neo = neo;
    }

    public PersonagemDAO getPersonagemDAO() {
        return new PersonagemDAO(neo);
    }

}
