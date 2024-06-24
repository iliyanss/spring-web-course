package com.bonappetit.util;


import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class LoggedUser {

    private Long id;
    private String username;

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public LoggedUser setId(Long id) {
        this.id = id;
        return this;
    }

    public LoggedUser setUsername(String username) {
        this.username = username;
        return this;
    }

    public boolean isLogged() {
        return this.username != null && this.id != null;
    }
}