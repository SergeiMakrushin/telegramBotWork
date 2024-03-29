package com.makrushin.telegrambot.repositiry;

import com.makrushin.telegrambot.model.NotificationTask;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface NotificationTaskRepository extends JpaRepository<NotificationTask,Long> {


    List<NotificationTask> findNotificationTaskByTimeMessage(LocalDateTime timeMessage);
}
