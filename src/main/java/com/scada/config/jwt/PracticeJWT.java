package com.scada.config.jwt;

import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;	

@SuppressWarnings("deprecation")
public class PracticeJWT {
	  // 암호화하기 위한 키
	  private static String SECRET_KEY = "secrettestjwttestsecrettestjwttestsecrettestjwttest";
	  // JWT 만료 시간 1시간
	  private static long tokenValidMilisecond = 1000L * 60 * 60;
	  // 실행 함수
	  public static void main(String[] args) {
	    // Program 인스턴스 생성
	    var p = new PracticeJWT();
	    // JWT 토큰 생성 - id는 test
	    var token = p.createToken("test");
	    // 콘솔 출력
	    System.out.println(token);
	    // JWT 토큰 복호화
	    var claims = p.getClaims(token);
	    // JWT 토큰 검증
	    if (claims != null && p.validateToken(claims)) {
	      // id를 취득한다.
	      var id = p.getKey(claims);
	      // Payload 값의 test 키의 값을 취득
	      var test = p.getClaims(claims, "test");
	      // 콘솔 출력
	      System.out.println(id);
	      System.out.println(test);
	    } else {
	      // 토큰이 정합성이 맞지 않으면
	      System.out.println("error");
	    }
	  }
	  // 토큰 생성 함수
	  public String createToken(String key) {
	    // Claims을 생성
	    var claims = Jwts.claims().setId(key);
	    // Payload 데이터 추가
	    claims.put("test", "Hello world");
	    // 현재 시간
	    Date now = new Date();
	    // JWT 토큰을 만드는데, Payload 정보와 생성시간, 만료시간, 알고리즘 종류와 암호화 키를 넣어 암호화 함.
	    return Jwts.builder()
	               .setClaims(claims)
	               .setIssuedAt(now)
	               .setExpiration(new Date(now.getTime() + tokenValidMilisecond))
	               .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
	               .compact();
	  }
	  // String으로 된 코드를 복호화한다.
	  public Jws<Claims> getClaims(String jwt) {
	    try {
	      // 암호화 키로 복호화한다.
	      // 즉 암호화 키가 다르면 에러가 발생한다.
	      return Jwts.parser()
	                 .setSigningKey(SECRET_KEY)
	                 .parseClaimsJws(jwt);
	    }catch(SignatureException e) {
	      return null;
	    }
	  }
	  // 토큰 검증 함수
	  public boolean validateToken(Jws<Claims> claims) {
	    // 토큰 만료 시간이 현재 시간을 지났는지 검증
	    return !claims.getBody()
	                  .getExpiration()
	                  .before(new Date());
	  }
	  // 토큰을 통해 Payload의 ID를 취득
	  public String getKey(Jws<Claims> claims) {
	    // Id 취득
	    return claims.getBody()
	                 .getId();
	  }
	  // 토큰을 통해 Payload의 데이터를 취득
	  public Object getClaims(Jws<Claims> claims, String key) {
	    // 데이터 취득
	    return claims.getBody()
	                 .get(key);
	  }
	
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
