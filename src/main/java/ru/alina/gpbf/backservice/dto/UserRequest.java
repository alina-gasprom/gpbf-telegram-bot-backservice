package ru.alina.gpbf.backservice.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public class UserRequest {
    @NotNull
    private Long userTelegramId;
    @NotBlank
    private String userName;

    private UserRequest() {
    }

    @JsonCreator
    public UserRequest(Long userTelegramId, String userName) {
        this.userTelegramId = userTelegramId;
        this.userName = userName;
    }

    public Long getUserTelegramId() {
        return userTelegramId;
    }

    public String getUserName() {
        return userName;
    }

    @Override
    public String toString() {
        return "UserRequest{" +
                "userTelegramId=" + userTelegramId +
                ", userName='" + userName + '\'' +
                '}';
    }
}
