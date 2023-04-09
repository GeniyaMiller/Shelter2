package pro.sky.telegrambot.enums;

/**
 * Описание статусов людей, которые хотят собаку.
 */

public enum OwnerStatus {
    FREE("Свободен"),
    TRIAL("На испытательном сроке"),
    HAVE_PET("Есть домашнее животное");

    private final String description;

    OwnerStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
