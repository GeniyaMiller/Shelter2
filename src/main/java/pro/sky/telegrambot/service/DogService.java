package pro.sky.telegrambot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.entity.Dog;
import pro.sky.telegrambot.repository.DogRepository;

import java.util.Optional;

/**
 * Методы для работы с репозиторием {@link DogRepository}
 */
@Service
public class DogService {
    private final DogRepository dogRepository;
    Logger logger = LoggerFactory.getLogger(DogService.class);

    public DogService(DogRepository dogRepository) {
        this.dogRepository = dogRepository;
    }

    /**
     * Сохраняем собаку в БД. <br>
     * Используется метод репозитория {@link DogRepository#save(Object)}
     *
     * @param dog создается объект собака
     * @return созданная собака
     */
    public Dog addDog(Dog dog){
        logger.debug("Добавляем собаку в БД.");
        return dogRepository.save(dog);
    }

    /**
     * Получаем информацию о собаке по идентификатору. <br>
     * Используется метод репозитория {@link DogRepository#findById(Object)}
     *
     * @param id идентификатор собаки
     * @return Optional
     */

    public Optional<Dog> getDog(long id){
        logger.debug("Получаем данные о собаке по id.");
        return dogRepository.findById(id);
    }

    /**
     * Изменяем данные о собаке в БД. <br>
     * Используется метод репозитория {@link DogRepository#save(Object)}
     *
     * @param dog создается объект собака
     * @return созданная собака
     */
    public Dog editDog(Dog dog){
        logger.debug("Изменяем данные собаки в БД.");
        return dogRepository.save(dog);
    }

    /**
     * Удаляет собаку из БД. <br>
     * Используется метод репозитория {@link DogRepository#save(Object)}
     *
     * @param id идентификатор собаки
     */
    public void deleteDog(long id){
        logger.debug("Удаляем собаку по id");
        dogRepository.deleteById(id);
    }
}
