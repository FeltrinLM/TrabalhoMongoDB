package DAO.Embedded;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import Database.MongoConnection;
import org.bson.Document;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class DBEmbeddedDAO {

    public void inserirColecaoFromJson() {
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("data/modelo-embedded.json");
            if (inputStream == null) throw new RuntimeException("Arquivo JSON não encontrado!");

            String json = new Scanner(inputStream, StandardCharsets.UTF_8).useDelimiter("\\A").next();
            Document documento = Document.parse(json);

            MongoDatabase db = MongoConnection.getDatabase();
            MongoCollection<Document> colecao = db.getCollection("colecoes_embedded");

            colecao.insertOne(documento);
            System.out.println("Coleção inserida com sucesso (Embedded).");

        } catch (Exception e) {
            System.err.println("Erro ao inserir documento: " + e.getMessage());
        }
    }
}
