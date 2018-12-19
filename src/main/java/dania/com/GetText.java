/**
 * Created by sviatosss on 14.12.2018.
 */
public class GetText {
    String language;
    String strForKeyBoard = "Для переходу в меню чи інструкцію використовуйте нижню панель";
    String strMainMenuTitle = "Головне меню:";
    String strWorksToday = "\uD83D\uDCC3 Справи на сьогодні";
    String strWorksTommorow = "\uD83D\uDCC3 На завтра";
    String strAddText = "➕ Додати";
    String strSettingsText = "\uD83D\uDEE0 Налаштування";
    String strSettingsTitle = "\uD83D\uDEE0 Налаштування мови:";
    String strInstractionText = " Використовуйте бота для 🕰 планування своїх днів..." +
            "Спершу \uD83D\uDCDD додайте справи на сьогодні чи завтра використовуйте кнопку \"Додати\" в меню...\n" +
            "Для перегляду своїх планів натисніть на \uD83D\uDD0D \"Справи на сьогодні\" чи  \uD83D\uDD0E \"На завтра\"...\n" +
            "Для того щоб \uD83D\uDD8D видалити уже виконані завдання нажміть на кнопу з \uD83D\uDDD3 датою і назвою відповідно до потреби...\n" +
            "Для перемикання мови використайте ⚙️ \"Налаштування\"...";
    String strSupportTextonToday = "Введіть завдання, яке хочете добавити на сьогодні";
    String strSupportTextonTomorrow = "Введіть завдання, яке хочете добавити на завтра";
    String strYouHave = "Ще залишилось - ";
    String strEverysthingDone = "Усі справи виконано !!!";
    String strDontdelete = "У вас немає що видаляти !!!";
    String strYouHaventPlan = "У вас немає планів !!!";
    String strWorkOnToday = "Справа на сьогодні - ";
    String strNoPlanToday = "У вас немає планів на сьогодні !!!";
    String strWorkOnTommorow = "Справа на завтра - ";
    String strNoPlanTommorow = "У вас немає планів на завтра !!!";


    public GetText(String language){this.language = language;}


    public String getStrForKeyBoard() {
        if (this.language.equals("ru")){
            return strForKeyBoard = "Для перехода в меню инструкцию используйте нижнюю панель";
        }else if (this.language.equals("us")){
            return strForKeyBoard = "Use the bottom panel to navigate to a menu or instruction";
        }else return strForKeyBoard;
    }

    public String getStrMainMenuTitle() {
        if (this.language.equals("ru")){
            return strMainMenuTitle = "Главное меню:";
        }else if (this.language.equals("us")){
            return strMainMenuTitle = "Main menu:";
        }else return strMainMenuTitle;
    }

    public String getStrWorksToday() {
        if (this.language.equals("ru")){
            return strWorksToday = "\uD83D\uDCC3 Дела на сегодня";
        }else if (this.language.equals("us")){
            return strWorksToday = "\uD83D\uDCC3 Tasks for today";
        }else return strWorksToday;
    }

    public String getStrAddText() {
        if (this.language.equals("ru")){
            return strAddText = "➕ Добавить";
        }else if (this.language.equals("us")){
            return strAddText = "➕ Add";
        }else return strAddText;
    }

    public String getStrSettingsText() {
        if (this.language.equals("ru")){
            return strSettingsText = "\uD83D\uDEE0 Настройка";
        }else if (this.language.equals("us")){
            return strSettingsText = "\uD83D\uDEE0 Settings";
        }else return strSettingsText;
    }

    public String getStrWorksTommorow() {
        if (this.language.equals("ru")){
            return strWorksTommorow = "\uD83D\uDCC3 На завтра ";
        }else if (this.language.equals("us")){
            return strWorksTommorow = "\uD83D\uDCC3 Tomorrow";
        }else return strWorksTommorow;
    }

    public String getStrSettingsTitle() {
        if (this.language.equals("ru")){
            return strSettingsTitle = "\uD83D\uDEE0 Выбор языка:";
        }else if (this.language.equals("us")){
            return strSettingsTitle = "\uD83D\uDEE0 Language settings:";
        }else return strSettingsTitle;
    }

