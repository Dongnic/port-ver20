package com.im.port.vo.entity;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.im.port.vo.dto.ChatRoomDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "chatroom")
public class ChatRoomEntity {
    @Id // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chatroomid")
    private Long id;

    @ColumnDefault("'기본서버'")
    private String title;
    
    // @Column(nullable = false)
    @ManyToOne(targetEntity = UserEntity.class, fetch = FetchType.EAGER, cascade = {CascadeType.REMOVE, CascadeType.MERGE, CascadeType.REFRESH})
    @JsonIgnore
    @JoinColumn(name="userid")
    private UserEntity userid;

    @CreationTimestamp
    private Timestamp regdate;

    public ChatRoomDto toDto(){
        return ChatRoomDto.builder()
                .id(id)
                .title(title)
                .userid(userid)
                .regdate(regdate)
                .build();
    }

    public ChatRoomEntity orElseThrow() {
        return null;
    }
}
