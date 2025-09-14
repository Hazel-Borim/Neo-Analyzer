package com.zel.neoanalyzer.db;
import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Session;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author hazel
 */
public class Neo4jConnection {
    private String uri;
    private String user;
    private String password;
    private Driver driver;
    
    public Neo4jConnection(String configFilePath) {
        loadConfig(configFilePath);  // lê URI, usuário e senha
        connect();                   // abre conexão
    }
    private void loadConfig(String path) {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("=", 2);
                if (parts.length == 2) {
                    switch (parts[0].trim()) {
                        case "NEO4J_URI":
                            uri = parts[1].trim();
                            break;
                        case "NEO4J_USERNAME":
                            user = parts[1].trim();
                            break;
                        case "NEO4J_PASSWORD":
                            password = parts[1].trim();
                            break;
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler arquivo de configuração: " + e.getMessage());
        }
    }
     // Conecta ao Neo4j
    private void connect() {
        driver = GraphDatabase.driver(uri, AuthTokens.basic(user, password));
        System.out.println("Conexão com Neo4j estabelecida!");
    }

    // Retorna uma sessão para executar queries
    public Session getSession() {
        return driver.session();
    }

    // Fecha a conexão
    public void close() {
        if (driver != null) {
            driver.close();
            System.out.println("Conexão com Neo4j fechada.");
        }
    }
}
