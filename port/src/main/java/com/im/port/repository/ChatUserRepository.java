package com.im.port.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.im.port.vo.entity.ChatUserEntity;

public interface ChatUserRepository extends JpaRepository<ChatUserEntity, Long> {
    
}
