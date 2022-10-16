package com.im.port.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.im.port.vo.entity.ChatRoomEntity;

public interface ChatRoomRepository extends JpaRepository<ChatRoomEntity, Long> {
    
    public Optional<ChatRoomEntity> findById(Long id);
    public List<ChatRoomEntity> findByUserid(Long id);

    @Query(value = " SELECT "
                 + "   a.chatroomid as chatroomid "
                 + " , a.title as title "
                 + " , a.userid as userid "
                 + " , a.discribe as discribe "
                 + " , a.regdate as regdate "
                 + " FROM chatroom a "
                 + " LEFT JOIN chatuser b "
                 + " ON a.chatroomid = b.chatroomid "
                 + " WHERE 1=1 "
                 + " AND b.userid = :userid ", nativeQuery = true)
    public List<ChatRoomEntity> selectSQLByUserid(@Param(value = "userid") Long id);

}
