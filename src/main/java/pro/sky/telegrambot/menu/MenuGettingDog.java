package pro.sky.telegrambot.menu;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;

/**
 * Класс "меню "забрать собаку""
 */
public class MenuGettingDog {
    private final TelegramBot telegramBot;

    public MenuGettingDog(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

    // Метод показать меню
    public void showMenu(TelegramBot telegramBot, Long chatId) {
        String text = "Здесь мы поможем подготовиться ко встрече с новым членом семьи. Дадим информацию по бюррократическим и бытовыми вопросам.";

        // Создание кнопок
        InlineKeyboardButton button1 = new InlineKeyboardButton("Правила знакомства с собакой");
        button1.callbackData("ButtonGettingDog1");

        InlineKeyboardButton button2 = new InlineKeyboardButton("Список документов для <<усыновления>>");
        button2.callbackData("ButtonGettingDog2");

        InlineKeyboardButton button3 = new InlineKeyboardButton("Правила первозки животного");
        button3.callbackData("ButtonGettingDog3");

        InlineKeyboardButton button4 = new InlineKeyboardButton("Обустройство дома для собаки");
        button4.callbackData("ButtonGettingDog4");

        InlineKeyboardButton button5 = new InlineKeyboardButton("Обустройство дома для собак с ограниченными возможностями");
        button5.callbackData("ButtonGettingDog5");

        InlineKeyboardButton button6 = new InlineKeyboardButton("Советы кинолога");
        button6.callbackData("ButtonGettingDog6");

        InlineKeyboardButton button7 = new InlineKeyboardButton("Контакты кинологов");
        button7.callbackData("ButtonGettingDog7");

        InlineKeyboardButton button8 = new InlineKeyboardButton("Причины отказа в <<усыновлении>>");
        button8.callbackData("ButtonGet8tingDog8");

        InlineKeyboardButton button9 = new InlineKeyboardButton("Позвать волонтера");
        button9.callbackData("ButtonCallVolunteer");

        InlineKeyboardButton button10 = new InlineKeyboardButton("Вернуться на главную");
        button10.callbackData("ButtonMainMenu");

        // Установка кнопок после текстового сообщения.
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.addRow(button1, button2);
        inlineKeyboardMarkup.addRow(button3, button4);
        inlineKeyboardMarkup.addRow(button5, button6);
        inlineKeyboardMarkup.addRow(button7, button8);
        inlineKeyboardMarkup.addRow(button9, button10);

        // Формирование "ответного сообщения" для пользователя, который перешел по кнопке "Как взять собаку из приюта"
        SendMessage sendMessage = new SendMessage(chatId, text).replyMarkup(inlineKeyboardMarkup);
        telegramBot.execute(sendMessage);
    }

}
