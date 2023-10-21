package com.eyecon.back.service;

import com.eyecon.back.dto.UserDTO;
import com.eyecon.back.entity.User;
import com.eyecon.back.repository.UserRepository;

import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;
	 
	public void join(UserDTO userDTO) {
        // 1. dto -> entity 변환
        // 2. repository의 save 메서드 호출
        User user = User.toUser(userDTO);
        userRepository.save(user);
        // repository의 save메서드 호출 (조건. entity객체를 넘겨줘야 함)
    }	
	
	

}
