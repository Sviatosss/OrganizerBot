import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Created by sviatosss on 12.12.2018.
 */
public class ToDoObject {
    String someId;
    public String someTime;
    public String someTask;

    public ToDoObject(String someTime, String someTask, String someId) {
        this.someTime = someTime;
        this.someTask = someTask;
        this.someId = someId;
    }
    public void sendToDoObject(Update update, ToDoObject toDoObject){
        Sending sending = new Sending();
        sending.sendMsg(update, "\uD83D\uDDD3 " + toDoObject.someTime + " - " + toDoObject.someTask);
    }
}