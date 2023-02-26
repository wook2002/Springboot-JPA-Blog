package com.jae.prj05.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jae.prj05.model.Board;
import com.jae.prj05.model.User;
import com.jae.prj05.repository.BoardRepository;


@Service
public class BoardService {
	
	@Autowired
	private BoardRepository boardRepository;
	
	@Transactional  
	public void 글쓰기(Board board, User user) { // title, content
		board.setCount(0);
		board.setUser(user);
		boardRepository.save(board);
	}
}