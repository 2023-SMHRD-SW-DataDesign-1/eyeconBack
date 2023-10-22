package com.eyecon.back.repository;

import java.util.List;

import com.eyecon.back.entity.Tokens;



public interface TokenRepositoryCustom  {
	List<Tokens> findAllValidTokenByUserId(String userName);
}
