package com.zel.neoanalyzer;
import com.zel.neoanalyzer.controller.PersonagemController;
import com.zel.neoanalyzer.dao.DAOFactory;
import com.zel.neoanalyzer.dao.PersonagemDAO;
import com.zel.neoanalyzer.db.Neo4jConnection;
import java.util.List;

public class NeoAnalyzer {

    public static void main(String[] args) {
        String configPath = "C:\\Users\\hazel\\Documents\\NetBeansProjects\\config.txt";
        Application app = new Application(configPath);
         
        app.run();
    }
}