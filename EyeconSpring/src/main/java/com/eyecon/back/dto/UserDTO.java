package com.eyecon.back.dto;

import java.time.LocalDateTime;

import com.eyecon.back.entity.User;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

	private Long id;
    private String email;
    private String password;
    private int coin;
    private String role;
    private LocalDateTime date;
    
	
    
    public static UserDTO toUserDTO(User user) {
    	UserDTO userDTO = new UserDTO();
    	
    	userDTO.setId(user.getId());
    	userDTO.setEmail(user.getEmail());
    	userDTO.setPassword(user.getPassword());
    	userDTO.setCoin(user.getCoin());
    	userDTO.setRole(user.getRole());
    	userDTO.setDate(user.getDate());
        return userDTO;
    }


	
}
