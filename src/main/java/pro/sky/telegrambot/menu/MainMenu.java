package pro.sky.telegrambot.menu;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;

/**
 * Класс стартовое меню. Выполнено в отдельном классе, для удобства вызова при выборе кнопки "В главное меню"
 */
public class MainMenu {
    private final TelegramBot telegramBot;

    public MainMenu(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

    // Метод показать меню
    public void showStartMenu(TelegramBot telegramBot, Long chatId) {
        String text = "Привет, Вас приветствует помощник приложения Help-Pets. \n" +
                "Пожалуйста, выберите пункт из представленного меню.";

        // Создание кнопок.
        InlineKeyboardButton button1 = new InlineKeyboardButton("Узнать информацию о приюте");
        button1.callbackData("ButtonMainMenu1");
        InlineKeyboardButton button2 = new InlineKeyboardButton("Как взять собаку из приюта");
        button2.callbackData("ButtonMainMenu2");
        InlineKeyboardButton button3 = new InlineKeyboardButton("Прислать отчет о питомце");
        button3.callbackData("ButtonMainMenu3");
        InlineKeyboardButton button4 = new InlineKeyboardButton("Позвать волонтера");
        button4.callbackData("ButtonCallVolunteer");

        // Установка кнопок в 2 ряда.
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.addRow(button1, button2);
        inlineKeyboardMarkup.addRow(button3, button4);

        // Формирование "ответного сообщения" для пользователя, который открыл телеграм-бот.
        SendMessage sendMessage = new SendMessage(chatId, text).replyMarkup(inlineKeyboardMarkup);

        telegramBot.execute(sendMessage);
    }
}
