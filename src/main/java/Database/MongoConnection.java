package Database;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoConnection {

    private static final String URI = "mongodb+srv://lorenzo:1234@trabalhobancodados.0nxya0x.mongodb.net/?retryWrites=true&w=majority&appName=TrabalhoBancoDados";
    private static final String DATABASE_NAME = "test";

    private static MongoClient mongoClient = null;

    // Singleton para obter conexão
    public static MongoDatabase getDatabase() {
        if (mongoClient == null) {
            mongoClient = MongoClients.create(URI);
        }
        return mongoClient.getDatabase(DATABASE_NAME);
    }

    public static void close() {
        if (mongoClient != null) {
            mongoClient.close();
        }
    }
}
