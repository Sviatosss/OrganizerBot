import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.toIntExact;

/**
 * Created by Sviat on 26.10.2018.
 */
public class Sending extends OrganizersBot {

    public void sendThumbnail(Update update, String url, String title){
        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setCaption(title);
        sendPhoto.setPhoto(url);
        sendPhoto.setChatId(update.getMessage().getChatId().toString());
        try {
            execute(sendPhoto);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public  void sendMsg(Update update, String s){
        if (update.hasCallbackQuery()){
            editMessage(update,s);
        }else {
            SendMessage sendMessage = new SendMessage();
            sendMessage.enableMarkdown(true);
            sendMessage.setChatId(update.getMessage().getChatId().toString());
            sendMessage.setText(s);
            sendMessage.disableWebPagePreview();
            try {
                execute(sendMessage);
            }catch (TelegramApiException e){
                e.printStackTrace();
            }
        }
    }
    public void keyboards(Update update, ArrayList<String> comandList){
        SendMessage sendMessage = new SendMessage();

        sendMessage.setChatId(update.getMessage().getChatId().toString());
        sendMessage.enableMarkdown(true);

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboar = new ArrayList<>();

        int i = 0;
        KeyboardRow row = new KeyboardRow();
        for (String comand:  comandList) {
            row.add(comand);
            i++;
            if (i % 2 == 0){
                keyboar.add(row);
                row = new KeyboardRow();
            }
        }
        replyKeyboardMarkup.setKeyboard(keyboar);
        replyKeyboardMarkup.setResizeKeyboard(true);
        GetText getText = new GetText(UsersManager.getInstance().getLanguage(update));
        sendMessage.setText(getText.getStrForKeyBoard());
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        sendMessage.disableWebPagePreview();
        try {
            execute(sendMessage);
        }catch (TelegramApiException e){
            e.printStackTrace();
        }
    }
    public void sendMainMenu(Update update){
        if (update.hasCallbackQuery()){
            GetText getText = new GetText(UsersManager.getInstance().getLanguage(update));
            SendMessage message = InlineKeyboardBuilder.create(update.getCallbackQuery().getMessage().getChat().getId())
                    .setText(getText.getStrMainMenuTitle())
                    .row()
                    .button(getText.getStrWorksToday(), "menu_main_get_today")
                    .button(getText.getStrAddText(), "menu_main_add_today")
                    .endRow()
                    .row()
                    .button(getText.getStrWorksTommorow(), "menu_main_get_tomorrow")
                    .button(getText.getStrAddText(), "menu_main_add_tomorrow")
                    .endRow()
//                .row()
//                .button("Інші дні", "menu_main_other_days")
//                .endRow()
                    .row()
                    .button(getText.getStrSettingsText(), "menu_main_settings")
                    .endRow()
                    .build();
            try {
                // Send the message
                sendApiMethod(message);
                //execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }else {
            GetText getText = new GetText(UsersManager.getInstance().getLanguage(update));
            SendMessage message = InlineKeyboardBuilder.create(update.getMessage().getChat().getId())
                    .setText(getText.getStrMainMenuTitle())
                    .row()
                    .button(getText.getStrWorksToday(), "menu_main_get_today")
                    .button(getText.getStrAddText(), "menu_main_add_today")
                    .endRow()
                    .row()
                    .button(getText.getStrWorksTommorow(), "menu_main_get_tomorrow")
                    .button(getText.getStrAddText(), "menu_main_add_tomorrow")
                    .endRow()
//                .row()
//                .button("Інші дні", "menu_main_other_days")
//                .endRow()
                    .row()
                    .button(getText.getStrSettingsText(), "menu_main_settings")
                    .endRow()
                    .build();
            try {
                // Send the message
                sendApiMethod(message);
                //execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }

    }
    public void editMessage(Update update, String answer){
        EditMessageText new_message = new EditMessageText()
                .setChatId(update.getCallbackQuery().getMessage().getChatId())
                .setMessageId(toIntExact(update.getCallbackQuery().getMessage().getMessageId()))
                .setText(answer);
        try {
            execute(new_message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void sendToDoForRemoving(ToDoObject toDoObject, Update update, String title){
        if (update.hasMessage()){
            SendMessage message = InlineKeyboardBuilder.create(update.getMessage().getChat().getId())
                    .setText(title)
                    .row()
                    .button("\uD83D\uDDD3 " + toDoObject.someTime + " - " + toDoObject.someTask, "remove|*|" + toDoObject.someId)
                    .endRow()
                    .build();
            try {
                // Send the message
                sendApiMethod(message);
                //execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }else {
            SendMessage message = InlineKeyboardBuilder.create(update.getCallbackQuery().getMessage().getChat().getId())
                    .setText(title)
                    .row()
                    .button("\uD83D\uDDD3 " + toDoObject.someTime + " - " + toDoObject.someTask, "remove|*|" + toDoObject.someId)
                    .endRow()
                    .build();
            try {
                // Send the message
                sendApiMethod(message);
                //execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }
    public void sendSettings(Update update){
        GetText getText = new GetText(UsersManager.getInstance().getLanguage(update));
        SendMessage message = InlineKeyboardBuilder.create(update.getCallbackQuery().getMessage().getChat().getId())
                .setText(getText.getStrSettingsTitle())
                .row()
                .button("\uD83C\uDDFA\uD83C\uDDE6 ua", "user_language_ua")
                .button("\uD83C\uDDF7\uD83C\uDDFA ru", "user_language_ru")
                .button("\uD83C\uDDFA\uD83C\uDDF8 us", "user_language_us")
                .endRow()
                .build();
        try {
            // Send the message
            sendApiMethod(message);
            //execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
    public void sendInstraction(Update update){
        GetText getText = new GetText(UsersManager.getInstance().getLanguage(update));
        String instraction = getText.getStrInstractionText();
        sendMsg(update, instraction);
    }
}
