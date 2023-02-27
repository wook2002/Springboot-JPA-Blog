package com.jae.prj05.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jae.prj05.config.auth.PrincipalDetail;
import com.jae.prj05.dto.ResponseDto;
import com.jae.prj05.model.Board;
import com.jae.prj05.service.BoardService;

@RestController
public class BoardApiController {
	
	@Autowired
	private BoardService boardService;
	
	@PostMapping("/api/board")
	public ResponseDto<Integer> save(
			@RequestBody Board board
			, @AuthenticationPrincipal PrincipalDetail principal
			// PrincipalDetail이 가지고 있는 데이터 가져옴
	){
		System.out.println("==BoardApiController==");
		boardService.글쓰기(board, principal.getUser()); 
		//=> User user로 받음
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
	
	@DeleteMapping("/api/board/{id}")
	public ResponseDto<Integer> deleteById(@PathVariable int id){
			boardService.글삭제하기(id);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
	
	// @DeleteMapping주소가 같아도 ok (요청하는 메서드가 다름(pub,delete)
	@PutMapping("/api/board/{id}")
	public ResponseDto<Integer> update(
				@PathVariable int id
				, @RequestBody Board board
			){
		System.out.println("==========test==========");
		System.out.println("PutMapping : " + id );
		System.out.println("PutMapping : " + board); 
				boardService.글수정하기(id, board);
				return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
	
}
