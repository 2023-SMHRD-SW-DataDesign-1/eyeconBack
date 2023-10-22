package com.eyecon.back.dto;

import com.eyecon.back.entity.User;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor

public class UserDTO {

    private String email;
    private String password;
    private Integer coin;
    private String role;
    private String date;
    
	
    
    public static UserDTO toUserDTO(User user) {
    	UserDTO userDTO = new UserDTO();
    	userDTO.setEmail(user.getEmail());
    	userDTO.setPassword(user.getPassword());
    	userDTO.setCoin(user.getCoin());
    	userDTO.setRole(user.getRole());
    	userDTO.setDate(user.getDate());
        return userDTO;
    }
	
}
