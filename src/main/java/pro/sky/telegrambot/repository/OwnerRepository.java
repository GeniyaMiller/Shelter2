package pro.sky.telegrambot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.sky.telegrambot.entity.Owner;

import java.util.List;
@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {
    Owner getOwnerByChatId(Long chatID);
    List<Owner> findAll();
}
