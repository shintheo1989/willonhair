package com.shintheo.willonhair.model;

import com.shintheo.willonhair.base.Gender;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
	
	private String firstname;
	private String lastname;
	private String email;
	private String password;
	private Gender gender;
	private String phone;
}
