package com.makrushin.telegrambot.repository;

import com.makrushin.telegrambot.model.SaveFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaveFileRepository extends JpaRepository<SaveFile,Integer> {
}
