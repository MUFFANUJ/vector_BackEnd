package com.AnujSingh.vector.repository;

import com.AnujSingh.vector.model.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Chat, Long> {
}
