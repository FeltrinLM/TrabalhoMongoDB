package DAO.Referenced;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import Database.MongoConnection;
import org.bson.Document;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import org.bson.json.JsonParseException;
import org.bson.json.JsonReader;

import org.bson.json.JsonObject;

import org.json.JSONArray;

public class ColecaoDAO {

    public void inserirColecoes() {
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("Data/colecoes.json");
            if (inputStream == null) throw new RuntimeException("Arquivo colecoes.json não encontrado!");

            String json = new Scanner(inputStream, StandardCharsets.UTF_8).useDelimiter("\\A").next();
            JSONArray jsonArray = new JSONArray(json);

            MongoDatabase db = MongoConnection.getDatabase();
            MongoCollection<Document> colecao = db.getCollection("colecoes");

            for (int i = 0; i < jsonArray.length(); i++) {
                colecao.insertOne(Document.parse(jsonArray.getJSONObject(i).toString()));
            }

            System.out.println("✅ Coleções inseridas com sucesso.");
        } catch (Exception e) {
            System.err.println("❌ Erro ao inserir coleções: " + e.getMessage());
        }
    }
}
