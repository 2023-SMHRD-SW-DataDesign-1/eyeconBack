package com.eyecon.back.entity;


import java.time.LocalDateTime;
import java.util.Collection;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.eyecon.back.dto.UserDTO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements UserDetails {

	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	//이메일
	@Column(unique = true)
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
	@Column
	private LocalDateTime date;
	
    public static User toUser(UserDTO userDTO) {
    	User user = new User();
    	user.setEmail(userDTO.getEmail());
    	user.setPw(userDTO.getPw());
    
        return user;
    }

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}


	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.email;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.pw;
	}


}
