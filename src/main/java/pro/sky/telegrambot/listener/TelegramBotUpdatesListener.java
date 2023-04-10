package pro.sky.telegrambot.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.entity.Owner;
import pro.sky.telegrambot.handler.CallBackQueryHandler;
import pro.sky.telegrambot.handler.Handler;
import pro.sky.telegrambot.handler.ImageHandler;
import pro.sky.telegrambot.handler.TextHandler;
import pro.sky.telegrambot.menu.MainMenu;
import pro.sky.telegrambot.service.OwnerService;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;


@Service
public class TelegramBotUpdatesListener implements UpdatesListener {

    private Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);

    private TelegramBot telegramBot;
    private final OwnerService ownerService;

    public TelegramBotUpdatesListener(TelegramBot telegramBot, OwnerService ownerService) {
        this.telegramBot = telegramBot;
        this.ownerService = ownerService;
    }

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
                try {
                    callBackHandler.handle(update);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                return;
            }

            if (update.message().photo() != null) {
                Handler imageHandler = new ImageHandler(telegramBot, ownerService);
                try {
                    imageHandler.handle(update);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            if (update.message().text() != null) {
                Handler textHandler = new TextHandler(telegramBot, ownerService);
                try {
                    textHandler.handle(update);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
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

    /**
     * Ежедневно телеграм-бот проверяет дату последнего отчета усыновителей и отправляет им уведомление,
     * если они не присылают отчет более двух дней. Также бот информирует о просрочке волонтера
     */
    @Scheduled(cron = "@daily")
    public void informOwner() {
        List<Owner> owners = ownerService.findAllOwners();

        Long VOLUNTEER_CHAT_ID = 5102380657L;

        for (Owner owner: owners) {
            if(owner.getDateOfLastReport().plusDays(2).isBefore(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES))){
                sendMessage(owner.getChatId(), "Дорогой усыновитель, мы заметили, что вы заполняете отчет не так подробно, " +
                                "как необходимо. Пожалуйста, подойди ответственнее к этому занятию.  " +
                                "В противном случае волонтеры приюта будут обязаны самолично " +
                                "проверять условия  содержания собаки");

                sendMessage(VOLUNTEER_CHAT_ID, "Усыновитель id: " + owner.getChatId() +
                        " не присылает отчеты в течение двух дней, пожалуйста свяжитесь с ним");
            }
        }
    }

    private void sendMessage(Long chatId, String message) {
        SendMessage sendMessage = new SendMessage(chatId, message);
        telegramBot.execute(sendMessage);
    }

}
