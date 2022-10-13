package com.im.port.config.interceptor;

import java.util.List;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Service;

import com.im.port.repository.OnlineUserRepository;
import com.im.port.vo.dto.OnlineUserDto;
import com.im.port.vo.entity.OnlineUserEntity;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MyChannelInterceptor implements ChannelInterceptor{

	private final OnlineUserRepository onlineUserRepository;

	@Override
	public Message preSend(Message<?> message, MessageChannel channel) {
		System.out.println(" ##### MyChannelInterceptor");
		System.out.println(" ##### Message : "+ message.getHeaders());
		StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
		System.out.println(" ##### accessor : "+accessor);
		System.out.println(" ##### accessor.getSessionId : "+accessor.getSessionId());
		String sessionid = accessor.getSessionId();
		StompCommand command = accessor.getCommand();
		System.out.println(" ##### getCommand : "+command);
		if (command.compareTo(StompCommand.SUBSCRIBE) == 0) {
			System.out.println("in command");
			List<String> userlist = accessor.getNativeHeader("userid");
			System.out.println("userlist---------------------"+userlist);
			String userid = userlist.get(0);
			System.out.println("userid---------------------"+userid);
			OnlineUserDto updateDto = new OnlineUserDto();
			System.out.println("new OnlineUserDto()");
			OnlineUserEntity onlineUserEntity = onlineUserRepository.findByUserid(userid);
			if (onlineUserEntity != null){
				updateDto = onlineUserEntity.toDto();
			}
			updateDto.setUserid(userid);
			updateDto.setSessionid(sessionid);
			onlineUserRepository.save(updateDto.toEntity());
			String destination = accessor.getDestination();
			System.out.println("구독 주소 : " + destination);
			System.out.println(message);
		} else if (command.compareTo(StompCommand.CONNECT) == 0) {
			System.out.println("사용자 연결");
		} else if (command.compareTo(StompCommand.DISCONNECT) == 0) {
			onlineUserRepository.deleteSQLBySessionid(sessionid);
			System.out.println("사용자 연결 해제");
		}
		return message;
	}
}