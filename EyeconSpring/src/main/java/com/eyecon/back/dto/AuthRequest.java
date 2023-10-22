package com.eyecon.back.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {
	private String email;
	private String password;
	
	public String email() {
		
		return this.email;
	}

	public String password() {
		// TODO Auto-generated method stub
		return this.password;
	}
}
