package com.project.level2_bookreview.domain;

import com.sun.istack.NotNull;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import lombok.Data;

/**
 * User
 */
@Data
@Entity
public class User {
	@GeneratedValue
	private Long id;

	@NotNull
	private String email;

	@NotNull
	private String password;
	
	@NotNull
	private String nickname;
}
