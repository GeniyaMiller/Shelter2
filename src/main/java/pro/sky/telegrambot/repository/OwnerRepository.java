package pro.sky.telegrambot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.telegrambot.entity.Owner;

public interface OwnerRepository extends JpaRepository<Owner, Long> {
}
