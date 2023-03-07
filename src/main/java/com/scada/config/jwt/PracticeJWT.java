package com.scada.config.jwt;

import java.security.Key;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;	

public class PracticeJWT {
	Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
	String jws = Jwts.builder().setSubject("Joe").signWith(key).compact();
	
	/**
	 * 로그인 요청 시 서버에선 시크릿 키를 통해 어세스토큰을 발급해야함
	 * 
	 * [다음 진행 사항]
	 * 1. 시크릿키 만들기?
	 * 2. 이 시크릿 키로 어세스 토큰을 발급 받아 jwt 토큰을 사용자에게 전달 -> 제대로 오는지 확인
	 * 
	 * 사용자는 모든 api요청마다 승인을 받아야지만 통과가능하게끔 해야함
	 *  -> 클라이언트는 api를 요청할 떄 Authorization Header? 에 Access Token을 담아서 보낸다.
	 *  
	 *  서버에서는 사용자가 보낸 토큰을 공개 키(public key)로 서명을 체크하고, 안에 담긴 정보를 확인
	 *  서버는 secret key로 사용자가 보낸 토큰의 서명을 복호화하여서 유효한 토큰인지 확인한다.
	 *  그 이후는 클라이언트가 원하는 요청값 전달
	 * 즉 토큰이 발급되지 않은 상태라면 라우터 이동이 불가능하거나 다시 로그인하거나 하는 방식으로 간다.
	 *  
	 * 단 어세스토큰의 설정을 길게 잡아 둘 시 보안문제 발생, 너무 짧게 잡으면 자주 로그인해야되는문제 발생
	 * 이 단점 보완하고자 [리프레시토큰] 을 만들어야함
	 * 
	 * Refresh Token을 한 달, Access Token 을 하루로 잡았다면
		Access Token의 기간이 다 되어도 Refresh Token의 기간이 남아있기 때문에
		사용자는 로그인 없이 다시 Access Token을 발급 받을 수 있다. (로그인 유지)
		Refresh Token는 Access Token를 다시 발급받기 위한 JWT.
	 */
	
}
