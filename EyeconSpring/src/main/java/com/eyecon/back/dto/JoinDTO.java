package com.eyecon.back.dto;


import com.eyecon.back.entity.Store;
import com.eyecon.back.entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JoinDTO {

	private Long id;
    private String email; 
    private String pw;
    private int coin;
	private String storeName;
	private String category; //편의점인지 마트인지
	private String place1;
	private String place2;
	
	
    public static JoinDTO toJoinDTO(User user, Store store) {
    	JoinDTO joinDTO = new JoinDTO();
    	
    	
    	joinDTO.setId(user.getId());
    	joinDTO.setId(store.getId());
    	joinDTO.setEmail(user.getEmail());
    	joinDTO.setStoreName(store.getStoreName());
    	joinDTO.setPw(user.getPw());
    	joinDTO.setCoin(user.getCoin());
        return joinDTO;
    }
	
	
}
