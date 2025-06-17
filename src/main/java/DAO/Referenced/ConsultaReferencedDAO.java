package DAO.Referenced;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import Database.MongoConnection;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.Arrays;

import static com.mongodb.client.model.Aggregates.*;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Projections.*;

public class ConsultaReferencedDAO {

    public void consulta1_EstampasComPecasAbaixoDe30() {
        System.out.println("\n🔎 Consulta 1: Listar estampas e suas peças com quantidade < 30\n");

        MongoDatabase db = MongoConnection.getDatabase();
        MongoCollection<Document> rel = db.getCollection("estampa_peca");

        var resultado = rel.aggregate(Arrays.asList(
                lookup("estampas", "id_estampa", "_id", "estampa_info"),
                unwind("$estampa_info"),
                lookup("pecas", "id_peca", "_id", "peca_info"),
                unwind("$peca_info"),
                match(lt("peca_info.quantidade", 30)),
                project(fields(
                        excludeId(),
                        computed("estampa", "$estampa_info.nome"),
                        computed("peca", "$peca_info.tipo"),
                        computed("tamanho", "$peca_info.tamanho"),
                        computed("quantidade", "$peca_info.quantidade")
                ))
        ));

        for (Document doc : resultado) {
            System.out.println(doc.toJson());
        }
    }

    public void consulta2_EstampasPorColecao() {
        System.out.println("\n🔎 Consulta 2: Listar nome das coleções e das estampas relacionadas\n");

        MongoDatabase db = MongoConnection.getDatabase();
        MongoCollection<Document> estampas = db.getCollection("estampas");

        var resultado = estampas.aggregate(Arrays.asList(
                lookup("colecoes", "id_colecao", "_id", "colecao_info"),
                unwind("$colecao_info"),
                project(fields(
                        excludeId(),
                        computed("colecao", "$colecao_info.nome"),
                        computed("estampa", "$nome")
                ))
        ));

        for (Document doc : resultado) {
            System.out.println(doc.toJson());
        }
    }
}
