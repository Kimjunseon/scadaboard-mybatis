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
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
	
	@Column(unique = true)
	private String id;
	
	private String password;
	
	@Column(unique = true)
	private String email;
	
	private String name;
	private String birth;
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
