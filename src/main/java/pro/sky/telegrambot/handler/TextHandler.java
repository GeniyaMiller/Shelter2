package pro.sky.telegrambot.handler;

import pro.sky.telegrambot.entity.Owner;
import pro.sky.telegrambot.service.OwnerService;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * "Перехватчик" updates типа Text
 */

public class TextHandler implements Handler {
    private final TelegramBot telegramBot;
    private final OwnerService ownerService;

    public TextHandler(TelegramBot telegramBot, OwnerService ownerService) {
        this.telegramBot = telegramBot;
        this.ownerService = ownerService;
    }

    @Override
    public void handle(Update update) throws IOException {
        Message message = update.message();
        Long chatId = message.chat().id();
        String text = message.text();
        Owner owner = ownerService.findOwnerByChatId(chatId);
        LocalDateTime date = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
        Long VOLUNTEER_CHAT_ID = 5102380657L;

        if (owner != null && text.length() > 100) {
            owner.setStringReport(text);
            owner.setDateOfLastReport(date);
            ownerService.addOwner(owner);
            if (owner.getPhotoReport() == null) {
                sendMessage(chatId, "Пожалуйста, добавьте фото к вашему отчету");
            }
            if (text.length() < 100) {
                sendMessage(chatId, "Пожалуйста предоставьте более подробный отчет");
            }
        }

        if(owner.getDateOfLastReport().plusDays(2).isBefore(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES))){
            telegramBot.execute(
                    new SendMessage(owner.getChatId(), "Дорогой усыновитель, мы заметили, что вы заполняете отчет не так подробно, " +
                            "как необходимо. Пожалуйста, подойди ответственнее к этому занятию.  " +
                            "В противном случае волонтеры приюта будут обязаны самолично " +
                            "проверять условия  содержания собаки"));

            sendMessage(VOLUNTEER_CHAT_ID, "Усыновитель id: " + owner.getChatId() +
                    " не присылает отчеты в течение двух дней, пожалуйста свяжитесь с ним");
        }
    }

    private void sendMessage(Long chatId, String message) {
        SendMessage sendMessage = new SendMessage(chatId, message);
        telegramBot.execute(sendMessage);
    }

}
