package com.eyecon.back.entity;


import com.eyecon.back.dto.UserDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name="user")
public class User {
	//이메일
	@Id
    private String email;

    // 비밀번호. 비밀번호
	@Column
	private String pw;

    // 보유코인수. 보유코인수
	@Column
	private Integer coin;

    // 권한분류. 권한분류
	@Column
	private String role;

    // 최근로그인일시. 최근로그인날짜
	@jakarta.persistence.Column
	private String date;
	
    public static User toUser(UserDTO userDTO) {
    	User user = new User();
    	user.setEmail(userDTO.getEmail());
    	user.setPw(userDTO.getPw());
    
        return user;
    }
}
