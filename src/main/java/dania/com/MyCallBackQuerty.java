/**
 * Created by sviatosss on 14.12.2018.
 */
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Created by sviatosss on 05.12.2018.
 */
public class MyCallBackQuerty {
    private static MyCallBackQuerty sInstance;

    public static MyCallBackQuerty getInstance() {
        if (sInstance == null) {
            sInstance = new MyCallBackQuerty();
        }
        return sInstance;
    }

    Sending sending = new Sending();

    public void switchByMenus(Update update){
        String call_data = update.getCallbackQuery().getData();
        if (call_data.contains("menu_main")){
            switchForMainMenu(update);
        }else if (call_data.contains("remove|*|")){
            ToDoManager.getInstance().removeToDoObject(update);
        }else if(call_data.contains("user_language")){
            UsersManager.getInstance().setLanguage(update.getCallbackQuery().getMessage().getChat(), call_data.replace("user_language_", ""));
            sending.sendMainMenu(update);
        }
    }

    public void switchForMainMenu(Update update){
        GetText getText = new GetText(UsersManager.getInstance().getLanguage(update));
        String call_data = update.getCallbackQuery().getData();
        if (call_data.equals("menu_main_get_today")) {
            ToDoManager.getInstance().getTodayTasks(update);
        }else if (call_data.equals("menu_main_get_tomorrow")) {
            ToDoManager.getInstance().getTomorrowTasks(update);
        }else if (call_data.equals("menu_main_add_today")) {
            UsersManager.getInstance().setAddStatus(update.getCallbackQuery().getMessage().getChat(), "add_today");
            sending.sendMsg(update, getText.getStrSupportTextonToday());
        }else if (call_data.equals("menu_main_add_tomorrow")) {
            UsersManager.getInstance().setAddStatus(update.getCallbackQuery().getMessage().getChat(), "add_tomorrow");
            sending.sendMsg(update, getText.getStrSupportTextonTomorrow());
        }else if(call_data.equals("menu_main_settings")){
            sending.sendSettings(update);
        }
    }
}
