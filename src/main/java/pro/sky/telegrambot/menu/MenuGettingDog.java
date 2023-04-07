package pro.sky.telegrambot.menu;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import pro.sky.telegrambot.enums.CallbackDataEnum;

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
        button1.callbackData(CallbackDataEnum.buttonGettingDog1);

        InlineKeyboardButton button2 = new InlineKeyboardButton("Список документов для <<усыновления>>");
        button2.callbackData(CallbackDataEnum.buttonGettingDog2);

        InlineKeyboardButton button3 = new InlineKeyboardButton("Правила первозки животного");
        button3.callbackData(CallbackDataEnum.buttonGettingDog3);

        InlineKeyboardButton button4 = new InlineKeyboardButton("Обустройство дома для собаки");
        button4.callbackData(CallbackDataEnum.buttonGettingDog4);

        InlineKeyboardButton button5 = new InlineKeyboardButton("Обустройство дома для собак с ограниченными возможностями");
        button5.callbackData(CallbackDataEnum.buttonGettingDog5);

        InlineKeyboardButton button6 = new InlineKeyboardButton("Советы кинолога");
        button6.callbackData(CallbackDataEnum.buttonGettingDog6);

        InlineKeyboardButton button7 = new InlineKeyboardButton("Контакты кинологов");
        button7.callbackData(CallbackDataEnum.buttonGettingDog7);

        InlineKeyboardButton button8 = new InlineKeyboardButton("Причины отказа в <<усыновлении>>");
        button8.callbackData(CallbackDataEnum.buttonGettingDog8);

        InlineKeyboardButton button9 = new InlineKeyboardButton("Позвать волонтера");
        button9.callbackData(CallbackDataEnum.buttonCallVolunteer);

        InlineKeyboardButton button10 = new InlineKeyboardButton("Вернуться на главную");
        button10.callbackData(CallbackDataEnum.buttonMainMenu);

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
