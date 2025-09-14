package com.zel.neoanalyzer.dao;

import com.zel.neoanalyzer.db.Neo4jConnection;
import com.zel.neoanalyzer.db.Neo4jConnection;
import org.neo4j.driver.Session;
import org.neo4j.driver.Result;
import org.neo4j.driver.Record;

import java.util.ArrayList;
import java.util.List;

public class PersonagemDAO {

    private Neo4jConnection neo;

    public PersonagemDAO(Neo4jConnection neo) {
        this.neo = neo;
    }

    // GET: busca todos os personagens com hobbies e pronomes
    public List<String> getAllPersonagens() {
        List<String> personagens = new ArrayList<>();
        try (Session session = neo.getSession()) {

            String query = ""
                + "MATCH (p:Personagem) "
                + "OPTIONAL MATCH (p)-[:USA_PRONOMES]->(pr:Pronome) "
                + "OPTIONAL MATCH (p)-[:TEM_HOBBY]->(h:Hobby) "
                + "RETURN p.nome AS nome, "
                + "collect(DISTINCT pr.nome) AS pronomes, "
                + "collect(DISTINCT h.nome) AS hobbies";

            Result result = session.run(query);

            while (result.hasNext()) {
                Record record = result.next();
                String nome = record.get("nome").asString();

                // converte listas de pronomes e hobbies para string
                List<Object> pronomesList = record.get("pronomes").asList();
                List<Object> hobbiesList = record.get("hobbies").asList();

                String pronomes = String.join(", ", pronomesList.stream().map(Object::toString).toList());
                String hobbies = String.join(", ", hobbiesList.stream().map(Object::toString).toList());

                personagens.add("Nome: " + nome + ", Pronomes: " + pronomes + ", Hobbies: " + hobbies);
            }
        }
        return personagens;
    }
}
