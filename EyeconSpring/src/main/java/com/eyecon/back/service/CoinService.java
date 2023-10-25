package com.eyecon.back.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.eyecon.back.dto.UserDTO;
import com.eyecon.back.entity.User;
import com.eyecon.back.repository.StoreRepository;
import com.eyecon.back.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CoinService {

	private final UserRepository userRepository;
	
    @Transactional
    public int removeCoin(UserDTO userDTO) {
        // 먼저 사용자를 찾습니다.
        Optional<User> optionalUser = userRepository.findByEmail(userDTO.getEmail());

        // 사용자가 존재하는지 확인합니다.
        if (!optionalUser.isPresent()) {
            throw new IllegalArgumentException("아이디가 없으면?" + userDTO.getEmail());
        }

        User user = optionalUser.get();

        // coin 값을 감소시킵니다.
        user.setCoin(user.getCoin() - 1);

        // 변경된 엔티티를 다시 저장합니다.
        userRepository.save(user);

        // 변경된 coin 값을 반환합니다.
        return user.getCoin();
    }

	public int findCoin(UserDTO userDTO) {
	
        Optional<User> optionalUser = userRepository.findByEmail(userDTO.getEmail());
        
        if (!optionalUser.isPresent()) {
            throw new IllegalArgumentException("아이디가 없으면?" + userDTO.getEmail());
        }
		
        User user = optionalUser.get();
        
        int countedCoin =user.getCoin();
       
        
		return countedCoin;
	}
	
	
}
