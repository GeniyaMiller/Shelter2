package pro.sky.telegrambot.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.entity.Owner;
import pro.sky.telegrambot.handler.CallBackQueryHandler;
import pro.sky.telegrambot.handler.Handler;
import pro.sky.telegrambot.menu.MainMenu;
import pro.sky.telegrambot.service.OwnerService;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service
public class TelegramBotUpdatesListener implements UpdatesListener {

    private Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);

    @Autowired
    private TelegramBot telegramBot;
    private OwnerService ownerService;

    public TelegramBotUpdatesListener(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    private final Pattern ownerData = Pattern.compile("\\d{11} [А-я]+");

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
                // Если телеграм-бот ловит текст, то создается сущность
                // Owner, которая заносится в БД
            } else if (text != null) {
                // Проверяем на соответствие шаблону NOTIFICATION_TASK_PATTERN
                Matcher matcher = ownerData.matcher(text);
                if (matcher.find()) {
                    String result = matcher.group(0).replaceAll(" ", "");

                    String phoneNumber = result.substring(0, 10);
                    String name = result.substring(11);

                    // Создаем сущность Owner
                    Owner owner = new Owner();
                    owner.setName(name);
                    owner.setPhoneNumber(phoneNumber);
                    owner.setChatId(chatId);

                    //Добавляем сущность Owner в БД
                    ownerService.addOwner(owner);

                    telegramBot.execute(new SendMessage(chatId, "Контактные данные успешно добавлены!"));
                } else {
                    telegramBot.execute(new SendMessage(chatId, "Некорректный формат сообщения."));
                }
            }
        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

}
