package com.scada.Dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor

public class UserDto {
	// 컨트롤러별로 로직이 다를 때, 회원가입 시나 비밀번호 수정 시에는 어떻게 구현해야 될 지?  
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
	
	
	@NotNull
	@Size(min=1, max=50, message = "아이디 오류!")
	@Column(unique = true)
	private String id;
	
	@NotNull
	@Size(min=8, max=50, message = "비밀번호 오류!")
	private String password;
	
	@Column(unique = true)
	private String email;
	
	private String name;
	private String birth;
	
	@NotNull
	@Size(min=8, max=50, message = "전화번호 오류!")
	private String phoneNumber;
	private String profileUrl;
	
	@ElementCollection(fetch = FetchType.EAGER) //roles 컬렉션
    @Builder.Default
	private List<Long> role = new ArrayList<>();
	
//	public UserDto(){
//		
//	}
//	
//	public UserDto(Long user_id, String id, String password, String email,
//			String name, String birth, String phonenumber, String profileurl,
//			Boolean role) {
//		this.userId = user_id;
//		this.id = id;
//		this.password = password;
//		this.email = email;
//		this.name = name;
//		this.birth = birth;
//		this.phoneNumber = phonenumber;
//		this.profileUrl = profileurl;
//		this.role = role;	
//	}
//	
//	@Override
//	public String toString() {
//		return "User [user_id=" + userId + ", id=" + id + ", password=" + password + ", email=" + email + ", name="
//				+ name + ", birth=" + birth + ", phone_number=" + phoneNumber + ", profile_url=" + profileUrl
//				+ ", role=" + role + "]";
//	}
//	
	
}
