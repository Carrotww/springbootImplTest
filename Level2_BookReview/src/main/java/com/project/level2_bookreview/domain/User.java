package com.project.level2_bookreview.domain;

import lombok.Data;
import jakarta.persistence.*;

/**
 * User
 */
@Data
@Entity
public class User {
	@GeneratedValue
	private Long id;

	private String email;

	private String password;
	
	private String nickname;
}
