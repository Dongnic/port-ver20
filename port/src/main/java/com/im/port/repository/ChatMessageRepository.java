package com.im.port.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.im.port.vo.entity.ChatMessageEntity;
import com.im.port.vo.entity.ChatRoomEntity;
 
public interface ChatMessageRepository extends JpaRepository<ChatMessageEntity, Long> {
    public List<ChatMessageEntity> findByChatroomidOrderByRegdate(ChatRoomEntity chatRoomEntity); // Jpa query methods
}
