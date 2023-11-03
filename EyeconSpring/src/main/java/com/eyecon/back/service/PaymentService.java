package com.eyecon.back.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.common.base.VerifyException;

import com.eyecon.back.configuration.JwtService;
import com.eyecon.back.dto.PaymentInfo;

import com.eyecon.back.entity.Coin;
import com.eyecon.back.entity.Payment.PaymentBuilder;
import com.eyecon.back.entity.User;
import com.eyecon.back.repository.CoinRepository;
import com.eyecon.back.repository.PaymentRepository;
import com.eyecon.back.repository.UserRepository;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;

import lombok.RequiredArgsConstructor;




@Service
@RequiredArgsConstructor
public class PaymentService {
	
	private final CoinRepository coinRepository;
	private final UserRepository userRepository;
	private final PaymentRepository paymentRepository;
	private final JwtService jwtService;
	

	// portone key
	@Value("impKey")
	private String impKey;
	@Value("impSecretKey")
	private String impSecret;

	@Transactional
	public void verifyIamportService(IamportResponse<Payment> irsp, PaymentInfo payment, String token) {
		// 실제 결제된 금액과 front에서 받은 payment값이 일치하는지 확인
		if(irsp.getResponse().getAmount().intValue()!=payment.getPrice()) {
			throw new VerifyException();
		}
		
		// coin테이블에 실제 결제된 금액에 해당되는 상품을 가져와서 실제 결제된 금액과 동일한지 확인
		Coin c = coinRepository.findByPrice(irsp.getResponse().getAmount().intValue());
		if(payment.getPrice()!=c.getPrice()) {
			throw new VerifyException();
		}
		
		// 토큰에서 사용자 이메일 추출
		String userEmail = jwtService.extractUsername(token);
		
		
		// 결제된 데이터를 DB payment테이블에 저장
		com.eyecon.back.entity.Payment paymentInfo = com.eyecon.back.entity.Payment.builder()
				.category(irsp.getResponse().getPayMethod())
				.coinId(c.getId())
				.date(payment.getDate())
				.time(payment.getTime())
				.email(userEmail)
				.finance(payment.getFinance())
				.number(payment.getNumber())
				.price(payment.getPrice())
				.coin(c.getCoinCnt())
				.build();
		paymentRepository.save(paymentInfo);
		
		// user 테이블에 기존코인수 + 결제한 코인수 UPDATE
		User user = userRepository.findByEmail(userEmail).get();
		user.setCoin(user.getCoin()+c.getCoinCnt());
		userRepository.save(user);
		
	}

	public List<com.eyecon.back.entity.Payment> selectAllPayment(String email) {
		return paymentRepository.findByEmail(email);
		
		
	}



}