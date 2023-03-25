package pro.sky.telegrambot.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.handler.CallBackQueryHandler;
import pro.sky.telegrambot.handler.Handler;
import pro.sky.telegrambot.menu.MainMenu;

import javax.annotation.PostConstruct;
import java.util.List;


@Service
public class TelegramBotUpdatesListener implements UpdatesListener {

    private Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);

    @Autowired
    private TelegramBot telegramBot;

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {
            logger.info("Processing update: {}", update);

            if (update.callbackQuery() != null) {
                Handler callBackHandler = new CallBackQueryHandler(telegramBot);
                callBackHandler.handle(update);
                return;
            }

            /**
             * Если телеграм-бот ловит текст "/start", то вызывается Главное меню
             */
            Message message = update.message();
            Long chatId = message.chat().id();
            String text = message.text();

            if ("/start".equals(text)) {
                MainMenu mainMenu = new MainMenu(telegramBot);
                // Вызывается стартовое меню
                mainMenu.showStartMenu(telegramBot, chatId);
                }
        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

}
