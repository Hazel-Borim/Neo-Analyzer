package com.zel.neoanalyzer;

import com.zel.neoanalyzer.dao.DAOFactory;
import com.zel.neoanalyzer.db.Neo4jConnection;
import java.util.List;

public class Application {
    private final Neo4jConnection neo;
    private final DAOFactory factory;

    public Application(String configPath) {
        this.neo = new Neo4jConnection(configPath);
        this.factory = new DAOFactory(neo);
    }

    public void run() {
        List<String> personagens = factory.getPersonagemDAO().getAllPersonagens();
        personagens.forEach(System.out::println);
    }

    public void close() {
        neo.close();
    }
}
