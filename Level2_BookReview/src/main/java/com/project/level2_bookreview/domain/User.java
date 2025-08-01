package com.project.level2_bookreview.domain;

import lombok.Data;
import jakarta.persistence.*;

/**
 * User
 */
@Data
@Entity
@Table(name = "users")
public class User {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String email;

	private String password;
	
	private String nickname;
}
