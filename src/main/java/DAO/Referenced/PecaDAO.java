package DAO.Referenced;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import Database.MongoConnection;
import org.bson.Document;
import org.json.JSONArray;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class PecaDAO {

    public void inserirPecas() {
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("Data/pecas.json");
            if (inputStream == null) throw new RuntimeException("Arquivo pecas.json não encontrado!");

            String json = new Scanner(inputStream, StandardCharsets.UTF_8).useDelimiter("\\A").next();
            JSONArray jsonArray = new JSONArray(json);

            MongoDatabase db = MongoConnection.getDatabase();
            MongoCollection<Document> colecao = db.getCollection("pecas");

            for (int i = 0; i < jsonArray.length(); i++) {
                colecao.insertOne(Document.parse(jsonArray.getJSONObject(i).toString()));
            }

            System.out.println("Peças inseridas com sucesso.");
        } catch (Exception e) {
            System.err.println("Erro ao inserir peças: " + e.getMessage());
        }
    }
}
