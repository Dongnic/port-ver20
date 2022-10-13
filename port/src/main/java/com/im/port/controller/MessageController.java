package com.im.port.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.im.port.service.ChatMessageServiceImpl;
import com.im.port.vo.dto.ChatMessageDto;
import com.im.port.vo.entity.ChatMessageEntity;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "*")
@Slf4j
@RequiredArgsConstructor
@RestController
public class MessageController {
	private final ChatMessageServiceImpl chatMessageService;
	private final SimpMessagingTemplate template;

	@MessageMapping("/message")
	public void sendMessage(@Payload ChatMessageDto chatMessage) throws Exception {
		log.info("전달 메세지 : " + chatMessage);
		
		ChatMessageEntity chatMessageEntity = chatMessageService.postMessage(chatMessage);
		System.out.println("chatMessage : " + chatMessageEntity);
		System.out.println("chatMessage.getChatroomid() : " + chatMessage.getChatroomid().getId());
		System.out.println("chatMessageEntity.toDto().getChatroomid().getId() : " + chatMessageEntity.toDto().getChatroomid().getId());
		template.convertAndSend("/sub/" + chatMessageEntity.toDto().getChatroomid().getId(), chatMessageEntity.toDto());
	}
}
