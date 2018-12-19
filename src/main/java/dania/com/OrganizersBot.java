import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Sviat on 17.10.2018.
 */

public class OrganizersBot extends TelegramLongPollingBot{
    public void onUpdateReceived(Update update) {

        if (update.hasMessage()){
            UsersManager.getInstance().issetUserByChat(update.getMessage().getChat());
            GetText getText = new GetText(UsersManager.getInstance().getLanguage(update));


            Sending sending = new Sending();
            String comand = update.getMessage().getText();
            if (comand.equals("/start")){
                sending.sendMainMenu(update);
                ArrayList<String> keyList = new ArrayList<>();
                keyList.add("\uD83D\uDD1D Menu");
                keyList.add("\uD83D\uDCDD Instraction");
                sending.keyboards(update, keyList);
            }else if(comand.equals("\uD83D\uDD1D Menu")){
                sending.sendMainMenu(update);
            }else if(comand.equals("\uD83D\uDCDD Instraction")){
                sending.sendInstraction(update);
            }else if(UsersManager.getInstance().issetAddStatus(update.getMessage().getChat())){
                ToDoManager.getInstance().addNewToDoObject(update);
            }

//            ToDoObject toDoObject = new ToDoObject("2018.12.14", "new today task");
//            ToDoManager.getInstance().addNewToDoObject(toDoObject, update);
//            ToDoManager.getInstance().getAllTasks(update);
        }else if (update.hasCallbackQuery()) {
            UsersManager.getInstance().issetUserByChat(update.getCallbackQuery().getMessage().getChat());
            MyCallBackQuerty.getInstance().switchByMenus(update);
        }
    }
    public String getBotUsername() {
        return "OrganizersBot";
    }
    public String getBotToken() {
        return "764491912:AAFu2dj6kigePQgGH5TFNad9VQumLPFScdE";
    }
}
