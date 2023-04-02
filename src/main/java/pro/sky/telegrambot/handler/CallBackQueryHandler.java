package pro.sky.telegrambot.handler;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.request.SendPhoto;
import pro.sky.telegrambot.enums.CallbackDataEnum;
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
            case CallbackDataEnum.buttonMainMenu1:
                MenuShelter menuShelter = new MenuShelter(telegramBot);
                menuShelter.showMenu(telegramBot, chatId);
                break;

            //Вызов кнопки "Забрать собаку"
            case CallbackDataEnum.buttonMainMenu2:
                MenuGettingDog menuGettingDog = new MenuGettingDog(telegramBot);
                menuGettingDog.showMenu(telegramBot, chatId);
                break;

            //Вызов кнопки "Прислать отчет о питомце"
            case CallbackDataEnum.buttonMainMenu3:
                MenuSendingReport menuSendingReport = new MenuSendingReport(telegramBot);
                menuSendingReport.showMenu(telegramBot, chatId);
                break;

            //Вызов кнопки "Вызов волонтера"
            case CallbackDataEnum.buttonCallVolunteer:
                MenuVolunteer menuVolunteer = new MenuVolunteer(telegramBot);
                menuVolunteer.showMenu(telegramBot, chatId);
                break;

            // Вызов "Главного меню"
            case CallbackDataEnum.buttonMainMenu:
                MainMenu mainMenu = new MainMenu(telegramBot);
                mainMenu.showStartMenu(telegramBot, chatId);
                break;


            // Кнопки меню "Информация о приюте"
            // Вызов кнопки "Расскажи о приюте"
            case CallbackDataEnum.buttonShelter1:
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
            case CallbackDataEnum.buttonShelter2:
                SendMessage sendMessageButtonShelter2 = new SendMessage(chatId, ("Часы работы приюта с 9:00 до 19:00 без выходных" + "\n" +
                        "г.Москва, Зубовский бульвар д.17 с.3"));
                telegramBot.execute(sendMessageButtonShelter2);
                break;

            // Вызов кнопки "Схема проезда"
            case CallbackDataEnum.buttonShelter3:
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
            case CallbackDataEnum.buttonShelter4:
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
            case CallbackDataEnum.buttonShelter5:
                String text = "Оставьте Ваши данные для связи в виде 89112223344 Михаил:";
                SendMessage sendMessageButtonShelter5 = new SendMessage(chatId, text);
                telegramBot.execute(sendMessageButtonShelter5);
                break;


            // Кнопки меню "Как взять собаку из приюта"
            // Вызов кнопки "Правила знакомства с собакой"
            case CallbackDataEnum.buttonGettingDog1:
                String gettingToKnow = "При первом контакте с собакой:\n" +
                        "- Подождите проявление инициативы от самой собаки.\n" +
                        "- Не подходите к собаке в лоб, она может расценить это как проявление плохих намерений.\n" +
                        "- Не смотрите собаке в глаза, ею это расценивается как угроза, вы еще не знакомы.\n" +
                        "- Позвольте себя обнюхать, собаки больше полагаются на них, чем на зрение.\n" +
                        "- Если собака захочет уйти – пусть уходит. Дайте возможность животному самому принимать решение, желает ли оно дальнейшего общения.\n" +
                        "- Если вы заметили, что собака провела с вами достаточно продолжительное время и готова продолжить общение, то можете предложить ей поглаживание.";
                SendMessage sendMessageButtonGettingDog1 = new SendMessage(chatId, gettingToKnow);
                telegramBot.execute(sendMessageButtonGettingDog1);
                break;

            // Вызов кнопки "Список документов для <<усыновления>>"
            case CallbackDataEnum.buttonGettingDog2:
                String docs = "Для усыновления вам понадобится:\n" +
                        "- Паспорт, или другой документ удостоверяющий личность.\n" +
                        "- Также необходимо внести пожертвование (на содержание остальных животных центра. Сумма пожертвования зависит от разных факторов (времени пребывания собаки в центре, затрат на ее лечение и т.п.).";
                SendMessage sendMessageDocs = new SendMessage(chatId, docs);
                telegramBot.execute(sendMessageDocs);
                break;

            // Вызов кнопки "Правила первозки животного"
            case CallbackDataEnum.buttonGettingDog3:
                String transportation = "1. Согласно пункту 23.3 ПДД РФ, собака или другой крупный питомец приравнивается к грузу. " +
                        "Перед началом поездки водителю необходимо разместить и закрепить животное. " +
                        "Питомец не должен загораживать обзор водителю и препятствовать управлению транспортным средством.\n"+
                        "2. Советы по перевозке .\n" +
                        "Первые поездки совершайте на короткие расстояния." +
                        "Дайте собаке привыкнуть к машине. Обязательно приучите животное забираться и вылезать только по команде не перемещаться по автомобилю." +
                        "Перед поездками не кормите животное, неподготовленного питомца может укачать. " +
                        "Если вы планируете перевозить четвероногого друга в переноске (мелкие и средние породы), дайте ему привыкнуть к ней. " +
                        "Поставьте сумку дома, можно постелить внутрь любимый плед или положить несколько игрушек, чтобы питомец  не воспринимал переноску как что-то опасное.\n" +
                        "3. С правилами перевозки железнодорожным и воздушным транспором вы можете ознакомиться на сайтах соответствующих компаний, услугами которых вы планируете воспользоваться.";
                SendMessage sendMessageTransportation = new SendMessage(chatId, transportation);
                telegramBot.execute(sendMessageTransportation);
                break;

            // Вызов кнопки "Обустройство дома для собаки"
            case CallbackDataEnum.buttonGettingDog4:
                String houseDog = "Спальное место.\n"+
                        "У каждой собаки в доме должен быть свой уголок. " +
                        "Для этого необходимо установить в выбранном месте квартиры лежанку, постелить подстилку или матрас." +
                        "Личную зону питомца лучше расположить подальше от радиаторов, бытовой техники, окон, входных дверей и выхода на балкон." +
                        "Спальное место должно соответствовать размерам собаки." +
                        "Длина спального места должна примерно на 15 сантиметров превышать длину " +
                        "питомца, а ширина — быть в два раза больше его высоты. " +
                        "Выбирая форму спального места, следует учитывать особенности характера собаки и ее любимую позу для сна." +
                        "Если у вас щенок, то на первое время можно купить или обустроить спальное место меньших размеров, чтобы потом сменить его на «взрослую» кровать.\n" +
                        "Посуда.\n" +
                        "У питомца обязательно должна быть своя посуда для еды и воды. Обеденную зону лучше организовать подальше от спального места, обычно для этого выделяют уголок на кухне." +
                        "Размеры миски должны соответствовать аппетиту вашего любимца. Лучше выбрать посуду на штативе, что позволит регулировать высоту.\n" +
                        "Аксессуары.\n" +
                        "Для прогулок вам необходимо преобрести поводки или ошейники,щенкам и маленьким собачкам подойдут шлейки, одевающиеся на туловище животного. Собакам бойцовских пород можно приобрести строгие ошейники" +
                        "Для путешествий, пригодится переноска. Щенкам или маленьким собачкам будет комфортно в специальной сумке или рюкзаке. Для большого животного понадобится специальный пластиковый разборный контейнер.\n" +
                        "Игушки. Собакам очень важно что-то грызть — так они массируют десна и очищают зубы от налета, делая их крепче и здоровее." +
                        "Также позаботьтесь об одежде для своих питомцев.";

                SendMessage sendMessageHouseDog = new SendMessage(chatId, houseDog);
                telegramBot.execute(sendMessageHouseDog);
                break;

            // Вызов кнопки "Обустройство дома для собаки-инвалида"
            case CallbackDataEnum.buttonGettingDog5:
                String houseSpecificDog = "Общие рекомендации по обустройству жилья описаны в пункте <<Обустройство дома для собак>>." +
                        "Более конкретные рекомендации связанные с спецификой заболевания животного вам будут даны от кинолога и ветеринара в момент процедуры <<усыновления>> питомца.";

                SendMessage sendMessageHouseSpecificDog = new SendMessage(chatId, houseSpecificDog);
                telegramBot.execute(sendMessageHouseSpecificDog);
                break;

            // Вызов кнопки "Советы кинолога"
            case CallbackDataEnum.buttonGettingDog6:
                String soviet = "Кинологи советуют с первых дней знакомства питомца с его новым домом правильно выстраить границы между вами.\n" +
                        "Основные правила общения:\n" +
                        "Необходимо дать питомцу время освоиться — питомец должен обойти дом, обнюхать новое место." +
                        "Семья должна давать собаке безопасность, любовь и позитив." +
                        "Язык собаки — это телодвижение, мимика, голос." +
                        "Собаки очень хорошо обучаются, глядя на нашу реакцию. Счастье одобряет и закрепляет поведение собаки, а равнодушие на какое-либо действие сигнализирует о том, что так делать не стоит.\n" +
                        "Встречи и прощание:\n" +
                        "Встречи — один из самых радостных и приятных моментов для собаки. как она будет проходить решаете только вы." +
                        "Расставание - питомец должен понимать, что хозяин уходит на небольшой промежуток времени, возвращается домой — и всё хорошо. Научилитесь правильно расставаться с животным.\n" +
                        "Наказание и примирение: \n" +
                        "Для экологичных отношений необязательно кричать или приказывать собаке. Важно гуманным способом запрещать питомцу те или иные действия — только в этом случае поведение закрепится.";

                SendMessage sendMessageSoviet = new SendMessage(chatId, soviet);
                telegramBot.execute(sendMessageSoviet);
                break;

                // Вызов кнопки "Контакты кинологов"
            case CallbackDataEnum.buttonGettingDog7:
                String contacts = "За помощью в постороении связи с вашим новоиспеченным питомцем вы можете обратиться к нашим кинологам:\n" +
                        "+79326762123 Весенников Алексей\n" +
                        "+79358945472 Старкина Алена";

                SendMessage sendMessageContacts = new SendMessage(chatId, contacts);
                telegramBot.execute(sendMessageContacts);
                break;

                //Вызов кнопки "Причина отказа в <<усыновлении>>"
            case CallbackDataEnum.buttonGettingDog8:
                String rejection = "Взять питомца из приюта не так уж легко. " +
                        "Работники и волонтеры стараются сделать все, чтобы собаки не оказались на улице повторно," +
                        " поэтому отдают животных только в надежные руки. Существует пять причин, по которым чаще всего отказывают желающим «усыновить» домашнего любимца.\n" +
                        "1 Большое количество животных дома\n" +
                        "2 Нестабильные отношения в семье\n" +
                        "3 Наличие маленьких детей\n" +
                        "4 Съемное жилье\n" +
                        "5 Животное в подарок или для работы";

                SendMessage sendMessageRejection = new SendMessage(chatId, rejection);
                telegramBot.execute(sendMessageRejection);
                break;
        }
    }
}
