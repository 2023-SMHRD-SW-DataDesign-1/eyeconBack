package com.eyecon.back.entity;


import javax.persistence.Column;

import org.springframework.security.core.userdetails.UserDetails;

import com.eyecon.back.dto.UserDTO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@javax.persistence.Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User  {
	//이메일
	@javax.persistence.Id
	
    private String email;

    // 비밀번호. 비밀번호
	@javax.persistence.Column
	private String pw;

    // 보유코인수. 보유코인수
	@Column
	private Integer coin;

    // 권한분류. 권한분류
	@Column
	private String role;

    // 최근로그인일시. 최근로그인날짜
	@Column
	private String date;
	
    public static User toUser(UserDTO userDTO) {
    	User user = new User();
    	user.setEmail(userDTO.getEmail());
    	user.setPw(userDTO.getPw());
    
        return user;
    }
}
