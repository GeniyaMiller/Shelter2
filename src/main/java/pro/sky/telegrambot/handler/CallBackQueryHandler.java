package pro.sky.telegrambot.handler;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.request.SendPhoto;
import pro.sky.telegrambot.menu.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * "Перехватчик" updates типа CallBackQuery
 */

public class CallBackQueryHandler implements Handler {
    private final TelegramBot telegramBot;

    public CallBackQueryHandler(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

    @Override
    public void handle(Update update) {
        Long chatId = update.callbackQuery().message().chat().id();
        CallbackQuery callbackQuery = update.callbackQuery();
        String data = callbackQuery.data();

        switch (data) {
            //Вызов кнопки "Информация о приюте"
            case "ButtonMainMenu1":
                MenuShelter menuShelter = new MenuShelter(telegramBot);
                menuShelter.showMenu(telegramBot, chatId);
                break;

            //Вызов кнопки "Забрать собаку"
            case "ButtonMainMenu2":
                MenuGettingDog menuGettingDog = new MenuGettingDog(telegramBot);
                menuGettingDog.showMenu(telegramBot, chatId);
                break;

            //Вызов кнопки "Прислать отчет о питомце"
            case "ButtonMainMenu3":
                MenuSendingReport menuSendingReport = new MenuSendingReport(telegramBot);
                menuSendingReport.showMenu(telegramBot, chatId);
                break;

            //Вызов кнопки "Вызов волонтера"
            case "ButtonCallVolunteer":
                MenuVolunteer menuVolunteer = new MenuVolunteer(telegramBot);
                menuVolunteer.showMenu(telegramBot, chatId);
                break;

            // Вызов "Главного меню"
            case "ButtonMainMenu":
                MainMenu mainMenu = new MainMenu(telegramBot);
                mainMenu.showStartMenu(telegramBot, chatId);
                break;


            // Кнопки меню "Информация о приюте"
            // Вызов кнопки "Расскажи о приюте"
            case "ButtonShelter1":
                String description = "Приют Help-Pets это место содержания бездомных, потерянных, брошенных и больных животных." +
                        "Тут находятся питомцы, от которых отказались хозяева, найденные на улице раненые кошки и собаки. " +
                        "Основаные функции приюта это:" +
                        "\n -принимать животных от владельцев или найденных на улице;" +
                        "\n -создать хорошие условия для проживания;" +
                        "\n -проводить работу по поиску новых хозяев;" +
                        "\n -временно принять животных, сданных владельцами;" +
                        "\n -приютить больных или травмированных кошек и собак.";
                SendMessage sendMessageButtonShelter1 = new SendMessage(chatId, description);
                telegramBot.execute(sendMessageButtonShelter1);
                break;

            // Вызов кнопки "Расписание времени работы и адрес приюта"
            case "ButtonShelter2":
                SendMessage sendMessageButtonShelter2 = new SendMessage(chatId, ("Часы работы приюта с 9:00 до 19:00 без выходных" + "\n" +
                        "г.Москва, Зубовский бульвар д.17 с.3"));
                telegramBot.execute(sendMessageButtonShelter2);
                break;

            // Вызов кнопки "Схема проезда"
            case "ButtonShelter3":
                try {
                    // Какая-то магия с CallBackQueryHadler, чтением из папки resources, и записыванием в переменную
                    // массив байтов drivingDirection
                    byte[] drivingDirection = Files.readAllBytes(
                            Paths.get(CallBackQueryHandler.class.getResource("/drivingDirection.jpg").toURI()));
                    SendPhoto sendPhoto = new SendPhoto(chatId, drivingDirection);
                    telegramBot.execute(sendPhoto);
                } catch (IOException | URISyntaxException e) {
                    throw new RuntimeException(e);
                }
                break;

            // Вызов кнопки "Рекомендации о технике безопасности"
            case "ButtonShelter4":
                String rules = "На территории приюта для всех посетителей действуют правила и распорядок," +
                        " установленные администрацией приюта: " +
                        "\n -проведение фото и видео фиксации без предварительного письменного согласования;" +
                        "\n -кормить животных кормами и продуктами, как на территории приюта;" +
                        "\n -посещать блок карантина и изолятор;" +
                        "\n -без необходимости находиться вблизи вольеров;" +
                        "\n -давать животным самостоятельно какие-либо ветеринарные или медицинские препараты." +
                        "\n -выгуливать животных без поводка.";
                SendMessage sendMessageButtonShelter4 = new SendMessage(chatId, rules);
                telegramBot.execute(sendMessageButtonShelter4);
                break;

            // Вызов кнопки "Контактные данные для связи"
            case "ButtonShelter5":
                String contactPhone = "+79102223355, Илья. \n" +
                        "+7902447766, Мария";
                SendMessage sendMessageButtonShelter5 = new SendMessage(chatId, contactPhone);
                telegramBot.execute(sendMessageButtonShelter5);
                break;


            // Кнопки меню "Как взять собаку из приюта"
            // Вызов кнопки .......
        }
    }
}
