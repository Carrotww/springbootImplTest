package com.project.level2_bookreview.domain;

import jakarta.persistence.Entity;
import lombok.Data;

/**
 * User
 */
@Data
@Entity
public class User {
	private String email;

	private String password;
	
	private String nickname;
}
