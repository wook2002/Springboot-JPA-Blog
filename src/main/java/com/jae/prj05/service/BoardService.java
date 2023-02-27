package com.jae.prj05.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
	
	@Transactional(readOnly = true)
	public Page<Board> 글목록(Pageable pageable) {
		return boardRepository.findAll(pageable);
	}

	@Transactional(readOnly = true)
	public Board 글상세보기(int id) {
		return boardRepository.findById(id)
				.orElseThrow(()->{
						return new IllegalArgumentException("글 상세보기 실패 : 아이디를 찾을 수가 없습니다.");
				});
	}

	@Transactional
	public void 글삭제하기(int id) {
		boardRepository.deleteById(id);
	}

	@Transactional  
	public void 글수정하기(int id, Board requestBoard) {
		// === 영속화 시켰다=== ★
		// (DB데이터 -> java객체로 들어감) 
		// (DB랑 데이터가 동기화되어 있음)
		Board board = boardRepository.findById(id) 
				.orElseThrow(()->{
					return new IllegalArgumentException("글 찾기 실패 : 아이디를 찾을 수가 없습니다.");
			});
		// 이름 이런 식으로 짓는거 좋은듯(requestBoard)
		board.setTitle(requestBoard.getTitle());
		board.setContent(requestBoard.getContent());
		// 해당 함수로 종료 시(Service가 종료될 때),
		// 트랜잭션이 종료됨.  이때
		// == ''더티체킹'' (영속화 되어 있는 board객체가 달라졌기에) ★
		// => 자동업데이트 됨(DB쪽으로 flush 된다)(commit도 됨)★
	}	

}