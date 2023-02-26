package com.jae.prj05.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jae.prj05.model.Board;


public interface BoardRepository extends JpaRepository<Board, Integer>{
	
}