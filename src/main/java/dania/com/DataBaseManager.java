import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class DataBaseManager {
    private static DataBaseManager sInstance;

    private static final String DB_HOST = "ds137550.mlab.com";
    private static final int DB_PORT = 37550;
    private static final String DB_NAME = "heroku_g6snzzw3";
    private static final String DB_USER = "draggon23";
    private static final String DB_PASSWORD = "gh2534ks";

    private static final String DB_URL = "mongodb://" + DB_USER + ":" + DB_PASSWORD + "@" + DB_HOST + ":" + DB_PORT + "/" + DB_NAME;

    private MongoCollection<Document> mUsersCollection;
    private MongoCollection<Document> mToDoListCollection;

    private DataBaseManager() {
        MongoClientURI clientURI = new MongoClientURI(DB_URL);
        MongoClient client = new MongoClient(clientURI);
        MongoDatabase db = client.getDatabase(DB_NAME);
        mUsersCollection = db.getCollection("users");
        mToDoListCollection = db.getCollection("to_do_list");
    }

    public static DataBaseManager getInstance() {
        if (sInstance == null) {
            sInstance = new DataBaseManager();
        }
        return sInstance;
    }

    public MongoCollection<Document> getmUsersCollection() {
        return mUsersCollection;
    }
    public MongoCollection<Document> getmToDoListCollection() { return mToDoListCollection; }
}