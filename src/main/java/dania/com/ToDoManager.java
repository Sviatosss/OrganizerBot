import com.mongodb.BasicDBList;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.json.JSONArray;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Created by sviatosss on 12.12.2018.
 */
public class ToDoManager {
    private static ToDoManager sInstance;

    public static ToDoManager getInstance() {
        if (sInstance == null) {
            sInstance = new ToDoManager();
        }
        return sInstance;
    }

    private MongoCollection<Document> mToDoListCollection = DataBaseManager.getInstance().getmToDoListCollection();

    public void addNewToDoObject(Update update) {
        String addStatus = UsersManager.getInstance().getAddStatus(update.getMessage().getChat());
        String time = "";
        if (addStatus.equals("add_today")){
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy.MM.dd");
            LocalDate localDate = LocalDate.now();
            time = dtf.format(localDate);
        }else if(addStatus.equals("add_tomorrow")){
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy.MM.dd");
            LocalDate localDate = LocalDate.now();
            String tommorowDay = dtf.format(localDate);
            int nextDay = Integer.parseInt(tommorowDay.split("\\.")[2]);
            nextDay++;
            time = tommorowDay.split("\\.")[0] + "." + tommorowDay.split("\\.")[1] + "." + nextDay;
        }
        ToDoObject toDoObject = new ToDoObject(time, update.getMessage().getText(), update.getUpdateId().toString());
        UsersManager.getInstance().setAddStatus(update.getMessage().getChat(), null);

        Document query = new Document("id", update.getMessage().getChat().getId().toString());
        Document mainDocument = mToDoListCollection.find(query).first();
        List<Document> docs = new ArrayList<>();
        if (mainDocument != null){
            ArrayList tasks = (ArrayList) mainDocument.get( "tasks");
            ArrayList<ToDoObject> allDoObjectArrayList = getAllDoObject(tasks);
            for (ToDoObject nowToDoObject: allDoObjectArrayList){
                docs.add(new Document("time", nowToDoObject.someTime).append("task", nowToDoObject.someTask).append("some_id", nowToDoObject.someId));
            }
            mToDoListCollection.deleteOne(mainDocument);
        }
        docs.add(new Document("time", toDoObject.someTime).append("task", toDoObject.someTask).append("some_id", toDoObject.someId));

        Document document = new Document("id", update.getMessage().getChat().getId().toString()).append("tasks", docs);
        mToDoListCollection.insertOne(document);

        getTodayTasks(update);
    }

