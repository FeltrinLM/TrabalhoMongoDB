package DAO.Referenced;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import Database.MongoConnection;
import org.bson.Document;
import org.json.JSONArray;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class EstampaDAO {

    public void inserirEstampas() {
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("Data/estampas.json");
            if (inputStream == null) throw new RuntimeException("Arquivo estampas.json não encontrado!");

            String json = new Scanner(inputStream, StandardCharsets.UTF_8).useDelimiter("\\A").next();
            JSONArray jsonArray = new JSONArray(json);

            MongoDatabase db = MongoConnection.getDatabase();
            MongoCollection<Document> colecao = db.getCollection("estampas");

            for (int i = 0; i < jsonArray.length(); i++) {
                colecao.insertOne(Document.parse(jsonArray.getJSONObject(i).toString()));
            }

            System.out.println("Estampas inseridas com sucesso.");
        } catch (Exception e) {
            System.err.println("Erro ao inserir estampas: " + e.getMessage());
        }
    }
}
