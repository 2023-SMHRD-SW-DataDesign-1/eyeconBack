package com.eyecon.back.service;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.eyecon.back.dto.UserDTO;
import com.eyecon.back.entity.User;
import com.eyecon.back.repository.UserRepository;

import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void join(UserDTO userDTO) {
        // 1. dto -> entity 변환
        User user = User.toUser(userDTO);

        // 2. 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        // 3. repository의 save 메서드 호출
        userRepository.save(user);
    }
	

}
