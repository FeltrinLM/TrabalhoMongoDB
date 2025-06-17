package DAO.Embedded;

import com.mongodb.client.*;
import Database.MongoConnection;
import org.bson.Document;
import static com.mongodb.client.model.Aggregates.*;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Projections.*;

import java.util.Arrays;

public class ConsultaEmbeddedDAO {

    public void consulta1_PecasComQuantidadeMenorQue30() {
        MongoDatabase db = MongoConnection.getDatabase();
        MongoCollection<Document> colecao = db.getCollection("colecoes_embedded");

        AggregateIterable<Document> resultado = colecao.aggregate(Arrays.asList(
                unwind("$estampas"),
                unwind("$estampas.pecas"),
                match(lt("estampas.pecas.quantidade", 30)),
                project(fields(
                        excludeId(),
                        computed("colecao", "$nome"),
                        computed("estampa", "$estampas.nome"),
                        computed("peca", "$estampas.pecas.tipo"),
                        computed("tamanho", "$estampas.pecas.tamanho"),
                        computed("quantidade", "$estampas.pecas.quantidade")
                ))
        ));

        for (Document doc : resultado) {
            System.out.println(doc.toJson());
        }
    }

    public void consulta2_EstampasComPecaCalça() {
        MongoDatabase db = MongoConnection.getDatabase();
        MongoCollection<Document> colecao = db.getCollection("colecoes_embedded");

        AggregateIterable<Document> resultado = colecao.aggregate(Arrays.asList(
                unwind("$estampas"),
                unwind("$estampas.pecas"),
                match(eq("estampas.pecas.tipo", "Calça")),
                project(fields(
                        excludeId(),
                        computed("colecao", "$nome"),
                        computed("estampa", "$estampas.nome"),
                        computed("tipo_peca", "$estampas.pecas.tipo")
                ))
        ));

        for (Document doc : resultado) {
            System.out.println(doc.toJson());
        }
    }
}
