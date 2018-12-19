import org.telegram.telegrambots.ApiContextInitializer;
        import org.telegram.telegrambots.meta.TelegramBotsApi;
        import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 * Created by Sviat on 17.10.2018.
 */
public class Main{
    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new OrganizersBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