    public String getStrInstractionText() {
        if (this.language.equals("ru")){
            return strInstractionText = "Используйте бота для 🕰  планирования своих дней ..." +
                    "Сначала \uD83D\uDCDD добавьте дела на сегодня или завтра используйте кнопку \"Добавить\" в меню ... \n" +
                    "Для просмотра своих планов нажмите на \uD83D\uDD0D \"Дела сегодня\" или \uD83D\uDD0E \"На завтра\" ...\n" +
                    "Для того чтобы \uD83D\uDD8D удалить уже выполненные задания нажмите на кнопку с \uD83D\uDDD3 датой и названием соответствии с потребностью ...\n" +
                    "Для переключения языка используйте ⚙️ \"Настройка\" ... ru";
        }else if (this.language.equals("us")){
            return strInstractionText = "Use the bot to 🕰  plan your days ..." +
                    "First, add things today or tomorrow using the Add button in the menu ...\n" +
                    "To view your plans, click \uD83D\uDD0D \"Today's Events\" or \uD83D\uDD0E \"For Tomorrow\" ...\n" +
                    "In order to remove the already executed tasks, press the button with the \uD83D\uDDD3 date and name as needed ...\n" +
                    "To switch the language use ⚙️ \"Settings\" ... us";
        }else return strInstractionText;
    }

    public String getStrSupportTextonToday() {
        if (this.language.equals("ru")){
            return strSupportTextonToday = "Введите задача, хотите добавить на сегодня";
        }else if (this.language.equals("us")){
            return strSupportTextonToday = "Enter the task you want to add today";
        }else return strSupportTextonToday;
    }

    public String getStrSupportTextonTomorrow() {
        if (this.language.equals("ru")){
            return strSupportTextonTomorrow = "Введите задачу кокую, хотите добавить на завтра";
        }else if (this.language.equals("us")){
            return strSupportTextonTomorrow = "Enter the task you want to add to tomorrow";
        }else return strSupportTextonTomorrow;
    }

    public String getStrYouHave() {
        if (this.language.equals("ru")){
            return strYouHave = "Еще осталось - ";
        }else if (this.language.equals("us")){
            return strYouHave = "Still left - ";
        }else return strYouHave;
    }

    public String getStrEverysthingDone() {
        if (this.language.equals("ru")){
            return strEverysthingDone = "Все дела выполнено !!!";
        }else if (this.language.equals("us")){
            return strEverysthingDone = "All work is done !!!";
        }else return strEverysthingDone;
    }

    public String getStrDontdelete() {
        if (this.language.equals("ru")){
            return strDontdelete = "У вас нечего удалять !!!";
        }else if (this.language.equals("us")){
            return strDontdelete = "You do not have to delete !!!";
        }else return strDontdelete;
    }

    public String getStrYouHaventPlan() {
        if (this.language.equals("ru")){
            return strYouHaventPlan = "У вас нет планов !!!";
        }else if (this.language.equals("us")){
            return strYouHaventPlan = "You have no plans !!!";
        }else return strYouHaventPlan;
    }


    public String getStrWorkOnToday() {
        if (this.language.equals("ru")){
            return strWorkOnToday = "Дело сегодня - ";
        }else if (this.language.equals("us")){
            return strWorkOnToday = "Task for today -";
        }else return strWorkOnToday;
    }

    public String getStrNoPlanToday() {
        if (this.language.equals("ru")){
            return strNoPlanToday = "У вас нет планов на сегодня !!!";
        }else if (this.language.equals("us")){
            return strNoPlanToday = "You do not have any plans for today !!!";
        }else return strNoPlanToday;
    }

    public String getStrWorkOnTommorow() {
        if (this.language.equals("ru")){
            return strWorkOnTommorow = "Дело на завтра - ";
        }else if (this.language.equals("us")){
            return strWorkOnTommorow = "The task for tomorrow - ";
        }else return strWorkOnTommorow;
    }

    public String getStrNoPlanTommorow() {
        if (this.language.equals("ru")){
            return strNoPlanTommorow = "У вас нет планов на завтра !!!";
        }else if (this.language.equals("us")){
            return strNoPlanTommorow = "You have no plans for tomorrow !!!";
        }else return strNoPlanTommorow;
    }
}
