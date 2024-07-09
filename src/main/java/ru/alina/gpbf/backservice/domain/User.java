package ru.alina.gpbf.backservice.domain;

public final class User {
    private Long id;

    private Long telegramId;

    private String userName;

    private User() {
    }


    public User(Long id, Long telegramId, String userName) {
        this.id = id;
        this.telegramId = telegramId;
        this.userName = userName;
    }

    public Long getId() {
        return id;
    }

    public Long getTelegramId() {
        return telegramId;
    }

    public String getUserName() {
        return userName;
    }

    @Override
    public String toString() {
        return "User{" +
                "telegramId=" + telegramId +
                ", userName='" + userName + '\'' +
                '}';
    }
}

