package org.client.springClient.dto;

import org.client.springClient.model.User;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenDto {

    private String tokenValue;

    private User user;

    public JwtTokenDto() {
    }

    public JwtTokenDto(String tokenValue) {
        this.tokenValue = tokenValue;
    }

    public JwtTokenDto(String tokenValue, User user) {
        this.tokenValue = tokenValue;
        this.user = user;
    }

    public String getTokenName() {
        return "Authorization";
    }

    public String getTokenValue() {
        return tokenValue;
    }

    public void setTokenValue(String tokenValue) {
        this.tokenValue = tokenValue;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
