package com.library.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
	
	Student findByStudentId(Long id);

}
