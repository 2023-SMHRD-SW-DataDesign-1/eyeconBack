package com.eyecon.back.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.eyecon.back.configuration.JwtService;
import com.eyecon.back.dto.ResultDTO;
import com.eyecon.back.dto.StoreDTO;
import com.eyecon.back.entity.Result;
import com.eyecon.back.entity.Store;
import com.eyecon.back.entity.User;
import com.eyecon.back.repository.StoreRepository;
import com.eyecon.back.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StoreService {

	private final StoreRepository storeRepository ;
	
	@Transactional
	public List findStore(StoreDTO storeDTO) {
		System.out.println("storeService.findStore");
		Store store = new Store();
		

        // Store의 user 필드에 User 설정
        store.setEmail(storeDTO.getEmail());
		
		
		List<Store> storeList = storeRepository.findAllByEmail(store.getEmail());
		System.out.println("이미지출력 리스트[0] : "+ storeList.get(0));

		

		
		return storeList;
	}
	
//	@Transactional
//    public List<String> findStore(StoreDTO storeDTO) {
//        System.out.println("storeService.findStore");
//
//        // 이메일로 Store 엔티티의 storeName 찾기
//        List<String> storeName = storeRepository.findStoreNameByUser_Email(storeDTO.getEmail());
//
//        System.out.println("Store names: " + storeName);
//
//        return storeName;
//    }



}
