package com.eyecon.back.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.eyecon.back.dto.StoreDTO;
import com.eyecon.back.dto.UserDTO;
import com.eyecon.back.entity.Store;
import com.eyecon.back.entity.User;
import com.eyecon.back.repository.StoreRepository;
import com.eyecon.back.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;
	private final StoreRepository storeRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void join(UserDTO userDTO, StoreDTO storeDTO) {
        // 1. dto -> entity 변환
        User user = User.toUser(userDTO);

        // 2. 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPw(encodedPassword);

        // 3. repository의 save 메서드 호출
        userRepository.save(user);
        Store store = Store.toStore(storeDTO);
//        Store store = Store.toStore()
//        storeDTO.setEmail(user.getEmail());
//        storeRepository.save(store);
        
//         store.setUser(user.getEmail());
        storeRepository.save(store);
        
    }
    
    public boolean checkEmail(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        return userOptional.isPresent();
    }
    
	@Transactional
	public int removeCoin(String email) {
	   UserDTO userDTO = new UserDTO();
		// 먼저 사용자를 찾습니다.
	    User user = userRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + email));
	    
	    // coin 값을 감소시킵니다.
	    user.setCoin(user.getCoin() - 1);
	    
	    // 변경된 엔티티를 다시 저장합니다.
	    userRepository.save(user);
	    
	    // 변경된 coin 값을 반환합니다.		
		return userDTO.getCoin();
	}
	


	

}
