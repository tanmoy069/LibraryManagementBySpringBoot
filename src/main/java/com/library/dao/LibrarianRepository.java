package com.library.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.model.Librarian;

public interface LibrarianRepository extends JpaRepository<Librarian, Long> {
	
	Librarian findByEmail(String email);

}