    public void removeToDoObject(Update update) {
        GetText getText = new GetText(UsersManager.getInstance().getLanguage(update));
        String some_id = update.getCallbackQuery().getData();
        some_id = some_id.replace("remove|*|", "");

        System.out.println(some_id);

        Document query = new Document("id", update.getCallbackQuery().getMessage().getChat().getId().toString());
        Document mainDocument = mToDoListCollection.find(query).first();
        if (mainDocument != null){
            ArrayList tasks = (ArrayList) mainDocument.get( "tasks");
            ArrayList<ToDoObject> allDoObjectArrayList = getAllDoObject(tasks);

            int someIndex = 0;
            List<Document> docs = new ArrayList<>();
            for (ToDoObject nowToDoObject: allDoObjectArrayList){
                if (nowToDoObject.someId.equals(some_id)){
                }else {
                    someIndex++;
                    docs.add(new Document("time", nowToDoObject.someTime).append("task", nowToDoObject.someTask).append("some_id", nowToDoObject.someId));
                }
            }
            mToDoListCollection.deleteOne(mainDocument);
            if (someIndex != 0){
                Document document = new Document("id",  update.getCallbackQuery().getMessage().getChat().getId().toString()).append("tasks", docs);
                mToDoListCollection.insertOne(document);
                Sending sending = new Sending();
                sending.sendMsg(update, getText.getStrYouHave() + docs.size());
            }else {
                Sending sending = new Sending();
                sending.sendMsg(update, getText.getStrEverysthingDone() );
            }
        }else {
            Sending sending = new Sending();
            sending.sendMsg(update, getText.getStrDontdelete());
        }
    }
    public void getAllTasks(Update update){
        GetText getText = new GetText(UsersManager.getInstance().getLanguage(update));
        Document query = new Document("id", update.getMessage().getChat().getId().toString());
        Document mainDocument = mToDoListCollection.find(query).first();

        if (mainDocument != null){
            ArrayList tasks = (ArrayList) mainDocument.get( "tasks");
            ArrayList<ToDoObject> toDoObjectArrayList = getAllDoObject(tasks);

            for (ToDoObject toDoObject: toDoObjectArrayList){
                toDoObject.sendToDoObject(update, toDoObject);
            }
        }else {
            Sending sending = new Sending();
            sending.sendMsg(update, getText.getStrYouHaventPlan());
        }

    }
    public void getTodayTasks(Update update){
        GetText getText = new GetText(UsersManager.getInstance().getLanguage(update));
        Document query = new Document();
        if (update.hasMessage()){
            query = new Document("id", update.getMessage().getChat().getId().toString());
        }else {
            query = new Document("id", update.getCallbackQuery().getMessage().getChat().getId().toString());
        }
        Document mainDocument = mToDoListCollection.find(query).first();

        if (mainDocument != null){
            ArrayList tasks = (ArrayList) mainDocument.get( "tasks");
            int i = 1;
            for( Iterator< Object > it = tasks.iterator(); it.hasNext(); ) {
                Document dbo  = (Document) it.next();
                String time = (String) dbo.get("time");
                String some_id = (String) dbo.get("some_id");

                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy.MM.dd");
                LocalDate localDate = LocalDate.now();

                if (dtf.format(localDate).equals(time)){
                    String task = (String) dbo.get("task");
                    ToDoObject currentToDoObject = new ToDoObject(time, task, some_id);
                    Sending sending = new Sending();
                    sending.sendToDoForRemoving(currentToDoObject,update,getText.getStrWorkOnToday() + i);
                    i++;
                }
            }
        }else {
            Sending sending = new Sending();
            sending.sendMsg(update, getText.getStrNoPlanToday());
        }
    }

    public void getTomorrowTasks(Update update){
        GetText getText = new GetText(UsersManager.getInstance().getLanguage(update));
        Document query = new Document("id", update.getCallbackQuery().getMessage().getChat().getId().toString());
        Document mainDocument = mToDoListCollection.find(query).first();

        if (mainDocument != null){
            ArrayList tasks = (ArrayList) mainDocument.get( "tasks");
            int i = 1;
            for( Iterator< Object > it = tasks.iterator(); it.hasNext(); ) {
                Document dbo  = (Document) it.next();
                String time = (String) dbo.get("time");
                String some_id = (String) dbo.get("some_id");

                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy.MM.dd");
                LocalDate localDate = LocalDate.now();
                String tommorowDay = dtf.format(localDate);
                int nextDay = Integer.parseInt(tommorowDay.split("\\.")[2]);
                nextDay++;
                String newDate = tommorowDay.split("\\.")[0] + "." + tommorowDay.split("\\.")[1] + "." + nextDay;
                if (newDate.equals(time)){
                    String task = (String) dbo.get("task");
                    ToDoObject currentToDoObject = new ToDoObject(time, task, some_id);
                    Sending sending = new Sending();
                    sending.sendToDoForRemoving(currentToDoObject,update,getText.getStrWorkOnTommorow() + i);
                    i++;
                }
            }
        }else {
            Sending sending = new Sending();
            sending.sendMsg(update, getText.getStrNoPlanTommorow());
        }
    }

    public ArrayList<ToDoObject> getAllDoObject(ArrayList tasks){
        ArrayList<ToDoObject> allDoObjectArrayList = new ArrayList<>();
        for( Iterator< Object > it = tasks.iterator(); it.hasNext(); ) {
            Document dbo  = (Document) it.next();
            String time = (String) dbo.get("time");
            String task = (String) dbo.get("task");
            String some_id = (String) dbo.get("some_id");
            ToDoObject currentToDoObject = new ToDoObject(time, task, some_id);
            allDoObjectArrayList.add(currentToDoObject);
        }
        return allDoObjectArrayList;
    }
}