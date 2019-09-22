package com.library.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "L_LIBRARIAN")
public class Librarian {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "librn_id", updatable = false)
	private long librnId;
	
	@Column(name = "librn_name")
	private String libName;
	
	@Column(name = "designation")
	private String designation;
	
	@Column(unique = true, name = "email")
	private String email;
	
	@Column(name = "password")
	private String password;

}
