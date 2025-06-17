package DAO.Referenced;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import Database.MongoConnection;
import org.bson.Document;
import org.json.JSONArray;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class EstampaPecaDAO {

    public void inserirEstampaPeca() {
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("Data/estampa_peca.json");
            if (inputStream == null) throw new RuntimeException("Arquivo estampa_peca.json não encontrado!");

            String json = new Scanner(inputStream, StandardCharsets.UTF_8).useDelimiter("\\A").next();
            JSONArray jsonArray = new JSONArray(json);

            MongoDatabase db = MongoConnection.getDatabase();
            MongoCollection<Document> colecao = db.getCollection("estampa_peca");

            for (int i = 0; i < jsonArray.length(); i++) {
                colecao.insertOne(Document.parse(jsonArray.getJSONObject(i).toString()));
            }

            System.out.println("✅ Estampa_Peca inserido com sucesso.");
        } catch (Exception e) {
            System.err.println("❌ Erro ao inserir estampa_peca: " + e.getMessage());
        }
    }
}
