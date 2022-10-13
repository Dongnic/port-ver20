package com.im.port.vo.dto;

import java.sql.Timestamp;

import com.im.port.vo.entity.ChatRoomEntity;
import com.im.port.vo.entity.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatRoomDto {
    private Long id;
    private String title;
    private UserEntity userid;
    private Timestamp regdate;

    public ChatRoomEntity toEntity(){
        return ChatRoomEntity.builder()
                .id(id)
                .title(title)
                .userid(userid)
                .regdate(regdate)
                .build();
    }
}
