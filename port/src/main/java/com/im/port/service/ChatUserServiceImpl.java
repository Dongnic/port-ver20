package com.im.port.service;

import org.springframework.stereotype.Service;

import com.im.port.repository.ChatUserRepository;
import com.im.port.vo.dto.ChatUserDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class ChatUserServiceImpl implements IChatUserService{

    private final ChatUserRepository repository;

    @Override
    public Long postChatUser(ChatUserDto chatUserDto) throws Exception {
        log.info(" ##### ChatUserServiceImpl postChatUser");
        return repository.save(chatUserDto.toEntity()).getId();
    }
    
}
