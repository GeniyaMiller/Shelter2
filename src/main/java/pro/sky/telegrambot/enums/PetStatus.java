package pro.sky.telegrambot.enums;

/**
 * Описание статусов домашних животных, находящихся в приюте.
 */

public enum PetStatus {
    FREE("Свободен"),
    BUSY("Занят");

    private final String description;

    PetStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
