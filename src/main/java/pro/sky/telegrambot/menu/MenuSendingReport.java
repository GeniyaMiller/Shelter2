package pro.sky.telegrambot.menu;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;

/**
 * Класс "меню "отправить отчет о питомце""
 */
public class MenuSendingReport {
    private final TelegramBot telegramBot;

    public MenuSendingReport(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

    // Метод показать меню
    public void showMenu(TelegramBot telegramBot, Long chatId) {
        String text = " Здесь вы можете отправить отчет о своем питомце," +
                " пожалуйста выберите пункт из представленного меню ";
        InlineKeyboardButton firstButton = new InlineKeyboardButton("Форма ежедневного отчета");
        firstButton.callbackData("ButtonSendingReport1");
        InlineKeyboardButton secondButton = new InlineKeyboardButton("Отправить отчет");
        secondButton.callbackData("ButtonSendingReport2");
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.addRow(firstButton, secondButton);
        SendMessage sendMessage = new SendMessage(chatId, text).replyMarkup(inlineKeyboardMarkup);
        telegramBot.execute(sendMessage);
    }
}
