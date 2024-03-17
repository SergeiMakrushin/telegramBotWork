package com.makrushin.telegrambot.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class NotificationTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private Long userId;
    private String name;
    private String message;
    private LocalDateTime timeMessage;

    public NotificationTask(Long id, Long userId, String name, String message, LocalDateTime timeMessage) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.message = message;
        this.timeMessage = timeMessage;
    }

    public NotificationTask() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTimeMessage() {
        return timeMessage;
    }

    public void setTimeMessage(LocalDateTime timeMessage) {
        this.timeMessage = timeMessage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NotificationTask that = (NotificationTask) o;
        return Objects.equals(id, that.id) && Objects.equals(userId, that.userId) && Objects.equals(name, that.name) && Objects.equals(message, that.message) && Objects.equals(timeMessage, that.timeMessage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, name, message, timeMessage);
    }

    @Override
    public String toString() {
        return "NotificationTask{" +
                "id=" + id +
                ", userId=" + userId +
                ", name='" + name + '\'' +
                ", message='" + message + '\'' +
                ", timeMessage=" + timeMessage +
                '}';
    }
}

