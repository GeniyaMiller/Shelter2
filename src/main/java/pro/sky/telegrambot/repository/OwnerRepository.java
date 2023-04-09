package pro.sky.telegrambot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.telegrambot.entity.Owner;

import java.util.List;

public interface OwnerRepository extends JpaRepository<Owner, Long> {
    Owner getOwnerByChatId(Long chatID);
    List<Owner> findAll();
}
