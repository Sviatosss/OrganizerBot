import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import static com.mongodb.client.model.Filters.eq;

/**
 * Created by sviatosss on 12.12.2018.
 */
public class UsersManager {
    private static UsersManager sInstance;

    public static UsersManager getInstance() {
        if (sInstance == null) {
            sInstance = new UsersManager();
        }
        return sInstance;
    }

    private MongoCollection<Document> mUsersCollection = DataBaseManager.getInstance().getmUsersCollection();

    public void issetUserByChat(Chat chat) {
        Document query = new Document("id", chat.getId().toString());
        Document user = mUsersCollection.find(query).first();


        if (user == null) {
            Document newUser = new Document("id", chat.getId().toString())
                    .append("firstName", chat.getFirstName())
                    .append("lastName", chat.getLastName())
                    .append("username", chat.getUserName())
                    .append("language", "ua")
                    .append("add_status", null);
            mUsersCollection.insertOne(newUser);
        }
    }
    public void setAddStatus(Chat chat, String newStatus){
        mUsersCollection.updateOne(eq("id", chat.getId().toString()), new Document("$set", new Document("add_status", newStatus)));
    }
    public String getAddStatus(Chat chat){
        Document query = new Document("id", chat.getId().toString());
        Document user = mUsersCollection.find(query).first();
        return user.getString("add_status");
    }
    public String getLanguage(Update update){
        Document query = new Document();
        if (update.hasMessage()){
            query = new Document("id", update.getMessage().getChat().getId().toString());
        }else {
            query = new Document("id", update.getCallbackQuery().getMessage().getChat().getId().toString());
        }
        Document user = mUsersCollection.find(query).first();
        return user.getString("language");
    }
    public void setLanguage(Chat chat, String newLanguage){
        mUsersCollection.updateOne(eq("id", chat.getId().toString()), new Document("$set", new Document("language", newLanguage)));

    }
    public boolean issetAddStatus(Chat chat){
        Document query = new Document("id", chat.getId().toString());
        Document user = mUsersCollection.find(query).first();
        String status = user.getString("add_status");
        if (status != null){
            return true;
        }else return false;
    }
}