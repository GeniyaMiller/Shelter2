package pro.sky.telegrambot.handler;

import pro.sky.telegrambot.entity.ContactDetails;
import pro.sky.telegrambot.entity.Owner;
import pro.sky.telegrambot.enums.PetType;
import pro.sky.telegrambot.enums.ProbationaryStatus;
import pro.sky.telegrambot.menu.InlineKeyboard;
import pro.sky.telegrambot.service.ContactDetailsService;
import pro.sky.telegrambot.service.OwnerService;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * "Перехватчик" updates типа Text
 */

public class TextHandler implements Handler {
    private final TelegramBot telegramBot;
    private final OwnerService ownerService;

    private final ContactDetailsService contactDetailsService;

    private final Pattern pattern = Pattern.compile("\\d{11} [А-я]+");

    public TextHandler(TelegramBot telegramBot,
                       ContactDetailsService contactDetailsService,
                       OwnerService ownerService) {
        this.telegramBot = telegramBot;
        this.contactDetailsService = contactDetailsService;
        this.ownerService = ownerService;
    }

    @Override
    public void handle(Update update)  {
        Message message = update.message();
        Long chatId = message.chat().id();
        String text = message.text();
        String name = message.chat().firstName();
        Matcher matcher = pattern.matcher(text);
        LocalDateTime dateOfStartProbation = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
        LocalDateTime dateOfEndProbation = dateOfStartProbation.plusDays(30);
        InlineKeyboard inlineKeyboard = new InlineKeyboard(telegramBot);

        if ("/start".equals(text)) {
            inlineKeyboard.showStartMenu(chatId);
        } else if ("/saveDogOwner".equals(text)) {
            PetType petType = PetType.DOG;
            ownerService.saveNewDogOwner(chatId,
                    name,
                    petType,
                    dateOfStartProbation,
                    dateOfEndProbation,
                    dateOfStartProbation,
                    ProbationaryStatus.ACTIVE
            );
            sendMessage(chatId, "Вы успешно зарегестрировались, ваши данные" +
                    "\nваше имя: " + name + " \nтип животного: " + petType);
        } else if (matcher.find()) {
            String result = matcher.group(0);
            findMatchesAndSaveInBd(result, chatId);
        } else {
            Owner owner = ownerService.findOwnerByChatId(chatId);
            if (owner != null && text.length() > 30) {
                owner.setStringReport(text);
                owner.setDateOfLastReport(dateOfStartProbation);
                ownerService.saveOwner(owner);
                sendMessage(chatId,"Вы успешно загрузили тесктовый отчет");
                if (owner.getPhotoReport() == null) {
                    sendMessage(chatId, "Пожалуйста не забудьте предоставить фото отчет");
                }
            } else {
                sendMessage(chatId, "Отчет недостаточно подробный, пожалуйста заполните отчет подробнее");
            }
        }
    }
    //Когда паттер сработал, метод обрезает вытаскивает из стринга имя и телефон и записывает результат в БД
    private void findMatchesAndSaveInBd(String foundString, Long chatId) {
        foundString = foundString.replaceAll(" ", "");
        String phoneNumber = foundString.substring(0, 10);
        String name = foundString.substring(11);
        ContactDetails contactDetails = new ContactDetails();
        contactDetails.setChatId(chatId);
        contactDetails.setPhoneNumber(phoneNumber);
        contactDetails.setName(name);
        contactDetailsService.save(contactDetails);
    }

    private void sendMessage(Long chatId, String message) {
        SendMessage sendMessage = new SendMessage(chatId, message);
        telegramBot.execute(sendMessage);
    }

}
