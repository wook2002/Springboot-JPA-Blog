package com.jae.prj05.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
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
			@RequestBody Board board,
			@AuthenticationPrincipal PrincipalDetail principal
			// PrincipalDetail이 가지고 있는 데이터 가져옴
			) {
		System.out.println("==BoardApiController==");
		boardService.글쓰기(board, principal.getUser()); 
		//=> User user로 받음
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
	
}
