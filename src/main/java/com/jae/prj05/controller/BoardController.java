package com.jae.prj05.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.jae.prj05.service.BoardService;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@GetMapping({"/",""})
	public String index(
						Model model
					, @PageableDefault(size=2, sort="id", direction = Sort.Direction.DESC) Pageable pageablel
				){
		model.addAttribute("boards", boardService.글목록(pageablel));
		return "index";
	}
	@GetMapping("/board/{id}")
	public String findById(@PathVariable int id, Model model) {
		model.addAttribute("board", boardService.글상세보기(id));
		return "board/detail";
	}
	
	///board/${board.id}/updateForm
	// Model : 이거 옛날에 썼던 그거임.(데이터 + 화면)
		@GetMapping("/board/{id}/updateForm")
		public String updateForm(@PathVariable int id, Model model) {
			model.addAttribute("board", boardService.글상세보기(id));
			return "board/updateForm";
		}
	
	@GetMapping("/board/saveForm")
	public String saveForm() {
		return "board/saveForm";
	}
	
	

}
