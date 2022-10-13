package com.im.port.service;

import java.util.List;

import com.im.port.vo.dto.ChatMessageDto;
import com.im.port.vo.dto.ChatRoomDto;
import com.im.port.vo.entity.ChatMessageEntity;

public interface IChatMessageService {
	List<ChatMessageDto> getMessagesByChatroomId(ChatRoomDto chatRoomDto) throws Exception;
	ChatMessageEntity postMessage(ChatMessageDto chatMessageDto) throws Exception;
}