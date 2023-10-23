package com.eyecon.back.repository;

import java.util.List;

import com.eyecon.back.entity.Token;



public interface TokenRepositoryCustom  {
	List<Token> findAllValidTokenByUserId(String userName);
}
