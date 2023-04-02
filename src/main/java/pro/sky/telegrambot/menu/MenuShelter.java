package pro.sky.telegrambot.menu;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;

/**
 * Класс "меню информация о приюте"
 */
public class MenuShelter {
    private final TelegramBot telegramBot;

    public MenuShelter(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

    // Метод показать меню
    public void showMenu(TelegramBot telegramBot, Long chatId){
        String text = "Здесь Вы можете узнать всю информацию о нашем приюте.";

        // Создание кнопок
        InlineKeyboardButton button1 = new InlineKeyboardButton("Расскажи о приюте");
        button1.callbackData("ButtonShelter1");
        InlineKeyboardButton button2 = new InlineKeyboardButton("Расписание времени работы и адрес приюта");
        button2.callbackData("ButtonShelter2");
        InlineKeyboardButton button3 = new InlineKeyboardButton("Схема проезда");
        button3.callbackData("ButtonShelter3");
        InlineKeyboardButton button4 = new InlineKeyboardButton("Рекомендации о технике безопасности");
        button4.callbackData("ButtonShelter4");
        InlineKeyboardButton button5 = new InlineKeyboardButton("Контактные данные для связи");
        button5.callbackData("ButtonShelter5");
        InlineKeyboardButton button6 = new InlineKeyboardButton("Позвать волонтера");
        button6.callbackData("ButtonCallVolunteer");
        InlineKeyboardButton button7 = new InlineKeyboardButton("Вернуться на главную");
        button7.callbackData("ButtonMainMenu");

        // Установка кнопок после текстового сообщения.
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.addRow(button1, button2);
        inlineKeyboardMarkup.addRow(button3, button4);
        inlineKeyboardMarkup.addRow(button5);
        inlineKeyboardMarkup.addRow(button6, button7);

        // Формирование "ответного сообщени" для пользователя, который перешел по кнопке "Узнать информацию о приюте"
        SendMessage sendMessage = new SendMessage(chatId, text).replyMarkup(inlineKeyboardMarkup);
        telegramBot.execute(sendMessage);
    }
}
