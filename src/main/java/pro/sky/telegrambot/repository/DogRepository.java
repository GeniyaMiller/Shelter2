package pro.sky.telegrambot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.telegrambot.entity.Dog;

public interface DogRepository extends JpaRepository<Dog, Long> {
}
