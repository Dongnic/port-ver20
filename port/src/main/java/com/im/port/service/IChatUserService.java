package com.im.port.service;

import com.im.port.vo.dto.ChatUserDto;

public interface IChatUserService {
    Long postChatUser(ChatUserDto chatUserDto) throws Exception; 
}
