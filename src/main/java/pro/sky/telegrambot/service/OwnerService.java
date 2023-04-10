package pro.sky.telegrambot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.entity.Owner;
import pro.sky.telegrambot.repository.DogRepository;
import pro.sky.telegrambot.repository.OwnerRepository;

import java.util.List;
import java.util.Optional;

/**
 * Методы для работы с репозиторием {@link OwnerRepository}
 */
@Service
public class OwnerService {
    private final OwnerRepository ownerRepository;
    Logger logger = LoggerFactory.getLogger(OwnerRepository.class);

    public OwnerService(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    /**
     * Сохраняем владельца в БД. <br>
     * Используется метод репозитория {@link OwnerRepository#save(Object)}
     *
     * @param owner создается объект владелец
     * @return созданный владелец
     */
    public Owner addOwner(Owner owner){
        logger.debug("Добавляем владельца в БД.");
        return ownerRepository.save(owner);
    }

    /**
     * Получаем информацию о владельце по идентификатору. <br>
     * Используется метод репозитория {@link OwnerRepository#findById(Object)}
     *
     * @param id идентификатор владельца
     * @return Optional
     */

    public Optional<Owner> getOwner(long id){
        logger.debug("Получаем данные о владельце по id.");
        return ownerRepository.findById(id);
    }

    /**
     * Изменяем данные о владельце в БД. <br>
     * Используется метод репозитория {@link OwnerRepository#save(Object)}
     *
     * @param owner создается объект владелец
     * @return созданный владец
     */
    public Owner editOwner(Owner owner){
        logger.debug("Изменяем данные владельца в БД.");
        return ownerRepository.save(owner);
    }

    /**
     * Удаляет владельца из БД. <br>
     * Используется метод репозитория {@link DogRepository#save(Object)}
     *
     * @param id идентификатор владельца
     */
    public void deleteOwner(long id){
        logger.debug("Удаляем владельца по id");
        ownerRepository.deleteById(id);
    }

    /**
     * Получаем информацию о владельце по идентификатору. <br>
     * Используется метод репозитория {@link OwnerRepository#getOwnerByChatId(Long chatID)}
     *
     * @param chatId идентификатор владельца
     * @return Owner
     */
    public Owner findOwnerByChatId(Long chatId) {
        return ownerRepository.getOwnerByChatId(chatId);
    }

    /**
     * Получаем информацию о владельцах из БД. <br>
     * Используется метод репозитория {@link OwnerRepository#findAll()}
     *
     * @return List<Owner>
     */
    public List<Owner> findAllOwners() {
        return ownerRepository.findAll();
    }
}
