package pro.sky.telegrambot.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
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
    private Integer phoneNumber;

    @Column(name = "chatId", nullable = false)
    private Long chatId;

    @Column(name = "photo_report")
    private byte[] photoReport;

    @Column(name = "string_report")
    private String stringReport;

    @Column(name = "last_report")
    private LocalDateTime dateOfLastReport;


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

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public byte[] getPhotoReport() {
        return photoReport;
    }

    public void setPhotoReport(byte[] photoReport) {
        this.photoReport = photoReport;
    }

    public String getStringReport() {
        return stringReport;
    }

    public void setStringReport(String stringReport) {
        this.stringReport = stringReport;
    }

    public LocalDateTime getDateOfLastReport() {
        return dateOfLastReport;
    }

    public void setDateOfLastReport(LocalDateTime dateOfLastReport) {
        this.dateOfLastReport = dateOfLastReport;
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
