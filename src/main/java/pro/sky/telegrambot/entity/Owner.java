package pro.sky.telegrambot.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "owner")
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "phoneNumber", nullable = false)
    private String phoneNumber;

    @Column(name = "chatId", nullable = false)
    private Long chatId;

//    @Column(name = "Status")
//    private OwnerStatus ownerStatus;

    public Owner() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

//    public OwnerStatus getOwnerStatus() {
//        return ownerStatus;
//    }

//    public void setOwnerStatus(OwnerStatus ownerStatus) {
//        this.ownerStatus = ownerStatus;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Owner owner = (Owner) o;
        return Objects.equals(id, owner.id) && Objects.equals(name, owner.name) && Objects.equals(phoneNumber, owner.phoneNumber) && Objects.equals(chatId, owner.chatId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, phoneNumber, chatId);
    }

    @Override
    public String toString() {
        return "Владелец {" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", chatId=" + chatId +
                '}';
    }
}
