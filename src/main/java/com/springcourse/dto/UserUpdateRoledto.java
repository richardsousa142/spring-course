package com.springcourse.dto;

import javax.validation.constraints.NotNull;

import com.springcourse.domain.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class UserUpdateRoledto {
	
	@NotNull(message = "Role required")
	private Role roleOfUser;
	
	@Override
	public String toString() {
		return "UserUpdateRoledto [role=" + roleOfUser + "]";
	}
}
