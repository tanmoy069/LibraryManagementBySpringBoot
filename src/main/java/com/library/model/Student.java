package com.library.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "L_STUDENT")
public class Student {
	
	@Id
	@Column(name = "std_id")
	private long studentId;
	
	@Column(name = "std_name")
	private String studentName;
	
	@Column(name = "std_addr")
	private String studentAddr;
	
	@Column(name = "phone")
	private long phone;
}
