package com.eyecon.back.service;

import java.util.NoSuchElementException;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.eyecon.back.configuration.JwtService;
import com.eyecon.back.dto.JoinDTO;
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
	private final JwtService jwtService;

	@Transactional
	public void join(JoinDTO joinDTO) {
		// User 엔티티 생성
		User user = new User();
		user.setId(joinDTO.getId());
		user.setEmail(joinDTO.getEmail());
		user.setPw(joinDTO.getPw());
		user.setCoin(joinDTO.getCoin() + 1);

		// 비밀번호 암호화

		String encodedPassword = passwordEncoder.encode(joinDTO.getPw());
		user.setPw(encodedPassword);

		// User 엔티티 저장
		userRepository.save(user);

		// Store 엔티티 생성
		Store store = new Store();
		store.setId(joinDTO.getId());
		store.setEmail(joinDTO.getEmail()); // User 엔티티 참조 설정
		store.setStoreName(joinDTO.getStoreName());
		store.setCategory(joinDTO.getCategory());
		store.setPlace1(joinDTO.getPlace1());
		store.setPlace2(joinDTO.getPlace2());
		store.setDong(joinDTO.getDong());

		// Store 엔티티 저장
		storeRepository.save(store);
	}

	public boolean checkEmail(String email) {
		Optional<User> userOptional = userRepository.findByEmail(email);
		return userOptional.isPresent();
	}

	public void findUser(User user) {
		/*
		 * System.out.println("asdfasdfdsf"); System.out.println(user.getEmail());
		 * System.out.println(user.getPw()); String encodedPassword =
		 * passwordEncoder.encode(user.getPw()); user.setPw(encodedPassword);
		 * System.out.println(encodedPassword); return
		 * userRepository.findByEmailAndPw(user.getEmail(),user.getPw()).orElseThrow(()-
		 * > new UsernameNotFoundException("The user does not exist"));
		 */
		Optional<User> u = Optional.ofNullable(userRepository.findByEmail(user.getEmail())
				.orElseThrow(() -> new UsernameNotFoundException("The user does not exist")));

		if (u.isPresent()) {
			if (!passwordEncoder.matches(user.getPassword(), u.get().getPassword())) {
				throw new BadCredentialsException("password error");
			}

		}

	}

	@Transactional
	public void updatePw(String accessToken, String updatePw) {
		String eamil = jwtService.extractUsername(accessToken);
		String encodedPassword = passwordEncoder.encode(updatePw);
		Optional<User> user = userRepository.findByEmail(eamil);

		if (user.isPresent()) {
			User userInfo = user.get();
			userInfo.setPw(encodedPassword);
			userRepository.save(userInfo);
			System.out.println("비밀번호 변경 완료");
		}
	}

}
